package guarana
package gtk
opaque type PrintUnixDialog <: Dialog = org.gnome.gtk.PrintUnixDialog & Dialog
object PrintUnixDialog {
  val CurrentPage: ExternalVar.Aux[PrintUnixDialog, Int] = ExternalVar[PrintUnixDialog, Int]("current-page", _.getCurrentPage(), _.setCurrentPage(_), true)
  val EmbedPageSetup: ExternalVar.Aux[PrintUnixDialog, Boolean] = ExternalVar[PrintUnixDialog, Boolean]("embed-page-setup", _.getEmbedPageSetup(), _.setEmbedPageSetup(_), true)
  val HasSelection: ExternalVar.Aux[PrintUnixDialog, Boolean] = ExternalVar[PrintUnixDialog, Boolean]("has-selection", _.getHasSelection(), _.setHasSelection(_), true)
  val ManualCapabilities: ExternalVar.Aux[PrintUnixDialog, java.util.Set[org.gnome.gtk.PrintCapabilities]] = ExternalVar[PrintUnixDialog, java.util.Set[org.gnome.gtk.PrintCapabilities]]("manual-capabilities", _.getManualCapabilities(), _.setManualCapabilities(_), true)
  val PageSetup: ExternalVar.Aux[PrintUnixDialog, org.gnome.gtk.PageSetup] = ExternalVar[PrintUnixDialog, org.gnome.gtk.PageSetup]("page-setup", _.getPageSetup(), _.setPageSetup(_), true)
  val SupportSelection: ExternalVar.Aux[PrintUnixDialog, Boolean] = ExternalVar[PrintUnixDialog, Boolean]("support-selection", _.getSupportSelection(), _.setSupportSelection(_), true)
  ()
  extension (v: PrintUnixDialog) {
    def unwrap: org.gnome.gtk.PrintUnixDialog = v
  }
  def init(v: PrintUnixDialog): Unit = {
    Dialog.init(v)
  }
  def uninitialized(): PrintUnixDialog = {
    val res = new org.gnome.gtk.PrintUnixDialog()
    res.asInstanceOf[PrintUnixDialog]
  }
}