package guarana.swing

import guarana.swing.util._
import java.awt.{Component => _, _}
import javax.swing.JLabel

opaque type Label <: Component = javax.swing.JLabel & Component
object Label {
  val UI = SwingVar[Label, javax.swing.plaf.LabelUI]("UI", _.getUI, _.setUI(_))
  val DisabledIcon = SwingVar[Label, javax.swing.Icon]("disabledIcon", _.getDisabledIcon, _.setDisabledIcon(_))
  val DisplayedMnemonic = SwingVar[Label, Int]("displayedMnemonic", _.getDisplayedMnemonic, _.setDisplayedMnemonic(_))
  val DisplayedMnemonicIndex = SwingVar[Label, Int]("displayedMnemonicIndex", _.getDisplayedMnemonicIndex, _.setDisplayedMnemonicIndex(_))
  val HorizontalAlignment = SwingVar[Label, Int]("horizontalAlignment", _.getHorizontalAlignment, _.setHorizontalAlignment(_))
  val HorizontalTextPosition = SwingVar[Label, Int]("horizontalTextPosition", _.getHorizontalTextPosition, _.setHorizontalTextPosition(_))
  val Icon = SwingVar[Label, javax.swing.Icon]("icon", _.getIcon, _.setIcon(_))
  val IconTextGap = SwingVar[Label, Int]("iconTextGap", _.getIconTextGap, _.setIconTextGap(_))
  val LabelFor = SwingVar[Label, java.awt.Component]("labelFor", _.getLabelFor, _.setLabelFor(_))
  val Text = SwingVar[Label, java.lang.String]("text", _.getText, _.setText(_))
  val VerticalAlignment = SwingVar[Label, Int]("verticalAlignment", _.getVerticalAlignment, _.setVerticalAlignment(_))
  val VerticalTextPosition = SwingVar[Label, Int]("verticalTextPosition", _.getVerticalTextPosition, _.setVerticalTextPosition(_))

  given ops: (v: Label) extended with {
    def UIClassID = v.getUIClassID
    def accessibleContext = v.getAccessibleContext
    def UI = Label.UI.forInstance(v)
    def disabledIcon = Label.DisabledIcon.forInstance(v)
    def displayedMnemonic = Label.DisplayedMnemonic.forInstance(v)
    def displayedMnemonicIndex = Label.DisplayedMnemonicIndex.forInstance(v)
    def horizontalAlignment = Label.HorizontalAlignment.forInstance(v)
    def horizontalTextPosition = Label.HorizontalTextPosition.forInstance(v)
    def icon = Label.Icon.forInstance(v)
    def iconTextGap = Label.IconTextGap.forInstance(v)
    def labelFor = Label.LabelFor.forInstance(v)
    def text = Label.Text.forInstance(v)
    def verticalAlignment = Label.VerticalAlignment.forInstance(v)
    def verticalTextPosition = Label.VerticalTextPosition.forInstance(v)
    def unwrap: javax.swing.JLabel = v
  }

  def uninitialized(): Label = JLabel().asInstanceOf[Label]

  def apply(
    UI: Opt[Binding[javax.swing.plaf.LabelUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    disabledIcon: Opt[Binding[javax.swing.Icon]] = UnsetParam,
    displayedMnemonic: Opt[Binding[Int]] = UnsetParam,
    displayedMnemonicIndex: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusCycleRoot: Opt[Binding[Boolean]] = UnsetParam,
    focusTraversalPolicy: Opt[Binding[java.awt.FocusTraversalPolicy]] = UnsetParam,
    focusTraversalPolicyProvider: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color]] = UnsetParam,
    horizontalAlignment: Opt[Binding[Int]] = UnsetParam,
    horizontalTextPosition: Opt[Binding[Int]] = UnsetParam,
    icon: Opt[Binding[javax.swing.Icon]] = UnsetParam,
    iconTextGap: Opt[Binding[Int]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier]] = UnsetParam,
    maximumSize: Opt[Binding[java.awt.Dimension]] = UnsetParam,
    minimumSize: Opt[Binding[java.awt.Dimension]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    nextFocusableComponent: Opt[Binding[java.awt.Component]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    preferredSize: Opt[Binding[java.awt.Dimension]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String]] = UnsetParam,
    toolTipText: Opt[Binding[java.lang.String]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    verticalAlignment: Opt[Binding[Int]] = UnsetParam,
    verticalTextPosition: Opt[Binding[Int]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam

  ): (given Scenegraph) => VarContextAction[Label] = {
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
    ifSet(disabledIcon, res.disabledIcon := _)
    ifSet(displayedMnemonic, res.displayedMnemonic := _)
    ifSet(displayedMnemonicIndex, res.displayedMnemonicIndex := _)
    ifSet(doubleBuffered, res.doubleBuffered := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, res.focusable := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(horizontalAlignment, res.horizontalAlignment := _)
    ifSet(horizontalTextPosition, res.horizontalTextPosition := _)
    ifSet(icon, res.icon := _)
    ifSet(iconTextGap, res.iconTextGap := _)
    ifSet(inheritsPopupMenu, res.inheritsPopupMenu := _)
    ifSet(inputVerifier, res.inputVerifier := _)
    ifSet(maximumSize, res.maximumSize := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(opaque, res.opaque := _)
    ifSet(preferredSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, res.requestFocusEnabled := _)
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