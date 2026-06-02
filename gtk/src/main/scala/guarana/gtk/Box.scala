
package guarana
package gtk

import guarana.util.*

opaque type Box <: guarana.gtk.Widget  = org.gnome.gtk.Box & guarana.gtk.Widget
object Box extends VarsMap {
  val BaselineChild: ExternalVar.Aux[Box, Int] = ExternalVar[Box, Int]("baseline-child", _.getBaselineChild(), _.setBaselineChild(_), true)
  val BaselinePosition: ExternalVar.Aux[Box, org.gnome.gtk.BaselinePosition] = ExternalVar[Box, org.gnome.gtk.BaselinePosition]("baseline-position", _.getBaselinePosition(), _.setBaselinePosition(_), true)
  val Homogeneous: ExternalVar.Aux[Box, Boolean] = ExternalVar[Box, Boolean]("homogeneous", _.getHomogeneous(), _.setHomogeneous(_), true)
  val Nodes: Var[Seq[Widget]] = Var[Seq[Widget]]("nodes", Seq.empty, false)
  val Orientation: ExternalVar.Aux[Box, org.gnome.gtk.Orientation] = ExternalVar[Box, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Spacing: ExternalVar.Aux[Box, Int] = ExternalVar[Box, Int]("spacing", _.getSpacing(), _.setSpacing(_), true)

  

  extension (v: Box) {
    def unwrap: org.gnome.gtk.Box = v

    def baselineChild: Var.Aux[Int, v.type] = guarana.gtk.Box.BaselineChild.asInstanceOf[Var.Aux[Int, v.type]]
    def baselinePosition: Var.Aux[org.gnome.gtk.BaselinePosition, v.type] = guarana.gtk.Box.BaselinePosition.asInstanceOf[Var.Aux[org.gnome.gtk.BaselinePosition, v.type]]
    def homogeneous: Var.Aux[Boolean, v.type] = guarana.gtk.Box.Homogeneous.asInstanceOf[Var.Aux[Boolean, v.type]]
    def nodes: Var.Aux[Seq[Widget], v.type] = guarana.gtk.Box.Nodes.asInstanceOf[Var.Aux[Seq[Widget], v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.Box.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def spacing: Var.Aux[Int, v.type] = guarana.gtk.Box.Spacing.asInstanceOf[Var.Aux[Int, v.type]]

    

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

  def wrap(v: org.gnome.gtk.Box): Box = 
    val res = v.asInstanceOf[Box]
    
    res

  def init(v: Box): Toolkit ?=> Unit = (tk: Toolkit) ?=> {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    tk.update {
      v.varUpdates := EventIterator.forsome {
        case v.nodes(_, newv) =>
          while (v.getFirstChild() != null) v.remove(v.getFirstChild())
          newv.foreach(w => v.append(w.unwrap))
      }
    }
    
  }
  def uninitialized(arg$0: org.gnome.gtk.Orientation, arg$1: Int): Box = {
    val res = new org.gnome.gtk.Box(arg$0, arg$1)
    
    res.asInstanceOf[Box]
  }
  
  def apply(
    arg$0: org.gnome.gtk.Orientation, arg$1: Int,
    baselineChild: Opt[Binding[Int]] = UnsetParam,
    baselinePosition: Opt[Binding[org.gnome.gtk.BaselinePosition]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
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
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    nodes: Opt[Binding[Seq[Widget]]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    spacing: Opt[Binding[Int]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Toolkit ?=> VarContextAction[Box] = {
    val res = uninitialized(arg$0, arg$1)
    guarana.gtk.Box.init(res)
    ifSet(baselineChild, res.baselineChild := _)
    ifSet(baselinePosition, res.baselinePosition := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
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
    ifSet(name, res.name := _)
    ifSet(nodes, res.nodes := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(spacing, res.spacing := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
  
}
        