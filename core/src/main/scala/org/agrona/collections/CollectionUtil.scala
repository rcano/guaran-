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

final class CollectionUtil

/** Utility functions for collection objects.
  */
object CollectionUtil {

  /** Validate that a load factor is in the range of 0.1 to 0.9. <p> Load factors in the range 0.5 - 0.7 are recommended for open-addressing
    * with linear probing.
    *
    * @param loadFactor
    *   to be validated.
    */
  def validateLoadFactor(loadFactor: Float): Unit =
    if (loadFactor < 0.1f || loadFactor > 0.9f) {
      throw new IllegalArgumentException("load factor must be in the range of 0.1 to 0.9: " + loadFactor);
    }
}
