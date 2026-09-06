package org.agrona.collections

import language.unsafeNulls

import java.util.{AbstractCollection, AbstractSet, Arrays, NoSuchElementException, Objects}
import java.util.{Map => JMap}
import java.util.Map.{Entry => JEntry}
import java.util.function.{BiConsumer, BiFunction, Consumer, Function, LongFunction, LongPredicate}

import org.agrona.BitUtil.findNextPositivePowerOfTwo
import org.agrona.collections.CollectionUtil.validateLoadFactor
import org.agrona.generation.DoNotSub

/**
 * Literal port of `org.agrona.collections.Long2ObjectHashMap`.
 *
 * `java.util.Map` implementation specialised for int keys using open addressing and
 * linear probing for cache efficient access.
 *
 * NB on the port: several private backing fields in the Java original share their name
 * with a public zero-arg accessor method (`size`/`size()`, `loadFactor`/`loadFactor()`,
 * `resizeThreshold`/`resizeThreshold()`, `values`/`values()`, `keySet`/`keySet()`,
 * `entrySet`/`entrySet()`, and `remaining`/`remaining()` on the iterator base class).
 * Java allows this because fields and methods are in separate namespaces; Scala unifies
 * them, so those particular backing fields are stored under a renamed identifier here
 * while every public method keeps its exact Java name and signature.
 *
 * @tparam V type of values stored in the `java.util.Map`.
 */
