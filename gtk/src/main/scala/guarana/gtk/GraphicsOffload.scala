package guarana
package gtk
import util.*
opaque type GraphicsOffload <: Widget = org.gnome.gtk.GraphicsOffload & Widget
object GraphicsOffload {
  val BlackBackground: ExternalVar.Aux[GraphicsOffload, Boolean] = ExternalVar[GraphicsOffload, Boolean]("black-background", _.getBlackBackground(), _.setBlackBackground(_), true)
  val Child: ExternalVar.Aux[GraphicsOffload, org.gnome.gtk.Widget | Null] = ExternalVar[GraphicsOffload, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Enabled: ExternalVar.Aux[GraphicsOffload, org.gnome.gtk.GraphicsOffloadEnabled] = ExternalVar[GraphicsOffload, org.gnome.gtk.GraphicsOffloadEnabled]("enabled", _.getEnabled(), _.setEnabled(_), true)
  ()
  extension (v: GraphicsOffload) {
    def unwrap: org.gnome.gtk.GraphicsOffload = v
    def blackBackground: Var.Aux[Boolean, v.type] = BlackBackground.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def enabled: Var.Aux[org.gnome.gtk.GraphicsOffloadEnabled, v.type] = Enabled.asInstanceOf[Var.Aux[org.gnome.gtk.GraphicsOffloadEnabled, v.type]]
  }
  def init(v: GraphicsOffload): Unit = {
    Widget.init(v)
  }
  def uninitialized(): GraphicsOffload = {
    val res = new org.gnome.gtk.GraphicsOffload()
    res.asInstanceOf[GraphicsOffload]
  }
  def apply(blackBackground: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, enabled: Opt[org.gnome.gtk.GraphicsOffloadEnabled] = UnsetParam): VarContextAction[GraphicsOffload] = {
    val res = uninitialized()
    init(res)
    ifSet(blackBackground, res.blackBackground := _)
    ifSet(child, res.child := _)
    ifSet(enabled, res.enabled := _)
    res
  }
}