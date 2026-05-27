package guarana
package gtk
opaque type VolumeButton <: ScaleButton = org.gnome.gtk.VolumeButton & ScaleButton
object VolumeButton {
  ()
  extension (v: VolumeButton) {
    def unwrap: org.gnome.gtk.VolumeButton = v
  }
  def init(v: VolumeButton): Unit = {
    ScaleButton.init(v)
  }
  def uninitialized(): VolumeButton = {
    val res = new org.gnome.gtk.VolumeButton()
    res.asInstanceOf[VolumeButton]
  }
}