package guarana
package gtk
opaque type GridView <: ListBase = org.gnome.gtk.GridView & ListBase
object GridView {
  val EnableRubberband: ExternalVar.Aux[GridView, Boolean] = ExternalVar[GridView, Boolean]("enable-rubberband", _.getEnableRubberband(), _.setEnableRubberband(_), true)
  val MaxColumns: ExternalVar.Aux[GridView, Int] = ExternalVar[GridView, Int]("max-columns", _.getMaxColumns(), _.setMaxColumns(_), true)
  val MinColumns: ExternalVar.Aux[GridView, Int] = ExternalVar[GridView, Int]("min-columns", _.getMinColumns(), _.setMinColumns(_), true)
  val SingleClickActivate: ExternalVar.Aux[GridView, Boolean] = ExternalVar[GridView, Boolean]("single-click-activate", _.getSingleClickActivate(), _.setSingleClickActivate(_), true)
  val TabBehavior: ExternalVar.Aux[GridView, org.gnome.gtk.ListTabBehavior | Null] = ExternalVar[GridView, org.gnome.gtk.ListTabBehavior | Null]("tab-behavior", _.getTabBehavior(), _.setTabBehavior(_), true)
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