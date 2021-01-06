//AUTOGENERATED FILE, DO NOT MODIFY

package guarana.swing

import language.implicitConversions
import java.awt.{Component => _, MenuBar => _, MenuItem => _, TextComponent => _, TextField => _, PopupMenu => _, _}
import java.awt.event._
import javax.swing.{Action => _, _}
import javax.swing.event._
import guarana.swing.util._
import scala.jdk.CollectionConverters._
import scala.util.chaining._

opaque type Node  >: java.awt.Component = java.awt.Component
object Node extends VarsMap {
  val Background: SwingVar.Aux[Node, java.awt.Color | Null] = SwingVar[Node, java.awt.Color | Null]("background", _.getBackground, _.setBackground(_))
  val Bounds: SwingVar.Aux[Node, Bounds] = SwingVar[Node, Bounds]("bounds", _.getBounds.nn, _.setBounds(_))
  val ComponentOrientation: SwingVar.Aux[Node, java.awt.ComponentOrientation] = SwingVar[Node, java.awt.ComponentOrientation]("componentOrientation", _.getComponentOrientation.nn, _.setComponentOrientation(_))
  val Cursor: SwingVar.Aux[Node, java.awt.Cursor | Null] = SwingVar[Node, java.awt.Cursor | Null]("cursor", _.getCursor, _.setCursor(_))
  val Enabled: SwingVar.Aux[Node, Boolean] = SwingVar[Node, Boolean]("enabled", _.isEnabled, _.setEnabled(_))
  val Focusable: SwingVar.Aux[Node, Boolean] = SwingVar[Node, Boolean]("focusable", _.isFocusable, _.setFocusable(_))
  private val FocusedMut: Var[Boolean] = Var[Boolean]("focusedMut", false, false)
  val Font: SwingVar.Aux[Node, java.awt.Font | Null] = SwingVar[Node, java.awt.Font | Null]("font", _.getFont, _.setFont(_))
  val Foreground: SwingVar.Aux[Node, java.awt.Color | Null] = SwingVar[Node, java.awt.Color | Null]("foreground", _.getForeground, _.setForeground(_))
  val MaxSize: SwingVar.Aux[Node, (Double, Double) | Null] = SwingVar[Node, (Double, Double) | Null]("maxSize", {n => val d = n.getMaximumSize; if (d != null) (d.getWidth, d.getHeight) else null}, {(n, d) => n.setMaximumSize(d.?(d => java.awt.Dimension(d._1.toInt, d._2.toInt)))})
  val MinSize: SwingVar.Aux[Node, (Double, Double) | Null] = SwingVar[Node, (Double, Double) | Null]("minSize", {n => val d = n.getMinimumSize; if (d != null) (d.getWidth, d.getHeight) else null}, {(n, d) => n.setMinimumSize(d.?(d => java.awt.Dimension(d._1.toInt, d._2.toInt)))})
  private[guarana] val MouseDragMut: Var[Option[MouseDrag]] = Var[Option[MouseDrag]]("mouseDragMut", None, false)
  private val MouseLocationMut: Var[(Int, Int)] = Var[(Int, Int)]("mouseLocationMut", (0, 0), false)
  val Name: SwingVar.Aux[Node, String | Null] = SwingVar[Node, String | Null]("name", _.getName, _.setName(_))
  val PrefSize: SwingVar.Aux[Node, (Double, Double) | Null] = SwingVar[Node, (Double, Double) | Null]("prefSize", {n => val d = n.getPreferredSize; if (d != null) (d.getWidth, d.getHeight) else null}, {(n, d) => n.setPreferredSize(d.?(d => java.awt.Dimension(d._1.toInt, d._2.toInt)))})
  val Visible: SwingVar.Aux[Node, Boolean] = SwingVar[Node, Boolean]("visible", _.isVisible, _.setVisible(_))

  val FocusEvents = Emitter[(FocusEvent, Boolean)]()
  val KeyEvents = Emitter[guarana.swing.KeyEvent]()
  val MouseEvents = Emitter[guarana.swing.MouseEvent]()

