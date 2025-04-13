package guarana
package impl

import language.implicitConversions
import org.agrona.collections.LongHashSet
import org.scalatest.funsuite.AnyFunSuite
import scala.concurrent.duration.*
import scala.util.chaining._

class SignalsTest extends AnyFunSuite {
  trait Signal[+T] extends util.Unique {
    def keyed: Keyed[this.type] = Keyed(this, this)
  }
  implicit def keyedSignal[T](s: Signal[T]): Keyed[s.type] = Keyed(s, s)

  def signal[T](initValue: T)(implicit sb: SignalSwitchboard[Signal], inferredName: guarana.util.DeclaringOwner) = {
    new Signal[T] {
      override def toString = inferredName.name
    }.tap(sb(_) = initValue)
  }

  val noopReporter = new SignalSwitchboard.Reporter[Signal] {
    def signalRemoved(sb: guarana.impl.SignalSwitchboard[Signal], s: Keyed[Signal[?]]): Unit = ()
    def signalInvalidated(sb: guarana.impl.SignalSwitchboard[Signal], s: Keyed[Signal[?]]): Unit = ()
    def signalUpdated[T](
        sb: guarana.impl.SignalSwitchboard[Signal],
        s: Keyed[Signal[T]],
        oldValue: Option[T],
        newValue: T,
        dependencies: LongHashSet,
        dependents: LongHashSet
    ): Unit = ()
  }
  val signalDescriptor = new SignalSwitchboard.SignalDescriptor[Signal] {
    def isExternal[T](s: Keyed[Signal[T]]) = false
    def getExternal[T](s: Keyed[Signal[T]]) = ???
    def describe[T](s: Keyed[Signal[T]]): String = s.id.toHexString

  }

  import animation.TransitionType

  test("simple signal propagation") {
    implicit val sb = SignalSwitchboard[Signal](noopReporter, signalDescriptor, false, ManualTimers)
    val count = signal(0)
    val text = signal("")
    sb.bind(text, TransitionType.Instant)(ctx => s"current count = ${ctx(count)}")

    assert(sb(text) == "current count = 0")
    assert(sb.relationships(text).exists(_.dependencies.contains(count.keyed.id))) //relationships get computed lazily with the value
    sb(count) = 1
    assert(sb(text) == "current count = 1")
  }

  test("complex signal propagation") {
    implicit val sb = SignalSwitchboard[Signal](noopReporter, signalDescriptor, false, ManualTimers)
    val count = signal(0)
    val name = signal("Unk")
    val text = signal("")
    sb.bind(text, TransitionType.Instant)(ctx => s"${ctx(count)} for ${ctx(name)}")

    assert(sb(text) == "0 for Unk")
    assert(sb.relationships(text).exists(r => r.dependencies.contains(count.keyed.id) && r.dependencies.contains(name.keyed.id)))
    sb(count) = 1
    assert(sb(text) == "1 for Unk")
    sb(name) = "P1"
    assert(sb(text) == "1 for P1")
  }

  test("signal rebinding disposes old binding") {
    implicit val sb = SignalSwitchboard[Signal](noopReporter, signalDescriptor, false, ManualTimers)
    val count = signal(0)
    val text = signal("")
    sb.bind(text, TransitionType.Instant)(ctx => s"current count = ${ctx(count)}")

    assert(sb(text) == "current count = 0")
    assert(sb.relationships(text).exists(_.dependencies.contains(count.keyed.id)))
    sb(count) = 1
    assert(sb(text) == "current count = 1")
    sb(text) = "new value"
    assert(sb(text) == "new value")
    sb(count) = 2
    assert(sb(text) == "new value")
  }

  test("signal self reference") {
    implicit val sb = SignalSwitchboard[Signal](noopReporter, signalDescriptor, false, ManualTimers)
    val count = signal(0)
    sb.bind(count, TransitionType.Instant) { ctx =>
      println(s"current count = ${ctx(count)}")
      ctx(count) + 1
    }

    assert(sb(count) == 1) //on bind it gets called so it equals 1
    assert(sb.relationships(count).exists(_.dependencies.isEmpty))
    sb(count) = 1
    assert(sb(count) == 1) //but the signal doesn't trigger itself
    sb(count) = 2
    assert(sb(count) == 2)
  }

  test("signal transitions on values") {
    implicit val sb = SignalSwitchboard[Signal](noopReporter, signalDescriptor, false, ManualTimers)
    val count = signal(0)
    sb.update(count, 8, TransitionType.Interp(Duration.Zero, 400.millis, animation.LinearCurve, 0, 60))
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())  // this call causes the Timeline to start tracking time

    // advance time some
    Thread.sleep(55)
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())
    assert(sb(count) == 1)
    Thread.sleep(55)
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())
    assert(sb(count) == 2)
  }

  test("signal transitions on bindings") {
    implicit val sb = SignalSwitchboard[Signal](noopReporter, signalDescriptor, false, ManualTimers)
    val count = signal(4)
    val mult = signal(0)
    sb.bind(mult, TransitionType.Interp(Duration.Zero, 400.millis, animation.LinearCurve, 0, 60))(ctx => ctx(count) * 2)

    assert(sb(mult) == 0)
    assert(sb.relationships(mult).exists(_.dependencies.contains(count.keyed.id))) //relationships get computed lazily with the value
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())  // this call causes the Timeline to start tracking time

    // beacuse we are linearly inteprolating from 0 to 8, in 400 millis, it means the numbre goes up by one every 50ms

    for (expected <- 1 to 8) {
      // advance time some
      Thread.sleep(55)
      ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())
      assert(sb(mult) == expected)
    }

    assert(ManualTimers.createdTimers.isEmpty)
  }

  test("signal transitions on bindings can be interrupted when a dependency changes") {
    implicit val sb = SignalSwitchboard[Signal](noopReporter, signalDescriptor, false, ManualTimers)
    val count = signal(4)
    val mult = signal(0)
    sb.bind(mult, TransitionType.Interp(Duration.Zero, 400.millis, animation.LinearCurve, 0, 60))(ctx => ctx(count) * 2)

    assert(sb(mult) == 0)
    assert(sb.relationships(mult).exists(_.dependencies.contains(count.keyed.id))) //relationships get computed lazily with the value
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())  // this call causes the Timeline to start tracking time

    // beacuse we are linearly inteprolating from 0 to 8, in 400 millis, it means the numbre goes up by one every 50ms

    // advance time some
    Thread.sleep(105)
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())
    assert(sb(mult) == 2)
    sb.update(count, 10)
    assert(ManualTimers.createdTimers.isEmpty)

    assert(sb(mult) == 2) // value must continue to be it's last known value
    assert(ManualTimers.createdTimers.size == 1)
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime()) // this call causes the Timeline to start tracking time
    // after reading it, it must retrigger a transition, this time to the number 20
    Thread.sleep(105) // wait two "steps"
    ManualTimers.createdTimers.toSeq.foreach(_.runCurrTime())
    assert(sb(mult) == 6)
  }
}
