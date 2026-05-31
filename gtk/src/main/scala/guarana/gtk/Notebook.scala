
package guarana
package gtk

import guarana.util.*

opaque type Notebook <: guarana.gtk.Widget  = org.gnome.gtk.Notebook & guarana.gtk.Widget
object Notebook extends VarsMap {
  val CurrentPage: ExternalVar.Aux[Notebook, Int] = ExternalVar[Notebook, Int]("current-page", _.getCurrentPage(), _.setCurrentPage(_), true)
  val GroupName: ExternalVar.Aux[Notebook, java.lang.String | Null] = ExternalVar[Notebook, java.lang.String | Null]("group-name", _.getGroupName(), _.setGroupName(_), true)
  val Scrollable: ExternalVar.Aux[Notebook, Boolean] = ExternalVar[Notebook, Boolean]("scrollable", _.getScrollable(), _.setScrollable(_), true)
  val ShowBorder: ExternalVar.Aux[Notebook, Boolean] = ExternalVar[Notebook, Boolean]("show-border", _.getShowBorder(), _.setShowBorder(_), true)
  val ShowTabs: ExternalVar.Aux[Notebook, Boolean] = ExternalVar[Notebook, Boolean]("show-tabs", _.getShowTabs(), _.setShowTabs(_), true)
  val TabPos: ExternalVar.Aux[Notebook, org.gnome.gtk.PositionType] = ExternalVar[Notebook, org.gnome.gtk.PositionType]("tab-pos", _.getTabPos(), _.setTabPos(_), true)

  

  extension (v: Notebook) {
    def unwrap: org.gnome.gtk.Notebook = v

    def currentPage: Var.Aux[Int, v.type] = guarana.gtk.Notebook.CurrentPage.asInstanceOf[Var.Aux[Int, v.type]]
    def groupName: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.Notebook.GroupName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def scrollable: Var.Aux[Boolean, v.type] = guarana.gtk.Notebook.Scrollable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showBorder: Var.Aux[Boolean, v.type] = guarana.gtk.Notebook.ShowBorder.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showTabs: Var.Aux[Boolean, v.type] = guarana.gtk.Notebook.ShowTabs.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tabPos: Var.Aux[org.gnome.gtk.PositionType, v.type] = guarana.gtk.Notebook.TabPos.asInstanceOf[Var.Aux[org.gnome.gtk.PositionType, v.type]]

    

    export unwrap.{
      onChangeCurrentPage,
      onCreateWindow,
      onDestroy,
      onDirectionChanged,
      onFocusTab,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onMoveFocusOut,
      onNotify,
      onPageAdded,
      onPageRemoved,
      onPageReordered,
      onQueryTooltip,
      onRealize,
      onReorderTab,
      onSelectPage,
      onShow,
      onStateFlagsChanged,
      onSwitchPage,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.Notebook): Notebook = 
    val res = v.asInstanceOf[Notebook]
    
    res

  def init(v: Notebook): Toolkit ?=> Unit = (tk: Toolkit) ?=> {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(): Notebook = {
    val res = new org.gnome.gtk.Notebook()
    
    res.asInstanceOf[Notebook]
  }
  
  def apply(
    
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    currentPage: Opt[Binding[Int]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    groupName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    scrollable: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showBorder: Opt[Binding[Boolean]] = UnsetParam,
    showTabs: Opt[Binding[Boolean]] = UnsetParam,
    tabPos: Opt[Binding[org.gnome.gtk.PositionType]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Toolkit ?=> VarContextAction[Notebook] = {
    val res = uninitialized()
    guarana.gtk.Notebook.init(res)
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
        