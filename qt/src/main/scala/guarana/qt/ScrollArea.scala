
package guarana
package qt
        
import io.qt.core.Qt
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type ScrollArea <: ScrollAreaBase  = io.qt.widgets.QScrollArea & ScrollAreaBase
object ScrollArea {
  private val ScrollAreaInitialized: Var[Boolean] = Var[Boolean]("ScrollAreaInitialized", false, false)
  val Alignment: ExternalVar.Aux[ScrollArea, io.qt.core.Qt.Alignment | Null] = ExternalVar[ScrollArea, io.qt.core.Qt.Alignment | Null]("alignment", _.alignment(), _.setAlignment(_), true)
  val Content: ExternalVar.Aux[ScrollArea, Widget | Null] = ExternalVar[ScrollArea, Widget | Null]("content", _.widget(), (c, v) => c.setWidget(v.?(_.unwrap)), true)
  val WidgetResizable: ExternalVar.Aux[ScrollArea, Boolean] = ExternalVar[ScrollArea, Boolean]("widgetResizable", _.widgetResizable(), _.setWidgetResizable(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: ScrollArea) {
      def alignment: Var.Aux[io.qt.core.Qt.Alignment | Null, v.type] = ScrollArea.Alignment.asInstanceOf[Var.Aux[io.qt.core.Qt.Alignment | Null, v.type]]
      def content: Var.Aux[Widget | Null, v.type] = ScrollArea.Content.asInstanceOf[Var.Aux[Widget | Null, v.type]]
      def widgetResizable: Var.Aux[Boolean, v.type] = ScrollArea.WidgetResizable.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      
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
    content: Opt[Binding[Widget | Null]] = UnsetParam,
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
  ): VarContextAction[ScrollArea] = {
    val res = uninitialized()
    ScrollArea.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(alignment, ScrollArea.ops.alignment(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(content, ScrollArea.ops.content(res) := _)
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
        