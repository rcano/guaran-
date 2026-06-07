
package guarana
package gtk

import guarana.util.*

opaque type AspectFrame <: guarana.gtk.Widget  = org.gnome.gtk.AspectFrame & guarana.gtk.Widget
object AspectFrame extends VarsMap {
  val Child: ExternalVar.Aux[AspectFrame, guarana.gtk.Widget | Null] = ExternalVar[AspectFrame, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val ObeyChild: ExternalVar.Aux[AspectFrame, Boolean] = ExternalVar[AspectFrame, Boolean]("obey-child", _.getObeyChild(), _.setObeyChild(_), true)
  val Ratio: ExternalVar.Aux[AspectFrame, Float] = ExternalVar[AspectFrame, Float]("ratio", _.getRatio(), _.setRatio(_), true)
  val Xalign: ExternalVar.Aux[AspectFrame, Float] = ExternalVar[AspectFrame, Float]("xalign", _.getXalign(), _.setXalign(_), true)
  val Yalign: ExternalVar.Aux[AspectFrame, Float] = ExternalVar[AspectFrame, Float]("yalign", _.getYalign(), _.setYalign(_), true)

  

  extension (v: AspectFrame) {
    def unwrap: org.gnome.gtk.AspectFrame = v

    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.AspectFrame.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def obeyChild: Var.Aux[Boolean, v.type] = guarana.gtk.AspectFrame.ObeyChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def ratio: Var.Aux[Float, v.type] = guarana.gtk.AspectFrame.Ratio.asInstanceOf[Var.Aux[Float, v.type]]
    def xalign: Var.Aux[Float, v.type] = guarana.gtk.AspectFrame.Xalign.asInstanceOf[Var.Aux[Float, v.type]]
    def yalign: Var.Aux[Float, v.type] = guarana.gtk.AspectFrame.Yalign.asInstanceOf[Var.Aux[Float, v.type]]

    

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

  def wrap(v: org.gnome.gtk.AspectFrame): AspectFrame = 
    val res = v.asInstanceOf[AspectFrame]
    
    res

  def init(v: AspectFrame): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): AspectFrame = {
    val res = org.gnome.gtk.AspectFrame.builder()
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    
    res.asInstanceOf[AspectFrame]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
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
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    obeyChild: Opt[Binding[Boolean]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    ratio: Opt[Binding[Float]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    xalign: Opt[Binding[Float]] = UnsetParam,
    yalign: Opt[Binding[Float]] = UnsetParam
  ): VarContextAction[AspectFrame] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.AspectFrame.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
    ifSet(name, res.name := _)
    ifSet(obeyChild, res.obeyChild := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(ratio, res.ratio := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(xalign, res.xalign := _)
    ifSet(yalign, res.yalign := _)
    res
  }
  
}
        