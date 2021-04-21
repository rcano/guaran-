package guarana

import scala.annotation.targetName
import scala.compiletime.erasedValue
import Binding.dyn

private[guarana] trait VarExtensions {
  
  inline def foldBinding[T](v: Var[T], v2: Binding[T], inline f: (T, T) => T)(using ValueOf[v.ForInstance], VarContext) =
    inline v2 match {
      case _: Binding.Const[Double] => v := f(v(), v2.asInstanceOf[Binding.Const[T]].value())
      case _ => v := dyn { f(v(), v2()) }
    }

  extension (v: Var[Byte])   @targetName("plusByte")   inline def += (v2: Binding[Byte])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2, (a, b) => (a + b).toByte)
  extension (v: Var[Short])  @targetName("plusShort")  inline def += (v2: Binding[Short])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2, (a, b) => (a + b).toShort)
  extension (v: Var[Int])    @targetName("plusInt")    inline def += (v2: Binding[Int])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,              _ + _)
  extension (v: Var[Long])   @targetName("plusLong")   inline def += (v2: Binding[Long])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,             _ + _)
  extension (v: Var[Float])  @targetName("plusFloat")  inline def += (v2: Binding[Float])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,            _ + _)
  extension (v: Var[Double]) @targetName("plusDouble") inline def += (v2: Binding[Double])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,           _ + _)

  extension (v: Var[Byte])   @targetName("minusByte")   inline def -= (v2: Binding[Byte])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2, (a, b) =>  (a - b).toByte)
  extension (v: Var[Short])  @targetName("minusShort")  inline def -= (v2: Binding[Short])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2, (a, b) => (a - b).toShort)
  extension (v: Var[Int])    @targetName("minusInt")    inline def -= (v2: Binding[Int])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,              _ - _)
  extension (v: Var[Long])   @targetName("minusLong")   inline def -= (v2: Binding[Long])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,             _ - _)
  extension (v: Var[Float])  @targetName("minusFloat")  inline def -= (v2: Binding[Float])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,            _ - _)
  extension (v: Var[Double]) @targetName("minusDouble") inline def -= (v2: Binding[Double])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,           _ - _)

  extension (v: Var[Byte])   @targetName("timesByte")   inline def *= (v2: Binding[Byte])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2, (a, b) =>  (a * b).toByte)
  extension (v: Var[Short])  @targetName("timesShort")  inline def *= (v2: Binding[Short])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2, (a, b) => (a * b).toShort)
  extension (v: Var[Int])    @targetName("timesInt")    inline def *= (v2: Binding[Int])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,              _ * _)
  extension (v: Var[Long])   @targetName("timesLong")   inline def *= (v2: Binding[Long])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,             _ * _)
  extension (v: Var[Float])  @targetName("timesFloat")  inline def *= (v2: Binding[Float])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,            _ * _)
  extension (v: Var[Double]) @targetName("timesDouble") inline def *= (v2: Binding[Double])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,           _ * _)

  extension (v: Var[Byte])   @targetName("divByte")   inline def /= (v2: Binding[Byte])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2, (a, b) =>  (a / b).toByte)
  extension (v: Var[Short])  @targetName("divShort")  inline def /= (v2: Binding[Short])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2, (a, b) => (a / b).toShort)
  extension (v: Var[Int])    @targetName("divInt")    inline def /= (v2: Binding[Int])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,              _ / _)
  extension (v: Var[Long])   @targetName("divLong")   inline def /= (v2: Binding[Long])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,             _ / _)
  extension (v: Var[Float])  @targetName("divFloat")  inline def /= (v2: Binding[Float])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,            _ / _)
  extension (v: Var[Double]) @targetName("divDouble") inline def /= (v2: Binding[Double])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,           _ / _)

  extension (v: Var[Byte])   @targetName("modByte")   inline def %= (v2: Binding[Byte])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2, (a, b) =>  (a % b).toByte)
  extension (v: Var[Short])  @targetName("modShort")  inline def %= (v2: Binding[Short])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2, (a, b) => (a % b).toShort)
  extension (v: Var[Int])    @targetName("modInt")    inline def %= (v2: Binding[Int])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,              _ % _)
  extension (v: Var[Long])   @targetName("modLong")   inline def %= (v2: Binding[Long])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,             _ % _)
  extension (v: Var[Float])  @targetName("modFloat")  inline def %= (v2: Binding[Float])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,            _ % _)
  extension (v: Var[Double]) @targetName("modDouble") inline def %= (v2: Binding[Double])(using ValueOf[v.ForInstance], VarContext) = foldBinding(v, v2,           _ % _)

  extension [T](v: Var[Seq[T]]) {
    @targetName("seqAppend")
    inline def :+=(t: T)(using ValueOf[v.ForInstance], VarContext) = v := v() :+ t
    @targetName("seqPrepend")
    inline def +:=(t: T)(using ValueOf[v.ForInstance], VarContext) = v := t +: v()
  }

  extension [T](v: Var[Set[T]]) {
    @targetName("setAdd")
    inline def +=(t: T)(using ValueOf[v.ForInstance], VarContext) = v := v() + t
    @targetName("setRemove")
    inline def -=(t: T)(using ValueOf[v.ForInstance], VarContext) = v := v() - t
    }
    extension [K, V](v: Var[Map[K, V]]) {
    @targetName("mapAdd")
    inline def +=(elem: (K, V))(using ValueOf[v.ForInstance], VarContext) = v := v().updated(elem._1, elem._2)
    @targetName("mapRemove")
    inline def -=(key: K)(using ValueOf[v.ForInstance], VarContext) = v := v() - key
  }
}
