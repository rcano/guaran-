package guarana

import language.experimental.macros
import language.implicitConversions
import scala.annotation.compileTimeOnly

case class Keyed[+T](keyed: T, instance: Any)

trait VarContext {
  def update[T](v: Var[T], binding: Binding[T])(implicit instance: ValueOf[v.ForInstance]): Unit
  def apply[T](v: ObsVal[T])(implicit instance: ValueOf[v.ForInstance]): T
}
object VarContext {
  @compileTimeOnly("No VarContext available")
  implicit def noContextAvailable: VarContext = ???
}

sealed trait ObsVal[+T] {
  def name: String
  type ForInstance <: Singleton

  def initialValue: T

  def apply()(implicit instance: ValueOf[ForInstance], context: VarContext): T = context(this)

  override def toString = s"ObsVal($name)"
}
object ObsVal {
  type Aux[T, Instance <: Singleton] = ObsVal[T] { type ForInstance = Instance }

  implicit def obs2Keyed[T](v: ObsVal[T])(implicit instance: ValueOf[v.ForInstance]): Keyed[v.type] = Keyed(v, instance.value)
}

sealed trait Var[T] extends ObsVal[T] {
  def :=(b: Binding[T])(implicit context: VarContext, instance: ValueOf[ForInstance]): Unit = context(this) = b

  def forInstance[S <: Singleton](s: S): Var.Aux[T, S] = this.asInstanceOf[Var.Aux[T, S]]
  def asObsValIn[S <: Singleton](s: S): ObsVal.Aux[T, S] = this.asInstanceOf[ObsVal.Aux[T, S]]

  override def toString = s"Var($name)"
}
object Var {
  type Aux[T, Instance <: Singleton] = Var[T] { type ForInstance = Instance }
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
  case class Compute[T](compute: VarContext => T) extends Binding[T]

  implicit def const[T](t: => T): Binding[T] = new Const(() => t)
  def bind[T](compute: VarContext => T): Binding[T] = new Compute(compute)

  def dyn[T](f: => T): Binding[T] = macro VarMacros.thunk2Binding[T]
}
