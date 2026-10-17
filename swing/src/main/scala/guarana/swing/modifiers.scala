package guarana
package swing

import scala.annotation.static
import scala.annotation.targetName

trait Modifier[-T] {
  def apply(t: T): VarContextAction[Unit]
}
object Modifier {

  @static def create[T](action: VarContextAction[T => Unit]): Modifier[T] = new Modifier[T] {
    def apply(t: T): VarContextAction[Unit] = action(t)
  }

  inline def apply[T](inline action: VarContextAction[T => Unit]): Modifier[T] = new Modifier[T] {
    def apply(t: T): VarContextAction[Unit] = action(t)
  }

  val empty: Modifier[Any] = apply(_ => ())

  given optModifier[T]: Conversion[Option[Modifier[T]], Modifier[T]] = {
    case None => empty
    case Some(value) => value
  }

  given seqModifier[T]: Conversion[Seq[Modifier[T]], Modifier[T]] = {
    case s if s.isEmpty => empty
    case seq => new Modifier[T] { def apply(t: T) = seq.foreach(_.apply(t)) }
  }

}

class ModifierVar[Base, T](val v: Var[T] { type ForInstance <: Base & Singleton }) {
  @targetName("assign")
  def :=(binding: Binding[T]): Modifier[Base] = Modifier(base => v.forInstance(base) := binding)
}

class ModifierEmitter[Base, T](val v: Emitter[T] { type ForInstance <: Base & Singleton }) {
  @targetName("assign")
  def :=(listener: EventIterator[T]): Modifier[Base] = Modifier(base => v.forInstance(base) := listener)
}
