package guarana
package gtk
opaque type DragIcon <: Widget = org.gnome.gtk.DragIcon & Widget
object DragIcon {
  val Child: ExternalVar.Aux[DragIcon, org.gnome.gtk.Widget | Null] = ExternalVar[DragIcon, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  ()
  extension (v: DragIcon) {
    def unwrap: org.gnome.gtk.DragIcon = v
  }
}