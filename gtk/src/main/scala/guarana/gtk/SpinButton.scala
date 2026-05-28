package guarana
package gtk
import util.*
opaque type SpinButton <: Widget = org.gnome.gtk.SpinButton & Widget
object SpinButton extends VarsMap {
  val ActivatesDefault: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("activates-default", _.getActivatesDefault(), _.setActivatesDefault(_), true)
  val Adjustment: ExternalVar.Aux[SpinButton, org.gnome.gtk.Adjustment] = ExternalVar[SpinButton, org.gnome.gtk.Adjustment]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  val Alignment: ExternalVar.Aux[SpinButton, Float] = ExternalVar[SpinButton, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val ClimbRate: ExternalVar.Aux[SpinButton, Double] = ExternalVar[SpinButton, Double]("climb-rate", _.getClimbRate(), _.setClimbRate(_), true)
  val Digits: ExternalVar.Aux[SpinButton, Int] = ExternalVar[SpinButton, Int]("digits", _.getDigits(), _.setDigits(_), true)
  val Editable: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val MaxWidthChars: ExternalVar.Aux[SpinButton, Int] = ExternalVar[SpinButton, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val Numeric: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("numeric", _.getNumeric(), _.setNumeric(_), true)
  val Orientation: ExternalVar.Aux[SpinButton, org.gnome.gtk.Orientation] = ExternalVar[SpinButton, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Position: ExternalVar.Aux[SpinButton, Int] = ExternalVar[SpinButton, Int]("position", _.getPosition(), _.setPosition(_), true)
  val SnapToTicks: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("snap-to-ticks", _.getSnapToTicks(), _.setSnapToTicks(_), true)
  val Text: ExternalVar.Aux[SpinButton, java.lang.String] = ExternalVar[SpinButton, java.lang.String]("text", _.getText(), _.setText(_), true)
  val UpdatePolicy: ExternalVar.Aux[SpinButton, org.gnome.gtk.SpinButtonUpdatePolicy] = ExternalVar[SpinButton, org.gnome.gtk.SpinButtonUpdatePolicy]("update-policy", _.getUpdatePolicy(), _.setUpdatePolicy(_), true)
  val Value: ExternalVar.Aux[SpinButton, Double] = ExternalVar[SpinButton, Double]("value", _.getValue(), _.setValue(_), true)
  val WidthChars: ExternalVar.Aux[SpinButton, Int] = ExternalVar[SpinButton, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)
  val Wrap: ExternalVar.Aux[SpinButton, Boolean] = ExternalVar[SpinButton, Boolean]("wrap", _.getWrap(), _.setWrap(_), true)
  ()
  extension (v: SpinButton) {
    def unwrap: org.gnome.gtk.SpinButton = v
    def activatesDefault: Var.Aux[Boolean, v.type] = ActivatesDefault.asInstanceOf[Var.Aux[Boolean, v.type]]
    def adjustment: Var.Aux[org.gnome.gtk.Adjustment, v.type] = Adjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment, v.type]]
    def alignment: Var.Aux[Float, v.type] = Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def climbRate: Var.Aux[Double, v.type] = ClimbRate.asInstanceOf[Var.Aux[Double, v.type]]
    def digits: Var.Aux[Int, v.type] = Digits.asInstanceOf[Var.Aux[Int, v.type]]
    def editable: Var.Aux[Boolean, v.type] = Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def numeric: Var.Aux[Boolean, v.type] = Numeric.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def position: Var.Aux[Int, v.type] = Position.asInstanceOf[Var.Aux[Int, v.type]]
    def snapToTicks: Var.Aux[Boolean, v.type] = SnapToTicks.asInstanceOf[Var.Aux[Boolean, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def updatePolicy: Var.Aux[org.gnome.gtk.SpinButtonUpdatePolicy, v.type] = UpdatePolicy.asInstanceOf[Var.Aux[org.gnome.gtk.SpinButtonUpdatePolicy, v.type]]
    def value: Var.Aux[Double, v.type] = Value.asInstanceOf[Var.Aux[Double, v.type]]
    def widthChars: Var.Aux[Int, v.type] = WidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def wrap: Var.Aux[Boolean, v.type] = Wrap.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate, unwrap.onChangeValue, unwrap.onInput, unwrap.onOutput, unwrap.onValueChanged, unwrap.onWrapped
  }
  def _wrap(v: org.gnome.gtk.SpinButton): SpinButton = {
    v.asInstanceOf
  }
  def init(v: SpinButton): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gtk.Adjustment | Null, arg$1: Double, arg$2: Int): SpinButton = {
    val res = new org.gnome.gtk.SpinButton(arg$0, arg$1, arg$2)
    res.asInstanceOf[SpinButton]
  }
  def apply(arg$0: org.gnome.gtk.Adjustment | Null, arg$1: Double, arg$2: Int, activatesDefault: Opt[Boolean] = UnsetParam, adjustment: Opt[org.gnome.gtk.Adjustment] = UnsetParam, alignment: Opt[Float] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, climbRate: Opt[Double] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, digits: Opt[Int] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, editable: Opt[Boolean] = UnsetParam, enableUndo: Opt[Boolean] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, maxWidthChars: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, numeric: Opt[Boolean] = UnsetParam, opacity: Opt[Double] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, position: Opt[Int] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, snapToTicks: Opt[Boolean] = UnsetParam, text: Opt[java.lang.String] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, updatePolicy: Opt[org.gnome.gtk.SpinButtonUpdatePolicy] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, value: Opt[Double] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, widthChars: Opt[Int] = UnsetParam, wrap: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, SpinButton] = {
    val res = uninitialized(arg$0, arg$1, arg$2)
    init(res)
    ifSet(activatesDefault, res.activatesDefault := _)
    ifSet(adjustment, res.adjustment := _)
    ifSet(alignment, res.alignment := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(climbRate, res.climbRate := _)
    ifSet(cursor, res.cursor := _)
    ifSet(digits, res.digits := _)
    ifSet(direction, res.direction := _)
    ifSet(editable, res.editable := _)
    ifSet(enableUndo, res.enableUndo := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(maxWidthChars, res.maxWidthChars := _)
    ifSet(name, res.name := _)
    ifSet(numeric, res.numeric := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(position, res.position := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(snapToTicks, res.snapToTicks := _)
    ifSet(text, res.text := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(updatePolicy, res.updatePolicy := _)
    ifSet(valign, res.valign := _)
    ifSet(value, res.value := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(widthChars, res.widthChars := _)
    ifSet(wrap, res.wrap := _)
    res
  }
}