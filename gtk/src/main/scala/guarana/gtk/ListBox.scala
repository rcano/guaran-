package guarana
package gtk
opaque type ListBox <: Widget = org.gnome.gtk.ListBox & Widget
object ListBox {
  val ActivateOnSingleClick: ExternalVar.Aux[ListBox, Boolean] = ExternalVar[ListBox, Boolean]("activate-on-single-click", _.getActivateOnSingleClick(), _.setActivateOnSingleClick(_), true)
  val SelectionMode: ExternalVar.Aux[ListBox, org.gnome.gtk.SelectionMode | Null] = ExternalVar[ListBox, org.gnome.gtk.SelectionMode | Null]("selection-mode", _.getSelectionMode(), _.setSelectionMode(_), true)
  val ShowSeparators: ExternalVar.Aux[ListBox, Boolean] = ExternalVar[ListBox, Boolean]("show-separators", _.getShowSeparators(), _.setShowSeparators(_), true)
  val TabBehavior: ExternalVar.Aux[ListBox, org.gnome.gtk.ListTabBehavior | Null] = ExternalVar[ListBox, org.gnome.gtk.ListTabBehavior | Null]("tab-behavior", _.getTabBehavior(), _.setTabBehavior(_), true)
  ()
  extension (v: ListBox) {
    def unwrap: org.gnome.gtk.ListBox = v
    export unwrap.onActivateCursorRow, unwrap.onMoveCursor, unwrap.onRowActivated, unwrap.onRowSelected, unwrap.onSelectAll, unwrap.onSelectedRowsChanged, unwrap.onToggleCursorRow, unwrap.onUnselectAll
  }
}