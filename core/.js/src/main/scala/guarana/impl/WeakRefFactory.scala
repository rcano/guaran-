package guarana
package impl

object WeakRefFactory {
  def apply[T <: AnyRef](v: T): util.WeakRef[T] = new util.WeakRef[T] {
    val underlying = scalajs.js.WeakRef(v)
    override def deref: T | Null = underlying.deref().orNull
  }
}
