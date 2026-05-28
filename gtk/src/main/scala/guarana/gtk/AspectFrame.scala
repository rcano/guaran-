package guarana
package gtk
import util.*
opaque type AspectFrame <: Widget = org.gnome.gtk.AspectFrame & Widget
object AspectFrame {
  val Child: ExternalVar.Aux[AspectFrame, org.gnome.gtk.Widget | Null] = ExternalVar[AspectFrame, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val ObeyChild: ExternalVar.Aux[AspectFrame, Boolean] = ExternalVar[AspectFrame, Boolean]("obey-child", _.getObeyChild(), _.setObeyChild(_), true)
  val Ratio: ExternalVar.Aux[AspectFrame, Float] = ExternalVar[AspectFrame, Float]("ratio", _.getRatio(), _.setRatio(_), true)
  val Xalign: ExternalVar.Aux[AspectFrame, Float] = ExternalVar[AspectFrame, Float]("xalign", _.getXalign(), _.setXalign(_), true)
  val Yalign: ExternalVar.Aux[AspectFrame, Float] = ExternalVar[AspectFrame, Float]("yalign", _.getYalign(), _.setYalign(_), true)
  ()
  extension (v: AspectFrame) {
    def unwrap: org.gnome.gtk.AspectFrame = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def obeyChild: Var.Aux[Boolean, v.type] = ObeyChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def ratio: Var.Aux[Float, v.type] = Ratio.asInstanceOf[Var.Aux[Float, v.type]]
    def xalign: Var.Aux[Float, v.type] = Xalign.asInstanceOf[Var.Aux[Float, v.type]]
    def yalign: Var.Aux[Float, v.type] = Yalign.asInstanceOf[Var.Aux[Float, v.type]]
  }
  def init(v: AspectFrame): Unit = {
    Widget.init(v)
  }
  def uninitialized(): AspectFrame = {
    val res = new org.gnome.gtk.AspectFrame()
    res.asInstanceOf[AspectFrame]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, obeyChild: Opt[Boolean] = UnsetParam, ratio: Opt[Float] = UnsetParam, xalign: Opt[Float] = UnsetParam, yalign: Opt[Float] = UnsetParam): VarContextAction[AspectFrame] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    ifSet(obeyChild, res.obeyChild := _)
    ifSet(ratio, res.ratio := _)
    ifSet(xalign, res.xalign := _)
    ifSet(yalign, res.yalign := _)
    res
  }
}