package guarana
package gtk
import util.*
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
    def autohide: Var.Aux[Boolean, v.type] = Autohide.asInstanceOf[Var.Aux[Boolean, v.type]]
    def cascadePopdown: Var.Aux[Boolean, v.type] = CascadePopdown.asInstanceOf[Var.Aux[Boolean, v.type]]
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def hasArrow: Var.Aux[Boolean, v.type] = HasArrow.asInstanceOf[Var.Aux[Boolean, v.type]]
    def mnemonicsVisible: Var.Aux[Boolean, v.type] = MnemonicsVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    def position: Var.Aux[org.gnome.gtk.PositionType, v.type] = Position.asInstanceOf[Var.Aux[org.gnome.gtk.PositionType, v.type]]
    export unwrap.onActivateDefault, unwrap.onClosed
  }
  def init(v: Popover): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Popover = {
    val res = new org.gnome.gtk.Popover()
    res.asInstanceOf[Popover]
  }
  def apply(autohide: Opt[Boolean] = UnsetParam, cascadePopdown: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, hasArrow: Opt[Boolean] = UnsetParam, mnemonicsVisible: Opt[Boolean] = UnsetParam, position: Opt[org.gnome.gtk.PositionType] = UnsetParam): VarContextAction[Popover] = {
    val res = uninitialized()
    init(res)
    ifSet(autohide, res.autohide := _)
    ifSet(cascadePopdown, res.cascadePopdown := _)
    ifSet(child, res.child := _)
    ifSet(hasArrow, res.hasArrow := _)
    ifSet(mnemonicsVisible, res.mnemonicsVisible := _)
    ifSet(position, res.position := _)
    res
  }
}