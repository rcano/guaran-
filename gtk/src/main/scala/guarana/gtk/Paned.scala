package guarana
package gtk
import util.*
opaque type Paned <: Widget = org.gnome.gtk.Paned & Widget
object Paned {
  val EndChild: ExternalVar.Aux[Paned, org.gnome.gtk.Widget | Null] = ExternalVar[Paned, org.gnome.gtk.Widget | Null]("end-child", _.getEndChild(), _.setEndChild(_), true)
  val Orientation: ExternalVar.Aux[Paned, org.gnome.gtk.Orientation] = ExternalVar[Paned, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Position: ExternalVar.Aux[Paned, Int] = ExternalVar[Paned, Int]("position", _.getPosition(), _.setPosition(_), true)
  val ResizeEndChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("resize-end-child", _.getResizeEndChild(), _.setResizeEndChild(_), true)
  val ResizeStartChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("resize-start-child", _.getResizeStartChild(), _.setResizeStartChild(_), true)
  val ShrinkEndChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("shrink-end-child", _.getShrinkEndChild(), _.setShrinkEndChild(_), true)
  val ShrinkStartChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("shrink-start-child", _.getShrinkStartChild(), _.setShrinkStartChild(_), true)
  val StartChild: ExternalVar.Aux[Paned, org.gnome.gtk.Widget | Null] = ExternalVar[Paned, org.gnome.gtk.Widget | Null]("start-child", _.getStartChild(), _.setStartChild(_), true)
  val WideHandle: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("wide-handle", _.getWideHandle(), _.setWideHandle(_), true)
  ()
  extension (v: Paned) {
    def unwrap: org.gnome.gtk.Paned = v
    def endChild: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = EndChild.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def position: Var.Aux[Int, v.type] = Position.asInstanceOf[Var.Aux[Int, v.type]]
    def resizeEndChild: Var.Aux[Boolean, v.type] = ResizeEndChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def resizeStartChild: Var.Aux[Boolean, v.type] = ResizeStartChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def shrinkEndChild: Var.Aux[Boolean, v.type] = ShrinkEndChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def shrinkStartChild: Var.Aux[Boolean, v.type] = ShrinkStartChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def startChild: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = StartChild.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def wideHandle: Var.Aux[Boolean, v.type] = WideHandle.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onAcceptPosition, unwrap.onCancelPosition, unwrap.onCycleChildFocus, unwrap.onCycleHandleFocus, unwrap.onMoveHandle, unwrap.onToggleHandleFocus
  }
  def init(v: Paned): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Paned = {
    val res = new org.gnome.gtk.Paned()
    res.asInstanceOf[Paned]
  }
  def apply(endChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, orientation: Opt[org.gnome.gtk.Orientation] = UnsetParam, position: Opt[Int] = UnsetParam, resizeEndChild: Opt[Boolean] = UnsetParam, resizeStartChild: Opt[Boolean] = UnsetParam, shrinkEndChild: Opt[Boolean] = UnsetParam, shrinkStartChild: Opt[Boolean] = UnsetParam, startChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, wideHandle: Opt[Boolean] = UnsetParam): VarContextAction[Paned] = {
    val res = uninitialized()
    init(res)
    ifSet(endChild, res.endChild := _)
    ifSet(orientation, res.orientation := _)
    ifSet(position, res.position := _)
    ifSet(resizeEndChild, res.resizeEndChild := _)
    ifSet(resizeStartChild, res.resizeStartChild := _)
    ifSet(shrinkEndChild, res.shrinkEndChild := _)
    ifSet(shrinkStartChild, res.shrinkStartChild := _)
    ifSet(startChild, res.startChild := _)
    ifSet(wideHandle, res.wideHandle := _)
    res
  }
}