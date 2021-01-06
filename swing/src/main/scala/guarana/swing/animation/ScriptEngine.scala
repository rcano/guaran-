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

  private[this] var scripts = impl.RingBuffer[Script | Null](initialBufferSize, null)
  private[this] var nextRunScripts = impl.RingBuffer[Script | Null](initialBufferSize, null)
  private[this] val scriptContexts = collection.mutable.HashMap[Script, Context]()
  
  def run(s: Script): Unit = {
    if (scripts.put(s) == null) {
      val newScripts = new impl.RingBuffer[Script | Null](scripts.capacity * 2, null)
      cfor(scripts.take())(_ != null, _ => scripts.take())(s => newScripts.put(s))
      scripts = newScripts
    }
  }
  private def putNextRun(s: Script): Unit = {
    if (nextRunScripts.put(s) == null) {
      val newNextRunScripts = new impl.RingBuffer[Script | Null](nextRunScripts.capacity * 2, null)
      cfor(nextRunScripts.take())(_ != null, _ => nextRunScripts.take())(s => newNextRunScripts.put(s))
      nextRunScripts = newNextRunScripts
    }
  }
  def update(nanoTime: Long): Boolean = scenegraph.update {
    cfor(scripts.take())(_ != null, _ => scripts.take()) { s =>
      val script = s.asInstanceOf[Script] //undo nullability
      val ctx = scriptContexts.getOrElseUpdate(script, new Context(nanoTime))
      val currentStepTime = (nanoTime - ctx.currentStepStart).nanos.toMillis

      val (currStep, stepsLength) = script match {
        case Steps(ss) => (ss(ctx.currentStep), ss.length)
        case _ => (script, 1)
      }
      def advanceOrRemove(): Unit = {
        if (ctx.currentStep < stepsLength - 1) {
          ctx.currentStep += 1
          ctx.currentStepStart = nanoTime
          run(script) //evaluate next step in this loop
        } else { //since the script is done, remove its context
          ctx.parentContext.?(_.childDone.set(ctx.indexInParent))
          scriptContexts -= script
        }
      }

      currStep match {
        case DoUntil(f) => if (f(currentStepTime)) advanceOrRemove() else putNextRun(script)
          
        case Fork(s) => 
          run(s) //this causes that its first step is executed in this step.
          advanceOrRemove()
          
        case Parallel(subscripts) if ctx.childDone == null => //first time executing in parallel, create subscript
          val stepsLength = subscripts.length
          ctx.childCount = stepsLength
          ctx.childDone = new java.util.BitSet(stepsLength)
          cfor(0)(_ < stepsLength, _ + 1) { i =>
            val subCtx = new Context(nanoTime)
            subCtx.parentContext = ctx
            subCtx.indexInParent = i
            val oneStepScript = subscripts(i)
            scriptContexts(oneStepScript) = subCtx
            run(oneStepScript) //this causes that its first step is executed in this step.
          }
          
          run(script) //reevaluate this parallel step after the parallel, because it is possible that those steps completed immediately and so this step must continue.
          
        case Parallel(subscript) => if (ctx.childDone.cardinality == ctx.childCount) advanceOrRemove() else putNextRun(script)
        case other => throw IllegalStateException("Can't process nested Steps. This should've been flattened out by the `script` method, unless the Steps was constructed without it. " + 
          s"Invalid script: $other")
      }
    }
    //swap scripts with nextRunScripts
    val t = nextRunScripts
    nextRunScripts = scripts
    scripts = t
    true
  }

  /** ScriptEngine is active if it has steps pending execution */
  def isActive = scripts.size != 0

  private class Context(var currentStepStart: Long) {
    var currentStep: Int = 0
      
    //track for particular events to be done
    var childCount = 0
    var childDone: java.util.BitSet = null
    //track context that caused this script to exists (i.e: in case of parallel steps)
    var parentContext: Context = _
    var indexInParent: Int = _
  }
}
