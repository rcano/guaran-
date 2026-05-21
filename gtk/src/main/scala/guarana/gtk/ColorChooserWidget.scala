package guarana
package gtk
opaque type ColorChooserWidget <: Widget = org.gnome.gtk.ColorChooserWidget & Widget
object ColorChooserWidget {
  ()
  extension (v: ColorChooserWidget) {
    def unwrap: org.gnome.gtk.ColorChooserWidget = v
  }
}