package guarana
package gtk
opaque type ListBoxRow <: Widget = org.gnome.gtk.ListBoxRow & Widget
object ListBoxRow {
  val Activatable: ExternalVar.Aux[ListBoxRow, Boolean] = ExternalVar[ListBoxRow, Boolean]("activatable", _.getActivatable(), _.setActivatable(_), true)
  val Selectable: ExternalVar.Aux[ListBoxRow, Boolean] = ExternalVar[ListBoxRow, Boolean]("selectable", _.getSelectable(), _.setSelectable(_), true)
  ()
  extension (v: ListBoxRow) {
    def unwrap: org.gnome.gtk.ListBoxRow = v
    export unwrap.onActivate
  }
  def init(v: ListBoxRow): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ListBoxRow = {
    val res = new org.gnome.gtk.ListBoxRow()
    res.asInstanceOf[ListBoxRow]
  }
}