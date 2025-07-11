//AUTOGENERATED FILE, DO NOT MODIFY

package guarana
package swing

import language.implicitConversions
import java.awt.{Component as _, Menu as _, MenuBar as _, MenuItem as _, TextComponent as _, TextField as _, PopupMenu as _, *}
import javax.swing.{Action as _, *}
import guarana.util.*

opaque type Window <: WindowBase  = javax.swing.JWindow & WindowBase
object Window extends VarsMap {
  val ContentPane: SwingVar.Aux[Window, java.awt.Container] = SwingVar[Window, java.awt.Container]("contentPane", _.getContentPane().nn, _.setContentPane(_))
  val GlassPane: SwingVar.Aux[Window, java.awt.Component | Null] = SwingVar[Window, java.awt.Component | Null]("glassPane", _.getGlassPane, _.setGlassPane(_))
  val LayeredPane: SwingVar.Aux[Window, javax.swing.JLayeredPane | Null] = SwingVar[Window, javax.swing.JLayeredPane | Null]("layeredPane", _.getLayeredPane, _.setLayeredPane(_))
  val TransferHandler: SwingVar.Aux[Window, javax.swing.TransferHandler | Null] = SwingVar[Window, javax.swing.TransferHandler | Null]("transferHandler", _.getTransferHandler, _.setTransferHandler(_))

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: Window) {
      def contentPane: Var.Aux[java.awt.Container, v.type] = Window.ContentPane.asInstanceOf[Var.Aux[java.awt.Container, v.type]]
      def glassPane: Var.Aux[java.awt.Component | Null, v.type] = Window.GlassPane.asInstanceOf[Var.Aux[java.awt.Component | Null, v.type]]
      def layeredPane: Var.Aux[javax.swing.JLayeredPane | Null, v.type] = Window.LayeredPane.asInstanceOf[Var.Aux[javax.swing.JLayeredPane | Null, v.type]]
      def transferHandler: Var.Aux[javax.swing.TransferHandler | Null, v.type] = Window.TransferHandler.asInstanceOf[Var.Aux[javax.swing.TransferHandler | Null, v.type]]

      

      def rootPane: JRootPane = v.getRootPane.nn
      def unwrap: javax.swing.JWindow = v
    }
  }

  def wrap(v: javax.swing.JWindow) = v.asInstanceOf[Window]

  def init(v: Window): Scenegraph ?=> Unit = (sc: Scenegraph) ?=> {
    WindowBase.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(parent: WindowBase | Null = null, gc: GraphicsConfiguration | Null = null): Window = {
    val res = javax.swing.JWindow(parent.?(_.unwrap), gc).asInstanceOf[Window]
    
    res
  }
  
  def apply(
    parent: WindowBase | Null = null, gc: GraphicsConfiguration | Null = null,
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
    name: Opt[Binding[String | Null]] = UnsetParam,
    opacity: Opt[Binding[Float]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    root: Opt[Binding[Node]] = UnsetParam,
    shape: Opt[Binding[java.awt.Shape | Null]] = UnsetParam,
    tpe: Opt[Binding[java.awt.Window.Type]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[Window] = {
    val res = uninitialized(parent, gc)
    Window.init(res)
    ifSet(alwaysOnTop, WindowBase.ops.alwaysOnTop(res) := _)
    ifSet(autoRequestFocus, WindowBase.ops.autoRequestFocus(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(contentPane, Window.ops.contentPane(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusCycleRoot, WindowBase.ops.focusCycleRoot(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(focusableWindowState, WindowBase.ops.focusableWindowState(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(glassPane, Window.ops.glassPane(res) := _)
    ifSet(iconImages, WindowBase.ops.iconImages(res) := _)
    ifSet(layeredPane, Window.ops.layeredPane(res) := _)
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
    ifSet(transferHandler, Window.ops.transferHandler(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
  
}