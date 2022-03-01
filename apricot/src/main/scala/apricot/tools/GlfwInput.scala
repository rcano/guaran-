package apricot.tools

import org.lwjgl.glfw.GLFW.*

object GlfwInput {
  object Keyboard {
    type Key =
      Space.type | Apostrophe.type | Comma.type | Minus.type | Period.type | Slash.type | Num0.type | Num1.type | Num2.type | Num3.type |
        Num4.type | Num5.type | Num6.type | Num7.type | Num8.type | Num9.type | Semicolon.type | Equal.type | A.type | B.type | C.type |
        D.type | E.type | F.type | G.type | H.type | I.type | J.type | K.type | L.type | M.type | N.type | O.type | P.type | Q.type |
        R.type | S.type | T.type | U.type | V.type | W.type | X.type | Y.type | Z.type | LeftBracket.type | Backslash.type |
        RightBracket.type | GraveAccent.type | World1.type | World2.type | Escape.type | Enter.type | Tab.type | Backspace.type |
        Insert.type | Delete.type | Right.type | Left.type | Down.type | Up.type | Page_up.type | Page_down.type | Home.type | End.type |
        CapsLock.type | ScrollLock.type | NumLock.type | PrintScreen.type | Pause.type | F1.type | F2.type | F3.type | F4.type | F5.type |
        F6.type | F7.type | F8.type | F9.type | F10.type | F11.type | F12.type | F13.type | F14.type | F15.type | F16.type | F17.type |
        F18.type | F19.type | F20.type | F21.type | F22.type | F23.type | F24.type | F25.type | Kp0.type | Kp1.type | Kp2.type | Kp3.type |
        Kp4.type | Kp5.type | Kp6.type | Kp7.type | Kp8.type | Kp9.type | KpDecimal.type | KpDivide.type | KpMultiply.type |
        KpSubtract.type | KpAdd.type | KpEnter.type | KpEqual.type | LeftShift.type | LeftControl.type | LeftAlt.type | LeftSuper.type |
        RightShift.type | RightControl.type | RightAlt.type | RightSuper.type | Menu.type | Last.type

    inline val Space = GLFW_KEY_SPACE
    inline val Apostrophe = GLFW_KEY_APOSTROPHE
    inline val Comma = GLFW_KEY_COMMA
    inline val Minus = GLFW_KEY_MINUS
    inline val Period = GLFW_KEY_PERIOD
    inline val Slash = GLFW_KEY_SLASH
    inline val Num0 = GLFW_KEY_0
    inline val Num1 = GLFW_KEY_1
    inline val Num2 = GLFW_KEY_2
    inline val Num3 = GLFW_KEY_3
    inline val Num4 = GLFW_KEY_4
    inline val Num5 = GLFW_KEY_5
    inline val Num6 = GLFW_KEY_6
    inline val Num7 = GLFW_KEY_7
    inline val Num8 = GLFW_KEY_8
    inline val Num9 = GLFW_KEY_9
    inline val Semicolon = GLFW_KEY_SEMICOLON
    inline val Equal = GLFW_KEY_EQUAL
    inline val A = GLFW_KEY_A
    inline val B = GLFW_KEY_B
    inline val C = GLFW_KEY_C
    inline val D = GLFW_KEY_D
    inline val E = GLFW_KEY_E
    inline val F = GLFW_KEY_F
    inline val G = GLFW_KEY_G
    inline val H = GLFW_KEY_H
    inline val I = GLFW_KEY_I
    inline val J = GLFW_KEY_J
    inline val K = GLFW_KEY_K
    inline val L = GLFW_KEY_L
    inline val M = GLFW_KEY_M
    inline val N = GLFW_KEY_N
    inline val O = GLFW_KEY_O
    inline val P = GLFW_KEY_P
    inline val Q = GLFW_KEY_Q
    inline val R = GLFW_KEY_R
    inline val S = GLFW_KEY_S
    inline val T = GLFW_KEY_T
    inline val U = GLFW_KEY_U
    inline val V = GLFW_KEY_V
    inline val W = GLFW_KEY_W
    inline val X = GLFW_KEY_X
    inline val Y = GLFW_KEY_Y
    inline val Z = GLFW_KEY_Z
    inline val LeftBracket = GLFW_KEY_LEFT_BRACKET
    inline val Backslash = GLFW_KEY_BACKSLASH
    inline val RightBracket = GLFW_KEY_RIGHT_BRACKET
    inline val GraveAccent = GLFW_KEY_GRAVE_ACCENT
    inline val World1 = GLFW_KEY_WORLD_1
    inline val World2 = GLFW_KEY_WORLD_2

