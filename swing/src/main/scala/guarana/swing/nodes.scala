package guarana.swing

import language.implicitConversions
import java.awt.{Container, ComponentOrientation, Color, Cursor, Dimension, GraphicsConfiguration, Rectangle}
import java.awt.event._
import javax.swing._
import guarana.swing.util._

opaque type Node = Container

object Node {
  val Background = SwingVar[Node, Color]("background", _.getBackground, _.setBackground(_))
  val Bounds = SwingVar[Node, Rectangle]("bounds", _.getBounds, _.setBounds(_))
  val ComponentOrientation = SwingVar[Node, ComponentOrientation]("componentOrientation", _.getComponentOrientation, _.setComponentOrientation(_))
  val Cursor = SwingVar[Node, Cursor]("cursor", _.getCursor, _.setCursor(_))
  val Enabled = SwingVar[Node, Boolean]("enabled", _.isEnabled, _.setEnabled(_))
  val Focusable = SwingVar[Node, Boolean]("focusable", _.isFocusable, _.setFocusable(_))
  val Font = SwingVar[Node, java.awt.Font]("font", _.getFont, _.setFont(_))
  val Foreground = SwingVar[Node, java.awt.Color]("foreground", _.getForeground, _.setForeground(_))

  val MaximumSize = SwingVar[Node, Dimension]("maximumSize", _.getMaximumSize, _.setMaximumSize(_))
  val MinimumSize = SwingVar[Node, Dimension]("minimumSize", _.getMinimumSize, _.setMinimumSize(_))
  val PreferredSize = SwingVar[Node, Dimension]("prefSize", _.getPreferredSize, _.setPreferredSize(_))
  val Visible = SwingVar[Node, Boolean]("visible", _.isVisible, _.setVisible(_))
  val MouseLocation = Var[(Int, Int)]("mouseLocation")

  given ops: (v: Node) extended with {
    def alignmentX = v.getAlignmentX
    def alignmentY = v.getAlignmentY
    def background = Node.Background.forInstance(v)
    def bounds = Node.Bounds.forInstance(v)
    def componentOrientation = Node.ComponentOrientation.forInstance(v)
    def cursor = Node.Cursor.forInstance(v)
    def enabled = Node.Enabled.forInstance(v)
    def focusable = Node.Focusable.forInstance(v)
    def font = Node.Font.forInstance(v)
    def foreground = Node.Foreground.forInstance(v)
    def visible = Node.Visible.forInstance(v)

    def insets = v.getInsets
    def location = v.getLocation
    def maximumSize = Node.MaximumSize.forInstance(v)
    def minimumSize = Node.MinimumSize.forInstance(v)
    def mouseLocation = Node.MouseLocation.asObsValIn(v)
    def prefSize = Node.PreferredSize.forInstance(v)
    def size = v.getSize
    def unwrap: Container = v

    def location(x: Int, y: Int) = v.setLocation(x, y)
    def size(x: Int, y: Int) = v.setSize(x, y)
  }

  def uninitialized(): Node = Container()

  /** In order for swing components to work properly, they'll need to register listeners that'll trigger changes in the scenegraph
    * so this initialization step is mandatory for things to work properly
    */
  def init(n: Node) = (given sc: Scenegraph) => {
    sc.update(Node.MouseLocation.forInstance(n) := (0, 0))
    n addMouseMotionListener new MouseMotionListener {
      def mouseDragged(evt: MouseEvent) = ()
      def mouseMoved(evt: MouseEvent) = sc.update {
        Node.MouseLocation.forInstance(n) := (evt.getX, evt.getY)
      }
    }
  }

  def apply(c: Container): Node = c

  def apply(
    background: Opt[Binding[Color]] = UnsetParam,
    bounds: Opt[Binding[Rectangle]] = UnsetParam,
    componentOrientation: Opt[Binding[ComponentOrientation]] = UnsetParam,
    cursor: Opt[Binding[Cursor]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color]] = UnsetParam,
    maximumSize: Opt[Binding[Dimension]] = UnsetParam,
    minimumSize: Opt[Binding[Dimension]] = UnsetParam,
    prefSize: Opt[Binding[Dimension]] = UnsetParam,
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
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}

