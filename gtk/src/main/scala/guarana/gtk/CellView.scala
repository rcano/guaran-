package guarana
package gtk
opaque type CellView <: Widget = org.gnome.gtk.CellView & Widget
object CellView {
  @deprecated("", "") val DisplayedRow: ExternalVar.Aux[CellView, org.gnome.gtk.TreePath | Null] = ExternalVar[CellView, org.gnome.gtk.TreePath | Null]("displayed-row", _.getDisplayedRow(), _.setDisplayedRow(_), true)
  @deprecated("", "") val DrawSensitive: ExternalVar.Aux[CellView, Boolean] = ExternalVar[CellView, Boolean]("draw-sensitive", _.getDrawSensitive(), _.setDrawSensitive(_), true)
  @deprecated("", "") val FitModel: ExternalVar.Aux[CellView, Boolean] = ExternalVar[CellView, Boolean]("fit-model", _.getFitModel(), _.setFitModel(_), true)
  @deprecated("", "") val Model: ExternalVar.Aux[CellView, org.gnome.gtk.TreeModel | Null] = ExternalVar[CellView, org.gnome.gtk.TreeModel | Null]("model", _.getModel(), _.setModel(_), true)
  val Orientation: ExternalVar.Aux[CellView, org.gnome.gtk.Orientation] = ExternalVar[CellView, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
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