package guarana
package impl

import animation.TransitionType
import animation.Timeline
import animation.Timeline.KeyFrame
import org.agrona.collections.{Long2ObjectHashMap, LongHashSet}
import scala.jdk.CollectionConverters.*
import AgronaUtils.*
import SignalSwitchboard.*
import scala.concurrent.duration.FiniteDuration


trait SignalSwitchboard[Signal[+T] <: util.Unique] {

  /** Obtains the current value of the signal
    */
  def apply[T](s: Keyed[Signal[T]]): T = get(s).getOrElse(throw new IllegalArgumentException(s"$s is not defined"))

  /** Conditional getter for a signal's value.
    */
  def get[T](s: Keyed[Signal[T]]): Entry[T]

  /** Obtains the current value of the signal if the signal is found, otherwise register it with initialValue and return it.
    */
  def getOrElseUpdate[T](s: Keyed[Signal[T]], initValue: => T): T = {
    get(s) match {
      case NotFound =>
        val result = initValue
        update(s, result, TransitionType.Instant)
        result
      case res: T @unchecked => res
    }
  }

  /** Change the value of the signal, propagating changes as needed
    */
  def update[T](s: Keyed[Signal[T]], value: T, transitionDef: TransitionType[T] = TransitionType.Instant): Unit

  /** Notifies the switchboard that an externally tracked value changed. We can't accurately track the old value, that'll depend on the
    * external property system.
    */
  def externalPropertyChanged[T](s: Keyed[Signal[T]], oldValue: Option[T]): Unit

  /** Bind a signal to a value computed from other signals. The context passed to the compute function will detect which signals were read
    * and which were written. The former will become dependencies for this signal (meaning that if any of those changes, this signal will be
    * updated in turn) while the later will become dependents (which means that when the current value of the signal changes, they will be
    * disposed)
    * @param s
    *   The signal to be bound
    * @param deps
    *   Dependencies for this signal
    * @param compute
    *   the function that computes the new value. Note that the elements of the Array correspond to the dependencies, but we must use an
    *   array instead of scala's Function type because there may be more than 22 dependencies.
    */
  def bind[T](s: Keyed[Signal[T]], transitionDef: TransitionType[T])(compute: SignalSwitchboard[Signal] => T): Unit

  /** Removes the passed signal, removing all internal state as well as dependent signals.
    */
  def remove(s: Keyed[Signal[Any]]): Unit

  /** Returns a new SignalSwitchboard which is a copy of this one.
    */
  def snapshot(newReporter: Reporter[Signal]): SignalSwitchboard[Signal]

  def relationships(s: Keyed[Signal[Any]]): Option[Relationships[Signal]]
}