    inline val Escape = GLFW_KEY_ESCAPE
    inline val Enter = GLFW_KEY_ENTER
    inline val Tab = GLFW_KEY_TAB
    inline val Backspace = GLFW_KEY_BACKSPACE
    inline val Insert = GLFW_KEY_INSERT
    inline val Delete = GLFW_KEY_DELETE
    inline val Right = GLFW_KEY_RIGHT
    inline val Left = GLFW_KEY_LEFT
    inline val Down = GLFW_KEY_DOWN
    inline val Up = GLFW_KEY_UP
    inline val Page_up = GLFW_KEY_PAGE_UP
    inline val Page_down = GLFW_KEY_PAGE_DOWN
    inline val Home = GLFW_KEY_HOME
    inline val End = GLFW_KEY_END
    inline val CapsLock = GLFW_KEY_CAPS_LOCK
    inline val ScrollLock = GLFW_KEY_SCROLL_LOCK
    inline val NumLock = GLFW_KEY_NUM_LOCK
    inline val PrintScreen = GLFW_KEY_PRINT_SCREEN
    inline val Pause = GLFW_KEY_PAUSE
    inline val F1 = GLFW_KEY_F1
    inline val F2 = GLFW_KEY_F2
    inline val F3 = GLFW_KEY_F3
    inline val F4 = GLFW_KEY_F4
    inline val F5 = GLFW_KEY_F5
    inline val F6 = GLFW_KEY_F6
    inline val F7 = GLFW_KEY_F7
    inline val F8 = GLFW_KEY_F8
    inline val F9 = GLFW_KEY_F9
    inline val F10 = GLFW_KEY_F10
    inline val F11 = GLFW_KEY_F11
    inline val F12 = GLFW_KEY_F12
    inline val F13 = GLFW_KEY_F13
    inline val F14 = GLFW_KEY_F14
    inline val F15 = GLFW_KEY_F15
    inline val F16 = GLFW_KEY_F16
    inline val F17 = GLFW_KEY_F17
    inline val F18 = GLFW_KEY_F18
    inline val F19 = GLFW_KEY_F19
    inline val F20 = GLFW_KEY_F20
    inline val F21 = GLFW_KEY_F21
    inline val F22 = GLFW_KEY_F22
    inline val F23 = GLFW_KEY_F23
    inline val F24 = GLFW_KEY_F24
    inline val F25 = GLFW_KEY_F25
    inline val Kp0 = GLFW_KEY_KP_0
    inline val Kp1 = GLFW_KEY_KP_1
    inline val Kp2 = GLFW_KEY_KP_2
    inline val Kp3 = GLFW_KEY_KP_3
    inline val Kp4 = GLFW_KEY_KP_4
    inline val Kp5 = GLFW_KEY_KP_5
    inline val Kp6 = GLFW_KEY_KP_6
    inline val Kp7 = GLFW_KEY_KP_7
    inline val Kp8 = GLFW_KEY_KP_8
    inline val Kp9 = GLFW_KEY_KP_9
    inline val KpDecimal = GLFW_KEY_KP_DECIMAL
    inline val KpDivide = GLFW_KEY_KP_DIVIDE
    inline val KpMultiply = GLFW_KEY_KP_MULTIPLY
    inline val KpSubtract = GLFW_KEY_KP_SUBTRACT
    inline val KpAdd = GLFW_KEY_KP_ADD
    inline val KpEnter = GLFW_KEY_KP_ENTER
    inline val KpEqual = GLFW_KEY_KP_EQUAL
    inline val LeftShift = GLFW_KEY_LEFT_SHIFT
    inline val LeftControl = GLFW_KEY_LEFT_CONTROL
    inline val LeftAlt = GLFW_KEY_LEFT_ALT
    inline val LeftSuper = GLFW_KEY_LEFT_SUPER
    inline val RightShift = GLFW_KEY_RIGHT_SHIFT
    inline val RightControl = GLFW_KEY_RIGHT_CONTROL
    inline val RightAlt = GLFW_KEY_RIGHT_ALT
    inline val RightSuper = GLFW_KEY_RIGHT_SUPER
    inline val Menu = GLFW_KEY_MENU
    inline val Last = GLFW_KEY_LAST

