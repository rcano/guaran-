package guarana.swing
package animation

import scala.concurrent.duration.DurationLong

private[animation] inline def cfor[T](inline init: T)(inline cond: T => Boolean, inline next: T => T)(action: T => ?): Unit = {
  var i = init
  while (cond(i)) {
    action(i)
    i = next(i)
  }
}

class ScriptEngine(scenegraph: Scenegraph, initialBufferSize: Int = 100) {

  private[this] var scripts = impl.RingBuffer[ScheduledStep | Null](initialBufferSize, null)
  private[this] var nextRunScripts = impl.RingBuffer[ScheduledStep | Null](initialBufferSize, null)
  private[this] var lastUpdateTime = 0l
  
  /** schedules the given script for running in the current step */
  def run(s: Script): Unit = {
    if (scripts.put(ScheduledStep(s)) == null) {
      val newScripts = new impl.RingBuffer[ScheduledStep | Null](scripts.capacity * 2, null)
      cfor(scripts.take())(_ != null, _ => scripts.take())(s => newScripts.put(s))
      scripts = newScripts
    }
  }
  /** schedules the given script for running in the next step */
  def putNextRun(s: Script): Unit = {
    if (nextRunScripts.put(ScheduledStep(s)) == null) {
      val newNextRunScripts = new impl.RingBuffer[ScheduledStep | Null](nextRunScripts.capacity * 2, null)
      cfor(nextRunScripts.take())(_ != null, _ => nextRunScripts.take())(s => newNextRunScripts.put(s))
      nextRunScripts = newNextRunScripts
    }
  }

  def update(nanoTime: Long): Boolean = scenegraph.update {
    lastUpdateTime = nanoTime
    cfor(scripts.take())(_ != null, _ => scripts.take()) { s =>
      val script = s.asInstanceOf[ScheduledStep] //undo nullability
      if (script.at == -1) script.at = nanoTime
      val currentStepTime = (nanoTime - script.at).nanos.toMillis

      script.step.nextStep(using this)(currentStepTime) match
        case StepEvalResult.Done => // do nothing
        case StepEvalResult.Cont => nextRunScripts.put(script)
        case StepEvalResult.NextStep(ns) => scripts.put(ScheduledStep(ns, nanoTime)) //execute in this step
    }
    //swap scripts with nextRunScripts
    val t = nextRunScripts
    nextRunScripts = scripts
    scripts = t
    true
  }
  def currentTime = lastUpdateTime

  /** ScriptEngine is active if it has steps pending execution */
  def isActive = scripts.size != 0

  private class ScheduledStep(val step: Script, var at: Long = -1)
}
