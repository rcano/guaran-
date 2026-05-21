package guarana
package gtk
opaque type MessageDialog <: Dialog = org.gnome.gtk.MessageDialog & Dialog
object MessageDialog {
  ()
  extension (v: MessageDialog) {
    def unwrap: org.gnome.gtk.MessageDialog = v
  }
}