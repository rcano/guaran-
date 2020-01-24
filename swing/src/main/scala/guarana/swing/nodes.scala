package guarana.swing

import language.implicitConversions
import java.awt.{Container, ComponentOrientation, Color, Cursor, Dimension, GraphicsConfiguration, Rectangle}
import java.awt.event._
import javax.swing._
import guarana.swing.util._

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

opaque type Node  = java.awt.Container
object Node extends VarsMap {
  val Background = SwingVar[Node, java.awt.Color | Null]("background", _.getBackground, _.setBackground(_))
  val Bounds = SwingVar[Node, java.awt.Rectangle]("bounds", _.getBounds.nn, _.setBounds(_))
  val ComponentOrientation = SwingVar[Node, java.awt.ComponentOrientation]("componentOrientation", _.getComponentOrientation.nn, _.setComponentOrientation(_))
  val Cursor = SwingVar[Node, java.awt.Cursor | Null]("cursor", _.getCursor, _.setCursor(_))
  val Enabled = SwingVar[Node, Boolean]("enabled", _.isEnabled, _.setEnabled(_))
  val Focusable = SwingVar[Node, Boolean]("focusable", _.isFocusable, _.setFocusable(_))
  val Font = SwingVar[Node, java.awt.Font | Null]("font", _.getFont, _.setFont(_))
  val Foreground = SwingVar[Node, java.awt.Color | Null]("foreground", _.getForeground, _.setForeground(_))
  val MaximumSize = SwingVar[Node, java.awt.Dimension | Null]("maximumSize", _.getMaximumSize, _.setMaximumSize(_))
  val MinimumSize = SwingVar[Node, java.awt.Dimension | Null]("minimumSize", _.getMinimumSize, _.setMinimumSize(_))
  val MouseLocation = Var[(Int, Int)]("mouseLocation", (0, 0))
  val PrefSize = SwingVar[Node, java.awt.Dimension | Null]("prefSize", _.getPreferredSize, _.setPreferredSize(_))
  val Visible = SwingVar[Node, Boolean]("visible", _.isVisible, _.setVisible(_))

  given ops: (v: Node) extended with {
    def background = Node.Background.forInstance(v)
    def bounds = Node.Bounds.forInstance(v)
    def componentOrientation = Node.ComponentOrientation.forInstance(v)
    def cursor = Node.Cursor.forInstance(v)
    def enabled = Node.Enabled.forInstance(v)
    def focusable = Node.Focusable.forInstance(v)
    def font = Node.Font.forInstance(v)
    def foreground = Node.Foreground.forInstance(v)
    def maximumSize = Node.MaximumSize.forInstance(v)
    def minimumSize = Node.MinimumSize.forInstance(v)
    def mouseLocation = Node.MouseLocation.forInstance(v)
    def prefSize = Node.PrefSize.forInstance(v)
    def visible = Node.Visible.forInstance(v)
    def alignmentX = v.getAlignmentX
    def alignmentY = v.getAlignmentY
    def insets = v.getInsets
    def location = v.getLocation
    def size = v.getSize
    def location(x: Int, y: Int) = v.setLocation(x, y)
    def size(x: Int, y: Int) = v.setSize(x, y)
    def children: Seq[Node] = (0 until v.getComponentCount).map(i => v.getComponent(i).asInstanceOf[Container])
    def unwrap: java.awt.Container = v
  }

  def apply(v: java.awt.Container) = v.asInstanceOf[Node]

