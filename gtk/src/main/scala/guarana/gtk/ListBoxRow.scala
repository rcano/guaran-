
package guarana
package gtk

import guarana.util.*

opaque type ListBoxRow <: guarana.gtk.Widget  = org.gnome.gtk.ListBoxRow & guarana.gtk.Widget
object ListBoxRow extends VarsMap {
  val ActionName: ExternalVar.Aux[ListBoxRow, java.lang.String | Null] = ExternalVar[ListBoxRow, java.lang.String | Null]("action-name", _.getActionName(), _.setActionName(_), true)
  val ActionTargetValue: ExternalVar.Aux[ListBoxRow, org.gnome.glib.Variant | Null] = ExternalVar[ListBoxRow, org.gnome.glib.Variant | Null]("action-target-value", _.getActionTargetValue(), _.setActionTargetValue(_), true)
  val Activatable: ExternalVar.Aux[ListBoxRow, Boolean] = ExternalVar[ListBoxRow, Boolean]("activatable", _.getActivatable(), _.setActivatable(_), true)
  val Child: ExternalVar.Aux[ListBoxRow, guarana.gtk.Widget | Null] = ExternalVar[ListBoxRow, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val Header: ExternalVar.Aux[ListBoxRow, guarana.gtk.Widget | Null] = ExternalVar[ListBoxRow, guarana.gtk.Widget | Null]("header", _.getHeader().?(guarana.gtk.Widget.wrap), (n, v) => n.setHeader(v.?(_.unwrap)), true)
  val Selectable: ExternalVar.Aux[ListBoxRow, Boolean] = ExternalVar[ListBoxRow, Boolean]("selectable", _.getSelectable(), _.setSelectable(_), true)

  

  extension (v: ListBoxRow) {
    def unwrap: org.gnome.gtk.ListBoxRow = v

    def actionName: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.ListBoxRow.ActionName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def actionTargetValue: Var.Aux[org.gnome.glib.Variant | Null, v.type] = guarana.gtk.ListBoxRow.ActionTargetValue.asInstanceOf[Var.Aux[org.gnome.glib.Variant | Null, v.type]]
    def activatable: Var.Aux[Boolean, v.type] = guarana.gtk.ListBoxRow.Activatable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.ListBoxRow.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def header: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.ListBoxRow.Header.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def selectable: Var.Aux[Boolean, v.type] = guarana.gtk.ListBoxRow.Selectable.asInstanceOf[Var.Aux[Boolean, v.type]]

    

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

  def wrap(v: org.gnome.gtk.ListBoxRow): ListBoxRow = 
    val res = v.asInstanceOf[ListBoxRow]
    
    res

  def init(v: ListBoxRow): Toolkit ?=> Unit = (tk: Toolkit) ?=> {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(): ListBoxRow = {
    val res = new org.gnome.gtk.ListBoxRow()
    
    res.asInstanceOf[ListBoxRow]
  }
  
  def apply(
    
    actionName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    actionTargetValue: Opt[Binding[org.gnome.glib.Variant | Null]] = UnsetParam,
    activatable: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
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
    header: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
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
    selectable: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Toolkit ?=> VarContextAction[ListBoxRow] = {
    val res = uninitialized()
    guarana.gtk.ListBoxRow.init(res)
    ifSet(actionName, res.actionName := _)
    ifSet(actionTargetValue, res.actionTargetValue := _)
    ifSet(activatable, res.activatable := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
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
    ifSet(header, res.header := _)
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
    ifSet(selectable, res.selectable := _)
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
        