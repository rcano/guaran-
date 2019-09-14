package guarana.impl

import language.higherKinds
import SignalSwitchboard._

trait SignalSwitchboard[Signal[+T]] {
  /**
   * obtains the current value of the signal
   */
  def apply[T](s: Signal[T]): T
  /**
   * change the value of the signal, propagating changes as needed
   */
  def update[T](s: Signal[T], value: T): Unit
  /**
   * Bind a signal to a value computed from other signals.
   * The context passed to the compute function will detect which signals were read and which were written. The former will become dependencies
   * for this signal (meaning that if any of those changes, this signal will be updated in turn) while the later will become dependents (which
   * means that when the current value of the signal changes, they will be disposed)
   * @param s The signal to be bound
   * @param deps Dependencies for this signal
   * @param compute the function that computes the new value. Note that the elements of the Array correspond to the dependencies, but
   *        we must use an array instead of scala's Function type because there may be more than 22 dependencies.
   */
  def bind[T](s: Signal[T])(compute: SignalSwitchboard[Signal] => T): Unit
  /**
   * Removes the passed signal, removing all internal state as well as dependent signals.
   */
  def remove(s: Signal[_]): Unit
  /**
   * Returns a new SignalSwitchboard which is a copy of this one.
   */
  def snapshot: SignalSwitchboard[Signal]

  def relationships(s: Signal[_]): Option[Relationships[Signal]]
}

private[impl] class SignalSwitchboardImpl[Signal[+T]](val reporter: Reporter[Signal]) extends SignalSwitchboard[Signal] {
  
  private val signalStates = collection.mutable.Map.empty[Signal[_], Any]

  /**
   * Stores signals that should be updated when the key signal changes
   */
  private val signalDeps = collection.mutable.Map.empty[Signal[_], Set[Signal[_]]].withDefaultValue(Set.empty)

  /**
   * Stores signals whose current binding is dependent on the binding of the key Signal.
   */
  private val signalRels = collection.mutable.Map.empty[Signal[_], Relationships[Signal]]
  
  private val signalEvaluator = collection.mutable.Map.empty[Signal[_], Eval]

  def apply[T](s: Signal[T]): T = signalStates.get(s.asInstanceOf[Signal[T]]).
    getOrElse(throw new IllegalArgumentException(s"$s is not defined")).asInstanceOf[T]

  def remove(s: Signal[_]): Unit = {
    signalStates.remove(s)
    unbindPrev(s)
    reporter.signalRemoved(s)
  }
    
  def update[T](s: Signal[T], value: T): Unit = {
    val oldv = signalStates.get(s)
    unbindPrev(s)
    signalStates(s) = value
    signalEvaluator(s) = GetState
    propagateSignal(s)
    reporter.signalUpdated(s, oldv, value, Set.empty, Set.empty)
  }
  
  def bind[T](s: Signal[T])(compute: SignalSwitchboard[Signal] => T): Unit = {
    val oldv = signalStates.get(s)
    unbindPrev(s)
    signalEvaluator(s) = Compute[Signal](compute)
    propagateSignal(s)
    val rels = signalRels(s)
    reporter.signalUpdated(s, oldv, signalStates(s), rels.dependencies, rels.dependents)
  }

  private def propagateSignal(s: Signal[_]): Unit = {
    signalEvaluator(s) match {
      case compute: Compute[Signal] =>
        val tracker = new TrackingContext(s)
        signalStates(s) = compute.f(tracker)
        signalRels(s) = Relationships[Signal](tracker.dependencies, tracker.dependents)
        for (dep <- tracker.dependencies) signalDeps(dep) = signalDeps(dep) + s
      case _ =>
    }
    signalDeps(s) foreach propagateSignal
//    reporter.signalUpdated(s,)
  }

  def relationships(s: Signal[_]) = signalRels.get(s)

  private def unbindPrev(s: Signal[_]): Unit = {
    signalEvaluator.remove(s) match {
      case Some(c: Compute[Signal]) =>
        signalRels.remove(s) match {
          case Some(Relationships(deps, denpts)) =>
            denpts foreach remove
            for (dep <- deps) signalDeps(dep) = signalDeps(dep) - s
          case _ =>
        }
      case _ => 
    }
  }

  def snapshot: SignalSwitchboard[Signal] = {
    val res = new SignalSwitchboardImpl[Signal](reporter)
    res.signalStates ++= signalStates
    res.signalDeps ++= signalDeps
    res.signalEvaluator ++= signalEvaluator
    res.signalRels ++= signalRels
    res
  }

  private val outerSb = this
  class TrackingContext(forSignal: Signal[_]) extends SignalSwitchboard[Signal] {
    val dependencies = collection.mutable.Set.empty[Signal[_]]
    val dependents = collection.mutable.Set.empty[Signal[_]]
    def apply[T](s: Signal[T]) = {
      if (forSignal != s) dependencies += s
      outerSb.apply(s)
    }
    def update[T](s: Signal[T], value: T) = {
      if (forSignal != s) dependents += s
      outerSb.update(s, value)
    }
    def bind[T](s: Signal[T])(compute: SignalSwitchboard[Signal] => T) = {
      if (forSignal != s) dependents += s
      outerSb.bind(s)(compute)
    }
    def relationships(s: Signal[_]) = outerSb.relationships(s)
    def remove(s: Signal[_]) = outerSb.remove(s)
    def snapshot = outerSb.snapshot
  }
}
object SignalSwitchboard {
  private[impl] sealed trait Eval
  private[impl] case object GetState extends Eval
  private[impl] case class Compute[Signal[+T]](f: SignalSwitchboard[Signal] => Any) extends Eval

  private[impl] case class Relationships[Signal[T]](dependencies: collection.Set[Signal[_]], dependents: collection.Set[Signal[_]])

  trait Reporter[Signal[+T]] {
    def signalRemoved(s: Signal[_]): Unit
    def signalUpdated[T](s: Signal[T], oldValue: Option[T], newValue: T, dependencies: collection.Set[Signal[_]], dependents: collection.Set[Signal[_]]): Unit
  }

  def apply[Signal[+T]](reporter: Reporter[Signal]): SignalSwitchboard[Signal] = new SignalSwitchboardImpl[Signal](reporter)
}
