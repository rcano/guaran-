package guarana
package gtk
import util.*
opaque type ListBox <: Widget = org.gnome.gtk.ListBox & Widget
object ListBox {
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
  def init(v: ListBox): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ListBox = {
    val res = new org.gnome.gtk.ListBox()
    res.asInstanceOf[ListBox]
  }
  def apply(activateOnSingleClick: Opt[Boolean] = UnsetParam, adjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, selectionMode: Opt[org.gnome.gtk.SelectionMode] = UnsetParam, showSeparators: Opt[Boolean] = UnsetParam, tabBehavior: Opt[org.gnome.gtk.ListTabBehavior] = UnsetParam): VarContextAction[ListBox] = {
    val res = uninitialized()
    init(res)
    ifSet(activateOnSingleClick, res.activateOnSingleClick := _)
    ifSet(adjustment, res.adjustment := _)
    ifSet(selectionMode, res.selectionMode := _)
    ifSet(showSeparators, res.showSeparators := _)
    ifSet(tabBehavior, res.tabBehavior := _)
    res
  }
}