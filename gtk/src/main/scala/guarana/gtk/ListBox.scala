package guarana
package gtk
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
    export unwrap.onActivateCursorRow, unwrap.onMoveCursor, unwrap.onRowActivated, unwrap.onRowSelected, unwrap.onSelectAll, unwrap.onSelectedRowsChanged, unwrap.onToggleCursorRow, unwrap.onUnselectAll
  }
  def init(v: ListBox): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ListBox = {
    val res = new org.gnome.gtk.ListBox()
    res.asInstanceOf[ListBox]
  }
}