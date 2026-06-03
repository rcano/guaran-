
package guarana
package gtk

import guarana.util.*

opaque type AppChooserWidget <: guarana.gtk.Widget  = org.gnome.gtk.AppChooserWidget & guarana.gtk.Widget
object AppChooserWidget extends VarsMap {
  @deprecated("", "") val ShowAll: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-all", _.getShowAll(), _.setShowAll(_), true)
  @deprecated("", "") val ShowDefault: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-default", _.getShowDefault(), _.setShowDefault(_), true)
  @deprecated("", "") val ShowFallback: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-fallback", _.getShowFallback(), _.setShowFallback(_), true)
  @deprecated("", "") val ShowOther: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-other", _.getShowOther(), _.setShowOther(_), true)
  @deprecated("", "") val ShowRecommended: ExternalVar.Aux[AppChooserWidget, Boolean] = ExternalVar[AppChooserWidget, Boolean]("show-recommended", _.getShowRecommended(), _.setShowRecommended(_), true)

  

  extension (v: AppChooserWidget) {
    def unwrap: org.gnome.gtk.AppChooserWidget = v

    @deprecated("", "") def showAll: Var.Aux[Boolean, v.type] = guarana.gtk.AppChooserWidget.ShowAll.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def showDefault: Var.Aux[Boolean, v.type] = guarana.gtk.AppChooserWidget.ShowDefault.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def showFallback: Var.Aux[Boolean, v.type] = guarana.gtk.AppChooserWidget.ShowFallback.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def showOther: Var.Aux[Boolean, v.type] = guarana.gtk.AppChooserWidget.ShowOther.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def showRecommended: Var.Aux[Boolean, v.type] = guarana.gtk.AppChooserWidget.ShowRecommended.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onApplicationActivated,
      onApplicationSelected,
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

  def wrap(v: org.gnome.gtk.AppChooserWidget): AppChooserWidget = 
    val res = v.asInstanceOf[AppChooserWidget]
    
    res

  def init(v: AppChooserWidget): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(arg$0: java.lang.String): AppChooserWidget = {
    val res = new org.gnome.gtk.AppChooserWidget(arg$0)
    
    res.asInstanceOf[AppChooserWidget]
  }
  
  def apply(
    arg$0: java.lang.String,
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
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showAll: Opt[Binding[Boolean]] = UnsetParam,
    showDefault: Opt[Binding[Boolean]] = UnsetParam,
    showFallback: Opt[Binding[Boolean]] = UnsetParam,
    showOther: Opt[Binding[Boolean]] = UnsetParam,
    showRecommended: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[AppChooserWidget] = {
    val res = uninitialized(arg$0)
    guarana.gtk.AppChooserWidget.init(res)
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
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showAll, res.showAll := _)
    ifSet(showDefault, res.showDefault := _)
    ifSet(showFallback, res.showFallback := _)
    ifSet(showOther, res.showOther := _)
    ifSet(showRecommended, res.showRecommended := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
  
}
        