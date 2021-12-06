package guarana

trait VarValueChanged[Signal[+T]] {
  type T
  val signal: Signal[T]
  val instance: Singleton
  val prev: Option[T]
  val curr: T

  override def toString = s"VarValueChanged($signal, $instance, $prev, $curr)"
}
object VarValueChanged {
  def apply[Signal[+T], U](s: Signal[U], i: Singleton, p: Option[U], c: U) = new VarValueChanged[Signal] {
    type T = U
    val signal = s
    val instance = i
    val prev = p
    val curr = c
  }
  def unapply[Signal[+T]](v: VarValueChanged[Signal]) = (v.signal, v.instance, v.prev, v.curr)
}