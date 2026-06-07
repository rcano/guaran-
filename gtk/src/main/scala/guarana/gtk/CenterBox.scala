
package guarana
package gtk

import guarana.util.*

opaque type CenterBox <: guarana.gtk.Widget  = org.gnome.gtk.CenterBox & guarana.gtk.Widget
object CenterBox extends VarsMap {
  val BaselinePosition: ExternalVar.Aux[CenterBox, org.gnome.gtk.BaselinePosition] = ExternalVar[CenterBox, org.gnome.gtk.BaselinePosition]("baseline-position", _.getBaselinePosition(), _.setBaselinePosition(_), true)
  val CenterWidget: ExternalVar.Aux[CenterBox, guarana.gtk.Widget | Null] = ExternalVar[CenterBox, guarana.gtk.Widget | Null]("center-widget", _.getCenterWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setCenterWidget(v.?(_.unwrap)), true)
  val EndWidget: ExternalVar.Aux[CenterBox, guarana.gtk.Widget | Null] = ExternalVar[CenterBox, guarana.gtk.Widget | Null]("end-widget", _.getEndWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setEndWidget(v.?(_.unwrap)), true)
  val Orientation: ExternalVar.Aux[CenterBox, org.gnome.gtk.Orientation] = ExternalVar[CenterBox, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val ShrinkCenterLast: ExternalVar.Aux[CenterBox, Boolean] = ExternalVar[CenterBox, Boolean]("shrink-center-last", _.getShrinkCenterLast(), _.setShrinkCenterLast(_), true)
  val StartWidget: ExternalVar.Aux[CenterBox, guarana.gtk.Widget | Null] = ExternalVar[CenterBox, guarana.gtk.Widget | Null]("start-widget", _.getStartWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setStartWidget(v.?(_.unwrap)), true)

  

  extension (v: CenterBox) {
    def unwrap: org.gnome.gtk.CenterBox = v

    def baselinePosition: Var.Aux[org.gnome.gtk.BaselinePosition, v.type] = guarana.gtk.CenterBox.BaselinePosition.asInstanceOf[Var.Aux[org.gnome.gtk.BaselinePosition, v.type]]
    def centerWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.CenterBox.CenterWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def endWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.CenterBox.EndWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.CenterBox.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def shrinkCenterLast: Var.Aux[Boolean, v.type] = guarana.gtk.CenterBox.ShrinkCenterLast.asInstanceOf[Var.Aux[Boolean, v.type]]
    def startWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.CenterBox.StartWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]

    

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

  def wrap(v: org.gnome.gtk.CenterBox): CenterBox = 
    val res = v.asInstanceOf[CenterBox]
    
    res

  def init(v: CenterBox): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): CenterBox = {
    val res = org.gnome.gtk.CenterBox.builder()
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    
    res.asInstanceOf[CenterBox]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    baselinePosition: Opt[Binding[org.gnome.gtk.BaselinePosition]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    centerWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    endWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
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
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    shrinkCenterLast: Opt[Binding[Boolean]] = UnsetParam,
    startWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[CenterBox] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.CenterBox.init(res)
    ifSet(baselinePosition, res.baselinePosition := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(centerWidget, res.centerWidget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(endWidget, res.endWidget := _)
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
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(shrinkCenterLast, res.shrinkCenterLast := _)
    ifSet(startWidget, res.startWidget := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
  
}
        