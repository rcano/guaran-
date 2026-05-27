package guarana
package gtk
opaque type Picture <: Widget = org.gnome.gtk.Picture & Widget
object Picture {
  val CanShrink: ExternalVar.Aux[Picture, Boolean] = ExternalVar[Picture, Boolean]("can-shrink", _.getCanShrink(), _.setCanShrink(_), true)
  val ContentFit: ExternalVar.Aux[Picture, org.gnome.gtk.ContentFit | Null] = ExternalVar[Picture, org.gnome.gtk.ContentFit | Null]("content-fit", _.getContentFit(), _.setContentFit(_), true)
  val IsolateContents: ExternalVar.Aux[Picture, Boolean] = ExternalVar[Picture, Boolean]("isolate-contents", _.getIsolateContents(), _.setIsolateContents(_), true)
  @deprecated("", "") val KeepAspectRatio: ExternalVar.Aux[Picture, Boolean] = ExternalVar[Picture, Boolean]("keep-aspect-ratio", _.getKeepAspectRatio(), _.setKeepAspectRatio(_), true)
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