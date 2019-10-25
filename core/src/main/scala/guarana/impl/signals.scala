package guarana
package impl

import language.higherKinds
import SignalSwitchboard._

trait SignalSwitchboard[Signal[+T]] {
  /**
   * Obtains the current value of the signal
   */
  def apply[T](s: Keyed[Signal[T]]): T = get(s).getOrElse(throw new IllegalArgumentException(s"$s is not defined"))

  /**
   * Conditional getter for a signal's value.
   */
  def get[T](s: Keyed[Signal[T]]): Option[T]

  /**
   * Obtains the current value of the signal if the signal is found, otherwise register it with initialValue and return it.
   */
  def getOrElseUpdate[T](s: Keyed[Signal[T]], initValue: => T): T = {
    get(s) match {
      case Some(res) => res
      case _ =>
        val result = initValue
        update(s, result)
        result
    }
  }
  /**
   * Change the value of the signal, propagating changes as needed
   */
  def update[T](s: Keyed[Signal[T]], value: T): Unit
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
  def bind[T](s: Keyed[Signal[T]])(compute: SignalSwitchboard[Signal] => T): Unit
  /**
   * Removes the passed signal, removing all internal state as well as dependent signals.
   */
  def remove(s: Keyed[Signal[_]]): Unit
  /**
   * Returns a new SignalSwitchboard which is a copy of this one.
   */
  def snapshot(newReporter: Reporter[Signal]): SignalSwitchboard[Signal]

  def relationships(s: Keyed[Signal[_]]): Option[Relationships[Signal]]
}

