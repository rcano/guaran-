package guarana
package gtk
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
  }
}