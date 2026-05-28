package guarana
package gtk
import util.*
opaque type Paned <: Widget = org.gnome.gtk.Paned & Widget
object Paned extends VarsMap {
  val EndChild: ExternalVar.Aux[Paned, org.gnome.gtk.Widget | Null] = ExternalVar[Paned, org.gnome.gtk.Widget | Null]("end-child", _.getEndChild(), _.setEndChild(_), true)
  val Orientation: ExternalVar.Aux[Paned, org.gnome.gtk.Orientation] = ExternalVar[Paned, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Position: ExternalVar.Aux[Paned, Int] = ExternalVar[Paned, Int]("position", _.getPosition(), _.setPosition(_), true)
  val ResizeEndChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("resize-end-child", _.getResizeEndChild(), _.setResizeEndChild(_), true)
  val ResizeStartChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("resize-start-child", _.getResizeStartChild(), _.setResizeStartChild(_), true)
  val ShrinkEndChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("shrink-end-child", _.getShrinkEndChild(), _.setShrinkEndChild(_), true)
  val ShrinkStartChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("shrink-start-child", _.getShrinkStartChild(), _.setShrinkStartChild(_), true)
  val StartChild: ExternalVar.Aux[Paned, org.gnome.gtk.Widget | Null] = ExternalVar[Paned, org.gnome.gtk.Widget | Null]("start-child", _.getStartChild(), _.setStartChild(_), true)
  val WideHandle: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("wide-handle", _.getWideHandle(), _.setWideHandle(_), true)
  ()
  extension (v: Paned) {
    def unwrap: org.gnome.gtk.Paned = v
    def endChild: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = EndChild.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def position: Var.Aux[Int, v.type] = Position.asInstanceOf[Var.Aux[Int, v.type]]
    def resizeEndChild: Var.Aux[Boolean, v.type] = ResizeEndChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def resizeStartChild: Var.Aux[Boolean, v.type] = ResizeStartChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def shrinkEndChild: Var.Aux[Boolean, v.type] = ShrinkEndChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def shrinkStartChild: Var.Aux[Boolean, v.type] = ShrinkStartChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def startChild: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = StartChild.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def wideHandle: Var.Aux[Boolean, v.type] = WideHandle.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onAcceptPosition, unwrap.onCancelPosition, unwrap.onCycleChildFocus, unwrap.onCycleHandleFocus, unwrap.onMoveHandle, unwrap.onToggleHandleFocus
  }
  def _wrap(v: org.gnome.gtk.Paned): Paned = {
    v.asInstanceOf
  }
  def init(v: Paned): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gtk.Orientation): Paned = {
    val res = new org.gnome.gtk.Paned(arg$0)
    res.asInstanceOf[Paned]
  }
  def apply(arg$0: org.gnome.gtk.Orientation, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, endChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, position: Opt[Int] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, resizeEndChild: Opt[Boolean] = UnsetParam, resizeStartChild: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, shrinkEndChild: Opt[Boolean] = UnsetParam, shrinkStartChild: Opt[Boolean] = UnsetParam, startChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, wideHandle: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, Paned] = {
    val res = uninitialized(arg$0)
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(endChild, res.endChild := _)
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
    ifSet(position, res.position := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(resizeEndChild, res.resizeEndChild := _)
    ifSet(resizeStartChild, res.resizeStartChild := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(shrinkEndChild, res.shrinkEndChild := _)
    ifSet(shrinkStartChild, res.shrinkStartChild := _)
    ifSet(startChild, res.startChild := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(wideHandle, res.wideHandle := _)
    res
  }
}