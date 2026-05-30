package guarana
package gtk
import util.*
opaque type Popover <: Widget = org.gnome.gtk.Popover & Widget
object Popover extends VarsMap {
  val Autohide: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("autohide", _.getAutohide(), _.setAutohide(_), true)
  val CascadePopdown: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("cascade-popdown", _.getCascadePopdown(), _.setCascadePopdown(_), true)
  val Child: ExternalVar.Aux[Popover, org.gnome.gtk.Widget | Null] = ExternalVar[Popover, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val HasArrow: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("has-arrow", _.getHasArrow(), _.setHasArrow(_), true)
  val MnemonicsVisible: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("mnemonics-visible", _.getMnemonicsVisible(), _.setMnemonicsVisible(_), true)
  val Position: ExternalVar.Aux[Popover, org.gnome.gtk.PositionType] = ExternalVar[Popover, org.gnome.gtk.PositionType]("position", _.getPosition(), _.setPosition(_), true)
  ()
  extension (v: Popover) {
    def unwrap: org.gnome.gtk.Popover = v
    def autohide: Var.Aux[Boolean, v.type] = Autohide.asInstanceOf[Var.Aux[Boolean, v.type]]
    def cascadePopdown: Var.Aux[Boolean, v.type] = CascadePopdown.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def hasArrow: Var.Aux[Boolean, v.type] = HasArrow.asInstanceOf[Var.Aux[Boolean, v.type]]
    def mnemonicsVisible: Var.Aux[Boolean, v.type] = MnemonicsVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    def position: Var.Aux[org.gnome.gtk.PositionType, v.type] = Position.asInstanceOf[Var.Aux[org.gnome.gtk.PositionType, v.type]]
    export unwrap.onActivateDefault, unwrap.onClosed
  }
  def _wrap(v: org.gnome.gtk.Popover): Popover = {
    v.asInstanceOf
  }
  def init(v: Popover): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): Popover = {
    val res = new org.gnome.gtk.Popover()
    res.asInstanceOf[Popover]
  }
  def apply(autohide: Opt[Binding[Boolean]] = UnsetParam, canFocus: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, cascadePopdown: Opt[Binding[Boolean]] = UnsetParam, child: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, hasArrow: Opt[Binding[Boolean]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, mnemonicsVisible: Opt[Binding[Boolean]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, position: Opt[Binding[org.gnome.gtk.PositionType]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam): ToolkitAction[Toolkit, Popover] = {
    val res = uninitialized()
    init(res)
    ifSet(autohide, res.autohide := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(cascadePopdown, res.cascadePopdown := _)
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
    ifSet(hasArrow, res.hasArrow := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(mnemonicsVisible, res.mnemonicsVisible := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(position, res.position := _)
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