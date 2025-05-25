
package guarana
package qt
        
import io.qt.widgets.*
import util.*

opaque type SpinBox <: SpinBoxBase  = io.qt.widgets.QSpinBox & SpinBoxBase
object SpinBox {
  private val SpinBoxInitialized: Var[Boolean] = Var[Boolean]("SpinBoxInitialized", false, false)
  val CleanText: ExternalObsVal.Aux[SpinBox, java.lang.String | Null] = ExternalObsVal[SpinBox, java.lang.String | Null]("cleanText", _.cleanText())
  val DisplayIntegerBase: ExternalVar.Aux[SpinBox, Int] = ExternalVar[SpinBox, Int]("displayIntegerBase", _.displayIntegerBase(), _.setDisplayIntegerBase(_), true)
  val Maximum: ExternalVar.Aux[SpinBox, Int] = ExternalVar[SpinBox, Int]("maximum", _.maximum(), _.setMaximum(_), true)
  val Minimum: ExternalVar.Aux[SpinBox, Int] = ExternalVar[SpinBox, Int]("minimum", _.minimum(), _.setMinimum(_), true)
  val Prefix: ExternalVar.Aux[SpinBox, java.lang.String | Null] = ExternalVar[SpinBox, java.lang.String | Null]("prefix", _.prefix(), _.setPrefix(_), true)
  val SingleStep: ExternalVar.Aux[SpinBox, Int] = ExternalVar[SpinBox, Int]("singleStep", _.singleStep(), _.setSingleStep(_), true)
  val StepType: ExternalVar.Aux[SpinBox, io.qt.widgets.QAbstractSpinBox.StepType] = ExternalVar[SpinBox, io.qt.widgets.QAbstractSpinBox.StepType]("stepType", _.stepType().unn, _.setStepType(_), true)
  val Suffix: ExternalVar.Aux[SpinBox, java.lang.String | Null] = ExternalVar[SpinBox, java.lang.String | Null]("suffix", _.suffix(), _.setSuffix(_), true)
  val Value: ExternalVar.Aux[SpinBox, Int] = ExternalVar[SpinBox, Int]("value", _.value(), _.setValue(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: SpinBox) {
      def cleanText: ObsVal.Aux[java.lang.String | Null, v.type] = SpinBox.CleanText.asInstanceOf[ObsVal.Aux[java.lang.String | Null, v.type]]
      def displayIntegerBase: Var.Aux[Int, v.type] = SpinBox.DisplayIntegerBase.asInstanceOf[Var.Aux[Int, v.type]]
      def maximum: Var.Aux[Int, v.type] = SpinBox.Maximum.asInstanceOf[Var.Aux[Int, v.type]]
      def minimum: Var.Aux[Int, v.type] = SpinBox.Minimum.asInstanceOf[Var.Aux[Int, v.type]]
      def prefix: Var.Aux[java.lang.String | Null, v.type] = SpinBox.Prefix.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def singleStep: Var.Aux[Int, v.type] = SpinBox.SingleStep.asInstanceOf[Var.Aux[Int, v.type]]
      def stepType: Var.Aux[io.qt.widgets.QAbstractSpinBox.StepType, v.type] = SpinBox.StepType.asInstanceOf[Var.Aux[io.qt.widgets.QAbstractSpinBox.StepType, v.type]]
      def suffix: Var.Aux[java.lang.String | Null, v.type] = SpinBox.Suffix.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def value: Var.Aux[Int, v.type] = SpinBox.Value.asInstanceOf[Var.Aux[Int, v.type]]

      

      def setValue(arg0: Int) = v.setValue(arg0)
      def unwrap: io.qt.widgets.QSpinBox = v
    }
  }

