package apricot.geom

import apricot.util.InlinedNumeric
import apricot.util.InlinedFractional

trait Shape2d {

  def bounds: Rect.Double
}

trait Rect[T] extends Shape2d {
  def x: T
  def y: T
  def width: T
  def height: T

  given numeric: (InlinedNumeric[T] & InlinedFractional[T])
  inline def maxX = x + width
  inline def maxY = y + height
  inline def midX = x + width / numeric.fromInt(2)
  inline def midY = y + height / numeric.fromInt(2)

  def bounds: Rect.Double = Rect.Double(x.toDouble, y.toDouble, width.toDouble, height.toDouble)
}
object Rect {
  case class Double(x: scala.Double, y: scala.Double, width: scala.Double, height: scala.Double) extends Rect[scala.Double] {
    override inline given numeric: InlinedNumeric.DoubleIsFractional = InlinedNumeric.DoubleIsFractional
  }
  case class Float(x: scala.Float, y: scala.Float, width: scala.Float, height: scala.Float) extends Rect[scala.Float] {
    override inline given numeric: InlinedNumeric.FloatIsFractional = InlinedNumeric.FloatIsFractional
  }
  case class Int(x: scala.Int, y: scala.Int, width: scala.Int, height: scala.Int) extends Rect[scala.Int] {
    override inline given numeric: InlinedNumeric.IntIsIntegral = InlinedNumeric.IntIsIntegral
  }
  case class Long(x: scala.Long, y: scala.Long, width: scala.Long, height: scala.Long) extends Rect[scala.Long] {
    override inline given numeric: InlinedNumeric.LongIsIntegral = InlinedNumeric.LongIsIntegral
  }
}
