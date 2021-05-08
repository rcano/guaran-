
package guarana
package qt
        
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type ScrollArea <: ScrollAreaBase  = io.qt.widgets.QScrollArea & ScrollAreaBase
object ScrollArea {
  private val ScrollAreaInitialized: Var[Boolean] = Var[Boolean]("ScrollAreaInitialized", false, false)
  val Alignment: ExternalVar.Aux[ScrollArea, io.qt.core.Qt.Alignment | Null] = ExternalVar[ScrollArea, io.qt.core.Qt.Alignment | Null]("alignment", _.alignment(), _.setAlignment(_), true)
  val FrameRect: ExternalVar.Aux[ScrollArea, io.qt.core.QRect | Null] = ExternalVar[ScrollArea, io.qt.core.QRect | Null]("frameRect", _.frameRect(), _.setFrameRect(_), true)
  val FrameShadow: ExternalVar.Aux[ScrollArea, io.qt.widgets.QFrame.Shadow] = ExternalVar[ScrollArea, io.qt.widgets.QFrame.Shadow]("frameShadow", _.frameShadow().nn, _.setFrameShadow(_), true)
  val FrameShape: ExternalVar.Aux[ScrollArea, io.qt.widgets.QFrame.Shape] = ExternalVar[ScrollArea, io.qt.widgets.QFrame.Shape]("frameShape", _.frameShape().nn, _.setFrameShape(_), true)
  val FrameWidth: ExternalObsVal.Aux[ScrollArea, Int] = ExternalObsVal[ScrollArea, Int]("frameWidth", _.frameWidth())
  val HorizontalScrollBarPolicy: ExternalVar.Aux[ScrollArea, io.qt.core.Qt.ScrollBarPolicy] = ExternalVar[ScrollArea, io.qt.core.Qt.ScrollBarPolicy]("horizontalScrollBarPolicy", _.horizontalScrollBarPolicy().nn, _.setHorizontalScrollBarPolicy(_), true)
  val LineWidth: ExternalVar.Aux[ScrollArea, Int] = ExternalVar[ScrollArea, Int]("lineWidth", _.lineWidth(), _.setLineWidth(_), true)
  val MidLineWidth: ExternalVar.Aux[ScrollArea, Int] = ExternalVar[ScrollArea, Int]("midLineWidth", _.midLineWidth(), _.setMidLineWidth(_), true)
  val SizeAdjustPolicy: ExternalVar.Aux[ScrollArea, io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy] = ExternalVar[ScrollArea, io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy]("sizeAdjustPolicy", _.sizeAdjustPolicy().nn, _.setSizeAdjustPolicy(_), true)
  val VerticalScrollBarPolicy: ExternalVar.Aux[ScrollArea, io.qt.core.Qt.ScrollBarPolicy] = ExternalVar[ScrollArea, io.qt.core.Qt.ScrollBarPolicy]("verticalScrollBarPolicy", _.verticalScrollBarPolicy().nn, _.setVerticalScrollBarPolicy(_), true)
  val WidgetResizable: ExternalVar.Aux[ScrollArea, Boolean] = ExternalVar[ScrollArea, Boolean]("widgetResizable", _.widgetResizable(), _.setWidgetResizable(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: ScrollArea) {
      def alignment: Var.Aux[io.qt.core.Qt.Alignment | Null, v.type] = ScrollArea.Alignment.asInstanceOf[Var.Aux[io.qt.core.Qt.Alignment | Null, v.type]]
      def frameRect: Var.Aux[io.qt.core.QRect | Null, v.type] = ScrollArea.FrameRect.asInstanceOf[Var.Aux[io.qt.core.QRect | Null, v.type]]
      def frameShadow: Var.Aux[io.qt.widgets.QFrame.Shadow, v.type] = ScrollArea.FrameShadow.asInstanceOf[Var.Aux[io.qt.widgets.QFrame.Shadow, v.type]]
      def frameShape: Var.Aux[io.qt.widgets.QFrame.Shape, v.type] = ScrollArea.FrameShape.asInstanceOf[Var.Aux[io.qt.widgets.QFrame.Shape, v.type]]
      def frameWidth: ObsVal.Aux[Int, v.type] = ScrollArea.FrameWidth.asInstanceOf[ObsVal.Aux[Int, v.type]]
      def horizontalScrollBarPolicy: Var.Aux[io.qt.core.Qt.ScrollBarPolicy, v.type] = ScrollArea.HorizontalScrollBarPolicy.asInstanceOf[Var.Aux[io.qt.core.Qt.ScrollBarPolicy, v.type]]
      def lineWidth: Var.Aux[Int, v.type] = ScrollArea.LineWidth.asInstanceOf[Var.Aux[Int, v.type]]
      def midLineWidth: Var.Aux[Int, v.type] = ScrollArea.MidLineWidth.asInstanceOf[Var.Aux[Int, v.type]]
      def sizeAdjustPolicy: Var.Aux[io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy, v.type] = ScrollArea.SizeAdjustPolicy.asInstanceOf[Var.Aux[io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy, v.type]]
      def verticalScrollBarPolicy: Var.Aux[io.qt.core.Qt.ScrollBarPolicy, v.type] = ScrollArea.VerticalScrollBarPolicy.asInstanceOf[Var.Aux[io.qt.core.Qt.ScrollBarPolicy, v.type]]
      def widgetResizable: Var.Aux[Boolean, v.type] = ScrollArea.WidgetResizable.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      def setDisabled(arg0: Boolean) = v.setDisabled(arg0)
      def setHidden(arg0: Boolean) = v.setHidden(arg0)
      def grab(arg0: io.qt.core.QRect | Null) = v.grab(arg0)
      def unwrap: io.qt.widgets.QScrollArea = v
    }
  }

