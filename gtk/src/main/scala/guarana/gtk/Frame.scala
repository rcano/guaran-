
package guarana
package gtk

import guarana.util.*

opaque type Frame <: guarana.gtk.Widget  = org.gnome.gtk.Frame & guarana.gtk.Widget
object Frame extends VarsMap {
  val Child: ExternalVar.Aux[Frame, guarana.gtk.Widget | Null] = ExternalVar[Frame, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val Label: ExternalVar.Aux[Frame, java.lang.String | Null] = ExternalVar[Frame, java.lang.String | Null]("label", _.getLabel(), _.setLabel(_), true)
  val LabelAlign: ExternalVar.Aux[Frame, Float] = ExternalVar[Frame, Float]("label-align", _.getLabelAlign(), _.setLabelAlign(_), true)
  val LabelWidget: ExternalVar.Aux[Frame, guarana.gtk.Widget | Null] = ExternalVar[Frame, guarana.gtk.Widget | Null]("label-widget", _.getLabelWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setLabelWidget(v.?(_.unwrap)), true)

  

  extension (v: Frame) {
    def unwrap: org.gnome.gtk.Frame = v

    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Frame.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def label: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.Frame.Label.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def labelAlign: Var.Aux[Float, v.type] = guarana.gtk.Frame.LabelAlign.asInstanceOf[Var.Aux[Float, v.type]]
    def labelWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Frame.LabelWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]

    

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

  def wrap(v: org.gnome.gtk.Frame): Frame = 
    val res = v.asInstanceOf[Frame]
    
    res

  def init(v: Frame): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(arg$0: java.lang.String | Null): Frame = {
    val res = new org.gnome.gtk.Frame(arg$0)
    
    res.asInstanceOf[Frame]
  }
  
  def apply(
    arg$0: java.lang.String | Null,
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
    label: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    labelAlign: Opt[Binding[Float]] = UnsetParam,
    labelWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Frame] = {
    val res = uninitialized(arg$0)
    guarana.gtk.Frame.init(res)
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
    ifSet(label, res.label := _)
    ifSet(labelAlign, res.labelAlign := _)
    ifSet(labelWidget, res.labelWidget := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
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
        