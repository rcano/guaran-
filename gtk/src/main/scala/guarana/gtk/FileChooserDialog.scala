package guarana
package gtk
opaque type FileChooserDialog <: Dialog = org.gnome.gtk.FileChooserDialog & Dialog
object FileChooserDialog {
  @deprecated("", "") val Action: ExternalVar.Aux[FileChooserDialog, org.gnome.gtk.FileChooserAction] = ExternalVar[FileChooserDialog, org.gnome.gtk.FileChooserAction]("action", _.getAction(), _.setAction(_), true)
  @deprecated("", "") val CreateFolders: ExternalVar.Aux[FileChooserDialog, Boolean] = ExternalVar[FileChooserDialog, Boolean]("create-folders", _.getCreateFolders(), _.setCreateFolders(_), true)
  @deprecated("", "") val CurrentFolder: ExternalVar.Aux[FileChooserDialog, org.gnome.gio.File | Null] = ExternalVar[FileChooserDialog, org.gnome.gio.File | Null]("current-folder", _.getCurrentFolder(), _.setCurrentFolder(_), true)
  @deprecated("", "") val SelectMultiple: ExternalVar.Aux[FileChooserDialog, Boolean] = ExternalVar[FileChooserDialog, Boolean]("select-multiple", _.getSelectMultiple(), _.setSelectMultiple(_), true)
  ()
  extension (v: FileChooserDialog) {
    def unwrap: org.gnome.gtk.FileChooserDialog = v
  }
  def init(v: FileChooserDialog): Unit = {
    Dialog.init(v)
  }
  def uninitialized(): FileChooserDialog = {
    val res = new org.gnome.gtk.FileChooserDialog()
    res.asInstanceOf[FileChooserDialog]
  }
}