  given ops as Ops.type = Ops
  object Ops {
    extension (v: Node) {
      def background: Var.Aux[java.awt.Color | Null, v.type] = Node.Background.asInstanceOf[Var.Aux[java.awt.Color | Null, v.type]]
      def bounds: Var.Aux[Bounds, v.type] = Node.Bounds.asInstanceOf[Var.Aux[Bounds, v.type]]
      def componentOrientation: Var.Aux[java.awt.ComponentOrientation, v.type] = Node.ComponentOrientation.asInstanceOf[Var.Aux[java.awt.ComponentOrientation, v.type]]
      def cursor: Var.Aux[java.awt.Cursor | Null, v.type] = Node.Cursor.asInstanceOf[Var.Aux[java.awt.Cursor | Null, v.type]]
      def enabled: Var.Aux[Boolean, v.type] = Node.Enabled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def focusable: Var.Aux[Boolean, v.type] = Node.Focusable.asInstanceOf[Var.Aux[Boolean, v.type]]
      def font: Var.Aux[java.awt.Font | Null, v.type] = Node.Font.asInstanceOf[Var.Aux[java.awt.Font | Null, v.type]]
      def foreground: Var.Aux[java.awt.Color | Null, v.type] = Node.Foreground.asInstanceOf[Var.Aux[java.awt.Color | Null, v.type]]
      def maxSize: Var.Aux[(Double, Double) | Null, v.type] = Node.MaxSize.asInstanceOf[Var.Aux[(Double, Double) | Null, v.type]]
      def minSize: Var.Aux[(Double, Double) | Null, v.type] = Node.MinSize.asInstanceOf[Var.Aux[(Double, Double) | Null, v.type]]
      def mouseDragMut: Var.Aux[Option[MouseDrag], v.type] = Node.MouseDragMut.asInstanceOf[Var.Aux[Option[MouseDrag], v.type]]
      def name: Var.Aux[String | Null, v.type] = Node.Name.asInstanceOf[Var.Aux[String | Null, v.type]]
      def prefSize: Var.Aux[(Double, Double) | Null, v.type] = Node.PrefSize.asInstanceOf[Var.Aux[(Double, Double) | Null, v.type]]
      def visible: Var.Aux[Boolean, v.type] = Node.Visible.asInstanceOf[Var.Aux[Boolean, v.type]]

      def focusEvents: Emitter.Aux[(FocusEvent, Boolean), v.type] = Node.FocusEvents.forInstance(v)
      def keyEvents: Emitter.Aux[guarana.swing.KeyEvent, v.type] = Node.KeyEvents.forInstance(v)
      def mouseEvents: Emitter.Aux[guarana.swing.MouseEvent, v.type] = Node.MouseEvents.forInstance(v)

      def focused = Node.FocusedMut.asObsValIn(v)
      def mouseLocation = Node.MouseLocationMut.asObsValIn(v)
      def mouseDrag = Node.MouseDragMut.asObsValIn(v)
      def alignmentX = v.getAlignmentX
      def alignmentY = v.getAlignmentY
      def location = v.getLocation
      def size = v.getSize
      def location(x: Int, y: Int) = v.setLocation(x, y)
      def requestFocus() = v.requestFocus()
      def requestFocusInWindow() = v.requestFocusInWindow()
      def size(x: Int, y: Int) = v.setSize(x, y)
      def showing = v.isShowing
      def unwrap: java.awt.Component = v
    }
  }

  def wrap(v: java.awt.Component) = v.asInstanceOf[Node]

  def init(v: Node): Scenegraph ?=> Unit = (using sc: Scenegraph) => {
    
    v.addPropertyChangeListener(varsPropertyListener(v))
    v addMouseMotionListener new java.awt.event.MouseMotionListener {
      def mouseDragged(evt: java.awt.event.MouseEvent | Null) = ()
      def mouseMoved(evt: java.awt.event.MouseEvent | Null) = sc.update {
        val nnEvt = evt.nn
        Node.MouseLocationMut.forInstance(v) := (nnEvt.getX, nnEvt.getY)
      }
    }
    v addFocusListener new FocusListener {
      def focusGained(evt: FocusEvent) = sc.update {
        Node.FocusedMut.forInstance(v) := true 
        summon[Emitter.Context].emit(v.focusEvents, (evt.nn -> true))
      }
      def focusLost(evt: FocusEvent) = sc.update {
        Node.FocusedMut.forInstance(v) := false
        summon[Emitter.Context].emit(v.focusEvents, (evt.nn -> false))
      }
    }
    v addComponentListener new ComponentAdapter {
      override def componentMoved(e: ComponentEvent): Unit = updateBounds()
      override def componentResized(e: ComponentEvent): Unit = updateBounds()
      def updateBounds(): Unit = sc.update {
        summon[VarContext].swingPropertyUpdated(ops.bounds(v), v.getBounds.nn)
      }
    
    
      v.addKeyListener(sc.awtInputListener)
      v.addMouseListener(sc.awtInputListener)
      v.addMouseMotionListener(sc.awtInputListener)
    }
    
  }
  def uninitialized(): Node = {
    val res = java.awt.Container().asInstanceOf[Node]
    
    res
  }
  
  def apply(
    
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    mouseDragMut: Opt[Binding[Option[MouseDrag]]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[Node] = {
    val res = uninitialized()
    Node.init(res)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(mouseDragMut, Node.ops.mouseDragMut(res) := _)
    ifSet(name, Node.ops.name(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
  val MouseLocation: ObsVal[(Int, Int)] = MouseLocationMut
  val MouseDrag: ObsVal[Option[MouseDrag]] = MouseDragMut
}