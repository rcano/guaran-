
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type TabView <: guarana.gtk.Widget  = org.gnome.adw.TabView & guarana.gtk.Widget
object TabView extends VarsMap {
  val DefaultIcon: ExternalVar.Aux[TabView, org.gnome.gio.Icon] = ExternalVar[TabView, org.gnome.gio.Icon]("default-icon", _.getDefaultIcon(), _.setDefaultIcon(_), true)
  val MenuModel: ExternalVar.Aux[TabView, org.gnome.gio.MenuModel | Null] = ExternalVar[TabView, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  val Shortcuts: ExternalVar.Aux[TabView, java.util.Set[org.gnome.adw.TabViewShortcuts]] = ExternalVar[TabView, java.util.Set[org.gnome.adw.TabViewShortcuts]]("shortcuts", _.getShortcuts(), _.setShortcuts(_), true)

  

  extension (v: TabView) {
    def unwrap: org.gnome.adw.TabView = v

    def defaultIcon: Var.Aux[org.gnome.gio.Icon, v.type] = guarana.gtk.adw.TabView.DefaultIcon.asInstanceOf[Var.Aux[org.gnome.gio.Icon, v.type]]
    def menuModel: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = guarana.gtk.adw.TabView.MenuModel.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def shortcuts: Var.Aux[java.util.Set[org.gnome.adw.TabViewShortcuts], v.type] = guarana.gtk.adw.TabView.Shortcuts.asInstanceOf[Var.Aux[java.util.Set[org.gnome.adw.TabViewShortcuts], v.type]]

    

    export unwrap.{
      onClosePage,
      onCreateWindow,
      onDestroy,
      onDirectionChanged,
      onHide,
      onIndicatorActivated,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onPageAttached,
      onPageDetached,
      onPageReordered,
      onQueryTooltip,
      onRealize,
      onSetupMenu,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.adw.TabView): TabView = 
    val res = v.asInstanceOf[TabView]
    
    res

  def init(v: TabView): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(selectedPage: Opt[org.gnome.adw.TabPage], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): TabView = {
    val res = {
      val res = org.gnome.adw.TabView.builder()
      ifSet(selectedPage, v => res.setSelectedPage(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[TabView]
  }
  
  def apply(
    selectedPage: Opt[org.gnome.adw.TabPage] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    defaultIcon: Opt[Binding[org.gnome.gio.Icon]] = UnsetParam,
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
    menuModel: Opt[Binding[org.gnome.gio.MenuModel | Null]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    shortcuts: Opt[Binding[java.util.Set[org.gnome.adw.TabViewShortcuts]]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[TabView] = {
    val res = uninitialized(selectedPage, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.TabView.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(defaultIcon, res.defaultIcon := _)
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
    ifSet(menuModel, res.menuModel := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(shortcuts, res.shortcuts := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        