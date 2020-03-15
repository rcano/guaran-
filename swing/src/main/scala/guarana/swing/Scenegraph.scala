package guarana.swing

import language.implicitConversions
import javax.swing.SwingUtilities
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration.Duration
import scala.util.Try
import guarana.swing.impl.SignalSwitchboard

object Scenegraph {
  type ContextAction[+R] =  (given VarContext & Emitter.Context) => R
}
class Scenegraph {

  type Signal[+T] = ObsVal[T]
  private[this] var switchboard = SignalSwitchboard[Signal](reporter)
  private[this] var emittersData = Map.empty[Keyed[Emitter[_]], EmitterData[_]]
  var stylist: Stylist = Stylist.NoOp

  private val systemEm: Double = plaf.CssLaf.fontDeterminedByOs.map(_.getSize2D.toDouble).getOrElse(14)

  val emSize = Var[Double]("emSize", systemEm).forInstance(Scenegraph.this) 

  private[this] val threadLocalContext = new ThreadLocal[ContextImpl] {
    override def initialValue = null
  }

  trait VarValueChanged {
    type T
    val key: Keyed[Signal[T]]
    val prev: Option[T]
    val curr: T

    override def toString = s"VarValueChanged($key, $prev, $curr)"
  }
  object VarValueChanged {
    def apply[U](k: Keyed[Signal[U]], p: Option[U], c: U) = new VarValueChanged {
      type T = U
      val key = k
      val prev = p
      val curr = c
    }
    def unapply(v: VarValueChanged) = (v.key.keyed, v.key.instance, v.prev, v.curr)
  }

  //emitters
  val varUpdates = Emitter[VarValueChanged]().forInstance(this)

  {
    emittersData = emitterRegister(emittersData, varUpdates)(given ValueOf(this))
  }


  def update[R](f: (given Scenegraph) => Scenegraph.ContextAction[R]): R = Await.result(updateAsync(f), Duration.Inf)
  def updateAsync[R](f: (given Scenegraph) => Scenegraph.ContextAction[R]): Future[R] = {
    val res = Promise[R]()
    def impl = res.complete(Try {
      var locallyCreatedContext = false
      var ctx = threadLocalContext.get()
      if (ctx == null) {
        ctx = new ContextImpl(switchboard.snapshot(reporter), emittersData)
        threadLocalContext.set(ctx)
        locallyCreatedContext = true
      }
      val res = f(given this)(given ctx)
      if (locallyCreatedContext) {
        switchboard = ctx.switchboard
        emittersData = ctx.emittersData
        threadLocalContext.set(null)
      }
      res
    })

    if (SwingUtilities.isEventDispatchThread) impl
    else {
      val r: Runnable = () => impl
      SwingUtilities.invokeLater(r)
    }
    
    res.future
  }
  
  private val reactingSwingVars = collection.mutable.HashSet.empty[Keyed[Signal[_]]]
  /** Executes the given thunk preventing swing property changes from being propagated to the signals.
    * Reason: When computing the values for swing properties, they'll notify that they were change, and we want to avoid resetting the value
    * and discarding the user provided Binding.
    */
  private def reactingToVar[R](k: Keyed[Signal[_]])(f: => R): R = {
    reactingSwingVars += k
    val res = f
    reactingSwingVars -= k
    res
  }
  private object reporter extends SignalSwitchboard.Reporter[Signal] {

    private def withContext(f: ContextImpl => Any): Unit = {
      val ctx = threadLocalContext.get()
      if (ctx != null) //during scenegraph bootstrapping, context is null
        f(ctx)
    }

    def signalRemoved(sb: SignalSwitchboard[Signal], s: Keyed[Signal[_]]): Unit = ()
    def signalInvalidated(sb: SignalSwitchboard[Signal], s: Keyed[Signal[_]]) = {
      //swing keys need to be eagerly computed
      s.keyed match {
        case v: Var[_] if v.eagerEvaluation => withContext { ctx =>
          reactingToVar(s) { ctx.switchboard(s) }
        }
        case _ =>
      }
    }

    def signalUpdated[T](sb: SignalSwitchboard[Signal], s: Keyed[Signal[T]], oldValue: Option[T], newValue: T, dependencies: collection.Set[Keyed[Signal[_]]], dependents: collection.Set[Keyed[Signal[_]]]): Unit = {
      withContext { ctx =>
        ctx.emit(varUpdates, VarValueChanged(s, oldValue, newValue))(given ValueOf(Scenegraph.this))
      }
    }
  }

