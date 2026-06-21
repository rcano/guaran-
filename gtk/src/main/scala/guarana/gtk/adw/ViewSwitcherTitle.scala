
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type ViewSwitcherTitle <: guarana.gtk.Widget  = org.gnome.adw.ViewSwitcherTitle & guarana.gtk.Widget
object ViewSwitcherTitle extends VarsMap {
  @deprecated("", "") val Stack: ExternalVar.Aux[ViewSwitcherTitle, org.gnome.adw.ViewStack | Null] = ExternalVar[ViewSwitcherTitle, org.gnome.adw.ViewStack | Null]("stack", _.getStack(), _.setStack(_), true)
  @deprecated("", "") val Subtitle: ExternalVar.Aux[ViewSwitcherTitle, java.lang.String] = ExternalVar[ViewSwitcherTitle, java.lang.String]("subtitle", _.getSubtitle(), _.setSubtitle(_), true)
  @deprecated("", "") val Title: ExternalVar.Aux[ViewSwitcherTitle, java.lang.String] = ExternalVar[ViewSwitcherTitle, java.lang.String]("title", _.getTitle(), _.setTitle(_), true)
  @deprecated("", "") val ViewSwitcherEnabled: ExternalVar.Aux[ViewSwitcherTitle, Boolean] = ExternalVar[ViewSwitcherTitle, Boolean]("view-switcher-enabled", _.getViewSwitcherEnabled(), _.setViewSwitcherEnabled(_), true)

  

  extension (v: ViewSwitcherTitle) {
    def unwrap: org.gnome.adw.ViewSwitcherTitle = v

    @deprecated("", "") def stack: Var.Aux[org.gnome.adw.ViewStack | Null, v.type] = guarana.gtk.adw.ViewSwitcherTitle.Stack.asInstanceOf[Var.Aux[org.gnome.adw.ViewStack | Null, v.type]]
    @deprecated("", "") def subtitle: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.ViewSwitcherTitle.Subtitle.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def title: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.ViewSwitcherTitle.Title.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def viewSwitcherEnabled: Var.Aux[Boolean, v.type] = guarana.gtk.adw.ViewSwitcherTitle.ViewSwitcherEnabled.asInstanceOf[Var.Aux[Boolean, v.type]]

    

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

  def wrap(v: org.gnome.adw.ViewSwitcherTitle): ViewSwitcherTitle = 
    val res = v.asInstanceOf[ViewSwitcherTitle]
    
    res

  def init(v: ViewSwitcherTitle): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): ViewSwitcherTitle = {
    val res = {
      val res = org.gnome.adw.ViewSwitcherTitle.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[ViewSwitcherTitle]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
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
    stack: Opt[Binding[org.gnome.adw.ViewStack | Null]] = UnsetParam,
    subtitle: Opt[Binding[java.lang.String]] = UnsetParam,
    title: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    viewSwitcherEnabled: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[ViewSwitcherTitle] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.ViewSwitcherTitle.init(res)
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
    ifSet(stack, res.stack := _)
    ifSet(subtitle, res.subtitle := _)
    ifSet(title, res.title := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(viewSwitcherEnabled, res.viewSwitcherEnabled := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        