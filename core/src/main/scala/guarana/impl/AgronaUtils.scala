package guarana
package impl

import org.agrona.collections.*

object AgronaUtils {
  extension (lhs: LongHashSet) inline def fastForeach(inline f: Long => Unit): Unit = {
    val it = lhs.iterator.unn
    while it.hasNext do f(it.nextValue)
  }
  extension (lhs: IntHashSet) inline def fastForeach(inline f: Int => Unit): Unit = {
    val it = lhs.iterator.unn
    while it.hasNext do f(it.nextValue)
  }
}
