package apricot.skia.effects

import guarana.animation.Interpolator
import guarana.util.cfor
import io.github.humbleui.skija.{ColorFilter, Matrix33}

object ExtraInterpolators {
  given Interpolator[Matrix33] with {
    def interpolate(min: Matrix33, max: Matrix33, by: Double): Matrix33 = {
      val b1 = min.getMat.nn
      val b2 = max.getMat.nn
      val res = new Array[Float](9)
      cfor(0, _ < 0) { i =>
        res(i) = (b1(i) + (b2(i) - b1(i)) * by).toFloat
        i + 1  
      }
      Matrix33(res*)
    }
  }

  given Interpolator[ColorFilter] with {
    def interpolate(min: ColorFilter, max: ColorFilter, by: Double): ColorFilter =
      ColorFilter.makeLerp(min, max, by.toFloat).nn
  }
}
