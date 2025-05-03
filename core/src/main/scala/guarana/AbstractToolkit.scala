package guarana

import guarana.impl.{EmitterStation, SignalSwitchboard}
import org.agrona.collections.{Int2ObjectHashMap, IntHashSet, LongHashSet}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, Promise}
import scala.util.Try
import scala.util.chaining.*

import language.implicitConversions
import impl.AgronaUtils.*

type ToolkitAction[-Tk <: AbstractToolkit, +R] = Tk ?=> VarContextAction[R]
abstract class AbstractToolkit {

  type Signal[+T] = ObsVal[T]

  private val signalDescriptor = new SignalSwitchboard.SignalDescriptor[Signal] {
    def isExternal[T](s: Keyed[ObsVal[T]]): Boolean = externalVars.contains(s.keyId)
    def getExternal[T](s: Keyed[ObsVal[T]]): T =
      val data = instancesData.get(s.instanceId).unn
      val instance = data.instance.deref
      seenVars.get(s.keyId).asInstanceOf[ExternalObsVal[T] { type ForInstance = instance.type }].get(instance)

    def describe[T](s: Keyed[ObsVal[T]]): String = {
      val data = instancesData.get(s.instanceId).unn
      val theVar = seenVars.get(s.keyId)
      val maxWidth = 60
      var instanceDescr = data.instance.deref.toString.stripPrefix("javax.swing.")
      instanceDescr = instanceDescr match {
        case s if s.length < maxWidth => s
        case s => s.take(maxWidth - 3) + "..."
      }
      s"$instanceDescr: $theVar"
    }
  }
  def timerDefs: animation.TimersDef
  private val switchboard = SignalSwitchboard[Signal](reporter, signalDescriptor, false, timerDefs)
  private val emitterStation = EmitterStation()
  case class InstanceData(instance: util.WeakRef[AnyRef], vars: IntHashSet, emitters: IntHashSet)
  private val instancesData = new Int2ObjectHashMap[InstanceData]()
  private val externalVars = new IntHashSet(1024)
  private val seenVars = new Int2ObjectHashMap[Signal[?]]
  private val cleaner = impl.RefCleaner()
  var stylist: Stylist = Stylist.NoOp

  protected def isOnToolkitThread(): Boolean
  protected def runOnToolkitThread(r: () => Any): Unit

  /** Reads the Metrics for the system */
  def getMetrics(): Stylist.Metrics

  val varcontextLogger = scribe.Logger("varcontext")
  private val stackContext = ScopedValue.newInstance[ContextImpl | Null]()

  def update[R](f: this.type ?=> VarContextAction[R]): R = Await.result(updateAsync(f), Duration.Inf)
  def updateAsync[R](f: this.type ?=> VarContextAction[R]): Future[R] = {
    val res = Promise[R]()
    def impl() = res.complete(Try {
      if (!stackContext.isBound()) {
        val ctx = ContextImpl(switchboard, instancesData, externalVars, seenVars, emitterStation)
        varcontextLogger.debug(s"no current varcontext, installing $ctx")
        val res = ScopedValue.where(stackContext, ctx).call(() => f(using this)(using stackContext.get().unn))
        varcontextLogger.debug(s"context $ctx popped")
        res
      } else {
        varcontextLogger.debug(s"using existing var context ${stackContext.get()}")
        f(using this)(using stackContext.get().unn)
      }
    })

    if (isOnToolkitThread()) impl()
    else {
      runOnToolkitThread(impl)
    }

    res.future
  }

  private val reactingExtVars = new LongHashSet(16)

  /** Executes the given thunk preventing external property changes from being propagated to the signals. Reason: When computing the values
    * for external properties, they'll notify that they were change, and we want to avoid resetting the value and discarding the user
    * provided Binding.
    */
  private def reactingToExtVar[R](k: Keyed[Signal[?]])(f: => R): R = {
    reactingExtVars.add(k.id)
    val res =
      try f
      finally reactingExtVars.remove(k.id)
    res
  }
  private object reporter extends SignalSwitchboard.Reporter[Signal] {

