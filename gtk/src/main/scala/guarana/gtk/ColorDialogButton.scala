package guarana
package gtk
opaque type ColorDialogButton <: Widget = org.gnome.gtk.ColorDialogButton & Widget
object ColorDialogButton {
  val Rgba: ExternalVar.Aux[ColorDialogButton, org.gnome.gdk.RGBA | Null] = ExternalVar[ColorDialogButton, org.gnome.gdk.RGBA | Null]("rgba", _.getRgba(), _.setRgba(_), true)
  ()
  extension (v: ColorDialogButton) {
    def unwrap: org.gnome.gtk.ColorDialogButton = v
    export unwrap.onActivate
  }
  def init(v: ColorDialogButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ColorDialogButton = {
    val res = new org.gnome.gtk.ColorDialogButton()
    res.asInstanceOf[ColorDialogButton]
  }
}