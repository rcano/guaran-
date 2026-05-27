package guarana
package gtk
opaque type ToggleButton <: Button = org.gnome.gtk.ToggleButton & Button
object ToggleButton {
  val Active: ExternalVar.Aux[ToggleButton, Boolean] = ExternalVar[ToggleButton, Boolean]("active", _.getActive(), _.setActive(_), true)
  ()
  extension (v: ToggleButton) {
    def unwrap: org.gnome.gtk.ToggleButton = v
    export unwrap.onToggled
  }
  def init(v: ToggleButton): Unit = {
    Button.init(v)
  }
  def uninitialized(): ToggleButton = {
    val res = new org.gnome.gtk.ToggleButton()
    res.asInstanceOf[ToggleButton]
  }
}