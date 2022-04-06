package guarana
package input

import java.time.Clock
import org.scalatest.funsuite.AnyFunSuite
import scala.concurrent.duration._
import scala.io.AnsiColor

class InputManagerTest extends AnyFunSuite {

  class TestInputManager extends InputManager[(Int, Boolean), Int] {
    object clock extends Clock {
      var currentTime: Long = 0
      def getZone() = java.time.ZoneId.systemDefault
      def instant() = java.time.Instant.ofEpochMilli(currentTime)
      override def withZone(z: java.time.ZoneId | Null) = ???
    }
    def combinationTolerance = 80.millis
    def getInputCode(evt: (Int, Boolean)): Int = evt._1
    def isDown(evt: (Int, Boolean)): Boolean = evt._2
    def sequenceDefaultPatience = 1.second

    var debugTransitions = false
    override protected def onEventTransition(combination: Input, event: (Int, Boolean), previousState: Any, newState: Any): Unit =
      //format: off
      if (debugTransitions) println(s"${AnsiColor.MAGENTA}[$combination]${AnsiColor.BLUE} ($previousState)${AnsiColor.RED} x ${AnsiColor.BLUE}$event${AnsiColor.RED} ==> ${AnsiColor.YELLOW}$newState${AnsiColor.RESET}")
      //format: on
  }

  class TestFixture {
    val im = TestInputManager()
    export im.syntax.{given, *}

    var triggered = false
    def addTestCombination(input: im.Input): Unit = im.recordCombination(input)(() => triggered = true)
  }

  // test numeration is done so that they are run in order since they are tested in alphabetical order, as returned by reflection

  test("Simple input")(new TestFixture {
    addTestCombination(0)
    // other inputs don't trigger it
    im.dispatch((1, true))
    assert(!triggered)
    // correct input
    im.dispatch((0, false)) // simple input ignores down state
    assert(triggered)
    triggered = false // reset
    im.dispatch((0, true)) // simple input ignores down state
    assert(triggered)
  })

  test("Pressed input")(new TestFixture {
    addTestCombination(0.↓)
    // ignores not down
    im.dispatch((0, false))
    assert(!triggered)
    // correct input
    im.dispatch((0, true))
    assert(triggered)
  })

  test("Released input")(new TestFixture {
    addTestCombination(0.↑)
    // ignores down
    im.dispatch((0, true))
    assert(!triggered)
    // correct input
    im.dispatch((0, false))
    assert(triggered)
  })

  test("Combined input, any order")(new TestFixture {
    addTestCombination(0 ~ 1)

    im.dispatch((0, true))
    assert(!triggered)
    im.dispatch((1, true))
    assert(triggered)

    triggered = false

    im.dispatch((1, true))
    assert(!triggered)
    im.dispatch((0, true))
    assert(triggered)
  })

  test("Combined input, time in between within tolerance")(new TestFixture {
    addTestCombination(0 ~ 1)

    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((1, true))
    assert(triggered)
  })

  test("Combined input, rejects when tolerance is exceeded")(new TestFixture {
    addTestCombination(0 ~ 1)

    im.dispatch((0, true))
    im.clock.currentTime += 100
    im.dispatch((1, true))
    assert(!triggered)
  })

  test("Combined input, 3 simultaneous (any order)")(new TestFixture {
    addTestCombination(0 ~ 1 ~ 2)

    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((1, true))
    im.clock.currentTime += 50
    im.dispatch((2, true))
    assert(triggered)

    triggered = false

    // try arbitrary order
    im.dispatch((2, true))
    im.clock.currentTime += 50
    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((1, true))
    assert(triggered)
  })

  test("Sequence, basic inputs")(new TestFixture {
    addTestCombination(InputSequence(0, 1, 2))

    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((1, true))
    im.clock.currentTime += 50
    im.dispatch((2, true))
    assert(triggered)
  })

  test("Sequence, accept intruders")(new TestFixture {
    addTestCombination(InputSequence(0, 1, 2).copy(allowOtherKeys = true))

    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((1, true))
    im.clock.currentTime += 50
    im.dispatch((8, true))
    im.clock.currentTime += 50
    im.dispatch((2, true))
    assert(triggered)
  })

  test("Sequence, ignoring releases by default")(new TestFixture {
    addTestCombination(InputSequence(0.↓, 1.↓, 2.↓))

    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((1, true))
    im.clock.currentTime += 50
    im.dispatch((0, false))
    im.clock.currentTime += 50
    im.dispatch((1, false))
    assert(!triggered)
    im.clock.currentTime += 50
    im.dispatch((2, true))
    assert(triggered)
  })

  test("Sequence, not ignoring releases")(new TestFixture {
    addTestCombination(InputSequence(0.↓, 1.↓, 2.↓).copy(ignoreReleases = false))

    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((1, true))
    im.clock.currentTime += 50
    im.dispatch((0, false))
    im.clock.currentTime += 50
    im.dispatch((1, false))
    assert(!triggered)
    im.clock.currentTime += 50
    im.dispatch((2, true))
    assert(!triggered)
  })

  test("WaitFor, no intruders")(new TestFixture {
    addTestCombination(InputSequence(0.↓, waitFor(1.↓, 500.millis, false, false)))

    im.dispatch((0, true))
    im.clock.currentTime += 499
    im.dispatch((1, true))
    assert(triggered)

    triggered = false

    im.dispatch((0, true))
    im.clock.currentTime += 501
    im.dispatch((1, true))
    assert(!triggered)
  })

  test("WaitFor, with intruders")(new TestFixture {
    addTestCombination(InputSequence(0.↓, waitFor(1.↓, 500.millis, false, false)))
    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((2, true)) //intruder here, and we asked to reject them
    im.clock.currentTime += 50
    im.dispatch((1, true))
    assert(!triggered)

    addTestCombination(InputSequence(2.↓, waitFor(3.↓, 500.millis, true, false)))
    im.dispatch((2, true))
    im.clock.currentTime += 50
    im.dispatch((0, true)) // intruder here, allowed
    im.clock.currentTime += 50
    im.dispatch((3, true))
    assert(triggered)
  })

  test("delay support")(new TestFixture {
    addTestCombination(InputSequence(0.↓, delay(500.millis), 0.↑).copy(ignoreReleases = false))
    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((0, false)) // released too early
    assert(!triggered)

    im.dispatch((0, true))
    im.clock.currentTime += 501
    im.dispatch((0, false))
    assert(triggered)
  })

  test("Ignore support")(new TestFixture {
    addTestCombination(InputSequence(0.↓, 0.↑).ignoring(10))
    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((11, true)) // not ignored, so must fail
    assert(!triggered)

    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((10, true)) // ignored
    im.clock.currentTime += 50
    im.dispatch((0, false)) // ignored
    assert(triggered)
  })
}
