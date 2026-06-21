
package guarana.gtk

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type MenuButton <: guarana.gtk.Widget  = org.gnome.gtk.MenuButton & guarana.gtk.Widget
object MenuButton extends VarsMap {
  val Active: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("active", _.getActive(), _.setActive(_), true)
  val AlwaysShowArrow: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("always-show-arrow", _.getAlwaysShowArrow(), _.setAlwaysShowArrow(_), true)
  val CanShrink: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("can-shrink", _.getCanShrink(), _.setCanShrink(_), true)
  val Child: ExternalVar.Aux[MenuButton, guarana.gtk.Widget | Null] = ExternalVar[MenuButton, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val HasFrame: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val MenuModel: ExternalVar.Aux[MenuButton, org.gnome.gio.MenuModel | Null] = ExternalVar[MenuButton, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  val Popover: ExternalVar.Aux[MenuButton, org.gnome.gtk.Popover | Null] = ExternalVar[MenuButton, org.gnome.gtk.Popover | Null]("popover", _.getPopover(), _.setPopover(_), true)
  val Primary: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("primary", _.getPrimary(), _.setPrimary(_), true)
  val UseUnderline: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)

  

  extension (v: MenuButton) {
    def unwrap: org.gnome.gtk.MenuButton = v

    def active: Var.Aux[Boolean, v.type] = guarana.gtk.MenuButton.Active.asInstanceOf[Var.Aux[Boolean, v.type]]
    def alwaysShowArrow: Var.Aux[Boolean, v.type] = guarana.gtk.MenuButton.AlwaysShowArrow.asInstanceOf[Var.Aux[Boolean, v.type]]
    def canShrink: Var.Aux[Boolean, v.type] = guarana.gtk.MenuButton.CanShrink.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.MenuButton.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def hasFrame: Var.Aux[Boolean, v.type] = guarana.gtk.MenuButton.HasFrame.asInstanceOf[Var.Aux[Boolean, v.type]]
    def menuModel: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = guarana.gtk.MenuButton.MenuModel.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def popover: Var.Aux[org.gnome.gtk.Popover | Null, v.type] = guarana.gtk.MenuButton.Popover.asInstanceOf[Var.Aux[org.gnome.gtk.Popover | Null, v.type]]
    def primary: Var.Aux[Boolean, v.type] = guarana.gtk.MenuButton.Primary.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useUnderline: Var.Aux[Boolean, v.type] = guarana.gtk.MenuButton.UseUnderline.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivate,
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

  def wrap(v: org.gnome.gtk.MenuButton): MenuButton = 
    val res = v.asInstanceOf[MenuButton]
    
    res

  def init(v: MenuButton): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(iconName: Opt[java.lang.String], label: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): MenuButton = {
    val res = {
      val res = org.gnome.gtk.MenuButton.builder()
      ifSet(iconName, v => res.setIconName(v))
      ifSet(label, v => res.setLabel(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[MenuButton]
  }
  
  def apply(
    iconName: Opt[java.lang.String] = UnsetParam, label: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    active: Opt[Binding[Boolean]] = UnsetParam,
    alwaysShowArrow: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canShrink: Opt[Binding[Boolean]] = UnsetParam,
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
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasFrame: Opt[Binding[Boolean]] = UnsetParam,
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
    popover: Opt[Binding[org.gnome.gtk.Popover | Null]] = UnsetParam,
    primary: Opt[Binding[Boolean]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    useUnderline: Opt[Binding[Boolean]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[MenuButton] = {
    val res = uninitialized(iconName, label, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.MenuButton.init(res)
    ifSet(active, res.active := _)
    ifSet(alwaysShowArrow, res.alwaysShowArrow := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canShrink, res.canShrink := _)
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
    ifSet(halign, res.halign := _)
    ifSet(hasFrame, res.hasFrame := _)
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
    ifSet(popover, res.popover := _)
    ifSet(primary, res.primary := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(useUnderline, res.useUnderline := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        