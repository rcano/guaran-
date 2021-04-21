package guarana

import language.implicitConversions
import javax.swing.SwingUtilities
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration.Duration
import scala.util.Try
import guarana.impl.{CopyOnWriteEmitterStation, CopyOnWriteSignalSwitchboard, EmitterStation, SignalSwitchboard}

type ToolkitAction[+R] = VarContext & Emitter.Context ?=> R
object Toolkit {
}
abstract class Toolkit {

  type Signal[+T] = ObsVal[T]
  private val switchboard = SignalSwitchboard[Signal](reporter, new SignalSwitchboard.SignalDescriptor[Signal] {
    def isExternal[T](s: ObsVal[T]) = s.isInstanceOf[ExternalObsVal[_]]
    def getExternal[T](s: ObsVal[T], instance: Any) = s.asInstanceOf[ExternalObsVal[T] { type ForInstance = instance.type}].get(instance)
  })
  private val emitterStation = EmitterStation()
  var stylist: Stylist = Stylist.NoOp

  protected def systemEm: Double
  protected def isOnToolkitThread(): Boolean
  protected def runOnToolkitThread(r: () => Any): Unit

  val emSize = Var[Double]("emSize", systemEm).forInstance(Toolkit.this) 

  private val threadLocalContext = new ThreadLocal[ContextImpl] {
    override def initialValue = null
  }

  def update[R](f: Toolkit ?=> ToolkitAction[R]): R = Await.result(updateAsync(f), Duration.Inf)
  def updateAsync[R](f: Toolkit ?=> ToolkitAction[R]): Future[R] = {
    val res = Promise[R]()
    def impl() = res.complete(Try {
      var locallyCreatedContext = false
      var ctx = threadLocalContext.get()
      if (ctx == null) {
        // in an ideal world, we would use a context like the following:
        // ctx = new ContextImpl(CopyOnWriteSignalSwitchboard(switchboard, reporter), CopyOnWriteEmitterStation(emitterStation))
        // this would make the toolkit immutable, allowing you to rollback change sin case of exception.
        // Unfortunately, due to the already mutable nature of of most UI toolkits, like swing, once you impact an external property,
        // it would not matter rolling back, and it doesn't  make sense to track all changes and try to undo them (even though possible)
        // beacuse it's already contrarian to how mutable toolkits works anyway. Given all of this, we'll prefer speed here and hence
        // directly mutate the toolkit state.
        ctx = ContextImpl(switchboard, emitterStation)
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
  
  private val reactingExtVars = collection.mutable.HashSet.empty[Keyed[Signal[_]]]
  /** Executes the given thunk preventing external property changes from being propagated to the signals.
    * Reason: When computing the values for external properties, they'll notify that they were change, and we want to avoid resetting the value
    * and discarding the user provided Binding.
    */
  private def reactingToVar[R](k: Keyed[Signal[_]])(f: => R): R = {
    reactingExtVars += k
    val res = f
    reactingExtVars -= k
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
      s.keyed match {
        case v: Var[_] if v.eagerEvaluation => withContext { ctx =>
          reactingToVar(s) { ctx.switchboard(s) }
        }
        case _ =>
      }
    }

    def signalUpdated[T](sb: SignalSwitchboard[Signal], s: Keyed[Signal[T]], oldValue: Option[T], newValue: T, dependencies: collection.Set[Keyed[Signal[_]]], dependents: collection.Set[Keyed[Signal[_]]]): Unit = {
      withContext { ctx =>
        given Emitter.Context = ctx
        ctx.emit(s.instance.varUpdates, VarValueChanged(s, oldValue, newValue))
      }
    }
  }

  private class SwitchboardAsVarContext(switchboard: SignalSwitchboard[Signal]) extends VarContext {
    def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = {
      switchboard.get(v) orElse stylist(v) getOrElse v.initialValue(instance.value)
    }
    def update[T](v: Var[T], binding: Binding[T])(using instance: ValueOf[v.ForInstance]): Unit = {
      binding match {
        case Binding.Const(c) => switchboard(v) = c()
        case Binding.Compute(c) => switchboard.bind(v)(sb => c(new SwitchboardAsVarContext(sb)))
      }
    }
    def externalPropertyUpdated[T](v: Var[T], value: T)(using instance: ValueOf[v.ForInstance]): Unit = {
      if (!reactingExtVars(v) && !switchboard.get(v).exists(_ == value)) {
        switchboard(v) = value
      }
    }
  }

  /**
   * ContextImpl uses a snapshotted switchboard so the changes can be applied: they are seen (this is in order to properly implement sequence in code)
   * After the lambda that uses this context is done, the updated switchboard replaces the old one, and we update our tracking.
   */
  private class ContextImpl(
    val switchboard: SignalSwitchboard[Signal],
    var emitterStation: EmitterStation,
  ) extends SwitchboardAsVarContext(switchboard) with Emitter.Context {

    //Emitter.Context

    def emit[A](emitter: Emitter[A], evt: A)(using instance: ValueOf[emitter.ForInstance]): Unit =
      emitterStation.emit(emitter, evt)(using summon, this)
    def listen[A](emitter: Emitter[A])(f: EventIterator[A])(using instance: ValueOf[emitter.ForInstance]): Unit =
      emitterStation.listen(emitter)(f)
  }

  /** Read-only view of the current state of vars value in the toolkit */
  object stateReader {
    /** Read the current value of ObsVal as it is read by doing `prop()` within a VarContext */
    def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T = {
      val keyed: Keyed[ObsVal[T]] = v
      switchboard.get(keyed) orElse stylist(keyed) getOrElse v.initialValue(instance.value)
    }
    /** Reads the stored value of the property, if any.*/
    def get[T](property: ObsVal[T])(using instance: ValueOf[property.ForInstance]): Option[T] = switchboard.get(property)

    /** Reads the stored value of the property or its default (it doesn't asks the stylist) */
    def getOrDefault[T](property: ObsVal[T])(using instance: ValueOf[property.ForInstance]): T = get(property).getOrElse {
      val k = property.instance.asInstanceOf[property.keyed.ForInstance]
      property.keyed.initialValue(k)
    }

    def emSize: Double = 
      val prop = Toolkit.this.emSize
      getOrDefault(prop)(using ValueOf(Toolkit.this))
  }

}
