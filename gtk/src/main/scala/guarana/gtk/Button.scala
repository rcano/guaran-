package guarana
package gtk
import util.*
opaque type Button <: Widget = org.gnome.gtk.Button & Widget
object Button {
  val ActionName: ExternalVar.Aux[Button, java.lang.String | Null] = ExternalVar[Button, java.lang.String | Null]("action-name", _.getActionName(), _.setActionName(_), true)
  val ActionTargetValue: ExternalVar.Aux[Button, org.gnome.glib.Variant | Null] = ExternalVar[Button, org.gnome.glib.Variant | Null]("action-target-value", _.getActionTargetValue(), _.setActionTargetValue(_), true)
  val CanShrink: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("can-shrink", _.getCanShrink(), _.setCanShrink(_), true)
  val Child: ExternalVar.Aux[Button, org.gnome.gtk.Widget | Null] = ExternalVar[Button, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val HasFrame: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val UseUnderline: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)
  ()
  extension (v: Button) {
    def unwrap: org.gnome.gtk.Button = v
    def actionName: Var.Aux[java.lang.String | Null, v.type] = ActionName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def actionTargetValue: Var.Aux[org.gnome.glib.Variant | Null, v.type] = ActionTargetValue.asInstanceOf[Var.Aux[org.gnome.glib.Variant | Null, v.type]]
    def canShrink: Var.Aux[Boolean, v.type] = CanShrink.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def hasFrame: Var.Aux[Boolean, v.type] = HasFrame.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useUnderline: Var.Aux[Boolean, v.type] = UseUnderline.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate, unwrap.onClicked
  }
  def init(v: Button): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Button = {
    val res = new org.gnome.gtk.Button()
    res.asInstanceOf[Button]
  }
  def apply(actionName: Opt[java.lang.String | Null] = UnsetParam, actionTargetValue: Opt[org.gnome.glib.Variant | Null] = UnsetParam, canShrink: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, hasFrame: Opt[Boolean] = UnsetParam, useUnderline: Opt[Boolean] = UnsetParam): VarContextAction[Button] = {
    val res = uninitialized()
    init(res)
    ifSet(actionName, res.actionName := _)
    ifSet(actionTargetValue, res.actionTargetValue := _)
    ifSet(canShrink, res.canShrink := _)
    ifSet(child, res.child := _)
    ifSet(hasFrame, res.hasFrame := _)
    ifSet(useUnderline, res.useUnderline := _)
    res
  }
}