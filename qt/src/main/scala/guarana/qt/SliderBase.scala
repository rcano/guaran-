
package guarana
package qt
        
import io.qt.core.Qt
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type SliderBase <: Widget  = io.qt.widgets.QAbstractSlider & Widget
object SliderBase {
  private val SliderBaseInitialized: Var[Boolean] = Var[Boolean]("SliderBaseInitialized", false, false)
  val InvertedAppearance: ExternalVar.Aux[SliderBase, Boolean] = ExternalVar[SliderBase, Boolean]("invertedAppearance", _.invertedAppearance(), _.setInvertedAppearance(_), true)
  val InvertedControls: ExternalVar.Aux[SliderBase, Boolean] = ExternalVar[SliderBase, Boolean]("invertedControls", _.invertedControls(), _.setInvertedControls(_), true)
  val Maximum: ExternalVar.Aux[SliderBase, Int] = ExternalVar[SliderBase, Int]("maximum", _.maximum(), _.setMaximum(_), true)
  val Minimum: ExternalVar.Aux[SliderBase, Int] = ExternalVar[SliderBase, Int]("minimum", _.minimum(), _.setMinimum(_), true)
  val Orientation: ExternalVar.Aux[SliderBase, io.qt.core.Qt.Orientation] = ExternalVar[SliderBase, io.qt.core.Qt.Orientation]("orientation", _.orientation().unn, _.setOrientation(_), true)
  val PageStep: ExternalVar.Aux[SliderBase, Int] = ExternalVar[SliderBase, Int]("pageStep", _.pageStep(), _.setPageStep(_), true)
  val SingleStep: ExternalVar.Aux[SliderBase, Int] = ExternalVar[SliderBase, Int]("singleStep", _.singleStep(), _.setSingleStep(_), true)
  val SliderDown: ExternalVar.Aux[SliderBase, Boolean] = ExternalVar[SliderBase, Boolean]("sliderDown", _.isSliderDown(), _.setSliderDown(_), true)
  val SliderPosition: ExternalVar.Aux[SliderBase, Int] = ExternalVar[SliderBase, Int]("sliderPosition", _.sliderPosition(), _.setSliderPosition(_), true)
  val Tracking: ExternalVar.Aux[SliderBase, Boolean] = ExternalVar[SliderBase, Boolean]("tracking", _.hasTracking(), _.setTracking(_), true)
  val Value: ExternalVar.Aux[SliderBase, Int] = ExternalVar[SliderBase, Int]("value", _.value(), _.setValue(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: SliderBase) {
      def invertedAppearance: Var.Aux[Boolean, v.type] = SliderBase.InvertedAppearance.asInstanceOf[Var.Aux[Boolean, v.type]]
      def invertedControls: Var.Aux[Boolean, v.type] = SliderBase.InvertedControls.asInstanceOf[Var.Aux[Boolean, v.type]]
      def maximum: Var.Aux[Int, v.type] = SliderBase.Maximum.asInstanceOf[Var.Aux[Int, v.type]]
      def minimum: Var.Aux[Int, v.type] = SliderBase.Minimum.asInstanceOf[Var.Aux[Int, v.type]]
      def orientation: Var.Aux[io.qt.core.Qt.Orientation, v.type] = SliderBase.Orientation.asInstanceOf[Var.Aux[io.qt.core.Qt.Orientation, v.type]]
      def pageStep: Var.Aux[Int, v.type] = SliderBase.PageStep.asInstanceOf[Var.Aux[Int, v.type]]
      def singleStep: Var.Aux[Int, v.type] = SliderBase.SingleStep.asInstanceOf[Var.Aux[Int, v.type]]
      def sliderDown: Var.Aux[Boolean, v.type] = SliderBase.SliderDown.asInstanceOf[Var.Aux[Boolean, v.type]]
      def sliderPosition: Var.Aux[Int, v.type] = SliderBase.SliderPosition.asInstanceOf[Var.Aux[Int, v.type]]
      def tracking: Var.Aux[Boolean, v.type] = SliderBase.Tracking.asInstanceOf[Var.Aux[Boolean, v.type]]
      def value: Var.Aux[Int, v.type] = SliderBase.Value.asInstanceOf[Var.Aux[Int, v.type]]

      

      def setDisabled(arg0: Boolean) = v.setDisabled(arg0)
      def setHidden(arg0: Boolean) = v.setHidden(arg0)
      def grab(arg0: io.qt.core.QRect | Null) = v.grab(arg0)
      def setValue(arg0: Int) = v.setValue(arg0)
      def setOrientation(arg0: io.qt.core.Qt.Orientation) = v.setOrientation(arg0)
      def setRange(arg0: Int, arg1: Int) = v.setRange(arg0, arg1)
      def unwrap: io.qt.widgets.QAbstractSlider = v
    }
  }

