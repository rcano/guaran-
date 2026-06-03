package guarana
package gtk

import guarana.animation.TimersDef
import org.gnome.gdk.Display
import org.gnome.glib.GLib
import org.gnome.gtk.{Application, CssProvider, Gtk}
import scala.concurrent.duration.FiniteDuration

object Toolkit extends AbstractToolkit {
  private val eventLoopThread = Thread.currentThread()

  override def timerDefs: TimersDef = TimersSupport()

  override protected def isOnToolkitThread(): Boolean = Thread.currentThread() == eventLoopThread

  override protected def runOnToolkitThread(r: () => Any): Unit = GLib.idleAddOnce(() => r())

  override def getMetrics(): Stylist.Metrics = Stylist.Metrics.NoOp

  def loadCss(css: String): Unit = {
    val cssProvider = CssProvider.builder().onParsingError((section, error) => 
      scribe.warn(s"CSS parsing problem, $section: ${error.readMessage()}")
    ).build()
    cssProvider.loadFromString(css)
    Gtk.styleContextAddProviderForDisplay(Display.getDefault(), cssProvider, Gtk.STYLE_PROVIDER_PRIORITY_APPLICATION)
  }

  private class TimersSupport extends TimersDef {
    type Timer = TimerImpl
    class TimerImpl(val delay: FiniteDuration, val onUpdate: Timer => Unit, val onRestart: Timer => Unit) {
      private[TimersSupport] var started: Boolean = false
      private[TimersSupport] var eventSource: Int = -1
    }

    given TimerLike: guarana.animation.TimerLike[Timer] with {
      override def apply(delay: FiniteDuration, onUpdate: Timer => Unit, onRestart: Timer => Unit): Timer =
        TimerImpl(delay, onUpdate, onRestart)

      extension (timer: Timer) {
        def start(): Unit = {
          if (timer.eventSource == -1) {
            timer.eventSource = GLib.timeoutAdd(
              GLib.PRIORITY_HIGH,
              timer.delay.toMillis.toInt,
              () => {
                // only perform the action if we are still in started mode
                if (timer.started) timer.onUpdate(timer)
                timer.started
              }
            )
          }
          timer.started = true
        }
        def stop(): Unit = {
          timer.eventSource = -1
          timer.started = false
        }
        override def restart(): Unit = {
          stop()
          timer.onRestart(timer)
          start()
        }
        def isRunning: Boolean = timer.started
      }

    }
  }
}
