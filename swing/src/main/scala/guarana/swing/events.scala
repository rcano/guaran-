package guarana.swing

import javax.swing.SwingUtilities

enum MouseButton(val code: Int) {
  case Primary extends MouseButton(1)
  case Middle extends MouseButton(2)
  case Secondary extends MouseButton(3)
  case Back extends MouseButton(4)
  case Forward extends MouseButton(5)
}
object MouseButton {
  def from(i: Int): MouseButton = i match {
    case 1 => Primary
    case 2 => Middle
    case 3 => Secondary
    case 4 => Back
    case 5 => Forward
  }
}

case class MousePosition(relativeX: Int, relativeY: Int, absoluteX: Int, absoluteY: Int)
case class MouseDrag(dragStart: MousePosition, dragStop: MousePosition, isReleased: Boolean, awtEvent: java.awt.event.MouseEvent) {
  def isButtonPressed(btn: MouseButton.Primary.type | MouseButton.Middle.type | MouseButton.Secondary.type): Boolean = btn match {
    case MouseButton.Primary => (awtEvent.getModifiersEx & java.awt.event.InputEvent.BUTTON1_DOWN_MASK) > 0
    case MouseButton.Middle => (awtEvent.getModifiersEx & java.awt.event.InputEvent.BUTTON2_DOWN_MASK) > 0
    case MouseButton.Secondary => (awtEvent.getModifiersEx & java.awt.event.InputEvent.BUTTON3_DOWN_MASK) > 0
  }
}

sealed trait KeyEvent {
  val peer: java.awt.event.KeyEvent
  export peer.{toString => _, getModifiers => _, setModifiers => _, _}
}

case class KeyPressed(peer: java.awt.event.KeyEvent) extends KeyEvent
case class KeyReleased(peer: java.awt.event.KeyEvent) extends KeyEvent
case class KeyTyped(peer: java.awt.event.KeyEvent) extends KeyEvent

sealed trait MouseEvent {
  val peer: java.awt.event.MouseEvent
  export peer.{toString => _, getModifiers => _, setModifiers => _, _}
}

case class MouseMoved(peer: java.awt.event.MouseEvent) extends MouseEvent
case class MouseDragged(peer: java.awt.event.MouseEvent) extends MouseEvent
case class MouseEntered(peer: java.awt.event.MouseEvent) extends MouseEvent
case class MouseExited(peer: java.awt.event.MouseEvent) extends MouseEvent
case class MousePressed(peer: java.awt.event.MouseEvent) extends MouseEvent
case class MouseReleased(peer: java.awt.event.MouseEvent) extends MouseEvent
case class MouseClicked(peer: java.awt.event.MouseEvent) extends MouseEvent