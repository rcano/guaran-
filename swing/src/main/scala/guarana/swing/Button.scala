package guarana.swing

import guarana.swing.util._
import java.awt.{Component => _, _}
import javax.swing.JButton

opaque type Button <: ButtonBase = javax.swing.JButton & ButtonBase
object Button extends VarsMap {
  val DefaultCapable = SwingVar[Button, Boolean]("defaultCapable", _.isDefaultCapable, _.setDefaultCapable(_))

  given ops: (v: Button) extended with {
    def defaultButton = v.isDefaultButton
    def defaultCapable = Button.DefaultCapable.forInstance(v)
    def unwrap: javax.swing.JButton = v
  }

  def uninitialized(): Button = JButton().asInstanceOf[Button]

  def init(n: Button) = (given sc: Scenegraph) => {
    ButtonBase.init(n)
    n.addPropertyChangeListener(varsPropertyListener(n))
  }

  def apply(
    UI: Opt[Binding[javax.swing.plaf.ButtonUI]] = UnsetParam,
    action: Opt[Binding[javax.swing.Action | Null]] = UnsetParam,
    actionCommand: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    borderPainted: Opt[Binding[Boolean]] = UnsetParam,
    bounds: Opt[Binding[Rectangle]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    contentAreaFilled: Opt[Binding[Boolean]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    defaultCapable: Opt[Binding[Boolean]] = UnsetParam,
    disabledIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    disabledSelectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    displayedMnemonicIndex: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusPainted: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    hideActionText: Opt[Binding[Boolean]] = UnsetParam,
    horizontalAlignment: Opt[Binding[Int]] = UnsetParam,
    horizontalTextPosition: Opt[Binding[Int]] = UnsetParam,
    icon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    iconTextGap: Opt[Binding[Int]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    label: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    margin: Opt[Binding[java.awt.Insets | Null]] = UnsetParam,
    maximumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    mnemonic: Opt[Binding[Int]] = UnsetParam,
    model: Opt[Binding[javax.swing.ButtonModel | Null]] = UnsetParam,
    multiClickThreshhold: Opt[Binding[Long]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    preferredSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    pressedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rolloverEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rolloverIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    rolloverSelectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    selected: Opt[Binding[Boolean]] = UnsetParam,
    selectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    verticalAlignment: Opt[Binding[Int]] = UnsetParam,
    verticalTextPosition: Opt[Binding[Int]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[Button] = {
    val res = uninitialized()
    ButtonBase.init(res)
    ifSet(UI, res.UI := _)
    ifSet(action, ButtonBase.ops.action(res) := _)
    ifSet(actionCommand, res.actionCommand := _)
    ifSet(actionMap, res.actionMap := _)
    ifSet(alignmentX, res.alignmentX := _)
    ifSet(alignmentY, res.alignmentY := _)
    ifSet(autoscrolls, res.autoscrolls := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, res.border := _)
    ifSet(borderPainted, ButtonBase.ops.borderPainted(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentPopupMenu, res.componentPopupMenu := _)
    ifSet(contentAreaFilled, res.contentAreaFilled := _)
    ifSet(debugGraphicsOptions, res.debugGraphicsOptions := _)
    ifSet(defaultCapable, Button.ops.defaultCapable(res) := _)
    ifSet(disabledIcon, res.disabledIcon := _)
    ifSet(disabledSelectedIcon, res.disabledSelectedIcon := _)
    ifSet(displayedMnemonicIndex, res.displayedMnemonicIndex := _)
    ifSet(doubleBuffered, res.doubleBuffered := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusPainted, res.focusPainted := _)
    ifSet(focusable, res.focusable := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(hideActionText, res.hideActionText := _)
    ifSet(horizontalAlignment, res.horizontalAlignment := _)
    ifSet(horizontalTextPosition, res.horizontalTextPosition := _)
    ifSet(icon, res.icon := _)
    ifSet(iconTextGap, res.iconTextGap := _)
    ifSet(inheritsPopupMenu, res.inheritsPopupMenu := _)
    ifSet(inputVerifier, res.inputVerifier := _)
    ifSet(label, res.label := _)
    ifSet(margin, res.margin := _)
    ifSet(maximumSize, res.maximumSize := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mnemonic, res.mnemonic := _)
    ifSet(model, ButtonBase.ops.model(res) := _)
    ifSet(multiClickThreshhold, res.multiClickThreshhold := _)
    ifSet(opaque, res.opaque := _)
    ifSet(preferredSize, Node.ops.prefSize(res) := _)
    ifSet(pressedIcon, res.pressedIcon := _)
    ifSet(requestFocusEnabled, res.requestFocusEnabled := _)
    ifSet(rolloverEnabled, res.rolloverEnabled := _)
    ifSet(rolloverIcon, res.rolloverIcon := _)
    ifSet(rolloverSelectedIcon, res.rolloverSelectedIcon := _)
    ifSet(selected, res.selected := _)
    ifSet(selectedIcon, res.selectedIcon := _)
    ifSet(text, res.text := _)
    ifSet(toolTipText, res.toolTipText := _)
    ifSet(transferHandler, res.transferHandler := _)
    ifSet(verifyInputWhenFocusTarget, res.verifyInputWhenFocusTarget := _)
    ifSet(verticalAlignment, res.verticalAlignment := _)
    ifSet(verticalTextPosition, res.verticalTextPosition := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}