  private def emitterDispose[A](emittersData: Map[Keyed[Emitter[_]], EmitterData[_]], emitter: Emitter[A])(given instance: ValueOf[emitter.ForInstance]): Map[Keyed[Emitter[_]], EmitterData[_]] = {
    emittersData.removed(emitter)
  }
  private def emitterEmit[A](emittersData: Map[Keyed[Emitter[_]], EmitterData[_]], emitter: Emitter[A], evt: A)(given instance: ValueOf[emitter.ForInstance], ctx: VarContext & Emitter.Context): Map[Keyed[Emitter[_]], EmitterData[_]] = {
    val key: Keyed[Emitter[_]] = emitter
    emittersData.get(key) match {
      case Some(data) => emittersData.updated(key, data.asInstanceOf[EmitterData[A]].emit(evt))
      case _ => emittersData
    }
  }
  private def emitterListen[A](emittersData: Map[Keyed[Emitter[_]], EmitterData[_]], emitter: Emitter[A])(f: EventIterator[A])(given instance: ValueOf[emitter.ForInstance]): Map[Keyed[Emitter[_]], EmitterData[_]] = {
    val key: Keyed[Emitter[_]] = Keyed(emitter, instance.value)
    emittersData.get(key) match {
      case Some(data) => emittersData.updated(key, data.asInstanceOf[EmitterData[A]].addListener(f))
      case _ => throw new IllegalStateException("Can't listen to a non registered Emitter")
    }
  }
  private def emitterRegister(emittersData: Map[Keyed[Emitter[_]], EmitterData[_]], emitter: Emitter[_])(given instance: ValueOf[emitter.ForInstance]): Map[Keyed[Emitter[_]], EmitterData[_]] = {
    val key: Keyed[Emitter[_]] = Keyed(emitter, instance.value)
    if (emittersData.get(key).isEmpty) emittersData.updated(key, EmitterData())
    else emittersData
  }


  private val scenegraphInfo = new Stylist.ScenegraphInfo {
    def get[T](property: Keyed[ObsVal[T]]): Option[T] = switchboard.get(property)
  }

  private class SwitchboardAsVarContext(switchboard: SignalSwitchboard[Signal]) extends VarContext {
    def apply[T](v: ObsVal[T])(given instance: ValueOf[v.ForInstance]): T = {
      switchboard.get(v) orElse stylist(scenegraphInfo)(v) getOrElse v.initialValue(instance.value)
    }
    def update[T](v: Var[T], binding: Binding[T])(given instance: ValueOf[v.ForInstance]): Unit = {
      binding match {
        case Binding.Const(c) => switchboard(v) = c()
        case Binding.Compute(c) => switchboard.bind(v)(sb =>
            c(new SwitchboardAsVarContext(sb)))
      }
    }
    private[guarana] def swingPropertyUpdated[T](v: Var[T], value: T)(given instance: ValueOf[v.ForInstance]): Unit = {
      if (!reactingSwingVars(v) && !switchboard.get(v).exists(_ == value)) {
        switchboard(v) = value
      }
    }
  }

  /**
   * ContextImpl uses a snapshotted switchboard so the changes can be applied as they are seen (this is in order to properly implement sequence in code)
   * After the lambda that uses this context is done, the updated switchboard replaces the old one, and we update our tracking.
   */
  private class ContextImpl(
    val switchboard: SignalSwitchboard[Signal],
    var emittersData: Map[Keyed[Emitter[_]], EmitterData[_]],
  ) extends SwitchboardAsVarContext(switchboard) with Emitter.Context {

    //Emitter.Context

    def dispose(emitter: Emitter[_])(given instance: ValueOf[emitter.ForInstance]): Unit =
      emittersData = emitterDispose(emittersData, emitter)
    def emit[A](emitter: Emitter[A], evt: A)(given instance: ValueOf[emitter.ForInstance]): Unit =
      emittersData = emitterEmit(emittersData, emitter, evt)(given summon, this)
    def listen[A](emitter: Emitter[A])(f: EventIterator[A])(given instance: ValueOf[emitter.ForInstance]): Unit =
      emittersData = emitterListen(emittersData, emitter)(f)
    def register(emitter: Emitter[_])(given instance: ValueOf[emitter.ForInstance]): Unit =
      emittersData = emitterRegister(emittersData, emitter)
  }

  private case class EmitterData[T](itIdx: Int = 0, listeners: collection.immutable.IntMap[EventIterator[T]] = collection.immutable.IntMap.empty[EventIterator[T]]) {
    def addListener(listener: EventIterator[T]): EmitterData[T] = copy(itIdx + 1, listeners.updated(itIdx, listener))

    def emit(evt: T): Scenegraph.ContextAction[EmitterData[T]] = {
      var updatedListeners = collection.immutable.IntMap.empty[EventIterator[T]]
      for ((key, listener) <- listeners) {
        listener.step(evt) foreach (newState => updatedListeners = updatedListeners.updated(key, newState))
      }
      copy(itIdx, updatedListeners)
    }
  }


  /** Read-only view of the current state of vars value in the scenegraph */
  object stateReader {
    def apply[T](v: ObsVal[T])(given instance: ValueOf[v.ForInstance]): T = {
      switchboard.get(v) orElse stylist(scenegraphInfo)(v) getOrElse v.initialValue(instance.value)
    }
  }
}
