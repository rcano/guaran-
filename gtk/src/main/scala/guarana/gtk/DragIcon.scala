package guarana
package gtk
opaque type DragIcon <: Widget = org.gnome.gtk.DragIcon & Widget
object DragIcon {
  val Child: ExternalVar.Aux[DragIcon, org.gnome.gtk.Widget | Null] = ExternalVar[DragIcon, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Focus: ExternalVar.Aux[DragIcon, org.gnome.gtk.Widget | Null] = ExternalVar[DragIcon, org.gnome.gtk.Widget | Null]("focus", _.getFocus(), _.setFocus(_), true)
  ()
  extension (v: DragIcon) {
    def unwrap: org.gnome.gtk.DragIcon = v
  }
  def init(v: DragIcon): Unit = {
    Widget.init(v)
  }
  def uninitialized(): DragIcon = {
    val res = new org.gnome.gtk.DragIcon()
    res.asInstanceOf[DragIcon]
  }
}