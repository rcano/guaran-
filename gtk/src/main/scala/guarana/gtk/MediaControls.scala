package guarana
package gtk
import util.*
opaque type MediaControls <: Widget = org.gnome.gtk.MediaControls & Widget
object MediaControls {
  val MediaStream: ExternalVar.Aux[MediaControls, org.gnome.gtk.MediaStream | Null] = ExternalVar[MediaControls, org.gnome.gtk.MediaStream | Null]("media-stream", _.getMediaStream(), _.setMediaStream(_), true)
  ()
  extension (v: MediaControls) {
    def unwrap: org.gnome.gtk.MediaControls = v
    def mediaStream: Var.Aux[org.gnome.gtk.MediaStream | Null, v.type] = MediaStream.asInstanceOf[Var.Aux[org.gnome.gtk.MediaStream | Null, v.type]]
  }
  def init(v: MediaControls): Unit = {
    Widget.init(v)
  }
  def uninitialized(): MediaControls = {
    val res = new org.gnome.gtk.MediaControls()
    res.asInstanceOf[MediaControls]
  }
  def apply(mediaStream: Opt[org.gnome.gtk.MediaStream | Null] = UnsetParam): VarContextAction[MediaControls] = {
    val res = uninitialized()
    init(res)
    ifSet(mediaStream, res.mediaStream := _)
    res
  }
}