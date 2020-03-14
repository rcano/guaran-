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

opaque type BorderPane <: Pane = javax.swing.JPanel & Pane
object BorderPane extends VarsMap {
  val Bottom: SwingVar.Aux[BorderPane, Node | Null] = SwingVar[BorderPane, Node | Null]("bottom", c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.SOUTH).asInstanceOf[Node | Null], (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.SOUTH) })
  val Center: SwingVar.Aux[BorderPane, Node | Null] = SwingVar[BorderPane, Node | Null]("center", c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.CENTER).asInstanceOf[Node | Null], (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.CENTER) })
  val Hgap: SwingVar.Aux[BorderPane, Double] = SwingVar[BorderPane, Double]("hgap", c => c.getLayout.asInstanceOf[BorderLayout].getHgap, (p, g) => p.getLayout.asInstanceOf[BorderLayout].setHgap(g.toInt))
  val Left: SwingVar.Aux[BorderPane, Node | Null] = SwingVar[BorderPane, Node | Null]("left", c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.WEST).asInstanceOf[Node | Null], (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.WEST) })
  val Right: SwingVar.Aux[BorderPane, Node | Null] = SwingVar[BorderPane, Node | Null]("right", c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.EAST).asInstanceOf[Node | Null], (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.EAST) })
  val Top: SwingVar.Aux[BorderPane, Node | Null] = SwingVar[BorderPane, Node | Null]("top", c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.NORTH).asInstanceOf[Node | Null], (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.NORTH) })
  val Vgap: SwingVar.Aux[BorderPane, Double] = SwingVar[BorderPane, Double]("vgap", c => c.getLayout.asInstanceOf[BorderLayout].getVgap, (p, g) => p.getLayout.asInstanceOf[BorderLayout].setVgap(g.toInt))

  

  extension ops on (v: BorderPane) {
    def bottom: Var.Aux[Node | Null, v.type] = BorderPane.Bottom.asInstanceOf[Var.Aux[Node | Null, v.type]]
    def center: Var.Aux[Node | Null, v.type] = BorderPane.Center.asInstanceOf[Var.Aux[Node | Null, v.type]]
    def hgap: Var.Aux[Double, v.type] = BorderPane.Hgap.asInstanceOf[Var.Aux[Double, v.type]]
    def left: Var.Aux[Node | Null, v.type] = BorderPane.Left.asInstanceOf[Var.Aux[Node | Null, v.type]]
    def right: Var.Aux[Node | Null, v.type] = BorderPane.Right.asInstanceOf[Var.Aux[Node | Null, v.type]]
    def top: Var.Aux[Node | Null, v.type] = BorderPane.Top.asInstanceOf[Var.Aux[Node | Null, v.type]]
    def vgap: Var.Aux[Double, v.type] = BorderPane.Vgap.asInstanceOf[Var.Aux[Double, v.type]]

    

    
    def unwrap: javax.swing.JPanel = v
  }

  def wrap(v: javax.swing.JPanel) = v.asInstanceOf[BorderPane]

  def init(v: BorderPane): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    Pane.init(v)
    
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(): BorderPane = {
    val res = javax.swing.JPanel().asInstanceOf[BorderPane]
    res.asInstanceOf[JPanel].setLayout(BorderLayout())
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
    bottom: Opt[Binding[Node | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    center: Opt[Binding[Node | Null]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    focusedMut: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    hgap: Opt[Binding[Double]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    left: Opt[Binding[Node | Null]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    mouseLocationMut: Opt[Binding[(Int, Int)]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    right: Opt[Binding[Node | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    top: Opt[Binding[Node | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    vgap: Opt[Binding[Double]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[BorderPane] = {
    val res = uninitialized()
    BorderPane.init(res)
    ifSet(UI, Pane.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bottom, BorderPane.ops.bottom(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(center, BorderPane.ops.center(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(focusedMut, Node.ops.focusedMut(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(hgap, BorderPane.ops.hgap(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(left, BorderPane.ops.left(res) := _)
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(mouseLocationMut, Node.ops.mouseLocationMut(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(right, BorderPane.ops.right(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(top, BorderPane.ops.top(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(vgap, BorderPane.ops.vgap(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
  
}