  def wrap(v: io.qt.widgets.QSpinBox): SpinBox = 
    val res = v.asInstanceOf[SpinBox]
    if !Toolkit.stateReader(SpinBoxInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: SpinBox): Unit = {
    SpinBoxBase.init(v)
    Toolkit.connectVar(Value.forInstance[v.type], v.valueChanged.unn)
    Toolkit.update(SpinBoxInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): SpinBox = {
    val res = new io.qt.widgets.QSpinBox()
    
    res.asInstanceOf[SpinBox]
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
    displayIntegerBase: Opt[Binding[Int]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    frame: Opt[Binding[Boolean]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    inputMethodHints: Opt[Binding[io.qt.core.Qt.InputMethodHints | Null]] = UnsetParam,
    keyboardTracking: Opt[Binding[Boolean]] = UnsetParam,
    layoutDirection: Opt[Binding[io.qt.core.Qt.LayoutDirection]] = UnsetParam,
    locale: Opt[Binding[io.qt.core.QLocale | Null]] = UnsetParam,
    maximum: Opt[Binding[Int]] = UnsetParam,
    maximumHeight: Opt[Binding[Int]] = UnsetParam,
    maximumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    maximumWidth: Opt[Binding[Int]] = UnsetParam,
    minimum: Opt[Binding[Int]] = UnsetParam,
    minimumHeight: Opt[Binding[Int]] = UnsetParam,
    minimumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    minimumWidth: Opt[Binding[Int]] = UnsetParam,
    mouseTracking: Opt[Binding[Boolean]] = UnsetParam,
    objectName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    prefix: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    readOnly: Opt[Binding[Boolean]] = UnsetParam,
    showGroupSeparator: Opt[Binding[Boolean]] = UnsetParam,
    singleStep: Opt[Binding[Int]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    specialValueText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    stepType: Opt[Binding[io.qt.widgets.QAbstractSpinBox.StepType]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    suffix: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
    toolTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipDuration: Opt[Binding[Int]] = UnsetParam,
    updatesEnabled: Opt[Binding[Boolean]] = UnsetParam,
    value: Opt[Binding[Int]] = UnsetParam,
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
  ): VarContextAction[SpinBox] = {
    val res = uninitialized()
    SpinBox.init(res)
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
    ifSet(displayIntegerBase, SpinBox.ops.displayIntegerBase(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(frame, SpinBoxBase.ops.frame(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(inputMethodHints, Widget.ops.inputMethodHints(res) := _)
    ifSet(keyboardTracking, SpinBoxBase.ops.keyboardTracking(res) := _)
    ifSet(layoutDirection, Widget.ops.layoutDirection(res) := _)
    ifSet(locale, Widget.ops.locale(res) := _)
    ifSet(maximum, SpinBox.ops.maximum(res) := _)
    ifSet(maximumHeight, Widget.ops.maximumHeight(res) := _)
    ifSet(maximumSize, Widget.ops.maximumSize(res) := _)
    ifSet(maximumWidth, Widget.ops.maximumWidth(res) := _)
    ifSet(minimum, SpinBox.ops.minimum(res) := _)
    ifSet(minimumHeight, Widget.ops.minimumHeight(res) := _)
    ifSet(minimumSize, Widget.ops.minimumSize(res) := _)
    ifSet(minimumWidth, Widget.ops.minimumWidth(res) := _)
    ifSet(mouseTracking, Widget.ops.mouseTracking(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(prefix, SpinBox.ops.prefix(res) := _)
    ifSet(readOnly, SpinBoxBase.ops.readOnly(res) := _)
    ifSet(showGroupSeparator, SpinBoxBase.ops.showGroupSeparator(res) := _)
    ifSet(singleStep, SpinBox.ops.singleStep(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(specialValueText, SpinBoxBase.ops.specialValueText(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(stepType, SpinBox.ops.stepType(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(suffix, SpinBox.ops.suffix(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(toolTip, Widget.ops.toolTip(res) := _)
    ifSet(toolTipDuration, Widget.ops.toolTipDuration(res) := _)
    ifSet(updatesEnabled, Widget.ops.updatesEnabled(res) := _)
    ifSet(value, SpinBox.ops.value(res) := _)
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
        