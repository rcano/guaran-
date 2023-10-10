package guarana
package impl

import scala.jdk.CollectionConverters.*
import java.lang.ref.{WeakReference, ReferenceQueue}
import KeyedWeakHashMap.*

class KeyedWeakHashMap[K, V] extends collection.mutable.Map[Keyed[K], V] {
  private val underlying = new java.util.HashMap[KeyDef[K], V](256, 0.65)
  private val refQueue = ReferenceQueue[Any]()
  
  extension (k: Keyed[K]) def toWeak: WeakKeyed[K] = throw UnsupportedOperationException("can't toWeak a Keyed anymore")//WeakKeyed(k.keyed, k.instance, refQueue)

  def addOne(elem: (Keyed[K], V)) = {
    expungeStaleEntries()
    underlying.put(elem._1.toWeak, elem._2)
    this
  }
  
  def iterator = {
    expungeStaleEntries()
    underlying.asScala.iterator.map(e => e._1.toKeyed -> e._2)
  }
  
  private var mutableKeyed: MutableWeakKeyed[K] | Null = null
  def get(key: Keyed[K]) = {
    import scala.language.unsafeNulls
    expungeStaleEntries()
    // if (mutableKeyed == null) mutableKeyed = MutableWeakKeyed(key.keyed, key.instance)
    // else {
    //   mutableKeyed.keyed = key.keyed
    //   mutableKeyed.instance = key.instance
    // }
    underlying.get(mutableKeyed).toOption
  }
  
  def subtractOne(elem: Keyed[K]) = {
    expungeStaleEntries()
    underlying.remove(elem.toWeak)
    this
  }

  private def expungeStaleEntries(): Unit = {
    var ref = refQueue.poll()
    while (ref != null) {
      underlying.remove(ref.asInstanceOf[WeakKeyed[K]])
      ref = refQueue.poll()
    }
  }

  override def size = {
    expungeStaleEntries()
    underlying.size
  }
}
object KeyedWeakHashMap {

  sealed trait KeyDef[K] {
    def keyed: K
    def get(): Singleton | Null
    override def equals(other: Any) = {
      // since we control all the instantiations and usages of KeyDef, we know we are never going to compare it against anything else, so save the instanceOf check
      val o = other.asInstanceOf[KeyDef[K]]
      val myInstance = get()
      if (myInstance == null) this eq o
      else keyed == o.keyed && myInstance == o.get()
    }
    override def hashCode = java.util.Objects.hash(keyed.asInstanceOf[AnyRef], get().asInstanceOf[AnyRef])
    override def toString = s"WeakKeyed($keyed, ${get()})"

    def toKeyed = Keyed(keyed, get().unn)
  }

  private[KeyedWeakHashMap] final class WeakKeyed[K](val keyed: K, instance: Singleton, queue: ReferenceQueue[Any] | Null) extends WeakReference[Singleton](instance, queue) with KeyDef[K]

  /** Special optimization for calling get. We don't want to create a new instance just to search */
  private[KeyedWeakHashMap] final class MutableWeakKeyed[K](
    var keyed: K,
    var instance: Singleton,
  ) extends KeyDef[K] {
    def get() = instance
  }
}