package guarana
package gtk
opaque type Popover <: Widget = org.gnome.gtk.Popover & Widget
object Popover {
  val Autohide: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("autohide", _.getAutohide(), _.setAutohide(_), true)
  val CascadePopdown: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("cascade-popdown", _.getCascadePopdown(), _.setCascadePopdown(_), true)
  val Child: ExternalVar.Aux[Popover, org.gnome.gtk.Widget | Null] = ExternalVar[Popover, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val HasArrow: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("has-arrow", _.getHasArrow(), _.setHasArrow(_), true)
  val MnemonicsVisible: ExternalVar.Aux[Popover, Boolean] = ExternalVar[Popover, Boolean]("mnemonics-visible", _.getMnemonicsVisible(), _.setMnemonicsVisible(_), true)
  val Position: ExternalVar.Aux[Popover, org.gnome.gtk.PositionType] = ExternalVar[Popover, org.gnome.gtk.PositionType]("position", _.getPosition(), _.setPosition(_), true)
  ()
  extension (v: Popover) {
    def unwrap: org.gnome.gtk.Popover = v
    export unwrap.onActivateDefault, unwrap.onClosed
  }
  def init(v: Popover): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Popover = {
    val res = new org.gnome.gtk.Popover()
    res.asInstanceOf[Popover]
  }
}