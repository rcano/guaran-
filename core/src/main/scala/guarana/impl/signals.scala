package guarana
package impl

import animation.TransitionType
import org.agrona.collections.LongHashSet
import scala.jdk.CollectionConverters.*
import scala.concurrent.duration.FiniteDuration

import SignalSwitchboard.*

trait SignalSwitchboard {

  def defaultValueProvider: DefaultVarValueProvider

  /** Obtains the current value of the signal
    */
  def apply[T](v: ObsVal[T], instance: v.ForInstance): T = get(v, instance).getOrElse(defaultValueProvider.defaultValueFor(v, instance))

  /** Conditional getter for a signal's value.
    */
  def get[T](v: ObsVal[T], instance: v.ForInstance): Entry[T]

  /** Change the value of the signal, propagating changes as needed
    */
  def update[T](v: Var[T], instance: v.ForInstance, value: T, transitionDef: TransitionType[T] = TransitionType.Instant): Unit

  /** Notifies the switchboard that an externally tracked value changed. We can't accurately track the old value, that'll depend on the
    * external property system.
    */
  def externalPropertyChanged[T](v: ExternalObsVal[T], instance: v.ForInstance, oldValue: Option[T]): Unit

  /** Bind a signal to a value computed from other signals. The context passed to the compute function will detect which signals were read
    * and which were written. The former will become dependencies for this signal (meaning that if any of those changes, this signal will be
    * updated in turn) while the later will become dependents (which means that when the current value of the signal changes, they will be
    * disposed)
    * @param s
    *   The signal to be bound
    * @param deps
    *   Dependencies for this signal
    * @param compute
    *   the function that computes the new value. Note that the elements of the Array correspond to the dependencies, but we must use an
    *   array instead of scala's Function type because there may be more than 22 dependencies.
    */
  def bind[T](v: Var[T], instance: v.ForInstance, transitionDef: TransitionType[T])(compute: SignalSwitchboard => T): Unit

  /** Removes the passed signal, removing all internal state as well as dependent signals.
    */
  def remove(s: Keyed[ObsVal[Any]]): Unit
  def remove(v: ObsVal[Any], instance: v.ForInstance): Unit = remove(Keyed(v, instance))

  /** Returns a new SignalSwitchboard which is a copy of this one.
    */
  def snapshot(newReporter: Reporter): SignalSwitchboard

  def relationships[T](v: ObsVal[T], instance: v.ForInstance): Option[Relationships]
}

object SignalSwitchboard {
  type Entry[T] = T | NotFound.type
  object NotFound

  extension [T](e: Entry[T]) {
    inline def getOrElse[U >: T](inline r: U): U = e match
      case NotFound => r
      case other: T @unchecked => other

    inline def map[U](f: T => U): Entry[U] = e match {
      case NotFound => NotFound
      case other: T @unchecked => f(other)
    }

    inline def isEmpty: Boolean = e == NotFound
    inline def orNull: T | Null = if e == NotFound then null else e.asInstanceOf[T]
    inline def entityToOption: Option[T] = Option.when(e != NotFound)(e.asInstanceOf[T])
    // inline def foreach(inline f: T => Unit): Unit = if e != NotFound then f(e.asInstanceOf[T])
  }

  case class Relationships private[impl] (dependencies: LongHashSet, dependents: LongHashSet) {
    def descr: String = {
      val depsStr = dependencies.asScala.map(id => Keyed(id).descrString).mkString(", ")
      val dpntsStr = dependents.asScala.map(id => Keyed(id).descrString).mkString(", ")
      s"Relationships(deps=$depsStr, dpnts=$dpntsStr)"
    }
  }

  @FunctionalInterface 
  trait DefaultVarValueProvider {
    def defaultValueFor[T](v: ObsVal[T], instance: v.ForInstance): T
  }

  trait Reporter {
    def signalRemoved[T](sb: SignalSwitchboard, s: Keyed[ObsVal[T]]): Unit
    def signalUpdated[T](
        sb: SignalSwitchboard,
        v: ObsVal[T],
        instance: v.ForInstance,
        oldValue: Option[T],
        newValue: T,
    ): Unit
    def signalInvalidated[T](sb: SignalSwitchboard, v: ObsVal[T], instance: v.ForInstance): Unit
  }

