//AUTOGENERATED FILE, DO NOT MODIFY

package guarana
package swing

import language.implicitConversions
import java.awt.{Component as _, Menu as _, MenuBar as _, MenuItem as _, TextComponent as _, TextField as _, PopupMenu as _, *}
import javax.swing.Action as _
import guarana.util.*
import scala.jdk.CollectionConverters.*

opaque type WindowBase <: Node  = java.awt.Window & Node
object WindowBase extends VarsMap {
  val AlwaysOnTop: SwingVar.Aux[WindowBase, Boolean] = SwingVar[WindowBase, Boolean]("alwaysOnTop", _.isAlwaysOnTop, _.setAlwaysOnTop(_))
  val AutoRequestFocus: SwingVar.Aux[WindowBase, Boolean] = SwingVar[WindowBase, Boolean]("autoRequestFocus", _.isAutoRequestFocus, _.setAutoRequestFocus(_))
  val FocusCycleRoot: SwingVar.Aux[WindowBase, Boolean] = SwingVar[WindowBase, Boolean]("focusCycleRoot", _.isFocusCycleRoot, _.setFocusCycleRoot(_))
  val FocusableWindowState: SwingVar.Aux[WindowBase, Boolean] = SwingVar[WindowBase, Boolean]("focusableWindowState", _.getFocusableWindowState, _.setFocusableWindowState(_))
  val IconImages: SwingVar.Aux[WindowBase, Seq[java.awt.Image]] = SwingVar[WindowBase, Seq[java.awt.Image]]("iconImages", _.getIconImages.nn.asScala.toSeq, (w, l) => w.setIconImages(l.asJava))
  val LocationByPlatform: SwingVar.Aux[WindowBase, Boolean] = SwingVar[WindowBase, Boolean]("locationByPlatform", _.isLocationByPlatform, _.setLocationByPlatform(_))
  val ModalExclusionType: SwingVar.Aux[WindowBase, java.awt.Dialog.ModalExclusionType] = SwingVar[WindowBase, java.awt.Dialog.ModalExclusionType]("modalExclusionType", _.getModalExclusionType.nn, _.setModalExclusionType(_))
  val Opacity: SwingVar.Aux[WindowBase, Float] = SwingVar[WindowBase, Float]("opacity", _.getOpacity, _.setOpacity(_))
  val Root: SwingVar.Aux[WindowBase, Node] = SwingVar[WindowBase, Node]("root", c => Node.wrap(c.getComponent(0).asInstanceOf[Container]), (w, n) => w.add(n.unwrap, 0))
  val Shape: SwingVar.Aux[WindowBase, java.awt.Shape | Null] = SwingVar[WindowBase, java.awt.Shape | Null]("shape", _.getShape, _.setShape(_))
  val Tpe: SwingVar.Aux[WindowBase, java.awt.Window.Type] = SwingVar[WindowBase, java.awt.Window.Type]("tpe", _.getType.nn, _.setType(_))

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: WindowBase) {
      def alwaysOnTop: Var.Aux[Boolean, v.type] = WindowBase.AlwaysOnTop.asInstanceOf[Var.Aux[Boolean, v.type]]
      def autoRequestFocus: Var.Aux[Boolean, v.type] = WindowBase.AutoRequestFocus.asInstanceOf[Var.Aux[Boolean, v.type]]
      def focusCycleRoot: Var.Aux[Boolean, v.type] = WindowBase.FocusCycleRoot.asInstanceOf[Var.Aux[Boolean, v.type]]
      def focusableWindowState: Var.Aux[Boolean, v.type] = WindowBase.FocusableWindowState.asInstanceOf[Var.Aux[Boolean, v.type]]
      def iconImages: Var.Aux[Seq[java.awt.Image], v.type] = WindowBase.IconImages.asInstanceOf[Var.Aux[Seq[java.awt.Image], v.type]]
      def locationByPlatform: Var.Aux[Boolean, v.type] = WindowBase.LocationByPlatform.asInstanceOf[Var.Aux[Boolean, v.type]]
      def modalExclusionType: Var.Aux[java.awt.Dialog.ModalExclusionType, v.type] = WindowBase.ModalExclusionType.asInstanceOf[Var.Aux[java.awt.Dialog.ModalExclusionType, v.type]]
      def opacity: Var.Aux[Float, v.type] = WindowBase.Opacity.asInstanceOf[Var.Aux[Float, v.type]]
      def root: Var.Aux[Node, v.type] = WindowBase.Root.asInstanceOf[Var.Aux[Node, v.type]]
      def shape: Var.Aux[java.awt.Shape | Null, v.type] = WindowBase.Shape.asInstanceOf[Var.Aux[java.awt.Shape | Null, v.type]]
      def tpe: Var.Aux[java.awt.Window.Type, v.type] = WindowBase.Tpe.asInstanceOf[Var.Aux[java.awt.Window.Type, v.type]]

      

      def accessibleContext = v.getAccessibleContext
      def active = v.isActive
      def alwaysOnTopSupported = v.isAlwaysOnTopSupported
      def bufferStrategy = v.getBufferStrategy
      def focusCycleRootAncestor = v.getFocusCycleRootAncestor
      def focusOwner = v.getFocusOwner
      def focusableWindow = v.isFocusableWindow
      def focused = v.isFocused
      def inputContext = v.getInputContext
      def locale = v.getLocale
      def mostRecentFocusOwner = v.getMostRecentFocusOwner
      def opaque = v.isOpaque
      def ownedWindows = v.getOwnedWindows
      def owner = v.getOwner
      def pack() = v.pack()
      def showing = v.isShowing
      def toFront() = v.toFront()
      def toBack() = v.toBack()
      def toolkit = v.getToolkit
      def validateRoot = v.isValidateRoot
      def warningString = v.getWarningString
      def windowFocusListeners = v.getWindowFocusListeners
      def windowListeners = v.getWindowListeners
      def windowStateListeners = v.getWindowStateListeners
      def unwrap: java.awt.Window = v
    }
  }

  def wrap(v: java.awt.Window) = v.asInstanceOf[WindowBase]

  def init(v: WindowBase): Scenegraph ?=> Unit = (sc: Scenegraph) ?=> {
    Node.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(parent: WindowBase | Null = null, gc: GraphicsConfiguration | Null = null): WindowBase = {
    val res = java.awt.Window(parent, gc).asInstanceOf[WindowBase]
    
    res
  }
  
  def apply(
    parent: WindowBase | Null = null, gc: GraphicsConfiguration | Null = null,
    alwaysOnTop: Opt[Binding[Boolean]] = UnsetParam,
    autoRequestFocus: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusCycleRoot: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    focusableWindowState: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    iconImages: Opt[Binding[Seq[java.awt.Image]]] = UnsetParam,
    locationByPlatform: Opt[Binding[Boolean]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    modalExclusionType: Opt[Binding[java.awt.Dialog.ModalExclusionType]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    opacity: Opt[Binding[Float]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    root: Opt[Binding[Node]] = UnsetParam,
    shape: Opt[Binding[java.awt.Shape | Null]] = UnsetParam,
    tpe: Opt[Binding[java.awt.Window.Type]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[WindowBase] = {
    val res = uninitialized(parent, gc)
    WindowBase.init(res)
    ifSet(alwaysOnTop, WindowBase.ops.alwaysOnTop(res) := _)
    ifSet(autoRequestFocus, WindowBase.ops.autoRequestFocus(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusCycleRoot, WindowBase.ops.focusCycleRoot(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(focusableWindowState, WindowBase.ops.focusableWindowState(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(iconImages, WindowBase.ops.iconImages(res) := _)
    ifSet(locationByPlatform, WindowBase.ops.locationByPlatform(res) := _)
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(modalExclusionType, WindowBase.ops.modalExclusionType(res) := _)
    ifSet(name, Node.ops.name(res) := _)
    ifSet(opacity, WindowBase.ops.opacity(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(root, WindowBase.ops.root(res) := _)
    ifSet(shape, WindowBase.ops.shape(res) := _)
    ifSet(tpe, WindowBase.ops.tpe(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
  
}