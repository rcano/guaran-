package guarana.swing

import guarana.swing.util._
import java.awt.{Component => _, _}
import javax.swing.JButton

opaque type Button <: ButtonBase = javax.swing.JButton & ButtonBase
object Button extends VarsMap {
  val DefaultCapable = SwingVar[Button, Boolean]("defaultCapable", _.isDefaultCapable, _.setDefaultCapable(_))

  
  given ops: (v: Button) extended with {
    def defaultCapable = Button.DefaultCapable.forInstance(v)

    def defaultButton = v.isDefaultButton
    def unwrap: javax.swing.JButton = v
  }

  def apply(v: javax.swing.JButton) = v.asInstanceOf[Button]

  def init(v: Button): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    ButtonBase.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(): Button = {
    val res = javax.swing.JButton().asInstanceOf[Button]
    
    res
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
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    contentAreaFilled: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
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
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    multiClickThreshhold: Opt[Binding[Long]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    pressedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rolloverEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rolloverIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    rolloverSelectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    selected: Opt[Binding[Boolean]] = UnsetParam,
    selectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    verticalAlignment: Opt[Binding[Int]] = UnsetParam,
    verticalTextPosition: Opt[Binding[Int]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[Button] = {
    val res = uninitialized()
    Button.init(res)
    ifSet(UI, ButtonBase.ops.UI(res) := _)
    ifSet(action, ButtonBase.ops.action(res) := _)
    ifSet(actionCommand, ButtonBase.ops.actionCommand(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(borderPainted, ButtonBase.ops.borderPainted(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(contentAreaFilled, ButtonBase.ops.contentAreaFilled(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(defaultCapable, Button.ops.defaultCapable(res) := _)
    ifSet(disabledIcon, ButtonBase.ops.disabledIcon(res) := _)
    ifSet(disabledSelectedIcon, ButtonBase.ops.disabledSelectedIcon(res) := _)
    ifSet(displayedMnemonicIndex, ButtonBase.ops.displayedMnemonicIndex(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusPainted, ButtonBase.ops.focusPainted(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(hideActionText, ButtonBase.ops.hideActionText(res) := _)
    ifSet(horizontalAlignment, ButtonBase.ops.horizontalAlignment(res) := _)
    ifSet(horizontalTextPosition, ButtonBase.ops.horizontalTextPosition(res) := _)
    ifSet(icon, ButtonBase.ops.icon(res) := _)
    ifSet(iconTextGap, ButtonBase.ops.iconTextGap(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(label, ButtonBase.ops.label(res) := _)
    ifSet(margin, ButtonBase.ops.margin(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mnemonic, ButtonBase.ops.mnemonic(res) := _)
    ifSet(model, ButtonBase.ops.model(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(multiClickThreshhold, ButtonBase.ops.multiClickThreshhold(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(pressedIcon, ButtonBase.ops.pressedIcon(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(rolloverEnabled, ButtonBase.ops.rolloverEnabled(res) := _)
    ifSet(rolloverIcon, ButtonBase.ops.rolloverIcon(res) := _)
    ifSet(rolloverSelectedIcon, ButtonBase.ops.rolloverSelectedIcon(res) := _)
    ifSet(selected, ButtonBase.ops.selected(res) := _)
    ifSet(selectedIcon, ButtonBase.ops.selectedIcon(res) := _)
    ifSet(text, ButtonBase.ops.text(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(verticalAlignment, ButtonBase.ops.verticalAlignment(res) := _)
    ifSet(verticalTextPosition, ButtonBase.ops.verticalTextPosition(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}

opaque type ToggleButton <: ButtonBase = javax.swing.JToggleButton & ButtonBase
object ToggleButton extends VarsMap {
  

  given ops: (v: ToggleButton) extended with {
    
    def unwrap: javax.swing.JToggleButton = v
  }

  def apply(v: javax.swing.JToggleButton) = v.asInstanceOf[ToggleButton]

  def init(v: ToggleButton): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    ButtonBase.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(): ToggleButton = {
    val res = javax.swing.JToggleButton().asInstanceOf[ToggleButton]
    
    res
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
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    contentAreaFilled: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
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
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    multiClickThreshhold: Opt[Binding[Long]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    pressedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rolloverEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rolloverIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    rolloverSelectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    selected: Opt[Binding[Boolean]] = UnsetParam,
    selectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    verticalAlignment: Opt[Binding[Int]] = UnsetParam,
    verticalTextPosition: Opt[Binding[Int]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[ToggleButton] = {
    val res = uninitialized()
    ToggleButton.init(res)
    ifSet(UI, ButtonBase.ops.UI(res) := _)
    ifSet(action, ButtonBase.ops.action(res) := _)
    ifSet(actionCommand, ButtonBase.ops.actionCommand(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(borderPainted, ButtonBase.ops.borderPainted(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(contentAreaFilled, ButtonBase.ops.contentAreaFilled(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(disabledIcon, ButtonBase.ops.disabledIcon(res) := _)
    ifSet(disabledSelectedIcon, ButtonBase.ops.disabledSelectedIcon(res) := _)
    ifSet(displayedMnemonicIndex, ButtonBase.ops.displayedMnemonicIndex(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusPainted, ButtonBase.ops.focusPainted(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(hideActionText, ButtonBase.ops.hideActionText(res) := _)
    ifSet(horizontalAlignment, ButtonBase.ops.horizontalAlignment(res) := _)
    ifSet(horizontalTextPosition, ButtonBase.ops.horizontalTextPosition(res) := _)
    ifSet(icon, ButtonBase.ops.icon(res) := _)
    ifSet(iconTextGap, ButtonBase.ops.iconTextGap(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(label, ButtonBase.ops.label(res) := _)
    ifSet(margin, ButtonBase.ops.margin(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mnemonic, ButtonBase.ops.mnemonic(res) := _)
    ifSet(model, ButtonBase.ops.model(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(multiClickThreshhold, ButtonBase.ops.multiClickThreshhold(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(pressedIcon, ButtonBase.ops.pressedIcon(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(rolloverEnabled, ButtonBase.ops.rolloverEnabled(res) := _)
    ifSet(rolloverIcon, ButtonBase.ops.rolloverIcon(res) := _)
    ifSet(rolloverSelectedIcon, ButtonBase.ops.rolloverSelectedIcon(res) := _)
    ifSet(selected, ButtonBase.ops.selected(res) := _)
    ifSet(selectedIcon, ButtonBase.ops.selectedIcon(res) := _)
    ifSet(text, ButtonBase.ops.text(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(verticalAlignment, ButtonBase.ops.verticalAlignment(res) := _)
    ifSet(verticalTextPosition, ButtonBase.ops.verticalTextPosition(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}
opaque type CheckBox <: ToggleButton = javax.swing.JCheckBox & ToggleButton
object CheckBox extends VarsMap {
  val BorderPaintedFlat = SwingVar[CheckBox, Boolean]("borderPaintedFlat", _.isBorderPaintedFlat, _.setBorderPaintedFlat(_))

  given ops: (v: CheckBox) extended with {
    def borderPaintedFlat = CheckBox.BorderPaintedFlat.forInstance(v)

    def unwrap: javax.swing.JCheckBox = v
  }

  def apply(v: javax.swing.JCheckBox) = v.asInstanceOf[CheckBox]

  def init(v: CheckBox): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    ToggleButton.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
  }
  def uninitialized(): CheckBox = {
    val res = javax.swing.JCheckBox().asInstanceOf[CheckBox]
    
    res
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
    borderPaintedFlat: Opt[Binding[Boolean]] = UnsetParam,
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    contentAreaFilled: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
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
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    multiClickThreshhold: Opt[Binding[Long]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    pressedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rolloverEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rolloverIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    rolloverSelectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    selected: Opt[Binding[Boolean]] = UnsetParam,
    selectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    verticalAlignment: Opt[Binding[Int]] = UnsetParam,
    verticalTextPosition: Opt[Binding[Int]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[CheckBox] = {
    val res = uninitialized()
    CheckBox.init(res)
    ifSet(UI, ButtonBase.ops.UI(res) := _)
    ifSet(action, ButtonBase.ops.action(res) := _)
    ifSet(actionCommand, ButtonBase.ops.actionCommand(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(borderPainted, ButtonBase.ops.borderPainted(res) := _)
    ifSet(borderPaintedFlat, CheckBox.ops.borderPaintedFlat(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(contentAreaFilled, ButtonBase.ops.contentAreaFilled(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(disabledIcon, ButtonBase.ops.disabledIcon(res) := _)
    ifSet(disabledSelectedIcon, ButtonBase.ops.disabledSelectedIcon(res) := _)
    ifSet(displayedMnemonicIndex, ButtonBase.ops.displayedMnemonicIndex(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusPainted, ButtonBase.ops.focusPainted(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(hideActionText, ButtonBase.ops.hideActionText(res) := _)
    ifSet(horizontalAlignment, ButtonBase.ops.horizontalAlignment(res) := _)
    ifSet(horizontalTextPosition, ButtonBase.ops.horizontalTextPosition(res) := _)
    ifSet(icon, ButtonBase.ops.icon(res) := _)
    ifSet(iconTextGap, ButtonBase.ops.iconTextGap(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(label, ButtonBase.ops.label(res) := _)
    ifSet(margin, ButtonBase.ops.margin(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mnemonic, ButtonBase.ops.mnemonic(res) := _)
    ifSet(model, ButtonBase.ops.model(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(multiClickThreshhold, ButtonBase.ops.multiClickThreshhold(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(pressedIcon, ButtonBase.ops.pressedIcon(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(rolloverEnabled, ButtonBase.ops.rolloverEnabled(res) := _)
    ifSet(rolloverIcon, ButtonBase.ops.rolloverIcon(res) := _)
    ifSet(rolloverSelectedIcon, ButtonBase.ops.rolloverSelectedIcon(res) := _)
    ifSet(selected, ButtonBase.ops.selected(res) := _)
    ifSet(selectedIcon, ButtonBase.ops.selectedIcon(res) := _)
    ifSet(text, ButtonBase.ops.text(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(verticalAlignment, ButtonBase.ops.verticalAlignment(res) := _)
    ifSet(verticalTextPosition, ButtonBase.ops.verticalTextPosition(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}
opaque type RadioButton <: ToggleButton = javax.swing.JRadioButton & ToggleButton
object RadioButton extends VarsMap {
  

  given ops: (v: RadioButton) extended with {
    
    
    def unwrap: javax.swing.JRadioButton = v
  }

  def apply(v: javax.swing.JRadioButton) = v.asInstanceOf[RadioButton]

  def init(v: RadioButton): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    ToggleButton.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(): RadioButton = {
    val res = javax.swing.JRadioButton().asInstanceOf[RadioButton]
    
    res
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
    bounds: Opt[Binding[java.awt.Rectangle]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[javax.swing.JPopupMenu | Null]] = UnsetParam,
    contentAreaFilled: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
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
    mouseLocation: Opt[Binding[(Int, Int)]] = UnsetParam,
    multiClickThreshhold: Opt[Binding[Long]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[java.awt.Dimension | Null]] = UnsetParam,
    pressedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rolloverEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rolloverIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    rolloverSelectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    selected: Opt[Binding[Boolean]] = UnsetParam,
    selectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    verticalAlignment: Opt[Binding[Int]] = UnsetParam,
    verticalTextPosition: Opt[Binding[Int]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): (given Scenegraph) => VarContextAction[RadioButton] = {
    val res = uninitialized()
    RadioButton.init(res)
    ifSet(UI, ButtonBase.ops.UI(res) := _)
    ifSet(action, ButtonBase.ops.action(res) := _)
    ifSet(actionCommand, ButtonBase.ops.actionCommand(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(autoscrolls, Component.ops.autoscrolls(res) := _)
    ifSet(background, Node.ops.background(res) := _)
    ifSet(border, Component.ops.border(res) := _)
    ifSet(borderPainted, ButtonBase.ops.borderPainted(res) := _)
    ifSet(bounds, Node.ops.bounds(res) := _)
    ifSet(componentOrientation, Node.ops.componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.componentPopupMenu(res) := _)
    ifSet(contentAreaFilled, ButtonBase.ops.contentAreaFilled(res) := _)
    ifSet(cursor, Node.ops.cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.debugGraphicsOptions(res) := _)
    ifSet(disabledIcon, ButtonBase.ops.disabledIcon(res) := _)
    ifSet(disabledSelectedIcon, ButtonBase.ops.disabledSelectedIcon(res) := _)
    ifSet(displayedMnemonicIndex, ButtonBase.ops.displayedMnemonicIndex(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusPainted, ButtonBase.ops.focusPainted(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(hideActionText, ButtonBase.ops.hideActionText(res) := _)
    ifSet(horizontalAlignment, ButtonBase.ops.horizontalAlignment(res) := _)
    ifSet(horizontalTextPosition, ButtonBase.ops.horizontalTextPosition(res) := _)
    ifSet(icon, ButtonBase.ops.icon(res) := _)
    ifSet(iconTextGap, ButtonBase.ops.iconTextGap(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(label, ButtonBase.ops.label(res) := _)
    ifSet(margin, ButtonBase.ops.margin(res) := _)
    ifSet(maximumSize, Node.ops.maximumSize(res) := _)
    ifSet(minimumSize, Node.ops.minimumSize(res) := _)
    ifSet(mnemonic, ButtonBase.ops.mnemonic(res) := _)
    ifSet(model, ButtonBase.ops.model(res) := _)
    ifSet(mouseLocation, Node.ops.mouseLocation(res) := _)
    ifSet(multiClickThreshhold, ButtonBase.ops.multiClickThreshhold(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(pressedIcon, ButtonBase.ops.pressedIcon(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(rolloverEnabled, ButtonBase.ops.rolloverEnabled(res) := _)
    ifSet(rolloverIcon, ButtonBase.ops.rolloverIcon(res) := _)
    ifSet(rolloverSelectedIcon, ButtonBase.ops.rolloverSelectedIcon(res) := _)
    ifSet(selected, ButtonBase.ops.selected(res) := _)
    ifSet(selectedIcon, ButtonBase.ops.selectedIcon(res) := _)
    ifSet(text, ButtonBase.ops.text(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(verticalAlignment, ButtonBase.ops.verticalAlignment(res) := _)
    ifSet(verticalTextPosition, ButtonBase.ops.verticalTextPosition(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
}
