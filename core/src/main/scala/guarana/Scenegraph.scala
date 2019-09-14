package guarana

import impl._

class Scenegraph {
  type Signal[+T] = Keyed[ObsVal[T]]
  private[this] var switchboard = SignalSwitchboard[Signal](reporter)
  
  val rootNode = Var.autoName[Node](null).forInstance(this)
  private[guarana] val mutableMouseLocation = Var.autoName[(Int, Int)]((0, 0)).forInstance(this)
  private[guarana] var requestRenderPass: () => Unit = () => ()
  def mouseLocation = mutableMouseLocation.asObsValIn(Scenegraph.this)

  private[this] var emittersData = Map.empty[Keyed[Emitter[_]], EmitterData[_]]

  {//register own vars
    switchboard(rootNode) = rootNode.initialValue
    switchboard(mutableMouseLocation) = mutableMouseLocation.initialValue
  }

//  private[this] var needRerender = false
  def update[R](f: VarContext with Emitter.Context => R): R = {
//    needRerender = false
    val ctx = new ContextImpl(switchboard.snapshot, emittersData)
    val res = f(ctx)
    switchboard = ctx.switchboard
//    if (needRerender) requestRenderPass()
    res
  }
  
  private object reporter extends SignalSwitchboard.Reporter[Signal] {

    def signalRemoved(s: Signal[_]): Unit = ()
    def signalUpdated[T](s: Signal[T], oldValue: Option[T], newValue: T, dependencies: collection.Set[Signal[_]], dependents: collection.Set[Signal[_]]): Unit = {
      if (s.keyed == Node.render && s.instance.isInstanceOf[Node]) requestRenderPass()
    }
  }

  private class SwitchboardAsVarContext(switchboard: SignalSwitchboard[Signal]) extends VarContext {
    def apply[T](v: ObsVal[T])(implicit instance: ValueOf[v.ForInstance]): T = switchboard(v)
    def update[T](v: Var[T], binding: Binding[T])(implicit instance: ValueOf[v.ForInstance]): Unit = {
      binding match {
        case Binding.Const(c) => switchboard(v) = c()
        case Binding.Compute(c) => switchboard.bind(v)(sb => c(new SwitchboardAsVarContext(sb)))
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

    def dispose(emitter: Emitter[_])(implicit instance: ValueOf[emitter.ForInstance]): Unit = {
      emittersData = emittersData.removed(Keyed(emitter, instance.value))
    }
    def emit[A](emitter: Emitter[A], evt: A)(implicit instance: ValueOf[emitter.ForInstance]): Unit = {
      val key: Keyed[Emitter[_]] = Keyed(emitter, instance.value)
      emittersData.get(key) foreach (data => emittersData = emittersData.updated(key, data.asInstanceOf[EmitterData[A]].emit(evt)))
    }
    def listen[A](emitter: Emitter[A])(f: EventIterator[A])(implicit instance: ValueOf[emitter.ForInstance]): Unit = {
      val key: Keyed[Emitter[_]] = Keyed(emitter, instance.value)
      emittersData.get(key) match {
        case Some(data) => emittersData = emittersData.updated(key, data.asInstanceOf[EmitterData[A]].addListener(f))
        case _ => throw new IllegalStateException("Can't listen to a non registered Emitter")
      }
    }
    def register(emitter: Emitter[_])(implicit instance: ValueOf[emitter.ForInstance]): Unit = {
      val key: Keyed[Emitter[_]] = Keyed(emitter, instance.value)
      if (emittersData.get(key).isEmpty) emittersData = emittersData.updated(key, EmitterData())
    }

  }

  private case class EmitterData[T](itIdx: Int = 0, listeners: collection.immutable.IntMap[EventIterator[T]] = collection.immutable.IntMap.empty[EventIterator[T]]) {
    def addListener(listener: EventIterator[T]): EmitterData[T] = copy(itIdx + 1, listeners.updated(itIdx, listener))

    def emit(evt: T): EmitterData[T] = {
      var updatedState = collection.immutable.IntMap.empty[EventIterator[T]]
      for ((key, listener) <- listeners) {
        listener.step(evt) foreach (newState => updatedState = updatedState.updated(key, newState))
      }
      copy(itIdx, updatedState)
    }
  }
}
