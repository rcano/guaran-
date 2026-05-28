package guarana
package gtk
import util.*
opaque type CheckButton <: Widget = org.gnome.gtk.CheckButton & Widget
object CheckButton {
  val ActionName: ExternalVar.Aux[CheckButton, java.lang.String | Null] = ExternalVar[CheckButton, java.lang.String | Null]("action-name", _.getActionName(), _.setActionName(_), true)
  val ActionTargetValue: ExternalVar.Aux[CheckButton, org.gnome.glib.Variant | Null] = ExternalVar[CheckButton, org.gnome.glib.Variant | Null]("action-target-value", _.getActionTargetValue(), _.setActionTargetValue(_), true)
  val Active: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("active", _.getActive(), _.setActive(_), true)
  val Child: ExternalVar.Aux[CheckButton, org.gnome.gtk.Widget | Null] = ExternalVar[CheckButton, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Inconsistent: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("inconsistent", _.getInconsistent(), _.setInconsistent(_), true)
  val Label: ExternalVar.Aux[CheckButton, java.lang.String | Null] = ExternalVar[CheckButton, java.lang.String | Null]("label", _.getLabel(), _.setLabel(_), true)
  val UseUnderline: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)
  ()
  extension (v: CheckButton) {
    def unwrap: org.gnome.gtk.CheckButton = v
    def actionName: Var.Aux[java.lang.String | Null, v.type] = ActionName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def actionTargetValue: Var.Aux[org.gnome.glib.Variant | Null, v.type] = ActionTargetValue.asInstanceOf[Var.Aux[org.gnome.glib.Variant | Null, v.type]]
    def active: Var.Aux[Boolean, v.type] = Active.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def inconsistent: Var.Aux[Boolean, v.type] = Inconsistent.asInstanceOf[Var.Aux[Boolean, v.type]]
    def label: Var.Aux[java.lang.String | Null, v.type] = Label.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def useUnderline: Var.Aux[Boolean, v.type] = UseUnderline.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate, unwrap.onToggled
  }
  def init(v: CheckButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): CheckButton = {
    val res = new org.gnome.gtk.CheckButton()
    res.asInstanceOf[CheckButton]
  }
  def apply(actionName: Opt[java.lang.String | Null] = UnsetParam, actionTargetValue: Opt[org.gnome.glib.Variant | Null] = UnsetParam, active: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, inconsistent: Opt[Boolean] = UnsetParam, label: Opt[java.lang.String | Null] = UnsetParam, useUnderline: Opt[Boolean] = UnsetParam): VarContextAction[CheckButton] = {
    val res = uninitialized()
    init(res)
    ifSet(actionName, res.actionName := _)
    ifSet(actionTargetValue, res.actionTargetValue := _)
    ifSet(active, res.active := _)
    ifSet(child, res.child := _)
    ifSet(inconsistent, res.inconsistent := _)
    ifSet(label, res.label := _)
    ifSet(useUnderline, res.useUnderline := _)
    res
  }
}