package guarana
package impl

object WeakRefFactory {
  def apply[T <: AnyRef](v: T): util.WeakRef[T] = new util.WeakRef[T] {
    val underlying = java.lang.ref.WeakReference(v)
    override def deref: T | Null = underlying.get()
  }
}
