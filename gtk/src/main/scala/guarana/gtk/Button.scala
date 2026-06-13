
package guarana
package gtk

import guarana.util.*

opaque type Button <: guarana.gtk.Widget  = org.gnome.gtk.Button & guarana.gtk.Widget
object Button extends VarsMap {
  val ActionName: ExternalVar.Aux[Button, java.lang.String | Null] = ExternalVar[Button, java.lang.String | Null]("action-name", _.getActionName(), _.setActionName(_), true)
  val ActionTargetValue: ExternalVar.Aux[Button, org.gnome.glib.Variant | Null] = ExternalVar[Button, org.gnome.glib.Variant | Null]("action-target-value", _.getActionTargetValue(), _.setActionTargetValue(_), true)
  val CanShrink: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("can-shrink", _.getCanShrink(), _.setCanShrink(_), true)
  val Child: ExternalVar.Aux[Button, guarana.gtk.Widget | Null] = ExternalVar[Button, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val HasFrame: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val Label: ExternalVar.Aux[Button, String | Null] = ExternalVar[Button, String | Null]("label", _.getLabel, _.setLabel(_), true)
  val UseUnderline: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)

  

  extension (v: Button) {
    def unwrap: org.gnome.gtk.Button = v

    def actionName: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.Button.ActionName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def actionTargetValue: Var.Aux[org.gnome.glib.Variant | Null, v.type] = guarana.gtk.Button.ActionTargetValue.asInstanceOf[Var.Aux[org.gnome.glib.Variant | Null, v.type]]
    def canShrink: Var.Aux[Boolean, v.type] = guarana.gtk.Button.CanShrink.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Button.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def hasFrame: Var.Aux[Boolean, v.type] = guarana.gtk.Button.HasFrame.asInstanceOf[Var.Aux[Boolean, v.type]]
    def label: Var.Aux[String | Null, v.type] = guarana.gtk.Button.Label.asInstanceOf[Var.Aux[String | Null, v.type]]
    def useUnderline: Var.Aux[Boolean, v.type] = guarana.gtk.Button.UseUnderline.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivate,
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

  def wrap(v: org.gnome.gtk.Button): Button = 
    val res = v.asInstanceOf[Button]
    
    res

  def init(v: Button): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(iconName: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], actionTarget: Opt[org.gnome.glib.Variant]): Button = {
    val res = {
      val res = org.gnome.gtk.Button.builder()
      ifSet(iconName, v => res.setIconName(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      ifSet(actionTarget, v => res.setActionTarget(v))
      res.build()
    }
    
    res.asInstanceOf[Button]
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
    useUnderline: Opt[Binding[Boolean]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Button] = {
    val res = uninitialized(iconName, cssName, heightRequest, widthRequest, accessibleRole, actionTarget)
    guarana.gtk.Button.init(res)
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
    ifSet(useUnderline, res.useUnderline := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        