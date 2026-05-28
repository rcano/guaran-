package guarana
package gtk
import util.*
opaque type ColorDialogButton <: Widget = org.gnome.gtk.ColorDialogButton & Widget
object ColorDialogButton {
  val Rgba: ExternalVar.Aux[ColorDialogButton, org.gnome.gdk.RGBA] = ExternalVar[ColorDialogButton, org.gnome.gdk.RGBA]("rgba", _.getRgba(), _.setRgba(_), true)
  ()
  extension (v: ColorDialogButton) {
    def unwrap: org.gnome.gtk.ColorDialogButton = v
    def rgba: Var.Aux[org.gnome.gdk.RGBA, v.type] = Rgba.asInstanceOf[Var.Aux[org.gnome.gdk.RGBA, v.type]]
    export unwrap.onActivate
  }
  def init(v: ColorDialogButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ColorDialogButton = {
    val res = new org.gnome.gtk.ColorDialogButton()
    res.asInstanceOf[ColorDialogButton]
  }
  def apply(rgba: Opt[org.gnome.gdk.RGBA] = UnsetParam): VarContextAction[ColorDialogButton] = {
    val res = uninitialized()
    init(res)
    ifSet(rgba, res.rgba := _)
    res
  }
}