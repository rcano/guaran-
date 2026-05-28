package guarana
package gtk
import util.*
opaque type FontButton <: Widget = org.gnome.gtk.FontButton & Widget
object FontButton extends VarsMap {
  @deprecated("", "") val Language: ExternalVar.Aux[FontButton, java.lang.String] = ExternalVar[FontButton, java.lang.String]("language", _.getLanguage(), _.setLanguage(_), true)
  @deprecated("", "") val Level: ExternalVar.Aux[FontButton, java.util.Set[org.gnome.gtk.FontChooserLevel]] = ExternalVar[FontButton, java.util.Set[org.gnome.gtk.FontChooserLevel]]("level", _.getLevel(), _.setLevel(_), true)
  @deprecated("", "") val Modal: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("modal", _.getModal(), _.setModal(_), true)
  @deprecated("", "") val PreviewText: ExternalVar.Aux[FontButton, java.lang.String] = ExternalVar[FontButton, java.lang.String]("preview-text", _.getPreviewText(), _.setPreviewText(_), true)
  @deprecated("", "") val ShowPreviewEntry: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("show-preview-entry", _.getShowPreviewEntry(), _.setShowPreviewEntry(_), true)
  @deprecated("", "") val Title: ExternalVar.Aux[FontButton, java.lang.String] = ExternalVar[FontButton, java.lang.String]("title", _.getTitle(), _.setTitle(_), true)
  @deprecated("", "") val UseFont: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("use-font", _.getUseFont(), _.setUseFont(_), true)
  @deprecated("", "") val UseSize: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("use-size", _.getUseSize(), _.setUseSize(_), true)
  ()
  extension (v: FontButton) {
    def unwrap: org.gnome.gtk.FontButton = v
    @deprecated("", "") def language: Var.Aux[java.lang.String, v.type] = Language.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def level: Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type] = Level.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type]]
    @deprecated("", "") def modal: Var.Aux[Boolean, v.type] = Modal.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def previewText: Var.Aux[java.lang.String, v.type] = PreviewText.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def showPreviewEntry: Var.Aux[Boolean, v.type] = ShowPreviewEntry.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def title: Var.Aux[java.lang.String, v.type] = Title.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def useFont: Var.Aux[Boolean, v.type] = UseFont.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def useSize: Var.Aux[Boolean, v.type] = UseSize.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate, unwrap.onFontSet
  }
  def _wrap(v: org.gnome.gtk.FontButton): FontButton = {
    v.asInstanceOf
  }
  def init(v: FontButton): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): FontButton = {
    val res = new org.gnome.gtk.FontButton()
    res.asInstanceOf[FontButton]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, language: Opt[java.lang.String] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, level: Opt[java.util.Set[org.gnome.gtk.FontChooserLevel]] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, modal: Opt[Boolean] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, previewText: Opt[java.lang.String] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showPreviewEntry: Opt[Boolean] = UnsetParam, title: Opt[java.lang.String] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, useFont: Opt[Boolean] = UnsetParam, useSize: Opt[Boolean] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, FontButton] = {
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
    ifSet(modal, res.modal := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(previewText, res.previewText := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showPreviewEntry, res.showPreviewEntry := _)
    ifSet(title, res.title := _)
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