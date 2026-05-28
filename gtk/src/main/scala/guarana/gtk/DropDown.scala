package guarana
package gtk
import util.*
opaque type DropDown <: Widget = org.gnome.gtk.DropDown & Widget
object DropDown extends VarsMap {
  val EnableSearch: ExternalVar.Aux[DropDown, Boolean] = ExternalVar[DropDown, Boolean]("enable-search", _.getEnableSearch(), _.setEnableSearch(_), true)
  val Expression: ExternalVar.Aux[DropDown, org.gnome.gtk.Expression | Null] = ExternalVar[DropDown, org.gnome.gtk.Expression | Null]("expression", _.getExpression(), _.setExpression(_), true)
  val Factory: ExternalVar.Aux[DropDown, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[DropDown, org.gnome.gtk.ListItemFactory | Null]("factory", _.getFactory(), _.setFactory(_), true)
  val HeaderFactory: ExternalVar.Aux[DropDown, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[DropDown, org.gnome.gtk.ListItemFactory | Null]("header-factory", _.getHeaderFactory(), _.setHeaderFactory(_), true)
  val ListFactory: ExternalVar.Aux[DropDown, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[DropDown, org.gnome.gtk.ListItemFactory | Null]("list-factory", _.getListFactory(), _.setListFactory(_), true)
  val Model: ExternalVar.Aux[DropDown, org.gnome.gio.ListModel[?] | Null] = ExternalVar[DropDown, org.gnome.gio.ListModel[?] | Null]("model", _.getModel(), _.setModel(_), true)
  val SearchMatchMode: ExternalVar.Aux[DropDown, org.gnome.gtk.StringFilterMatchMode] = ExternalVar[DropDown, org.gnome.gtk.StringFilterMatchMode]("search-match-mode", _.getSearchMatchMode(), _.setSearchMatchMode(_), true)
  val Selected: ExternalVar.Aux[DropDown, Int] = ExternalVar[DropDown, Int]("selected", _.getSelected(), _.setSelected(_), true)
  val ShowArrow: ExternalVar.Aux[DropDown, Boolean] = ExternalVar[DropDown, Boolean]("show-arrow", _.getShowArrow(), _.setShowArrow(_), true)
  ()
  extension (v: DropDown) {
    def unwrap: org.gnome.gtk.DropDown = v
    def enableSearch: Var.Aux[Boolean, v.type] = EnableSearch.asInstanceOf[Var.Aux[Boolean, v.type]]
    def expression: Var.Aux[org.gnome.gtk.Expression | Null, v.type] = Expression.asInstanceOf[Var.Aux[org.gnome.gtk.Expression | Null, v.type]]
    def factory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = Factory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def headerFactory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = HeaderFactory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def listFactory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = ListFactory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def model: Var.Aux[org.gnome.gio.ListModel[?] | Null, v.type] = Model.asInstanceOf[Var.Aux[org.gnome.gio.ListModel[?] | Null, v.type]]
    def searchMatchMode: Var.Aux[org.gnome.gtk.StringFilterMatchMode, v.type] = SearchMatchMode.asInstanceOf[Var.Aux[org.gnome.gtk.StringFilterMatchMode, v.type]]
    def selected: Var.Aux[Int, v.type] = Selected.asInstanceOf[Var.Aux[Int, v.type]]
    def showArrow: Var.Aux[Boolean, v.type] = ShowArrow.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate
  }
  def _wrap(v: org.gnome.gtk.DropDown): DropDown = {
    v.asInstanceOf
  }
  def init(v: DropDown): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gio.ListModel[?] | Null, arg$1: org.gnome.gtk.Expression | Null): DropDown = {
    val res = new org.gnome.gtk.DropDown(arg$0, arg$1)
    res.asInstanceOf[DropDown]
  }
  def apply(arg$0: org.gnome.gio.ListModel[?] | Null, arg$1: org.gnome.gtk.Expression | Null, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, enableSearch: Opt[Boolean] = UnsetParam, expression: Opt[org.gnome.gtk.Expression | Null] = UnsetParam, factory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, headerFactory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, listFactory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, model: Opt[org.gnome.gio.ListModel[?] | Null] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, searchMatchMode: Opt[org.gnome.gtk.StringFilterMatchMode] = UnsetParam, selected: Opt[Int] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showArrow: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, DropDown] = {
    val res = uninitialized(arg$0, arg$1)
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(enableSearch, res.enableSearch := _)
    ifSet(expression, res.expression := _)
    ifSet(factory, res.factory := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(headerFactory, res.headerFactory := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(listFactory, res.listFactory := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(model, res.model := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(searchMatchMode, res.searchMatchMode := _)
    ifSet(selected, res.selected := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showArrow, res.showArrow := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}