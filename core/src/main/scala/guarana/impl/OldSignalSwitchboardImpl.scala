package guarana
package impl

import animation.TransitionType
import animation.Timeline
import animation.Timeline.KeyFrame
import guarana.impl.AgronaUtils.*
import guarana.{ExternalVar, Keyed, Var}
import org.agrona.collections.{Long2ObjectHashMap, LongHashSet}

import SignalSwitchboard.*

private[impl] class OldSignalSwitchboardImpl(
    val reporter: Reporter,
    val defaultValueProvider: DefaultVarValueProvider,
    val varsLookup: VarsLookup,
    val useReentrancyDetection: Boolean,
    val timers: animation.TimersDef
) extends SignalSwitchboard {
  import OldSignalSwitchboardImpl.*
  import State.*
  private val signalStates = new Long2ObjectHashMap[State](1024, 0.67f)

  /** Stores signals that should be updated when the key signal changes
    */
  private val signalDeps = new Long2ObjectHashMap[LongHashSet]()

  /** Stores signals whose current binding is dependent on the binding of the key Signal.
    */
  private val signalRels = new Long2ObjectHashMap[Relationships]()

  private val signalEvaluator = new Long2ObjectHashMap[Evaluator]()

  private val reentrancyDetector = new LongHashSet(32);

  private type ProjVar[t, u, instance] = Var[t]#Projection[u] { type ForInstance = instance }

  def get[T](v: ObsVal[T], instance: v.ForInstance): Entry[T] = {
    val s = Keyed(v, instance)
    (signalStates.get(s.id): @unchecked) match {
      case null => NotFound
      case Value(null) => null.asInstanceOf[T]
      case Value(value: T @unchecked) => value
      case Recompute(oldv) =>
        signalRels
          .get(s.id)
          .?(_.dependents `fastForeach` (e => remove(Keyed(e)))) //when recomputing the value, we gotta undo all the dependents

        if (reentrancyDetector.contains(s.id))
          throw new IllegalStateException(s"Detected evaluation loop in ${varsLookup.describe(v, instance)} ${s.descrString}")

        if (useReentrancyDetection) reentrancyDetector.add(s.id)
        val tracker = new TrackingContext(this, s)

        // before computing the value, we set the signalState to the oldValue in case the compute lambda has a self reference
        if (oldv != null) signalStates.put(s.id, Value(oldv))
        else signalStates.remove(s.id)

        val compute = signalEvaluator.get(s.id).asInstanceOf[Evaluator.Compute]
        val targetValue = compute.f(tracker).asInstanceOf[T]

        // relationships management
        val computedRels = Relationships(tracker.dependencies, tracker.dependents)
        tracker.dependents.fastForeach(l =>
          if (computedRels.dependencies.contains(l))
            throw new IllegalStateException(
              s"Var ${varsLookup.describe(s)} depends on ${varsLookup.describe(Keyed(l))} but it also has it as dependent, this will always lead to stack overflow"
            )
        )
        scribe.debug(
          s"recomputed signal ${varsLookup.describe(s)} ${s.descrString} to $targetValue, new relationships: $computedRels"
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
        
        if (result != oldv) {
          reporter.signalUpdated(this, v, instance, Option(oldv.asInstanceOf[T]), result, computedRels.dependencies, computedRels.dependents)
        }

        result

      case Transitioning(currValue, timer) =>
        currValue.asInstanceOf[T]

      case External => v.asInstanceOf[ExternalVar[T] { type ForInstance = v.ForInstance}].get(instance)
    }
  }

  private def startTransition[T](s: Keyed[ObsVal[T]], oldv: T | Null, targetValue: T, defn: TransitionType.Interp[T]): T = {
    import defn.*
    val min = oldv.nullFold(v => v, baseValue)
    val initValue = min

    if (initValue == targetValue) {
      signalStates.put(s.id, Value(targetValue))
      return initValue
    }

    val trn = Transition(delay, duration, initValue, targetValue, curve, defn.interp)
    // println(s"${Console.MAGENTA}Creating transition from $initValue -- to -- $targetValue${Console.RESET}")

    val computedRels = signalRels.get(s.id)
    val dependencies = computedRels.nullFold(_.dependencies, EmptyUnmodifiableLongHashSet)
    val dependents = computedRels.nullFold(_.dependents, EmptyUnmodifiableLongHashSet)

    lazy val anim: Timeline.Animation[timers.Timer] = Timeline(IArray(KeyFrame((delay + duration).toNanos, elapsed => {
      val curr = trn.valueAt(elapsed)
      // println(s"interpolating var after $elapsed, $initValue / $curr / $targetValue")
      signalStates.put(s.id, Transitioning(curr, anim.timer))
      propagateSignal(s, skipSelf = true)
      varsLookup.lookup(s) match {
        case null =>
        case (v, i) =>
          reporter.signalUpdated(this, v, i.asInstanceOf[v.ForInstance], Option(oldv.unn), curr, dependencies, dependents)
      }
    }, () => {
      signalStates.put(s.id, Value(targetValue))
      propagateSignal(s, skipSelf = true)
      varsLookup.lookup(s) match {
        case null =>
        case (v, i) =>
          reporter.signalUpdated(this, v, i.asInstanceOf[v.ForInstance], Option(oldv.unn), targetValue, dependencies, dependents)
      }
    })), Timeline.Cycles.SingleShot, updatesPerSecond, false)(timers)

    signalStates.put(s.id, Transitioning(initValue, anim.timer))
    anim.start()

    initValue
  }

  def remove(s: Keyed[ObsVal[Any]]): Unit = {
    signalStates.remove(s.id)
    unbindPrev(s)
    reporter.signalRemoved(this, s)
  }

  def update[T](v: Var[T], instance: v.ForInstance, value: T, transitionDef: TransitionType[T]): Unit = {
    // type VarProj[t, u] = Var[t]#Projection[u] { type ForInstance = v.ForInstance } // we must do this type alias because scala pat-mat has syntax bugs with A#B syntax
    // v match {
    //   case projection: VarProj[u, T] @unchecked =>
    //     val prev = get(projection.projected, instance).getOrElse(projection.projected.initialValue(instance))
    //     val newValue = projection.set(prev, value)
    //   case _ =>
    // }

    val s = Keyed(v, instance)
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
        // varsLookup.getExternal(s) == value
        case _ => false
      }
    ) {
      unbindPrev(s)
      val oldValue = currState.flatMap {
        case Value(v: T @ unchecked) => Some(v)
        case _ => None
      }
      transitionDef match {
        case TransitionType.Instant => signalStates.put(s.id, if v.isInstanceOf[ExternalVar[T]] then External else Value(value))
        case interp: (TransitionType.Interp[T] @unchecked) => startTransition(s, oldValue.orNull, value, interp)
      }
      propagateSignal(s)
      reporter.signalUpdated(this, v, instance, oldValue, value, EmptyUnmodifiableLongHashSet, EmptyUnmodifiableLongHashSet)
    }
  }

  def externalPropertyChanged[T](v: ExternalObsVal[T], instance: v.ForInstance, oldValue: Option[T]): Unit =
    // an external change doesn't mean a voluntary (by the user) change on the behavior of the signal (whether compute or set)
    // so we don't unbind its current state and instead just propagate the signal invalidation, unless there is no current state, in
    // which case it means the external signal was never observed, and we need to setup an initial state so signal propagation works
    val s = Keyed(v, instance)
    if !signalStates.containsKey(s.id) then signalStates.put(s.id, External)
    propagateSignal(s)
    reporter.signalUpdated(this, v, instance, oldValue, v.get(instance), EmptyUnmodifiableLongHashSet, EmptyUnmodifiableLongHashSet)

  def bind[T](v: Var[T], instance: v.ForInstance, transitionDef: TransitionType[T])(compute: SignalSwitchboard => T): Unit = {
    val s = Keyed(v, instance)
    val prevValueOrNull = get(v, instance).orNull
    unbindPrev(s)
    signalStates.put(s.id, Recompute(prevValueOrNull))
    signalEvaluator.put(s.id, Evaluator.Compute(compute, transitionDef))
    propagateSignal(s)
  }

  private val depth = new java.util.concurrent.atomic.AtomicInteger(0)
  private def propagateSignal[T](s: Keyed[ObsVal[T]], skipSelf: Boolean = false): Unit = {
    // due to how propagating signal works, where the set of dependencies is iterated, it is entirely possible to find during
    // the iteration a signal that was removed, hence why we check here if that's the case by checking the state
    if !signalStates.containsKey(s.id) then return

    val (v, i) = varsLookup.lookup(s).unn

    scribe.debug(s"${"  " * depth.getAndIncrement()}propagating invalidation for ${varsLookup
      .describe(s)} ${s.descrString} ${signalRels.get(s.id).nullFold(_.descr, "No-Rels")}")
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
          case External =>
            val prev = v.asInstanceOf[ExternalVar[Any] { type ForInstance = i.type }].get(i)
            signalStates.put(s.id, Recompute(prev))
        }
      }
    }
    signalDeps.get(s.id).?(_.fastForeach(l => propagateSignal(Keyed(l))))

    // report at the end of the process, so that all of `s` dependencies get reported and invalidated first
    reporter.signalInvalidated(this, v, i.asInstanceOf[v.ForInstance])
    scribe.debug(s"${"  " * depth.decrementAndGet()}done propagating invalidation for ${varsLookup.describe(s)} ${s.descrString}")
  }

  def relationships[T](v: ObsVal[T], instance: v.ForInstance) = signalRels.get(Keyed(v, instance).id).toOption

  private def unbindPrev[T](s: Keyed[ObsVal[T]]): Unit = {
    signalStates.get(s.id).? {
      case Transitioning(_, timer) => timer.asInstanceOf[timers.Timer].stop()
      case _ =>
    }
    signalEvaluator.remove(s.id) match {
      case _: Evaluator.Compute =>
        signalRels.remove(s.id) match {
          case null =>
          case Relationships(deps, denpts) =>
            denpts `fastForeach` (l => remove(Keyed(l)))
            deps `fastForeach` (dep => signalDeps.get(dep).?(_.remove(s.id)))
        }
      case _ =>
    }
  }

  def snapshot(newReporter: Reporter): SignalSwitchboard = {
    val res = new OldSignalSwitchboardImpl(newReporter, defaultValueProvider, varsLookup, useReentrancyDetection, timers)
    res.signalStates `putAll` signalStates
    res.signalDeps `putAll` signalDeps
    res.signalEvaluator `putAll` signalEvaluator
    res.signalRels `putAll` signalRels
    res
  }
}
object OldSignalSwitchboardImpl {
  private[impl] val EmptyUnmodifiableLongHashSet = new LongHashSet {}

  private[impl] enum State {
    case Value(value: Any)
    case Recompute(oldValue: Any)
    case External
    case Projected
    case Transitioning(currValue: Any, timer: Any)
  }

  private[impl] enum Evaluator {
    case Set
    case SetWithTransition(transitionDef: TransitionType[?])
    case Compute(f: SignalSwitchboard => Any, transitionDef: TransitionType[?])
  }
}


