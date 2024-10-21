package apricot.util.directmem

import scala.deriving.Mirror
import scala.annotation.experimental
import guarana.util.MacroUtils

object test {
  
  type Point2D = Struct {
    var x: Double
    var y: Double
  }
  object Point2D {
    given sized: Sized[Point2D] = Struct.derivedSized[Point2D]
    def apply(x: Double, y: Double)(using ctx: AllocContext): Pointer[Point2D, ctx.type] =
      val res = ctx.allocStruct[Point2D]
      // res.x = x
      // res.y = y
      res
  }
  import Point2D.sized

  type TwoPoints = Struct {
    def a: Inlined[Point2D]
    def b: Inlined[Point2D]
  }
  object TwoPoints:
    given sized: Sized[TwoPoints] = Struct.derivedSized[TwoPoints]
  import TwoPoints.sized

  
  def main(args: Array[String]): Unit = {
    StackAlloc.withFrame { ctx ?=>
      val p = Point2D(1, 2)

      val sm = Struct.structMirror[Point2D]
      // summon[Sized[sm.MirroredType]]

      // println(p.show)
      // println(s"Point[x=${p.x},y=${p.y}]")
      
      val twoPoints = ctx.allocStruct[TwoPoints]
      // val partA = twoPoints.a
      // partA.x = 30.4
      // partA.y = 12
      
      // println(twoPoints.show)
      // println(s"sub point = Point${twoPoints.a.show}")
    }
  }

}
