package guarana
package gtk
import util.*
opaque type Picture <: Widget = org.gnome.gtk.Picture & Widget
object Picture extends VarsMap {
  val AlternativeText: ExternalVar.Aux[Picture, java.lang.String | Null] = ExternalVar[Picture, java.lang.String | Null]("alternative-text", _.getAlternativeText(), _.setAlternativeText(_), true)
  val CanShrink: ExternalVar.Aux[Picture, Boolean] = ExternalVar[Picture, Boolean]("can-shrink", _.getCanShrink(), _.setCanShrink(_), true)
  val ContentFit: ExternalVar.Aux[Picture, org.gnome.gtk.ContentFit] = ExternalVar[Picture, org.gnome.gtk.ContentFit]("content-fit", _.getContentFit(), _.setContentFit(_), true)
  val File: ExternalVar.Aux[Picture, org.gnome.gio.File | Null] = ExternalVar[Picture, org.gnome.gio.File | Null]("file", _.getFile(), _.setFile(_), true)
  val IsolateContents: ExternalVar.Aux[Picture, Boolean] = ExternalVar[Picture, Boolean]("isolate-contents", _.getIsolateContents(), _.setIsolateContents(_), true)
  @deprecated("", "") val KeepAspectRatio: ExternalVar.Aux[Picture, Boolean] = ExternalVar[Picture, Boolean]("keep-aspect-ratio", _.getKeepAspectRatio(), _.setKeepAspectRatio(_), true)
  val Paintable: ExternalVar.Aux[Picture, org.gnome.gdk.Paintable | Null] = ExternalVar[Picture, org.gnome.gdk.Paintable | Null]("paintable", _.getPaintable(), _.setPaintable(_), true)
  ()
  extension (v: Picture) {
    def unwrap: org.gnome.gtk.Picture = v
    def alternativeText: Var.Aux[java.lang.String | Null, v.type] = AlternativeText.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def canShrink: Var.Aux[Boolean, v.type] = CanShrink.asInstanceOf[Var.Aux[Boolean, v.type]]
    def contentFit: Var.Aux[org.gnome.gtk.ContentFit, v.type] = ContentFit.asInstanceOf[Var.Aux[org.gnome.gtk.ContentFit, v.type]]
    def file: Var.Aux[org.gnome.gio.File | Null, v.type] = File.asInstanceOf[Var.Aux[org.gnome.gio.File | Null, v.type]]
    def isolateContents: Var.Aux[Boolean, v.type] = IsolateContents.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def keepAspectRatio: Var.Aux[Boolean, v.type] = KeepAspectRatio.asInstanceOf[Var.Aux[Boolean, v.type]]
    def paintable: Var.Aux[org.gnome.gdk.Paintable | Null, v.type] = Paintable.asInstanceOf[Var.Aux[org.gnome.gdk.Paintable | Null, v.type]]
  }
  def _wrap(v: org.gnome.gtk.Picture): Picture = {
    v.asInstanceOf
  }
  def init(v: Picture): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): Picture = {
    val res = new org.gnome.gtk.Picture()
    res.asInstanceOf[Picture]
  }
  def apply(alternativeText: Opt[Binding[java.lang.String | Null]] = UnsetParam, canFocus: Opt[Binding[Boolean]] = UnsetParam, canShrink: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, contentFit: Opt[Binding[org.gnome.gtk.ContentFit]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, file: Opt[Binding[org.gnome.gio.File | Null]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, isolateContents: Opt[Binding[Boolean]] = UnsetParam, keepAspectRatio: Opt[Binding[Boolean]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, paintable: Opt[Binding[org.gnome.gdk.Paintable | Null]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam): ToolkitAction[Toolkit, Picture] = {
    val res = uninitialized()
    init(res)
    ifSet(alternativeText, res.alternativeText := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canShrink, res.canShrink := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(contentFit, res.contentFit := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(file, res.file := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(isolateContents, res.isolateContents := _)
    ifSet(keepAspectRatio, res.keepAspectRatio := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(paintable, res.paintable := _)
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