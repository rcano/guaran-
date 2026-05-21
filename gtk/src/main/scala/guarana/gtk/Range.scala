package guarana
package gtk
opaque type Range <: Widget = org.gnome.gtk.Range & Widget
object Range {
  val Adjustment: ExternalVar.Aux[Range, org.gnome.gtk.Adjustment | Null] = ExternalVar[Range, org.gnome.gtk.Adjustment | Null]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  val FillLevel: ExternalVar.Aux[Range, Double] = ExternalVar[Range, Double]("fill-level", _.getFillLevel(), _.setFillLevel(_), true)
  val Inverted: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("inverted", _.getInverted(), _.setInverted(_), true)
  val RestrictToFillLevel: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("restrict-to-fill-level", _.getRestrictToFillLevel(), _.setRestrictToFillLevel(_), true)
  val RoundDigits: ExternalVar.Aux[Range, Int] = ExternalVar[Range, Int]("round-digits", _.getRoundDigits(), _.setRoundDigits(_), true)
  val ShowFillLevel: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("show-fill-level", _.getShowFillLevel(), _.setShowFillLevel(_), true)
  ()
  extension (v: Range) {
    def unwrap: org.gnome.gtk.Range = v
    export unwrap.onAdjustBounds, unwrap.onChangeValue, unwrap.onMoveSlider, unwrap.onValueChanged
  }
}