//AUTOGENERATED FILE, DO NOT MODIFY

package guarana.swing

import language.implicitConversions
import java.awt.{Component => _, MenuBar => _, MenuItem => _, TextComponent => _, TextField => _, PopupMenu => _, *}
import java.awt.event.*
import javax.swing.{Action => _, *}
import javax.swing.event.*
import guarana.swing.util.*
import scala.jdk.CollectionConverters.*
import scala.util.chaining.*

opaque type Label <: Component  = javax.swing.JLabel & Component
object Label extends VarsMap {
  val UI: SwingVar.Aux[Label, javax.swing.plaf.LabelUI] = SwingVar[Label, javax.swing.plaf.LabelUI]("UI", _.getUI.nn, _.setUI(_))
  val DisabledIcon: SwingVar.Aux[Label, javax.swing.Icon | Null] = SwingVar[Label, javax.swing.Icon | Null]("disabledIcon", _.getDisabledIcon, _.setDisabledIcon(_))
  val DisplayedMnemonic: SwingVar.Aux[Label, Int] = SwingVar[Label, Int]("displayedMnemonic", _.getDisplayedMnemonic, _.setDisplayedMnemonic(_))
  val DisplayedMnemonicIndex: SwingVar.Aux[Label, Int] = SwingVar[Label, Int]("displayedMnemonicIndex", _.getDisplayedMnemonicIndex, _.setDisplayedMnemonicIndex(_))
  val HorizontalAlignment: SwingVar.Aux[Label, Int] = SwingVar[Label, Int]("horizontalAlignment", _.getHorizontalAlignment, _.setHorizontalAlignment(_))
  val HorizontalTextPosition: SwingVar.Aux[Label, Int] = SwingVar[Label, Int]("horizontalTextPosition", _.getHorizontalTextPosition, _.setHorizontalTextPosition(_))
  val Icon: SwingVar.Aux[Label, javax.swing.Icon | Null] = SwingVar[Label, javax.swing.Icon | Null]("icon", _.getIcon, _.setIcon(_))
  val IconTextGap: SwingVar.Aux[Label, Double] = SwingVar[Label, Double]("iconTextGap", _.getIconTextGap, (l, g) => l.setIconTextGap(g.toInt))
  val LabelFor: SwingVar.Aux[Label, java.awt.Component | Null] = SwingVar[Label, java.awt.Component | Null]("labelFor", _.getLabelFor, _.setLabelFor(_))
  val Text: SwingVar.Aux[Label, java.lang.String | Null] = SwingVar[Label, java.lang.String | Null]("text", _.getText, _.setText(_))
  val VerticalAlignment: SwingVar.Aux[Label, Int] = SwingVar[Label, Int]("verticalAlignment", _.getVerticalAlignment, _.setVerticalAlignment(_))
  val VerticalTextPosition: SwingVar.Aux[Label, Int] = SwingVar[Label, Int]("verticalTextPosition", _.getVerticalTextPosition, _.setVerticalTextPosition(_))

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: Label) {
      def UI: Var.Aux[javax.swing.plaf.LabelUI, v.type] = Label.UI.asInstanceOf[Var.Aux[javax.swing.plaf.LabelUI, v.type]]
      def disabledIcon: Var.Aux[javax.swing.Icon | Null, v.type] = Label.DisabledIcon.asInstanceOf[Var.Aux[javax.swing.Icon | Null, v.type]]
      def displayedMnemonic: Var.Aux[Int, v.type] = Label.DisplayedMnemonic.asInstanceOf[Var.Aux[Int, v.type]]
      def displayedMnemonicIndex: Var.Aux[Int, v.type] = Label.DisplayedMnemonicIndex.asInstanceOf[Var.Aux[Int, v.type]]
      def horizontalAlignment: Var.Aux[Int, v.type] = Label.HorizontalAlignment.asInstanceOf[Var.Aux[Int, v.type]]
      def horizontalTextPosition: Var.Aux[Int, v.type] = Label.HorizontalTextPosition.asInstanceOf[Var.Aux[Int, v.type]]
      def icon: Var.Aux[javax.swing.Icon | Null, v.type] = Label.Icon.asInstanceOf[Var.Aux[javax.swing.Icon | Null, v.type]]
      def iconTextGap: Var.Aux[Double, v.type] = Label.IconTextGap.asInstanceOf[Var.Aux[Double, v.type]]
      def labelFor: Var.Aux[java.awt.Component | Null, v.type] = Label.LabelFor.asInstanceOf[Var.Aux[java.awt.Component | Null, v.type]]
      def text: Var.Aux[java.lang.String | Null, v.type] = Label.Text.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def verticalAlignment: Var.Aux[Int, v.type] = Label.VerticalAlignment.asInstanceOf[Var.Aux[Int, v.type]]
      def verticalTextPosition: Var.Aux[Int, v.type] = Label.VerticalTextPosition.asInstanceOf[Var.Aux[Int, v.type]]

      

      
      def unwrap: javax.swing.JLabel = v
    }
  }

  def wrap(v: javax.swing.JLabel) = v.asInstanceOf[Label]

  def init(v: Label): Scenegraph ?=> Unit = (sc: Scenegraph) ?=> {
    Component.init(v)
    v.addPropertyChangeListener(varsPropertyListener(v))
    
    
  }
  def uninitialized(): Label = {
    val res = javax.swing.JLabel().asInstanceOf[Label]
    
    res
  }
  
  def apply(
    
    UI: Opt[Binding[javax.swing.plaf.LabelUI]] = UnsetParam,
    actionMap: Opt[Binding[javax.swing.ActionMap]] = UnsetParam,
    alignmentX: Opt[Binding[Float]] = UnsetParam,
    alignmentY: Opt[Binding[Float]] = UnsetParam,
    autoscrolls: Opt[Binding[Boolean]] = UnsetParam,
    background: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    border: Opt[Binding[javax.swing.border.Border | Null]] = UnsetParam,
    bounds: Opt[Binding[Bounds]] = UnsetParam,
    componentOrientation: Opt[Binding[java.awt.ComponentOrientation]] = UnsetParam,
    componentPopupMenu: Opt[Binding[PopupMenu | Null]] = UnsetParam,
    cursor: Opt[Binding[java.awt.Cursor | Null]] = UnsetParam,
    debugGraphicsOptions: Opt[Binding[Int]] = UnsetParam,
    disabledIcon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    displayedMnemonic: Opt[Binding[Int]] = UnsetParam,
    displayedMnemonicIndex: Opt[Binding[Int]] = UnsetParam,
    doubleBuffered: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    font: Opt[Binding[java.awt.Font | Null]] = UnsetParam,
    foreground: Opt[Binding[java.awt.Color | Null]] = UnsetParam,
    horizontalAlignment: Opt[Binding[Int]] = UnsetParam,
    horizontalTextPosition: Opt[Binding[Int]] = UnsetParam,
    icon: Opt[Binding[javax.swing.Icon | Null]] = UnsetParam,
    iconTextGap: Opt[Binding[Double]] = UnsetParam,
    inheritsPopupMenu: Opt[Binding[Boolean]] = UnsetParam,
    inputVerifier: Opt[Binding[javax.swing.InputVerifier | Null]] = UnsetParam,
    labelFor: Opt[Binding[java.awt.Component | Null]] = UnsetParam,
    maxSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    minSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    opaque: Opt[Binding[Boolean]] = UnsetParam,
    prefSize: Opt[Binding[(Double, Double) | Null]] = UnsetParam,
    requestFocusEnabled: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipText: Opt[Binding[String | Null]] = UnsetParam,
    transferHandler: Opt[Binding[javax.swing.TransferHandler | Null]] = UnsetParam,
    verifyInputWhenFocusTarget: Opt[Binding[Boolean]] = UnsetParam,
    verticalAlignment: Opt[Binding[Int]] = UnsetParam,
    verticalTextPosition: Opt[Binding[Int]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): Scenegraph ?=> VarContextAction[Label] = {
    val res = uninitialized()
    Label.init(res)
    ifSet(UI, Label.ops.UI(res) := _)
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
    ifSet(disabledIcon, Label.ops.disabledIcon(res) := _)
    ifSet(displayedMnemonic, Label.ops.displayedMnemonic(res) := _)
    ifSet(displayedMnemonicIndex, Label.ops.displayedMnemonicIndex(res) := _)
    ifSet(doubleBuffered, Component.ops.doubleBuffered(res) := _)
    ifSet(enabled, Node.ops.enabled(res) := _)
    ifSet(focusable, Node.ops.focusable(res) := _)
    ifSet(font, Node.ops.font(res) := _)
    ifSet(foreground, Node.ops.foreground(res) := _)
    ifSet(horizontalAlignment, Label.ops.horizontalAlignment(res) := _)
    ifSet(horizontalTextPosition, Label.ops.horizontalTextPosition(res) := _)
    ifSet(icon, Label.ops.icon(res) := _)
    ifSet(iconTextGap, Label.ops.iconTextGap(res) := _)
    ifSet(inheritsPopupMenu, Component.ops.inheritsPopupMenu(res) := _)
    ifSet(inputVerifier, Component.ops.inputVerifier(res) := _)
    ifSet(labelFor, Label.ops.labelFor(res) := _)
    ifSet(maxSize, Node.ops.maxSize(res) := _)
    ifSet(minSize, Node.ops.minSize(res) := _)
    ifSet(name, Node.ops.name(res) := _)
    ifSet(opaque, Component.ops.opaque(res) := _)
    ifSet(prefSize, Node.ops.prefSize(res) := _)
    ifSet(requestFocusEnabled, Component.ops.requestFocusEnabled(res) := _)
    ifSet(text, Label.ops.text(res) := _)
    ifSet(toolTipText, Component.ops.toolTipText(res) := _)
    ifSet(transferHandler, Component.ops.transferHandler(res) := _)
    ifSet(verifyInputWhenFocusTarget, Component.ops.verifyInputWhenFocusTarget(res) := _)
    ifSet(verticalAlignment, Label.ops.verticalAlignment(res) := _)
    ifSet(verticalTextPosition, Label.ops.verticalTextPosition(res) := _)
    ifSet(visible, Node.ops.visible(res) := _)
    res
  }
  
}