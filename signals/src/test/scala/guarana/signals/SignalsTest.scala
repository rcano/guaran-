package guarana.signals

import org.scalatest.FunSuite

class SignalsTest extends FunSuite {

  test("simple signal propagation") {
    val sb = new SignalSwitchboard()
    val count = sb.signal("count", 0)
    val text = sb.signal("text", "")
    sb.bind(text, count)(arr => s"current count = ${arr(0).asInstanceOf[Int]}")

    assert(sb.get(text) == "current count = 0")
    sb(count) = 1
    assert(sb.get(text) == "current count = 1")
  }

  test("complex signal propagation") {
    val sb = new SignalSwitchboard()
    val count = sb.signal("count", 0)
    val name = sb.signal("name", "Unk")
    val text = sb.signal("text", "")
    sb.bind(text, count, name)(arr => s"${arr(0)} for ${arr(1)}")

    assert(sb.get(text) == "0 for Unk")
    sb(count) = 1
    assert(sb.get(text) == "1 for Unk")
    sb(name) = "P1"
    assert(sb.get(text) == "1 for P1")
  }

  test("signal rebinding disposes old binding") {
    val sb = new SignalSwitchboard()
    val count = sb.signal("count", 0)
    val text = sb.signal("text", "")
    sb.bind(text, count)(arr => s"current count = ${arr(0).asInstanceOf[Int]}")

    assert(sb.get(text) == "current count = 0")
    sb(count) = 1
    assert(sb.get(text) == "current count = 1")
    sb(text) = "new value"
    assert(sb.get(text) == "new value")
    sb(count) = 2
    assert(sb.get(text) == "new value")
  }
}
