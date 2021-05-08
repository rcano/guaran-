
package guarana
package qt
        
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type TextField <: Widget  = io.qt.widgets.QLineEdit & Widget
object TextField {
  private val TextFieldInitialized: Var[Boolean] = Var[Boolean]("TextFieldInitialized", false, false)
  val AcceptableInput: ExternalObsVal.Aux[TextField, Boolean] = ExternalObsVal[TextField, Boolean]("acceptableInput", _.hasAcceptableInput())
  val Alignment: ExternalVar.Aux[TextField, io.qt.core.Qt.Alignment | Null] = ExternalVar[TextField, io.qt.core.Qt.Alignment | Null]("alignment", _.alignment(), _.setAlignment(_), true)
  val ClearButtonEnabled: ExternalVar.Aux[TextField, Boolean] = ExternalVar[TextField, Boolean]("clearButtonEnabled", _.isClearButtonEnabled(), _.setClearButtonEnabled(_), true)
  val CursorMoveStyle: ExternalVar.Aux[TextField, io.qt.core.Qt.CursorMoveStyle] = ExternalVar[TextField, io.qt.core.Qt.CursorMoveStyle]("cursorMoveStyle", _.cursorMoveStyle().nn, _.setCursorMoveStyle(_), true)
  val CursorPosition: ExternalVar.Aux[TextField, Int] = ExternalVar[TextField, Int]("cursorPosition", _.cursorPosition(), _.setCursorPosition(_), true)
  val DisplayText: ExternalObsVal.Aux[TextField, java.lang.String | Null] = ExternalObsVal[TextField, java.lang.String | Null]("displayText", _.displayText())
  val DragEnabled: ExternalVar.Aux[TextField, Boolean] = ExternalVar[TextField, Boolean]("dragEnabled", _.dragEnabled(), _.setDragEnabled(_), true)
  val EchoMode: ExternalVar.Aux[TextField, io.qt.widgets.QLineEdit.EchoMode] = ExternalVar[TextField, io.qt.widgets.QLineEdit.EchoMode]("echoMode", _.echoMode().nn, _.setEchoMode(_), true)
  val Frame: ExternalVar.Aux[TextField, Boolean] = ExternalVar[TextField, Boolean]("frame", _.hasFrame(), _.setFrame(_), true)
  val HasSelectedText: ExternalObsVal.Aux[TextField, Boolean] = ExternalObsVal[TextField, Boolean]("hasSelectedText", _.hasSelectedText())
  val InputMask: ExternalVar.Aux[TextField, java.lang.String | Null] = ExternalVar[TextField, java.lang.String | Null]("inputMask", _.inputMask(), _.setInputMask(_), true)
  val MaxLength: ExternalVar.Aux[TextField, Int] = ExternalVar[TextField, Int]("maxLength", _.maxLength(), _.setMaxLength(_), true)
  val Modified: ExternalVar.Aux[TextField, Boolean] = ExternalVar[TextField, Boolean]("modified", _.isModified(), _.setModified(_), true)
  val PlaceholderText: ExternalVar.Aux[TextField, java.lang.String | Null] = ExternalVar[TextField, java.lang.String | Null]("placeholderText", _.placeholderText(), _.setPlaceholderText(_), true)
  val ReadOnly: ExternalVar.Aux[TextField, Boolean] = ExternalVar[TextField, Boolean]("readOnly", _.isReadOnly(), _.setReadOnly(_), true)
  val RedoAvailable: ExternalObsVal.Aux[TextField, Boolean] = ExternalObsVal[TextField, Boolean]("redoAvailable", _.isRedoAvailable())
  val SelectedText: ExternalObsVal.Aux[TextField, java.lang.String | Null] = ExternalObsVal[TextField, java.lang.String | Null]("selectedText", _.selectedText())
  val Text: ExternalVar.Aux[TextField, java.lang.String | Null] = ExternalVar[TextField, java.lang.String | Null]("text", _.text(), _.setText(_), true)
  val UndoAvailable: ExternalObsVal.Aux[TextField, Boolean] = ExternalObsVal[TextField, Boolean]("undoAvailable", _.isUndoAvailable())

  val Events = Emitter[Event]()
  val TextEdited = Emitter[String]()

