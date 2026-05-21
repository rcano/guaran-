package guarana
package gtk
opaque type Image <: Widget = org.gnome.gtk.Image & Widget
object Image {
  val IconSize: ExternalVar.Aux[Image, org.gnome.gtk.IconSize | Null] = ExternalVar[Image, org.gnome.gtk.IconSize | Null]("icon-size", _.getIconSize(), _.setIconSize(_), true)
  val PixelSize: ExternalVar.Aux[Image, Int] = ExternalVar[Image, Int]("pixel-size", _.getPixelSize(), _.setPixelSize(_), true)
  ()
  extension (v: Image) {
    def unwrap: org.gnome.gtk.Image = v
  }
}