private[impl] class SignalSwitchboardImpl[Signal[+T] <: util.Unique](
    val reporter: Reporter[Signal],
    val signalDescriptor: SignalDescriptor[Signal],
    val useReentrancyDetection: Boolean,
    val timers: animation.TimersDef
) extends SignalSwitchboard[Signal] {
  import State.*
  private val signalStates = new Long2ObjectHashMap[State](1024, 0.67f)

  /** Stores signals that should be updated when the key signal changes
    */
  private val signalDeps = new Long2ObjectHashMap[LongHashSet]()

  /** Stores signals whose current binding is dependent on the binding of the key Signal.
    */
  private val signalRels = new Long2ObjectHashMap[Relationships[Signal]]()

  private val signalEvaluator = new Long2ObjectHashMap[Evaluator]()

  private val reentrancyDetector = new LongHashSet(32);

  def get[T](s: Keyed[Signal[T]]): Entry[T] = {
    (signalStates.get(s.id): @unchecked) match {
      case null => NotFound
      case Value(null) => null.asInstanceOf[T]
      case Value(value: T @unchecked) => value
      case Recompute(oldv) =>
        signalRels
          .get(s.id)
          .?(_.dependents `fastForeach` (e => remove(Keyed(e)))) //when recomputing the value, we gotta undo all the dependents

        if (reentrancyDetector.contains(s.id))
          throw new IllegalStateException(s"Detected evaluation loop in ${signalDescriptor.describe(s)} ${s.descrString}")

        if (useReentrancyDetection) reentrancyDetector.add(s.id)
        val tracker = new TrackingContext(s)

        // before computing the value, we set the signalState to the oldValue in case the compute lambda has a self reference
        if (oldv != null) signalStates.put(s.id, Value(oldv))
        else signalStates.remove(s.id)

        val compute = signalEvaluator.get(s.id).asInstanceOf[Evaluator.Compute[Signal]]
        val targetValue = compute.f(tracker).asInstanceOf[T]

        // relationships management
        val computedRels = Relationships[Signal](tracker.dependencies, tracker.dependents)
        tracker.dependents.fastForeach(l =>
          if (computedRels.dependencies.contains(l))
            throw new IllegalStateException(
              s"Var ${signalDescriptor.describe(s)} depends on ${signalDescriptor.describe(Keyed(l))} but it also has it as dependent, this will always lead to stack overflow"
            )
        )
        scribe.debug(
          s"recomputed signal ${signalDescriptor.describe(s)} ${s.descrString} to $targetValue, new relationships: $computedRels"
        )
        signalRels.put(s.id, computedRels)
        tracker.dependencies.fastForeach(dep =>
          var deps = signalDeps.get(dep)
          if deps == null then
            deps = new LongHashSet()
            signalDeps.put(dep, deps)
          deps `add` s.id
        )

        val result = compute.transitionDef match {
          case TransitionType.Instant =>
            signalStates.put(s.id, Value(targetValue))
            targetValue

          case defn: (TransitionType.Interp[T] @unchecked) => startTransition[T](s, oldv.asInstanceOf, targetValue, defn)
        }

        if (useReentrancyDetection) reentrancyDetector.remove(s.id)
        
        if (result != oldv)
          reporter.signalUpdated(this, s, Option(oldv), result, computedRels.dependencies, computedRels.dependents)

        result

      case Transitioning(currValue, timer) =>
        currValue.asInstanceOf[T]

      case External => signalDescriptor.getExternal(s)
    }
  }

  private def startTransition[T](s: Keyed[Signal[T]], oldv: T | Null, targetValue: T, defn: TransitionType.Interp[T]): T = {
    import defn.*
    val min = oldv.nullFold(v => v, baseValue)
    val initValue = min

    if (initValue == targetValue) {
      signalStates.put(s.id, Value(targetValue))
      return initValue
    }

    val trn = Transition(delay, duration, initValue, targetValue, curve, defn.interp)
    // println(s"${Console.MAGENTA}Creating transition from $initValue -- to -- $targetValue${Console.RESET}")

    val computedRels = signalRels.get(s.id).unn

    lazy val anim: Timeline.Animation[timers.Timer] = Timeline(IArray(KeyFrame((delay + duration).toNanos, elapsed => {
      val curr = trn.valueAt(elapsed)
      // println(s"interpolating var after $elapsed, $initValue / $curr / $targetValue")
      signalStates.put(s.id, Transitioning(curr, anim.timer))
      propagateSignal(s, skipSelf = true)
      reporter.signalUpdated(this, s, Option(oldv), curr, computedRels.dependencies, computedRels.dependents)
    }, () => {
      signalStates.put(s.id, Value(targetValue))
      propagateSignal(s, skipSelf = true)
      reporter.signalUpdated(this, s, Option(oldv), targetValue, computedRels.dependencies, computedRels.dependents)
    })), Timeline.Cycles.SingleShot, updatesPerSecond, false)(timers)

    signalStates.put(s.id, Transitioning(initValue, anim.timer))
    anim.start()

    initValue
  }

  def remove(s: Keyed[Signal[Any]]): Unit = {
    signalStates.remove(s.id)
    unbindPrev(s)
    reporter.signalRemoved(this, s)
  }

  def update[T](s: Keyed[Signal[T]], value: T, transitionDef: TransitionType[T]): Unit = {
    val currState = signalStates.get(s.id).toOption
    if (
      !currState.exists {
        case Value(`value`) => true
        case External =>
          /* external properties must always update, since we have no way of tracking for a value change because we get notified of update after the underlying
           value was modified. There's no way of weaving this. In the future we might investigate how to do it, but we have to be mindful of allocations as well.
           ExternalVar implementors are encouraged to make this check on their own before notifying the switchboard.
           */
          false
        // signalDescriptor.getExternal(s) == value
        case _ => false
      }
    ) {
      unbindPrev(s)
      val oldValue = currState.flatMap {
        case Value(v: T @ unchecked) => Some(v)
        case _ => None
      }
      transitionDef match {
        case TransitionType.Instant => signalStates.put(s.id, if signalDescriptor.isExternal(s) then External else Value(value))
        case interp: (TransitionType.Interp[T] @unchecked) => startTransition(s, oldValue.orNull, value, interp)
      }
      propagateSignal(s)
      reporter.signalUpdated(this, s, oldValue, value, EmptyUnmodifiableLongHashSet, EmptyUnmodifiableLongHashSet)
    }
  }

  def externalPropertyChanged[T](s: Keyed[Signal[T]], oldValue: Option[T]): Unit =
    // an external change doesn't mean a voluntary (by the user) change on the behavior of the signal (whether compute or set)
    // so we don't unbind its current state and instead just propagate the signal invalidation, unless there is no current state, in
    // which case it means the external signal was never observed, and we need to setup an initial state so signal propagation works
    if !signalStates.containsKey(s.id) then signalStates.put(s.id, External)
    propagateSignal(s)
    reporter.signalUpdated(this, s, oldValue, signalDescriptor.getExternal(s), EmptyUnmodifiableLongHashSet, EmptyUnmodifiableLongHashSet)

  def bind[T](s: Keyed[Signal[T]], transitionDef: TransitionType[T])(compute: SignalSwitchboard[Signal] => T): Unit = {
    unbindPrev(s)
    signalStates.put(s.id, Recompute(get(s).orNull))
    signalEvaluator.put(s.id, Evaluator.Compute[Signal](compute, transitionDef))
    propagateSignal(s)
  }

  private val depth = new java.util.concurrent.atomic.AtomicInteger(0)
  private def propagateSignal(s: Keyed[Signal[Any]], skipSelf: Boolean = false): Unit = {
    // due to how propagating signal works, where the set of dependencies is iterated, it is entirely possible to find during
    // the iteration a signal that was removed, hence why we check here if that's the case by checking the state
    if !signalStates.containsKey(s.id) then return

    scribe.debug(s"${"  " * depth.getAndIncrement()}propagating invalidation for ${signalDescriptor
      .describe(s)} ${s.descrString} ${signalRels.get(s.id).nullFold(_.descr(signalDescriptor), "No-Rels")}")
    if (!skipSelf) {
      signalEvaluator.get(s.id).? { compute =>
        signalStates.get(s.id) match {
          case null => signalStates.put(s.id, Recompute(null))
          case _: Recompute => //if already recompute, do nothing
          case Transitioning(currValue, timer) =>
            timer.asInstanceOf[timers.Timer].stop()
            // println(s"timer interrupted at $currValue")
            signalStates.put(s.id, Recompute(currValue))
          case Value(oldv) => signalStates.put(s.id, Recompute(oldv))
          case External => signalStates.put(s.id, Recompute(signalDescriptor.getExternal(s)))
        }
      }
    }
    signalDeps.get(s.id).?(_.fastForeach(l => propagateSignal(Keyed(l))))

    // report at the end of the process, so that all of `s` dependencies get reported and invalidated first
    reporter.signalInvalidated(this, s)
    scribe.debug(s"${"  " * depth.decrementAndGet()}done propagating invalidation for ${signalDescriptor.describe(s)} ${s.descrString}")
  }

  def relationships(s: Keyed[Signal[Any]]) = signalRels.get(s.id).toOption

  private def unbindPrev(s: Keyed[Signal[Any]]): Unit = {
    signalStates.get(s.id).? {
      case Transitioning(_, timer) => timer.asInstanceOf[timers.Timer].stop()
      case _ =>
    }
    signalEvaluator.remove(s.id) match {
      case _: Evaluator.Compute[Signal] =>
        signalRels.remove(s.id) match {
          case null =>
          case Relationships(deps, denpts) =>
            denpts `fastForeach` (l => remove(Keyed(l)))
            deps `fastForeach` (dep => signalDeps.get(dep).?(_.remove(s.id)))
        }
      case _ =>
    }
  }

  def snapshot(newReporter: Reporter[Signal]): SignalSwitchboard[Signal] = {
    val res = new SignalSwitchboardImpl[Signal](newReporter, signalDescriptor, useReentrancyDetection, timers)
    res.signalStates `putAll` signalStates
    res.signalDeps `putAll` signalDeps
    res.signalEvaluator `putAll` signalEvaluator
    res.signalRels `putAll` signalRels
    res
  }

  private val outerSb = this
  class TrackingContext[T](forSignal: Keyed[Signal[T]]) extends SignalSwitchboard[Signal] {
    val dependencies = new LongHashSet(4)
    val dependents = new LongHashSet(4)
    def get[T](s: Keyed[Signal[T]]) = {
      if (forSignal != s) dependencies `add` s.id
      outerSb.get(s)
    }
    def update[T](s: Keyed[Signal[T]], value: T, transitionDef: TransitionType[T]) = {
      if (forSignal != s) dependents `add` s.id
      outerSb.update(s, value, transitionDef)
    }
    def bind[T](s: Keyed[Signal[T]], transitionDef: TransitionType[T])(compute: SignalSwitchboard[Signal] => T) = {
      if (forSignal != s) dependents `add` s.id
      outerSb.bind(s, transitionDef)(compute)
    }
    def externalPropertyChanged[T](s: Keyed[Signal[T]], oldValue: Option[T]): Unit = outerSb.externalPropertyChanged(s, oldValue)
    def relationships(s: Keyed[Signal[Any]]) = outerSb.relationships(s)
    def remove(s: Keyed[Signal[Any]]) = outerSb.remove(s)
    def snapshot(newReporter: Reporter[Signal]) = outerSb.snapshot(newReporter)
  }
}
object SignalSwitchboard {
  type Entry[T] = T | NotFound.type
  object NotFound

