package guarana
package gtk
opaque type ProgressBar <: Widget = org.gnome.gtk.ProgressBar & Widget
object ProgressBar {
  val Ellipsize: ExternalVar.Aux[ProgressBar, org.gnome.pango.EllipsizeMode | Null] = ExternalVar[ProgressBar, org.gnome.pango.EllipsizeMode | Null]("ellipsize", _.getEllipsize(), _.setEllipsize(_), true)
  val Fraction: ExternalVar.Aux[ProgressBar, Double] = ExternalVar[ProgressBar, Double]("fraction", _.getFraction(), _.setFraction(_), true)
  val Inverted: ExternalVar.Aux[ProgressBar, Boolean] = ExternalVar[ProgressBar, Boolean]("inverted", _.getInverted(), _.setInverted(_), true)
  val PulseStep: ExternalVar.Aux[ProgressBar, Double] = ExternalVar[ProgressBar, Double]("pulse-step", _.getPulseStep(), _.setPulseStep(_), true)
  val ShowText: ExternalVar.Aux[ProgressBar, Boolean] = ExternalVar[ProgressBar, Boolean]("show-text", _.getShowText(), _.setShowText(_), true)
  ()
  extension (v: ProgressBar) {
    def unwrap: org.gnome.gtk.ProgressBar = v
  }
  def init(v: ProgressBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ProgressBar = {
    val res = new org.gnome.gtk.ProgressBar()
    res.asInstanceOf[ProgressBar]
  }
}