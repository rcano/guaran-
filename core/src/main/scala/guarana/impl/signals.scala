package guarana
package impl

import language.higherKinds
import org.agrona.collections.{LongHashSet, Long2ObjectHashMap}
import scala.jdk.CollectionConverters.*
import AgronaUtils.*
import SignalSwitchboard.*

trait SignalSwitchboard[Signal[+T] <: util.Unique] {
  /**
   * Obtains the current value of the signal
   */
  def apply[T](s: Keyed[Signal[T]]): T = get(s).getOrElse(throw new IllegalArgumentException(s"$s is not defined"))

  /**
   * Conditional getter for a signal's value.
   */
  def get[T](s: Keyed[Signal[T]]): Entry[T]

  /**
   * Obtains the current value of the signal if the signal is found, otherwise register it with initialValue and return it.
   */
  def getOrElseUpdate[T](s: Keyed[Signal[T]], initValue: => T): T = {
    get(s) match {
      case NotFound =>
        val result = initValue
        update(s, result)
        result
      case res: T => res
    }
  }
  /**
   * Change the value of the signal, propagating changes as needed
   */
  def update[T](s: Keyed[Signal[T]], value: T): Unit
  /** Notifies the switchboard that an externally tracked value changed.
    * We can't accurately track the old value, that'll depend on the external property system.
    */
  def externalPropertyChanged[T](s: Keyed[Signal[T]], oldValue: Option[T]): Unit
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
  def remove(s: Keyed[Signal[Any]]): Unit
  /**
   * Returns a new SignalSwitchboard which is a copy of this one.
   */
  def snapshot(newReporter: Reporter[Signal]): SignalSwitchboard[Signal]

  def relationships(s: Keyed[Signal[Any]]): Option[Relationships[Signal]]
}

