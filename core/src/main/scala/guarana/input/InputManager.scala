package guarana.input

import java.time.{Clock, Instant}
import scala.annotation.targetName
import scala.annotation.unchecked.uncheckedVariance
import scala.concurrent.duration.*

trait InputManager[EventType, InputCode] {
  import internal.*

  def clock: Clock

  /** When doing a sequential combination, such as A then B, this duration specifies the maximum time to await for the second input. Note
    * that this can be overriden in the actual combination
    */
  def sequenceDefaultPatience: FiniteDuration
  def combinationTolerance: FiniteDuration
  def getInputCode(evt: EventType): InputCode
  def isDown(evt: EventType): Boolean

  protected def onEventTransition(combination: InputMatcher[?], event: EventType, previousState: Any, newState: Any): Unit = {}
  private val combinationProcessors = collection.mutable.HashMap.empty[InputMatcher[?], CombinationProcessor[?]]

  def recordCombination[T <: Tuple](combination: InputMatcher[T])(action: T => Unit): Unit = combinationProcessors(combination) =
    CombinationProcessor(combination, action)

  def dispatch(evt: EventType): Unit = combinationProcessors.valuesIterator foreach (_(evt))

  extension [T <: Tuple](result: InputMatcher[T] | Option[T]) {
    inline def fold[R](onNextMatcher: InputMatcher[T] => R)(onProduced: Option[T] => R): R = result match {
      case opt: Option[T @unchecked] => onProduced(opt)
      case m: InputMatcher[T @unchecked] => onNextMatcher(m)
    }
  }
  trait InputMatcher[+T <: Tuple] {
    def apply(evt: EventType): InputMatcher[T] | Option[T]

    def map[U <: Tuple](f: T => U): InputMatcher[U] = mapNamed(s"$this.map(...)")(f)
    def mapNamed[U <: Tuple](name: String)(f: T => U): InputMatcher[U] =
      NamedInputMatcher(name, evt => apply(evt).fold(_.map(f))(_.map(f)))

    def flatMap[U <: Tuple](f: T => InputMatcher[U]): InputMatcher[U] = flatMapNamed(s"$this.flatMap(...)")(f)
    def flatMapNamed[U <: Tuple](name: String)(f: T => InputMatcher[U]): InputMatcher[U] = NamedInputMatcher(
      name,
      evt =>
        apply(evt).fold(_.flatMap(f)) {
          case Some(t) => f(t)
          case None => None
        }
    )
    def withFilter(f: T => Boolean) = filter(f)
    def filter(f: T => Boolean): InputMatcher[T] =
      NamedInputMatcher(s"$this.filter(...)", evt => apply(evt).fold(_.filter(f))(_.filter(f)))

    def named(s: String) = NamedInputMatcher(s, apply)
  }
  inline def NamedInputMatcher[T <: Tuple](name: String, inline f: EventType => InputMatcher[T] | Option[T]): InputMatcher[T] =
    new InputMatcher[T] {
      def apply(evt: EventType) = f(evt)
      override def toString = name
    }
  object InputMatcher {
    def failed[T <: Tuple]: InputMatcher[T] = NamedInputMatcher("Failed()", evt => None)
    def simple[T](t: T): InputMatcher[Tuple1[T]] = pure(Tuple1(t))
    def pure[T <: Tuple](t: T): InputMatcher[T] = NamedInputMatcher(s"Pure($t)", evt => Some(t))
    def any: InputMatcher[Tuple1[EventType]] = NamedInputMatcher("Any()", evt => Some(Tuple1(evt)))
    lazy val emptySuccess: InputMatcher[EmptyTuple] = NamedInputMatcher("()", evt => Some(EmptyTuple))
  }

  object syntax {
    def matchInput(expected: InputCode): InputMatcher[Tuple1[InputCode]] =
      NamedInputMatcher(s"Match($expected)", evt => Option.when(getInputCode(evt) == expected)(Tuple1(expected)))
    given Conversion[InputCode, InputMatcher[Tuple1[InputCode]]] = matchInput

    type AndThen[t <: Tuple, u <: Tuple] <: Tuple = (t, u) match {
      case (t1, Tuple1[u1]) => Tuple.Concat[t, u]
      case _ => Tuple.Concat[t, Tuple1[u]]
    }

    extension [T <: Tuple](input: InputMatcher[T]) {
      @targetName("matchAlternatives")
      def ||[U <: Tuple](input2: InputMatcher[U]): InputMatcher[T | U] = NamedInputMatcher(
        s"($input || $input2)",
        evt =>
          input(evt).fold(_ || input2) {
            case s @ Some(_) => s
            case _ => input2(evt)
          }
      )

      def ~>[U <: Tuple](input2: InputMatcher[U]) = input.andThen(input2)

      def andThen[U <: Tuple](
          input2: InputMatcher[U],
          tolerance: FiniteDuration = sequenceDefaultPatience
      ): InputMatcher[AndThen[T, U]] = {
        val inner = for {
          (ts1, i1) <- input.timestamped
          (ts2, i2) <- input2.timestamped
          if (ts2.toEpochMilli - ts1.toEpochMilli) < tolerance.toMillis
        } yield (i1, i2) match {
          case (t: Tuple, r2: Tuple1[?]) => (t ++ r2).asInstanceOf[AndThen[T, U]]
          case (t1, t2) => (t1 ++ Tuple1(t2)).asInstanceOf[AndThen[T, U]]
        }
        inner.named(s"($input :: $input2)")
      }

