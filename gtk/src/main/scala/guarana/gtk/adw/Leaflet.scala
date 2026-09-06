
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type Leaflet <: guarana.gtk.Widget  = org.gnome.adw.Leaflet & guarana.gtk.Widget
object Leaflet extends VarsMap {
  @deprecated("", "") val CanNavigateBack: ExternalVar.Aux[Leaflet, Boolean] = ExternalVar[Leaflet, Boolean]("can-navigate-back", _.getCanNavigateBack(), _.setCanNavigateBack(_), true)
  @deprecated("", "") val CanNavigateForward: ExternalVar.Aux[Leaflet, Boolean] = ExternalVar[Leaflet, Boolean]("can-navigate-forward", _.getCanNavigateForward(), _.setCanNavigateForward(_), true)
  @deprecated("", "") val CanUnfold: ExternalVar.Aux[Leaflet, Boolean] = ExternalVar[Leaflet, Boolean]("can-unfold", _.getCanUnfold(), _.setCanUnfold(_), true)
  @deprecated("", "") val ChildTransitionParams: ExternalVar.Aux[Leaflet, org.gnome.adw.SpringParams] = ExternalVar[Leaflet, org.gnome.adw.SpringParams]("child-transition-params", _.getChildTransitionParams(), _.setChildTransitionParams(_), true)
  @deprecated("", "") val FoldThresholdPolicy: ExternalVar.Aux[Leaflet, org.gnome.adw.FoldThresholdPolicy] = ExternalVar[Leaflet, org.gnome.adw.FoldThresholdPolicy]("fold-threshold-policy", _.getFoldThresholdPolicy(), _.setFoldThresholdPolicy(_), true)
  @deprecated("", "") val Homogeneous: ExternalVar.Aux[Leaflet, Boolean] = ExternalVar[Leaflet, Boolean]("homogeneous", _.getHomogeneous(), _.setHomogeneous(_), true)
  @deprecated("", "") val ModeTransitionDuration: ExternalVar.Aux[Leaflet, Int] = ExternalVar[Leaflet, Int]("mode-transition-duration", _.getModeTransitionDuration(), _.setModeTransitionDuration(_), true)
  val Orientation: ExternalVar.Aux[Leaflet, org.gnome.gtk.Orientation] = ExternalVar[Leaflet, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  @deprecated("", "") val TransitionType: ExternalVar.Aux[Leaflet, org.gnome.adw.LeafletTransitionType] = ExternalVar[Leaflet, org.gnome.adw.LeafletTransitionType]("transition-type", _.getTransitionType(), _.setTransitionType(_), true)

  

  extension (v: Leaflet) {
    def unwrap: org.gnome.adw.Leaflet = v

    @deprecated("", "") def canNavigateBack: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Leaflet.CanNavigateBack.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def canNavigateForward: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Leaflet.CanNavigateForward.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def canUnfold: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Leaflet.CanUnfold.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def childTransitionParams: Var.Aux[org.gnome.adw.SpringParams, v.type] = guarana.gtk.adw.Leaflet.ChildTransitionParams.asInstanceOf[Var.Aux[org.gnome.adw.SpringParams, v.type]]
    @deprecated("", "") def foldThresholdPolicy: Var.Aux[org.gnome.adw.FoldThresholdPolicy, v.type] = guarana.gtk.adw.Leaflet.FoldThresholdPolicy.asInstanceOf[Var.Aux[org.gnome.adw.FoldThresholdPolicy, v.type]]
    @deprecated("", "") def homogeneous: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Leaflet.Homogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def modeTransitionDuration: Var.Aux[Int, v.type] = guarana.gtk.adw.Leaflet.ModeTransitionDuration.asInstanceOf[Var.Aux[Int, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.adw.Leaflet.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    @deprecated("", "") def transitionType: Var.Aux[org.gnome.adw.LeafletTransitionType, v.type] = guarana.gtk.adw.Leaflet.TransitionType.asInstanceOf[Var.Aux[org.gnome.adw.LeafletTransitionType, v.type]]

    

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

  def wrap(v: org.gnome.adw.Leaflet): Leaflet = 
    val res = v.asInstanceOf[Leaflet]
    
    res

  def init(v: Leaflet): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(visibleChild: Opt[guarana.gtk.Widget], visibleChildName: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Leaflet = {
    val res = {
      val res = org.gnome.adw.Leaflet.builder()
      ifSet(visibleChild, v => res.setVisibleChild(v.unwrap))
      ifSet(visibleChildName, v => res.setVisibleChildName(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[Leaflet]
  }
  
  def apply(
    visibleChild: Opt[guarana.gtk.Widget] = UnsetParam, visibleChildName: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canNavigateBack: Opt[Binding[Boolean]] = UnsetParam,
    canNavigateForward: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    canUnfold: Opt[Binding[Boolean]] = UnsetParam,
    childTransitionParams: Opt[Binding[org.gnome.adw.SpringParams]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    foldThresholdPolicy: Opt[Binding[org.gnome.adw.FoldThresholdPolicy]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    homogeneous: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    modeTransitionDuration: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transitionType: Opt[Binding[org.gnome.adw.LeafletTransitionType]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Leaflet] = {
    val res = uninitialized(visibleChild, visibleChildName, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.Leaflet.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canNavigateBack, res.canNavigateBack := _)
    ifSet(canNavigateForward, res.canNavigateForward := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(canUnfold, res.canUnfold := _)
    ifSet(childTransitionParams, res.childTransitionParams := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(foldThresholdPolicy, res.foldThresholdPolicy := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(homogeneous, res.homogeneous := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(modeTransitionDuration, res.modeTransitionDuration := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(transitionType, res.transitionType := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        