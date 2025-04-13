package guarana
package animation

trait Interpolator[A]:
  def interpolate(min: A, max: A, by: Double): A
  inline def bimap[B](inline contra: B => A, inline map: A => B): Interpolator[B] = 
    (min, max, by) => map(interpolate(contra(min), contra(max), by))
  /** Transform this interpolator by using the custom curve.
    *  A curve is a function from [0,1) => [0,1)
    */
  inline def withCurve(curve: Double => Double): Interpolator[A] = (min, max, by) => interpolate(min, max, curve(by))

object Interpolator {

  def constant[A](a: A): Interpolator[A] = (_, _, _) => a
  private val _max: Interpolator[Any] = (_, max, _) => max
  def max[A]: Interpolator[A] = _max.asInstanceOf

  given Interpolator[Byte] = (min, max, by) => (((max - min) * by) + min).toByte
  given Interpolator[Short] = (min, max, by) => (((max - min) * by) + min).toShort
  given Interpolator[Char] = (min, max, by) => (((max - min) * by) + min).toInt.toChar
  given Interpolator[Int] = (min, max, by) => (((max - min) * by) + min).toInt
  given Interpolator[Long] = (min, max, by) => (((max - min) * by) + min).toLong
  given Interpolator[Float] = (min, max, by) => (((max - min) * by) + min).toFloat
  given Interpolator[Double] = (min, max, by) => ((max - min) * by) + min
  given Interpolator[Boolean] = summon[Interpolator[Int]].bimap(b => if b then 1 else 0, _ == 1)
  given [Bounds: BoundsLike]: Interpolator[Bounds] = 
    val interp = summon[Interpolator[Double]].interpolate
    (min, max, by) => summon[BoundsLike[Bounds]](
      x = interp(min.x, max.x, by),
      y = interp(min.y, max.y, by),
      width = interp(min.width, max.width, by),
      height = interp(min.height, max.height, by)
    ) 

  given Interpolator[EmptyTuple] = (_, _, _) => EmptyTuple
  given [H: Interpolator, T <: Tuple: Interpolator]: Interpolator[H *: T] = (min, max, by) =>
    summon[Interpolator[H]].interpolate(min.head, max.head, by) 
    *: summon[Interpolator[T]].interpolate(min.tail, max.tail, by)
}