package guarana
package gtk
opaque type ShortcutsShortcut <: Widget = org.gnome.gtk.ShortcutsShortcut & Widget
object ShortcutsShortcut {
  ()
  extension (v: ShortcutsShortcut) {
    def unwrap: org.gnome.gtk.ShortcutsShortcut = v
  }
  def init(v: ShortcutsShortcut): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ShortcutsShortcut = {
    val res = new org.gnome.gtk.ShortcutsShortcut()
    res.asInstanceOf[ShortcutsShortcut]
  }
}