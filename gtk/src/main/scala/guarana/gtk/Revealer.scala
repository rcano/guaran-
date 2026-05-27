package guarana
package gtk
opaque type Revealer <: Widget = org.gnome.gtk.Revealer & Widget
object Revealer {
  val RevealChild: ExternalVar.Aux[Revealer, Boolean] = ExternalVar[Revealer, Boolean]("reveal-child", _.getRevealChild(), _.setRevealChild(_), true)
  val TransitionDuration: ExternalVar.Aux[Revealer, Int] = ExternalVar[Revealer, Int]("transition-duration", _.getTransitionDuration(), _.setTransitionDuration(_), true)
  val TransitionType: ExternalVar.Aux[Revealer, org.gnome.gtk.RevealerTransitionType | Null] = ExternalVar[Revealer, org.gnome.gtk.RevealerTransitionType | Null]("transition-type", _.getTransitionType(), _.setTransitionType(_), true)
  ()
  extension (v: Revealer) {
    def unwrap: org.gnome.gtk.Revealer = v
  }
  def init(v: Revealer): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Revealer = {
    val res = new org.gnome.gtk.Revealer()
    res.asInstanceOf[Revealer]
  }
}