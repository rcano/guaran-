package guarana
package gtk
opaque type StackSidebar <: Widget = org.gnome.gtk.StackSidebar & Widget
object StackSidebar {
  val Stack: ExternalVar.Aux[StackSidebar, org.gnome.gtk.Stack | Null] = ExternalVar[StackSidebar, org.gnome.gtk.Stack | Null]("stack", _.getStack(), _.setStack(_), true)
  ()
  extension (v: StackSidebar) {
    def unwrap: org.gnome.gtk.StackSidebar = v
  }
}