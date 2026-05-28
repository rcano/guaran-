package guarana
package gtk
import util.*
opaque type Video <: Widget = org.gnome.gtk.Video & Widget
object Video extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.Video): Video = {
    v.asInstanceOf
  }
  def init(v: Video): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): Video = {
    val res = new org.gnome.gtk.Video()
    res.asInstanceOf[Video]
  }
  def apply(autoplay: Opt[Boolean] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, file: Opt[org.gnome.gio.File | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, graphicsOffload: Opt[org.gnome.gtk.GraphicsOffloadEnabled] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, loop: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, mediaStream: Opt[org.gnome.gtk.MediaStream | Null] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, Video] = {
    val res = uninitialized()
    init(res)
    ifSet(autoplay, res.autoplay := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(file, res.file := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(graphicsOffload, res.graphicsOffload := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(loop, res.loop := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(mediaStream, res.mediaStream := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}