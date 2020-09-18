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

opaque type Window <: WindowBase = javax.swing.JWindow & WindowBase
object Window extends VarsMap {
  val ContentPane: SwingVar.Aux[Window, java.awt.Container] = SwingVar[Window, java.awt.Container]("contentPane", _.getContentPane().nn, _.setContentPane(_))
  val GlassPane: SwingVar.Aux[Window, java.awt.Component | Null] = SwingVar[Window, java.awt.Component | Null]("glassPane", _.getGlassPane, _.setGlassPane(_))
  val LayeredPane: SwingVar.Aux[Window, javax.swing.JLayeredPane | Null] = SwingVar[Window, javax.swing.JLayeredPane | Null]("layeredPane", _.getLayeredPane, _.setLayeredPane(_))
  val TransferHandler: SwingVar.Aux[Window, javax.swing.TransferHandler | Null] = SwingVar[Window, javax.swing.TransferHandler | Null]("transferHandler", _.getTransferHandler, _.setTransferHandler(_))

  

  extension ops on (v: Window) {
    def contentPane: Var.Aux[java.awt.Container, v.type] = Window.ContentPane.asInstanceOf[Var.Aux[java.awt.Container, v.type]]
    def glassPane: Var.Aux[java.awt.Component | Null, v.type] = Window.GlassPane.asInstanceOf[Var.Aux[java.awt.Component | Null, v.type]]
    def layeredPane: Var.Aux[javax.swing.JLayeredPane | Null, v.type] = Window.LayeredPane.asInstanceOf[Var.Aux[javax.swing.JLayeredPane | Null, v.type]]
    def transferHandler: Var.Aux[javax.swing.TransferHandler | Null, v.type] = Window.TransferHandler.asInstanceOf[Var.Aux[javax.swing.TransferHandler | Null, v.type]]

    

    def rootPane: JRootPane = v.getRootPane.nn
    def unwrap: javax.swing.JWindow = v
  }

  def wrap(v: javax.swing.JWindow) = v.asInstanceOf[Window]

  def init(v: Window): Scenegraph ?=> Unit = (using sc: Scenegraph) => {
    WindowBase.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(gc: GraphicsConfiguration | Null = null): Window = {
    val res = javax.swing.JWindow(gc).asInstanceOf[Window]
    
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
    enabled: Opt[Binding[Boolean]] = UnsetParam,
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
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    modalExclusionType: Opt[Binding[java.awt.Dialog.ModalExclusionType]] = UnsetParam,
    mouseDragMut: Opt[Binding[Option[MouseDrag]]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    opacity: Opt[Binding[Float]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    root: Opt[Binding[Node]] = UnsetParam,
    shape: Opt[Binding[java.awt.Shape | Null]] = UnsetParam,
    tpe: Opt[Binding[java.awt.Window.Type]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[Window] = {
    val res = uninitialized()
    Window.init(res)
    ifSet(alwaysOnTop, WindowBase.ops.extension_alwaysOnTop(res) := _)
    ifSet(autoRequestFocus, WindowBase.ops.extension_autoRequestFocus(res) := _)
    ifSet(background, Node.ops.extension_background(res) := _)
    ifSet(bounds, Node.ops.extension_bounds(res) := _)
    ifSet(componentOrientation, Node.ops.extension_componentOrientation(res) := _)
    ifSet(contentPane, Window.ops.extension_contentPane(res) := _)
    ifSet(cursor, Node.ops.extension_cursor(res) := _)
    ifSet(enabled, Node.ops.extension_enabled(res) := _)
    ifSet(focusCycleRoot, WindowBase.ops.extension_focusCycleRoot(res) := _)
    ifSet(focusable, Node.ops.extension_focusable(res) := _)
    ifSet(focusableWindowState, WindowBase.ops.extension_focusableWindowState(res) := _)
    ifSet(font, Node.ops.extension_font(res) := _)
    ifSet(foreground, Node.ops.extension_foreground(res) := _)
    ifSet(glassPane, Window.ops.extension_glassPane(res) := _)
    ifSet(iconImages, WindowBase.ops.extension_iconImages(res) := _)
    ifSet(layeredPane, Window.ops.extension_layeredPane(res) := _)
    ifSet(locationByPlatform, WindowBase.ops.extension_locationByPlatform(res) := _)
    ifSet(maxSize, Node.ops.extension_maxSize(res) := _)
    ifSet(minSize, Node.ops.extension_minSize(res) := _)
    ifSet(modalExclusionType, WindowBase.ops.extension_modalExclusionType(res) := _)
    ifSet(mouseDragMut, Node.ops.extension_mouseDragMut(res) := _)
    ifSet(name, Node.ops.extension_name(res) := _)
    ifSet(opacity, WindowBase.ops.extension_opacity(res) := _)
    ifSet(prefSize, Node.ops.extension_prefSize(res) := _)
    ifSet(root, WindowBase.ops.extension_root(res) := _)
    ifSet(shape, WindowBase.ops.extension_shape(res) := _)
    ifSet(tpe, WindowBase.ops.extension_tpe(res) := _)
    ifSet(transferHandler, Window.ops.extension_transferHandler(res) := _)
    ifSet(visible, Node.ops.extension_visible(res) := _)
    res
  }
  
}