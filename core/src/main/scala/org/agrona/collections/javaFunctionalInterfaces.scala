package org.agrona.collections

trait IntObjConsumer[T] { def accept(i: Int, v: T): Unit }
trait LongObjConsumer[T] { def accept(i: Long, v: T): Unit }

/**
 * This is an (int, Object) -&gt; Object primitive specialisation of a BiFunction.
 *
 * @param <T> the type of the input to the function.
 * @param <R> the type of the output to the function.
 */
@FunctionalInterface
trait IntObjectToObjectFunction[T, R] {
    /**
     * Applies this function to the given arguments.
     *
     * @param i the second function argument.
     * @param t the first function argument.
     * @return the function result.
     */
    def apply(i: Int, t: T): R
}

/**
 * This is an (long, Object) -&gt; Object primitive specialisation of a BiFunction.
 *
 * @param <T> the type of the input to the function.
 * @param <R> the type of the output to the function.
 */
@FunctionalInterface
trait LongObjectToObjectFunction[T, R] {
    /**
     * Applies this function to the given arguments.
     *
     * @param i the second function argument.
     * @param t the first function argument.
     * @return the function result.
     */
    def apply(i: Long, t: T): R
}

/**
 * This is an (int, Object) primitive specialisation of a BiPredicate.
 *
 * @param <T> the type of the input to the predicate.
 */
@FunctionalInterface
trait IntObjPredicate[T] {
    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param valueOne for the tuple.
     * @param valueTwo for the tuple.
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    def test(valueOne: Int, valueTwo: T): Boolean
}

/**
 * This is an (Long, Object) primitive specialisation of a BiPredicate.
 *
 * @param <T> the type of the input to the predicate.
 */
@FunctionalInterface
trait LongObjPredicate[T] {
    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param valueOne for the tuple.
     * @param valueTwo for the tuple.
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    def test(valueOne: Long, valueTwo: T): Boolean
}