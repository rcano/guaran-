
package guarana
package qt
        
import io.qt.core.Qt
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type Dial <: SliderBase  = io.qt.widgets.QDial & SliderBase
object Dial {
  private val DialInitialized: Var[Boolean] = Var[Boolean]("DialInitialized", false, false)
  val NotchSize: ExternalObsVal.Aux[Dial, Int] = ExternalObsVal[Dial, Int]("notchSize", _.notchSize())
  val NotchTarget: ExternalVar.Aux[Dial, Double] = ExternalVar[Dial, Double]("notchTarget", _.notchTarget(), _.setNotchTarget(_), true)
  val NotchesVisible: ExternalVar.Aux[Dial, Boolean] = ExternalVar[Dial, Boolean]("notchesVisible", _.notchesVisible(), _.setNotchesVisible(_), true)
  val Wrapping: ExternalVar.Aux[Dial, Boolean] = ExternalVar[Dial, Boolean]("wrapping", _.wrapping(), _.setWrapping(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: Dial) {
      def notchSize: ObsVal.Aux[Int, v.type] = Dial.NotchSize.asInstanceOf[ObsVal.Aux[Int, v.type]]
      def notchTarget: Var.Aux[Double, v.type] = Dial.NotchTarget.asInstanceOf[Var.Aux[Double, v.type]]
      def notchesVisible: Var.Aux[Boolean, v.type] = Dial.NotchesVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
      def wrapping: Var.Aux[Boolean, v.type] = Dial.Wrapping.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      def setNotchesVisible(arg0: Boolean) = v.setNotchesVisible(arg0)
      def setWrapping(arg0: Boolean) = v.setWrapping(arg0)
      def unwrap: io.qt.widgets.QDial = v
    }
  }

  def wrap(v: io.qt.widgets.QDial): Dial = 
    val res = v.asInstanceOf[Dial]
    if !Toolkit.stateReader(DialInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: Dial): Unit = {
    SliderBase.init(v)
    Toolkit.update(DialInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): Dial = {
    val res = new io.qt.widgets.QDial()
    
    res.asInstanceOf[Dial]
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
    notchTarget: Opt[Binding[Double]] = UnsetParam,
    notchesVisible: Opt[Binding[Boolean]] = UnsetParam,
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
    windowTitle: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    wrapping: Opt[Binding[Boolean]] = UnsetParam
  ): ToolkitAction[Dial] = {
    val res = uninitialized()
    Dial.init(res)
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
    ifSet(notchTarget, Dial.ops.notchTarget(res) := _)
    ifSet(notchesVisible, Dial.ops.notchesVisible(res) := _)
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
    ifSet(wrapping, Dial.ops.wrapping(res) := _)
    res
  }
  
}
        