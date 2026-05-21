package guarana
package gtk
opaque type HeaderBar <: Widget = org.gnome.gtk.HeaderBar & Widget
object HeaderBar {
  val DecorationLayout: ExternalVar.Aux[HeaderBar, java.lang.String | Null] = ExternalVar[HeaderBar, java.lang.String | Null]("decoration-layout", _.getDecorationLayout(), _.setDecorationLayout(_), true)
  val ShowTitleButtons: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("show-title-buttons", _.getShowTitleButtons(), _.setShowTitleButtons(_), true)
  val TitleWidget: ExternalVar.Aux[HeaderBar, org.gnome.gtk.Widget | Null] = ExternalVar[HeaderBar, org.gnome.gtk.Widget | Null]("title-widget", _.getTitleWidget(), _.setTitleWidget(_), true)
  val UseNativeControls: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("use-native-controls", _.getUseNativeControls(), _.setUseNativeControls(_), true)
  ()
  extension (v: HeaderBar) {
    def unwrap: org.gnome.gtk.HeaderBar = v
  }
}