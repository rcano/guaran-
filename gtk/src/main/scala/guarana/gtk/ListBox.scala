package guarana
package gtk
import util.*
opaque type ListBox <: Widget = org.gnome.gtk.ListBox & Widget
object ListBox extends VarsMap {
  val ActivateOnSingleClick: ExternalVar.Aux[ListBox, Boolean] = ExternalVar[ListBox, Boolean]("activate-on-single-click", _.getActivateOnSingleClick(), _.setActivateOnSingleClick(_), true)
  val Adjustment: ExternalVar.Aux[ListBox, org.gnome.gtk.Adjustment | Null] = ExternalVar[ListBox, org.gnome.gtk.Adjustment | Null]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  val SelectionMode: ExternalVar.Aux[ListBox, org.gnome.gtk.SelectionMode] = ExternalVar[ListBox, org.gnome.gtk.SelectionMode]("selection-mode", _.getSelectionMode(), _.setSelectionMode(_), true)
  val ShowSeparators: ExternalVar.Aux[ListBox, Boolean] = ExternalVar[ListBox, Boolean]("show-separators", _.getShowSeparators(), _.setShowSeparators(_), true)
  val TabBehavior: ExternalVar.Aux[ListBox, org.gnome.gtk.ListTabBehavior] = ExternalVar[ListBox, org.gnome.gtk.ListTabBehavior]("tab-behavior", _.getTabBehavior(), _.setTabBehavior(_), true)
  ()
  extension (v: ListBox) {
    def unwrap: org.gnome.gtk.ListBox = v
    def activateOnSingleClick: Var.Aux[Boolean, v.type] = ActivateOnSingleClick.asInstanceOf[Var.Aux[Boolean, v.type]]
    def adjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = Adjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def selectionMode: Var.Aux[org.gnome.gtk.SelectionMode, v.type] = SelectionMode.asInstanceOf[Var.Aux[org.gnome.gtk.SelectionMode, v.type]]
    def showSeparators: Var.Aux[Boolean, v.type] = ShowSeparators.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tabBehavior: Var.Aux[org.gnome.gtk.ListTabBehavior, v.type] = TabBehavior.asInstanceOf[Var.Aux[org.gnome.gtk.ListTabBehavior, v.type]]
    export unwrap.onActivateCursorRow, unwrap.onMoveCursor, unwrap.onRowActivated, unwrap.onRowSelected, unwrap.onSelectAll, unwrap.onSelectedRowsChanged, unwrap.onToggleCursorRow, unwrap.onUnselectAll
  }
  def _wrap(v: org.gnome.gtk.ListBox): ListBox = {
    v.asInstanceOf
  }
  def init(v: ListBox): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): ListBox = {
    val res = new org.gnome.gtk.ListBox()
    res.asInstanceOf[ListBox]
  }
  def apply(activateOnSingleClick: Opt[Boolean] = UnsetParam, adjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, selectionMode: Opt[org.gnome.gtk.SelectionMode] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showSeparators: Opt[Boolean] = UnsetParam, tabBehavior: Opt[org.gnome.gtk.ListTabBehavior] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, ListBox] = {
    val res = uninitialized()
    init(res)
    ifSet(activateOnSingleClick, res.activateOnSingleClick := _)
    ifSet(adjustment, res.adjustment := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(selectionMode, res.selectionMode := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showSeparators, res.showSeparators := _)
    ifSet(tabBehavior, res.tabBehavior := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}