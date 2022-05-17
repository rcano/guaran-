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
package org.agrona

final class BitUtil {}

/**
 * Miscellaneous useful functions for dealing with low level bits and bytes.
 */
object BitUtil {

    /**
     * Fast method of finding the next power of 2 greater than or equal to the supplied value.
     * <p>
     * If the value is &lt= 0 then 1 will be returned.
     * <p>
     * This method is not suitable for {@link Integer#MIN_VALUE} or numbers greater than 2^30. When provided
     * then {@link Integer#MIN_VALUE} will be returned.
     *
     * @param value from which to search for next power of 2.
     * @return The next power of 2 or the value itself if it is a power of 2.
     */
    def findNextPositivePowerOfTwo(value: Int): Int =
    {
        return 1 << (Integer.SIZE - Integer.numberOfLeadingZeros(value - 1))
    }

    /**
     * Fast method of finding the next power of 2 greater than or equal to the supplied value.
     * <p>
     * If the value is &lt= 0 then 1 will be returned.
     * <p>
     * This method is not suitable for {@link Long#MIN_VALUE} or numbers greater than 2^62. When provided
     * then {@link Long#MIN_VALUE} will be returned.
     *
     * @param value from which to search for next power of 2.
     * @return The next power of 2 or the value itself if it is a power of 2.
     */
    def findNextPositivePowerOfTwo(value: Long): Long =
    {
        return 1L << (java.lang.Long.SIZE - java.lang.Long.numberOfLeadingZeros(value - 1))
    }

}
