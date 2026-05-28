package guarana
package gtk
import util.*
opaque type Switch <: Widget = org.gnome.gtk.Switch & Widget
object Switch {
  val ActionName: ExternalVar.Aux[Switch, java.lang.String | Null] = ExternalVar[Switch, java.lang.String | Null]("action-name", _.getActionName(), _.setActionName(_), true)
  val ActionTargetValue: ExternalVar.Aux[Switch, org.gnome.glib.Variant | Null] = ExternalVar[Switch, org.gnome.glib.Variant | Null]("action-target-value", _.getActionTargetValue(), _.setActionTargetValue(_), true)
  val Active: ExternalVar.Aux[Switch, Boolean] = ExternalVar[Switch, Boolean]("active", _.getActive(), _.setActive(_), true)
  val State: ExternalVar.Aux[Switch, Boolean] = ExternalVar[Switch, Boolean]("state", _.getState(), _.setState(_), true)
  ()
  extension (v: Switch) {
    def unwrap: org.gnome.gtk.Switch = v
    def actionName: Var.Aux[java.lang.String | Null, v.type] = ActionName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def actionTargetValue: Var.Aux[org.gnome.glib.Variant | Null, v.type] = ActionTargetValue.asInstanceOf[Var.Aux[org.gnome.glib.Variant | Null, v.type]]
    def active: Var.Aux[Boolean, v.type] = Active.asInstanceOf[Var.Aux[Boolean, v.type]]
    def state: Var.Aux[Boolean, v.type] = State.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate, unwrap.onStateSet
  }
  def init(v: Switch): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Switch = {
    val res = new org.gnome.gtk.Switch()
    res.asInstanceOf[Switch]
  }
  def apply(actionName: Opt[java.lang.String | Null] = UnsetParam, actionTargetValue: Opt[org.gnome.glib.Variant | Null] = UnsetParam, active: Opt[Boolean] = UnsetParam, state: Opt[Boolean] = UnsetParam): VarContextAction[Switch] = {
    val res = uninitialized()
    init(res)
    ifSet(actionName, res.actionName := _)
    ifSet(actionTargetValue, res.actionTargetValue := _)
    ifSet(active, res.active := _)
    ifSet(state, res.state := _)
    res
  }
}