  def wrap(v: io.qt.widgets.QAbstractSlider): SliderBase = 
    val res = v.asInstanceOf[SliderBase]
    if !Toolkit.stateReader(SliderBaseInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: SliderBase): Unit = {
    Widget.init(v)
    Toolkit.connectVar(Value.forInstance[v.type], v.valueChanged.unn)
    Toolkit.update(SliderBaseInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): SliderBase = {
    val res = new io.qt.widgets.QAbstractSlider()
    
    res.asInstanceOf[SliderBase]
  }
  
  def apply(
    
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    autoFillBackground: Opt[Binding[Boolean]] = UnsetParam,
    baseSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    inputMethodHints: Opt[Binding[io.qt.core.Qt.InputMethodHints | Null]] = UnsetParam,
    invertedAppearance: Opt[Binding[Boolean]] = UnsetParam,
    invertedControls: Opt[Binding[Boolean]] = UnsetParam,
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
    orientation: Opt[Binding[io.qt.core.Qt.Orientation]] = UnsetParam,
    pageStep: Opt[Binding[Int]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    singleStep: Opt[Binding[Int]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    sliderDown: Opt[Binding[Boolean]] = UnsetParam,
    sliderPosition: Opt[Binding[Int]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
    toolTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipDuration: Opt[Binding[Int]] = UnsetParam,
    tracking: Opt[Binding[Boolean]] = UnsetParam,
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
    windowTitle: Opt[Binding[java.lang.String | Null]] = UnsetParam
  ): VarContextAction[SliderBase] = {
    val res = uninitialized()
    SliderBase.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(inputMethodHints, Widget.ops.inputMethodHints(res) := _)
    ifSet(invertedAppearance, SliderBase.ops.invertedAppearance(res) := _)
    ifSet(invertedControls, SliderBase.ops.invertedControls(res) := _)
    ifSet(layoutDirection, Widget.ops.layoutDirection(res) := _)
    ifSet(locale, Widget.ops.locale(res) := _)
    ifSet(maximum, SliderBase.ops.maximum(res) := _)
    ifSet(maximumHeight, Widget.ops.maximumHeight(res) := _)
    ifSet(maximumSize, Widget.ops.maximumSize(res) := _)
    ifSet(maximumWidth, Widget.ops.maximumWidth(res) := _)
    ifSet(minimum, SliderBase.ops.minimum(res) := _)
    ifSet(minimumHeight, Widget.ops.minimumHeight(res) := _)
    ifSet(minimumSize, Widget.ops.minimumSize(res) := _)
    ifSet(minimumWidth, Widget.ops.minimumWidth(res) := _)
    ifSet(mouseTracking, Widget.ops.mouseTracking(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(orientation, SliderBase.ops.orientation(res) := _)
    ifSet(pageStep, SliderBase.ops.pageStep(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(singleStep, SliderBase.ops.singleStep(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(sliderDown, SliderBase.ops.sliderDown(res) := _)
    ifSet(sliderPosition, SliderBase.ops.sliderPosition(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(toolTip, Widget.ops.toolTip(res) := _)
    ifSet(toolTipDuration, Widget.ops.toolTipDuration(res) := _)
    ifSet(tracking, SliderBase.ops.tracking(res) := _)
    ifSet(updatesEnabled, Widget.ops.updatesEnabled(res) := _)
    ifSet(value, SliderBase.ops.value(res) := _)
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
  
}
        