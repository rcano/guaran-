package guarana
package gtk
opaque type Overlay <: Widget = org.gnome.gtk.Overlay & Widget
object Overlay {
  val Child: ExternalVar.Aux[Overlay, org.gnome.gtk.Widget | Null] = ExternalVar[Overlay, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  ()
  extension (v: Overlay) {
    def unwrap: org.gnome.gtk.Overlay = v
    export unwrap.onGetChildPosition
  }
}