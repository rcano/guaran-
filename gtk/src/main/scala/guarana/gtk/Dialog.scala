package guarana
package gtk
opaque type Dialog <: Window = org.gnome.gtk.Dialog & Window
object Dialog {
  ()
  extension (v: Dialog) {
    def unwrap: org.gnome.gtk.Dialog = v
    export unwrap.onClose, unwrap.onResponse
  }
  def init(v: Dialog): Unit = {
    Window.init(v)
  }
  def uninitialized(): Dialog = {
    val res = new org.gnome.gtk.Dialog()
    res.asInstanceOf[Dialog]
  }
}