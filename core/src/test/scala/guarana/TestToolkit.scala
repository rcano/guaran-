package guarana

import guarana.animation.TimersDef
import scala.concurrent.duration.FiniteDuration
import scala.collection.mutable.Buffer

class TestToolkit extends AbstractToolkit {
  protected def isOnToolkitThread(): Boolean = true
  protected def runOnToolkitThread(r: () => Any): Unit = r()

  def timerDefs: animation.TimersDef = ManualTimers

  def getMetrics() = Stylist.Metrics.NoOp
}

 trait ManualTimers extends TimersDef {
    class TimerImpl(val delay: FiniteDuration, val onUpdate: TimerImpl => Unit, val onRestart: TimerImpl => Unit) {
      var started = false
      def runCurrTime(): Unit = onUpdate(this)
    }
    type Timer = TimerImpl

    val createdTimers = Buffer[TimerImpl]()

    given TimerLike: animation.TimerLike[Timer] with {

      def apply(delay: FiniteDuration, onUpdate: TimerImpl => Unit, onRestart: TimerImpl => Unit): TimerImpl = TimerImpl(delay, onUpdate, onRestart)

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
  object ManualTimers extends ManualTimers