  def wrap(v: io.qt.widgets.QScrollArea): ScrollArea = 
    val res = v.asInstanceOf[ScrollArea]
    if !Toolkit.stateReader(ScrollAreaInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: ScrollArea): Unit = {
    ScrollAreaBase.init(v)
    Toolkit.update(ScrollAreaInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): ScrollArea = {
    val res = new io.qt.widgets.QScrollArea()
    
    res.asInstanceOf[ScrollArea]
  }
  
  def apply(
    
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    alignment: Opt[Binding[io.qt.core.Qt.Alignment | Null]] = UnsetParam,
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
    widgetResizable: Opt[Binding[Boolean]] = UnsetParam,
    windowFilePath: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowIcon: Opt[Binding[io.qt.gui.QIcon | Null]] = UnsetParam,
    windowIconText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowModality: Opt[Binding[io.qt.core.Qt.WindowModality]] = UnsetParam,
    windowModified: Opt[Binding[Boolean]] = UnsetParam,
    windowOpacity: Opt[Binding[Double]] = UnsetParam,
    windowTitle: Opt[Binding[java.lang.String | Null]] = UnsetParam
  ): ToolkitAction[ScrollArea] = {
    val res = uninitialized()
    ScrollArea.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(alignment, ScrollArea.ops.alignment(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(frameRect, ScrollArea.ops.frameRect(res) := _)
    ifSet(frameShadow, ScrollArea.ops.frameShadow(res) := _)
    ifSet(frameShape, ScrollArea.ops.frameShape(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(horizontalScrollBarPolicy, ScrollArea.ops.horizontalScrollBarPolicy(res) := _)
    ifSet(inputMethodHints, Widget.ops.inputMethodHints(res) := _)
    ifSet(layoutDirection, Widget.ops.layoutDirection(res) := _)
    ifSet(lineWidth, ScrollArea.ops.lineWidth(res) := _)
    ifSet(locale, Widget.ops.locale(res) := _)
    ifSet(maximumHeight, Widget.ops.maximumHeight(res) := _)
    ifSet(maximumSize, Widget.ops.maximumSize(res) := _)
    ifSet(maximumWidth, Widget.ops.maximumWidth(res) := _)
    ifSet(midLineWidth, ScrollArea.ops.midLineWidth(res) := _)
    ifSet(minimumHeight, Widget.ops.minimumHeight(res) := _)
    ifSet(minimumSize, Widget.ops.minimumSize(res) := _)
    ifSet(minimumWidth, Widget.ops.minimumWidth(res) := _)
    ifSet(mouseTracking, Widget.ops.mouseTracking(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeAdjustPolicy, ScrollArea.ops.sizeAdjustPolicy(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(toolTip, Widget.ops.toolTip(res) := _)
    ifSet(toolTipDuration, Widget.ops.toolTipDuration(res) := _)
    ifSet(updatesEnabled, Widget.ops.updatesEnabled(res) := _)
    ifSet(verticalScrollBarPolicy, ScrollArea.ops.verticalScrollBarPolicy(res) := _)
    ifSet(viewport, ScrollAreaBase.ops.viewport(res) := _)
    ifSet(visible, Widget.ops.visible(res) := _)
    ifSet(whatsThis, Widget.ops.whatsThis(res) := _)
    ifSet(widgetResizable, ScrollArea.ops.widgetResizable(res) := _)
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
        