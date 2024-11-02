package guarana

import language.implicitConversions

trait Emitter[A] extends util.Unique {
  type ForInstance <: Singleton

  final def forInstance[S <: Singleton](s: S): Emitter.Aux[A, S] = this.asInstanceOf[Emitter.Aux[A, S]]

  final def :=(listener: EventIterator[A])(implicit ctx: VarContext, instance: ValueOf[ForInstance]): Unit = ctx.listen(this)(listener)

  final def toVar(initialValue: A, listener: EventIterator[A])(implicit
      ctx: VarContext,
      instance: ValueOf[ForInstance]
  ): ObsVal.Aux[A, ForInstance] = {
    val res = Var[A]("emitter-var", initialValue).forInstance[ForInstance]
    val weakRef = java.lang.ref.WeakReference(res)
    this := listener.takeWhile(_ => !weakRef.refersTo(null)).foreach(e => res := e)
    res
  }

  /** Given an instance of ForInstance, perform a side-effect every time a this emitter receives a listener on a specific instance when it
    * had previously none.
    *
    * **Implementation Note:** Due to dotty compilation reifying the type of the parameter of this method, we need to turn to <:< to ensure
    * the parameter is of type object and no ClassCastException ocurrs, because otherwise when creating actual instances of ObsVal and
    * setting ForInstance to something, it will reify the parameter instead of keeping the generic version defined in this trait.
    */
  private[guarana] def onFirstListener(v: Any)(using v.type <:< ForInstance): Unit = ()

  /** Given an instance of ForInstance, perform a side-effect whenever this emitter has no listeners (in a specific instance).
    *
    * **Implementation Note:** Due to dotty compilation reifying the type of the parameter of this method, we need to turn to <:< to ensure
    * the parameter is of type object and no ClassCastException ocurrs, because otherwise when creating actual instances of ObsVal and
    * setting ForInstance to something, it will reify the parameter instead of keeping the generic version defined in this trait.
    */
  private[guarana] def onNoListener(v: Any)(using v.type <:< ForInstance): Unit = ()
}
object Emitter {
  val doNothingOnListener: (Emitter[?], Any) => Unit = (_, _) => ()

  type Aux[A, Instance <: Singleton] = Emitter[A] { type ForInstance = Instance }

  def apply[T]()(using inferredName: guarana.util.DeclaringOwner) = new Emitter[T] {
    type ForInstance = this.type
    override def toString = inferredName.toString
  }

  implicit def emitter2Keyed[T](e: Emitter[T])(using instance: ValueOf[e.ForInstance]): Keyed[e.type] = Keyed(e, instance.value)
}

sealed trait EventIterator[-T] {
  def step(t: T): ToolkitAction[Option[EventIterator[T]]]

  def foreach[U <: T](f: ToolkitAction[U => Any]): EventIterator[U]
  def forsome[U <: T](f: ToolkitAction[PartialFunction[U, Any]]): EventIterator[U]
  def filter[U <: T](pred: ToolkitAction[U => Boolean]): EventIterator[U]
  def take(size: Int): EventIterator[T]
  def takeWhile[U <: T](pred: ToolkitAction[U => Boolean]): EventIterator[U]
  def drop(size: Int): EventIterator[T]
  def dropWhile[U <: T](pred: ToolkitAction[U => Boolean]): EventIterator[U]
}
object EventIterator extends EventIterator[Any] {
  def step(t: Any) = Some(this)

  private val step0 = EventIteratorImpl[Any](Seq.empty)
  def foreach[U <: Any](f: ToolkitAction[U => Any]): EventIterator[U] = step0.foreach(f)
  def forsome[U <: Any](f: ToolkitAction[PartialFunction[U, Any]]): EventIterator[U] = step0.forsome(f)
  def filter[U <: Any](pred: ToolkitAction[U => Boolean]): EventIterator[U] = step0.filter(pred)
  def take(size: Int) = step0.take(size)
  def takeWhile[U <: Any](pred: ToolkitAction[U => Boolean]): EventIterator[U] = step0.takeWhile(pred)
  def drop(size: Int) = step0.drop(size)
  def dropWhile[U <: Any](pred: ToolkitAction[U => Boolean]): EventIterator[U] = step0.dropWhile(pred)

