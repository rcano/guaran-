package guarana
package gtk
opaque type ShortcutsSection <: Box = org.gnome.gtk.ShortcutsSection & Box
object ShortcutsSection {
  ()
  extension (v: ShortcutsSection) {
    def unwrap: org.gnome.gtk.ShortcutsSection = v
    export unwrap.onChangeCurrentPage
  }
}