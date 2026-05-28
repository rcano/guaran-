package guarana
package gtk
import util.*
opaque type AppChooserWidget <: Widget = org.gnome.gtk.AppChooserWidget & Widget
object AppChooserWidget extends VarsMap {
  @deprecated("", "") val ShowAll: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-all", _.getShowAll(), _.setShowAll(_), true)
  @deprecated("", "") val ShowDefault: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-default", _.getShowDefault(), _.setShowDefault(_), true)
  @deprecated("", "") val ShowFallback: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-fallback", _.getShowFallback(), _.setShowFallback(_), true)
  @deprecated("", "") val ShowOther: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-other", _.getShowOther(), _.setShowOther(_), true)
  @deprecated("", "") val ShowRecommended: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-recommended", _.getShowRecommended(), _.setShowRecommended(_), true)
  ()
  extension (v: AppChooserWidget) {
    def unwrap: org.gnome.gtk.AppChooserWidget = v
    @deprecated("", "") def showAll: Var.Aux[Boolean, v.type] = ShowAll.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def showDefault: Var.Aux[Boolean, v.type] = ShowDefault.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def showFallback: Var.Aux[Boolean, v.type] = ShowFallback.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def showOther: Var.Aux[Boolean, v.type] = ShowOther.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def showRecommended: Var.Aux[Boolean, v.type] = ShowRecommended.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onApplicationActivated, unwrap.onApplicationSelected
  }
  def _wrap(v: org.gnome.gtk.AppChooserWidget): AppChooserWidget = {
    v.asInstanceOf
  }
  def init(v: AppChooserWidget): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: java.lang.String): AppChooserWidget = {
    val res = new org.gnome.gtk.AppChooserWidget(arg$0)
    res.asInstanceOf[AppChooserWidget]
  }
  def apply(arg$0: java.lang.String, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showAll: Opt[Boolean] = UnsetParam, showDefault: Opt[Boolean] = UnsetParam, showFallback: Opt[Boolean] = UnsetParam, showOther: Opt[Boolean] = UnsetParam, showRecommended: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, AppChooserWidget] = {
    val res = uninitialized(arg$0)
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
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
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showAll, res.showAll := _)
    ifSet(showDefault, res.showDefault := _)
    ifSet(showFallback, res.showFallback := _)
    ifSet(showOther, res.showOther := _)
    ifSet(showRecommended, res.showRecommended := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}