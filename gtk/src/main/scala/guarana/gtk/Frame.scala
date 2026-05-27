package guarana
package gtk
opaque type Frame <: Widget = org.gnome.gtk.Frame & Widget
object Frame {
  ()
  extension (v: Frame) {
    def unwrap: org.gnome.gtk.Frame = v
  }
  def init(v: Frame): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Frame = {
    val res = new org.gnome.gtk.Frame()
    res.asInstanceOf[Frame]
  }
}