package guarana
package gtk
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
  }
  def init(v: Picture): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Picture = {
    val res = new org.gnome.gtk.Picture()
    res.asInstanceOf[Picture]
  }
}