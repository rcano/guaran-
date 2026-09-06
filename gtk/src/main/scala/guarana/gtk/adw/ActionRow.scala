
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type ActionRow <: guarana.gtk.Widget  = org.gnome.adw.ActionRow & guarana.gtk.Widget
object ActionRow extends VarsMap {
  val ActivatableWidget: ExternalVar.Aux[ActionRow, guarana.gtk.Widget | Null] = ExternalVar[ActionRow, guarana.gtk.Widget | Null]("activatable-widget", _.getActivatableWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setActivatableWidget(v.?(_.unwrap)), true)
  @deprecated("", "") val IconName: ExternalVar.Aux[ActionRow, java.lang.String | Null] = ExternalVar[ActionRow, java.lang.String | Null]("icon-name", _.getIconName(), _.setIconName(_), true)
  val SubtitleLines: ExternalVar.Aux[ActionRow, Int] = ExternalVar[ActionRow, Int]("subtitle-lines", _.getSubtitleLines(), _.setSubtitleLines(_), true)
  val SubtitleSelectable: ExternalVar.Aux[ActionRow, Boolean] = ExternalVar[ActionRow, Boolean]("subtitle-selectable", _.getSubtitleSelectable(), _.setSubtitleSelectable(_), true)
  val TitleLines: ExternalVar.Aux[ActionRow, Int] = ExternalVar[ActionRow, Int]("title-lines", _.getTitleLines(), _.setTitleLines(_), true)

  

  extension (v: ActionRow) {
    def unwrap: org.gnome.adw.ActionRow = v

    def activatableWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.ActionRow.ActivatableWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    @deprecated("", "") def iconName: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.adw.ActionRow.IconName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def subtitleLines: Var.Aux[Int, v.type] = guarana.gtk.adw.ActionRow.SubtitleLines.asInstanceOf[Var.Aux[Int, v.type]]
    def subtitleSelectable: Var.Aux[Boolean, v.type] = guarana.gtk.adw.ActionRow.SubtitleSelectable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def titleLines: Var.Aux[Int, v.type] = guarana.gtk.adw.ActionRow.TitleLines.asInstanceOf[Var.Aux[Int, v.type]]

    

    export unwrap.{
      onActivate,
      onActivated,
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

  def wrap(v: org.gnome.adw.ActionRow): ActionRow = 
    val res = v.asInstanceOf[ActionRow]
    
    res

  def init(v: ActionRow): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(subtitle: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], actionTarget: Opt[org.gnome.glib.Variant]): ActionRow = {
    val res = {
      val res = org.gnome.adw.ActionRow.builder()
      ifSet(subtitle, v => res.setSubtitle(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      ifSet(actionTarget, v => res.setActionTarget(v))
      res.build()
    }
    
    res.asInstanceOf[ActionRow]
  }
  
  def apply(
    subtitle: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, actionTarget: Opt[org.gnome.glib.Variant] = UnsetParam,
    activatableWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
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
    iconName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    subtitleLines: Opt[Binding[Int]] = UnsetParam,
    subtitleSelectable: Opt[Binding[Boolean]] = UnsetParam,
    titleLines: Opt[Binding[Int]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[ActionRow] = {
    val res = uninitialized(subtitle, cssName, heightRequest, widthRequest, accessibleRole, actionTarget)
    guarana.gtk.adw.ActionRow.init(res)
    ifSet(activatableWidget, res.activatableWidget := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
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
    ifSet(iconName, res.iconName := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(subtitleLines, res.subtitleLines := _)
    ifSet(subtitleSelectable, res.subtitleSelectable := _)
    ifSet(titleLines, res.titleLines := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        