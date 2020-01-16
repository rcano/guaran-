package guarana.swing

import language.implicitConversions
import scala.annotation.{alpha}
import scala.annotation.compileTimeOnly

case class Keyed[+T](keyed: T, instance: Any)

type VarContextAction[+T] = (given VarContext) => T

trait VarContext {
  def update[T](v: Var[T], binding: Binding[T])(implicit instance: ValueOf[v.ForInstance]): Unit
  def apply[T](v: ObsVal[T])(given instance: ValueOf[v.ForInstance]): T
}
object VarContext {
  @compileTimeOnly("No VarContext available")
  implicit def noContextAvailable: VarContext = ???
}

sealed trait ObsVal[+T] {
  def name: String
  type ForInstance <: Singleton

  def apply()(given instance: ValueOf[ForInstance]): VarContextAction[T] = (given c) => c(this)

  override def toString = s"ObsVal($name)"
}
object ObsVal {
  type Aux[T, Instance <: Singleton] = ObsVal[T] { type ForInstance = Instance }

  implicit def obs2Keyed[T](v: ObsVal[T])(implicit instance: ValueOf[v.ForInstance]): Keyed[v.type] = Keyed(v, instance.value)
}

sealed trait Var[T] extends ObsVal[T] {
  @alpha("assign")
  def :=(b: Binding[T])(given instance: ValueOf[ForInstance]): VarContextAction[this.type] = (given ctx) => { ctx(this) = b; this }

  def forInstance[S <: Singleton](s: S): Var.Aux[T, S] = this.asInstanceOf[Var.Aux[T, S]]
  def asObsValIn[S <: Singleton](s: S): ObsVal.Aux[T, S] = this.asInstanceOf[ObsVal.Aux[T, S]]

  override def toString = s"Var($name)"
}
object Var {
  type Aux[T, Instance <: Singleton] = Var[T] { type ForInstance = Instance }
  def apply[T](varName: => String) = new Var[T] {
    lazy val name = varName
    type ForInstance = this.type
  }
  // inline def autoName[T] = ${autoNameMacro[T]}

  // def autoNameMacro[T](given ctx: scala.quoted.QuoteContext, t: scala.quoted.Type[T])/*: scala.quoted.Expr[Var[T]]*/ = {
  //   import ctx.tasty.given
  //   val inferredName = scala.quoted.Expr(ctx.tasty.rootPosition.sourceCode)
  //   '{
  //     $inferredName
  //     // new _root_.guarana.swing.Var[T] {
  //     //   lazy val name = $inferredName
  //     //   type ForInstance = this.type
  //     // }
  //   }
  // }
}

enum Binding[T] {
  case Const(value: () => T)
  case Compute(compute: VarContext => T)
}

object Binding {
  given const[T]: Conversion[T, Const[T]] = t => new Const(() => t)
  def bind[T](compute: VarContext => T): Compute[T] = new Compute(compute)

  inline def dyn[T](f: VarContextAction[T]) = Compute(c => f(given c))
}

trait SwingObsVal[+T] extends ObsVal[T] {
  type ForInstance <: Node with Singleton
  private[swing] def get(n: ForInstance): T
  override def apply()(given instance: ValueOf[ForInstance]): VarContextAction[T] = get(instance.value)
}

trait SwingVar[T] extends Var[T] with SwingObsVal[T] {
  private[swing] def set(n: ForInstance, v: T): Unit
  @alpha("assign")
  override def :=(b: Binding[T])(given instance: ValueOf[ForInstance]): VarContextAction[this.type] = {
    val ctx = summon[VarContext]
    b match {
      case Binding.Const(v) => 
        val t = v()
        set(instance.value, t)
        ctx(this) = t
      case Binding.Compute(c) => 
        ctx(this) = Binding.bind { ctx =>
          val t = c(ctx)
          set(instance.value, t)
          t
        }
        ctx(this) //because swing is not really reactive, we need to compute this as eagerly as possible because it may trigger re rendering
    }
    this
  }
}

object SwingVar {
  def apply[N <: Node, T](varName: => String, getter: N => T, setter: (N, T) => Unit): SwingVar[T] = new SwingVar[T] {
    lazy val name = varName
    type ForInstance <: N & Singleton
    def get(n: ForInstance) = getter(n)
    def set(n: ForInstance, t: T) = setter(n, t)
  }
}

// object TestVars {

//   val a = Var[String]("a").forInstance(Binding)
//   val b = Var[String]("b").forInstance(Binding)

//   given vc: VarContext = null

//   a := Binding.dyn {

//     b := Binding.dyn { a() }

//     "hello"
//   }
// }