package guarana
package gtk
opaque type StackSwitcher <: Widget = org.gnome.gtk.StackSwitcher & Widget
object StackSwitcher {
  val Orientation: ExternalVar.Aux[StackSwitcher, org.gnome.gtk.Orientation] = ExternalVar[StackSwitcher, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Stack: ExternalVar.Aux[StackSwitcher, org.gnome.gtk.Stack | Null] = ExternalVar[StackSwitcher, org.gnome.gtk.Stack | Null]("stack", _.getStack(), _.setStack(_), true)
  ()
  extension (v: StackSwitcher) {
    def unwrap: org.gnome.gtk.StackSwitcher = v
  }
  def init(v: StackSwitcher): Unit = {
    Widget.init(v)
  }
  def uninitialized(): StackSwitcher = {
    val res = new org.gnome.gtk.StackSwitcher()
    res.asInstanceOf[StackSwitcher]
  }
}