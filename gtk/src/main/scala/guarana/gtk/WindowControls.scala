package guarana
package gtk
opaque type WindowControls <: Widget = org.gnome.gtk.WindowControls & Widget
object WindowControls {
  val Side: ExternalVar.Aux[WindowControls, org.gnome.gtk.PackType | Null] = ExternalVar[WindowControls, org.gnome.gtk.PackType | Null]("side", _.getSide(), _.setSide(_), true)
  val UseNativeControls: ExternalVar.Aux[WindowControls, Boolean] = ExternalVar[WindowControls, Boolean]("use-native-controls", _.getUseNativeControls(), _.setUseNativeControls(_), true)
  ()
  extension (v: WindowControls) {
    def unwrap: org.gnome.gtk.WindowControls = v
  }
  def init(v: WindowControls): Unit = {
    Widget.init(v)
  }
  def uninitialized(): WindowControls = {
    val res = new org.gnome.gtk.WindowControls()
    res.asInstanceOf[WindowControls]
  }
}