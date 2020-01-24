package guarana.swing

import guarana.swing.util._
import java.awt.{TextComponent => _, Component => _ , _}
import javax.swing.text.JTextComponent

opaque type TextField <: TextComponent = javax.swing.JTextField & TextComponent
object TextField extends VarsMap {
  val Action = SwingVar[TextField, javax.swing.Action | Null]("action", _.getAction, _.setAction(_))
  val Columns = SwingVar[TextField, Int]("columns", _.getColumns, _.setColumns(_))
  val HorizontalAlignment = SwingVar[TextField, Int]("horizontalAlignment", _.getHorizontalAlignment, _.setHorizontalAlignment(_))
  val ScrollOffset = SwingVar[TextField, Int]("scrollOffset", _.getScrollOffset, _.setScrollOffset(_))

  given ops: (v: TextField) extended with {
    def action = TextField.Action.forInstance(v)
    def columns = TextField.Columns.forInstance(v)
    def horizontalAlignment = TextField.HorizontalAlignment.forInstance(v)
    def scrollOffset = TextField.ScrollOffset.forInstance(v)
    def horizontalVisibility = v.getHorizontalVisibility
    def unwrap: javax.swing.JTextField = v
  }

  def apply(v: javax.swing.JTextField) = v.asInstanceOf[TextField]

