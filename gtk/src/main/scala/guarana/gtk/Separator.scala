package guarana
package gtk
opaque type Separator <: Widget = org.gnome.gtk.Separator & Widget
object Separator {
  ()
  extension (v: Separator) {
    def unwrap: org.gnome.gtk.Separator = v
  }
}