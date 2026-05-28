package guarana
package gtk
import util.*
opaque type ProgressBar <: Widget = org.gnome.gtk.ProgressBar & Widget
object ProgressBar {
  val Ellipsize: ExternalVar.Aux[ProgressBar, org.gnome.pango.EllipsizeMode] = ExternalVar[ProgressBar, org.gnome.pango.EllipsizeMode]("ellipsize", _.getEllipsize(), _.setEllipsize(_), true)
  val Fraction: ExternalVar.Aux[ProgressBar, Double] = ExternalVar[ProgressBar, Double]("fraction", _.getFraction(), _.setFraction(_), true)
  val Inverted: ExternalVar.Aux[ProgressBar, Boolean] = ExternalVar[ProgressBar, Boolean]("inverted", _.getInverted(), _.setInverted(_), true)
  val Orientation: ExternalVar.Aux[ProgressBar, org.gnome.gtk.Orientation] = ExternalVar[ProgressBar, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val PulseStep: ExternalVar.Aux[ProgressBar, Double] = ExternalVar[ProgressBar, Double]("pulse-step", _.getPulseStep(), _.setPulseStep(_), true)
  val ShowText: ExternalVar.Aux[ProgressBar, Boolean] = ExternalVar[ProgressBar, Boolean]("show-text", _.getShowText(), _.setShowText(_), true)
  val Text: ExternalVar.Aux[ProgressBar, java.lang.String | Null] = ExternalVar[ProgressBar, java.lang.String | Null]("text", _.getText(), _.setText(_), true)
  ()
  extension (v: ProgressBar) {
    def unwrap: org.gnome.gtk.ProgressBar = v
    def ellipsize: Var.Aux[org.gnome.pango.EllipsizeMode, v.type] = Ellipsize.asInstanceOf[Var.Aux[org.gnome.pango.EllipsizeMode, v.type]]
    def fraction: Var.Aux[Double, v.type] = Fraction.asInstanceOf[Var.Aux[Double, v.type]]
    def inverted: Var.Aux[Boolean, v.type] = Inverted.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def pulseStep: Var.Aux[Double, v.type] = PulseStep.asInstanceOf[Var.Aux[Double, v.type]]
    def showText: Var.Aux[Boolean, v.type] = ShowText.asInstanceOf[Var.Aux[Boolean, v.type]]
    def text: Var.Aux[java.lang.String | Null, v.type] = Text.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
  }
  def init(v: ProgressBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ProgressBar = {
    val res = new org.gnome.gtk.ProgressBar()
    res.asInstanceOf[ProgressBar]
  }
  def apply(ellipsize: Opt[org.gnome.pango.EllipsizeMode] = UnsetParam, fraction: Opt[Double] = UnsetParam, inverted: Opt[Boolean] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, pulseStep: Opt[Double] = UnsetParam, showText: Opt[Boolean] = UnsetParam, text: Opt[java.lang.String | Null] = UnsetParam): VarContextAction[ProgressBar] = {
    val res = uninitialized()
    init(res)
    ifSet(ellipsize, res.ellipsize := _)
    ifSet(fraction, res.fraction := _)
    ifSet(inverted, res.inverted := _)
    ifSet(orientation, res.orientation := _)
    ifSet(pulseStep, res.pulseStep := _)
    ifSet(showText, res.showText := _)
    ifSet(text, res.text := _)
    res
  }
}