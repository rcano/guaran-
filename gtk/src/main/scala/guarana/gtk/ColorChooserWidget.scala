package guarana
package gtk
opaque type ColorChooserWidget <: Widget = org.gnome.gtk.ColorChooserWidget & Widget
object ColorChooserWidget {
  @deprecated("", "") val UseAlpha: ExternalVar.Aux[ColorChooserWidget, Boolean] = ExternalVar[ColorChooserWidget, Boolean]("use-alpha", _.getUseAlpha(), _.setUseAlpha(_), true)
  ()
  extension (v: ColorChooserWidget) {
    def unwrap: org.gnome.gtk.ColorChooserWidget = v
  }
  def init(v: ColorChooserWidget): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ColorChooserWidget = {
    val res = new org.gnome.gtk.ColorChooserWidget()
    res.asInstanceOf[ColorChooserWidget]
  }
}