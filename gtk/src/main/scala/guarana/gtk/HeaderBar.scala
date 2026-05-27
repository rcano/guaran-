package guarana
package gtk
opaque type HeaderBar <: Widget = org.gnome.gtk.HeaderBar & Widget
object HeaderBar {
  val ShowTitleButtons: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("show-title-buttons", _.getShowTitleButtons(), _.setShowTitleButtons(_), true)
  val UseNativeControls: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("use-native-controls", _.getUseNativeControls(), _.setUseNativeControls(_), true)
  ()
  extension (v: HeaderBar) {
    def unwrap: org.gnome.gtk.HeaderBar = v
  }
  def init(v: HeaderBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): HeaderBar = {
    val res = new org.gnome.gtk.HeaderBar()
    res.asInstanceOf[HeaderBar]
  }
}