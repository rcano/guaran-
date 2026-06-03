
package guarana
package gtk

import guarana.util.*

opaque type Inscription <: guarana.gtk.Widget  = org.gnome.gtk.Inscription & guarana.gtk.Widget
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

  

  extension (v: Inscription) {
    def unwrap: org.gnome.gtk.Inscription = v

    def attributes: Var.Aux[org.gnome.pango.AttrList | Null, v.type] = guarana.gtk.Inscription.Attributes.asInstanceOf[Var.Aux[org.gnome.pango.AttrList | Null, v.type]]
    def minChars: Var.Aux[Int, v.type] = guarana.gtk.Inscription.MinChars.asInstanceOf[Var.Aux[Int, v.type]]
    def minLines: Var.Aux[Int, v.type] = guarana.gtk.Inscription.MinLines.asInstanceOf[Var.Aux[Int, v.type]]
    def natChars: Var.Aux[Int, v.type] = guarana.gtk.Inscription.NatChars.asInstanceOf[Var.Aux[Int, v.type]]
    def natLines: Var.Aux[Int, v.type] = guarana.gtk.Inscription.NatLines.asInstanceOf[Var.Aux[Int, v.type]]
    def text: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.Inscription.Text.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def textOverflow: Var.Aux[org.gnome.gtk.InscriptionOverflow, v.type] = guarana.gtk.Inscription.TextOverflow.asInstanceOf[Var.Aux[org.gnome.gtk.InscriptionOverflow, v.type]]
    def wrapMode: Var.Aux[org.gnome.pango.WrapMode, v.type] = guarana.gtk.Inscription.WrapMode.asInstanceOf[Var.Aux[org.gnome.pango.WrapMode, v.type]]
    def xalign: Var.Aux[Float, v.type] = guarana.gtk.Inscription.Xalign.asInstanceOf[Var.Aux[Float, v.type]]
    def yalign: Var.Aux[Float, v.type] = guarana.gtk.Inscription.Yalign.asInstanceOf[Var.Aux[Float, v.type]]

    

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

  def wrap(v: org.gnome.gtk.Inscription): Inscription = 
    val res = v.asInstanceOf[Inscription]
    
    res

  def init(v: Inscription): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(arg$0: java.lang.String | Null): Inscription = {
    val res = new org.gnome.gtk.Inscription(arg$0)
    
    res.asInstanceOf[Inscription]
  }
  
  def apply(
    arg$0: java.lang.String | Null,
    attributes: Opt[Binding[org.gnome.pango.AttrList | Null]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    minChars: Opt[Binding[Int]] = UnsetParam,
    minLines: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    natChars: Opt[Binding[Int]] = UnsetParam,
    natLines: Opt[Binding[Int]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    textOverflow: Opt[Binding[org.gnome.gtk.InscriptionOverflow]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    wrapMode: Opt[Binding[org.gnome.pango.WrapMode]] = UnsetParam,
    xalign: Opt[Binding[Float]] = UnsetParam,
    yalign: Opt[Binding[Float]] = UnsetParam
  ): VarContextAction[Inscription] = {
    val res = uninitialized(arg$0)
    guarana.gtk.Inscription.init(res)
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
        