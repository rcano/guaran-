package guarana
package gtk
import util.*
opaque type MenuButton <: Widget = org.gnome.gtk.MenuButton & Widget
object MenuButton {
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
  def init(v: MenuButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): MenuButton = {
    val res = new org.gnome.gtk.MenuButton()
    res.asInstanceOf[MenuButton]
  }
  def apply(active: Opt[Boolean] = UnsetParam, alwaysShowArrow: Opt[Boolean] = UnsetParam, canShrink: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, hasFrame: Opt[Boolean] = UnsetParam, menuModel: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam, popover: Opt[org.gnome.gtk.Popover | Null] = UnsetParam, primary: Opt[Boolean] = UnsetParam, useUnderline: Opt[Boolean] = UnsetParam): VarContextAction[MenuButton] = {
    val res = uninitialized()
    init(res)
    ifSet(active, res.active := _)
    ifSet(alwaysShowArrow, res.alwaysShowArrow := _)
    ifSet(canShrink, res.canShrink := _)
    ifSet(child, res.child := _)
    ifSet(hasFrame, res.hasFrame := _)
    ifSet(menuModel, res.menuModel := _)
    ifSet(popover, res.popover := _)
    ifSet(primary, res.primary := _)
    ifSet(useUnderline, res.useUnderline := _)
    res
  }
}