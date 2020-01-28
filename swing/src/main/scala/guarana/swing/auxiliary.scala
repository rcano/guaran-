package guarana.swing

import language.implicitConversions

given metrics: (d: Double | Float | Int) extended with {
  inline def em(given sc: Scenegraph) = Binding.dyn {
    (inline d match {
      case i: Int => i.toDouble
      case d: Double => d
      case f: Float => f.toDouble
    }) * sc.emSize()
  }
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

given documentOps: (d: javax.swing.text.Document) extended with {
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
    res.minimumSize := Binding.dyn(java.awt.Dimension(minWidth().toInt, minHeight().toInt))
    res.prefSize := Binding.dyn(res.minimumSize())
    res.maximumSize := Binding.dyn(java.awt.Dimension(maxWidth().toInt, maxHeight().toInt))
    res
  }

  def glue(): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, Int.MaxValue.toDouble, 0.0, Int.MaxValue.toDouble)
  def horizontalGlue(): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, Int.MaxValue.toDouble, 0.0, 0.0)
  def horizontalStrut(width: Binding[Double]): (given Scenegraph) =>  VarContextAction[Node] = createFiller(width, width, 0.0, Int.MaxValue.toDouble)
  def verticalGlue(): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, 0.0, 0.0, Int.MaxValue.toDouble)
  def verticalStrut(height: Binding[Double]): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, 0.0, height, height)
  def strut(width: Binding[Double], height: Binding[Double]): (given Scenegraph) =>  VarContextAction[Node] = createFiller(width, width, height, height)
}