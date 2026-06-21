
package guarana.gtk

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type Grid <: guarana.gtk.Widget  = org.gnome.gtk.Grid & guarana.gtk.Widget
object Grid extends VarsMap, internal.GridLayoutSupport {
  val BaselineRow: ExternalVar.Aux[Grid, Int] = ExternalVar[Grid, Int]("baseline-row", _.getBaselineRow(), _.setBaselineRow(_), true)
  val ColumnHomogeneous: ExternalVar.Aux[Grid, Boolean] = ExternalVar[Grid, Boolean]("column-homogeneous", _.getColumnHomogeneous(), _.setColumnHomogeneous(_), true)
  val ColumnSpacing: ExternalVar.Aux[Grid, Int] = ExternalVar[Grid, Int]("column-spacing", _.getColumnSpacing(), _.setColumnSpacing(_), true)
  val Orientation: ExternalVar.Aux[Grid, org.gnome.gtk.Orientation] = ExternalVar[Grid, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val RowHomogeneous: ExternalVar.Aux[Grid, Boolean] = ExternalVar[Grid, Boolean]("row-homogeneous", _.getRowHomogeneous(), _.setRowHomogeneous(_), true)
  val RowSpacing: ExternalVar.Aux[Grid, Int] = ExternalVar[Grid, Int]("row-spacing", _.getRowSpacing(), _.setRowSpacing(_), true)

  

  extension (v: Grid) {
    def unwrap: org.gnome.gtk.Grid = v

    def baselineRow: Var.Aux[Int, v.type] = guarana.gtk.Grid.BaselineRow.asInstanceOf[Var.Aux[Int, v.type]]
    def columnHomogeneous: Var.Aux[Boolean, v.type] = guarana.gtk.Grid.ColumnHomogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def columnSpacing: Var.Aux[Int, v.type] = guarana.gtk.Grid.ColumnSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.Grid.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def rowHomogeneous: Var.Aux[Boolean, v.type] = guarana.gtk.Grid.RowHomogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def rowSpacing: Var.Aux[Int, v.type] = guarana.gtk.Grid.RowSpacing.asInstanceOf[Var.Aux[Int, v.type]]

    

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

  def wrap(v: org.gnome.gtk.Grid): Grid = 
    val res = v.asInstanceOf[Grid]
    
    res

  def init(v: Grid): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    Toolkit.update(initNodesVar(v))
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Grid = {
    val res = {
      val res = org.gnome.gtk.Grid.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[Grid]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    baselineRow: Opt[Binding[Int]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    columnHomogeneous: Opt[Binding[Boolean]] = UnsetParam,
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
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    nodes: Opt[Binding[Seq[GridEntry]]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    rowHomogeneous: Opt[Binding[Boolean]] = UnsetParam,
    rowSpacing: Opt[Binding[Int]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Grid] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.Grid.init(res)
    ifSet(baselineRow, res.baselineRow := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(columnHomogeneous, res.columnHomogeneous := _)
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
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(rowHomogeneous, res.rowHomogeneous := _)
    ifSet(rowSpacing, res.rowSpacing := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(nodes, res.nodes := _)
    res
  }
  
}
        