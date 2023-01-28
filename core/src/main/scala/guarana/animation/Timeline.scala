package guarana
package animation

import scala.compiletime.ops.int.*
import scala.concurrent.duration.*
import scala.util.chaining.*

object Timeline {

  /** Models an interpolation curve. It maps a 0..1 interval to another 0..1. The most trivial curve is the linear one
    */
  @FunctionalInterface
  trait Curve:
    def apply(at: Double): Double

  val LinearCurve: Curve = identity

  /** Built-in interpolator instance that provides ease in/out behavior. <p> An ease-both interpolator will make an animation start slow,
    * then accelerate and slow down again towards the end, all in a smooth manner. <p> The implementation uses the algorithm for easing
    * defined in SMIL 3.0 with an acceleration and deceleration factor of 0.2, respectively.
    */
  val EaseBothCurve: Curve = t =>
    clamp(
      if t < 0.2 then 3.125 * t * t
      else if t > 0.8 then -3.125 * t * t + 6.25 * t - 2.125
      else 1.25 * t - 0.125
    )

  private inline def clamp(t: Double) = if t < 0 then 0 else if t > 1 then 1 else t

  private val noOnStep: Long => Unit = _ => ()

  /** Generic defintion of a KeyFrame
    *
    * @param duration
    *   Time in nanos that this frame lasts
    * @param step
    *   Action that gets executed while this Frame is ongoing. The function receives elapsed time in nanos since the frame started
    * @param endAction
    *   Final action that gets executed after the frame's time is up.
    */
  final case class KeyFrame(duration: Long, step: Long => Unit, endAction: () => Unit) {
    def +(other: KeyFrame): KeyFrame = {
      val (longerFrame, shorterFrame) = if duration >= other.duration then (this, other) else (other, this)
      val shorterRatio = shorterFrame.duration.toDouble / longerFrame.duration
      KeyFrame(
        longerFrame.duration,
        elapsed =>
          if elapsed <= shorterFrame.duration then shorterFrame.step(elapsed)
          longerFrame.step(elapsed),
        () => { longerFrame.endAction(); shorterFrame.endAction() }
      )
    }
  }

  def KeyFrame[T](duration: FiniteDuration, prop: Var[T], max: T, curve: Curve)(using
      i: Interpolator[T],
      v: ValueOf[prop.ForInstance]
  ): AbstractToolkit ?=> KeyFrame =
    KeyFrame[T](duration, prop, summon[AbstractToolkit].stateReader(prop), max, curve)

  def KeyFrame[T](duration: FiniteDuration, prop: Var[T], min: T, max: T, curve: Curve)(using
      i: Interpolator[T],
      v: ValueOf[prop.ForInstance]
  ): AbstractToolkit ?=> KeyFrame = {

    val durationNs = duration.toNanos
    val sc = summon[AbstractToolkit]
    KeyFrame(
      durationNs,
      elapsed => sc.update { prop := i.interpolate(min, max, curve(elapsed.toDouble / durationNs)) },
      () => ()
    )
  }

  /** Calls the action lambda with a percentage (between 0 and 1) of completion on each frame of the engine. Ensures a last call at 100%
    * (1).
    */
  def during(time: FiniteDuration)(action: Double => Unit): KeyFrame = {
    val duration = time.toNanos
    KeyFrame(duration, elapsed => action(elapsed.toDouble / duration), () => ())
  }
  def after(time: FiniteDuration)(action: => Unit) = KeyFrame(time.toNanos, noOnStep, () => action)

  enum Cycles {
    case Infinite
    case Iterations(value: Int)
  }
  object Cycles {
    val SingleShot = Iterations(1)
  }

  def apply(
      frames: IArray[Timeline.KeyFrame],
      cycles: Cycles,
      ups: Int = 10,
      autoReverse: Boolean = false
  )(timersDef: TimersDef): Animation[timersDef.Timer] = new Animation[timersDef.Timer] {
    var cycle = 0L
    var frame = 0
    var frameStartedAt = -1L
    val timer = timersDef.TimerLike(
      (1000 / ups).millis,
      onUpdate = timer => {
        val nanoTime = System.nanoTime
        val f = frames(frame)
        if (frameStartedAt == -1) frameStartedAt = nanoTime
        val delta = nanoTime - frameStartedAt
        inline def shouldReverse = autoReverse && cycle % 2 == 1
        if (delta >= f.duration) {
          f.step(if shouldReverse then 0 else f.duration)
          f.endAction()
          if (!shouldReverse && frame == (frames.length - 1)) || (shouldReverse && frame == 0) then cycle += 1
          else frame = (frame + (if (shouldReverse) -1 else 1)) % frames.length
          frameStartedAt = nanoTime - (delta - f.duration)

          cycles match {
            case Cycles.Iterations(value) if value <= cycle =>
              timer.stop()
              //restart the values, in case the timer gets played again
              cycle = 0
              frame = 0
              frameStartedAt = -1L
            case _ =>
          }
        } else {
          f.step(if shouldReverse then f.duration - delta else delta)
        }
      },
      onRestart = timer => frameStartedAt = -1
    )
  }

  trait Animation[Timer] {
    /** Current cycle. This is manipulated by the animation as it progresses, but can be modified externally, usually to trigger reversing*/
    var cycle: Long
    def timer: Timer
  }
}
