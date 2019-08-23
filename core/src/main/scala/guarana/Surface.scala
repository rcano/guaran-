package guarana

import java.awt.Graphics2D

trait Surface {

  def width: Int
  def height: Int

  def getPixels(arr: Array[Int]): Unit
  def setPixels(arr: Array[Int]): Unit

  def update(f: Graphics2D => Any): Unit
}

//object Surface {
//
//  /**
//   * Expose the drawing primitives to modify a surface's content.
//   * Note that a DrawingOps must only be used within the update method, capturing the variable and using it outside it has undefined behaviour.
//   */
//  trait DrawingOps {
//    var paint: Paint
//    //var stroke: Stroke
//    //var transform: AffineTransform
//    //var clip: Rectangle
//
//    /**
//     * Execute the given thunk and reset the context to the state it was before calling this method.
//     */
//    @inline final def withContext[R](f: => R): R = {
//      val prevPaint = paint
////      val prevStroke = stroke
////      val prevTransform = transform
////      val prevClip = clip
//      val res = f
//
//      paint = prevPaint
////      stroke = prevStroke
////      transform = prevTransform
////      clip = prevClip
//      res
//    }
//
//    def drawRect(): Unit
//    def fillRect(): Unit
//  }
//}
//
//sealed trait Paint extends Any
//final case class Color(color: Int) extends AnyVal with Paint
//sealed trait Gradient extends Any with Paint
//case class LinearGradient(starX: Double, startY: Double, endX: Double, endY: Double,
//                          fractions: Array[Double], colors: Array[Color], cycle: Gradient.Cycle = Gradient.Cycle.NoCycle) extends Gradient
//case class RadiantGradient(centerX: Double, centerY: Double, radius: Double, focusX: Double, focusY: Double,
//                           fractions: Array[Double], colors: Array[Color], cycle: Gradient.Cycle = Gradient.Cycle.NoCycle) extends Gradient
//
//object Gradient {
//  sealed trait Cycle
//  object Cycle {
//    case object NoCycle extends Cycle
//    case object Reflect extends Cycle
//    case object Repeat extends Cycle
//  }
//}
