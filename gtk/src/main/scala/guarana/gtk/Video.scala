package guarana
package gtk
import util.*
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
    def autoplay: Var.Aux[Boolean, v.type] = Autoplay.asInstanceOf[Var.Aux[Boolean, v.type]]
    def file: Var.Aux[org.gnome.gio.File | Null, v.type] = File.asInstanceOf[Var.Aux[org.gnome.gio.File | Null, v.type]]
    def graphicsOffload: Var.Aux[org.gnome.gtk.GraphicsOffloadEnabled, v.type] = GraphicsOffload.asInstanceOf[Var.Aux[org.gnome.gtk.GraphicsOffloadEnabled, v.type]]
    def loop: Var.Aux[Boolean, v.type] = Loop.asInstanceOf[Var.Aux[Boolean, v.type]]
    def mediaStream: Var.Aux[org.gnome.gtk.MediaStream | Null, v.type] = MediaStream.asInstanceOf[Var.Aux[org.gnome.gtk.MediaStream | Null, v.type]]
  }
  def init(v: Video): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Video = {
    val res = new org.gnome.gtk.Video()
    res.asInstanceOf[Video]
  }
  def apply(autoplay: Opt[Boolean] = UnsetParam, file: Opt[org.gnome.gio.File | Null] = UnsetParam, graphicsOffload: Opt[org.gnome.gtk.GraphicsOffloadEnabled] = UnsetParam, loop: Opt[Boolean] = UnsetParam, mediaStream: Opt[org.gnome.gtk.MediaStream | Null] = UnsetParam): VarContextAction[Video] = {
    val res = uninitialized()
    init(res)
    ifSet(autoplay, res.autoplay := _)
    ifSet(file, res.file := _)
    ifSet(graphicsOffload, res.graphicsOffload := _)
    ifSet(loop, res.loop := _)
    ifSet(mediaStream, res.mediaStream := _)
    res
  }
}