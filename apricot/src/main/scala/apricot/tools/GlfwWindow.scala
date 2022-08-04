package apricot
package tools

import guarana.*
import org.lwjgl.glfw.*
import org.lwjgl.opengl.*
import org.lwjgl.system.MemoryUtil.*
import scala.compiletime.uninitialized

import GLFW.*

class GlfwWindow(val engine: ApricotEngine[? <: AbstractToolkit], windowHints: Map[Int, Int] = Map.empty)(using val tk: AbstractToolkit) {
  import GlfwWindow.*

  val windowHandle = {
    GlfwWindow // init statics
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)
    windowHints.foreach(glfwWindowHint(_, _))
    glfwCreateWindow(100, 100, "", NULL, NULL)
  }
  require(windowHandle != NULL, "window creation failed")

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
    tk.update(
      summon[Emitter.Context].emit(mouseEvents, MouseEvent.Button(btn.asInstanceOf, InputAction.of(action).asInstanceOf, mods.asInstanceOf))
    )
  private val windowScrollListener: GLFWScrollCallbackI = (_, xoffset, yoffset) =>
    tk.update(summon[Emitter.Context].emit(mouseEvents, MouseEvent.Scrolled(xoffset, yoffset)))
  private val windowFocusListener: GLFWWindowFocusCallbackI = (_, focused) => tk.update(this.focused := focused)
  private val windowCloseListener: GLFWWindowCloseCallbackI = _ => tk.update(this.closeAction()())
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
  def closeAction = CloseAction.forInstance(this)
  def iconified = Iconified.forInstance(this)
  def keyEvents = KeyEvents.forInstance(this)
  def charInputEvents = CharInputEvents.forInstance(this)
  def mouseEvents = MouseEvents.forInstance(this)

  private def setVisible(v: Boolean): Unit = if (v) glfwShowWindow(windowHandle) else glfwHideWindow(windowHandle)

  def isKeyDown(key: GlfwInput.Keyboard.Key): Boolean = glfwGetKey(windowHandle, key) == GLFW_PRESS

  override def toString = s"GlfwWindow(handle=${windowHandle.toHexString}, title=${tk.stateReader(title)})"
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
  val CloseAction = Var[() => Unit]("closeAction", () => ())
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
}
