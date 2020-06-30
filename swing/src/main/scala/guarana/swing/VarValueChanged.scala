package guarana.swing

trait VarValueChanged[Signal[+T]] {
  type T
  val key: Keyed[Signal[T]]
  val prev: Option[T]
  val curr: T

  override def toString = s"VarValueChanged($key, $prev, $curr)"
}
object VarValueChanged {
  def apply[Signal[+T], U](k: Keyed[Signal[U]], p: Option[U], c: U) = new VarValueChanged[Signal] {
    type T = U
    val key = k
    val prev = p
    val curr = c
  }
  def unapply[Signal[+T]](v: VarValueChanged[Signal]) = (v.key.keyed, v.key.instance, v.prev, v.curr)
}