  private type Step[-T] = (VarContext, T) => StepResult[T]
  private case class StepResult[-T](stepNewState: Option[Step[T]], abortIteartor: Boolean, passThroughElem: Boolean)

  private case class EventIteratorImpl[T](opsChain: collection.Seq[Step[T]]) extends EventIterator[T] {
    def step(t: T): ToolkitAction[Option[EventIterator[T]]] = {
      def it(remSteps: collection.Seq[Step[T]], resChain: collection.mutable.Buffer[Step[T]]): Option[EventIterator[T]] = {
        remSteps.headOption flatMap { step =>
          val stepRes = step(summon, t)
          stepRes.stepNewState.foreach(step => resChain += step)

          val resIt = if (stepRes.passThroughElem) {
            val t = remSteps.tail
            if (t.nonEmpty) it(t, resChain) else Some(EventIteratorImpl(resChain))
          } else Some(EventIteratorImpl(resChain ++ remSteps.tail))

          resIt.filter(_ => !stepRes.abortIteartor)
        }
      }
      it(opsChain, new collection.mutable.ArrayBuffer(opsChain.size))
    }

    def foreach[U <: T](f: ToolkitAction[U => Any]): EventIterator[U] = {
      lazy val step: Step[U] = new Step[U] {
        def apply(ctx: VarContext, u: U) = { f(using ctx)(u); StepResult(Some(step), false, true) }
        override def toString = "foreach"
      }
      EventIteratorImpl[U](opsChain :+ step)
    }
    private val doNothing = [T] => (t: T) => (): Any
    def forsome[U <: T](f: ToolkitAction[PartialFunction[U, Any]]): EventIterator[U] = {
      lazy val step: Step[U] = new Step[U] {
        def apply(ctx: VarContext, u: U) = { f(using ctx).applyOrElse(u, doNothing[U]); StepResult(Some(step), false, true) }
        override def toString = "foreach"
      }
      EventIteratorImpl[U](opsChain :+ step)
    }
    def filter[U <: T](pred: ToolkitAction[U => Boolean]): EventIterator[U] = {
      lazy val step: Step[U] = new Step[U] {
        def apply(ctx: VarContext, u: U) = StepResult(Some(step), false, pred(using ctx)(u))
        override def toString = s"filter(${pred(using null.asInstanceOf)})"
      }
      EventIteratorImpl[U](opsChain :+ step)
    }
    def take(size: Int): EventIterator[T] = {
      def step(size: Int): Step[T] = new Step[T] {
        def apply(ctx: VarContext, t: T) = {
          if (size > 1) StepResult(Some(step(size - 1)), false, true) else StepResult[Any](None, true, true)
        }
        override def toString = s"take($size)"
      }
      EventIteratorImpl(opsChain :+ step(size))
    }
    def takeWhile[U <: T](pred: ToolkitAction[U => Boolean]): EventIterator[U] = {
      lazy val step: Step[U] = new Step[U] {
        def apply(ctx: VarContext, t: U) =
          if (pred(using ctx)(t)) StepResult(Some(step), false, true) else StepResult[Any](None, true, false)
        override def toString = s"takeWhile(${pred(using null.asInstanceOf)})"
      }
      EventIteratorImpl(opsChain :+ step)
    }
    def drop(size: Int): EventIterator[T] = {
      def step(size: Int): Step[T] = new Step[T] {
        def apply(ctx: VarContext, t: T) =
          if (size > 0) StepResult(Some(step(size - 1)), false, false) else StepResult[Any](None, false, true)
        override def toString = s"drop($size)"
      }
      EventIteratorImpl(opsChain :+ step(size))
    }
    def dropWhile[U <: T](pred: ToolkitAction[U => Boolean]): EventIterator[U] = {
      lazy val step: Step[U] = new Step[U] {
        def apply(ctx: VarContext, t: U) =
          if (pred(using ctx)(t)) StepResult(Some(step), false, false) else StepResult[Any](None, false, true)
        override def toString = s"dropWhile(${pred(using null.asInstanceOf)})"
      }
      EventIteratorImpl(opsChain :+ step)
    }

    override def toString = s"EventIterator(${opsChain.mkString(" → ")})"
  }
}
