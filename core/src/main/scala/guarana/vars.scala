package guarana

import language.implicitConversions
import scala.annotation.targetName
import scala.annotation.compileTimeOnly
import scala.concurrent.duration.FiniteDuration

// type Keyed[+T] = impl.Keyed[T]
// export impl.Keyed

type VarContextAction[+T] = VarContext ?=> T

trait VarContext {
  def update[T](v: Var[T], binding: Binding[T])(using instance: ValueOf[v.ForInstance]): Unit
  def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T

  def listen[A](emitter: Emitter[A])(f: EventIterator[A])(implicit instance: ValueOf[emitter.ForInstance]): Unit
  def emit[A](emitter: Emitter[A], evt: A)(implicit instance: ValueOf[emitter.ForInstance]): Unit

  def externalPropertyUpdated[T](v: ObsVal[T], oldValue: Option[T])(using instance: ValueOf[v.ForInstance]): Unit
}
object VarContext {
  @compileTimeOnly("No VarContext available")
  implicit def noContextAvailable: VarContext = ???
}

sealed trait ObsVal[+T] extends util.Unique {
  def name: String
  type ForInstance <: Singleton

  /** Given an instance of ForInstance, provide a default value. **Implementation Note:** Due to dotty compilation reifying the type of the
    * parameter of this method, we need to turn to <:< to ensure the parameter is of type object and no ClassCastException ocurrs, because
    * otherwise when creating actual instances of ObsVal and setting ForInstance to something, it will reify the parameter instead of
    * keeping the generic version defined in this trait.
    */
  def initialValue(v: Any)(using v.type <:< ForInstance): T

  /** Given an instance of ForInstance, perform a side-effect the first time the toolkit associates this var to it. **Implementation Note:**
    * Due to dotty compilation reifying the type of the parameter of this method, we need to turn to <:< to ensure the parameter is of type
    * object and no ClassCastException ocurrs, because otherwise when creating actual instances of ObsVal and setting ForInstance to
    * something, it will reify the parameter instead of keeping the generic version defined in this trait.
    */
  private[guarana] def onFirstAssociation(v: Any)(using v.type <:< ForInstance): Unit = ()

  @targetName("value")
  def apply()(using instance: ValueOf[ForInstance]): VarContextAction[T] = c ?=> c(this)

  override def toString = s"ObsVal($name)"

  def unapply(evt: VarValueChanged[ObsVal])(using instance: ValueOf[ForInstance]): Option[(Option[T], T)] = {
    if (evt.instance == instance.value && evt.signal == this) Some((evt.prev.asInstanceOf[Option[T]], evt.curr.asInstanceOf[T]))
    else None
  }
  object generic {
    def unapply(evt: VarValueChanged[ObsVal]): Option[(evt.signal.ForInstance, Option[T], T)] = {
      if (evt.signal == ObsVal.this)
        Some((evt.instance.asInstanceOf[evt.signal.ForInstance], evt.prev.asInstanceOf[Option[T]], evt.curr.asInstanceOf[T]))
      else None
    }
  }
}
object ObsVal {
  val VarUpdates = Emitter[VarValueChanged[ObsVal]]()
  type Aux[T, Instance <: Singleton] = ObsVal[T] { type ForInstance = Instance }

  implicit def obs2Keyed[T](v: ObsVal[T])(implicit instance: ValueOf[v.ForInstance]): Keyed[v.type] = Keyed(v, instance.value)

  val DebounceEmitter = Emitter[Any]()

  extension [T, Val[t] <: ObsVal[t]](inline v: Val[T]) {
    inline def forInstance[S <: Singleton](s: S): Val[T] { type ForInstance = S } = v.asInstanceOf[Val[T] { type ForInstance = S }]
    inline def forInstance[S <: Singleton]: Val[T] { type ForInstance = S } = v.asInstanceOf[Val[T] { type ForInstance = S }]
    inline def asObsValIn[S <: Singleton](s: S): ObsVal.Aux[T, S] = v.asInstanceOf[ObsVal.Aux[T, S]]
  }

