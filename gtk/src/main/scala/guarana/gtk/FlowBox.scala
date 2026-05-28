package guarana
package gtk
import util.*
opaque type FlowBox <: Widget = org.gnome.gtk.FlowBox & Widget
object FlowBox extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.FlowBox): FlowBox = {
    v.asInstanceOf
  }
  def init(v: FlowBox): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): FlowBox = {
    val res = new org.gnome.gtk.FlowBox()
    res.asInstanceOf[FlowBox]
  }
  def apply(activateOnSingleClick: Opt[Boolean] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, columnSpacing: Opt[Int] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, homogeneous: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, maxChildrenPerLine: Opt[Int] = UnsetParam, minChildrenPerLine: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, rowSpacing: Opt[Int] = UnsetParam, selectionMode: Opt[org.gnome.gtk.SelectionMode] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, FlowBox] = {
    val res = uninitialized()
    init(res)
    ifSet(activateOnSingleClick, res.activateOnSingleClick := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
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
    ifSet(homogeneous, res.homogeneous := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(maxChildrenPerLine, res.maxChildrenPerLine := _)
    ifSet(minChildrenPerLine, res.minChildrenPerLine := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(rowSpacing, res.rowSpacing := _)
    ifSet(selectionMode, res.selectionMode := _)
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