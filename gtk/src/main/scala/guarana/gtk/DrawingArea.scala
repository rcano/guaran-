package guarana
package gtk
import util.*
opaque type DrawingArea <: Widget = org.gnome.gtk.DrawingArea & Widget
object DrawingArea {
  val ContentHeight: ExternalVar.Aux[DrawingArea, Int] = ExternalVar[DrawingArea, Int]("content-height", _.getContentHeight(), _.setContentHeight(_), true)
  val ContentWidth: ExternalVar.Aux[DrawingArea, Int] = ExternalVar[DrawingArea, Int]("content-width", _.getContentWidth(), _.setContentWidth(_), true)
  ()
  extension (v: DrawingArea) {
    def unwrap: org.gnome.gtk.DrawingArea = v
    def contentHeight: Var.Aux[Int, v.type] = ContentHeight.asInstanceOf[Var.Aux[Int, v.type]]
    def contentWidth: Var.Aux[Int, v.type] = ContentWidth.asInstanceOf[Var.Aux[Int, v.type]]
    export unwrap.onResize
  }
  def init(v: DrawingArea): Unit = {
    Widget.init(v)
  }
  def uninitialized(): DrawingArea = {
    val res = new org.gnome.gtk.DrawingArea()
    res.asInstanceOf[DrawingArea]
  }
  def apply(contentHeight: Opt[Int] = UnsetParam, contentWidth: Opt[Int] = UnsetParam): VarContextAction[DrawingArea] = {
    val res = uninitialized()
    init(res)
    ifSet(contentHeight, res.contentHeight := _)
    ifSet(contentWidth, res.contentWidth := _)
    res
  }
}