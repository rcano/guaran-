package guarana
package gtk
import util.*
opaque type CheckButton <: Widget = org.gnome.gtk.CheckButton & Widget
object CheckButton extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.CheckButton): CheckButton = {
    v.asInstanceOf
  }
  def init(v: CheckButton): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): CheckButton = {
    val res = new org.gnome.gtk.CheckButton()
    res.asInstanceOf[CheckButton]
  }
  def apply(actionName: Opt[java.lang.String | Null] = UnsetParam, actionTargetValue: Opt[org.gnome.glib.Variant | Null] = UnsetParam, active: Opt[Boolean] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, inconsistent: Opt[Boolean] = UnsetParam, label: Opt[java.lang.String | Null] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, useUnderline: Opt[Boolean] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, CheckButton] = {
    val res = uninitialized()
    init(res)
    ifSet(actionName, res.actionName := _)
    ifSet(actionTargetValue, res.actionTargetValue := _)
    ifSet(active, res.active := _)
    ifSet(canFocus, res.canFocus := _)
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
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(inconsistent, res.inconsistent := _)
    ifSet(label, res.label := _)
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