  def apply(
      reporter: Reporter,
      defaultValueProvider: DefaultVarValueProvider,
      varsLookup: VarsLookup,
      useReentrancyDetection: Boolean,
      timers: animation.TimersDef
  ): SignalSwitchboard =
    // new OldSignalSwitchboardImpl(reporter, defaultValueProvider, varsLookup, useReentrancyDetection, timers)
    new BetterSignalSwitchboardImpl(reporter, defaultValueProvider, varsLookup, useReentrancyDetection, timers)

  class TrackingContext[T](outerSb: SignalSwitchboard, forSignal: Keyed[ObsVal[T]]) extends SignalSwitchboard {
    val dependencies = new LongHashSet(4)
    val dependents = new LongHashSet(4)

    def defaultValueProvider = outerSb.defaultValueProvider
    def get[T](v: ObsVal[T], instance: v.ForInstance) = {
      val s = Keyed(v, instance)
      if (forSignal != s) dependencies `add` s.id
      outerSb.get(v, instance)
    }
    def update[T](v: Var[T], instance: v.ForInstance, value: T, transitionDef: TransitionType[T]) = {
      val s = Keyed(v, instance)
      if (forSignal != s) dependents `add` s.id
      outerSb.update(v, instance, value, transitionDef)
    }
    def bind[T](v: Var[T], instance: v.ForInstance, transitionDef: TransitionType[T])(compute: SignalSwitchboard => T) = {
      val s = Keyed(v, instance)
      if (forSignal != s) dependents `add` s.id
      outerSb.bind(v, instance, transitionDef)(compute)
    }
    def externalPropertyChanged[T](v: ExternalObsVal[T], instance: v.ForInstance, oldValue: Option[T]): Unit = outerSb.externalPropertyChanged(v, instance, oldValue)
    def relationships[T](v: ObsVal[T], instance: v.ForInstance) = outerSb.relationships(v, instance)
    def remove(s: Keyed[ObsVal[Any]]): Unit = outerSb.remove(s)
    def snapshot(newReporter: Reporter) = outerSb.snapshot(newReporter)
  }

  private[impl] case class Transition[T](delay: FiniteDuration, duration: FiniteDuration, min: T, max: T, curve: animation.Curve, interp: animation.Interpolator[T]) {
    private val delayNs = delay.toNanos
    private val durationNs = duration.toNanos
    def valueAt(tNanos: Long): T =
      if (tNanos < delayNs) min
      else interp.interpolate(min, max, curve(tNanos.toDouble / durationNs))
  }
}

/** Specialized SignalSwitchboard that doesn't copy the original one until a write is performed. */
class CopyOnWriteSignalSwitchboard(
    val copy: SignalSwitchboard,
    val defaultValueProvider: DefaultVarValueProvider,
    val reporter: SignalSwitchboard.Reporter
) extends SignalSwitchboard {
  var copied: SignalSwitchboard | Null = null
  inline def theInstance = if (copied != null) copied.asInstanceOf[SignalSwitchboard] else copy
  private def createCopy() = if (copied == null) copied = copy.snapshot(reporter)
  def get[T](v: ObsVal[T], instance: v.ForInstance) = theInstance.get(v, instance)
  def update[T](v: Var[T], instance: v.ForInstance, value: T, transitionDef: TransitionType[T]) = {
    createCopy()
    theInstance.update(v, instance, value, transitionDef)
  }
  def externalPropertyChanged[T](v: ExternalObsVal[T], instance: v.ForInstance, oldValue: Option[T]): Unit = theInstance.externalPropertyChanged(v, instance, oldValue)
  def bind[T](v: Var[T], instance: v.ForInstance, transitionDef: TransitionType[T])(compute: SignalSwitchboard => T) = {
    createCopy()
    theInstance.bind(v, instance, transitionDef)(compute)
  }
  def relationships[T](v: ObsVal[T], instance: v.ForInstance) = theInstance.relationships(v, instance)
  def remove(s: Keyed[ObsVal[Any]]): Unit = {
    createCopy()
    theInstance.remove(s)
  }
  def snapshot(newReporter: SignalSwitchboard.Reporter) = CopyOnWriteSignalSwitchboard(theInstance, defaultValueProvider, newReporter)
}
