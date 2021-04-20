package guarana.swing

import language.implicitConversions
import scala.util.{Try}
import util.*

extension (d: Double | Float | Int) {
  inline def em(using sc: Scenegraph, vc: VarContext) = 
    (inline d match {
      case i: Int => i.toDouble
      case d: Double => d
      case f: Float => f.toDouble
    }) * sc.emSize()
}

/** Calculates the map of name→var for this node by using reflection
  */
trait VarsMap {
  protected var ignoreProperties = collection.mutable.Set.empty[String]
  protected lazy val VarsMap = getClass.getDeclaredMethods.asInstanceOf[Array[java.lang.reflect.Method]].iterator
    .filter(f => classOf[SwingVar[_]].isAssignableFrom(f.getReturnType) && f.getParameterCount == 0)
    .map(_.invoke(this).asInstanceOf[SwingVar[_]])
    .map(v => v.name.toLowerCase.nn -> v).toMap
  protected inline def varsPropertyListener(instance: Any, debug: Boolean = false)(using sg: Scenegraph): java.beans.PropertyChangeListener = { evt =>
    val property = evt.getPropertyName.toLowerCase.nn
    if (debug) println(s"Trying to update $property")
    VarsMap.get(property) foreach {
      case sv: SwingVar[t] =>
        if (debug) println("  found swing var")
        sg.update(summon[VarContext].externalPropertyUpdated(sv, evt.getNewValue.asInstanceOf[t])(using ValueOf(instance.asInstanceOf[sv.ForInstance])))
    }
  }: java.beans.PropertyChangeListener
}

extension [T, U](e: T | Null) inline def ? (inline f: T => U): U | Null = if (e != null) f(e.asInstanceOf[T]) else null
extension [T](e: T | Null) inline def toOption: Option[T] = if (e != null) Some(e.asInstanceOf[T]) else None
extension [F[_], T](e: F[T | Null] | Null) inline def nnn: F[T] = e.asInstanceOf[F[T]]

extension (d: javax.swing.text.Document) {
  def defaultRootElement = d.getDefaultRootElement
  def length = d.getLength
  def empty = d.length == 0
  def nonEmpty = !d.empty
  def text: String = d.getText(0, d.getLength).nn
  def text_=(t: String) = d match {
    case d: javax.swing.text.AbstractDocument => d.replace(0, d.getLength, t, null)
    case _ => 
      d.remove(0, d.getLength)
      d.insertString(0, t, null)
    }
  def startPosition = d.getStartPosition
  def endPosition = d.getEndPosition
  def rootElements = d.getRootElements
}

extension [E](m: javax.swing.ListModel[E]) {
  def elements: collection.IndexedSeqView[E] = (0 until m.getSize).view.map(m.getElementAt(_).nn)
}
implicit def seqToListModel[E](s: Seq[E]): javax.swing.ListModel[E] = new javax.swing.AbstractListModel[E] {
  def getSize() = s.size
  def getElementAt(i: Int) = s(i)
}

object Box {
  
  def createFiller(
    minWidth: Binding[Double],
    maxWidth: Binding[Double],
    minHeight: Binding[Double],
    maxHeight: Binding[Double]): Scenegraph ?=>  VarContextAction[Node] = {

    val res = Node.wrap(javax.swing.Box.createGlue().nn.asInstanceOf[java.awt.Container])
    Node.init(res)
    res.minSize := Binding.dyn((minWidth(), minHeight()))
    res.prefSize := Binding.dyn(res.minSize())
    res.maxSize := Binding.dyn((maxWidth(), maxHeight()))
    res
  }

