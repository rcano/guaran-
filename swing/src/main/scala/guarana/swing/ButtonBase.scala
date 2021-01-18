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

opaque type ButtonBase <: Component  = javax.swing.AbstractButton & Component
object ButtonBase extends VarsMap {
  val UI: SwingVar.Aux[ButtonBase, javax.swing.plaf.ButtonUI] = SwingVar[ButtonBase, javax.swing.plaf.ButtonUI]("UI", _.getUI.nn, _.setUI(_))
  val Action: SwingVar.Aux[ButtonBase, javax.swing.Action | Null] = SwingVar[ButtonBase, javax.swing.Action | Null]("action", _.getAction, _.setAction(_))
  val ActionCommand: SwingVar.Aux[ButtonBase, java.lang.String | Null] = SwingVar[ButtonBase, java.lang.String | Null]("actionCommand", _.getActionCommand, _.setActionCommand(_))
  val Armed: SwingVar.Aux[ButtonBase, Boolean] = SwingVar[ButtonBase, Boolean]("armed", _.getModel.nn.isArmed, _.getModel.nn.setArmed(_))
  val BorderPainted: SwingVar.Aux[ButtonBase, Boolean] = SwingVar[ButtonBase, Boolean]("borderPainted", _.isBorderPainted, _.setBorderPainted(_))
  val ContentAreaFilled: SwingVar.Aux[ButtonBase, Boolean] = SwingVar[ButtonBase, Boolean]("contentAreaFilled", _.isContentAreaFilled, _.setContentAreaFilled(_))
  val DisabledIcon: SwingVar.Aux[ButtonBase, javax.swing.Icon | Null] = SwingVar[ButtonBase, javax.swing.Icon | Null]("disabledIcon", _.getDisabledIcon, _.setDisabledIcon(_))
  val DisabledSelectedIcon: SwingVar.Aux[ButtonBase, javax.swing.Icon | Null] = SwingVar[ButtonBase, javax.swing.Icon | Null]("disabledSelectedIcon", _.getDisabledSelectedIcon, _.setDisabledSelectedIcon(_))
  val DisplayedMnemonicIndex: SwingVar.Aux[ButtonBase, Int] = SwingVar[ButtonBase, Int]("displayedMnemonicIndex", _.getDisplayedMnemonicIndex, _.setDisplayedMnemonicIndex(_))
  val Enabled: SwingVar.Aux[ButtonBase, Boolean] = SwingVar[ButtonBase, Boolean]("enabled", _.getModel.nn.isEnabled, _.getModel.nn.setEnabled(_))
  val FocusPainted: SwingVar.Aux[ButtonBase, Boolean] = SwingVar[ButtonBase, Boolean]("focusPainted", _.isFocusPainted, _.setFocusPainted(_))
  val HideActionText: SwingVar.Aux[ButtonBase, Boolean] = SwingVar[ButtonBase, Boolean]("hideActionText", _.getHideActionText, _.setHideActionText(_))
  val HorizontalAlignment: SwingVar.Aux[ButtonBase, Int] = SwingVar[ButtonBase, Int]("horizontalAlignment", _.getHorizontalAlignment, _.setHorizontalAlignment(_))
  val HorizontalTextPosition: SwingVar.Aux[ButtonBase, Int] = SwingVar[ButtonBase, Int]("horizontalTextPosition", _.getHorizontalTextPosition, _.setHorizontalTextPosition(_))
  val Icon: SwingVar.Aux[ButtonBase, javax.swing.Icon | Null] = SwingVar[ButtonBase, javax.swing.Icon | Null]("icon", _.getIcon, _.setIcon(_))
  val IconTextGap: SwingVar.Aux[ButtonBase, Int] = SwingVar[ButtonBase, Int]("iconTextGap", _.getIconTextGap, _.setIconTextGap(_))
  val Label: SwingVar.Aux[ButtonBase, java.lang.String | Null] = SwingVar[ButtonBase, java.lang.String | Null]("label", _.getLabel, _.setLabel(_))
  val Margin: SwingVar.Aux[ButtonBase, java.awt.Insets | Null] = SwingVar[ButtonBase, java.awt.Insets | Null]("margin", _.getMargin, _.setMargin(_))
  val Mnemonic: SwingVar.Aux[ButtonBase, Int] = SwingVar[ButtonBase, Int]("mnemonic", _.getMnemonic, _.setMnemonic(_))
  val Model: SwingVar.Aux[ButtonBase, javax.swing.ButtonModel | Null] = SwingVar[ButtonBase, javax.swing.ButtonModel | Null]("model", _.getModel, _.setModel(_))
  val MultiClickThreshhold: SwingVar.Aux[ButtonBase, Long] = SwingVar[ButtonBase, Long]("multiClickThreshhold", _.getMultiClickThreshhold, _.setMultiClickThreshhold(_))
  val Pressed: SwingVar.Aux[ButtonBase, Boolean] = SwingVar[ButtonBase, Boolean]("pressed", _.getModel.nn.isPressed, _.getModel.nn.setPressed(_))
  val PressedIcon: SwingVar.Aux[ButtonBase, javax.swing.Icon | Null] = SwingVar[ButtonBase, javax.swing.Icon | Null]("pressedIcon", _.getPressedIcon, _.setPressedIcon(_))
  val Rollover: SwingVar.Aux[ButtonBase, Boolean] = SwingVar[ButtonBase, Boolean]("rollover", _.getModel.nn.isRollover, _.getModel.nn.setRollover(_))
  val RolloverEnabled: SwingVar.Aux[ButtonBase, Boolean] = SwingVar[ButtonBase, Boolean]("rolloverEnabled", _.isRolloverEnabled, _.setRolloverEnabled(_))
  val RolloverIcon: SwingVar.Aux[ButtonBase, javax.swing.Icon | Null] = SwingVar[ButtonBase, javax.swing.Icon | Null]("rolloverIcon", _.getRolloverIcon, _.setRolloverIcon(_))
  val RolloverSelectedIcon: SwingVar.Aux[ButtonBase, javax.swing.Icon | Null] = SwingVar[ButtonBase, javax.swing.Icon | Null]("rolloverSelectedIcon", _.getRolloverSelectedIcon, _.setRolloverSelectedIcon(_))
  val Selected: SwingVar.Aux[ButtonBase, Boolean] = SwingVar[ButtonBase, Boolean]("selected", _.isSelected, _.setSelected(_))
  val SelectedIcon: SwingVar.Aux[ButtonBase, javax.swing.Icon | Null] = SwingVar[ButtonBase, javax.swing.Icon | Null]("selectedIcon", _.getSelectedIcon, _.setSelectedIcon(_))
  val Text: SwingVar.Aux[ButtonBase, java.lang.String | Null] = SwingVar[ButtonBase, java.lang.String | Null]("text", _.getText, _.setText(_))
  val VerticalAlignment: SwingVar.Aux[ButtonBase, Int] = SwingVar[ButtonBase, Int]("verticalAlignment", _.getVerticalAlignment, _.setVerticalAlignment(_))
  val VerticalTextPosition: SwingVar.Aux[ButtonBase, Int] = SwingVar[ButtonBase, Int]("verticalTextPosition", _.getVerticalTextPosition, _.setVerticalTextPosition(_))

