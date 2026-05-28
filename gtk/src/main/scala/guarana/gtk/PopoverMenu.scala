package guarana
package gtk
import util.*
opaque type PopoverMenu <: Popover = org.gnome.gtk.PopoverMenu & Popover
object PopoverMenu {
  val Flags: ExternalVar.Aux[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags]] = ExternalVar[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags]]("flags", _.getFlags(), _.setFlags(_), true)
  val MenuModel: ExternalVar.Aux[PopoverMenu, org.gnome.gio.MenuModel | Null] = ExternalVar[PopoverMenu, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  ()
  extension (v: PopoverMenu) {
    def unwrap: org.gnome.gtk.PopoverMenu = v
    def flags: Var.Aux[java.util.Set[org.gnome.gtk.PopoverMenuFlags], v.type] = Flags.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.PopoverMenuFlags], v.type]]
    def menuModel: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = MenuModel.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
  }
  def init(v: PopoverMenu): Unit = {
    Popover.init(v)
  }
  def uninitialized(): PopoverMenu = {
    val res = new org.gnome.gtk.PopoverMenu()
    res.asInstanceOf[PopoverMenu]
  }
  def apply(flags: Opt[java.util.Set[org.gnome.gtk.PopoverMenuFlags]] = UnsetParam, menuModel: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam): VarContextAction[PopoverMenu] = {
    val res = uninitialized()
    init(res)
    ifSet(flags, res.flags := _)
    ifSet(menuModel, res.menuModel := _)
    res
  }
}