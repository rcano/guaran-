package guarana
package gtk
opaque type FontChooserDialog <: Dialog = org.gnome.gtk.FontChooserDialog & Dialog
object FontChooserDialog {
  @deprecated("", "") val Language: ExternalVar.Aux[FontChooserDialog, java.lang.String] = ExternalVar[FontChooserDialog, java.lang.String]("language", _.getLanguage(), _.setLanguage(_), true)
  @deprecated("", "") val Level: ExternalVar.Aux[FontChooserDialog, java.util.Set[org.gnome.gtk.FontChooserLevel]] = ExternalVar[FontChooserDialog, java.util.Set[org.gnome.gtk.FontChooserLevel]]("level", _.getLevel(), _.setLevel(_), true)
  @deprecated("", "") val PreviewText: ExternalVar.Aux[FontChooserDialog, java.lang.String] = ExternalVar[FontChooserDialog, java.lang.String]("preview-text", _.getPreviewText(), _.setPreviewText(_), true)
  @deprecated("", "") val ShowPreviewEntry: ExternalVar.Aux[FontChooserDialog, Boolean] = ExternalVar[FontChooserDialog, Boolean]("show-preview-entry", _.getShowPreviewEntry(), _.setShowPreviewEntry(_), true)
  ()
  extension (v: FontChooserDialog) {
    def unwrap: org.gnome.gtk.FontChooserDialog = v
  }
  def init(v: FontChooserDialog): Unit = {
    Dialog.init(v)
  }
  def uninitialized(): FontChooserDialog = {
    val res = new org.gnome.gtk.FontChooserDialog()
    res.asInstanceOf[FontChooserDialog]
  }
}