private[impl] class SignalSwitchboardImpl[Signal[+T] <: util.Unique](
  val reporter: Reporter[Signal],
  val signalDescriptor: SignalDescriptor[Signal]
) extends SignalSwitchboard[Signal] {
  import State.*
  private val signalStates = new Long2ObjectHashMap[State](1024, 0.67f)
  // private val signalStates = KeyedWeakHashMap[Signal[Any], State]

  /**
   * Stores signals that should be updated when the key signal changes
   */
  // private val signalDeps = KeyedWeakHashMap[Signal[Any], Set[Keyed[Signal[Any]]]].withDefaultValue(Set.empty)
  private val signalDeps = new Long2ObjectHashMap[LongHashSet]()

  /**
   * Stores signals whose current binding is dependent on the binding of the key Signal.
   */
  // private val signalRels = KeyedWeakHashMap[Signal[Any], Relationships[Signal]]
  private val signalRels = new Long2ObjectHashMap[Relationships[Signal]]()
  
  // private val signalEvaluator = KeyedWeakHashMap[Signal[Any], Compute[Signal]]
  private val signalEvaluator = new Long2ObjectHashMap[Compute[Signal]]()

  def get[T](s: Keyed[Signal[T]]): Entry[T] = {
    (signalStates.get(s.id): @unchecked) match {
      case null => NotFound
      case Value(null) => null.asInstanceOf[T]
      case Value(value: T @unchecked) => value
      case Recompute(oldv) =>
        signalRels.get(s.id).?(_.dependents fastForeach (e => remove(Keyed(e)))) //when recomputing the value, we gotta undo all the dependents

        val tracker = new TrackingContext(s)
        // before computing the value, we set the signalState to the oldValue in case the compute lambda has a self reference
        if (oldv != null) signalStates.put(s.id, Value(oldv))
        else signalStates.remove(s.id)
        val result = signalEvaluator.get(s.id).asInstanceOf[Compute[Signal]].f(tracker)
        signalStates.put(s.id, Value(result))

        val computedRels = Relationships[Signal](tracker.dependencies, tracker.dependents)
        signalRels.put(s.id, computedRels)

        tracker.dependencies.fastForeach(dep =>
          var deps = signalDeps.get(dep)
          if deps == null then
            deps = new LongHashSet()
            signalDeps.put(dep, deps)
          deps add s.id
        )

        if (result != oldv)
          reporter.signalUpdated(this, s, Option(oldv), result, computedRels.dependencies, computedRels.dependents)

        result.asInstanceOf[T]
      case External => signalDescriptor.getExternal(s)
    }
  }

  def remove(s: Keyed[Signal[Any]]): Unit = {
    signalStates.remove(s.id)
    unbindPrev(s)
    reporter.signalRemoved(this, s)
  }
    
  def update[T](s: Keyed[Signal[T]], value: T): Unit = {
    val oldv = signalStates.get(s.id).toOption
    if (!oldv.exists {
      case Value(`value`) => true
      case External => signalDescriptor.getExternal(s) == value
      case _ => false
    }) {
      unbindPrev(s)
      signalStates.put(s.id, if signalDescriptor.isExternal(s) then External else Value(value))
      propagateSignal(None)(s)
      reporter.signalUpdated(this, s, oldv, value, EmptyUnmodifiableLongHashSet, EmptyUnmodifiableLongHashSet)
    }
  }

  def externalPropertyChanged[T](s: Keyed[Signal[T]], oldValue: Option[T]): Unit =
    // an external change doesn't mean a voluntary (by the user) change on the behavior of the signal (whether compute or set)
    // so we don't unbind its current state and instead just propagate the signal invalidation, unless there is no current state, in
    // which case it means the external signal was never observed, and we need to setup an initial state so signal propagation works
    if !signalStates.containsKey(s.id) then signalStates.put(s.id, External)
    propagateSignal(None)(s)
    reporter.signalUpdated(this, s, oldValue, signalDescriptor.getExternal(s), EmptyUnmodifiableLongHashSet, EmptyUnmodifiableLongHashSet)
  
  def bind[T](s: Keyed[Signal[T]])(compute: SignalSwitchboard[Signal] => T): Unit = {
    unbindPrev(s)
    signalStates.put(s.id, Recompute(get(s).orNull)) 
    signalEvaluator.put(s.id, Compute[Signal](compute))
    propagateSignal(None)(s)
  }

  private def propagateSignal(parent: Option[Keyed[Signal[Any]]])(s: Keyed[Signal[Any]]): Unit = {
    // due to how propagating signal works, where the set of dependencies is iterated, it is entirely possible to find during
    // the iteration a signal that was removed, hence why we check here if that's the case by checking the state
    if !signalStates.containsKey(s.id) then return

    signalEvaluator.get(s.id).? { compute =>
      signalStates.get(s.id) match {
        case null => signalStates.put(s.id, Recompute(null))
        case _: Recompute => //if already recompute, do nothing
        case Value(oldv) => signalStates.put(s.id, Recompute(oldv))
        case External => signalStates.put(s.id, Recompute(signalDescriptor.getExternal(s)))
      }
    }
    signalDeps.get(s.id).?(_.fastForeach(l => propagateSignal(Some(s))(Keyed(l))))

    // report at the end of the process, so that all of `s` dependencies get reported and invalidated first
    reporter.signalInvalidated(this, s)
  }

  def relationships(s: Keyed[Signal[Any]]) = signalRels.get(s.id).toOption

  private def unbindPrev(s: Keyed[Signal[Any]]): Unit = {
    signalEvaluator.remove(s.id) match {
      case c: Compute[Signal] =>
        signalRels.remove(s.id) match {
          case null =>
          case Relationships(deps, denpts) =>
            denpts fastForeach (l => remove(Keyed(l)))
            deps fastForeach (dep =>
              signalDeps.get(dep).?(_.remove(s.id))
            )
        }
      case _ => 
    }
  }

  def snapshot(newReporter: Reporter[Signal]): SignalSwitchboard[Signal] = {
    val res = new SignalSwitchboardImpl[Signal](newReporter, signalDescriptor)
    res.signalStates putAll signalStates
    res.signalDeps putAll signalDeps
    res.signalEvaluator putAll signalEvaluator
    res.signalRels putAll signalRels
    res
  }

  private val outerSb = this
  class TrackingContext[T](forSignal: Keyed[Signal[T]]) extends SignalSwitchboard[Signal] {
    val dependencies = new LongHashSet(4)
    val dependents = new LongHashSet(4)
    def get[T](s: Keyed[Signal[T]]) = {
      if (forSignal != s) dependencies add s.id
      outerSb.get(s)
    }
    def update[T](s: Keyed[Signal[T]], value: T) = {
      if (forSignal != s) dependents add s.id
      outerSb.update(s, value)
    }
    def bind[T](s: Keyed[Signal[T]])(compute: SignalSwitchboard[Signal] => T) = {
      if (forSignal != s) dependents add s.id
      outerSb.bind(s)(compute)
    }
    def externalPropertyChanged[T](s: Keyed[Signal[T]], oldValue: Option[T]): Unit = outerSb.externalPropertyChanged(s, oldValue)
    def relationships(s: Keyed[Signal[Any]]) = outerSb.relationships(s)
    def remove(s: Keyed[Signal[Any]]) = outerSb.remove(s)
    def snapshot(newReporter: Reporter[Signal]) = outerSb.snapshot(newReporter)
  }
}
object SignalSwitchboard {
  type Entry[T] = T | NotFound.type
  object NotFound

