package guarana
package gtk
import util.*
opaque type PageSetupUnixDialog <: Dialog = org.gnome.gtk.PageSetupUnixDialog & Dialog
object PageSetupUnixDialog {
  val PageSetup: ExternalVar.Aux[PageSetupUnixDialog, org.gnome.gtk.PageSetup] = ExternalVar[PageSetupUnixDialog, org.gnome.gtk.PageSetup]("page-setup", _.getPageSetup(), _.setPageSetup(_), true)
  val PrintSettings: ExternalVar.Aux[PageSetupUnixDialog, org.gnome.gtk.PrintSettings | Null] = ExternalVar[PageSetupUnixDialog, org.gnome.gtk.PrintSettings | Null]("print-settings", _.getPrintSettings(), _.setPrintSettings(_), true)
  ()
  extension (v: PageSetupUnixDialog) {
    def unwrap: org.gnome.gtk.PageSetupUnixDialog = v
    def pageSetup: Var.Aux[org.gnome.gtk.PageSetup, v.type] = PageSetup.asInstanceOf[Var.Aux[org.gnome.gtk.PageSetup, v.type]]
    def printSettings: Var.Aux[org.gnome.gtk.PrintSettings | Null, v.type] = PrintSettings.asInstanceOf[Var.Aux[org.gnome.gtk.PrintSettings | Null, v.type]]
  }
  def init(v: PageSetupUnixDialog): Unit = {
    Dialog.init(v)
  }
  def uninitialized(): PageSetupUnixDialog = {
    val res = new org.gnome.gtk.PageSetupUnixDialog()
    res.asInstanceOf[PageSetupUnixDialog]
  }
  def apply(pageSetup: Opt[org.gnome.gtk.PageSetup] = UnsetParam, printSettings: Opt[org.gnome.gtk.PrintSettings | Null] = UnsetParam): VarContextAction[PageSetupUnixDialog] = {
    val res = uninitialized()
    init(res)
    ifSet(pageSetup, res.pageSetup := _)
    ifSet(printSettings, res.printSettings := _)
    res
  }
}