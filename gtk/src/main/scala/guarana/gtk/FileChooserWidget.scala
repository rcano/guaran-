package guarana
package gtk
opaque type FileChooserWidget <: Widget = org.gnome.gtk.FileChooserWidget & Widget
object FileChooserWidget {
  ()
  extension (v: FileChooserWidget) {
    def unwrap: org.gnome.gtk.FileChooserWidget = v
    export unwrap.onDesktopFolder, unwrap.onDownFolder, unwrap.onHomeFolder, unwrap.onLocationPopup, unwrap.onLocationPopupOnPaste, unwrap.onLocationTogglePopup, unwrap.onPlacesShortcut, unwrap.onQuickBookmark, unwrap.onRecentShortcut, unwrap.onSearchShortcut, unwrap.onShowHidden, unwrap.onUpFolder
  }
}