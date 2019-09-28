package guarana

import language.existentials
import impl._

class Scenegraph {
  type Signal[+T] = Keyed[ObsVal[T]]
  private[this] var switchboard = SignalSwitchboard[Signal](reporter)
  private[this] var emittersData = Map.empty[Keyed[Emitter[_]], EmitterData[_]]

  private[this] val threadLocalContext = new ThreadLocal[ContextImpl] {
    override def initialValue = null
  }

  //vars definitions
  val rootNode = Var.autoName[Node](null).forInstance(this)
  private[guarana] val mutableMouseLocation = Var[(Int, Int)]("mouseLocation", (0, 0)).forInstance(this)
  val width = Var[Int]("width", 0).forInstance(this)
  val height = Var[Int]("height", 0).forInstance(this)
  def mouseLocation = mutableMouseLocation.asObsValIn(Scenegraph.this)

  {//register own vars
    switchboard(rootNode) = rootNode.initialValue
    switchboard(mutableMouseLocation) = mutableMouseLocation.initialValue
    switchboard(width) = width.initialValue
    switchboard(height) = height.initialValue
  }


  //emitters
  val varUpdates = Emitter[(Signal[T], Option[T], T) forSome { type T}]().forInstance(this)

  {
    emittersData = emitterRegister(emittersData, varUpdates)
  }

  private[guarana] var requestRenderPass: () => Unit = () => ()
//  private[this] var needRerender = false
  def update[R](f: VarContext with Emitter.Context => R): R = {
//    needRerender = false
    var locallyCreatedContext = false
    var ctx = threadLocalContext.get()
    if (ctx == null) {
      ctx = new ContextImpl(switchboard.snapshot, emittersData)
      threadLocalContext.set(ctx)
      locallyCreatedContext = true
    }
    val res = f(ctx)
    if (locallyCreatedContext) {
      switchboard = ctx.switchboard
      emittersData = ctx.emittersData
      threadLocalContext.set(null)
    }
//    if (needRerender) requestRenderPass()
    res
  }
  
  private object reporter extends SignalSwitchboard.Reporter[Signal] {

    def signalRemoved(s: Signal[_]): Unit = ()
    def signalInvalidated(s: Signal[_]) = if (s.keyed == Node.render && s.instance.isInstanceOf[Node]) requestRenderPass()
    def signalUpdated[T](s: Signal[T], oldValue: Option[T], newValue: T, dependencies: collection.Set[Signal[_]], dependents: collection.Set[Signal[_]]): Unit = {
      val ctx = threadLocalContext.get()
      if (ctx != null) //during scenegraph bootstrapping, context is null
        ctx.emit(varUpdates, (s, oldValue, newValue))
    }
  }

  private def emitterDispose(emittersData: Map[Keyed[Emitter[_]], EmitterData[_]], emitter: Emitter[_])(implicit instance: ValueOf[emitter.ForInstance]): Map[Keyed[Emitter[_]], EmitterData[_]] = {
    emittersData.removed(emitter)
  }
  private def emitterEmit[A](emittersData: Map[Keyed[Emitter[_]], EmitterData[_]], emitter: Emitter[A], evt: A)(implicit instance: ValueOf[emitter.ForInstance]): Map[Keyed[Emitter[_]], EmitterData[_]] = {
    val key: Keyed[Emitter[_]] = emitter
    emittersData.get(key) match {
      case Some(data) => emittersData.updated(key, data.asInstanceOf[EmitterData[A]].emit(evt))
      case _ => emittersData
    }
  }
  private def emitterListen[A](emittersData: Map[Keyed[Emitter[_]], EmitterData[_]], emitter: Emitter[A])(f: EventIterator[A])(implicit instance: ValueOf[emitter.ForInstance]): Map[Keyed[Emitter[_]], EmitterData[_]] = {
    val key: Keyed[Emitter[_]] = Keyed(emitter, instance.value)
    emittersData.get(key) match {
      case Some(data) => emittersData.updated(key, data.asInstanceOf[EmitterData[A]].addListener(f))
      case _ => throw new IllegalStateException("Can't listen to a non registered Emitter")
    }
  }
  private def emitterRegister(emittersData: Map[Keyed[Emitter[_]], EmitterData[_]], emitter: Emitter[_])(implicit instance: ValueOf[emitter.ForInstance]): Map[Keyed[Emitter[_]], EmitterData[_]] = {
    val key: Keyed[Emitter[_]] = Keyed(emitter, instance.value)
    if (emittersData.get(key).isEmpty) emittersData.updated(key, EmitterData())
    else emittersData
  }


  private class SwitchboardAsVarContext(switchboard: SignalSwitchboard[Signal]) extends VarContext {
    def apply[T](v: ObsVal[T])(implicit instance: ValueOf[v.ForInstance]): T = switchboard.getOrElseUpdate(v, v.initialValue)
    def update[T](v: Var[T], binding: Binding[T])(implicit instance: ValueOf[v.ForInstance]): Unit = {
      binding match {
        case Binding.Const(c) => switchboard(v) = c()
        case Binding.Compute(c) => switchboard.bind(v)(sb =>
            c(new SwitchboardAsVarContext(sb)))
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

    def dispose(emitter: Emitter[_])(implicit instance: ValueOf[emitter.ForInstance]): Unit =
      emittersData = emitterDispose(emittersData, emitter)
    def emit[A](emitter: Emitter[A], evt: A)(implicit instance: ValueOf[emitter.ForInstance]): Unit =
      emittersData = emitterEmit(emittersData, emitter, evt)
    def listen[A](emitter: Emitter[A])(f: EventIterator[A])(implicit instance: ValueOf[emitter.ForInstance]): Unit =
      emittersData = emitterListen(emittersData, emitter)(f)
    def register(emitter: Emitter[_])(implicit instance: ValueOf[emitter.ForInstance]): Unit =
      emittersData = emitterRegister(emittersData, emitter)
  }

  private case class EmitterData[T](itIdx: Int = 0, listeners: collection.immutable.IntMap[EventIterator[T]] = collection.immutable.IntMap.empty[EventIterator[T]]) {
    def addListener(listener: EventIterator[T]): EmitterData[T] = copy(itIdx + 1, listeners.updated(itIdx, listener))

    def emit(evt: T): EmitterData[T] = {
      var updatedListeners = collection.immutable.IntMap.empty[EventIterator[T]]
      for ((key, listener) <- listeners) {
        listener.step(evt) foreach (newState => updatedListeners = updatedListeners.updated(key, newState))
      }
      copy(itIdx, updatedListeners)
    }
  }
}
