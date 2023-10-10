package guarana.web

import guarana.web.utils.{IsPresent, YesNo, OnOff}

/** Represents a transformation from the given type `T` to a `U`
  */
trait Codec[T, U] {
  def encode(t: T): U
  def decode(s: U): T
}
object Codec {
  type To[x] = [t] =>> Codec[t, x]
  inline def apply[T, U](inline e: T => U, inline d: U => T): Codec[T, U] = new Codec[T, U] {
    def encode(t: T): U = e(t)
    def decode(u: U): T = d(u)
  }

  transparent inline def of[T](using x: Codec[T, ?]): x.type = x


  given Codec[String, String] = apply(s => s, s => s)
  given Codec[Int, String] = apply(_.toString(), _.toInt)
  given Codec[Long, String] = apply(_.toString(), _.toLong)
  given Codec[Double, String] = apply(_.toString(), _.toDouble)
  given Codec[Boolean, String] = apply(_.toString(), _.toBoolean)

  val BooleanAsIsCodec: Codec[Boolean, Boolean] = apply(v => v, v => v)
  val IntAsIsCodec: Codec[Int, Int] = apply(v => v, v => v)
  val LongAsIsCodec: Codec[Long, Long] = apply(v => v, v => v)
  val DoubleAsIsCodec: Codec[Double, Double] = apply(v => v, v => v)
  val StringAsIsCodec: Codec[String, String] = apply(v => v, v => v)

  given Codec[IsPresent, String] = apply(if _ then "" else null, _ != null)

  given Codec[YesNo, String] = apply(if _ then "yes" else "no", "yes".==)
    
  given Codec[OnOff, String] = apply(if _ then "on" else "off", "on".==)
}
