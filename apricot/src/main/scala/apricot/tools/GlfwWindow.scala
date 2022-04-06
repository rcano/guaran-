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
      case this.vsync(_, useVsync) => glfwSwapInterval(if useVsync then 1 else 0)
      case _ =>
    }
  }

  private val windowPosListener: GLFWWindowPosCallbackI = (_, x, y) => tk.update(summon[VarContext].externalPropertyUpdated(position, None))
  private val windowSizeListener: GLFWWindowSizeCallbackI = (_, w, h) => {
    updateSurface(w, h)
    tk.update(summon[VarContext].externalPropertyUpdated(size, None))
  }
  private val windowIconifiedListener: GLFWWindowIconifyCallbackI = (_, b) =>
    tk.update(summon[VarContext].externalPropertyUpdated(iconified, Some(!b)))
  private val windowKeyListener: GLFWKeyCallbackI = (_, key, scancode, action, mods) =>
    tk.update(summon[Emitter.Context].emit(keyEvents, KeyEvent(key.asInstanceOf, InputAction.of(action), mods.asInstanceOf, scancode)))
  private val windowCharListener: GLFWCharCallbackI = (_, codepoint) =>
    tk.update(summon[Emitter.Context].emit(charInputEvents, codepoint.toChar))
  private val windowCursorEnterListener: GLFWCursorEnterCallbackI = (_, entered) =>
    tk.update {
      this.mousePosition := (-1.0, -1.0)
      summon[Emitter.Context].emit(mouseEvents, if entered then MouseEvent.Entered else MouseEvent.Exited)
    }
  private val windowCursorPosListener: GLFWCursorPosCallbackI = (_, x, y) =>
    tk.update {
      this.mousePosition := (x, y)
      summon[Emitter.Context].emit(mouseEvents, MouseEvent.Moved(x, y))
    }
  private val windowMouseButtonListener: GLFWMouseButtonCallbackI = (_, btn, action, mods) =>
    tk.update(summon[Emitter.Context].emit(mouseEvents, MouseEvent.Button(btn.asInstanceOf, InputAction.of(action).asInstanceOf, mods.asInstanceOf)))
  private val windowScrollListener: GLFWScrollCallbackI = (_, xoffset, yoffset) =>
    tk.update(summon[Emitter.Context].emit(mouseEvents, MouseEvent.Scrolled(xoffset, yoffset)))
  private val windowFocusListener: GLFWWindowFocusCallbackI = (_, focused) => tk.update(this.focused := focused)
  private val windowCloseListener: GLFWWindowCloseCallbackI = _ => ()
  private val windowDropListener: GLFWDropCallbackI = (_, count, filePathes) => ()
  // private val windowFramebufferSizeListener: GLFWFramebufferSizeCallbackI = (_, w, h) => ???
  glfwSetWindowSizeCallback(windowHandle, windowSizeListener)
  glfwSetWindowPosCallback(windowHandle, windowPosListener)
  glfwSetWindowIconifyCallback(windowHandle, windowIconifiedListener)
  glfwSetKeyCallback(windowHandle, windowKeyListener)
  glfwSetCharCallback(windowHandle, windowCharListener)
  glfwSetCursorEnterCallback(windowHandle, windowCursorEnterListener)
  glfwSetCursorPosCallback(windowHandle, windowCursorPosListener)
  glfwSetMouseButtonCallback(windowHandle, windowMouseButtonListener)
  glfwSetScrollCallback(windowHandle, windowScrollListener)
  glfwSetWindowFocusCallback(windowHandle, windowFocusListener)
  glfwSetWindowCloseCallback(windowHandle, windowCloseListener)
  glfwSetDropCallback(windowHandle, windowDropListener)

  def vsync = Vsync.forInstance(this)
  def position = Position.forInstance(this)
  def size = Size.forInstance(this)
  def title = Title.forInstance(this)
  def visible = Visible.forInstance(this)
  def focused = Focused.forInstance(this)
  def mousePosition = MousePosition.forInstance(this)
  def iconified = Iconified.forInstance(this)
  def keyEvents = KeyEvents.forInstance(this)
  def charInputEvents = CharInputEvents.forInstance(this)
  def mouseEvents = MouseEvents.forInstance(this)

  private def setVisible(v: Boolean): Unit = if (v) glfwShowWindow(windowHandle) else glfwHideWindow(windowHandle)

  def isKeyDown(key: GlfwInput.Keyboard.Key): Boolean = glfwGetKey(windowHandle, key) == GLFW_PRESS

  private def updateSurface(width: Int, height: Int): Unit = {
    scribe.debug(s"Resizing skia surface to $width x $height")
    // Create render target, surface and retrieve canvas from it
    val fbId = GL11.glGetInteger(GL30.GL_FRAMEBUFFER_BINDING)

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

  val Vsync = Var[Boolean]("vsync", true)
  val Position = ExternalVar[GlfwWindow, (Int, Int)]("position", getWindowPosition, setWindowPosition)
  val Size = ExternalVar[GlfwWindow, (Int, Int)]("size", getWindowSize, setWindowSize)
  val Title = Var[String]("title", "", true)
  val Visible = Var[Boolean]("visible", false, true)
  val Iconified = ExternalVar[GlfwWindow, Boolean]("iconified", isWindowIconified, setWindowIconified)
  val Focused = Var[Boolean]("focused", true)
  val MousePosition = Var("mousePosition", (0.0, 0.0))
  val KeyEvents = Emitter[KeyEvent]()
  val CharInputEvents = Emitter[Char]()
  val MouseEvents = Emitter[MouseEvent]()

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
  object GlfwOffscreenOglContextFactory extends ApricotEngine.OffscreenSurfaceFactory {
    class GlfwHandle(
        val surface: Surface,
        private[GlfwOffscreenOglContextFactory] val window: Long,
        private[GlfwOffscreenOglContextFactory] val context: DirectContext,
    ) extends HandleLike {
      def makeCurrent() = glfwMakeContextCurrent(window)
    }
    type Handle = GlfwHandle

    def create(width: Int, height: Int): Handle = {
      val currentFbo = GL11.glGetInteger(GL30.GL_FRAMEBUFFER_BINDING)
      scribe.debug(s"main window fbid = ${currentFbo}")

      glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE)
      glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE)
      glfwWindowHint(GLFW_TRANSPARENT_FRAMEBUFFER, GLFW_TRUE)
      glfwWindowHint(GLFW_DOUBLEBUFFER, GLFW_FALSE)
      val windowHandle = glfwCreateWindow(width, height, "offscreen", NULL, NULL)

      glfwMakeContextCurrent(windowHandle)
      val capabilities = GL.createCapabilities()
      glfwSwapInterval(0)

      val skiaContext = DirectContext.makeGL()
      val fbId = GL11.glGetInteger(GL30.GL_FRAMEBUFFER_BINDING)

      scribe.debug(s"new fbid = ${fbId}")

      val renderTarget = BackendRenderTarget.makeGL(width, height, /*samples*/ 0, /*stencil*/ 8, fbId, FramebufferFormat.GR_GL_RGBA8)

      val surface = Surface.makeFromBackendRenderTarget(
        skiaContext,
        renderTarget,
        SurfaceOrigin.BOTTOM_LEFT,
        SurfaceColorFormat.RGBA_8888,
        ColorSpace.getSRGB()
      )

      // GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, currentFbo)

      GlfwHandle(surface, windowHandle, skiaContext)
    }
    def destroy(handle: Handle): Unit =
      handle.makeCurrent()
      handle.surface.close()
      handle.context.close()
      glfwDestroyWindow(handle.window)
  }
}
