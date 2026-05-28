package guarana
package gtk
import util.*
opaque type Frame <: Widget = org.gnome.gtk.Frame & Widget
object Frame {
  val Child: ExternalVar.Aux[Frame, org.gnome.gtk.Widget | Null] = ExternalVar[Frame, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Label: ExternalVar.Aux[Frame, java.lang.String | Null] = ExternalVar[Frame, java.lang.String | Null]("label", _.getLabel(), _.setLabel(_), true)
  val LabelAlign: ExternalVar.Aux[Frame, Float] = ExternalVar[Frame, Float]("label-align", _.getLabelAlign(), _.setLabelAlign(_), true)
  val LabelWidget: ExternalVar.Aux[Frame, org.gnome.gtk.Widget | Null] = ExternalVar[Frame, org.gnome.gtk.Widget | Null]("label-widget", _.getLabelWidget(), _.setLabelWidget(_), true)
  ()
  extension (v: Frame) {
    def unwrap: org.gnome.gtk.Frame = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def label: Var.Aux[java.lang.String | Null, v.type] = Label.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def labelAlign: Var.Aux[Float, v.type] = LabelAlign.asInstanceOf[Var.Aux[Float, v.type]]
    def labelWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = LabelWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
  }
  def init(v: Frame): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Frame = {
    val res = new org.gnome.gtk.Frame()
    res.asInstanceOf[Frame]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, label: Opt[java.lang.String | Null] = UnsetParam, labelAlign: Opt[Float] = UnsetParam, labelWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam): VarContextAction[Frame] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    ifSet(label, res.label := _)
    ifSet(labelAlign, res.labelAlign := _)
    ifSet(labelWidget, res.labelWidget := _)
    res
  }
}