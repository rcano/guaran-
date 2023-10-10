package guarana.util

import scala.quoted.*
import scala.annotation.compileTimeOnly

object MacroUtils {
  /*erased*/ inline def logType[T] = ${logTypeMacro[T]}
  private def logTypeMacro[T: quoted.Type](using q: quoted.Quotes): quoted.Expr[Unit] = {
    val simpleType = quoted.Type.show[T]
    val typeRepr = q.reflect.TypeRepr.of[T]
    q.reflect.report.warning(s"""type = $simpleType
                                |
                                |dealias = ${quoted.Type.show(using typeRepr.dealias.asType)}
                                |
                                |widen = ${quoted.Type.show(using typeRepr.widen.asType)}""".stripMargin)
    '{}
  }

  inline def typeDescrOf[T]: String = ${typeDescrOfMacro[T]}
  private def typeDescrOfMacro[T: quoted.Type](using q: quoted.Quotes): quoted.Expr[String] = {
    import q.reflect.*
    quoted.Expr(TypeTree.of[T].show(using Printer.TreeShortCode))
  }

  inline def showRaw(inline a: Any): Unit = ${showRawMacro('a)}
  def showRawMacro(a: Expr[Any])(using q: Quotes): Expr[Unit] = {
    import q.reflect.*
    val render = a.asTerm.show(using Printer.TreeAnsiCode)
    println(render)
    report.info(render)
    '{()}
  }

  inline def showTree(inline a: Any): Unit = ${showTreeMacro('a)}
  def showTreeMacro(a: Expr[Any])(using q: Quotes): Expr[Unit] = {
    import q.reflect.*
    val render = a.asTerm.show(using Printer.TreeStructure)
    println(render)
    report.info(render)
    '{()}
  }

  @compileTimeOnly("soft compile fail")
  def softFail: Unit = ()
}
