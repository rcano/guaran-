package guarana

import language.experimental.macros
import language.implicitConversions
import scala.annotation.compileTimeOnly

trait VarContext {
  def update[T](v: Var[T], binding: Var.Binding[T])(implicit instance: ValueOf[v.ForInstance]): Unit
  def apply[T](v: Var[T])(implicit instance: ValueOf[v.ForInstance]): T
}

trait Var[T] {
  type ForInstance <: Singleton
  def :=(b: => Var.Binding[T])(implicit context: VarContext, instance: ValueOf[ForInstance]): Unit = context(this) = b

  @compileTimeOnly("can't apply this Var out of scope")
  def apply(): T = ???

  def forInstance[S <: Singleton](s: S) = this.asInstanceOf[Var[T] { type ForInstance = S }]
}
object Var {
  sealed trait Binding[T]
  case class Const[T](value: () => T) extends Binding[T]
  case class Compute[T](dependencies: () => Seq[Dep[_]], dependents: () => Seq[Dep[_]], compute: VarContext => T) extends Binding[T]

  def const[T](t: => T): Binding[T] = new Const(() => t)
  def bind[T](dependencies: => Seq[Dep[_]], dependents: => Seq[Dep[_]])(compute: VarContext => T): Binding[T] = new Compute(() => dependencies, () => dependents, compute)
  
  final case class Dep[T](variable: Var[T], instance: Any)
  implicit def var2Dep[T](v: Var[T])(implicit instance: ValueOf[v.ForInstance]): Dep[T] = Dep(v, instance.value)

  def apply[T]() = new Var[T] {
    type ForInstance = this.type
  }

  def Binding[T](f: => T): Var.Binding[T] = macro VarMacros.thunk2Binding[T]
}
