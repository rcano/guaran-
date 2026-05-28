package guarana
package gtk
import util.*
opaque type Box <: Widget = org.gnome.gtk.Box & Widget
object Box {
  val BaselineChild: ExternalVar.Aux[Box, Int] = ExternalVar[Box, Int]("baseline-child", _.getBaselineChild(), _.setBaselineChild(_), true)
  val BaselinePosition: ExternalVar.Aux[Box, org.gnome.gtk.BaselinePosition] = ExternalVar[Box, org.gnome.gtk.BaselinePosition]("baseline-position", _.getBaselinePosition(), _.setBaselinePosition(_), true)
  val Homogeneous: ExternalVar.Aux[Box, Boolean] = ExternalVar[Box, Boolean]("homogeneous", _.getHomogeneous(), _.setHomogeneous(_), true)
  val Orientation: ExternalVar.Aux[Box, org.gnome.gtk.Orientation] = ExternalVar[Box, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Spacing: ExternalVar.Aux[Box, Int] = ExternalVar[Box, Int]("spacing", _.getSpacing(), _.setSpacing(_), true)
  ()
  extension (v: Box) {
    def unwrap: org.gnome.gtk.Box = v
    def baselineChild: Var.Aux[Int, v.type] = BaselineChild.asInstanceOf[Var.Aux[Int, v.type]]
    def baselinePosition: Var.Aux[org.gnome.gtk.BaselinePosition, v.type] = BaselinePosition.asInstanceOf[Var.Aux[org.gnome.gtk.BaselinePosition, v.type]]
    def homogeneous: Var.Aux[Boolean, v.type] = Homogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def spacing: Var.Aux[Int, v.type] = Spacing.asInstanceOf[Var.Aux[Int, v.type]]
  }
  def init(v: Box): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Box = {
    val res = new org.gnome.gtk.Box()
    res.asInstanceOf[Box]
  }
  def apply(baselineChild: Opt[Int] = UnsetParam, baselinePosition: Opt[org.gnome.gtk.BaselinePosition] = UnsetParam, homogeneous: Opt[Boolean] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, spacing: Opt[Int] = UnsetParam): VarContextAction[Box] = {
    val res = uninitialized()
    init(res)
    ifSet(baselineChild, res.baselineChild := _)
    ifSet(baselinePosition, res.baselinePosition := _)
    ifSet(homogeneous, res.homogeneous := _)
    ifSet(orientation, res.orientation := _)
    ifSet(spacing, res.spacing := _)
    res
  }
}