  extension [T](v: ObsVal[T]) {
    def debounce(
        duration: FiniteDuration
    )(using tk: AbstractToolkit & animation.TimersDef, instance: ValueOf[v.ForInstance]): ObsVal.Aux[T, instance.value.type] = {
      tk.update {
        val debouncer = DebounceEmitter.asInstanceOf[Emitter[T]].forInstance(instance.value)
        var lastValue = v()
        val notifier = debouncer.toVar(lastValue, EventIterator)
        val timer = tk.TimerLike(
          duration,
          timer => {
            timer.stop()
            tk.update(summon[VarContext].emit(debouncer, lastValue))
          },
          _ => ()
        )
        instance.value.varUpdates := EventIterator.foreach {
          case v(_, newv) =>
            lastValue = newv
            timer.restart()
          case _ =>
        }
        notifier
      }
    }
  }
}

trait Var[T] extends ObsVal[T] {
  @targetName("assign")
  def :=(b: Binding[T])(using instance: ValueOf[ForInstance]): VarContextAction[this.type] = ctx ?=> { ctx(this) = b; this }
  final def update(f: T => T)(using instance: ValueOf[ForInstance]): VarContextAction[this.type] = this := f(this())
  // final def :=(n: Null)(using instance: ValueOf[ForInstance], nullEv: Null <:< T): VarContextAction[this.type] = (using ctx) => { ctx(this) = Binding.Const(() => nullEv(null)); this }
  def eagerEvaluation: Boolean

  private val _projections = new collection.mutable.ArrayBuffer[Projection[?]](0)
  def projections: collection.Seq[Projection[?]] = _projections

  class Projection[U] private[Var](val name: String, val get: T => U, val set: (T, U) => T) extends Var[U] {
    _projections += this
    type ForInstance = Var.this.ForInstance
    override def initialValue(v: Any)(using v.type <:< ForInstance): U = get(Var.this.initialValue(v))
    override def eagerEvaluation: Boolean = Var.this.eagerEvaluation
    def projected: Var.Aux[T, ForInstance] = Var.this
  }

  protected def derive[U](name: String, get: T => U, set: (T, U) => T): Var[U] = Projection(name, get, set)

  override def toString = s"Var($name)"
}

object Var extends VarExtensions {
  val doNothingOnFirstAssociation: Any => Unit = _ => ()

  type Aux[T, Instance <: Singleton] = Var[T] { type ForInstance = Instance }
  def apply[T](
      varName: => String,
      initValue: => T,
      eagerEvaluation: Boolean = false,
      onFirstAssociation: Any => Unit = doNothingOnFirstAssociation
  ) = {
    val ev = eagerEvaluation
    val ofa = onFirstAssociation
    new Var[T] {
      def initialValue(v: Any)(using v.type <:< ForInstance): T = initValue
      def eagerEvaluation = ev
      lazy val name = varName
      type ForInstance = this.type
      override private[guarana] def onFirstAssociation(v: Any)(using v.type <:< ForInstance): Unit = ofa(v)
    }
  }
  inline def autoName[T](initValue: => T, eagerEvaluation: Boolean = false, onFirstAssociation: Any => Unit = doNothingOnFirstAssociation)(
      using inline inferredName: guarana.util.DeclaringOwner
  ) = {
    apply(inferredName.name, initValue, eagerEvaluation, onFirstAssociation)
  }
}

enum Binding[+T] {
  case Const(value: () => T, transitionType: Option[animation.TransitionType[T]] = None)
  case Compute(compute: VarContext => T, transitionType: Option[animation.TransitionType[T]] = None)
  // case Transition(compute: VarContext => T)

  def map[U](f: T => U): Binding[U] = this match {
    case Const(v, t) =>
      val cached = f(v())
      Const(() => cached, None)
    case Compute(compute, t) => Compute(ctx => f(compute(ctx)), None)
  }

