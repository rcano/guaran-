package guarana
package gtk
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
    export unwrap.onAdjustBounds, unwrap.onChangeValue, unwrap.onMoveSlider, unwrap.onValueChanged
  }
  def init(v: Range): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Range = {
    val res = new org.gnome.gtk.Range()
    res.asInstanceOf[Range]
  }
}