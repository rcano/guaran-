package guarana
package gtk
opaque type PopoverMenuBar <: Widget = org.gnome.gtk.PopoverMenuBar & Widget
object PopoverMenuBar {
  val MenuModel: ExternalVar.Aux[PopoverMenuBar, org.gnome.gio.MenuModel | Null] = ExternalVar[PopoverMenuBar, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  ()
  extension (v: PopoverMenuBar) {
    def unwrap: org.gnome.gtk.PopoverMenuBar = v
  }
  def init(v: PopoverMenuBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): PopoverMenuBar = {
    val res = new org.gnome.gtk.PopoverMenuBar()
    res.asInstanceOf[PopoverMenuBar]
  }
}