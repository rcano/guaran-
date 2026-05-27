package guarana
package gtk
opaque type FontButton <: Widget = org.gnome.gtk.FontButton & Widget
object FontButton {
  val Modal: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("modal", _.getModal(), _.setModal(_), true)
  val Title: ExternalVar.Aux[FontButton, java.lang.String | Null] = ExternalVar[FontButton, java.lang.String | Null]("title", _.getTitle(), _.setTitle(_), true)
  val UseFont: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("use-font", _.getUseFont(), _.setUseFont(_), true)
  val UseSize: ExternalVar.Aux[FontButton, Boolean] = ExternalVar[FontButton, Boolean]("use-size", _.getUseSize(), _.setUseSize(_), true)
  ()
  extension (v: FontButton) {
    def unwrap: org.gnome.gtk.FontButton = v
    export unwrap.onActivate, unwrap.onFontSet
  }
  def init(v: FontButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): FontButton = {
    val res = new org.gnome.gtk.FontButton()
    res.asInstanceOf[FontButton]
  }
}