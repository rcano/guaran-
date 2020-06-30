package guarana.swing
package animation

import javax.swing.Timer

object ScriptedAnimation {
  def apply(script: Script, ups: Int): Scenegraph ?=> Timer = {
    val scriptEngine = ScriptEngine(summon, 5)
    scriptEngine.run(script)

    var frameStartedAt = -1l
    val timer = new Timer(1000 / ups, null) {
      override def start(): Unit = {
        frameStartedAt = -1
        super.start()
      }
    }

    val al: java.awt.event.ActionListener = _ => {
      if (scriptEngine.isActive) {
        val nanoTime = System.nanoTime
        if (frameStartedAt == -1) frameStartedAt = nanoTime
        val delta = nanoTime - frameStartedAt
        scriptEngine.update(delta)
      } else timer.stop()
    }
    timer.addActionListener(al)

    timer
  }
}