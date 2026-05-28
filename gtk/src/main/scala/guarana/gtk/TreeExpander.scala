package guarana
package gtk
import util.*
opaque type TreeExpander <: Widget = org.gnome.gtk.TreeExpander & Widget
object TreeExpander {
  val Child: ExternalVar.Aux[TreeExpander, org.gnome.gtk.Widget | Null] = ExternalVar[TreeExpander, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val HideExpander: ExternalVar.Aux[TreeExpander, Boolean] = ExternalVar[TreeExpander, Boolean]("hide-expander", _.getHideExpander(), _.setHideExpander(_), true)
  val IndentForDepth: ExternalVar.Aux[TreeExpander, Boolean] = ExternalVar[TreeExpander, Boolean]("indent-for-depth", _.getIndentForDepth(), _.setIndentForDepth(_), true)
  val IndentForIcon: ExternalVar.Aux[TreeExpander, Boolean] = ExternalVar[TreeExpander, Boolean]("indent-for-icon", _.getIndentForIcon(), _.setIndentForIcon(_), true)
  val ListRow: ExternalVar.Aux[TreeExpander, org.gnome.gtk.TreeListRow | Null] = ExternalVar[TreeExpander, org.gnome.gtk.TreeListRow | Null]("list-row", _.getListRow(), _.setListRow(_), true)
  ()
  extension (v: TreeExpander) {
    def unwrap: org.gnome.gtk.TreeExpander = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def hideExpander: Var.Aux[Boolean, v.type] = HideExpander.asInstanceOf[Var.Aux[Boolean, v.type]]
    def indentForDepth: Var.Aux[Boolean, v.type] = IndentForDepth.asInstanceOf[Var.Aux[Boolean, v.type]]
    def indentForIcon: Var.Aux[Boolean, v.type] = IndentForIcon.asInstanceOf[Var.Aux[Boolean, v.type]]
    def listRow: Var.Aux[org.gnome.gtk.TreeListRow | Null, v.type] = ListRow.asInstanceOf[Var.Aux[org.gnome.gtk.TreeListRow | Null, v.type]]
  }
  def init(v: TreeExpander): Unit = {
    Widget.init(v)
  }
  def uninitialized(): TreeExpander = {
    val res = new org.gnome.gtk.TreeExpander()
    res.asInstanceOf[TreeExpander]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, hideExpander: Opt[Boolean] = UnsetParam, indentForDepth: Opt[Boolean] = UnsetParam, indentForIcon: Opt[Boolean] = UnsetParam, listRow: Opt[org.gnome.gtk.TreeListRow | Null] = UnsetParam): VarContextAction[TreeExpander] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    ifSet(hideExpander, res.hideExpander := _)
    ifSet(indentForDepth, res.indentForDepth := _)
    ifSet(indentForIcon, res.indentForIcon := _)
    ifSet(listRow, res.listRow := _)
    res
  }
}