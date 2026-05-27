package guarana
package gtk
opaque type TreeView <: Widget = org.gnome.gtk.TreeView & Widget
object TreeView {
  val ActivateOnSingleClick: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("activate-on-single-click", _.getActivateOnSingleClick(), _.setActivateOnSingleClick(_), true)
  val EnableSearch: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("enable-search", _.getEnableSearch(), _.setEnableSearch(_), true)
  val EnableTreeLines: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("enable-tree-lines", _.getEnableTreeLines(), _.setEnableTreeLines(_), true)
  val FixedHeightMode: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("fixed-height-mode", _.getFixedHeightMode(), _.setFixedHeightMode(_), true)
  val HeadersClickable: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("headers-clickable", _.getHeadersClickable(), _.setHeadersClickable(_), true)
  val HeadersVisible: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("headers-visible", _.getHeadersVisible(), _.setHeadersVisible(_), true)
  val HoverExpand: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("hover-expand", _.getHoverExpand(), _.setHoverExpand(_), true)
  val HoverSelection: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("hover-selection", _.getHoverSelection(), _.setHoverSelection(_), true)
  val LevelIndentation: ExternalVar.Aux[TreeView, Int] = ExternalVar[TreeView, Int]("level-indentation", _.getLevelIndentation(), _.setLevelIndentation(_), true)
  val Reorderable: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("reorderable", _.getReorderable(), _.setReorderable(_), true)
  val RubberBanding: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("rubber-banding", _.getRubberBanding(), _.setRubberBanding(_), true)
  val SearchColumn: ExternalVar.Aux[TreeView, Int] = ExternalVar[TreeView, Int]("search-column", _.getSearchColumn(), _.setSearchColumn(_), true)
  val ShowExpanders: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("show-expanders", _.getShowExpanders(), _.setShowExpanders(_), true)
  val TooltipColumn: ExternalVar.Aux[TreeView, Int] = ExternalVar[TreeView, Int]("tooltip-column", _.getTooltipColumn(), _.setTooltipColumn(_), true)
  ()
  extension (v: TreeView) {
    def unwrap: org.gnome.gtk.TreeView = v
    export unwrap.onColumnsChanged, unwrap.onCursorChanged, unwrap.onExpandCollapseCursorRow, unwrap.onMoveCursor, unwrap.onRowActivated, unwrap.onRowCollapsed, unwrap.onRowExpanded, unwrap.onSelectAll, unwrap.onSelectCursorParent, unwrap.onSelectCursorRow, unwrap.onStartInteractiveSearch, unwrap.onTestCollapseRow, unwrap.onTestExpandRow, unwrap.onToggleCursorRow, unwrap.onUnselectAll
  }
  def init(v: TreeView): Unit = {
    Widget.init(v)
  }
  def uninitialized(): TreeView = {
    val res = new org.gnome.gtk.TreeView()
    res.asInstanceOf[TreeView]
  }
}