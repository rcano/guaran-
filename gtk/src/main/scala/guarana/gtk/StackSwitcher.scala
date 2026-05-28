package guarana
package gtk
import util.*
opaque type StackSwitcher <: Widget = org.gnome.gtk.StackSwitcher & Widget
object StackSwitcher {
  val Orientation: ExternalVar.Aux[StackSwitcher, org.gnome.gtk.Orientation] = ExternalVar[StackSwitcher, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Stack: ExternalVar.Aux[StackSwitcher, org.gnome.gtk.Stack | Null] = ExternalVar[StackSwitcher, org.gnome.gtk.Stack | Null]("stack", _.getStack(), _.setStack(_), true)
  ()
  extension (v: StackSwitcher) {
    def unwrap: org.gnome.gtk.StackSwitcher = v
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def stack: Var.Aux[org.gnome.gtk.Stack | Null, v.type] = Stack.asInstanceOf[Var.Aux[org.gnome.gtk.Stack | Null, v.type]]
  }
  def init(v: StackSwitcher): Unit = {
    Widget.init(v)
  }
  def uninitialized(): StackSwitcher = {
    val res = new org.gnome.gtk.StackSwitcher()
    res.asInstanceOf[StackSwitcher]
  }
  def apply(orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, stack: Opt[org.gnome.gtk.Stack | Null] = UnsetParam): VarContextAction[StackSwitcher] = {
    val res = uninitialized()
    init(res)
    ifSet(orientation, res.orientation := _)
    ifSet(stack, res.stack := _)
    res
  }
}