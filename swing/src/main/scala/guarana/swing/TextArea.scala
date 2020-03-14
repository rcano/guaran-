//AUTOGENERATED FILE, DO NOT MODIFY

package guarana.swing

import language.implicitConversions
import java.awt.{Component => _, TextComponent => _, TextField => _, _}
import java.awt.event._
import javax.swing._
import javax.swing.event._
import guarana.swing.util._
import scala.jdk.CollectionConverters._
import scala.util.chaining._

opaque type TextArea <: TextComponent = javax.swing.JTextArea & TextComponent
object TextArea extends VarsMap {
  val Columns: SwingVar.Aux[TextArea, Int] = SwingVar[TextArea, Int]("columns", _.getColumns, _.setColumns(_))
  val LineWrap: SwingVar.Aux[TextArea, Boolean] = SwingVar[TextArea, Boolean]("lineWrap", _.getLineWrap, _.setLineWrap(_))
  val Rows: SwingVar.Aux[TextArea, Int] = SwingVar[TextArea, Int]("rows", _.getRows, _.setRows(_))
  val TabSize: SwingVar.Aux[TextArea, Int] = SwingVar[TextArea, Int]("tabSize", _.getTabSize, _.setTabSize(_))
  val WrapStyleWord: SwingVar.Aux[TextArea, Boolean] = SwingVar[TextArea, Boolean]("wrapStyleWord", _.getWrapStyleWord, _.setWrapStyleWord(_))

  

  extension ops on (v: TextArea) {
    def columns: Var.Aux[Int, v.type] = TextArea.Columns.asInstanceOf[Var.Aux[Int, v.type]]
    def lineWrap: Var.Aux[Boolean, v.type] = TextArea.LineWrap.asInstanceOf[Var.Aux[Boolean, v.type]]
    def rows: Var.Aux[Int, v.type] = TextArea.Rows.asInstanceOf[Var.Aux[Int, v.type]]
    def tabSize: Var.Aux[Int, v.type] = TextArea.TabSize.asInstanceOf[Var.Aux[Int, v.type]]
    def wrapStyleWord: Var.Aux[Boolean, v.type] = TextArea.WrapStyleWord.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    def lineCount = v.getLineCount
    def unwrap: javax.swing.JTextArea = v
  }

  def wrap(v: javax.swing.JTextArea) = v.asInstanceOf[TextArea]

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
    editable: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusAccelerator: Opt[Binding[Char]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    focusedMut: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    highlighter: Opt[Binding[javax.swing.text.Highlighter | Null]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    keymap: Opt[Binding[javax.swing.text.Keymap | Null]] = UnsetParam,
    lineWrap: Opt[Binding[Boolean]] = UnsetParam,
    margin: Opt[Binding[java.awt.Insets | Null]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    mouseLocationMut: Opt[Binding[(Int, Int)]] = UnsetParam,
    navigationFilter: Opt[Binding[javax.swing.text.NavigationFilter | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
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
    ifSet(focusedMut, Node.ops.focusedMut(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(highlighter, TextComponent.ops.highlighter(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(keymap, TextComponent.ops.keymap(res) := _)
    ifSet(lineWrap, TextArea.ops.lineWrap(res) := _)
    ifSet(margin, TextComponent.ops.margin(res) := _)
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(mouseLocationMut, Node.ops.mouseLocationMut(res) := _)
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