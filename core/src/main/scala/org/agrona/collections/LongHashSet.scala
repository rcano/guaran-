/*
 * Copyright 2014-2021 Real Logic Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.agrona.collections

import java.util.*

import org.agrona.BitUtil.findNextPositivePowerOfTwo
import scala.annotation.static
import scala.compiletime.uninitialized

object LongHashSet {

  /** The initial capacity used when none is specified in the constructor.
    */
  val DEFAULT_INITIAL_CAPACITY = 8

  private[LongHashSet] val MISSING_VALUE: Long = -1

  @static private def next(index: Int, mask: Int): Int = {
    return (index + 1) & mask
  }
}

/** Open-addressing with linear-probing expandable hash set. Allocation free in steady state use when expanded. <p> By storing elements as
  * long primitives this significantly reduces memory consumption compared with Java's builtin <code>HashSet&ltLong&gt</code>. It
  * implements <code>Set&ltLong&gt</code> for convenience, but calling functionality via those methods can add boxing overhead to your
  * usage. <p> This class is not Threadsafe. <p> This HashSet caches its iterator object by default, so nested iteration is not supported.
  * You can override this behaviour at construction by indicating that the iterator should not be cached.
  *
  * @see
  *   LongIterator
  * @see
  *   Set
  *
  * @constructor
  *
  * Construct a hash set with a proposed initial capacity, load factor, and indicated iterator caching support.
  *
  * @param proposedCapacity
  *   for the initial capacity of the set.
  * @param loadFactor
  *   to be used for resizing.
  * @param shouldAvoidAllocation
  *   should the iterator be cached to avoid further allocation.
  */
