package guarana
package gtk
import util.*
opaque type PopoverBin <: Widget = org.gnome.gtk.PopoverBin & Widget
object PopoverBin {
  val Child: ExternalVar.Aux[PopoverBin, org.gnome.gtk.Widget | Null] = ExternalVar[PopoverBin, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val HandleInput: ExternalVar.Aux[PopoverBin, Boolean] = ExternalVar[PopoverBin, Boolean]("handle-input", _.getHandleInput(), _.setHandleInput(_), true)
  val MenuModel: ExternalVar.Aux[PopoverBin, org.gnome.gio.MenuModel | Null] = ExternalVar[PopoverBin, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  val Popover: ExternalVar.Aux[PopoverBin, org.gnome.gtk.Popover | Null] = ExternalVar[PopoverBin, org.gnome.gtk.Popover | Null]("popover", _.getPopover(), _.setPopover(_), true)
  ()
  extension (v: PopoverBin) {
    def unwrap: org.gnome.gtk.PopoverBin = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def handleInput: Var.Aux[Boolean, v.type] = HandleInput.asInstanceOf[Var.Aux[Boolean, v.type]]
    def menuModel: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = MenuModel.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def popover: Var.Aux[org.gnome.gtk.Popover | Null, v.type] = Popover.asInstanceOf[Var.Aux[org.gnome.gtk.Popover | Null, v.type]]
  }
  def init(v: PopoverBin): Unit = {
    Widget.init(v)
  }
  def uninitialized(): PopoverBin = {
    val res = new org.gnome.gtk.PopoverBin()
    res.asInstanceOf[PopoverBin]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, handleInput: Opt[Boolean] = UnsetParam, menuModel: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam, popover: Opt[org.gnome.gtk.Popover | Null] = UnsetParam): VarContextAction[PopoverBin] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    ifSet(handleInput, res.handleInput := _)
    ifSet(menuModel, res.menuModel := _)
    ifSet(popover, res.popover := _)
    res
  }
}