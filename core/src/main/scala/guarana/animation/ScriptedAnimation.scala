package guarana
package animation

import scala.concurrent.duration.*

/** Creates a javax.swing.Timer setup to run a [[Script]].
  */
object ScriptedAnimation {
  def apply(script: Script, ups: Int)(timersDef: TimersDef): AbstractToolkit ?=> timersDef.Timer = {
    val scriptEngine = ScriptEngine(summon, 5)
    scriptEngine.run(script)

    var frameStartedAt = -1L
    var lastDelta = 0L

    timersDef.TimerLike(
      (1000 / ups).millis,
      onUpdate = timer => {
        if (scriptEngine.isActive) {
          val nanoTime = System.nanoTime
          if (frameStartedAt == -1) frameStartedAt = nanoTime - lastDelta
          lastDelta = nanoTime - frameStartedAt
          scriptEngine.update(lastDelta)
        } else timer.stop()
      },
      onRestart = timer => frameStartedAt = -1
    )
  }
}
