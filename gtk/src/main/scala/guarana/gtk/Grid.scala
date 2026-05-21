package guarana
package gtk
opaque type Grid <: Widget = org.gnome.gtk.Grid & Widget
object Grid {
  val BaselineRow: ExternalVar.Aux[Grid, Int] = ExternalVar[Grid, Int]("baseline-row", _.getBaselineRow(), _.setBaselineRow(_), true)
  val ColumnHomogeneous: ExternalVar.Aux[Grid, Boolean] = ExternalVar[Grid, Boolean]("column-homogeneous", _.getColumnHomogeneous(), _.setColumnHomogeneous(_), true)
  val ColumnSpacing: ExternalVar.Aux[Grid, Int] = ExternalVar[Grid, Int]("column-spacing", _.getColumnSpacing(), _.setColumnSpacing(_), true)
  val RowHomogeneous: ExternalVar.Aux[Grid, Boolean] = ExternalVar[Grid, Boolean]("row-homogeneous", _.getRowHomogeneous(), _.setRowHomogeneous(_), true)
  val RowSpacing: ExternalVar.Aux[Grid, Int] = ExternalVar[Grid, Int]("row-spacing", _.getRowSpacing(), _.setRowSpacing(_), true)
  ()
  extension (v: Grid) {
    def unwrap: org.gnome.gtk.Grid = v
  }
}