  val ActionEvents = Emitter[java.awt.event.ActionEvent]()

  given ops: Ops.type = Ops
  object Ops {
    extension (v: ButtonBase) {
      def UI: Var.Aux[javax.swing.plaf.ButtonUI, v.type] = ButtonBase.UI.asInstanceOf[Var.Aux[javax.swing.plaf.ButtonUI, v.type]]
      def action: Var.Aux[javax.swing.Action | Null, v.type] = ButtonBase.Action.asInstanceOf[Var.Aux[javax.swing.Action | Null, v.type]]
      def actionCommand: Var.Aux[java.lang.String | Null, v.type] = ButtonBase.ActionCommand.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def armed: Var.Aux[Boolean, v.type] = ButtonBase.Armed.asInstanceOf[Var.Aux[Boolean, v.type]]
      def borderPainted: Var.Aux[Boolean, v.type] = ButtonBase.BorderPainted.asInstanceOf[Var.Aux[Boolean, v.type]]
      def contentAreaFilled: Var.Aux[Boolean, v.type] = ButtonBase.ContentAreaFilled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def disabledIcon: Var.Aux[javax.swing.Icon | Null, v.type] = ButtonBase.DisabledIcon.asInstanceOf[Var.Aux[javax.swing.Icon | Null, v.type]]
      def disabledSelectedIcon: Var.Aux[javax.swing.Icon | Null, v.type] = ButtonBase.DisabledSelectedIcon.asInstanceOf[Var.Aux[javax.swing.Icon | Null, v.type]]
      def displayedMnemonicIndex: Var.Aux[Int, v.type] = ButtonBase.DisplayedMnemonicIndex.asInstanceOf[Var.Aux[Int, v.type]]
      def enabled: Var.Aux[Boolean, v.type] = ButtonBase.Enabled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def focusPainted: Var.Aux[Boolean, v.type] = ButtonBase.FocusPainted.asInstanceOf[Var.Aux[Boolean, v.type]]
      def hideActionText: Var.Aux[Boolean, v.type] = ButtonBase.HideActionText.asInstanceOf[Var.Aux[Boolean, v.type]]
      def horizontalAlignment: Var.Aux[Int, v.type] = ButtonBase.HorizontalAlignment.asInstanceOf[Var.Aux[Int, v.type]]
      def horizontalTextPosition: Var.Aux[Int, v.type] = ButtonBase.HorizontalTextPosition.asInstanceOf[Var.Aux[Int, v.type]]
      def icon: Var.Aux[javax.swing.Icon | Null, v.type] = ButtonBase.Icon.asInstanceOf[Var.Aux[javax.swing.Icon | Null, v.type]]
      def iconTextGap: Var.Aux[Int, v.type] = ButtonBase.IconTextGap.asInstanceOf[Var.Aux[Int, v.type]]
      def label: Var.Aux[java.lang.String | Null, v.type] = ButtonBase.Label.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def margin: Var.Aux[java.awt.Insets | Null, v.type] = ButtonBase.Margin.asInstanceOf[Var.Aux[java.awt.Insets | Null, v.type]]
      def mnemonic: Var.Aux[Int, v.type] = ButtonBase.Mnemonic.asInstanceOf[Var.Aux[Int, v.type]]
      def model: Var.Aux[javax.swing.ButtonModel | Null, v.type] = ButtonBase.Model.asInstanceOf[Var.Aux[javax.swing.ButtonModel | Null, v.type]]
      def multiClickThreshhold: Var.Aux[Long, v.type] = ButtonBase.MultiClickThreshhold.asInstanceOf[Var.Aux[Long, v.type]]
      def pressed: Var.Aux[Boolean, v.type] = ButtonBase.Pressed.asInstanceOf[Var.Aux[Boolean, v.type]]
      def pressedIcon: Var.Aux[javax.swing.Icon | Null, v.type] = ButtonBase.PressedIcon.asInstanceOf[Var.Aux[javax.swing.Icon | Null, v.type]]
      def rollover: Var.Aux[Boolean, v.type] = ButtonBase.Rollover.asInstanceOf[Var.Aux[Boolean, v.type]]
      def rolloverEnabled: Var.Aux[Boolean, v.type] = ButtonBase.RolloverEnabled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def rolloverIcon: Var.Aux[javax.swing.Icon | Null, v.type] = ButtonBase.RolloverIcon.asInstanceOf[Var.Aux[javax.swing.Icon | Null, v.type]]
      def rolloverSelectedIcon: Var.Aux[javax.swing.Icon | Null, v.type] = ButtonBase.RolloverSelectedIcon.asInstanceOf[Var.Aux[javax.swing.Icon | Null, v.type]]
      def selected: Var.Aux[Boolean, v.type] = ButtonBase.Selected.asInstanceOf[Var.Aux[Boolean, v.type]]
      def selectedIcon: Var.Aux[javax.swing.Icon | Null, v.type] = ButtonBase.SelectedIcon.asInstanceOf[Var.Aux[javax.swing.Icon | Null, v.type]]
      def text: Var.Aux[java.lang.String | Null, v.type] = ButtonBase.Text.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def verticalAlignment: Var.Aux[Int, v.type] = ButtonBase.VerticalAlignment.asInstanceOf[Var.Aux[Int, v.type]]
      def verticalTextPosition: Var.Aux[Int, v.type] = ButtonBase.VerticalTextPosition.asInstanceOf[Var.Aux[Int, v.type]]

