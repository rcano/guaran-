package guarana
package gtk
opaque type ColorChooserDialog <: Dialog = org.gnome.gtk.ColorChooserDialog & Dialog
object ColorChooserDialog {
  ()
  extension (v: ColorChooserDialog) {
    def unwrap: org.gnome.gtk.ColorChooserDialog = v
  }
}