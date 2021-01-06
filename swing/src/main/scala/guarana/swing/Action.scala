package guarana.swing

import javax.swing.{Action => JAction, Icon, KeyStroke}
import util._

opaque type Action = javax.swing.AbstractAction
object Action extends VarsMap {
  val Accelerator = customProperty[KeyStroke](JAction.ACCELERATOR_KEY)
  val ActionCommand = customProperty[Any](JAction.ACTION_COMMAND_KEY)
  val DisplayedMnemonicIndex = customProperty[Int](JAction.DISPLAYED_MNEMONIC_INDEX_KEY)
  val Enabled = SwingVar[Action, Boolean]("enabled", _.isEnabled, _.setEnabled(_))
  val LargeIcon = customProperty[Icon](JAction.LARGE_ICON_KEY)
  val Tooltip = customProperty[String](JAction.SHORT_DESCRIPTION)
  val Mnemonic = customProperty[Int](JAction.MNEMONIC_KEY)
  val Name = customProperty[String](JAction.NAME)
  val Selected = customProperty[Boolean](JAction.SELECTED_KEY)
  val SmallIcon = customProperty[Icon](JAction.SMALL_ICON)

  /** Use this method to obtain a Var view over a property in the Action */
  def customProperty[T](name: String): SwingVar.Aux[Action, T | Null] =
    SwingVar[Action, T | Null](name, _.getValue(name).asInstanceOf[T | Null], _.putValue(name, _))

  given ops as Ops.type = Ops
  object Ops {
    extension (v: Action) {
      def accelerator = Accelerator.forInstance(v)
      def actionCommand = ActionCommand.forInstance(v)
      def displayedMnemonicIndex = DisplayedMnemonicIndex.forInstance(v)
      def enabled = Enabled.forInstance(v)
      def largeIcon = LargeIcon.forInstance(v)
      def tooltip = Tooltip.forInstance(v)
      def mnemonic = Mnemonic.forInstance(v)
      def name = Name.forInstance(v)
      def selected = Selected.forInstance(v)
      def smallIcon = SmallIcon.forInstance(v)

      def unwrap: JAction = v
    }
  }


  def init(v: Action): Scenegraph ?=> Unit = (using sc: Scenegraph) =>
    v.addPropertyChangeListener(varsPropertyListener(v))
  def wrap(v: JAction): Action = v.asInstanceOf[Action]

  def apply(
    accelerator: Opt[Binding[KeyStroke | Null]] = UnsetParam,
    actionCommand: Opt[Binding[Any | Null]] = UnsetParam,
    displayedMnemonicIndex: Opt[Binding[Int | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    largeIcon: Opt[Binding[Icon | Null]] = UnsetParam,
    tooltip: Opt[Binding[String | Null]] = UnsetParam,
    mnemonic: Opt[Binding[Int | Null]] = UnsetParam,
    name: Opt[Binding[String | Null]] = UnsetParam,
    selected: Opt[Binding[Boolean | Null]] = UnsetParam,
    smallIcon: Opt[Binding[Icon | Null]] = UnsetParam,
  )(onAction: Scenegraph.ContextAction[java.awt.event.ActionEvent => Any]): Scenegraph ?=> VarContextAction[Action] = {
    val res = wrap(new javax.swing.AbstractAction() {
      override def actionPerformed(evt: java.awt.event.ActionEvent) =
        summon[Scenegraph].update(onAction(evt.asInstanceOf))
    })
    init(res)
    ifSet(accelerator, res.accelerator := _)
    ifSet(actionCommand, res.actionCommand := _)
    ifSet(displayedMnemonicIndex, res.displayedMnemonicIndex := _)
    ifSet(enabled, Ops.enabled(res) := _)
    ifSet(largeIcon, res.largeIcon := _)
    ifSet(tooltip, res.tooltip := _)
    ifSet(mnemonic, res.mnemonic := _)
    ifSet(name, res.name := _)
    ifSet(selected, res.selected := _)
    ifSet(smallIcon, res.smallIcon := _)
    res
  }
}