      def actionEvents: Emitter.Aux[java.awt.event.ActionEvent, v.type] = ButtonBase.ActionEvents.forInstance(v)

      def actionListeners = v.getActionListeners
      def changeListeners = v.getChangeListeners
      def itemListeners = v.getItemListeners
      def selectedObjects = v.getSelectedObjects
      def unwrap: javax.swing.AbstractButton = v
    }
  }

  def wrap(v: javax.swing.AbstractButton) = v.asInstanceOf[ButtonBase]

  def init(v: ButtonBase): Scenegraph ?=> Unit = (sc: Scenegraph) ?=> {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    val m = v.getModel.nn
    var wasArmed = m.isArmed
    var wasEnabled = m.isEnabled
    var wasPressed = m.isPressed
    var wasRollover = m.isRollover
    var wasSelected = v.isSelected
    val cl: javax.swing.event.ChangeListener = evt => sc.update {
      val ctx = summon[VarContext]
      val m = v.getModel.nn
      if (m.isArmed != wasArmed)
        ctx.swingPropertyUpdated(ops.armed(v), m.isArmed)
      wasArmed = m.isArmed
      if (m.isEnabled != wasEnabled)
        ctx.swingPropertyUpdated(ops.enabled(v), m.isEnabled)
      wasEnabled = m.isEnabled
      if (m.isPressed != wasPressed)
        ctx.swingPropertyUpdated(ops.pressed(v), m.isPressed)
      wasPressed = m.isPressed
      if (m.isRollover != wasRollover)
        ctx.swingPropertyUpdated(ops.rollover(v), m.isRollover)
      wasRollover = m.isRollover
      if (v.isSelected != wasSelected)
        ctx.swingPropertyUpdated(ops.selected(v), v.isSelected)
      wasSelected = v.isSelected
    }
    v.addChangeListener(cl)
    val al: java.awt.event.ActionListener = evt => sc.update(summon[Emitter.Context].emit(v.actionEvents, evt.nn))
    v.addActionListener(al)
  }
  
  
}