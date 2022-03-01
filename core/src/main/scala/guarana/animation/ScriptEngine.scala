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
          case StepEvalResult.Done => script.dispose()
          case StepEvalResult.Cont => nextRunScripts.put(script)
          case StepEvalResult.NextStep(ns) => scripts.put(newScheduledStep(ns, nanoTime)) //execute in this step
      } else {
        script.dispose()
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

  private val stepsPool = new collection.mutable.Queue[ScheduledStep]()
  private def newScheduledStep(s: Script, at: Long = -1): ScheduledStep = {
    if stepsPool.isEmpty then
      // populate the pool a bit
      for _ <- 0 until 10 do stepsPool += ScheduledStep(EndOfScript, -1)
    val res = stepsPool.dequeue()
    res.step = s
    res.at = at
    res
  }
  private class ScheduledStep(var step: Script, var at: Long = -1) {
    def dispose(): Unit = stepsPool += this
  }
}
