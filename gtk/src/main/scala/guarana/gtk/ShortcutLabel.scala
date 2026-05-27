package guarana
package gtk
opaque type ShortcutLabel <: Widget = org.gnome.gtk.ShortcutLabel & Widget
object ShortcutLabel {
  ()
  extension (v: ShortcutLabel) {
    def unwrap: org.gnome.gtk.ShortcutLabel = v
  }
  def init(v: ShortcutLabel): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ShortcutLabel = {
    val res = new org.gnome.gtk.ShortcutLabel()
    res.asInstanceOf[ShortcutLabel]
  }
}