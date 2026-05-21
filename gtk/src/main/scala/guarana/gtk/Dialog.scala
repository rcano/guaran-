package guarana
package gtk
opaque type Dialog <: Window = org.gnome.gtk.Dialog & Window
object Dialog {
  ()
  extension (v: Dialog) {
    def unwrap: org.gnome.gtk.Dialog = v
    export unwrap.onClose, unwrap.onResponse
  }
}