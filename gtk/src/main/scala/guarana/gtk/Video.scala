package guarana
package gtk
opaque type Video <: Widget = org.gnome.gtk.Video & Widget
object Video {
  val Autoplay: ExternalVar.Aux[Video, Boolean] = ExternalVar[Video, Boolean]("autoplay", _.getAutoplay(), _.setAutoplay(_), true)
  val File: ExternalVar.Aux[Video, org.gnome.gio.File | Null] = ExternalVar[Video, org.gnome.gio.File | Null]("file", _.getFile(), _.setFile(_), true)
  val GraphicsOffload: ExternalVar.Aux[Video, org.gnome.gtk.GraphicsOffloadEnabled] = ExternalVar[Video, org.gnome.gtk.GraphicsOffloadEnabled]("graphics-offload", _.getGraphicsOffload(), _.setGraphicsOffload(_), true)
  val Loop: ExternalVar.Aux[Video, Boolean] = ExternalVar[Video, Boolean]("loop", _.getLoop(), _.setLoop(_), true)
  val MediaStream: ExternalVar.Aux[Video, org.gnome.gtk.MediaStream | Null] = ExternalVar[Video, org.gnome.gtk.MediaStream | Null]("media-stream", _.getMediaStream(), _.setMediaStream(_), true)
  ()
  extension (v: Video) {
    def unwrap: org.gnome.gtk.Video = v
  }
  def init(v: Video): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Video = {
    val res = new org.gnome.gtk.Video()
    res.asInstanceOf[Video]
  }
}