  private[impl] val EmptyUnmodifiableLongHashSet = new LongHashSet {}

  extension [T](e: Entry[T]) {
    inline def getOrElse[U >: T](inline r: U): U = e match
      case NotFound => r
      case other: T => other

    inline def isEmpty: Boolean = e == NotFound
    inline def orNull: T | Null = if e == NotFound then null else e.asInstanceOf[T]
    // inline def foreach(inline f: T => Unit): Unit = if e != NotFound then f(e.asInstanceOf[T])
  }

  private[impl] case class Transition[T](delay: FiniteDuration, duration: FiniteDuration, min: T, max: T, curve: animation.Curve, interp: animation.Interpolator[T]) {
    private val delayNs = delay.toNanos
    private val durationNs = duration.toNanos
    def valueAt(tNanos: Long): T =
      if (tNanos < delayNs) min
      else interp.interpolate(min, max, curve(tNanos.toDouble / durationNs))
  }

  private[impl] enum State {
    case Value(value: Any)
    case Recompute(oldValue: Any)
    case External
    case Transitioning(currValue: Any, timer: Any)
    // case Transition(delayNs: Long, from: Any, to: Any, curr: Any, curve: animation.Curve, interp: animation.Interpolator[Any], timer: Any)
  }

  private[impl] enum Evaluator {
    case Set
    case SetWithTransition(transitionDef: TransitionType[?])
    case Compute[Signal[+T] <: util.Unique](f: SignalSwitchboard[Signal] => Any, transitionDef: TransitionType[?])
  }

