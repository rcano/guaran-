package apricot.util

/** A store that computes a value on demand the first time and then stores it.
  * It supports invalidating the cache so the value must be recomputed.
  */
class CachedValue[T] private(f: () => T) {
  private var cached: T | Null = null
  def get: T = {
    if (cached == null) cached = f()
    cached.asInstanceOf[T]
  }
  def invalidate(): Unit = cached = null
}
object CachedValue {
  def apply[T](f: => T): CachedValue[T] = new CachedValue(() => f)
}
