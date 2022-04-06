package guarana.input

import guarana.util.{DeclaringOwner, StateMachine}
import java.time.{Clock, Duration as JDuration, Instant}
import scala.annotation.targetName
import scala.concurrent.duration.*
import InputManager.*

object InputManager {
  enum InputDef[T] {
    case Simple(input: T)
    case Press(input: InputDef[T])
    case Release(input: InputDef[T])
    case Combined(inputs: Set[InputDef[T]])

    /** Specifies a duration through which no key should be pressed */
    case Delay(duration: FiniteDuration)
    case WaitFor(input: InputDef[T], patience: Duration, allowOtherKeys: Boolean = true, ignoreReleases: Boolean = true)
    case Sequence(patience: FiniteDuration, input: Seq[InputDef[T]], allowOtherKeys: Boolean = false, ignoreReleases: Boolean = true)
    case Ignoring(inner: InputDef[T], toIgnore: Set[T])

    def fold[S](s: S)(f: (s: S, input: InputDef[T]) => S): S = this match {
      case i: Simple[T] => f(s, i)
      case i @ Press(next) => f(f(s, next), i)
      case i @ Release(next) => f(f(s, next), i)
      case i @ Combined(inputs) => f(inputs.foldLeft(s)(f), i)
      case i: Delay[T] => f(s, i)
      case i @ WaitFor(next, _, _, _) => f(f(s, next), i)
      case i @ Sequence(_, inputs, _, _) => f(inputs.foldLeft(s)(f), i)
      case i @ Ignoring(inner, _) => f(f(s, inner), i)
    }
  }
}

trait InputManager[-EventType, InputCode] {
  import internal.*

  type Input = InputDef[InputCode]

  def clock: Clock

  /** When doing a sequential combination, such as A then B, this duration specifies the maximum time to await for the second input. Note
    * that this can be overriden in the actual combination
    */
  def sequenceDefaultPatience: FiniteDuration
  def combinationTolerance: FiniteDuration
  def getInputCode(evt: EventType): InputCode
  def isDown(evt: EventType): Boolean

  /** Overridable point that gets called by the input handling state machine. Intended mainly for debugging purposes */
  protected def onEventTransition(combination: Input, event: EventType, previousState: Any, newState: Any): Unit = {}

  private val combinationProcessors = collection.mutable.HashMap.empty[Input, CombinationProcessor]

  def recordCombination(combination: Input)(action: () => Unit): Unit = combinationProcessors(combination) =
    CombinationProcessor(combination, action)
  def dispatch(evt: EventType): Unit = combinationProcessors.valuesIterator foreach (_(evt))

  object syntax {
    given Conversion[InputCode, Input] = i => InputDef.Simple(i)

    extension (input: Input) {
      @targetName("combine")
      def ~(input2: Input) = (input, input2) match {
        case (InputDef.Combined(c1), InputDef.Combined(c2)) => InputDef.Combined(c1 ++ c2)
        case (InputDef.Combined(c1), c2) => InputDef.Combined(c1 + c2)
        case (c1, InputDef.Combined(c2)) => InputDef.Combined(c2 + c1)
        case (c1, c2) => InputDef.Combined(Set(c1, c2))
      } //InputDef.Combined(input, input2)
      @targetName("press")
      def ↓ = InputDef.Press(input)
      @targetName("release")
      def ↑ = InputDef.Release(input)

      def ignoring(keys: InputCode*) = InputDef.Ignoring(input, keys.toSet)
      def ignoring(keys: Set[InputCode]) = InputDef.Ignoring(input, keys)
    }

    def InputSequence(inputs: Input*): InputDef.Sequence[InputCode] =
      InputDef.Sequence(sequenceDefaultPatience, inputs.toVector, false, true)
    def InputSequence(patience: FiniteDuration, inputs: Input*): InputDef.Sequence[InputCode] =
      InputDef.Sequence(patience, inputs.toVector, false, true)

    export InputDef.Delay as delay
    def waitFor(
        input: InputDef[InputCode],
        patience: Duration,
        allowOtherKeys: Boolean = true,
        ignoreReleases: Boolean = true
    ): InputDef.WaitFor[InputCode] = InputDef.WaitFor(input, patience, allowOtherKeys, ignoreReleases)
  }

  /** Internal implementation, all these are private */
  private object internal {

    /** Extractor of [[EventType]] */
    object Event:
      def unapply(evt: EventType) = (getInputCode(evt), isDown(evt))

    /** Stores the running information necessary to process a stream of events for a given combination */
    class CombinationProcessor(combination: Input, action: () => Unit) extends StateMachine[EventType] {

      case class State(
          input: Input,
          acceptIntroduerInput: Boolean,
          resetsStateMachineOnMismatch: Boolean,
          ignoreInputReleases: Boolean,
          toIgnore: Set[InputCode]
      )

      def initState = selectState(State(combination, false, true, false, Set.empty), emit())

      def selectState(state: State, nextState: => Transition): Transition = state.input match {
        case v: InputDef.Simple[InputCode] => handleSimple(v, state, nextState)
        case InputDef.Press(input) => handlePressRelease(input, true, state, nextState)
        case InputDef.Release(input) => handlePressRelease(input, false, state, nextState)
        case v: InputDef.Combined[InputCode] => handleCombined(v, state, nextState)
        case v: InputDef.Delay[InputCode] => handleDelay(v, state, nextState)
        case v: InputDef.WaitFor[InputCode] => handleWaitFor(v, state, nextState)
        case v: InputDef.Sequence[InputCode] => handleSequence(v, state, nextState)
        case InputDef.Ignoring(inner, toIgnore) => selectState(state.copy(input = inner, toIgnore = toIgnore), nextState)
      }

