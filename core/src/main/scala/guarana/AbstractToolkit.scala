package guarana

import guarana.impl.{EmitterStation, SignalSwitchboard}
import org.agrona.collections.LongHashSet
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, Promise}
import scala.util.Try

import language.implicitConversions

type ToolkitAction[-Tk <: AbstractToolkit, +R] = Tk ?=> VarContextAction[R]
abstract class AbstractToolkit {

  def timerDefs: animation.TimersDef
  private val varsLookup = impl.VarsLookup()
  private val switchboard = {
    val defaultValueProvider = new SignalSwitchboard.DefaultVarValueProvider {
      def defaultValueFor[T](v: ObsVal[T], instance: v.ForInstance): T =
        stylist(getMetrics(), v, instance)(using AbstractToolkit.this) getOrElse v.initialValue(instance)
    }
    SignalSwitchboard(reporter, defaultValueProvider, varsLookup, false, timerDefs)
  }
  private val emitterStation = EmitterStation()
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
    def run() = res.complete(Try {
      if (!stackContext.isBound()) {
        val ctx = ContextImpl(switchboard, emitterStation)
        impl.Debug.elidable { varcontextLogger.debug(s"no current varcontext, installing $ctx") }
        val res = ScopedValue.where(stackContext, ctx).call(() => f(using this)(using stackContext.get().unn))
        impl.Debug.elidable { varcontextLogger.debug(s"context $ctx popped") }
        res
      } else {
        impl.Debug.elidable { varcontextLogger.debug(s"using existing var context ${stackContext.get()}") }
        f(using this)(using stackContext.get().unn)
      }
    })

    if (isOnToolkitThread()) run()
    else {
      runOnToolkitThread(run)
    }

