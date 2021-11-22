package guarana

import language.implicitConversions
import scala.util.{Try}
import util.*

/** Unsafe not null. Casts away nullity without a check */
extension [T](e: T | Null) inline def unn: T = e.asInstanceOf[T]
extension [T, U](e: T | Null) inline def ?(inline f: T => U): U | Null = if (e != null) f(e.asInstanceOf[T]) else null
extension [T](e: T | Null) inline def toOption: Option[T] = if (e != null) Some(e.asInstanceOf[T]) else None
extension [F[_], T](e: F[T | Null] | Null) inline def nnn: F[T] = e.asInstanceOf[e.type & F[T]]

case class Insets(top: Double = 0, right: Double = 0, bot: Double = 0, left: Double = 0)
object Insets {
  def all(topRightBottomLeft: Double): Insets = Insets(topRightBottomLeft, topRightBottomLeft, topRightBottomLeft, topRightBottomLeft)
}

/** Emitter for updates to var for this object.
  * The extension method is only available if a Emitter.Context is present, because it is otherwise useless to use it
  */
extension (a: Any) def varUpdates(using Emitter.Context) = ObsVal.VarUpdates.forInstance(a)

/** Support async Futures as vars */
extension [T](f: scala.concurrent.Future[T]) def asObsVal(using sc: AbstractToolkit, ec: scala.concurrent.ExecutionContext): ExternalObsVal[Option[Try[T]]] { type ForInstance = scala.concurrent.Future.type } = 
  new ExternalVar[Option[Try[T]]] {
    lazy val name = f.toString
    type ForInstance = scala.concurrent.Future.type
    def get(n: ForInstance) = f.value
    private[guarana] def set(n: ForInstance, v: Option[Try[T]]): Unit = ()
    def eagerEvaluation = false

    f.onComplete(res => sc.update(summon[VarContext].externalPropertyUpdated(this, Some(None))))(ec)
  }

extension [T](f: java.util.concurrent.CompletableFuture[T]) def asObsVal(using sc: AbstractToolkit): ExternalObsVal[Option[Try[T]]] { type ForInstance = scala.concurrent.Future.type } =
  new ExternalVar[Option[Try[T]]] {
    lazy val name = f.toString
    type ForInstance = scala.concurrent.Future.type
    def get(n: ForInstance) = if f.isDone then Some(Try(f.get().asInstanceOf[T])) else None
    private[guarana] def set(n: ForInstance, v: Option[Try[T]]): Unit = ()
    def eagerEvaluation = false

    f.handle((_: T | Null, _: Throwable | Null) => sc.update(summon[VarContext].externalPropertyUpdated(this, Some(None))))
  }

/** Definition of a Bounds like shape
  */
trait BoundsLike[Bounds] {
  /** Create a new Bounds instance */
  def apply(x: Double = 0, y: Double = 0, width: Double = 0, height: Double = 0): Bounds
  extension (b: Bounds) {
    def x: Double
    def y: Double
    def width: Double
    def height: Double
    /** equivalent to just x (this doesn't account for negative widths, for instance) */
    def minX: Double = x
    /** x + width */
    def maxX: Double = x + width
    /** equivalent to just y (this doesn't account for negative height, for instance) */
    def minY: Double = y
    /** y + height */
    def maxY: Double = y + height
    /** x + width / 2 */
    def centerX: Double = x + width / 2
    /** y + height / 2 */
    def centerY: Double = y + height / 2
  }
}

