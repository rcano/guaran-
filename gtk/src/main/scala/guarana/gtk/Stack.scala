
package guarana.gtk

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type Stack <: guarana.gtk.Widget  = org.gnome.gtk.Stack & guarana.gtk.Widget
object Stack extends VarsMap {
  val Hhomogeneous: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("hhomogeneous", _.getHhomogeneous(), _.setHhomogeneous(_), true)
  val InterpolateSize: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("interpolate-size", _.getInterpolateSize(), _.setInterpolateSize(_), true)
  val TransitionDuration: ExternalVar.Aux[Stack, Int] = ExternalVar[Stack, Int]("transition-duration", _.getTransitionDuration(), _.setTransitionDuration(_), true)
  val TransitionType: ExternalVar.Aux[Stack, org.gnome.gtk.StackTransitionType] = ExternalVar[Stack, org.gnome.gtk.StackTransitionType]("transition-type", _.getTransitionType(), _.setTransitionType(_), true)
  val Vhomogeneous: ExternalVar.Aux[Stack, Boolean] = ExternalVar[Stack, Boolean]("vhomogeneous", _.getVhomogeneous(), _.setVhomogeneous(_), true)

  

  extension (v: Stack) {
    def unwrap: org.gnome.gtk.Stack = v

    def hhomogeneous: Var.Aux[Boolean, v.type] = guarana.gtk.Stack.Hhomogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def interpolateSize: Var.Aux[Boolean, v.type] = guarana.gtk.Stack.InterpolateSize.asInstanceOf[Var.Aux[Boolean, v.type]]
    def transitionDuration: Var.Aux[Int, v.type] = guarana.gtk.Stack.TransitionDuration.asInstanceOf[Var.Aux[Int, v.type]]
    def transitionType: Var.Aux[org.gnome.gtk.StackTransitionType, v.type] = guarana.gtk.Stack.TransitionType.asInstanceOf[Var.Aux[org.gnome.gtk.StackTransitionType, v.type]]
    def vhomogeneous: Var.Aux[Boolean, v.type] = guarana.gtk.Stack.Vhomogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]

    

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

  def wrap(v: org.gnome.gtk.Stack): Stack = 
    val res = v.asInstanceOf[Stack]
    
    res

  def init(v: Stack): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(visibleChild: Opt[guarana.gtk.Widget], visibleChildName: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Stack = {
    val res = {
      val res = org.gnome.gtk.Stack.builder()
      ifSet(visibleChild, v => res.setVisibleChild(v.unwrap))
      ifSet(visibleChildName, v => res.setVisibleChildName(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[Stack]
  }
  
  def apply(
    visibleChild: Opt[guarana.gtk.Widget] = UnsetParam, visibleChildName: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
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
    hhomogeneous: Opt[Binding[Boolean]] = UnsetParam,
    interpolateSize: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transitionDuration: Opt[Binding[Int]] = UnsetParam,
    transitionType: Opt[Binding[org.gnome.gtk.StackTransitionType]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    vhomogeneous: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Stack] = {
    val res = uninitialized(visibleChild, visibleChildName, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.Stack.init(res)
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
    ifSet(hhomogeneous, res.hhomogeneous := _)
    ifSet(interpolateSize, res.interpolateSize := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(transitionDuration, res.transitionDuration := _)
    ifSet(transitionType, res.transitionType := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(vhomogeneous, res.vhomogeneous := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        