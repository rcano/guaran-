package guarana
package gtk
opaque type FontButton <: Widget = org.gnome.gtk.FontButton & Widget
object FontButton {
  @deprecated("", "") val Language: ExternalVar.Aux[FontButton, java.lang.String] = ExternalVar[FontButton, java.lang.String]("language", _.getLanguage(), _.setLanguage(_), true)
  @deprecated("", "") val Level: ExternalVar.Aux[FontButton, java.util.Set[org.gnome.gtk.FontChooserLevel]] = ExternalVar[FontButton, java.util.Set[org.gnome.gtk.FontChooserLevel]]("level", _.getLevel(), _.setLevel(_), true)
  @deprecated("", "") val Modal: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("modal", _.getModal(), _.setModal(_), true)
  @deprecated("", "") val PreviewText: ExternalVar.Aux[FontButton, java.lang.String] = ExternalVar[FontButton, java.lang.String]("preview-text", _.getPreviewText(), _.setPreviewText(_), true)
  @deprecated("", "") val ShowPreviewEntry: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("show-preview-entry", _.getShowPreviewEntry(), _.setShowPreviewEntry(_), true)
  @deprecated("", "") val Title: ExternalVar.Aux[FontButton, java.lang.String] = ExternalVar[FontButton, java.lang.String]("title", _.getTitle(), _.setTitle(_), true)
  @deprecated("", "") val UseFont: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("use-font", _.getUseFont(), _.setUseFont(_), true)
  @deprecated("", "") val UseSize: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("use-size", _.getUseSize(), _.setUseSize(_), true)
  ()
  extension (v: FontButton) {
    def unwrap: org.gnome.gtk.FontButton = v
    export unwrap.onActivate, unwrap.onFontSet
  }
  def init(v: FontButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): FontButton = {
    val res = new org.gnome.gtk.FontButton()
    res.asInstanceOf[FontButton]
  }
}