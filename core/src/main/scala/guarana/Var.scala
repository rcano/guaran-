package guarana

import language.experimental.macros
import language.implicitConversions
import scala.annotation.compileTimeOnly


class Var[T] private[Var](var dependencies: () => Seq[Var[_]], var signalCompute: () => T Either (Array[Any] => T)) {
  def :=(v: Var[T]): Unit = {
    this.dependencies = v.dependencies
    this.signalCompute = v.signalCompute
  }

  @compileTimeOnly("can't apply this Var out of scope")
  def apply(): T = ???
}
object Var {
  def const[T](t: => T): Var[T] = new Var(() => Seq.empty, () => Left(t))
  def apply[T](deps: => Seq[Var[_]])(compute: Array[Any] => T): Var[T] = new Var(() => deps, () => Right(compute))

  implicit def thunk2Var[T](f: => T): Var[T] = macro VarMacros.thunk2Var[T]
}
