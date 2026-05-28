package guarana
package gtk
import util.*
opaque type Range <: Widget = org.gnome.gtk.Range & Widget
object Range {
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
  def init(v: Range): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Range = {
    val res = new org.gnome.gtk.Range()
    res.asInstanceOf[Range]
  }
  def apply(adjustment: Opt[org.gnome.gtk.Adjustment] = UnsetParam, fillLevel: Opt[Double] = UnsetParam, flippable: Opt[Boolean] = UnsetParam, inverted: Opt[Boolean] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, restrictToFillLevel: Opt[Boolean] = UnsetParam, roundDigits: Opt[Int] = UnsetParam, showFillLevel: Opt[Boolean] = UnsetParam, sliderSizeFixed: Opt[Boolean] = UnsetParam, value: Opt[Double] = UnsetParam): VarContextAction[Range] = {
    val res = uninitialized()
    init(res)
    ifSet(adjustment, res.adjustment := _)
    ifSet(fillLevel, res.fillLevel := _)
    ifSet(flippable, res.flippable := _)
    ifSet(inverted, res.inverted := _)
    ifSet(orientation, res.orientation := _)
    ifSet(restrictToFillLevel, res.restrictToFillLevel := _)
    ifSet(roundDigits, res.roundDigits := _)
    ifSet(showFillLevel, res.showFillLevel := _)
    ifSet(sliderSizeFixed, res.sliderSizeFixed := _)
    ifSet(value, res.value := _)
    res
  }
}