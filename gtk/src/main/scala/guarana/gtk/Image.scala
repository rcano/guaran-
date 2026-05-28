package guarana
package gtk
import util.*
opaque type Image <: Widget = org.gnome.gtk.Image & Widget
object Image {
  val IconSize: ExternalVar.Aux[Image, org.gnome.gtk.IconSize] = ExternalVar[Image, org.gnome.gtk.IconSize]("icon-size", _.getIconSize(), _.setIconSize(_), true)
  val PixelSize: ExternalVar.Aux[Image, Int] = ExternalVar[Image, Int]("pixel-size", _.getPixelSize(), _.setPixelSize(_), true)
  ()
  extension (v: Image) {
    def unwrap: org.gnome.gtk.Image = v
    def iconSize: Var.Aux[org.gnome.gtk.IconSize, v.type] = IconSize.asInstanceOf[Var.Aux[org.gnome.gtk.IconSize, v.type]]
    def pixelSize: Var.Aux[Int, v.type] = PixelSize.asInstanceOf[Var.Aux[Int, v.type]]
  }
  def init(v: Image): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Image = {
    val res = new org.gnome.gtk.Image()
    res.asInstanceOf[Image]
  }
  def apply(iconSize: Opt[org.gnome.gtk.IconSize] = UnsetParam, pixelSize: Opt[Int] = UnsetParam): VarContextAction[Image] = {
    val res = uninitialized()
    init(res)
    ifSet(iconSize, res.iconSize := _)
    ifSet(pixelSize, res.pixelSize := _)
    res
  }
}