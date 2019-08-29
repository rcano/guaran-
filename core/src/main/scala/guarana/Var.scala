package guarana

import language.experimental.macros
import language.implicitConversions
import scala.annotation.compileTimeOnly

trait VarContext {
  def update[T](v: Var[T], binding: Binding[T])(implicit instance: ValueOf[v.ForInstance]): Unit
  def apply[T](v: ObsVal[T])(implicit instance: ValueOf[v.ForInstance]): T
}
object VarContext {
  @compileTimeOnly("No VarContext available")
  implicit def noContextAvailable: VarContext = ???
}

trait ObsVal[+T] {
  def name: String
  type ForInstance <: Singleton

  def initialValue: T

  def apply()(implicit instance: ValueOf[ForInstance], context: VarContext): T = context(this)

  override def toString = s"ObsVal($name)"
}

trait Var[T] extends ObsVal[T] {
  def :=(b: => Binding[T])(implicit context: VarContext, instance: ValueOf[ForInstance]): Unit = context(this) = b

  def forInstance[S <: Singleton](s: S) = this.asInstanceOf[Var[T] { type ForInstance = S }]
  def asObsVal: ObsVal[T] { type ForInstance = Var.this.ForInstance } = this

  override def toString = s"Var($name)"
}
object Var {
  def apply[T](varName: => String, initValue: => T) = new Var[T] {
    def initialValue = initValue
    lazy val name = varName
    type ForInstance = this.type
  }
  def autoName[T](initValue: => T)(implicit fullname: => sourcecode.FullName) = new Var[T] {
    def initialValue = initValue
    lazy val name = fullname.value
    type ForInstance = this.type
  }
}

sealed trait Binding[T]
object Binding {
  case class Const[T](value: () => T) extends Binding[T]
  case class Compute[T](dependencies: () => Seq[Dep[_]], dependents: () => Seq[Dep[_]], compute: VarContext => T) extends Binding[T]

  implicit def const[T](t: => T): Binding[T] = new Const(() => t)
  def bind[T](dependencies: => Seq[Dep[_]], dependents: => Seq[Dep[_]])(compute: VarContext => T): Binding[T] =
    new Compute(() => dependencies, () => dependents, compute)

  final case class Dep[T](variable: ObsVal[T], instance: Any)
  implicit def var2Dep[T](v: ObsVal[T])(implicit instance: ValueOf[v.ForInstance]): Dep[T] = Dep(v, instance.value)
  
  def dyn[T](f: => T): Binding[T] = macro VarMacros.thunk2Binding[T]
}
