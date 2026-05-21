package guarana
package gtk
opaque type Fixed <: Widget = org.gnome.gtk.Fixed & Widget
object Fixed {
  ()
  extension (v: Fixed) {
    def unwrap: org.gnome.gtk.Fixed = v
  }
}