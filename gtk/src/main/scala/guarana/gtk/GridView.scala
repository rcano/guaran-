package guarana
package gtk
opaque type GridView <: ListBase = org.gnome.gtk.GridView & ListBase
object GridView {
  val EnableRubberband: ExternalVar.Aux[GridView, Boolean] = ExternalVar[GridView, Boolean]("enable-rubberband", _.getEnableRubberband(), _.setEnableRubberband(_), true)
  val Factory: ExternalVar.Aux[GridView, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[GridView, org.gnome.gtk.ListItemFactory | Null]("factory", _.getFactory(), _.setFactory(_), true)
  val MaxColumns: ExternalVar.Aux[GridView, Int] = ExternalVar[GridView, Int]("max-columns", _.getMaxColumns(), _.setMaxColumns(_), true)
  val MinColumns: ExternalVar.Aux[GridView, Int] = ExternalVar[GridView, Int]("min-columns", _.getMinColumns(), _.setMinColumns(_), true)
  val Model: ExternalVar.Aux[GridView, org.gnome.gtk.SelectionModel[?] | Null] = ExternalVar[GridView, org.gnome.gtk.SelectionModel[?] | Null]("model", _.getModel(), _.setModel(_), true)
  val SingleClickActivate: ExternalVar.Aux[GridView, Boolean] = ExternalVar[GridView, Boolean]("single-click-activate", _.getSingleClickActivate(), _.setSingleClickActivate(_), true)
  val TabBehavior: ExternalVar.Aux[GridView, org.gnome.gtk.ListTabBehavior] = ExternalVar[GridView, org.gnome.gtk.ListTabBehavior]("tab-behavior", _.getTabBehavior(), _.setTabBehavior(_), true)
  ()
  extension (v: GridView) {
    def unwrap: org.gnome.gtk.GridView = v
    export unwrap.onActivate
  }
  def init(v: GridView): Unit = {
    ListBase.init(v)
  }
  def uninitialized(): GridView = {
    val res = new org.gnome.gtk.GridView()
    res.asInstanceOf[GridView]
  }
}