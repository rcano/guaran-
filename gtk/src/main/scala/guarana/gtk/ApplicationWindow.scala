package guarana
package gtk
opaque type ApplicationWindow <: Window = org.gnome.gtk.ApplicationWindow & Window
object ApplicationWindow {
  val ShowMenubar: ExternalVar.Aux[ApplicationWindow, Boolean] = ExternalVar[ApplicationWindow, Boolean]("show-menubar", _.getShowMenubar(), _.setShowMenubar(_), true)
  ()
  extension (v: ApplicationWindow) {
    def unwrap: org.gnome.gtk.ApplicationWindow = v
  }
}