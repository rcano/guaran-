package guarana

import scala.language.existentials
import impl._

class Scenegraph {

  private[this] var switchboard = new SignalSwitchboard()
  private[this] val varToSignals = collection.mutable.HashMap.empty[Binding.Dep[_], Signal[_]]
  private[this] val varDependents = collection.mutable.HashMap.empty[Binding.Dep[_], Seq[Binding.Dep[_]]]
  private[this] val rootNode = Var.autoName[Node](null).forInstance(this)
  private[this] val mouseLocation = Var.autoName[(Int, Int)]((0, 0)).forInstance(this)


  private[this] val threadLocalVarContext = new ThreadLocal[ContextImpl]()

  def update[R](f: Context => R): R = {
    var ctx = threadLocalVarContext.get()
    val locallyCreatedCtx = if (ctx == null) {
      ctx = new ContextImpl(switchboard.snapshot)
      threadLocalVarContext.set(ctx)
      true
    } else false
    
    val res = f(ctx)

    if (locallyCreatedCtx) {
      switchboard = ctx.switchboard

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

      threadLocalVarContext.set(null)
    }
    
    res
  }

  private def getSignal[T](v: Binding.Dep[T]): Signal[T] = varToSignals.
    getOrElseUpdate(v, switchboard.register(new Signal[T] {}, v.variable.initialValue)).asInstanceOf[Signal[T]]

  trait Context extends VarContext {
    def rootNode = Scenegraph.this.rootNode
    def mouseLocation = Scenegraph.this.mouseLocation.asInstanceOf[ObsVal[(Int, Int)] { type ForInstance = Scenegraph.this.type }]
  }

  /**
   * ContextImpl uses a snapshotted switchboard so the changes can be applied as they are seen (this is in order to properly implement sequence in code)
   * After the lambda that uses this context is done, the updated switchboard replaces the old one, and we update our tracking.
   */
  private class ContextImpl(val switchboard: SignalSwitchboard) extends Context {
    val toUpdate = collection.mutable.ArrayBuffer.empty[(Var[T], Any, Binding[T]) forSome { type T }]
    
    def update[T](v: Var[T], binding: Binding[T])(implicit instance: ValueOf[v.ForInstance]): Unit = {
      toUpdate += ((v, instance.value, binding))

      val varDep = Binding.Dep(v, instance.value)
      val signal = getSignal(varDep)
        binding match {
          case Binding.Const(v) => switchboard(signal) = v()
          case Binding.Compute(deps, _, compute) =>
            switchboard.bind(signal, deps().map(d => getSignal(d)):_*)(_ => Scenegraph.this.update(compute(_)))
        }
    }
    def apply[T](v: ObsVal[T])(implicit instance: ValueOf[v.ForInstance]): T = varToSignals.get(Binding.Dep(v, instance.value)).map(switchboard(_).asInstanceOf[T]).getOrElse(
      throw new IllegalStateException(s"Var $v was not defined or got reclaimed"))
  }
}
