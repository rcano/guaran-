package guarana
package gtk
opaque type ListBase <: Widget = org.gnome.gtk.ListBase & Widget
object ListBase {
  ()
  extension (v: ListBase) {
    def unwrap: org.gnome.gtk.ListBase = v
  }
}