//AUTOGENERATED FILE, DO NOT MODIFY

package guarana.swing

import language.implicitConversions
import java.awt.{Component => _, TextComponent => _, TextField => _, _}
import java.awt.event._
import javax.swing._
import javax.swing.event._
import guarana.swing.util._
import scala.jdk.CollectionConverters._
import scala.util.chaining._

opaque type Frame <: WindowBase = javax.swing.JFrame & WindowBase
object Frame extends VarsMap {
  val ContentPane: SwingVar.Aux[Frame, java.awt.Container] = SwingVar[Frame, java.awt.Container]("contentPane", _.getContentPane().nn, _.setContentPane(_))
  val DefaultCloseOperation: SwingVar.Aux[Frame, Int] = SwingVar[Frame, Int]("defaultCloseOperation", _.getDefaultCloseOperation, _.setDefaultCloseOperation(_))
  val ExtendedState: SwingVar.Aux[Frame, Int] = SwingVar[Frame, Int]("extendedState", _.getExtendedState, _.setExtendedState(_))
  val GlassPane: SwingVar.Aux[Frame, java.awt.Component | Null] = SwingVar[Frame, java.awt.Component | Null]("glassPane", _.getGlassPane, _.setGlassPane(_))
  val LayeredPane: SwingVar.Aux[Frame, javax.swing.JLayeredPane | Null] = SwingVar[Frame, javax.swing.JLayeredPane | Null]("layeredPane", _.getLayeredPane, _.setLayeredPane(_))
  val MaximizedBounds: SwingVar.Aux[Frame, Bounds | Null] = SwingVar[Frame, Bounds | Null]("maximizedBounds", _.getMaximizedBounds, _.setMaximizedBounds(_))
  val MenuBar: SwingVar.Aux[Frame, javax.swing.JMenuBar | Null] = SwingVar[Frame, javax.swing.JMenuBar | Null]("menuBar", _.getJMenuBar, _.setJMenuBar(_))
  val Resizable: SwingVar.Aux[Frame, Boolean] = SwingVar[Frame, Boolean]("resizable", _.isResizable, _.setResizable(_))
  val State: SwingVar.Aux[Frame, Int] = SwingVar[Frame, Int]("state", _.getState, _.setState(_))
  val Title: SwingVar.Aux[Frame, java.lang.String | Null] = SwingVar[Frame, java.lang.String | Null]("title", _.getTitle, _.setTitle(_))
  val TransferHandler: SwingVar.Aux[Frame, javax.swing.TransferHandler | Null] = SwingVar[Frame, javax.swing.TransferHandler | Null]("transferHandler", _.getTransferHandler, _.setTransferHandler(_))
  val Undecorated: SwingVar.Aux[Frame, Boolean] = SwingVar[Frame, Boolean]("undecorated", _.isUndecorated, _.setUndecorated(_))

  

  extension ops on (v: Frame) {
    def contentPane: Var.Aux[java.awt.Container, v.type] = Frame.ContentPane.asInstanceOf[Var.Aux[java.awt.Container, v.type]]
    def defaultCloseOperation: Var.Aux[Int, v.type] = Frame.DefaultCloseOperation.asInstanceOf[Var.Aux[Int, v.type]]
    def extendedState: Var.Aux[Int, v.type] = Frame.ExtendedState.asInstanceOf[Var.Aux[Int, v.type]]
    def glassPane: Var.Aux[java.awt.Component | Null, v.type] = Frame.GlassPane.asInstanceOf[Var.Aux[java.awt.Component | Null, v.type]]
    def layeredPane: Var.Aux[javax.swing.JLayeredPane | Null, v.type] = Frame.LayeredPane.asInstanceOf[Var.Aux[javax.swing.JLayeredPane | Null, v.type]]
    def maximizedBounds: Var.Aux[Bounds | Null, v.type] = Frame.MaximizedBounds.asInstanceOf[Var.Aux[Bounds | Null, v.type]]
    def menuBar: Var.Aux[javax.swing.JMenuBar | Null, v.type] = Frame.MenuBar.asInstanceOf[Var.Aux[javax.swing.JMenuBar | Null, v.type]]
    def resizable: Var.Aux[Boolean, v.type] = Frame.Resizable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def state: Var.Aux[Int, v.type] = Frame.State.asInstanceOf[Var.Aux[Int, v.type]]
    def title: Var.Aux[java.lang.String | Null, v.type] = Frame.Title.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def transferHandler: Var.Aux[javax.swing.TransferHandler | Null, v.type] = Frame.TransferHandler.asInstanceOf[Var.Aux[javax.swing.TransferHandler | Null, v.type]]
    def undecorated: Var.Aux[Boolean, v.type] = Frame.Undecorated.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    def rootPane: JRootPane = v.getRootPane.nn
    def unwrap: javax.swing.JFrame = v
  }

