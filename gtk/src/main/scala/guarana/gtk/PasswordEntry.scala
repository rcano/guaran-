package guarana
package gtk
opaque type PasswordEntry <: Widget = org.gnome.gtk.PasswordEntry & Widget
object PasswordEntry {
  val Alignment: ExternalVar.Aux[PasswordEntry, Float] = ExternalVar[PasswordEntry, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val Editable: ExternalVar.Aux[PasswordEntry, Boolean] = ExternalVar[PasswordEntry, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[PasswordEntry, Boolean] = ExternalVar[PasswordEntry, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val ExtraMenu: ExternalVar.Aux[PasswordEntry, org.gnome.gio.MenuModel | Null] = ExternalVar[PasswordEntry, org.gnome.gio.MenuModel | Null]("extra-menu", _.getExtraMenu(), _.setExtraMenu(_), true)
  val MaxWidthChars: ExternalVar.Aux[PasswordEntry, Int] = ExternalVar[PasswordEntry, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val Position: ExternalVar.Aux[PasswordEntry, Int] = ExternalVar[PasswordEntry, Int]("position", _.getPosition(), _.setPosition(_), true)
  val ShowPeekIcon: ExternalVar.Aux[PasswordEntry, Boolean] = ExternalVar[PasswordEntry, Boolean]("show-peek-icon", _.getShowPeekIcon(), _.setShowPeekIcon(_), true)
  val Text: ExternalVar.Aux[PasswordEntry, java.lang.String] = ExternalVar[PasswordEntry, java.lang.String]("text", _.getText(), _.setText(_), true)
  val WidthChars: ExternalVar.Aux[PasswordEntry, Int] = ExternalVar[PasswordEntry, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)
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