package guarana
package gtk
import util.*
opaque type Notebook <: Widget = org.gnome.gtk.Notebook & Widget
object Notebook {
  val CurrentPage: ExternalVar.Aux[Notebook, Int] = ExternalVar[Notebook, Int]("current-page", _.getCurrentPage(), _.setCurrentPage(_), true)
  val GroupName: ExternalVar.Aux[Notebook, java.lang.String | Null] = ExternalVar[Notebook, java.lang.String | Null]("group-name", _.getGroupName(), _.setGroupName(_), true)
  val Scrollable: ExternalVar.Aux[Notebook, Boolean] = ExternalVar[Notebook, Boolean]("scrollable", _.getScrollable(), _.setScrollable(_), true)
  val ShowBorder: ExternalVar.Aux[Notebook, Boolean] = ExternalVar[Notebook, Boolean]("show-border", _.getShowBorder(), _.setShowBorder(_), true)
  val ShowTabs: ExternalVar.Aux[Notebook, Boolean] = ExternalVar[Notebook, Boolean]("show-tabs", _.getShowTabs(), _.setShowTabs(_), true)
  val TabPos: ExternalVar.Aux[Notebook, org.gnome.gtk.PositionType] = ExternalVar[Notebook, org.gnome.gtk.PositionType]("tab-pos", _.getTabPos(), _.setTabPos(_), true)
  ()
  extension (v: Notebook) {
    def unwrap: org.gnome.gtk.Notebook = v
    def currentPage: Var.Aux[Int, v.type] = CurrentPage.asInstanceOf[Var.Aux[Int, v.type]]
    def groupName: Var.Aux[java.lang.String | Null, v.type] = GroupName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def scrollable: Var.Aux[Boolean, v.type] = Scrollable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showBorder: Var.Aux[Boolean, v.type] = ShowBorder.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showTabs: Var.Aux[Boolean, v.type] = ShowTabs.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tabPos: Var.Aux[org.gnome.gtk.PositionType, v.type] = TabPos.asInstanceOf[Var.Aux[org.gnome.gtk.PositionType, v.type]]
    export unwrap.onChangeCurrentPage, unwrap.onCreateWindow, unwrap.onFocusTab, unwrap.onMoveFocusOut, unwrap.onPageAdded, unwrap.onPageRemoved, unwrap.onPageReordered, unwrap.onReorderTab, unwrap.onSelectPage, unwrap.onSwitchPage
  }
  def init(v: Notebook): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Notebook = {
    val res = new org.gnome.gtk.Notebook()
    res.asInstanceOf[Notebook]
  }
  def apply(currentPage: Opt[Int] = UnsetParam, groupName: Opt[java.lang.String | Null] = UnsetParam, scrollable: Opt[Boolean] = UnsetParam, showBorder: Opt[Boolean] = UnsetParam, showTabs: Opt[Boolean] = UnsetParam, tabPos: Opt[org.gnome.gtk.PositionType] = UnsetParam): VarContextAction[Notebook] = {
    val res = uninitialized()
    init(res)
    ifSet(currentPage, res.currentPage := _)
    ifSet(groupName, res.groupName := _)
    ifSet(scrollable, res.scrollable := _)
    ifSet(showBorder, res.showBorder := _)
    ifSet(showTabs, res.showTabs := _)
    ifSet(tabPos, res.tabPos := _)
    res
  }
}