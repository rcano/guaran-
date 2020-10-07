//AUTOGENERATED FILE, DO NOT MODIFY

package guarana.swing

import language.implicitConversions
import java.awt.{Component => _, MenuBar => _, MenuItem => _, TextComponent => _, TextField => _, _}
import java.awt.event._
import javax.swing.{Action => _, _}
import javax.swing.event._
import guarana.swing.util._
import scala.jdk.CollectionConverters._
import scala.util.chaining._

opaque type RadioButton <: ToggleButton = javax.swing.JRadioButton & ToggleButton
object RadioButton extends VarsMap {
  

  

  given ops as Ops.type = Ops
  object Ops {
    extension (v: RadioButton) {
      

      

      
      def unwrap: javax.swing.JRadioButton = v
    }
  }

  def wrap(v: javax.swing.JRadioButton) = v.asInstanceOf[RadioButton]

  def init(v: RadioButton): Scenegraph ?=> Unit = (using sc: Scenegraph) => {
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
    armed: Opt[Binding[Boolean]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    borderPainted: Opt[Binding[Boolean]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
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
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    verticalAlignment: Opt[Binding[Int]] = UnsetParam,
    verticalTextPosition: Opt[Binding[Int]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[RadioButton] = {
    val res = uninitialized()
    RadioButton.init(res)
    ifSet(UI, ButtonBase.ops.extension_UI(res) := _)
    ifSet(action, ButtonBase.ops.extension_action(res) := _)
    ifSet(actionCommand, ButtonBase.ops.extension_actionCommand(res) := _)
    ifSet(actionMap, Component.ops.extension_actionMap(res) := _)
    ifSet(alignmentX, Component.ops.extension_alignmentX(res) := _)
    ifSet(alignmentY, Component.ops.extension_alignmentY(res) := _)
    ifSet(armed, ButtonBase.ops.extension_armed(res) := _)
    ifSet(autoscrolls, Component.ops.extension_autoscrolls(res) := _)
    ifSet(background, Node.ops.extension_background(res) := _)
    ifSet(border, Component.ops.extension_border(res) := _)
    ifSet(borderPainted, ButtonBase.ops.extension_borderPainted(res) := _)
    ifSet(bounds, Node.ops.extension_bounds(res) := _)
    ifSet(componentOrientation, Node.ops.extension_componentOrientation(res) := _)
    ifSet(componentPopupMenu, Component.ops.extension_componentPopupMenu(res) := _)
    ifSet(contentAreaFilled, ButtonBase.ops.extension_contentAreaFilled(res) := _)
    ifSet(cursor, Node.ops.extension_cursor(res) := _)
    ifSet(debugGraphicsOptions, Component.ops.extension_debugGraphicsOptions(res) := _)
    ifSet(disabledIcon, ButtonBase.ops.extension_disabledIcon(res) := _)
    ifSet(disabledSelectedIcon, ButtonBase.ops.extension_disabledSelectedIcon(res) := _)
    ifSet(displayedMnemonicIndex, ButtonBase.ops.extension_displayedMnemonicIndex(res) := _)
    ifSet(doubleBuffered, Component.ops.extension_doubleBuffered(res) := _)
    ifSet(enabled, ButtonBase.ops.extension_enabled(res) := _)
    ifSet(focusPainted, ButtonBase.ops.extension_focusPainted(res) := _)
    ifSet(focusable, Node.ops.extension_focusable(res) := _)
    ifSet(font, Node.ops.extension_font(res) := _)
    ifSet(foreground, Node.ops.extension_foreground(res) := _)
    ifSet(hideActionText, ButtonBase.ops.extension_hideActionText(res) := _)
    ifSet(horizontalAlignment, ButtonBase.ops.extension_horizontalAlignment(res) := _)
    ifSet(horizontalTextPosition, ButtonBase.ops.extension_horizontalTextPosition(res) := _)
    ifSet(icon, ButtonBase.ops.extension_icon(res) := _)
    ifSet(iconTextGap, ButtonBase.ops.extension_iconTextGap(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.extension_inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.extension_inputVerifier(res) := _)
    ifSet(label, ButtonBase.ops.extension_label(res) := _)
    ifSet(margin, ButtonBase.ops.extension_margin(res) := _)
    ifSet(maxSize, Node.ops.extension_maxSize(res) := _)
    ifSet(minSize, Node.ops.extension_minSize(res) := _)
    ifSet(mnemonic, ButtonBase.ops.extension_mnemonic(res) := _)
    ifSet(model, ButtonBase.ops.extension_model(res) := _)
    ifSet(mouseDragMut, Node.ops.extension_mouseDragMut(res) := _)
    ifSet(multiClickThreshhold, ButtonBase.ops.extension_multiClickThreshhold(res) := _)
    ifSet(name, Node.ops.extension_name(res) := _)
    ifSet(opaque, Component.ops.extension_opaque(res) := _)
    ifSet(prefSize, Node.ops.extension_prefSize(res) := _)
    ifSet(pressed, ButtonBase.ops.extension_pressed(res) := _)
    ifSet(pressedIcon, ButtonBase.ops.extension_pressedIcon(res) := _)
    ifSet(requestFocusEnabled, Component.ops.extension_requestFocusEnabled(res) := _)
    ifSet(rollover, ButtonBase.ops.extension_rollover(res) := _)
    ifSet(rolloverEnabled, ButtonBase.ops.extension_rolloverEnabled(res) := _)
    ifSet(rolloverIcon, ButtonBase.ops.extension_rolloverIcon(res) := _)
    ifSet(rolloverSelectedIcon, ButtonBase.ops.extension_rolloverSelectedIcon(res) := _)
    ifSet(selected, ButtonBase.ops.extension_selected(res) := _)
    ifSet(selectedIcon, ButtonBase.ops.extension_selectedIcon(res) := _)
    ifSet(text, ButtonBase.ops.extension_text(res) := _)
    ifSet(toolTipText, Component.ops.extension_toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.extension_transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.extension_verifyInputWhenFocusTarget(res) := _)
    ifSet(verticalAlignment, ButtonBase.ops.extension_verticalAlignment(res) := _)
    ifSet(verticalTextPosition, ButtonBase.ops.extension_verticalTextPosition(res) := _)
    ifSet(visible, Node.ops.extension_visible(res) := _)
    res
  }
  def apply(a: Action): Scenegraph ?=> RadioButton = wrap(javax.swing.JRadioButton(a.unwrap)).tap(init)
}