package guarana
package gtk
import util.*
opaque type ProgressBar <: Widget = org.gnome.gtk.ProgressBar & Widget
object ProgressBar extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.ProgressBar): ProgressBar = {
    v.asInstanceOf
  }
  def init(v: ProgressBar): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): ProgressBar = {
    val res = new org.gnome.gtk.ProgressBar()
    res.asInstanceOf[ProgressBar]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, ellipsize: Opt[org.gnome.pango.EllipsizeMode] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, fraction: Opt[Double] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, inverted: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, pulseStep: Opt[Double] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showText: Opt[Boolean] = UnsetParam, text: Opt[java.lang.String | Null] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, ProgressBar] = {
    val res = uninitialized()
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(ellipsize, res.ellipsize := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(fraction, res.fraction := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(inverted, res.inverted := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(pulseStep, res.pulseStep := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showText, res.showText := _)
    ifSet(text, res.text := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}