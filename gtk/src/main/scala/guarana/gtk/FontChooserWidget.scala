package guarana
package gtk
import util.*
opaque type FontChooserWidget <: Widget = org.gnome.gtk.FontChooserWidget & Widget
object FontChooserWidget extends VarsMap {
  @deprecated("", "") val Language: ExternalVar.Aux[FontChooserWidget, java.lang.String] = ExternalVar[FontChooserWidget, java.lang.String]("language", _.getLanguage(), _.setLanguage(_), true)
  @deprecated("", "") val Level: ExternalVar.Aux[FontChooserWidget, java.util.Set[org.gnome.gtk.FontChooserLevel]] = ExternalVar[FontChooserWidget, java.util.Set[org.gnome.gtk.FontChooserLevel]]("level", _.getLevel(), _.setLevel(_), true)
  @deprecated("", "") val PreviewText: ExternalVar.Aux[FontChooserWidget, java.lang.String] = ExternalVar[FontChooserWidget, java.lang.String]("preview-text", _.getPreviewText(), _.setPreviewText(_), true)
  @deprecated("", "") val ShowPreviewEntry: ExternalVar.Aux[FontChooserWidget, Boolean] = ExternalVar[FontChooserWidget, Boolean]("show-preview-entry", _.getShowPreviewEntry(), _.setShowPreviewEntry(_), true)
  ()
  extension (v: FontChooserWidget) {
    def unwrap: org.gnome.gtk.FontChooserWidget = v
    @deprecated("", "") def language: Var.Aux[java.lang.String, v.type] = Language.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def level: Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type] = Level.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type]]
    @deprecated("", "") def previewText: Var.Aux[java.lang.String, v.type] = PreviewText.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def showPreviewEntry: Var.Aux[Boolean, v.type] = ShowPreviewEntry.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def _wrap(v: org.gnome.gtk.FontChooserWidget): FontChooserWidget = {
    v.asInstanceOf
  }
  def init(v: FontChooserWidget): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): FontChooserWidget = {
    val res = new org.gnome.gtk.FontChooserWidget()
    res.asInstanceOf[FontChooserWidget]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, language: Opt[java.lang.String] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, level: Opt[java.util.Set[org.gnome.gtk.FontChooserLevel]] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, previewText: Opt[java.lang.String] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showPreviewEntry: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, FontChooserWidget] = {
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
    ifSet(previewText, res.previewText := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showPreviewEntry, res.showPreviewEntry := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}