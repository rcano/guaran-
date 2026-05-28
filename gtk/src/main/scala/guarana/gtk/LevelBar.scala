package guarana
package gtk
import util.*
opaque type LevelBar <: Widget = org.gnome.gtk.LevelBar & Widget
object LevelBar extends VarsMap {
  val Inverted: ExternalVar.Aux[LevelBar, Boolean] = ExternalVar[LevelBar, Boolean]("inverted", _.getInverted(), _.setInverted(_), true)
  val MaxValue: ExternalVar.Aux[LevelBar, Double] = ExternalVar[LevelBar, Double]("max-value", _.getMaxValue(), _.setMaxValue(_), true)
  val MinValue: ExternalVar.Aux[LevelBar, Double] = ExternalVar[LevelBar, Double]("min-value", _.getMinValue(), _.setMinValue(_), true)
  val Mode: ExternalVar.Aux[LevelBar, org.gnome.gtk.LevelBarMode] = ExternalVar[LevelBar, org.gnome.gtk.LevelBarMode]("mode", _.getMode(), _.setMode(_), true)
  val Orientation: ExternalVar.Aux[LevelBar, org.gnome.gtk.Orientation] = ExternalVar[LevelBar, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Value: ExternalVar.Aux[LevelBar, Double] = ExternalVar[LevelBar, Double]("value", _.getValue(), _.setValue(_), true)
  ()
  extension (v: LevelBar) {
    def unwrap: org.gnome.gtk.LevelBar = v
    def inverted: Var.Aux[Boolean, v.type] = Inverted.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxValue: Var.Aux[Double, v.type] = MaxValue.asInstanceOf[Var.Aux[Double, v.type]]
    def minValue: Var.Aux[Double, v.type] = MinValue.asInstanceOf[Var.Aux[Double, v.type]]
    def mode: Var.Aux[org.gnome.gtk.LevelBarMode, v.type] = Mode.asInstanceOf[Var.Aux[org.gnome.gtk.LevelBarMode, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def value: Var.Aux[Double, v.type] = Value.asInstanceOf[Var.Aux[Double, v.type]]
    export unwrap.onOffsetChanged
  }
  def _wrap(v: org.gnome.gtk.LevelBar): LevelBar = {
    v.asInstanceOf
  }
  def init(v: LevelBar): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): LevelBar = {
    val res = new org.gnome.gtk.LevelBar()
    res.asInstanceOf[LevelBar]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, inverted: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, maxValue: Opt[Double] = UnsetParam, minValue: Opt[Double] = UnsetParam, mode: Opt[org.gnome.gtk.LevelBarMode] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, value: Opt[Double] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, LevelBar] = {
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
    ifSet(inverted, res.inverted := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(maxValue, res.maxValue := _)
    ifSet(minValue, res.minValue := _)
    ifSet(mode, res.mode := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(value, res.value := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}