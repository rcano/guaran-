
package guarana
package qt
        
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type ColumnView <: AbstractItemView  = io.qt.widgets.QColumnView & AbstractItemView
object ColumnView {
  private val ColumnViewInitialized: Var[Boolean] = Var[Boolean]("ColumnViewInitialized", false, false)
  val ResizeGripsVisible: ExternalVar.Aux[ColumnView, Boolean] = ExternalVar[ColumnView, Boolean]("resizeGripsVisible", _.resizeGripsVisible(), _.setResizeGripsVisible(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: ColumnView) {
      def resizeGripsVisible: Var.Aux[Boolean, v.type] = ColumnView.ResizeGripsVisible.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      
      def unwrap: io.qt.widgets.QColumnView = v
    }
  }

  def wrap(v: io.qt.widgets.QColumnView): ColumnView = 
    val res = v.asInstanceOf[ColumnView]
    if !Toolkit.stateReader(ColumnViewInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: ColumnView): Unit = {
    AbstractItemView.init(v)
    Toolkit.update(ColumnViewInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): ColumnView = {
    val res = new io.qt.widgets.QColumnView()
    
    res.asInstanceOf[ColumnView]
  }
  
  def apply(
    
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    alternatingRowColors: Opt[Binding[Boolean]] = UnsetParam,
    autoFillBackground: Opt[Binding[Boolean]] = UnsetParam,
    autoScroll: Opt[Binding[Boolean]] = UnsetParam,
    autoScrollMargin: Opt[Binding[Int]] = UnsetParam,
    baseSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    defaultDropAction: Opt[Binding[io.qt.core.Qt.DropAction]] = UnsetParam,
    dragDropMode: Opt[Binding[io.qt.widgets.QAbstractItemView.DragDropMode]] = UnsetParam,
    dragDropOverwriteMode: Opt[Binding[Boolean]] = UnsetParam,
    dragEnabled: Opt[Binding[Boolean]] = UnsetParam,
    editTriggers: Opt[Binding[io.qt.widgets.QAbstractItemView.EditTriggers | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    frameRect: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    frameShadow: Opt[Binding[io.qt.widgets.QFrame.Shadow]] = UnsetParam,
    frameShape: Opt[Binding[io.qt.widgets.QFrame.Shape]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    horizontalScrollBarPolicy: Opt[Binding[io.qt.core.Qt.ScrollBarPolicy]] = UnsetParam,
    horizontalScrollMode: Opt[Binding[io.qt.widgets.QAbstractItemView.ScrollMode]] = UnsetParam,
    iconSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
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
    resizeGripsVisible: Opt[Binding[Boolean]] = UnsetParam,
    selectionBehavior: Opt[Binding[io.qt.widgets.QAbstractItemView.SelectionBehavior]] = UnsetParam,
    selectionMode: Opt[Binding[io.qt.widgets.QAbstractItemView.SelectionMode]] = UnsetParam,
    showDropIndicator: Opt[Binding[Boolean]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeAdjustPolicy: Opt[Binding[io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabKeyNavigation: Opt[Binding[Boolean]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
    textElideMode: Opt[Binding[io.qt.core.Qt.TextElideMode]] = UnsetParam,
    toolTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipDuration: Opt[Binding[Int]] = UnsetParam,
    updatesEnabled: Opt[Binding[Boolean]] = UnsetParam,
    verticalScrollBarPolicy: Opt[Binding[io.qt.core.Qt.ScrollBarPolicy]] = UnsetParam,
    verticalScrollMode: Opt[Binding[io.qt.widgets.QAbstractItemView.ScrollMode]] = UnsetParam,
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
  ): ToolkitAction[ColumnView] = {
    val res = uninitialized()
    ColumnView.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(alternatingRowColors, AbstractItemView.ops.alternatingRowColors(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(autoScroll, AbstractItemView.ops.autoScroll(res) := _)
    ifSet(autoScrollMargin, AbstractItemView.ops.autoScrollMargin(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(defaultDropAction, AbstractItemView.ops.defaultDropAction(res) := _)
    ifSet(dragDropMode, AbstractItemView.ops.dragDropMode(res) := _)
    ifSet(dragDropOverwriteMode, AbstractItemView.ops.dragDropOverwriteMode(res) := _)
    ifSet(dragEnabled, AbstractItemView.ops.dragEnabled(res) := _)
    ifSet(editTriggers, AbstractItemView.ops.editTriggers(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(frameRect, ScrollAreaBase.ops.frameRect(res) := _)
    ifSet(frameShadow, ScrollAreaBase.ops.frameShadow(res) := _)
    ifSet(frameShape, ScrollAreaBase.ops.frameShape(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(horizontalScrollBarPolicy, ScrollAreaBase.ops.horizontalScrollBarPolicy(res) := _)
    ifSet(horizontalScrollMode, AbstractItemView.ops.horizontalScrollMode(res) := _)
    ifSet(iconSize, AbstractItemView.ops.iconSize(res) := _)
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
    ifSet(resizeGripsVisible, ColumnView.ops.resizeGripsVisible(res) := _)
    ifSet(selectionBehavior, AbstractItemView.ops.selectionBehavior(res) := _)
    ifSet(selectionMode, AbstractItemView.ops.selectionMode(res) := _)
    ifSet(showDropIndicator, AbstractItemView.ops.showDropIndicator(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeAdjustPolicy, ScrollAreaBase.ops.sizeAdjustPolicy(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabKeyNavigation, AbstractItemView.ops.tabKeyNavigation(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(textElideMode, AbstractItemView.ops.textElideMode(res) := _)
    ifSet(toolTip, Widget.ops.toolTip(res) := _)
    ifSet(toolTipDuration, Widget.ops.toolTipDuration(res) := _)
    ifSet(updatesEnabled, Widget.ops.updatesEnabled(res) := _)
    ifSet(verticalScrollBarPolicy, ScrollAreaBase.ops.verticalScrollBarPolicy(res) := _)
    ifSet(verticalScrollMode, AbstractItemView.ops.verticalScrollMode(res) := _)
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
        