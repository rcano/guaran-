package guarana
package gtk
opaque type PageSetupUnixDialog <: Dialog = org.gnome.gtk.PageSetupUnixDialog & Dialog
object PageSetupUnixDialog {
  val PageSetup: ExternalVar.Aux[PageSetupUnixDialog, org.gnome.gtk.PageSetup] = ExternalVar[PageSetupUnixDialog, org.gnome.gtk.PageSetup]("page-setup", _.getPageSetup(), _.setPageSetup(_), true)
  val PrintSettings: ExternalVar.Aux[PageSetupUnixDialog, org.gnome.gtk.PrintSettings | Null] = ExternalVar[PageSetupUnixDialog, org.gnome.gtk.PrintSettings | Null]("print-settings", _.getPrintSettings(), _.setPrintSettings(_), true)
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