  def apply()(using ctx: VarContext): T = this match {
    case Const(v, _) => v()
    case Compute(compute, _) => compute(ctx)
  }

  def transitionType: Option[animation.TransitionType[T]]

  def withTransition[U >: T](t: Option[animation.TransitionType[U]]): Binding[U] = this match {
    case Const(v, _) => Const(v, t)
    case Compute(compute, _) => Compute(compute, t)
  }
}

object Binding {
  given const[T]: Conversion[T, Const[T]] = t => new Const(() => t, None)
  def bind[T](compute: VarContext => T): Compute[T] = new Compute(compute, None)

  inline def dyn[T](inline f: VarContextAction[T]): Compute[T] = impl.BindingMacro.dyn[T](f)
  inline def dynDebug[T](inline f: VarContextAction[T]): Compute[T] = impl.BindingMacro.dynDebug[T](f)

  val Null = Const[Null](() => null, None)
}

trait ExternalObsVal[+T] extends ObsVal[T] {
  type ForInstance <: Singleton
  private[guarana] def get(n: ForInstance): T
  def initialValue(v: Any)(using ev: v.type <:< ForInstance) = get(ev(v))
}

object ExternalObsVal {
  type Aux[N, T] = ExternalObsVal[T] {
    type ForInstance <: N & Singleton
  }
  def apply[N, T](varName: => String, getter: N => T, onFirstAssociation: N => Unit = Var.doNothingOnFirstAssociation): Aux[N, T] = {
    val ofa = onFirstAssociation
    new ExternalObsVal[T] {
      lazy val name = varName
      type ForInstance <: N & Singleton
      def get(n: ForInstance) = getter(n)

      override private[guarana] def onFirstAssociation(v: Any)(using ev: v.type <:< ForInstance): Unit = ofa(ev(v))
    }
  }
}

trait ExternalVar[T] extends Var[T] with ExternalObsVal[T] {
  private[guarana] def set(n: ForInstance, v: T): Unit
  // @targetName("assign")
  // override def :=(b: Binding[T])(using instance: ValueOf[ForInstance]): VarContextAction[this.type] = {
  //   val ctx = summon[VarContext]
  //   b match {
  //     case Binding.Const(v, tr) =>
  //       val t = v()
  //       val current = get(instance.value)
  //       if (current != t) {
  //         set(instance.value, t)
  //         ctx(this) = Binding.Const(() => t, tr)
  //       }
  //     case Binding.Compute(c, tr) =>
  //       val wr = impl.WeakRefFactory(instance.value.asInstanceOf[AnyRef])
  //       ctx(this) = Binding.Compute(
  //         { ctx =>
  //           wr.deref match {
  //             case null => c(ctx)
  //             case instance =>
  //               val t = c(ctx)
  //               set(instance.asInstanceOf[ForInstance], t)
  //               t
  //           }
  //         },
  //         tr
  //       )
  //   }
  //   this
  // }
}

object ExternalVar {
  type Aux[N, T] = ExternalVar[T] {
    type ForInstance <: N & Singleton
  }
  def apply[N, T](
      varName: => String,
      getter: N => T,
      setter: (N, T) => Unit,
      eagerEvaluation: Boolean = false,
      onFirstAssociation: N => Unit = Var.doNothingOnFirstAssociation
  ): Aux[N, T] = {
    val ev = eagerEvaluation
    val ofa = onFirstAssociation
    new ExternalVar[T] {
      lazy val name = varName
      type ForInstance <: N & Singleton
      def get(n: ForInstance) = getter(n)
      def set(n: ForInstance, t: T) = setter(n, t)
      def eagerEvaluation = ev
      override private[guarana] def onFirstAssociation(v: Any)(using ev: v.type <:< ForInstance): Unit = ofa(ev(v))
    }
  }
}

