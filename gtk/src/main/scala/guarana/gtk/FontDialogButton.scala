package guarana
package gtk
opaque type FontDialogButton <: Widget = org.gnome.gtk.FontDialogButton & Widget
object FontDialogButton {
  val FontFeatures: ExternalVar.Aux[FontDialogButton, java.lang.String | Null] = ExternalVar[FontDialogButton, java.lang.String | Null]("font-features", _.getFontFeatures(), _.setFontFeatures(_), true)
  val Language: ExternalVar.Aux[FontDialogButton, org.gnome.pango.Language | Null] = ExternalVar[FontDialogButton, org.gnome.pango.Language | Null]("language", _.getLanguage(), _.setLanguage(_), true)
  val Level: ExternalVar.Aux[FontDialogButton, org.gnome.gtk.FontLevel] = ExternalVar[FontDialogButton, org.gnome.gtk.FontLevel]("level", _.getLevel(), _.setLevel(_), true)
  val UseFont: ExternalVar.Aux[FontDialogButton, Boolean] = ExternalVar[FontDialogButton, Boolean]("use-font", _.getUseFont(), _.setUseFont(_), true)
  val UseSize: ExternalVar.Aux[FontDialogButton, Boolean] = ExternalVar[FontDialogButton, Boolean]("use-size", _.getUseSize(), _.setUseSize(_), true)
  ()
  extension (v: FontDialogButton) {
    def unwrap: org.gnome.gtk.FontDialogButton = v
    export unwrap.onActivate
  }
  def init(v: FontDialogButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): FontDialogButton = {
    val res = new org.gnome.gtk.FontDialogButton()
    res.asInstanceOf[FontDialogButton]
  }
}