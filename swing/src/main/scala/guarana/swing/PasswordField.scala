//AUTOGENERATED FILE, DO NOT MODIFY

package guarana.swing

import language.implicitConversions
import java.awt.{Component => _, MenuBar => _, MenuItem => _, TextComponent => _, TextField => _, _}
import java.awt.event._
import javax.swing.{Action => _, _}
import javax.swing.event._
import guarana.swing.util._
import scala.jdk.CollectionConverters._
import scala.util.chaining._

opaque type PasswordField <: TextField = javax.swing.JPasswordField & TextField
object PasswordField extends VarsMap {
  val EchoChar: SwingVar.Aux[PasswordField, Char] = SwingVar[PasswordField, Char]("echoChar", _.getEchoChar, _.setEchoChar(_))

  

  given ops as Ops.type = Ops
  object Ops {
    extension (v: PasswordField) {
      def echoChar: Var.Aux[Char, v.type] = PasswordField.EchoChar.asInstanceOf[Var.Aux[Char, v.type]]

      

      def password = v.getPassword
      def unwrap: javax.swing.JPasswordField = v
    }
  }

  def wrap(v: javax.swing.JPasswordField) = v.asInstanceOf[PasswordField]

  def init(v: PasswordField): Scenegraph ?=> Unit = (using sc: Scenegraph) => {
    TextField.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(): PasswordField = {
    val res = javax.swing.JPasswordField().asInstanceOf[PasswordField]
    
    res
  }
  
  def apply(
    
    UI: Opt[Binding[javax.swing.plaf.TextUI]] = UnsetParam,
    action: Opt[Binding[javax.swing.Action | Null]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    caret: Opt[Binding[javax.swing.text.Caret]] = UnsetParam,
    caretColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    columns: Opt[Binding[Int]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    disabledTextColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    document: Opt[Binding[javax.swing.text.Document]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    dragEnabled: Opt[Binding[Boolean]] = UnsetParam,
    dropMode: Opt[Binding[javax.swing.DropMode | Null]] = UnsetParam,
    echoChar: Opt[Binding[Char]] = UnsetParam,
    editable: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusAccelerator: Opt[Binding[Char]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    highlighter: Opt[Binding[javax.swing.text.Highlighter | Null]] = UnsetParam,
    horizontalAlignment: Opt[Binding[Int]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    keymap: Opt[Binding[javax.swing.text.Keymap | Null]] = UnsetParam,
    margin: Opt[Binding[java.awt.Insets | Null]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    mouseDragMut: Opt[Binding[Option[MouseDrag]]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    navigationFilter: Opt[Binding[javax.swing.text.NavigationFilter | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    scrollOffset: Opt[Binding[Int]] = UnsetParam,
    selectedTextColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    selectionColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[PasswordField] = {
    val res = uninitialized()
    PasswordField.init(res)
    ifSet(UI, TextComponent.ops.extension_UI(res) := _)
    ifSet(action, TextField.ops.extension_action(res) := _)
    ifSet(actionMap, Component.ops.extension_actionMap(res) := _)
    ifSet(alignmentX, Component.ops.extension_alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.extension_alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.extension_autoscrolls(res) := _)
    ifSet(background, Node.ops.extension_background(res) := _)
    ifSet(border, Component.ops.extension_border(res) := _)
    ifSet(bounds, Node.ops.extension_bounds(res) := _)
    ifSet(caret, TextComponent.ops.extension_caret(res) := _)
    ifSet(caretColor, TextComponent.ops.extension_caretColor(res) := _)
    ifSet(columns, TextField.ops.extension_columns(res) := _)
    ifSet(componentOrientation, Node.ops.extension_componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.extension_componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.extension_cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.extension_debugGraphicsOptions(res) := _)
    ifSet(disabledTextColor, TextComponent.ops.extension_disabledTextColor(res) := _)
    ifSet(document, TextComponent.ops.extension_document(res) := _)
    ifSet(doubleBuffered, Component.ops.extension_doubleBuffered(res) := _)
    ifSet(dragEnabled, TextComponent.ops.extension_dragEnabled(res) := _)
    ifSet(dropMode, TextComponent.ops.extension_dropMode(res) := _)
    ifSet(echoChar, PasswordField.ops.extension_echoChar(res) := _)
    ifSet(editable, TextComponent.ops.extension_editable(res) := _)
    ifSet(enabled, Node.ops.extension_enabled(res) := _)
    ifSet(focusAccelerator, TextComponent.ops.extension_focusAccelerator(res) := _)
    ifSet(focusable, Node.ops.extension_focusable(res) := _)
    ifSet(font, Node.ops.extension_font(res) := _)
    ifSet(foreground, Node.ops.extension_foreground(res) := _)
    ifSet(highlighter, TextComponent.ops.extension_highlighter(res) := _)
    ifSet(horizontalAlignment, TextField.ops.extension_horizontalAlignment(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.extension_inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.extension_inputVerifier(res) := _)
    ifSet(keymap, TextComponent.ops.extension_keymap(res) := _)
    ifSet(margin, TextComponent.ops.extension_margin(res) := _)
    ifSet(maxSize, Node.ops.extension_maxSize(res) := _)
    ifSet(minSize, Node.ops.extension_minSize(res) := _)
    ifSet(mouseDragMut, Node.ops.extension_mouseDragMut(res) := _)
    ifSet(name, Node.ops.extension_name(res) := _)
    ifSet(navigationFilter, TextComponent.ops.extension_navigationFilter(res) := _)
    ifSet(opaque, Component.ops.extension_opaque(res) := _)
    ifSet(prefSize, Node.ops.extension_prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.extension_requestFocusEnabled(res) := _)
    ifSet(scrollOffset, TextField.ops.extension_scrollOffset(res) := _)
    ifSet(selectedTextColor, TextComponent.ops.extension_selectedTextColor(res) := _)
    ifSet(selectionColor, TextComponent.ops.extension_selectionColor(res) := _)
    ifSet(toolTipText, Component.ops.extension_toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.extension_transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.extension_verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.extension_visible(res) := _)
    res
  }
  
}