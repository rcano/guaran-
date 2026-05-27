package guarana
package gtk
opaque type Scrollbar <: Widget = org.gnome.gtk.Scrollbar & Widget
object Scrollbar {
  ()
  extension (v: Scrollbar) {
    def unwrap: org.gnome.gtk.Scrollbar = v
  }
  def init(v: Scrollbar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Scrollbar = {
    val res = new org.gnome.gtk.Scrollbar()
    res.asInstanceOf[Scrollbar]
  }
}