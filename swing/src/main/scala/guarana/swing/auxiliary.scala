package guarana.swing

import language.implicitConversions
import util._

extension metricsBinding on (d: Double | Float | Int) {
  inline def em(given sc: Scenegraph) = Binding.dyn {
    (inline d match {
      case i: Int => i.toDouble
      case d: Double => d
      case f: Float => f.toDouble
    }) * sc.emSize()
  }
}
extension metrics on (d: Double | Float | Int) {
  inline def em(given sc: Scenegraph, vc: VarContext) = 
    (inline d match {
      case i: Int => i.toDouble
      case d: Double => d
      case f: Float => f.toDouble
    }) * sc.emSize()
}

/** Calculates the map of name→var for this node by using reflection
  */
trait VarsMap {
  protected lazy val VarsMap = getClass.getDeclaredMethods.asInstanceOf[Array[java.lang.reflect.Method]].iterator
    .filter(f => classOf[Var[_]].isAssignableFrom(f.getReturnType) && f.getParameterCount == 0)
    .map(_.invoke(this).asInstanceOf[Var[_]])
    .map(v => v.name.toLowerCase -> v).toMap
  protected def varsPropertyListener(instance: Any)(given sg: Scenegraph): java.beans.PropertyChangeListener = { evt =>
    VarsMap.get(evt.getPropertyName) foreach {
      case sv: SwingVar[t] =>
        sg.update(summon[VarContext].swingPropertyUpdated(sv, evt.getNewValue.asInstanceOf[t])(given ValueOf(instance.asInstanceOf[sv.ForInstance])))
      case v: Var[t] =>
        sg.update(v.forInstance(instance) := evt.getNewValue.asInstanceOf[t])
    }
  }: java.beans.PropertyChangeListener
}

extension documentOps on (d: javax.swing.text.Document) {
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

object Box {
  
  def createFiller(
    minWidth: Binding[Double],
    maxWidth: Binding[Double],
    minHeight: Binding[Double],
    maxHeight: Binding[Double]): (given Scenegraph) =>  VarContextAction[Node] = {

    val res = Node(javax.swing.Box.createGlue().nn.asInstanceOf[java.awt.Container])
    Node.init(res)
    res.minSize := Binding.dyn((minWidth(), minHeight()))
    res.prefSize := Binding.dyn(res.minSize())
    res.maxSize := Binding.dyn((maxWidth(), maxHeight()))
    res
  }

  def glue(): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, Int.MaxValue.toDouble, 0.0, Int.MaxValue.toDouble)
  def horizontalGlue(): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, Int.MaxValue.toDouble, 0.0, 0.0)
  def horizontalStrut(width: Binding[Double]): (given Scenegraph) =>  VarContextAction[Node] = createFiller(width, width, 0.0, Int.MaxValue.toDouble)
  def verticalGlue(): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, 0.0, 0.0, Int.MaxValue.toDouble)
  def verticalStrut(height: Binding[Double]): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, 0.0, height, height)
  def strut(width: Binding[Double], height: Binding[Double]): (given Scenegraph) =>  VarContextAction[Node] = createFiller(width, width, height, height)
}

def Font(name: String, style: Opt[Int] = UnsetParam, size: Opt[Double] = UnsetParam): java.awt.Font = {
  var res = java.awt.Font.getFont(name).nn
  ifSet(style, s => res = res.deriveFont(s).nn)
  ifSet(size, s => res = res.deriveFont(s.toFloat).nn)
  res
}

type Bounds = java.awt.Rectangle
def Bounds(x: Double = 0, y: Double = 0, width: Double = 0, height: Double = 0) =
  java.awt.Rectangle(x.toInt, y.toInt, width.toInt, height.toInt)