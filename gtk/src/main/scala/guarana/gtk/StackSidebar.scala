package guarana
package gtk
opaque type StackSidebar <: Widget = org.gnome.gtk.StackSidebar & Widget
object StackSidebar {
  ()
  extension (v: StackSidebar) {
    def unwrap: org.gnome.gtk.StackSidebar = v
  }
  def init(v: StackSidebar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): StackSidebar = {
    val res = new org.gnome.gtk.StackSidebar()
    res.asInstanceOf[StackSidebar]
  }
}