@SerialVersionUID(8717061229749503234L)
class LongHashSet(
    val proposedCapacity: Int = LongHashSet.DEFAULT_INITIAL_CAPACITY,
    val loadFactor: Float = Hashing.DEFAULT_LOAD_FACTOR,
    val shouldAvoidAllocation: Boolean = true
) extends java.util.AbstractSet[java.lang.Long],
      java.io.Serializable {
  import LongHashSet.*

  private[LongHashSet] var containsMissingValue = false
  private var _resizeThreshold: Long = uninitialized

  // {
  //   (capacity * loadFactor).toInt
  // }
  // NB: excludes missing value
  private var sizeOfArrayValues = 0

  private[LongHashSet] var values: Array[Long] = uninitialized
  private var _iterator: LongIterator | Null = null

  {
    val capacity = findNextPositivePowerOfTwo(Math.max(DEFAULT_INITIAL_CAPACITY, proposedCapacity))
    _resizeThreshold = (capacity * loadFactor).toInt
    values = Array.fill(capacity)(MISSING_VALUE)
  }

  /** Get the total capacity for the set to which the load factor with be a fraction of.
    *
    * @return
    *   the total capacity for the set.
    */
  def capacity: Long = values.length

  /** Get the actual threshold which when reached the map will resize. This is a function of the current capacity and load factor.
    *
    * @return
    *   the threshold when the map will resize.
    */
  def resizeThreshold: Long = _resizeThreshold

  /** {@inheritDoc}
    */
  override def add(value: java.lang.Long): Boolean = add(value.longValue)

  /** Primitive specialised overload of {this#add(Long)}.
    *
    * @param value
    *   the value to add.
    * @return
    *   true if the collection has changed, false otherwise.
    * @throws IllegalArgumentException
    *   if value is missingValue.
    */
  def add(value: Long): Boolean = {
    if (value == MISSING_VALUE)
    {
      val previousContainsMissingValue = this.containsMissingValue
      containsMissingValue = true
      return !previousContainsMissingValue
    }

    val mask = values.length - 1
    var index = Hashing.hash(value, mask)

    while (values(index) != MISSING_VALUE) {
      if (values(index) == value)
      {
        return false
      }

      index = next(index, mask)
    }

    values(index) = value
    sizeOfArrayValues += 1

    if (sizeOfArrayValues > resizeThreshold) {
      increaseCapacity()
    }

    return true
  }

  private def increaseCapacity(): Unit = {
    val newCapacity = values.length * 2
    if (newCapacity < 0)
    {
      throw new IllegalStateException("max capacity reached at size=" + size())
    }

    rehash(newCapacity)
  }

  private def rehash(newCapacity: Int): Unit = {
    val capacity = newCapacity
    val mask = newCapacity - 1
    _resizeThreshold = (newCapacity * loadFactor).toInt // @DoNotSub

    val tempValues = Array.fill(capacity)(MISSING_VALUE)

    {
      var i = -1
      while ({ i += 1; i < values.length }) {
        val value = values(i)
        if (value != MISSING_VALUE) {
          var newHash = Hashing.hash(value, mask)
          while (tempValues(newHash) != MISSING_VALUE)
          {
            newHash += 1
            newHash = newHash & mask
          }

          tempValues(newHash) = value
        }
      }
    }

    values = tempValues
  }

  /** {@inheritDoc}
    */
  override def remove(value: Object): Boolean = {
    return value.isInstanceOf[java.lang.Long] && remove(value.asInstanceOf[java.lang.Long].longValue())
  }

  /** Specialised version of {this#remove(Object)} for long.
    *
    * @param value
    *   the value to remove.
    * @return
    *   true if the value was present, false otherwise.
    */
  def remove(value: Long): Boolean = {
    if (value == MISSING_VALUE)
    {
      val previousContainsMissingValue = this.containsMissingValue
      containsMissingValue = false
      return previousContainsMissingValue
    }

    val mask = values.length - 1
    var index = Hashing.hash(value, mask)

    while (values(index) != MISSING_VALUE) {
      if (values(index) == value)
      {
        values(index) = MISSING_VALUE
        compactChain(index)
        sizeOfArrayValues -= 1
        return true
      }

      index = next(index, mask)
    }

    return false
  }

  def compactChain(deleteIndex0: Int): Unit = {
    var deleteIndex = deleteIndex0
    val mask = values.length - 1

    var index = deleteIndex
    while (true) {
      index = next(index, mask)
      if (values(index) == MISSING_VALUE)
      {
        return
      }

      val hash = Hashing.hash(values(index), mask)

      if (
        (index < hash && (hash <= deleteIndex || deleteIndex <= index)) ||
        (hash <= deleteIndex && deleteIndex <= index)
      ) {
        values(deleteIndex) = values(index)

        values(index) = MISSING_VALUE
        deleteIndex = index
      }
    }
  }

  /** Compact the backing arrays by rehashing with a capacity just larger than current size and giving consideration to the load factor.
    */
  def compact(): Unit = {
    val idealCapacity = Math.round(size() * (1.0 / loadFactor)).toInt
    rehash(findNextPositivePowerOfTwo(Math.max(DEFAULT_INITIAL_CAPACITY, idealCapacity)))
  }

  /** {@inheritDoc}
    */
  override def contains(value: Object): Boolean = value match
    case i: java.lang.Long => contains(i.longValue)
    case _ => false

  /** Contains method that does not box values.
    *
    * @param value
    *   to be checked for if the set contains it.
    * @return
    *   true if the value is contained in the set otherwise false.
    * @see
    *   Collection#contains(Object)
    */
  def contains(value: Long): Boolean = {
    if (value == MISSING_VALUE)
    {
      return containsMissingValue
    }

    val mask = values.length - 1
    var index = Hashing.hash(value, mask)

    while (values(index) != MISSING_VALUE) {
      if (values(index) == value)
      {
        return true
      }

      index = next(index, mask)
    }

    return false
  }

  /** {@inheritDoc}
    */
  override def size(): Int = {
    return sizeOfArrayValues + (if containsMissingValue then 1 else 0)
  }

  /** {@inheritDoc}
    */
  override def isEmpty(): Boolean = {
    return size() == 0
  }

  /** {@inheritDoc}
    */
  override def clear(): Unit = {
    if (size() > 0) {
      Arrays.fill(values, MISSING_VALUE)
      sizeOfArrayValues = 0
      containsMissingValue = false
    }
  }

  /** {@inheritDoc}
    */
  override def addAll(coll: java.util.Collection[? <: java.lang.Long]): Boolean = {
    var added = false

    val it = coll.iterator().nn
    while (it.hasNext()) {
      val value = it.next().nn
      added |= add(value)
    }

    return added
  }

  /** Alias for {@link #addAll(Collection)} for the specialized case when adding another LongHashSet, avoids boxing and allocations.
    *
    * @param coll
    *   containing the values to be added.
    * @return
    *   {@code true} if this set changed as a result of the call.
    */
  def addAll(coll: LongHashSet): Boolean = {
    var acc = false

    var i = -1
    while ({ i += 1; i < coll.values.length }) {
      val value = coll.values(i)
      if (value != MISSING_VALUE) {
        acc |= add(value)
      }
    }

    if (coll.containsMissingValue)
    {
      acc |= add(MISSING_VALUE)
    }

    return acc
  }

  /** LongHashSet specialised variant of {this#containsAll(Collection)}.
    *
    * @param other
    *   long hash set to compare against.
    * @return
    *   true if every element in other is in this.
    */
  def containsAll(other: LongHashSet): Boolean = {
    var i = -1
    while ({ i += 1; i < other.values.length }) {
      val value = other.values(i)
      if (value != MISSING_VALUE && !contains(value)) {
        return false
      }
    }

    return !other.containsMissingValue || this.containsMissingValue
  }

  /** Fast Path set difference for comparison with another LongHashSet. <p> <b>Note:</b> garbage free in the identical case, allocates
    * otherwise.
    *
    * @param other
    *   the other set to subtract.
    * @return
    *   null if identical, otherwise the set of differences.
    */
  def difference(other: LongHashSet): LongHashSet = {
    var difference: LongHashSet | Null = null

    var i = -1
    while ({ i += 1; i < values.length }) {
      val value = values(i)
      if (value != MISSING_VALUE && !other.contains(value)) {
        if (difference == null)
        {
          difference = new LongHashSet()
        }

        difference.add(value)
      }
    }

    if (other.containsMissingValue && !this.containsMissingValue)
    {
      if (difference == null)
      {
        difference = new LongHashSet()
      }

      difference.add(MISSING_VALUE)
    }

    return difference.asInstanceOf[LongHashSet]
  }

  /** {@inheritDoc}
    */
  override def removeAll(coll: java.util.Collection[?]): Boolean = {
    var removed = false

    val it = coll.iterator.nn
    while (it.hasNext()) {
      removed |= remove(it.next().nn)
    }

    return removed
  }

  /** Alias for {@link #removeAll(Collection)} for the specialized case when removing another LongHashSet, avoids boxing and allocations
    *
    * @param coll
    *   containing the values to be removed.
    * @return
    *   {@code true} if this set changed as a result of the call.
    */
  def removeAll(coll: LongHashSet): Boolean = {
    var acc = false

    var i = -1
    while ({ i += 1; i < coll.values.length }) {
      val value = coll.values(i)
      if (value != MISSING_VALUE) {
        acc |= remove(value)
      }
    }

    if (coll.containsMissingValue)
    {
      acc |= remove(MISSING_VALUE)
    }

    return acc
  }

  /** {@inheritDoc}
    */
  override def iterator(): LongIterator = {
    var iterator = this._iterator
    if (null == iterator)
    {
      iterator = new LongIterator()
      if (shouldAvoidAllocation) {
        this._iterator = iterator
      }
    }

    return iterator.reset()
  }

  /** Copye values from another {@link LongHashSet} longo this one.
    *
    * @param that
    *   set to copy values from.
    */
  def copy(that: LongHashSet): Unit = {
    if (this.values.length != that.values.length)
    {
      throw new IllegalArgumentException("cannot copy object: masks not equal")
    }

    System.arraycopy(that.values, 0, this.values, 0, this.values.length)
    this.sizeOfArrayValues = that.sizeOfArrayValues
    this.containsMissingValue = that.containsMissingValue
  }

  /** {@inheritDoc}
    */
  override def toString(): String = {
    val sb = new StringBuilder()
    sb.append('{')

    for (value <- values)
    {
      if (value != MISSING_VALUE) {
        sb.append(value).append(", ")
      }
    }

    if (containsMissingValue) {
      sb.append(MISSING_VALUE).append(", ")
    }

    if (sb.length() > 1) {
      sb.setLength(sb.length() - 2)
    }

    sb.append('}')

    return sb.toString()
  }

  /** {@inheritDoc}
    */
  def toArray[T](a: Array[T]): Array[T] = {
    val componentType = a.getClass().getComponentType().nn
    if (!componentType.isAssignableFrom(classOf[Long]))
    {
      throw new ArrayStoreException("cannot store Longs in array of type " + componentType)
    }

    val _size = size()
    val arrayCopy = if a.length >= _size then a else Array.copyOf(a, _size)
    copyValues(arrayCopy.asInstanceOf[Array[Object]])

    return arrayCopy
  }

  /** {@inheritDoc}
    */
  override def toArray(): Array[Object] = {
    val arrayCopy = new Array[Object](size())
    copyValues(arrayCopy)

    return arrayCopy
  }

  private def copyValues(arrayCopy: Array[Object]): Unit = {
    var i = 0
    val _values = this.values
    for (value <- _values)
    {
      if (MISSING_VALUE != value) {
        i += 1
        arrayCopy(i) = value: java.lang.Long
      }
    }

    if (containsMissingValue) {
      arrayCopy(sizeOfArrayValues) = MISSING_VALUE: java.lang.Long
    }
  }

  /** {@inheritDoc}
    */
  override def equals(other: Any): Boolean = {
    if (other == this)
    {
      return true
    }

    other match {
      case otherSet: LongHashSet =>
        return otherSet.containsMissingValue == containsMissingValue &&
          otherSet.sizeOfArrayValues == sizeOfArrayValues &&
          containsAll(otherSet)

      case c: java.util.Set[?] =>
        if (c.size != size) return false

        try return containsAll(c)
        catch case _: (ClassCastException | NullPointerException) => false

      case _ => false
    }
  }

  /** {@inheritDoc}
    */
  override def hashCode(): Int = {
    var hashCode = 0
    var i = -1
    while ({ i += 1; i < values.length }) {
      val value = values(i)
      if (value != MISSING_VALUE) {
        hashCode += java.lang.Long.hashCode(value)
      }
    }

    if (containsMissingValue)
    {
      hashCode += java.lang.Long.hashCode(MISSING_VALUE)
    }

    return hashCode
  }

  /** Iterator which supports unboxed access to the values via {@link #nextValue()}.
    */
  @SerialVersionUID(351844349377836408L)
  final class LongIterator extends java.util.Iterator[java.lang.Long], java.io.Serializable {
    private var _remaining: Int = uninitialized
    private var positionCounter: Int = uninitialized
    private var stopCounter: Int = uninitialized
    private var isPositionValid = false

    def reset(): LongIterator = {
      _remaining = size()

      val values = LongHashSet.this.values
      val length = values.length
      var i = length

      if (values(length - 1) != LongHashSet.MISSING_VALUE)
      {
        var i = -1
        while ({ i += 1; i < length && values(i) != LongHashSet.MISSING_VALUE }) ()
      }

      stopCounter = i
      positionCounter = i + length
      isPositionValid = false

      return this
    }

    /** {@inheritDoc}
      */
    def hasNext(): Boolean = {
      return _remaining > 0
    }

    /** Returns number of remaining (not yet visited) elements.
      *
      * @return
      *   number of remaining elements.
      */
    def remaining(): Long = {
      return _remaining
    }

    /** {@inheritDoc}
      */
    def next(): java.lang.Long = {
      return nextValue()
    }

    /** Strongly typed alternative of {@link Iterator#next()} to avoid boxing.
      *
      * @return
      *   the next long value.
      */
    def nextValue(): Long = {
      if (_remaining == 1 && containsMissingValue)
      {
        _remaining = 0
        isPositionValid = true

        return LongHashSet.MISSING_VALUE
      }

      findNext()

      val values = LongHashSet.this.values

      return values(position(values))
    }

    /** {@inheritDoc}
      */
    override def remove(): Unit = {
      if (isPositionValid) {
        if (0 == _remaining && containsMissingValue) {
          containsMissingValue = false
        } else {
          val values = LongHashSet.this.values
          val _position = position(values)
          values(_position) = MISSING_VALUE
          sizeOfArrayValues -= 1

          compactChain(_position)
        }

        isPositionValid = false
      } else {
        throw new IllegalStateException()
      }
    }

    def findNext(): Unit = {
      val values = LongHashSet.this.values
      val mask = values.length - 1
      isPositionValid = true

      var i = positionCounter
      while ({ i -= 1; i >= stopCounter })
      {
        val index = i & mask
        if (values(index) != LongHashSet.MISSING_VALUE) {
          positionCounter = i
          _remaining -= 1
          return
        }
      }

      isPositionValid = false
      throw new NoSuchElementException()
    }

    def position(values: Array[Long]): Int = {
      return positionCounter & (values.length - 1)
    }
  }

}