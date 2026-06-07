
package guarana
package gtk

import guarana.util.*

opaque type CheckButton <: guarana.gtk.Widget  = org.gnome.gtk.CheckButton & guarana.gtk.Widget
object CheckButton extends VarsMap {
  val ActionName: ExternalVar.Aux[CheckButton, java.lang.String | Null] = ExternalVar[CheckButton, java.lang.String | Null]("action-name", _.getActionName(), _.setActionName(_), true)
  val ActionTargetValue: ExternalVar.Aux[CheckButton, org.gnome.glib.Variant | Null] = ExternalVar[CheckButton, org.gnome.glib.Variant | Null]("action-target-value", _.getActionTargetValue(), _.setActionTargetValue(_), true)
  val Active: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("active", _.getActive(), _.setActive(_), true)
  val Child: ExternalVar.Aux[CheckButton, guarana.gtk.Widget | Null] = ExternalVar[CheckButton, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val Inconsistent: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("inconsistent", _.getInconsistent(), _.setInconsistent(_), true)
  val Label: ExternalVar.Aux[CheckButton, java.lang.String | Null] = ExternalVar[CheckButton, java.lang.String | Null]("label", _.getLabel(), _.setLabel(_), true)
  val UseUnderline: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)

  

  extension (v: CheckButton) {
    def unwrap: org.gnome.gtk.CheckButton = v

    def actionName: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.CheckButton.ActionName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def actionTargetValue: Var.Aux[org.gnome.glib.Variant | Null, v.type] = guarana.gtk.CheckButton.ActionTargetValue.asInstanceOf[Var.Aux[org.gnome.glib.Variant | Null, v.type]]
    def active: Var.Aux[Boolean, v.type] = guarana.gtk.CheckButton.Active.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.CheckButton.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def inconsistent: Var.Aux[Boolean, v.type] = guarana.gtk.CheckButton.Inconsistent.asInstanceOf[Var.Aux[Boolean, v.type]]
    def label: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.CheckButton.Label.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def useUnderline: Var.Aux[Boolean, v.type] = guarana.gtk.CheckButton.UseUnderline.asInstanceOf[Var.Aux[Boolean, v.type]]

    

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
      onToggled,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.CheckButton): CheckButton = 
    val res = v.asInstanceOf[CheckButton]
    
    res

  def init(v: CheckButton): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(): CheckButton = {
    val res = new org.gnome.gtk.CheckButton()
    
    res.asInstanceOf[CheckButton]
  }
  
  def apply(
    
    actionName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    actionTargetValue: Opt[Binding[org.gnome.glib.Variant | Null]] = UnsetParam,
    active: Opt[Binding[Boolean]] = UnsetParam,
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
    inconsistent: Opt[Binding[Boolean]] = UnsetParam,
    label: Opt[Binding[java.lang.String | Null]] = UnsetParam,
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
  ): VarContextAction[CheckButton] = {
    val res = uninitialized()
    guarana.gtk.CheckButton.init(res)
    ifSet(actionName, res.actionName := _)
    ifSet(actionTargetValue, res.actionTargetValue := _)
    ifSet(active, res.active := _)
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
    ifSet(inconsistent, res.inconsistent := _)
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
        