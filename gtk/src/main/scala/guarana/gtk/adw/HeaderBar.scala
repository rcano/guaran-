
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type HeaderBar <: guarana.gtk.Widget  = org.gnome.adw.HeaderBar & guarana.gtk.Widget
object HeaderBar extends VarsMap {
  val CenteringPolicy: ExternalVar.Aux[HeaderBar, org.gnome.adw.CenteringPolicy] = ExternalVar[HeaderBar, org.gnome.adw.CenteringPolicy]("centering-policy", _.getCenteringPolicy(), _.setCenteringPolicy(_), true)
  val DecorationLayout: ExternalVar.Aux[HeaderBar, java.lang.String | Null] = ExternalVar[HeaderBar, java.lang.String | Null]("decoration-layout", _.getDecorationLayout(), _.setDecorationLayout(_), true)
  val ShowBackButton: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("show-back-button", _.getShowBackButton(), _.setShowBackButton(_), true)
  val ShowEndTitleButtons: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("show-end-title-buttons", _.getShowEndTitleButtons(), _.setShowEndTitleButtons(_), true)
  val ShowStartTitleButtons: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("show-start-title-buttons", _.getShowStartTitleButtons(), _.setShowStartTitleButtons(_), true)
  val ShowTitle: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("show-title", _.getShowTitle(), _.setShowTitle(_), true)
  val TitleWidget: ExternalVar.Aux[HeaderBar, guarana.gtk.Widget | Null] = ExternalVar[HeaderBar, guarana.gtk.Widget | Null]("title-widget", _.getTitleWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setTitleWidget(v.?(_.unwrap)), true)

  

  extension (v: HeaderBar) {
    def unwrap: org.gnome.adw.HeaderBar = v

    def centeringPolicy: Var.Aux[org.gnome.adw.CenteringPolicy, v.type] = guarana.gtk.adw.HeaderBar.CenteringPolicy.asInstanceOf[Var.Aux[org.gnome.adw.CenteringPolicy, v.type]]
    def decorationLayout: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.adw.HeaderBar.DecorationLayout.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def showBackButton: Var.Aux[Boolean, v.type] = guarana.gtk.adw.HeaderBar.ShowBackButton.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showEndTitleButtons: Var.Aux[Boolean, v.type] = guarana.gtk.adw.HeaderBar.ShowEndTitleButtons.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showStartTitleButtons: Var.Aux[Boolean, v.type] = guarana.gtk.adw.HeaderBar.ShowStartTitleButtons.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showTitle: Var.Aux[Boolean, v.type] = guarana.gtk.adw.HeaderBar.ShowTitle.asInstanceOf[Var.Aux[Boolean, v.type]]
    def titleWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.HeaderBar.TitleWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]

    

    export unwrap.{
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.adw.HeaderBar): HeaderBar = 
    val res = v.asInstanceOf[HeaderBar]
    
    res

  def init(v: HeaderBar): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): HeaderBar = {
    val res = {
      val res = org.gnome.adw.HeaderBar.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[HeaderBar]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    centeringPolicy: Opt[Binding[org.gnome.adw.CenteringPolicy]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    decorationLayout: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showBackButton: Opt[Binding[Boolean]] = UnsetParam,
    showEndTitleButtons: Opt[Binding[Boolean]] = UnsetParam,
    showStartTitleButtons: Opt[Binding[Boolean]] = UnsetParam,
    showTitle: Opt[Binding[Boolean]] = UnsetParam,
    titleWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[HeaderBar] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.HeaderBar.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(centeringPolicy, res.centeringPolicy := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showBackButton, res.showBackButton := _)
    ifSet(showEndTitleButtons, res.showEndTitleButtons := _)
    ifSet(showStartTitleButtons, res.showStartTitleButtons := _)
    ifSet(showTitle, res.showTitle := _)
    ifSet(titleWidget, res.titleWidget := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        