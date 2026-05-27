package guarana
package gtk
opaque type Box <: Widget = org.gnome.gtk.Box & Widget
object Box {
  val BaselineChild: ExternalVar.Aux[Box, Int] = ExternalVar[Box, Int]("baseline-child", _.getBaselineChild(), _.setBaselineChild(_), true)
  val BaselinePosition: ExternalVar.Aux[Box, org.gnome.gtk.BaselinePosition | Null] = ExternalVar[Box, org.gnome.gtk.BaselinePosition | Null]("baseline-position", _.getBaselinePosition(), _.setBaselinePosition(_), true)
  val Homogeneous: ExternalVar.Aux[Box, Boolean] = ExternalVar[Box, Boolean]("homogeneous", _.getHomogeneous(), _.setHomogeneous(_), true)
  val Spacing: ExternalVar.Aux[Box, Int] = ExternalVar[Box, Int]("spacing", _.getSpacing(), _.setSpacing(_), true)
  ()
  extension (v: Box) {
    def unwrap: org.gnome.gtk.Box = v
  }
  def init(v: Box): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Box = {
    val res = new org.gnome.gtk.Box()
    res.asInstanceOf[Box]
  }
}