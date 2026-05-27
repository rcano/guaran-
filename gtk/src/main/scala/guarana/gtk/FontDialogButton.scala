package guarana
package gtk
opaque type FontDialogButton <: Widget = org.gnome.gtk.FontDialogButton & Widget
object FontDialogButton {
  val Level: ExternalVar.Aux[FontDialogButton, org.gnome.gtk.FontLevel | Null] = ExternalVar[FontDialogButton, org.gnome.gtk.FontLevel | Null]("level", _.getLevel(), _.setLevel(_), true)
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