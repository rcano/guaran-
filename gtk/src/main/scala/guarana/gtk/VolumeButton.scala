package guarana
package gtk
opaque type VolumeButton <: ScaleButton = org.gnome.gtk.VolumeButton & ScaleButton
object VolumeButton {
  ()
  extension (v: VolumeButton) {
    def unwrap: org.gnome.gtk.VolumeButton = v
  }
}