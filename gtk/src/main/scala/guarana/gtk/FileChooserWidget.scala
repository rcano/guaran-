package guarana
package gtk
import util.*
opaque type FileChooserWidget <: Widget = org.gnome.gtk.FileChooserWidget & Widget
object FileChooserWidget extends VarsMap {
  @deprecated("", "") val Action: ExternalVar.Aux[FileChooserWidget, org.gnome.gtk.FileChooserAction] = ExternalVar[FileChooserWidget, org.gnome.gtk.FileChooserAction]("action", _.getAction(), _.setAction(_), true)
  @deprecated("", "") val CreateFolders: ExternalVar.Aux[FileChooserWidget, Boolean] = ExternalVar[FileChooserWidget, Boolean]("create-folders", _.getCreateFolders(), _.setCreateFolders(_), true)
  @deprecated("", "") val CurrentFolder: ExternalVar.Aux[FileChooserWidget, org.gnome.gio.File | Null] = ExternalVar[FileChooserWidget, org.gnome.gio.File | Null]("current-folder", _.getCurrentFolder(), _.setCurrentFolder(_), true)
  @deprecated("", "") val SelectMultiple: ExternalVar.Aux[FileChooserWidget, Boolean] = ExternalVar[FileChooserWidget, Boolean]("select-multiple", _.getSelectMultiple(), _.setSelectMultiple(_), true)
  ()
  extension (v: FileChooserWidget) {
    def unwrap: org.gnome.gtk.FileChooserWidget = v
    @deprecated("", "") def action: Var.Aux[org.gnome.gtk.FileChooserAction, v.type] = Action.asInstanceOf[Var.Aux[org.gnome.gtk.FileChooserAction, v.type]]
    @deprecated("", "") def createFolders: Var.Aux[Boolean, v.type] = CreateFolders.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def currentFolder: Var.Aux[org.gnome.gio.File | Null, v.type] = CurrentFolder.asInstanceOf[Var.Aux[org.gnome.gio.File | Null, v.type]]
    @deprecated("", "") def selectMultiple: Var.Aux[Boolean, v.type] = SelectMultiple.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onDesktopFolder, unwrap.onDownFolder, unwrap.onHomeFolder, unwrap.onLocationPopup, unwrap.onLocationPopupOnPaste, unwrap.onLocationTogglePopup, unwrap.onPlacesShortcut, unwrap.onQuickBookmark, unwrap.onRecentShortcut, unwrap.onSearchShortcut, unwrap.onShowHidden, unwrap.onUpFolder
  }
  def _wrap(v: org.gnome.gtk.FileChooserWidget): FileChooserWidget = {
    v.asInstanceOf
  }
  def init(v: FileChooserWidget): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gtk.FileChooserAction): FileChooserWidget = {
    val res = new org.gnome.gtk.FileChooserWidget(arg$0)
    res.asInstanceOf[FileChooserWidget]
  }
  def apply(arg$0: org.gnome.gtk.FileChooserAction, action: Opt[org.gnome.gtk.FileChooserAction] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, createFolders: Opt[Boolean] = UnsetParam, currentFolder: Opt[org.gnome.gio.File | Null] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, selectMultiple: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, FileChooserWidget] = {
    val res = uninitialized(arg$0)
    init(res)
    ifSet(action, res.action := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(createFolders, res.createFolders := _)
    ifSet(currentFolder, res.currentFolder := _)
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
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(selectMultiple, res.selectMultiple := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}