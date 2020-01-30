package guarana.swing

import language.implicitConversions
import scala.annotation.{alpha}
import scala.annotation.compileTimeOnly

case class Keyed[+T](keyed: T, instance: Any)

type VarContextAction[+T] = (given VarContext) => T

trait VarContext {
  def update[T](v: Var[T], binding: Binding[T])(given instance: ValueOf[v.ForInstance]): Unit
  def apply[T](v: ObsVal[T])(given instance: ValueOf[v.ForInstance]): T

  private[guarana] def swingPropertyUpdated[T](v: Var[T], value: T)(given instance: ValueOf[v.ForInstance]): Unit
}
object VarContext {
  @compileTimeOnly("No VarContext available")
  implicit def noContextAvailable: VarContext = ???
}

sealed trait ObsVal[+T] {
  def name: String
  type ForInstance <: Singleton

  /** Given an instance of ForInstance, provide a default value.
    * **Implementation Note:** Due to dotty compilation reifying the type of the parameter of this method, we need to turn to <:< to ensure the parameter
    * is of type object and no ClassCastExceptionOcurrs, because otherwise when creating actual instances of ObsVal and setting ForInstance to something,
    * it will reify the parameter instead of keeping the generic version defined in this trait.
    */
  def initialValue(v: Any)(given v.type <:< ForInstance): T

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
  def apply[T](varName: => String, initValue: => T) = {
    new Var[T] {
      def initialValue(v: Any)(given v.type <:< ForInstance): T = initValue
      lazy val name = varName
      type ForInstance = this.type
    }
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

  def map[U](f: T => U): Binding[U] = this match {
    case Const(v) => 
      val cached = f(v())
      Const(() => cached)
    case Compute(compute) => Compute(ctx => f(compute(ctx)))
  }

  def apply()(given ctx: VarContext): T = this match {
    case Const(v) => v() 
    case Compute(compute) => compute(ctx)
  }
}

object Binding {
  given const[T]: Conversion[T, Const[T]] = t => new Const(() => t)
  def bind[T](compute: VarContext => T): Compute[T] = new Compute(compute)

  inline def dyn[T](f: VarContextAction[T]) = Compute(c => f(given c))
}

trait SwingObsVal[+T] extends ObsVal[T] {
  type ForInstance <: Node with Singleton
  private[swing] def get(n: ForInstance): T
  def initialValue(v: Any)(given ev: v.type <:< ForInstance) = get(ev(v))
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
  type Aux[N <: Node, T] = SwingVar[T] {
    type ForInstance <: N & Singleton
  }
  def apply[N <: Node, T](varName: => String, getter: N => T, setter: (N, T) => Unit): Aux[N, T] = new SwingVar[T] {
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