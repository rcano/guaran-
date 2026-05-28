package guarana
package gtk
import util.*
opaque type LockButton <: Button = org.gnome.gtk.LockButton & Button
object LockButton extends VarsMap {
  @deprecated("", "") val Permission: ExternalVar.Aux[LockButton, org.gnome.gio.Permission | Null] = ExternalVar[LockButton, org.gnome.gio.Permission | Null]("permission", _.getPermission(), _.setPermission(_), true)
  ()
  extension (v: LockButton) {
    def unwrap: org.gnome.gtk.LockButton = v
    @deprecated("", "") def permission: Var.Aux[org.gnome.gio.Permission | Null, v.type] = Permission.asInstanceOf[Var.Aux[org.gnome.gio.Permission | Null, v.type]]
  }
  def _wrap(v: org.gnome.gtk.LockButton): LockButton = {
    v.asInstanceOf
  }
  def init(v: LockButton): ToolkitAction[Toolkit, Unit] = {
    Button.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gio.Permission | Null): LockButton = {
    val res = new org.gnome.gtk.LockButton(arg$0)
    res.asInstanceOf[LockButton]
  }
  def apply(arg$0: org.gnome.gio.Permission | Null, actionName: Opt[java.lang.String | Null] = UnsetParam, actionTargetValue: Opt[org.gnome.glib.Variant | Null] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canShrink: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasFrame: Opt[Boolean] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, permission: Opt[org.gnome.gio.Permission | Null] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, useUnderline: Opt[Boolean] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, LockButton] = {
    val res = uninitialized(arg$0)
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
    ifSet(permission, res.permission := _)
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