package guarana
package gtk
opaque type ComboBoxText <: ComboBox = org.gnome.gtk.ComboBoxText & ComboBox
object ComboBoxText {
  ()
  extension (v: ComboBoxText) {
    def unwrap: org.gnome.gtk.ComboBoxText = v
  }
  def init(v: ComboBoxText): Unit = {
    ComboBox.init(v)
  }
  def uninitialized(): ComboBoxText = {
    val res = new org.gnome.gtk.ComboBoxText()
    res.asInstanceOf[ComboBoxText]
  }
}