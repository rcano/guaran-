package apricot
package tools

import guarana.*
import io.github.humbleui.skija.{
  BackendRenderTarget,
  Canvas,
  ColorSpace,
  DirectContext,
  FramebufferFormat,
  Surface,
  SurfaceColorFormat,
  SurfaceOrigin
}
import org.lwjgl.glfw.*, GLFW.*
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.*
import scala.compiletime.uninitialized

class GlfwWindow(engine: ApricotEngine[? <: AbstractToolkit])(using tk: AbstractToolkit) {
  import GlfwWindow.*

  val windowHandle = {
    GlfwWindow // init statics
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
    glfwCreateWindow(100, 100, "", NULL, NULL)
  }
  require(windowHandle != NULL, "window creation failed")

  val glCapabilities = {
    glfwMakeContextCurrent(windowHandle)
    GL.createCapabilities()
  }

  // Create Skia OpenGL context
  // Do once per app launch
  val skiaContext = DirectContext.makeGL()
  private var surface: Surface | Null = null
  private var canvas: Canvas = uninitialized

  private inline given ValueOf[this.type] = ValueOf(this)

  tk.update {
    this.varUpdates := EventIterator.foreach {
      case this.title(_, title) => glfwSetWindowTitle(windowHandle, title)
      case this.visible(_, v) => setVisible(v)
      case _ =>
    }
  }
  private val windowPosListener: GLFWWindowPosCallbackI = (_, x, y) => tk.update(summon[VarContext].externalPropertyUpdated(position, None))
  private val windowSizeListener: GLFWWindowSizeCallbackI = (_, w, h) => {
    tk.update(summon[VarContext].externalPropertyUpdated(size, None))
    updateSurface(w, h)
  }
  private val windowIconifiedListener: GLFWWindowIconifyCallbackI = (_, b) =>
    tk.update(summon[VarContext].externalPropertyUpdated(iconified, Some(!b)))
  // private val windowFramebufferSizeListener: GLFWFramebufferSizeCallbackI = (_, w, h) => ???
  glfwSetWindowSizeCallback(windowHandle, windowSizeListener)
  glfwSetWindowPosCallback(windowHandle, windowPosListener)
  glfwSetWindowIconifyCallback(windowHandle, windowIconifiedListener)

  def position = Position.forInstance(this)
  def size = Size.forInstance(this)
  def title = Title.forInstance(this)
  def visible = Visible.forInstance(this)
  def iconified = Iconified.forInstance(this)

  private def setVisible(v: Boolean): Unit = if (v) {
    glfwMakeContextCurrent(windowHandle)
    glfwSwapInterval(1) // we use an interval of 1ms because we expect v-sync to kick in
    glfwShowWindow(windowHandle)
  } else {
    glfwHideWindow(windowHandle)
  }

  private def updateSurface(width: Int, height: Int): Unit = {
    scribe.debug(s"Resizing skia surface to $width x $height")
    // Create render target, surface and retrieve canvas from it
    val fbId = GL11.glGetInteger(0x8ca6) // GL_FRAMEBUFFER_BINDING, yes, that magic constant, comes from somewhere
    val renderTarget = BackendRenderTarget.makeGL(width, height, /*samples*/ 0, /*stencil*/ 8, fbId, FramebufferFormat.GR_GL_RGBA8)

    surface.?(_.close())

    surface = Surface.makeFromBackendRenderTarget(
      skiaContext,
      renderTarget,
      SurfaceOrigin.BOTTOM_LEFT,
      SurfaceColorFormat.RGBA_8888,
      ColorSpace.getSRGB()
    )
    canvas = surface.unn.getCanvas()
    engine.setRenderingSurface(surface.unn, canvas)
  }
}

object GlfwWindow {
  // initialize GLFW only once
  GLFWErrorCallback.createPrint(System.err).unn.set()
  assert(glfwInit(), "failed to initialize GLFW")

  val Position = ExternalVar[GlfwWindow, (Int, Int)]("position", getWindowPosition, setWindowPosition)
  val Size = ExternalVar[GlfwWindow, (Int, Int)]("size", getWindowSize, setWindowSize)
  val Title = Var[String]("title", "", true)
  val Visible = Var[Boolean]("visible", false, true)
  val Iconified = ExternalVar[GlfwWindow, Boolean]("iconified", isWindowIconified, setWindowIconified)

  private def getWindowPosition(w: GlfwWindow): (Int, Int) =
    val xs, ys = new Array[Int](1)
    glfwGetWindowPos(w.windowHandle, xs, ys)
    (xs(0), ys(0))
  private def setWindowPosition(w: GlfwWindow, s: (Int, Int)): Unit =
    glfwSetWindowPos(w.windowHandle, s._1, s._2)

  private def getWindowSize(w: GlfwWindow): (Int, Int) =
    val xs, ys = new Array[Int](1)
    glfwGetWindowSize(w.windowHandle, xs, ys)
    (xs(0), ys(0))
  private def setWindowSize(w: GlfwWindow, s: (Int, Int)): Unit =
    glfwSetWindowSize(w.windowHandle, s._1, s._2)

  private def isWindowIconified(w: GlfwWindow): Boolean = glfwGetWindowAttrib(w.windowHandle, GLFW_ICONIFIED) == 1
  private def setWindowIconified(w: GlfwWindow, iconified: Boolean): Unit =
    if (iconified) glfwIconifyWindow(w.windowHandle)
    else glfwRestoreWindow(w.windowHandle)
}
