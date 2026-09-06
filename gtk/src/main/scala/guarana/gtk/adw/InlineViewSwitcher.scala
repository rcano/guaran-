
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type InlineViewSwitcher <: guarana.gtk.Widget  = org.gnome.adw.InlineViewSwitcher & guarana.gtk.Widget
object InlineViewSwitcher extends VarsMap {
  val CanShrink: ExternalVar.Aux[InlineViewSwitcher, Boolean] = ExternalVar[InlineViewSwitcher, Boolean]("can-shrink", _.getCanShrink(), _.setCanShrink(_), true)
  val DisplayMode: ExternalVar.Aux[InlineViewSwitcher, org.gnome.adw.InlineViewSwitcherDisplayMode] = ExternalVar[InlineViewSwitcher, org.gnome.adw.InlineViewSwitcherDisplayMode]("display-mode", _.getDisplayMode(), _.setDisplayMode(_), true)
  val Homogeneous: ExternalVar.Aux[InlineViewSwitcher, Boolean] = ExternalVar[InlineViewSwitcher, Boolean]("homogeneous", _.getHomogeneous(), _.setHomogeneous(_), true)
  val Orientation: ExternalVar.Aux[InlineViewSwitcher, org.gnome.gtk.Orientation] = ExternalVar[InlineViewSwitcher, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Stack: ExternalVar.Aux[InlineViewSwitcher, org.gnome.adw.ViewStack | Null] = ExternalVar[InlineViewSwitcher, org.gnome.adw.ViewStack | Null]("stack", _.getStack(), _.setStack(_), true)

  

  extension (v: InlineViewSwitcher) {
    def unwrap: org.gnome.adw.InlineViewSwitcher = v

    def canShrink: Var.Aux[Boolean, v.type] = guarana.gtk.adw.InlineViewSwitcher.CanShrink.asInstanceOf[Var.Aux[Boolean, v.type]]
    def displayMode: Var.Aux[org.gnome.adw.InlineViewSwitcherDisplayMode, v.type] = guarana.gtk.adw.InlineViewSwitcher.DisplayMode.asInstanceOf[Var.Aux[org.gnome.adw.InlineViewSwitcherDisplayMode, v.type]]
    def homogeneous: Var.Aux[Boolean, v.type] = guarana.gtk.adw.InlineViewSwitcher.Homogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.adw.InlineViewSwitcher.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def stack: Var.Aux[org.gnome.adw.ViewStack | Null, v.type] = guarana.gtk.adw.InlineViewSwitcher.Stack.asInstanceOf[Var.Aux[org.gnome.adw.ViewStack | Null, v.type]]

    

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

  def wrap(v: org.gnome.adw.InlineViewSwitcher): InlineViewSwitcher = 
    val res = v.asInstanceOf[InlineViewSwitcher]
    
    res

  def init(v: InlineViewSwitcher): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): InlineViewSwitcher = {
    val res = {
      val res = org.gnome.adw.InlineViewSwitcher.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[InlineViewSwitcher]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canShrink: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    displayMode: Opt[Binding[org.gnome.adw.InlineViewSwitcherDisplayMode]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    homogeneous: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    stack: Opt[Binding[org.gnome.adw.ViewStack | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[InlineViewSwitcher] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.InlineViewSwitcher.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canShrink, res.canShrink := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(displayMode, res.displayMode := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(homogeneous, res.homogeneous := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
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
        