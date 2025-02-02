
package guarana
package qt
        
import io.qt.core.Qt
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type HeaderView <: ItemViewBase  = io.qt.widgets.QHeaderView & ItemViewBase
object HeaderView {
  private val HeaderViewInitialized: Var[Boolean] = Var[Boolean]("HeaderViewInitialized", false, false)
  val CascadingSectionResizes: ExternalVar.Aux[HeaderView, Boolean] = ExternalVar[HeaderView, Boolean]("cascadingSectionResizes", _.cascadingSectionResizes(), _.setCascadingSectionResizes(_), true)
  val DefaultAlignment: ExternalVar.Aux[HeaderView, io.qt.core.Qt.Alignment | Null] = ExternalVar[HeaderView, io.qt.core.Qt.Alignment | Null]("defaultAlignment", _.defaultAlignment(), _.setDefaultAlignment(_), true)
  val DefaultSectionSize: ExternalVar.Aux[HeaderView, Int] = ExternalVar[HeaderView, Int]("defaultSectionSize", _.defaultSectionSize(), _.setDefaultSectionSize(_), true)
  val FirstSectionMovable: ExternalVar.Aux[HeaderView, Boolean] = ExternalVar[HeaderView, Boolean]("firstSectionMovable", _.isFirstSectionMovable(), _.setFirstSectionMovable(_), true)
  val HighlightSections: ExternalVar.Aux[HeaderView, Boolean] = ExternalVar[HeaderView, Boolean]("highlightSections", _.highlightSections(), _.setHighlightSections(_), true)
  val MaximumSectionSize: ExternalVar.Aux[HeaderView, Int] = ExternalVar[HeaderView, Int]("maximumSectionSize", _.maximumSectionSize(), _.setMaximumSectionSize(_), true)
  val MinimumSectionSize: ExternalVar.Aux[HeaderView, Int] = ExternalVar[HeaderView, Int]("minimumSectionSize", _.minimumSectionSize(), _.setMinimumSectionSize(_), true)
  val ShowSortIndicator: ExternalVar.Aux[HeaderView, Boolean] = ExternalVar[HeaderView, Boolean]("showSortIndicator", _.isSortIndicatorShown(), _.setSortIndicatorShown(_), true)
  val SortIndicatorClearable: ExternalVar.Aux[HeaderView, Boolean] = ExternalVar[HeaderView, Boolean]("sortIndicatorClearable", _.isSortIndicatorClearable(), _.setSortIndicatorClearable(_), true)
  val StretchLastSection: ExternalVar.Aux[HeaderView, Boolean] = ExternalVar[HeaderView, Boolean]("stretchLastSection", _.stretchLastSection(), _.setStretchLastSection(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: HeaderView) {
      def cascadingSectionResizes: Var.Aux[Boolean, v.type] = HeaderView.CascadingSectionResizes.asInstanceOf[Var.Aux[Boolean, v.type]]
      def defaultAlignment: Var.Aux[io.qt.core.Qt.Alignment | Null, v.type] = HeaderView.DefaultAlignment.asInstanceOf[Var.Aux[io.qt.core.Qt.Alignment | Null, v.type]]
      def defaultSectionSize: Var.Aux[Int, v.type] = HeaderView.DefaultSectionSize.asInstanceOf[Var.Aux[Int, v.type]]
      def firstSectionMovable: Var.Aux[Boolean, v.type] = HeaderView.FirstSectionMovable.asInstanceOf[Var.Aux[Boolean, v.type]]
      def highlightSections: Var.Aux[Boolean, v.type] = HeaderView.HighlightSections.asInstanceOf[Var.Aux[Boolean, v.type]]
      def maximumSectionSize: Var.Aux[Int, v.type] = HeaderView.MaximumSectionSize.asInstanceOf[Var.Aux[Int, v.type]]
      def minimumSectionSize: Var.Aux[Int, v.type] = HeaderView.MinimumSectionSize.asInstanceOf[Var.Aux[Int, v.type]]
      def showSortIndicator: Var.Aux[Boolean, v.type] = HeaderView.ShowSortIndicator.asInstanceOf[Var.Aux[Boolean, v.type]]
      def sortIndicatorClearable: Var.Aux[Boolean, v.type] = HeaderView.SortIndicatorClearable.asInstanceOf[Var.Aux[Boolean, v.type]]
      def stretchLastSection: Var.Aux[Boolean, v.type] = HeaderView.StretchLastSection.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      def setOffset(arg0: Int) = v.setOffset(arg0)
      def setOffsetToSectionPosition(arg0: Int) = v.setOffsetToSectionPosition(arg0)
      def setOffsetToLastSection() = v.setOffsetToLastSection()
      def headerDataChanged(arg0: io.qt.core.Qt.Orientation, arg1: Int, arg2: Int) = v.headerDataChanged(arg0, arg1, arg2)
      def unwrap: io.qt.widgets.QHeaderView = v
    }
  }

