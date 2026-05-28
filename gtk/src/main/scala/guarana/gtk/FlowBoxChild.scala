package guarana
package gtk
import util.*
opaque type FlowBoxChild <: Widget = org.gnome.gtk.FlowBoxChild & Widget
object FlowBoxChild {
  val Child: ExternalVar.Aux[FlowBoxChild, org.gnome.gtk.Widget | Null] = ExternalVar[FlowBoxChild, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  ()
  extension (v: FlowBoxChild) {
    def unwrap: org.gnome.gtk.FlowBoxChild = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    export unwrap.onActivate
  }
  def init(v: FlowBoxChild): Unit = {
    Widget.init(v)
  }
  def uninitialized(): FlowBoxChild = {
    val res = new org.gnome.gtk.FlowBoxChild()
    res.asInstanceOf[FlowBoxChild]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam): VarContextAction[FlowBoxChild] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    res
  }
}