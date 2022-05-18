package guarana

import language.implicitConversions
import scala.annotation.targetName
import scala.annotation.compileTimeOnly

// type Keyed[+T] = impl.Keyed[T]
// export impl.Keyed

type VarContextAction[+T] = VarContext ?=> T

trait VarContext {
  def update[T](v: Var[T], binding: Binding[T])(using instance: ValueOf[v.ForInstance]): Unit
  def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T

  def externalPropertyUpdated[T](v: ObsVal[T], oldValue: Option[T])(using instance: ValueOf[v.ForInstance]): Unit
}
object VarContext {
  @compileTimeOnly("No VarContext available")
  implicit def noContextAvailable: VarContext = ???
}

sealed trait ObsVal[+T] extends util.Unique {
  def name: String
  type ForInstance <: Singleton

  /** Given an instance of ForInstance, provide a default value.
    * **Implementation Note:** Due to dotty compilation reifying the type of the parameter of this method, we need to turn to <:< to ensure the parameter
    * is of type object and no ClassCastExceptionOcurrs, because otherwise when creating actual instances of ObsVal and setting ForInstance to something,
    * it will reify the parameter instead of keeping the generic version defined in this trait.
    */
  def initialValue(v: Any)(using v.type <:< ForInstance): T

  def apply()(using instance: ValueOf[ForInstance]): VarContextAction[T] = c ?=> c(this)

  override def toString = s"ObsVal($name)"

  def unapply(evt: VarValueChanged[ObsVal])(using instance: ValueOf[ForInstance]): Option[(Option[T], T)] = {
    if (evt.instance == instance.value && evt.signal == this) Some((evt.prev.asInstanceOf[Option[T]], evt.curr.asInstanceOf[T]))
    else None
  }
  object generic {
    def unapply(evt: VarValueChanged[ObsVal]): Option[(evt.signal.ForInstance, Option[T], T)] = {
      if (evt.signal == ObsVal.this) Some((evt.instance.asInstanceOf[evt.signal.ForInstance], evt.prev.asInstanceOf[Option[T]], evt.curr.asInstanceOf[T]))
      else None
    }
  }
}
object ObsVal {
  val VarUpdates = Emitter[VarValueChanged[ObsVal]]()
  type Aux[T, Instance <: Singleton] = ObsVal[T] { type ForInstance = Instance }

  implicit def obs2Keyed[T](v: ObsVal[T])(implicit instance: ValueOf[v.ForInstance]): Keyed[v.type] = Keyed(v, instance.value)
}

sealed trait Var[T] extends ObsVal[T] {
  @targetName("assign")
  def :=(b: Binding[T])(using instance: ValueOf[ForInstance]): VarContextAction[this.type] = ctx ?=> { ctx(this) = b; this }
  // final def :=(n: Null)(using instance: ValueOf[ForInstance], nullEv: Null <:< T): VarContextAction[this.type] = (using ctx) => { ctx(this) = Binding.Const(() => nullEv(null)); this }
  def eagerEvaluation: Boolean

  def forInstance[S <: Singleton]: Var.Aux[T, S] = this.asInstanceOf[Var.Aux[T, S]]
  def forInstance[S <: Singleton](s: S): Var.Aux[T, S] = this.asInstanceOf[Var.Aux[T, S]]
  def asObsValIn[S <: Singleton](s: S): ObsVal.Aux[T, S] = this.asInstanceOf[ObsVal.Aux[T, S]]

  override def toString = s"Var($name)"
}
object Var extends VarExtensions {
  type Aux[T, Instance <: Singleton] = Var[T] { type ForInstance = Instance }
  def apply[T](varName: => String, initValue: => T, eagerEvaluation: Boolean = false) = {
    val ev = eagerEvaluation
    new Var[T] {
      def initialValue(v: Any)(using v.type <:< ForInstance): T = initValue
      def eagerEvaluation = ev
      lazy val name = varName
      type ForInstance = this.type
    }
  }
  inline def autoName[T](initValue: => T, eagerEvaluation: Boolean = false)(using inline inferredName: guarana.util.DeclaringOwner) = {
    apply(inferredName.name, initValue, eagerEvaluation)
  }
}

enum Binding[+T] {
  case Const(value: () => T)
  case Compute(compute: VarContext => T)

  def map[U](f: T => U): Binding[U] = this match {
    case Const(v) => 
      val cached = f(v())
      Const(() => cached)
    case Compute(compute) => Compute(ctx => f(compute(ctx)))
  }

  def apply()(using ctx: VarContext): T = this match {
    case Const(v) => v() 
    case Compute(compute) => compute(ctx)
  }
}

object Binding {
  given const[T]: Conversion[T, Const[T]] = t => new Const(() => t)
  def bind[T](compute: VarContext => T): Compute[T] = new Compute(compute)

  inline def dyn[T](f: VarContextAction[T]) = Compute(c => f(using c))

  val Null = Const[Null](() => null)
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
  def apply[N, T](varName: => String, getter: N => T): Aux[N, T] = new ExternalObsVal[T] {
    lazy val name = varName
    type ForInstance <: N & Singleton
    def get(n: ForInstance) = getter(n)
  }
}

trait ExternalVar[T] extends Var[T] with ExternalObsVal[T] {
  private[guarana] def set(n: ForInstance, v: T): Unit
  @targetName("assign")
  override def :=(b: Binding[T])(using instance: ValueOf[ForInstance]): VarContextAction[this.type] = {
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
    }
    this
  }
}

object ExternalVar {
  type Aux[N, T] = ExternalVar[T] {
    type ForInstance <: N & Singleton
  }
  def apply[N, T](varName: => String, getter: N => T, setter: (N, T) => Unit, eagerEvaluation: Boolean = false): Aux[N, T] = {
    val ev = eagerEvaluation
    new ExternalVar[T] {
      lazy val name = varName
      type ForInstance <: N & Singleton
      def get(n: ForInstance) = getter(n)
      def set(n: ForInstance, t: T) = setter(n, t)
      def eagerEvaluation = ev
    }
  }
}

import collection.mutable.{AbstractBuffer, ArrayBuffer, IndexedBuffer, IndexedSeqOps}
class ObsBuffer[T] extends 
    AbstractBuffer[T],
    IndexedBuffer[T] {
  import ObsBuffer.Event.*

  override def knownSize = length

  private val elements = ArrayBuffer[T]()

  val observers = ArrayBuffer.empty[PartialFunction[ObsBuffer.Event[T], Unit]]
  extension (f: PartialFunction[ObsBuffer.Event[T], Unit]) private inline def applyIfDefined(e: ObsBuffer.Event[T]): Unit =
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