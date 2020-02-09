package guarana.swing.util

import compiletime.{constValue, erasedValue, summonFrom, error}

trait ProductFields[P <: Product] {
  def fields: Seq[(String, scala.reflect.ClassTag[_])]
}

object ProductFields {
  inline def summonAllValues[T <: Tuple]: List[?] = inline compiletime.erasedValue[T] match {
    case _: Unit => Nil
    case _: (t *: ts) => compiletime.constValue[t] :: summonAllValues[ts]
  }
  inline def summonAllTypes[T <: Tuple]: List[scala.reflect.ClassTag[?]] = inline compiletime.erasedValue[T] match {
    case _: Unit => Nil
    case _: (t *: ts) => summonFrom { case m: scala.reflect.ClassTag[`t`] => m } :: summonAllTypes[ts]
  }

  inline given productFields[P <: Product](given mirror: deriving.Mirror.ProductOf[P]) as ProductFields[P] = {
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
    case _: Unit => Nil
    case _: (t *: ts) => lense[P](compiletime.constValue[t]) :: lenseForLabels[ts, P]
  }

  inline given productLenses[P <: Product](given inline mirror: deriving.Mirror.ProductOf[P]) as ProductLenses[P] = {
    val lenses = lenseForLabels[mirror.MirroredElemLabels, P]
    // val allLabels = ProductFields.summonAllValues[mirror.MirroredElemLabels].asInstanceOf[Seq[String]]
    // allLabels.map(l => lense[P](l))
    ???
  }
  import scala.quoted._, matching.{summonExpr}
  inline def lense[P <: Product](inline label: Any) = ${lenseMacro[P]('label)}
  private def lenseMacro[P <: Product](labelNameExpr: Expr[Any])(given ctx: QuoteContext, pt: Type[P]) = {
    import ctx.tasty.{_, given}
    
    val labelName = labelNameExpr.asInstanceOf[Expr[String]].value
    // TypeApply(Select("", ???), Nil)
    println(s"Trying field $labelName")
    println(s"Trying field ${'{
      (p: String) => ()
      }.unseal}")
    // '{$p.copy(labelName = _)}
    '{"hakoona"}
  }
}