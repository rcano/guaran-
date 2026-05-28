package guarana
package gtk
import util.*
opaque type HeaderBar <: Widget = org.gnome.gtk.HeaderBar & Widget
object HeaderBar extends VarsMap {
  val DecorationLayout: ExternalVar.Aux[HeaderBar, java.lang.String | Null] = ExternalVar[HeaderBar, java.lang.String | Null]("decoration-layout", _.getDecorationLayout(), _.setDecorationLayout(_), true)
  val ShowTitleButtons: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("show-title-buttons", _.getShowTitleButtons(), _.setShowTitleButtons(_), true)
  val TitleWidget: ExternalVar.Aux[HeaderBar, org.gnome.gtk.Widget | Null] = ExternalVar[HeaderBar, org.gnome.gtk.Widget | Null]("title-widget", _.getTitleWidget(), _.setTitleWidget(_), true)
  val UseNativeControls: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("use-native-controls", _.getUseNativeControls(), _.setUseNativeControls(_), true)
  ()
  extension (v: HeaderBar) {
    def unwrap: org.gnome.gtk.HeaderBar = v
    def decorationLayout: Var.Aux[java.lang.String | Null, v.type] = DecorationLayout.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def showTitleButtons: Var.Aux[Boolean, v.type] = ShowTitleButtons.asInstanceOf[Var.Aux[Boolean, v.type]]
    def titleWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = TitleWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def useNativeControls: Var.Aux[Boolean, v.type] = UseNativeControls.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def _wrap(v: org.gnome.gtk.HeaderBar): HeaderBar = {
    v.asInstanceOf
  }
  def init(v: HeaderBar): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): HeaderBar = {
    val res = new org.gnome.gtk.HeaderBar()
    res.asInstanceOf[HeaderBar]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, decorationLayout: Opt[java.lang.String | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showTitleButtons: Opt[Boolean] = UnsetParam, titleWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, useNativeControls: Opt[Boolean] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, HeaderBar] = {
    val res = uninitialized()
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(decorationLayout, res.decorationLayout := _)
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
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showTitleButtons, res.showTitleButtons := _)
    ifSet(titleWidget, res.titleWidget := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(useNativeControls, res.useNativeControls := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}