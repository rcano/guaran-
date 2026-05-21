package guarana
package gtk
opaque type AppChooserWidget <: Widget = org.gnome.gtk.AppChooserWidget & Widget
object AppChooserWidget {
  val DefaultText: ExternalVar.Aux[AppChooserWidget, java.lang.String | Null] = ExternalVar[AppChooserWidget, java.lang.String | Null]("default-text", _.getDefaultText(), _.setDefaultText(_), true)
  val ShowAll: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-all", _.getShowAll(), _.setShowAll(_), true)
  val ShowDefault: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-default", _.getShowDefault(), _.setShowDefault(_), true)
  val ShowFallback: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-fallback", _.getShowFallback(), _.setShowFallback(_), true)
  val ShowOther: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-other", _.getShowOther(), _.setShowOther(_), true)
  val ShowRecommended: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-recommended", _.getShowRecommended(), _.setShowRecommended(_), true)
  ()
  extension (v: AppChooserWidget) {
    def unwrap: org.gnome.gtk.AppChooserWidget = v
    export unwrap.onApplicationActivated, unwrap.onApplicationSelected
  }
}