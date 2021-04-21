package guarana.util

import compiletime.{constValue, erasedValue, summonFrom, error}
import scala.util.chaining.*

trait ProductFields[P <: Product] {
  def fields: Seq[(String, scala.reflect.ClassTag[_])]
}

object ProductFields {
  inline def summonAllValues[T <: Tuple]: List[?] = inline compiletime.erasedValue[T] match {
    case EmptyTuple => Nil
    case _: (t *: ts) => compiletime.constValue[t] :: summonAllValues[ts]
  }
  inline def summonAllTypes[T <: Tuple]: List[scala.reflect.ClassTag[?]] = inline compiletime.erasedValue[T] match {
    case EmptyTuple => Nil
    case _: (t *: ts) => summonFrom { case m: scala.reflect.ClassTag[`t`] => m } :: summonAllTypes[ts]
  }

  inline given productFields[P <: Product](using mirror: deriving.Mirror.ProductOf[P]): ProductFields[P] = {
    val allValues = summonAllValues[mirror.MirroredElemLabels].asInstanceOf[Seq[String]]
    val allTypes = summonAllTypes[mirror.MirroredElemTypes]
    new ProductFields[P] {
      val fields = allValues.zip(allTypes)
    }
  }
}
type Lense[T, U] = (U, T) => U

trait ProductLenses[P <: Product] {
  def lenses: Seq[Lense[?, P]]
}
object ProductLenses {

  inline def lenseForLabels[T <: Tuple, P <: Product]: List[_] = inline compiletime.erasedValue[T] match {
    case EmptyTuple => Nil
    case _: (t *: ts) => lense[P](compiletime.constValue[t]) :: lenseForLabels[ts, P]
  }

  inline given productLenses[P <: Product](using inline mirror: deriving.Mirror.ProductOf[P]): ProductLenses[P] = {
    val lenses = lenseForLabels[mirror.MirroredElemLabels, P]
    // val allLabels = ProductFields.summonAllValues[mirror.MirroredElemLabels].asInstanceOf[Seq[String]]
    // allLabels.map(l => lense[P](l))
    ???
  }
  import scala.quoted.*
  inline def lense[P <: Product](inline label: Any) = ${lenseMacro[P]('label)}
  private def lenseMacro[P <: Product](labelNameExpr: Expr[Any])(using ctx: Quotes, pt: Type[P]) = {
    
    // val labelName = labelNameExpr.asInstanceOf[Expr[String]].value
    // // TypeApply(Select("", ???), Nil)
    // println(s"Trying field $labelName")
    // println(s"Trying field ${'{
    //   (p: String) => ()
    //   }.unseal}")
    // '{$p.copy(labelName = _)}
    '{"hakoona"}
  }
}

case class DeclaringVal(name: String)
object DeclaringVal {
  inline given declaringVal: DeclaringVal = ${declaringValMacro}
  import scala.quoted.*
  def declaringValMacro(using ctx: Quotes): Expr[DeclaringVal] = {
    import ctx.reflect.*
    def isSynthetic(name: String) = name == "<init>" || (name.startsWith("<local ") && name.endsWith(">"))
    val owner = Iterator.unfold(Symbol.spliceOwner)(o => if (o == Symbol.noSymbol) None else Some(o, o.maybeOwner))
      .drop(1)
      .dropWhile(o => o.name.trim.nn.pipe(n => isSynthetic(n) || n == "ev") || o.isLocalDummy)
      .nextOption.getOrElse(throw new AssertionError("failed to detect declaring val")).name
    '{DeclaringVal(${Expr(owner)})}
  }
}