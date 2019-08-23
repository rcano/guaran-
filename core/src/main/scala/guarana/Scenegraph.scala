package guarana

import scala.language.existentials
import signals._

class Scenegraph {

  private[this] var switchboard = new SignalSwitchboard()
  private[this] val varToSignals = collection.mutable.HashMap.empty[Var.Dep[_], Signal[_]]
  private[this] val varDependents = collection.mutable.HashMap.empty[Var.Dep[_], Seq[Var.Dep[_]]]
  private[this] val rootNode = Var.autoName[Node].forInstance(this)
  private[this] val mouse = Var.autoName[(Int, Int)].forInstance(this)


  def update[R](f: Context => R): R = {
    val ctx = new ContextImpl(switchboard.snapshot)
    val res = f(ctx)

    switchboard = ctx.switchboard

    ctx.toUpdate foreach {
      case (variable, key, binding) =>
        val varDep = Var.Dep(variable, key)

        //clean up previous dependents because we are rebinding this Var
        for {
          dependents <- varDependents.remove(varDep)
          dep <- dependents
          s <- varToSignals.get(dep)
        } switchboard.remove(s)

        //now register new binding
        binding match {
          case Var.Compute(_, depnt, _) =>
            varDependents(varDep) = depnt()
          case _ =>
        }
    }
    
    res
  }

  private def getSignal[T](v: Var.Dep[T]): Signal[T] = varToSignals.
    getOrElseUpdate(v, switchboard.register(new Signal[T] {}, null)).asInstanceOf[Signal[T]]

  trait Context extends VarContext {
    val rootNode = Scenegraph.this.rootNode
    val mouse = Scenegraph.this.mouse
  }

  /**
   * ContextImpl uses a snapshotted switchboard so the changes can be applied as they are seen (this is in order to properly implement sequence in code)
   * After the lambda that uses this context is done, the updated switchboard replaces the old one, and we update our tracking.
   */
  private class ContextImpl(val switchboard: SignalSwitchboard) extends Context {
    val toUpdate = collection.mutable.ArrayBuffer.empty[(Var[T], Any, Var.Binding[T]) forSome { type T }]
    
    def update[T](v: Var[T], binding: Var.Binding[T])(implicit instance: ValueOf[v.ForInstance]): Unit = {
      toUpdate += ((v, instance.value, binding))

      val varDep = Var.Dep(v, instance.value)
      val signal = getSignal(varDep)
        binding match {
          case Var.Const(v) => switchboard(signal) = v()
          case Var.Compute(deps, _, compute) =>
            switchboard.bind(signal, deps().map(d => getSignal(d)):_*)(sb => Scenegraph.this.update(compute(_)))
        }
    }
    def apply[T](v: Var[T])(implicit instance: ValueOf[v.ForInstance]): T = varToSignals.get(Var.Dep(v, instance.value)).map(switchboard(_).asInstanceOf[T]).getOrElse(
      throw new IllegalStateException(s"Var $v was not defined or got reclaimed"))
  }
}
