package guarana
package animation

import javax.swing.Timer

/** Creates a javax.swing.Timer setup to run a [[Script]].
  *
  */ 
object ScriptedAnimation {
  def apply(script: Script, ups: Int): Scenegraph ?=> Timer = {
    val scriptEngine = ScriptEngine(summon, 5)
    scriptEngine.run(script)

    var frameStartedAt = -1l
    var lastDelta = 0l
    val timer = new Timer(1000 / ups, null) {
      override def start(): Unit = {
        frameStartedAt = -1
        super.start()
      }
    }

    val al: java.awt.event.ActionListener = _ => {
      if (scriptEngine.isActive) {
        val nanoTime = System.nanoTime
        if (frameStartedAt == -1) frameStartedAt = nanoTime - lastDelta
        lastDelta = nanoTime - frameStartedAt
        scriptEngine.update(lastDelta)
      } else timer.stop()
    }
    timer.addActionListener(al)

    timer
  }
}