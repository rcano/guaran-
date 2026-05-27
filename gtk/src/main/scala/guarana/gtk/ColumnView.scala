package guarana
package gtk
opaque type ColumnView <: Widget = org.gnome.gtk.ColumnView & Widget
object ColumnView {
  val EnableRubberband: ExternalVar.Aux[ColumnView, Boolean] = ExternalVar[ColumnView, Boolean]("enable-rubberband", _.getEnableRubberband(), _.setEnableRubberband(_), true)
  val Reorderable: ExternalVar.Aux[ColumnView, Boolean] = ExternalVar[ColumnView, Boolean]("reorderable", _.getReorderable(), _.setReorderable(_), true)
  val ShowColumnSeparators: ExternalVar.Aux[ColumnView, Boolean] = ExternalVar[ColumnView, Boolean]("show-column-separators", _.getShowColumnSeparators(), _.setShowColumnSeparators(_), true)
  val ShowRowSeparators: ExternalVar.Aux[ColumnView, Boolean] = ExternalVar[ColumnView, Boolean]("show-row-separators", _.getShowRowSeparators(), _.setShowRowSeparators(_), true)
  val SingleClickActivate: ExternalVar.Aux[ColumnView, Boolean] = ExternalVar[ColumnView, Boolean]("single-click-activate", _.getSingleClickActivate(), _.setSingleClickActivate(_), true)
  val TabBehavior: ExternalVar.Aux[ColumnView, org.gnome.gtk.ListTabBehavior | Null] = ExternalVar[ColumnView, org.gnome.gtk.ListTabBehavior | Null]("tab-behavior", _.getTabBehavior(), _.setTabBehavior(_), true)
  ()
  extension (v: ColumnView) {
    def unwrap: org.gnome.gtk.ColumnView = v
    export unwrap.onActivate
  }
  def init(v: ColumnView): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ColumnView = {
    val res = new org.gnome.gtk.ColumnView()
    res.asInstanceOf[ColumnView]
  }
}