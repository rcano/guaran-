
package guarana
package gtk

import guarana.util.*

opaque type LinkButton <: guarana.gtk.Button  = org.gnome.gtk.LinkButton & guarana.gtk.Button
object LinkButton extends VarsMap {
  val Uri: ExternalVar.Aux[LinkButton, java.lang.String] = ExternalVar[LinkButton, java.lang.String]("uri", _.getUri(), _.setUri(_), true)
  val Visited: ExternalVar.Aux[LinkButton, Boolean] = ExternalVar[LinkButton, Boolean]("visited", _.getVisited(), _.setVisited(_), true)

  

  extension (v: LinkButton) {
    def unwrap: org.gnome.gtk.LinkButton = v

    def uri: Var.Aux[java.lang.String, v.type] = guarana.gtk.LinkButton.Uri.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def visited: Var.Aux[Boolean, v.type] = guarana.gtk.LinkButton.Visited.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivate,
      onActivateLink,
      onClicked,
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

  def wrap(v: org.gnome.gtk.LinkButton): LinkButton = 
    val res = v.asInstanceOf[LinkButton]
    
    res

  def init(v: LinkButton): Unit = {
    guarana.gtk.Button.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(iconName: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], actionTarget: Opt[org.gnome.glib.Variant]): LinkButton = {
    val res = {
      val res = org.gnome.gtk.LinkButton.builder()
      ifSet(iconName, v => res.setIconName(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      ifSet(actionTarget, v => res.setActionTarget(v))
      res.build()
    }
    
    res.asInstanceOf[LinkButton]
  }
  
  def apply(
    iconName: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, actionTarget: Opt[org.gnome.glib.Variant] = UnsetParam,
    actionName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    actionTargetValue: Opt[Binding[org.gnome.glib.Variant | Null]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canShrink: Opt[Binding[Boolean]] = UnsetParam,
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
    hasFrame: Opt[Binding[Boolean]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    label: Opt[Binding[String | Null]] = UnsetParam,
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
    uri: Opt[Binding[java.lang.String]] = UnsetParam,
    useUnderline: Opt[Binding[Boolean]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    visited: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[LinkButton] = {
    val res = uninitialized(iconName, cssName, heightRequest, widthRequest, accessibleRole, actionTarget)
    guarana.gtk.LinkButton.init(res)
    ifSet(actionName, res.actionName := _)
    ifSet(actionTargetValue, res.actionTargetValue := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canShrink, res.canShrink := _)
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
    ifSet(hasFrame, res.hasFrame := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(label, res.label := _)
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
    ifSet(uri, res.uri := _)
    ifSet(useUnderline, res.useUnderline := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(visited, res.visited := _)
    
    res
  }
  
}
        