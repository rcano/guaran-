package guarana
package gtk
import util.*
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
    def attributes: Var.Aux[org.gnome.pango.AttrList | Null, v.type] = Attributes.asInstanceOf[Var.Aux[org.gnome.pango.AttrList | Null, v.type]]
    def minChars: Var.Aux[Int, v.type] = MinChars.asInstanceOf[Var.Aux[Int, v.type]]
    def minLines: Var.Aux[Int, v.type] = MinLines.asInstanceOf[Var.Aux[Int, v.type]]
    def natChars: Var.Aux[Int, v.type] = NatChars.asInstanceOf[Var.Aux[Int, v.type]]
    def natLines: Var.Aux[Int, v.type] = NatLines.asInstanceOf[Var.Aux[Int, v.type]]
    def text: Var.Aux[java.lang.String | Null, v.type] = Text.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def textOverflow: Var.Aux[org.gnome.gtk.InscriptionOverflow, v.type] = TextOverflow.asInstanceOf[Var.Aux[org.gnome.gtk.InscriptionOverflow, v.type]]
    def wrapMode: Var.Aux[org.gnome.pango.WrapMode, v.type] = WrapMode.asInstanceOf[Var.Aux[org.gnome.pango.WrapMode, v.type]]
    def xalign: Var.Aux[Float, v.type] = Xalign.asInstanceOf[Var.Aux[Float, v.type]]
    def yalign: Var.Aux[Float, v.type] = Yalign.asInstanceOf[Var.Aux[Float, v.type]]
  }
  def init(v: Inscription): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Inscription = {
    val res = new org.gnome.gtk.Inscription()
    res.asInstanceOf[Inscription]
  }
  def apply(attributes: Opt[org.gnome.pango.AttrList | Null] = UnsetParam, minChars: Opt[Int] = UnsetParam, minLines: Opt[Int] = UnsetParam, natChars: Opt[Int] = UnsetParam, natLines: Opt[Int] = UnsetParam, text: Opt[java.lang.String | Null] = UnsetParam, textOverflow: Opt[org.gnome.gtk.InscriptionOverflow] = UnsetParam, wrapMode: Opt[org.gnome.pango.WrapMode] = UnsetParam, xalign: Opt[Float] = UnsetParam, yalign: Opt[Float] = UnsetParam): VarContextAction[Inscription] = {
    val res = uninitialized()
    init(res)
    ifSet(attributes, res.attributes := _)
    ifSet(minChars, res.minChars := _)
    ifSet(minLines, res.minLines := _)
    ifSet(natChars, res.natChars := _)
    ifSet(natLines, res.natLines := _)
    ifSet(text, res.text := _)
    ifSet(textOverflow, res.textOverflow := _)
    ifSet(wrapMode, res.wrapMode := _)
    ifSet(xalign, res.xalign := _)
    ifSet(yalign, res.yalign := _)
    res
  }
}