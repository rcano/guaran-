
package guarana
package gtk

import guarana.util.*

opaque type FontChooserWidget <: guarana.gtk.Widget  = org.gnome.gtk.FontChooserWidget & guarana.gtk.Widget
object FontChooserWidget extends VarsMap {
  @deprecated("", "") val Language: ExternalVar.Aux[FontChooserWidget, java.lang.String] = ExternalVar[FontChooserWidget, java.lang.String]("language", _.getLanguage(), _.setLanguage(_), true)
  @deprecated("", "") val Level: ExternalVar.Aux[FontChooserWidget, java.util.Set[org.gnome.gtk.FontChooserLevel]] = ExternalVar[FontChooserWidget, java.util.Set[org.gnome.gtk.FontChooserLevel]]("level", _.getLevel(), _.setLevel(_), true)
  @deprecated("", "") val PreviewText: ExternalVar.Aux[FontChooserWidget, java.lang.String] = ExternalVar[FontChooserWidget, java.lang.String]("preview-text", _.getPreviewText(), _.setPreviewText(_), true)
  @deprecated("", "") val ShowPreviewEntry: ExternalVar.Aux[FontChooserWidget, Boolean] = ExternalVar[FontChooserWidget, Boolean]("show-preview-entry", _.getShowPreviewEntry(), _.setShowPreviewEntry(_), true)

  

  extension (v: FontChooserWidget) {
    def unwrap: org.gnome.gtk.FontChooserWidget = v

    @deprecated("", "") def language: Var.Aux[java.lang.String, v.type] = guarana.gtk.FontChooserWidget.Language.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def level: Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type] = guarana.gtk.FontChooserWidget.Level.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type]]
    @deprecated("", "") def previewText: Var.Aux[java.lang.String, v.type] = guarana.gtk.FontChooserWidget.PreviewText.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def showPreviewEntry: Var.Aux[Boolean, v.type] = guarana.gtk.FontChooserWidget.ShowPreviewEntry.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
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

  def wrap(v: org.gnome.gtk.FontChooserWidget): FontChooserWidget = 
    val res = v.asInstanceOf[FontChooserWidget]
    
    res

  def init(v: FontChooserWidget): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], font: Opt[java.lang.String], fontDesc: Opt[org.gnome.pango.FontDescription]): FontChooserWidget = {
    val res = org.gnome.gtk.FontChooserWidget.builder()
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    ifSet(font, v => res.setFont(v))
    ifSet(fontDesc, v => res.setFontDesc(v))
    
    res.asInstanceOf[FontChooserWidget]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, font: Opt[java.lang.String] = UnsetParam, fontDesc: Opt[org.gnome.pango.FontDescription] = UnsetParam,
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
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    previewText: Opt[Binding[java.lang.String]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showPreviewEntry: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[FontChooserWidget] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole, font, fontDesc)
    guarana.gtk.FontChooserWidget.init(res)
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
        