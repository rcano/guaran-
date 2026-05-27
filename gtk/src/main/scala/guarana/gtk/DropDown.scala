package guarana
package gtk
opaque type DropDown <: Widget = org.gnome.gtk.DropDown & Widget
object DropDown {
  val EnableSearch: ExternalVar.Aux[DropDown, Boolean] = ExternalVar[DropDown, Boolean]("enable-search", _.getEnableSearch(), _.setEnableSearch(_), true)
  val SearchMatchMode: ExternalVar.Aux[DropDown, org.gnome.gtk.StringFilterMatchMode | Null] = ExternalVar[DropDown, org.gnome.gtk.StringFilterMatchMode | Null]("search-match-mode", _.getSearchMatchMode(), _.setSearchMatchMode(_), true)
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