
package guarana
package gtk

import guarana.util.*

opaque type Range <: guarana.gtk.Widget  = org.gnome.gtk.Range & guarana.gtk.Widget
object Range extends VarsMap {
  val Adjustment: ExternalVar.Aux[Range, org.gnome.gtk.Adjustment] = ExternalVar[Range, org.gnome.gtk.Adjustment]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  val FillLevel: ExternalVar.Aux[Range, Double] = ExternalVar[Range, Double]("fill-level", _.getFillLevel(), _.setFillLevel(_), true)
  val Flippable: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("flippable", _.getFlippable(), _.setFlippable(_), true)
  val Inverted: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("inverted", _.getInverted(), _.setInverted(_), true)
  val Orientation: ExternalVar.Aux[Range, org.gnome.gtk.Orientation] = ExternalVar[Range, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val RestrictToFillLevel: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("restrict-to-fill-level", _.getRestrictToFillLevel(), _.setRestrictToFillLevel(_), true)
  val RoundDigits: ExternalVar.Aux[Range, Int] = ExternalVar[Range, Int]("round-digits", _.getRoundDigits(), _.setRoundDigits(_), true)
  val ShowFillLevel: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("show-fill-level", _.getShowFillLevel(), _.setShowFillLevel(_), true)
  val SliderSizeFixed: ExternalVar.Aux[Range, Boolean] = ExternalVar[Range, Boolean]("slider-size-fixed", _.getSliderSizeFixed(), _.setSliderSizeFixed(_), true)
  val Value: ExternalVar.Aux[Range, Double] = ExternalVar[Range, Double]("value", _.getValue(), _.setValue(_), true)

  

  extension (v: Range) {
    def unwrap: org.gnome.gtk.Range = v

    def adjustment: Var.Aux[org.gnome.gtk.Adjustment, v.type] = guarana.gtk.Range.Adjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment, v.type]]
    def fillLevel: Var.Aux[Double, v.type] = guarana.gtk.Range.FillLevel.asInstanceOf[Var.Aux[Double, v.type]]
    def flippable: Var.Aux[Boolean, v.type] = guarana.gtk.Range.Flippable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def inverted: Var.Aux[Boolean, v.type] = guarana.gtk.Range.Inverted.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.Range.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def restrictToFillLevel: Var.Aux[Boolean, v.type] = guarana.gtk.Range.RestrictToFillLevel.asInstanceOf[Var.Aux[Boolean, v.type]]
    def roundDigits: Var.Aux[Int, v.type] = guarana.gtk.Range.RoundDigits.asInstanceOf[Var.Aux[Int, v.type]]
    def showFillLevel: Var.Aux[Boolean, v.type] = guarana.gtk.Range.ShowFillLevel.asInstanceOf[Var.Aux[Boolean, v.type]]
    def sliderSizeFixed: Var.Aux[Boolean, v.type] = guarana.gtk.Range.SliderSizeFixed.asInstanceOf[Var.Aux[Boolean, v.type]]
    def value: Var.Aux[Double, v.type] = guarana.gtk.Range.Value.asInstanceOf[Var.Aux[Double, v.type]]

    

    export unwrap.{
      onAdjustBounds,
      onChangeValue,
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onMoveSlider,
      onNotify,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize,
      onValueChanged
    }
  }

  def wrap(v: org.gnome.gtk.Range): Range = 
    val res = v.asInstanceOf[Range]
    
    res

  def init(v: Range): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(): Range = {
    val res = new org.gnome.gtk.Range()
    
    res.asInstanceOf[Range]
  }
  
  def apply(
    
    adjustment: Opt[Binding[org.gnome.gtk.Adjustment]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    fillLevel: Opt[Binding[Double]] = UnsetParam,
    flippable: Opt[Binding[Boolean]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    inverted: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    restrictToFillLevel: Opt[Binding[Boolean]] = UnsetParam,
    roundDigits: Opt[Binding[Int]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showFillLevel: Opt[Binding[Boolean]] = UnsetParam,
    sliderSizeFixed: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    value: Opt[Binding[Double]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Range] = {
    val res = uninitialized()
    guarana.gtk.Range.init(res)
    ifSet(adjustment, res.adjustment := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(fillLevel, res.fillLevel := _)
    ifSet(flippable, res.flippable := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
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
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(restrictToFillLevel, res.restrictToFillLevel := _)
    ifSet(roundDigits, res.roundDigits := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showFillLevel, res.showFillLevel := _)
    ifSet(sliderSizeFixed, res.sliderSizeFixed := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(value, res.value := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
  
}
        