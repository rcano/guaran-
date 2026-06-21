
package guarana
package gtk

import guarana.util.*

opaque type ListBox <: guarana.gtk.Widget  = org.gnome.gtk.ListBox & guarana.gtk.Widget
object ListBox extends VarsMap {
  val ActivateOnSingleClick: ExternalVar.Aux[ListBox, Boolean] = ExternalVar[ListBox, Boolean]("activate-on-single-click", _.getActivateOnSingleClick(), _.setActivateOnSingleClick(_), true)
  val Adjustment: ExternalVar.Aux[ListBox, org.gnome.gtk.Adjustment | Null] = ExternalVar[ListBox, org.gnome.gtk.Adjustment | Null]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  val Nodes: Var[Seq[Widget]] = Var[Seq[Widget]]("nodes", Seq.empty, true)
  val SelectionMode: ExternalVar.Aux[ListBox, org.gnome.gtk.SelectionMode] = ExternalVar[ListBox, org.gnome.gtk.SelectionMode]("selection-mode", _.getSelectionMode(), _.setSelectionMode(_), true)
  val ShowSeparators: ExternalVar.Aux[ListBox, Boolean] = ExternalVar[ListBox, Boolean]("show-separators", _.getShowSeparators(), _.setShowSeparators(_), true)
  val TabBehavior: ExternalVar.Aux[ListBox, org.gnome.gtk.ListTabBehavior] = ExternalVar[ListBox, org.gnome.gtk.ListTabBehavior]("tab-behavior", _.getTabBehavior(), _.setTabBehavior(_), true)

  

  extension (v: ListBox) {
    def unwrap: org.gnome.gtk.ListBox = v

    def activateOnSingleClick: Var.Aux[Boolean, v.type] = guarana.gtk.ListBox.ActivateOnSingleClick.asInstanceOf[Var.Aux[Boolean, v.type]]
    def adjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = guarana.gtk.ListBox.Adjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def nodes: Var.Aux[Seq[Widget], v.type] = guarana.gtk.ListBox.Nodes.asInstanceOf[Var.Aux[Seq[Widget], v.type]]
    def selectionMode: Var.Aux[org.gnome.gtk.SelectionMode, v.type] = guarana.gtk.ListBox.SelectionMode.asInstanceOf[Var.Aux[org.gnome.gtk.SelectionMode, v.type]]
    def showSeparators: Var.Aux[Boolean, v.type] = guarana.gtk.ListBox.ShowSeparators.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tabBehavior: Var.Aux[org.gnome.gtk.ListTabBehavior, v.type] = guarana.gtk.ListBox.TabBehavior.asInstanceOf[Var.Aux[org.gnome.gtk.ListTabBehavior, v.type]]

    

    export unwrap.{
      onActivateCursorRow,
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveCursor,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onRowActivated,
      onRowSelected,
      onSelectAll,
      onSelectedRowsChanged,
      onShow,
      onStateFlagsChanged,
      onToggleCursorRow,
      onUnmap,
      onUnrealize,
      onUnselectAll
    }
  }

  def wrap(v: org.gnome.gtk.ListBox): ListBox = 
    val res = v.asInstanceOf[ListBox]
    
    res

  def init(v: ListBox): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    Toolkit.update {
      v.varUpdates := EventIterator.forsome {
        case v.nodes(_, newv) =>
          while (v.getFirstChild() != null) v.remove(v.getFirstChild())
          newv.foreach(w => v.append(w.unwrap))
      }
    }
    
  }
  def uninitialized(acceptUnpairedRelease: Opt[Boolean], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): ListBox = {
    val res = {
      val res = org.gnome.gtk.ListBox.builder()
      ifSet(acceptUnpairedRelease, v => res.setAcceptUnpairedRelease(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[ListBox]
  }
  
  def apply(
    acceptUnpairedRelease: Opt[Boolean] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    activateOnSingleClick: Opt[Binding[Boolean]] = UnsetParam,
    adjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
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
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    nodes: Opt[Binding[Seq[Widget]]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    selectionMode: Opt[Binding[org.gnome.gtk.SelectionMode]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showSeparators: Opt[Binding[Boolean]] = UnsetParam,
    tabBehavior: Opt[Binding[org.gnome.gtk.ListTabBehavior]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[ListBox] = {
    val res = uninitialized(acceptUnpairedRelease, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.ListBox.init(res)
    ifSet(activateOnSingleClick, res.activateOnSingleClick := _)
    ifSet(adjustment, res.adjustment := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
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
    ifSet(name, res.name := _)
    ifSet(nodes, res.nodes := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(selectionMode, res.selectionMode := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showSeparators, res.showSeparators := _)
    ifSet(tabBehavior, res.tabBehavior := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        