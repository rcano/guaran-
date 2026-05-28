package guarana
package gtk
import util.*
opaque type Grid <: Widget = org.gnome.gtk.Grid & Widget
object Grid extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.Grid): Grid = {
    v.asInstanceOf
  }
  def init(v: Grid): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): Grid = {
    val res = new org.gnome.gtk.Grid()
    res.asInstanceOf[Grid]
  }
  def apply(baselineRow: Opt[Int] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, columnHomogeneous: Opt[Boolean] = UnsetParam, columnSpacing: Opt[Int] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, rowHomogeneous: Opt[Boolean] = UnsetParam, rowSpacing: Opt[Int] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, Grid] = {
    val res = uninitialized()
    init(res)
    ifSet(baselineRow, res.baselineRow := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(columnHomogeneous, res.columnHomogeneous := _)
    ifSet(columnSpacing, res.columnSpacing := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(rowHomogeneous, res.rowHomogeneous := _)
    ifSet(rowSpacing, res.rowSpacing := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}