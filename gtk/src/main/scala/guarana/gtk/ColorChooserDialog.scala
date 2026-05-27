package guarana
package gtk
opaque type ColorChooserDialog <: Dialog = org.gnome.gtk.ColorChooserDialog & Dialog
object ColorChooserDialog {
  @deprecated("", "") val UseAlpha: ExternalVar.Aux[ColorChooserDialog, Boolean] = ExternalVar[ColorChooserDialog, Boolean]("use-alpha", _.getUseAlpha(), _.setUseAlpha(_), true)
  ()
  extension (v: ColorChooserDialog) {
    def unwrap: org.gnome.gtk.ColorChooserDialog = v
  }
  def init(v: ColorChooserDialog): Unit = {
    Dialog.init(v)
  }
  def uninitialized(): ColorChooserDialog = {
    val res = new org.gnome.gtk.ColorChooserDialog()
    res.asInstanceOf[ColorChooserDialog]
  }
}