package apricot.tools

import guarana.*
import guarana.input.InputManager
import java.time.Clock
import scala.concurrent.duration.*
import scala.io.AnsiColor

class GlfwKeyboardInputManager()(using tk: AbstractToolkit) extends InputManager[KeyEvent, GlfwInput.Keyboard.Key] {
  val clock = Clock.systemDefaultZone.nn
  def combinationTolerance = 80.millis
  def getInputCode(evt: KeyEvent) = evt.key
  def isDown(evt: KeyEvent) = evt.action == InputAction.Pressed
  def sequenceDefaultPatience = 999.hours

  var debugTransitions = false
  override protected def onEventTransition(combination: InputMatcher[?], event: KeyEvent, previousState: Any, newState: Any): Unit =
      //format: off
      if (debugTransitions) println(s"${AnsiColor.MAGENTA}[$combination]${AnsiColor.BLUE} ($previousState)${AnsiColor.RED} x ${AnsiColor.BLUE}$event${AnsiColor.RED} ==> ${AnsiColor.YELLOW}$newState${AnsiColor.RESET}")
      //format: on

  private var connected = collection.mutable.HashSet.empty[GlfwWindow]
  def connect(window: GlfwWindow): Unit = {
    connected += window
    tk.update { window.keyEvents := EventIterator.takeWhile(_ => connected(window)).foreach(dispatch) }
  }
  def disconnect(window: GlfwWindow): Unit = connected -= window
}
