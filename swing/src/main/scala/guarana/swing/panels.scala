package guarana.swing

import language.implicitConversions
import javax.swing._
import guarana.swing.util._
import scala.util.chaining._

opaque type Pane <: Component = JPanel & Component
object Pane {
  val UI = SwingVar[Pane, javax.swing.plaf.PanelUI]("UI", _.getUI.nn, _.setUI(_))

  given ops: (v: Pane) extended with {
    def UI = Pane.UI.forInstance(v)
    def unwrap: javax.swing.JPanel = v
  }

  def init(v: Pane) = (given sc: Scenegraph) => {
    Component.init(v)
  }

  def uninitialized(): Pane = JPanel().asInstanceOf[Pane]

  def apply(
    UI: Opt[Binding[javax.swing.plaf.PanelUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
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
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    preferredSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[Pane] = {
    val res = uninitialized()
    Component.init(res)
    ifSet(UI, res.UI := _)
    ifSet(actionMap, res.actionMap := _)
    ifSet(alignmentX, res.alignmentX := _)
    ifSet(alignmentY, res.alignmentY := _)
    ifSet(autoscrolls, res.autoscrolls := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, res.border := _)
    ifSet(componentPopupMenu, res.componentPopupMenu := _)
    ifSet(debugGraphicsOptions, res.debugGraphicsOptions := _)
    ifSet(doubleBuffered, res.doubleBuffered := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, res.focusable := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(inheritsPopupMenu, res.inheritsPopupMenu := _)
    ifSet(inputVerifier, res.inputVerifier := _)
    ifSet(maximumSize, res.maximumSize := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(opaque, res.opaque := _)
    ifSet(preferredSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, res.requestFocusEnabled := _)
    ifSet(toolTipText, res.toolTipText := _)
    ifSet(transferHandler, res.transferHandler := _)
    ifSet(verifyInputWhenFocusTarget, res.verifyInputWhenFocusTarget := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}

opaque type AbsolutePositioningPane <: Pane = Pane
object AbsolutePositioningPane {
  val Nodes = SwingVar[Pane, Seq[Node]](
    "nodes",
     c => (0 until c.getComponentCount).map(c.getComponent(_).asInstanceOf[Node]),
     (p, children) => { p.removeAll(); children foreach (n => p.add(n.unwrap)) }
  )

  given ops: (v: AbsolutePositioningPane) extended with {
    def nodes = AbsolutePositioningPane.Nodes.forInstance(v)
  }

  def init(v: AbsolutePositioningPane) = (given sc: Scenegraph) => {
    Pane.init(v)
  }

  def uninitialized(): AbsolutePositioningPane = {
    val res = Pane.uninitialized()
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
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
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
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    preferredSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,

    nodes: Opt[Binding[Seq[Node]]] = UnsetParam,
  ): (given Scenegraph) => VarContextAction[AbsolutePositioningPane] = {
    val res = Pane(
      UI = UI,
      actionMap = actionMap,
      alignmentX = alignmentX,
      alignmentY = alignmentY,
      autoscrolls = autoscrolls,
      background = background,
      border = border,
      componentPopupMenu = componentPopupMenu,
      debugGraphicsOptions = debugGraphicsOptions,
      doubleBuffered = doubleBuffered,
      enabled = enabled,
      focusable = focusable,
      font = font,
      foreground = foreground,
      inheritsPopupMenu = inheritsPopupMenu,
      inputVerifier = inputVerifier,
      maximumSize = maximumSize,
      minimumSize = minimumSize,
      opaque = opaque,
      preferredSize = preferredSize,
      requestFocusEnabled = requestFocusEnabled,
      toolTipText = toolTipText,
      transferHandler = transferHandler,
      verifyInputWhenFocusTarget = verifyInputWhenFocusTarget,
      visible = visible,
    )
    res.asInstanceOf[JPanel].setLayout(null)
    ifSet(nodes, res.nodes := _)
    res
  }
}


opaque type BorderPane <: Pane = Pane
object BorderPane {
  import java.awt.BorderLayout
  val Top = SwingVar[Pane, Node | Null](
    "top",
     c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.NORTH).asInstanceOf[Node | Null],
     (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.NORTH) }
  )
  val Bottom = SwingVar[Pane, Node | Null](
    "bottom",
     c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.SOUTH).asInstanceOf[Node | Null],
     (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.SOUTH) }
  )
  val Left = SwingVar[Pane, Node | Null](
    "left",
     c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.WEST).asInstanceOf[Node | Null],
     (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.WEST) }
  )
  val Right = SwingVar[Pane, Node | Null](
    "right",
     c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.EAST).asInstanceOf[Node | Null],
     (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.EAST) }
  )
  val Center = SwingVar[Pane, Node | Null](
    "center",
     c => c.getLayout.asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.CENTER).asInstanceOf[Node | Null],
     (p, n) => { p.add(if (n == null) null else n.unwrap, BorderLayout.CENTER) }
  )
  val Hgap = SwingVar[Pane, Int](
    "hgap",
     c => c.getLayout.asInstanceOf[BorderLayout].getHgap,
     (p, g) => { p.getLayout.asInstanceOf[BorderLayout].setHgap(g) }
  )
  val Vgap = SwingVar[Pane, Int](
    "vgap",
     c => c.getLayout.asInstanceOf[BorderLayout].getVgap,
     (p, g) => { p.getLayout.asInstanceOf[BorderLayout].setVgap(g) }
  )

  given ops: (v: BorderPane) extended with {
    def top = BorderPane.Top.forInstance(v)
    def bottom = BorderPane.Bottom.forInstance(v)
    def left = BorderPane.Left.forInstance(v)
    def right = BorderPane.Right.forInstance(v)
    def center = BorderPane.Center.forInstance(v)
    def hgap = BorderPane.Hgap.forInstance(v)
    def vgap = BorderPane.Vgap.forInstance(v)
  }

  def init(v: BorderPane) = (given sc: Scenegraph) => {
    Pane.init(v)
  }

  def uninitialized(): BorderPane = {
    val res = Pane.uninitialized()
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
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
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
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    preferredSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,

    top: Opt[Binding[Node | Null]] = UnsetParam,
    bottom: Opt[Binding[Node | Null]] = UnsetParam,
    left: Opt[Binding[Node | Null]] = UnsetParam,
    right: Opt[Binding[Node | Null]] = UnsetParam,
    center: Opt[Binding[Node | Null]] = UnsetParam,
    hgap: Opt[Int] = UnsetParam,
    vgap: Opt[Int] = UnsetParam,
  ): (given Scenegraph) => VarContextAction[BorderPane] = {
    val res = Pane(
      UI = UI,
      actionMap = actionMap,
      alignmentX = alignmentX,
      alignmentY = alignmentY,
      autoscrolls = autoscrolls,
      background = background,
      border = border,
      componentPopupMenu = componentPopupMenu,
      debugGraphicsOptions = debugGraphicsOptions,
      doubleBuffered = doubleBuffered,
      enabled = enabled,
      focusable = focusable,
      font = font,
      foreground = foreground,
      inheritsPopupMenu = inheritsPopupMenu,
      inputVerifier = inputVerifier,
      maximumSize = maximumSize,
      minimumSize = minimumSize,
      opaque = opaque,
      preferredSize = preferredSize,
      requestFocusEnabled = requestFocusEnabled,
      toolTipText = toolTipText,
      transferHandler = transferHandler,
      verifyInputWhenFocusTarget = verifyInputWhenFocusTarget,
      visible = visible,
    )
    res.asInstanceOf[JPanel].setLayout(BorderLayout())
    ifSet(top, res.top := _)
    ifSet(bottom, res.bottom := _)
    ifSet(left, res.left := _)
    ifSet(right, res.right := _)
    ifSet(center, res.center := _)
    ifSet(hgap, res.hgap := _)
    ifSet(vgap, res.vgap := _)
    res
  }
}



