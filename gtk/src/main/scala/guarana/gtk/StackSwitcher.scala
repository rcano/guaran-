package guarana
package gtk
opaque type StackSwitcher <: Widget = org.gnome.gtk.StackSwitcher & Widget
object StackSwitcher {
  val Stack: ExternalVar.Aux[StackSwitcher, org.gnome.gtk.Stack | Null] = ExternalVar[StackSwitcher, org.gnome.gtk.Stack | Null]("stack", _.getStack(), _.setStack(_), true)
  ()
  extension (v: StackSwitcher) {
    def unwrap: org.gnome.gtk.StackSwitcher = v
  }
}