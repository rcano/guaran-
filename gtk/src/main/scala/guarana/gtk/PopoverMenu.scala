
package guarana.gtk

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type PopoverMenu <: guarana.gtk.Popover  = org.gnome.gtk.PopoverMenu & guarana.gtk.Popover
object PopoverMenu extends VarsMap {
  val Flags: ExternalVar.Aux[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags]] = ExternalVar[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags]]("flags", _.getFlags(), _.setFlags(_), true)
  val MenuModel: ExternalVar.Aux[PopoverMenu, org.gnome.gio.MenuModel | Null] = ExternalVar[PopoverMenu, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)

  

  extension (v: PopoverMenu) {
    def unwrap: org.gnome.gtk.PopoverMenu = v

    def flags: Var.Aux[java.util.Set[org.gnome.gtk.PopoverMenuFlags], v.type] = guarana.gtk.PopoverMenu.Flags.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.PopoverMenuFlags], v.type]]
    def menuModel: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = guarana.gtk.PopoverMenu.MenuModel.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]

    

    export unwrap.{
      onActivateDefault,
      onClosed,
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

  def wrap(v: org.gnome.gtk.PopoverMenu): PopoverMenu = 
    val res = v.asInstanceOf[PopoverMenu]
    
    res

  def init(v: PopoverMenu): Unit = {
    guarana.gtk.Popover.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(visibleSubmenu: Opt[java.lang.String], defaultWidget: Opt[guarana.gtk.Widget], pointingTo: Opt[org.gnome.gdk.Rectangle], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): PopoverMenu = {
    val res = {
      val res = org.gnome.gtk.PopoverMenu.builder()
      ifSet(visibleSubmenu, v => res.setVisibleSubmenu(v))
      ifSet(defaultWidget, v => res.setDefaultWidget(v.unwrap))
      ifSet(pointingTo, v => res.setPointingTo(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[PopoverMenu]
  }
  
  def apply(
    visibleSubmenu: Opt[java.lang.String] = UnsetParam, defaultWidget: Opt[guarana.gtk.Widget] = UnsetParam, pointingTo: Opt[org.gnome.gdk.Rectangle] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    autohide: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    cascadePopdown: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    flags: Opt[Binding[java.util.Set[org.gnome.gtk.PopoverMenuFlags]]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasArrow: Opt[Binding[Boolean]] = UnsetParam,
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
    mnemonicsVisible: Opt[Binding[Boolean]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    position: Opt[Binding[org.gnome.gtk.PositionType]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[PopoverMenu] = {
    val res = uninitialized(visibleSubmenu, defaultWidget, pointingTo, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.PopoverMenu.init(res)
    ifSet(autohide, res.autohide := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(cascadePopdown, res.cascadePopdown := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(flags, res.flags := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasArrow, res.hasArrow := _)
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
    ifSet(mnemonicsVisible, res.mnemonicsVisible := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
    ifSet(position, res.position := _)
    ifSet(receivesDefault, res.receivesDefault := _)
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
        