  given ops: Ops.type = Ops
  object Ops {
    extension (v: TextField) {
      def acceptableInput: ObsVal.Aux[Boolean, v.type] = TextField.AcceptableInput.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def alignment: Var.Aux[io.qt.core.Qt.Alignment | Null, v.type] = TextField.Alignment.asInstanceOf[Var.Aux[io.qt.core.Qt.Alignment | Null, v.type]]
      def clearButtonEnabled: Var.Aux[Boolean, v.type] = TextField.ClearButtonEnabled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def cursorMoveStyle: Var.Aux[io.qt.core.Qt.CursorMoveStyle, v.type] = TextField.CursorMoveStyle.asInstanceOf[Var.Aux[io.qt.core.Qt.CursorMoveStyle, v.type]]
      def cursorPosition: Var.Aux[Int, v.type] = TextField.CursorPosition.asInstanceOf[Var.Aux[Int, v.type]]
      def displayText: ObsVal.Aux[java.lang.String | Null, v.type] = TextField.DisplayText.asInstanceOf[ObsVal.Aux[java.lang.String | Null, v.type]]
      def dragEnabled: Var.Aux[Boolean, v.type] = TextField.DragEnabled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def echoMode: Var.Aux[io.qt.widgets.QLineEdit.EchoMode, v.type] = TextField.EchoMode.asInstanceOf[Var.Aux[io.qt.widgets.QLineEdit.EchoMode, v.type]]
      def frame: Var.Aux[Boolean, v.type] = TextField.Frame.asInstanceOf[Var.Aux[Boolean, v.type]]
      def hasSelectedText: ObsVal.Aux[Boolean, v.type] = TextField.HasSelectedText.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def inputMask: Var.Aux[java.lang.String | Null, v.type] = TextField.InputMask.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def maxLength: Var.Aux[Int, v.type] = TextField.MaxLength.asInstanceOf[Var.Aux[Int, v.type]]
      def modified: Var.Aux[Boolean, v.type] = TextField.Modified.asInstanceOf[Var.Aux[Boolean, v.type]]
      def placeholderText: Var.Aux[java.lang.String | Null, v.type] = TextField.PlaceholderText.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def readOnly: Var.Aux[Boolean, v.type] = TextField.ReadOnly.asInstanceOf[Var.Aux[Boolean, v.type]]
      def redoAvailable: ObsVal.Aux[Boolean, v.type] = TextField.RedoAvailable.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def selectedText: ObsVal.Aux[java.lang.String | Null, v.type] = TextField.SelectedText.asInstanceOf[ObsVal.Aux[java.lang.String | Null, v.type]]
      def text: Var.Aux[java.lang.String | Null, v.type] = TextField.Text.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def undoAvailable: ObsVal.Aux[Boolean, v.type] = TextField.UndoAvailable.asInstanceOf[ObsVal.Aux[Boolean, v.type]]

      def events: Emitter.Aux[Event, v.type] = TextField.Events.forInstance(v)
      def textEdited: Emitter.Aux[String, v.type] = TextField.TextEdited.forInstance(v)

      def setDisabled(arg0: Boolean) = v.setDisabled(arg0)
      def setHidden(arg0: Boolean) = v.setHidden(arg0)
      def grab(arg0: io.qt.core.QRect | Null) = v.grab(arg0)
      def setText(arg0: java.lang.String | Null) = v.setText(arg0)
      def clear() = v.clear()
      def selectAll() = v.selectAll()
      def undo() = v.undo()
      def redo() = v.redo()
      def cut() = v.cut()
      def copy() = v.copy()
      def paste() = v.paste()
      def inputMethodQuery(arg0: io.qt.core.Qt.InputMethodQuery, arg1: java.lang.Object | Null) = v.inputMethodQuery(arg0, arg1)
      def unwrap: io.qt.widgets.QLineEdit = v
    }
  }

