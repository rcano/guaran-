package guarana
package gtk
opaque type SearchBar <: Widget = org.gnome.gtk.SearchBar & Widget
object SearchBar {
  val Child: ExternalVar.Aux[SearchBar, org.gnome.gtk.Widget | Null] = ExternalVar[SearchBar, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val KeyCaptureWidget: ExternalVar.Aux[SearchBar, org.gnome.gtk.Widget | Null] = ExternalVar[SearchBar, org.gnome.gtk.Widget | Null]("key-capture-widget", _.getKeyCaptureWidget(), _.setKeyCaptureWidget(_), true)
  val ShowCloseButton: ExternalVar.Aux[SearchBar, Boolean] = ExternalVar[SearchBar, Boolean]("show-close-button", _.getShowCloseButton(), _.setShowCloseButton(_), true)
  ()
  extension (v: SearchBar) {
    def unwrap: org.gnome.gtk.SearchBar = v
  }
}