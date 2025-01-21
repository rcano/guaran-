package apricot
package skia

import apricot.graphics.GraphicsStack
import apricot.tools.GlfwWindow
import guarana.*
import io.github.humbleui.skija
import org.lwjgl.opengl.{GL11, GL30}
import org.lwjgl.opengl.GL

class SkiaGraphicsStack extends GraphicsStack {
  type Color = Int
  type Image = skija.Image
  type GraphicsContext = SurfaceBaseSkiaGraphicsContext

  override protected object imageSupport extends ImageSupport {

    override def load(bytes: Array[Byte], format: String): Image = ???

    override given managedImage: Managed[Image] with {
      override inline def close(r: Image): Unit = r.close()
    }
  }

  override protected val colorLike: ColorLike[Int] = new ColorLike[Int] {
    def apply(r: Float, g: Float, b: Float, a: Float = 1f) = rgba((r * 255).toInt, (g * 255).toInt, (b * 255).toInt, (a * 255).toInt)
    def rgba(r: Int, g: Int, b: Int, a: Int = 255) = skija.Color.makeARGB(a, r, g, b)
    def argb(argb: Int): Int = argb

    extension (c: Color) {
      def red: Int = skija.Color.getR(c)
      def green: Int = skija.Color.getG(c)
      def blue: Int = skija.Color.getB(c)
      def alpha: Int = skija.Color.getA(c)
      def asRgba: Int = c
      def asArgb: Int = c
    }
  }

  trait SurfaceBaseSkiaGraphicsContext extends GraphicsStack.GraphicsContext{
    def surface: skija.Surface
    def canvas: skija.Canvas

    override def renderLayers(layers: Iterable[Layer], clearFirst: Boolean, color: Int): Unit = {
      if (clearFirst) canvas.clear(color)
      layers.foreach(_.draw(SkiaGraphicsStack.this, this))
      surface.flush()
    }
  }

  private val initializedWindows = scala.collection.concurrent.TrieMap.empty[SkiaGraphicsContext, Unit]
  class SkiaGraphicsContext private[SkiaGraphicsStack] (window: GlfwWindow) extends SurfaceBaseSkiaGraphicsContext {
    initializedWindows(this) = ()
    private[SkiaGraphicsStack] val glCapabilities = {
      org.lwjgl.glfw.GLFW.glfwMakeContextCurrent(window.windowHandle)
      GL.createCapabilities().unn
    }
    private[SkiaGraphicsStack] lazy val skiaContext = skija.DirectContext.makeGL()
    private[SkiaGraphicsStack] var _surface: skija.Surface | Null = null

    def surface: skija.Surface = _surface.unn
    def canvas: skija.Canvas = _surface.unn.getCanvas()

    override def renderLayers(layers: Iterable[Layer], clearFirst: Boolean, color: Int): Unit = {
      org.lwjgl.glfw.GLFW.glfwMakeContextCurrent(window.windowHandle)
      super.renderLayers(layers, clearFirst, color)
    }

    private[SkiaGraphicsStack] var closed = false
    override def close(): Unit = {
      initializedWindows.remove(this)
      closed = true
      _surface.?(_.close())
      skiaContext.abandon()
    }

    private[SkiaGraphicsStack] def resizeSurface(width: Int, height: Int)(using VarContext): Unit = {
      org.lwjgl.glfw.GLFW.glfwMakeContextCurrent(window.windowHandle)
      val fbId = GL11.glGetInteger(GL30.GL_FRAMEBUFFER_BINDING)
      scribe.debug(s"Resizing skia surface to $width x $height")

      _surface.?(_.close())

      val renderTarget =
        skija.BackendRenderTarget.makeGL(width, height, /*samples*/ 0, /*stencil*/ 8, fbId, skija.FramebufferFormat.GR_GL_RGBA8)

      _surface = skija.Surface.makeFromBackendRenderTarget(
        skiaContext,
        renderTarget,
        skija.SurfaceOrigin.BOTTOM_LEFT,
        skija.SurfaceColorFormat.RGBA_8888,
        skija.ColorSpace.getSRGB()
      )
    }
  }
  override def setup(window: GlfwWindow): SkiaGraphicsContext = window.tk.update {
    val res = SkiaGraphicsContext(window)
    val (width, height) = window.size()
    res.resizeSurface(width, height)
    window.varUpdates := EventIterator.takeWhile(_ => !res.closed).foreach {
      case window.size(_, (width, height)) => res.resizeSurface(width, height)
      case _ =>
    }
    res
  }

  override def shutdown(): Unit = {
    initializedWindows.keys.foreach(_.close())
    initializedWindows.clear()
  }

}
