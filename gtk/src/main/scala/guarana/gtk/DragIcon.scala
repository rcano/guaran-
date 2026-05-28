package guarana
package gtk
import util.*
opaque type DragIcon <: Widget = org.gnome.gtk.DragIcon & Widget
object DragIcon {
  val Child: ExternalVar.Aux[DragIcon, org.gnome.gtk.Widget | Null] = ExternalVar[DragIcon, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Focus: ExternalVar.Aux[DragIcon, org.gnome.gtk.Widget | Null] = ExternalVar[DragIcon, org.gnome.gtk.Widget | Null]("focus", _.getFocus(), _.setFocus(_), true)
  ()
  extension (v: DragIcon) {
    def unwrap: org.gnome.gtk.DragIcon = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def focus: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Focus.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
  }
  def init(v: DragIcon): Unit = {
    Widget.init(v)
  }
  def uninitialized(): DragIcon = {
    val res = new org.gnome.gtk.DragIcon()
    res.asInstanceOf[DragIcon]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focus: Opt[org.gnome.gtk.Widget | Null] = UnsetParam): VarContextAction[DragIcon] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    ifSet(focus, res.focus := _)
    res
  }
}