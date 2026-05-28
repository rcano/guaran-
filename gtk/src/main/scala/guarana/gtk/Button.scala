package guarana
package gtk
import util.*
opaque type Button <: Widget = org.gnome.gtk.Button & Widget
object Button extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.Button): Button = {
    v.asInstanceOf
  }
  def init(v: Button): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): Button = {
    val res = new org.gnome.gtk.Button()
    res.asInstanceOf[Button]
  }
  def apply(actionName: Opt[java.lang.String | Null] = UnsetParam, actionTargetValue: Opt[org.gnome.glib.Variant | Null] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canShrink: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasFrame: Opt[Boolean] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, useUnderline: Opt[Boolean] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, Button] = {
    val res = uninitialized()
    init(res)
    ifSet(actionName, res.actionName := _)
    ifSet(actionTargetValue, res.actionTargetValue := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canShrink, res.canShrink := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasFrame, res.hasFrame := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(useUnderline, res.useUnderline := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}