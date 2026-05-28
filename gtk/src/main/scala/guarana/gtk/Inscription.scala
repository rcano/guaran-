package guarana
package gtk
import util.*
opaque type Inscription <: Widget = org.gnome.gtk.Inscription & Widget
object Inscription extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.Inscription): Inscription = {
    v.asInstanceOf
  }
  def init(v: Inscription): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: java.lang.String | Null): Inscription = {
    val res = new org.gnome.gtk.Inscription(arg$0)
    res.asInstanceOf[Inscription]
  }
  def apply(arg$0: java.lang.String | Null, attributes: Opt[org.gnome.pango.AttrList | Null] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, minChars: Opt[Int] = UnsetParam, minLines: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, natChars: Opt[Int] = UnsetParam, natLines: Opt[Int] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, text: Opt[java.lang.String | Null] = UnsetParam, textOverflow: Opt[org.gnome.gtk.InscriptionOverflow] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, wrapMode: Opt[org.gnome.pango.WrapMode] = UnsetParam, xalign: Opt[Float] = UnsetParam, yalign: Opt[Float] = UnsetParam): ToolkitAction[Toolkit, Inscription] = {
    val res = uninitialized(arg$0)
    init(res)
    ifSet(attributes, res.attributes := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(minChars, res.minChars := _)
    ifSet(minLines, res.minLines := _)
    ifSet(name, res.name := _)
    ifSet(natChars, res.natChars := _)
    ifSet(natLines, res.natLines := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(text, res.text := _)
    ifSet(textOverflow, res.textOverflow := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(wrapMode, res.wrapMode := _)
    ifSet(xalign, res.xalign := _)
    ifSet(yalign, res.yalign := _)
    res
  }
}