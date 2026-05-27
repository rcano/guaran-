package guarana
package gtk
opaque type Scale <: Range = org.gnome.gtk.Scale & Range
object Scale {
  val Digits: ExternalVar.Aux[Scale, Int] = ExternalVar[Scale, Int]("digits", _.getDigits(), _.setDigits(_), true)
  val DrawValue: ExternalVar.Aux[Scale, Boolean] = ExternalVar[Scale, Boolean]("draw-value", _.getDrawValue(), _.setDrawValue(_), true)
  val HasOrigin: ExternalVar.Aux[Scale, Boolean] = ExternalVar[Scale, Boolean]("has-origin", _.getHasOrigin(), _.setHasOrigin(_), true)
  val ValuePos: ExternalVar.Aux[Scale, org.gnome.gtk.PositionType] = ExternalVar[Scale, org.gnome.gtk.PositionType]("value-pos", _.getValuePos(), _.setValuePos(_), true)
  ()
  extension (v: Scale) {
    def unwrap: org.gnome.gtk.Scale = v
  }
  def init(v: Scale): Unit = {
    Range.init(v)
  }
  def uninitialized(): Scale = {
    val res = new org.gnome.gtk.Scale()
    res.asInstanceOf[Scale]
  }
}