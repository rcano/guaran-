
package guarana
package qt
        
import io.qt.widgets.*
import util.*

opaque type SpinBoxBase <: Widget  = io.qt.widgets.QAbstractSpinBox & Widget
object SpinBoxBase {
  private val SpinBoxBaseInitialized: Var[Boolean] = Var[Boolean]("SpinBoxBaseInitialized", false, false)
  val Accelerated: ExternalVar.Aux[SpinBoxBase, Boolean] = ExternalVar[SpinBoxBase, Boolean]("accelerated", _.isAccelerated(), _.setAccelerated(_), true)
  val AcceptableInput: ExternalObsVal.Aux[SpinBoxBase, Boolean] = ExternalObsVal[SpinBoxBase, Boolean]("acceptableInput", _.hasAcceptableInput())
  val Alignment: ExternalVar.Aux[SpinBoxBase, io.qt.core.Qt.Alignment | Null] = ExternalVar[SpinBoxBase, io.qt.core.Qt.Alignment | Null]("alignment", _.alignment(), _.setAlignment(_), true)
  val ButtonSymbols: ExternalVar.Aux[SpinBoxBase, io.qt.widgets.QAbstractSpinBox.ButtonSymbols] = ExternalVar[SpinBoxBase, io.qt.widgets.QAbstractSpinBox.ButtonSymbols]("buttonSymbols", _.buttonSymbols().unn, _.setButtonSymbols(_), true)
  val CorrectionMode: ExternalVar.Aux[SpinBoxBase, io.qt.widgets.QAbstractSpinBox.CorrectionMode] = ExternalVar[SpinBoxBase, io.qt.widgets.QAbstractSpinBox.CorrectionMode]("correctionMode", _.correctionMode().unn, _.setCorrectionMode(_), true)
  val Frame: ExternalVar.Aux[SpinBoxBase, Boolean] = ExternalVar[SpinBoxBase, Boolean]("frame", _.hasFrame(), _.setFrame(_), true)
  val KeyboardTracking: ExternalVar.Aux[SpinBoxBase, Boolean] = ExternalVar[SpinBoxBase, Boolean]("keyboardTracking", _.keyboardTracking(), _.setKeyboardTracking(_), true)
  val ReadOnly: ExternalVar.Aux[SpinBoxBase, Boolean] = ExternalVar[SpinBoxBase, Boolean]("readOnly", _.isReadOnly(), _.setReadOnly(_), true)
  val ShowGroupSeparator: ExternalVar.Aux[SpinBoxBase, Boolean] = ExternalVar[SpinBoxBase, Boolean]("showGroupSeparator", _.isGroupSeparatorShown(), _.setGroupSeparatorShown(_), true)
  val SpecialValueText: ExternalVar.Aux[SpinBoxBase, java.lang.String | Null] = ExternalVar[SpinBoxBase, java.lang.String | Null]("specialValueText", _.specialValueText(), _.setSpecialValueText(_), true)
  val Text: ExternalObsVal.Aux[SpinBoxBase, java.lang.String | Null] = ExternalObsVal[SpinBoxBase, java.lang.String | Null]("text", _.text())
  val Wrapping: ExternalVar.Aux[SpinBoxBase, Boolean] = ExternalVar[SpinBoxBase, Boolean]("wrapping", _.wrapping(), _.setWrapping(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: SpinBoxBase) {
      def accelerated: Var.Aux[Boolean, v.type] = SpinBoxBase.Accelerated.asInstanceOf[Var.Aux[Boolean, v.type]]
      def acceptableInput: ObsVal.Aux[Boolean, v.type] = SpinBoxBase.AcceptableInput.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def alignment: Var.Aux[io.qt.core.Qt.Alignment | Null, v.type] = SpinBoxBase.Alignment.asInstanceOf[Var.Aux[io.qt.core.Qt.Alignment | Null, v.type]]
      def buttonSymbols: Var.Aux[io.qt.widgets.QAbstractSpinBox.ButtonSymbols, v.type] = SpinBoxBase.ButtonSymbols.asInstanceOf[Var.Aux[io.qt.widgets.QAbstractSpinBox.ButtonSymbols, v.type]]
      def correctionMode: Var.Aux[io.qt.widgets.QAbstractSpinBox.CorrectionMode, v.type] = SpinBoxBase.CorrectionMode.asInstanceOf[Var.Aux[io.qt.widgets.QAbstractSpinBox.CorrectionMode, v.type]]
      def frame: Var.Aux[Boolean, v.type] = SpinBoxBase.Frame.asInstanceOf[Var.Aux[Boolean, v.type]]
      def keyboardTracking: Var.Aux[Boolean, v.type] = SpinBoxBase.KeyboardTracking.asInstanceOf[Var.Aux[Boolean, v.type]]
      def readOnly: Var.Aux[Boolean, v.type] = SpinBoxBase.ReadOnly.asInstanceOf[Var.Aux[Boolean, v.type]]
      def showGroupSeparator: Var.Aux[Boolean, v.type] = SpinBoxBase.ShowGroupSeparator.asInstanceOf[Var.Aux[Boolean, v.type]]
      def specialValueText: Var.Aux[java.lang.String | Null, v.type] = SpinBoxBase.SpecialValueText.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def text: ObsVal.Aux[java.lang.String | Null, v.type] = SpinBoxBase.Text.asInstanceOf[ObsVal.Aux[java.lang.String | Null, v.type]]
      def wrapping: Var.Aux[Boolean, v.type] = SpinBoxBase.Wrapping.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      def setDisabled(arg0: Boolean) = v.setDisabled(arg0)
      def setHidden(arg0: Boolean) = v.setHidden(arg0)
      def grab(arg0: io.qt.core.QRect | Null) = v.grab(arg0)
      def stepUp() = v.stepUp()
      def stepDown() = v.stepDown()
      def selectAll() = v.selectAll()
      def clear() = v.clear()
      def unwrap: io.qt.widgets.QAbstractSpinBox = v
    }
  }

