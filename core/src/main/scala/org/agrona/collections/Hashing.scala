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

private class Hashing {}
/**
 * Hashing functions for applying to integers.
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
        var x = value

        x = ((x >>> 16) ^ x) * 0x119de1f3
        x = ((x >>> 16) ^ x) * 0x119de1f3
        x = (x >>> 16) ^ x

        return x
    }

    /**
     * Generate a hash for a long value.
     *
     * @param value to be hashed.
     * @return the hashed value.
     */
    def hash(value: Long): Int =
    {
        var x = value

        x = (x ^ (x >>> 30)) * 0xbf58476d1ce4e5b9L
        x = (x ^ (x >>> 27)) * 0x94d049bb133111ebL
        x = x ^ (x >>> 31)

        return x.toInt ^ (x >>> 32).toInt
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
        return hash(value) & mask
    }

    /**
     * Generate a hash for an object and apply mask to get a remainder.
     *
     * @param value to be hashed.
     * @param mask  mask to be applied that must be a power of 2 - 1.
     * @return the hash of the value.
     */
    def hash(value: Object, mask: Int): Int =
    {
        return hash(value.hashCode()) & mask
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
        return hash(value) & mask
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
        val h = hash(value)
        val evenHash = (h << 1) - (h << 8)

        return evenHash & mask
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
        val h = hash(value)
        val evenHash = (h << 1) - (h << 8)

        return evenHash & mask
    }

    /**
     * Combined two 32-bit keys into a 64-bit compound.
     *
     * @param keyPartA to make the upper bits
     * @param keyPartB to make the lower bits.
     * @return the compound key
     */
    def compoundKey(keyPartA: Int, keyPartB: Int): Long =
    {
        return (keyPartA.toLong << 32) | (keyPartB & 0xFFFF_FFFFL)
    }
}
