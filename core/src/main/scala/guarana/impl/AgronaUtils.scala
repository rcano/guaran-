package guarana
package impl

import org.agrona.collections.*

object AgronaUtils {
  extension (lhs: LongHashSet) inline infix def fastForeach(inline f: Long => Unit): Unit = {
    val it = lhs.iterator
    try while it.hasNext do f(it.nextValue())
    catch case e: NoSuchElementException => scribe.error(s"Agrona iterator caused NoSuchElementExceptions", e)
  }
  extension (lhs: IntHashSet) inline infix def fastForeach(inline f: Int => Unit): Unit = {
    val it = lhs.iterator
    try while it.hasNext do f(it.nextValue())
    catch case e: NoSuchElementException => scribe.error(s"Agrona iterator caused NoSuchElementExceptions", e)

  }
}
