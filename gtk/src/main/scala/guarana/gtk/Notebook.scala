package guarana
package gtk
import util.*
opaque type Notebook <: Widget = org.gnome.gtk.Notebook & Widget
object Notebook extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.Notebook): Notebook = {
    v.asInstanceOf
  }
  def init(v: Notebook): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): Notebook = {
    val res = new org.gnome.gtk.Notebook()
    res.asInstanceOf[Notebook]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, currentPage: Opt[Int] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, groupName: Opt[java.lang.String | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, scrollable: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showBorder: Opt[Boolean] = UnsetParam, showTabs: Opt[Boolean] = UnsetParam, tabPos: Opt[org.gnome.gtk.PositionType] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, Notebook] = {
    val res = uninitialized()
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(currentPage, res.currentPage := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(groupName, res.groupName := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(scrollable, res.scrollable := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showBorder, res.showBorder := _)
    ifSet(showTabs, res.showTabs := _)
    ifSet(tabPos, res.tabPos := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}