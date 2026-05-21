package guarana
package gtk
opaque type PasswordEntry <: Widget = org.gnome.gtk.PasswordEntry & Widget
object PasswordEntry {
  val ExtraMenu: ExternalVar.Aux[PasswordEntry, org.gnome.gio.MenuModel | Null] = ExternalVar[PasswordEntry, org.gnome.gio.MenuModel | Null]("extra-menu", _.getExtraMenu(), _.setExtraMenu(_), true)
  val ShowPeekIcon: ExternalVar.Aux[PasswordEntry, Boolean] = ExternalVar[PasswordEntry, Boolean]("show-peek-icon", _.getShowPeekIcon(), _.setShowPeekIcon(_), true)
  ()
  extension (v: PasswordEntry) {
    def unwrap: org.gnome.gtk.PasswordEntry = v
    export unwrap.onActivate
  }
}