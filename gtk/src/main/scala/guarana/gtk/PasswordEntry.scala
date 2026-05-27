package guarana
package gtk
opaque type PasswordEntry <: Widget = org.gnome.gtk.PasswordEntry & Widget
object PasswordEntry {
  val ShowPeekIcon: ExternalVar.Aux[PasswordEntry, Boolean] = ExternalVar[PasswordEntry, Boolean]("show-peek-icon", _.getShowPeekIcon(), _.setShowPeekIcon(_), true)
  ()
  extension (v: PasswordEntry) {
    def unwrap: org.gnome.gtk.PasswordEntry = v
    export unwrap.onActivate
  }
  def init(v: PasswordEntry): Unit = {
    Widget.init(v)
  }
  def uninitialized(): PasswordEntry = {
    val res = new org.gnome.gtk.PasswordEntry()
    res.asInstanceOf[PasswordEntry]
  }
}