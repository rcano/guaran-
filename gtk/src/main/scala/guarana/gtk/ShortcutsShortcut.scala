
package guarana
package gtk

import guarana.util.*

opaque type ShortcutsShortcut <: guarana.gtk.Widget  = org.gnome.gtk.ShortcutsShortcut & guarana.gtk.Widget
object ShortcutsShortcut extends VarsMap {
  

  

  extension (v: ShortcutsShortcut) {
    def unwrap: org.gnome.gtk.ShortcutsShortcut = v

    

    

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

  def wrap(v: org.gnome.gtk.ShortcutsShortcut): ShortcutsShortcut = 
    val res = v.asInstanceOf[ShortcutsShortcut]
    
    res

  def init(v: ShortcutsShortcut): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(accelSizeGroup: Opt[org.gnome.gtk.SizeGroup], accelerator: Opt[java.lang.String], actionName: Opt[java.lang.String], icon: Opt[org.gnome.gio.Icon], iconSet: Opt[Boolean], shortcutType: Opt[org.gnome.gtk.ShortcutType], subtitle: Opt[java.lang.String], subtitleSet: Opt[Boolean], title: Opt[java.lang.String], titleSizeGroup: Opt[org.gnome.gtk.SizeGroup], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): ShortcutsShortcut = {
    val res = org.gnome.gtk.ShortcutsShortcut.builder()
    ifSet(accelSizeGroup, v => res.setAccelSizeGroup(v))
    ifSet(accelerator, v => res.setAccelerator(v))
    ifSet(actionName, v => res.setActionName(v))
    ifSet(icon, v => res.setIcon(v))
    ifSet(iconSet, v => res.setIconSet(v))
    ifSet(shortcutType, v => res.setShortcutType(v))
    ifSet(subtitle, v => res.setSubtitle(v))
    ifSet(subtitleSet, v => res.setSubtitleSet(v))
    ifSet(title, v => res.setTitle(v))
    ifSet(titleSizeGroup, v => res.setTitleSizeGroup(v))
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    
    res.asInstanceOf[ShortcutsShortcut]
  }
  
  def apply(
    accelSizeGroup: Opt[org.gnome.gtk.SizeGroup] = UnsetParam, accelerator: Opt[java.lang.String] = UnsetParam, actionName: Opt[java.lang.String] = UnsetParam, icon: Opt[org.gnome.gio.Icon] = UnsetParam, iconSet: Opt[Boolean] = UnsetParam, shortcutType: Opt[org.gnome.gtk.ShortcutType] = UnsetParam, subtitle: Opt[java.lang.String] = UnsetParam, subtitleSet: Opt[Boolean] = UnsetParam, title: Opt[java.lang.String] = UnsetParam, titleSizeGroup: Opt[org.gnome.gtk.SizeGroup] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
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
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[ShortcutsShortcut] = {
    val res = uninitialized(accelSizeGroup, accelerator, actionName, icon, iconSet, shortcutType, subtitle, subtitleSet, title, titleSizeGroup, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.ShortcutsShortcut.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
  
}
        