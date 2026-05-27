package guarana
package gtk
opaque type TreeView <: Widget = org.gnome.gtk.TreeView & Widget
object TreeView {
  @deprecated("", "") val ActivateOnSingleClick: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("activate-on-single-click", _.getActivateOnSingleClick(), _.setActivateOnSingleClick(_), true)
  @deprecated("", "") val EnableSearch: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("enable-search", _.getEnableSearch(), _.setEnableSearch(_), true)
  @deprecated("", "") val EnableTreeLines: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("enable-tree-lines", _.getEnableTreeLines(), _.setEnableTreeLines(_), true)
  @deprecated("", "") val ExpanderColumn: ExternalVar.Aux[TreeView, org.gnome.gtk.TreeViewColumn | Null] = ExternalVar[TreeView, org.gnome.gtk.TreeViewColumn | Null]("expander-column", _.getExpanderColumn(), _.setExpanderColumn(_), true)
  @deprecated("", "") val FixedHeightMode: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("fixed-height-mode", _.getFixedHeightMode(), _.setFixedHeightMode(_), true)
  @deprecated("", "") val GridLines: ExternalVar.Aux[TreeView, org.gnome.gtk.TreeViewGridLines] = ExternalVar[TreeView, org.gnome.gtk.TreeViewGridLines]("grid-lines", _.getGridLines(), _.setGridLines(_), true)
  val Hadjustment: ExternalVar.Aux[TreeView, org.gnome.gtk.Adjustment | Null] = ExternalVar[TreeView, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  @deprecated("", "") val HeadersClickable: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("headers-clickable", _.getHeadersClickable(), _.setHeadersClickable(_), true)
  @deprecated("", "") val HeadersVisible: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("headers-visible", _.getHeadersVisible(), _.setHeadersVisible(_), true)
  @deprecated("", "") val HoverExpand: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("hover-expand", _.getHoverExpand(), _.setHoverExpand(_), true)
  @deprecated("", "") val HoverSelection: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("hover-selection", _.getHoverSelection(), _.setHoverSelection(_), true)
  val HscrollPolicy: ExternalVar.Aux[TreeView, org.gnome.gtk.ScrollablePolicy] = ExternalVar[TreeView, org.gnome.gtk.ScrollablePolicy]("hscroll-policy", _.getHscrollPolicy(), _.setHscrollPolicy(_), true)
  @deprecated("", "") val LevelIndentation: ExternalVar.Aux[TreeView, Int] = ExternalVar[TreeView, Int]("level-indentation", _.getLevelIndentation(), _.setLevelIndentation(_), true)
  @deprecated("", "") val Model: ExternalVar.Aux[TreeView, org.gnome.gtk.TreeModel | Null] = ExternalVar[TreeView, org.gnome.gtk.TreeModel | Null]("model", _.getModel(), _.setModel(_), true)
  @deprecated("", "") val Reorderable: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("reorderable", _.getReorderable(), _.setReorderable(_), true)
  @deprecated("", "") val RowSeparatorFunc: ExternalVar.Aux[TreeView, org.gnome.gtk.TreeViewRowSeparatorFunc | Null] = ExternalVar[TreeView, org.gnome.gtk.TreeViewRowSeparatorFunc | Null]("row-separator-func", _.getRowSeparatorFunc(), _.setRowSeparatorFunc(_), true)
  @deprecated("", "") val RubberBanding: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("rubber-banding", _.getRubberBanding(), _.setRubberBanding(_), true)
  @deprecated("", "") val SearchColumn: ExternalVar.Aux[TreeView, Int] = ExternalVar[TreeView, Int]("search-column", _.getSearchColumn(), _.setSearchColumn(_), true)
  @deprecated("", "") val SearchEntry: ExternalVar.Aux[TreeView, org.gnome.gtk.Editable | Null] = ExternalVar[TreeView, org.gnome.gtk.Editable | Null]("search-entry", _.getSearchEntry(), _.setSearchEntry(_), true)
  @deprecated("", "") val SearchEqualFunc: ExternalVar.Aux[TreeView, org.gnome.gtk.TreeViewSearchEqualFunc | Null] = ExternalVar[TreeView, org.gnome.gtk.TreeViewSearchEqualFunc | Null]("search-equal-func", _.getSearchEqualFunc(), _.setSearchEqualFunc(_), true)
  @deprecated("", "") val ShowExpanders: ExternalVar.Aux[TreeView, Boolean] = ExternalVar[TreeView, Boolean]("show-expanders", _.getShowExpanders(), _.setShowExpanders(_), true)
  @deprecated("", "") val TooltipColumn: ExternalVar.Aux[TreeView, Int] = ExternalVar[TreeView, Int]("tooltip-column", _.getTooltipColumn(), _.setTooltipColumn(_), true)
  val Vadjustment: ExternalVar.Aux[TreeView, org.gnome.gtk.Adjustment | Null] = ExternalVar[TreeView, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  val VscrollPolicy: ExternalVar.Aux[TreeView, org.gnome.gtk.ScrollablePolicy] = ExternalVar[TreeView, org.gnome.gtk.ScrollablePolicy]("vscroll-policy", _.getVscrollPolicy(), _.setVscrollPolicy(_), true)
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