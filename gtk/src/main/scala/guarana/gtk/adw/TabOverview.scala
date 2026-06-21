
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type TabOverview <: guarana.gtk.Widget  = org.gnome.adw.TabOverview & guarana.gtk.Widget
object TabOverview extends VarsMap {
  val Child: ExternalVar.Aux[TabOverview, guarana.gtk.Widget | Null] = ExternalVar[TabOverview, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val EnableNewTab: ExternalVar.Aux[TabOverview, Boolean] = ExternalVar[TabOverview, Boolean]("enable-new-tab", _.getEnableNewTab(), _.setEnableNewTab(_), true)
  val EnableSearch: ExternalVar.Aux[TabOverview, Boolean] = ExternalVar[TabOverview, Boolean]("enable-search", _.getEnableSearch(), _.setEnableSearch(_), true)
  val ExtraDragPreload: ExternalVar.Aux[TabOverview, Boolean] = ExternalVar[TabOverview, Boolean]("extra-drag-preload", _.getExtraDragPreload(), _.setExtraDragPreload(_), true)
  val Inverted: ExternalVar.Aux[TabOverview, Boolean] = ExternalVar[TabOverview, Boolean]("inverted", _.getInverted(), _.setInverted(_), true)
  val Open: ExternalVar.Aux[TabOverview, Boolean] = ExternalVar[TabOverview, Boolean]("open", _.getOpen(), _.setOpen(_), true)
  val SecondaryMenu: ExternalVar.Aux[TabOverview, org.gnome.gio.MenuModel | Null] = ExternalVar[TabOverview, org.gnome.gio.MenuModel | Null]("secondary-menu", _.getSecondaryMenu(), _.setSecondaryMenu(_), true)
  val ShowEndTitleButtons: ExternalVar.Aux[TabOverview, Boolean] = ExternalVar[TabOverview, Boolean]("show-end-title-buttons", _.getShowEndTitleButtons(), _.setShowEndTitleButtons(_), true)
  val ShowStartTitleButtons: ExternalVar.Aux[TabOverview, Boolean] = ExternalVar[TabOverview, Boolean]("show-start-title-buttons", _.getShowStartTitleButtons(), _.setShowStartTitleButtons(_), true)
  val View: ExternalVar.Aux[TabOverview, org.gnome.adw.TabView | Null] = ExternalVar[TabOverview, org.gnome.adw.TabView | Null]("view", _.getView(), _.setView(_), true)

  

  extension (v: TabOverview) {
    def unwrap: org.gnome.adw.TabOverview = v

    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.TabOverview.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def enableNewTab: Var.Aux[Boolean, v.type] = guarana.gtk.adw.TabOverview.EnableNewTab.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableSearch: Var.Aux[Boolean, v.type] = guarana.gtk.adw.TabOverview.EnableSearch.asInstanceOf[Var.Aux[Boolean, v.type]]
    def extraDragPreload: Var.Aux[Boolean, v.type] = guarana.gtk.adw.TabOverview.ExtraDragPreload.asInstanceOf[Var.Aux[Boolean, v.type]]
    def inverted: Var.Aux[Boolean, v.type] = guarana.gtk.adw.TabOverview.Inverted.asInstanceOf[Var.Aux[Boolean, v.type]]
    def open: Var.Aux[Boolean, v.type] = guarana.gtk.adw.TabOverview.Open.asInstanceOf[Var.Aux[Boolean, v.type]]
    def secondaryMenu: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = guarana.gtk.adw.TabOverview.SecondaryMenu.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def showEndTitleButtons: Var.Aux[Boolean, v.type] = guarana.gtk.adw.TabOverview.ShowEndTitleButtons.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showStartTitleButtons: Var.Aux[Boolean, v.type] = guarana.gtk.adw.TabOverview.ShowStartTitleButtons.asInstanceOf[Var.Aux[Boolean, v.type]]
    def view: Var.Aux[org.gnome.adw.TabView | Null, v.type] = guarana.gtk.adw.TabOverview.View.asInstanceOf[Var.Aux[org.gnome.adw.TabView | Null, v.type]]

    

    export unwrap.{
      onCreateTab,
      onDestroy,
      onDirectionChanged,
      onExtraDragDrop,
      onExtraDragValue,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.adw.TabOverview): TabOverview = 
    val res = v.asInstanceOf[TabOverview]
    
    res

  def init(v: TabOverview): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): TabOverview = {
    val res = {
      val res = org.gnome.adw.TabOverview.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[TabOverview]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    enableNewTab: Opt[Binding[Boolean]] = UnsetParam,
    enableSearch: Opt[Binding[Boolean]] = UnsetParam,
    extraDragPreload: Opt[Binding[Boolean]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    inverted: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    open: Opt[Binding[Boolean]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    secondaryMenu: Opt[Binding[org.gnome.gio.MenuModel | Null]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showEndTitleButtons: Opt[Binding[Boolean]] = UnsetParam,
    showStartTitleButtons: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    view: Opt[Binding[org.gnome.adw.TabView | Null]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[TabOverview] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.TabOverview.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(enableNewTab, res.enableNewTab := _)
    ifSet(enableSearch, res.enableSearch := _)
    ifSet(extraDragPreload, res.extraDragPreload := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(inverted, res.inverted := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(open, res.open := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(secondaryMenu, res.secondaryMenu := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showEndTitleButtons, res.showEndTitleButtons := _)
    ifSet(showStartTitleButtons, res.showStartTitleButtons := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(view, res.view := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        