    def signalRemoved(sb: SignalSwitchboard[Signal], s: Keyed[Signal[?]]): Unit = ()
    def signalInvalidated(sb: SignalSwitchboard[Signal], s: Keyed[Signal[?]]) = {
      scribe.debug(s"Keyed(${instancesData.get(s.instanceId).?(_.instance.deref)}, ${seenVars.get(s.keyId)}) invalidated")
      seenVars.get(s.keyId) match {
        case v: Var[_] if v.eagerEvaluation =>
          scribe.debug(s"Keyed(${instancesData.get(s.instanceId).?(_.instance.deref)}, ${seenVars.get(s.keyId)}) eagerly evaluating")
          switchboard.get(s)
        // v match {
        //   case ev: ExternalVar[t] => reactingToExtVar(s) {

        //     val data = instancesData.get(s.instanceId).unn
        //     val instanceOpt = data.instance
        //     instanceOpt.deref.toOption.foreach { instance =>
        //       val evInstance = instance.asInstanceOf[ev.ForInstance]
        //       if (ev.get(evInstance) != curr) ev.set(evInstance, curr)
        //     }
        //   }

        //   case _ => switchboard.get(s)
        // }
        // /* We intentionally don't run evaluations on the same tick and context that's reacting to signal invalidation, we do it on the next frame. This prevents some potentially huge
        //  * chain recation calls by distributing as tasks to the eventloop.
        //  * We also read straight from the switchboard because we want it to be computed, not affect our VarContext tracking.
        //  */
        // val stack = new Exception()
        // runOnToolkitThread(() => try reactingToVar(s) { switchboard.get(s) } catch case any: Throwable => {any.addSuppressed(stack); throw any})
        case _ =>
      }
      // s.keyed match {
      //   case v: Var[_] if v.eagerEvaluation => withContext { ctx =>
      //     reactingToVar(s) { ctx.switchboard(s) }
      //   }
      //   case _ =>
      // }
    }

    def signalUpdated[T](
        sb: SignalSwitchboard[Signal],
        s: Keyed[Signal[T]],
        oldValue: Option[T],
        newValue: T,
        dependencies: LongHashSet,
        dependents: LongHashSet
    ): Unit = {
      update {
        val ctx = summon[VarContext].asInstanceOf[ContextImpl]
        // FIXME: broken api
        val data = instancesData.get(s.instanceId).unn
        val instanceOpt = data.instance
        scribe.debug(s"Keyed(${instanceOpt.deref}, ${seenVars.get(s.keyId)}) updated")
        instanceOpt.deref.toOption.foreach { instance =>
          // println(s"signal updated ${signalDescriptor.describe(s)}, reacting ? ${reactingExtVars.contains(s.id)}")
          if (signalDescriptor.isExternal(s) && !reactingExtVars.contains(s.id)) {
            reactingToExtVar(s) {
              // println(s"    setting external prop ${signalDescriptor.describe(s)}")
              seenVars.get(s.keyId).?(v => v.asInstanceOf[ExternalVar[T] { type ForInstance = instance.type }].set(instance, newValue))
            }
          }

          if (emitterStation.hasListeners(instance.varUpdates)) {
            scribe.debug(s"Keyed($instance, ${seenVars.get(s.keyId)}) has listeners on its changes, emitting VarValueChanged")
            seenVars.get(s.keyId).?(v => ctx.emit(instance.varUpdates, VarValueChanged(v, instance, oldValue, newValue)))
          }
        }
      }
    }
  }

