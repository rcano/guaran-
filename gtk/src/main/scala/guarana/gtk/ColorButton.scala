package guarana
package gtk
import util.*
opaque type ColorButton <: Widget = org.gnome.gtk.ColorButton & Widget
object ColorButton extends VarsMap {
  @deprecated("", "") val Modal: ExternalVar.Aux[ColorButton, Boolean] = ExternalVar[ColorButton, Boolean]("modal", _.getModal(), _.setModal(_), true)
  @deprecated("", "") val Title: ExternalVar.Aux[ColorButton, java.lang.String] = ExternalVar[ColorButton, java.lang.String]("title", _.getTitle(), _.setTitle(_), true)
  @deprecated("", "") val UseAlpha: ExternalVar.Aux[ColorButton, Boolean] = ExternalVar[ColorButton, Boolean]("use-alpha", _.getUseAlpha(), _.setUseAlpha(_), true)
  ()
  extension (v: ColorButton) {
    def unwrap: org.gnome.gtk.ColorButton = v
    @deprecated("", "") def modal: Var.Aux[Boolean, v.type] = Modal.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def title: Var.Aux[java.lang.String, v.type] = Title.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def useAlpha: Var.Aux[Boolean, v.type] = UseAlpha.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate, unwrap.onColorSet
  }
  def _wrap(v: org.gnome.gtk.ColorButton): ColorButton = {
    v.asInstanceOf
  }
  def init(v: ColorButton): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): ColorButton = {
    val res = new org.gnome.gtk.ColorButton()
    res.asInstanceOf[ColorButton]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, modal: Opt[Boolean] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, title: Opt[java.lang.String] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, useAlpha: Opt[Boolean] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, ColorButton] = {
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
    ifSet(modal, res.modal := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(title, res.title := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(useAlpha, res.useAlpha := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}