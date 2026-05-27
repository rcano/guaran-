package guarana
package gtk
opaque type ShortcutsWindow <: Window = org.gnome.gtk.ShortcutsWindow & Window
object ShortcutsWindow {
  ()
  extension (v: ShortcutsWindow) {
    def unwrap: org.gnome.gtk.ShortcutsWindow = v
    export unwrap.onClose, unwrap.onSearch
  }
  def init(v: ShortcutsWindow): Unit = {
    Window.init(v)
  }
  def uninitialized(): ShortcutsWindow = {
    val res = new org.gnome.gtk.ShortcutsWindow()
    res.asInstanceOf[ShortcutsWindow]
  }
}