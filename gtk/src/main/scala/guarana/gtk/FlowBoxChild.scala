package guarana
package gtk
opaque type FlowBoxChild <: Widget = org.gnome.gtk.FlowBoxChild & Widget
object FlowBoxChild {
  ()
  extension (v: FlowBoxChild) {
    def unwrap: org.gnome.gtk.FlowBoxChild = v
    export unwrap.onActivate
  }
  def init(v: FlowBoxChild): Unit = {
    Widget.init(v)
  }
  def uninitialized(): FlowBoxChild = {
    val res = new org.gnome.gtk.FlowBoxChild()
    res.asInstanceOf[FlowBoxChild]
  }
}