package guarana
package gtk
opaque type MessageDialog <: Dialog = org.gnome.gtk.MessageDialog & Dialog
object MessageDialog {
  ()
  extension (v: MessageDialog) {
    def unwrap: org.gnome.gtk.MessageDialog = v
  }
  def init(v: MessageDialog): Unit = {
    Dialog.init(v)
  }
  def uninitialized(): MessageDialog = {
    val res = new org.gnome.gtk.MessageDialog()
    res.asInstanceOf[MessageDialog]
  }
}