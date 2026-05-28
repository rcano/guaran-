package guarana
package gtk
import util.*
opaque type Revealer <: Widget = org.gnome.gtk.Revealer & Widget
object Revealer {
  val Child: ExternalVar.Aux[Revealer, org.gnome.gtk.Widget | Null] = ExternalVar[Revealer, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val RevealChild: ExternalVar.Aux[Revealer, Boolean] = ExternalVar[Revealer, Boolean]("reveal-child", _.getRevealChild(), _.setRevealChild(_), true)
  val TransitionDuration: ExternalVar.Aux[Revealer, Int] = ExternalVar[Revealer, Int]("transition-duration", _.getTransitionDuration(), _.setTransitionDuration(_), true)
  val TransitionType: ExternalVar.Aux[Revealer, org.gnome.gtk.RevealerTransitionType] = ExternalVar[Revealer, org.gnome.gtk.RevealerTransitionType]("transition-type", _.getTransitionType(), _.setTransitionType(_), true)
  ()
  extension (v: Revealer) {
    def unwrap: org.gnome.gtk.Revealer = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def revealChild: Var.Aux[Boolean, v.type] = RevealChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def transitionDuration: Var.Aux[Int, v.type] = TransitionDuration.asInstanceOf[Var.Aux[Int, v.type]]
    def transitionType: Var.Aux[org.gnome.gtk.RevealerTransitionType, v.type] = TransitionType.asInstanceOf[Var.Aux[org.gnome.gtk.RevealerTransitionType, v.type]]
  }
  def init(v: Revealer): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Revealer = {
    val res = new org.gnome.gtk.Revealer()
    res.asInstanceOf[Revealer]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, revealChild: Opt[Boolean] = UnsetParam, transitionDuration: Opt[Int] = UnsetParam, transitionType: Opt[org.gnome.gtk.RevealerTransitionType] = UnsetParam): VarContextAction[Revealer] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    ifSet(revealChild, res.revealChild := _)
    ifSet(transitionDuration, res.transitionDuration := _)
    ifSet(transitionType, res.transitionType := _)
    res
  }
}