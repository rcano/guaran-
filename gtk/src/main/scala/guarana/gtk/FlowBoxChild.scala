package guarana
package gtk
opaque type FlowBoxChild <: Widget = org.gnome.gtk.FlowBoxChild & Widget
object FlowBoxChild {
  val Child: ExternalVar.Aux[FlowBoxChild, org.gnome.gtk.Widget | Null] = ExternalVar[FlowBoxChild, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  ()
  extension (v: FlowBoxChild) {
    def unwrap: org.gnome.gtk.FlowBoxChild = v
    export unwrap.onActivate
  }
}