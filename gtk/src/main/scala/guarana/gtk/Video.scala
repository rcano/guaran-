
package guarana
package gtk

import guarana.util.*

opaque type Video <: guarana.gtk.Widget  = org.gnome.gtk.Video & guarana.gtk.Widget
object Video extends VarsMap {
  val Autoplay: ExternalVar.Aux[Video, Boolean] = ExternalVar[Video, Boolean]("autoplay", _.getAutoplay(), _.setAutoplay(_), true)
  val File: ExternalVar.Aux[Video, org.gnome.gio.File | Null] = ExternalVar[Video, org.gnome.gio.File | Null]("file", _.getFile(), _.setFile(_), true)
  val GraphicsOffload: ExternalVar.Aux[Video, org.gnome.gtk.GraphicsOffloadEnabled] = ExternalVar[Video, org.gnome.gtk.GraphicsOffloadEnabled]("graphics-offload", _.getGraphicsOffload(), _.setGraphicsOffload(_), true)
  val Loop: ExternalVar.Aux[Video, Boolean] = ExternalVar[Video, Boolean]("loop", _.getLoop(), _.setLoop(_), true)
  val MediaStream: ExternalVar.Aux[Video, org.gnome.gtk.MediaStream | Null] = ExternalVar[Video, org.gnome.gtk.MediaStream | Null]("media-stream", _.getMediaStream(), _.setMediaStream(_), true)

  

  extension (v: Video) {
    def unwrap: org.gnome.gtk.Video = v

    def autoplay: Var.Aux[Boolean, v.type] = guarana.gtk.Video.Autoplay.asInstanceOf[Var.Aux[Boolean, v.type]]
    def file: Var.Aux[org.gnome.gio.File | Null, v.type] = guarana.gtk.Video.File.asInstanceOf[Var.Aux[org.gnome.gio.File | Null, v.type]]
    def graphicsOffload: Var.Aux[org.gnome.gtk.GraphicsOffloadEnabled, v.type] = guarana.gtk.Video.GraphicsOffload.asInstanceOf[Var.Aux[org.gnome.gtk.GraphicsOffloadEnabled, v.type]]
    def loop: Var.Aux[Boolean, v.type] = guarana.gtk.Video.Loop.asInstanceOf[Var.Aux[Boolean, v.type]]
    def mediaStream: Var.Aux[org.gnome.gtk.MediaStream | Null, v.type] = guarana.gtk.Video.MediaStream.asInstanceOf[Var.Aux[org.gnome.gtk.MediaStream | Null, v.type]]

    

    export unwrap.{
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.Video): Video = 
    val res = v.asInstanceOf[Video]
    
    res

  def init(v: Video): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Video = {
    val res = {
      val res = org.gnome.gtk.Video.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[Video]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    autoplay: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    file: Opt[Binding[org.gnome.gio.File | Null]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    graphicsOffload: Opt[Binding[org.gnome.gtk.GraphicsOffloadEnabled]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    loop: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    mediaStream: Opt[Binding[org.gnome.gtk.MediaStream | Null]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Video] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.Video.init(res)
    ifSet(autoplay, res.autoplay := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
        