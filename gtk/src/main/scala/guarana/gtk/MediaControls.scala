package guarana
package gtk
opaque type MediaControls <: Widget = org.gnome.gtk.MediaControls & Widget
object MediaControls {
  val MediaStream: ExternalVar.Aux[MediaControls, org.gnome.gtk.MediaStream | Null] = ExternalVar[MediaControls, org.gnome.gtk.MediaStream | Null]("media-stream", _.getMediaStream(), _.setMediaStream(_), true)
  ()
  extension (v: MediaControls) {
    def unwrap: org.gnome.gtk.MediaControls = v
  }
}