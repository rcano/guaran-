package guarana
package gtk
import util.*
opaque type Stack <: Widget = org.gnome.gtk.Stack & Widget
object Stack {
  val Hhomogeneous: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("hhomogeneous", _.getHhomogeneous(), _.setHhomogeneous(_), true)
  val InterpolateSize: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("interpolate-size", _.getInterpolateSize(), _.setInterpolateSize(_), true)
  val TransitionDuration: ExternalVar.Aux[Stack, Int] = ExternalVar[Stack, Int]("transition-duration", _.getTransitionDuration(), _.setTransitionDuration(_), true)
  val TransitionType: ExternalVar.Aux[Stack, org.gnome.gtk.StackTransitionType] = ExternalVar[Stack, org.gnome.gtk.StackTransitionType]("transition-type", _.getTransitionType(), _.setTransitionType(_), true)
  val Vhomogeneous: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("vhomogeneous", _.getVhomogeneous(), _.setVhomogeneous(_), true)
  ()
  extension (v: Stack) {
    def unwrap: org.gnome.gtk.Stack = v
    def hhomogeneous: Var.Aux[Boolean, v.type] = Hhomogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def interpolateSize: Var.Aux[Boolean, v.type] = InterpolateSize.asInstanceOf[Var.Aux[Boolean, v.type]]
    def transitionDuration: Var.Aux[Int, v.type] = TransitionDuration.asInstanceOf[Var.Aux[Int, v.type]]
    def transitionType: Var.Aux[org.gnome.gtk.StackTransitionType, v.type] = TransitionType.asInstanceOf[Var.Aux[org.gnome.gtk.StackTransitionType, v.type]]
    def vhomogeneous: Var.Aux[Boolean, v.type] = Vhomogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def init(v: Stack): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Stack = {
    val res = new org.gnome.gtk.Stack()
    res.asInstanceOf[Stack]
  }
  def apply(hhomogeneous: Opt[Boolean] = UnsetParam, interpolateSize: Opt[Boolean] = UnsetParam, transitionDuration: Opt[Int] = UnsetParam, transitionType: Opt[org.gnome.gtk.StackTransitionType] = UnsetParam, vhomogeneous: Opt[Boolean] = UnsetParam): VarContextAction[Stack] = {
    val res = uninitialized()
    init(res)
    ifSet(hhomogeneous, res.hhomogeneous := _)
    ifSet(interpolateSize, res.interpolateSize := _)
    ifSet(transitionDuration, res.transitionDuration := _)
    ifSet(transitionType, res.transitionType := _)
    ifSet(vhomogeneous, res.vhomogeneous := _)
    res
  }
}