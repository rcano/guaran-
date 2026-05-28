package guarana
package gtk
import util.*
opaque type InfoBar <: Widget = org.gnome.gtk.InfoBar & Widget
object InfoBar extends VarsMap {
  @deprecated("", "") val MessageType: ExternalVar.Aux[InfoBar, org.gnome.gtk.MessageType] = ExternalVar[InfoBar, org.gnome.gtk.MessageType]("message-type", _.getMessageType(), _.setMessageType(_), true)
  @deprecated("", "") val Revealed: ExternalVar.Aux[InfoBar, Boolean] = ExternalVar[InfoBar, Boolean]("revealed", _.getRevealed(), _.setRevealed(_), true)
  @deprecated("", "") val ShowCloseButton: ExternalVar.Aux[InfoBar, Boolean] = ExternalVar[InfoBar, Boolean]("show-close-button", _.getShowCloseButton(), _.setShowCloseButton(_), true)
  ()
  extension (v: InfoBar) {
    def unwrap: org.gnome.gtk.InfoBar = v
    @deprecated("", "") def messageType: Var.Aux[org.gnome.gtk.MessageType, v.type] = MessageType.asInstanceOf[Var.Aux[org.gnome.gtk.MessageType, v.type]]
    @deprecated("", "") def revealed: Var.Aux[Boolean, v.type] = Revealed.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def showCloseButton: Var.Aux[Boolean, v.type] = ShowCloseButton.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onClose, unwrap.onResponse
  }
  def _wrap(v: org.gnome.gtk.InfoBar): InfoBar = {
    v.asInstanceOf
  }
  def init(v: InfoBar): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): InfoBar = {
    val res = new org.gnome.gtk.InfoBar()
    res.asInstanceOf[InfoBar]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, messageType: Opt[org.gnome.gtk.MessageType] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, revealed: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showCloseButton: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, InfoBar] = {
    val res = uninitialized()
    init(res)
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
    ifSet(messageType, res.messageType := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(revealed, res.revealed := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showCloseButton, res.showCloseButton := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}