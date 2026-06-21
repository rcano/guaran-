
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type SpinRow <: guarana.gtk.Widget  = org.gnome.adw.SpinRow & guarana.gtk.Widget
object SpinRow extends VarsMap {
  val Alignment: ExternalVar.Aux[SpinRow, Float] = ExternalVar[SpinRow, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val ClimbRate: ExternalVar.Aux[SpinRow, Double] = ExternalVar[SpinRow, Double]("climb-rate", _.getClimbRate(), _.setClimbRate(_), true)
  val Digits: ExternalVar.Aux[SpinRow, Int] = ExternalVar[SpinRow, Int]("digits", _.getDigits(), _.setDigits(_), true)
  val Editable: ExternalVar.Aux[SpinRow, Boolean] = ExternalVar[SpinRow, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[SpinRow, Boolean] = ExternalVar[SpinRow, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val MaxWidthChars: ExternalVar.Aux[SpinRow, Int] = ExternalVar[SpinRow, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val Numeric: ExternalVar.Aux[SpinRow, Boolean] = ExternalVar[SpinRow, Boolean]("numeric", _.getNumeric(), _.setNumeric(_), true)
  val Position: ExternalVar.Aux[SpinRow, Int] = ExternalVar[SpinRow, Int]("position", _.getPosition(), _.setPosition(_), true)
  val SnapToTicks: ExternalVar.Aux[SpinRow, Boolean] = ExternalVar[SpinRow, Boolean]("snap-to-ticks", _.getSnapToTicks(), _.setSnapToTicks(_), true)
  val Text: ExternalVar.Aux[SpinRow, java.lang.String] = ExternalVar[SpinRow, java.lang.String]("text", _.getText(), _.setText(_), true)
  val UpdatePolicy: ExternalVar.Aux[SpinRow, org.gnome.gtk.SpinButtonUpdatePolicy] = ExternalVar[SpinRow, org.gnome.gtk.SpinButtonUpdatePolicy]("update-policy", _.getUpdatePolicy(), _.setUpdatePolicy(_), true)
  val Value: ExternalVar.Aux[SpinRow, Double] = ExternalVar[SpinRow, Double]("value", _.getValue(), _.setValue(_), true)
  val WidthChars: ExternalVar.Aux[SpinRow, Int] = ExternalVar[SpinRow, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)
  val Wrap: ExternalVar.Aux[SpinRow, Boolean] = ExternalVar[SpinRow, Boolean]("wrap", _.getWrap(), _.setWrap(_), true)

  

  extension (v: SpinRow) {
    def unwrap: org.gnome.adw.SpinRow = v

    def alignment: Var.Aux[Float, v.type] = guarana.gtk.adw.SpinRow.Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def climbRate: Var.Aux[Double, v.type] = guarana.gtk.adw.SpinRow.ClimbRate.asInstanceOf[Var.Aux[Double, v.type]]
    def digits: Var.Aux[Int, v.type] = guarana.gtk.adw.SpinRow.Digits.asInstanceOf[Var.Aux[Int, v.type]]
    def editable: Var.Aux[Boolean, v.type] = guarana.gtk.adw.SpinRow.Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = guarana.gtk.adw.SpinRow.EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = guarana.gtk.adw.SpinRow.MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def numeric: Var.Aux[Boolean, v.type] = guarana.gtk.adw.SpinRow.Numeric.asInstanceOf[Var.Aux[Boolean, v.type]]
    def position: Var.Aux[Int, v.type] = guarana.gtk.adw.SpinRow.Position.asInstanceOf[Var.Aux[Int, v.type]]
    def snapToTicks: Var.Aux[Boolean, v.type] = guarana.gtk.adw.SpinRow.SnapToTicks.asInstanceOf[Var.Aux[Boolean, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.SpinRow.Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def updatePolicy: Var.Aux[org.gnome.gtk.SpinButtonUpdatePolicy, v.type] = guarana.gtk.adw.SpinRow.UpdatePolicy.asInstanceOf[Var.Aux[org.gnome.gtk.SpinButtonUpdatePolicy, v.type]]
    def value: Var.Aux[Double, v.type] = guarana.gtk.adw.SpinRow.Value.asInstanceOf[Var.Aux[Double, v.type]]
    def widthChars: Var.Aux[Int, v.type] = guarana.gtk.adw.SpinRow.WidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def wrap: Var.Aux[Boolean, v.type] = guarana.gtk.adw.SpinRow.Wrap.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivate,
      onActivated,
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
      onWrapped
    }
  }

  def wrap(v: org.gnome.adw.SpinRow): SpinRow = 
    val res = v.asInstanceOf[SpinRow]
    
    res

  def init(v: SpinRow): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(adjustment: Opt[org.gnome.gtk.Adjustment], subtitle: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], actionTarget: Opt[org.gnome.glib.Variant], xalign: Opt[Float]): SpinRow = {
    val res = {
      val res = org.gnome.adw.SpinRow.builder()
      ifSet(adjustment, v => res.setAdjustment(v))
      ifSet(subtitle, v => res.setSubtitle(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      ifSet(actionTarget, v => res.setActionTarget(v))
      ifSet(xalign, v => res.setXalign(v))
      res.build()
    }
    
    res.asInstanceOf[SpinRow]
  }
  
  def apply(
    adjustment: Opt[org.gnome.gtk.Adjustment] = UnsetParam, subtitle: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, actionTarget: Opt[org.gnome.glib.Variant] = UnsetParam, xalign: Opt[Float] = UnsetParam,
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
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
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
  ): VarContextAction[SpinRow] = {
    val res = uninitialized(adjustment, subtitle, cssName, heightRequest, widthRequest, accessibleRole, actionTarget, xalign)
    guarana.gtk.adw.SpinRow.init(res)
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
        