
package guarana
package gtk

import guarana.util.*

opaque type Popover <: guarana.gtk.Widget  = org.gnome.gtk.Popover & guarana.gtk.Widget
object Popover extends VarsMap {
  val Autohide: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("autohide", _.getAutohide(), _.setAutohide(_), true)
  val CascadePopdown: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("cascade-popdown", _.getCascadePopdown(), _.setCascadePopdown(_), true)
  val Child: ExternalVar.Aux[Popover, guarana.gtk.Widget | Null] = ExternalVar[Popover, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val HasArrow: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("has-arrow", _.getHasArrow(), _.setHasArrow(_), true)
  val MnemonicsVisible: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("mnemonics-visible", _.getMnemonicsVisible(), _.setMnemonicsVisible(_), true)
  val Position: ExternalVar.Aux[Popover, org.gnome.gtk.PositionType] = ExternalVar[Popover, org.gnome.gtk.PositionType]("position", _.getPosition(), _.setPosition(_), true)

  

  extension (v: Popover) {
    def unwrap: org.gnome.gtk.Popover = v

    def autohide: Var.Aux[Boolean, v.type] = guarana.gtk.Popover.Autohide.asInstanceOf[Var.Aux[Boolean, v.type]]
    def cascadePopdown: Var.Aux[Boolean, v.type] = guarana.gtk.Popover.CascadePopdown.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Popover.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def hasArrow: Var.Aux[Boolean, v.type] = guarana.gtk.Popover.HasArrow.asInstanceOf[Var.Aux[Boolean, v.type]]
    def mnemonicsVisible: Var.Aux[Boolean, v.type] = guarana.gtk.Popover.MnemonicsVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    def position: Var.Aux[org.gnome.gtk.PositionType, v.type] = guarana.gtk.Popover.Position.asInstanceOf[Var.Aux[org.gnome.gtk.PositionType, v.type]]

    

    export unwrap.{
      onActivateDefault,
      onClosed,
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

  def wrap(v: org.gnome.gtk.Popover): Popover = 
    val res = v.asInstanceOf[Popover]
    
    res

  def init(v: Popover): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(defaultWidget: Opt[guarana.gtk.Widget], pointingTo: Opt[org.gnome.gdk.Rectangle], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Popover = {
    val res = org.gnome.gtk.Popover.builder()
    ifSet(defaultWidget, v => res.setDefaultWidget(v.unwrap))
    ifSet(pointingTo, v => res.setPointingTo(v))
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    
    res.asInstanceOf[Popover]
  }
  
  def apply(
    defaultWidget: Opt[guarana.gtk.Widget] = UnsetParam, pointingTo: Opt[org.gnome.gdk.Rectangle] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    autohide: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    cascadePopdown: Opt[Binding[Boolean]] = UnsetParam,
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
    hasArrow: Opt[Binding[Boolean]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    mnemonicsVisible: Opt[Binding[Boolean]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    position: Opt[Binding[org.gnome.gtk.PositionType]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Popover] = {
    val res = uninitialized(defaultWidget, pointingTo, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.Popover.init(res)
    ifSet(autohide, res.autohide := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(cascadePopdown, res.cascadePopdown := _)
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
    ifSet(hasArrow, res.hasArrow := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(mnemonicsVisible, res.mnemonicsVisible := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(position, res.position := _)
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
        