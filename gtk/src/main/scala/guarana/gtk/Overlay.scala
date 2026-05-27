package guarana
package gtk
opaque type Overlay <: Widget = org.gnome.gtk.Overlay & Widget
object Overlay {
  ()
  extension (v: Overlay) {
    def unwrap: org.gnome.gtk.Overlay = v
    export unwrap.onGetChildPosition
  }
  def init(v: Overlay): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Overlay = {
    val res = new org.gnome.gtk.Overlay()
    res.asInstanceOf[Overlay]
  }
}