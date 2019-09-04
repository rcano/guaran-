package guarana

sealed trait Emitter[A] {
  type ForInstance <: Singleton

}
object Emitter {
  type Aux[A, Instance <: Singleton] = Emitter[A] { type ForInstance = Instance }

  private[guarana] trait Context {
    def listen[A](emitter: Emitter[A])(f: PartialFunction[A, Any])(implicit instance: ValueOf[emitter.ForInstance]): Unit
    def emit[A](emitter: Emitter[A], evt: A)(implicit instance: ValueOf[emitter.ForInstance]): Unit
    def dispose(emitter: Emitter[_])(implicit instance: ValueOf[emitter.ForInstance]): Unit
  }
}

sealed trait EventIterator[-T] {
  def step(t: T): Option[EventIterator[T]]

  def foreach[U <: T](f: U => Any): EventIterator[U]
  def filter[U <: T](pred: U => Boolean): EventIterator[U]
  def take(size: Int): EventIterator[T]
  def takeWhile[U <: T](pred: U => Boolean): EventIterator[U]
  def drop(size: Int): EventIterator[T]
  def dropWhile[U <: T](pred: U => Boolean): EventIterator[U]
}
object EventIterator extends EventIterator[Any] {
  def step(t: Any) = Some(this)

  private val step0 = EventIteratorImpl[Any](Seq.empty)
  def foreach[U <: Any](f: U => Any) = step0.foreach(f)
  def filter[U <: Any](pred: U => Boolean) = step0.filter(pred)
  def take(size: Int) = step0.take(size)
  def takeWhile[U <: Any](pred: U => Boolean) = step0.takeWhile(pred)
  def drop(size: Int) = step0.drop(size)
  def dropWhile[U <: Any](pred: U => Boolean) = step0.dropWhile(pred)

  private type Step[-T] = T => StepResult[T]
  private case class StepResult[-T](stepNewState: Option[Step[T]], abortIteartor: Boolean, passThroughElem: Boolean)

  private case class EventIteratorImpl[T](opsChain: collection.Seq[Step[T]]) extends EventIterator[T] {
    def step(t: T): Option[EventIterator[T]] = {
      def it(remSteps: collection.Seq[Step[T]], resChain: collection.mutable.Buffer[Step[T]]): Option[EventIterator[T]] = {
        remSteps.headOption flatMap { step =>
          val stepRes = step(t)
            stepRes.stepNewState.foreach(step => resChain += step)
            
          val resIt = if (stepRes.passThroughElem) {
              val t = remSteps.tail
              if (t.nonEmpty) it(t, resChain) else Some(EventIteratorImpl(resChain))
            } else Some(EventIteratorImpl(resChain ++ remSteps.tail))

          resIt.filter(_ => !stepRes.abortIteartor)
        }
      }
      it(opsChain, collection.mutable.Buffer.empty)
    }

    def foreach[U <: T](f: U => Any): EventIterator[U] = {
      lazy val step: Step[U] = u => { f(u); StepResult(Some(step), false, true) }
      EventIteratorImpl[U](opsChain :+ step)
    }
    def filter[U <: T](pred: U => Boolean): EventIterator[U] = {
      lazy val step: Step[U] = u => StepResult(Some(step), false, pred(u))
      EventIteratorImpl[U](opsChain :+ step)
    }
    def take(size: Int): EventIterator[T] = {
      def step(size: Int): Step[T] = t => {if (size > 1) StepResult(Some(step(size - 1)), false, true) else StepResult[Any](None, true, true)}
      EventIteratorImpl(opsChain :+ step(size))
    }
    def takeWhile[U <: T](pred: U => Boolean): EventIterator[U] = {
      lazy val step: Step[U] = t => if (pred(t)) StepResult(Some(step), false, true) else StepResult[Any](None, true, false)
      EventIteratorImpl(opsChain :+ step)
    }
    def drop(size: Int): EventIterator[T] = {
      def step(size: Int): Step[T] = t => if (size > 0) StepResult(Some(step(size - 1)), false, false) else StepResult[Any](None, false, true)
      EventIteratorImpl(opsChain :+ step(size))
    }
    def dropWhile[U <: T](pred: U => Boolean): EventIterator[U] = {
      lazy val step: Step[U] = t => if (pred(t)) StepResult(Some(step), false, false) else StepResult[Any](None, false, true)
      EventIteratorImpl(opsChain :+ step)
    }

    override def toString = f"${getClass.getName}@${System.identityHashCode(this)}%h"
  }
}