opaque type GridPane <: Pane = Pane
object GridPane {
  import javax.swing.GroupLayout
  val Rows = Var[Seq[Seq[Node]]]("rows", Seq.empty)
  val Hgap = Var[Int]("hgap", 0)
  val Vgap = Var[Int]("vgap", 0)
  private val layoutVar = SwingVar[GridPane, Unit]("_layout", _ => (), (_, _) => ())
  val AutoCreateContainerGaps = SwingVar[GridPane, Boolean]("autoCreateContainerGaps",
   _.getLayout.asInstanceOf[GroupLayout].getAutoCreateContainerGaps(),
   _.getLayout.asInstanceOf[GroupLayout].setAutoCreateContainerGaps(_)
  )

  given ops: (v: GridPane) extended with {
    def rows = GridPane.Rows.forInstance(v)
    def hgap = GridPane.Hgap.forInstance(v)
    def vgap = GridPane.Vgap.forInstance(v)
    def autoCreateContainerGaps = GridPane.AutoCreateContainerGaps.forInstance(v)
  }

  def init(v: GridPane) = (given sc: Scenegraph) => {
    Pane.init(v)
    v.asInstanceOf[JPanel].setLayout(GroupLayout(v))
    sc.update(layoutVar.forInstance(v) := Binding.dyn {
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
    val res = Pane.uninitialized()
    res.asInstanceOf[JPanel].setLayout(GroupLayout(res))
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
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
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
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    preferredSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,

    rows: Opt[Binding[Seq[Seq[Node]]]] = UnsetParam,
    hgap: Opt[Binding[Int]] = UnsetParam,
    vgap: Opt[Binding[Int]] = UnsetParam,
    autoCreateContainerGaps: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[GridPane] = {
    val res = Pane(
      UI = UI,
      actionMap = actionMap,
      alignmentX = alignmentX,
      alignmentY = alignmentY,
      autoscrolls = autoscrolls,
      background = background,
      border = border,
      componentPopupMenu = componentPopupMenu,
      debugGraphicsOptions = debugGraphicsOptions,
      doubleBuffered = doubleBuffered,
      enabled = enabled,
      focusable = focusable,
      font = font,
      foreground = foreground,
      inheritsPopupMenu = inheritsPopupMenu,
      inputVerifier = inputVerifier,
      maximumSize = maximumSize,
      minimumSize = minimumSize,
      opaque = opaque,
      preferredSize = preferredSize,
      requestFocusEnabled = requestFocusEnabled,
      toolTipText = toolTipText,
      transferHandler = transferHandler,
      verifyInputWhenFocusTarget = verifyInputWhenFocusTarget,
      visible = visible,
    )
    //we need to reinit the pane with our custom logic, so remove the previous property listener
    val pane = res.unwrap
    pane.getMouseMotionListeners.nn foreach pane.removeMouseMotionListener
    pane.getPropertyChangeListeners.nn foreach pane.removePropertyChangeListener
    ifSet(rows, res.rows := _)
    ifSet(hgap, res.hgap := _)
    ifSet(vgap, res.vgap := _)
    init(res)
    ifSet(autoCreateContainerGaps, res.autoCreateContainerGaps := _)
    res
  }
}