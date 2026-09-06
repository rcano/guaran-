package org.agrona.collections

import language.unsafeNulls

import java.lang.reflect.{Array => JReflectArray}
import java.util.{AbstractSet, Arrays, Collection, NoSuchElementException, Set => JSet}
import java.util.function.{IntConsumer, IntPredicate, Predicate}

import org.agrona.BitUtil.findNextPositivePowerOfTwo
import org.agrona.collections.CollectionUtil.validateLoadFactor
import org.agrona.generation.DoNotSub

/**
 * Literal port of `org.agrona.collections.IntHashSet`.
 *
 * Open-addressing with linear-probing expandable hash set. Allocation free in steady
 * state use when expanded.
 *
 * By storing elements as `int` primitives this significantly reduces memory consumption
 * compared with Java's builtin `HashSet[Integer]`. It extends `java.util.AbstractSet[Integer]`
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
 * @see IntIterator
 * @see JSet
 */
object IntHashSet
{
  /**
   * The initial capacity used when none is specified in the constructor.
   */
  val DEFAULT_INITIAL_CAPACITY: Int = 8

  private[collections] val MISSING_VALUE: Int = 0

  @DoNotSub private[collections] def next(index: Int, mask: Int): Int = (index + 1) & mask
}

class IntHashSet(
  proposedCapacity: Int = LongHashSet.DEFAULT_INITIAL_CAPACITY,
  loadFactorCtor: Float = Hashing.DEFAULT_LOAD_FACTOR,
  shouldAvoidAllocationCtor: Boolean = true)
  extends AbstractSet[Integer]
{
  import IntHashSet._

  private val shouldAvoidAllocation: Boolean = shouldAvoidAllocationCtor
  private var containsMissingValue: Boolean = false
  private val _loadFactor: Float = loadFactorCtor
  @DoNotSub private var _resizeThreshold: Int = 0
  // NB: excludes missing value
  @DoNotSub private var sizeOfArrayValues: Int = 0

  private var values: Array[Int] = scala.compiletime.uninitialized
  private var _iterator: IntIterator = scala.compiletime.uninitialized

  validateLoadFactor(loadFactorCtor)
  sizeOfArrayValues = 0
  {
    @DoNotSub val capacity = findNextPositivePowerOfTwo(Math.max(DEFAULT_INITIAL_CAPACITY, proposedCapacity))
    _resizeThreshold = (capacity * loadFactorCtor).toInt // @DoNotSub
    values = new Array[Int](capacity)
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
  override def add(value: Integer): Boolean = add(value.intValue())

  /**
   * Primitive specialised overload of `add(Integer)`.
   *
   * @param value the value to add.
   * @return true if the collection has changed, false otherwise.
   */
  def add(value: Int): Boolean =
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

    val tempValues = new Array[Int](capacity)
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
  override def remove(value: Any): Boolean = remove(value.asInstanceOf[Integer].intValue())

  /**
   * Specialised version of `remove(Object)` for int.
   *
   * @param value the value to remove.
   * @return true if the value was present, false otherwise.
   */
  def remove(value: Int): Boolean =
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
  override def contains(value: Any): Boolean = contains(value.asInstanceOf[Integer].intValue())

  /**
   * Contains method that does not box values.
   *
   * @param value to be checked for if the set contains it.
   * @return true if the value is contained in the set otherwise false.
   * @see Collection#contains(Object)
   */
  def contains(value: Int): Boolean =
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
  override def addAll(coll: Collection[? <: Integer]): Boolean =
  {
    var added = false

    val it = coll.iterator()
    while (it.hasNext)
    {
      val value: Integer = it.next()
      added |= add(value)
    }

    added
  }

  /**
   * Alias for `addAll(Collection)` for the specialized case when adding another IntHashSet,
   * avoids boxing and allocations.
   *
   * @param coll containing the values to be added.
   * @return `true` if this set changed as a result of the call.
   */
  def addAll(coll: IntHashSet): Boolean =
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
   * IntHashSet specialised variant of `containsAll(Collection)`.
   *
   * @param coll int hash set to compare against.
   * @return true if every element in other is in this.
   */
  def containsAll(coll: IntHashSet): Boolean =
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
   * Fast Path set difference for comparison with another IntHashSet.
   *
   * Note: garbage free in the identical case, allocates otherwise.
   *
   * @param other the other set to subtract.
   * @return null if identical, otherwise the set of differences.
   */
  def difference(other: IntHashSet): IntHashSet =
  {
    var difference: IntHashSet = null

    val values = this.values
    @DoNotSub var i = 0
    while (i < values.length)
    {
      val value = values(i)
      if (MISSING_VALUE != value && !other.contains(value))
      {
        if (null == difference)
        {
          difference = new IntHashSet()
        }

        difference.add(value)
      }
      i += 1
    }

    if (containsMissingValue && !other.containsMissingValue)
    {
      if (null == difference)
      {
        difference = new IntHashSet()
      }

      difference.add(MISSING_VALUE)
    }

    difference
  }

  /**
   * {@inheritDoc}
   */
  override def removeIf(filter: Predicate[? >: Integer]): Boolean = super.removeIf(filter)

  /**
   * Removes all the elements of this collection that satisfy the given predicate.
   *
   * NB: Renamed from removeIf to avoid overloading on parameter types of lambda
   * expression, which doesn't play well with type inference in lambda expressions.
   *
   * @param filter which returns `true` for elements to be removed.
   * @return `true` if any elements were removed.
   */
  def removeIfInt(filter: IntPredicate): Boolean =
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
   * Alias for `removeAll(Collection)` for the specialized case when removing another IntHashSet,
   * avoids boxing and allocations.
   *
   * @param coll containing the values to be removed.
   * @return `true` if this set changed as a result of the call.
   */
  def removeAll(coll: IntHashSet): Boolean =
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
   * Alias for `retainAll(Collection)` for the specialized case when retaining on another IntHashSet,
   * avoids boxing and allocations.
   *
   * @param coll containing elements to be retained in this set.
   * @return `true` if this set changed as a result of the call.
   */
  def retainAll(coll: IntHashSet): Boolean =
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
  override def iterator(): IntIterator =
  {
    var iterator = this._iterator
    if (null == iterator)
    {
      iterator = new IntIterator()
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
  def forEachInt(action: IntConsumer): Unit =
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
   * Copy values from another `IntHashSet` into this one.
   *
   * @param that set to copy values from.
   */
  def copy(that: IntHashSet): Unit =
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
    if (!componentType.isAssignableFrom(classOf[Integer]))
    {
      throw new ArrayStoreException("cannot store Integers in array of type " + componentType)
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
        arrayCopy(i) = Integer.valueOf(value)
        i += 1
      }
      vi += 1
    }

    if (containsMissingValue)
    {
      arrayCopy(sizeOfArrayValues) = Integer.valueOf(MISSING_VALUE)
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
      case otherSet: IntHashSet =>
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
        hashCode += Integer.hashCode(value)
      }
      i += 1
    }

    if (containsMissingValue)
    {
      hashCode += Integer.hashCode(MISSING_VALUE)
    }

    hashCode
  }

  /**
   * Iterator which supports unboxed access to the values via `nextValue()`.
   */
  final class IntIterator private[IntHashSet] () extends java.util.Iterator[Integer]
  {
    @DoNotSub private var remainingCount: Int = 0
    @DoNotSub private var positionCounter: Int = 0
    @DoNotSub private var stopCounter: Int = 0
    private var isPositionValid: Boolean = false

    private[IntHashSet] def reset(): IntIterator =
    {
      remainingCount = size()

      val values = IntHashSet.this.values
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
    override def next(): Integer = nextValue()

    /**
     * Strongly typed alternative of `Iterator#next()` to avoid boxing.
     *
     * @return the next int value.
     */
    def nextValue(): Int =
    {
      if (remainingCount == 1 && containsMissingValue)
      {
        remainingCount = 0
        isPositionValid = true

        return MISSING_VALUE
      }

      findNext()

      val values = IntHashSet.this.values

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
          val values = IntHashSet.this.values
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
      val values = IntHashSet.this.values
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

    @DoNotSub private def position(values: Array[Int]): Int =
    {
      positionCounter & (values.length - 1)
    }
  }
}
