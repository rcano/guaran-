package guarana
package gtk
opaque type StackSwitcher <: Widget = org.gnome.gtk.StackSwitcher & Widget
object StackSwitcher {
  ()
  extension (v: StackSwitcher) {
    def unwrap: org.gnome.gtk.StackSwitcher = v
  }
  def init(v: StackSwitcher): Unit = {
    Widget.init(v)
  }
  def uninitialized(): StackSwitcher = {
    val res = new org.gnome.gtk.StackSwitcher()
    res.asInstanceOf[StackSwitcher]
  }
}