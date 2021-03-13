package guarana.swing
package animation

import javax.swing.Timer
import scala.compiletime.ops.int.*
import scala.concurrent.duration.FiniteDuration
import scala.util.chaining.*

object Timeline {
  private[this] val noOnStep: Long => Unit = _ => ()
  final case class KeyFrame(after: Long, step: Long => Unit, endAction: () => Unit)
  
  /**
   * Calls the action lambda with a percentage (between 0 and 1) of completion on each frame of the engine. Ensures a last call at 100% (1).
   */
  def during(time: FiniteDuration)(action: Double => Unit): KeyFrame = {
    val duration = time.toNanos
    KeyFrame(duration, elapsed => action(elapsed.toDouble / duration), () => action(1))
  }
  def after(time: FiniteDuration)(action: => Unit) = KeyFrame(time.toNanos, noOnStep, () => action)


  sealed trait Infinite
  object Infinite extends Infinite
  type ValidCycle[T <: Infinite | Int] = T match {
    case Infinite => true
    case Int => T > 0
  }

  def apply(
    frames: IArray[Timeline.KeyFrame],
    cycleCount: Infinite | Int,
    ups: Int = 10,
    autoReverse: Boolean = false
  )(using ValidCycle[cycleCount.type] =:= true): Timer = {
    new Timer(1000 / ups, null) {
      setCoalesce(true)
      setRepeats(cycleCount != 1)

      var cycle = 0l
      var frame = 0
      var frameStartedAt = -1l
      val listener: java.awt.event.ActionListener = evt => {
        val nanoTime = System.nanoTime
        val f = frames(frame)
        if (frameStartedAt == -1) frameStartedAt = nanoTime
        val delta = nanoTime - frameStartedAt
        if (delta >= f.after) {
          f.endAction()
          if (frame == (frames.length - 1) || (autoReverse && cycle % 2 == 1 && frame == 0)) cycle += 1
          frame = (frame + (if (autoReverse && cycle % 2 == 1) -1 else 1)) % frames.length
          frameStartedAt = nanoTime - (delta - f.after)
          
          cycleCount match
            case maxCount: Int =>
              stop()
              //restart the values, in case the timer gets played again
              cycle = 0
              frame = 0
              frameStartedAt = -1l
            case _ =>

        } else {
          f.step(delta)
        }      
      }
      addActionListener(listener)
    
      override def start(): Unit = {
        frameStartedAt = -1
        super.start()
      }
    }
  }

  apply(IArray(), 1)

}
