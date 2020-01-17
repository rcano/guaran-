package guarana.swing

import language.implicitConversions
import java.awt.{Container, ComponentOrientation, Color, Cursor, Dimension, GraphicsConfiguration, Rectangle}
import javax.swing._
import guarana.swing.util._

opaque type Window <: Node = java.awt.Window & Node
object Window {

  val AlwaysOnTop = SwingVar[Window, Boolean]("alwaysOnTop", _.isAlwaysOnTop, _.setAlwaysOnTop(_))
  val AutoRequestFocus = SwingVar[Window, Boolean]("autoRequestFocus", _.isAutoRequestFocus, _.setAutoRequestFocus(_))
  val FocusCycleRoot = SwingVar[Window, Boolean]("focusCycleRoot", _.isFocusCycleRoot, _.setFocusCycleRoot(_))
  val FocusableWindowState = SwingVar[Window, Boolean]("focusableWindowState", _.getFocusableWindowState, _.setFocusableWindowState(_))
  val IconImages = SwingVar[Window, java.util.List[_ <: java.awt.Image]]("iconImages", _.getIconImages, _.setIconImages(_))
  val LocationByPlatform = SwingVar[Window, Boolean]("locationByPlatform", _.isLocationByPlatform, _.setLocationByPlatform(_))
  val ModalExclusionType = SwingVar[Window, java.awt.Dialog.ModalExclusionType]("modalExclusionType", _.getModalExclusionType, _.setModalExclusionType(_))
  val Opacity = SwingVar[Window, Float]("opacity", _.getOpacity, _.setOpacity(_))
  val Shape = SwingVar[Window, java.awt.Shape]("shape", _.getShape, _.setShape(_))
  val Type = SwingVar[Window, java.awt.Window.Type]("type", _.getType, _.setType(_))
  val Visible = SwingVar[Window, Boolean]("visible", _.isVisible, _.setVisible(_))
  val Root = SwingVar[Window, Node]("root", c => Node(c.getComponent(0).asInstanceOf[Container]), (w, n) => w.add(n.unwrap, 0))

  given ops: (v: Window) extended with {
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
    def showing = v.isShowing
    def toolkit = v.getToolkit
    def validateRoot = v.isValidateRoot
    def warningString = v.getWarningString
    def windowFocusListeners = v.getWindowFocusListeners
    def windowListeners = v.getWindowListeners
    def windowStateListeners = v.getWindowStateListeners
    def alwaysOnTop = Window.AlwaysOnTop.forInstance(v)
    def autoRequestFocus = Window.AutoRequestFocus.forInstance(v)
    def focusCycleRoot = Window.FocusCycleRoot.forInstance(v)
    def focusableWindowState = Window.FocusableWindowState.forInstance(v)
    def iconImages = Window.IconImages.forInstance(v)
    def locationByPlatform = Window.LocationByPlatform.forInstance(v)
    def modalExclusionType = Window.ModalExclusionType.forInstance(v)
    def opacity = Window.Opacity.forInstance(v)
    def root = Window.Root.forInstance(v)
    def shape = Window.Shape.forInstance(v)
    def tpe = Window.Type.forInstance(v)
    def visible = Window.Visible.forInstance(v)
    def unwrap: java.awt.Window = v
  } 

  def uninitialized(parent: java.awt.Window = null, gc: GraphicsConfiguration = null): Window = java.awt.Window(parent, gc).asInstanceOf[Window]

