package guarana
package gtk
import util.*
opaque type CenterBox <: Widget = org.gnome.gtk.CenterBox & Widget
object CenterBox extends VarsMap {
  val BaselinePosition: ExternalVar.Aux[CenterBox, org.gnome.gtk.BaselinePosition] = ExternalVar[CenterBox, org.gnome.gtk.BaselinePosition]("baseline-position", _.getBaselinePosition(), _.setBaselinePosition(_), true)
  val CenterWidget: ExternalVar.Aux[CenterBox, org.gnome.gtk.Widget | Null] = ExternalVar[CenterBox, org.gnome.gtk.Widget | Null]("center-widget", _.getCenterWidget(), _.setCenterWidget(_), true)
  val EndWidget: ExternalVar.Aux[CenterBox, org.gnome.gtk.Widget | Null] = ExternalVar[CenterBox, org.gnome.gtk.Widget | Null]("end-widget", _.getEndWidget(), _.setEndWidget(_), true)
  val Orientation: ExternalVar.Aux[CenterBox, org.gnome.gtk.Orientation] = ExternalVar[CenterBox, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val ShrinkCenterLast: ExternalVar.Aux[CenterBox, Boolean] = ExternalVar[CenterBox, Boolean]("shrink-center-last", _.getShrinkCenterLast(), _.setShrinkCenterLast(_), true)
  val StartWidget: ExternalVar.Aux[CenterBox, org.gnome.gtk.Widget | Null] = ExternalVar[CenterBox, org.gnome.gtk.Widget | Null]("start-widget", _.getStartWidget(), _.setStartWidget(_), true)
  ()
  extension (v: CenterBox) {
    def unwrap: org.gnome.gtk.CenterBox = v
    def baselinePosition: Var.Aux[org.gnome.gtk.BaselinePosition, v.type] = BaselinePosition.asInstanceOf[Var.Aux[org.gnome.gtk.BaselinePosition, v.type]]
    def centerWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = CenterWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def endWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = EndWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def shrinkCenterLast: Var.Aux[Boolean, v.type] = ShrinkCenterLast.asInstanceOf[Var.Aux[Boolean, v.type]]
    def startWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = StartWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
  }
  def _wrap(v: org.gnome.gtk.CenterBox): CenterBox = {
    v.asInstanceOf
  }
  def init(v: CenterBox): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): CenterBox = {
    val res = new org.gnome.gtk.CenterBox()
    res.asInstanceOf[CenterBox]
  }
  def apply(baselinePosition: Opt[org.gnome.gtk.BaselinePosition] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, centerWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, endWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, shrinkCenterLast: Opt[Boolean] = UnsetParam, startWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, CenterBox] = {
    val res = uninitialized()
    init(res)
    ifSet(baselinePosition, res.baselinePosition := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(centerWidget, res.centerWidget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(endWidget, res.endWidget := _)
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
    ifSet(sensitive, res.sensitive := _)
    ifSet(shrinkCenterLast, res.shrinkCenterLast := _)
    ifSet(startWidget, res.startWidget := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}