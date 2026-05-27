package guarana
package gtk
opaque type Viewport <: Widget = org.gnome.gtk.Viewport & Widget
object Viewport {
  val ScrollToFocus: ExternalVar.Aux[Viewport, Boolean] = ExternalVar[Viewport, Boolean]("scroll-to-focus", _.getScrollToFocus(), _.setScrollToFocus(_), true)
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