package guarana
package gtk
opaque type Image <: Widget = org.gnome.gtk.Image & Widget
object Image {
  val IconSize: ExternalVar.Aux[Image, org.gnome.gtk.IconSize] = ExternalVar[Image, org.gnome.gtk.IconSize]("icon-size", _.getIconSize(), _.setIconSize(_), true)
  val PixelSize: ExternalVar.Aux[Image, Int] = ExternalVar[Image, Int]("pixel-size", _.getPixelSize(), _.setPixelSize(_), true)
  ()
  extension (v: Image) {
    def unwrap: org.gnome.gtk.Image = v
  }
  def init(v: Image): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Image = {
    val res = new org.gnome.gtk.Image()
    res.asInstanceOf[Image]
  }
}