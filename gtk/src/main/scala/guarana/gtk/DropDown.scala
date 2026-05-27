package guarana
package gtk
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
    export unwrap.onActivate
  }
  def init(v: DropDown): Unit = {
    Widget.init(v)
  }
  def uninitialized(): DropDown = {
    val res = new org.gnome.gtk.DropDown()
    res.asInstanceOf[DropDown]
  }
}