package guarana
package gtk
import util.*
opaque type Overlay <: Widget = org.gnome.gtk.Overlay & Widget
object Overlay {
  val Child: ExternalVar.Aux[Overlay, org.gnome.gtk.Widget | Null] = ExternalVar[Overlay, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  ()
  extension (v: Overlay) {
    def unwrap: org.gnome.gtk.Overlay = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    export unwrap.onGetChildPosition
  }
  def init(v: Overlay): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Overlay = {
    val res = new org.gnome.gtk.Overlay()
    res.asInstanceOf[Overlay]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam): VarContextAction[Overlay] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    res
  }
}