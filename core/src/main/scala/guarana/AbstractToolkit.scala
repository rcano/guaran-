package guarana

import language.implicitConversions
import impl.AgronaUtils.*
import javax.swing.SwingUtilities
import org.agrona.collections.{Int2ObjectHashMap, IntHashSet, LongHashSet}
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration.Duration
import scala.util.Try
import guarana.impl.{CopyOnWriteEmitterStation, CopyOnWriteSignalSwitchboard, EmitterStation, SignalSwitchboard}
import scala.collection.View.Single

type ToolkitAction[+R] = VarContext & Emitter.Context ?=> R
abstract class AbstractToolkit {

  type Signal[+T] = ObsVal[T]
  private val switchboard = SignalSwitchboard[Signal](
    reporter,
    new SignalSwitchboard.SignalDescriptor[Signal] {
      def isExternal[T](s: Keyed[ObsVal[T]]): Boolean = externalVars.contains(s.keyId)
      def getExternal[T](s: Keyed[ObsVal[T]]): T =
        val data = instanceVars.get(s.instanceId).unn
        val instance = data.instance
        seenVars.get(s.keyId).asInstanceOf[ExternalObsVal[T] { type ForInstance = instance.type }].get(instance)
    }
  )
  private val emitterStation = EmitterStation()
  case class InstanceData(instance: Any, vars: IntHashSet, emitters: IntHashSet)
  private val instanceVars = new Int2ObjectHashMap[InstanceData]()
  private val externalVars = new IntHashSet(1024)
  private val seenVars = new Int2ObjectHashMap[Signal[?]]
  private val cleaner = impl.RefCleaner()

  protected def isOnToolkitThread(): Boolean
  protected def runOnToolkitThread(r: () => Any): Unit

  private val threadLocalContext = new ThreadLocal[ContextImpl] {
    override def initialValue = null
  }

