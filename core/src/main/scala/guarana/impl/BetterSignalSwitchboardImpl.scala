package guarana
package impl

import animation.TransitionType
import animation.Timeline
import animation.Timeline.KeyFrame
import guarana.impl.AgronaUtils.*
import guarana.{ExternalVar, Keyed, Var}
import org.agrona.collections.{Long2ObjectHashMap, LongHashSet}

import SignalSwitchboard.*
import java.util.function.LongFunction

class BetterSignalSwitchboardImpl(
    val reporter: Reporter,
    val defaultValueProvider: DefaultVarValueProvider,
    val varsLookup: VarsLookup,
    val useReentrancyDetection: Boolean,
    val timers: animation.TimersDef
) extends SignalSwitchboard {

  private[impl] class Signal[T](var currValue: State[T]) {
    var reactToMyChanges: LongHashSet | Null = null
    def instantiatedReactToMyChanges: LongHashSet = {
      if (reactToMyChanges == null) reactToMyChanges = LongHashSet()
      reactToMyChanges.unn
    }
  }

  private[impl] enum State[+T] {
    case Value(value: T)
    case Recompute(prevValue: Option[T], prevRelationships: Relationships | Null, f: SignalSwitchboard => T, transitionDef: TransitionType[T])
    case BindingResult(value: T, relationships: Relationships, f: SignalSwitchboard => T, transitionDef: TransitionType[T])
    case Transitioning(curr: T, timer: timers.Timer, to: Value[T] | BindingResult[T])
    case External
    case Projections(baseValue: T)
    /** This value represents a signal that has been recorded as consequence of a binding, but this switchboard hasn't observed via a setter
      * yet
      */
    case Unseen

    def relationshipsOpt: Option[Relationships] = this match {
      case State.BindingResult(relationships = rels) => Some(rels)
      case State.Transitioning(to = State.BindingResult(relationships = rels)) => Some(rels)
      case _ => None
    }

    def knownValue(v: ObsVal[? >: T], instance: v.ForInstance): Entry[T] = this match {
      case Value(v) => v
      case BindingResult(value = v) => v
      case Transitioning(curr = v) => v
      case Projections(projectedBaseValue) =>
        val baseValue = v match {
          case eo: (ExternalObsVal[T] {  type ForInstance = v.ForInstance }) @unchecked => eo.get(instance)
          case _ => projectedBaseValue
        }
        val vv = v.asInstanceOf[Var.Aux[T, v.ForInstance]]
        vv.projections.foldLeft[Entry[T]](baseValue) { case (base, proj: vv.Projection[u] @unchecked) =>
          signals.get(Keyed(proj, instance).id) match {
            case null => base // if the projection was never observed, it doesn't interact
            case s: Signal[`u`] @unchecked =>
              // if it has been observed but it's in Unseen state (maybe got unbound) then it also doesn't interact
              s.currValue match {
                case Unseen => base
                case _ =>
                  // if even one binding is in an unresolved state, then we are also unknown and we have to be
                  // fully evaluate with get
                  s.currValue.knownValue(proj, instance) match {
                    case NotFound => NotFound
                    case value => proj.set(base.asInstanceOf[T], value.asInstanceOf[u]).asInstanceOf[Entry[T]]
                  }
              }
          }
        }
        baseValue
      case External => v.asInstanceOf[ExternalVar[T] { type ForInstance = v.ForInstance }].get(instance)
      case _ => NotFound
    }
  }

  private type ProjVar[t, u, instance] = Var[t]#Projection[u] { type ForInstance = instance }
  extension [T](v: ObsVal[T]) {
    private def projected[U]: Var.Aux[U, v.ForInstance] | Null = v match {
      case pv: ProjVar[U, T, v.ForInstance] @unchecked => pv.projected
      case _ => null
    }
    @annotation.tailrec
    private def foreachProjectedParent(f: Var.Aux[?, v.ForInstance] => Unit): Unit = {
      v match {
        case pv: ProjVar[?, T, v.ForInstance] @unchecked =>
          f(pv.projected)
          pv.projected.foreachProjectedParent(f)
        case _ =>
      }
    }
  }

  private[impl] val signals = new Long2ObjectHashMap[Signal[?]](1024, 0.67f)
  private val reentrancyDetector = new LongHashSet(32);
  private val newSignal: LongFunction[Signal[?]] = (_: Long) => Signal(State.Unseen)

  override def get[T](v: ObsVal[T], instance: v.ForInstance): Entry[T] = {
    val s = Keyed(v, instance)
    signals.get(s.id) match {
      case null =>
        v match {
          case proj: ProjVar[?, T, v.ForInstance] @unchecked => proj.get(apply(proj.projected, instance))
          case _ => NotFound
        }
        
      case state: Signal[T] @unchecked =>
        Debug.elidable {
          scribe.debug(s"Evaluating Var($instance, $v), signal ${state.currValue}")
        }
        state.currValue match {
          case State.Recompute(oldv, prevRels, compute, transition) =>
            val vv = v.asInstanceOf[Var.Aux[T, v.ForInstance]]
            prevRels.?(_.dependents fastForeach (e => remove(Keyed(e)))) //when recomputing the value, we gotta undo all the dependents

            if (reentrancyDetector.contains(s.id))
              throw new IllegalStateException(s"Detected evaluation loop in ${varsLookup.describe(v, instance)} ${s.descrString}")
            if (useReentrancyDetection) reentrancyDetector.add(s.id)

            val tracker = new TrackingContext(this, s)

            // before computing the value, we set the signalState to the oldValue in case the compute lambda has a self reference
            oldv.fold(state.currValue = State.Unseen)(oldv => state.currValue = State.Value(oldv))
            val targetValue = compute(tracker)

            // relationships management
            val computedRels = Relationships(tracker.dependencies, tracker.dependents)
            tracker.dependents.fastForeach(l =>
              if (computedRels.dependencies.contains(l))
                throw new IllegalStateException(
                  s"Var ${varsLookup.describe(s)} depends on ${varsLookup.describe(Keyed(l))} but it also has it as dependent, this will always lead to stack overflow"
                )
            )
            Debug.elidable {
              scribe.debug(
                s"recomputed signal ${varsLookup.describe(s)} ${s.descrString} to $targetValue, new relationships: $computedRels"
              )
            }
            tracker.dependencies fastForeach { dep =>
              signals.computeIfAbsent(dep, newSignal).instantiatedReactToMyChanges.add(s.id)
            }

            updateSpecific(vv, instance, state, targetValue)
            state.currValue = State.BindingResult(targetValue, computedRels, compute, transition)

            val currGetResult = transition match
              case defn: TransitionType.Interp[T] @unchecked => startTransition(s.asInstanceOf[Keyed[Var[T]]], oldv, state, defn)
              case _ => targetValue

            if (useReentrancyDetection) reentrancyDetector.remove(s.id)

            if (oldv.exists(_ != currGetResult)) {
              reportUpdate(v, instance, oldv, currGetResult)

              vv.projections.foreach { case p: vv.Projection[u] =>
                reportUpdate(p, instance, oldv.map(p.get), p.get(currGetResult))
              }
            }

            currGetResult

          case State.Projections(projectedBaseValue) =>
            val baseValue = v match {
              case eo: (ExternalObsVal[T] {  type ForInstance = v.ForInstance }) @unchecked => eo.get(instance)
              case _ => projectedBaseValue
            }
            val vv = v.asInstanceOf[Var.Aux[T, v.ForInstance]]
            vv.projections.foldLeft(baseValue) { case (baseValue, proj: vv.Projection[u] @unchecked) =>
              signals.get(Keyed(proj, instance).id) match {
                case null => baseValue // if we know nothing about the projection use baseValue
                case s: Signal[`u`] @unchecked => s.currValue match {
                  case State.Unseen => baseValue // if the signal was added as unseen, use baseValue
                  case _ => proj.set(baseValue, apply(proj, instance))
                }
              }
            }

          case other => other.knownValue(v, instance)
        }
    }
  }

  override def update[T](v: Var[T], instance: v.ForInstance, value: T, transitionDef: TransitionType[T]): Unit = {
    val s = Keyed(v, instance)

    val signal = signals.computeIfAbsent(s.id, newSignal).asInstanceOf[Signal[T]]

    if (!v.isInstanceOf[MutContentVar[?]]) {
      signal.currValue match {
        case State.Value(`value`) => return // early exit
        case _ =>
      }
    }

    unbindPrev(s)
    val oldValue = signal.currValue.knownValue(v, instance).entityToOption

    transitionDef match {
      case TransitionType.Instant => updateSpecific(v, instance, signal, value)
      case defn: TransitionType.Interp[T] @unchecked =>
        signal.currValue = State.Value(value) // set the value directly to the signal so the transition knows
        startTransition(s, oldValue, signal, defn)
    }

    signals.put(s.id, signal) // make sure the signal is there since unbind can remove undepended signals

    v.projected.?(bindToProjections(_, instance))

    propagateSignalInvalidation(s, skipSelf = true, propagateToProjections = true)
    reportUpdate(v, instance, oldValue, value)

    v.projections.foreach { case p: v.Projection[u] =>
      reportUpdate(p, instance, oldValue.map(p.get), p.get(value))
    }
  }

  private def updateSpecific[T](v: Var[T], instance: v.ForInstance, signal: Signal[T], value: T): Unit = {
    v match {
      case _: (ExternalVar[T] { type ForInstance = v.ForInstance }) @unchecked => signal.currValue = State.External
      case _ => signal.currValue = State.Value(value)
    }
    Debug.elidable {
      scribe.debug(s"Var($instance, $v) updated to ${signal.currValue}")
    }
  }

  override def externalPropertyChanged[T](v: ExternalObsVal[T], instance: v.ForInstance, oldValue: Option[T]): Unit = {
    // an external change doesn't mean a voluntary (by the user) change on the behavior of the signal (whether compute or set)
    // so we don't unbind its current state and instead just propagate the signal invalidation. It there's no current state it means
    // no actually observable var is depending on this, so we can remain oblivious to its contents.
    val s = Keyed(v, instance)
    signals.get(s.id).?{ signal => 
      Debug.elidable(scribe.debug(s"${varsLookup.describe(s)} in state ${signal.currValue} was externally changed"))
      propagateSignalInvalidation(s, skipSelf = false, propagateToProjections = true)
      reportUpdate(v, instance, oldValue, v.get(instance))
    }
  }

  override def bind[T](v: Var[T], instance: v.ForInstance, transitionDef: TransitionType[T])(compute: SignalSwitchboard => T): Unit = {
    val s = Keyed(v, instance)
    val signal = signals.computeIfAbsent(s.id, newSignal).asInstanceOf[Signal[T]]
    val prevValueOpt = signal.currValue.knownValue(v, instance).entityToOption
    unbindPrev(s)
    signal.currValue = State.Recompute(prevValueOpt, signal.currValue.relationshipsOpt.orNull, compute, transitionDef)
    signals.put(s.id, signal)

    v.projected.?(bindToProjections(_, instance))

    propagateSignalInvalidation(s, skipSelf = true, propagateToProjections = true)
  }

  private def bindToProjections[T](v: Var[T], instance: v.ForInstance): Unit = {
    val s = Keyed(v, instance)
    val baseValue = apply(v, instance)
    val signal = signals.computeIfAbsent(s.id, newSignal).asInstanceOf[Signal[T]]
    unbindPrev(s, unbindProjections = false)
    signal.currValue = State.Projections(baseValue)
    signals.put(s.id, signal)

    Debug.elidable {
      scribe.debug(s"Var($instance, $v) bound to its projections")
    }

    v.projected.?(bindToProjections(_, instance))

    propagateSignalInvalidation(s, skipSelf = true, propagateToProjections = false)
  }

  override def remove(s: Keyed[ObsVal[Any]]): Unit = {
    unbindPrev(s)
    reporter.signalRemoved(this, s)
  }

  override def snapshot(newReporter: Reporter): SignalSwitchboard = {
    val res = new BetterSignalSwitchboardImpl(newReporter, defaultValueProvider, varsLookup, useReentrancyDetection, timers)
    signals.forEach((key, signal) =>
      val newSignal = res.Signal(signal.currValue.asInstanceOf)
      if (signal.reactToMyChanges != null) 
        newSignal.instantiatedReactToMyChanges.addAll(signal.reactToMyChanges.unn)
      res.signals.put(key, newSignal)
    )
    res
  }

  override def relationships[T](v: ObsVal[T], instance: v.ForInstance): Option[Relationships] = signals.get(Keyed(v, instance).id) match {
    case null => None
    case state: Signal[T] @unchecked => state.currValue.relationshipsOpt
  }

  private def unbindPrev[T](s: Keyed[ObsVal[T]], unbindProjections: Boolean = true): Unit = {
    def undoRelationships(rels: Relationships): Unit = {
      rels.dependents fastForeach (l => remove(Keyed(l)))
      rels.dependencies fastForeach (dep => signals.get(dep).?(signal =>
        signal.reactToMyChanges.?(_.remove(s.id))
        if (signal.currValue == State.Unseen && signal.reactToMyChanges.nullFold(_.isEmpty, true)) {
          // the signal was added purely as dependency of the one being unbind, so we can remove it as well
          signals.remove(dep)
        }
      ))
    }
    signals.get(s.id) match {
      case null =>
      case state: Signal[T] @unchecked =>
        state.currValue match {
          case State.BindingResult(relationships = rels) => undoRelationships(rels)
          case State.Transitioning(timer = t, to = toState) =>
            t.stop()
            toState match {
              case State.BindingResult(relationships = rels) => undoRelationships(rels)
              case _ =>
            }
            
          case _ =>
        }
        val canRemove = state.reactToMyChanges.nullFold(_.isEmpty, true)
        if (canRemove) signals.remove(s.id)

        if (unbindProjections) {
          varsLookup.lookup(s) match {
            case null =>
            case (v: Var[u], i) => v.projections.foreach(p => unbindPrev(Keyed(p, i.asInstanceOf[p.ForInstance])))
            case _ => 
          }
        }
    }
  }

  private val depth = new java.util.concurrent.atomic.AtomicInteger(0)
  private def propagateSignalInvalidation[T](s: Keyed[ObsVal[T]], skipSelf: Boolean, propagateToProjections: Boolean): Unit = {
    // due to how propagating signal works, where the set of dependencies is iterated, it is entirely possible to find during
    // the iteration a signal that was removed, hence why we check here if that's the case by checking the state
    if !signals.containsKey(s.id) then return

    val currState = signals.get(s.id).unn

    val (v, i) = varsLookup.lookup(s).nullFold(identity, return)
    Debug.elidable {
      scribe.debug(s"${"  " * depth.getAndIncrement()}propagating invalidation for ${varsLookup
        .describe(s)} ${s.descrString} ${currState.currValue}")
    }

    if (!skipSelf) {
      currState.currValue match {
        case _: State.Recompute[T] @unchecked | _: State.Value[T] @unchecked | State.External => // if recompute do nothing, and if constant value it just doesn't react
        case State.BindingResult(value, rels, f, defn) =>
          currState.currValue = State.Recompute(Some(value), rels, f, defn)
        case State.Transitioning(curr, timer, to) =>
          timer.stop()
          to match {
            case State.BindingResult(relationships = rels, f = f, transitionDef = defn) =>
              currState.currValue = State.Recompute(Some(curr), rels, f, defn)
            case s: State.Value[T] @unchecked => 
              currState.currValue = s
          }
        case _ =>
      }
    }
    currState.reactToMyChanges.?(_.fastForeach(l => propagateSignalInvalidation(Keyed(l), false, true)))

    v.foreachProjectedParent { parent =>
      val k2 = Keyed(parent, i.asInstanceOf[parent.ForInstance])
      propagateSignalInvalidation(k2, false, false)
    }

    if (propagateToProjections) v match {
      case vv: Var[T] => vv.projections.foreach(p =>
        val k2 = Keyed(p, i.asInstanceOf[p.ForInstance])
        propagateSignalInvalidation(k2, false, true)
      )
      case _ =>
    }

    // report at the end of the process, so that all of `s` dependencies get reported and invalidated first
    reporter.signalInvalidated(this, v, i.asInstanceOf[v.ForInstance])
    Debug.elidable {
      scribe.debug(s"${"  " * depth.decrementAndGet()}done propagating invalidation for ${varsLookup.describe(s)} ${s.descrString}")
    }
  }

  private def startTransition[T](s: Keyed[Var[T]], oldv: Option[T], state: Signal[T], defn: TransitionType.Interp[T]): T = {
    import defn.*
    val min = oldv.fold(baseValue)(v => v)
    val initValue = min
    val targetState = state.currValue
    val targetValue = targetState match {
      case State.Value(value) => value
      case State.BindingResult(value = value) => value
      case other => throw new IllegalStateException(s"Can't transition to state $other")
    }

    if (initValue == targetValue) return initValue

    val trn = Transition(delay, duration, initValue, targetValue, curve, defn.interp)

    lazy val anim: Timeline.Animation[timers.Timer] = Timeline(IArray(KeyFrame((delay + duration).toNanos, elapsed => {
      val curr = trn.valueAt(elapsed)
      varsLookup.lookup(s) match {
        case null => anim.timer.stop() // if the variable was collected, no need to continue the animation
        case (v, i) =>
          // println(s"interpolating var after $elapsed, $initValue / $curr / $targetValue")
          // specifically set the state to transitioning because updateSpecific might leave it as External, once
          // the animation is done and we are no longer transitioning we don't need this
          state.currValue = State.Transitioning(curr, anim.timer, targetState.asInstanceOf)
          propagateSignalInvalidation(s, skipSelf = true, propagateToProjections = true)
          reportUpdate(v, i.asInstanceOf[v.ForInstance], oldv, curr)
      }
    }, () => {
      varsLookup.lookup(s) match {
        case null =>
        case (v, i) =>
          state.currValue = targetState
          propagateSignalInvalidation(s, skipSelf = true, propagateToProjections = true)
          reportUpdate(v, i.asInstanceOf[v.ForInstance], oldv, targetValue)
      }
    })), Timeline.Cycles.SingleShot, updatesPerSecond, false)(timers)

    state.currValue = State.Transitioning(initValue, anim.timer, targetState.asInstanceOf)
    anim.start()
    initValue
  }

  private def reportUpdate[T](v: ObsVal[T], i: v.ForInstance, oldValue: Option[T], newValue: T): Unit = {
    type Instance = v.ForInstance
    reporter.signalUpdated(this, v, i, oldValue, newValue)
    v.foreachProjectedParent { parent =>
      get(parent, i) match {
        case NotFound =>
        case v: Any => reporter.signalUpdated(this, parent, i, None, v)
      }
    }
    def reportDownStream(v: Var.Aux[?, Instance]): Unit = {
      v.projections.foreach { case p: v.Projection[u] =>
        get(p, i) match {
          case NotFound =>
          case value => 
            reportUpdate(p, i, None, value)
            reportDownStream(p)
        }
      }
    }
    v match {
      case vv: Var.Aux[?, Instance] @unchecked => reportDownStream(vv)
      case _ =>
    }
  }
}

object BetterSignalSwitchboardImpl {
  private[impl] val EmptyUnmodifiableLongHashSet = new LongHashSet {}
}
