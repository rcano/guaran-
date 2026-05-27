package guarana
package gtk
opaque type Inscription <: Widget = org.gnome.gtk.Inscription & Widget
object Inscription {
  val Attributes: ExternalVar.Aux[Inscription, org.gnome.pango.AttrList | Null] = ExternalVar[Inscription, org.gnome.pango.AttrList | Null]("attributes", _.getAttributes(), _.setAttributes(_), true)
  val MinChars: ExternalVar.Aux[Inscription, Int] = ExternalVar[Inscription, Int]("min-chars", _.getMinChars(), _.setMinChars(_), true)
  val MinLines: ExternalVar.Aux[Inscription, Int] = ExternalVar[Inscription, Int]("min-lines", _.getMinLines(), _.setMinLines(_), true)
  val NatChars: ExternalVar.Aux[Inscription, Int] = ExternalVar[Inscription, Int]("nat-chars", _.getNatChars(), _.setNatChars(_), true)
  val NatLines: ExternalVar.Aux[Inscription, Int] = ExternalVar[Inscription, Int]("nat-lines", _.getNatLines(), _.setNatLines(_), true)
  val Text: ExternalVar.Aux[Inscription, java.lang.String | Null] = ExternalVar[Inscription, java.lang.String | Null]("text", _.getText(), _.setText(_), true)
  val TextOverflow: ExternalVar.Aux[Inscription, org.gnome.gtk.InscriptionOverflow] = ExternalVar[Inscription, org.gnome.gtk.InscriptionOverflow]("text-overflow", _.getTextOverflow(), _.setTextOverflow(_), true)
  val WrapMode: ExternalVar.Aux[Inscription, org.gnome.pango.WrapMode] = ExternalVar[Inscription, org.gnome.pango.WrapMode]("wrap-mode", _.getWrapMode(), _.setWrapMode(_), true)
  val Xalign: ExternalVar.Aux[Inscription, Float] = ExternalVar[Inscription, Float]("xalign", _.getXalign(), _.setXalign(_), true)
  val Yalign: ExternalVar.Aux[Inscription, Float] = ExternalVar[Inscription, Float]("yalign", _.getYalign(), _.setYalign(_), true)
  ()
  extension (v: Inscription) {
    def unwrap: org.gnome.gtk.Inscription = v
  }
  def init(v: Inscription): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Inscription = {
    val res = new org.gnome.gtk.Inscription()
    res.asInstanceOf[Inscription]
  }
}