  def init(v: TextField): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    TextComponent.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
  }
  def uninitialized(): TextField = {
    val res = javax.swing.JTextField().asInstanceOf[TextField]
    
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
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
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
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    navigationFilter: Opt[Binding[javax.swing.text.NavigationFilter | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    scrollOffset: Opt[Binding[Int]] = UnsetParam,
    selectedTextColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    selectionColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[TextField] = {
    val res = uninitialized()
    TextField.init(res)
    ifSet(UI, TextComponent.ops.UI(res) := _)
    ifSet(action, TextField.ops.action(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(caret, TextComponent.ops.caret(res) := _)
    ifSet(caretColor, TextComponent.ops.caretColor(res) := _)
    ifSet(columns, TextField.ops.columns(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(disabledTextColor, TextComponent.ops.disabledTextColor(res) := _)
    ifSet(document, TextComponent.ops.document(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(dragEnabled, TextComponent.ops.dragEnabled(res) := _)
    ifSet(dropMode, TextComponent.ops.dropMode(res) := _)
    ifSet(editable, TextComponent.ops.editable(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusAccelerator, TextComponent.ops.focusAccelerator(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(highlighter, TextComponent.ops.highlighter(res) := _)
    ifSet(horizontalAlignment, TextField.ops.horizontalAlignment(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(keymap, TextComponent.ops.keymap(res) := _)
    ifSet(margin, TextComponent.ops.margin(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(navigationFilter, TextComponent.ops.navigationFilter(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(scrollOffset, TextField.ops.scrollOffset(res) := _)
    ifSet(selectedTextColor, TextComponent.ops.selectedTextColor(res) := _)
    ifSet(selectionColor, TextComponent.ops.selectionColor(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}


opaque type PasswordField <: TextField = javax.swing.JPasswordField & TextField
object PasswordField extends VarsMap {
  val EchoChar = SwingVar[PasswordField, Char]("echoChar", _.getEchoChar, _.setEchoChar(_))

  given ops: (v: PasswordField) extended with {
    def echoChar = PasswordField.EchoChar.forInstance(v)
    def password = v.getPassword
    def unwrap: javax.swing.JPasswordField = v
  }

  def apply(v: javax.swing.JPasswordField) = v.asInstanceOf[PasswordField]

  def init(v: PasswordField): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
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
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
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
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    navigationFilter: Opt[Binding[javax.swing.text.NavigationFilter | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    scrollOffset: Opt[Binding[Int]] = UnsetParam,
    selectedTextColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    selectionColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[PasswordField] = {
    val res = uninitialized()
    PasswordField.init(res)
    ifSet(UI, TextComponent.ops.UI(res) := _)
    ifSet(action, TextField.ops.action(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(caret, TextComponent.ops.caret(res) := _)
    ifSet(caretColor, TextComponent.ops.caretColor(res) := _)
    ifSet(columns, TextField.ops.columns(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(disabledTextColor, TextComponent.ops.disabledTextColor(res) := _)
    ifSet(document, TextComponent.ops.document(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(dragEnabled, TextComponent.ops.dragEnabled(res) := _)
    ifSet(dropMode, TextComponent.ops.dropMode(res) := _)
    ifSet(echoChar, PasswordField.ops.echoChar(res) := _)
    ifSet(editable, TextComponent.ops.editable(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusAccelerator, TextComponent.ops.focusAccelerator(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(highlighter, TextComponent.ops.highlighter(res) := _)
    ifSet(horizontalAlignment, TextField.ops.horizontalAlignment(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(keymap, TextComponent.ops.keymap(res) := _)
    ifSet(margin, TextComponent.ops.margin(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(navigationFilter, TextComponent.ops.navigationFilter(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(scrollOffset, TextField.ops.scrollOffset(res) := _)
    ifSet(selectedTextColor, TextComponent.ops.selectedTextColor(res) := _)
    ifSet(selectionColor, TextComponent.ops.selectionColor(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}

opaque type TextArea <: TextComponent = javax.swing.JTextArea & TextComponent
object TextArea extends VarsMap {
  val Columns = SwingVar[TextArea, Int]("columns", _.getColumns, _.setColumns(_))
  val LineWrap = SwingVar[TextArea, Boolean]("lineWrap", _.getLineWrap, _.setLineWrap(_))
  val Rows = SwingVar[TextArea, Int]("rows", _.getRows, _.setRows(_))
  val TabSize = SwingVar[TextArea, Int]("tabSize", _.getTabSize, _.setTabSize(_))
  val WrapStyleWord = SwingVar[TextArea, Boolean]("wrapStyleWord", _.getWrapStyleWord, _.setWrapStyleWord(_))

  given ops: (v: TextArea) extended with {
    def columns = TextArea.Columns.forInstance(v)
    def lineWrap = TextArea.LineWrap.forInstance(v)
    def rows = TextArea.Rows.forInstance(v)
    def tabSize = TextArea.TabSize.forInstance(v)
    def wrapStyleWord = TextArea.WrapStyleWord.forInstance(v)
    def lineCount = v.getLineCount
    def unwrap: javax.swing.JTextArea = v
  }

  def apply(v: javax.swing.JTextArea) = v.asInstanceOf[TextArea]

  def init(v: TextArea): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    TextComponent.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
  }
  def uninitialized(): TextArea = {
    val res = javax.swing.JTextArea().asInstanceOf[TextArea]
    
    res
  }
  
  def apply(
    UI: Opt[Binding[javax.swing.plaf.TextUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
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
    editable: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusAccelerator: Opt[Binding[Char]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    highlighter: Opt[Binding[javax.swing.text.Highlighter | Null]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    keymap: Opt[Binding[javax.swing.text.Keymap | Null]] = UnsetParam,
    lineWrap: Opt[Binding[Boolean]] = UnsetParam,
    margin: Opt[Binding[java.awt.Insets | Null]] = UnsetParam,
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    navigationFilter: Opt[Binding[javax.swing.text.NavigationFilter | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rows: Opt[Binding[Int]] = UnsetParam,
    selectedTextColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    selectionColor: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    tabSize: Opt[Binding[Int]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    wrapStyleWord: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[TextArea] = {
    val res = uninitialized()
    TextArea.init(res)
    ifSet(UI, TextComponent.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(caret, TextComponent.ops.caret(res) := _)
    ifSet(caretColor, TextComponent.ops.caretColor(res) := _)
    ifSet(columns, TextArea.ops.columns(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(disabledTextColor, TextComponent.ops.disabledTextColor(res) := _)
    ifSet(document, TextComponent.ops.document(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(dragEnabled, TextComponent.ops.dragEnabled(res) := _)
    ifSet(dropMode, TextComponent.ops.dropMode(res) := _)
    ifSet(editable, TextComponent.ops.editable(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusAccelerator, TextComponent.ops.focusAccelerator(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(highlighter, TextComponent.ops.highlighter(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(keymap, TextComponent.ops.keymap(res) := _)
    ifSet(lineWrap, TextArea.ops.lineWrap(res) := _)
    ifSet(margin, TextComponent.ops.margin(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(navigationFilter, TextComponent.ops.navigationFilter(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(rows, TextArea.ops.rows(res) := _)
    ifSet(selectedTextColor, TextComponent.ops.selectedTextColor(res) := _)
    ifSet(selectionColor, TextComponent.ops.selectionColor(res) := _)
    ifSet(tabSize, TextArea.ops.tabSize(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    ifSet(wrapStyleWord, TextArea.ops.wrapStyleWord(res) := _)
    res
  }
}
