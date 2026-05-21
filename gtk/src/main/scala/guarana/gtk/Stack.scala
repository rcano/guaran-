package guarana
package gtk
opaque type Stack <: Widget = org.gnome.gtk.Stack & Widget
object Stack {
  val Hhomogeneous: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("hhomogeneous", _.getHhomogeneous(), _.setHhomogeneous(_), true)
  val InterpolateSize: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("interpolate-size", _.getInterpolateSize(), _.setInterpolateSize(_), true)
  val TransitionDuration: ExternalVar.Aux[Stack, Int] = ExternalVar[Stack, Int]("transition-duration", _.getTransitionDuration(), _.setTransitionDuration(_), true)
  val TransitionType: ExternalVar.Aux[Stack, org.gnome.gtk.StackTransitionType | Null] = ExternalVar[Stack, org.gnome.gtk.StackTransitionType | Null]("transition-type", _.getTransitionType(), _.setTransitionType(_), true)
  val Vhomogeneous: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("vhomogeneous", _.getVhomogeneous(), _.setVhomogeneous(_), true)
  val VisibleChild: ExternalVar.Aux[Stack, org.gnome.gtk.Widget | Null] = ExternalVar[Stack, org.gnome.gtk.Widget | Null]("visible-child", _.getVisibleChild(), _.setVisibleChild(_), true)
  val VisibleChildName: ExternalVar.Aux[Stack, java.lang.String | Null] = ExternalVar[Stack, java.lang.String | Null]("visible-child-name", _.getVisibleChildName(), _.setVisibleChildName(_), true)
  ()
  extension (v: Stack) {
    def unwrap: org.gnome.gtk.Stack = v
  }
}