      def andThenWithDelay[U <: Tuple](
          input2: InputMatcher[U],
          delay: FiniteDuration = sequenceDefaultPatience
      ): InputMatcher[AndThen[T, U]] = {
        val inner = for {
          (ts, v1) <- input.timestamped
          (ts2, v2) <- input2.timestamped
          if ts2.toEpochMilli - ts.toEpochMilli >= delay.toMillis
        } yield (v1, v2) match {
          case (t: Tuple, r2: Tuple1[?]) => (t ++ r2).asInstanceOf[AndThen[T, U]]
          case (t1, t2) => (t1 ++ Tuple1(t2)).asInstanceOf[AndThen[T, U]]
        }
        inner.named(s"($input, delay($delay), $input2)")
      }

      @targetName("combine")
      def ~[U <: Tuple](input2: InputMatcher[U]): InputMatcher[(T, U)] =
        val res =
          (input.andThen(input2, combinationTolerance) || input2.andThen(input, combinationTolerance).map {
            case i2 *: i1 => (i1 ++ Tuple1(i2))
            case _ => EmptyTuple
          })
            .asInstanceOf[InputMatcher[(T, U)]] // something is very weird with the type of res, and scalac isn't helping
        NamedInputMatcher(s"($input ~ $input2)", res(_))

      @targetName("press")
      def ↓ : InputMatcher[T] = NamedInputMatcher(s"Press($input)", evt => if isDown(evt) then input(evt) else None)
      @targetName("release")
      def ↑ : InputMatcher[T] = NamedInputMatcher(s"Release($input)", evt => if !isDown(evt) then input(evt) else None)

      /** Recovers this input with the given InputMatcher in case of failure.
        *
        * Note that the recover parameter is a function, this is because of the iterative neature of InputMatchers, where the result of
        * processing an event might be follow up matcher, and you want to recover that one as well with the the same logic.
        */
      def recoverWith[U >: T <: Tuple](
          recover: PartialFunction[(EventType, InputMatcher[T]), InputMatcher[U]]
      ): InputMatcher[U] =
        NamedInputMatcher(
          s"Recoverable($input)",
          evt =>
            input(evt) match {
              case None if recover.isDefinedAt(evt -> input) => recover(evt -> input)
              case im: InputMatcher[T @unchecked] => im.recoverWith(recover)
              case other => other
            }
        )

      def ignoringFailuresFor(keys: Set[InputCode]): InputMatcher[T] =
        input.recoverWith {
          case (evt, input) if keys(getInputCode(evt)) => input
        }

      def ignoringFailuresFor(keys: InputCode*): InputMatcher[T] = ignoringFailuresFor(keys.toSet)

      def ignoreIntruderKeys: InputMatcher[T] = input.recoverWith((_, input) => input)

      def ignoringReleases: InputMatcher[T] = input.recoverWith((_, input) =>
        NamedInputMatcher(
          s"$input.ignoringRelases",
          evt =>
            input(evt) match {
              case None if !isDown(evt) => input
              case other => other
            }
        )
      )

      def timestamped: InputMatcher[(Instant, T)] = input.mapNamed(s"$input.stamped")(Instant.now(clock).nn -> _)
    }

    // this should be doable with regular inline, but be my guest fighting the compiler unpredictability around inline matches
    transparent inline def InputSequence(inline inputs: InputMatcher[?]*): InputMatcher[?] = ${
      InputManager.inputSequenceMacro[EventType, InputCode, InputMatcher[?]]('{ InputManager.this }, 'sequenceDefaultPatience)('inputs)
    }
    transparent inline def InputSequenceWithPatience(patience: FiniteDuration)(inline inputs: InputMatcher[?]*): InputMatcher[?] = ${
      InputManager.inputSequenceMacro[EventType, InputCode, InputMatcher[?]]('{ InputManager.this }, 'patience)('inputs)
    }

  }

  /** Internal implementation, all these are private */
  private object internal {

    /** Stores the running information necessary to process a stream of events for a given combination */
    class CombinationProcessor[T <: Tuple](combination: InputMatcher[T], action: T => Unit) {
      var state = combination
      def apply(evt: EventType): Unit = state(evt) match {
        case Some(r) =>
          onEventTransition(combination, evt, state, "trigger")
          state = combination
          action(r)
        case None =>
          onEventTransition(combination, evt, state, combination)
          state = combination //reset
        case im: InputMatcher[T @unchecked] =>
          onEventTransition(combination, evt, state, im)
          state = im
      }
    }
  }

}

object InputManager {
  import scala.quoted.*
  def inputSequenceMacro[
      EventType: Type,
      InputCode: Type,
      MatcherType <: InputManager[EventType, InputCode]#InputMatcher[?]: Type
  ](
      im: Expr[InputManager[EventType, InputCode]],
      patience: Expr[FiniteDuration]
  )(
      inputs: Expr[Seq[MatcherType]]
  )(using Quotes): Expr[MatcherType] = {
    import quotes.reflect.*

    val imTerm = im.asTerm

    val res = inputs match {
      case Varargs(inputs) =>
        inputs.reduce((im1, im2) =>
          val res = Select
            .unique(Select.unique(imTerm, "syntax"), "andThen")
            .appliedToType(im1.asTerm.tpe.asInstanceOf[AppliedType].args.head)
            .appliedTo(im1.asTerm)
            .appliedToType(im2.asTerm.tpe.asInstanceOf[AppliedType].args.head)
            .appliedTo(im2.asTerm, patience.asTerm)
            .asExprOf[MatcherType]
          // println(res.show)
          res
        )
      case _ =>
        report.error(s"Args must be explicit, but was ${inputs.show}", inputs)
        '{ ??? }
    }
    res
  }
}
