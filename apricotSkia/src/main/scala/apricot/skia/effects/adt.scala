package apricot.skia.effects

import guarana.{Insets, ?}
import guarana.animation.Interpolator
import io.github.humbleui.skija
import scala.collection.immutable.SortedMap
import scala.concurrent.duration.*
import io.github.humbleui.skija.Matrix33

type Radians = Double
type Degrees = Double

/** Astract definition of a function that can produce values of type [T] */
trait ValueGenerator[T]:
  def apply(): T
  def map[V](f: T => V): ValueGenerator[V] = () => f(apply())
object ValueGenerator:
  def apply[T](f: => T): ValueGenerator[T] = () => f
  def constant[T](t: T): ValueGenerator[T] = () => t
  def roundRobin[T](values: T*): ValueGenerator[T] = (() => Iterator.unfold(0)(i => Some((values(i), (i + 1) % values.length))).next)
  def random[T](values: T*): ValueGenerator[T] = () => values(scala.util.Random.nextInt(values.size))

/** A curve definition is a function that given a value between 0..1, produces an instance T. Note: a curve doesn't have to be an analog
  * curve, it could be an escalonated sequence, like 1,2,3
  */
trait Curve[T]:
  def apply(at: Double): T
  def map[U](f: T => U): Curve[U] = v => f(this(v))
  def cached(snapshots: Int): Curve.CachedCurve[T] = {
    val indexedPoints = Iterator.tabulate(snapshots)(i => 
      val at = i.toDouble / (snapshots - 1)
      at -> apply(at)
    ).to(SortedMap)
    new Curve.CachedCurve(indexedPoints)
  }
object Curve {
  def constant[T](v: T): Curve[T] = _ => v
  def apply[T](function: Double => T): Curve[T] = v => function(v)
  def apply[T](points: CurvePoint[T]*): Curve[T] = {
    require(points.nonEmpty)
    require(points.head.point == 0, "The first curve point must be at 0")
    val indexedPoints = points.map(v => (v.point, v.value)).to(SortedMap)
    //return the Curve by means of a SAM function:
    (at: Double) => indexedPoints.maxBefore(at).get._2
  }
  def withState[T, S](initialS: S)(next: (at: Double, state: S) => (T, S)): Curve[T] = new Curve {
    var last: S = initialS
    def apply(at: Double) = {
      // in case the curve is reused, detect at == 0 and reset it
      if (at == 0) last = initialS
      val (res, nextState) = next(at, last)
      last = nextState
      res
    }
  }

  def lerp[T](min: CurvePoint[T], next: CurvePoint[T], morePoints: CurvePoint[T]*)(using i: Interpolator[T]): Curve[T] = {
    require(min.point == 0, "linear interpolation must start at 0")
    val max = morePoints.lastOption.getOrElse(next)
    require(max.point == 1.0, "linear interpolation must end at 1")
    val indexedPoints = (min +: next +: morePoints).map(v => (v.point, v.value)).to(SortedMap)
    (at: Double) => at match {
      case 0.0 => min.value
      case 1.0 => max.value
      case _ =>
        val (minAt, minValue) = indexedPoints.maxBefore(at).get
        val (maxAt, maxValue) = indexedPoints.minAfter(at).get
        val lerpAt = (at - minAt) / (maxAt - minAt)
        i.interpolate(minValue, maxValue, lerpAt)
    }
  }

  def iterate[T](elems: T*): Curve[T] = {
    val stepIncrease = 1.0 / elems.size
    val curvePoints = elems.zipWithIndex.map((elem, idx) => elem `at` (stepIncrease * idx))
    apply(curvePoints*)
  }

  class CachedCurve[T](val index: SortedMap[Double, T]) extends Curve[T] {
    private val minValue = index.head._2
    private val maxValue = index.last._2
    def apply(at: Double) = at match {
      case 0.0 => minValue
      case 1.0 => maxValue
      case at => index.maxBefore(at).get._2
    }
  }
}

/** Keypoint? keyframe? */
case class CurvePoint[T](point: Double, value: T)
extension [T](t: T) infix def at(v: Double): CurvePoint[T] = CurvePoint(v, t)

/** Either a generator of curves (which allows each spawned particle to behave differently) or a simple curve (shared by all particles) */
type GeneratedCurve[T] = ValueGenerator[Curve[T]]

case class CacheInfo(snapshots: Int, insets: Insets)

