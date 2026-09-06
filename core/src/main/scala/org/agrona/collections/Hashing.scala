package org.agrona.collections

/**
 * Literal port of `org.agrona.collections.Hashing`.
 *
 * Kept as a Scala `object` (module, effectively a "final class with private constructor
 * and only static members") to mirror the Java original as closely as possible, rather
 * than reaching for a Scala package-object or top-level functions.
 */
object Hashing
{
  /**
   * Default load factor to be used in open-addressing hashed data structures.
   */
  val DEFAULT_LOAD_FACTOR: Float = 0.65f

  /**
   * Generate a hash for an int value.
   *
   * @param value to be hashed.
   * @return the hashed value.
   */
  def hash(value: Int): Int =
  {
    var x: Int = value

    x = ((x >>> 16) ^ x) * 0x119de1f3
    x = ((x >>> 16) ^ x) * 0x119de1f3
    x = (x >>> 16) ^ x

    x
  }

  /**
   * Generate a hash for a long value.
   *
   * @param value to be hashed.
   * @return the hashed value.
   */
  def hash(value: Long): Int =
  {
    var x: Long = value

    x = (x ^ (x >>> 30)) * 0xbf58476d1ce4e5b9L
    x = (x ^ (x >>> 27)) * 0x94d049bb133111ebL
    x = x ^ (x >>> 31)

    x.toInt ^ (x >>> 32).toInt
  }

  /**
   * Generate a hash for an int value and apply mask to get remainder.
   *
   * @param value to be hashed.
   * @param mask  mask to be applied that must be a power of 2 - 1.
   * @return the hash of the value.
   */
  def hash(value: Int, mask: Int): Int =
  {
    hash(value) & mask
  }

  /**
   * Generate a hash for an object and apply mask to get a remainder.
   *
   * @param value to be hashed.
   * @param mask  mask to be applied that must be a power of 2 - 1.
   * @return the hash of the value.
   */
  def hash(value: AnyRef, mask: Int): Int =
  {
    hash(value.hashCode()) & mask
  }

  /**
   * Generate a hash for a long value and apply mask to get a remainder.
   *
   * @param value to be hashed.
   * @param mask  mask to be applied that must be a power of 2 - 1.
   * @return the hash of the value.
   */
  def hash(value: Long, mask: Int): Int =
  {
    hash(value) & mask
  }

  /**
   * Generate an even hash for an int value and apply mask to get a remainder that will be even.
   *
   * @param value to be hashed.
   * @param mask  mask to be applied that must be a power of 2 - 1.
   * @return the hash of the value which is always even.
   */
  def evenHash(value: Int, mask: Int): Int =
  {
    val hash: Int = this.hash(value)
    val evenHash: Int = (hash << 1) - (hash << 8)

    evenHash & mask
  }

  /**
   * Generate an even hash for a long value and apply mask to get a remainder that will be even.
   *
   * @param value to be hashed.
   * @param mask  mask to be applied that must be a power of 2 - 1.
   * @return the hash of the value which is always even.
   */
  def evenHash(value: Long, mask: Int): Int =
  {
    val hash: Int = this.hash(value)
    val evenHash: Int = (hash << 1) - (hash << 8)

    evenHash & mask
  }

  /**
   * Combined two 32-bit keys into a 64-bit compound.
   *
   * @param keyPartA to make the upper bits.
   * @param keyPartB to make the lower bits.
   * @return the compound key.
   */
  def compoundKey(keyPartA: Int, keyPartB: Int): Long =
  {
    (keyPartA.toLong << 32) | (keyPartB & 0xFFFFFFFFL)
  }
}
