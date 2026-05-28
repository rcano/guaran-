package guarana
package gtk
import util.*
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
    def currentPage: Var.Aux[Int, v.type] = CurrentPage.asInstanceOf[Var.Aux[Int, v.type]]
    def embedPageSetup: Var.Aux[Boolean, v.type] = EmbedPageSetup.asInstanceOf[Var.Aux[Boolean, v.type]]
    def hasSelection: Var.Aux[Boolean, v.type] = HasSelection.asInstanceOf[Var.Aux[Boolean, v.type]]
    def manualCapabilities: Var.Aux[java.util.Set[org.gnome.gtk.PrintCapabilities], v.type] = ManualCapabilities.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.PrintCapabilities], v.type]]
    def pageSetup: Var.Aux[org.gnome.gtk.PageSetup, v.type] = PageSetup.asInstanceOf[Var.Aux[org.gnome.gtk.PageSetup, v.type]]
    def supportSelection: Var.Aux[Boolean, v.type] = SupportSelection.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def init(v: PrintUnixDialog): Unit = {
    Dialog.init(v)
  }
  def uninitialized(): PrintUnixDialog = {
    val res = new org.gnome.gtk.PrintUnixDialog()
    res.asInstanceOf[PrintUnixDialog]
  }
  def apply(currentPage: Opt[Int] = UnsetParam, embedPageSetup: Opt[Boolean] = UnsetParam, hasSelection: Opt[Boolean] = UnsetParam, manualCapabilities: Opt[java.util.Set[org.gnome.gtk.PrintCapabilities]] = UnsetParam, pageSetup: Opt[org.gnome.gtk.PageSetup] = UnsetParam, supportSelection: Opt[Boolean] = UnsetParam): VarContextAction[PrintUnixDialog] = {
    val res = uninitialized()
    init(res)
    ifSet(currentPage, res.currentPage := _)
    ifSet(embedPageSetup, res.embedPageSetup := _)
    ifSet(hasSelection, res.hasSelection := _)
    ifSet(manualCapabilities, res.manualCapabilities := _)
    ifSet(pageSetup, res.pageSetup := _)
    ifSet(supportSelection, res.supportSelection := _)
    res
  }
}