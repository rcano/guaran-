package guarana
package gtk
import util.*
opaque type Range <: Widget = org.gnome.gtk.Range & Widget
object Range extends VarsMap {
  val Adjustment: ExternalVar.Aux[Range, org.gnome.gtk.Adjustment] = ExternalVar[Range, org.gnome.gtk.Adjustment]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  val FillLevel: ExternalVar.Aux[Range, Double] = ExternalVar[Range, Double]("fill-level", _.getFillLevel(), _.setFillLevel(_), true)
  val Flippable: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("flippable", _.getFlippable(), _.setFlippable(_), true)
  val Inverted: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("inverted", _.getInverted(), _.setInverted(_), true)
  val Orientation: ExternalVar.Aux[Range, org.gnome.gtk.Orientation] = ExternalVar[Range, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val RestrictToFillLevel: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("restrict-to-fill-level", _.getRestrictToFillLevel(), _.setRestrictToFillLevel(_), true)
  val RoundDigits: ExternalVar.Aux[Range, Int] = ExternalVar[Range, Int]("round-digits", _.getRoundDigits(), _.setRoundDigits(_), true)
  val ShowFillLevel: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("show-fill-level", _.getShowFillLevel(), _.setShowFillLevel(_), true)
  val SliderSizeFixed: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("slider-size-fixed", _.getSliderSizeFixed(), _.setSliderSizeFixed(_), true)
  val Value: ExternalVar.Aux[Range, Double] = ExternalVar[Range, Double]("value", _.getValue(), _.setValue(_), true)
  ()
  extension (v: Range) {
    def unwrap: org.gnome.gtk.Range = v
    def adjustment: Var.Aux[org.gnome.gtk.Adjustment, v.type] = Adjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment, v.type]]
    def fillLevel: Var.Aux[Double, v.type] = FillLevel.asInstanceOf[Var.Aux[Double, v.type]]
    def flippable: Var.Aux[Boolean, v.type] = Flippable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def inverted: Var.Aux[Boolean, v.type] = Inverted.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def restrictToFillLevel: Var.Aux[Boolean, v.type] = RestrictToFillLevel.asInstanceOf[Var.Aux[Boolean, v.type]]
    def roundDigits: Var.Aux[Int, v.type] = RoundDigits.asInstanceOf[Var.Aux[Int, v.type]]
    def showFillLevel: Var.Aux[Boolean, v.type] = ShowFillLevel.asInstanceOf[Var.Aux[Boolean, v.type]]
    def sliderSizeFixed: Var.Aux[Boolean, v.type] = SliderSizeFixed.asInstanceOf[Var.Aux[Boolean, v.type]]
    def value: Var.Aux[Double, v.type] = Value.asInstanceOf[Var.Aux[Double, v.type]]
    export unwrap.onAdjustBounds, unwrap.onChangeValue, unwrap.onMoveSlider, unwrap.onValueChanged
  }
  def _wrap(v: org.gnome.gtk.Range): Range = {
    v.asInstanceOf
  }
  def init(v: Range): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): Range = {
    val res = new org.gnome.gtk.Range()
    res.asInstanceOf[Range]
  }
  def apply(adjustment: Opt[org.gnome.gtk.Adjustment] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, fillLevel: Opt[Double] = UnsetParam, flippable: Opt[Boolean] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, inverted: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, restrictToFillLevel: Opt[Boolean] = UnsetParam, roundDigits: Opt[Int] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showFillLevel: Opt[Boolean] = UnsetParam, sliderSizeFixed: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, value: Opt[Double] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, Range] = {
    val res = uninitialized()
    init(res)
    ifSet(adjustment, res.adjustment := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(fillLevel, res.fillLevel := _)
    ifSet(flippable, res.flippable := _)
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
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}