//AUTOGENERATED FILE, DO NOT MODIFY

package guarana.swing

import language.implicitConversions
import java.awt.{Component => _, MenuBar => _, MenuItem => _, TextComponent => _, TextField => _, PopupMenu => _, _}
import java.awt.event._
import javax.swing.{Action => _, _}
import javax.swing.event._
import guarana.swing.util._
import scala.jdk.CollectionConverters._
import scala.util.chaining._

opaque type CheckBoxMenuItem <: MenuItem  = javax.swing.JCheckBoxMenuItem & MenuItem
object CheckBoxMenuItem extends VarsMap {
  val State: SwingVar.Aux[CheckBoxMenuItem, Boolean] = SwingVar[CheckBoxMenuItem, Boolean]("state", _.getState, _.setState(_))

  

  given ops as Ops.type = Ops
  object Ops {
    extension (v: CheckBoxMenuItem) {
      def state: Var.Aux[Boolean, v.type] = CheckBoxMenuItem.State.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      
      def unwrap: javax.swing.JCheckBoxMenuItem = v
    }
  }

  def wrap(v: javax.swing.JCheckBoxMenuItem) = v.asInstanceOf[CheckBoxMenuItem]

  def init(v: CheckBoxMenuItem): Scenegraph ?=> Unit = (using sc: Scenegraph) => {
    MenuItem.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(): CheckBoxMenuItem = {
    val res = javax.swing.JCheckBoxMenuItem().asInstanceOf[CheckBoxMenuItem]
    
    res
  }
  
  def apply(
    
    UI: Opt[Binding[javax.swing.plaf.MenuItemUI | Null]] = UnsetParam,
    accelerator: Opt[Binding[javax.swing.KeyStroke | Null]] = UnsetParam,
    action: Opt[Binding[javax.swing.Action | Null]] = UnsetParam,
    actionCommand: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    armed: Opt[Binding[Boolean]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    borderPainted: Opt[Binding[Boolean]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[PopupMenu | Null]] = UnsetParam,
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
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    mnemonic: Opt[Binding[Int]] = UnsetParam,
    model: Opt[Binding[javax.swing.ButtonModel | Null]] = UnsetParam,
    mouseDragMut: Opt[Binding[Option[MouseDrag]]] = UnsetParam,
    multiClickThreshhold: Opt[Binding[Long]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    pressed: Opt[Binding[Boolean]] = UnsetParam,
    pressedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rollover: Opt[Binding[Boolean]] = UnsetParam,
    rolloverEnabled: Opt[Binding[Boolean]] = UnsetParam,
    rolloverIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    rolloverSelectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    selected: Opt[Binding[Boolean]] = UnsetParam,
    selectedIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    state: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    verticalAlignment: Opt[Binding[Int]] = UnsetParam,
    verticalTextPosition: Opt[Binding[Int]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[CheckBoxMenuItem] = {
    val res = uninitialized()
    CheckBoxMenuItem.init(res)
    ifSet(UI, MenuItem.ops.UI(res) := _)
    ifSet(accelerator, MenuItem.ops.accelerator(res) := _)
    ifSet(action, ButtonBase.ops.action(res) := _)
    ifSet(actionCommand, ButtonBase.ops.actionCommand(res) := _)
    ifSet(actionMap, Component.ops.actionMap(res) := _)
    ifSet(alignmentX, Component.ops.alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.alignmentY(res) := _)
    ifSet(armed, ButtonBase.ops.armed(res) := _)
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
    ifSet(enabled, ButtonBase.ops.enabled(res) := _)
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
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(mnemonic, ButtonBase.ops.mnemonic(res) := _)
    ifSet(model, ButtonBase.ops.model(res) := _)
    ifSet(mouseDragMut, Node.ops.mouseDragMut(res) := _)
    ifSet(multiClickThreshhold, ButtonBase.ops.multiClickThreshhold(res) := _)
    ifSet(name, Node.ops.name(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(pressed, ButtonBase.ops.pressed(res) := _)
    ifSet(pressedIcon, ButtonBase.ops.pressedIcon(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(rollover, ButtonBase.ops.rollover(res) := _)
    ifSet(rolloverEnabled, ButtonBase.ops.rolloverEnabled(res) := _)
    ifSet(rolloverIcon, ButtonBase.ops.rolloverIcon(res) := _)
    ifSet(rolloverSelectedIcon, ButtonBase.ops.rolloverSelectedIcon(res) := _)
    ifSet(selected, ButtonBase.ops.selected(res) := _)
    ifSet(selectedIcon, ButtonBase.ops.selectedIcon(res) := _)
    ifSet(state, CheckBoxMenuItem.ops.state(res) := _)
    ifSet(text, ButtonBase.ops.text(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(verticalAlignment, ButtonBase.ops.verticalAlignment(res) := _)
    ifSet(verticalTextPosition, ButtonBase.ops.verticalTextPosition(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
  def apply(a: Action): Scenegraph ?=> CheckBoxMenuItem = wrap(javax.swing.JCheckBoxMenuItem(a.unwrap)).tap(init)
}