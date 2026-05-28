package guarana
package gtk
import util.*
opaque type ActionBar <: Widget = org.gnome.gtk.ActionBar & Widget
object ActionBar {
  val CenterWidget: ExternalVar.Aux[ActionBar, org.gnome.gtk.Widget | Null] = ExternalVar[ActionBar, org.gnome.gtk.Widget | Null]("center-widget", _.getCenterWidget(), _.setCenterWidget(_), true)
  val Revealed: ExternalVar.Aux[ActionBar, Boolean] = ExternalVar[ActionBar, Boolean]("revealed", _.getRevealed(), _.setRevealed(_), true)
  ()
  extension (v: ActionBar) {
    def unwrap: org.gnome.gtk.ActionBar = v
    def centerWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = CenterWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def revealed: Var.Aux[Boolean, v.type] = Revealed.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def init(v: ActionBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ActionBar = {
    val res = new org.gnome.gtk.ActionBar()
    res.asInstanceOf[ActionBar]
  }
  def apply(centerWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, revealed: Opt[Boolean] = UnsetParam): VarContextAction[ActionBar] = {
    val res = uninitialized()
    init(res)
    ifSet(centerWidget, res.centerWidget := _)
    ifSet(revealed, res.revealed := _)
    res
  }
}