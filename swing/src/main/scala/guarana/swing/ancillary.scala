package guarana
package swing

import language.implicitConversions
import guarana.*
import guarana.util.*
import scala.annotation.targetName

extension (d: Double | Float | Int) {
  inline def em(using sc: Scenegraph, vc: VarContext) =
    (inline d match {
      case i: Int => i.toDouble
      case d: Double => d
      case f: Float => f.toDouble
    }) * sc.emSize()
}

inline def scenegraph(using s: Scenegraph): Scenegraph = s

/** Calculates the map of name→var for this node by using reflection
  */
trait VarsMap {
  protected var ignoreProperties = collection.mutable.Set.empty[String]
  protected lazy val VarsMap = getClass.getDeclaredMethods
    .asInstanceOf[Array[java.lang.reflect.Method]]
    .iterator
    .filter(f => classOf[SwingVar[?]].isAssignableFrom(f.getReturnType) && f.getParameterCount == 0)
    .map(_.invoke(this).asInstanceOf[SwingVar[?]])
    .map(v => v.name.toLowerCase.nn -> v)
    .toMap
  protected inline def varsPropertyListener(instance: Any, debug: Boolean = false)(using
      sg: Scenegraph
  ): java.beans.PropertyChangeListener = { evt =>
    val property = evt.unn.getPropertyName.unn.toLowerCase.unn
    if (debug) scribe.info(s"Trying to update $property")
    VarsMap.get(property) foreach { case sv: SwingVar[t] =>
      if (debug) scribe.info("  found swing var")
      sg.update(
        summon[VarContext].externalPropertyUpdated(sv, Some(evt.unn.getOldValue.asInstanceOf[t]))(using
          ValueOf(instance.asInstanceOf[sv.ForInstance])
        )
      )
    }
  }: java.beans.PropertyChangeListener
}

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
      maxHeight: Binding[Double]
  ): Scenegraph ?=> VarContextAction[Component] = {

    val res = Component.wrap(javax.swing.Box.createGlue().nn.asInstanceOf[javax.swing.JComponent])
    Component.init(res)
    res.minSize := Binding.dyn((minWidth(), minHeight()))
    res.prefSize := Binding.dyn(res.minSize())
    res.maxSize := Binding.dyn((maxWidth(), maxHeight()))
    res
  }

  def glue(): Scenegraph ?=> VarContextAction[Component] = createFiller(0.0, Int.MaxValue.toDouble, 0.0, Int.MaxValue.toDouble)
  def horizontalGlue(): Scenegraph ?=> VarContextAction[Component] = createFiller(0.0, Int.MaxValue.toDouble, 0.0, 0.0)
  def horizontalStrut(width: Binding[Double]): Scenegraph ?=> VarContextAction[Component] =
    createFiller(width, width, 0.0, Int.MaxValue.toDouble)
  def verticalGlue(): Scenegraph ?=> VarContextAction[Component] = createFiller(0.0, 0.0, 0.0, Int.MaxValue.toDouble)
  def verticalStrut(height: Binding[Double]): Scenegraph ?=> VarContextAction[Component] = createFiller(0.0, 0.0, height, height)
  def strut(width: Binding[Double], height: Binding[Double]): Scenegraph ?=> VarContextAction[Component] =
    createFiller(width, width, height, height)
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

extension (n: Node) {

  /** Wraps this node in a box that ensures it is centered horizontally */
  def xCentered(setMaxSizeToPrefSize: Boolean = true): Scenegraph ?=> VarContextAction[Node] = {
    if (setMaxSizeToPrefSize) n.maxSize := Binding.dyn { n.prefSize() }
    Hbox(nodes = Seq(Box.horizontalGlue(), n, Box.horizontalGlue()))
  }

  /** Wraps this node in a box that ensures it is centered vertically */
  def yCentered(setMaxSizeToPrefSize: Boolean = true): Scenegraph ?=> VarContextAction[Node] = {
    if (setMaxSizeToPrefSize) n.maxSize := Binding.dyn { n.prefSize() }
    Vbox(nodes = Seq(Box.verticalGlue(), n, Box.verticalGlue()))
  }
}

type Bounds = java.awt.Rectangle
def Bounds(x: Double = 0, y: Double = 0, width: Double = 0, height: Double = 0): Bounds =
  java.awt.Rectangle(x.toInt, y.toInt, width.toInt, height.toInt)
@targetName("boundsFromDim")
def Bounds(dim: (width: Double, height: Double)): Bounds = Bounds(0, 0, dim.width, dim.height)
@targetName("boundsFromLoc")
def Bounds(loc: (x: Double, y: Double)): Bounds = Bounds(loc.x, loc.y, 0, 0)
def Bounds(dim: java.awt.Dimension): Bounds = java.awt.Rectangle(dim)
def Bounds(rec: java.awt.Rectangle): Bounds = java.awt.Rectangle(rec)

extension (i: Insets) {
  def toAwt = java.awt.Insets(i.top.round.toInt, i.left.round.toInt, i.bot.round.toInt, i.right.round.toInt)
}

extension (obj: Insets.type) {
  def fromAwt(i: java.awt.Insets): Insets = Insets(i.top, i.right, i.bottom, i.left)
}
given BoundsLike[Bounds] with {
  override def apply(x: Double, y: Double, width: Double, height: Double): Bounds = Bounds(x, y, width, height)
  extension (b: Bounds) override def x: Double = b.getX()
  extension (b: Bounds) override def y: Double = b.getY()
  extension (b: Bounds) override def width: Double = b.getWidth()
  extension (b: Bounds) override def height: Double = b.getHeight()
}

extension (size: (Double, Double)) {
  def *(d: Double): (Double, Double) = (size._1 * d, size._2 * d)
  def /(d: Double): (Double, Double) = (size._1 / d, size._2 / d)
  def +(other: (Double, Double)) = (size._1 + other._1, size._2 + other._2)
  def -(other: (Double, Double)) = (size._1 - other._1, size._2 - other._2)
}

object SwingExecutionContext extends scala.concurrent.ExecutionContext {
  def execute(runnable: Runnable): Unit = javax.swing.SwingUtilities.invokeLater(runnable)
  def reportFailure(cause: Throwable): Unit = cause.printStackTrace()
}
