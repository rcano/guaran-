package guarana
package gtk
opaque type DragIcon <: Widget = org.gnome.gtk.DragIcon & Widget
object DragIcon {
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