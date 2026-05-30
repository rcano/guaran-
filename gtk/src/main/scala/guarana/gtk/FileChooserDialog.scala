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
  def apply(arg$0: java.lang.String | Null, arg$1: org.gnome.gtk.Window | Null, arg$2: org.gnome.gtk.FileChooserAction, arg$3: java.lang.String | Null, arg$4: Array[java.lang.Object], action: Opt[Binding[org.gnome.gtk.FileChooserAction]] = UnsetParam, application: Opt[Binding[org.gnome.gtk.Application | Null]] = UnsetParam, canFocus: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, child: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, createFolders: Opt[Binding[Boolean]] = UnsetParam, currentFolder: Opt[Binding[org.gnome.gio.File | Null]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, decorated: Opt[Binding[Boolean]] = UnsetParam, defaultWidget: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, deletable: Opt[Binding[Boolean]] = UnsetParam, destroyWithParent: Opt[Binding[Boolean]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, display: Opt[Binding[org.gnome.gdk.Display]] = UnsetParam, focus: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusVisible: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, gravity: Opt[Binding[org.gnome.gtk.WindowGravity]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, handleMenubarAccel: Opt[Binding[Boolean]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, hideOnClose: Opt[Binding[Boolean]] = UnsetParam, iconName: Opt[Binding[java.lang.String | Null]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, mnemonicsVisible: Opt[Binding[Boolean]] = UnsetParam, modal: Opt[Binding[Boolean]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, resizable: Opt[Binding[Boolean]] = UnsetParam, selectMultiple: Opt[Binding[Boolean]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, title: Opt[Binding[java.lang.String | Null]] = UnsetParam, titlebar: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, transientFor: Opt[Binding[org.gnome.gtk.Window | Null]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam): ToolkitAction[Toolkit, FileChooserDialog] = {
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