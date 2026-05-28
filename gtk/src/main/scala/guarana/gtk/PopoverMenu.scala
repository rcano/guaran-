package guarana
package gtk
import util.*
opaque type PopoverMenu <: Popover = org.gnome.gtk.PopoverMenu & Popover
object PopoverMenu extends VarsMap {
  val Flags: ExternalVar.Aux[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags]] = ExternalVar[PopoverMenu, java.util.Set[org.gnome.gtk.PopoverMenuFlags]]("flags", _.getFlags(), _.setFlags(_), true)
  val MenuModel: ExternalVar.Aux[PopoverMenu, org.gnome.gio.MenuModel | Null] = ExternalVar[PopoverMenu, org.gnome.gio.MenuModel | Null]("menu-model", _.getMenuModel(), _.setMenuModel(_), true)
  ()
  extension (v: PopoverMenu) {
    def unwrap: org.gnome.gtk.PopoverMenu = v
    def flags: Var.Aux[java.util.Set[org.gnome.gtk.PopoverMenuFlags], v.type] = Flags.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.PopoverMenuFlags], v.type]]
    def menuModel: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = MenuModel.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
  }
  def _wrap(v: org.gnome.gtk.PopoverMenu): PopoverMenu = {
    v.asInstanceOf
  }
  def init(v: PopoverMenu): ToolkitAction[Toolkit, Unit] = {
    Popover.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): PopoverMenu = {
    val res = new org.gnome.gtk.PopoverMenu()
    res.asInstanceOf[PopoverMenu]
  }
  def apply(autohide: Opt[Boolean] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, cascadePopdown: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, flags: Opt[java.util.Set[org.gnome.gtk.PopoverMenuFlags]] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasArrow: Opt[Boolean] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, menuModel: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam, mnemonicsVisible: Opt[Boolean] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, position: Opt[org.gnome.gtk.PositionType] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, PopoverMenu] = {
    val res = uninitialized()
    init(res)
    ifSet(autohide, res.autohide := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(cascadePopdown, res.cascadePopdown := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(flags, res.flags := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasArrow, res.hasArrow := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(menuModel, res.menuModel := _)
    ifSet(mnemonicsVisible, res.mnemonicsVisible := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(position, res.position := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}