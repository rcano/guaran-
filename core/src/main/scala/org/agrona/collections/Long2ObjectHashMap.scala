/*
 * Copyright 2014-2021 Real Logic Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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

import java.io.Serializable
import java.util.*
import java.util.function.Consumer
import java.util.function.LongFunction

import java.util.Objects.requireNonNull
import org.agrona.BitUtil.findNextPositivePowerOfTwo
import org.agrona.collections.CollectionUtil.validateLoadFactor

import scala.compiletime.uninitialized

object Long2ObjectHashMap {
  val MIN_CAPACITY = 8

  def apply[V <: Object | Null](mapToCopy: Long2ObjectHashMap[V]): Long2ObjectHashMap[V] = {
    val res = new Long2ObjectHashMap[V](mapToCopy.capacity(), mapToCopy.loadFactor, mapToCopy.shouldAvoidAllocation)
    System.arraycopy(mapToCopy._values, 0, res._values, 0, mapToCopy._values.length)
    res
  }
}

/** {@link java.util.Map} implementation specialised for long keys using open addressing and linear probing for cache efficient access.
  *
  * @param [V]
  *   type of values stored in the {@link java.util.Map}
  *
  * Construct a new map allowing a configuration for initial capacity and load factor.
  *
  * @param initialCapacity
  *   for the backing array
  * @param loadFactor
  *   limit for resizing on puts
  * @param shouldAvoidAllocation
  *   should allocation be avoided by caching iterators and map entries.
  */
