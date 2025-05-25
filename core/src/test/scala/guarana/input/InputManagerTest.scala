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
    override protected def onEventTransition(combination: InputMatcher[?], event: (Int, Boolean), previousState: Any, newState: Any): Unit =
      //format: off
      if (debugTransitions) println(s"${AnsiColor.MAGENTA}[$combination]${AnsiColor.BLUE} ($previousState)${AnsiColor.RED} x ${AnsiColor.BLUE}$event${AnsiColor.RED} ==> ${AnsiColor.YELLOW}$newState${AnsiColor.RESET}")
      //format: on
  }

  class TestFixture {
    val im = TestInputManager()

    var triggered = false
    def addTestCombination[T <: Tuple](input: im.InputMatcher[T]): Unit = im.recordCombination(input)(_ => triggered = true)
  }

  test("Simple input")(new TestFixture {
    import im.syntax.given
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
    import im.syntax.{given, *}
    addTestCombination(0.↓)
    // ignores not down
    im.dispatch((0, false))
    assert(!triggered)
    // correct input
    im.dispatch((0, true))
    assert(triggered)
  })

  test("Released input")(new TestFixture {
    import im.syntax.{given, *}
    addTestCombination(0.↑)
    // ignores down
    im.dispatch((0, true))
    assert(!triggered)
    // correct input
    im.dispatch((0, false))
    assert(triggered)
  })

  test("Alternatives")(new TestFixture {
    import im.syntax.{given, *}
    addTestCombination(0.↑ || 0.↓)
    im.dispatch((0, false))
    assert(triggered)
    triggered = false
    im.dispatch((0, true))
    assert(triggered)
    triggered = false
    im.dispatch((1, true)) //other things don't work
    assert(!triggered)
  })

  test("Combined input, any order")(new TestFixture {
    import im.syntax.{given, *}
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
    import im.syntax.{given, *}
    addTestCombination(0 ~ 1)

    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((1, true))
    assert(triggered)
  })

  test("Combined input, rejects when tolerance is exceeded")(new TestFixture {
    import im.syntax.{given, *}
    addTestCombination(0 ~ 1)

    im.dispatch((0, true))
    im.clock.currentTime += 100
    im.dispatch((1, true))
    assert(!triggered)
  })

  test("Combined input, 3 simultaneous (any order)")(new TestFixture {
    import im.syntax.{given, *}
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
    im.clock.currentTime += 29
    im.dispatch((1, true))
    assert(triggered)
  })

  test("Sequence, basic inputs")(new TestFixture {
    import im.syntax.{given, *}
    
    addTestCombination(InputSequence(0, 1, 2))

    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((1, true))
    im.clock.currentTime += 50
    im.dispatch((2, true))
    assert(triggered)
  })

  test("Sequence, accept intruders")(new TestFixture {
    import im.syntax.{given, *}
    addTestCombination(InputSequence(0, 1, 2).ignoreIntruderKeys)

    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((1, true))
    im.clock.currentTime += 50
    im.dispatch((8, true))
    im.clock.currentTime += 50
    im.dispatch((2, true))
    assert(triggered)
  })

  test("Sequence, ignoring releases")(new TestFixture {
    import im.syntax.{given, *}
    addTestCombination(InputSequence(0.↓, 1.↓, 2.↓).ignoringReleases)

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
    import im.syntax.{given, *}
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
    assert(!triggered)
  })

  test("WaitFor, no intruders")(new TestFixture {
    import im.syntax.{given, *}
    addTestCombination(0.↓.andThen(1.↓, 500.millis))

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
    import im.syntax.{given, *}
    addTestCombination(0.↓.andThen(1.↓, 500.millis))
    im.dispatch((0, true))
    im.clock.currentTime += 50
    im.dispatch((2, true)) //intruder here, and we asked to reject them
    im.clock.currentTime += 50
    im.dispatch((1, true))
    assert(!triggered)

    addTestCombination(2.↓.andThen(3.↓, 500.millis).ignoreIntruderKeys)
    im.dispatch((2, true))
    im.clock.currentTime += 50
    im.dispatch((0, true)) // intruder here, allowed
    im.clock.currentTime += 50
    im.dispatch((3, true))
    assert(triggered)
  })

  test("delay support")(new TestFixture {
    import im.syntax.{given, *}

    addTestCombination(0.↓.andThenWithDelay(0.↑, 500.millis))
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
    import im.syntax.{given, *}
    addTestCombination(InputSequence(0.↓, 0.↑).ignoringFailuresFor(10))
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

  test("Detecing hold down time")(new TestFixture {
    import im.syntax.{given, *}
    
    val input = for {
      (t1, Tuple1((code, _))) <- im.InputMatcher.any.↓.timestamped
      (t2, _) <- code.↑.timestamped
    } yield (code, t2.toEpochMilli - t1.toEpochMilli)

    var recordedInput = -1
    var recordedDuration = 0l
    im.recordCombination(input)((input, duration) => 
      recordedInput = input
      recordedDuration = duration
    )
    im.dispatch((50, true))
    im.clock.currentTime += 350
    im.dispatch((50, false))
    assert(recordedInput == 50)
    assert(recordedDuration == 350l)
  })
}
