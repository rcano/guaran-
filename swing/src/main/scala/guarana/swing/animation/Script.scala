package guarana.swing
package animation

import language.implicitConversions

import scala.concurrent.duration.FiniteDuration

sealed trait Script
case class Steps private[animation](steps: IndexedSeq[Script]) extends Script
/**
 * Calls the lambda passed to DoUntil until it returns true on each step of the ScriptEngine.
 * @param p Function that takes the elapsed time in millis since this step started and returns whether this step is done or not (true for yes)
 */
case class DoUntil private[animation](p: Scenegraph.ContextAction[Long => Boolean])(repr: String) extends Script {
  override def toString = repr
  override def hashCode = System.identityHashCode(this)
  override def equals(other: Any) = this eq other.asInstanceOf[AnyRef]
}
case class Parallel private[animation](steps: IndexedSeq[Script]) extends Script
case class Fork private[animation](script: Script) extends Script

object Script {
  extension on (s: Script) {
    def &(s2: Script): Parallel = s match {
      case Parallel(steps) => Parallel(steps :+ s2)
      case _ => Parallel(IndexedSeq(s, s2))
    }
  }
  def script(steps: Script*): Script = Steps(steps.flatMap {
      case Steps(steps) => steps
      case other => Seq(other)
    }.to(Vector))
  
  def step(f: Scenegraph.ContextAction[Unit]): DoUntil = DoUntil(l => {f; true})(s"Step()")
  def doUntil(f: Scenegraph.ContextAction[Long => Boolean]): DoUntil = DoUntil(f)(s"DoUntil()")
  def doWhile(cond: Scenegraph.ContextAction[Boolean])(action: Scenegraph.ContextAction[Any]): DoUntil = DoUntil { _ =>
    val cont = cond
    if (cont) action
    cont
  }(s"DoWhile()")
  def forLoop[T](init: T)(cond: Scenegraph.ContextAction[T => Boolean], inc: Scenegraph.ContextAction[T => T])(action: Scenegraph.ContextAction[T => Any]): DoUntil = {
    var it = init
    DoUntil { l =>
      val cont = cond(it)
      if (cont) action(it)
      it = inc(it)
      cont
    }(s"For($init, cond, inc)")
  }
  def await(d: FiniteDuration): DoUntil = {
    val deadline = d.toMillis
    DoUntil(_ >= deadline)(s"Await($d)")
  }
  def after[R](d: FiniteDuration)(action: Scenegraph.ContextAction[R]): Script = script(await(d), step(action))
  def interp[R](during: FiniteDuration)(action: Scenegraph.ContextAction[Double => R]): DoUntil = {
    val deadline = during.toMillis
    DoUntil { l =>
      action(l.toDouble / deadline min 1)
      l >= deadline
    }(s"Interpolate(during=$during)")
  }

  def fork(scripts: Script*): Fork = Fork(script(scripts:_*))
  
  // implicit def any2step[R](r: R): DoUntil = step(r)
}