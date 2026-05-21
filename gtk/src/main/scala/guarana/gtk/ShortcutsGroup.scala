package guarana
package gtk
opaque type ShortcutsGroup <: Box = org.gnome.gtk.ShortcutsGroup & Box
object ShortcutsGroup {
  ()
  extension (v: ShortcutsGroup) {
    def unwrap: org.gnome.gtk.ShortcutsGroup = v
  }
}