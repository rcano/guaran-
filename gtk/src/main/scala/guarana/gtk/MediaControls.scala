package guarana
package gtk
opaque type MediaControls <: Widget = org.gnome.gtk.MediaControls & Widget
object MediaControls {
  ()
  extension (v: MediaControls) {
    def unwrap: org.gnome.gtk.MediaControls = v
  }
  def init(v: MediaControls): Unit = {
    Widget.init(v)
  }
  def uninitialized(): MediaControls = {
    val res = new org.gnome.gtk.MediaControls()
    res.asInstanceOf[MediaControls]
  }
}