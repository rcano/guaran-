
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type Squeezer <: guarana.gtk.Widget  = org.gnome.adw.Squeezer & guarana.gtk.Widget
object Squeezer extends VarsMap {
  @deprecated("", "") val AllowNone: ExternalVar.Aux[Squeezer, Boolean] = ExternalVar[Squeezer, Boolean]("allow-none", _.getAllowNone(), _.setAllowNone(_), true)
  @deprecated("", "") val Homogeneous: ExternalVar.Aux[Squeezer, Boolean] = ExternalVar[Squeezer, Boolean]("homogeneous", _.getHomogeneous(), _.setHomogeneous(_), true)
  @deprecated("", "") val InterpolateSize: ExternalVar.Aux[Squeezer, Boolean] = ExternalVar[Squeezer, Boolean]("interpolate-size", _.getInterpolateSize(), _.setInterpolateSize(_), true)
  val Orientation: ExternalVar.Aux[Squeezer, org.gnome.gtk.Orientation] = ExternalVar[Squeezer, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  @deprecated("", "") val SwitchThresholdPolicy: ExternalVar.Aux[Squeezer, org.gnome.adw.FoldThresholdPolicy] = ExternalVar[Squeezer, org.gnome.adw.FoldThresholdPolicy]("switch-threshold-policy", _.getSwitchThresholdPolicy(), _.setSwitchThresholdPolicy(_), true)
  @deprecated("", "") val TransitionDuration: ExternalVar.Aux[Squeezer, Int] = ExternalVar[Squeezer, Int]("transition-duration", _.getTransitionDuration(), _.setTransitionDuration(_), true)
  @deprecated("", "") val TransitionType: ExternalVar.Aux[Squeezer, org.gnome.adw.SqueezerTransitionType] = ExternalVar[Squeezer, org.gnome.adw.SqueezerTransitionType]("transition-type", _.getTransitionType(), _.setTransitionType(_), true)
  @deprecated("", "") val Xalign: ExternalVar.Aux[Squeezer, Float] = ExternalVar[Squeezer, Float]("xalign", _.getXalign(), _.setXalign(_), true)
  @deprecated("", "") val Yalign: ExternalVar.Aux[Squeezer, Float] = ExternalVar[Squeezer, Float]("yalign", _.getYalign(), _.setYalign(_), true)

  

  extension (v: Squeezer) {
    def unwrap: org.gnome.adw.Squeezer = v

    @deprecated("", "") def allowNone: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Squeezer.AllowNone.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def homogeneous: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Squeezer.Homogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def interpolateSize: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Squeezer.InterpolateSize.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.adw.Squeezer.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    @deprecated("", "") def switchThresholdPolicy: Var.Aux[org.gnome.adw.FoldThresholdPolicy, v.type] = guarana.gtk.adw.Squeezer.SwitchThresholdPolicy.asInstanceOf[Var.Aux[org.gnome.adw.FoldThresholdPolicy, v.type]]
    @deprecated("", "") def transitionDuration: Var.Aux[Int, v.type] = guarana.gtk.adw.Squeezer.TransitionDuration.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def transitionType: Var.Aux[org.gnome.adw.SqueezerTransitionType, v.type] = guarana.gtk.adw.Squeezer.TransitionType.asInstanceOf[Var.Aux[org.gnome.adw.SqueezerTransitionType, v.type]]
    @deprecated("", "") def xalign: Var.Aux[Float, v.type] = guarana.gtk.adw.Squeezer.Xalign.asInstanceOf[Var.Aux[Float, v.type]]
    @deprecated("", "") def yalign: Var.Aux[Float, v.type] = guarana.gtk.adw.Squeezer.Yalign.asInstanceOf[Var.Aux[Float, v.type]]

    

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

  def wrap(v: org.gnome.adw.Squeezer): Squeezer = 
    val res = v.asInstanceOf[Squeezer]
    
    res

  def init(v: Squeezer): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Squeezer = {
    val res = {
      val res = org.gnome.adw.Squeezer.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[Squeezer]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    allowNone: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    homogeneous: Opt[Binding[Boolean]] = UnsetParam,
    interpolateSize: Opt[Binding[Boolean]] = UnsetParam,
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
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    switchThresholdPolicy: Opt[Binding[org.gnome.adw.FoldThresholdPolicy]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transitionDuration: Opt[Binding[Int]] = UnsetParam,
    transitionType: Opt[Binding[org.gnome.adw.SqueezerTransitionType]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    xalign: Opt[Binding[Float]] = UnsetParam,
    yalign: Opt[Binding[Float]] = UnsetParam
  ): VarContextAction[Squeezer] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.Squeezer.init(res)
    ifSet(allowNone, res.allowNone := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(homogeneous, res.homogeneous := _)
    ifSet(interpolateSize, res.interpolateSize := _)
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
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(switchThresholdPolicy, res.switchThresholdPolicy := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(transitionDuration, res.transitionDuration := _)
    ifSet(transitionType, res.transitionType := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(xalign, res.xalign := _)
    ifSet(yalign, res.yalign := _)
    
    res
  }
  
}
        