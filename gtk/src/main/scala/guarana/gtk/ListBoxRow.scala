package guarana
package gtk
import util.*
opaque type ListBoxRow <: Widget = org.gnome.gtk.ListBoxRow & Widget
object ListBoxRow extends VarsMap {
  val ActionName: ExternalVar.Aux[ListBoxRow, java.lang.String | Null] = ExternalVar[ListBoxRow, java.lang.String | Null]("action-name", _.getActionName(), _.setActionName(_), true)
  val ActionTargetValue: ExternalVar.Aux[ListBoxRow, org.gnome.glib.Variant | Null] = ExternalVar[ListBoxRow, org.gnome.glib.Variant | Null]("action-target-value", _.getActionTargetValue(), _.setActionTargetValue(_), true)
  val Activatable: ExternalVar.Aux[ListBoxRow, Boolean] = ExternalVar[ListBoxRow, Boolean]("activatable", _.getActivatable(), _.setActivatable(_), true)
  val Child: ExternalVar.Aux[ListBoxRow, org.gnome.gtk.Widget | Null] = ExternalVar[ListBoxRow, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Header: ExternalVar.Aux[ListBoxRow, org.gnome.gtk.Widget | Null] = ExternalVar[ListBoxRow, org.gnome.gtk.Widget | Null]("header", _.getHeader(), _.setHeader(_), true)
  val Selectable: ExternalVar.Aux[ListBoxRow, Boolean] = ExternalVar[ListBoxRow, Boolean]("selectable", _.getSelectable(), _.setSelectable(_), true)
  ()
  extension (v: ListBoxRow) {
    def unwrap: org.gnome.gtk.ListBoxRow = v
    def actionName: Var.Aux[java.lang.String | Null, v.type] = ActionName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def actionTargetValue: Var.Aux[org.gnome.glib.Variant | Null, v.type] = ActionTargetValue.asInstanceOf[Var.Aux[org.gnome.glib.Variant | Null, v.type]]
    def activatable: Var.Aux[Boolean, v.type] = Activatable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def header: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Header.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def selectable: Var.Aux[Boolean, v.type] = Selectable.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate
  }
  def _wrap(v: org.gnome.gtk.ListBoxRow): ListBoxRow = {
    v.asInstanceOf
  }
  def init(v: ListBoxRow): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): ListBoxRow = {
    val res = new org.gnome.gtk.ListBoxRow()
    res.asInstanceOf[ListBoxRow]
  }
  def apply(actionName: Opt[java.lang.String | Null] = UnsetParam, actionTargetValue: Opt[org.gnome.glib.Variant | Null] = UnsetParam, activatable: Opt[Boolean] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, header: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, selectable: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, ListBoxRow] = {
    val res = uninitialized()
    init(res)
    ifSet(actionName, res.actionName := _)
    ifSet(actionTargetValue, res.actionTargetValue := _)
    ifSet(activatable, res.activatable := _)
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
    ifSet(header, res.header := _)
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
    ifSet(selectable, res.selectable := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}