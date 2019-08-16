package guarana.signals

trait Signal[+T]

class SignalSwitchboard {
  import SignalSwitchboard._
  
  private[this] val signalStates = collection.mutable.Map.empty[Signal[_], Any]
  private[this] val signalDeps = collection.mutable.Map.empty[Signal[_], Array[Signal[_]]].withDefaultValue(Array.empty)
  private[this] val signalEvaluator = collection.mutable.Map.empty[Signal[_], Eval]

  def register[T](s: Signal[T], initialValue: T): s.type = {
    signalStates(s) = initialValue
    signalEvaluator(s) = GetState
    s
  }

  /**
   * obtains the current value of the signal
   */
  def get[T](s: Signal[T]): T = signalStates.get(s.asInstanceOf[Signal[T]]).
    getOrElse(throw new IllegalArgumentException("Such Signal does not belong to this switchboard")).asInstanceOf[T]
    
  /**
   * change the value of the signal, propagating changes as needed
   */
  def update[T](s: Signal[T], value: T): Unit = {
    val key = s.asInstanceOf[Signal[T]]
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
    val key = s.asInstanceOf[Signal[T]]
    unbindPrev(key)
    val depImpls = deps.map(_.asInstanceOf[Signal[_]]).to(Array)
    depImpls foreach (si => signalDeps(si) = signalDeps(si) :+ key)
    signalEvaluator(key) = Compute(depImpls, compute)
    propagateSignal(key)
  }

  private def propagateSignal(s: Signal[_]): Unit = {
    signalEvaluator(s) match {
      case Compute(deps, f) =>
        signalStates(s) = f(deps.map(signalStates))
      case _ =>
    }
    signalDeps(s) foreach propagateSignal
  }

  private def unbindPrev(s: Signal[_]): Unit = {
    signalEvaluator.remove(s) match {
      case Some(c: Compute) =>
        for (dep <- c.deps) signalDeps(dep) = signalDeps(dep).filterNot(s.==)
      case _ => 
    }
  }
}
object SignalSwitchboard {
  private[SignalSwitchboard] sealed trait Eval
  private[SignalSwitchboard] case object GetState extends Eval
  private[SignalSwitchboard] case class Compute(deps: Array[Signal[_]], f: Array[Any] => Any) extends Eval
}
