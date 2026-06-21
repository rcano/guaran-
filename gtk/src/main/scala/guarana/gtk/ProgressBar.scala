
package guarana.gtk

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type ProgressBar <: guarana.gtk.Widget  = org.gnome.gtk.ProgressBar & guarana.gtk.Widget
object ProgressBar extends VarsMap {
  val Ellipsize: ExternalVar.Aux[ProgressBar, org.gnome.pango.EllipsizeMode] = ExternalVar[ProgressBar, org.gnome.pango.EllipsizeMode]("ellipsize", _.getEllipsize(), _.setEllipsize(_), true)
  val Fraction: ExternalVar.Aux[ProgressBar, Double] = ExternalVar[ProgressBar, Double]("fraction", _.getFraction(), _.setFraction(_), true)
  val Inverted: ExternalVar.Aux[ProgressBar, Boolean] = ExternalVar[ProgressBar, Boolean]("inverted", _.getInverted(), _.setInverted(_), true)
  val Orientation: ExternalVar.Aux[ProgressBar, org.gnome.gtk.Orientation] = ExternalVar[ProgressBar, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val PulseStep: ExternalVar.Aux[ProgressBar, Double] = ExternalVar[ProgressBar, Double]("pulse-step", _.getPulseStep(), _.setPulseStep(_), true)
  val ShowText: ExternalVar.Aux[ProgressBar, Boolean] = ExternalVar[ProgressBar, Boolean]("show-text", _.getShowText(), _.setShowText(_), true)
  val Text: ExternalVar.Aux[ProgressBar, java.lang.String | Null] = ExternalVar[ProgressBar, java.lang.String | Null]("text", _.getText(), _.setText(_), true)

  

  extension (v: ProgressBar) {
    def unwrap: org.gnome.gtk.ProgressBar = v

    def ellipsize: Var.Aux[org.gnome.pango.EllipsizeMode, v.type] = guarana.gtk.ProgressBar.Ellipsize.asInstanceOf[Var.Aux[org.gnome.pango.EllipsizeMode, v.type]]
    def fraction: Var.Aux[Double, v.type] = guarana.gtk.ProgressBar.Fraction.asInstanceOf[Var.Aux[Double, v.type]]
    def inverted: Var.Aux[Boolean, v.type] = guarana.gtk.ProgressBar.Inverted.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.ProgressBar.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def pulseStep: Var.Aux[Double, v.type] = guarana.gtk.ProgressBar.PulseStep.asInstanceOf[Var.Aux[Double, v.type]]
    def showText: Var.Aux[Boolean, v.type] = guarana.gtk.ProgressBar.ShowText.asInstanceOf[Var.Aux[Boolean, v.type]]
    def text: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.ProgressBar.Text.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]

    

    export unwrap.{
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.ProgressBar): ProgressBar = 
    val res = v.asInstanceOf[ProgressBar]
    
    res

  def init(v: ProgressBar): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): ProgressBar = {
    val res = {
      val res = org.gnome.gtk.ProgressBar.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[ProgressBar]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    ellipsize: Opt[Binding[org.gnome.pango.EllipsizeMode]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    fraction: Opt[Binding[Double]] = UnsetParam,
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
    pulseStep: Opt[Binding[Double]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showText: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[ProgressBar] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.ProgressBar.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
        