  case class Relationships[Signal[+T] <: util.Unique] private[impl] (dependencies: LongHashSet, dependents: LongHashSet) {
    def descr(sd: SignalDescriptor[Signal]): String = {
      val depsStr = dependencies.asScala.map(id => Keyed(id).descrString).mkString(", ")
      val dpntsStr = dependents.asScala.map(id => Keyed(id).descrString).mkString(", ")
      s"Relationships(deps=$depsStr, dpnts=$dpntsStr)"
    }
  }

  trait Reporter[Signal[+T] <: util.Unique] {
    def signalRemoved(sb: SignalSwitchboard[Signal], s: Keyed[Signal[Any]]): Unit
    def signalUpdated[T](
        sb: SignalSwitchboard[Signal],
        s: Keyed[Signal[T]],
        oldValue: Option[T],
        newValue: T,
        dependencies: LongHashSet,
        dependents: LongHashSet
    ): Unit
    def signalInvalidated(sb: SignalSwitchboard[Signal], s: Keyed[Signal[Any]]): Unit
  }

  trait SignalDescriptor[Signal[+T] <: util.Unique] {
    def isExternal[T](s: Keyed[Signal[T]]): Boolean
    def getExternal[T](s: Keyed[Signal[T]]): T
    def describe[T](s: Keyed[Signal[T]]): String
  }

  def apply[Signal[+T] <: util.Unique](
      reporter: Reporter[Signal],
      signalDescriptor: SignalDescriptor[Signal],
      useReentrancyDetection: Boolean,
      timers: animation.TimersDef
  ): SignalSwitchboard[Signal] =
    new SignalSwitchboardImpl(reporter, signalDescriptor, useReentrancyDetection, timers)
}

/** Specialized SignalSwitchboard that doesn't copy the original one until a write is performed. */
class CopyOnWriteSignalSwitchboard[Signal[+T] <: util.Unique](
    val copy: SignalSwitchboard[Signal],
    val reporter: SignalSwitchboard.Reporter[Signal]
) extends SignalSwitchboard[Signal] {
  var copied: SignalSwitchboard[Signal] | Null = null
  inline def theInstance = if (copied != null) copied.asInstanceOf[SignalSwitchboard[Signal]] else copy
  private def createCopy() = if (copied == null) copied = copy.snapshot(reporter)
  def get[T](s: Keyed[Signal[T]]) = theInstance.get(s)
  def update[T](s: Keyed[Signal[T]], value: T, transitionDef: TransitionType[T]) = {
    createCopy()
    theInstance.update(s, value, transitionDef)
  }
  def externalPropertyChanged[T](s: Keyed[Signal[T]], oldValue: Option[T]): Unit = theInstance.externalPropertyChanged(s, oldValue)
  def bind[T](s: Keyed[Signal[T]], transitionDef: TransitionType[T])(compute: SignalSwitchboard[Signal] => T) = {
    createCopy()
    theInstance.bind(s, transitionDef)(compute)
  }
  def relationships(s: Keyed[Signal[Any]]) = theInstance.relationships(s)
  def remove(s: Keyed[Signal[Any]]) = {
    createCopy()
    theInstance.remove(s)
  }
  def snapshot(newReporter: SignalSwitchboard.Reporter[Signal]) = CopyOnWriteSignalSwitchboard(theInstance, newReporter)
}
