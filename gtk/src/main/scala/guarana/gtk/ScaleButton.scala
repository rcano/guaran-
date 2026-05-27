package guarana
package gtk
opaque type ScaleButton <: Widget = org.gnome.gtk.ScaleButton & Widget
object ScaleButton {
  val Adjustment: ExternalVar.Aux[ScaleButton, org.gnome.gtk.Adjustment] = ExternalVar[ScaleButton, org.gnome.gtk.Adjustment]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  val HasFrame: ExternalVar.Aux[ScaleButton, Boolean] = ExternalVar[ScaleButton, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val Orientation: ExternalVar.Aux[ScaleButton, org.gnome.gtk.Orientation] = ExternalVar[ScaleButton, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Value: ExternalVar.Aux[ScaleButton, Double] = ExternalVar[ScaleButton, Double]("value", _.getValue(), _.setValue(_), true)
  ()
  extension (v: ScaleButton) {
    def unwrap: org.gnome.gtk.ScaleButton = v
    export unwrap.onPopdown, unwrap.onPopup, unwrap.onValueChanged
  }
  def init(v: ScaleButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ScaleButton = {
    val res = new org.gnome.gtk.ScaleButton()
    res.asInstanceOf[ScaleButton]
  }
}