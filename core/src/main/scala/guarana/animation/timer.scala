package guarana.animation

import scala.concurrent.duration.FiniteDuration
import scala.collection.mutable.Buffer

trait TimersDef {
  type Timer
  given TimerLike: TimerLike[Timer]
}

object TimersDef {

  /** Trivial Timers implementaiton that requires manual stepping. Useful for when designing a manual event-loop. */
  trait ManualTimers extends TimersDef {
    class TimerImpl(val delay: FiniteDuration, val onUpdate: TimerImpl => Unit, val onRestart: TimerImpl => Unit) {
      var started = false
      def runCurrTime(): Unit = onUpdate(this)
    }
    type Timer = TimerImpl

    val createdTimers = Buffer[TimerImpl]()

    given TimerLike: TimerLike[Timer] with {

      def apply(delay: FiniteDuration, onUpdate: TimerImpl => Unit, onRestart: TimerImpl => Unit): TimerImpl =
        TimerImpl(delay, onUpdate, onRestart)

      extension (t: Timer) {
        override def start(): Unit = {
          t.started = true
          createdTimers += t
        }
        override def stop(): Unit = {
          t.started = false
          createdTimers -= t
        }
        override def restart(): Unit = t.onRestart(t)
        override def isRunning: Boolean = t.started
      }

    }
  }

  /** A TimersDef implementation with timers that simply throw on all operations. Usfeul for toolkits that don't support transitions */
  object UnsupportedTimersDef extends TimersDef {
    object FakeTimer
    type Timer = FakeTimer.type

    given TimerLike: TimerLike[Timer] with {
      def apply(delay: FiniteDuration, onUpdate: Timer => Unit, onRestart: Timer => Unit): Timer = FakeTimer

      extension (t: Timer) {
        override def start(): Unit = throw new UnsupportedOperationException
        override def stop(): Unit = throw new UnsupportedOperationException
        override def isRunning: Boolean = false
      }
    }
  }
}

trait TimerLike[T] {
  def apply(delay: FiniteDuration, onUpdate: T => Unit, onRestart: T => Unit): T
  extension (t: T) {
    def start(): Unit
    def stop(): Unit
    def restart(): Unit = {
      stop()
      start()
    }

    def isRunning: Boolean
    def isStopped: Boolean = !isRunning
  }
}
