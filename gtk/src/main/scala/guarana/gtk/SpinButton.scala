package guarana
package gtk
opaque type SpinButton <: Widget = org.gnome.gtk.SpinButton & Widget
object SpinButton {
  val ActivatesDefault: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("activates-default", _.getActivatesDefault(), _.setActivatesDefault(_), true)
  val Adjustment: ExternalVar.Aux[SpinButton, org.gnome.gtk.Adjustment] = ExternalVar[SpinButton, org.gnome.gtk.Adjustment]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  val Alignment: ExternalVar.Aux[SpinButton, Float] = ExternalVar[SpinButton, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val ClimbRate: ExternalVar.Aux[SpinButton, Double] = ExternalVar[SpinButton, Double]("climb-rate", _.getClimbRate(), _.setClimbRate(_), true)
  val Digits: ExternalVar.Aux[SpinButton, Int] = ExternalVar[SpinButton, Int]("digits", _.getDigits(), _.setDigits(_), true)
  val Editable: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val MaxWidthChars: ExternalVar.Aux[SpinButton, Int] = ExternalVar[SpinButton, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val Numeric: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("numeric", _.getNumeric(), _.setNumeric(_), true)
  val Orientation: ExternalVar.Aux[SpinButton, org.gnome.gtk.Orientation] = ExternalVar[SpinButton, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Position: ExternalVar.Aux[SpinButton, Int] = ExternalVar[SpinButton, Int]("position", _.getPosition(), _.setPosition(_), true)
  val SnapToTicks: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("snap-to-ticks", _.getSnapToTicks(), _.setSnapToTicks(_), true)
  val Text: ExternalVar.Aux[SpinButton, java.lang.String] = ExternalVar[SpinButton, java.lang.String]("text", _.getText(), _.setText(_), true)
  val UpdatePolicy: ExternalVar.Aux[SpinButton, org.gnome.gtk.SpinButtonUpdatePolicy] = ExternalVar[SpinButton, org.gnome.gtk.SpinButtonUpdatePolicy]("update-policy", _.getUpdatePolicy(), _.setUpdatePolicy(_), true)
  val Value: ExternalVar.Aux[SpinButton, Double] = ExternalVar[SpinButton, Double]("value", _.getValue(), _.setValue(_), true)
  val WidthChars: ExternalVar.Aux[SpinButton, Int] = ExternalVar[SpinButton, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)
  val Wrap: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("wrap", _.getWrap(), _.setWrap(_), true)
  ()
  extension (v: SpinButton) {
    def unwrap: org.gnome.gtk.SpinButton = v
    export unwrap.onActivate, unwrap.onChangeValue, unwrap.onInput, unwrap.onOutput, unwrap.onValueChanged, unwrap.onWrapped
  }
  def init(v: SpinButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): SpinButton = {
    val res = new org.gnome.gtk.SpinButton()
    res.asInstanceOf[SpinButton]
  }
}