package guarana
package gtk
opaque type ComboBoxText <: ComboBox = org.gnome.gtk.ComboBoxText & ComboBox
object ComboBoxText {
  ()
  extension (v: ComboBoxText) {
    def unwrap: org.gnome.gtk.ComboBoxText = v
  }
}