package guarana
package gtk
opaque type CheckButton <: Widget = org.gnome.gtk.CheckButton & Widget
object CheckButton {
  val Active: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("active", _.getActive(), _.setActive(_), true)
  val Inconsistent: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("inconsistent", _.getInconsistent(), _.setInconsistent(_), true)
  val UseUnderline: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)
  ()
  extension (v: CheckButton) {
    def unwrap: org.gnome.gtk.CheckButton = v
    export unwrap.onActivate, unwrap.onToggled
  }
  def init(v: CheckButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): CheckButton = {
    val res = new org.gnome.gtk.CheckButton()
    res.asInstanceOf[CheckButton]
  }
}