opaque type Component <: Node = javax.swing.JComponent
object Component {
  val ActionMap = SwingVar[Component, javax.swing.ActionMap]("actionMap", _.getActionMap, _.setActionMap(_))
  val AlignmentX = SwingVar[Component, Float]("alignmentX", _.getAlignmentX, _.setAlignmentX(_))
  val AlignmentY = SwingVar[Component, Float]("alignmentY", _.getAlignmentY, _.setAlignmentY(_))
  val Autoscrolls = SwingVar[Component, Boolean]("autoscrolls", _.getAutoscrolls, _.setAutoscrolls(_))
  val Border = SwingVar[Component, javax.swing.border.Border]("border", _.getBorder, _.setBorder(_))
  val ComponentPopupMenu = SwingVar[Component, javax.swing.JPopupMenu]("componentPopupMenu", _.getComponentPopupMenu, _.setComponentPopupMenu(_))
  val DebugGraphicsOptions = SwingVar[Component, Int]("debugGraphicsOptions", _.getDebugGraphicsOptions, _.setDebugGraphicsOptions(_))
  val DoubleBuffered = SwingVar[Component, Boolean]("doubleBuffered", _.isDoubleBuffered, _.setDoubleBuffered(_))
  val InheritsPopupMenu = SwingVar[Component, Boolean]("inheritsPopupMenu", _.getInheritsPopupMenu, _.setInheritsPopupMenu(_))
  val InputVerifier = SwingVar[Component, javax.swing.InputVerifier]("inputVerifier", _.getInputVerifier, _.setInputVerifier(_))
  // val NextFocusableComponent = SwingVar[Component, java.awt.Component]("nextFocusableComponent", _.getNextFocusableComponent, _.setNextFocusableComponent(_))
  val Opaque = SwingVar[Component, Boolean]("opaque", _.isOpaque, _.setOpaque(_))
  val RequestFocusEnabled = SwingVar[Component, Boolean]("requestFocusEnabled", _.isRequestFocusEnabled, _.setRequestFocusEnabled(_))
  val ToolTipText = SwingVar[Component, java.lang.String]("toolTipText", _.getToolTipText, _.setToolTipText(_))
  val TransferHandler = SwingVar[Component, javax.swing.TransferHandler]("transferHandler", _.getTransferHandler, _.setTransferHandler(_))
  val VerifyInputWhenFocusTarget = SwingVar[Component, Boolean]("verifyInputWhenFocusTarget", _.getVerifyInputWhenFocusTarget, _.setVerifyInputWhenFocusTarget(_))

  def init(n: Component) = (given sc: Scenegraph) => {
    Node.init(n)
  }

  given ops: (v: Component) extended with {
    def UI = v.getUI
    def UIClassID = v.getUIClassID
    def ancestorListeners = v.getAncestorListeners
    def inputMap = v.getInputMap
    // def managingFocus = v.isManagingFocus
    def optimizedDrawingEnabled = v.isOptimizedDrawingEnabled
    def paintingForPrint = v.isPaintingForPrint
    def paintingTile = v.isPaintingTile
    def registeredKeyStrokes = v.getRegisteredKeyStrokes
    def rootPane = v.getRootPane
    def topLevelAncestor = v.getTopLevelAncestor
    def vetoableChangeListeners = v.getVetoableChangeListeners
    def visibleRect = v.getVisibleRect
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
    // def nextFocusableComponent = Component.NextFocusableComponent.forInstance(v)
    def opaque = Component.Opaque.forInstance(v)
    def requestFocusEnabled = Component.RequestFocusEnabled.forInstance(v)
    def toolTipText = Component.ToolTipText.forInstance(v)
    def transferHandler = Component.TransferHandler.forInstance(v)
    def verifyInputWhenFocusTarget = Component.VerifyInputWhenFocusTarget.forInstance(v)
    def unwrap: javax.swing.JComponent = v
  }
}
