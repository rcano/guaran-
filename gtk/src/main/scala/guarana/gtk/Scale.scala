package guarana
package gtk
import util.*
opaque type Scale <: Range = org.gnome.gtk.Scale & Range
object Scale {
  val Digits: ExternalVar.Aux[Scale, Int] = ExternalVar[Scale, Int]("digits", _.getDigits(), _.setDigits(_), true)
  val DrawValue: ExternalVar.Aux[Scale, Boolean] = ExternalVar[Scale, Boolean]("draw-value", _.getDrawValue(), _.setDrawValue(_), true)
  val HasOrigin: ExternalVar.Aux[Scale, Boolean] = ExternalVar[Scale, Boolean]("has-origin", _.getHasOrigin(), _.setHasOrigin(_), true)
  val ValuePos: ExternalVar.Aux[Scale, org.gnome.gtk.PositionType] = ExternalVar[Scale, org.gnome.gtk.PositionType]("value-pos", _.getValuePos(), _.setValuePos(_), true)
  ()
  extension (v: Scale) {
    def unwrap: org.gnome.gtk.Scale = v
    def digits: Var.Aux[Int, v.type] = Digits.asInstanceOf[Var.Aux[Int, v.type]]
    def drawValue: Var.Aux[Boolean, v.type] = DrawValue.asInstanceOf[Var.Aux[Boolean, v.type]]
    def hasOrigin: Var.Aux[Boolean, v.type] = HasOrigin.asInstanceOf[Var.Aux[Boolean, v.type]]
    def valuePos: Var.Aux[org.gnome.gtk.PositionType, v.type] = ValuePos.asInstanceOf[Var.Aux[org.gnome.gtk.PositionType, v.type]]
  }
  def init(v: Scale): Unit = {
    Range.init(v)
  }
  def uninitialized(): Scale = {
    val res = new org.gnome.gtk.Scale()
    res.asInstanceOf[Scale]
  }
  def apply(digits: Opt[Int] = UnsetParam, drawValue: Opt[Boolean] = UnsetParam, hasOrigin: Opt[Boolean] = UnsetParam, valuePos: Opt[org.gnome.gtk.PositionType] = UnsetParam): VarContextAction[Scale] = {
    val res = uninitialized()
    init(res)
    ifSet(digits, res.digits := _)
    ifSet(drawValue, res.drawValue := _)
    ifSet(hasOrigin, res.hasOrigin := _)
    ifSet(valuePos, res.valuePos := _)
    res
  }
}