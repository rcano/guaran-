
package guarana
package gtk

import guarana.util.*

opaque type Expander <: guarana.gtk.Widget  = org.gnome.gtk.Expander & guarana.gtk.Widget
object Expander extends VarsMap {
  val Child: ExternalVar.Aux[Expander, guarana.gtk.Widget | Null] = ExternalVar[Expander, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val Expanded: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("expanded", _.getExpanded(), _.setExpanded(_), true)
  val Label: ExternalVar.Aux[Expander, java.lang.String | Null] = ExternalVar[Expander, java.lang.String | Null]("label", _.getLabel(), _.setLabel(_), true)
  val LabelWidget: ExternalVar.Aux[Expander, guarana.gtk.Widget | Null] = ExternalVar[Expander, guarana.gtk.Widget | Null]("label-widget", _.getLabelWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setLabelWidget(v.?(_.unwrap)), true)
  val ResizeToplevel: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("resize-toplevel", _.getResizeToplevel(), _.setResizeToplevel(_), true)
  val UseMarkup: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("use-markup", _.getUseMarkup(), _.setUseMarkup(_), true)
  val UseUnderline: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)

  

  extension (v: Expander) {
    def unwrap: org.gnome.gtk.Expander = v

    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Expander.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def expanded: Var.Aux[Boolean, v.type] = guarana.gtk.Expander.Expanded.asInstanceOf[Var.Aux[Boolean, v.type]]
    def label: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.Expander.Label.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def labelWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Expander.LabelWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def resizeToplevel: Var.Aux[Boolean, v.type] = guarana.gtk.Expander.ResizeToplevel.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useMarkup: Var.Aux[Boolean, v.type] = guarana.gtk.Expander.UseMarkup.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useUnderline: Var.Aux[Boolean, v.type] = guarana.gtk.Expander.UseUnderline.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivate,
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

  def wrap(v: org.gnome.gtk.Expander): Expander = 
    val res = v.asInstanceOf[Expander]
    
    res

  def init(v: Expander): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Expander = {
    val res = org.gnome.gtk.Expander.builder()
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    
    res.asInstanceOf[Expander]
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
    expanded: Opt[Binding[Boolean]] = UnsetParam,
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
    resizeToplevel: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    useMarkup: Opt[Binding[Boolean]] = UnsetParam,
    useUnderline: Opt[Binding[Boolean]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Expander] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.Expander.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(expanded, res.expanded := _)
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
    ifSet(resizeToplevel, res.resizeToplevel := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(useMarkup, res.useMarkup := _)
    ifSet(useUnderline, res.useUnderline := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
  
}
        