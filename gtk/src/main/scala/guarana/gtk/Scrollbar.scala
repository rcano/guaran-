package guarana
package gtk
opaque type Scrollbar <: Widget = org.gnome.gtk.Scrollbar & Widget
object Scrollbar {
  val Orientation: ExternalVar.Aux[Scrollbar, org.gnome.gtk.Orientation] = ExternalVar[Scrollbar, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  ()
  extension (v: Scrollbar) {
    def unwrap: org.gnome.gtk.Scrollbar = v
  }
  def init(v: Scrollbar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Scrollbar = {
    val res = new org.gnome.gtk.Scrollbar()
    res.asInstanceOf[Scrollbar]
  }
}