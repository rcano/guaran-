package guarana
package gtk
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
    export unwrap.onActivateCursorChild, unwrap.onChildActivated, unwrap.onMoveCursor, unwrap.onSelectAll, unwrap.onSelectedChildrenChanged, unwrap.onToggleCursorChild, unwrap.onUnselectAll
  }
  def init(v: FlowBox): Unit = {
    Widget.init(v)
  }
  def uninitialized(): FlowBox = {
    val res = new org.gnome.gtk.FlowBox()
    res.asInstanceOf[FlowBox]
  }
}