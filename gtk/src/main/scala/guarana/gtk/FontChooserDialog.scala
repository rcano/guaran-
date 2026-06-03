
package guarana
package gtk

import guarana.util.*

opaque type FontChooserDialog <: guarana.gtk.Window  = org.gnome.gtk.FontChooserDialog & guarana.gtk.Window
object FontChooserDialog extends VarsMap {
  @deprecated("", "") val Language: ExternalVar.Aux[FontChooserDialog, java.lang.String] = ExternalVar[FontChooserDialog, java.lang.String]("language", _.getLanguage(), _.setLanguage(_), true)
  @deprecated("", "") val Level: ExternalVar.Aux[FontChooserDialog, java.util.Set[org.gnome.gtk.FontChooserLevel]] = ExternalVar[FontChooserDialog, java.util.Set[org.gnome.gtk.FontChooserLevel]]("level", _.getLevel(), _.setLevel(_), true)
  @deprecated("", "") val PreviewText: ExternalVar.Aux[FontChooserDialog, java.lang.String] = ExternalVar[FontChooserDialog, java.lang.String]("preview-text", _.getPreviewText(), _.setPreviewText(_), true)
  @deprecated("", "") val ShowPreviewEntry: ExternalVar.Aux[FontChooserDialog, Boolean] = ExternalVar[FontChooserDialog, Boolean]("show-preview-entry", _.getShowPreviewEntry(), _.setShowPreviewEntry(_), true)

  

  extension (v: FontChooserDialog) {
    def unwrap: org.gnome.gtk.FontChooserDialog = v

    @deprecated("", "") def language: Var.Aux[java.lang.String, v.type] = guarana.gtk.FontChooserDialog.Language.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def level: Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type] = guarana.gtk.FontChooserDialog.Level.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type]]
    @deprecated("", "") def previewText: Var.Aux[java.lang.String, v.type] = guarana.gtk.FontChooserDialog.PreviewText.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def showPreviewEntry: Var.Aux[Boolean, v.type] = guarana.gtk.FontChooserDialog.ShowPreviewEntry.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivateDefault,
      onActivateFocus,
      onClose,
      onCloseRequest,
      onDestroy,
      onDirectionChanged,
      onEnableDebugging,
      onHide,
      onKeynavFailed,
      onKeysChanged,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onResponse,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.FontChooserDialog): FontChooserDialog = 
    val res = v.asInstanceOf[FontChooserDialog]
    
    res

  def init(v: FontChooserDialog): Unit = {
    guarana.gtk.Window.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(arg$0: java.lang.String | Null, arg$1: org.gnome.gtk.Window | Null): FontChooserDialog = {
    val res = new org.gnome.gtk.FontChooserDialog(arg$0, arg$1)
    
    res.asInstanceOf[FontChooserDialog]
  }
  
  def apply(
    arg$0: java.lang.String | Null, arg$1: org.gnome.gtk.Window | Null,
    application: Opt[Binding[org.gnome.gtk.Application | Null]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    decorated: Opt[Binding[Boolean]] = UnsetParam,
    defaultWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    deletable: Opt[Binding[Boolean]] = UnsetParam,
    destroyWithParent: Opt[Binding[Boolean]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    display: Opt[Binding[org.gnome.gdk.Display]] = UnsetParam,
    focus: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusVisible: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    gravity: Opt[Binding[org.gnome.gtk.WindowGravity]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    handleMenubarAccel: Opt[Binding[Boolean]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    hideOnClose: Opt[Binding[Boolean]] = UnsetParam,
    iconName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    language: Opt[Binding[java.lang.String]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    level: Opt[Binding[java.util.Set[org.gnome.gtk.FontChooserLevel]]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    mnemonicsVisible: Opt[Binding[Boolean]] = UnsetParam,
    modal: Opt[Binding[Boolean]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    previewText: Opt[Binding[java.lang.String]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    resizable: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showPreviewEntry: Opt[Binding[Boolean]] = UnsetParam,
    title: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    titlebar: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transientFor: Opt[Binding[org.gnome.gtk.Window | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[FontChooserDialog] = {
    val res = uninitialized(arg$0, arg$1)
    guarana.gtk.FontChooserDialog.init(res)
    ifSet(application, res.application := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(decorated, res.decorated := _)
    ifSet(defaultWidget, res.defaultWidget := _)
    ifSet(deletable, res.deletable := _)
    ifSet(destroyWithParent, res.destroyWithParent := _)
    ifSet(direction, res.direction := _)
    ifSet(display, res.display := _)
    ifSet(focus, res.focus := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusVisible, res.focusVisible := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(gravity, res.gravity := _)
    ifSet(halign, res.halign := _)
    ifSet(handleMenubarAccel, res.handleMenubarAccel := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(hideOnClose, res.hideOnClose := _)
    ifSet(iconName, res.iconName := _)
    ifSet(language, res.language := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(level, res.level := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(mnemonicsVisible, res.mnemonicsVisible := _)
    ifSet(modal, res.modal := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(previewText, res.previewText := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(resizable, res.resizable := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showPreviewEntry, res.showPreviewEntry := _)
    ifSet(title, res.title := _)
    ifSet(titlebar, res.titlebar := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(transientFor, res.transientFor := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
  
}
        