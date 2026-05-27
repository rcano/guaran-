package guarana
package gtk
opaque type Expander <: Widget = org.gnome.gtk.Expander & Widget
object Expander {
  val Expanded: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("expanded", _.getExpanded(), _.setExpanded(_), true)
  val ResizeToplevel: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("resize-toplevel", _.getResizeToplevel(), _.setResizeToplevel(_), true)
  val UseMarkup: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("use-markup", _.getUseMarkup(), _.setUseMarkup(_), true)
  val UseUnderline: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)
  ()
  extension (v: Expander) {
    def unwrap: org.gnome.gtk.Expander = v
    export unwrap.onActivate
  }
  def init(v: Expander): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Expander = {
    val res = new org.gnome.gtk.Expander()
    res.asInstanceOf[Expander]
  }
}