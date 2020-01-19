package guarana.swing

import guarana.swing.util._
import java.awt.{TextComponent => _, _}
import javax.swing.text.JTextComponent

opaque type TextField <: TextComponent = javax.swing.JTextField & TextComponent
object TextField extends VarsMap {
  val Action = SwingVar[TextField, javax.swing.Action | Null]("action", _.getAction, _.setAction(_))
  val Columns = SwingVar[TextField, Int]("columns", _.getColumns, _.setColumns(_))
  val HorizontalAlignment = SwingVar[TextField, Int]("horizontalAlignment", _.getHorizontalAlignment, _.setHorizontalAlignment(_))
  val ScrollOffset = SwingVar[TextField, Int]("scrollOffset", _.getScrollOffset, _.setScrollOffset(_))

  given ops: (v: TextField) extended with {
    def actionListeners = v.getActionListeners
    def horizontalVisibility = v.getHorizontalVisibility
    def action = TextField.Action.forInstance(v)
    def columns = TextField.Columns.forInstance(v)
    def horizontalAlignment = TextField.HorizontalAlignment.forInstance(v)
    def scrollOffset = TextField.ScrollOffset.forInstance(v)
    def unwrap: javax.swing.JTextField = v
  }

  def uninitialized(): TextField = javax.swing.JTextField().asInstanceOf[TextField]

  def init(n: TextField) = (given sc: Scenegraph) => {
    TextComponent.init(n)
    n.addPropertyChangeListener(varsPropertyListener(n))
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
    bounds: Opt[Binding[Rectangle]] = UnsetParam,
    caret: Opt[Binding[javax.swing.text.Caret]] = UnsetParam,
    caretColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    columns: Opt[Binding[Int]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    disabledTextColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    document: Opt[Binding[javax.swing.text.Document]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    dragEnabled: Opt[Binding[Boolean]] = UnsetParam,
    dropMode: Opt[Binding[javax.swing.DropMode | Null]] = UnsetParam,
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
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    navigationFilter: Opt[Binding[javax.swing.text.NavigationFilter | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    scrollOffset: Opt[Binding[Int]] = UnsetParam,
    selectedTextColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    selectionColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[TextField] = {
    val res = uninitialized()
    init(res)
    ifSet(UI, res.UI := _)
    ifSet(action, TextField.ops.action(res) := _)
    ifSet(actionMap, res.actionMap := _)
    ifSet(alignmentX, res.alignmentX := _)
    ifSet(alignmentY, res.alignmentY := _)
    ifSet(autoscrolls, res.autoscrolls := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, res.border := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(caret, res.caret := _)
    ifSet(caretColor, res.caretColor := _)
    ifSet(columns, res.columns := _)
    ifSet(componentPopupMenu, res.componentPopupMenu := _)
    ifSet(debugGraphicsOptions, res.debugGraphicsOptions := _)
    ifSet(disabledTextColor, res.disabledTextColor := _)
    ifSet(document, res.document := _)
    ifSet(doubleBuffered, res.doubleBuffered := _)
    ifSet(dragEnabled, res.dragEnabled := _)
    ifSet(dropMode, res.dropMode := _)
    ifSet(editable, res.editable := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusAccelerator, res.focusAccelerator := _)
    ifSet(focusable, res.focusable := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(highlighter, res.highlighter := _)
    ifSet(horizontalAlignment, res.horizontalAlignment := _)
    ifSet(inheritsPopupMenu, res.inheritsPopupMenu := _)
    ifSet(inputVerifier, res.inputVerifier := _)
    ifSet(keymap, res.keymap := _)
    ifSet(margin, res.margin := _)
    ifSet(maximumSize, res.maximumSize := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(navigationFilter, res.navigationFilter := _)
    ifSet(opaque, res.opaque := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, res.requestFocusEnabled := _)
    ifSet(scrollOffset, res.scrollOffset := _)
    ifSet(selectedTextColor, res.selectedTextColor := _)
    ifSet(selectionColor, res.selectionColor := _)
    ifSet(toolTipText, res.toolTipText := _)
    ifSet(transferHandler, res.transferHandler := _)
    ifSet(verifyInputWhenFocusTarget, res.verifyInputWhenFocusTarget := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}


opaque type PasswordField <: TextField = javax.swing.JPasswordField & TextField
object PasswordField extends VarsMap {

  val EchoCar = SwingVar[PasswordField, Char]("echoChar", _.getEchoChar, _.setEchoChar(_))

  given ops: (v: PasswordField) extended with {
    def echoChar = PasswordField.EchoCar.forInstance(v)
    def password = v.getPassword
    def unwrap: javax.swing.JPasswordField = v
  }

  def uninitialized(): PasswordField = javax.swing.JPasswordField().asInstanceOf[PasswordField]

  def init(n: TextField) = (given sc: Scenegraph) => {
    TextField.init(n)
    n.addPropertyChangeListener(varsPropertyListener(n))
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
    bounds: Opt[Binding[Rectangle]] = UnsetParam,
    caret: Opt[Binding[javax.swing.text.Caret]] = UnsetParam,
    caretColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    columns: Opt[Binding[Int]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
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
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    navigationFilter: Opt[Binding[javax.swing.text.NavigationFilter | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    scrollOffset: Opt[Binding[Int]] = UnsetParam,
    selectedTextColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    selectionColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[PasswordField] = {
    val res = uninitialized()
    init(res)
    ifSet(UI, res.UI := _)
    ifSet(action, TextField.ops.action(res) := _)
    ifSet(actionMap, res.actionMap := _)
    ifSet(alignmentX, res.alignmentX := _)
    ifSet(alignmentY, res.alignmentY := _)
    ifSet(autoscrolls, res.autoscrolls := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, res.border := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(caret, res.caret := _)
    ifSet(caretColor, res.caretColor := _)
    ifSet(columns, TextField.ops.columns(res) := _)
    ifSet(componentPopupMenu, res.componentPopupMenu := _)
    ifSet(debugGraphicsOptions, res.debugGraphicsOptions := _)
    ifSet(disabledTextColor, res.disabledTextColor := _)
    ifSet(document, res.document := _)
    ifSet(doubleBuffered, res.doubleBuffered := _)
    ifSet(dragEnabled, res.dragEnabled := _)
    ifSet(dropMode, res.dropMode := _)
    ifSet(echoChar, PasswordField.ops.echoChar(res) := _)
    ifSet(editable, res.editable := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusAccelerator, res.focusAccelerator := _)
    ifSet(focusable, res.focusable := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(highlighter, res.highlighter := _)
    ifSet(horizontalAlignment, TextField.ops.horizontalAlignment(res) := _)
    ifSet(inheritsPopupMenu, res.inheritsPopupMenu := _)
    ifSet(inputVerifier, res.inputVerifier := _)
    ifSet(keymap, res.keymap := _)
    ifSet(margin, res.margin := _)
    ifSet(maximumSize, res.maximumSize := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(navigationFilter, res.navigationFilter := _)
    ifSet(opaque, res.opaque := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, res.requestFocusEnabled := _)
    ifSet(scrollOffset, TextField.ops.scrollOffset(res) := _)
    ifSet(selectedTextColor, res.selectedTextColor := _)
    ifSet(selectionColor, res.selectionColor := _)
    ifSet(toolTipText, res.toolTipText := _)
    ifSet(transferHandler, res.transferHandler := _)
    ifSet(verifyInputWhenFocusTarget, res.verifyInputWhenFocusTarget := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}