
package guarana
package gtk

import guarana.util.*

opaque type Paned <: guarana.gtk.Widget  = org.gnome.gtk.Paned & guarana.gtk.Widget
object Paned extends VarsMap {
  val EndChild: ExternalVar.Aux[Paned, guarana.gtk.Widget | Null] = ExternalVar[Paned, guarana.gtk.Widget | Null]("end-child", _.getEndChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setEndChild(v.?(_.unwrap)), true)
  val Orientation: ExternalVar.Aux[Paned, org.gnome.gtk.Orientation] = ExternalVar[Paned, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Position: ExternalVar.Aux[Paned, Int] = ExternalVar[Paned, Int]("position", _.getPosition(), _.setPosition(_), true)
  val ResizeEndChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("resize-end-child", _.getResizeEndChild(), _.setResizeEndChild(_), true)
  val ResizeStartChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("resize-start-child", _.getResizeStartChild(), _.setResizeStartChild(_), true)
  val ShrinkEndChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("shrink-end-child", _.getShrinkEndChild(), _.setShrinkEndChild(_), true)
  val ShrinkStartChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("shrink-start-child", _.getShrinkStartChild(), _.setShrinkStartChild(_), true)
  val StartChild: ExternalVar.Aux[Paned, guarana.gtk.Widget | Null] = ExternalVar[Paned, guarana.gtk.Widget | Null]("start-child", _.getStartChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setStartChild(v.?(_.unwrap)), true)
  val WideHandle: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("wide-handle", _.getWideHandle(), _.setWideHandle(_), true)

  

  extension (v: Paned) {
    def unwrap: org.gnome.gtk.Paned = v

    def endChild: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Paned.EndChild.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.Paned.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def position: Var.Aux[Int, v.type] = guarana.gtk.Paned.Position.asInstanceOf[Var.Aux[Int, v.type]]
    def resizeEndChild: Var.Aux[Boolean, v.type] = guarana.gtk.Paned.ResizeEndChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def resizeStartChild: Var.Aux[Boolean, v.type] = guarana.gtk.Paned.ResizeStartChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def shrinkEndChild: Var.Aux[Boolean, v.type] = guarana.gtk.Paned.ShrinkEndChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def shrinkStartChild: Var.Aux[Boolean, v.type] = guarana.gtk.Paned.ShrinkStartChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def startChild: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.Paned.StartChild.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def wideHandle: Var.Aux[Boolean, v.type] = guarana.gtk.Paned.WideHandle.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onAcceptPosition,
      onCancelPosition,
      onCycleChildFocus,
      onCycleHandleFocus,
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onMoveHandle,
      onNotify,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onToggleHandleFocus,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.Paned): Paned = 
    val res = v.asInstanceOf[Paned]
    
    res

  def init(v: Paned): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(arg$0: org.gnome.gtk.Orientation): Paned = {
    val res = new org.gnome.gtk.Paned(arg$0)
    
    res.asInstanceOf[Paned]
  }
  
  def apply(
    arg$0: org.gnome.gtk.Orientation,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    endChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
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
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    position: Opt[Binding[Int]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    resizeEndChild: Opt[Binding[Boolean]] = UnsetParam,
    resizeStartChild: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    shrinkEndChild: Opt[Binding[Boolean]] = UnsetParam,
    shrinkStartChild: Opt[Binding[Boolean]] = UnsetParam,
    startChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    wideHandle: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Paned] = {
    val res = uninitialized(arg$0)
    guarana.gtk.Paned.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(endChild, res.endChild := _)
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
    ifSet(position, res.position := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(resizeEndChild, res.resizeEndChild := _)
    ifSet(resizeStartChild, res.resizeStartChild := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(shrinkEndChild, res.shrinkEndChild := _)
    ifSet(shrinkStartChild, res.shrinkStartChild := _)
    ifSet(startChild, res.startChild := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(wideHandle, res.wideHandle := _)
    res
  }
  
}
        