    opaque type ModKeys <: AnyVal = Int
    object ModKeys {
      extension (keys: ModKeys) {
        def isShiftDown = (keys & ModShift) > 0
        def isControlDown = (keys & ModControl) > 0
        def isAltDown = (keys & ModAlt) > 0
        def isSuperDown = (keys & ModSuper) > 0
        def isCapsLockOn = (keys & ModCapsLock) > 0
        def isNumLockOn = (keys & ModNumLock) > 0

        def show: String =
          var mods: List[String] = Nil
          if isShiftDown then mods +:= "shift down"
          if isControlDown then mods +:= "ctrl down"
          if isAltDown then mods +:= "alt down"
          if isSuperDown then mods +:= "super down"
          if isCapsLockOn then mods +:= "caps lock on"
          if isNumLockOn then mods +:= "num lock on"
          mods.mkString(", ")
      }
    }

    inline val ModShift = GLFW_MOD_SHIFT
    inline val ModControl = GLFW_MOD_CONTROL
    inline val ModAlt = GLFW_MOD_ALT
    inline val ModSuper = GLFW_MOD_SUPER
    inline val ModCapsLock = GLFW_MOD_CAPS_LOCK
    inline val ModNumLock = GLFW_MOD_NUM_LOCK
  }

  object Mouse {
    type Button = Button1.type | Button2.type | Button3.type | Button4.type | Button5.type | Button6.type | Button7.type | Button8.type

    inline val Button1 = GLFW_MOUSE_BUTTON_1
    inline val Button2 = GLFW_MOUSE_BUTTON_2
    inline val Button3 = GLFW_MOUSE_BUTTON_3
    inline val Button4 = GLFW_MOUSE_BUTTON_4
    inline val Button5 = GLFW_MOUSE_BUTTON_5
    inline val Button6 = GLFW_MOUSE_BUTTON_6
    inline val Button7 = GLFW_MOUSE_BUTTON_7
    inline val Button8 = GLFW_MOUSE_BUTTON_8
    inline val ButtonLast = Button8
    inline val ButtonLeft = Button1
    inline val ButtonRight = Button2
    inline val ButtonMiddle = Button3
  }
}

sealed trait MouseButtonAction { self: InputAction => }
enum InputAction:
  case Pressed extends InputAction, MouseButtonAction
  case Released extends InputAction, MouseButtonAction
  case Repeated
object InputAction:
  def of(a: Int) = a match
    case GLFW_PRESS => Pressed
    case GLFW_RELEASE => Released
    case GLFW_REPEAT => Repeated

case class KeyEvent(key: GlfwInput.Keyboard.Key, action: InputAction, mods: GlfwInput.Keyboard.ModKeys, scancode: Int) {
  lazy val keyName = glfwGetKeyName(key, scancode)
  override def toString = s"KeyEvent($keyName, $action, [${mods.show}])"
}

enum MouseEvent {
  case Button(button: GlfwInput.Mouse.Button, action: MouseButtonAction, mods: GlfwInput.Keyboard.ModKeys)
  case Moved(x: Double, y: Double)
  case Scrolled(xOffset: Double, yOffset: Double)
  case Entered
  case Exited

  override def toString() = this match
    case Button(btn, a, mods) => s"MouseButtonEvt($btn, $a, [${mods.show}])"
    case Moved(x, y) => s"MouseMovedEvt(x=$x, y=$y)"
    case Scrolled(x, y) => s"MouseScrolledEvt(xOffset=$x, yOffset=$y)"
    case Entered => "MouseEnteredEvt"
    case Exited => "MouseExitedEvt"
}
