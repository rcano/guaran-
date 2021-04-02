package guarana.swing
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

case class Script(nextStep: ScriptEngine ?=> Scenegraph.ContextAction[Long => StepEvalResult]) {
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
val EndOfScript = Script(_ => StepEvalResult.Done)

/** The Scripting DSL provides the basic combinators for writing arbitrary logic in CPS style
  * while enjoying cooperative multitasking, enabling writing logic that spans through time
  * in a transparent.
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
  given [T]: Conversion[Script, ScriptMonad[T]] = _.asInstanceOf[ScriptMonad[T]]
  given [T]: Conversion[ScriptMonad[T], Script] = _.asInstanceOf[Script]
  
  given scriptCpsMonad: cps.CpsMonad[ScriptMonad] with {
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
  
  inline def script(inline script: ScriptEngine ?=> Scenegraph.ContextAction[Any]): Script = 
    Script(_ => NextStep(cps.async(using scriptCpsMonad)(script)))
  
  inline def interp(during: FiniteDuration)(inline action: ScriptEngine ?=> Scenegraph.ContextAction[Double => Any]): Unit = 
    val deadline = during.toMillis
    doUntil { l => 
      action(l.toDouble / deadline min 1)
      l >= deadline
    }
  inline def await(d: FiniteDuration): Unit = 
    val deadline = d.toMillis
    doUntil(_ >= deadline)

  inline def waitUntil(inline cond: ScriptEngine ?=> Scenegraph.ContextAction[Boolean]): Unit =
    doUntil(_ => cond)

  inline def doUntil(inline cond: ScriptEngine ?=> Scenegraph.ContextAction[Long => Boolean]): Unit =
    cps.await[ScriptMonad, Unit](doUntilStep(cond))
  def doUntilStep(cond: ScriptEngine ?=> Scenegraph.ContextAction[Long => Boolean]): Script =
    Script { l => if cond(l) then Done else Cont }
  
  inline def parallel(inline t: NonEmptyTuple)(using inline u: Tuple.Union[t.type] =:= Script): Unit = {
    cps.await[ScriptMonad, Unit](t.toArray.reduce(_.asInstanceOf[Script] & _.asInstanceOf[Script]).asInstanceOf[Script])
  }

  inline def `yield`: Unit = cps.await[ScriptMonad, Unit](yieldStep)
  def yieldStep: Script =
    var firstTime = true
    doUntilStep { _ => 
      val res = firstTime
      firstTime = !firstTime
      res
    }

  def currentTimeNanos(using se: ScriptEngine): Long = se.currentTime
  def currentTimeMillis(using se: ScriptEngine): Long = se.currentTime / 1000000
}

