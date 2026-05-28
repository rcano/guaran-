package guarana
package gtk
import util.*
opaque type ToggleButton <: Button = org.gnome.gtk.ToggleButton & Button
object ToggleButton {
  val Active: ExternalVar.Aux[ToggleButton, Boolean] = ExternalVar[ToggleButton, Boolean]("active", _.getActive(), _.setActive(_), true)
  ()
  extension (v: ToggleButton) {
    def unwrap: org.gnome.gtk.ToggleButton = v
    def active: Var.Aux[Boolean, v.type] = Active.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onToggled
  }
  def init(v: ToggleButton): Unit = {
    Button.init(v)
  }
  def uninitialized(): ToggleButton = {
    val res = new org.gnome.gtk.ToggleButton()
    res.asInstanceOf[ToggleButton]
  }
  def apply(active: Opt[Boolean] = UnsetParam): VarContextAction[ToggleButton] = {
    val res = uninitialized()
    init(res)
    ifSet(active, res.active := _)
    res
  }
}