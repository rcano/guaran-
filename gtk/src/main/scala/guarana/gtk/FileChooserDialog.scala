package guarana
package gtk
opaque type FileChooserDialog <: Dialog = org.gnome.gtk.FileChooserDialog & Dialog
object FileChooserDialog {
  ()
  extension (v: FileChooserDialog) {
    def unwrap: org.gnome.gtk.FileChooserDialog = v
  }
}