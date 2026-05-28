package guarana
package gtk
import util.*
opaque type PopoverMenuBar <: Widget = org.gnome.gtk.PopoverMenuBar & Widget
object PopoverMenuBar {
  val MenuModel: ExternalVar.Aux[PopoverMenuBar, org.gnome.gio.MenuModel | Null] = ExternalVar[PopoverMenuBar, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  ()
  extension (v: PopoverMenuBar) {
    def unwrap: org.gnome.gtk.PopoverMenuBar = v
    def menuModel: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = MenuModel.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
  }
  def init(v: PopoverMenuBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): PopoverMenuBar = {
    val res = new org.gnome.gtk.PopoverMenuBar()
    res.asInstanceOf[PopoverMenuBar]
  }
  def apply(menuModel: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam): VarContextAction[PopoverMenuBar] = {
    val res = uninitialized()
    init(res)
    ifSet(menuModel, res.menuModel := _)
    res
  }
}