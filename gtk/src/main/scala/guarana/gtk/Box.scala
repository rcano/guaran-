package guarana
package gtk
import util.*
opaque type Box <: Widget = org.gnome.gtk.Box & Widget
object Box extends VarsMap {
  val BaselineChild: ExternalVar.Aux[Box, Int] = ExternalVar[Box, Int]("baseline-child", _.getBaselineChild(), _.setBaselineChild(_), true)
  val BaselinePosition: ExternalVar.Aux[Box, org.gnome.gtk.BaselinePosition] = ExternalVar[Box, org.gnome.gtk.BaselinePosition]("baseline-position", _.getBaselinePosition(), _.setBaselinePosition(_), true)
  val Homogeneous: ExternalVar.Aux[Box, Boolean] = ExternalVar[Box, Boolean]("homogeneous", _.getHomogeneous(), _.setHomogeneous(_), true)
  val Orientation: ExternalVar.Aux[Box, org.gnome.gtk.Orientation] = ExternalVar[Box, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Spacing: ExternalVar.Aux[Box, Int] = ExternalVar[Box, Int]("spacing", _.getSpacing(), _.setSpacing(_), true)
  ()
  extension (v: Box) {
    def unwrap: org.gnome.gtk.Box = v
    def baselineChild: Var.Aux[Int, v.type] = BaselineChild.asInstanceOf[Var.Aux[Int, v.type]]
    def baselinePosition: Var.Aux[org.gnome.gtk.BaselinePosition, v.type] = BaselinePosition.asInstanceOf[Var.Aux[org.gnome.gtk.BaselinePosition, v.type]]
    def homogeneous: Var.Aux[Boolean, v.type] = Homogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def spacing: Var.Aux[Int, v.type] = Spacing.asInstanceOf[Var.Aux[Int, v.type]]
  }
  def _wrap(v: org.gnome.gtk.Box): Box = {
    v.asInstanceOf
  }
  def init(v: Box): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gtk.Orientation, arg$1: Int): Box = {
    val res = new org.gnome.gtk.Box(arg$0, arg$1)
    res.asInstanceOf[Box]
  }
  def apply(arg$0: org.gnome.gtk.Orientation, arg$1: Int, baselineChild: Opt[Int] = UnsetParam, baselinePosition: Opt[org.gnome.gtk.BaselinePosition] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, homogeneous: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, spacing: Opt[Int] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, Box] = {
    val res = uninitialized(arg$0, arg$1)
    init(res)
    ifSet(baselineChild, res.baselineChild := _)
    ifSet(baselinePosition, res.baselinePosition := _)
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
    ifSet(homogeneous, res.homogeneous := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(spacing, res.spacing := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}