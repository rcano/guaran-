package guarana
package gtk
opaque type ShortcutsShortcut <: Widget = org.gnome.gtk.ShortcutsShortcut & Widget
object ShortcutsShortcut {
  ()
  extension (v: ShortcutsShortcut) {
    def unwrap: org.gnome.gtk.ShortcutsShortcut = v
  }
}