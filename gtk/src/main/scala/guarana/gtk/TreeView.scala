
package guarana
package gtk

import guarana.util.*

opaque type TreeView <: guarana.gtk.Widget  = org.gnome.gtk.TreeView & guarana.gtk.Widget
object TreeView extends VarsMap {
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

  

  extension (v: TreeView) {
    def unwrap: org.gnome.gtk.TreeView = v

    @deprecated("", "") def activateOnSingleClick: Var.Aux[Boolean, v.type] = guarana.gtk.TreeView.ActivateOnSingleClick.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def enableSearch: Var.Aux[Boolean, v.type] = guarana.gtk.TreeView.EnableSearch.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def enableTreeLines: Var.Aux[Boolean, v.type] = guarana.gtk.TreeView.EnableTreeLines.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def expanderColumn: Var.Aux[org.gnome.gtk.TreeViewColumn | Null, v.type] = guarana.gtk.TreeView.ExpanderColumn.asInstanceOf[Var.Aux[org.gnome.gtk.TreeViewColumn | Null, v.type]]
    @deprecated("", "") def fixedHeightMode: Var.Aux[Boolean, v.type] = guarana.gtk.TreeView.FixedHeightMode.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def gridLines: Var.Aux[org.gnome.gtk.TreeViewGridLines, v.type] = guarana.gtk.TreeView.GridLines.asInstanceOf[Var.Aux[org.gnome.gtk.TreeViewGridLines, v.type]]
    def hadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = guarana.gtk.TreeView.Hadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    @deprecated("", "") def headersClickable: Var.Aux[Boolean, v.type] = guarana.gtk.TreeView.HeadersClickable.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def headersVisible: Var.Aux[Boolean, v.type] = guarana.gtk.TreeView.HeadersVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def hoverExpand: Var.Aux[Boolean, v.type] = guarana.gtk.TreeView.HoverExpand.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def hoverSelection: Var.Aux[Boolean, v.type] = guarana.gtk.TreeView.HoverSelection.asInstanceOf[Var.Aux[Boolean, v.type]]
    def hscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = guarana.gtk.TreeView.HscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    @deprecated("", "") def levelIndentation: Var.Aux[Int, v.type] = guarana.gtk.TreeView.LevelIndentation.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def model: Var.Aux[org.gnome.gtk.TreeModel | Null, v.type] = guarana.gtk.TreeView.Model.asInstanceOf[Var.Aux[org.gnome.gtk.TreeModel | Null, v.type]]
    @deprecated("", "") def reorderable: Var.Aux[Boolean, v.type] = guarana.gtk.TreeView.Reorderable.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def rowSeparatorFunc: Var.Aux[org.gnome.gtk.TreeViewRowSeparatorFunc | Null, v.type] = guarana.gtk.TreeView.RowSeparatorFunc.asInstanceOf[Var.Aux[org.gnome.gtk.TreeViewRowSeparatorFunc | Null, v.type]]
    @deprecated("", "") def rubberBanding: Var.Aux[Boolean, v.type] = guarana.gtk.TreeView.RubberBanding.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def searchColumn: Var.Aux[Int, v.type] = guarana.gtk.TreeView.SearchColumn.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def searchEntry: Var.Aux[org.gnome.gtk.Editable | Null, v.type] = guarana.gtk.TreeView.SearchEntry.asInstanceOf[Var.Aux[org.gnome.gtk.Editable | Null, v.type]]
    @deprecated("", "") def searchEqualFunc: Var.Aux[org.gnome.gtk.TreeViewSearchEqualFunc | Null, v.type] = guarana.gtk.TreeView.SearchEqualFunc.asInstanceOf[Var.Aux[org.gnome.gtk.TreeViewSearchEqualFunc | Null, v.type]]
    @deprecated("", "") def showExpanders: Var.Aux[Boolean, v.type] = guarana.gtk.TreeView.ShowExpanders.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def tooltipColumn: Var.Aux[Int, v.type] = guarana.gtk.TreeView.TooltipColumn.asInstanceOf[Var.Aux[Int, v.type]]
    def vadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = guarana.gtk.TreeView.Vadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def vscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = guarana.gtk.TreeView.VscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]

    

