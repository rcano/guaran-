package guarana
package gtk
import util.*
opaque type Picture <: Widget = org.gnome.gtk.Picture & Widget
object Picture {
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
  def init(v: Picture): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Picture = {
    val res = new org.gnome.gtk.Picture()
    res.asInstanceOf[Picture]
  }
  def apply(alternativeText: Opt[java.lang.String | Null] = UnsetParam, canShrink: Opt[Boolean] = UnsetParam, contentFit: Opt[org.gnome.gtk.ContentFit] = UnsetParam, file: Opt[org.gnome.gio.File | Null] = UnsetParam, isolateContents: Opt[Boolean] = UnsetParam, keepAspectRatio: Opt[Boolean] = UnsetParam, paintable: Opt[org.gnome.gdk.Paintable | Null] = UnsetParam): VarContextAction[Picture] = {
    val res = uninitialized()
    init(res)
    ifSet(alternativeText, res.alternativeText := _)
    ifSet(canShrink, res.canShrink := _)
    ifSet(contentFit, res.contentFit := _)
    ifSet(file, res.file := _)
    ifSet(isolateContents, res.isolateContents := _)
    ifSet(keepAspectRatio, res.keepAspectRatio := _)
    ifSet(paintable, res.paintable := _)
    res
  }
}