package guarana
package gtk
opaque type GraphicsOffload <: Widget = org.gnome.gtk.GraphicsOffload & Widget
object GraphicsOffload {
  val BlackBackground: ExternalVar.Aux[GraphicsOffload, Boolean] = ExternalVar[GraphicsOffload, Boolean]("black-background", _.getBlackBackground(), _.setBlackBackground(_), true)
  val Enabled: ExternalVar.Aux[GraphicsOffload, org.gnome.gtk.GraphicsOffloadEnabled | Null] = ExternalVar[GraphicsOffload, org.gnome.gtk.GraphicsOffloadEnabled | Null]("enabled", _.getEnabled(), _.setEnabled(_), true)
  ()
  extension (v: GraphicsOffload) {
    def unwrap: org.gnome.gtk.GraphicsOffload = v
  }
  def init(v: GraphicsOffload): Unit = {
    Widget.init(v)
  }
  def uninitialized(): GraphicsOffload = {
    val res = new org.gnome.gtk.GraphicsOffload()
    res.asInstanceOf[GraphicsOffload]
  }
}