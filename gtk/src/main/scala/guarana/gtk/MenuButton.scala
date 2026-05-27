package guarana
package gtk
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
    export unwrap.onActivate
  }
  def init(v: MenuButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): MenuButton = {
    val res = new org.gnome.gtk.MenuButton()
    res.asInstanceOf[MenuButton]
  }
}