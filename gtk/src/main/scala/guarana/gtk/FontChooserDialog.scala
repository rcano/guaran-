package guarana
package gtk
opaque type FontChooserDialog <: Dialog = org.gnome.gtk.FontChooserDialog & Dialog
object FontChooserDialog {
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