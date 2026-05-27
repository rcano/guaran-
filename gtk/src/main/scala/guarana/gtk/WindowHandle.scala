package guarana
package gtk
opaque type WindowHandle <: Widget = org.gnome.gtk.WindowHandle & Widget
object WindowHandle {
  ()
  extension (v: WindowHandle) {
    def unwrap: org.gnome.gtk.WindowHandle = v
  }
  def init(v: WindowHandle): Unit = {
    Widget.init(v)
  }
  def uninitialized(): WindowHandle = {
    val res = new org.gnome.gtk.WindowHandle()
    res.asInstanceOf[WindowHandle]
  }
}