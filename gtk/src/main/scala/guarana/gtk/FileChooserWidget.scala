package guarana
package gtk
opaque type FileChooserWidget <: Widget = org.gnome.gtk.FileChooserWidget & Widget
object FileChooserWidget {
  @deprecated("", "") val Action: ExternalVar.Aux[FileChooserWidget, org.gnome.gtk.FileChooserAction] = ExternalVar[FileChooserWidget, org.gnome.gtk.FileChooserAction]("action", _.getAction(), _.setAction(_), true)
  @deprecated("", "") val CreateFolders: ExternalVar.Aux[FileChooserWidget, Boolean] = ExternalVar[FileChooserWidget, Boolean]("create-folders", _.getCreateFolders(), _.setCreateFolders(_), true)
  @deprecated("", "") val CurrentFolder: ExternalVar.Aux[FileChooserWidget, org.gnome.gio.File | Null] = ExternalVar[FileChooserWidget, org.gnome.gio.File | Null]("current-folder", _.getCurrentFolder(), _.setCurrentFolder(_), true)
  @deprecated("", "") val SelectMultiple: ExternalVar.Aux[FileChooserWidget, Boolean] = ExternalVar[FileChooserWidget, Boolean]("select-multiple", _.getSelectMultiple(), _.setSelectMultiple(_), true)
  ()
  extension (v: FileChooserWidget) {
    def unwrap: org.gnome.gtk.FileChooserWidget = v
    export unwrap.onDesktopFolder, unwrap.onDownFolder, unwrap.onHomeFolder, unwrap.onLocationPopup, unwrap.onLocationPopupOnPaste, unwrap.onLocationTogglePopup, unwrap.onPlacesShortcut, unwrap.onQuickBookmark, unwrap.onRecentShortcut, unwrap.onSearchShortcut, unwrap.onShowHidden, unwrap.onUpFolder
  }
  def init(v: FileChooserWidget): Unit = {
    Widget.init(v)
  }
  def uninitialized(): FileChooserWidget = {
    val res = new org.gnome.gtk.FileChooserWidget()
    res.asInstanceOf[FileChooserWidget]
  }
}