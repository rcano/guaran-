package guarana
package gtk
opaque type Separator <: Widget = org.gnome.gtk.Separator & Widget
object Separator {
  val Orientation: ExternalVar.Aux[Separator, org.gnome.gtk.Orientation] = ExternalVar[Separator, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
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