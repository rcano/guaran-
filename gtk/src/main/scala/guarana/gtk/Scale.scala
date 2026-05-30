package guarana
package gtk
import util.*
opaque type Scale <: Range = org.gnome.gtk.Scale & Range
object Scale extends VarsMap {
  val Digits: ExternalVar.Aux[Scale, Int] = ExternalVar[Scale, Int]("digits", _.getDigits(), _.setDigits(_), true)
  val DrawValue: ExternalVar.Aux[Scale, Boolean] = ExternalVar[Scale, Boolean]("draw-value", _.getDrawValue(), _.setDrawValue(_), true)
  val HasOrigin: ExternalVar.Aux[Scale, Boolean] = ExternalVar[Scale, Boolean]("has-origin", _.getHasOrigin(), _.setHasOrigin(_), true)
  val ValuePos: ExternalVar.Aux[Scale, org.gnome.gtk.PositionType] = ExternalVar[Scale, org.gnome.gtk.PositionType]("value-pos", _.getValuePos(), _.setValuePos(_), true)
  ()
  extension (v: Scale) {
    def unwrap: org.gnome.gtk.Scale = v
    def digits: Var.Aux[Int, v.type] = Digits.asInstanceOf[Var.Aux[Int, v.type]]
    def drawValue: Var.Aux[Boolean, v.type] = DrawValue.asInstanceOf[Var.Aux[Boolean, v.type]]
    def hasOrigin: Var.Aux[Boolean, v.type] = HasOrigin.asInstanceOf[Var.Aux[Boolean, v.type]]
    def valuePos: Var.Aux[org.gnome.gtk.PositionType, v.type] = ValuePos.asInstanceOf[Var.Aux[org.gnome.gtk.PositionType, v.type]]
  }
  def _wrap(v: org.gnome.gtk.Scale): Scale = {
    v.asInstanceOf
  }
  def init(v: Scale): ToolkitAction[Toolkit, Unit] = {
    Range.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gtk.Orientation, arg$1: org.gnome.gtk.Adjustment | Null): Scale = {
    val res = new org.gnome.gtk.Scale(arg$0, arg$1)
    res.asInstanceOf[Scale]
  }
  def apply(arg$0: org.gnome.gtk.Orientation, arg$1: org.gnome.gtk.Adjustment | Null, adjustment: Opt[Binding[org.gnome.gtk.Adjustment]] = UnsetParam, canFocus: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, digits: Opt[Binding[Int]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, drawValue: Opt[Binding[Boolean]] = UnsetParam, fillLevel: Opt[Binding[Double]] = UnsetParam, flippable: Opt[Binding[Boolean]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, hasOrigin: Opt[Binding[Boolean]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, inverted: Opt[Binding[Boolean]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, restrictToFillLevel: Opt[Binding[Boolean]] = UnsetParam, roundDigits: Opt[Binding[Int]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, showFillLevel: Opt[Binding[Boolean]] = UnsetParam, sliderSizeFixed: Opt[Binding[Boolean]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, value: Opt[Binding[Double]] = UnsetParam, valuePos: Opt[Binding[org.gnome.gtk.PositionType]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam): ToolkitAction[Toolkit, Scale] = {
    val res = uninitialized(arg$0, arg$1)
    init(res)
    ifSet(adjustment, res.adjustment := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(digits, res.digits := _)
    ifSet(direction, res.direction := _)
    ifSet(drawValue, res.drawValue := _)
    ifSet(fillLevel, res.fillLevel := _)
    ifSet(flippable, res.flippable := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasOrigin, res.hasOrigin := _)
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
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(restrictToFillLevel, res.restrictToFillLevel := _)
    ifSet(roundDigits, res.roundDigits := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showFillLevel, res.showFillLevel := _)
    ifSet(sliderSizeFixed, res.sliderSizeFixed := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(value, res.value := _)
    ifSet(valuePos, res.valuePos := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}