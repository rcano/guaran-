package guarana
package gtk
import util.*
opaque type Revealer <: Widget = org.gnome.gtk.Revealer & Widget
object Revealer extends VarsMap {
  val Child: ExternalVar.Aux[Revealer, org.gnome.gtk.Widget | Null] = ExternalVar[Revealer, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val RevealChild: ExternalVar.Aux[Revealer, Boolean] = ExternalVar[Revealer, Boolean]("reveal-child", _.getRevealChild(), _.setRevealChild(_), true)
  val TransitionDuration: ExternalVar.Aux[Revealer, Int] = ExternalVar[Revealer, Int]("transition-duration", _.getTransitionDuration(), _.setTransitionDuration(_), true)
  val TransitionType: ExternalVar.Aux[Revealer, org.gnome.gtk.RevealerTransitionType] = ExternalVar[Revealer, org.gnome.gtk.RevealerTransitionType]("transition-type", _.getTransitionType(), _.setTransitionType(_), true)
  ()
  extension (v: Revealer) {
    def unwrap: org.gnome.gtk.Revealer = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def revealChild: Var.Aux[Boolean, v.type] = RevealChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def transitionDuration: Var.Aux[Int, v.type] = TransitionDuration.asInstanceOf[Var.Aux[Int, v.type]]
    def transitionType: Var.Aux[org.gnome.gtk.RevealerTransitionType, v.type] = TransitionType.asInstanceOf[Var.Aux[org.gnome.gtk.RevealerTransitionType, v.type]]
  }
  def _wrap(v: org.gnome.gtk.Revealer): Revealer = {
    v.asInstanceOf
  }
  def init(v: Revealer): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): Revealer = {
    val res = new org.gnome.gtk.Revealer()
    res.asInstanceOf[Revealer]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, revealChild: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, transitionDuration: Opt[Int] = UnsetParam, transitionType: Opt[org.gnome.gtk.RevealerTransitionType] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, Revealer] = {
    val res = uninitialized()
    init(res)
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
    ifSet(revealChild, res.revealChild := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(transitionDuration, res.transitionDuration := _)
    ifSet(transitionType, res.transitionType := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}