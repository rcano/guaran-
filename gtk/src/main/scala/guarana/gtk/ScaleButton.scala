package guarana
package gtk
opaque type ScaleButton <: Widget = org.gnome.gtk.ScaleButton & Widget
object ScaleButton {
  val Adjustment: ExternalVar.Aux[ScaleButton, org.gnome.gtk.Adjustment | Null] = ExternalVar[ScaleButton, org.gnome.gtk.Adjustment | Null]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  val HasFrame: ExternalVar.Aux[ScaleButton, Boolean] = ExternalVar[ScaleButton, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val Value: ExternalVar.Aux[ScaleButton, Double] = ExternalVar[ScaleButton, Double]("value", _.getValue(), _.setValue(_), true)
  ()
  extension (v: ScaleButton) {
    def unwrap: org.gnome.gtk.ScaleButton = v
    export unwrap.onPopdown, unwrap.onPopup, unwrap.onValueChanged
  }
}