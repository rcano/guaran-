
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type OverlaySplitView <: guarana.gtk.Widget  = org.gnome.adw.OverlaySplitView & guarana.gtk.Widget
object OverlaySplitView extends VarsMap {
  val Collapsed: ExternalVar.Aux[OverlaySplitView, Boolean] = ExternalVar[OverlaySplitView, Boolean]("collapsed", _.getCollapsed(), _.setCollapsed(_), true)
  val Content: ExternalVar.Aux[OverlaySplitView, guarana.gtk.Widget | Null] = ExternalVar[OverlaySplitView, guarana.gtk.Widget | Null]("content", _.getContent().?(guarana.gtk.Widget.wrap), (n, v) => n.setContent(v.?(_.unwrap)), true)
  val EnableHideGesture: ExternalVar.Aux[OverlaySplitView, Boolean] = ExternalVar[OverlaySplitView, Boolean]("enable-hide-gesture", _.getEnableHideGesture(), _.setEnableHideGesture(_), true)
  val EnableShowGesture: ExternalVar.Aux[OverlaySplitView, Boolean] = ExternalVar[OverlaySplitView, Boolean]("enable-show-gesture", _.getEnableShowGesture(), _.setEnableShowGesture(_), true)
  val MaxSidebarWidth: ExternalVar.Aux[OverlaySplitView, Double] = ExternalVar[OverlaySplitView, Double]("max-sidebar-width", _.getMaxSidebarWidth(), _.setMaxSidebarWidth(_), true)
  val MinSidebarWidth: ExternalVar.Aux[OverlaySplitView, Double] = ExternalVar[OverlaySplitView, Double]("min-sidebar-width", _.getMinSidebarWidth(), _.setMinSidebarWidth(_), true)
  val PinSidebar: ExternalVar.Aux[OverlaySplitView, Boolean] = ExternalVar[OverlaySplitView, Boolean]("pin-sidebar", _.getPinSidebar(), _.setPinSidebar(_), true)
  val ShowSidebar: ExternalVar.Aux[OverlaySplitView, Boolean] = ExternalVar[OverlaySplitView, Boolean]("show-sidebar", _.getShowSidebar(), _.setShowSidebar(_), true)
  val Sidebar: ExternalVar.Aux[OverlaySplitView, guarana.gtk.Widget | Null] = ExternalVar[OverlaySplitView, guarana.gtk.Widget | Null]("sidebar", _.getSidebar().?(guarana.gtk.Widget.wrap), (n, v) => n.setSidebar(v.?(_.unwrap)), true)
  val SidebarPosition: ExternalVar.Aux[OverlaySplitView, org.gnome.gtk.PackType] = ExternalVar[OverlaySplitView, org.gnome.gtk.PackType]("sidebar-position", _.getSidebarPosition(), _.setSidebarPosition(_), true)
  val SidebarWidthFraction: ExternalVar.Aux[OverlaySplitView, Double] = ExternalVar[OverlaySplitView, Double]("sidebar-width-fraction", _.getSidebarWidthFraction(), _.setSidebarWidthFraction(_), true)
  val SidebarWidthUnit: ExternalVar.Aux[OverlaySplitView, org.gnome.adw.LengthUnit] = ExternalVar[OverlaySplitView, org.gnome.adw.LengthUnit]("sidebar-width-unit", _.getSidebarWidthUnit(), _.setSidebarWidthUnit(_), true)

  

  extension (v: OverlaySplitView) {
    def unwrap: org.gnome.adw.OverlaySplitView = v

    def collapsed: Var.Aux[Boolean, v.type] = guarana.gtk.adw.OverlaySplitView.Collapsed.asInstanceOf[Var.Aux[Boolean, v.type]]
    def content: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.OverlaySplitView.Content.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def enableHideGesture: Var.Aux[Boolean, v.type] = guarana.gtk.adw.OverlaySplitView.EnableHideGesture.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableShowGesture: Var.Aux[Boolean, v.type] = guarana.gtk.adw.OverlaySplitView.EnableShowGesture.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxSidebarWidth: Var.Aux[Double, v.type] = guarana.gtk.adw.OverlaySplitView.MaxSidebarWidth.asInstanceOf[Var.Aux[Double, v.type]]
    def minSidebarWidth: Var.Aux[Double, v.type] = guarana.gtk.adw.OverlaySplitView.MinSidebarWidth.asInstanceOf[Var.Aux[Double, v.type]]
    def pinSidebar: Var.Aux[Boolean, v.type] = guarana.gtk.adw.OverlaySplitView.PinSidebar.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showSidebar: Var.Aux[Boolean, v.type] = guarana.gtk.adw.OverlaySplitView.ShowSidebar.asInstanceOf[Var.Aux[Boolean, v.type]]
    def sidebar: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.OverlaySplitView.Sidebar.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def sidebarPosition: Var.Aux[org.gnome.gtk.PackType, v.type] = guarana.gtk.adw.OverlaySplitView.SidebarPosition.asInstanceOf[Var.Aux[org.gnome.gtk.PackType, v.type]]
    def sidebarWidthFraction: Var.Aux[Double, v.type] = guarana.gtk.adw.OverlaySplitView.SidebarWidthFraction.asInstanceOf[Var.Aux[Double, v.type]]
    def sidebarWidthUnit: Var.Aux[org.gnome.adw.LengthUnit, v.type] = guarana.gtk.adw.OverlaySplitView.SidebarWidthUnit.asInstanceOf[Var.Aux[org.gnome.adw.LengthUnit, v.type]]

    

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

  def wrap(v: org.gnome.adw.OverlaySplitView): OverlaySplitView = 
    val res = v.asInstanceOf[OverlaySplitView]
    
    res

  def init(v: OverlaySplitView): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): OverlaySplitView = {
    val res = {
      val res = org.gnome.adw.OverlaySplitView.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[OverlaySplitView]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    collapsed: Opt[Binding[Boolean]] = UnsetParam,
    content: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    enableHideGesture: Opt[Binding[Boolean]] = UnsetParam,
    enableShowGesture: Opt[Binding[Boolean]] = UnsetParam,
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
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    pinSidebar: Opt[Binding[Boolean]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showSidebar: Opt[Binding[Boolean]] = UnsetParam,
    sidebar: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    sidebarPosition: Opt[Binding[org.gnome.gtk.PackType]] = UnsetParam,
    sidebarWidthFraction: Opt[Binding[Double]] = UnsetParam,
    sidebarWidthUnit: Opt[Binding[org.gnome.adw.LengthUnit]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[OverlaySplitView] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.OverlaySplitView.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(collapsed, res.collapsed := _)
    ifSet(content, res.content := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(enableHideGesture, res.enableHideGesture := _)
    ifSet(enableShowGesture, res.enableShowGesture := _)
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
    ifSet(parent, res.parent := _)
    ifSet(pinSidebar, res.pinSidebar := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showSidebar, res.showSidebar := _)
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
        