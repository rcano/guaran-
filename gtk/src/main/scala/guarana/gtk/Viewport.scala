package guarana
package gtk
import util.*
opaque type Viewport <: Widget = org.gnome.gtk.Viewport & Widget
object Viewport extends VarsMap {
  val Child: ExternalVar.Aux[Viewport, org.gnome.gtk.Widget | Null] = ExternalVar[Viewport, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Hadjustment: ExternalVar.Aux[Viewport, org.gnome.gtk.Adjustment | Null] = ExternalVar[Viewport, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  val HscrollPolicy: ExternalVar.Aux[Viewport, org.gnome.gtk.ScrollablePolicy] = ExternalVar[Viewport, org.gnome.gtk.ScrollablePolicy]("hscroll-policy", _.getHscrollPolicy(), _.setHscrollPolicy(_), true)
  val ScrollToFocus: ExternalVar.Aux[Viewport, Boolean] = ExternalVar[Viewport, Boolean]("scroll-to-focus", _.getScrollToFocus(), _.setScrollToFocus(_), true)
  val Vadjustment: ExternalVar.Aux[Viewport, org.gnome.gtk.Adjustment | Null] = ExternalVar[Viewport, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  val VscrollPolicy: ExternalVar.Aux[Viewport, org.gnome.gtk.ScrollablePolicy] = ExternalVar[Viewport, org.gnome.gtk.ScrollablePolicy]("vscroll-policy", _.getVscrollPolicy(), _.setVscrollPolicy(_), true)
  ()
  extension (v: Viewport) {
    def unwrap: org.gnome.gtk.Viewport = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def hadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = Hadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def hscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = HscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    def scrollToFocus: Var.Aux[Boolean, v.type] = ScrollToFocus.asInstanceOf[Var.Aux[Boolean, v.type]]
    def vadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = Vadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def vscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = VscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
  }
  def _wrap(v: org.gnome.gtk.Viewport): Viewport = {
    v.asInstanceOf
  }
  def init(v: Viewport): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gtk.Adjustment | Null, arg$1: org.gnome.gtk.Adjustment | Null): Viewport = {
    val res = new org.gnome.gtk.Viewport(arg$0, arg$1)
    res.asInstanceOf[Viewport]
  }
  def apply(arg$0: org.gnome.gtk.Adjustment | Null, arg$1: org.gnome.gtk.Adjustment | Null, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, hadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, hscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, scrollToFocus: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, vadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, vscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam): ToolkitAction[Toolkit, Viewport] = {
    val res = uninitialized(arg$0, arg$1)
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(hadjustment, res.hadjustment := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(hscrollPolicy, res.hscrollPolicy := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(scrollToFocus, res.scrollToFocus := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(vadjustment, res.vadjustment := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(vscrollPolicy, res.vscrollPolicy := _)
    res
  }
}