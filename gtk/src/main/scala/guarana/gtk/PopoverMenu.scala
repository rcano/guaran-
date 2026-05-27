package guarana
package gtk
opaque type PopoverMenu <: Popover = org.gnome.gtk.PopoverMenu & Popover
object PopoverMenu {
  val Flags: ExternalVar.Aux[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags] | Null] = ExternalVar[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags] | Null]("flags", _.getFlags(), _.setFlags(_), true)
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