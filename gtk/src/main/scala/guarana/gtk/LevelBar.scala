package guarana
package gtk
opaque type LevelBar <: Widget = org.gnome.gtk.LevelBar & Widget
object LevelBar {
  val Inverted: ExternalVar.Aux[LevelBar, Boolean] = ExternalVar[LevelBar, Boolean]("inverted", _.getInverted(), _.setInverted(_), true)
  val MaxValue: ExternalVar.Aux[LevelBar, Double] = ExternalVar[LevelBar, Double]("max-value", _.getMaxValue(), _.setMaxValue(_), true)
  val MinValue: ExternalVar.Aux[LevelBar, Double] = ExternalVar[LevelBar, Double]("min-value", _.getMinValue(), _.setMinValue(_), true)
  val Mode: ExternalVar.Aux[LevelBar, org.gnome.gtk.LevelBarMode] = ExternalVar[LevelBar, org.gnome.gtk.LevelBarMode]("mode", _.getMode(), _.setMode(_), true)
  val Orientation: ExternalVar.Aux[LevelBar, org.gnome.gtk.Orientation] = ExternalVar[LevelBar, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Value: ExternalVar.Aux[LevelBar, Double] = ExternalVar[LevelBar, Double]("value", _.getValue(), _.setValue(_), true)
  ()
  extension (v: LevelBar) {
    def unwrap: org.gnome.gtk.LevelBar = v
    export unwrap.onOffsetChanged
  }
  def init(v: LevelBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): LevelBar = {
    val res = new org.gnome.gtk.LevelBar()
    res.asInstanceOf[LevelBar]
  }
}