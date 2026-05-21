package guarana
package gtk
opaque type PopoverBin <: Widget = org.gnome.gtk.PopoverBin & Widget
object PopoverBin {
  val Child: ExternalVar.Aux[PopoverBin, org.gnome.gtk.Widget | Null] = ExternalVar[PopoverBin, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val HandleInput: ExternalVar.Aux[PopoverBin, Boolean] = ExternalVar[PopoverBin, Boolean]("handle-input", _.getHandleInput(), _.setHandleInput(_), true)
  val MenuModel: ExternalVar.Aux[PopoverBin, org.gnome.gio.MenuModel | Null] = ExternalVar[PopoverBin, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  val Popover: ExternalVar.Aux[PopoverBin, org.gnome.gtk.Popover | Null] = ExternalVar[PopoverBin, org.gnome.gtk.Popover | Null]("popover", _.getPopover(), _.setPopover(_), true)
  ()
  extension (v: PopoverBin) {
    def unwrap: org.gnome.gtk.PopoverBin = v
  }
}