  def apply(
    parent: java.awt.Window = null, gc: GraphicsConfiguration = null,
    alwaysOnTop: Opt[Binding[Boolean]] = UnsetParam,
    autoRequestFocus: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color]] = UnsetParam,
    focusCycleRoot: Opt[Binding[Boolean]] = UnsetParam,
    focusableWindowState: Opt[Binding[Boolean]] = UnsetParam,
    iconImages: Opt[Binding[java.util.List[_ <: java.awt.Image]]] = UnsetParam,
    locationByPlatform: Opt[Binding[Boolean]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension]] = UnsetParam,
    modalExclusionType: Opt[Binding[java.awt.Dialog.ModalExclusionType]] = UnsetParam,
    opacity: Opt[Binding[Float]] = UnsetParam,
    root: Node = Node.uninitialized(),
    shape: Opt[Binding[java.awt.Shape]] = UnsetParam,
    tpe: Opt[Binding[java.awt.Window.Type]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    bounds: Opt[Binding[Rectangle]] = UnsetParam,
    componentOrientation: Opt[Binding[ComponentOrientation]] = UnsetParam,
    cursor: Opt[Binding[Cursor]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    maximumSize: Opt[Binding[Dimension]] = UnsetParam,
    prefSize: Opt[Binding[Dimension]] = UnsetParam,
  ): (given Scenegraph) => VarContextAction[Window] = {
    val res = uninitialized(parent, gc)
    Node.init(res)
    ifSet(alwaysOnTop, Window.ops.alwaysOnTop(res) := _)
    ifSet(autoRequestFocus, Window.ops.autoRequestFocus(res) := _)
    ifSet(focusCycleRoot, Window.ops.focusCycleRoot(res) := _)
    ifSet(focusableWindowState, Window.ops.focusableWindowState(res) := _)
    ifSet(iconImages, Window.ops.iconImages(res) := _)
    ifSet(locationByPlatform, Window.ops.locationByPlatform(res) := _)
    ifSet(modalExclusionType, Window.ops.modalExclusionType(res) := _)
    ifSet(opacity, Window.ops.opacity(res) := _)
    Window.ops.root(res) := root
    ifSet(shape, Window.ops.shape(res) := _)
    ifSet(tpe, Window.ops.tpe(res) := _)
    ifSet(visible, Window.ops.visible(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    res
  }

}

opaque type Frame <: Node & Window = java.awt.Frame & Node
object Frame {
  val ExtendedState = SwingVar[Frame, Int]("extendedState", _.getExtendedState, _.setExtendedState(_))
  val IconImage = SwingVar[Frame, java.awt.Image]("iconImage", _.getIconImage, _.setIconImage(_))
  val MaximizedBounds = SwingVar[Frame, java.awt.Rectangle]("maximizedBounds", _.getMaximizedBounds, _.setMaximizedBounds(_))
  val MenuBar = SwingVar[Frame, java.awt.MenuBar]("menuBar", _.getMenuBar, _.setMenuBar(_))
  val Resizable = SwingVar[Frame, Boolean]("resizable", _.isResizable, _.setResizable(_))
  val State = SwingVar[Frame, Int]("state", _.getState, _.setState(_))
  val Title = SwingVar[Frame, java.lang.String]("title", _.getTitle, _.setTitle(_))
  val Undecorated = SwingVar[Frame, Boolean]("undecorated", _.isUndecorated, _.setUndecorated(_))

  given ops: (v: Frame) extended with {
    def accessibleContext = v.getAccessibleContext
    def extendedState = Frame.ExtendedState.forInstance(v)
    def iconImage = Frame.IconImage.forInstance(v)
    def maximizedBounds = Frame.MaximizedBounds.forInstance(v)
    def menuBar = Frame.MenuBar.forInstance(v)
    def resizable = Frame.Resizable.forInstance(v)
    def state = Frame.State.forInstance(v)
    def title = Frame.Title.forInstance(v)
    def undecorated = Frame.Undecorated.forInstance(v)
    def unwrap: java.awt.Frame = v
  }

  def uninitialized(title: String = null, gc: GraphicsConfiguration = null): Frame = java.awt.Frame(title, gc).asInstanceOf[Frame]

  def apply(
    gc: GraphicsConfiguration = null,
    alwaysOnTop: Opt[Binding[Boolean]] = UnsetParam,
    autoRequestFocus: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color]] = UnsetParam,
    focusCycleRoot: Opt[Binding[Boolean]] = UnsetParam,
    focusableWindowState: Opt[Binding[Boolean]] = UnsetParam,
    iconImages: Opt[Binding[java.util.List[_ <: java.awt.Image]]] = UnsetParam,
    locationByPlatform: Opt[Binding[Boolean]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension]] = UnsetParam,
    modalExclusionType: Opt[Binding[java.awt.Dialog.ModalExclusionType]] = UnsetParam,
    opacity: Opt[Binding[Float]] = UnsetParam,
    shape: Opt[Binding[java.awt.Shape]] = UnsetParam,
    root: Node = Node.uninitialized(),
    tpe: Opt[Binding[java.awt.Window.Type]] = UnsetParam,
    visible: Opt[Boolean] = UnsetParam,
    bounds: Opt[Binding[Rectangle]] = UnsetParam,
    componentOrientation: Opt[Binding[ComponentOrientation]] = UnsetParam,
    cursor: Opt[Binding[Cursor]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    maximumSize: Opt[Binding[Dimension]] = UnsetParam,
    prefSize: Opt[Binding[Dimension]] = UnsetParam,
    extendedState: Opt[Binding[Int]] = UnsetParam,
    iconImage: Opt[Binding[java.awt.Image]] = UnsetParam,
    maximizedBounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
    menuBar: Opt[Binding[java.awt.MenuBar]] = UnsetParam,
    resizable: Opt[Binding[Boolean]] = UnsetParam,
    state: Opt[Binding[Int]] = UnsetParam,
    title: Opt[Binding[java.lang.String]] = UnsetParam,
    undecorated: Opt[Boolean] = UnsetParam
  ): (given Scenegraph) => VarContextAction[Frame] = {
    val res = uninitialized(gc = gc)
    Node.init(res)
    ifSet(alwaysOnTop, Window.ops.alwaysOnTop(res) := _)
    ifSet(autoRequestFocus, Window.ops.autoRequestFocus(res) := _)
    ifSet(focusCycleRoot, Window.ops.focusCycleRoot(res) := _)
    ifSet(focusableWindowState, Window.ops.focusableWindowState(res) := _)
    ifSet(iconImages, Window.ops.iconImages(res) := _)
    ifSet(locationByPlatform, Window.ops.locationByPlatform(res) := _)
    ifSet(modalExclusionType, Window.ops.modalExclusionType(res) := _)
    ifSet(opacity, Window.ops.opacity(res) := _)
    Window.ops.root(res) := root
    ifSet(shape, Window.ops.shape(res) := _)
    ifSet(tpe, Window.ops.tpe(res) := _)
    ifSet(visible, Window.ops.visible(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(extendedState, Frame.ops.extendedState(res) := _)
    ifSet(iconImage, Frame.ops.iconImage(res) := _)
    ifSet(maximizedBounds, Frame.ops.maximizedBounds(res) := _)
    ifSet(menuBar, Frame.ops.menuBar(res) := _)
    ifSet(resizable, Frame.ops.resizable(res) := _)
    ifSet(state, Frame.ops.state(res) := _)
    ifSet(title, Frame.ops.title(res) := _)
    ifSet(undecorated, Frame.ops.undecorated(res) := _)
    res
  }
}