class Long2ObjectHashMap[V](
  initialCapacity: Int = Long2ObjectHashMap.MIN_CAPACITY,
  loadFactorCtor: Float = Hashing.DEFAULT_LOAD_FACTOR,
  shouldAvoidAllocationCtor: Boolean = true)
  extends JMap[java.lang.Long, V]
{
  import Long2ObjectHashMap._

  private val _loadFactor: Float = loadFactorCtor
  @DoNotSub private var _resizeThreshold: Int = 0
  @DoNotSub private var _size: Int = 0
  private val shouldAvoidAllocation: Boolean = shouldAvoidAllocationCtor

  private var keys: Array[Long] = scala.compiletime.uninitialized
  private var valuesArray: Array[Object] = scala.compiletime.uninitialized

  private var valueCollection: ValueCollection = scala.compiletime.uninitialized
  private var _keySet: KeySet = scala.compiletime.uninitialized
  private var _entrySet: EntrySet = scala.compiletime.uninitialized

  validateLoadFactor(loadFactorCtor)

  {
    /* @DoNotSub */ val capacity = findNextPositivePowerOfTwo(Math.max(MIN_CAPACITY, initialCapacity))
    /* @DoNotSub */ _resizeThreshold = (capacity * loadFactorCtor).toInt

    keys = new Array[Long](capacity)
    valuesArray = new Array[Object](capacity)
  }

  /**
   * Copy construct a new map from an existing one.
   *
   * @param mapToCopy for construction.
   */
  def this(mapToCopy: Long2ObjectHashMap[V]) =
  {
    this(mapToCopy.keys.length, mapToCopy._loadFactor, mapToCopy.shouldAvoidAllocation)
    this._resizeThreshold = mapToCopy._resizeThreshold
    this._size = mapToCopy._size
    this.keys = mapToCopy.keys.clone()
    this.valuesArray = mapToCopy.valuesArray.clone()
  }

  /**
   * Get the load factor beyond which the map will increase size.
   *
   * @return load factor for when the map should increase size.
   */
  def loadFactor(): Float = _loadFactor

  /**
   * Get the total capacity for the map to which the load factor will be a fraction of.
   *
   * @return the total capacity for the map.
   */
  @DoNotSub def capacity(): Int = valuesArray.length

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
  @DoNotSub override def size(): Int = _size

  /**
   * {@inheritDoc}
   */
  override def isEmpty(): Boolean = 0 == _size

  /**
   * {@inheritDoc}
   */
  override def forEach(action: BiConsumer[? >: java.lang.Long, ? >: V]): Unit =
  {
    forEachLong(new LongObjConsumer[V]
    {
      override def accept(key: Long, value: V): Unit = action.accept(key, value)
    })
  }

  /**
   * Use `forEachLong(LongObjConsumer)` instead.
   *
   * @param consumer a callback called for each key/value pair in the map.
   * @see #forEachLong
   * @deprecated Use `forEachLong(LongObjConsumer)` instead.
   */
  @deprecated("Use forEachLong(LongObjConsumer) instead.", "port")
  def longForEach(consumer: LongObjConsumer[V]): Unit = forEachLong(consumer)

  /**
   * Primitive specialised implementation of `Map#forEach(BiConsumer)`.
   *
   * NB: Renamed from forEach to avoid overloading on parameter types of lambda
   * expression, which doesn't play well with type inference in lambda expressions.
   *
   * @param consumer a callback called for each key/value pair in the map.
   */
  def forEachLong(consumer: LongObjConsumer[V]): Unit =
  {
    Objects.requireNonNull(consumer)
    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val length = values.length

    @DoNotSub var index = 0
    @DoNotSub var remaining = _size
    while (remaining > 0 && index < length)
    {
      val value = values(index)
      if (null != value)
      {
        consumer.accept(keys(index), unmapNullValue(value))
        remaining -= 1
      }
      index += 1
    }
  }

  /**
   * {@inheritDoc}
   */
  override def containsKey(key: Any): Boolean = containsKey(key.asInstanceOf[java.lang.Long].longValue())

  /**
   * Overloaded version of `Map#containsKey(Object)` that takes a primitive int key.
   *
   * @param key for indexing the `Map`.
   * @return true if the key is found otherwise false.
   */
  def containsKey(key: Long): Boolean =
  {
    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(key, mask)

    var found = false
    while (null != values(index))
    {
      if (key == keys(index))
      {
        found = true
        return found
      }

      index = (index + 1) & mask
    }

    found
  }

  /**
   * {@inheritDoc}
   */
  override def containsValue(value: Any): Boolean =
  {
    var found = false
    val vObj: AnyRef = value.asInstanceOf[AnyRef]
    val v = mapNullValue(vObj)

    if (null != v)
    {
      val values = this.valuesArray
      @DoNotSub val length = values.length
      @DoNotSub var i = 0
      @DoNotSub var remaining = _size
      while (remaining > 0 && i < length)
      {
        val existingValue = values(i)
        if (null != existingValue)
        {
          if (Objects.equals(existingValue, v))
          {
            found = true
            return found
          }
          remaining -= 1
        }
        i += 1
      }
    }

    found
  }

  /**
   * {@inheritDoc}
   */
  override def get(key: Any): V = get(key.asInstanceOf[java.lang.Long].longValue())

  /**
   * Overloaded version of `Map#get(Object)` that takes a primitive int key.
   *
   * @param key for indexing the `Map`.
   * @return the value if found otherwise null.
   */
  def get(key: Long): V = unmapNullValue(getMapped(key))

  /**
   * Returns the value to which the specified key is mapped, or defaultValue if this map
   * contains no mapping for the key.
   *
   * @param key          whose associated value is to be returned.
   * @param defaultValue the default mapping of the key.
   * @return the value to which the specified key is mapped, or `defaultValue` if this
   *         map contains no mapping for the key.
   */
  def getOrDefault(key: Long, defaultValue: V): V =
  {
    val value = getMapped(key)
    if (null != value) unmapNullValue(value) else defaultValue
  }

  /**
   * Get mapped value without boxing the key.
   *
   * @param key to get value by.
   * @return mapped value or `null`.
   */
  protected def getMapped(key: Long): V =
  {
    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(key, mask)

    var value = values(index)
    while (null != value)
    {
      if (key == keys(index))
      {
        return value.asInstanceOf[V]
      }

      index = (index + 1) & mask
      value = values(index)
    }

    value.asInstanceOf[V]
  }

  /**
   * {@inheritDoc}
   */
  override def computeIfAbsent(key: java.lang.Long, mappingFunction: Function[? >: java.lang.Long, ? <: V]): V =
    computeIfAbsent(key.longValue(), new LongFunction[V]
    {
      override def apply(k: Long): V = mappingFunction.apply(k)
    })

  /**
   * Get a value for a given key, or if it does not exist then default the value via an
   * `LongFunction` and put it in the map.
   *
   * Primitive specialized version of `Map#computeIfAbsent(Object, Function)`.
   *
   * @param key             to search on.
   * @param mappingFunction to provide a value if the get returns null.
   * @return the value if found otherwise the default.
   */
  def computeIfAbsent(key: Long, mappingFunction: LongFunction[? <: V]): V =
  {
    Objects.requireNonNull(mappingFunction)
    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(key, mask)

    var mappedValue = values(index)
    while (null != mappedValue)
    {
      if (key == keys(index))
      {
        return finishComputeIfAbsent(keys, values, index, key, mappedValue, mappingFunction)
      }

      index = (index + 1) & mask
      mappedValue = values(index)
    }

    finishComputeIfAbsent(keys, values, index, key, mappedValue, mappingFunction)
  }

  // Java exits its `while` loop via `break` and falls through to shared code that uses
  // whatever `index`/`mappedValue` the loop stopped at (match or empty slot); this
  // private helper is that shared continuation, called from both loop-exit points below.
  private def finishComputeIfAbsent(
    keys: Array[Long],
    values: Array[Object],
    @DoNotSub index: Int,
    key: Long,
    mappedValueAtIndex: Object,
    mappingFunction: LongFunction[? <: V]): V =
  {
    var value = unmapNullValue(mappedValueAtIndex)

    if (null == value)
    {
      val computed = mappingFunction.apply(key)
      if (null != computed)
      {
        value = computed
        values(index) = value.asInstanceOf[Object]
        if (null == mappedValueAtIndex)
        {
          keys(index) = key
          _size += 1
          if (_size > _resizeThreshold)
          {
            increaseCapacity()
          }
        }
      }
    }

    value
  }

  /**
   * {@inheritDoc}
   */
  override def computeIfPresent(
    key: java.lang.Long, remappingFunction: BiFunction[? >: java.lang.Long, ? >: V, ? <: V]): V =
    computeIfPresent(key.longValue(), new LongObjectToObjectFunction[V, V]
    {
      override def apply(k: Long, value: V): V = remappingFunction.apply(k, value)
    })

  /**
   * If the value for the specified key is present and non-null, attempts to compute a
   * new mapping given the key and its current mapped value.
   *
   * If the function returns `null`, the mapping is removed.
   *
   * Primitive specialized version of `Map#computeIfPresent(Object, BiFunction)`.
   *
   * @param key               to search on.
   * @param remappingFunction to provide a value if the get returns missingValue.
   * @return the new value associated with the specified key, or `null` if none.
   */
  def computeIfPresent(
    key: Long, remappingFunction: LongObjectToObjectFunction[? >: V, ? <: V]): V =
  {
    Objects.requireNonNull(remappingFunction)
    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(key, mask)

    var mappedValue = values(index)
    while (null != mappedValue)
    {
      if (key == keys(index))
      {
        var value = unmapNullValue(mappedValue)
        if (null != value)
        {
          value = remappingFunction.apply(key, value)
          values(index) = value.asInstanceOf[Object]
          if (null == value)
          {
            _size -= 1
            compactChain(index)
          }
        }
        return value
      }

      index = (index + 1) & mask
      mappedValue = values(index)
    }

    unmapNullValue(mappedValue)
  }

  /**
   * {@inheritDoc}
   */
  override def compute(key: java.lang.Long, remappingFunction: BiFunction[? >: java.lang.Long, ? >: V, ? <: V]): V =
    compute(key.longValue(), new LongObjectToObjectFunction[V, V]
    {
      override def apply(k: Long, value: V): V = remappingFunction.apply(k, value)
    })

  /**
   * Attempts to compute a mapping for the specified key and its current mapped value
   * (or `null` if there is no current mapping).
   *
   * If the function returns `null`, the mapping is removed (or remains absent if
   * initially absent).
   *
   * Primitive specialized version of `Map#compute(Object, BiFunction)`.
   *
   * @param key               to search on.
   * @param remappingFunction to provide a value if the get returns missingValue.
   * @return the new value associated with the specified key, or `null` if none.
   */
  def compute(key: Long, remappingFunction: LongObjectToObjectFunction[? >: V, ? <: V]): V =
  {
    Objects.requireNonNull(remappingFunction)
    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(key, mask)

    var mappedvalue = values(index)
    while (null != mappedvalue)
    {
      if (key == keys(index))
      {
        return finishCompute(keys, values, mask, index, key, mappedvalue, remappingFunction)
      }
      index = (index + 1) & mask
      mappedvalue = values(index)
    }

    finishCompute(keys, values, mask, index, key, mappedvalue, remappingFunction)
  }

  private def finishCompute(
    keys: Array[Long],
    values: Array[Object],
    @DoNotSub mask: Int,
    @DoNotSub index: Int,
    key: Long,
    mappedvalue: Object,
    remappingFunction: LongObjectToObjectFunction[? >: V, ? <: V]): V =
  {
    val newValue = remappingFunction.apply(key, unmapNullValue(mappedvalue))
    if (null != newValue)
    {
      values(index) = newValue.asInstanceOf[Object]
      if (null == mappedvalue)
      {
        keys(index) = key
        _size += 1
        if (_size > _resizeThreshold)
        {
          increaseCapacity()
        }
      }
    }
    else if (null != mappedvalue)
    {
      values(index) = null
      _size -= 1
      compactChain(index)
    }

    newValue
  }

  /**
   * {@inheritDoc}
   */
  override def merge(key: java.lang.Long, value: V, remappingFunction: BiFunction[? >: V, ? >: V, ? <: V]): V =
    merge(key.longValue(), value, remappingFunction)

  /**
   * Primitive specialised version of `Map#merge(Object, Object, BiFunction)`.
   *
   * @param key               with which the resulting value is to be associated.
   * @param value             the non-null value to be merged with the existing value
   *                          associated with the key or, if no existing value or a null
   *                          value is associated with the key, to be associated with the
   *                          key.
   * @param remappingFunction the function to recompute a value if present.
   * @return the new value associated with the specified key, or null if no value is
   *         associated with the key.
   */
  def merge(key: Long, value: V, remappingFunction: BiFunction[? >: V, ? >: V, ? <: V]): V =
  {
    Objects.requireNonNull(value)
    Objects.requireNonNull(remappingFunction)
    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(key, mask)

    var mappedvalue = values(index)
    while (null != mappedvalue)
    {
      if (key == keys(index))
      {
        return finishMerge(keys, values, mask, index, key, value, mappedvalue, remappingFunction)
      }
      index = (index + 1) & mask
      mappedvalue = values(index)
    }

    finishMerge(keys, values, mask, index, key, value, mappedvalue, remappingFunction)
  }

  private def finishMerge(
    keys: Array[Long],
    values: Array[Object],
    @DoNotSub mask: Int,
    @DoNotSub index: Int,
    key: Long,
    value: V,
    mappedvalue: Object,
    remappingFunction: BiFunction[? >: V, ? >: V, ? <: V]): V =
  {
    val oldValue = unmapNullValue(mappedvalue)
    val newValue = if (null == oldValue) value else remappingFunction.apply(oldValue, value)

    if (null != newValue)
    {
      values(index) = newValue.asInstanceOf[Object]
      if (null == mappedvalue)
      {
        keys(index) = key
        _size += 1
        if (_size > _resizeThreshold)
        {
          increaseCapacity()
        }
      }
    }
    else if (null != mappedvalue)
    {
      values(index) = null
      _size -= 1
      compactChain(index)
    }

    newValue
  }

  /**
   * {@inheritDoc}
   */
  override def put(key: java.lang.Long, value: V): V = put(key.longValue(), value)

  /**
   * Overloaded version of `Map#put(Object, Object)` that takes a primitive int key.
   *
   * @param key   for indexing the `Map`.
   * @param value to be inserted in the `Map`.
   * @return the previous value if found otherwise null.
   */
  def put(key: Long, value: V): V =
  {
    val v = mapNullValue(value.asInstanceOf[AnyRef])
    Objects.requireNonNull(v, "value cannot be null")

    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(key, mask)

    var oldValue = values(index)
    while (null != oldValue)
    {
      if (key == keys(index))
      {

        values(index) = v
        return unmapNullValue(oldValue)
      }

      index = (index + 1) & mask
      oldValue = values(index)
    }

    _size += 1
    keys(index) = key
    values(index) = v

    if (_size > _resizeThreshold)
    {
      increaseCapacity()
    }

    unmapNullValue(oldValue)
  }

  /**
   * {@inheritDoc}
   */
  override def remove(key: Any): V = remove(key.asInstanceOf[java.lang.Long].longValue())

  /**
   * Overloaded version of `Map#remove(Object)` that takes a primitive int key.
   *
   * @param key for indexing the `Map`.
   * @return the value if found otherwise null.
   */
  def remove(key: Long): V =
  {
    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(key, mask)

    var value = values(index)
    while (null != value)
    {
      if (key == keys(index))
      {
        values(index) = null
        _size -= 1

        compactChain(index)
        return unmapNullValue(value)
      }

      index = (index + 1) & mask
      value = values(index)
    }

    unmapNullValue(value)
  }

  /**
   * {@inheritDoc}
   */
  override def remove(key: Any, value: Any): Boolean = remove(key.asInstanceOf[java.lang.Long].longValue(), value.asInstanceOf[V])

  /**
   * Primitive specialised version of `Map#remove(Object, Object)`.
   *
   * @param key   with which the specified value is associated.
   * @param value expected to be associated with the specified key.
   * @return `true` if the value was removed.
   */
  def remove(key: Long, value: V): Boolean =
  {
    val v = mapNullValue(value.asInstanceOf[AnyRef])
    if (null != v)
    {
      val keys = this.keys
      val values = this.valuesArray
      @DoNotSub val mask = values.length - 1
      @DoNotSub var index = Hashing.hash(key, mask)

      var mappedValue = values(index)
      while (null != mappedValue)
      {
        if (key == keys(index))
        {
          if (Objects.equals(unmapNullValue(mappedValue), value))
          {
            values(index) = null
            _size -= 1

            compactChain(index)
            return true
          }
          return false
        }

        index = (index + 1) & mask
        mappedValue = values(index)
      }
    }
    false
  }

  /**
   * {@inheritDoc}
   */
  override def clear(): Unit =
  {
    if (_size > 0)
    {
      Arrays.fill(valuesArray.asInstanceOf[Array[Object]], null)
      _size = 0
    }
  }

  /**
   * Compact the `Map` backing arrays by rehashing with a capacity just larger than
   * current size and giving consideration to the load factor.
   */
  def compact(): Unit =
  {
    @DoNotSub val idealCapacity = Math.round(size() * (1.0d / _loadFactor)).toInt
    rehash(findNextPositivePowerOfTwo(Math.max(MIN_CAPACITY, idealCapacity)))
  }

  /**
   * {@inheritDoc}
   */
  override def putAll(map: JMap[? <: java.lang.Long, ? <: V]): Unit =
  {
    val it = map.entrySet().iterator()
    while (it.hasNext)
    {
      val entry = it.next()
      put(entry.getKey(), entry.getValue())
    }
  }

  /**
   * Put all values from the given map into this one without allocation.
   *
   * @param map whose value are to be added.
   */
  def putAll(map: Long2ObjectHashMap[? <: V]): Unit =
  {
    val iterator: map.EntryIterator = map.entrySet().iterator()
    while (iterator.hasNext)
    {
      iterator.findNext()
      put(iterator.getLongKey(), iterator.getValue().asInstanceOf[V])
    }
  }

  /**
   * Primitive specialised version of `putIfAbsent(Object, Object)`.
   *
   * @param key   with which the specified value is to be associated.
   * @param value to be associated with the specified key.
   * @return the previous value associated with the specified key, or `null` if there was
   *         no mapping for the key.
   */
  def putIfAbsent(key: Long, value: V): V =
  {
    val v = mapNullValue(value.asInstanceOf[AnyRef])
    Objects.requireNonNull(v, "value cannot be null")

    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(key, mask)

    var mappedValue = values(index)
    while (null != mappedValue)
    {
      if (key == keys(index))
      {
        return unmapNullValue(mappedValue)
      }

      index = (index + 1) & mask
      mappedValue = values(index)
    }

    val oldValue = unmapNullValue(mappedValue)

    _size += 1
    keys(index) = key
    values(index) = v

    if (_size > _resizeThreshold)
    {
      increaseCapacity()
    }

    oldValue
  }

  /**
   * {@inheritDoc}
   */
  override def keySet(): KeySet =
  {
    if (null == _keySet)
    {
      _keySet = new KeySet()
    }

    _keySet
  }

  /**
   * {@inheritDoc}
   */
  override def values(): ValueCollection =
  {
    if (null == valueCollection)
    {
      valueCollection = new ValueCollection()
    }

    valueCollection
  }

  /**
   * {@inheritDoc}
   */
  override def entrySet(): EntrySet =
  {
    if (null == _entrySet)
    {
      _entrySet = new EntrySet()
    }

    _entrySet
  }

  /**
   * {@inheritDoc}
   */
  override def toString(): String =
  {
    if (isEmpty())
    {
      return "{}"
    }

    val entryIterator = new EntryIterator()
    entryIterator.reset()

    val sb = new StringBuilder().append('{')
    while (true)
    {
      entryIterator.next()
      sb.append(entryIterator.getLongKey()).append('=').append(unmapNullValue(entryIterator.getValue().asInstanceOf[Object]))
      if (!entryIterator.hasNext())
      {
        return sb.append('}').toString()
      }
      sb.append(',').append(' ')
    }

    sb.toString() // unreachable, mirrors Java's structurally-infinite while(true)
  }

  /**
   * {@inheritDoc}
   */
  override def equals(o: Any): Boolean =
  {
    if (this.asInstanceOf[AnyRef] eq o.asInstanceOf[AnyRef])
    {
      return true
    }

    o match
    {
      case that: JMap[_, _] =>
        if (_size != that.size())
        {
          return false
        }

        val keys = this.keys
        val values = this.valuesArray
        @DoNotSub var i = 0
        @DoNotSub val length = values.length
        while (i < length)
        {
          val thisValue = values(i)
          if (null != thisValue)
          {
            val thatValue = that.asInstanceOf[JMap[Any, Any]].get(java.lang.Long.valueOf(keys(i)))
            if (!thisValue.equals(mapNullValue(thatValue.asInstanceOf[AnyRef])))
            {
              return false
            }
          }
          i += 1
        }

        true
      case _ => false
    }
  }

  /**
   * {@inheritDoc}
   */
  @DoNotSub override def hashCode(): Int =
  {
    @DoNotSub var result = 0

    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub var i = 0
    @DoNotSub val length = values.length
    while (i < length)
    {
      val value = values(i)
      if (null != value)
      {
        result += (java.lang.Long.hashCode(keys(i)) ^ value.hashCode())
      }
      i += 1
    }

    result
  }

  /**
   * Interceptor for masking null values.
   *
   * @param value value to mask.
   * @return masked value.
   */
  protected def mapNullValue(value: Any): AnyRef = value.asInstanceOf[AnyRef]

  /**
   * Interceptor for unmasking null values.
   *
   * @param value value to unmask.
   * @return unmasked value.
   */
  protected def unmapNullValue(value: Any): V = value.asInstanceOf[V]

  /**
   * Primitive specialised version of `Map#replace(Object, Object)`.
   *
   * @param key   key with which the specified value is associated.
   * @param value value to be associated with the specified key.
   * @return the previous value associated with the specified key, or `null` if there was
   *         no mapping for the key.
   */
  def replace(key: Long, value: V): V =
  {
    val v = mapNullValue(value.asInstanceOf[AnyRef])
    Objects.requireNonNull(v, "value cannot be null")

    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(key, mask)

    var oldValue = values(index)
    while (null != oldValue)
    {
      if (key == keys(index))
      {
        values(index) = v
        return unmapNullValue(oldValue)
      }

      index = (index + 1) & mask
      oldValue = values(index)
    }

    unmapNullValue(oldValue)
  }

  /**
   * Primitive specialised version of `Map#replace(Object, Object, Object)`.
   *
   * @param key      key with which the specified value is associated.
   * @param oldValue value expected to be associated with the specified key.
   * @param newValue value to be associated with the specified key.
   * @return `true` if the value was replaced.
   */
  def replace(key: Long, oldValue: V, newValue: V): Boolean =
  {
    val v = mapNullValue(newValue.asInstanceOf[AnyRef])
    Objects.requireNonNull(v, "value cannot be null")

    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = Hashing.hash(key, mask)

    var mappedValue = values(index)
    while (null != mappedValue)
    {
      if (key == keys(index))
      {
        if (Objects.equals(unmapNullValue(mappedValue), oldValue))
        {
          values(index) = v
          return true
        }
        return false
      }

      index = (index + 1) & mask
      mappedValue = values(index)
    }

    false
  }

  /**
   * {@inheritDoc}
   */
  override def replaceAll(function: BiFunction[? >: java.lang.Long, ? >: V, ? <: V]): Unit =
  {
    replaceAllLong(new LongObjectToObjectFunction[V, V]
    {
      override def apply(k: Long, value: V): V = function.apply(k, value)
    })
  }

  /**
   * Primitive specialised version of `Map#replaceAll(BiFunction)`.
   *
   * NB: Renamed from replaceAll to avoid overloading on parameter types of lambda
   * expression, which doesn't play well with type inference in lambda expressions.
   *
   * @param function the function to apply to each entry.
   */
  def replaceAllLong(function: LongObjectToObjectFunction[? >: V, ? <: V]): Unit =
  {
    Objects.requireNonNull(function)
    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val length = values.length
    @DoNotSub var remaining = _size

    @DoNotSub var index = 0
    while (remaining > 0 && index < length)
    {
      val oldValue = values(index)
      if (null != oldValue)
      {
        val newVal = mapNullValue(function.apply(keys(index), unmapNullValue(oldValue)).asInstanceOf[AnyRef])
        Objects.requireNonNull(newVal, "value cannot be null")
        values(index) = newVal
        remaining -= 1
      }
      index += 1
    }
  }

  private def increaseCapacity(): Unit =
  {
    @DoNotSub val newCapacity = valuesArray.length << 1
    if (newCapacity < 0)
    {
      throw new IllegalStateException("max capacity reached at size=" + _size)
    }

    rehash(newCapacity)
  }

  private def rehash(@DoNotSub newCapacity: Int): Unit =
  {
    @DoNotSub val mask = newCapacity - 1
    /* @DoNotSub */ _resizeThreshold = (newCapacity * _loadFactor).toInt

    val tempKeys = new Array[Long](newCapacity)
    val tempValues = new Array[Object](newCapacity)

    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub var i = 0
    @DoNotSub val size = values.length
    while (i < size)
    {
      val value = values(i)
      if (null != value)
      {
        val key = keys(i)
        @DoNotSub var index = Hashing.hash(key, mask)
        while (null != tempValues(index))
        {
          index = (index + 1) & mask
        }

        tempKeys(index) = key
        tempValues(index) = value
      }
      i += 1
    }

    this.keys = tempKeys
    this.valuesArray = tempValues
  }

  private def compactChain(@DoNotSub deleteIndexParam: Int): Unit =
  {
    var deleteIndex = deleteIndexParam
    val keys = this.keys
    val values = this.valuesArray
    @DoNotSub val mask = values.length - 1
    @DoNotSub var index = deleteIndex
    var continue = true
    while (continue)
    {
      index = (index + 1) & mask
      val value = values(index)
      if (null == value)
      {
        continue = false
      }
      else
      {
        val key = keys(index)
        @DoNotSub val hash = Hashing.hash(key, mask)

        if ((index < hash && (hash <= deleteIndex || deleteIndex <= index)) ||
          (hash <= deleteIndex && deleteIndex <= index))
        {
          keys(deleteIndex) = key
          values(deleteIndex) = value

          values(index) = null
          deleteIndex = index
        }
      }
    }
  }

  /**
   * Set of keys which supports optionally cached iterators to avoid allocation.
   */
  final class KeySet extends AbstractSet[java.lang.Long]
  {
    private val cachedKeyIterator: KeyIterator = if (shouldAvoidAllocation) new KeyIterator() else null

    /**
     * {@inheritDoc}
     */
    override def iterator(): KeyIterator =
    {
      var keyIterator = this.cachedKeyIterator
      if (null == keyIterator)
      {
        keyIterator = new KeyIterator()
      }

      keyIterator.reset()
      keyIterator
    }

    /**
     * {@inheritDoc}
     */
    @DoNotSub override def size(): Int = Long2ObjectHashMap.this.size()

    /**
     * {@inheritDoc}
     */
    override def contains(o: Any): Boolean = Long2ObjectHashMap.this.containsKey(o)

    /**
     * Checks if the key is contained in the map.
     *
     * @param key to check.
     * @return `true` if the key is contained in the map.
     */
    def contains(key: Long): Boolean = Long2ObjectHashMap.this.containsKey(key)

    /**
     * {@inheritDoc}
     */
    override def remove(o: Any): Boolean = null != Long2ObjectHashMap.this.remove(o)

    /**
     * Removes key and the corresponding value from the map.
     *
     * @param key to be removed.
     * @return `true` if the mapping was removed.
     */
    def remove(key: Long): Boolean = null != Long2ObjectHashMap.this.remove(key)

    /**
     * {@inheritDoc}
     */
    override def clear(): Unit = Long2ObjectHashMap.this.clear()

    /**
     * Removes all the elements of this collection that satisfy the given predicate.
     *
     * NB: Renamed from removeIf to avoid overloading on parameter types of lambda
     * expression, which doesn't play well with type inference in lambda expressions.
     *
     * @param filter a predicate to apply.
     * @return `true` if at least one key was removed.
     */
    def removeIfLong(filter: LongPredicate): Boolean =
    {
      var removed = false
      val iterator = this.iterator()
      while (iterator.hasNext)
      {
        if (filter.test(iterator.nextLong()))
        {
          iterator.remove()
          removed = true
        }
      }
      removed
    }
  }

  /**
   * Collection of values which supports optionally cached iterators to avoid allocation.
   */
  final class ValueCollection extends AbstractCollection[V]
  {
    private val cachedValueIterator: ValueIterator = if (shouldAvoidAllocation) new ValueIterator() else null

    /**
     * {@inheritDoc}
     */
    override def iterator(): ValueIterator =
    {
      var valueIterator = this.cachedValueIterator
      if (null == valueIterator)
      {
        valueIterator = new ValueIterator()
      }

      valueIterator.reset()
      valueIterator
    }

    /**
     * {@inheritDoc}
     */
    @DoNotSub override def size(): Int = Long2ObjectHashMap.this.size()

    /**
     * {@inheritDoc}
     */
    override def contains(o: Any): Boolean = Long2ObjectHashMap.this.containsValue(o)

    /**
     * {@inheritDoc}
     */
    override def clear(): Unit = Long2ObjectHashMap.this.clear()

    /**
     * {@inheritDoc}
     */
    override def forEach(action: Consumer[? >: V]): Unit =
    {
      @DoNotSub var remaining = Long2ObjectHashMap.this._size

      val values = Long2ObjectHashMap.this.valuesArray
      @DoNotSub var i = 0
      @DoNotSub val length = values.length
      while (remaining > 0 && i < length)
      {
        val value = values(i)
        if (null != value)
        {
          action.accept(unmapNullValue(value))
          remaining -= 1
        }
        i += 1
      }
    }
  }

  /**
   * Set of entries which supports access via an optionally cached iterator to avoid
   * allocation.
   */
  final class EntrySet extends AbstractSet[JEntry[java.lang.Long, V]]
  {
    private val cachedEntryIterator: EntryIterator = if (shouldAvoidAllocation) new EntryIterator() else null

    /**
     * {@inheritDoc}
     */
    override def iterator(): EntryIterator =
    {
      var entryIterator = this.cachedEntryIterator
      if (null == entryIterator)
      {
        entryIterator = new EntryIterator()
      }

      entryIterator.reset()
      entryIterator
    }

    /**
     * {@inheritDoc}
     */
    @DoNotSub override def size(): Int = Long2ObjectHashMap.this.size()

    /**
     * {@inheritDoc}
     */
    override def clear(): Unit = Long2ObjectHashMap.this.clear()

    /**
     * {@inheritDoc}
     */
    override def contains(o: Any): Boolean =
    {
      o match
      {
        case entry: JEntry[_, _] =>
          val key = entry.getKey().asInstanceOf[java.lang.Long].longValue()
          val value = getMapped(key)
          null != value && value.equals(mapNullValue(entry.getValue().asInstanceOf[AnyRef]))
        case _ => false
      }
    }

    /**
     * Removes all the elements of this collection that satisfy the given predicate.
     *
     * NB: Renamed from removeIf to avoid overloading on parameter types of lambda
     * expression, which doesn't play well with type inference in lambda expressions.
     *
     * @param filter a predicate to apply.
     * @return `true` if at least one key was removed.
     */
    def removeIfLong(filter: LongObjPredicate[V]): Boolean =
    {
      var removed = false
      val iterator = this.iterator()
      while (iterator.hasNext)
      {
        iterator.findNext()
        if (filter.test(iterator.getLongKey(), iterator.getValue()))
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
    override def toArray(): Array[Object] = toArray(new Array[Object](size()))

    /**
     * {@inheritDoc}
     */
    override def toArray[T](a: Array[T & Object]): Array[T & Object] =
    {
      val array: Array[T & Object] =
        if (a.length >= _size) a
        else java.lang.reflect.Array.newInstance(a.getClass.getComponentType, _size).asInstanceOf[Array[T & Object]]
      val it = iterator()

      @DoNotSub var i = 0
      while (i < array.length)
      {
        if (it.hasNext)
        {
          it.next()
          array(i) = it.allocateDuplicateEntry().asInstanceOf[T & Object]
        }
        else
        {
          array(i) = null.asInstanceOf[T & Object]
          i = array.length
        }
        i += 1
      }

      array
    }
  }

  /**
   * Base iterator implementation that contains basic logic of traversing the elements
   * in the backing array.
   *
   * @tparam T type of elements.
   */
  private[collections] abstract class AbstractIterator[T] extends java.util.Iterator[T]
  {
    @DoNotSub private var posCounter: Int = 0
    @DoNotSub private var stopCounter: Int = 0
    @DoNotSub private var _remaining: Int = 0
    private[Long2ObjectHashMap] var isPositionValid: Boolean = false

    /**
     * Position of the current element.
     *
     * @return position of the current element.
     */
    @DoNotSub protected final def position(): Int = posCounter & (valuesArray.length - 1)

    /**
     * Number of remaining elements.
     *
     * @return number of remaining elements.
     */
    @DoNotSub def remaining(): Int = _remaining

    /**
     * {@inheritDoc}
     */
    override def hasNext(): Boolean = _remaining > 0

    /**
     * Find the next element.
     *
     * @throws NoSuchElementException if no more elements.
     */
    protected[Long2ObjectHashMap] final def findNext(): Unit =
    {
      if (!hasNext())
      {
        throw new NoSuchElementException()
      }

      val values = Long2ObjectHashMap.this.valuesArray
      @DoNotSub val mask = values.length - 1

      @DoNotSub var i = posCounter - 1
      @DoNotSub val stop = stopCounter
      while (i >= stop)
      {
        @DoNotSub val index = i & mask
        if (null != values(index))
        {
          posCounter = i
          isPositionValid = true
          _remaining -= 1
          return
        }
        i -= 1
      }

      isPositionValid = false
      throw new IllegalStateException()
    }

    /**
     * {@inheritDoc}
     */
    override def remove(): Unit =
    {
      if (isPositionValid)
      {
        @DoNotSub val position = this.position()
        valuesArray(position) = null
        _size -= 1

        compactChain(position)

        isPositionValid = false
      }
      else
      {
        throw new IllegalStateException()
      }
    }

    private[Long2ObjectHashMap] def reset(): Unit =
    {
      _remaining = Long2ObjectHashMap.this._size
      val values = Long2ObjectHashMap.this.valuesArray
      @DoNotSub val capacity = values.length

      @DoNotSub var i = capacity
      if (null != values(capacity - 1))
      {
        i = 0
        while (i < capacity && null != values(i))
        {
          i += 1
        }
      }

      stopCounter = i
      posCounter = i + capacity
      isPositionValid = false
    }
  }

  /**
   * Iterator over values.
   */
  final class ValueIterator extends AbstractIterator[V]
  {
    /**
     * {@inheritDoc}
     */
    override def next(): V =
    {
      findNext()
      unmapNullValue(valuesArray(position()))
    }
  }

  /**
   * Iterator over keys which supports access to unboxed keys via `nextLong()`.
   */
  final class KeyIterator extends AbstractIterator[java.lang.Long]
  {
    /**
     * {@inheritDoc}
     */
    override def next(): java.lang.Long = nextLong()

    /**
     * Return next key without boxing.
     *
     * @return next key.
     */
    def nextLong(): Long =
    {
      findNext()
      keys(position())
    }
  }

  /**
   * Iterator over entries which supports access to unboxed keys via `getLongKey()`.
   */
  final class EntryIterator
    extends AbstractIterator[JEntry[java.lang.Long, V]]
    with JEntry[java.lang.Long, V]
  {
    /**
     * {@inheritDoc}
     */
    override def next(): JEntry[java.lang.Long, V] =
    {
      findNext()
      if (shouldAvoidAllocation)
      {
        this
      }
      else
      {
        allocateDuplicateEntry()
      }
    }

    private[collections] def allocateDuplicateEntry(): JEntry[java.lang.Long, V] =
      new MapEntry(getLongKey(), getValue())

    /**
     * {@inheritDoc}
     */
    override def getKey(): java.lang.Long = getLongKey()

    /**
     * Get key without boxing.
     *
     * @return key.
     */
    def getLongKey(): Long = keys(position())

    /**
     * {@inheritDoc}
     */
    override def getValue(): V = unmapNullValue(valuesArray(position()))

    /**
     * {@inheritDoc}
     */
    override def setValue(value: V): V =
    {
      val v = mapNullValue(value.asInstanceOf[AnyRef])
      Objects.requireNonNull(v, "value cannot be null")

      if (!this.isPositionValid)
      {
        throw new IllegalStateException()
      }

      @DoNotSub val pos = position()
      val values = Long2ObjectHashMap.this.valuesArray
      val oldValue = values(pos)
      values(pos) = v

      unmapNullValue(oldValue)
    }

    /**
     * An `java.util.Map.Entry` implementation.
     */
    final class MapEntry(k: Long, v: V) extends JEntry[java.lang.Long, V]
    {
      /**
       * {@inheritDoc}
       */
      override def getKey(): java.lang.Long = java.lang.Long.valueOf(k)

      /**
       * {@inheritDoc}
       */
      override def getValue(): V = v

      /**
       * {@inheritDoc}
       */
      override def setValue(value: V): V = Long2ObjectHashMap.this.put(k, value)

      /**
       * {@inheritDoc}
       */
      @DoNotSub override def hashCode(): Int = java.lang.Long.hashCode(k) ^ (if (null != v) v.hashCode() else 0)

      /**
       * {@inheritDoc}
       */
      override def equals(o: Any): Boolean =
      {
        o match
        {
          case e: JEntry[_, _] =>
            (e.getKey() != null && e.getKey().equals(k)) &&
              ((e.getValue() == null && v == null) || e.getValue().equals(v))
          case _ => false
        }
      }

      /**
       * {@inheritDoc}
       */
      override def toString(): String = k + "=" + v
    }
  }
}

/**
 * Companion holding the static-in-Java constant.
 */
object Long2ObjectHashMap
{
  @DoNotSub private[collections] val MIN_CAPACITY: Int = 8
}
