package guarana
package gtk
opaque type ListBoxRow <: Widget = org.gnome.gtk.ListBoxRow & Widget
object ListBoxRow {
  val ActionName: ExternalVar.Aux[ListBoxRow, java.lang.String | Null] = ExternalVar[ListBoxRow, java.lang.String | Null]("action-name", _.getActionName(), _.setActionName(_), true)
  val ActionTargetValue: ExternalVar.Aux[ListBoxRow, org.gnome.glib.Variant | Null] = ExternalVar[ListBoxRow, org.gnome.glib.Variant | Null]("action-target-value", _.getActionTargetValue(), _.setActionTargetValue(_), true)
  val Activatable: ExternalVar.Aux[ListBoxRow, Boolean] = ExternalVar[ListBoxRow, Boolean]("activatable", _.getActivatable(), _.setActivatable(_), true)
  val Child: ExternalVar.Aux[ListBoxRow, org.gnome.gtk.Widget | Null] = ExternalVar[ListBoxRow, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Header: ExternalVar.Aux[ListBoxRow, org.gnome.gtk.Widget | Null] = ExternalVar[ListBoxRow, org.gnome.gtk.Widget | Null]("header", _.getHeader(), _.setHeader(_), true)
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