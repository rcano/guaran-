package org.agrona.collections

import org.agrona.BitUtil

import java.util.{List => JList, Map => JMap}
import java.util.function.{Function, Predicate, ToIntFunction}

/**
 * Utility functions for collection objects.
 *
 * Literal port of `org.agrona.collections.CollectionUtil`, kept as a Scala `object`
 * mirroring the Java `final class` with a private constructor and only static members.
 * Uses `java.util.List`/`java.util.Map` and `java.util.function` types directly (rather
 * than Scala collections/`Function1`) and explicit index-based `while`/`for` loops in
 * place of Scala's `foldLeft`, `filterNot`, etc., to preserve the java-isms of the
 * original.
 */
object CollectionUtil
{
  /**
   * A getOrDefault that doesn't create garbage if its supplier is non-capturing.
   *
   * @param map      to perform the lookup on.
   * @param key      on which the lookup is done.
   * @param supplier of the default value if one is not found.
   * @tparam K type of the key.
   * @tparam V type of the value.
   * @return the value if found or a new default which has been added to the map.
   */
  def getOrDefault[K, V](map: JMap[K, V], key: K, supplier: Function[K, V]): V =
  {
    var value: V = map.get(key)
    if (value == null)
    {
      value = supplier.apply(key)
      map.put(key, value)
    }

    value
  }

  /**
   * Garbage free sum function.
   *
   * Note: the list must implement `java.util.RandomAccess` to be efficient.
   *
   * @param values   the list of input values.
   * @param function function that maps each value to an int.
   * @tparam V the value to add up.
   * @return the sum of all the int values returned for each member of the list.
   */
  def sum[V](values: JList[V], function: ToIntFunction[V]): Int =
  {
    var total: Int = 0

    val size: Int = values.size()
    var i: Int = 0
    while (i < size)
    {
      val value: V = values.get(i)
      total += function.applyAsInt(value)
      i += 1
    }

    total
  }

  /**
   * Validate that a load factor is in the range of 0.1 to 0.9.
   *
   * Load factors in the range 0.5 - 0.7 are recommended for open-addressing with linear
   * probing.
   *
   * @param loadFactor to be validated.
   */
  def validateLoadFactor(loadFactor: Float): Unit =
  {
    if (loadFactor < 0.1f || loadFactor > 0.9f)
    {
      throw new IllegalArgumentException("load factor must be in the range of 0.1 to 0.9: " + loadFactor)
    }
  }

  /**
   * Validate that a number is a power of two.
   *
   * @param value to be validated.
   */
  def validatePositivePowerOfTwo(value: Int): Unit =
  {
    if (!BitUtil.isPowerOfTwo(value))
    {
      throw new IllegalArgumentException("value must be a positive power of two: " + value)
    }
  }

  /**
   * Remove element from a list if it matches a predicate.
   *
   * Note: the list must implement `java.util.RandomAccess` to be efficient.
   *
   * @param values    to be iterated over.
   * @param predicate to test the value against.
   * @tparam T type of the value.
   * @return the number of items removed.
   */
  def removeIf[T](values: JList[T], predicate: Predicate[T]): Int =
  {
    var size: Int = values.size()
    var total: Int = 0

    var i: Int = 0
    while (i < size)
    {
      val value: T = values.get(i)
      if (predicate.test(value))
      {
        values.remove(i)
        total += 1
        size -= 1
      }
      else
      {
        i += 1
      }
    }

    total
  }
}
