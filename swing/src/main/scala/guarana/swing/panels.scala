package guarana.swing

import language.implicitConversions
import java.awt.{BorderLayout, Dimension}
import javax.swing._
import guarana.swing.util._
import scala.util.chaining._

opaque type Pane <: Component = javax.swing.JPanel & Component
object Pane extends VarsMap {
  val UI = SwingVar[Pane, javax.swing.plaf.PanelUI]("UI", _.getUI.nn, _.setUI(_))

  given ops: (v: Pane) extended with {
    def UI = Pane.UI.forInstance(v)
    
    def unwrap: javax.swing.JPanel = v
  }

  def apply(v: javax.swing.JPanel) = v.asInstanceOf[Pane]

  def init(v: Pane): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
  }
  def uninitialized(): Pane = {
    val res = javax.swing.JPanel().asInstanceOf[Pane]
    
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
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
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
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[Pane] = {
    val res = uninitialized()
    Pane.init(res)
    ifSet(UI, Pane.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}


opaque type AbsolutePositioningPane <: Pane = javax.swing.JPanel & Pane
object AbsolutePositioningPane extends VarsMap {
  val Nodes = SwingVar[AbsolutePositioningPane, Seq[Node]]("nodes", c => (0 until c.getComponentCount).map(c.getComponent(_).asInstanceOf[Node]), (p, children) => { p.removeAll(); children foreach (n => p.add(n.unwrap)) })

  given ops: (v: AbsolutePositioningPane) extended with {
    def nodes = AbsolutePositioningPane.Nodes.forInstance(v)
    
    def unwrap: javax.swing.JPanel = v
  }

  def apply(v: javax.swing.JPanel) = v.asInstanceOf[AbsolutePositioningPane]

  def init(v: AbsolutePositioningPane): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    Pane.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
  }
  def uninitialized(): AbsolutePositioningPane = {
    val res = javax.swing.JPanel().asInstanceOf[AbsolutePositioningPane]
    res.asInstanceOf[JPanel].setLayout(null)
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
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
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
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    nodes: Opt[Binding[Seq[Node]]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[AbsolutePositioningPane] = {
    val res = uninitialized()
    AbsolutePositioningPane.init(res)
    ifSet(UI, Pane.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(nodes, AbsolutePositioningPane.ops.nodes(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}


opaque type BorderPane <: Pane = javax.swing.JPanel & Pane
object BorderPane extends VarsMap {
  val Bottom = SwingVar[BorderPane, Node | Null]("bottom", c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.SOUTH).asInstanceOf[Node | Null], (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.SOUTH) })
  val Center = SwingVar[BorderPane, Node | Null]("center", c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.CENTER).asInstanceOf[Node | Null], (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.CENTER) })
  val Hgap = SwingVar[BorderPane, Int]("hgap", c => c.getLayout.asInstanceOf[BorderLayout].getHgap, (p, g) => p.getLayout.asInstanceOf[BorderLayout].setHgap(g))
  val Left = SwingVar[BorderPane, Node | Null]("left", c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.WEST).asInstanceOf[Node | Null], (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.WEST) })
  val Right = SwingVar[BorderPane, Node | Null]("right", c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.EAST).asInstanceOf[Node | Null], (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.EAST) })
  val Top = SwingVar[BorderPane, Node | Null]("top", c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.NORTH).asInstanceOf[Node | Null], (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.NORTH) })
  val Vgap = SwingVar[BorderPane, Int]("vgap", c => c.getLayout.asInstanceOf[BorderLayout].getVgap, (p, g) => p.getLayout.asInstanceOf[BorderLayout].setVgap(g))

  given ops: (v: BorderPane) extended with {
    def bottom = BorderPane.Bottom.forInstance(v)
    def center = BorderPane.Center.forInstance(v)
    def hgap = BorderPane.Hgap.forInstance(v)
    def left = BorderPane.Left.forInstance(v)
    def right = BorderPane.Right.forInstance(v)
    def top = BorderPane.Top.forInstance(v)
    def vgap = BorderPane.Vgap.forInstance(v)
    
    def unwrap: javax.swing.JPanel = v
  }

  def apply(v: javax.swing.JPanel) = v.asInstanceOf[BorderPane]

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
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
    center: Opt[Binding[Node | Null]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    hgap: Opt[Binding[Int]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    left: Opt[Binding[Node | Null]] = UnsetParam,
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    right: Opt[Binding[Node | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    top: Opt[Binding[Node | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    vgap: Opt[Binding[Int]] = UnsetParam,
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
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(hgap, BorderPane.ops.hgap(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(left, BorderPane.ops.left(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
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


opaque type GridPane <: Pane = javax.swing.JPanel & Pane
object GridPane extends VarsMap {
  val AutoCreateContainerGaps = SwingVar[GridPane, Boolean]("autoCreateContainerGaps", _.getLayout.asInstanceOf[GroupLayout].getAutoCreateContainerGaps(), _.getLayout.asInstanceOf[GroupLayout].setAutoCreateContainerGaps(_))
  val Hgap = Var[Int]("hgap", 0)
  val LayoutVar = SwingVar[GridPane, Unit]("layoutVar", _ => (), (_, _) => ())
  val Rows = Var[Seq[Seq[Node]]]("rows", Seq.empty)
  val Vgap = Var[Int]("vgap", 0)

  given ops: (v: GridPane) extended with {
    def autoCreateContainerGaps = GridPane.AutoCreateContainerGaps.forInstance(v)
    def hgap = GridPane.Hgap.forInstance(v)
    def layoutVar = GridPane.LayoutVar.forInstance(v)
    def rows = GridPane.Rows.forInstance(v)
    def vgap = GridPane.Vgap.forInstance(v)
    
    def unwrap: javax.swing.JPanel = v
  }

  def apply(v: javax.swing.JPanel) = v.asInstanceOf[GridPane]

  def init(v: GridPane): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    Pane.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    sc.update(LayoutVar.forInstance(v) := Binding.dyn {
    val rows = v.rows()
    val hgap = v.hgap()
    val vgap = v.vgap()
    
    val layout = v.getLayout.asInstanceOf[GroupLayout]
    val hgroup = layout.createSequentialGroup().nn
    val vgroup = layout.createSequentialGroup().nn
    
    val hSeqGroups = collection.mutable.Map.empty[Int, GroupLayout#ParallelGroup]
    val vSeqGroups = collection.mutable.Map.empty[Int, GroupLayout#ParallelGroup]
    
    val rowSize = rows.size
    
    for {
      (row, rowIdx) <- rows.zipWithIndex
      colSize = row.length
      (node, colIdx) <- row.zipWithIndex
    } {
      hSeqGroups
        .getOrElseUpdate(colIdx, layout.createParallelGroup().nn.tap { g => 
          hgroup.addGroup(g)
          if (hgap > 0 && colIdx < colSize - 1) hgroup.addGap(hgap)
        })
        .addComponent(node.unwrap)
    
      vSeqGroups
        .getOrElseUpdate(rowIdx, layout.createBaselineGroup(true, false).nn.tap { g => 
          vgroup.addGroup(g)
          if (vgap > 0 && rowIdx < rowSize - 1) vgroup.addGap(vgap)
        })
        .addComponent(node.unwrap)
    }
    
    layout.setHorizontalGroup(hgroup)
    layout.setVerticalGroup(vgroup)
    })
  }
  def uninitialized(): GridPane = {
    val res = javax.swing.JPanel().asInstanceOf[GridPane]
    res.asInstanceOf[JPanel].setLayout(GroupLayout(res))
    res
  }
  
  def apply(
    UI: Opt[Binding[javax.swing.plaf.PanelUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoCreateContainerGaps: Opt[Binding[Boolean]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    hgap: Opt[Binding[Int]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    layoutVar: Opt[Binding[Unit]] = UnsetParam,
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rows: Opt[Binding[Seq[Seq[Node]]]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    vgap: Opt[Binding[Int]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[GridPane] = {
    val res = uninitialized()
    GridPane.init(res)
    ifSet(UI, Pane.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoCreateContainerGaps, GridPane.ops.autoCreateContainerGaps(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(hgap, GridPane.ops.hgap(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(layoutVar, GridPane.ops.layoutVar(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(rows, GridPane.ops.rows(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(vgap, GridPane.ops.vgap(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}

opaque type Hbox <: Pane = javax.swing.JPanel & Pane
object Hbox extends VarsMap {
  val Nodes = SwingVar[Hbox, Seq[Node]]("nodes", c => (0 until c.getComponentCount).map(c.getComponent(_).asInstanceOf[Node]), (p, children) => { p.removeAll(); children foreach (n => p.add(n.unwrap)) })

  given ops: (v: Hbox) extended with {
    def nodes = Hbox.Nodes.forInstance(v)
    
    def unwrap: javax.swing.JPanel = v
  }

  def apply(v: javax.swing.JPanel) = v.asInstanceOf[Hbox]

  def init(v: Hbox): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
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
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
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
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    nodes: Opt[Binding[Seq[Node]]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[Hbox] = {
    val res = uninitialized()
    Hbox.init(res)
    ifSet(UI, Pane.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(nodes, Hbox.ops.nodes(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}


opaque type Vbox <: Pane = javax.swing.JPanel & Pane
object Vbox extends VarsMap {
  val Nodes = SwingVar[Vbox, Seq[Node]]("nodes", c => (0 until c.getComponentCount).map(c.getComponent(_).asInstanceOf[Node]), (p, children) => { p.removeAll(); children foreach (n => p.add(n.unwrap)) })

  given ops: (v: Vbox) extended with {
    def nodes = Vbox.Nodes.forInstance(v)
    
    def unwrap: javax.swing.JPanel = v
  }

  def apply(v: javax.swing.JPanel) = v.asInstanceOf[Vbox]

  def init(v: Vbox): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    Pane.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
  }
  def uninitialized(): Vbox = {
    val res = javax.swing.JPanel().asInstanceOf[Vbox]
    res.asInstanceOf[JPanel].setLayout(BoxLayout(res, BoxLayout.Y_AXIS))
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
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
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
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    nodes: Opt[Binding[Seq[Node]]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[Vbox] = {
    val res = uninitialized()
    Vbox.init(res)
    ifSet(UI, Pane.ops.UI(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(nodes, Vbox.ops.nodes(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}

object Box {
  
  def createFiller(
    minWidth: Binding[Double],
    maxWidth: Binding[Double],
    minHeight: Binding[Double],
    maxHeight: Binding[Double]): (given Scenegraph) =>  VarContextAction[Node] = {

    val res = Node(javax.swing.Box.createGlue().nn.asInstanceOf[java.awt.Container])
    Node.init(res)
    res.minimumSize := Binding.dyn(Dimension(minWidth().toInt, minHeight().toInt))
    res.prefSize := Binding.dyn(res.minimumSize())
    res.maximumSize := Binding.dyn(Dimension(maxWidth().toInt, maxHeight().toInt))
    res
  }

  def glue(): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, Int.MaxValue.toDouble, 0.0, Int.MaxValue.toDouble)
  def horizontalGlue(): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, Int.MaxValue.toDouble, 0.0, 0.0)
  def horizontalStrut(width: Binding[Double]): (given Scenegraph) =>  VarContextAction[Node] = createFiller(width, width, 0.0, Int.MaxValue.toDouble)
  def verticalGlue(): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, 0.0, 0.0, Int.MaxValue.toDouble)
  def verticalStrut(height: Binding[Double]): (given Scenegraph) =>  VarContextAction[Node] = createFiller(0.0, 0.0, height, height)
  def strut(width: Binding[Double], height: Binding[Double]): (given Scenegraph) =>  VarContextAction[Node] = createFiller(width, width, height, height)
}