package guarana
package gtk
import util.*
opaque type ListBoxRow <: Widget = org.gnome.gtk.ListBoxRow & Widget
object ListBoxRow {
  val ActionName: ExternalVar.Aux[ListBoxRow, java.lang.String | Null] = ExternalVar[ListBoxRow, java.lang.String | Null]("action-name", _.getActionName(), _.setActionName(_), true)
  val ActionTargetValue: ExternalVar.Aux[ListBoxRow, org.gnome.glib.Variant | Null] = ExternalVar[ListBoxRow, org.gnome.glib.Variant | Null]("action-target-value", _.getActionTargetValue(), _.setActionTargetValue(_), true)
  val Activatable: ExternalVar.Aux[ListBoxRow, Boolean] = ExternalVar[ListBoxRow, Boolean]("activatable", _.getActivatable(), _.setActivatable(_), true)
  val Child: ExternalVar.Aux[ListBoxRow, org.gnome.gtk.Widget | Null] = ExternalVar[ListBoxRow, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Header: ExternalVar.Aux[ListBoxRow, org.gnome.gtk.Widget | Null] = ExternalVar[ListBoxRow, org.gnome.gtk.Widget | Null]("header", _.getHeader(), _.setHeader(_), true)
  val Selectable: ExternalVar.Aux[ListBoxRow, Boolean] = ExternalVar[ListBoxRow, Boolean]("selectable", _.getSelectable(), _.setSelectable(_), true)
  ()
  extension (v: ListBoxRow) {
    def unwrap: org.gnome.gtk.ListBoxRow = v
    def actionName: Var.Aux[java.lang.String | Null, v.type] = ActionName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def actionTargetValue: Var.Aux[org.gnome.glib.Variant | Null, v.type] = ActionTargetValue.asInstanceOf[Var.Aux[org.gnome.glib.Variant | Null, v.type]]
    def activatable: Var.Aux[Boolean, v.type] = Activatable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def header: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Header.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def selectable: Var.Aux[Boolean, v.type] = Selectable.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate
  }
  def init(v: ListBoxRow): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ListBoxRow = {
    val res = new org.gnome.gtk.ListBoxRow()
    res.asInstanceOf[ListBoxRow]
  }
  def apply(actionName: Opt[java.lang.String | Null] = UnsetParam, actionTargetValue: Opt[org.gnome.glib.Variant | Null] = UnsetParam, activatable: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, header: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, selectable: Opt[Boolean] = UnsetParam): VarContextAction[ListBoxRow] = {
    val res = uninitialized()
    init(res)
    ifSet(actionName, res.actionName := _)
    ifSet(actionTargetValue, res.actionTargetValue := _)
    ifSet(activatable, res.activatable := _)
    ifSet(child, res.child := _)
    ifSet(header, res.header := _)
    ifSet(selectable, res.selectable := _)
    res
  }
}