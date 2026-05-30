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
  def apply(canFocus: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, decorationLayout: Opt[Binding[java.lang.String | Null]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, showTitleButtons: Opt[Binding[Boolean]] = UnsetParam, titleWidget: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, useNativeControls: Opt[Binding[Boolean]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam): ToolkitAction[Toolkit, HeaderBar] = {
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