  def init(v: Node): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    
    v.addPropertyChangeListener(varsPropertyListener(v))
    v addMouseMotionListener new java.awt.event.MouseMotionListener {
      def mouseDragged(evt: java.awt.event.MouseEvent | Null) = ()
      def mouseMoved(evt: java.awt.event.MouseEvent | Null) = sc.update {
        val nnEvt = evt.nn
        Node.MouseLocation.forInstance(v) := (nnEvt.getX, nnEvt.getY)
      }
    }
  }
  def uninitialized(): Node = {
    val res = java.awt.Container().asInstanceOf[Node]
    
    res
  }
  
  def apply(
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[Node] = {
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
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}



opaque type Component <: Node = javax.swing.JComponent & Node
object Component extends VarsMap {
  val ActionMap = SwingVar[Component, javax.swing.ActionMap]("actionMap", _.getActionMap.nn, _.setActionMap(_))
  val AlignmentX = SwingVar[Component, Float]("alignmentX", _.getAlignmentX, _.setAlignmentX(_))
  val AlignmentY = SwingVar[Component, Float]("alignmentY", _.getAlignmentY, _.setAlignmentY(_))
  val Autoscrolls = SwingVar[Component, Boolean]("autoscrolls", _.getAutoscrolls, _.setAutoscrolls(_))
  val Border = SwingVar[Component, javax.swing.border.Border | Null]("border", _.getBorder, _.setBorder(_))
  val ComponentPopupMenu = SwingVar[Component, javax.swing.JPopupMenu | Null]("componentPopupMenu", _.getComponentPopupMenu, _.setComponentPopupMenu(_))
  val DebugGraphicsOptions = SwingVar[Component, Int]("debugGraphicsOptions", _.getDebugGraphicsOptions, _.setDebugGraphicsOptions(_))
  val DoubleBuffered = SwingVar[Component, Boolean]("doubleBuffered", _.isDoubleBuffered, _.setDoubleBuffered(_))
  val InheritsPopupMenu = SwingVar[Component, Boolean]("inheritsPopupMenu", _.getInheritsPopupMenu, _.setInheritsPopupMenu(_))
  val InputVerifier = SwingVar[Component, javax.swing.InputVerifier | Null]("inputVerifier", _.getInputVerifier, _.setInputVerifier(_))
  val Opaque = SwingVar[Component, Boolean]("opaque", _.isOpaque, _.setOpaque(_))
  val RequestFocusEnabled = SwingVar[Component, Boolean]("requestFocusEnabled", _.isRequestFocusEnabled, _.setRequestFocusEnabled(_))
  val ToolTipText = SwingVar[Component, String | Null]("toolTipText", _.getToolTipText, _.setToolTipText(_))
  val TransferHandler = SwingVar[Component, javax.swing.TransferHandler | Null]("transferHandler", _.getTransferHandler, _.setTransferHandler(_))
  val VerifyInputWhenFocusTarget = SwingVar[Component, Boolean]("verifyInputWhenFocusTarget", _.getVerifyInputWhenFocusTarget, _.setVerifyInputWhenFocusTarget(_))

  given ops: (v: Component) extended with {
    def actionMap = Component.ActionMap.forInstance(v)
    def alignmentX = Component.AlignmentX.forInstance(v)
    def alignmentY = Component.AlignmentY.forInstance(v)
    def autoscrolls = Component.Autoscrolls.forInstance(v)
    def border = Component.Border.forInstance(v)
    def componentPopupMenu = Component.ComponentPopupMenu.forInstance(v)
    def debugGraphicsOptions = Component.DebugGraphicsOptions.forInstance(v)
    def doubleBuffered = Component.DoubleBuffered.forInstance(v)
    def inheritsPopupMenu = Component.InheritsPopupMenu.forInstance(v)
    def inputVerifier = Component.InputVerifier.forInstance(v)
    def opaque = Component.Opaque.forInstance(v)
    def requestFocusEnabled = Component.RequestFocusEnabled.forInstance(v)
    def toolTipText = Component.ToolTipText.forInstance(v)
    def transferHandler = Component.TransferHandler.forInstance(v)
    def verifyInputWhenFocusTarget = Component.VerifyInputWhenFocusTarget.forInstance(v)
    def UI = v.getUI
    def UIClassID = v.getUIClassID
    def ancestorListeners = v.getAncestorListeners
    def inputMap = v.getInputMap
    def optimizedDrawingEnabled = v.isOptimizedDrawingEnabled
    def paintingForPrint = v.isPaintingForPrint
    def paintingTile = v.isPaintingTile
    def registeredKeyStrokes = v.getRegisteredKeyStrokes
    def rootPane = v.getRootPane
    def topLevelAncestor = v.getTopLevelAncestor
    def vetoableChangeListeners = v.getVetoableChangeListeners
    def visibleRect = v.getVisibleRect
    def unwrap: javax.swing.JComponent = v
  }

  def apply(v: javax.swing.JComponent) = v.asInstanceOf[Component]

  def init(v: Component): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    Node.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
  }
  
}

