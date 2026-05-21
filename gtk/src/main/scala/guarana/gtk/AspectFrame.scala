package guarana
package gtk
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
  }
}