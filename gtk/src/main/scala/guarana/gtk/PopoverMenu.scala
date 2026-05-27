package guarana
package gtk
opaque type PopoverMenu <: Popover = org.gnome.gtk.PopoverMenu & Popover
object PopoverMenu {
  val Flags: ExternalVar.Aux[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags]] = ExternalVar[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags]]("flags", _.getFlags(), _.setFlags(_), true)
  val MenuModel: ExternalVar.Aux[PopoverMenu, org.gnome.gio.MenuModel | Null] = ExternalVar[PopoverMenu, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  ()
  extension (v: PopoverMenu) {
    def unwrap: org.gnome.gtk.PopoverMenu = v
  }
  def init(v: PopoverMenu): Unit = {
    Popover.init(v)
  }
  def uninitialized(): PopoverMenu = {
    val res = new org.gnome.gtk.PopoverMenu()
    res.asInstanceOf[PopoverMenu]
  }
}