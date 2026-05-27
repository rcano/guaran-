package guarana
package gtk
opaque type Viewport <: Widget = org.gnome.gtk.Viewport & Widget
object Viewport {
  val Child: ExternalVar.Aux[Viewport, org.gnome.gtk.Widget | Null] = ExternalVar[Viewport, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Hadjustment: ExternalVar.Aux[Viewport, org.gnome.gtk.Adjustment | Null] = ExternalVar[Viewport, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  val HscrollPolicy: ExternalVar.Aux[Viewport, org.gnome.gtk.ScrollablePolicy] = ExternalVar[Viewport, org.gnome.gtk.ScrollablePolicy]("hscroll-policy", _.getHscrollPolicy(), _.setHscrollPolicy(_), true)
  val ScrollToFocus: ExternalVar.Aux[Viewport, Boolean] = ExternalVar[Viewport, Boolean]("scroll-to-focus", _.getScrollToFocus(), _.setScrollToFocus(_), true)
  val Vadjustment: ExternalVar.Aux[Viewport, org.gnome.gtk.Adjustment | Null] = ExternalVar[Viewport, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  val VscrollPolicy: ExternalVar.Aux[Viewport, org.gnome.gtk.ScrollablePolicy] = ExternalVar[Viewport, org.gnome.gtk.ScrollablePolicy]("vscroll-policy", _.getVscrollPolicy(), _.setVscrollPolicy(_), true)
  ()
  extension (v: Viewport) {
    def unwrap: org.gnome.gtk.Viewport = v
  }
  def init(v: Viewport): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Viewport = {
    val res = new org.gnome.gtk.Viewport()
    res.asInstanceOf[Viewport]
  }
}