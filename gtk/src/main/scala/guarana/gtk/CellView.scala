package guarana
package gtk
opaque type CellView <: Widget = org.gnome.gtk.CellView & Widget
object CellView {
  val DrawSensitive: ExternalVar.Aux[CellView, Boolean] = ExternalVar[CellView, Boolean]("draw-sensitive", _.getDrawSensitive(), _.setDrawSensitive(_), true)
  val FitModel: ExternalVar.Aux[CellView, Boolean] = ExternalVar[CellView, Boolean]("fit-model", _.getFitModel(), _.setFitModel(_), true)
  ()
  extension (v: CellView) {
    def unwrap: org.gnome.gtk.CellView = v
  }
  def init(v: CellView): Unit = {
    Widget.init(v)
  }
  def uninitialized(): CellView = {
    val res = new org.gnome.gtk.CellView()
    res.asInstanceOf[CellView]
  }
}