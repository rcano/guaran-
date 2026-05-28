package guarana
package gtk
import util.*
opaque type FontChooserDialog <: Dialog = org.gnome.gtk.FontChooserDialog & Dialog
object FontChooserDialog extends VarsMap {
  @deprecated("", "") val Language: ExternalVar.Aux[FontChooserDialog, java.lang.String] = ExternalVar[FontChooserDialog, java.lang.String]("language", _.getLanguage(), _.setLanguage(_), true)
  @deprecated("", "") val Level: ExternalVar.Aux[FontChooserDialog, java.util.Set[org.gnome.gtk.FontChooserLevel]] = ExternalVar[FontChooserDialog, java.util.Set[org.gnome.gtk.FontChooserLevel]]("level", _.getLevel(), _.setLevel(_), true)
  @deprecated("", "") val PreviewText: ExternalVar.Aux[FontChooserDialog, java.lang.String] = ExternalVar[FontChooserDialog, java.lang.String]("preview-text", _.getPreviewText(), _.setPreviewText(_), true)
  @deprecated("", "") val ShowPreviewEntry: ExternalVar.Aux[FontChooserDialog, Boolean] = ExternalVar[FontChooserDialog, Boolean]("show-preview-entry", _.getShowPreviewEntry(), _.setShowPreviewEntry(_), true)
  ()
  extension (v: FontChooserDialog) {
    def unwrap: org.gnome.gtk.FontChooserDialog = v
    @deprecated("", "") def language: Var.Aux[java.lang.String, v.type] = Language.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def level: Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type] = Level.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.FontChooserLevel], v.type]]
    @deprecated("", "") def previewText: Var.Aux[java.lang.String, v.type] = PreviewText.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def showPreviewEntry: Var.Aux[Boolean, v.type] = ShowPreviewEntry.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def _wrap(v: org.gnome.gtk.FontChooserDialog): FontChooserDialog = {
    v.asInstanceOf
  }
  def init(v: FontChooserDialog): ToolkitAction[Toolkit, Unit] = {
    Dialog.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: java.lang.String | Null, arg$1: org.gnome.gtk.Window | Null): FontChooserDialog = {
    val res = new org.gnome.gtk.FontChooserDialog(arg$0, arg$1)
    res.asInstanceOf[FontChooserDialog]
  }
  def apply(arg$0: java.lang.String | Null, arg$1: org.gnome.gtk.Window | Null, application: Opt[org.gnome.gtk.Application | Null] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, decorated: Opt[Boolean] = UnsetParam, defaultWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, deletable: Opt[Boolean] = UnsetParam, destroyWithParent: Opt[Boolean] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, display: Opt[org.gnome.gdk.Display] = UnsetParam, focus: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusVisible: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, gravity: Opt[org.gnome.gtk.WindowGravity] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, handleMenubarAccel: Opt[Boolean] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, hideOnClose: Opt[Boolean] = UnsetParam, iconName: Opt[java.lang.String | Null] = UnsetParam, language: Opt[java.lang.String] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, level: Opt[java.util.Set[org.gnome.gtk.FontChooserLevel]] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, mnemonicsVisible: Opt[Boolean] = UnsetParam, modal: Opt[Boolean] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, previewText: Opt[java.lang.String] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, resizable: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showPreviewEntry: Opt[Boolean] = UnsetParam, title: Opt[java.lang.String | Null] = UnsetParam, titlebar: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, transientFor: Opt[org.gnome.gtk.Window | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, FontChooserDialog] = {
    val res = uninitialized(arg$0, arg$1)
    init(res)
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