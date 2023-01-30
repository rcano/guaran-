
package guarana
package qt
        
import io.qt.core.Qt
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type ScrollAreaBase <: Widget  = io.qt.widgets.QAbstractScrollArea & Widget
object ScrollAreaBase {
  private val ScrollAreaBaseInitialized: Var[Boolean] = Var[Boolean]("ScrollAreaBaseInitialized", false, false)
  val FrameRect: ExternalVar.Aux[ScrollAreaBase, io.qt.core.QRect | Null] = ExternalVar[ScrollAreaBase, io.qt.core.QRect | Null]("frameRect", _.frameRect(), _.setFrameRect(_), true)
  val FrameShadow: ExternalVar.Aux[ScrollAreaBase, io.qt.widgets.QFrame.Shadow] = ExternalVar[ScrollAreaBase, io.qt.widgets.QFrame.Shadow]("frameShadow", _.frameShadow().unn, _.setFrameShadow(_), true)
  val FrameShape: ExternalVar.Aux[ScrollAreaBase, io.qt.widgets.QFrame.Shape] = ExternalVar[ScrollAreaBase, io.qt.widgets.QFrame.Shape]("frameShape", _.frameShape().unn, _.setFrameShape(_), true)
  val FrameWidth: ExternalObsVal.Aux[ScrollAreaBase, Int] = ExternalObsVal[ScrollAreaBase, Int]("frameWidth", _.frameWidth())
  val HorizontalScrollBarPolicy: ExternalVar.Aux[ScrollAreaBase, io.qt.core.Qt.ScrollBarPolicy] = ExternalVar[ScrollAreaBase, io.qt.core.Qt.ScrollBarPolicy]("horizontalScrollBarPolicy", _.horizontalScrollBarPolicy().unn, _.setHorizontalScrollBarPolicy(_), true)
  val LineWidth: ExternalVar.Aux[ScrollAreaBase, Int] = ExternalVar[ScrollAreaBase, Int]("lineWidth", _.lineWidth(), _.setLineWidth(_), true)
  val MidLineWidth: ExternalVar.Aux[ScrollAreaBase, Int] = ExternalVar[ScrollAreaBase, Int]("midLineWidth", _.midLineWidth(), _.setMidLineWidth(_), true)
  val SizeAdjustPolicy: ExternalVar.Aux[ScrollAreaBase, io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy] = ExternalVar[ScrollAreaBase, io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy]("sizeAdjustPolicy", _.sizeAdjustPolicy().unn, _.setSizeAdjustPolicy(_), true)
  val VerticalScrollBarPolicy: ExternalVar.Aux[ScrollAreaBase, io.qt.core.Qt.ScrollBarPolicy] = ExternalVar[ScrollAreaBase, io.qt.core.Qt.ScrollBarPolicy]("verticalScrollBarPolicy", _.verticalScrollBarPolicy().unn, _.setVerticalScrollBarPolicy(_), true)
  val Viewport: ExternalVar.Aux[ScrollAreaBase, Widget] = ExternalVar[ScrollAreaBase, Widget]("viewport", c => c.viewport().unn, (c, v) => c.setViewport(v.unwrap), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: ScrollAreaBase) {
      def frameRect: Var.Aux[io.qt.core.QRect | Null, v.type] = ScrollAreaBase.FrameRect.asInstanceOf[Var.Aux[io.qt.core.QRect | Null, v.type]]
      def frameShadow: Var.Aux[io.qt.widgets.QFrame.Shadow, v.type] = ScrollAreaBase.FrameShadow.asInstanceOf[Var.Aux[io.qt.widgets.QFrame.Shadow, v.type]]
      def frameShape: Var.Aux[io.qt.widgets.QFrame.Shape, v.type] = ScrollAreaBase.FrameShape.asInstanceOf[Var.Aux[io.qt.widgets.QFrame.Shape, v.type]]
      def frameWidth: ObsVal.Aux[Int, v.type] = ScrollAreaBase.FrameWidth.asInstanceOf[ObsVal.Aux[Int, v.type]]
      def horizontalScrollBarPolicy: Var.Aux[io.qt.core.Qt.ScrollBarPolicy, v.type] = ScrollAreaBase.HorizontalScrollBarPolicy.asInstanceOf[Var.Aux[io.qt.core.Qt.ScrollBarPolicy, v.type]]
      def lineWidth: Var.Aux[Int, v.type] = ScrollAreaBase.LineWidth.asInstanceOf[Var.Aux[Int, v.type]]
      def midLineWidth: Var.Aux[Int, v.type] = ScrollAreaBase.MidLineWidth.asInstanceOf[Var.Aux[Int, v.type]]
      def sizeAdjustPolicy: Var.Aux[io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy, v.type] = ScrollAreaBase.SizeAdjustPolicy.asInstanceOf[Var.Aux[io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy, v.type]]
      def verticalScrollBarPolicy: Var.Aux[io.qt.core.Qt.ScrollBarPolicy, v.type] = ScrollAreaBase.VerticalScrollBarPolicy.asInstanceOf[Var.Aux[io.qt.core.Qt.ScrollBarPolicy, v.type]]
      def viewport: Var.Aux[Widget, v.type] = ScrollAreaBase.Viewport.asInstanceOf[Var.Aux[Widget, v.type]]

      

      def setDisabled(arg0: Boolean) = v.setDisabled(arg0)
      def setHidden(arg0: Boolean) = v.setHidden(arg0)
      def grab(arg0: io.qt.core.QRect | Null) = v.grab(arg0)
      def unwrap: io.qt.widgets.QAbstractScrollArea = v
    }
  }

  def wrap(v: io.qt.widgets.QAbstractScrollArea): ScrollAreaBase = 
    val res = v.asInstanceOf[ScrollAreaBase]
    if !Toolkit.stateReader(ScrollAreaBaseInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: ScrollAreaBase): Unit = {
    Widget.init(v)
    Toolkit.update(ScrollAreaBaseInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): ScrollAreaBase = {
    val res = new io.qt.widgets.QAbstractScrollArea()
    
    res.asInstanceOf[ScrollAreaBase]
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
    frameRect: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    frameShadow: Opt[Binding[io.qt.widgets.QFrame.Shadow]] = UnsetParam,
    frameShape: Opt[Binding[io.qt.widgets.QFrame.Shape]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    horizontalScrollBarPolicy: Opt[Binding[io.qt.core.Qt.ScrollBarPolicy]] = UnsetParam,
    inputMethodHints: Opt[Binding[io.qt.core.Qt.InputMethodHints | Null]] = UnsetParam,
    layoutDirection: Opt[Binding[io.qt.core.Qt.LayoutDirection]] = UnsetParam,
    lineWidth: Opt[Binding[Int]] = UnsetParam,
    locale: Opt[Binding[io.qt.core.QLocale | Null]] = UnsetParam,
    maximumHeight: Opt[Binding[Int]] = UnsetParam,
    maximumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    maximumWidth: Opt[Binding[Int]] = UnsetParam,
    midLineWidth: Opt[Binding[Int]] = UnsetParam,
    minimumHeight: Opt[Binding[Int]] = UnsetParam,
    minimumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    minimumWidth: Opt[Binding[Int]] = UnsetParam,
    mouseTracking: Opt[Binding[Boolean]] = UnsetParam,
    objectName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeAdjustPolicy: Opt[Binding[io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
    toolTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipDuration: Opt[Binding[Int]] = UnsetParam,
    updatesEnabled: Opt[Binding[Boolean]] = UnsetParam,
    verticalScrollBarPolicy: Opt[Binding[io.qt.core.Qt.ScrollBarPolicy]] = UnsetParam,
    viewport: Opt[Binding[Widget]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    whatsThis: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowFilePath: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowIcon: Opt[Binding[io.qt.gui.QIcon | Null]] = UnsetParam,
    windowIconText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowModality: Opt[Binding[io.qt.core.Qt.WindowModality]] = UnsetParam,
    windowModified: Opt[Binding[Boolean]] = UnsetParam,
    windowOpacity: Opt[Binding[Double]] = UnsetParam,
    windowTitle: Opt[Binding[java.lang.String | Null]] = UnsetParam
  ): ToolkitAction[ScrollAreaBase] = {
    val res = uninitialized()
    ScrollAreaBase.init(res)
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
    ifSet(frameRect, ScrollAreaBase.ops.frameRect(res) := _)
    ifSet(frameShadow, ScrollAreaBase.ops.frameShadow(res) := _)
    ifSet(frameShape, ScrollAreaBase.ops.frameShape(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(horizontalScrollBarPolicy, ScrollAreaBase.ops.horizontalScrollBarPolicy(res) := _)
    ifSet(inputMethodHints, Widget.ops.inputMethodHints(res) := _)
    ifSet(layoutDirection, Widget.ops.layoutDirection(res) := _)
    ifSet(lineWidth, ScrollAreaBase.ops.lineWidth(res) := _)
    ifSet(locale, Widget.ops.locale(res) := _)
    ifSet(maximumHeight, Widget.ops.maximumHeight(res) := _)
    ifSet(maximumSize, Widget.ops.maximumSize(res) := _)
    ifSet(maximumWidth, Widget.ops.maximumWidth(res) := _)
    ifSet(midLineWidth, ScrollAreaBase.ops.midLineWidth(res) := _)
    ifSet(minimumHeight, Widget.ops.minimumHeight(res) := _)
    ifSet(minimumSize, Widget.ops.minimumSize(res) := _)
    ifSet(minimumWidth, Widget.ops.minimumWidth(res) := _)
    ifSet(mouseTracking, Widget.ops.mouseTracking(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeAdjustPolicy, ScrollAreaBase.ops.sizeAdjustPolicy(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(toolTip, Widget.ops.toolTip(res) := _)
    ifSet(toolTipDuration, Widget.ops.toolTipDuration(res) := _)
    ifSet(updatesEnabled, Widget.ops.updatesEnabled(res) := _)
    ifSet(verticalScrollBarPolicy, ScrollAreaBase.ops.verticalScrollBarPolicy(res) := _)
    ifSet(viewport, ScrollAreaBase.ops.viewport(res) := _)
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
        