package guarana.util

/** Represents types that once instatiated, they are unique. That means they are not expected to be compared
  * to other instances of the same type and be able to yield true.
  * 
  * This is equivalent to object identity by java standards, but it allows us to optimize the value to a known int
  */
transparent trait Unique {
  val uniqueId = Unique.createdCount.incrementAndGet()
}
private[util] object Unique {
  val createdCount = new java.util.concurrent.atomic.AtomicInteger()
}
