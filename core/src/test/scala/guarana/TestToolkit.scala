package guarana

import guarana.animation.TimersDef

class TestToolkit extends AbstractToolkit {
  protected def isOnToolkitThread(): Boolean = true
  protected def runOnToolkitThread(r: () => Any): Unit = r()

  def timerDefs: animation.TimersDef = ManualTimers

  def getMetrics() = Stylist.Metrics.NoOp
}

object ManualTimers extends TimersDef.ManualTimers