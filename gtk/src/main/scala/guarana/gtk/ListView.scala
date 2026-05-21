package guarana
package gtk
opaque type ListView <: ListBase = org.gnome.gtk.ListView & ListBase
object ListView {
  val EnableRubberband: ExternalVar.Aux[ListView, Boolean] = ExternalVar[ListView, Boolean]("enable-rubberband", _.getEnableRubberband(), _.setEnableRubberband(_), true)
  val Factory: ExternalVar.Aux[ListView, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[ListView, org.gnome.gtk.ListItemFactory | Null]("factory", _.getFactory(), _.setFactory(_), true)
  val HeaderFactory: ExternalVar.Aux[ListView, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[ListView, org.gnome.gtk.ListItemFactory | Null]("header-factory", _.getHeaderFactory(), _.setHeaderFactory(_), true)
  val Model: ExternalVar.Aux[ListView, org.gnome.gtk.SelectionModel[?] | Null] = ExternalVar[ListView, org.gnome.gtk.SelectionModel[?] | Null]("model", _.getModel(), _.setModel(_), true)
  val ShowSeparators: ExternalVar.Aux[ListView, Boolean] = ExternalVar[ListView, Boolean]("show-separators", _.getShowSeparators(), _.setShowSeparators(_), true)
  val SingleClickActivate: ExternalVar.Aux[ListView, Boolean] = ExternalVar[ListView, Boolean]("single-click-activate", _.getSingleClickActivate(), _.setSingleClickActivate(_), true)
  val TabBehavior: ExternalVar.Aux[ListView, org.gnome.gtk.ListTabBehavior | Null] = ExternalVar[ListView, org.gnome.gtk.ListTabBehavior | Null]("tab-behavior", _.getTabBehavior(), _.setTabBehavior(_), true)
  ()
  extension (v: ListView) {
    def unwrap: org.gnome.gtk.ListView = v
    export unwrap.onActivate
  }
}