  def wrap(v: io.qt.widgets.QAbstractSpinBox): SpinBoxBase = 
    val res = v.asInstanceOf[SpinBoxBase]
    if !Toolkit.stateReader(SpinBoxBaseInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: SpinBoxBase): Unit = {
    Widget.init(v)
    Toolkit.update(SpinBoxBaseInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): SpinBoxBase = {
    val res = new io.qt.widgets.QAbstractSpinBox()
    
    res.asInstanceOf[SpinBoxBase]
  }
  
  def apply(
    
    accelerated: Opt[Binding[Boolean]] = UnsetParam,
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    alignment: Opt[Binding[io.qt.core.Qt.Alignment | Null]] = UnsetParam,
    autoFillBackground: Opt[Binding[Boolean]] = UnsetParam,
    baseSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    buttonSymbols: Opt[Binding[io.qt.widgets.QAbstractSpinBox.ButtonSymbols]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    correctionMode: Opt[Binding[io.qt.widgets.QAbstractSpinBox.CorrectionMode]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    frame: Opt[Binding[Boolean]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    inputMethodHints: Opt[Binding[io.qt.core.Qt.InputMethodHints | Null]] = UnsetParam,
    keyboardTracking: Opt[Binding[Boolean]] = UnsetParam,
    layoutDirection: Opt[Binding[io.qt.core.Qt.LayoutDirection]] = UnsetParam,
    locale: Opt[Binding[io.qt.core.QLocale | Null]] = UnsetParam,
    maximumHeight: Opt[Binding[Int]] = UnsetParam,
    maximumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    maximumWidth: Opt[Binding[Int]] = UnsetParam,
    minimumHeight: Opt[Binding[Int]] = UnsetParam,
    minimumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    minimumWidth: Opt[Binding[Int]] = UnsetParam,
    mouseTracking: Opt[Binding[Boolean]] = UnsetParam,
    objectName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    readOnly: Opt[Binding[Boolean]] = UnsetParam,
    showGroupSeparator: Opt[Binding[Boolean]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    specialValueText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
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
    windowTitle: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    wrapping: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[SpinBoxBase] = {
    val res = uninitialized()
    SpinBoxBase.init(res)
    ifSet(accelerated, SpinBoxBase.ops.accelerated(res) := _)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(alignment, SpinBoxBase.ops.alignment(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(buttonSymbols, SpinBoxBase.ops.buttonSymbols(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(correctionMode, SpinBoxBase.ops.correctionMode(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(frame, SpinBoxBase.ops.frame(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(inputMethodHints, Widget.ops.inputMethodHints(res) := _)
    ifSet(keyboardTracking, SpinBoxBase.ops.keyboardTracking(res) := _)
    ifSet(layoutDirection, Widget.ops.layoutDirection(res) := _)
    ifSet(locale, Widget.ops.locale(res) := _)
    ifSet(maximumHeight, Widget.ops.maximumHeight(res) := _)
    ifSet(maximumSize, Widget.ops.maximumSize(res) := _)
    ifSet(maximumWidth, Widget.ops.maximumWidth(res) := _)
    ifSet(minimumHeight, Widget.ops.minimumHeight(res) := _)
    ifSet(minimumSize, Widget.ops.minimumSize(res) := _)
    ifSet(minimumWidth, Widget.ops.minimumWidth(res) := _)
    ifSet(mouseTracking, Widget.ops.mouseTracking(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(readOnly, SpinBoxBase.ops.readOnly(res) := _)
    ifSet(showGroupSeparator, SpinBoxBase.ops.showGroupSeparator(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(specialValueText, SpinBoxBase.ops.specialValueText(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
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
    ifSet(wrapping, SpinBoxBase.ops.wrapping(res) := _)
    res
  }
  
}
        