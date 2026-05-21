package guarana
package gtk
opaque type ColorButton <: Widget = org.gnome.gtk.ColorButton & Widget
object ColorButton {
  val Modal: ExternalVar.Aux[ColorButton, Boolean] = ExternalVar[ColorButton, Boolean]("modal", _.getModal(), _.setModal(_), true)
  val Title: ExternalVar.Aux[ColorButton, java.lang.String | Null] = ExternalVar[ColorButton, java.lang.String | Null]("title", _.getTitle(), _.setTitle(_), true)
  ()
  extension (v: ColorButton) {
    def unwrap: org.gnome.gtk.ColorButton = v
    export unwrap.onActivate, unwrap.onColorSet
  }
}