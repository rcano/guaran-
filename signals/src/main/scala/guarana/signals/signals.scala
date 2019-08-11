package guarana.signals

import scala.annotation.compileTimeOnly

abstract class Signal[T] private[signals]() {
  @compileTimeOnly("apply can only be called within the concext of signals")
  def apply(): T
  @compileTimeOnly("apply can only be called within the concext of signals")
  def :=(t: T): Unit
}

class SignalSwitchboard {
  import SignalSwitchboard._
  
  private[this] val signalStates = collection.mutable.Map.empty[SignalImpl[_], Any]
  private[this] val signalDeps = collection.mutable.Map.empty[SignalImpl[_], Array[SignalImpl[_]]]
  private[this] val signalEvaluator = collection.mutable.Map.empty[SignalImpl[_], Eval]

  def signal[T](descr: String, initialValue: T): Signal[T] = {
    val res = SignalImpl[T](descr)
    signalStates(res) = initialValue
    res
  }
  /**
   * obtains the current value of the signal
   */
  def get[T](s: Signal[T]): T = signalStates.get(s.asInstanceOf[SignalImpl[T]]).
    getOrElse(throw new IllegalArgumentException("Such Signal does not belong to this switchboard")).asInstanceOf[T]
    
  /**
   * change the value of the signal, propagating changes as needed
   */
  def update[T](s: Signal[T], value: T): Unit = {
    val key = s.asInstanceOf[SignalImpl[T]]
    unbindPrev(key)
    signalStates(key) = value
    signalEvaluator(key) = GetState
    propagateSignal(key)
  }
  
  /**
   * Bind a signal to a value computed from other signals.
   * @param s The signal to be bound
   * @param deps Dependencies for this signal
   * @param compute the function that computes the new value. Note that the elements of the Array correspond to the dependencies, but
   *        we must use an array instead of scala's Function type because there may be more than 22 dependencies.
   */
  def bind[T](s: Signal[T], deps: Signal[_]*)(compute: Array[Any] => T): Unit = {
    val key = s.asInstanceOf[SignalImpl[T]]
    unbindPrev(key)
    val depImpls = deps.map(_.asInstanceOf[SignalImpl[_]]).to(Array)
    depImpls foreach (si => signalDeps(si) = signalDeps(si) :+ key)
    signalEvaluator(key) = Compute(depImpls, compute)
    propagateSignal(key)
  }

  private def propagateSignal(s: SignalImpl[_]): Unit = {
    signalEvaluator(s) match {
      case Compute(deps, f) =>
        signalStates(s) = f(deps.map(signalStates))
        signalDeps(s) foreach propagateSignal
      case _ =>
    }
  }

  private def unbindPrev(s: SignalImpl[_]): Unit = {
    signalEvaluator.remove(s) match {
      case Some(c: Compute) =>
        for (dep <- c.deps) signalDeps(dep) = signalDeps(dep).filterNot(s.==)
      case _ => 
    }
  }
}
object SignalSwitchboard {
  private[SignalSwitchboard] case class SignalImpl[T](name: String) extends Signal[T]

  private[SignalSwitchboard] sealed trait Eval
  private[SignalSwitchboard] case object GetState extends Eval
  private[SignalSwitchboard] case class Compute(deps: Array[SignalImpl[_]], f: Array[Any] => Any) extends Eval
}
