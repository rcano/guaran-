package guarana
package gtk
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
    export unwrap.onEdgeOvershot, unwrap.onEdgeReached, unwrap.onMoveFocusOut, unwrap.onScrollChild
  }
  def init(v: ScrolledWindow): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ScrolledWindow = {
    val res = new org.gnome.gtk.ScrolledWindow()
    res.asInstanceOf[ScrolledWindow]
  }
}