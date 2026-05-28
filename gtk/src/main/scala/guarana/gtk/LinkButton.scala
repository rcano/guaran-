package guarana
package gtk
import util.*
opaque type LinkButton <: Button = org.gnome.gtk.LinkButton & Button
object LinkButton extends VarsMap {
  val Uri: ExternalVar.Aux[LinkButton, java.lang.String] = ExternalVar[LinkButton, java.lang.String]("uri", _.getUri(), _.setUri(_), true)
  val Visited: ExternalVar.Aux[LinkButton, Boolean] = ExternalVar[LinkButton, Boolean]("visited", _.getVisited(), _.setVisited(_), true)
  ()
  extension (v: LinkButton) {
    def unwrap: org.gnome.gtk.LinkButton = v
    def uri: Var.Aux[java.lang.String, v.type] = Uri.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def visited: Var.Aux[Boolean, v.type] = Visited.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivateLink
  }
  def _wrap(v: org.gnome.gtk.LinkButton): LinkButton = {
    v.asInstanceOf
  }
  def init(v: LinkButton): ToolkitAction[Toolkit, Unit] = {
    Button.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: java.lang.String): LinkButton = {
    val res = new org.gnome.gtk.LinkButton(arg$0)
    res.asInstanceOf[LinkButton]
  }
  def apply(arg$0: java.lang.String, actionName: Opt[java.lang.String | Null] = UnsetParam, actionTargetValue: Opt[org.gnome.glib.Variant | Null] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canShrink: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasFrame: Opt[Boolean] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, uri: Opt[java.lang.String] = UnsetParam, useUnderline: Opt[Boolean] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, visited: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, LinkButton] = {
    val res = uninitialized(arg$0)
    init(res)
    ifSet(actionName, res.actionName := _)
    ifSet(actionTargetValue, res.actionTargetValue := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canShrink, res.canShrink := _)
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
    ifSet(hasFrame, res.hasFrame := _)
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