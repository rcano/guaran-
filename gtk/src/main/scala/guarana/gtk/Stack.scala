package guarana
package gtk
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
  }
  def init(v: Stack): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Stack = {
    val res = new org.gnome.gtk.Stack()
    res.asInstanceOf[Stack]
  }
}