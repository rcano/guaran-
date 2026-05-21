package guarana
package gtk
opaque type PageSetupUnixDialog <: Dialog = org.gnome.gtk.PageSetupUnixDialog & Dialog
object PageSetupUnixDialog {
  ()
  extension (v: PageSetupUnixDialog) {
    def unwrap: org.gnome.gtk.PageSetupUnixDialog = v
  }
}