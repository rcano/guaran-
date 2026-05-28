package guarana
package gtk
import util.*
opaque type ApplicationWindow <: Window = org.gnome.gtk.ApplicationWindow & Window
object ApplicationWindow {
  @deprecated("", "") val HelpOverlay: ExternalVar.Aux[ApplicationWindow, org.gnome.gtk.ShortcutsWindow | Null] = ExternalVar[ApplicationWindow, org.gnome.gtk.ShortcutsWindow | Null]("help-overlay", _.getHelpOverlay(), _.setHelpOverlay(_), true)
  val ShowMenubar: ExternalVar.Aux[ApplicationWindow, Boolean] = ExternalVar[ApplicationWindow, Boolean]("show-menubar", _.getShowMenubar(), _.setShowMenubar(_), true)
  ()
  extension (v: ApplicationWindow) {
    def unwrap: org.gnome.gtk.ApplicationWindow = v
    @deprecated("", "") def helpOverlay: Var.Aux[org.gnome.gtk.ShortcutsWindow | Null, v.type] = HelpOverlay.asInstanceOf[Var.Aux[org.gnome.gtk.ShortcutsWindow | Null, v.type]]
    def showMenubar: Var.Aux[Boolean, v.type] = ShowMenubar.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def init(v: ApplicationWindow): Unit = {
    Window.init(v)
  }
  def uninitialized(): ApplicationWindow = {
    val res = new org.gnome.gtk.ApplicationWindow()
    res.asInstanceOf[ApplicationWindow]
  }
  def apply(helpOverlay: Opt[org.gnome.gtk.ShortcutsWindow | Null] = UnsetParam, showMenubar: Opt[Boolean] = UnsetParam): VarContextAction[ApplicationWindow] = {
    val res = uninitialized()
    init(res)
    ifSet(helpOverlay, res.helpOverlay := _)
    ifSet(showMenubar, res.showMenubar := _)
    res
  }
}