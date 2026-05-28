package guarana
package gtk
import util.*
opaque type Scrollbar <: Widget = org.gnome.gtk.Scrollbar & Widget
object Scrollbar {
  val Orientation: ExternalVar.Aux[Scrollbar, org.gnome.gtk.Orientation] = ExternalVar[Scrollbar, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  ()
  extension (v: Scrollbar) {
    def unwrap: org.gnome.gtk.Scrollbar = v
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
  }
  def init(v: Scrollbar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Scrollbar = {
    val res = new org.gnome.gtk.Scrollbar()
    res.asInstanceOf[Scrollbar]
  }
  def apply(orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam): VarContextAction[Scrollbar] = {
    val res = uninitialized()
    init(res)
    ifSet(orientation, res.orientation := _)
    res
  }
}