package apricot.util

import scala.language.implicitConversions

object InlinedNumeric {

  inline def apply[T](using num: InlinedNumeric[T]): InlinedNumeric[T] = num

  trait IntIsIntegral extends InlinedNumeric[Int], InlinedIntegral[Int] {
    extension (x: Int) {
      inline def +(y: Int): Int = x + y
      inline def -(y: Int): Int = x - y
      inline def *(y: Int): Int = x * y
      inline def /(y: Int): Int = x / y
      inline def %(y: Int): Int = x % y
      inline def unary_- : Int = -x
      inline def toInt: Int = x
      inline def toLong: Long = x.toLong
      inline def toFloat: Float = x.toFloat
      inline def toDouble: Double = x.toDouble
    }
    inline def fromInt(x: Int): Int = x
    def parseString(str: String): Option[Int] = str.toIntOption
    inline def compare(x: Int, y: Int): Int = java.lang.Integer.compare(x, y)
  }
  implicit object IntIsIntegral extends IntIsIntegral

  trait LongIsIntegral extends InlinedNumeric[Long], InlinedIntegral[Long] {
    extension (x: Long) {
      inline def +(y: Long): Long = x + y
      inline def -(y: Long): Long = x - y
      inline def *(y: Long): Long = x * y
      inline def /(y: Long): Long = x / y
      inline def %(y: Long): Long = x % y
      inline def unary_- : Long = -x
      inline def toInt: Int = x.toInt
      inline def toLong: Long = x
      inline def toFloat: Float = x.toFloat
      inline def toDouble: Double = x.toDouble
    }
    inline def fromInt(x: Int): Long = x.toLong
    def parseString(str: String): Option[Long] = str.toLongOption
    inline def compare(x: Long, y: Long): Int = java.lang.Long.compare(x, y)
  }
  implicit object LongIsIntegral extends LongIsIntegral

  trait FloatIsFractional extends InlinedNumeric[Float], InlinedFractional[Float] {
    extension (x: Float) {
      inline def +(y: Float): Float = x + y
      inline def -(y: Float): Float = x - y
      inline def *(y: Float): Float = x * y
      inline def /(y: Float): Float = x / y
      inline def unary_- : Float = -x
      inline def toInt: Int = x.toInt
      inline def toLong: Long = x.toLong
      inline def toFloat: Float = x
      inline def toDouble: Double = x.toDouble
    }
    inline def fromInt(x: Int): Float = x.toFloat
    def parseString(str: String): Option[Float] = str.toFloatOption
    inline def compare(x: Float, y: Float): Int = java.lang.Float.compare(x, y)
  }
  implicit object FloatIsFractional extends FloatIsFractional

  trait DoubleIsFractional extends InlinedNumeric[Double], InlinedFractional[Double] {
    extension (x: Double) {
      inline def +(y: Double): Double = x + y
      inline def -(y: Double): Double = x - y
      inline def *(y: Double): Double = x * y
      inline def /(y: Double): Double = x / y
      inline def unary_- : Double = -x
      inline def toInt: Int = x.toInt
      inline def toLong: Long = x.toLong
      inline def toFloat: Float = x.toFloat
      inline def toDouble: Double = x
      // logic in InlinedNumeric base trait mishandles sign(-0.0) and sign(Double.NaN)
    }
    inline def fromInt(x: Int): Double = x.toDouble
    def parseString(str: String): Option[Double] = str.toDoubleOption
    inline def compare(x: Double, y: Double): Int = java.lang.Double.compare(x, y)
  }
  implicit object DoubleIsFractional extends DoubleIsFractional
}

/** This class replicates scala.math.Numeric but all the designed for extension methods and inlining.
  * The abstract methods are non inlined so that they can be used in generic fashion, but all instances are inlined.
  */
trait InlinedNumeric[T] {
  def parseString(str: String): Option[T]
  /* inline  */def fromInt(x: Int): T
  /* inline  */def zero = fromInt(0)
  /* inline  */def one = fromInt(1)
  /* inline  */def compare(x: T, y: T): Int
  /* inline  */def max[U <: T](x: U, y: U): U = if (x >= y) x else y
  /* inline  */def min[U <: T](x: U, y: U): U = if (x <= y) x else y
  extension (x: T) {
    /* inline  */def +(y: T): T
    /* inline  */def -(y: T): T
    /* inline  */def *(y: T): T
    /* inline  */def unary_- : T
    /* inline  */def toInt: Int
    /* inline  */def toLong: Long
    /* inline  */def toFloat: Float
    /* inline  */def toDouble: Double
  
    inline def abs: T = inline x match {
      case x: Float => math.abs(x).asInstanceOf[T]
      case x: Double => math.abs(x).asInstanceOf[T]
      case _ => if (x < zero) -x else x
    }
      

    /* inline  */def <=(y: T): Boolean = compare(x, y) <= 0
    /* inline  */def >=(y: T): Boolean = compare(x, y) >= 0
    /* inline  */def <(y: T): Boolean = compare(x, y) < 0
    /* inline  */def >(y: T): Boolean = compare(x, y) > 0
    /* inline  */def equiv(y: T): Boolean = compare(x, y) == 0
  
    inline def sign: T = inline x match {
      case x: Float => math.signum(x).asInstanceOf[T]
      case x: Double => math.signum(x).asInstanceOf[T]
      case _ => 
        if (x < zero) -one
        else if (x > zero) one
        else zero
    }
  }
}

trait InlinedIntegral[T] extends InlinedFractional[T] {
  extension (x: T) {
    inline def %(rhs: T): T
    inline def /%(rhs: T) = (x / rhs, x % rhs)
  }
}

trait InlinedFractional[T] {
  extension (x: T) {
    inline def /(rhs: T): T
  }
}