      def handleSimple(keyCode: InputDef.Simple[InputCode], state: State, nextState: => Transition): Transition =
        inputTransition(state) { case Event(keyCode.input, _) => nextState }

      def handlePressRelease(input: Input, press: Boolean, state: State, nextState: => Transition): Transition = {
        val innerTransition = selectState(state.copy(input = input), nextState)
        inputTransition(state) {
          case evt @ Event(keyCode, `press`) if innerTransition.isDefinedAt(evt) => innerTransition(evt)
        }
      }

      /** Processing a Combined
        *
        * @param matched
        *   Represents an input that was already captured, so that only the other is left.
        */
      def handleCombined(
          input: InputDef.Combined[InputCode],
          state: State,
          nextState: => Transition
      ): Transition = {
        def rec(matched: Set[Input], nextDeadline: Option[Instant]): Transition = {
          given DeclaringOwner = DeclaringOwner(s"handleCombined(matched = $matched)", 127)

          if (matched.size == input.inputs.size) nextState
          else {
            val innerTransition = (input.inputs -- matched).iterator
              .map(i =>
                selectState(
                  state.copy(input = i, resetsStateMachineOnMismatch = false, acceptIntroduerInput = false),
                  rec(matched + i, Some(Instant.now(clock).nn.plusMillis(combinationTolerance.toMillis).nn))
                )
              )
              .reduce(_ orElse _)

            inputTransition(state) {
              case evt if innerTransition.isDefinedAt(evt) && (nextDeadline.isEmpty || nextDeadline.get.isAfter(Instant.now(clock))) =>
                innerTransition(evt)
            }
          }
        }
        rec(Set.empty, None)
      }

      def handleDelay(delay: InputDef.Delay[InputCode], state: State, nextState: => Transition): Transition = {
        val deadline = Instant.now(clock).nn.plusMillis(delay.duration.toMillis)
        inputTransition(state) {
          // when receiving an evt after the delay, we immediately forward it to the next state
          case evt if Instant.now(clock).nn.isAfter(deadline) => nextState(evt)
        }
      }

      def handleWaitFor(input: InputDef.WaitFor[InputCode], state: State, nextState: => Transition): Transition = {
        val deadline = Instant.now(clock).nn.plusMillis(input.patience.toMillis)
        val innerTransition = selectState(
          state.copy(input = input.input, acceptIntroduerInput = input.allowOtherKeys, ignoreInputReleases = input.ignoreReleases),
          nextState
        )
        inputTransition(state) {
          case evt if Instant.now(clock).nn.isBefore(deadline) && innerTransition.isDefinedAt(evt) => innerTransition(evt)
        }
      }

      def handleSequence(input: InputDef.Sequence[InputCode], state: State, nextState: => Transition): Transition = {
        def rec(ordinal: Int, deadline: Instant): Transition = {
          given DeclaringOwner = DeclaringOwner(s"handleSequence(ord = $ordinal)", 172)
          if (ordinal == input.input.length) nextState
          else {
            val innerTransition = selectState(
              state.copy(input.input(ordinal), acceptIntroduerInput = input.allowOtherKeys, ignoreInputReleases = input.ignoreReleases),
              rec(ordinal + 1, Instant.now(clock).nn.plusMillis(input.patience.toMillis).nn)
            )
            inputTransition(state) {
              // for ordinal 0, the very first input, we don't need to check the time
              case evt if (ordinal == 0 || Instant.now(clock).nn.isBefore(deadline)) && innerTransition.isDefinedAt(evt) =>
                innerTransition(evt)
            }
          }
        }
        rec(0, Instant.now(clock).nn)
      }

      def emit(): Transition = {
        try action()
        catch case scala.util.control.NonFatal(e) => e.printStackTrace()
        initState
      }

      def inputTransition(state: State)(f: PartialFunction[EventType, Transition])(using name: DeclaringOwner): Transition =
        namedTransition(s"$state - ${name.name}:${name.line}")(f.orElse {
          case Event(code, down) if state.toIgnore(code) || (state.ignoreInputReleases && !down) => current
          case _ if state.resetsStateMachineOnMismatch && !state.acceptIntroduerInput => initState
          case _ if state.acceptIntroduerInput => current
          // if we should not reset and intruders are not accepted, make it so this state doesn't handle it
        })

      override protected def onTransition(event: EventType, prevState: Transition, newState: Transition): Unit =
        onEventTransition(combination, event, prevState, newState)
    }
  }

  trait InputMatcher[T <: Tuple] {
    def apply(evt: EventType): InputMatcher[T] | Option[T]

    def map[U <: Tuple](f: T => U): InputMatcher[U] = evt => apply(evt) match {
      case opt: Option[T @unchecked] => opt.map(f)
      case m: InputMatcher[T @unchecked] => m.map(f)
    }

    def flatMap[U <: Tuple](f: T => InputMatcher[U]): InputMatcher[U] = evt => apply(evt) match {
      case opt: Option[T @unchecked] => opt match {
        case Some(t) => f(t)
        case None => None
      }
      case m: InputMatcher[T @unchecked] => m.flatMap(f)
    }
    def filter(f: T => Boolean): InputMatcher[T] = evt => apply(evt) match {
      case opt: Option[T @unchecked] => opt.filter(f)
      case m: InputMatcher[T @unchecked] => m.filter(f)
    }
  }
  object InputMatcher {
    def failed[T <: Tuple]: InputMatcher[T] = evt => None
    def simple[T](t: T): InputMatcher[Tuple1[T]] = pure(Tuple1(t))
    def pure[T <: Tuple](t: T): InputMatcher[T] = evt => Some(t)
  }
}
