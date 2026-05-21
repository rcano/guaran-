package guarana
package gtk
opaque type ColorDialogButton <: Widget = org.gnome.gtk.ColorDialogButton & Widget
object ColorDialogButton {
  val Dialog: ExternalVar.Aux[ColorDialogButton, org.gnome.gtk.ColorDialog | Null] = ExternalVar[ColorDialogButton, org.gnome.gtk.ColorDialog | Null]("dialog", _.getDialog(), _.setDialog(_), true)
  val Rgba: ExternalVar.Aux[ColorDialogButton, org.gnome.gdk.RGBA | Null] = ExternalVar[ColorDialogButton, org.gnome.gdk.RGBA | Null]("rgba", _.getRgba(), _.setRgba(_), true)
  ()
  extension (v: ColorDialogButton) {
    def unwrap: org.gnome.gtk.ColorDialogButton = v
    export unwrap.onActivate
  }
}