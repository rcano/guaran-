package guarana
package gtk
opaque type PageSetupUnixDialog <: Dialog = org.gnome.gtk.PageSetupUnixDialog & Dialog
object PageSetupUnixDialog {
  ()
  extension (v: PageSetupUnixDialog) {
    def unwrap: org.gnome.gtk.PageSetupUnixDialog = v
  }
  def init(v: PageSetupUnixDialog): Unit = {
    Dialog.init(v)
  }
  def uninitialized(): PageSetupUnixDialog = {
    val res = new org.gnome.gtk.PageSetupUnixDialog()
    res.asInstanceOf[PageSetupUnixDialog]
  }
}