private[impl] class SignalSwitchboardImpl[Signal[+T]](val reporter: Reporter[Signal]) extends SignalSwitchboard[Signal] {
  
  private val signalStates = collection.mutable.Map.empty[Keyed[Signal[_]], State]

  /**
   * Stores signals that should be updated when the key signal changes
   */
  private val signalDeps = collection.mutable.Map.empty[Keyed[Signal[_]], Set[Keyed[Signal[_]]]].withDefaultValue(Set.empty)

  /**
   * Stores signals whose current binding is dependent on the binding of the key Signal.
   */
  private val signalRels = collection.mutable.Map.empty[Keyed[Signal[_]], Relationships[Signal]]
  
  private val signalEvaluator = collection.mutable.Map.empty[Keyed[Signal[_]], Eval]

  def get[T](s: Keyed[Signal[T]]): Option[T] = {
    signalStates.get(s) map {
      case Value(value: T @unchecked) => value
      case Recompute(oldv) =>
        signalRels.get(s) foreach (_.dependents foreach remove) //when recomputing the value, we gotta undo all the dependents

        val tracker = new TrackingContext(s)
        if (oldv != null)//before computing the value, we set the signalState to the oldValue in case the compute lambda has a self reference
          signalStates(s) = Value(oldv)
        else
          signalStates.remove(s)
        val result = signalEvaluator(s).asInstanceOf[Compute[Signal]].f(tracker)
        signalStates(s) = Value(result)

        val computedRels = Relationships[Signal](tracker.dependencies, tracker.dependents)
        signalRels(s) = computedRels

        for (dep <- tracker.dependencies) signalDeps(dep) = signalDeps(dep) + s

        if (result != oldv)
          reporter.signalUpdated(this, s, Option(oldv), result, computedRels.dependencies, computedRels.dependents)

        result.asInstanceOf[T]
    }
  }

  def remove(s: Keyed[Signal[_]]): Unit = {
    signalStates.remove(s)
    unbindPrev(s)
    reporter.signalRemoved(this, s)
  }
    
  def update[T](s: Keyed[Signal[T]], value: T): Unit = {
    val oldv = signalStates.get(s)
    if (!oldv.exists(_ == value)) {
      unbindPrev(s)
      signalStates(s) = Value(value)
      signalEvaluator(s) = GetState
      propagateSignal(None)(s)
      reporter.signalUpdated(this, s, oldv, value, Set.empty, Set.empty)
    }
  }
  
  def bind[T](s: Keyed[Signal[T]])(compute: SignalSwitchboard[Signal] => T): Unit = {
    unbindPrev(s)
    signalEvaluator(s) = Compute[Signal](compute)
    propagateSignal(None)(s)
  }

  private def propagateSignal(parent: Option[Keyed[Signal[_]]])(s: Keyed[Signal[_]]): Unit = {
    signalEvaluator.get(s) match { //due to how propagating signal works, where the set of dependencies is iterated, it is entirely possible to find during the iteration a signal that was removed, hence why we use get here
      case Some(binding) =>
        binding match {
          case compute: Compute[Signal] =>
            signalStates.get(s) match {
              case Some(_: Recompute) => //if already recompute, do nothing
              case Some(Value(oldv)) => signalStates(s) = Recompute(oldv)
              case _ => signalStates(s) = Recompute(null)
            }
          case _ =>
        }
        signalDeps(s) foreach propagateSignal(Some(s))

        reporter.signalInvalidated(this, s)

      case _ =>
    }
  }

  def relationships(s: Keyed[Signal[_]]) = signalRels.get(s)

  private def unbindPrev(s: Keyed[Signal[_]]): Unit = {
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

  def snapshot(newReporter: Reporter[Signal]): SignalSwitchboard[Signal] = {
    val res = new SignalSwitchboardImpl[Signal](newReporter)
    res.signalStates ++= signalStates
    res.signalDeps ++= signalDeps
    res.signalEvaluator ++= signalEvaluator
    res.signalRels ++= signalRels
    res
  }

  private val outerSb = this
  class TrackingContext(forSignal: Keyed[Signal[_]]) extends SignalSwitchboard[Signal] {
    val dependencies = collection.mutable.Set.empty[Keyed[Signal[_]]]
    val dependents = collection.mutable.Set.empty[Keyed[Signal[_]]]
    def get[T](s: Keyed[Signal[T]]) = {
      if (forSignal != s) dependencies += s
      outerSb.get(s)
    }
    def update[T](s: Keyed[Signal[T]], value: T) = {
      if (forSignal != s) dependents += s
      outerSb.update(s, value)
    }
    def bind[T](s: Keyed[Signal[T]])(compute: SignalSwitchboard[Signal] => T) = {
      if (forSignal != s) dependents += s
      outerSb.bind(s)(compute)
    }
    def relationships(s: Keyed[Signal[_]]) = outerSb.relationships(s)
    def remove(s: Keyed[Signal[_]]) = outerSb.remove(s)
    def snapshot(newReporter: Reporter[Signal]) = outerSb.snapshot(newReporter)
  }
}
object SignalSwitchboard {
  private[impl] sealed trait State
  private[impl] case class Value(value: Any) extends State
  private[impl] case class Recompute(oldValue: Any) extends State

  private[impl] sealed trait Eval
  private[impl] case object GetState extends Eval
  private[impl] case class Compute[Signal[+T]](f: SignalSwitchboard[Signal] => Any) extends Eval

  private[impl] case class Relationships[Signal[T]](dependencies: collection.Set[Keyed[Signal[_]]], dependents: collection.Set[Keyed[Signal[_]]])

  trait Reporter[Signal[+T]] {
    def signalRemoved(sb: SignalSwitchboard[Signal], s: Keyed[Signal[_]]): Unit
    def signalUpdated[T](sb: SignalSwitchboard[Signal], s: Keyed[Signal[T]], oldValue: Option[T], newValue: T, dependencies: collection.Set[Keyed[Signal[_]]], dependents: collection.Set[Keyed[Signal[_]]]): Unit
    def signalInvalidated(sb: SignalSwitchboard[Signal], s: Keyed[Signal[_]]): Unit
  }

  def apply[Signal[+T]](reporter: Reporter[Signal]): SignalSwitchboard[Signal] = new SignalSwitchboardImpl[Signal](reporter)
}
