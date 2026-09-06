package org.agrona.collections

import language.unsafeNulls

import java.lang.reflect.{Array => JReflectArray}
import java.util.{AbstractSet, Arrays, Collection, NoSuchElementException, Set => JSet}
import java.util.function.{LongConsumer, LongPredicate, Predicate}

import org.agrona.BitUtil.findNextPositivePowerOfTwo
import org.agrona.collections.CollectionUtil.validateLoadFactor
import org.agrona.generation.DoNotSub

/**
 * Literal port of `org.agrona.collections.LongHashSet`.
 *
 * Open-addressing with linear-probing expandable hash set. Allocation free in steady
 * state use when expanded.
 *
 * By storing elements as `int` primitives this significantly reduces memory consumption
 * compared with Java's builtin `HashSet[java.lang.Long]`. It extends `java.util.AbstractSet[java.lang.Long]`
 * for convenience, but calling functionality via those methods can add boxing overhead to
 * your usage.
 *
 * This class is not Threadsafe.
 *
 * This HashSet caches its iterator object by default, so nested iteration is not
 * supported. You can override this behaviour at construction by indicating that the
 * iterator should not be cached.
 *
 * NB on the port: Java allows a field and a zero-arg method of the same name to coexist
 * (e.g. field `loadFactor` + method `loadFactor()`) because fields and methods occupy
 * separate namespaces. Scala unifies these namespaces, so the backing fields that would
 * otherwise collide with their accessor methods (`loadFactor`, `resizeThreshold`,
 * `iterator`) are stored under a leading-underscore name here; the public API keeps the
 * exact Java method names/signatures.
 *
 * @see LongIterator
 * @see JSet
 */
object LongHashSet
{
  /**
   * The initial capacity used when none is specified in the constructor.
   */
  val DEFAULT_INITIAL_CAPACITY: Int = 8

  private[collections] val MISSING_VALUE: Long = 0L

  @DoNotSub private[collections] def next(index: Int, mask: Int): Int = (index + 1) & mask
}

