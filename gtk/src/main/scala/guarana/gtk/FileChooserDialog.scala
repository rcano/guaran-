package guarana
package gtk
opaque type FileChooserDialog <: Dialog = org.gnome.gtk.FileChooserDialog & Dialog
object FileChooserDialog {
  ()
  extension (v: FileChooserDialog) {
    def unwrap: org.gnome.gtk.FileChooserDialog = v
  }
  def init(v: FileChooserDialog): Unit = {
    Dialog.init(v)
  }
  def uninitialized(): FileChooserDialog = {
    val res = new org.gnome.gtk.FileChooserDialog()
    res.asInstanceOf[FileChooserDialog]
  }
}