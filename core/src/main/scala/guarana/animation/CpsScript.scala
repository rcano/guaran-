package guarana
package animation

import scala.compiletime.codeOf
import scala.concurrent.duration._
import scala.util.chaining._

  
enum StepEvalResult {
  case Done
  case Cont
  case NextStep(step: Script)
}
import StepEvalResult._

class Script(val nextStep: ScriptEngine ?=> ToolkitAction[Long => StepEvalResult]) {
  def &(other: Script): Script = Script { l =>
    (nextStep(l), other.nextStep(l)) match {
      case (Done, Done) => Done
      case (Cont, Cont) => Cont
      case (Cont, Done) => NextStep(Script(l2 => nextStep(l2 + l)))
      case (Done, Cont) => NextStep(Script(l2 => other.nextStep(l2 + l)))
      case (n: NextStep, Done) => n
      case (Done, n: NextStep) => n
      case (Cont, NextStep(ns)) => NextStep(Script(l2 => nextStep(l2 + l)) & ns)
      case (NextStep(ns), Cont) => NextStep(ns & Script(l2 => other.nextStep(l2 + l)))
      case (NextStep(s1), NextStep(s2)) => NextStep(s1 & s2)
    }
  }
}
object Script {
  given [T]: Conversion[Script, ScriptDsl.ScriptMonad[T]] = _.asInstanceOf[ScriptDsl.ScriptMonad[T]]
}
val EndOfScript = Script(_ => StepEvalResult.Done)

/** The Scripting DSL provides the basic combinators for writing arbitrary logic in CPS style
  * while enjoying cooperative multitasking, enabling writing logic that spans through time
  * in a transparent way.
  * 
  * Example:
  * 
  * {{{
  *   TODO: provide example
  * }}} 
  *
  */ 
object ScriptDsl {
  // type ScriptMonad[T] = Script
  object opaques:
    opaque type ScriptMonad[T] = Script
  type ScriptMonad[T] = opaques.ScriptMonad[T]
  private given [T]: Conversion[ScriptMonad[T], Script] = _.asInstanceOf[Script]
  
  given scriptCpsMonad: cps.CpsMonad[ScriptMonad] with cps.CpsMonadInstanceContext[ScriptMonad] with {
    def pure[T](t: T) = EndOfScript
    def map[A, B](s: ScriptMonad[A])(f: A => B): ScriptMonad[B] = Script { l => 
      s.nextStep(l) match {
        case Done => 
          f(null.asInstanceOf[A])
          Done
        case Cont => Cont
        case NextStep(next) => NextStep(map(next)(f))
      }
    }
    def flatMap[A, B](s: ScriptMonad[A])(f: A => ScriptMonad[B]): ScriptMonad[B] = Script { l => 
      s.nextStep(l) match {
        case Done => NextStep(f(null.asInstanceOf[A]))
        case Cont => Cont
        case NextStep(next) => NextStep(flatMap(next)(f))
      }
    }
  }
  given scriptCpsMonadContext: cps.CpsMonadInstanceContextBody[ScriptMonad] = cps.CpsMonadInstanceContextBody(scriptCpsMonad)
  
  inline def script(inline script: ScriptEngine ?=> ToolkitAction[Any]): Script = 
    Script(_ => NextStep(cps.async(using scriptCpsMonad)(script)))
  
  inline def interp(during: FiniteDuration)(inline action: ScriptEngine ?=> ToolkitAction[Double => Any]): Unit = 
    val deadline = during.toMillis
    doUntil { l => 
      action(l.toDouble / deadline min 1)
      l >= deadline
    }
  inline def await(d: FiniteDuration): Unit = 
    val deadline = d.toMillis
    doUntil(_ >= deadline)

  inline def waitUntil(inline cond: ScriptEngine ?=> ToolkitAction[Boolean]): Unit =
    doUntil(_ => cond)

  inline def doUntil(inline cond: ScriptEngine ?=> ToolkitAction[Long => Boolean]): Unit =
    cps.await[ScriptMonad, Unit, ScriptMonad](doUntilStep(cond))
  def doUntilStep(cond: ScriptEngine ?=> ToolkitAction[Long => Boolean]): Script =
    Script { l => if cond(l) then Done else Cont }
  
  inline def parallel(inline t: NonEmptyTuple)(using inline u: Tuple.Union[t.type] =:= Script): Unit = {
    cps.await[ScriptMonad, Unit, ScriptMonad](t.toArray.reduce(_.asInstanceOf[Script] & _.asInstanceOf[Script]).asInstanceOf[Script])
  }

  inline def `yield`: Unit = cps.await[ScriptMonad, Unit, ScriptMonad](yieldStep)
  val yieldStep: Script = doUntilStep { _ != 0 } // the first time it is executed by the engine, it always starts at 0

  inline def endScript: Unit = cps.await[ScriptMonad, Unit, ScriptMonad](EndOfScript)

  def currentTimeNanos(using se: ScriptEngine): Long = se.currentTime
  def currentTimeMillis(using se: ScriptEngine): Long = se.currentTime / 1000000
}