@SerialVersionUID(-87577678740521569L)
class Long2ObjectHashMap[V <: Object | Null](
    initialCapacity: Int = Long2ObjectHashMap.MIN_CAPACITY,
    val loadFactor: Float = Hashing.DEFAULT_LOAD_FACTOR,
    val shouldAvoidAllocation: Boolean = true,
) extends Map[java.lang.Long, V],
      Serializable {

  private var _resizeThreshold: Int = uninitialized
  private var _size: Int = 0

  private var keys: Array[Long] = uninitialized
  private[Long2ObjectHashMap] var _values: Array[Object | Null] = uninitialized

  private var _valueCollection: ValueCollection | Null = null
  private var _keySet: KeySet | Null = null
  private var _entrySet: EntrySet | Null = null

  {
    validateLoadFactor(loadFactor)

    val capacity = findNextPositivePowerOfTwo(Math.max(Long2ObjectHashMap.MIN_CAPACITY, initialCapacity))
    _resizeThreshold = (capacity * loadFactor).toInt

    keys = new Array[Long](capacity)
    _values = new Array[Object | Null](capacity)
  }

  /** Get the total capacity for the map to which the load factor will be a fraction of.
    *
    * @return
    *   the total capacity for the map.
    */
  def capacity(): Int = {
    return _values.length
  }

  /** Get the actual threshold which when reached the map will resize. This is a function of the current capacity and load factor.
    *
    * @return
    *   the threshold when the map will resize.
    */
  def resizeThreshold(): Int = {
    return _resizeThreshold
  }

  /** {@inheritDoc}
    */
  def size(): Int = {
    return _size
  }

  /** {@inheritDoc}
    */
  def isEmpty(): Boolean = {
    return 0 == _size
  }

  /** {@inheritDoc}
    */
  def containsKey(key: Object): Boolean = {
    return containsKey(key.asInstanceOf[java.lang.Long].longValue())
  }

  /** Overloaded version of {@link Map#containsKey(Object)} that takes a primitive long key.
    *
    * @param key
    *   for indexing the {@link Map}
    * @return
    *   true if the key is found otherwise false.
    */
  def containsKey(key: Long): Boolean = {
    val mask = _values.length - 1
    var index = Hashing.hash(key, mask)

    var found = false
    while (!found && null != _values(index))
    {
      if (key == keys(index))
      {
        found = true
      }

      index = (index + 1) & mask
    }

    return found
  }

  /** {@inheritDoc}
    */
  def containsValue(value: Object): Boolean = {
    var found = false
    val newVal = mapNullValue(value)

    if (null != newVal)
    {
      var remaining = Long2ObjectHashMap.this.size

      var i = -1
      var length = _values.length
      while ({ i += 1; !found && remaining > 0 && i < length }) {
        if (null != _values(i)) {
          if (newVal.equals(_values(i)))
          {
            found = true
          }
          remaining -= 1
        }
      }
    }

    return found
  }

  /** {@inheritDoc}
    */
  def get(key: Object): V | Null = {
    return get(key.asInstanceOf[java.lang.Long].longValue())
  }

  /** Overloaded version of {@link Map#get(Object)} that takes a primitive long key.
    *
    * @param key
    *   for indexing the {@link Map}
    * @return
    *   the value if found otherwise null
    */
  def get(key: Long): V | Null = {
    return unmapNullValue(getMapped(key))
  }

  /** Get mapped value without boxing the key.
    *
    * @param key
    *   to get value by.
    * @return
    *   mapped value or {@code null}.
    */
  protected def getMapped(key: Long): V | Null = {
    val mask = _values.length - 1
    var index = Hashing.hash(key, mask)

    var value: Object | Null = null
    while ({ value = _values(index); null != value && key != keys(index) })
    {
      index = (index + 1) & mask
    }

    return value.asInstanceOf[V]
  }

  /** Get a value for a given key, or if it does not exist then default the value via a {@link java.util.function.LongFunction} and put it
    * in the map. <p> Primitive specialized version of {@link java.util.Map#computeIfAbsent}.
    *
    * @param key
    *   to search on.
    * @param mappingFunction
    *   to provide a value if the get returns null.
    * @return
    *   the value if found otherwise the default.
    */
  def computeIfAbsent(key: Long, mappingFunction: LongFunction[? <: V]): V = {
    var value = getMapped(key)
    if (value == null) {
      value = mappingFunction.apply(key)
      if (value != null) {
        put(key, value)
      }
    } else {
      value = unmapNullValue(value)
    }

    return value.asInstanceOf[V]
  }

  /** {@inheritDoc}
    */
  def put(key: java.lang.Long | Null, value: V | Null): V | Null = {
    return put(key.nn.longValue(), value)
  }

  /** Overloaded version of {@link Map#put(Object, Object)} that takes a primitive long key.
    *
    * @param key
    *   for indexing the {@link Map}
    * @param value
    *   to be inserted in the {@link Map}
    * @return
    *   the previous value if found otherwise null
    */
  def put(key: Long, value: V | Null): V | Null = {
    val newVal = mapNullValue(value).asInstanceOf[V | Null]
    requireNonNull(newVal, "value cannot be null")

    var oldValue: V | Null = null
    val mask = _values.length - 1
    var index = Hashing.hash(key, mask)

    {
      var found = false
      while (!found && null != _values(index)) {
        if (key == keys(index)) {
          oldValue = _values(index).asInstanceOf[V | Null]
          found = true
        } else {
          index = (index + 1) & mask
        }

      }
    }

    if (null == oldValue)
    {
      _size += 1
      keys(index) = key
    }

    _values(index) = newVal

    if (_size > _resizeThreshold) {
      increaseCapacity()
    }

    return unmapNullValue(oldValue)
  }

  /** {@inheritDoc}
    */
  def remove(key: Object): V | Null = {
    return remove(key.asInstanceOf[java.lang.Long].longValue())
  }

  /** Overloaded version of {@link Map#remove(Object)} that takes a primitive long key.
    *
    * @param key
    *   for indexing the {@link Map}
    * @return
    *   the value if found otherwise null
    */
  def remove(key: Long): V | Null = {
    val mask = _values.length - 1
    var index = Hashing.hash(key, mask)

    var value: Object | Null = null
    var found = false
    while ({ value = _values(index); !found && null != value })
    {
      if (key == keys(index)) {
        _values(index) = null
        _size -= 1

        compactChain(index)
        found = true
      } else {
        index = (index + 1) & mask
      }

    }

    return unmapNullValue(value.asInstanceOf[V | Null])
  }

  /** {@inheritDoc}
    */
  def clear(): Unit = {
    if (_size > 0) {
      Arrays.fill(_values, null)
      _size = 0
    }
  }

  /** Compact the {@link Map} backing arrays by rehashing with a capacity just larger than current size and giving consideration to the load
    * factor.
    */
  def compact(): Unit = {
    val idealCapacity = Math.round(size() * (1.0d / loadFactor)).toInt
    rehash(findNextPositivePowerOfTwo(Math.max(Long2ObjectHashMap.MIN_CAPACITY, idealCapacity)))
  }

  /** {@inheritDoc}
    */
  def putAll(map: java.util.Map[? <: java.lang.Long, ? <: V]): Unit = {
    val it = map.entrySet().nn.iterator().nn
    while (it.hasNext()) {
      val entry = it.next().asInstanceOf[java.util.Map.Entry[? <: java.lang.Long, ? <: V]]
      put(entry.getKey().asInstanceOf[java.lang.Long].longValue(), entry.getValue().asInstanceOf[V])
    }
  }

  /** {@inheritDoc}
    */
  def keySet(): KeySet = {
    if (null == _keySet)
    {
      _keySet = new KeySet()
    }

    return _keySet.asInstanceOf[KeySet]
  }

  /** {@inheritDoc}
    */
  def values(): ValueCollection = {
    if (null == _valueCollection)
    {
      _valueCollection = new ValueCollection()
    }

    return _valueCollection.asInstanceOf[ValueCollection]
  }

  /** {@inheritDoc}
    */
  def entrySet(): EntrySet = {
    if (null == _entrySet)
    {
      _entrySet = new EntrySet()
    }

    return _entrySet.asInstanceOf[EntrySet]
  }

  /** {@inheritDoc}
    */
  override def toString(): String = {
    if (isEmpty())
    {
      return "{}"
    }

    val entryIterator = new EntryIterator()
    entryIterator.reset()

    val sb = new StringBuilder().append('{')
    while (true) {
      entryIterator.next()
      sb.append(entryIterator.getLongKey()).append('=').append(unmapNullValue(entryIterator.getValue()))
      if (!entryIterator.hasNext())
      {
        return sb.append('}').toString()
      }
      sb.append(',').append(' ')
    }
    throw IllegalStateException("Unreachable code")
  }

  /** {@inheritDoc}
    */
  override def equals(o: Any): Boolean = {
    if (this == o)
    {
      return true
    }

    o match {
      case that: java.util.Map[?, ?] =>
        if (size != that.size())
        {
          return false
        }

        var i = -1
        val length = _values.length
        while ({ i += 1; i < length }) {
          val thisValue = _values(i)
          if (null != thisValue) {
            val thatValue = that.get(keys(i))
            if (!thisValue.equals(mapNullValue(thatValue))) {
              return false
            }
          }
        }
        return true

      case _ => false
    }
  }

  /** {@inheritDoc}
    */
  override def hashCode(): Int = {
    var result = 0
    var i = -1
    val length = _values.length
    while ({ i += 1; i < length })
    {
      val value = _values(i)
      if (null != value) {
        result += (java.lang.Long.hashCode(keys(i)) ^ value.hashCode())
      }
    }

    return result
  }

  /** Interceptor for masking null values.
    *
    * @param value
    *   value to mask.
    * @return
    *   masked value.
    */
  protected def mapNullValue(value: Object | Null): Object | Null = {
    return value
  }

  /** Interceptor for unmasking null values.
    *
    * @param value
    *   value to unmask.
    * @return
    *   unmasked value.
    */
  protected def unmapNullValue(value: V | Null): V | Null = {
    return value
  }

  /** Primitive specialised version of {@link #replace(Object, Object)}
    *
    * @param key
    *   key with which the specified value is associated
    * @param value
    *   value to be associated with the specified key
    * @return
    *   the previous value associated with the specified key, or {@code null} if there was no mapping for the key.
    */
  def replace(key: Long, value: V): V | Null = {
    var currentValue = get(key)
    if (currentValue != null)
    {
      currentValue = put(key, value)
    }

    return currentValue
  }

  /** Primitive specialised version of {@link #replace(Object, Object, Object)}
    *
    * @param key
    *   key with which the specified value is associated
    * @param oldValue
    *   value expected to be associated with the specified key
    * @param newValue
    *   value to be associated with the specified key
    * @return
    *   {@code true} if the value was replaced
    */
  def replace(key: Long, oldValue: V, newValue: V): Boolean = {
    val curValue = get(key)
    if (curValue == null || !Objects.equals(unmapNullValue(curValue), oldValue))
    {
      return false
    }

    put(key, newValue)

    return true
  }

  private def increaseCapacity(): Unit = {
    val newCapacity = _values.length << 1
    if (newCapacity < 0)
    {
      throw new IllegalStateException("max capacity reached at size=" + size)
    }

    rehash(newCapacity)
  }

  private def rehash(newCapacity: Int): Unit = {
    val mask = newCapacity - 1
    /* @DoNotSub */
    _resizeThreshold = (newCapacity * loadFactor).toInt

    val tempKeys = new Array[Long](newCapacity)
    val tempValues = new Array[Object | Null](newCapacity)

    var i = -1
    _size = _values.length
    while ({ i += 1; i < _size })
    {
      val value = _values(i)
      if (null != value) {
        val key = keys(i)
        var index = Hashing.hash(key, mask)
        while (null != tempValues(index))
        {
          index = (index + 1) & mask
        }

        tempKeys(index) = key
        tempValues(index) = value
      }
    }

    keys = tempKeys
    _values = tempValues
  }

  private def compactChain(deleteIndex0: Int): Unit = {
    var deleteIndex = deleteIndex0
    val mask = _values.length - 1
    var index = deleteIndex
    var break = false
    while (!break) {
      index = (index + 1) & mask
      if (null != _values(index))
      {
          val hash = Hashing.hash(keys(index), mask)
    
          if (
            (index < hash && (hash <= deleteIndex || deleteIndex <= index)) ||
            (hash <= deleteIndex && deleteIndex <= index)
          ) {
            keys(deleteIndex) = keys(index)
            _values(deleteIndex) = _values(index)
    
            _values(index) = null
            deleteIndex = index
          }
      } else {
          break = true
      }

    }
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////
  // Sets and Collections
  ///////////////////////////////////////////////////////////////////////////////////////////////

  /** Set of keys which supports optionally cached iterators to avoid allocation.
    */
  @SerialVersionUID(174654887531298424L)
  final class KeySet extends AbstractSet[java.lang.Long], Serializable {
    private val keyIterator: KeyIterator | Null = if shouldAvoidAllocation then new KeyIterator() else null

    /** {@inheritDoc}
      */
    def iterator(): KeyIterator = {
      var keyIterator = if (null == this.keyIterator) new KeyIterator() else this.keyIterator

      keyIterator.reset()
      return keyIterator
    }

    /** {@inheritDoc}
      */
    def size(): Int = {
      return Long2ObjectHashMap.this.size()
    }

    /** {@inheritDoc}
      */
    override def contains(o: Object): Boolean = {
      return Long2ObjectHashMap.this.containsKey(o)
    }

    /** Checks if the key is contained in the map.
      *
      * @param key
      *   to check.
      * @return
      *   {@code true} if the key is contained in the map.
      */
    def contains(key: Long): Boolean = {
      return Long2ObjectHashMap.this.containsKey(key)
    }

    /** {@inheritDoc}
      */
    override def remove(o: Object): Boolean = {
      return null != Long2ObjectHashMap.this.remove(o)
    }

    /** Removes key and the corresponding value from the map.
      *
      * @param key
      *   to be removed.
      * @return
      *   {@code true} if the mapping was removed.
      */
    def remove(key: Long): Boolean = {
      return null != Long2ObjectHashMap.this.remove(key)
    }

    /** {@inheritDoc}
      */
    override def clear(): Unit = {
      Long2ObjectHashMap.this.clear()
    }
  }

  /** Collection of values which supports optionally cached iterators to avoid allocation.
    */
  @SerialVersionUID(6851282235497568109L)
  final class ValueCollection extends AbstractCollection[V], Serializable {
    private val valueIterator: ValueIterator | Null = if shouldAvoidAllocation then new ValueIterator() else null

    /** {@inheritDoc}
      */
    def iterator(): ValueIterator = {
      val valueIterator = if (null == this.valueIterator) new ValueIterator() else this.valueIterator

      valueIterator.reset()
      return valueIterator
    }

    /** {@inheritDoc}
      */
    def size(): Int = {
      return Long2ObjectHashMap.this.size()
    }

    /** {@inheritDoc}
      */
    override def contains(o: Object): Boolean = {
      return Long2ObjectHashMap.this.containsValue(o)
    }

    /** {@inheritDoc}
      */
    override def clear(): Unit = {
      Long2ObjectHashMap.this.clear()
    }

    /** {@inheritDoc}
      */
    override def forEach(action: Consumer[? >: V]): Unit = {
      var remaining = Long2ObjectHashMap.this.size

      var i = -1
      val length = _values.length
      while ({ i += 1; remaining > 0 && i < length }) {
        if (null != _values(i)) {
          action.accept(unmapNullValue(_values(i).asInstanceOf[V | Null]))
          remaining -= 1
        }
      }
    }
  }

  /** Set of entries which supports access via an optionally cached iterator to avoid allocation.
    */
  @SerialVersionUID(6797969139720339177L)
  final class EntrySet extends AbstractSet[java.util.Map.Entry[java.lang.Long, V]], Serializable {
    private val entryIterator = if shouldAvoidAllocation then new EntryIterator() else null

    /** {@inheritDoc}
      */
    def iterator(): EntryIterator = {
      val entryIterator = if (null == this.entryIterator) new EntryIterator() else this.entryIterator

      entryIterator.reset()
      return entryIterator
    }

    /** {@inheritDoc}
      */
    def size(): Int = {
      return Long2ObjectHashMap.this.size()
    }

    /** {@inheritDoc}
      */
    override def clear(): Unit = {
      Long2ObjectHashMap.this.clear()
    }

    /** {@inheritDoc}
      */
    override def contains(o: Object): Boolean = {
      if (!(o.isInstanceOf[java.util.Map.Entry[?, ?]]))
      {
        return false
      }

      val entry = o.asInstanceOf[java.util.Map.Entry[?, ?]]
      val key = entry.getKey().asInstanceOf[Long]
      val value = getMapped(key)
      return value != null && value.equals(mapNullValue(entry.getValue()))
    }

    /** {@inheritDoc}
      */
    override def toArray(): Array[Object] = {
      return toArray(new Array[Object](size()))
    }

    /** {@inheritDoc}
      */
    def toArray[T](a: Array[T]): Array[T] = {
      val array =
        if a.length >= size then a else java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size).asInstanceOf[Array[T]]
      val it = iterator()

      var i = -1
      while ({ i += 1; i < array.length && it.hasNext() })
      {
        it.next()
        array(i) = it.allocateDuplicateEntry().asInstanceOf[T]
      }
      if i < array.length then array.asInstanceOf[Array[T | Null]](i) = null

      return array
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////
  // Iterators
  ///////////////////////////////////////////////////////////////////////////////////////////////

  /** Base iterator implementation that contains basic logic of traversing the element in the backing array.
    *
    * @param [T]
    *   type of elements.
    */
  @SerialVersionUID(7955640333577513200L)
  abstract class AbstractIterator[T] extends Iterator[T], Serializable {
    private var posCounter: Int = 0
    private var stopCounter: Int = 0
    private var _remaining: Int = 0
    protected[AbstractIterator] var isPositionValid = false

    /** Position of the current element.
      *
      * @return
      *   position of the current element.
      */
    protected final def position(): Int = {
      return posCounter & (_values.length - 1)
    }

    /** Number of remaining elements.
      *
      * @return
      *   number of remaining elements.
      */
    def remaining(): Int = {
      return _remaining
    }

    /** {@inheritDoc}
      */
    def hasNext(): Boolean = {
      return _remaining > 0
    }

    /** Find the next element.
      *
      * @throws NoSuchElementException
      *   if no more elements.
      */
    protected final def findNext(): Unit = {
      if (!hasNext())
      {
        throw new NoSuchElementException()
      }

      val _values = Long2ObjectHashMap.this._values
      val mask = _values.length - 1

      var i = posCounter
      while ({ i -= 1; i >= stopCounter }) {
        val index = i & mask
        if (null != _values(index)) {
          posCounter = i
          isPositionValid = true
          _remaining -= 1
          return
        }
      }

      isPositionValid = false
      throw new IllegalStateException()
    }

    /** {@inheritDoc}
      */
    def next(): T

    /** {@inheritDoc}
      */
    override def remove(): Unit = {
      if (isPositionValid) {
        val pos = position()
        _values(pos) = null
        _size -= 1

        compactChain(pos)

        isPositionValid = false
      } else {
        throw new IllegalStateException()
      }
    }

    def reset(): Unit = {
      _remaining = Long2ObjectHashMap.this.size
      val _values = Long2ObjectHashMap.this._values
      val capacity = _values.length

      var i = capacity
      if (null != _values(capacity - 1))
      {
        i = -1
        while ({ i += 1; i < capacity && null != _values(i) }) ()
      }

      stopCounter = i
      posCounter = i + capacity
      isPositionValid = false
    }
  }

  /** Iterator over values.
    */
  @SerialVersionUID(-410109102792377049L)
  class ValueIterator extends AbstractIterator[V], Serializable {

    /** {@inheritDoc}
      */
    def next(): V = {
      findNext()

      return unmapNullValue(_values(position()).asInstanceOf[V | Null]).asInstanceOf[V]
    }
  }

  /** Iterator over keys which supports access to unboxed keys via {@link #nextLong()}.
    */
  @SerialVersionUID(-4905479491707153377L)
  class KeyIterator extends AbstractIterator[java.lang.Long], Serializable {

    /** {@inheritDoc}
      */
    def next(): java.lang.Long = {
      return nextLong()
    }

    /** Return next key without boxing.
      *
      * @return
      *   next key.
      */
    def nextLong(): Long = {
      findNext()

      return keys(position())
    }
  }

  /** Iterator over entries which supports access to unboxed keys via {@link #getLongKey()}.
    */
  @SerialVersionUID(2227334666048171527L)
  class EntryIterator
      extends AbstractIterator[java.util.Map.Entry[java.lang.Long, V]],
        java.util.Map.Entry[java.lang.Long, V],
        Serializable {

    /** {@inheritDoc}
      */
    def next(): java.util.Map.Entry[java.lang.Long, V] = {
      findNext()
      if (shouldAvoidAllocation)
      {
        return this
      }

      return allocateDuplicateEntry()
    }

    private[Long2ObjectHashMap] def allocateDuplicateEntry(): java.util.Map.Entry[java.lang.Long, V] = {
      return new MapEntry(getLongKey(), getValue())
    }

    /** {@inheritDoc}
      */
    def getKey(): java.lang.Long = {
      return getLongKey()
    }

    /** Get key without boxing.
      *
      * @return
      *   key.
      */
    def getLongKey(): Long = {
      return keys(position())
    }

    /** {@inheritDoc}
      */
    def getValue(): V = {
      return unmapNullValue(_values(position()).asInstanceOf[V | Null]).asInstanceOf[V]
    }

    /** {@inheritDoc}
      */
    def setValue(value: V | Null): V | Null = {
      val newVal = mapNullValue(value).asInstanceOf[V]
      requireNonNull(newVal, "value cannot be null")

      if (!this.isPositionValid)
      {
        throw new IllegalStateException()
      }

      val pos = position()
      val oldValue = _values(pos)
      _values(pos) = newVal

      return oldValue.asInstanceOf[V | Null]
    }

    /** An {@link java.util.Map.Entry} implementation.
      */
    @SerialVersionUID(-6648311124347304211L)
    final class MapEntry(private val k: Long, private val v: V) extends java.util.Map.Entry[java.lang.Long, V], Serializable {

      /** {@inheritDoc}
        */
      def getKey(): java.lang.Long = {
        return k
      }

      /** {@inheritDoc}
        */
      def getValue(): V = {
        return v
      }

      /** {@inheritDoc}
        */
      def setValue(value: V | Null): V | Null = {
        return Long2ObjectHashMap.this.put(k, value)
      }

      /** {@inheritDoc}
        */
      override def hashCode(): Int = {
        return java.lang.Long.hashCode(getLongKey()) ^ (v.hashCode())
      }

      /** {@inheritDoc}
        */
      override def equals(o: Any): Boolean = {
        if (!(o.isInstanceOf[java.util.Map.Entry[?, ?]]))
        {
          return false
        }

        val e = o.asInstanceOf[java.util.Map.Entry[?, ?]]

        return (e.getKey() != null && e.getKey().equals(k)) &&
          ((e.getValue() == null && v == null) || e.getValue().equals(v))
      }

      /** {@inheritDoc}
        */
      override def toString(): String = {
        return k + "=" + v
      }
    }
  }
}
