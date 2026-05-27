package guarana
package gtk
opaque type ShortcutsGroup <: Box = org.gnome.gtk.ShortcutsGroup & Box
object ShortcutsGroup {
  ()
  extension (v: ShortcutsGroup) {
    def unwrap: org.gnome.gtk.ShortcutsGroup = v
  }
  def init(v: ShortcutsGroup): Unit = {
    Box.init(v)
  }
  def uninitialized(): ShortcutsGroup = {
    val res = new org.gnome.gtk.ShortcutsGroup()
    res.asInstanceOf[ShortcutsGroup]
  }
}