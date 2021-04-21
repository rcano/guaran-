package guarana
package impl

import language.implicitConversions
import org.scalatest.funsuite.AnyFunSuite
import scala.util.chaining._

class SignalsTest extends AnyFunSuite {
  trait Signal[+T] {}
  implicit def keyedSignal(s: Signal[_]): Keyed[s.type] = Keyed(s, s)

  def signal[T](initValue: T)(implicit sb: SignalSwitchboard[Signal], inferredName: guarana.util.DeclaringVal) = {
    new Signal[T] {
      override def toString = inferredName.name
    }.tap(sb(_) = initValue)
  }

  val noopReporter = new SignalSwitchboard.Reporter[Signal] {
    def signalRemoved(sb: guarana.impl.SignalSwitchboard[Signal], s: Keyed[Signal[_]]): Unit = ()
    def signalInvalidated(sb: guarana.impl.SignalSwitchboard[Signal], s: Keyed[Signal[_]]): Unit = ()
    def signalUpdated[T](
      sb: guarana.impl.SignalSwitchboard[Signal],
      s: Keyed[Signal[T]],
      oldValue: Option[T],
      newValue: T,
      dependencies: collection.Set[Keyed[Signal[_]]],
      dependents: collection.Set[Keyed[Signal[_]]]
    ): Unit = ()
  }
  val signalDescriptor = new SignalSwitchboard.SignalDescriptor[Signal] {
    def isExternal[T](s: Signal[T]) = false
    def getExternal[T](s: Signal[T], instance: Any) = ???
  }

  test("simple signal propagation") {
    implicit val sb = SignalSwitchboard[Signal](noopReporter, signalDescriptor)
    val count = signal(0)
    val text = signal("")
    sb.bind(text)(ctx => s"current count = ${ctx(count)}")

    assert(sb(text) == "current count = 0")
    assert(sb.relationships(text).exists(_.dependencies(count))) //relationships get computed lazily with the value
    sb(count) = 1
    assert(sb(text) == "current count = 1")
  }

  test("complex signal propagation") {
    implicit val sb = SignalSwitchboard[Signal](noopReporter, signalDescriptor)
    val count = signal(0)
    val name = signal("Unk")
    val text = signal("")
    sb.bind(text)(ctx => s"${ctx(count)} for ${ctx(name)}")

    assert(sb(text) == "0 for Unk")
    assert(sb.relationships(text).exists(r => r.dependencies(count) && r.dependencies(name)))
    sb(count) = 1
    assert(sb(text) == "1 for Unk")
    sb(name) = "P1"
    assert(sb(text) == "1 for P1")
  }

  test("signal rebinding disposes old binding") {
    implicit val sb = SignalSwitchboard[Signal](noopReporter, signalDescriptor)
    val count = signal(0)
    val text = signal("")
    sb.bind(text)(ctx => s"current count = ${ctx(count)}")

    assert(sb(text) == "current count = 0")
    assert(sb.relationships(text).exists(_.dependencies(count)))
    sb(count) = 1
    assert(sb(text) == "current count = 1")
    sb(text) = "new value"
    assert(sb(text) == "new value")
    sb(count) = 2
    assert(sb(text) == "new value")
  }


  test("signal self reference") {
    implicit val sb = SignalSwitchboard[Signal](noopReporter, signalDescriptor)
    val count = signal(0)
    sb.bind(count) { ctx =>
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
}
