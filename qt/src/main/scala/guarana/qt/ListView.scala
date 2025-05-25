
package guarana
package qt
        
import io.qt.widgets.*
import util.*

opaque type ListView <: ItemViewBase  = io.qt.widgets.QListView & ItemViewBase
object ListView {
  private val ListViewInitialized: Var[Boolean] = Var[Boolean]("ListViewInitialized", false, false)
  val BatchSize: ExternalVar.Aux[ListView, Int] = ExternalVar[ListView, Int]("batchSize", _.batchSize(), _.setBatchSize(_), true)
  val Flow: ExternalVar.Aux[ListView, io.qt.widgets.QListView.Flow] = ExternalVar[ListView, io.qt.widgets.QListView.Flow]("flow", _.flow().unn, _.setFlow(_), true)
  val GridSize: ExternalVar.Aux[ListView, io.qt.core.QSize | Null] = ExternalVar[ListView, io.qt.core.QSize | Null]("gridSize", _.gridSize(), _.setGridSize(_), true)
  val IsWrapping: ExternalVar.Aux[ListView, Boolean] = ExternalVar[ListView, Boolean]("isWrapping", _.isWrapping(), _.setWrapping(_), true)
  val ItemAlignment: ExternalVar.Aux[ListView, io.qt.core.Qt.Alignment | Null] = ExternalVar[ListView, io.qt.core.Qt.Alignment | Null]("itemAlignment", _.itemAlignment(), _.setItemAlignment(_), true)
  val LayoutMode: ExternalVar.Aux[ListView, io.qt.widgets.QListView.LayoutMode] = ExternalVar[ListView, io.qt.widgets.QListView.LayoutMode]("layoutMode", _.layoutMode().unn, _.setLayoutMode(_), true)
  val ModelColumn: ExternalVar.Aux[ListView, Int] = ExternalVar[ListView, Int]("modelColumn", _.modelColumn(), _.setModelColumn(_), true)
  val Movement: ExternalVar.Aux[ListView, io.qt.widgets.QListView.Movement] = ExternalVar[ListView, io.qt.widgets.QListView.Movement]("movement", _.movement().unn, _.setMovement(_), true)
  val ResizeMode: ExternalVar.Aux[ListView, io.qt.widgets.QListView.ResizeMode] = ExternalVar[ListView, io.qt.widgets.QListView.ResizeMode]("resizeMode", _.resizeMode().unn, _.setResizeMode(_), true)
  val SelectionRectVisible: ExternalVar.Aux[ListView, Boolean] = ExternalVar[ListView, Boolean]("selectionRectVisible", _.isSelectionRectVisible(), _.setSelectionRectVisible(_), true)
  val Spacing: ExternalVar.Aux[ListView, Int] = ExternalVar[ListView, Int]("spacing", _.spacing(), _.setSpacing(_), true)
  val UniformItemSizes: ExternalVar.Aux[ListView, Boolean] = ExternalVar[ListView, Boolean]("uniformItemSizes", _.uniformItemSizes(), _.setUniformItemSizes(_), true)
  val ViewMode: ExternalVar.Aux[ListView, io.qt.widgets.QListView.ViewMode] = ExternalVar[ListView, io.qt.widgets.QListView.ViewMode]("viewMode", _.viewMode().unn, _.setViewMode(_), true)
  val WordWrap: ExternalVar.Aux[ListView, Boolean] = ExternalVar[ListView, Boolean]("wordWrap", _.wordWrap(), _.setWordWrap(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: ListView) {
      def batchSize: Var.Aux[Int, v.type] = ListView.BatchSize.asInstanceOf[Var.Aux[Int, v.type]]
      def flow: Var.Aux[io.qt.widgets.QListView.Flow, v.type] = ListView.Flow.asInstanceOf[Var.Aux[io.qt.widgets.QListView.Flow, v.type]]
      def gridSize: Var.Aux[io.qt.core.QSize | Null, v.type] = ListView.GridSize.asInstanceOf[Var.Aux[io.qt.core.QSize | Null, v.type]]
      def isWrapping: Var.Aux[Boolean, v.type] = ListView.IsWrapping.asInstanceOf[Var.Aux[Boolean, v.type]]
      def itemAlignment: Var.Aux[io.qt.core.Qt.Alignment | Null, v.type] = ListView.ItemAlignment.asInstanceOf[Var.Aux[io.qt.core.Qt.Alignment | Null, v.type]]
      def layoutMode: Var.Aux[io.qt.widgets.QListView.LayoutMode, v.type] = ListView.LayoutMode.asInstanceOf[Var.Aux[io.qt.widgets.QListView.LayoutMode, v.type]]
      def modelColumn: Var.Aux[Int, v.type] = ListView.ModelColumn.asInstanceOf[Var.Aux[Int, v.type]]
      def movement: Var.Aux[io.qt.widgets.QListView.Movement, v.type] = ListView.Movement.asInstanceOf[Var.Aux[io.qt.widgets.QListView.Movement, v.type]]
      def resizeMode: Var.Aux[io.qt.widgets.QListView.ResizeMode, v.type] = ListView.ResizeMode.asInstanceOf[Var.Aux[io.qt.widgets.QListView.ResizeMode, v.type]]
      def selectionRectVisible: Var.Aux[Boolean, v.type] = ListView.SelectionRectVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
      def spacing: Var.Aux[Int, v.type] = ListView.Spacing.asInstanceOf[Var.Aux[Int, v.type]]
      def uniformItemSizes: Var.Aux[Boolean, v.type] = ListView.UniformItemSizes.asInstanceOf[Var.Aux[Boolean, v.type]]
      def viewMode: Var.Aux[io.qt.widgets.QListView.ViewMode, v.type] = ListView.ViewMode.asInstanceOf[Var.Aux[io.qt.widgets.QListView.ViewMode, v.type]]
      def wordWrap: Var.Aux[Boolean, v.type] = ListView.WordWrap.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      
      def unwrap: io.qt.widgets.QListView = v
    }
  }