    res.future
  }

  private val reactingExtVars = new LongHashSet(16)

  /** Executes the given thunk preventing external property changes from being propagated to the signals. Reason: When computing the values
    * for external properties, they'll notify that they were change, and we want to avoid resetting the value and discarding the user
    * provided Binding.
    */
  private def reactingToExtVar[R](k: Keyed[ObsVal[?]])(f: => R): R = {
    reactingExtVars.add(k.id)
    val res =
      try f
      finally reactingExtVars.remove(k.id)
    res
  }
  private object reporter extends SignalSwitchboard.Reporter {

    def signalRemoved[T](sb: SignalSwitchboard, s: Keyed[ObsVal[T]]): Unit = ()
    def signalInvalidated[T](sb: SignalSwitchboard, v: ObsVal[T], instance: v.ForInstance) = {
      impl.Debug.elidable { scribe.debug(s"Keyed($instance, $v) invalidated") }
      v match {
        case v: Var.Aux[T, v.ForInstance] @unchecked if v.eagerEvaluation =>
          impl.Debug.elidable { scribe.debug(s"Keyed($instance, $v) eagerly evaluating") }
          switchboard.get(v, instance)
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
        sb: SignalSwitchboard,
        v: ObsVal[T],
        instance: v.ForInstance,
        oldValue: Option[T],
        newValue: T,
        dependencies: LongHashSet,
        dependents: LongHashSet
    ): Unit = {
      val s = Keyed(v, instance)
      // FIXME: broken api
      impl.Debug.elidable { scribe.debug(s"Keyed($instance, $v) updated") }
      // println(s"signal updated ${signalDescriptor.describe(s)}, reacting ? ${reactingExtVars.contains(s.id)}")
      v match {
        case v: ExternalVar[T] { type ForInstance = v.ForInstance } if !reactingExtVars.contains(s.id) =>
          reactingToExtVar(s) {
            // println(s"    setting external prop ${signalDescriptor.describe(s)}")
            v.asInstanceOf[ExternalVar[T] { type ForInstance = instance.type }].set(instance, newValue)
          }
        case _ =>
      }

      if (emitterStation.hasListeners(instance.varUpdates)) {
        impl.Debug.elidable { scribe.debug(s"Keyed($instance, $v) has listeners on its changes, emitting VarValueChanged") }
        given ctx: ContextImpl = {
          if (stackContext.isBound()) stackContext.get().unn
          else ContextImpl(switchboard, emitterStation)
        }
        ScopedValue.where(stackContext, ctx).call { () =>
          ctx.emit(instance.varUpdates, VarValueChanged(v, instance, oldValue, newValue))
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
      val switchboard: SignalSwitchboard,
      val emitterStation: EmitterStation,
  ) extends VarContext {

    def recordInstance(instance: Any): varsLookup.InstanceData = {
      varsLookup.recordInstance(instance, switchboard.remove, emitterStation.remove)
    }
    def recordVarUsage[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): Unit = {
      varsLookup.recordVarUsage(v, switchboard.remove, emitterStation.remove)
    }

    def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = {
      checkActiveContext()
      recordVarUsage(v)
      switchboard(v, instance.value)
    }
    def update[T](v: Var[T], binding: Binding[T])(using instance: ValueOf[v.ForInstance]): Unit = {
      checkActiveContext()
      recordVarUsage(v)
      lazy val styleTrn = stylist.getTransition(getMetrics(), v, instance.value)(using AbstractToolkit.this)
      binding match {
        case Binding.Const(c, trOpt) =>
          switchboard.update(v, instance.value, c(), trOpt.orElse(styleTrn).getOrElse(animation.TransitionType.Instant))
        case Binding.Compute(c, trOpt) =>
          switchboard.bind(v, instance.value, trOpt.orElse(styleTrn).getOrElse(animation.TransitionType.Instant))(sb =>
            val ctx = stackContext.orElse(null) match {
              case null =>
                impl.Debug.elidable { varcontextLogger.debug(s"evaluating binding, no existing context.") }
                new ContextImpl(sb, AbstractToolkit.this.emitterStation)
              case parent =>
                impl.Debug.elidable { varcontextLogger.debug(s"evaluating binding, existing context = $parent") }
                new ContextImpl(sb, parent.emitterStation)
            }
            impl.Debug.elidable { varcontextLogger.debug(s"evaluating binding, installing context $ctx") }
            val res = ScopedValue.where(stackContext, ctx).call(() => c(stackContext.get().unn))
            impl.Debug.elidable { varcontextLogger.debug(s"evaluating binding, context $ctx popped") }
            res
          )
      }
    }
    def externalPropertyUpdated[T](v: ObsVal[T], oldValue: Option[T])(using instance: ValueOf[v.ForInstance]): Unit = {
      checkActiveContext()
      recordVarUsage(v)
      if (!reactingExtVars.contains(ObsVal.obs2Keyed(v).id)) {
        switchboard.externalPropertyChanged(v.asInstanceOf[ExternalVar[T] { type ForInstance = v.ForInstance }], instance.value, oldValue)
      }
    }

    private def checkActiveContext(): Unit = {
      val tlc = stackContext.orElse(null)
      if (tlc == null || tlc != this)
        throw IllegalStateException(
          s"Scenegraph VarContext $this is no longer active, this means this context was leaked/captured onto a lambda"
        )
    }

    // Emitter.Context

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
      switchboard(v.asInstanceOf[Var.Aux[T, v.ForInstance]], instance.value)
    }

    /** Reads the stored value of the property, if any. */
    def get[T](property: ObsVal[T])(using instance: ValueOf[property.ForInstance]): Option[T] =
      switchboard.get(property.asInstanceOf[Var.Aux[T, property.ForInstance]], instance.value) match
        case impl.SignalSwitchboard.NotFound => None
        case t: T => Some(t)

    /** Reads the stored value of the property or its default (it doesn't asks the stylist) */
    def getOrDefault[T](property: ObsVal[T])(using instance: ValueOf[property.ForInstance]): T =
      switchboard
        .get(property.asInstanceOf[Var.Aux[T, property.ForInstance]], instance.value)
        .getOrElse(property.initialValue(instance.value))

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
