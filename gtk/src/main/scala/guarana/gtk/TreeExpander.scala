package guarana
package gtk
opaque type TreeExpander <: Widget = org.gnome.gtk.TreeExpander & Widget
object TreeExpander {
  val HideExpander: ExternalVar.Aux[TreeExpander, Boolean] = ExternalVar[TreeExpander, Boolean]("hide-expander", _.getHideExpander(), _.setHideExpander(_), true)
  val IndentForDepth: ExternalVar.Aux[TreeExpander, Boolean] = ExternalVar[TreeExpander, Boolean]("indent-for-depth", _.getIndentForDepth(), _.setIndentForDepth(_), true)
  val IndentForIcon: ExternalVar.Aux[TreeExpander, Boolean] = ExternalVar[TreeExpander, Boolean]("indent-for-icon", _.getIndentForIcon(), _.setIndentForIcon(_), true)
  ()
  extension (v: TreeExpander) {
    def unwrap: org.gnome.gtk.TreeExpander = v
  }
  def init(v: TreeExpander): Unit = {
    Widget.init(v)
  }
  def uninitialized(): TreeExpander = {
    val res = new org.gnome.gtk.TreeExpander()
    res.asInstanceOf[TreeExpander]
  }
}