  def wrap(v: io.qt.widgets.QHeaderView): HeaderView = 
    val res = v.asInstanceOf[HeaderView]
    if !Toolkit.stateReader(HeaderViewInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: HeaderView): Unit = {
    ItemViewBase.init(v)
    Toolkit.connectVar(SortIndicatorClearable.forInstance[v.type], v.sortIndicatorClearableChanged.unn)
    Toolkit.update(HeaderViewInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(orientation: io.qt.core.Qt.Orientation, parent: Widget | Null = null): HeaderView = {
    val res = new io.qt.widgets.QHeaderView(orientation, parent.?(_.unwrap))
    
    res.asInstanceOf[HeaderView]
  }
  
  def apply(
    orientation: io.qt.core.Qt.Orientation, parent: Widget | Null = null,
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    alternatingRowColors: Opt[Binding[Boolean]] = UnsetParam,
    autoFillBackground: Opt[Binding[Boolean]] = UnsetParam,
    autoScroll: Opt[Binding[Boolean]] = UnsetParam,
    autoScrollMargin: Opt[Binding[Int]] = UnsetParam,
    baseSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    cascadingSectionResizes: Opt[Binding[Boolean]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    defaultAlignment: Opt[Binding[io.qt.core.Qt.Alignment | Null]] = UnsetParam,
    defaultDropAction: Opt[Binding[io.qt.core.Qt.DropAction]] = UnsetParam,
    defaultSectionSize: Opt[Binding[Int]] = UnsetParam,
    dragDropMode: Opt[Binding[io.qt.widgets.QAbstractItemView.DragDropMode]] = UnsetParam,
    dragDropOverwriteMode: Opt[Binding[Boolean]] = UnsetParam,
    dragEnabled: Opt[Binding[Boolean]] = UnsetParam,
    editTriggers: Opt[Binding[io.qt.widgets.QAbstractItemView.EditTriggers | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    firstSectionMovable: Opt[Binding[Boolean]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    frameRect: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    frameShadow: Opt[Binding[io.qt.widgets.QFrame.Shadow]] = UnsetParam,
    frameShape: Opt[Binding[io.qt.widgets.QFrame.Shape]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    highlightSections: Opt[Binding[Boolean]] = UnsetParam,
    horizontalScrollBarPolicy: Opt[Binding[io.qt.core.Qt.ScrollBarPolicy]] = UnsetParam,
    horizontalScrollMode: Opt[Binding[io.qt.widgets.QAbstractItemView.ScrollMode]] = UnsetParam,
    iconSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    inputMethodHints: Opt[Binding[io.qt.core.Qt.InputMethodHints | Null]] = UnsetParam,
    layoutDirection: Opt[Binding[io.qt.core.Qt.LayoutDirection]] = UnsetParam,
    lineWidth: Opt[Binding[Int]] = UnsetParam,
    locale: Opt[Binding[io.qt.core.QLocale | Null]] = UnsetParam,
    maximumHeight: Opt[Binding[Int]] = UnsetParam,
    maximumSectionSize: Opt[Binding[Int]] = UnsetParam,
    maximumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    maximumWidth: Opt[Binding[Int]] = UnsetParam,
    midLineWidth: Opt[Binding[Int]] = UnsetParam,
    minimumHeight: Opt[Binding[Int]] = UnsetParam,
    minimumSectionSize: Opt[Binding[Int]] = UnsetParam,
    minimumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    minimumWidth: Opt[Binding[Int]] = UnsetParam,
    mouseTracking: Opt[Binding[Boolean]] = UnsetParam,
    objectName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    selectionBehavior: Opt[Binding[io.qt.widgets.QAbstractItemView.SelectionBehavior]] = UnsetParam,
    selectionMode: Opt[Binding[io.qt.widgets.QAbstractItemView.SelectionMode]] = UnsetParam,
    showDropIndicator: Opt[Binding[Boolean]] = UnsetParam,
    showSortIndicator: Opt[Binding[Boolean]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeAdjustPolicy: Opt[Binding[io.qt.widgets.QAbstractScrollArea.SizeAdjustPolicy]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    sortIndicatorClearable: Opt[Binding[Boolean]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    stretchLastSection: Opt[Binding[Boolean]] = UnsetParam,
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
  ): VarContextAction[HeaderView] = {
    val res = uninitialized(orientation, parent)
    HeaderView.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(alternatingRowColors, ItemViewBase.ops.alternatingRowColors(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(autoScroll, ItemViewBase.ops.autoScroll(res) := _)
    ifSet(autoScrollMargin, ItemViewBase.ops.autoScrollMargin(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(cascadingSectionResizes, HeaderView.ops.cascadingSectionResizes(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(defaultAlignment, HeaderView.ops.defaultAlignment(res) := _)
    ifSet(defaultDropAction, ItemViewBase.ops.defaultDropAction(res) := _)
    ifSet(defaultSectionSize, HeaderView.ops.defaultSectionSize(res) := _)
    ifSet(dragDropMode, ItemViewBase.ops.dragDropMode(res) := _)
    ifSet(dragDropOverwriteMode, ItemViewBase.ops.dragDropOverwriteMode(res) := _)
    ifSet(dragEnabled, ItemViewBase.ops.dragEnabled(res) := _)
    ifSet(editTriggers, ItemViewBase.ops.editTriggers(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(firstSectionMovable, HeaderView.ops.firstSectionMovable(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(frameRect, ScrollAreaBase.ops.frameRect(res) := _)
    ifSet(frameShadow, ScrollAreaBase.ops.frameShadow(res) := _)
    ifSet(frameShape, ScrollAreaBase.ops.frameShape(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(highlightSections, HeaderView.ops.highlightSections(res) := _)
    ifSet(horizontalScrollBarPolicy, ScrollAreaBase.ops.horizontalScrollBarPolicy(res) := _)
    ifSet(horizontalScrollMode, ItemViewBase.ops.horizontalScrollMode(res) := _)
    ifSet(iconSize, ItemViewBase.ops.iconSize(res) := _)
    ifSet(inputMethodHints, Widget.ops.inputMethodHints(res) := _)
    ifSet(layoutDirection, Widget.ops.layoutDirection(res) := _)
    ifSet(lineWidth, ScrollAreaBase.ops.lineWidth(res) := _)
    ifSet(locale, Widget.ops.locale(res) := _)
    ifSet(maximumHeight, Widget.ops.maximumHeight(res) := _)
    ifSet(maximumSectionSize, HeaderView.ops.maximumSectionSize(res) := _)
    ifSet(maximumSize, Widget.ops.maximumSize(res) := _)
    ifSet(maximumWidth, Widget.ops.maximumWidth(res) := _)
    ifSet(midLineWidth, ScrollAreaBase.ops.midLineWidth(res) := _)
    ifSet(minimumHeight, Widget.ops.minimumHeight(res) := _)
    ifSet(minimumSectionSize, HeaderView.ops.minimumSectionSize(res) := _)
    ifSet(minimumSize, Widget.ops.minimumSize(res) := _)
    ifSet(minimumWidth, Widget.ops.minimumWidth(res) := _)
    ifSet(mouseTracking, Widget.ops.mouseTracking(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(selectionBehavior, ItemViewBase.ops.selectionBehavior(res) := _)
    ifSet(selectionMode, ItemViewBase.ops.selectionMode(res) := _)
    ifSet(showDropIndicator, ItemViewBase.ops.showDropIndicator(res) := _)
    ifSet(showSortIndicator, HeaderView.ops.showSortIndicator(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeAdjustPolicy, ScrollAreaBase.ops.sizeAdjustPolicy(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(sortIndicatorClearable, HeaderView.ops.sortIndicatorClearable(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(stretchLastSection, HeaderView.ops.stretchLastSection(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabKeyNavigation, ItemViewBase.ops.tabKeyNavigation(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(textElideMode, ItemViewBase.ops.textElideMode(res) := _)
    ifSet(toolTip, Widget.ops.toolTip(res) := _)
    ifSet(toolTipDuration, Widget.ops.toolTipDuration(res) := _)
    ifSet(updatesEnabled, Widget.ops.updatesEnabled(res) := _)
    ifSet(verticalScrollBarPolicy, ScrollAreaBase.ops.verticalScrollBarPolicy(res) := _)
    ifSet(verticalScrollMode, ItemViewBase.ops.verticalScrollMode(res) := _)
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
        