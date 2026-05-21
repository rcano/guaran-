package guarana
package gtk
opaque type ListBoxRow <: Widget = org.gnome.gtk.ListBoxRow & Widget
object ListBoxRow {
  val Activatable: ExternalVar.Aux[ListBoxRow, Boolean] = ExternalVar[ListBoxRow, Boolean]("activatable", _.getActivatable(), _.setActivatable(_), true)
  val Child: ExternalVar.Aux[ListBoxRow, org.gnome.gtk.Widget | Null] = ExternalVar[ListBoxRow, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Selectable: ExternalVar.Aux[ListBoxRow, Boolean] = ExternalVar[ListBoxRow, Boolean]("selectable", _.getSelectable(), _.setSelectable(_), true)
  ()
  extension (v: ListBoxRow) {
    def unwrap: org.gnome.gtk.ListBoxRow = v
    export unwrap.onActivate
  }
}