  def update[R](f: this.type ?=> ToolkitAction[R]): R = Await.result(updateAsync(f), Duration.Inf)
  def updateAsync[R](f: this.type ?=> ToolkitAction[R]): Future[R] = {
    val res = Promise[R]()
    def impl() = res.complete(Try {
      var locallyCreatedContext = false
      var ctx = threadLocalContext.get()
      if (ctx == null) {
        // in an ideal world, we would use a context like the following:
        // ctx = new ContextImpl(CopyOnWriteSignalSwitchboard(switchboard, reporter), CopyOnWriteEmitterStation(emitterStation))
        // this would make the toolkit immutable, allowing you to rollback changes in case of exception.
        // Unfortunately, due to the already mutable nature of of most UI toolkits, like swing, once you impact an external property,
        // it would not matter rolling back, and it doesn't  make sense to track all changes and try to undo them (even though possible)
        // beacuse it's already contrarian to how mutable toolkits works anyway. Given all of this, we'll prefer speed here and hence
        // directly mutate the toolkit state.
        ctx = ContextImpl(switchboard, instanceVars, externalVars, seenVars, emitterStation)
        threadLocalContext.set(ctx)
        locallyCreatedContext = true
      }
      val res = f(using this)(using ctx)
      if (locallyCreatedContext) {
        // in an immutable world, we would need to update our internal state:
        // switchboard = ctx.switchboard.theInstance
        // emitterStation = ctx.emitterStation.theInstance
        threadLocalContext.set(null)
      }
      res
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
  private def reactingToVar[R](k: Keyed[Signal[_]])(f: => R): R = {
    reactingExtVars add k.id
    val res = f
    reactingExtVars remove k.id
    res
  }
  private object reporter extends SignalSwitchboard.Reporter[Signal] {

    private def withContext(f: ContextImpl => Any): Unit = {
      val ctx = threadLocalContext.get()
      if (ctx != null) //during toolkit bootstrapping, context is null
        f(ctx)
    }

    def signalRemoved(sb: SignalSwitchboard[Signal], s: Keyed[Signal[_]]): Unit = ()
    def signalInvalidated(sb: SignalSwitchboard[Signal], s: Keyed[Signal[_]]) = {
      seenVars.get(s.keyId) match {
        case v: Var[_] if v.eagerEvaluation =>
          withContext { ctx =>
            reactingToVar(s) { ctx.switchboard(s) }
          }
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
      withContext { ctx =>
        given Emitter.Context = ctx
        // FIXME: broken api
        val data = instanceVars.get(s.instanceId).unn
        if emitterStation.hasEmitter(data.instance.varUpdates) then
          seenVars.get(s.keyId).?(v => ctx.emit(data.instance.varUpdates, VarValueChanged(v, data.instance, oldValue, newValue)))
      // instanceVars.get(s.keyId).?((instance, _) => ctx.emit(instance.varUpdates, VarValueChanged(s, oldValue, newValue)))
      }
    }
  }

  private class SwitchboardAsVarContext(
      switchboard: SignalSwitchboard[Signal],
      instanceVars: Int2ObjectHashMap[InstanceData],
      externalVars: IntHashSet,
      seenVars: Int2ObjectHashMap[Signal[?]],
  ) extends VarContext {
    def recordInstance(instance: Any): InstanceData = {
      val instanceId = impl.Keyed.getId(instance)
      instanceVars.get(instanceId) match
        case null =>
          val vars = new IntHashSet(8)
          val emitters = new IntHashSet(4)
          val data = InstanceData(instance, vars, emitters)
          instanceVars.put(instanceId, data)
          cleaner.register(
            instance,
            () => instanceVars.remove(instanceId).?(data =>
              data.vars.fastForeach(v => switchboard.remove(impl.Keyed.raw(v, instanceId)))
              data.emitters.fastForeach(v => emitterStation.remove(impl.Keyed.raw(v, instanceId)))
            )
          )
          data

        case data: InstanceData => data
    }
    def recordVarUsage[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): Unit = {
      recordInstance(instance.value).vars.add(v.uniqueId)
      seenVars.put(v.uniqueId, v)
      if (v.isInstanceOf[ExternalObsVal[?]]) externalVars.add(v.uniqueId)
    }

    def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = {
      recordVarUsage(v)
      switchboard.get(v) getOrElse v.initialValue(instance.value)
    }
    def update[T](v: Var[T], binding: Binding[T])(using instance: ValueOf[v.ForInstance]): Unit = {
      recordVarUsage(v)
      binding match {
        case Binding.Const(c) => switchboard(v) = c()
        case Binding.Compute(c) => switchboard.bind(v)(sb => c(new SwitchboardAsVarContext(sb, instanceVars, externalVars, seenVars)))
      }
    }
    def externalPropertyUpdated[T](v: ObsVal[T], oldValue: Option[T])(using instance: ValueOf[v.ForInstance]): Unit = {
      recordVarUsage(v)
      if (!reactingExtVars.contains(v)) {
        switchboard.externalPropertyChanged(v, oldValue)
      }
    }
  }

  /** ContextImpl uses a snapshotted switchboard so the changes can be applied: they are seen (this is in order to properly implement
    * sequence in code) After the lambda that uses this context is done, the updated switchboard replaces the old one, and we update our
    * tracking.
    */
  private class ContextImpl(
      val switchboard: SignalSwitchboard[Signal],
      val instanceVars: Int2ObjectHashMap[InstanceData],
      val externalVars: IntHashSet,
      val seenVars: Int2ObjectHashMap[Signal[?]],
      var emitterStation: EmitterStation,
  ) extends SwitchboardAsVarContext(switchboard, instanceVars, externalVars, seenVars)
      with Emitter.Context {

    //Emitter.Context

    def emit[A](emitter: Emitter[A], evt: A)(using instance: ValueOf[emitter.ForInstance]): Unit =
      recordInstance(instance.value).emitters.add(emitter.uniqueId)
      emitterStation.emit(emitter, evt)(using summon, this)
    def listen[A](emitter: Emitter[A])(f: EventIterator[A])(using instance: ValueOf[emitter.ForInstance]): Unit =
      recordInstance(instance.value).emitters.add(emitter.uniqueId)
      emitterStation.listen(emitter)(f)
  }

  /** Read-only view of the current state of vars value in the toolkit */
  object stateReader {

    /** Read the current value of ObsVal as it is read by doing `prop()` within a VarContext */
    def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = {
      val keyed: Keyed[ObsVal[T]] = v
      switchboard.get(keyed) getOrElse v.initialValue(instance.value)
    }

    /** Reads the stored value of the property, if any. */
    def get[T](property: ObsVal[T])(using instance: ValueOf[property.ForInstance]): Option[T] = switchboard.get(property) match
      case impl.SignalSwitchboard.NotFound => None
      case t: T => Some(t)
  }

}
