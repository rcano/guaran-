package guarana
package gtk
opaque type WindowHandle <: Widget = org.gnome.gtk.WindowHandle & Widget
object WindowHandle {
  val Child: ExternalVar.Aux[WindowHandle, org.gnome.gtk.Widget | Null] = ExternalVar[WindowHandle, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
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