package guarana
package gtk
opaque type ApplicationWindow <: Window = org.gnome.gtk.ApplicationWindow & Window
object ApplicationWindow {
  @deprecated("", "") val HelpOverlay: ExternalVar.Aux[ApplicationWindow, org.gnome.gtk.ShortcutsWindow | Null] = ExternalVar[ApplicationWindow, org.gnome.gtk.ShortcutsWindow | Null]("help-overlay", _.getHelpOverlay(), _.setHelpOverlay(_), true)
  val ShowMenubar: ExternalVar.Aux[ApplicationWindow, Boolean] = ExternalVar[ApplicationWindow, Boolean]("show-menubar", _.getShowMenubar(), _.setShowMenubar(_), true)
  ()
  extension (v: ApplicationWindow) {
    def unwrap: org.gnome.gtk.ApplicationWindow = v
  }
  def init(v: ApplicationWindow): Unit = {
    Window.init(v)
  }
  def uninitialized(): ApplicationWindow = {
    val res = new org.gnome.gtk.ApplicationWindow()
    res.asInstanceOf[ApplicationWindow]
  }
}