    export unwrap.{
      onColumnsChanged,
      onCursorChanged,
      onDestroy,
      onDirectionChanged,
      onExpandCollapseCursorRow,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveCursor,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onRowActivated,
      onRowCollapsed,
      onRowExpanded,
      onSelectAll,
      onSelectCursorParent,
      onSelectCursorRow,
      onShow,
      onStartInteractiveSearch,
      onStateFlagsChanged,
      onTestCollapseRow,
      onTestExpandRow,
      onToggleCursorRow,
      onUnmap,
      onUnrealize,
      onUnselectAll
    }
  }

  def wrap(v: org.gnome.gtk.TreeView): TreeView = 
    val res = v.asInstanceOf[TreeView]
    
    res

  def init(v: TreeView): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(enableGridLines: Opt[org.gnome.gtk.TreeViewGridLines], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): TreeView = {
    val res = org.gnome.gtk.TreeView.builder()
    ifSet(enableGridLines, v => res.setEnableGridLines(v))
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    
    res.asInstanceOf[TreeView]
  }
  
  def apply(
    enableGridLines: Opt[org.gnome.gtk.TreeViewGridLines] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    activateOnSingleClick: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    enableSearch: Opt[Binding[Boolean]] = UnsetParam,
    enableTreeLines: Opt[Binding[Boolean]] = UnsetParam,
    expanderColumn: Opt[Binding[org.gnome.gtk.TreeViewColumn | Null]] = UnsetParam,
    fixedHeightMode: Opt[Binding[Boolean]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    gridLines: Opt[Binding[org.gnome.gtk.TreeViewGridLines]] = UnsetParam,
    hadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    headersClickable: Opt[Binding[Boolean]] = UnsetParam,
    headersVisible: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    hoverExpand: Opt[Binding[Boolean]] = UnsetParam,
    hoverSelection: Opt[Binding[Boolean]] = UnsetParam,
    hscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    levelIndentation: Opt[Binding[Int]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    model: Opt[Binding[org.gnome.gtk.TreeModel | Null]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    reorderable: Opt[Binding[Boolean]] = UnsetParam,
    rowSeparatorFunc: Opt[Binding[org.gnome.gtk.TreeViewRowSeparatorFunc | Null]] = UnsetParam,
    rubberBanding: Opt[Binding[Boolean]] = UnsetParam,
    searchColumn: Opt[Binding[Int]] = UnsetParam,
    searchEntry: Opt[Binding[org.gnome.gtk.Editable | Null]] = UnsetParam,
    searchEqualFunc: Opt[Binding[org.gnome.gtk.TreeViewSearchEqualFunc | Null]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showExpanders: Opt[Binding[Boolean]] = UnsetParam,
    tooltipColumn: Opt[Binding[Int]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    vadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    vscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam
  ): VarContextAction[TreeView] = {
    val res = uninitialized(enableGridLines, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.TreeView.init(res)
    ifSet(activateOnSingleClick, res.activateOnSingleClick := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(enableSearch, res.enableSearch := _)
    ifSet(enableTreeLines, res.enableTreeLines := _)
    ifSet(expanderColumn, res.expanderColumn := _)
    ifSet(fixedHeightMode, res.fixedHeightMode := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(gridLines, res.gridLines := _)
    ifSet(hadjustment, res.hadjustment := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(headersClickable, res.headersClickable := _)
    ifSet(headersVisible, res.headersVisible := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(hoverExpand, res.hoverExpand := _)
    ifSet(hoverSelection, res.hoverSelection := _)
    ifSet(hscrollPolicy, res.hscrollPolicy := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(levelIndentation, res.levelIndentation := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(model, res.model := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(reorderable, res.reorderable := _)
    ifSet(rowSeparatorFunc, res.rowSeparatorFunc := _)
    ifSet(rubberBanding, res.rubberBanding := _)
    ifSet(searchColumn, res.searchColumn := _)
    ifSet(searchEntry, res.searchEntry := _)
    ifSet(searchEqualFunc, res.searchEqualFunc := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showExpanders, res.showExpanders := _)
    ifSet(tooltipColumn, res.tooltipColumn := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(vadjustment, res.vadjustment := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(vscrollPolicy, res.vscrollPolicy := _)
    res
  }
  
}
        