  def wrap(v: io.qt.widgets.QListView): ListView = 
    val res = v.asInstanceOf[ListView]
    if !Toolkit.stateReader(ListViewInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: ListView): Unit = {
    ItemViewBase.init(v)
    Toolkit.update(ListViewInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): ListView = {
    val res = new io.qt.widgets.QListView()
    
    res.asInstanceOf[ListView]
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
    batchSize: Opt[Binding[Int]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    defaultDropAction: Opt[Binding[io.qt.core.Qt.DropAction]] = UnsetParam,
    dragDropMode: Opt[Binding[io.qt.widgets.QAbstractItemView.DragDropMode]] = UnsetParam,
    dragDropOverwriteMode: Opt[Binding[Boolean]] = UnsetParam,
    dragEnabled: Opt[Binding[Boolean]] = UnsetParam,
    editTriggers: Opt[Binding[io.qt.widgets.QAbstractItemView.EditTriggers | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    flow: Opt[Binding[io.qt.widgets.QListView.Flow]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    frameRect: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    frameShadow: Opt[Binding[io.qt.widgets.QFrame.Shadow]] = UnsetParam,
    frameShape: Opt[Binding[io.qt.widgets.QFrame.Shape]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    gridSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    horizontalScrollBarPolicy: Opt[Binding[io.qt.core.Qt.ScrollBarPolicy]] = UnsetParam,
    horizontalScrollMode: Opt[Binding[io.qt.widgets.QAbstractItemView.ScrollMode]] = UnsetParam,
    iconSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    inputMethodHints: Opt[Binding[io.qt.core.Qt.InputMethodHints | Null]] = UnsetParam,
    isWrapping: Opt[Binding[Boolean]] = UnsetParam,
    itemAlignment: Opt[Binding[io.qt.core.Qt.Alignment | Null]] = UnsetParam,
    layoutDirection: Opt[Binding[io.qt.core.Qt.LayoutDirection]] = UnsetParam,
    layoutMode: Opt[Binding[io.qt.widgets.QListView.LayoutMode]] = UnsetParam,
    lineWidth: Opt[Binding[Int]] = UnsetParam,
    locale: Opt[Binding[io.qt.core.QLocale | Null]] = UnsetParam,
    maximumHeight: Opt[Binding[Int]] = UnsetParam,
    maximumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    maximumWidth: Opt[Binding[Int]] = UnsetParam,
    midLineWidth: Opt[Binding[Int]] = UnsetParam,
    minimumHeight: Opt[Binding[Int]] = UnsetParam,
    minimumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    minimumWidth: Opt[Binding[Int]] = UnsetParam,
    modelColumn: Opt[Binding[Int]] = UnsetParam,
    mouseTracking: Opt[Binding[Boolean]] = UnsetParam,
    movement: Opt[Binding[io.qt.widgets.QListView.Movement]] = UnsetParam,
    objectName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    resizeMode: Opt[Binding[io.qt.widgets.QListView.ResizeMode]] = UnsetParam,
    selectionBehavior: Opt[Binding[io.qt.widgets.QAbstractItemView.SelectionBehavior]] = UnsetParam,
    selectionMode: Opt[Binding[io.qt.widgets.QAbstractItemView.SelectionMode]] = UnsetParam,
    selectionRectVisible: Opt[Binding[Boolean]] = UnsetParam,
    showDropIndicator: Opt[Binding[Boolean]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeAdjustPolicy: Opt[Binding[io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    spacing: Opt[Binding[Int]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabKeyNavigation: Opt[Binding[Boolean]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
    textElideMode: Opt[Binding[io.qt.core.Qt.TextElideMode]] = UnsetParam,
    toolTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipDuration: Opt[Binding[Int]] = UnsetParam,
    uniformItemSizes: Opt[Binding[Boolean]] = UnsetParam,
    updatesEnabled: Opt[Binding[Boolean]] = UnsetParam,
    verticalScrollBarPolicy: Opt[Binding[io.qt.core.Qt.ScrollBarPolicy]] = UnsetParam,
    verticalScrollMode: Opt[Binding[io.qt.widgets.QAbstractItemView.ScrollMode]] = UnsetParam,
    viewMode: Opt[Binding[io.qt.widgets.QListView.ViewMode]] = UnsetParam,
    viewport: Opt[Binding[Widget]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    whatsThis: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowFilePath: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowIcon: Opt[Binding[io.qt.gui.QIcon | Null]] = UnsetParam,
    windowIconText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowModality: Opt[Binding[io.qt.core.Qt.WindowModality]] = UnsetParam,
    windowModified: Opt[Binding[Boolean]] = UnsetParam,
    windowOpacity: Opt[Binding[Double]] = UnsetParam,
    windowTitle: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    wordWrap: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[ListView] = {
    val res = uninitialized()
    ListView.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(alternatingRowColors, ItemViewBase.ops.alternatingRowColors(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(autoScroll, ItemViewBase.ops.autoScroll(res) := _)
    ifSet(autoScrollMargin, ItemViewBase.ops.autoScrollMargin(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(batchSize, ListView.ops.batchSize(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(defaultDropAction, ItemViewBase.ops.defaultDropAction(res) := _)
    ifSet(dragDropMode, ItemViewBase.ops.dragDropMode(res) := _)
    ifSet(dragDropOverwriteMode, ItemViewBase.ops.dragDropOverwriteMode(res) := _)
    ifSet(dragEnabled, ItemViewBase.ops.dragEnabled(res) := _)
    ifSet(editTriggers, ItemViewBase.ops.editTriggers(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(flow, ListView.ops.flow(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(frameRect, ScrollAreaBase.ops.frameRect(res) := _)
    ifSet(frameShadow, ScrollAreaBase.ops.frameShadow(res) := _)
    ifSet(frameShape, ScrollAreaBase.ops.frameShape(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(gridSize, ListView.ops.gridSize(res) := _)
    ifSet(horizontalScrollBarPolicy, ScrollAreaBase.ops.horizontalScrollBarPolicy(res) := _)
    ifSet(horizontalScrollMode, ItemViewBase.ops.horizontalScrollMode(res) := _)
    ifSet(iconSize, ItemViewBase.ops.iconSize(res) := _)
    ifSet(inputMethodHints, Widget.ops.inputMethodHints(res) := _)
    ifSet(isWrapping, ListView.ops.isWrapping(res) := _)
    ifSet(itemAlignment, ListView.ops.itemAlignment(res) := _)
    ifSet(layoutDirection, Widget.ops.layoutDirection(res) := _)
    ifSet(layoutMode, ListView.ops.layoutMode(res) := _)
    ifSet(lineWidth, ScrollAreaBase.ops.lineWidth(res) := _)
    ifSet(locale, Widget.ops.locale(res) := _)
    ifSet(maximumHeight, Widget.ops.maximumHeight(res) := _)
    ifSet(maximumSize, Widget.ops.maximumSize(res) := _)
    ifSet(maximumWidth, Widget.ops.maximumWidth(res) := _)
    ifSet(midLineWidth, ScrollAreaBase.ops.midLineWidth(res) := _)
    ifSet(minimumHeight, Widget.ops.minimumHeight(res) := _)
    ifSet(minimumSize, Widget.ops.minimumSize(res) := _)
    ifSet(minimumWidth, Widget.ops.minimumWidth(res) := _)
    ifSet(modelColumn, ListView.ops.modelColumn(res) := _)
    ifSet(mouseTracking, Widget.ops.mouseTracking(res) := _)
    ifSet(movement, ListView.ops.movement(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(resizeMode, ListView.ops.resizeMode(res) := _)
    ifSet(selectionBehavior, ItemViewBase.ops.selectionBehavior(res) := _)
    ifSet(selectionMode, ItemViewBase.ops.selectionMode(res) := _)
    ifSet(selectionRectVisible, ListView.ops.selectionRectVisible(res) := _)
    ifSet(showDropIndicator, ItemViewBase.ops.showDropIndicator(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeAdjustPolicy, ScrollAreaBase.ops.sizeAdjustPolicy(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(spacing, ListView.ops.spacing(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabKeyNavigation, ItemViewBase.ops.tabKeyNavigation(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(textElideMode, ItemViewBase.ops.textElideMode(res) := _)
    ifSet(toolTip, Widget.ops.toolTip(res) := _)
    ifSet(toolTipDuration, Widget.ops.toolTipDuration(res) := _)
    ifSet(uniformItemSizes, ListView.ops.uniformItemSizes(res) := _)
    ifSet(updatesEnabled, Widget.ops.updatesEnabled(res) := _)
    ifSet(verticalScrollBarPolicy, ScrollAreaBase.ops.verticalScrollBarPolicy(res) := _)
    ifSet(verticalScrollMode, ItemViewBase.ops.verticalScrollMode(res) := _)
    ifSet(viewMode, ListView.ops.viewMode(res) := _)
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
    ifSet(wordWrap, ListView.ops.wordWrap(res) := _)
    res
  }
  
}
        