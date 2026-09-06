
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type Sidebar <: guarana.gtk.Widget  = org.gnome.adw.Sidebar & guarana.gtk.Widget
object Sidebar extends VarsMap {
  val DropPreload: ExternalVar.Aux[Sidebar, Boolean] = ExternalVar[Sidebar, Boolean]("drop-preload", _.getDropPreload(), _.setDropPreload(_), true)
  val Filter: ExternalVar.Aux[Sidebar, org.gnome.gtk.Filter | Null] = ExternalVar[Sidebar, org.gnome.gtk.Filter | Null]("filter", _.getFilter(), _.setFilter(_), true)
  val MenuModel: ExternalVar.Aux[Sidebar, org.gnome.gio.MenuModel | Null] = ExternalVar[Sidebar, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  val Mode: ExternalVar.Aux[Sidebar, org.gnome.adw.SidebarMode] = ExternalVar[Sidebar, org.gnome.adw.SidebarMode]("mode", _.getMode(), _.setMode(_), true)
  val Placeholder: ExternalVar.Aux[Sidebar, guarana.gtk.Widget | Null] = ExternalVar[Sidebar, guarana.gtk.Widget | Null]("placeholder", _.getPlaceholder().?(guarana.gtk.Widget.wrap), (n, v) => n.setPlaceholder(v.?(_.unwrap)), true)
  val Selected: ExternalVar.Aux[Sidebar, Int] = ExternalVar[Sidebar, Int]("selected", _.getSelected(), _.setSelected(_), true)

  

  extension (v: Sidebar) {
    def unwrap: org.gnome.adw.Sidebar = v

    def dropPreload: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Sidebar.DropPreload.asInstanceOf[Var.Aux[Boolean, v.type]]
    def filter: Var.Aux[org.gnome.gtk.Filter | Null, v.type] = guarana.gtk.adw.Sidebar.Filter.asInstanceOf[Var.Aux[org.gnome.gtk.Filter | Null, v.type]]
    def menuModel: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = guarana.gtk.adw.Sidebar.MenuModel.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def mode: Var.Aux[org.gnome.adw.SidebarMode, v.type] = guarana.gtk.adw.Sidebar.Mode.asInstanceOf[Var.Aux[org.gnome.adw.SidebarMode, v.type]]
    def placeholder: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.Sidebar.Placeholder.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def selected: Var.Aux[Int, v.type] = guarana.gtk.adw.Sidebar.Selected.asInstanceOf[Var.Aux[Int, v.type]]

    

    export unwrap.{
      onActivated,
      onDestroy,
      onDirectionChanged,
      onDrop,
      onDropEnter,
      onDropValueLoaded,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onSetupMenu,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.adw.Sidebar): Sidebar = 
    val res = v.asInstanceOf[Sidebar]
    
    res

  def init(v: Sidebar): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Sidebar = {
    val res = {
      val res = org.gnome.adw.Sidebar.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[Sidebar]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    dropPreload: Opt[Binding[Boolean]] = UnsetParam,
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
    menuModel: Opt[Binding[org.gnome.gio.MenuModel | Null]] = UnsetParam,
    mode: Opt[Binding[org.gnome.adw.SidebarMode]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    placeholder: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    selected: Opt[Binding[Int]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Sidebar] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.Sidebar.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(dropPreload, res.dropPreload := _)
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
    ifSet(menuModel, res.menuModel := _)
    ifSet(mode, res.mode := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
    ifSet(placeholder, res.placeholder := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(selected, res.selected := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        