/** A renderable atom */
case class ParticleDescr(
    image: skija.Image,
    delay: ValueGenerator[FiniteDuration],
    duration: ValueGenerator[FiniteDuration],
    startPositionOffset: ValueGenerator[(Double, Double)],
    positionCurve: GeneratedCurve[(Double, Double)] | Null = null,
    blendModeCurve: GeneratedCurve[skija.BlendMode] | Null = null,
    colorFilterCurve: GeneratedCurve[skija.ColorFilter] | Null = null,
    imageFilterCurve: GeneratedCurve[skija.ImageFilter] | Null = null,
    shaders: GeneratedCurve[skija.Shader] | Null = null,
    blurCurve: GeneratedCurve[Double] | Null = null,
    // glowCurve: GeneratedCurve[Double] | Null = null,
    alphaCurve: GeneratedCurve[Double] | Null = null,
    transformationCurve: GeneratedCurve[skija.Matrix33] | Null = null,
    cacheInfo: CacheInfo | Null = null,
) {
  def position(position: GeneratedCurve[(Double, Double)]) = copy(positionCurve = position)
  def transform(transformation: GeneratedCurve[Matrix33]) = copy(transformationCurve = transformation)
  def rotate(byRadians: GeneratedCurve[Degrees]) = transform(byRadians.map(rotationCurve =>
    val existingTransformCurve = transformationCurve.?(_())
    Curve { at =>
      val rotationMatrix = Matrix33.makeRotate(rotationCurve(at).toFloat)  
      if (existingTransformCurve != null) existingTransformCurve(at).makeConcat(rotationMatrix)
      else rotationMatrix
    }
  ))
  def translate(byOffset: GeneratedCurve[(Double, Double)]) = transform(byOffset.map(translationCurve =>
    val existingTransformCurve = transformationCurve.?(_())
    Curve { at =>
      val (xOffset, yOffset) = translationCurve(at)
      val translationMatrix = Matrix33.makeTranslate(xOffset.toFloat, yOffset.toFloat)
      if (existingTransformCurve != null) existingTransformCurve(at).makeConcat(translationMatrix)
      else translationMatrix
    }
  ))
  def scale(by: GeneratedCurve[(Double, Double)]) = transform(by.map(scaleCurve =>
    val existingTransformCurve = transformationCurve.?(_())
    Curve { at =>
      val (xScale, yScale) = scaleCurve(at)
      val scaleMatrix = Matrix33.makeScale(xScale.toFloat, yScale.toFloat)
      if (existingTransformCurve != null) existingTransformCurve(at).makeConcat(scaleMatrix)
      else scaleMatrix
    }
  ))

  def alpha(values: GeneratedCurve[Double]) = copy(alphaCurve = values)
  def blur(strength: GeneratedCurve[Double]) = copy(blurCurve = strength)
  // def glow(strength: GeneratedCurve[Double]) = copy(glowCurve = strength)
  def shade(shaders: GeneratedCurve[skija.Shader]) = copy(shaders = shaders)
  def imageFilter(imageFilter: GeneratedCurve[skija.ImageFilter]) = copy(imageFilterCurve = imageFilter)
  def color(colorFilter: GeneratedCurve[skija.ColorFilter]) = copy(colorFilterCurve = colorFilter)
  def blend(blend: GeneratedCurve[skija.BlendMode]) = copy(blendModeCurve = blend)

  def cached(snapshots: Int, insets: Insets) = copy(cacheInfo = CacheInfo(snapshots, insets))
}
case class ParticleEmitter(
    particles: ParticleDescr*
    // blendModeCurve: GeneratedCurve[skija.BlendMode] | Null = null,
    // colorFilterCurve: GeneratedCurve[skija.ColorFilter] | Null = null,
    // imageFilterCurve: GeneratedCurve[skija.ImageFilter] | Null = null,
    // shaders: GeneratedCurve[skija.Shader] | Null = null,
    // blurCurve: GeneratedCurve[Double] | Null = null,
    // // glowCurve: GeneratedCurve[Double] | Null = null,
    // alphaCurve: GeneratedCurve[Double] | Null = null,
    // transformationCurve: GeneratedCurve[skija.Matrix33] | Null = null,
) {
  // def transform(transformation: GeneratedCurve[Matrix33]) = copy(transformationCurve = transformation)
  // def rotate(byRadians: GeneratedCurve[Degrees]) = transform(byRadians.map(_.map(rads => Matrix33.makeRotate(rads.toFloat))))
  // def translate(byOffset: GeneratedCurve[(Double, Double)]) =
  //   transform(byOffset.map(_.map((x, y) => Matrix33.makeTranslate(x.toFloat, y.toFloat))))

  // def alpha(values: GeneratedCurve[Double]) = copy(alphaCurve = values)
  // def blur(strength: GeneratedCurve[Double]) = copy(blurCurve = strength)
  // // def glow(strength: GeneratedCurve[Double]) = copy(glowCurve = strength)
  // def shade(shaders: GeneratedCurve[skija.Shader]) = copy(shaders = shaders)
  // def imageFilter(imageFilter: GeneratedCurve[skija.ImageFilter]) = copy(imageFilterCurve = imageFilter)
  // def color(colorFilter: GeneratedCurve[skija.ColorFilter]) = copy(colorFilterCurve = colorFilter)
  // def blend(blend: GeneratedCurve[skija.BlendMode]) = copy(blendModeCurve = blend)
}