  def wrap(v: io.qt.widgets.QLineEdit): TextField = 
    val res = v.asInstanceOf[TextField]
    if !Toolkit.stateReader(TextFieldInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: TextField): Unit = {
    Widget.init(v)
    Toolkit.connectVar(Text.forInstance[v.type], v.textChanged.nn)
    Toolkit.update(TextFieldInitialized.forInstance[v.type] := true)
    v.textChanged.nn.connect(slot((newText: String) => Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.text(v), None))))
    v.selectionChanged.nn.connect(slot(Toolkit.update {
      val vc = summon[VarContext]
      vc.externalPropertyUpdated(Ops.hasSelectedText(v), None)
      vc.externalPropertyUpdated(Ops.selectedText(v), None)
    }))
    v.cursorPositionChanged.nn.connect(slot((oldp: java.lang.Integer, newp: java.lang.Integer) => Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.cursorPosition(v), Some(oldp)))))
    v.editingFinished.nn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.events(v), Event.EditFinished))))
    v.inputRejected.nn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.events(v), Event.InputRejected))))
    v.returnPressed.nn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.events(v), Event.ReturnPressed))))
    v.textEdited.nn.connect(slot((newText: String) => Toolkit.update(summon[Emitter.Context].emit(Ops.textEdited(v), newText))))
  }
  def uninitialized(): TextField = {
    val res = new io.qt.widgets.QLineEdit()
    
    res.asInstanceOf[TextField]
  }
  
  def apply(
    
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    alignment: Opt[Binding[io.qt.core.Qt.Alignment | Null]] = UnsetParam,
    autoFillBackground: Opt[Binding[Boolean]] = UnsetParam,
    baseSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    clearButtonEnabled: Opt[Binding[Boolean]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    cursorMoveStyle: Opt[Binding[io.qt.core.Qt.CursorMoveStyle]] = UnsetParam,
    cursorPosition: Opt[Binding[Int]] = UnsetParam,
    dragEnabled: Opt[Binding[Boolean]] = UnsetParam,
    echoMode: Opt[Binding[io.qt.widgets.QLineEdit.EchoMode]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    frame: Opt[Binding[Boolean]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    inputMask: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    inputMethodHints: Opt[Binding[io.qt.core.Qt.InputMethodHints | Null]] = UnsetParam,
    layoutDirection: Opt[Binding[io.qt.core.Qt.LayoutDirection]] = UnsetParam,
    locale: Opt[Binding[io.qt.core.QLocale | Null]] = UnsetParam,
    maxLength: Opt[Binding[Int]] = UnsetParam,
    maximumHeight: Opt[Binding[Int]] = UnsetParam,
    maximumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    maximumWidth: Opt[Binding[Int]] = UnsetParam,
    minimumHeight: Opt[Binding[Int]] = UnsetParam,
    minimumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    minimumWidth: Opt[Binding[Int]] = UnsetParam,
    modified: Opt[Binding[Boolean]] = UnsetParam,
    mouseTracking: Opt[Binding[Boolean]] = UnsetParam,
    objectName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    placeholderText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    readOnly: Opt[Binding[Boolean]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipDuration: Opt[Binding[Int]] = UnsetParam,
    updatesEnabled: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    whatsThis: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowFilePath: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowIcon: Opt[Binding[io.qt.gui.QIcon | Null]] = UnsetParam,
    windowIconText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowModality: Opt[Binding[io.qt.core.Qt.WindowModality]] = UnsetParam,
    windowModified: Opt[Binding[Boolean]] = UnsetParam,
    windowOpacity: Opt[Binding[Double]] = UnsetParam,
    windowTitle: Opt[Binding[java.lang.String | Null]] = UnsetParam
  ): ToolkitAction[TextField] = {
    val res = uninitialized()
    TextField.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(alignment, TextField.ops.alignment(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(clearButtonEnabled, TextField.ops.clearButtonEnabled(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(cursorMoveStyle, TextField.ops.cursorMoveStyle(res) := _)
    ifSet(cursorPosition, TextField.ops.cursorPosition(res) := _)
    ifSet(dragEnabled, TextField.ops.dragEnabled(res) := _)
    ifSet(echoMode, TextField.ops.echoMode(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(frame, TextField.ops.frame(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(inputMask, TextField.ops.inputMask(res) := _)
    ifSet(inputMethodHints, Widget.ops.inputMethodHints(res) := _)
    ifSet(layoutDirection, Widget.ops.layoutDirection(res) := _)
    ifSet(locale, Widget.ops.locale(res) := _)
    ifSet(maxLength, TextField.ops.maxLength(res) := _)
    ifSet(maximumHeight, Widget.ops.maximumHeight(res) := _)
    ifSet(maximumSize, Widget.ops.maximumSize(res) := _)
    ifSet(maximumWidth, Widget.ops.maximumWidth(res) := _)
    ifSet(minimumHeight, Widget.ops.minimumHeight(res) := _)
    ifSet(minimumSize, Widget.ops.minimumSize(res) := _)
    ifSet(minimumWidth, Widget.ops.minimumWidth(res) := _)
    ifSet(modified, TextField.ops.modified(res) := _)
    ifSet(mouseTracking, Widget.ops.mouseTracking(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(placeholderText, TextField.ops.placeholderText(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(readOnly, TextField.ops.readOnly(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(text, TextField.ops.text(res) := _)
    ifSet(toolTip, Widget.ops.toolTip(res) := _)
    ifSet(toolTipDuration, Widget.ops.toolTipDuration(res) := _)
    ifSet(updatesEnabled, Widget.ops.updatesEnabled(res) := _)
    ifSet(visible, Widget.ops.visible(res) := _)
    ifSet(whatsThis, Widget.ops.whatsThis(res) := _)
    ifSet(windowFilePath, Widget.ops.windowFilePath(res) := _)
    ifSet(windowIcon, Widget.ops.windowIcon(res) := _)
    ifSet(windowIconText, Widget.ops.windowIconText(res) := _)
    ifSet(windowModality, Widget.ops.windowModality(res) := _)
    ifSet(windowModified, Widget.ops.windowModified(res) := _)
    ifSet(windowOpacity, Widget.ops.windowOpacity(res) := _)
    ifSet(windowTitle, Widget.ops.windowTitle(res) := _)
    res
  }
  enum Event:
    case EditFinished, InputRejected, ReturnPressed
}
        