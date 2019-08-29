package guarana.impl

import org.scalatest.FunSuite

class SignalsTest extends FunSuite {

  def signal[T](initValue: T)(implicit sb: SignalSwitchboard) = {
    sb.register(new Signal[T] {}, initValue)
  }

  test("simple signal propagation") {
    implicit val sb = new SignalSwitchboard()
    val count = signal(0)
    val text = signal("")
    sb.bind(text, count)(ctx => s"current count = ${ctx(count)}")

    assert(sb(text) == "current count = 0")
    sb(count) = 1
    assert(sb(text) == "current count = 1")
  }

  test("complex signal propagation") {
    implicit val sb = new SignalSwitchboard()
    val count = signal(0)
    val name = signal("Unk")
    val text = signal("")
    sb.bind(text, count, name)(ctx => s"${ctx(count)} for ${ctx(name)}")

    assert(sb(text) == "0 for Unk")
    sb(count) = 1
    assert(sb(text) == "1 for Unk")
    sb(name) = "P1"
    assert(sb(text) == "1 for P1")
  }

  test("signal rebinding disposes old binding") {
    implicit val sb = new SignalSwitchboard()
    val count = signal(0)
    val text = signal("")
    sb.bind(text, count)(ctx => s"current count = ${ctx(count)}")

    assert(sb(text) == "current count = 0")
    sb(count) = 1
    assert(sb(text) == "current count = 1")
    sb(text) = "new value"
    assert(sb(text) == "new value")
    sb(count) = 2
    assert(sb(text) == "new value")
  }


  test("signal self reference") {
    implicit val sb = new SignalSwitchboard()
    val count = signal(0)
    sb.bind(count, count) { ctx =>
      println(s"current count = ${ctx(count)}")
      ctx(count) + 1
    }

    assert(sb(count) == 1) //on bind it gets called so it equals 1
    sb(count) = 1
    assert(sb(count) == 1) //but the signal doesn't trigger itself
    sb(count) = 2
    assert(sb(count) == 2)
  }
}
