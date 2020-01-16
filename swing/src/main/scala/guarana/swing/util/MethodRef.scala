package guarana.swing
package util

import java.lang.invoke.MethodHandle
import scala.quoted._

private[swing] inline def methodRef[T](f: => T => Any)(given c: scala.reflect.ClassTag[T]): MethodHandle = 
  ${methodRefMacro('f, '{c.runtimeClass.asInstanceOf[Class[T]]})}

private def methodRefMacro[T: Type](f: Expr[T => Any], clazz: Expr[Class[T]])(given ctx: QuoteContext) = {
  import ctx.tasty.{_, given}
  // println(ctx.show(f, show.SyntaxHighlight.ANSI))
  println(f.unseal.showExtractors)

  val res = f.unseal match {
    case Inlined(_, _, Block(List(
      DefDef(_, _, List(List(ValDef(tName, _, _))), _, Some(Block(List(
        DefDef(_, _, List(_), _, Some(Apply(Select(Ident(tName2), methodName), _)))), _)))), _)) if tName == tName2 =>

      val methodNameExpr = Expr(methodName)

      '{
        java.lang.invoke.MethodHandles.lookup.unreflect($clazz.getMethod($methodNameExpr))
      }

    case _ => 
      ctx.error("Unsupported method selector. Must be of the form `_.someMethod _ `")
      ???
  }
  println(ctx.show(res, show.SyntaxHighlight.ANSI))
  res
}