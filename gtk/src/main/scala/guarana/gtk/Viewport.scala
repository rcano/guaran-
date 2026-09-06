
package guarana.gtk

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type Viewport <: guarana.gtk.Widget  = org.gnome.gtk.Viewport & guarana.gtk.Widget
object Viewport extends VarsMap {
  val Child: ExternalVar.Aux[Viewport, guarana.gtk.Widget | Null] = ExternalVar[Viewport, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val Hadjustment: ExternalVar.Aux[Viewport, org.gnome.gtk.Adjustment | Null] = ExternalVar[Viewport, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  val HscrollPolicy: ExternalVar.Aux[Viewport, org.gnome.gtk.ScrollablePolicy] = ExternalVar[Viewport, org.gnome.gtk.ScrollablePolicy]("hscroll-policy", _.getHscrollPolicy(), _.setHscrollPolicy(_), true)
  val ScrollToFocus: ExternalVar.Aux[Viewport, Boolean] = ExternalVar[Viewport, Boolean]("scroll-to-focus", _.getScrollToFocus(), _.setScrollToFocus(_), true)
  val Vadjustment: ExternalVar.Aux[Viewport, org.gnome.gtk.Adjustment | Null] = ExternalVar[Viewport, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  val VscrollPolicy: ExternalVar.Aux[Viewport, org.gnome.gtk.ScrollablePolicy] = ExternalVar[Viewport, org.gnome.gtk.ScrollablePolicy]("vscroll-policy", _.getVscrollPolicy(), _.setVscrollPolicy(_), true)

  

  extension (v: Viewport) {
    def unwrap: org.gnome.gtk.Viewport = v

    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Viewport.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def hadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = guarana.gtk.Viewport.Hadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def hscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = guarana.gtk.Viewport.HscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    def scrollToFocus: Var.Aux[Boolean, v.type] = guarana.gtk.Viewport.ScrollToFocus.asInstanceOf[Var.Aux[Boolean, v.type]]
    def vadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = guarana.gtk.Viewport.Vadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def vscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = guarana.gtk.Viewport.VscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]

    

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

  def wrap(v: org.gnome.gtk.Viewport): Viewport = 
    val res = v.asInstanceOf[Viewport]
    
    res

  def init(v: Viewport): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Viewport = {
    val res = {
      val res = org.gnome.gtk.Viewport.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[Viewport]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    hadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    hscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam,
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
    scrollToFocus: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    vadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    vscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam
  ): VarContextAction[Viewport] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.Viewport.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
    ifSet(parent, res.parent := _)
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
        