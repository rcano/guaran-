package org.agrona.generation

import scala.annotation.StaticAnnotation

/**
 * NOT part of the requested translation. In real Agrona, `@DoNotSub` is read by their
 * build-time code generator, which produces `Long2ObjectHashMap`/`LongHashSet` etc. from
 * the `Int...` sources by textually substituting `int` -> `long`, except where a member
 * is annotated `@DoNotSub` (sizes, counts, indices, hash-derived positions must stay
 * `int` even in the `Long` variant). Since both the `Int...` and `Long...` classes are
 * hand-translated here rather than generated, this annotation has no runtime behaviour;
 * it is kept purely so the annotation sites in the ported source read the same as the
 * Java originals.
 */
class DoNotSub extends StaticAnnotation
