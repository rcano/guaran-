package guarana
package gtk
opaque type CellView <: Widget = org.gnome.gtk.CellView & Widget
object CellView {
  val DrawSensitive: ExternalVar.Aux[CellView, Boolean] = ExternalVar[CellView, Boolean]("draw-sensitive", _.getDrawSensitive(), _.setDrawSensitive(_), true)
  val FitModel: ExternalVar.Aux[CellView, Boolean] = ExternalVar[CellView, Boolean]("fit-model", _.getFitModel(), _.setFitModel(_), true)
  val Model: ExternalVar.Aux[CellView, org.gnome.gtk.TreeModel | Null] = ExternalVar[CellView, org.gnome.gtk.TreeModel | Null]("model", _.getModel(), _.setModel(_), true)
  ()
  extension (v: CellView) {
    def unwrap: org.gnome.gtk.CellView = v
  }
}