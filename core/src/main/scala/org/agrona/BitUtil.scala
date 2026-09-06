package org.agrona

/**
 * NOT part of the requested translation. `org.agrona.BitUtil` is a large utility class;
 * the six classes asked for (`CollectionUtil`, `Hashing`, `Int2ObjectHashMap`, `IntHashSet`,
 * `Long2ObjectHashMap`, `LongHashSet`) statically import exactly two of its members:
 * `isPowerOfTwo(int)` and `findNextPositivePowerOfTwo(int)`. This object supplies only
 * those two, faithfully, so the ported classes compile and behave correctly; it is not
 * a translation of the real `BitUtil`.
 */
object BitUtil
{
  /**
   * Is a value a positive power of two.
   *
   * @param value to be checked.
   * @return true if the value is a positive power of two, otherwise false.
   */
  def isPowerOfTwo(value: Int): Boolean =
  {
    value > 0 && ((value & (value - 1)) == 0)
  }

  /**
   * Find the next power of two greater than or equal to the given value.
   *
   * @param value from which to search for next power of two.
   * @return the next power of two greater than or equal to value.
   */
  def findNextPositivePowerOfTwo(value: Int): Int =
  {
    1 << (32 - Integer.numberOfLeadingZeros(value - 1))
  }
}
