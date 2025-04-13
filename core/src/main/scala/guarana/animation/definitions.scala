package guarana.animation

import scala.concurrent.duration.FiniteDuration

/** Models an interpolation curve. It maps a 0..1 interval to another 0..1. The most trivial curve is the linear one
  */
@FunctionalInterface
trait Curve:
  def apply(at: Double): Double

val LinearCurve: Curve = identity

/** Built-in interpolator instance that provides ease in/out behavior. <p> An ease-both interpolator will make an animation start slow, then
  * accelerate and slow down again towards the end, all in a smooth manner. <p> The implementation uses the algorithm for easing defined in
  * SMIL 3.0 with an acceleration and deceleration factor of 0.2, respectively.
  */
val EaseBothCurve: Curve = t =>
  clamp(
    if t < 0.2 then 3.125 * t * t
    else if t > 0.8 then -3.125 * t * t + 6.25 * t - 2.125
    else 1.25 * t - 0.125
  )

inline def clamp(t: Double) = if t < 0 then 0 else if t > 1 then 1 else t

enum TransitionType[+T] {
  case Instant
  case Interp[T](delay: FiniteDuration, duration: FiniteDuration, curve: Curve, baseValue: T, updatesPerSecond: Int)(using
      val interp: Interpolator[T]
  ) extends TransitionType[T]
}