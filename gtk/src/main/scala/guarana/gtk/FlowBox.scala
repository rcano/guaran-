
package guarana
package gtk

import guarana.util.*

opaque type FlowBox <: guarana.gtk.Widget  = org.gnome.gtk.FlowBox & guarana.gtk.Widget
object FlowBox extends VarsMap {
  val ActivateOnSingleClick: ExternalVar.Aux[FlowBox, Boolean] = ExternalVar[FlowBox, Boolean]("activate-on-single-click", _.getActivateOnSingleClick(), _.setActivateOnSingleClick(_), true)
  val ColumnSpacing: ExternalVar.Aux[FlowBox, Int] = ExternalVar[FlowBox, Int]("column-spacing", _.getColumnSpacing(), _.setColumnSpacing(_), true)
  val Homogeneous: ExternalVar.Aux[FlowBox, Boolean] = ExternalVar[FlowBox, Boolean]("homogeneous", _.getHomogeneous(), _.setHomogeneous(_), true)
  val MaxChildrenPerLine: ExternalVar.Aux[FlowBox, Int] = ExternalVar[FlowBox, Int]("max-children-per-line", _.getMaxChildrenPerLine(), _.setMaxChildrenPerLine(_), true)
  val MinChildrenPerLine: ExternalVar.Aux[FlowBox, Int] = ExternalVar[FlowBox, Int]("min-children-per-line", _.getMinChildrenPerLine(), _.setMinChildrenPerLine(_), true)
  val Nodes: Var[Seq[Widget]] = Var[Seq[Widget]]("nodes", Seq.empty, false)
  val Orientation: ExternalVar.Aux[FlowBox, org.gnome.gtk.Orientation] = ExternalVar[FlowBox, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val RowSpacing: ExternalVar.Aux[FlowBox, Int] = ExternalVar[FlowBox, Int]("row-spacing", _.getRowSpacing(), _.setRowSpacing(_), true)
  val SelectionMode: ExternalVar.Aux[FlowBox, org.gnome.gtk.SelectionMode] = ExternalVar[FlowBox, org.gnome.gtk.SelectionMode]("selection-mode", _.getSelectionMode(), _.setSelectionMode(_), true)

  

  extension (v: FlowBox) {
    def unwrap: org.gnome.gtk.FlowBox = v

    def activateOnSingleClick: Var.Aux[Boolean, v.type] = guarana.gtk.FlowBox.ActivateOnSingleClick.asInstanceOf[Var.Aux[Boolean, v.type]]
    def columnSpacing: Var.Aux[Int, v.type] = guarana.gtk.FlowBox.ColumnSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    def homogeneous: Var.Aux[Boolean, v.type] = guarana.gtk.FlowBox.Homogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxChildrenPerLine: Var.Aux[Int, v.type] = guarana.gtk.FlowBox.MaxChildrenPerLine.asInstanceOf[Var.Aux[Int, v.type]]
    def minChildrenPerLine: Var.Aux[Int, v.type] = guarana.gtk.FlowBox.MinChildrenPerLine.asInstanceOf[Var.Aux[Int, v.type]]
    def nodes: Var.Aux[Seq[Widget], v.type] = guarana.gtk.FlowBox.Nodes.asInstanceOf[Var.Aux[Seq[Widget], v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.FlowBox.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def rowSpacing: Var.Aux[Int, v.type] = guarana.gtk.FlowBox.RowSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    def selectionMode: Var.Aux[org.gnome.gtk.SelectionMode, v.type] = guarana.gtk.FlowBox.SelectionMode.asInstanceOf[Var.Aux[org.gnome.gtk.SelectionMode, v.type]]

    

    export unwrap.{
      onActivateCursorChild,
      onChildActivated,
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
      onSelectAll,
      onSelectedChildrenChanged,
      onShow,
      onStateFlagsChanged,
      onToggleCursorChild,
      onUnmap,
      onUnrealize,
      onUnselectAll
    }
  }

  def wrap(v: org.gnome.gtk.FlowBox): FlowBox = 
    val res = v.asInstanceOf[FlowBox]
    
    res

  def init(v: FlowBox): Unit = {
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
  def uninitialized(): FlowBox = {
    val res = new org.gnome.gtk.FlowBox()
    
    res.asInstanceOf[FlowBox]
  }
  
  def apply(
    
    activateOnSingleClick: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    columnSpacing: Opt[Binding[Int]] = UnsetParam,
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
    homogeneous: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    maxChildrenPerLine: Opt[Binding[Int]] = UnsetParam,
    minChildrenPerLine: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    nodes: Opt[Binding[Seq[Widget]]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    rowSpacing: Opt[Binding[Int]] = UnsetParam,
    selectionMode: Opt[Binding[org.gnome.gtk.SelectionMode]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[FlowBox] = {
    val res = uninitialized()
    guarana.gtk.FlowBox.init(res)
    ifSet(activateOnSingleClick, res.activateOnSingleClick := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(columnSpacing, res.columnSpacing := _)
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
    ifSet(homogeneous, res.homogeneous := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(maxChildrenPerLine, res.maxChildrenPerLine := _)
    ifSet(minChildrenPerLine, res.minChildrenPerLine := _)
    ifSet(name, res.name := _)
    ifSet(nodes, res.nodes := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(rowSpacing, res.rowSpacing := _)
    ifSet(selectionMode, res.selectionMode := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
  
}
        