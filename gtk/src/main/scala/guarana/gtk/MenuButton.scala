package guarana
package gtk
import util.*
opaque type MenuButton <: Widget = org.gnome.gtk.MenuButton & Widget
object MenuButton extends VarsMap {
  val Active: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("active", _.getActive(), _.setActive(_), true)
  val AlwaysShowArrow: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("always-show-arrow", _.getAlwaysShowArrow(), _.setAlwaysShowArrow(_), true)
  val CanShrink: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("can-shrink", _.getCanShrink(), _.setCanShrink(_), true)
  val Child: ExternalVar.Aux[MenuButton, org.gnome.gtk.Widget | Null] = ExternalVar[MenuButton, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val HasFrame: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val MenuModel: ExternalVar.Aux[MenuButton, org.gnome.gio.MenuModel | Null] = ExternalVar[MenuButton, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  val Popover: ExternalVar.Aux[MenuButton, org.gnome.gtk.Popover | Null] = ExternalVar[MenuButton, org.gnome.gtk.Popover | Null]("popover", _.getPopover(), _.setPopover(_), true)
  val Primary: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("primary", _.getPrimary(), _.setPrimary(_), true)
  val UseUnderline: ExternalVar.Aux[MenuButton, Boolean] = ExternalVar[MenuButton, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)
  ()
  extension (v: MenuButton) {
    def unwrap: org.gnome.gtk.MenuButton = v
    def active: Var.Aux[Boolean, v.type] = Active.asInstanceOf[Var.Aux[Boolean, v.type]]
    def alwaysShowArrow: Var.Aux[Boolean, v.type] = AlwaysShowArrow.asInstanceOf[Var.Aux[Boolean, v.type]]
    def canShrink: Var.Aux[Boolean, v.type] = CanShrink.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def hasFrame: Var.Aux[Boolean, v.type] = HasFrame.asInstanceOf[Var.Aux[Boolean, v.type]]
    def menuModel: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = MenuModel.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def popover: Var.Aux[org.gnome.gtk.Popover | Null, v.type] = Popover.asInstanceOf[Var.Aux[org.gnome.gtk.Popover | Null, v.type]]
    def primary: Var.Aux[Boolean, v.type] = Primary.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useUnderline: Var.Aux[Boolean, v.type] = UseUnderline.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate
  }
  def _wrap(v: org.gnome.gtk.MenuButton): MenuButton = {
    v.asInstanceOf
  }
  def init(v: MenuButton): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): MenuButton = {
    val res = new org.gnome.gtk.MenuButton()
    res.asInstanceOf[MenuButton]
  }
  def apply(active: Opt[Boolean] = UnsetParam, alwaysShowArrow: Opt[Boolean] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canShrink: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasFrame: Opt[Boolean] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, menuModel: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, popover: Opt[org.gnome.gtk.Popover | Null] = UnsetParam, primary: Opt[Boolean] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, useUnderline: Opt[Boolean] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, MenuButton] = {
    val res = uninitialized()
    init(res)
    ifSet(active, res.active := _)
    ifSet(alwaysShowArrow, res.alwaysShowArrow := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canShrink, res.canShrink := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
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