import collection.mutable.{AbstractBuffer, ArrayBuffer, IndexedBuffer}
class ObsBuffer[T] extends AbstractBuffer[T], IndexedBuffer[T] {
  import ObsBuffer.Event.*

  override def knownSize = length

  private val elements = ArrayBuffer[T]()

  val observers = ArrayBuffer.empty[PartialFunction[ObsBuffer.Event[T], Unit]]

  def addObserver(replayExisting: Boolean)(obs: PartialFunction[ObsBuffer.Event[T], Unit]): Unit = {
    observers += obs
    if (replayExisting)
      obs.applyIfDefined(Added(elements.toSeq))
  }
  extension (f: PartialFunction[ObsBuffer.Event[T], Unit])
    private inline def applyIfDefined(e: ObsBuffer.Event[T]): Unit =
      if (f.isDefinedAt(e)) f(e)

  def insert(idx: Int, elem: T): Unit = {
    elements.insert(idx, elem)
    observers foreach (_.applyIfDefined(Inserted(Seq(elem), idx)))
  }
  def insertAll(idx: Int, elems: IterableOnce[T]): Unit = {
    val es = elems.iterator.to(collection.immutable.IndexedSeq)
    elements.insertAll(idx, es)
    observers foreach (_.applyIfDefined(Inserted(es, idx)))
  }
  def prepend(elem: T): this.type = {
    elements.prepend(elem)
    observers foreach (_.applyIfDefined(Inserted(Seq(elem), 0)))
    this
  }
  def remove(idx: Int, count: Int): Unit = {
    val removed = elements.view.slice(idx, idx + count).to(Seq)
    elements.remove(idx, count)
    observers foreach (_.applyIfDefined(Removed(removed, idx)))
  }
  def remove(idx: Int): T = {
    val removed = elements.remove(idx)
    observers foreach (_.applyIfDefined(Removed(Seq(removed), idx)))
    removed
  }

  // Members declared in scala.collection.mutable.Clearable
  def clear(): Unit = {
    val size = elements.size
    elements.clear()
    observers foreach (_.applyIfDefined(Cleared(size)))
  }

  // Members declared in scala.collection.mutable.Growable
  def addOne(elem: T): this.type = {
    elements.addOne(elem)
    observers foreach (_.applyIfDefined(Added(Seq(elem))))
    this
  }
  override def addAll(elems: IterableOnce[T]): this.type = {
    val es = elems.iterator.to(collection.immutable.IndexedSeq)
    elements.addAll(es)
    observers foreach (_.applyIfDefined(Added(es)))
    this
  }

  // Members declared in scala.collection.mutable.SeqOps
  def update(idx: Int, elem: T): Unit =
    val oldv = elements(idx)
    elements(idx) = elem
    observers foreach (_.applyIfDefined(Replaced(oldv, elem, idx)))

  // Members declared in scala.collection.SeqOps
  def apply(i: Int): T = elements(i)
  def length: Int = elements.length

  private var sizeVar: Var.Aux[Int, this.type] | Null = null
  def createSizeVar[tk <: AbstractToolkit](): ToolkitAction[tk, Var.Aux[Int, this.type]] = tk ?=>
    vc ?=> {
      if (sizeVar == null) {
        sizeVar = Var[Int]("size", length).forInstance(this)
        val zv = sizeVar.unn
        given ValueOf[this.type] = ValueOf(this)
        addObserver(false) { case _ =>
          tk.update {
            zv := length
          }
        }
      }
      sizeVar.unn
    }
}
object ObsBuffer {
  enum Event[+T] {
    case Added(elems: Seq[T])
    case Inserted(elems: Seq[T], at: Int)
    case Removed(elems: Seq[T], at: Int)
    case Replaced(oldElem: T, newElem: T, at: Int)
    case Cleared(numberOfElements: Int)
  }

  def apply[T](elems: T*): ObsBuffer[T] = {
    val res = new ObsBuffer[T]()
    res ++= elems
    res
  }
}
