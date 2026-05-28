package guarana
package gtk
import util.*
opaque type GraphicsOffload <: Widget = org.gnome.gtk.GraphicsOffload & Widget
object GraphicsOffload extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.GraphicsOffload): GraphicsOffload = {
    v.asInstanceOf
  }
  def init(v: GraphicsOffload): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gtk.Widget | Null): GraphicsOffload = {
    val res = new org.gnome.gtk.GraphicsOffload(arg$0)
    res.asInstanceOf[GraphicsOffload]
  }
  def apply(arg$0: org.gnome.gtk.Widget | Null, blackBackground: Opt[Boolean] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, enabled: Opt[org.gnome.gtk.GraphicsOffloadEnabled] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, GraphicsOffload] = {
    val res = uninitialized(arg$0)
    init(res)
    ifSet(blackBackground, res.blackBackground := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(enabled, res.enabled := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
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
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}