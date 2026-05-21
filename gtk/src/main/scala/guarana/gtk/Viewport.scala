package guarana
package gtk
opaque type Viewport <: Widget = org.gnome.gtk.Viewport & Widget
object Viewport {
  val Child: ExternalVar.Aux[Viewport, org.gnome.gtk.Widget | Null] = ExternalVar[Viewport, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val ScrollToFocus: ExternalVar.Aux[Viewport, Boolean] = ExternalVar[Viewport, Boolean]("scroll-to-focus", _.getScrollToFocus(), _.setScrollToFocus(_), true)
  ()
  extension (v: Viewport) {
    def unwrap: org.gnome.gtk.Viewport = v
  }
}