  def wrap(v: javax.swing.JFrame) = v.asInstanceOf[Frame]

  def init(v: Frame): Scenegraph ?=> Unit = (using sc: Scenegraph) => {
    WindowBase.init(v)
    
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(gc: GraphicsConfiguration | Null = null): Frame = {
    val res = javax.swing.JFrame(gc).asInstanceOf[Frame]
    
    res
  }
  
  def apply(
    gc: GraphicsConfiguration | Null = null,
    alwaysOnTop: Opt[Binding[Boolean]] = UnsetParam,
    autoRequestFocus: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    contentPane: Opt[Binding[java.awt.Container]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    defaultCloseOperation: Opt[Binding[Int]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    extendedState: Opt[Binding[Int]] = UnsetParam,
    focusCycleRoot: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    focusableWindowState: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    glassPane: Opt[Binding[java.awt.Component | Null]] = UnsetParam,
    iconImages: Opt[Binding[Seq[java.awt.Image]]] = UnsetParam,
    layeredPane: Opt[Binding[javax.swing.JLayeredPane | Null]] = UnsetParam,
    locationByPlatform: Opt[Binding[Boolean]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    maximizedBounds: Opt[Binding[Bounds | Null]] = UnsetParam,
    menuBar: Opt[Binding[javax.swing.JMenuBar | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    modalExclusionType: Opt[Binding[java.awt.Dialog.ModalExclusionType]] = UnsetParam,
    opacity: Opt[Binding[Float]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    resizable: Opt[Binding[Boolean]] = UnsetParam,
    root: Opt[Binding[Node]] = UnsetParam,
    shape: Opt[Binding[java.awt.Shape | Null]] = UnsetParam,
    state: Opt[Binding[Int]] = UnsetParam,
    title: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tpe: Opt[Binding[java.awt.Window.Type]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    undecorated: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[Frame] = {
    val res = uninitialized()
    Frame.init(res)
    ifSet(alwaysOnTop, WindowBase.ops.alwaysOnTop(res) := _)
    ifSet(autoRequestFocus, WindowBase.ops.autoRequestFocus(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(contentPane, Frame.ops.contentPane(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(defaultCloseOperation, Frame.ops.defaultCloseOperation(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(extendedState, Frame.ops.extendedState(res) := _)
    ifSet(focusCycleRoot, WindowBase.ops.focusCycleRoot(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(focusableWindowState, WindowBase.ops.focusableWindowState(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(glassPane, Frame.ops.glassPane(res) := _)
    ifSet(iconImages, WindowBase.ops.iconImages(res) := _)
    ifSet(layeredPane, Frame.ops.layeredPane(res) := _)
    ifSet(locationByPlatform, WindowBase.ops.locationByPlatform(res) := _)
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(maximizedBounds, Frame.ops.maximizedBounds(res) := _)
    ifSet(menuBar, Frame.ops.menuBar(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(modalExclusionType, WindowBase.ops.modalExclusionType(res) := _)
    ifSet(opacity, WindowBase.ops.opacity(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(resizable, Frame.ops.resizable(res) := _)
    ifSet(root, WindowBase.ops.root(res) := _)
    ifSet(shape, WindowBase.ops.shape(res) := _)
    ifSet(state, Frame.ops.state(res) := _)
    ifSet(title, Frame.ops.title(res) := _)
    ifSet(tpe, WindowBase.ops.tpe(res) := _)
    ifSet(transferHandler, Frame.ops.transferHandler(res) := _)
    ifSet(undecorated, Frame.ops.undecorated(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
  
}