  def glue(): Scenegraph ?=> VarContextAction[Node] = createFiller(0.0, Int.MaxValue.toDouble, 0.0, Int.MaxValue.toDouble)
  def horizontalGlue(): Scenegraph ?=> VarContextAction[Node] = createFiller(0.0, Int.MaxValue.toDouble, 0.0, 0.0)
  def horizontalStrut(width: Binding[Double]): Scenegraph ?=> VarContextAction[Node] = createFiller(width, width, 0.0, Int.MaxValue.toDouble)
  def verticalGlue(): Scenegraph ?=> VarContextAction[Node] = createFiller(0.0, 0.0, 0.0, Int.MaxValue.toDouble)
  def verticalStrut(height: Binding[Double]): Scenegraph ?=> VarContextAction[Node] = createFiller(0.0, 0.0, height, height)
  def strut(width: Binding[Double], height: Binding[Double]): Scenegraph ?=> VarContextAction[Node] = createFiller(width, width, height, height)
}

type Font = java.awt.Font
def Font(name: String, style: Opt[Int] = UnsetParam, size: Opt[Double] = UnsetParam)(using sc: Scenegraph): java.awt.Font = {
  import java.awt.font.TextAttribute
  val r = sc.stateReader(sc.emSize).toInt
  var res = new java.awt.Font(java.util.Map.of(TextAttribute.FAMILY, name))
  ifSet(style, s => res = res.deriveFont(s).nn)
  ifSet(size, s => res = res.deriveFont(s.toFloat).nn)
  res
}

type Bounds = java.awt.Rectangle
def Bounds(x: Double = 0, y: Double = 0, width: Double = 0, height: Double = 0) =
  java.awt.Rectangle(x.toInt, y.toInt, width.toInt, height.toInt)

case class Insets(top: Double = 0, right: Double = 0, bot: Double = 0, left: Double = 0) {
  def toAwt = java.awt.Insets(top.round.toInt, left.round.toInt, bot.round.toInt, right.round.toInt)
}
object Insets {
  def all(topRightBottomLeft: Double): Insets = Insets(topRightBottomLeft, topRightBottomLeft, topRightBottomLeft, topRightBottomLeft)
  def fromAwt(i: java.awt.Insets): Insets = Insets(i.top, i.right, i.bottom, i.left)
}

  /** Emitter for updates to var for this object.
    * The extension method is only available if a Emitter.Context is present, because it is otherwise useless to use it
    */
extension (a: Any) def varUpdates(using Emitter.Context) = ObsVal.VarUpdates.forInstance(a)

/** Support async Futures as vars */
extension [T](f: scala.concurrent.Future[T]) def asObsVal(using sc: Scenegraph): ExternalObsVal[Option[Try[T]]] { type ForInstance = scala.concurrent.Future.type } = 
  new ExternalVar[Option[Try[T]]] {
    lazy val name = f.toString
    type ForInstance = scala.concurrent.Future.type
    def get(n: ForInstance) = f.value
    private[swing] def set(n: ForInstance, v: Option[Try[T]]): Unit = ()
    def eagerEvaluation = false

    f.onComplete(res => sc.update(summon[VarContext].externalPropertyUpdated(this, Some(res))))
      (SwingExecutionContext) //we're going to do scenegrph.update, so it makes sense to already be in the right thread
  }

extension [T](f: java.util.concurrent.CompletableFuture[T]) def asObsVal(using sc: Scenegraph): ExternalObsVal[Option[Try[T]]] { type ForInstance = scala.concurrent.Future.type } =
  new ExternalVar[Option[Try[T]]] {
    lazy val name = f.toString
    type ForInstance = scala.concurrent.Future.type
    def get(n: ForInstance) = if f.isDone then Some(Try(f.get())) else None
    private[swing] def set(n: ForInstance, v: Option[Try[T]]): Unit = ()
    def eagerEvaluation = false

    f.handle((_, _) => sc.update(summon[VarContext].externalPropertyUpdated(this, get(scala.concurrent.Future))))
  }

object SwingExecutionContext extends scala.concurrent.ExecutionContext {
  def execute(runnable: Runnable): Unit = javax.swing.SwingUtilities.invokeLater(runnable)
  def reportFailure(cause: Throwable): Unit = cause.printStackTrace()
}