package guarana

import scala.language.existentials
import scala.util.chaining._
import impl._

class Scenegraph {

  private[this] var switchboard = new SignalSwitchboard()
  private[this] val varToSignals = collection.mutable.HashMap.empty[Binding.Dep[_], Signal[_]]
  private[this] val varDependents = collection.mutable.HashMap.empty[Binding.Dep[_], Seq[Binding.Dep[_]]]
  
  val rootNode = Var.autoName[Node](null).forInstance(this)
  private[guarana] val mutableMouseLocation = Var.autoName[(Int, Int)]((0, 0)).forInstance(this)
  private[guarana] var requestRenderPass: () => Unit = () => ()
  def mouseLocation = mutableMouseLocation.asObsValIn(Scenegraph.this)

  private[this] var emittersData = Map.empty[Keyed[Emitter[_]], EmitterData[_]]

  {//register own vars
    getSignal(rootNode, switchboard)
    getSignal(mutableMouseLocation, switchboard)
  }

  private[this] val threadLocalVarContext = new ThreadLocal[ContextImpl]()
  private[this] val updateNestingLevel = ThreadLocal.withInitial(() => 0)
  private def log(a: Any) = () // println("  " * updateNestingLevel.get + a)

  def update[R](f: VarContext with Emitter.Context => R): R = update(None, f)
  
  private def update[R](triggerVar: Option[Binding.Dep[_]], f: VarContext with Emitter.Context => R): R = {
    updateNestingLevel.set(updateNestingLevel.get() + 1)

    var ctx = threadLocalVarContext.get()
    val locallyCreatedCtx = if (ctx == null) {
      log("ConextImpl created")
      ctx = new ContextImpl(switchboard.snapshot, emittersData)
      threadLocalVarContext.set(ctx)
      true
    } else false

    log(s"processing for $triggerVar")

    val res = f(ctx)

    if (locallyCreatedCtx) {
      switchboard = ctx.switchboard
      emittersData = ctx.emittersData

      var needRerender = false
      ctx.toUpdate foreach {
        case (variable, key, binding) =>
          val varDep = Binding.Dep(variable, key)

          //clean up previous dependents because we are rebinding this Var
          for {
            dependents <- varDependents.remove(varDep)
            dep <- dependents
            s <- varToSignals.get(dep)
          } switchboard.remove(s)

          //now register new binding
          binding match {
            case Binding.Compute(_, depnt, _) =>
              varDependents(varDep) = depnt()
            case _ =>
          }
      }

      for (Binding.Dep(variable, key) <- ctx.updatedVars) {
        //detect if the render var in nodes changed to trigger a render pass
        log(s"testing $variable for $key")
        if (variable == Node.render && key.isInstanceOf[Node]) {
          log("→should rerender")
//          if (key.isInstanceOf[Node]) {
//            println(s"$variable changed for $key")
          needRerender = true
        }
      }

      threadLocalVarContext.set(null)
      log("ConextImpl disposed")

      if (needRerender) requestRenderPass()
    }

    updateNestingLevel.set(updateNestingLevel.get() - 1)

    res
  }

  private def getSignal[T](v: Binding.Dep[T], sb: SignalSwitchboard): Signal[T] = varToSignals.
    getOrElseUpdate(v, sb.register(new Signal[T] {}, v.variable.initialValue)).asInstanceOf[Signal[T]]

  /**
   * ContextImpl uses a snapshotted switchboard so the changes can be applied as they are seen (this is in order to properly implement sequence in code)
   * After the lambda that uses this context is done, the updated switchboard replaces the old one, and we update our tracking.
   */
  private class ContextImpl(
    val switchboard: SignalSwitchboard,
    var emittersData: Map[Keyed[Emitter[_]], EmitterData[_]],
  ) extends VarContext with Emitter.Context {

    //VarContext
    val toUpdate = collection.mutable.ArrayBuffer.empty[(Var[T], Any, Binding[T]) forSome { type T }]
    val updatedVars = collection.mutable.ArrayBuffer.empty[Binding.Dep[_]]
    
    def update[T](v: Var[T], binding: Binding[T])(implicit instance: ValueOf[v.ForInstance]): Unit = {
      toUpdate += ((v, instance.value, binding))

      updatedVars += v

      val signal = getSignal(v, switchboard)
        binding match {
          case Binding.Const(v) => switchboard(signal) = v()
          case Binding.Compute(deps, _, compute) =>
            val sg = Scenegraph.this //capture this variable locally to prevent the lambda from keeping a reference to this context
            switchboard.bind(signal, deps().map(d => getSignal(d, switchboard)):_*)(_ => sg.update(Some(v), ctx => {
                  ctx.asInstanceOf[ContextImpl].updatedVars += v
                  compute(ctx)
                }))
        }
    }
    def apply[T](v: ObsVal[T])(implicit instance: ValueOf[v.ForInstance]): T = varToSignals.get(Binding.Dep(v, instance.value)).
//      map(switchboard(_).asInstanceOf[T]).getOrElse(throw new IllegalStateException(s"$v was not defined or got reclaimed"))
      map(switchboard(_).asInstanceOf[T]).getOrElse(v.initialValue)



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

  private case class Keyed[T](val key: T, instance: Any)

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
