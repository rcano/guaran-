package guarana
package gtk
import util.*
opaque type FileChooserDialog <: Dialog = org.gnome.gtk.FileChooserDialog & Dialog
object FileChooserDialog extends VarsMap {
  @deprecated("", "") val Action: ExternalVar.Aux[FileChooserDialog, org.gnome.gtk.FileChooserAction] = ExternalVar[FileChooserDialog, org.gnome.gtk.FileChooserAction]("action", _.getAction(), _.setAction(_), true)
  @deprecated("", "") val CreateFolders: ExternalVar.Aux[FileChooserDialog, Boolean] = ExternalVar[FileChooserDialog, Boolean]("create-folders", _.getCreateFolders(), _.setCreateFolders(_), true)
  @deprecated("", "") val CurrentFolder: ExternalVar.Aux[FileChooserDialog, org.gnome.gio.File | Null] = ExternalVar[FileChooserDialog, org.gnome.gio.File | Null]("current-folder", _.getCurrentFolder(), _.setCurrentFolder(_), true)
  @deprecated("", "") val SelectMultiple: ExternalVar.Aux[FileChooserDialog, Boolean] = ExternalVar[FileChooserDialog, Boolean]("select-multiple", _.getSelectMultiple(), _.setSelectMultiple(_), true)
  ()
  extension (v: FileChooserDialog) {
    def unwrap: org.gnome.gtk.FileChooserDialog = v
    @deprecated("", "") def action: Var.Aux[org.gnome.gtk.FileChooserAction, v.type] = Action.asInstanceOf[Var.Aux[org.gnome.gtk.FileChooserAction, v.type]]
    @deprecated("", "") def createFolders: Var.Aux[Boolean, v.type] = CreateFolders.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def currentFolder: Var.Aux[org.gnome.gio.File | Null, v.type] = CurrentFolder.asInstanceOf[Var.Aux[org.gnome.gio.File | Null, v.type]]
    @deprecated("", "") def selectMultiple: Var.Aux[Boolean, v.type] = SelectMultiple.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def _wrap(v: org.gnome.gtk.FileChooserDialog): FileChooserDialog = {
    v.asInstanceOf
  }
  def init(v: FileChooserDialog): ToolkitAction[Toolkit, Unit] = {
    Dialog.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: java.lang.String | Null, arg$1: org.gnome.gtk.Window | Null, arg$2: org.gnome.gtk.FileChooserAction, arg$3: java.lang.String | Null, arg$4: Array[java.lang.Object]): FileChooserDialog = {
    val res = new org.gnome.gtk.FileChooserDialog(arg$0, arg$1, arg$2, arg$3, arg$4)
    res.asInstanceOf[FileChooserDialog]
  }
  def apply(arg$0: java.lang.String | Null, arg$1: org.gnome.gtk.Window | Null, arg$2: org.gnome.gtk.FileChooserAction, arg$3: java.lang.String | Null, arg$4: Array[java.lang.Object], action: Opt[org.gnome.gtk.FileChooserAction] = UnsetParam, application: Opt[org.gnome.gtk.Application | Null] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, createFolders: Opt[Boolean] = UnsetParam, currentFolder: Opt[org.gnome.gio.File | Null] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, decorated: Opt[Boolean] = UnsetParam, defaultWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, deletable: Opt[Boolean] = UnsetParam, destroyWithParent: Opt[Boolean] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, display: Opt[org.gnome.gdk.Display] = UnsetParam, focus: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusVisible: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, gravity: Opt[org.gnome.gtk.WindowGravity] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, handleMenubarAccel: Opt[Boolean] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, hideOnClose: Opt[Boolean] = UnsetParam, iconName: Opt[java.lang.String | Null] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, mnemonicsVisible: Opt[Boolean] = UnsetParam, modal: Opt[Boolean] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, resizable: Opt[Boolean] = UnsetParam, selectMultiple: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, title: Opt[java.lang.String | Null] = UnsetParam, titlebar: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, transientFor: Opt[org.gnome.gtk.Window | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, FileChooserDialog] = {
    val res = uninitialized(arg$0, arg$1, arg$2, arg$3, arg$4)
    init(res)
    ifSet(action, res.action := _)
    ifSet(application, res.application := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(createFolders, res.createFolders := _)
    ifSet(currentFolder, res.currentFolder := _)
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
    ifSet(layoutManager, res.layoutManager := _)
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
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(resizable, res.resizable := _)
    ifSet(selectMultiple, res.selectMultiple := _)
    ifSet(sensitive, res.sensitive := _)
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