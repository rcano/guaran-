package guarana.swing

import language.implicitConversions
import scala.annotation.{alpha}
import scala.annotation.compileTimeOnly

case class Keyed[+T](keyed: T, instance: Any)

type VarContextAction[+T] = VarContext ?=> T

trait VarContext {
  def update[T](v: Var[T], binding: Binding[T])(using instance: ValueOf[v.ForInstance]): Unit
  def apply[T](v: ObsVal[T])(using instance: ValueOf[v.ForInstance]): T

  private[guarana] def swingPropertyUpdated[T](v: Var[T], value: T)(using instance: ValueOf[v.ForInstance]): Unit
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
  def initialValue(v: Any)(using v.type <:< ForInstance): T

  def apply()(using instance: ValueOf[ForInstance]): VarContextAction[T] = (using c) => c(this)

  override def toString = s"ObsVal($name)"

  def unapply(evt: VarValueChanged[ObsVal])(using instance: ValueOf[ForInstance]): Option[(Option[T], T)] = {
    if (evt.key.instance == instance.value && evt.key.keyed == this) Some((evt.prev.asInstanceOf[Option[T]], evt.curr.asInstanceOf[T]))
    else None
  }
  object generic {
    def unapply(evt: VarValueChanged[ObsVal]): Option[(evt.key.keyed.ForInstance, Option[T], T)] = {
      if (evt.key.keyed == ObsVal.this) Some((evt.key.instance.asInstanceOf[evt.key.keyed.ForInstance], evt.prev.asInstanceOf[Option[T]], evt.curr.asInstanceOf[T]))
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
  @alpha("assign")
  def :=(b: Binding[T])(using instance: ValueOf[ForInstance]): VarContextAction[this.type] = (using ctx) => { ctx(this) = b; this }
  // final def :=(n: Null)(using instance: ValueOf[ForInstance], nullEv: Null <:< T): VarContextAction[this.type] = (using ctx) => { ctx(this) = Binding.Const(() => nullEv(null)); this }
  def eagerEvaluation: Boolean

  def forInstance[S <: Singleton](s: S): Var.Aux[T, S] = this.asInstanceOf[Var.Aux[T, S]]
  def asObsValIn[S <: Singleton](s: S): ObsVal.Aux[T, S] = this.asInstanceOf[ObsVal.Aux[T, S]]

  override def toString = s"Var($name)"
}
object Var {
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
  inline def autoName[T](initValue: => T, eagerEvaluation: Boolean = false)(using inline inferredName: guarana.swing.util.DeclaringVal) = {
    apply(inferredName.name, initValue, eagerEvaluation)
  }
  // inline def autoName[T] = ${autoNameMacro[T]}

  // def autoNameMacro[T](using ctx: scala.quoted.QuoteContext, t: scala.quoted.Type[T])/*: scala.quoted.Expr[Var[T]]*/ = {
  //   val inferredName = scala.quoted.Expr(ctx.tasty.rootPosition.sourceCode)
  //   '{
  //     $inferredName
  //     new _root_.guarana.swing.Var[T] {
  //       lazy val name = $inferredName
  //       type ForInstance = this.type
  //     }
  //   }
  // }
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
  given const[T] as Conversion[T, Const[T]] = t => new Const(() => t)
  def bind[T](compute: VarContext => T): Compute[T] = new Compute(compute)

  inline def dyn[T](f: VarContextAction[T]) = Compute(c => f(using c))

  val Null = Const[Null](() => null)
}

trait SwingObsVal[+T] extends ObsVal[T] {
  type ForInstance <: Node with Singleton
  private[swing] def get(n: ForInstance): T
  def initialValue(v: Any)(using ev: v.type <:< ForInstance) = get(ev(v))
}

trait SwingVar[T] extends Var[T] with SwingObsVal[T] {
  private[swing] def set(n: ForInstance, v: T): Unit
  @alpha("assign")
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
        ctx(this) //because swing is not really reactive, we need to compute this as eagerly as possible because it may trigger re rendering
    }
    this
  }
  def eagerEvaluation = true
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


import collection.mutable.{AbstractBuffer, ArrayBuffer, IndexedBuffer, IndexedSeqOps}
class ObsBuffer[T] extends 
    AbstractBuffer[T],
    IndexedBuffer[T],
    IndexedSeqOps[T, ObsBuffer, ObsBuffer[T]],
    collection.IterableFactoryDefaults[T, ObsBuffer] {
  import ObsBuffer.Event._

  private val elements = ArrayBuffer[T]()

  val observers = ArrayBuffer.empty[PartialFunction[ObsBuffer.Event[T], Unit]]
  private inline def (f: PartialFunction[ObsBuffer.Event[T], Unit]) applyIfDefined(e: ObsBuffer.Event[T]): Unit =
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
    elements.clear()
    observers foreach (_.applyIfDefined(Cleared))
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
  def update(idx: Int, elem: T): Unit = elements(idx) = elem
  
  // Members declared in scala.collection.SeqOps
  def apply(i: Int): T = elements(i)
  def length: Int = elements.length

}
object ObsBuffer {
  enum Event[+T] {
    case Added(elems: Seq[T])
    case Inserted(elems: Seq[T], at: Int)
    case Removed(elems: Seq[T], at: Int)
    case Replaced(oldElem: T, newElem: T)
    case Cleared
  }

  def apply[T](elems: T*): ObsBuffer[T] = {
    val res = new ObsBuffer[T]()
    res ++= elems
    res
  }
}