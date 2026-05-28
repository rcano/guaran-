package guarana
package gtk
import util.*
opaque type DropDown <: Widget = org.gnome.gtk.DropDown & Widget
object DropDown {
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
  def init(v: DropDown): Unit = {
    Widget.init(v)
  }
  def uninitialized(): DropDown = {
    val res = new org.gnome.gtk.DropDown()
    res.asInstanceOf[DropDown]
  }
  def apply(enableSearch: Opt[Boolean] = UnsetParam, expression: Opt[org.gnome.gtk.Expression | Null] = UnsetParam, factory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, headerFactory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, listFactory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, model: Opt[org.gnome.gio.ListModel[?] | Null] = UnsetParam, searchMatchMode: Opt[org.gnome.gtk.StringFilterMatchMode] = UnsetParam, selected: Opt[Int] = UnsetParam, showArrow: Opt[Boolean] = UnsetParam): VarContextAction[DropDown] = {
    val res = uninitialized()
    init(res)
    ifSet(enableSearch, res.enableSearch := _)
    ifSet(expression, res.expression := _)
    ifSet(factory, res.factory := _)
    ifSet(headerFactory, res.headerFactory := _)
    ifSet(listFactory, res.listFactory := _)
    ifSet(model, res.model := _)
    ifSet(searchMatchMode, res.searchMatchMode := _)
    ifSet(selected, res.selected := _)
    ifSet(showArrow, res.showArrow := _)
    res
  }
}