package guarana
package gtk
opaque type PopoverMenuBar <: Widget = org.gnome.gtk.PopoverMenuBar & Widget
object PopoverMenuBar {
  ()
  extension (v: PopoverMenuBar) {
    def unwrap: org.gnome.gtk.PopoverMenuBar = v
  }
  def init(v: PopoverMenuBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): PopoverMenuBar = {
    val res = new org.gnome.gtk.PopoverMenuBar()
    res.asInstanceOf[PopoverMenuBar]
  }
}