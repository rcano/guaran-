
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type ViewSwitcherSidebar <: guarana.gtk.Widget  = org.gnome.adw.ViewSwitcherSidebar & guarana.gtk.Widget
object ViewSwitcherSidebar extends VarsMap {
  val Filter: ExternalVar.Aux[ViewSwitcherSidebar, org.gnome.gtk.Filter | Null] = ExternalVar[ViewSwitcherSidebar, org.gnome.gtk.Filter | Null]("filter", _.getFilter(), _.setFilter(_), true)
  val Mode: ExternalVar.Aux[ViewSwitcherSidebar, org.gnome.adw.SidebarMode] = ExternalVar[ViewSwitcherSidebar, org.gnome.adw.SidebarMode]("mode", _.getMode(), _.setMode(_), true)
  val Placeholder: ExternalVar.Aux[ViewSwitcherSidebar, guarana.gtk.Widget | Null] = ExternalVar[ViewSwitcherSidebar, guarana.gtk.Widget | Null]("placeholder", _.getPlaceholder().?(guarana.gtk.Widget.wrap), (n, v) => n.setPlaceholder(v.?(_.unwrap)), true)
  val Stack: ExternalVar.Aux[ViewSwitcherSidebar, org.gnome.adw.ViewStack | Null] = ExternalVar[ViewSwitcherSidebar, org.gnome.adw.ViewStack | Null]("stack", _.getStack(), _.setStack(_), true)

  

  extension (v: ViewSwitcherSidebar) {
    def unwrap: org.gnome.adw.ViewSwitcherSidebar = v

    def filter: Var.Aux[org.gnome.gtk.Filter | Null, v.type] = guarana.gtk.adw.ViewSwitcherSidebar.Filter.asInstanceOf[Var.Aux[org.gnome.gtk.Filter | Null, v.type]]
    def mode: Var.Aux[org.gnome.adw.SidebarMode, v.type] = guarana.gtk.adw.ViewSwitcherSidebar.Mode.asInstanceOf[Var.Aux[org.gnome.adw.SidebarMode, v.type]]
    def placeholder: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.ViewSwitcherSidebar.Placeholder.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def stack: Var.Aux[org.gnome.adw.ViewStack | Null, v.type] = guarana.gtk.adw.ViewSwitcherSidebar.Stack.asInstanceOf[Var.Aux[org.gnome.adw.ViewStack | Null, v.type]]

    

    export unwrap.{
      onActivated,
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

  def wrap(v: org.gnome.adw.ViewSwitcherSidebar): ViewSwitcherSidebar = 
    val res = v.asInstanceOf[ViewSwitcherSidebar]
    
    res

  def init(v: ViewSwitcherSidebar): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): ViewSwitcherSidebar = {
    val res = {
      val res = org.gnome.adw.ViewSwitcherSidebar.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[ViewSwitcherSidebar]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    filter: Opt[Binding[org.gnome.gtk.Filter | Null]] = UnsetParam,
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
    mode: Opt[Binding[org.gnome.adw.SidebarMode]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    placeholder: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    stack: Opt[Binding[org.gnome.adw.ViewStack | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[ViewSwitcherSidebar] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.ViewSwitcherSidebar.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(filter, res.filter := _)
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
    ifSet(mode, res.mode := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(placeholder, res.placeholder := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(stack, res.stack := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        