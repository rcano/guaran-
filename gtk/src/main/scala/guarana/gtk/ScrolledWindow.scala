
package guarana
package gtk

import guarana.util.*

opaque type ScrolledWindow <: guarana.gtk.Widget  = org.gnome.gtk.ScrolledWindow & guarana.gtk.Widget
object ScrolledWindow extends VarsMap {
  val Child: ExternalVar.Aux[ScrolledWindow, guarana.gtk.Widget | Null] = ExternalVar[ScrolledWindow, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val HasFrame: ExternalVar.Aux[ScrolledWindow, Boolean] = ExternalVar[ScrolledWindow, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val KineticScrolling: ExternalVar.Aux[ScrolledWindow, Boolean] = ExternalVar[ScrolledWindow, Boolean]("kinetic-scrolling", _.getKineticScrolling(), _.setKineticScrolling(_), true)
  val MaxContentHeight: ExternalVar.Aux[ScrolledWindow, Int] = ExternalVar[ScrolledWindow, Int]("max-content-height", _.getMaxContentHeight(), _.setMaxContentHeight(_), true)
  val MaxContentWidth: ExternalVar.Aux[ScrolledWindow, Int] = ExternalVar[ScrolledWindow, Int]("max-content-width", _.getMaxContentWidth(), _.setMaxContentWidth(_), true)
  val MinContentHeight: ExternalVar.Aux[ScrolledWindow, Int] = ExternalVar[ScrolledWindow, Int]("min-content-height", _.getMinContentHeight(), _.setMinContentHeight(_), true)
  val MinContentWidth: ExternalVar.Aux[ScrolledWindow, Int] = ExternalVar[ScrolledWindow, Int]("min-content-width", _.getMinContentWidth(), _.setMinContentWidth(_), true)
  val OverlayScrolling: ExternalVar.Aux[ScrolledWindow, Boolean] = ExternalVar[ScrolledWindow, Boolean]("overlay-scrolling", _.getOverlayScrolling(), _.setOverlayScrolling(_), true)
  val Placement: ExternalVar.Aux[ScrolledWindow, org.gnome.gtk.CornerType] = ExternalVar[ScrolledWindow, org.gnome.gtk.CornerType]("placement", _.getPlacement(), _.setPlacement(_), true)
  val PropagateNaturalHeight: ExternalVar.Aux[ScrolledWindow, Boolean] = ExternalVar[ScrolledWindow, Boolean]("propagate-natural-height", _.getPropagateNaturalHeight(), _.setPropagateNaturalHeight(_), true)
  val PropagateNaturalWidth: ExternalVar.Aux[ScrolledWindow, Boolean] = ExternalVar[ScrolledWindow, Boolean]("propagate-natural-width", _.getPropagateNaturalWidth(), _.setPropagateNaturalWidth(_), true)

  

  extension (v: ScrolledWindow) {
    def unwrap: org.gnome.gtk.ScrolledWindow = v

    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.ScrolledWindow.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def hasFrame: Var.Aux[Boolean, v.type] = guarana.gtk.ScrolledWindow.HasFrame.asInstanceOf[Var.Aux[Boolean, v.type]]
    def kineticScrolling: Var.Aux[Boolean, v.type] = guarana.gtk.ScrolledWindow.KineticScrolling.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxContentHeight: Var.Aux[Int, v.type] = guarana.gtk.ScrolledWindow.MaxContentHeight.asInstanceOf[Var.Aux[Int, v.type]]
    def maxContentWidth: Var.Aux[Int, v.type] = guarana.gtk.ScrolledWindow.MaxContentWidth.asInstanceOf[Var.Aux[Int, v.type]]
    def minContentHeight: Var.Aux[Int, v.type] = guarana.gtk.ScrolledWindow.MinContentHeight.asInstanceOf[Var.Aux[Int, v.type]]
    def minContentWidth: Var.Aux[Int, v.type] = guarana.gtk.ScrolledWindow.MinContentWidth.asInstanceOf[Var.Aux[Int, v.type]]
    def overlayScrolling: Var.Aux[Boolean, v.type] = guarana.gtk.ScrolledWindow.OverlayScrolling.asInstanceOf[Var.Aux[Boolean, v.type]]
    def placement: Var.Aux[org.gnome.gtk.CornerType, v.type] = guarana.gtk.ScrolledWindow.Placement.asInstanceOf[Var.Aux[org.gnome.gtk.CornerType, v.type]]
    def propagateNaturalHeight: Var.Aux[Boolean, v.type] = guarana.gtk.ScrolledWindow.PropagateNaturalHeight.asInstanceOf[Var.Aux[Boolean, v.type]]
    def propagateNaturalWidth: Var.Aux[Boolean, v.type] = guarana.gtk.ScrolledWindow.PropagateNaturalWidth.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onDestroy,
      onDirectionChanged,
      onEdgeOvershot,
      onEdgeReached,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onMoveFocusOut,
      onNotify,
      onQueryTooltip,
      onRealize,
      onScrollChild,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.ScrolledWindow): ScrolledWindow = 
    val res = v.asInstanceOf[ScrolledWindow]
    
    res

  def init(v: ScrolledWindow): Toolkit ?=> Unit = (tk: Toolkit) ?=> {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(): ScrolledWindow = {
    val res = new org.gnome.gtk.ScrolledWindow()
    
    res.asInstanceOf[ScrolledWindow]
  }
  
  def apply(
    
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasFrame: Opt[Binding[Boolean]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    kineticScrolling: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    maxContentHeight: Opt[Binding[Int]] = UnsetParam,
    maxContentWidth: Opt[Binding[Int]] = UnsetParam,
    minContentHeight: Opt[Binding[Int]] = UnsetParam,
    minContentWidth: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    overlayScrolling: Opt[Binding[Boolean]] = UnsetParam,
    placement: Opt[Binding[org.gnome.gtk.CornerType]] = UnsetParam,
    propagateNaturalHeight: Opt[Binding[Boolean]] = UnsetParam,
    propagateNaturalWidth: Opt[Binding[Boolean]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Toolkit ?=> VarContextAction[ScrolledWindow] = {
    val res = uninitialized()
    guarana.gtk.ScrolledWindow.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasFrame, res.hasFrame := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(kineticScrolling, res.kineticScrolling := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(maxContentHeight, res.maxContentHeight := _)
    ifSet(maxContentWidth, res.maxContentWidth := _)
    ifSet(minContentHeight, res.minContentHeight := _)
    ifSet(minContentWidth, res.minContentWidth := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(overlayScrolling, res.overlayScrolling := _)
    ifSet(placement, res.placement := _)
    ifSet(propagateNaturalHeight, res.propagateNaturalHeight := _)
    ifSet(propagateNaturalWidth, res.propagateNaturalWidth := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
  
}
        