package guarana
package gtk
opaque type Separator <: Widget = org.gnome.gtk.Separator & Widget
object Separator {
  ()
  extension (v: Separator) {
    def unwrap: org.gnome.gtk.Separator = v
  }
  def init(v: Separator): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Separator = {
    val res = new org.gnome.gtk.Separator()
    res.asInstanceOf[Separator]
  }
}