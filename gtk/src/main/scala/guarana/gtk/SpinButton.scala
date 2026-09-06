
package guarana.gtk

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type SpinButton <: guarana.gtk.Widget  = org.gnome.gtk.SpinButton & guarana.gtk.Widget
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

  

  extension (v: SpinButton) {
    def unwrap: org.gnome.gtk.SpinButton = v

    def activatesDefault: Var.Aux[Boolean, v.type] = guarana.gtk.SpinButton.ActivatesDefault.asInstanceOf[Var.Aux[Boolean, v.type]]
    def adjustment: Var.Aux[org.gnome.gtk.Adjustment, v.type] = guarana.gtk.SpinButton.Adjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment, v.type]]
    def alignment: Var.Aux[Float, v.type] = guarana.gtk.SpinButton.Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def climbRate: Var.Aux[Double, v.type] = guarana.gtk.SpinButton.ClimbRate.asInstanceOf[Var.Aux[Double, v.type]]
    def digits: Var.Aux[Int, v.type] = guarana.gtk.SpinButton.Digits.asInstanceOf[Var.Aux[Int, v.type]]
    def editable: Var.Aux[Boolean, v.type] = guarana.gtk.SpinButton.Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = guarana.gtk.SpinButton.EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = guarana.gtk.SpinButton.MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def numeric: Var.Aux[Boolean, v.type] = guarana.gtk.SpinButton.Numeric.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.SpinButton.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def position: Var.Aux[Int, v.type] = guarana.gtk.SpinButton.Position.asInstanceOf[Var.Aux[Int, v.type]]
    def snapToTicks: Var.Aux[Boolean, v.type] = guarana.gtk.SpinButton.SnapToTicks.asInstanceOf[Var.Aux[Boolean, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = guarana.gtk.SpinButton.Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def updatePolicy: Var.Aux[org.gnome.gtk.SpinButtonUpdatePolicy, v.type] = guarana.gtk.SpinButton.UpdatePolicy.asInstanceOf[Var.Aux[org.gnome.gtk.SpinButtonUpdatePolicy, v.type]]
    def value: Var.Aux[Double, v.type] = guarana.gtk.SpinButton.Value.asInstanceOf[Var.Aux[Double, v.type]]
    def widthChars: Var.Aux[Int, v.type] = guarana.gtk.SpinButton.WidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def wrap: Var.Aux[Boolean, v.type] = guarana.gtk.SpinButton.Wrap.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivate,
      onChangeValue,
      onDestroy,
      onDirectionChanged,
      onHide,
      onInput,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onOutput,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize,
      onValueChanged,
      onWrapped
    }
  }

  def wrap(v: org.gnome.gtk.SpinButton): SpinButton = 
    val res = v.asInstanceOf[SpinButton]
    
    res

  def init(v: SpinButton): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], editingCanceled: Opt[Boolean], xalign: Opt[Float]): SpinButton = {
    val res = {
      val res = org.gnome.gtk.SpinButton.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      ifSet(editingCanceled, v => res.setEditingCanceled(v))
      ifSet(xalign, v => res.setXalign(v))
      res.build()
    }
    
    res.asInstanceOf[SpinButton]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, editingCanceled: Opt[Boolean] = UnsetParam, xalign: Opt[Float] = UnsetParam,
    activatesDefault: Opt[Binding[Boolean]] = UnsetParam,
    adjustment: Opt[Binding[org.gnome.gtk.Adjustment]] = UnsetParam,
    alignment: Opt[Binding[Float]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    climbRate: Opt[Binding[Double]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    digits: Opt[Binding[Int]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    editable: Opt[Binding[Boolean]] = UnsetParam,
    enableUndo: Opt[Binding[Boolean]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    maxWidthChars: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    numeric: Opt[Binding[Boolean]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    position: Opt[Binding[Int]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    snapToTicks: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    updatePolicy: Opt[Binding[org.gnome.gtk.SpinButtonUpdatePolicy]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    value: Opt[Binding[Double]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    widthChars: Opt[Binding[Int]] = UnsetParam,
    wrap: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[SpinButton] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole, editingCanceled, xalign)
    guarana.gtk.SpinButton.init(res)
    ifSet(activatesDefault, res.activatesDefault := _)
    ifSet(adjustment, res.adjustment := _)
    ifSet(alignment, res.alignment := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(climbRate, res.climbRate := _)
    ifSet(cssClasses, res.cssClasses := _)
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
    ifSet(parent, res.parent := _)
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
        