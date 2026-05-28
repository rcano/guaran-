package guarana
package gtk
import util.*
opaque type ScrolledWindow <: Widget = org.gnome.gtk.ScrolledWindow & Widget
object ScrolledWindow {
  val Child: ExternalVar.Aux[ScrolledWindow, org.gnome.gtk.Widget | Null] = ExternalVar[ScrolledWindow, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
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
  ()
  extension (v: ScrolledWindow) {
    def unwrap: org.gnome.gtk.ScrolledWindow = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def hasFrame: Var.Aux[Boolean, v.type] = HasFrame.asInstanceOf[Var.Aux[Boolean, v.type]]
    def kineticScrolling: Var.Aux[Boolean, v.type] = KineticScrolling.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxContentHeight: Var.Aux[Int, v.type] = MaxContentHeight.asInstanceOf[Var.Aux[Int, v.type]]
    def maxContentWidth: Var.Aux[Int, v.type] = MaxContentWidth.asInstanceOf[Var.Aux[Int, v.type]]
    def minContentHeight: Var.Aux[Int, v.type] = MinContentHeight.asInstanceOf[Var.Aux[Int, v.type]]
    def minContentWidth: Var.Aux[Int, v.type] = MinContentWidth.asInstanceOf[Var.Aux[Int, v.type]]
    def overlayScrolling: Var.Aux[Boolean, v.type] = OverlayScrolling.asInstanceOf[Var.Aux[Boolean, v.type]]
    def placement: Var.Aux[org.gnome.gtk.CornerType, v.type] = Placement.asInstanceOf[Var.Aux[org.gnome.gtk.CornerType, v.type]]
    def propagateNaturalHeight: Var.Aux[Boolean, v.type] = PropagateNaturalHeight.asInstanceOf[Var.Aux[Boolean, v.type]]
    def propagateNaturalWidth: Var.Aux[Boolean, v.type] = PropagateNaturalWidth.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onEdgeOvershot, unwrap.onEdgeReached, unwrap.onMoveFocusOut, unwrap.onScrollChild
  }
  def init(v: ScrolledWindow): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ScrolledWindow = {
    val res = new org.gnome.gtk.ScrolledWindow()
    res.asInstanceOf[ScrolledWindow]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, hasFrame: Opt[Boolean] = UnsetParam, kineticScrolling: Opt[Boolean] = UnsetParam, maxContentHeight: Opt[Int] = UnsetParam, maxContentWidth: Opt[Int] = UnsetParam, minContentHeight: Opt[Int] = UnsetParam, minContentWidth: Opt[Int] = UnsetParam, overlayScrolling: Opt[Boolean] = UnsetParam, placement: Opt[org.gnome.gtk.CornerType] = UnsetParam, propagateNaturalHeight: Opt[Boolean] = UnsetParam, propagateNaturalWidth: Opt[Boolean] = UnsetParam): VarContextAction[ScrolledWindow] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    ifSet(hasFrame, res.hasFrame := _)
    ifSet(kineticScrolling, res.kineticScrolling := _)
    ifSet(maxContentHeight, res.maxContentHeight := _)
    ifSet(maxContentWidth, res.maxContentWidth := _)
    ifSet(minContentHeight, res.minContentHeight := _)
    ifSet(minContentWidth, res.minContentWidth := _)
    ifSet(overlayScrolling, res.overlayScrolling := _)
    ifSet(placement, res.placement := _)
    ifSet(propagateNaturalHeight, res.propagateNaturalHeight := _)
    ifSet(propagateNaturalWidth, res.propagateNaturalWidth := _)
    res
  }
}