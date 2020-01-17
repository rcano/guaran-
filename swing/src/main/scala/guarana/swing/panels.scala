package guarana.swing

import language.implicitConversions
import javax.swing._
import guarana.swing.util._


opaque type Pane <: Component = JPanel & Component
object Pane {
  val UI = SwingVar[Pane, javax.swing.plaf.PanelUI]("UI", _.getUI, _.setUI(_))

  given ops: (v: Pane) extended with {
    def UI = Pane.UI.forInstance(v)
    def unwrap: javax.swing.JPanel = v
  }

  def uninitialized(): Pane = JPanel().asInstanceOf[Pane]

  def apply(
    UI: Opt[Binding[javax.swing.plaf.PanelUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier]] = UnsetParam,
    maximumSize: Opt[Binding[java.awt.Dimension]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    preferredSize: Opt[Binding[java.awt.Dimension]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[java.lang.String]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler]] = UnsetParam,
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

  def apply(
    UI: Opt[Binding[javax.swing.plaf.PanelUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier]] = UnsetParam,
    maximumSize: Opt[Binding[java.awt.Dimension]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    preferredSize: Opt[Binding[java.awt.Dimension]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    toolTipText: Opt[Binding[java.lang.String]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler]] = UnsetParam,
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