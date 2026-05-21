package guarana
package gtk
opaque type SpinButton <: Widget = org.gnome.gtk.SpinButton & Widget
object SpinButton {
  val ActivatesDefault: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("activates-default", _.getActivatesDefault(), _.setActivatesDefault(_), true)
  val Adjustment: ExternalVar.Aux[SpinButton, org.gnome.gtk.Adjustment | Null] = ExternalVar[SpinButton, org.gnome.gtk.Adjustment | Null]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  val ClimbRate: ExternalVar.Aux[SpinButton, Double] = ExternalVar[SpinButton, Double]("climb-rate", _.getClimbRate(), _.setClimbRate(_), true)
  val Digits: ExternalVar.Aux[SpinButton, Int] = ExternalVar[SpinButton, Int]("digits", _.getDigits(), _.setDigits(_), true)
  val Numeric: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("numeric", _.getNumeric(), _.setNumeric(_), true)
  val SnapToTicks: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("snap-to-ticks", _.getSnapToTicks(), _.setSnapToTicks(_), true)
  val UpdatePolicy: ExternalVar.Aux[SpinButton, org.gnome.gtk.SpinButtonUpdatePolicy | Null] = ExternalVar[SpinButton, org.gnome.gtk.SpinButtonUpdatePolicy | Null]("update-policy", _.getUpdatePolicy(), _.setUpdatePolicy(_), true)
  val Value: ExternalVar.Aux[SpinButton, Double] = ExternalVar[SpinButton, Double]("value", _.getValue(), _.setValue(_), true)
  val Wrap: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("wrap", _.getWrap(), _.setWrap(_), true)
  ()
  extension (v: SpinButton) {
    def unwrap: org.gnome.gtk.SpinButton = v
    export unwrap.onActivate, unwrap.onChangeValue, unwrap.onInput, unwrap.onOutput, unwrap.onValueChanged, unwrap.onWrapped
  }
}