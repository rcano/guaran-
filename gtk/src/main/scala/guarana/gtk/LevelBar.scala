package guarana
package gtk
import util.*
opaque type LevelBar <: Widget = org.gnome.gtk.LevelBar & Widget
object LevelBar {
  val Inverted: ExternalVar.Aux[LevelBar, Boolean] = ExternalVar[LevelBar, Boolean]("inverted", _.getInverted(), _.setInverted(_), true)
  val MaxValue: ExternalVar.Aux[LevelBar, Double] = ExternalVar[LevelBar, Double]("max-value", _.getMaxValue(), _.setMaxValue(_), true)
  val MinValue: ExternalVar.Aux[LevelBar, Double] = ExternalVar[LevelBar, Double]("min-value", _.getMinValue(), _.setMinValue(_), true)
  val Mode: ExternalVar.Aux[LevelBar, org.gnome.gtk.LevelBarMode] = ExternalVar[LevelBar, org.gnome.gtk.LevelBarMode]("mode", _.getMode(), _.setMode(_), true)
  val Orientation: ExternalVar.Aux[LevelBar, org.gnome.gtk.Orientation] = ExternalVar[LevelBar, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Value: ExternalVar.Aux[LevelBar, Double] = ExternalVar[LevelBar, Double]("value", _.getValue(), _.setValue(_), true)
  ()
  extension (v: LevelBar) {
    def unwrap: org.gnome.gtk.LevelBar = v
    def inverted: Var.Aux[Boolean, v.type] = Inverted.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxValue: Var.Aux[Double, v.type] = MaxValue.asInstanceOf[Var.Aux[Double, v.type]]
    def minValue: Var.Aux[Double, v.type] = MinValue.asInstanceOf[Var.Aux[Double, v.type]]
    def mode: Var.Aux[org.gnome.gtk.LevelBarMode, v.type] = Mode.asInstanceOf[Var.Aux[org.gnome.gtk.LevelBarMode, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def value: Var.Aux[Double, v.type] = Value.asInstanceOf[Var.Aux[Double, v.type]]
    export unwrap.onOffsetChanged
  }
  def init(v: LevelBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): LevelBar = {
    val res = new org.gnome.gtk.LevelBar()
    res.asInstanceOf[LevelBar]
  }
  def apply(inverted: Opt[Boolean] = UnsetParam, maxValue: Opt[Double] = UnsetParam, minValue: Opt[Double] = UnsetParam, mode: Opt[org.gnome.gtk.LevelBarMode] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, value: Opt[Double] = UnsetParam): VarContextAction[LevelBar] = {
    val res = uninitialized()
    init(res)
    ifSet(inverted, res.inverted := _)
    ifSet(maxValue, res.maxValue := _)
    ifSet(minValue, res.minValue := _)
    ifSet(mode, res.mode := _)
    ifSet(orientation, res.orientation := _)
    ifSet(value, res.value := _)
    res
  }
}