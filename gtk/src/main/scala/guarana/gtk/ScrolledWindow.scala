package guarana
package gtk
opaque type ScrolledWindow <: Widget = org.gnome.gtk.ScrolledWindow & Widget
object ScrolledWindow {
  val Child: ExternalVar.Aux[ScrolledWindow, org.gnome.gtk.Widget | Null] = ExternalVar[ScrolledWindow, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Hadjustment: ExternalVar.Aux[ScrolledWindow, org.gnome.gtk.Adjustment | Null] = ExternalVar[ScrolledWindow, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  val HasFrame: ExternalVar.Aux[ScrolledWindow, Boolean] = ExternalVar[ScrolledWindow, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val KineticScrolling: ExternalVar.Aux[ScrolledWindow, Boolean] = ExternalVar[ScrolledWindow, Boolean]("kinetic-scrolling", _.getKineticScrolling(), _.setKineticScrolling(_), true)
  val MaxContentHeight: ExternalVar.Aux[ScrolledWindow, Int] = ExternalVar[ScrolledWindow, Int]("max-content-height", _.getMaxContentHeight(), _.setMaxContentHeight(_), true)
  val MaxContentWidth: ExternalVar.Aux[ScrolledWindow, Int] = ExternalVar[ScrolledWindow, Int]("max-content-width", _.getMaxContentWidth(), _.setMaxContentWidth(_), true)
  val MinContentHeight: ExternalVar.Aux[ScrolledWindow, Int] = ExternalVar[ScrolledWindow, Int]("min-content-height", _.getMinContentHeight(), _.setMinContentHeight(_), true)
  val MinContentWidth: ExternalVar.Aux[ScrolledWindow, Int] = ExternalVar[ScrolledWindow, Int]("min-content-width", _.getMinContentWidth(), _.setMinContentWidth(_), true)
  val OverlayScrolling: ExternalVar.Aux[ScrolledWindow, Boolean] = ExternalVar[ScrolledWindow, Boolean]("overlay-scrolling", _.getOverlayScrolling(), _.setOverlayScrolling(_), true)
  val PropagateNaturalHeight: ExternalVar.Aux[ScrolledWindow, Boolean] = ExternalVar[ScrolledWindow, Boolean]("propagate-natural-height", _.getPropagateNaturalHeight(), _.setPropagateNaturalHeight(_), true)
  val PropagateNaturalWidth: ExternalVar.Aux[ScrolledWindow, Boolean] = ExternalVar[ScrolledWindow, Boolean]("propagate-natural-width", _.getPropagateNaturalWidth(), _.setPropagateNaturalWidth(_), true)
  val Vadjustment: ExternalVar.Aux[ScrolledWindow, org.gnome.gtk.Adjustment | Null] = ExternalVar[ScrolledWindow, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  ()
  extension (v: ScrolledWindow) {
    def unwrap: org.gnome.gtk.ScrolledWindow = v
    export unwrap.onEdgeOvershot, unwrap.onEdgeReached, unwrap.onMoveFocusOut, unwrap.onScrollChild
  }
}