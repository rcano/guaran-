package guarana
package gtk
opaque type Frame <: Widget = org.gnome.gtk.Frame & Widget
object Frame {
  val Child: ExternalVar.Aux[Frame, org.gnome.gtk.Widget | Null] = ExternalVar[Frame, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Label: ExternalVar.Aux[Frame, java.lang.String | Null] = ExternalVar[Frame, java.lang.String | Null]("label", _.getLabel(), _.setLabel(_), true)
  val LabelAlign: ExternalVar.Aux[Frame, Float] = ExternalVar[Frame, Float]("label-align", _.getLabelAlign(), _.setLabelAlign(_), true)
  val LabelWidget: ExternalVar.Aux[Frame, org.gnome.gtk.Widget | Null] = ExternalVar[Frame, org.gnome.gtk.Widget | Null]("label-widget", _.getLabelWidget(), _.setLabelWidget(_), true)
  ()
  extension (v: Frame) {
    def unwrap: org.gnome.gtk.Frame = v
  }
  def init(v: Frame): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Frame = {
    val res = new org.gnome.gtk.Frame()
    res.asInstanceOf[Frame]
  }
}