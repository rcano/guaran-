package guarana
package gtk
import util.*
opaque type Grid <: Widget = org.gnome.gtk.Grid & Widget
object Grid {
  val BaselineRow: ExternalVar.Aux[Grid, Int] = ExternalVar[Grid, Int]("baseline-row", _.getBaselineRow(), _.setBaselineRow(_), true)
  val ColumnHomogeneous: ExternalVar.Aux[Grid, Boolean] = ExternalVar[Grid, Boolean]("column-homogeneous", _.getColumnHomogeneous(), _.setColumnHomogeneous(_), true)
  val ColumnSpacing: ExternalVar.Aux[Grid, Int] = ExternalVar[Grid, Int]("column-spacing", _.getColumnSpacing(), _.setColumnSpacing(_), true)
  val Orientation: ExternalVar.Aux[Grid, org.gnome.gtk.Orientation] = ExternalVar[Grid, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val RowHomogeneous: ExternalVar.Aux[Grid, Boolean] = ExternalVar[Grid, Boolean]("row-homogeneous", _.getRowHomogeneous(), _.setRowHomogeneous(_), true)
  val RowSpacing: ExternalVar.Aux[Grid, Int] = ExternalVar[Grid, Int]("row-spacing", _.getRowSpacing(), _.setRowSpacing(_), true)
  ()
  extension (v: Grid) {
    def unwrap: org.gnome.gtk.Grid = v
    def baselineRow: Var.Aux[Int, v.type] = BaselineRow.asInstanceOf[Var.Aux[Int, v.type]]
    def columnHomogeneous: Var.Aux[Boolean, v.type] = ColumnHomogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def columnSpacing: Var.Aux[Int, v.type] = ColumnSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def rowHomogeneous: Var.Aux[Boolean, v.type] = RowHomogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def rowSpacing: Var.Aux[Int, v.type] = RowSpacing.asInstanceOf[Var.Aux[Int, v.type]]
  }
  def init(v: Grid): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Grid = {
    val res = new org.gnome.gtk.Grid()
    res.asInstanceOf[Grid]
  }
  def apply(baselineRow: Opt[Int] = UnsetParam, columnHomogeneous: Opt[Boolean] = UnsetParam, columnSpacing: Opt[Int] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, rowHomogeneous: Opt[Boolean] = UnsetParam, rowSpacing: Opt[Int] = UnsetParam): VarContextAction[Grid] = {
    val res = uninitialized()
    init(res)
    ifSet(baselineRow, res.baselineRow := _)
    ifSet(columnHomogeneous, res.columnHomogeneous := _)
    ifSet(columnSpacing, res.columnSpacing := _)
    ifSet(orientation, res.orientation := _)
    ifSet(rowHomogeneous, res.rowHomogeneous := _)
    ifSet(rowSpacing, res.rowSpacing := _)
    res
  }
}