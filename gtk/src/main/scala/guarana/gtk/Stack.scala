package guarana
package gtk
import util.*
opaque type Stack <: Widget = org.gnome.gtk.Stack & Widget
object Stack extends VarsMap {
  val Hhomogeneous: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("hhomogeneous", _.getHhomogeneous(), _.setHhomogeneous(_), true)
  val InterpolateSize: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("interpolate-size", _.getInterpolateSize(), _.setInterpolateSize(_), true)
  val TransitionDuration: ExternalVar.Aux[Stack, Int] = ExternalVar[Stack, Int]("transition-duration", _.getTransitionDuration(), _.setTransitionDuration(_), true)
  val TransitionType: ExternalVar.Aux[Stack, org.gnome.gtk.StackTransitionType] = ExternalVar[Stack, org.gnome.gtk.StackTransitionType]("transition-type", _.getTransitionType(), _.setTransitionType(_), true)
  val Vhomogeneous: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("vhomogeneous", _.getVhomogeneous(), _.setVhomogeneous(_), true)
  ()
  extension (v: Stack) {
    def unwrap: org.gnome.gtk.Stack = v
    def hhomogeneous: Var.Aux[Boolean, v.type] = Hhomogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def interpolateSize: Var.Aux[Boolean, v.type] = InterpolateSize.asInstanceOf[Var.Aux[Boolean, v.type]]
    def transitionDuration: Var.Aux[Int, v.type] = TransitionDuration.asInstanceOf[Var.Aux[Int, v.type]]
    def transitionType: Var.Aux[org.gnome.gtk.StackTransitionType, v.type] = TransitionType.asInstanceOf[Var.Aux[org.gnome.gtk.StackTransitionType, v.type]]
    def vhomogeneous: Var.Aux[Boolean, v.type] = Vhomogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def _wrap(v: org.gnome.gtk.Stack): Stack = {
    v.asInstanceOf
  }
  def init(v: Stack): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): Stack = {
    val res = new org.gnome.gtk.Stack()
    res.asInstanceOf[Stack]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, hhomogeneous: Opt[Boolean] = UnsetParam, interpolateSize: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, transitionDuration: Opt[Int] = UnsetParam, transitionType: Opt[org.gnome.gtk.StackTransitionType] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, vhomogeneous: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, Stack] = {
    val res = uninitialized()
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
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
    ifSet(hhomogeneous, res.hhomogeneous := _)
    ifSet(interpolateSize, res.interpolateSize := _)
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
    ifSet(transitionDuration, res.transitionDuration := _)
    ifSet(transitionType, res.transitionType := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(vhomogeneous, res.vhomogeneous := _)
    ifSet(visible, res.visible := _)
    res
  }
}