package guarana.swing

import guarana.swing.util._
import java.awt.{Component => _, _}
import javax.swing.JButton

opaque type ButtonBase <: Component = javax.swing.AbstractButton & Component
object ButtonBase extends VarsMap {
  val UI = SwingVar[ButtonBase, javax.swing.plaf.ButtonUI]("UI", _.getUI.nn, _.setUI(_))
  val Action = SwingVar[ButtonBase, javax.swing.Action | Null]("action", _.getAction, _.setAction(_))
  val ActionCommand = SwingVar[ButtonBase, java.lang.String | Null]("actionCommand", _.getActionCommand, _.setActionCommand(_))
  val BorderPainted = SwingVar[ButtonBase, Boolean]("borderPainted", _.isBorderPainted, _.setBorderPainted(_))
  val ContentAreaFilled = SwingVar[ButtonBase, Boolean]("contentAreaFilled", _.isContentAreaFilled, _.setContentAreaFilled(_))
  val DisabledIcon = SwingVar[ButtonBase, javax.swing.Icon | Null]("disabledIcon", _.getDisabledIcon, _.setDisabledIcon(_))
  val DisabledSelectedIcon = SwingVar[ButtonBase, javax.swing.Icon | Null]("disabledSelectedIcon", _.getDisabledSelectedIcon, _.setDisabledSelectedIcon(_))
  val DisplayedMnemonicIndex = SwingVar[ButtonBase, Int]("displayedMnemonicIndex", _.getDisplayedMnemonicIndex, _.setDisplayedMnemonicIndex(_))
  val FocusPainted = SwingVar[ButtonBase, Boolean]("focusPainted", _.isFocusPainted, _.setFocusPainted(_))
  val HideActionText = SwingVar[ButtonBase, Boolean]("hideActionText", _.getHideActionText, _.setHideActionText(_))
  val HorizontalAlignment = SwingVar[ButtonBase, Int]("horizontalAlignment", _.getHorizontalAlignment, _.setHorizontalAlignment(_))
  val HorizontalTextPosition = SwingVar[ButtonBase, Int]("horizontalTextPosition", _.getHorizontalTextPosition, _.setHorizontalTextPosition(_))
  val Icon = SwingVar[ButtonBase, javax.swing.Icon | Null]("icon", _.getIcon, _.setIcon(_))
  val IconTextGap = SwingVar[ButtonBase, Int]("iconTextGap", _.getIconTextGap, _.setIconTextGap(_))
  val Label = SwingVar[ButtonBase, java.lang.String | Null]("label", _.getLabel, _.setLabel(_))
  val Margin = SwingVar[ButtonBase, java.awt.Insets | Null]("margin", _.getMargin, _.setMargin(_))
  val Mnemonic = SwingVar[ButtonBase, Int]("mnemonic", _.getMnemonic, _.setMnemonic(_))
  val Model = SwingVar[ButtonBase, javax.swing.ButtonModel | Null]("model", _.getModel, _.setModel(_))
  val MultiClickThreshhold = SwingVar[ButtonBase, Long]("multiClickThreshhold", _.getMultiClickThreshhold, _.setMultiClickThreshhold(_))
  val PressedIcon = SwingVar[ButtonBase, javax.swing.Icon | Null]("pressedIcon", _.getPressedIcon, _.setPressedIcon(_))
  val RolloverEnabled = SwingVar[ButtonBase, Boolean]("rolloverEnabled", _.isRolloverEnabled, _.setRolloverEnabled(_))
  val RolloverIcon = SwingVar[ButtonBase, javax.swing.Icon | Null]("rolloverIcon", _.getRolloverIcon, _.setRolloverIcon(_))
  val RolloverSelectedIcon = SwingVar[ButtonBase, javax.swing.Icon | Null]("rolloverSelectedIcon", _.getRolloverSelectedIcon, _.setRolloverSelectedIcon(_))
  val Selected = SwingVar[ButtonBase, Boolean]("selected", _.isSelected, _.setSelected(_))
  val SelectedIcon = SwingVar[ButtonBase, javax.swing.Icon | Null]("selectedIcon", _.getSelectedIcon, _.setSelectedIcon(_))
  val Text = SwingVar[ButtonBase, java.lang.String | Null]("text", _.getText, _.setText(_))
  val VerticalAlignment = SwingVar[ButtonBase, Int]("verticalAlignment", _.getVerticalAlignment, _.setVerticalAlignment(_))
  val VerticalTextPosition = SwingVar[ButtonBase, Int]("verticalTextPosition", _.getVerticalTextPosition, _.setVerticalTextPosition(_))

  val ActionEvents = Emitter[java.awt.event.ActionEvent]()

  given ops: (v: ButtonBase) extended with {
    def UI = ButtonBase.UI.forInstance(v)
    def action = ButtonBase.Action.forInstance(v)
    def actionCommand = ButtonBase.ActionCommand.forInstance(v)
    def borderPainted = ButtonBase.BorderPainted.forInstance(v)
    def contentAreaFilled = ButtonBase.ContentAreaFilled.forInstance(v)
    def disabledIcon = ButtonBase.DisabledIcon.forInstance(v)
    def disabledSelectedIcon = ButtonBase.DisabledSelectedIcon.forInstance(v)
    def displayedMnemonicIndex = ButtonBase.DisplayedMnemonicIndex.forInstance(v)
    def focusPainted = ButtonBase.FocusPainted.forInstance(v)
    def hideActionText = ButtonBase.HideActionText.forInstance(v)
    def horizontalAlignment = ButtonBase.HorizontalAlignment.forInstance(v)
    def horizontalTextPosition = ButtonBase.HorizontalTextPosition.forInstance(v)
    def icon = ButtonBase.Icon.forInstance(v)
    def iconTextGap = ButtonBase.IconTextGap.forInstance(v)
    def label = ButtonBase.Label.forInstance(v)
    def margin = ButtonBase.Margin.forInstance(v)
    def mnemonic = ButtonBase.Mnemonic.forInstance(v)
    def model = ButtonBase.Model.forInstance(v)
    def multiClickThreshhold = ButtonBase.MultiClickThreshhold.forInstance(v)
    def pressedIcon = ButtonBase.PressedIcon.forInstance(v)
    def rolloverEnabled = ButtonBase.RolloverEnabled.forInstance(v)
    def rolloverIcon = ButtonBase.RolloverIcon.forInstance(v)
    def rolloverSelectedIcon = ButtonBase.RolloverSelectedIcon.forInstance(v)
    def selected = ButtonBase.Selected.forInstance(v)
    def selectedIcon = ButtonBase.SelectedIcon.forInstance(v)
    def text = ButtonBase.Text.forInstance(v)
    def verticalAlignment = ButtonBase.VerticalAlignment.forInstance(v)
    def verticalTextPosition = ButtonBase.VerticalTextPosition.forInstance(v)

    def actionEvents = ButtonBase.ActionEvents.forInstance(v)
     
    def actionListeners = v.getActionListeners
    def changeListeners = v.getChangeListeners
    def itemListeners = v.getItemListeners
    def selectedObjects = v.getSelectedObjects
    def unwrap: javax.swing.AbstractButton = v
  }

  def apply(v: javax.swing.AbstractButton) = v.asInstanceOf[ButtonBase]

  def init(v: ButtonBase): (given Scenegraph) => Unit = (given sc: Scenegraph) => {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    val al: java.awt.event.ActionListener = evt => sc.update(summon[Emitter.Context].emit(v.actionEvents, evt.nn))
    v.addActionListener(al)
  }
  
}
