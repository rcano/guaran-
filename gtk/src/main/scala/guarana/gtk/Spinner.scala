package guarana
package gtk
import util.*
opaque type Spinner <: Widget = org.gnome.gtk.Spinner & Widget
object Spinner {
  val Spinning: ExternalVar.Aux[Spinner, Boolean] = ExternalVar[Spinner, Boolean]("spinning", _.getSpinning(), _.setSpinning(_), true)
  ()
  extension (v: Spinner) {
    def unwrap: org.gnome.gtk.Spinner = v
    def spinning: Var.Aux[Boolean, v.type] = Spinning.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def init(v: Spinner): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Spinner = {
    val res = new org.gnome.gtk.Spinner()
    res.asInstanceOf[Spinner]
  }
  def apply(spinning: Opt[Boolean] = UnsetParam): VarContextAction[Spinner] = {
    val res = uninitialized()
    init(res)
    ifSet(spinning, res.spinning := _)
    res
  }
}