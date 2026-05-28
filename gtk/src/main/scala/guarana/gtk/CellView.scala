package guarana
package gtk
import util.*
opaque type CellView <: Widget = org.gnome.gtk.CellView & Widget
object CellView extends VarsMap {
  @deprecated("", "") val DisplayedRow: ExternalVar.Aux[CellView, org.gnome.gtk.TreePath | Null] = ExternalVar[CellView, org.gnome.gtk.TreePath | Null]("displayed-row", _.getDisplayedRow(), _.setDisplayedRow(_), true)
  @deprecated("", "") val DrawSensitive: ExternalVar.Aux[CellView, Boolean] = ExternalVar[CellView, Boolean]("draw-sensitive", _.getDrawSensitive(), _.setDrawSensitive(_), true)
  @deprecated("", "") val FitModel: ExternalVar.Aux[CellView, Boolean] = ExternalVar[CellView, Boolean]("fit-model", _.getFitModel(), _.setFitModel(_), true)
  @deprecated("", "") val Model: ExternalVar.Aux[CellView, org.gnome.gtk.TreeModel | Null] = ExternalVar[CellView, org.gnome.gtk.TreeModel | Null]("model", _.getModel(), _.setModel(_), true)
  val Orientation: ExternalVar.Aux[CellView, org.gnome.gtk.Orientation] = ExternalVar[CellView, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  ()
  extension (v: CellView) {
    def unwrap: org.gnome.gtk.CellView = v
    @deprecated("", "") def displayedRow: Var.Aux[org.gnome.gtk.TreePath | Null, v.type] = DisplayedRow.asInstanceOf[Var.Aux[org.gnome.gtk.TreePath | Null, v.type]]
    @deprecated("", "") def drawSensitive: Var.Aux[Boolean, v.type] = DrawSensitive.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def fitModel: Var.Aux[Boolean, v.type] = FitModel.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def model: Var.Aux[org.gnome.gtk.TreeModel | Null, v.type] = Model.asInstanceOf[Var.Aux[org.gnome.gtk.TreeModel | Null, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
  }
  def _wrap(v: org.gnome.gtk.CellView): CellView = {
    v.asInstanceOf
  }
  def init(v: CellView): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): CellView = {
    val res = new org.gnome.gtk.CellView()
    res.asInstanceOf[CellView]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, displayedRow: Opt[org.gnome.gtk.TreePath | Null] = UnsetParam, drawSensitive: Opt[Boolean] = UnsetParam, fitModel: Opt[Boolean] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, model: Opt[org.gnome.gtk.TreeModel | Null] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, CellView] = {
    val res = uninitialized()
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(displayedRow, res.displayedRow := _)
    ifSet(drawSensitive, res.drawSensitive := _)
    ifSet(fitModel, res.fitModel := _)
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
    ifSet(model, res.model := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
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