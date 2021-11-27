package guarana
package animation

import scala.concurrent.duration.DurationLong

private[animation] inline def cfor[T](inline init: T)(inline cond: T => Boolean, inline next: T => T)(action: T => ?): Unit = {
  var i = init
  while (cond(i)) {
    action(i)
    i = next(i)
  }
}

class ScriptEngine(toolkit: AbstractToolkit, initialBufferSize: Int = 100) {

  private[this] var scripts = impl.RingBuffer[ScheduledStep | Null](initialBufferSize, null)
  private[this] var nextRunScripts = impl.RingBuffer[ScheduledStep | Null](initialBufferSize, null)
  private[this] var lastUpdateTime = 0l
  private[this] val mustRemove = collection.mutable.Set.empty[Script]
  
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

  def update(nanoTime: Long): Boolean = toolkit.update {
    lastUpdateTime = nanoTime
    cfor(scripts.take())(_ != null, _ => scripts.take()) { s =>
      val script = s.asInstanceOf[ScheduledStep] //undo nullability
      if (!mustRemove(script.step)) { //do nothing with this script
        if (script.at == -1) script.at = nanoTime
        val currentStepTime = (nanoTime - script.at) / 1000000 // to millis
  
        script.step.nextStep(using this)(currentStepTime) match
          case StepEvalResult.Done => // do nothing
          case StepEvalResult.Cont => nextRunScripts.put(script)
          case StepEvalResult.NextStep(ns) => scripts.put(ScheduledStep(ns, nanoTime)) //execute in this step
      }
    }
    //swap scripts with nextRunScripts
    val t = nextRunScripts
    nextRunScripts = scripts
    scripts = t
    //things that weren't removed this run, will not be removed later either. This acts on the "current" list
    mustRemove.clear()
    true
  }
  def currentTime = lastUpdateTime

  /** ScriptEngine is active if it has steps pending execution */
  def isActive = scripts.size != 0

  def remove(s: Script): Unit = mustRemove += s

  private class ScheduledStep(val step: Script, var at: Long = -1)
}
