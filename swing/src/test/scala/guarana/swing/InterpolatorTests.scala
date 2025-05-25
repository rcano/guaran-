package guarana
package swing

import org.scalatest.funsuite.AnyFunSuite

class InterpolatorTests extends AnyFunSuite {
  
  val boundsInterp = summon[animation.Interpolator[Bounds]]
  test("bounds interpolator focusing on size") {
    val res = boundsInterp.interpolate(Bounds(), Bounds(width = 10, height = 10), 0.5)
    assert(res.x == 0)
    assert(res.y == 0)
    assert(res.width == 5)
    assert(res.height == 5)
  }

  test("bounds interpolator focusing on position") {
    val res = boundsInterp.interpolate(Bounds(247, 163, 96, 193), Bounds(250, 163, 96, 193), 33.0 / 500)
    assert(res.x == 247)
    assert(res.y == 163)
    assert(res.width == 96)
    assert(res.height == 193)
  }
}