class LongHashSet(
  proposedCapacity: Int = LongHashSet.DEFAULT_INITIAL_CAPACITY,
  loadFactorCtor: Float = Hashing.DEFAULT_LOAD_FACTOR,
  shouldAvoidAllocationCtor: Boolean = true)
  extends AbstractSet[java.lang.Long]
{
  import LongHashSet._

  private val shouldAvoidAllocation: Boolean = shouldAvoidAllocationCtor
  private var containsMissingValue: Boolean = false
  private val _loadFactor: Float = loadFactorCtor
  @DoNotSub private var _resizeThreshold: Int = 0
  // NB: excludes missing value
  @DoNotSub private var sizeOfArrayValues: Int = 0

  private var values: Array[Long] = scala.compiletime.uninitialized
  private var _iterator: LongIterator = scala.compiletime.uninitialized

  validateLoadFactor(loadFactorCtor)
  sizeOfArrayValues = 0
  {
    @DoNotSub val capacity = findNextPositivePowerOfTwo(Math.max(DEFAULT_INITIAL_CAPACITY, proposedCapacity))
    _resizeThreshold = (capacity * loadFactorCtor).toInt // @DoNotSub
    values = new Array[Long](capacity)
  }

  /**
   * Construct a hash set with a proposed initial capacity, load factor, iterator caching
   * support and `0` as a missing value.
   *
   * @param proposedCapacity for the initial capacity of the set.
   * @param loadFactor       to be used for resizing.
   */
  def this(@DoNotSub proposedCapacity: Int, loadFactor: Float) =
    this(proposedCapacity, loadFactor, true)

  /**
   * Get the load factor beyond which the set will increase size.
   *
   * @return load factor for when the set should increase size.
   */
  def loadFactor(): Float = _loadFactor

  /**
   * Get the total capacity for the set to which the load factor with be a fraction of.
   *
   * @return the total capacity for the set.
   */
  @DoNotSub def capacity(): Int = values.length

  /**
   * Get the actual threshold which when reached the map will resize.
   * This is a function of the current capacity and load factor.
   *
   * @return the threshold when the map will resize.
   */
  @DoNotSub def resizeThreshold(): Int = _resizeThreshold

  /**
   * {@inheritDoc}
   */
  override def add(value: java.lang.Long): Boolean = add(value.longValue())

  /**
   * Primitive specialised overload of `add(java.lang.Long)`.
   *
   * @param value the value to add.
   * @return true if the collection has changed, false otherwise.
   */
  def add(value: Long): Boolean =
  {
    if (MISSING_VALUE == value)
    {
      val previousContainsMissingValue = this.containsMissingValue
      containsMissingValue = true
      return !previousContainsMissingValue
    }

    val values = this.values
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(value, mask)

    var oldValue = values(index)
    while (MISSING_VALUE != oldValue)
    {
      if (oldValue == value)
      {
        return false
      }

      index = next(index, mask)
      oldValue = values(index)
    }

    values(index) = value
    sizeOfArrayValues += 1

    if (sizeOfArrayValues > _resizeThreshold)
    {
      increaseCapacity()
    }

    true
  }

  private def increaseCapacity(): Unit =
  {
    @DoNotSub val newCapacity = values.length * 2
    if (newCapacity < 0)
    {
      throw new IllegalStateException("max capacity reached at size=" + size())
    }

    rehash(newCapacity)
  }

  private def rehash(@DoNotSub newCapacity: Int): Unit =
  {
    @DoNotSub val capacity = newCapacity
    @DoNotSub val mask = newCapacity - 1
    /* @DoNotSub */ _resizeThreshold = (newCapacity * _loadFactor).toInt

    val tempValues = new Array[Long](capacity)
    val values = this.values
    @DoNotSub var vi = 0
    while (vi < values.length)
    {
      val value = values(vi)
      if (MISSING_VALUE != value)
      {
        @DoNotSub var newHash = Hashing.hash(value, mask)
        while (MISSING_VALUE != tempValues(newHash))
        {
          newHash = (newHash + 1) & mask
        }

        tempValues(newHash) = value
      }
      vi += 1
    }

    this.values = tempValues
  }

  /**
   * {@inheritDoc}
   */
  override def remove(value: Any): Boolean = remove(value.asInstanceOf[java.lang.Long].longValue())

  /**
   * Specialised version of `remove(Object)` for int.
   *
   * @param value the value to remove.
   * @return true if the value was present, false otherwise.
   */
  def remove(value: Long): Boolean =
  {
    if (MISSING_VALUE == value)
    {
      val previousContainsMissingValue = this.containsMissingValue
      containsMissingValue = false
      return previousContainsMissingValue
    }

    val values = this.values
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(value, mask)

    var oldValue = values(index)
    while (MISSING_VALUE != oldValue)
    {
      if (oldValue == value)
      {
        values(index) = MISSING_VALUE
        sizeOfArrayValues -= 1
        compactChain(index)
        return true
      }

      index = next(index, mask)
      oldValue = values(index)
    }

    false
  }

  @DoNotSub private[collections] def compactChain(deleteIndexParam: Int): Unit =
  {
    var deleteIndex = deleteIndexParam
    val values = this.values
    @DoNotSub val mask = values.length - 1

    @DoNotSub var index = deleteIndex
    var continue = true
    while (continue)
    {
      index = next(index, mask)
      val value = values(index)
      if (MISSING_VALUE == value)
      {
        return
      }

      @DoNotSub val hash = Hashing.hash(value, mask)

      if ((index < hash && (hash <= deleteIndex || deleteIndex <= index)) ||
        (hash <= deleteIndex && deleteIndex <= index))
      {
        values(deleteIndex) = value
        values(index) = MISSING_VALUE
        deleteIndex = index
      }
    }
  }

  /**
   * Compact the backing arrays by rehashing with a capacity just larger than current size
   * and giving consideration to the load factor.
   */
  def compact(): Unit =
  {
    @DoNotSub val idealCapacity = Math.round(size() * (1.0 / _loadFactor)).toInt
    rehash(findNextPositivePowerOfTwo(Math.max(DEFAULT_INITIAL_CAPACITY, idealCapacity)))
  }

  /**
   * {@inheritDoc}
   */
  override def contains(value: Any): Boolean = contains(value.asInstanceOf[java.lang.Long].longValue())

  /**
   * Contains method that does not box values.
   *
   * @param value to be checked for if the set contains it.
   * @return true if the value is contained in the set otherwise false.
   * @see Collection#contains(Object)
   */
  def contains(value: Long): Boolean =
  {
    if (MISSING_VALUE == value)
    {
      return containsMissingValue
    }

    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(value, mask)

    var existingValue = values(index)
    while (MISSING_VALUE != existingValue)
    {
      if (existingValue == value)
      {
        return true
      }

      index = next(index, mask)
      existingValue = values(index)
    }

    false
  }

  /**
   * {@inheritDoc}
   */
  @DoNotSub override def size(): Int = sizeOfArrayValues + (if (containsMissingValue) 1 else 0)

  /**
   * {@inheritDoc}
   */
  override def isEmpty(): Boolean = size() == 0

  /**
   * {@inheritDoc}
   */
  override def clear(): Unit =
  {
    if (size() > 0)
    {
      Arrays.fill(values, MISSING_VALUE)
      sizeOfArrayValues = 0
      containsMissingValue = false
    }
  }

  /**
   * {@inheritDoc}
   */
  override def addAll(coll: Collection[? <: java.lang.Long]): Boolean =
  {
    var added = false

    val it = coll.iterator()
    while (it.hasNext)
    {
      val value: java.lang.Long = it.next()
      added |= add(value)
    }

    added
  }

  /**
   * Alias for `addAll(Collection)` for the specialized case when adding another LongHashSet,
   * avoids boxing and allocations.
   *
   * @param coll containing the values to be added.
   * @return `true` if this set changed as a result of the call.
   */
  def addAll(coll: LongHashSet): Boolean =
  {
    var acc = false

    @DoNotSub var i = 0
    val cvalues = coll.values
    while (i < cvalues.length)
    {
      val value = cvalues(i)
      if (MISSING_VALUE != value)
      {
        acc |= add(value)
      }
      i += 1
    }

    if (coll.containsMissingValue)
    {
      acc |= add(MISSING_VALUE)
    }

    acc
  }

  /**
   * LongHashSet specialised variant of `containsAll(Collection)`.
   *
   * @param coll int hash set to compare against.
   * @return true if every element in other is in this.
   */
  def containsAll(coll: LongHashSet): Boolean =
  {
    @DoNotSub var i = 0
    val cvalues = coll.values
    while (i < cvalues.length)
    {
      val value = cvalues(i)
      if (MISSING_VALUE != value && !contains(value))
      {
        return false
      }
      i += 1
    }

    containsMissingValue || !coll.containsMissingValue
  }

  /**
   * Fast Path set difference for comparison with another LongHashSet.
   *
   * Note: garbage free in the identical case, allocates otherwise.
   *
   * @param other the other set to subtract.
   * @return null if identical, otherwise the set of differences.
   */
  def difference(other: LongHashSet): LongHashSet =
  {
    var difference: LongHashSet = null

    val values = this.values
    @DoNotSub var i = 0
    while (i < values.length)
    {
      val value = values(i)
      if (MISSING_VALUE != value && !other.contains(value))
      {
        if (null == difference)
        {
          difference = new LongHashSet()
        }

        difference.add(value)
      }
      i += 1
    }

    if (containsMissingValue && !other.containsMissingValue)
    {
      if (null == difference)
      {
        difference = new LongHashSet()
      }

      difference.add(MISSING_VALUE)
    }

    difference
  }

  /**
   * {@inheritDoc}
   */
  override def removeIf(filter: Predicate[? >: java.lang.Long]): Boolean = super.removeIf(filter)

  /**
   * Removes all the elements of this collection that satisfy the given predicate.
   *
   * NB: Renamed from removeIf to avoid overloading on parameter types of lambda
   * expression, which doesn't play well with type inference in lambda expressions.
   *
   * @param filter which returns `true` for elements to be removed.
   * @return `true` if any elements were removed.
   */
  def removeIfLong(filter: LongPredicate): Boolean =
  {
    var removed = false
    val iterator = this.iterator()
    while (iterator.hasNext)
    {
      if (filter.test(iterator.nextValue()))
      {
        iterator.remove()
        removed = true
      }
    }

    removed
  }

  /**
   * {@inheritDoc}
   */
  override def removeAll(coll: Collection[?]): Boolean =
  {
    var removed = false

    val it = coll.iterator()
    while (it.hasNext)
    {
      val value = it.next()
      removed |= remove(value)
    }

    removed
  }

  /**
   * Alias for `removeAll(Collection)` for the specialized case when removing another LongHashSet,
   * avoids boxing and allocations.
   *
   * @param coll containing the values to be removed.
   * @return `true` if this set changed as a result of the call.
   */
  def removeAll(coll: LongHashSet): Boolean =
  {
    var removed = false

    @DoNotSub var i = 0
    val cvalues = coll.values
    while (i < cvalues.length)
    {
      val value = cvalues(i)
      if (MISSING_VALUE != value)
      {
        removed |= remove(value)
      }
      i += 1
    }

    if (coll.containsMissingValue)
    {
      removed |= remove(MISSING_VALUE)
    }

    removed
  }

  /**
   * {@inheritDoc}
   */
  override def retainAll(coll: Collection[?]): Boolean =
  {
    var removed = false
    val values = this.values
    @DoNotSub val length = values.length
    @DoNotSub var i = 0
    while (i < length)
    {
      val value = values(i)
      if (MISSING_VALUE != value && !coll.contains(value))
      {
        values(i) = MISSING_VALUE
        sizeOfArrayValues -= 1
        removed = true
      }
      i += 1
    }

    if (removed && sizeOfArrayValues > 0)
    {
      rehash(values.length)
    }

    if (containsMissingValue && !coll.contains(MISSING_VALUE))
    {
      containsMissingValue = false
      removed = true
    }
    removed
  }

  /**
   * Alias for `retainAll(Collection)` for the specialized case when retaining on another LongHashSet,
   * avoids boxing and allocations.
   *
   * @param coll containing elements to be retained in this set.
   * @return `true` if this set changed as a result of the call.
   */
  def retainAll(coll: LongHashSet): Boolean =
  {
    var removed = false
    @DoNotSub val length = values.length
    @DoNotSub var i = 0
    while (i < length)
    {
      val value = values(i)
      if (MISSING_VALUE != value && !coll.contains(value))
      {
        values(i) = MISSING_VALUE
        sizeOfArrayValues -= 1
        removed = true
      }
      i += 1
    }

    if (removed && sizeOfArrayValues > 0)
    {
      rehash(values.length)
    }

    if (containsMissingValue && !coll.containsMissingValue)
    {
      containsMissingValue = false
      removed = true
    }
    removed
  }

  /**
   * {@inheritDoc}
   */
  override def iterator(): LongIterator =
  {
    var iterator = this._iterator
    if (null == iterator)
    {
      iterator = new LongIterator()
      if (shouldAvoidAllocation)
      {
        this._iterator = iterator
      }
    }

    iterator.reset()
  }

  /**
   * Iterate over the collection without boxing.
   *
   * @param action to be taken for each element.
   */
  def forEachLong(action: LongConsumer): Unit =
  {
    if (sizeOfArrayValues > 0)
    {
      val values = this.values
      @DoNotSub var i = 0
      while (i < values.length)
      {
        val v = values(i)
        if (MISSING_VALUE != v)
        {
          action.accept(v)
        }
        i += 1
      }
    }
    if (containsMissingValue)
    {
      action.accept(MISSING_VALUE)
    }
  }

  /**
   * Copy values from another `LongHashSet` into this one.
   *
   * @param that set to copy values from.
   */
  def copy(that: LongHashSet): Unit =
  {
    if (values.length != that.values.length)
    {
      throw new IllegalArgumentException("cannot copy object: masks not equal")
    }

    System.arraycopy(that.values, 0, values, 0, values.length)
    this.sizeOfArrayValues = that.sizeOfArrayValues
    this.containsMissingValue = that.containsMissingValue
  }

  /**
   * {@inheritDoc}
   */
  override def toString(): String =
  {
    val sb = new StringBuilder()
    sb.append('{')

    val values = this.values
    @DoNotSub var i = 0
    while (i < values.length)
    {
      val value = values(i)
      if (MISSING_VALUE != value)
      {
        sb.append(value).append(", ")
      }
      i += 1
    }

    if (containsMissingValue)
    {
      sb.append(MISSING_VALUE).append(", ")
    }

    if (sb.length() > 1)
    {
      sb.setLength(sb.length() - 2)
    }

    sb.append('}')

    sb.toString()
  }

  /**
   * {@inheritDoc}
   */
  override def toArray[T](a: Array[T & Object]): Array[T & Object] =
  {
    val componentType = a.getClass.getComponentType
    if (!componentType.isAssignableFrom(classOf[java.lang.Long]))
    {
      throw new ArrayStoreException("cannot store Longs in array of type " + componentType)
    }

    @DoNotSub val size = this.size()
    val arrayCopy: Array[T & Object] =
      if (a.length >= size) a
      else JReflectArray.newInstance(componentType, size).asInstanceOf[Array[T & Object]]
    copyValues(arrayCopy.asInstanceOf[Array[Object]])

    arrayCopy
  }

  /**
   * {@inheritDoc}
   */
  override def toArray(): Array[Object] =
  {
    val arrayCopy = new Array[Object](size())
    copyValues(arrayCopy)

    arrayCopy
  }

  private def copyValues(arrayCopy: Array[Object]): Unit =
  {
    @DoNotSub var i = 0
    val values = this.values
    @DoNotSub var vi = 0
    while (vi < values.length)
    {
      val value = values(vi)
      if (MISSING_VALUE != value)
      {
        arrayCopy(i) = java.lang.Long.valueOf(value)
        i += 1
      }
      vi += 1
    }

    if (containsMissingValue)
    {
      arrayCopy(sizeOfArrayValues) = java.lang.Long.valueOf(MISSING_VALUE)
    }
  }

  /**
   * {@inheritDoc}
   */
  override def equals(other: Any): Boolean =
  {
    if (other.asInstanceOf[AnyRef] eq this)
    {
      return true
    }

    other match
    {
      case otherSet: LongHashSet =>
        otherSet.containsMissingValue == containsMissingValue &&
          otherSet.sizeOfArrayValues == sizeOfArrayValues &&
          containsAll(otherSet)
      case c: JSet[_] =>
        if (c.size() != size())
        {
          false
        }
        else
        {
          try
          {
            containsAllJavaSet(c)
          }
          catch
          {
            case _: ClassCastException | _: NullPointerException => false
          }
        }
      case _ => false
    }
  }

  private def containsAllJavaSet(c: JSet[?]): Boolean =
  {
    val it = c.iterator()
    while (it.hasNext)
    {
      if (!this.contains(it.next()))
      {
        return false
      }
    }
    true
  }

  /**
   * {@inheritDoc}
   */
  @DoNotSub override def hashCode(): Int =
  {
    @DoNotSub var hashCode = 0
    @DoNotSub var i = 0
    while (i < values.length)
    {
      val value = values(i)
      if (MISSING_VALUE != value)
      {
        hashCode += java.lang.Long.hashCode(value)
      }
      i += 1
    }

    if (containsMissingValue)
    {
      hashCode += java.lang.Long.hashCode(MISSING_VALUE)
    }

    hashCode
  }

  /**
   * Iterator which supports unboxed access to the values via `nextValue()`.
   */
  final class LongIterator private[LongHashSet] () extends java.util.Iterator[java.lang.Long]
  {
    @DoNotSub private var remainingCount: Int = 0
    @DoNotSub private var positionCounter: Int = 0
    @DoNotSub private var stopCounter: Int = 0
    private var isPositionValid: Boolean = false

    private[LongHashSet] def reset(): LongIterator =
    {
      remainingCount = size()

      val values = LongHashSet.this.values
      @DoNotSub val length = values.length
      @DoNotSub var i = length

      if (MISSING_VALUE != values(length - 1))
      {
        i = 0
        while (i < length && MISSING_VALUE != values(i))
        {
          i += 1
        }
      }

      stopCounter = i
      positionCounter = i + length
      isPositionValid = false

      this
    }

    /**
     * {@inheritDoc}
     */
    override def hasNext(): Boolean = remainingCount > 0

    /**
     * Returns number of remaining (not yet visited) elements.
     *
     * @return number of remaining elements.
     */
    @DoNotSub def remaining(): Int = remainingCount

    /**
     * {@inheritDoc}
     */
    override def next(): java.lang.Long = nextValue()

    /**
     * Strongly typed alternative of `Iterator#next()` to avoid boxing.
     *
     * @return the next int value.
     */
    def nextValue(): Long =
    {
      if (remainingCount == 1 && containsMissingValue)
      {
        remainingCount = 0
        isPositionValid = true

        return MISSING_VALUE
      }

      findNext()

      val values = LongHashSet.this.values

      values(position(values))
    }

    /**
     * {@inheritDoc}
     */
    override def remove(): Unit =
    {
      if (isPositionValid)
      {
        if (0 == remainingCount && containsMissingValue)
        {
          containsMissingValue = false
        }
        else
        {
          val values = LongHashSet.this.values
          @DoNotSub val position = this.position(values)
          values(position) = MISSING_VALUE
          sizeOfArrayValues -= 1

          compactChain(position)
        }

        isPositionValid = false
      }
      else
      {
        throw new IllegalStateException()
      }
    }

    private def findNext(): Unit =
    {
      val values = LongHashSet.this.values
      @DoNotSub val mask = values.length - 1
      isPositionValid = true

      @DoNotSub var i = positionCounter - 1
      @DoNotSub val stop = stopCounter
      while (i >= stop)
      {
        @DoNotSub val index = i & mask
        if (MISSING_VALUE != values(index))
        {
          positionCounter = i
          remainingCount -= 1
          return
        }
        i -= 1
      }

      isPositionValid = false
      throw new NoSuchElementException()
    }

    @DoNotSub private def position(values: Array[Long]): Int =
    {
      positionCounter & (values.length - 1)
    }
  }
}
