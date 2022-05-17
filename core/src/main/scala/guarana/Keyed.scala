package guarana
// package impl

import scala.annotation.compileTimeOnly

opaque type Keyed[+T] = Long
object Keyed {
  inline def apply[T](keyed: T, instance: Singleton): Keyed[T] = raw(getId(keyed), getId(instance))
  def raw[T](key: Int, instance: Int): Keyed[T] = ((key.toLong << 32) & 0xFFFFFFFF00000000L) | (instance & 0xFFFFFFFF)

  inline def getId(inline a: Any): Int = inline a match {
    case i: Int => i
    case u: util.Unique => u.uniqueId
    case _ => inferId(a)
  }
  def inferId(a: Any): Int = a match {
    case i: Int => i
    case u: util.Unique => u.uniqueId
    case _ => System.identityHashCode(a)
  }
  def apply[T](id: Long): Keyed[T] = id

  extension [T](k: Keyed[T]) {
    def keyId: Int = ((k >>> 32) & 0xFFFFFFFF).toInt
    def instanceId: Int = (k & 0xFFFFFFFF).toInt
    def id: Long = k

    @compileTimeOnly("this are going to be deleted")
    def keyed: T = ???
    @compileTimeOnly("this are going to be deleted")
    def instance: Singleton = ???
  }
}
