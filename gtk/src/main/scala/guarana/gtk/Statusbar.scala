package guarana
package gtk
opaque type Statusbar <: Widget = org.gnome.gtk.Statusbar & Widget
object Statusbar {
  ()
  extension (v: Statusbar) {
    def unwrap: org.gnome.gtk.Statusbar = v
    export unwrap.onTextPopped, unwrap.onTextPushed
  }
  def init(v: Statusbar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Statusbar = {
    val res = new org.gnome.gtk.Statusbar()
    res.asInstanceOf[Statusbar]
  }
}