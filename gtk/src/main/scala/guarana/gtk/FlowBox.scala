package guarana
package gtk
import util.*
opaque type FlowBox <: Widget = org.gnome.gtk.FlowBox & Widget
object FlowBox {
  val ActivateOnSingleClick: ExternalVar.Aux[FlowBox, Boolean] = ExternalVar[FlowBox, Boolean]("activate-on-single-click", _.getActivateOnSingleClick(), _.setActivateOnSingleClick(_), true)
  val ColumnSpacing: ExternalVar.Aux[FlowBox, Int] = ExternalVar[FlowBox, Int]("column-spacing", _.getColumnSpacing(), _.setColumnSpacing(_), true)
  val Homogeneous: ExternalVar.Aux[FlowBox, Boolean] = ExternalVar[FlowBox, Boolean]("homogeneous", _.getHomogeneous(), _.setHomogeneous(_), true)
  val MaxChildrenPerLine: ExternalVar.Aux[FlowBox, Int] = ExternalVar[FlowBox, Int]("max-children-per-line", _.getMaxChildrenPerLine(), _.setMaxChildrenPerLine(_), true)
  val MinChildrenPerLine: ExternalVar.Aux[FlowBox, Int] = ExternalVar[FlowBox, Int]("min-children-per-line", _.getMinChildrenPerLine(), _.setMinChildrenPerLine(_), true)
  val Orientation: ExternalVar.Aux[FlowBox, org.gnome.gtk.Orientation] = ExternalVar[FlowBox, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val RowSpacing: ExternalVar.Aux[FlowBox, Int] = ExternalVar[FlowBox, Int]("row-spacing", _.getRowSpacing(), _.setRowSpacing(_), true)
  val SelectionMode: ExternalVar.Aux[FlowBox, org.gnome.gtk.SelectionMode] = ExternalVar[FlowBox, org.gnome.gtk.SelectionMode]("selection-mode", _.getSelectionMode(), _.setSelectionMode(_), true)
  ()
  extension (v: FlowBox) {
    def unwrap: org.gnome.gtk.FlowBox = v
    def activateOnSingleClick: Var.Aux[Boolean, v.type] = ActivateOnSingleClick.asInstanceOf[Var.Aux[Boolean, v.type]]
    def columnSpacing: Var.Aux[Int, v.type] = ColumnSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    def homogeneous: Var.Aux[Boolean, v.type] = Homogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxChildrenPerLine: Var.Aux[Int, v.type] = MaxChildrenPerLine.asInstanceOf[Var.Aux[Int, v.type]]
    def minChildrenPerLine: Var.Aux[Int, v.type] = MinChildrenPerLine.asInstanceOf[Var.Aux[Int, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def rowSpacing: Var.Aux[Int, v.type] = RowSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    def selectionMode: Var.Aux[org.gnome.gtk.SelectionMode, v.type] = SelectionMode.asInstanceOf[Var.Aux[org.gnome.gtk.SelectionMode, v.type]]
    export unwrap.onActivateCursorChild, unwrap.onChildActivated, unwrap.onMoveCursor, unwrap.onSelectAll, unwrap.onSelectedChildrenChanged, unwrap.onToggleCursorChild, unwrap.onUnselectAll
  }
  def init(v: FlowBox): Unit = {
    Widget.init(v)
  }
  def uninitialized(): FlowBox = {
    val res = new org.gnome.gtk.FlowBox()
    res.asInstanceOf[FlowBox]
  }
  def apply(activateOnSingleClick: Opt[Boolean] = UnsetParam, columnSpacing: Opt[Int] = UnsetParam, homogeneous: Opt[Boolean] = UnsetParam, maxChildrenPerLine: Opt[Int] = UnsetParam, minChildrenPerLine: Opt[Int] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, rowSpacing: Opt[Int] = UnsetParam, selectionMode: Opt[org.gnome.gtk.SelectionMode] = UnsetParam): VarContextAction[FlowBox] = {
    val res = uninitialized()
    init(res)
    ifSet(activateOnSingleClick, res.activateOnSingleClick := _)
    ifSet(columnSpacing, res.columnSpacing := _)
    ifSet(homogeneous, res.homogeneous := _)
    ifSet(maxChildrenPerLine, res.maxChildrenPerLine := _)
    ifSet(minChildrenPerLine, res.minChildrenPerLine := _)
    ifSet(orientation, res.orientation := _)
    ifSet(rowSpacing, res.rowSpacing := _)
    ifSet(selectionMode, res.selectionMode := _)
    res
  }
}