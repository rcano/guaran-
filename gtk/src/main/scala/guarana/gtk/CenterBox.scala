package guarana
package gtk
import util.*
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
    def baselinePosition: Var.Aux[org.gnome.gtk.BaselinePosition, v.type] = BaselinePosition.asInstanceOf[Var.Aux[org.gnome.gtk.BaselinePosition, v.type]]
    def centerWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = CenterWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def endWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = EndWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def shrinkCenterLast: Var.Aux[Boolean, v.type] = ShrinkCenterLast.asInstanceOf[Var.Aux[Boolean, v.type]]
    def startWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = StartWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
  }
  def init(v: CenterBox): Unit = {
    Widget.init(v)
  }
  def uninitialized(): CenterBox = {
    val res = new org.gnome.gtk.CenterBox()
    res.asInstanceOf[CenterBox]
  }
  def apply(baselinePosition: Opt[org.gnome.gtk.BaselinePosition] = UnsetParam, centerWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, endWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, shrinkCenterLast: Opt[Boolean] = UnsetParam, startWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam): VarContextAction[CenterBox] = {
    val res = uninitialized()
    init(res)
    ifSet(baselinePosition, res.baselinePosition := _)
    ifSet(centerWidget, res.centerWidget := _)
    ifSet(endWidget, res.endWidget := _)
    ifSet(orientation, res.orientation := _)
    ifSet(shrinkCenterLast, res.shrinkCenterLast := _)
    ifSet(startWidget, res.startWidget := _)
    res
  }
}