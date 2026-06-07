
package guarana
package gtk

import guarana.util.*

opaque type FontDialogButton <: guarana.gtk.Widget  = org.gnome.gtk.FontDialogButton & guarana.gtk.Widget
object FontDialogButton extends VarsMap {
  val FontFeatures: ExternalVar.Aux[FontDialogButton, java.lang.String | Null] = ExternalVar[FontDialogButton, java.lang.String | Null]("font-features", _.getFontFeatures(), _.setFontFeatures(_), true)
  val Language: ExternalVar.Aux[FontDialogButton, org.gnome.pango.Language | Null] = ExternalVar[FontDialogButton, org.gnome.pango.Language | Null]("language", _.getLanguage(), _.setLanguage(_), true)
  val Level: ExternalVar.Aux[FontDialogButton, org.gnome.gtk.FontLevel] = ExternalVar[FontDialogButton, org.gnome.gtk.FontLevel]("level", _.getLevel(), _.setLevel(_), true)
  val UseFont: ExternalVar.Aux[FontDialogButton, Boolean] = ExternalVar[FontDialogButton, Boolean]("use-font", _.getUseFont(), _.setUseFont(_), true)
  val UseSize: ExternalVar.Aux[FontDialogButton, Boolean] = ExternalVar[FontDialogButton, Boolean]("use-size", _.getUseSize(), _.setUseSize(_), true)

  

  extension (v: FontDialogButton) {
    def unwrap: org.gnome.gtk.FontDialogButton = v

    def fontFeatures: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.FontDialogButton.FontFeatures.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def language: Var.Aux[org.gnome.pango.Language | Null, v.type] = guarana.gtk.FontDialogButton.Language.asInstanceOf[Var.Aux[org.gnome.pango.Language | Null, v.type]]
    def level: Var.Aux[org.gnome.gtk.FontLevel, v.type] = guarana.gtk.FontDialogButton.Level.asInstanceOf[Var.Aux[org.gnome.gtk.FontLevel, v.type]]
    def useFont: Var.Aux[Boolean, v.type] = guarana.gtk.FontDialogButton.UseFont.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useSize: Var.Aux[Boolean, v.type] = guarana.gtk.FontDialogButton.UseSize.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivate,
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

  def wrap(v: org.gnome.gtk.FontDialogButton): FontDialogButton = 
    val res = v.asInstanceOf[FontDialogButton]
    
    res

  def init(v: FontDialogButton): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(dialog: Opt[org.gnome.gtk.FontDialog], fontDesc: Opt[org.gnome.pango.FontDescription], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): FontDialogButton = {
    val res = {
      val res = org.gnome.gtk.FontDialogButton.builder()
      ifSet(dialog, v => res.setDialog(v))
      ifSet(fontDesc, v => res.setFontDesc(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[FontDialogButton]
  }
  
  def apply(
    dialog: Opt[org.gnome.gtk.FontDialog] = UnsetParam, fontDesc: Opt[org.gnome.pango.FontDescription] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontFeatures: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    language: Opt[Binding[org.gnome.pango.Language | Null]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    level: Opt[Binding[org.gnome.gtk.FontLevel]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    useFont: Opt[Binding[Boolean]] = UnsetParam,
    useSize: Opt[Binding[Boolean]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[FontDialogButton] = {
    val res = uninitialized(dialog, fontDesc, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.FontDialogButton.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
        