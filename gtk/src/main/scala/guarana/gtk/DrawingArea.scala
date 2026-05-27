package guarana
package gtk
opaque type DrawingArea <: Widget = org.gnome.gtk.DrawingArea & Widget
object DrawingArea {
  val ContentHeight: ExternalVar.Aux[DrawingArea, Int] = ExternalVar[DrawingArea, Int]("content-height", _.getContentHeight(), _.setContentHeight(_), true)
  val ContentWidth: ExternalVar.Aux[DrawingArea, Int] = ExternalVar[DrawingArea, Int]("content-width", _.getContentWidth(), _.setContentWidth(_), true)
  ()
  extension (v: DrawingArea) {
    def unwrap: org.gnome.gtk.DrawingArea = v
    export unwrap.onResize
  }
  def init(v: DrawingArea): Unit = {
    Widget.init(v)
  }
  def uninitialized(): DrawingArea = {
    val res = new org.gnome.gtk.DrawingArea()
    res.asInstanceOf[DrawingArea]
  }
}