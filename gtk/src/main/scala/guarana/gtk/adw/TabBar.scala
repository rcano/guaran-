
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type TabBar <: guarana.gtk.Widget  = org.gnome.adw.TabBar & guarana.gtk.Widget
object TabBar extends VarsMap {
  val Autohide: ExternalVar.Aux[TabBar, Boolean] = ExternalVar[TabBar, Boolean]("autohide", _.getAutohide(), _.setAutohide(_), true)
  val EndActionWidget: ExternalVar.Aux[TabBar, guarana.gtk.Widget | Null] = ExternalVar[TabBar, guarana.gtk.Widget | Null]("end-action-widget", _.getEndActionWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setEndActionWidget(v.?(_.unwrap)), true)
  val ExpandTabs: ExternalVar.Aux[TabBar, Boolean] = ExternalVar[TabBar, Boolean]("expand-tabs", _.getExpandTabs(), _.setExpandTabs(_), true)
  val ExtraDragPreload: ExternalVar.Aux[TabBar, Boolean] = ExternalVar[TabBar, Boolean]("extra-drag-preload", _.getExtraDragPreload(), _.setExtraDragPreload(_), true)
  val Inverted: ExternalVar.Aux[TabBar, Boolean] = ExternalVar[TabBar, Boolean]("inverted", _.getInverted(), _.setInverted(_), true)
  val StartActionWidget: ExternalVar.Aux[TabBar, guarana.gtk.Widget | Null] = ExternalVar[TabBar, guarana.gtk.Widget | Null]("start-action-widget", _.getStartActionWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setStartActionWidget(v.?(_.unwrap)), true)
  val View: ExternalVar.Aux[TabBar, org.gnome.adw.TabView | Null] = ExternalVar[TabBar, org.gnome.adw.TabView | Null]("view", _.getView(), _.setView(_), true)

  

  extension (v: TabBar) {
    def unwrap: org.gnome.adw.TabBar = v

    def autohide: Var.Aux[Boolean, v.type] = guarana.gtk.adw.TabBar.Autohide.asInstanceOf[Var.Aux[Boolean, v.type]]
    def endActionWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.TabBar.EndActionWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def expandTabs: Var.Aux[Boolean, v.type] = guarana.gtk.adw.TabBar.ExpandTabs.asInstanceOf[Var.Aux[Boolean, v.type]]
    def extraDragPreload: Var.Aux[Boolean, v.type] = guarana.gtk.adw.TabBar.ExtraDragPreload.asInstanceOf[Var.Aux[Boolean, v.type]]
    def inverted: Var.Aux[Boolean, v.type] = guarana.gtk.adw.TabBar.Inverted.asInstanceOf[Var.Aux[Boolean, v.type]]
    def startActionWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.TabBar.StartActionWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def view: Var.Aux[org.gnome.adw.TabView | Null, v.type] = guarana.gtk.adw.TabBar.View.asInstanceOf[Var.Aux[org.gnome.adw.TabView | Null, v.type]]

    

    export unwrap.{
      onDestroy,
      onDirectionChanged,
      onExtraDragDrop,
      onExtraDragValue,
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

  def wrap(v: org.gnome.adw.TabBar): TabBar = 
    val res = v.asInstanceOf[TabBar]
    
    res

  def init(v: TabBar): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): TabBar = {
    val res = {
      val res = org.gnome.adw.TabBar.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[TabBar]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    autohide: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    endActionWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    expandTabs: Opt[Binding[Boolean]] = UnsetParam,
    extraDragPreload: Opt[Binding[Boolean]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    inverted: Opt[Binding[Boolean]] = UnsetParam,
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
    startActionWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    view: Opt[Binding[org.gnome.adw.TabView | Null]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[TabBar] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.TabBar.init(res)
    ifSet(autohide, res.autohide := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(endActionWidget, res.endActionWidget := _)
    ifSet(expandTabs, res.expandTabs := _)
    ifSet(extraDragPreload, res.extraDragPreload := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(inverted, res.inverted := _)
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
    ifSet(startActionWidget, res.startActionWidget := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(view, res.view := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        