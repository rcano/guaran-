package guarana
package gtk
opaque type Paned <: Widget = org.gnome.gtk.Paned & Widget
object Paned {
  val Position: ExternalVar.Aux[Paned, Int] = ExternalVar[Paned, Int]("position", _.getPosition(), _.setPosition(_), true)
  val ResizeEndChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("resize-end-child", _.getResizeEndChild(), _.setResizeEndChild(_), true)
  val ResizeStartChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("resize-start-child", _.getResizeStartChild(), _.setResizeStartChild(_), true)
  val ShrinkEndChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("shrink-end-child", _.getShrinkEndChild(), _.setShrinkEndChild(_), true)
  val ShrinkStartChild: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("shrink-start-child", _.getShrinkStartChild(), _.setShrinkStartChild(_), true)
  val WideHandle: ExternalVar.Aux[Paned, Boolean] = ExternalVar[Paned, Boolean]("wide-handle", _.getWideHandle(), _.setWideHandle(_), true)
  ()
  extension (v: Paned) {
    def unwrap: org.gnome.gtk.Paned = v
    export unwrap.onAcceptPosition, unwrap.onCancelPosition, unwrap.onCycleChildFocus, unwrap.onCycleHandleFocus, unwrap.onMoveHandle, unwrap.onToggleHandleFocus
  }
  def init(v: Paned): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Paned = {
    val res = new org.gnome.gtk.Paned()
    res.asInstanceOf[Paned]
  }
}