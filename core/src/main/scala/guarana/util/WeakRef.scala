package guarana.util

/** Platform independent representation of a weak reference. If you are on a specific platform
  * you'd better use the specific time. This class is a minimal api surface to satisfy Guaraná's needs.
  */
trait WeakRef[+T <: AnyRef] {
  def deref: T | Null
}
object WeakRef {
  def apply[T <: AnyRef](v: T): WeakRef[T] = guarana.impl.WeakRefFactory(v)
}