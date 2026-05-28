package guarana
package gtk
import util.*
opaque type FontDialogButton <: Widget = org.gnome.gtk.FontDialogButton & Widget
object FontDialogButton extends VarsMap {
  val FontFeatures: ExternalVar.Aux[FontDialogButton, java.lang.String | Null] = ExternalVar[FontDialogButton, java.lang.String | Null]("font-features", _.getFontFeatures(), _.setFontFeatures(_), true)
  val Language: ExternalVar.Aux[FontDialogButton, org.gnome.pango.Language | Null] = ExternalVar[FontDialogButton, org.gnome.pango.Language | Null]("language", _.getLanguage(), _.setLanguage(_), true)
  val Level: ExternalVar.Aux[FontDialogButton, org.gnome.gtk.FontLevel] = ExternalVar[FontDialogButton, org.gnome.gtk.FontLevel]("level", _.getLevel(), _.setLevel(_), true)
  val UseFont: ExternalVar.Aux[FontDialogButton, Boolean] = ExternalVar[FontDialogButton, Boolean]("use-font", _.getUseFont(), _.setUseFont(_), true)
  val UseSize: ExternalVar.Aux[FontDialogButton, Boolean] = ExternalVar[FontDialogButton, Boolean]("use-size", _.getUseSize(), _.setUseSize(_), true)
  ()
  extension (v: FontDialogButton) {
    def unwrap: org.gnome.gtk.FontDialogButton = v
    def fontFeatures: Var.Aux[java.lang.String | Null, v.type] = FontFeatures.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def language: Var.Aux[org.gnome.pango.Language | Null, v.type] = Language.asInstanceOf[Var.Aux[org.gnome.pango.Language | Null, v.type]]
    def level: Var.Aux[org.gnome.gtk.FontLevel, v.type] = Level.asInstanceOf[Var.Aux[org.gnome.gtk.FontLevel, v.type]]
    def useFont: Var.Aux[Boolean, v.type] = UseFont.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useSize: Var.Aux[Boolean, v.type] = UseSize.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate
  }
  def _wrap(v: org.gnome.gtk.FontDialogButton): FontDialogButton = {
    v.asInstanceOf
  }
  def init(v: FontDialogButton): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gtk.FontDialog | Null): FontDialogButton = {
    val res = new org.gnome.gtk.FontDialogButton(arg$0)
    res.asInstanceOf[FontDialogButton]
  }
  def apply(arg$0: org.gnome.gtk.FontDialog | Null, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontFeatures: Opt[java.lang.String | Null] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, language: Opt[org.gnome.pango.Language | Null] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, level: Opt[org.gnome.gtk.FontLevel] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, useFont: Opt[Boolean] = UnsetParam, useSize: Opt[Boolean] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, FontDialogButton] = {
    val res = uninitialized(arg$0)
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontFeatures, res.fontFeatures := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(language, res.language := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(level, res.level := _)
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
    ifSet(useFont, res.useFont := _)
    ifSet(useSize, res.useSize := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}