
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type ToolbarView <: guarana.gtk.Widget  = org.gnome.adw.ToolbarView & guarana.gtk.Widget
object ToolbarView extends VarsMap {
  val BottomBarStyle: ExternalVar.Aux[ToolbarView, org.gnome.adw.ToolbarStyle] = ExternalVar[ToolbarView, org.gnome.adw.ToolbarStyle]("bottom-bar-style", _.getBottomBarStyle(), _.setBottomBarStyle(_), true)
  val Content: ExternalVar.Aux[ToolbarView, guarana.gtk.Widget | Null] = ExternalVar[ToolbarView, guarana.gtk.Widget | Null]("content", _.getContent().?(guarana.gtk.Widget.wrap), (n, v) => n.setContent(v.?(_.unwrap)), true)
  val ExtendContentToBottomEdge: ExternalVar.Aux[ToolbarView, Boolean] = ExternalVar[ToolbarView, Boolean]("extend-content-to-bottom-edge", _.getExtendContentToBottomEdge(), _.setExtendContentToBottomEdge(_), true)
  val ExtendContentToTopEdge: ExternalVar.Aux[ToolbarView, Boolean] = ExternalVar[ToolbarView, Boolean]("extend-content-to-top-edge", _.getExtendContentToTopEdge(), _.setExtendContentToTopEdge(_), true)
  val RevealBottomBars: ExternalVar.Aux[ToolbarView, Boolean] = ExternalVar[ToolbarView, Boolean]("reveal-bottom-bars", _.getRevealBottomBars(), _.setRevealBottomBars(_), true)
  val RevealTopBars: ExternalVar.Aux[ToolbarView, Boolean] = ExternalVar[ToolbarView, Boolean]("reveal-top-bars", _.getRevealTopBars(), _.setRevealTopBars(_), true)
  val TopBarStyle: ExternalVar.Aux[ToolbarView, org.gnome.adw.ToolbarStyle] = ExternalVar[ToolbarView, org.gnome.adw.ToolbarStyle]("top-bar-style", _.getTopBarStyle(), _.setTopBarStyle(_), true)

  

  extension (v: ToolbarView) {
    def unwrap: org.gnome.adw.ToolbarView = v

    def bottomBarStyle: Var.Aux[org.gnome.adw.ToolbarStyle, v.type] = guarana.gtk.adw.ToolbarView.BottomBarStyle.asInstanceOf[Var.Aux[org.gnome.adw.ToolbarStyle, v.type]]
    def content: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.ToolbarView.Content.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def extendContentToBottomEdge: Var.Aux[Boolean, v.type] = guarana.gtk.adw.ToolbarView.ExtendContentToBottomEdge.asInstanceOf[Var.Aux[Boolean, v.type]]
    def extendContentToTopEdge: Var.Aux[Boolean, v.type] = guarana.gtk.adw.ToolbarView.ExtendContentToTopEdge.asInstanceOf[Var.Aux[Boolean, v.type]]
    def revealBottomBars: Var.Aux[Boolean, v.type] = guarana.gtk.adw.ToolbarView.RevealBottomBars.asInstanceOf[Var.Aux[Boolean, v.type]]
    def revealTopBars: Var.Aux[Boolean, v.type] = guarana.gtk.adw.ToolbarView.RevealTopBars.asInstanceOf[Var.Aux[Boolean, v.type]]
    def topBarStyle: Var.Aux[org.gnome.adw.ToolbarStyle, v.type] = guarana.gtk.adw.ToolbarView.TopBarStyle.asInstanceOf[Var.Aux[org.gnome.adw.ToolbarStyle, v.type]]

    

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

  def wrap(v: org.gnome.adw.ToolbarView): ToolbarView = 
    val res = v.asInstanceOf[ToolbarView]
    
    res

  def init(v: ToolbarView): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): ToolbarView = {
    val res = {
      val res = org.gnome.adw.ToolbarView.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[ToolbarView]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    bottomBarStyle: Opt[Binding[org.gnome.adw.ToolbarStyle]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    content: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    extendContentToBottomEdge: Opt[Binding[Boolean]] = UnsetParam,
    extendContentToTopEdge: Opt[Binding[Boolean]] = UnsetParam,
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
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    revealBottomBars: Opt[Binding[Boolean]] = UnsetParam,
    revealTopBars: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    topBarStyle: Opt[Binding[org.gnome.adw.ToolbarStyle]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[ToolbarView] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.ToolbarView.init(res)
    ifSet(bottomBarStyle, res.bottomBarStyle := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(content, res.content := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(extendContentToBottomEdge, res.extendContentToBottomEdge := _)
    ifSet(extendContentToTopEdge, res.extendContentToTopEdge := _)
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
    ifSet(revealBottomBars, res.revealBottomBars := _)
    ifSet(revealTopBars, res.revealTopBars := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(topBarStyle, res.topBarStyle := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        