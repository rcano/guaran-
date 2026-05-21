package guarana
package gtk
opaque type PopoverMenu <: Popover = org.gnome.gtk.PopoverMenu & Popover
object PopoverMenu {
  val Flags: ExternalVar.Aux[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags] | Null] = ExternalVar[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags] | Null]("flags", _.getFlags(), _.setFlags(_), true)
  val MenuModel: ExternalVar.Aux[PopoverMenu, org.gnome.gio.MenuModel | Null] = ExternalVar[PopoverMenu, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  ()
  extension (v: PopoverMenu) {
    def unwrap: org.gnome.gtk.PopoverMenu = v
  }
}