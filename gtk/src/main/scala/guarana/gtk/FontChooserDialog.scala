package guarana
package gtk
opaque type FontChooserDialog <: Dialog = org.gnome.gtk.FontChooserDialog & Dialog
object FontChooserDialog {
  ()
  extension (v: FontChooserDialog) {
    def unwrap: org.gnome.gtk.FontChooserDialog = v
  }
}