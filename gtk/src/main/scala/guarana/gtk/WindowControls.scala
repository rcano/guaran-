package guarana
package gtk
import util.*
opaque type WindowControls <: Widget = org.gnome.gtk.WindowControls & Widget
object WindowControls {
  val DecorationLayout: ExternalVar.Aux[WindowControls, java.lang.String | Null] = ExternalVar[WindowControls, java.lang.String | Null]("decoration-layout", _.getDecorationLayout(), _.setDecorationLayout(_), true)
  val Side: ExternalVar.Aux[WindowControls, org.gnome.gtk.PackType] = ExternalVar[WindowControls, org.gnome.gtk.PackType]("side", _.getSide(), _.setSide(_), true)
  val UseNativeControls: ExternalVar.Aux[WindowControls, Boolean] = ExternalVar[WindowControls, Boolean]("use-native-controls", _.getUseNativeControls(), _.setUseNativeControls(_), true)
  ()
  extension (v: WindowControls) {
    def unwrap: org.gnome.gtk.WindowControls = v
    def decorationLayout: Var.Aux[java.lang.String | Null, v.type] = DecorationLayout.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def side: Var.Aux[org.gnome.gtk.PackType, v.type] = Side.asInstanceOf[Var.Aux[org.gnome.gtk.PackType, v.type]]
    def useNativeControls: Var.Aux[Boolean, v.type] = UseNativeControls.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def init(v: WindowControls): Unit = {
    Widget.init(v)
  }
  def uninitialized(): WindowControls = {
    val res = new org.gnome.gtk.WindowControls()
    res.asInstanceOf[WindowControls]
  }
  def apply(decorationLayout: Opt[java.lang.String | Null] = UnsetParam, side: Opt[org.gnome.gtk.PackType] = UnsetParam, useNativeControls: Opt[Boolean] = UnsetParam): VarContextAction[WindowControls] = {
    val res = uninitialized()
    init(res)
    ifSet(decorationLayout, res.decorationLayout := _)
    ifSet(side, res.side := _)
    ifSet(useNativeControls, res.useNativeControls := _)
    res
  }
}