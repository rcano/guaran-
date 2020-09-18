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

opaque type Hbox <: Pane = javax.swing.JPanel & Pane
object Hbox extends VarsMap {
  val Nodes: SwingVar.Aux[Hbox, Seq[Node]] = SwingVar[Hbox, Seq[Node]]("nodes", c => (0 until c.getComponentCount).map(c.getComponent(_).asInstanceOf[Node]), (p, children) => { p.removeAll(); children foreach (n => p.add(n.unwrap)) })

  

  extension ops on (v: Hbox) {
    def nodes: Var.Aux[Seq[Node], v.type] = Hbox.Nodes.asInstanceOf[Var.Aux[Seq[Node], v.type]]

    

    
    def unwrap: javax.swing.JPanel = v
  }

  def wrap(v: javax.swing.JPanel) = v.asInstanceOf[Hbox]

  def init(v: Hbox): Scenegraph ?=> Unit = (using sc: Scenegraph) => {
    Pane.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(): Hbox = {
    val res = javax.swing.JPanel().asInstanceOf[Hbox]
    res.asInstanceOf[JPanel].setLayout(BoxLayout(res, BoxLayout.X_AXIS))
    res
  }
  
  def apply(
    
    UI: Opt[Binding[javax.swing.plaf.PanelUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    mouseDragMut: Opt[Binding[Option[MouseDrag]]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    nodes: Opt[Binding[Seq[Node]]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[Hbox] = {
    val res = uninitialized()
    Hbox.init(res)
    ifSet(UI, Pane.ops.extension_UI(res) := _)
    ifSet(actionMap, Component.ops.extension_actionMap(res) := _)
    ifSet(alignmentX, Component.ops.extension_alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.extension_alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.extension_autoscrolls(res) := _)
    ifSet(background, Node.ops.extension_background(res) := _)
    ifSet(border, Component.ops.extension_border(res) := _)
    ifSet(bounds, Node.ops.extension_bounds(res) := _)
    ifSet(componentOrientation, Node.ops.extension_componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.extension_componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.extension_cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.extension_debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.extension_doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.extension_enabled(res) := _)
    ifSet(focusable, Node.ops.extension_focusable(res) := _)
    ifSet(font, Node.ops.extension_font(res) := _)
    ifSet(foreground, Node.ops.extension_foreground(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.extension_inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.extension_inputVerifier(res) := _)
    ifSet(maxSize, Node.ops.extension_maxSize(res) := _)
    ifSet(minSize, Node.ops.extension_minSize(res) := _)
    ifSet(mouseDragMut, Node.ops.extension_mouseDragMut(res) := _)
    ifSet(name, Node.ops.extension_name(res) := _)
    ifSet(nodes, Hbox.ops.extension_nodes(res) := _)
    ifSet(opaque, Component.ops.extension_opaque(res) := _)
    ifSet(prefSize, Node.ops.extension_prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.extension_requestFocusEnabled(res) := _)
    ifSet(toolTipText, Component.ops.extension_toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.extension_transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.extension_verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.extension_visible(res) := _)
    res
  }
  
}