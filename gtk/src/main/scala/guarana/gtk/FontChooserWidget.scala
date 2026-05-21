package guarana
package gtk
opaque type FontChooserWidget <: Widget = org.gnome.gtk.FontChooserWidget & Widget
object FontChooserWidget {
  ()
  extension (v: FontChooserWidget) {
    def unwrap: org.gnome.gtk.FontChooserWidget = v
  }
}