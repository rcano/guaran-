package guarana
package gtk
opaque type FontChooserWidget <: Widget = org.gnome.gtk.FontChooserWidget & Widget
object FontChooserWidget {
  @deprecated("", "") val Language: ExternalVar.Aux[FontChooserWidget, java.lang.String] = ExternalVar[FontChooserWidget, java.lang.String]("language", _.getLanguage(), _.setLanguage(_), true)
  @deprecated("", "") val Level: ExternalVar.Aux[FontChooserWidget, java.util.Set[org.gnome.gtk.FontChooserLevel]] = ExternalVar[FontChooserWidget, java.util.Set[org.gnome.gtk.FontChooserLevel]]("level", _.getLevel(), _.setLevel(_), true)
  @deprecated("", "") val PreviewText: ExternalVar.Aux[FontChooserWidget, java.lang.String] = ExternalVar[FontChooserWidget, java.lang.String]("preview-text", _.getPreviewText(), _.setPreviewText(_), true)
  @deprecated("", "") val ShowPreviewEntry: ExternalVar.Aux[FontChooserWidget, Boolean] = ExternalVar[FontChooserWidget, Boolean]("show-preview-entry", _.getShowPreviewEntry(), _.setShowPreviewEntry(_), true)
  ()
  extension (v: FontChooserWidget) {
    def unwrap: org.gnome.gtk.FontChooserWidget = v
  }
  def init(v: FontChooserWidget): Unit = {
    Widget.init(v)
  }
  def uninitialized(): FontChooserWidget = {
    val res = new org.gnome.gtk.FontChooserWidget()
    res.asInstanceOf[FontChooserWidget]
  }
}