  private[impl] val EmptyUnmodifiableLongHashSet = new LongHashSet {

  }

  extension [T](e: Entry[T]) {
    inline def getOrElse[U >: T](inline r: U): U = e match
      case NotFound => r
      case other: T => other

    inline def isEmpty: Boolean = e == NotFound
    inline def orNull: T | Null = if e == NotFound then null else e.asInstanceOf[T]
    // inline def foreach(inline f: T => Unit): Unit = if e != NotFound then f(e.asInstanceOf[T])
  }

  private[impl] enum State {
    case Value(value: Any)
    case Recompute(oldValue: Any)
    case External
  }
  
  private[impl] case class Compute[Signal[+T] <: util.Unique](f: SignalSwitchboard[Signal] => Any)

  private[impl] case class Relationships[Signal[T] <: util.Unique](dependencies: LongHashSet, dependents: LongHashSet)

  trait Reporter[Signal[+T] <: util.Unique] {
    def signalRemoved(sb: SignalSwitchboard[Signal], s: Keyed[Signal[Any]]): Unit
    def signalUpdated[T](sb: SignalSwitchboard[Signal], s: Keyed[Signal[T]], oldValue: Option[T], newValue: T, dependencies: LongHashSet, dependents: LongHashSet): Unit
    def signalInvalidated(sb: SignalSwitchboard[Signal], s: Keyed[Signal[Any]]): Unit
  }

  trait SignalDescriptor[Signal[+T] <: util.Unique] {
    def isExternal[T](s: Keyed[Signal[T]]): Boolean
    def getExternal[T](s: Keyed[Signal[T]]): T
    def describe[T](s: Keyed[Signal[T]]): String
  }

  def apply[Signal[+T] <: util.Unique](reporter: Reporter[Signal], signalDescriptor: SignalDescriptor[Signal]): SignalSwitchboard[Signal] =
    new SignalSwitchboardImpl[Signal](reporter, signalDescriptor)
}

/** Specialized SignalSwitchboard that doesn't copy the original one until a write is performed. */
class CopyOnWriteSignalSwitchboard[Signal[+T] <: util.Unique](val copy: SignalSwitchboard[Signal], val reporter: SignalSwitchboard.Reporter[Signal]) extends SignalSwitchboard[Signal] {
  var copied: SignalSwitchboard[Signal] | Null = null
  inline def theInstance = if (copied != null) copied.asInstanceOf[SignalSwitchboard[Signal]] else copy
  private def createCopy() = if (copied == null) copied = copy.snapshot(reporter)
  def get[T](s: Keyed[Signal[T]]) = theInstance.get(s)
  def update[T](s: Keyed[Signal[T]], value: T) = {
    createCopy()
    theInstance.update(s, value)
  }
  def externalPropertyChanged[T](s: Keyed[Signal[T]], oldValue: Option[T]): Unit = theInstance.externalPropertyChanged(s, oldValue)
  def bind[T](s: Keyed[Signal[T]])(compute: SignalSwitchboard[Signal] => T) = {
    createCopy()
    theInstance.bind(s)(compute)
  }
  def relationships(s: Keyed[Signal[Any]]) = theInstance.relationships(s)
  def remove(s: Keyed[Signal[Any]]) = {
    createCopy()
    theInstance.remove(s)
  }
  def snapshot(newReporter: SignalSwitchboard.Reporter[Signal]) = CopyOnWriteSignalSwitchboard(theInstance, newReporter)
}