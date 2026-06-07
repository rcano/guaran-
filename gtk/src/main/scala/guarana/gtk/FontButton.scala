
package guarana
package gtk

import guarana.util.*

opaque type FontButton <: guarana.gtk.Widget  = org.gnome.gtk.FontButton & guarana.gtk.Widget
object FontButton extends VarsMap {
  @deprecated("", "") val Language: ExternalVar.Aux[FontButton, java.lang.String] = ExternalVar[FontButton, java.lang.String]("language", _.getLanguage(), _.setLanguage(_), true)
  @deprecated("", "") val Level: ExternalVar.Aux[FontButton, java.util.Set[org.gnome.gtk.FontChooserLevel]] = ExternalVar[FontButton, java.util.Set[org.gnome.gtk.FontChooserLevel]]("level", _.getLevel(), _.setLevel(_), true)
  @deprecated("", "") val Modal: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("modal", _.getModal(), _.setModal(_), true)
  @deprecated("", "") val PreviewText: ExternalVar.Aux[FontButton, java.lang.String] = ExternalVar[FontButton, java.lang.String]("preview-text", _.getPreviewText(), _.setPreviewText(_), true)
  @deprecated("", "") val ShowPreviewEntry: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("show-preview-entry", _.getShowPreviewEntry(), _.setShowPreviewEntry(_), true)
  @deprecated("", "") val Title: ExternalVar.Aux[FontButton, java.lang.String] = ExternalVar[FontButton, java.lang.String]("title", _.getTitle(), _.setTitle(_), true)
  @deprecated("", "") val UseFont: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("use-font", _.getUseFont(), _.setUseFont(_), true)
  @deprecated("", "") val UseSize: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("use-size", _.getUseSize(), _.setUseSize(_), true)

  

  extension (v: FontButton) {
    def unwrap: org.gnome.gtk.FontButton = v

    @deprecated("", "") def language: Var.Aux[java.lang.String, v.type] = guarana.gtk.FontButton.Language.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def level: Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type] = guarana.gtk.FontButton.Level.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type]]
    @deprecated("", "") def modal: Var.Aux[Boolean, v.type] = guarana.gtk.FontButton.Modal.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def previewText: Var.Aux[java.lang.String, v.type] = guarana.gtk.FontButton.PreviewText.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def showPreviewEntry: Var.Aux[Boolean, v.type] = guarana.gtk.FontButton.ShowPreviewEntry.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def title: Var.Aux[java.lang.String, v.type] = guarana.gtk.FontButton.Title.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def useFont: Var.Aux[Boolean, v.type] = guarana.gtk.FontButton.UseFont.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def useSize: Var.Aux[Boolean, v.type] = guarana.gtk.FontButton.UseSize.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivate,
      onDestroy,
      onDirectionChanged,
      onFontSet,
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

  def wrap(v: org.gnome.gtk.FontButton): FontButton = 
    val res = v.asInstanceOf[FontButton]
    
    res

  def init(v: FontButton): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(): FontButton = {
    val res = new org.gnome.gtk.FontButton()
    
    res.asInstanceOf[FontButton]
  }
  
  def apply(
    
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
    language: Opt[Binding[java.lang.String]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    level: Opt[Binding[java.util.Set[org.gnome.gtk.FontChooserLevel]]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    modal: Opt[Binding[Boolean]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    previewText: Opt[Binding[java.lang.String]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showPreviewEntry: Opt[Binding[Boolean]] = UnsetParam,
    title: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    useFont: Opt[Binding[Boolean]] = UnsetParam,
    useSize: Opt[Binding[Boolean]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[FontButton] = {
    val res = uninitialized()
    guarana.gtk.FontButton.init(res)
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
        