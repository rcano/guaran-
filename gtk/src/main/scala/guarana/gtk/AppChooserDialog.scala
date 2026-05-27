package guarana
package gtk
opaque type AppChooserDialog <: Dialog = org.gnome.gtk.AppChooserDialog & Dialog
object AppChooserDialog {
  ()
  extension (v: AppChooserDialog) {
    def unwrap: org.gnome.gtk.AppChooserDialog = v
  }
  def init(v: AppChooserDialog): Unit = {
    Dialog.init(v)
  }
  def uninitialized(): AppChooserDialog = {
    val res = new org.gnome.gtk.AppChooserDialog()
    res.asInstanceOf[AppChooserDialog]
  }
}