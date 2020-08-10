package guarana.swing
package impl

import java.lang.ref.{WeakReference, ReferenceQueue}
import KeyedWeakHashMap._

class KeyedWeakHashMap[K, V] extends collection.mutable.Map[Keyed[K], V] {
  private val underlying = collection.mutable.AnyRefMap.empty[WeakKeyed[K], V]
  private val refQueue = ReferenceQueue[Any]()
  
  def (k: Keyed[K]) toWeak: WeakKeyed[K] = WeakKeyed(k.keyed, k.instance, refQueue)

  def addOne(elem: (Keyed[K], V)) = {
    expungeStaleEntries()
    underlying(elem._1.toWeak) = elem._2
    this
  }
  
  def iterator = {
    expungeStaleEntries()
    underlying.iterator.map(e => e._1.toKeyed -> e._2)
  }
  
  def get(key: Keyed[K]) = {
    expungeStaleEntries()
    underlying.get(key.toWeak)
  }
  
  def subtractOne(elem: Keyed[K]) = {
    expungeStaleEntries()
    underlying -= elem.toWeak
    this
  }

  private def expungeStaleEntries(): Unit = {
    Iterator.continually(refQueue.poll()).takeWhile(_ != null).foreach { ref =>
      underlying -= ref.asInstanceOf[WeakKeyed[K]]
    }
  }

  override def size = {
    expungeStaleEntries()
    underlying.size
  }
}
object KeyedWeakHashMap {
  private[KeyedWeakHashMap] class WeakKeyed[K](val keyed: K, instance: Any, queue: ReferenceQueue[Any]) extends WeakReference[Any](instance, queue) {
    override def equals(o: Any) = o match {
      case o: WeakKeyed[K@unchecked] => 
        val myInstance = get()
        if (myInstance == null) this eq o
        else keyed == o.keyed && myInstance == o.get()
      case _ => false
    }
    override val hashCode = java.util.Objects.hash(keyed.asInstanceOf[AnyRef], get().asInstanceOf[AnyRef])
    override def toString = s"WeakKeyed($keyed, ${get()})"

    def toKeyed = Keyed(keyed, get())
  }
}