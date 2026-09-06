
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type Flap <: guarana.gtk.Widget  = org.gnome.adw.Flap & guarana.gtk.Widget
object Flap extends VarsMap {
  @deprecated("", "") val Content: ExternalVar.Aux[Flap, guarana.gtk.Widget | Null] = ExternalVar[Flap, guarana.gtk.Widget | Null]("content", _.getContent().?(guarana.gtk.Widget.wrap), (n, v) => n.setContent(v.?(_.unwrap)), true)
  @deprecated("", "") val Flap: ExternalVar.Aux[Flap, guarana.gtk.Widget | Null] = ExternalVar[Flap, guarana.gtk.Widget | Null]("flap", _.getFlap().?(guarana.gtk.Widget.wrap), (n, v) => n.setFlap(v.?(_.unwrap)), true)
  @deprecated("", "") val FlapPosition: ExternalVar.Aux[Flap, org.gnome.gtk.PackType] = ExternalVar[Flap, org.gnome.gtk.PackType]("flap-position", _.getFlapPosition(), _.setFlapPosition(_), true)
  @deprecated("", "") val FoldDuration: ExternalVar.Aux[Flap, Int] = ExternalVar[Flap, Int]("fold-duration", _.getFoldDuration(), _.setFoldDuration(_), true)
  @deprecated("", "") val FoldPolicy: ExternalVar.Aux[Flap, org.gnome.adw.FlapFoldPolicy] = ExternalVar[Flap, org.gnome.adw.FlapFoldPolicy]("fold-policy", _.getFoldPolicy(), _.setFoldPolicy(_), true)
  @deprecated("", "") val FoldThresholdPolicy: ExternalVar.Aux[Flap, org.gnome.adw.FoldThresholdPolicy] = ExternalVar[Flap, org.gnome.adw.FoldThresholdPolicy]("fold-threshold-policy", _.getFoldThresholdPolicy(), _.setFoldThresholdPolicy(_), true)
  @deprecated("", "") val Locked: ExternalVar.Aux[Flap, Boolean] = ExternalVar[Flap, Boolean]("locked", _.getLocked(), _.setLocked(_), true)
  @deprecated("", "") val Modal: ExternalVar.Aux[Flap, Boolean] = ExternalVar[Flap, Boolean]("modal", _.getModal(), _.setModal(_), true)
  val Orientation: ExternalVar.Aux[Flap, org.gnome.gtk.Orientation] = ExternalVar[Flap, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  @deprecated("", "") val RevealFlap: ExternalVar.Aux[Flap, Boolean] = ExternalVar[Flap, Boolean]("reveal-flap", _.getRevealFlap(), _.setRevealFlap(_), true)
  @deprecated("", "") val RevealParams: ExternalVar.Aux[Flap, org.gnome.adw.SpringParams] = ExternalVar[Flap, org.gnome.adw.SpringParams]("reveal-params", _.getRevealParams(), _.setRevealParams(_), true)
  @deprecated("", "") val Separator: ExternalVar.Aux[Flap, guarana.gtk.Widget | Null] = ExternalVar[Flap, guarana.gtk.Widget | Null]("separator", _.getSeparator().?(guarana.gtk.Widget.wrap), (n, v) => n.setSeparator(v.?(_.unwrap)), true)
  @deprecated("", "") val SwipeToClose: ExternalVar.Aux[Flap, Boolean] = ExternalVar[Flap, Boolean]("swipe-to-close", _.getSwipeToClose(), _.setSwipeToClose(_), true)
  @deprecated("", "") val SwipeToOpen: ExternalVar.Aux[Flap, Boolean] = ExternalVar[Flap, Boolean]("swipe-to-open", _.getSwipeToOpen(), _.setSwipeToOpen(_), true)
  @deprecated("", "") val TransitionType: ExternalVar.Aux[Flap, org.gnome.adw.FlapTransitionType] = ExternalVar[Flap, org.gnome.adw.FlapTransitionType]("transition-type", _.getTransitionType(), _.setTransitionType(_), true)

  

  extension (v: Flap) {
    def unwrap: org.gnome.adw.Flap = v

    @deprecated("", "") def content: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.Flap.Content.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    @deprecated("", "") def flap: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.Flap.Flap.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    @deprecated("", "") def flapPosition: Var.Aux[org.gnome.gtk.PackType, v.type] = guarana.gtk.adw.Flap.FlapPosition.asInstanceOf[Var.Aux[org.gnome.gtk.PackType, v.type]]
    @deprecated("", "") def foldDuration: Var.Aux[Int, v.type] = guarana.gtk.adw.Flap.FoldDuration.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def foldPolicy: Var.Aux[org.gnome.adw.FlapFoldPolicy, v.type] = guarana.gtk.adw.Flap.FoldPolicy.asInstanceOf[Var.Aux[org.gnome.adw.FlapFoldPolicy, v.type]]
    @deprecated("", "") def foldThresholdPolicy: Var.Aux[org.gnome.adw.FoldThresholdPolicy, v.type] = guarana.gtk.adw.Flap.FoldThresholdPolicy.asInstanceOf[Var.Aux[org.gnome.adw.FoldThresholdPolicy, v.type]]
    @deprecated("", "") def locked: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Flap.Locked.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def modal: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Flap.Modal.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.adw.Flap.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    @deprecated("", "") def revealFlap: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Flap.RevealFlap.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def revealParams: Var.Aux[org.gnome.adw.SpringParams, v.type] = guarana.gtk.adw.Flap.RevealParams.asInstanceOf[Var.Aux[org.gnome.adw.SpringParams, v.type]]
    @deprecated("", "") def separator: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.Flap.Separator.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    @deprecated("", "") def swipeToClose: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Flap.SwipeToClose.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def swipeToOpen: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Flap.SwipeToOpen.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def transitionType: Var.Aux[org.gnome.adw.FlapTransitionType, v.type] = guarana.gtk.adw.Flap.TransitionType.asInstanceOf[Var.Aux[org.gnome.adw.FlapTransitionType, v.type]]

    

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

  def wrap(v: org.gnome.adw.Flap): Flap = 
    val res = v.asInstanceOf[Flap]
    
    res

  def init(v: Flap): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Flap = {
    val res = {
      val res = org.gnome.adw.Flap.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[Flap]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    content: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    flap: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    flapPosition: Opt[Binding[org.gnome.gtk.PackType]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    foldDuration: Opt[Binding[Int]] = UnsetParam,
    foldPolicy: Opt[Binding[org.gnome.adw.FlapFoldPolicy]] = UnsetParam,
    foldThresholdPolicy: Opt[Binding[org.gnome.adw.FoldThresholdPolicy]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    locked: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    modal: Opt[Binding[Boolean]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    revealFlap: Opt[Binding[Boolean]] = UnsetParam,
    revealParams: Opt[Binding[org.gnome.adw.SpringParams]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    separator: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    swipeToClose: Opt[Binding[Boolean]] = UnsetParam,
    swipeToOpen: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transitionType: Opt[Binding[org.gnome.adw.FlapTransitionType]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Flap] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.Flap.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(content, res.content := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(flap, res.flap := _)
    ifSet(flapPosition, res.flapPosition := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(foldDuration, res.foldDuration := _)
    ifSet(foldPolicy, res.foldPolicy := _)
    ifSet(foldThresholdPolicy, res.foldThresholdPolicy := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(locked, res.locked := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(modal, res.modal := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(revealFlap, res.revealFlap := _)
    ifSet(revealParams, res.revealParams := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(separator, res.separator := _)
    ifSet(swipeToClose, res.swipeToClose := _)
    ifSet(swipeToOpen, res.swipeToOpen := _)
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
        