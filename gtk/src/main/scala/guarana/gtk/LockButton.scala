package guarana
package gtk
opaque type LockButton <: Button = org.gnome.gtk.LockButton & Button
object LockButton {
  val Permission: ExternalVar.Aux[LockButton, org.gnome.gio.Permission | Null] = ExternalVar[LockButton, org.gnome.gio.Permission | Null]("permission", _.getPermission(), _.setPermission(_), true)
  ()
  extension (v: LockButton) {
    def unwrap: org.gnome.gtk.LockButton = v
  }
}