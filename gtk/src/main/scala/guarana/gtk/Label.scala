package guarana
package gtk
opaque type Label <: Widget = org.gnome.gtk.Label & Widget
object Label {
  val Ellipsize: ExternalVar.Aux[Label, org.gnome.pango.EllipsizeMode | Null] = ExternalVar[Label, org.gnome.pango.EllipsizeMode | Null]("ellipsize", _.getEllipsize(), _.setEllipsize(_), true)
  val Justify: ExternalVar.Aux[Label, org.gnome.gtk.Justification | Null] = ExternalVar[Label, org.gnome.gtk.Justification | Null]("justify", _.getJustify(), _.setJustify(_), true)
  val Label: ExternalVar.Aux[Label, java.lang.String | Null] = ExternalVar[Label, java.lang.String | Null]("label", _.getLabel(), _.setLabel(_), true)
  val Lines: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("lines", _.getLines(), _.setLines(_), true)
  val MaxWidthChars: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val NaturalWrapMode: ExternalVar.Aux[Label, org.gnome.gtk.NaturalWrapMode | Null] = ExternalVar[Label, org.gnome.gtk.NaturalWrapMode | Null]("natural-wrap-mode", _.getNaturalWrapMode(), _.setNaturalWrapMode(_), true)
  val Selectable: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("selectable", _.getSelectable(), _.setSelectable(_), true)
  val SingleLineMode: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("single-line-mode", _.getSingleLineMode(), _.setSingleLineMode(_), true)
  val UseMarkup: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("use-markup", _.getUseMarkup(), _.setUseMarkup(_), true)
  val UseUnderline: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)
  val WidthChars: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)
  val Wrap: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("wrap", _.getWrap(), _.setWrap(_), true)
  val WrapMode: ExternalVar.Aux[Label, org.gnome.pango.WrapMode | Null] = ExternalVar[Label, org.gnome.pango.WrapMode | Null]("wrap-mode", _.getWrapMode(), _.setWrapMode(_), true)
  val Xalign: ExternalVar.Aux[Label, Float] = ExternalVar[Label, Float]("xalign", _.getXalign(), _.setXalign(_), true)
  val Yalign: ExternalVar.Aux[Label, Float] = ExternalVar[Label, Float]("yalign", _.getYalign(), _.setYalign(_), true)
  ()
  extension (v: Label) {
    def unwrap: org.gnome.gtk.Label = v
    export unwrap.onActivateCurrentLink, unwrap.onActivateLink, unwrap.onCopyClipboard, unwrap.onMoveCursor
  }
  def init(v: Label): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Label = {
    val res = new org.gnome.gtk.Label()
    res.asInstanceOf[Label]
  }
}