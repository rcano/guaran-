package guarana.swing

import guarana.swing.util._
import java.awt.{Component => _, _}
import javax.swing.text.JTextComponent
import javax.swing.event.{DocumentEvent, DocumentListener}

opaque type TextComponent <: Component = javax.swing.text.JTextComponent & Component
object TextComponent extends VarsMap {
  val UI = SwingVar[TextComponent, javax.swing.plaf.TextUI]("UI", _.getUI.nn, _.setUI(_))
  val Caret = SwingVar[TextComponent, javax.swing.text.Caret]("caret", _.getCaret.nn, _.setCaret(_))
  val CaretColor = SwingVar[TextComponent, java.awt.Color | Null]("caretColor", _.getCaretColor, _.setCaretColor(_))
  val DisabledTextColor = SwingVar[TextComponent, java.awt.Color | Null]("disabledTextColor", _.getDisabledTextColor, _.setDisabledTextColor(_))
  val Document = SwingVar[TextComponent, javax.swing.text.Document]("document", _.getDocument.nn, _.setDocument(_))
  val DragEnabled = SwingVar[TextComponent, Boolean]("dragEnabled", _.getDragEnabled, _.setDragEnabled(_))
  val DropMode = SwingVar[TextComponent, javax.swing.DropMode | Null]("dropMode", _.getDropMode, _.setDropMode(_))
  val Editable = SwingVar[TextComponent, Boolean]("editable", _.isEditable, _.setEditable(_))
  val FocusAccelerator = SwingVar[TextComponent, Char]("focusAccelerator", _.getFocusAccelerator, _.setFocusAccelerator(_))
  val Highlighter = SwingVar[TextComponent, javax.swing.text.Highlighter | Null]("highlighter", _.getHighlighter, _.setHighlighter(_))
  val Keymap = SwingVar[TextComponent, javax.swing.text.Keymap | Null]("keymap", _.getKeymap, _.setKeymap(_))
  val Margin = SwingVar[TextComponent, java.awt.Insets | Null]("margin", _.getMargin, _.setMargin(_))
  val NavigationFilter = SwingVar[TextComponent, javax.swing.text.NavigationFilter | Null]("navigationFilter", _.getNavigationFilter, _.setNavigationFilter(_))
  val SelectedTextColor = SwingVar[TextComponent, java.awt.Color | Null]("selectedTextColor", _.getSelectedTextColor, _.setSelectedTextColor(_))
  val SelectionColor = SwingVar[TextComponent, java.awt.Color | Null]("selectionColor", _.getSelectionColor, _.setSelectionColor(_))

  given ops: (v: TextComponent) extended with {
    def UI = TextComponent.UI.forInstance(v)
    def caret = TextComponent.Caret.forInstance(v)
    def caretColor = TextComponent.CaretColor.forInstance(v)
    def disabledTextColor = TextComponent.DisabledTextColor.forInstance(v)
    def document = TextComponent.Document.forInstance(v)
    def dragEnabled = TextComponent.DragEnabled.forInstance(v)
    def dropMode = TextComponent.DropMode.forInstance(v)
    def editable = TextComponent.Editable.forInstance(v)
    def focusAccelerator = TextComponent.FocusAccelerator.forInstance(v)
    def highlighter = TextComponent.Highlighter.forInstance(v)
    def keymap = TextComponent.Keymap.forInstance(v)
    def margin = TextComponent.Margin.forInstance(v)
    def navigationFilter = TextComponent.NavigationFilter.forInstance(v)
    def selectedTextColor = TextComponent.SelectedTextColor.forInstance(v)
    def selectionColor = TextComponent.SelectionColor.forInstance(v)
    def actions = v.getActions
    def caretListeners = v.getCaretListeners
    def dropLocation = v.getDropLocation
    def preferredScrollableViewportSize = v.getPreferredScrollableViewportSize
    def scrollableTracksViewportHeight = v.getScrollableTracksViewportHeight
    def scrollableTracksViewportWidth = v.getScrollableTracksViewportWidth
    def selectedText = v.getSelectedText
    def text = v.getText
    def text_=(s: String) = v.setText(s)
    def unwrap: javax.swing.text.JTextComponent = v
  }

  def apply(v: javax.swing.text.JTextComponent) = v.asInstanceOf[TextComponent]

  def init(v: TextComponent): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    v.getDocument.addDocumentListener(new DocumentListener {
      def changedUpdate(evt: DocumentEvent | Null) = notifyChange()
      def insertUpdate(evt: DocumentEvent | Null) = notifyChange()
      def removeUpdate(evt: DocumentEvent | Null) = notifyChange()
      def notifyChange() = summon[Scenegraph].update(summon[VarContext].swingPropertyUpdated(ops.document(v), v.getDocument.nn))
    })
  }
  
}


given documentOps: (d: javax.swing.text.Document) extended with {
  def defaultRootElement = d.getDefaultRootElement
  def length = d.getLength
  def empty = d.length == 0
  def nonEmpty = !d.empty
  def text: String = d.getText(0, d.getLength).nn
  def text_=(t: String) = d match {
    case d: javax.swing.text.AbstractDocument => d.replace(0, d.getLength, t, null)
    case _ => 
      d.remove(0, d.getLength)
      d.insertString(0, t, null)
    }
  def startPosition = d.getStartPosition
  def endPosition = d.getEndPosition
  def rootElements = d.getRootElements
}