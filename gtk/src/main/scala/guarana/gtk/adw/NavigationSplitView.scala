
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type NavigationSplitView <: guarana.gtk.Widget  = org.gnome.adw.NavigationSplitView & guarana.gtk.Widget
object NavigationSplitView extends VarsMap {
  val Collapsed: ExternalVar.Aux[NavigationSplitView, Boolean] = ExternalVar[NavigationSplitView, Boolean]("collapsed", _.getCollapsed(), _.setCollapsed(_), true)
  val Content: ExternalVar.Aux[NavigationSplitView, org.gnome.adw.NavigationPage | Null] = ExternalVar[NavigationSplitView, org.gnome.adw.NavigationPage | Null]("content", _.getContent(), _.setContent(_), true)
  val MaxSidebarWidth: ExternalVar.Aux[NavigationSplitView, Double] = ExternalVar[NavigationSplitView, Double]("max-sidebar-width", _.getMaxSidebarWidth(), _.setMaxSidebarWidth(_), true)
  val MinSidebarWidth: ExternalVar.Aux[NavigationSplitView, Double] = ExternalVar[NavigationSplitView, Double]("min-sidebar-width", _.getMinSidebarWidth(), _.setMinSidebarWidth(_), true)
  val ShowContent: ExternalVar.Aux[NavigationSplitView, Boolean] = ExternalVar[NavigationSplitView, Boolean]("show-content", _.getShowContent(), _.setShowContent(_), true)
  val Sidebar: ExternalVar.Aux[NavigationSplitView, org.gnome.adw.NavigationPage | Null] = ExternalVar[NavigationSplitView, org.gnome.adw.NavigationPage | Null]("sidebar", _.getSidebar(), _.setSidebar(_), true)
  val SidebarPosition: ExternalVar.Aux[NavigationSplitView, org.gnome.gtk.PackType] = ExternalVar[NavigationSplitView, org.gnome.gtk.PackType]("sidebar-position", _.getSidebarPosition(), _.setSidebarPosition(_), true)
  val SidebarWidthFraction: ExternalVar.Aux[NavigationSplitView, Double] = ExternalVar[NavigationSplitView, Double]("sidebar-width-fraction", _.getSidebarWidthFraction(), _.setSidebarWidthFraction(_), true)
  val SidebarWidthUnit: ExternalVar.Aux[NavigationSplitView, org.gnome.adw.LengthUnit] = ExternalVar[NavigationSplitView, org.gnome.adw.LengthUnit]("sidebar-width-unit", _.getSidebarWidthUnit(), _.setSidebarWidthUnit(_), true)

  

  extension (v: NavigationSplitView) {
    def unwrap: org.gnome.adw.NavigationSplitView = v

    def collapsed: Var.Aux[Boolean, v.type] = guarana.gtk.adw.NavigationSplitView.Collapsed.asInstanceOf[Var.Aux[Boolean, v.type]]
    def content: Var.Aux[org.gnome.adw.NavigationPage | Null, v.type] = guarana.gtk.adw.NavigationSplitView.Content.asInstanceOf[Var.Aux[org.gnome.adw.NavigationPage | Null, v.type]]
    def maxSidebarWidth: Var.Aux[Double, v.type] = guarana.gtk.adw.NavigationSplitView.MaxSidebarWidth.asInstanceOf[Var.Aux[Double, v.type]]
    def minSidebarWidth: Var.Aux[Double, v.type] = guarana.gtk.adw.NavigationSplitView.MinSidebarWidth.asInstanceOf[Var.Aux[Double, v.type]]
    def showContent: Var.Aux[Boolean, v.type] = guarana.gtk.adw.NavigationSplitView.ShowContent.asInstanceOf[Var.Aux[Boolean, v.type]]
    def sidebar: Var.Aux[org.gnome.adw.NavigationPage | Null, v.type] = guarana.gtk.adw.NavigationSplitView.Sidebar.asInstanceOf[Var.Aux[org.gnome.adw.NavigationPage | Null, v.type]]
    def sidebarPosition: Var.Aux[org.gnome.gtk.PackType, v.type] = guarana.gtk.adw.NavigationSplitView.SidebarPosition.asInstanceOf[Var.Aux[org.gnome.gtk.PackType, v.type]]
    def sidebarWidthFraction: Var.Aux[Double, v.type] = guarana.gtk.adw.NavigationSplitView.SidebarWidthFraction.asInstanceOf[Var.Aux[Double, v.type]]
    def sidebarWidthUnit: Var.Aux[org.gnome.adw.LengthUnit, v.type] = guarana.gtk.adw.NavigationSplitView.SidebarWidthUnit.asInstanceOf[Var.Aux[org.gnome.adw.LengthUnit, v.type]]

    

    export unwrap.{
      onDestroy,
      onDirectionChanged,
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

  def wrap(v: org.gnome.adw.NavigationSplitView): NavigationSplitView = 
    val res = v.asInstanceOf[NavigationSplitView]
    
    res

  def init(v: NavigationSplitView): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): NavigationSplitView = {
    val res = {
      val res = org.gnome.adw.NavigationSplitView.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[NavigationSplitView]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    collapsed: Opt[Binding[Boolean]] = UnsetParam,
    content: Opt[Binding[org.gnome.adw.NavigationPage | Null]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
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
    maxSidebarWidth: Opt[Binding[Double]] = UnsetParam,
    minSidebarWidth: Opt[Binding[Double]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showContent: Opt[Binding[Boolean]] = UnsetParam,
    sidebar: Opt[Binding[org.gnome.adw.NavigationPage | Null]] = UnsetParam,
    sidebarPosition: Opt[Binding[org.gnome.gtk.PackType]] = UnsetParam,
    sidebarWidthFraction: Opt[Binding[Double]] = UnsetParam,
    sidebarWidthUnit: Opt[Binding[org.gnome.adw.LengthUnit]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[NavigationSplitView] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.NavigationSplitView.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(collapsed, res.collapsed := _)
    ifSet(content, res.content := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
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
    ifSet(maxSidebarWidth, res.maxSidebarWidth := _)
    ifSet(minSidebarWidth, res.minSidebarWidth := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showContent, res.showContent := _)
    ifSet(sidebar, res.sidebar := _)
    ifSet(sidebarPosition, res.sidebarPosition := _)
    ifSet(sidebarWidthFraction, res.sidebarWidthFraction := _)
    ifSet(sidebarWidthUnit, res.sidebarWidthUnit := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        