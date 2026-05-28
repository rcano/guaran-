package guarana
package gtk
import util.*
opaque type ScaleButton <: Widget = org.gnome.gtk.ScaleButton & Widget
object ScaleButton {
  val Adjustment: ExternalVar.Aux[ScaleButton, org.gnome.gtk.Adjustment] = ExternalVar[ScaleButton, org.gnome.gtk.Adjustment]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  val HasFrame: ExternalVar.Aux[ScaleButton, Boolean] = ExternalVar[ScaleButton, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val Orientation: ExternalVar.Aux[ScaleButton, org.gnome.gtk.Orientation] = ExternalVar[ScaleButton, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Value: ExternalVar.Aux[ScaleButton, Double] = ExternalVar[ScaleButton, Double]("value", _.getValue(), _.setValue(_), true)
  ()
  extension (v: ScaleButton) {
    def unwrap: org.gnome.gtk.ScaleButton = v
    def adjustment: Var.Aux[org.gnome.gtk.Adjustment, v.type] = Adjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment, v.type]]
    def hasFrame: Var.Aux[Boolean, v.type] = HasFrame.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def value: Var.Aux[Double, v.type] = Value.asInstanceOf[Var.Aux[Double, v.type]]
    export unwrap.onPopdown, unwrap.onPopup, unwrap.onValueChanged
  }
  def init(v: ScaleButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ScaleButton = {
    val res = new org.gnome.gtk.ScaleButton()
    res.asInstanceOf[ScaleButton]
  }
  def apply(adjustment: Opt[org.gnome.gtk.Adjustment] = UnsetParam, hasFrame: Opt[Boolean] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, value: Opt[Double] = UnsetParam): VarContextAction[ScaleButton] = {
    val res = uninitialized()
    init(res)
    ifSet(adjustment, res.adjustment := _)
    ifSet(hasFrame, res.hasFrame := _)
    ifSet(orientation, res.orientation := _)
    ifSet(value, res.value := _)
    res
  }
}