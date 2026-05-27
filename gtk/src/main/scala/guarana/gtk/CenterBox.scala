package guarana
package gtk
opaque type CenterBox <: Widget = org.gnome.gtk.CenterBox & Widget
object CenterBox {
  val BaselinePosition: ExternalVar.Aux[CenterBox, org.gnome.gtk.BaselinePosition] = ExternalVar[CenterBox, org.gnome.gtk.BaselinePosition]("baseline-position", _.getBaselinePosition(), _.setBaselinePosition(_), true)
  val CenterWidget: ExternalVar.Aux[CenterBox, org.gnome.gtk.Widget | Null] = ExternalVar[CenterBox, org.gnome.gtk.Widget | Null]("center-widget", _.getCenterWidget(), _.setCenterWidget(_), true)
  val EndWidget: ExternalVar.Aux[CenterBox, org.gnome.gtk.Widget | Null] = ExternalVar[CenterBox, org.gnome.gtk.Widget | Null]("end-widget", _.getEndWidget(), _.setEndWidget(_), true)
  val Orientation: ExternalVar.Aux[CenterBox, org.gnome.gtk.Orientation] = ExternalVar[CenterBox, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val ShrinkCenterLast: ExternalVar.Aux[CenterBox, Boolean] = ExternalVar[CenterBox, Boolean]("shrink-center-last", _.getShrinkCenterLast(), _.setShrinkCenterLast(_), true)
  val StartWidget: ExternalVar.Aux[CenterBox, org.gnome.gtk.Widget | Null] = ExternalVar[CenterBox, org.gnome.gtk.Widget | Null]("start-widget", _.getStartWidget(), _.setStartWidget(_), true)
  ()
  extension (v: CenterBox) {
    def unwrap: org.gnome.gtk.CenterBox = v
  }
  def init(v: CenterBox): Unit = {
    Widget.init(v)
  }
  def uninitialized(): CenterBox = {
    val res = new org.gnome.gtk.CenterBox()
    res.asInstanceOf[CenterBox]
  }
}