package guarana
package gtk
opaque type Notebook <: Widget = org.gnome.gtk.Notebook & Widget
object Notebook {
  val Scrollable: ExternalVar.Aux[Notebook, Boolean] = ExternalVar[Notebook, Boolean]("scrollable", _.getScrollable(), _.setScrollable(_), true)
  val ShowBorder: ExternalVar.Aux[Notebook, Boolean] = ExternalVar[Notebook, Boolean]("show-border", _.getShowBorder(), _.setShowBorder(_), true)
  val ShowTabs: ExternalVar.Aux[Notebook, Boolean] = ExternalVar[Notebook, Boolean]("show-tabs", _.getShowTabs(), _.setShowTabs(_), true)
  val TabPos: ExternalVar.Aux[Notebook, org.gnome.gtk.PositionType | Null] = ExternalVar[Notebook, org.gnome.gtk.PositionType | Null]("tab-pos", _.getTabPos(), _.setTabPos(_), true)
  ()
  extension (v: Notebook) {
    def unwrap: org.gnome.gtk.Notebook = v
    export unwrap.onChangeCurrentPage, unwrap.onCreateWindow, unwrap.onFocusTab, unwrap.onMoveFocusOut, unwrap.onPageAdded, unwrap.onPageRemoved, unwrap.onPageReordered, unwrap.onReorderTab, unwrap.onSelectPage, unwrap.onSwitchPage
  }
  def init(v: Notebook): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Notebook = {
    val res = new org.gnome.gtk.Notebook()
    res.asInstanceOf[Notebook]
  }
}