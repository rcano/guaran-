package guarana
package gtk
opaque type CenterBox <: Widget = org.gnome.gtk.CenterBox & Widget
object CenterBox {
  val BaselinePosition: ExternalVar.Aux[CenterBox, org.gnome.gtk.BaselinePosition | Null] = ExternalVar[CenterBox, org.gnome.gtk.BaselinePosition | Null]("baseline-position", _.getBaselinePosition(), _.setBaselinePosition(_), true)
  val ShrinkCenterLast: ExternalVar.Aux[CenterBox, Boolean] = ExternalVar[CenterBox, Boolean]("shrink-center-last", _.getShrinkCenterLast(), _.setShrinkCenterLast(_), true)
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