  /** ContextImpl can use a snapshotted switchboard so the changes can be applied: they are seen (this is in order to properly implement
    * sequence in code) and after the lambda that uses this context is done, the updated switchboard replaces the old one, and we update our
    * tracking. But due to guarana being mostly used to build on top of existing frameworks which are mutable already, there's no much point
    * in this, so we opt to mutate in place. Nevertheless the framework is built with that flexbility in mind.
    */
  private class ContextImpl(
      val switchboard: SignalSwitchboard[Signal],
      val instancesData: Int2ObjectHashMap[InstanceData],
      val externalVars: IntHashSet,
      val seenVars: Int2ObjectHashMap[Signal[?]],
      val emitterStation: EmitterStation,
  ) extends VarContext {

    def recordInstance(instance: Any): InstanceData = {
      val instanceId = Keyed.getId(instance)
      instancesData.get(instanceId) match
        case null =>
          val vars = new IntHashSet(8)
          val emitters = new IntHashSet(4)
          val data = InstanceData(util.WeakRef(instance.asInstanceOf[AnyRef]), vars, emitters)
          instancesData.put(instanceId, data)
          // format: off
          cleaner.register(
            instance,
            () => instancesData.remove(instanceId).?(data =>
              scribe.debug(f"instance data 0x$instanceId%H removed")
              data.vars.fastForeach(v => switchboard.remove(Keyed.raw(v, instanceId)))
              data.emitters.fastForeach(v => emitterStation.remove(Keyed.raw(v, instanceId)))
            )
          )
          // format: on
          data

        case data: InstanceData => data
    }
    def recordVarUsage[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): Unit = {
      val varForInstanceAdded = recordInstance(instance.value).vars.add(v.uniqueId)
      seenVars.put(v.uniqueId, v)
      if (v.isInstanceOf[ExternalObsVal[?]]) externalVars.add(v.uniqueId)
      if (varForInstanceAdded) {
        scribe.debug(s"Var(${instance.value}, $v) recorded. Key = ${ObsVal.obs2Keyed(v)}")
        v.onFirstAssociation(instance.value)
      }
    }

    def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = {
      checkActiveContext()
      recordVarUsage(v)
      switchboard
        .get(v)
        .getOrElse(stylist(getMetrics(), v, instance.value)(using AbstractToolkit.this) getOrElse v.initialValue(instance.value))
    }
    def update[T](v: Var[T], binding: Binding[T])(using instance: ValueOf[v.ForInstance]): Unit = {
      checkActiveContext()
      recordVarUsage(v)
      lazy val styleTrn = stylist.getTransition(getMetrics(), v, instance.value)(using AbstractToolkit.this)
      binding match {
        case Binding.Const(c, trOpt) => switchboard.update(v, c(), trOpt.orElse(styleTrn).getOrElse(animation.TransitionType.Instant))
        case Binding.Compute(c, trOpt) =>
          switchboard.bind(v, trOpt.orElse(styleTrn).getOrElse(animation.TransitionType.Instant))(sb =>
            val ctx = stackContext.orElse(null) match {
              case null =>
                varcontextLogger.debug(s"evaluating binding, no existing context.")
                new ContextImpl(
                  sb,
                  AbstractToolkit.this.instancesData,
                  AbstractToolkit.this.externalVars,
                  AbstractToolkit.this.seenVars,
                  AbstractToolkit.this.emitterStation
                )
              case parent =>
                varcontextLogger.debug(s"evaluating binding, existing context = $parent")
                new ContextImpl(sb, parent.instancesData, parent.externalVars, parent.seenVars, parent.emitterStation)
            }
            varcontextLogger.debug(s"evaluating binding, installing context $ctx")
            val res = ScopedValue.where(stackContext, ctx).call(() => c(stackContext.get().unn))
            varcontextLogger.debug(s"evaluating binding, context $ctx popped")
            res
          )
      }
    }
    def externalPropertyUpdated[T](v: ObsVal[T], oldValue: Option[T])(using instance: ValueOf[v.ForInstance]): Unit = {
      checkActiveContext()
      recordVarUsage(v)
      if (!reactingExtVars.contains(ObsVal.obs2Keyed(v).id)) {
        switchboard.externalPropertyChanged(v, oldValue)
      }
    }

    private def checkActiveContext(): Unit = {
      val tlc = stackContext.orElse(null)
      if (tlc == null || tlc != this)
        throw IllegalStateException(
          s"Scenegraph VarContext $this is no longer active, this means this context was leaked/captured onto a lambda"
        )
    }

    //Emitter.Context

    def emit[A](emitter: Emitter[A], evt: A)(using instance: ValueOf[emitter.ForInstance]): Unit = {
      checkActiveContext()
      recordInstance(instance.value).emitters.add(emitter.uniqueId)
      emitterStation.emit(emitter, evt)(using instance, this)
    }
    def listen[A](emitter: Emitter[A])(f: EventIterator[A])(using instance: ValueOf[emitter.ForInstance]): Unit = {
      checkActiveContext()
      recordInstance(instance.value).emitters.add(emitter.uniqueId)
      emitterStation.listen(emitter)(f)
    }
  }

  /** Read-only view of the current state of vars value in the toolkit */
  object stateReader {

    /** Read the current value of ObsVal as it is read by doing `prop()` within a VarContext */
    def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = {
      val keyed: Keyed[ObsVal[T]] = v
      switchboard.get(keyed) `getOrElse` (stylist(getMetrics(), v, instance.value)(using AbstractToolkit.this) getOrElse v.initialValue(
        instance.value
      ))
    }

    /** Reads the stored value of the property, if any. */
    def get[T](property: ObsVal[T])(using instance: ValueOf[property.ForInstance]): Option[T] = switchboard.get(property) match
      case impl.SignalSwitchboard.NotFound => None
      case t: T => Some(t)

    /** Reads the stored value of the property or its default (it doesn't asks the stylist) */
    def getOrDefault[T](property: ObsVal[T])(using instance: ValueOf[property.ForInstance]): T =
      switchboard.get(property).getOrElse(property.initialValue(instance.value))

    def hasEmitter[A](emitter: Emitter[A])(using v: ValueOf[emitter.ForInstance]): Boolean = emitterStation.hasEmitter(emitter)
    def hasListeners[A](emitter: Emitter[A])(using v: ValueOf[emitter.ForInstance]): Boolean = emitterStation.hasListeners(emitter)
  }

  /** Configures the logging system to trace all events to show for this one particular node */
  def logTrace(node: Any, lineFilter: (String => Boolean) | Null = null): TraceControl = {
    val _lineFilter = lineFilter.nullFold(identity, (_: String).contains(node.toString))
    val traceHandler = scribe.handler.LogHandler(
      minimumLevel = Some(scribe.Level.Trace),
      modifiers = List(scribe.filter.FilterBuilder(include = List(r => _lineFilter(r.logOutput.plainText))))
    )
    scribe.Logger("guarana").withHandler(traceHandler).replace()
    return new TraceControl {
      def cancel() = {
        scribe.Logger("guarana").withoutHandler(traceHandler).replace()
      }
    }
  }

  trait TraceControl {
    def cancel(): Unit
  }
}
