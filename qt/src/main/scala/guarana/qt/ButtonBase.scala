
package guarana
package qt
        
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type ButtonBase <: Widget  = io.qt.widgets.QAbstractButton & Widget
object ButtonBase {
  private val ButtonBaseInitialized: Var[Boolean] = Var[Boolean]("ButtonBaseInitialized", false, false)
  val AutoExclusive: ExternalVar.Aux[ButtonBase, Boolean] = ExternalVar[ButtonBase, Boolean]("autoExclusive", _.autoExclusive(), _.setAutoExclusive(_), true)
  val AutoRepeat: ExternalVar.Aux[ButtonBase, Boolean] = ExternalVar[ButtonBase, Boolean]("autoRepeat", _.autoRepeat(), _.setAutoRepeat(_), true)
  val AutoRepeatDelay: ExternalVar.Aux[ButtonBase, Int] = ExternalVar[ButtonBase, Int]("autoRepeatDelay", _.autoRepeatDelay(), _.setAutoRepeatDelay(_), true)
  val AutoRepeatInterval: ExternalVar.Aux[ButtonBase, Int] = ExternalVar[ButtonBase, Int]("autoRepeatInterval", _.autoRepeatInterval(), _.setAutoRepeatInterval(_), true)
  val Checkable: ExternalVar.Aux[ButtonBase, Boolean] = ExternalVar[ButtonBase, Boolean]("checkable", _.isCheckable(), _.setCheckable(_), true)
  val Checked: ExternalVar.Aux[ButtonBase, Boolean] = ExternalVar[ButtonBase, Boolean]("checked", _.isChecked(), _.setChecked(_), true)
  val Down: ExternalVar.Aux[ButtonBase, Boolean] = ExternalVar[ButtonBase, Boolean]("down", _.isDown(), _.setDown(_), true)
  val Icon: ExternalVar.Aux[ButtonBase, io.qt.gui.QIcon | Null] = ExternalVar[ButtonBase, io.qt.gui.QIcon | Null]("icon", _.icon(), _.setIcon(_), true)
  val IconSize: ExternalVar.Aux[ButtonBase, io.qt.core.QSize | Null] = ExternalVar[ButtonBase, io.qt.core.QSize | Null]("iconSize", _.iconSize(), _.setIconSize(_), true)
  val Shortcut: ExternalVar.Aux[ButtonBase, io.qt.gui.QKeySequence | Null] = ExternalVar[ButtonBase, io.qt.gui.QKeySequence | Null]("shortcut", _.shortcut(), _.setShortcut(_), true)
  val Text: ExternalVar.Aux[ButtonBase, java.lang.String | Null] = ExternalVar[ButtonBase, java.lang.String | Null]("text", _.text(), _.setText(_), true)

  val Clicked = Emitter[Unit]()

  given ops: Ops.type = Ops
  object Ops {
    extension (v: ButtonBase) {
      def autoExclusive: Var.Aux[Boolean, v.type] = ButtonBase.AutoExclusive.asInstanceOf[Var.Aux[Boolean, v.type]]
      def autoRepeat: Var.Aux[Boolean, v.type] = ButtonBase.AutoRepeat.asInstanceOf[Var.Aux[Boolean, v.type]]
      def autoRepeatDelay: Var.Aux[Int, v.type] = ButtonBase.AutoRepeatDelay.asInstanceOf[Var.Aux[Int, v.type]]
      def autoRepeatInterval: Var.Aux[Int, v.type] = ButtonBase.AutoRepeatInterval.asInstanceOf[Var.Aux[Int, v.type]]
      def checkable: Var.Aux[Boolean, v.type] = ButtonBase.Checkable.asInstanceOf[Var.Aux[Boolean, v.type]]
      def checked: Var.Aux[Boolean, v.type] = ButtonBase.Checked.asInstanceOf[Var.Aux[Boolean, v.type]]
      def down: Var.Aux[Boolean, v.type] = ButtonBase.Down.asInstanceOf[Var.Aux[Boolean, v.type]]
      def icon: Var.Aux[io.qt.gui.QIcon | Null, v.type] = ButtonBase.Icon.asInstanceOf[Var.Aux[io.qt.gui.QIcon | Null, v.type]]
      def iconSize: Var.Aux[io.qt.core.QSize | Null, v.type] = ButtonBase.IconSize.asInstanceOf[Var.Aux[io.qt.core.QSize | Null, v.type]]
      def shortcut: Var.Aux[io.qt.gui.QKeySequence | Null, v.type] = ButtonBase.Shortcut.asInstanceOf[Var.Aux[io.qt.gui.QKeySequence | Null, v.type]]
      def text: Var.Aux[java.lang.String | Null, v.type] = ButtonBase.Text.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]

      def clicked: Emitter.Aux[Unit, v.type] = ButtonBase.Clicked.forInstance(v)

      def setDisabled(arg0: Boolean) = v.setDisabled(arg0)
      def setHidden(arg0: Boolean) = v.setHidden(arg0)
      def grab(arg0: io.qt.core.QRect | Null) = v.grab(arg0)
      def setIconSize(arg0: io.qt.core.QSize | Null) = v.setIconSize(arg0)
      def animateClick() = v.animateClick()
      def click() = v.click()
      def toggle() = v.toggle()
      def setChecked(arg0: Boolean) = v.setChecked(arg0)
      def unwrap: io.qt.widgets.QAbstractButton = v
    }
  }

  def wrap(v: io.qt.widgets.QAbstractButton): ButtonBase = 
    val res = v.asInstanceOf[ButtonBase]
    if !Toolkit.stateReader(ButtonBaseInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: ButtonBase): Unit = {
    Widget.init(v)
    Toolkit.update(ButtonBaseInitialized.forInstance[v.type] := true)
    v.pressed.nn.connect(slot(Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.down(v), Some(false)))))
    v.released.nn.connect(slot(Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.down(v), Some(true)))))
    v.toggled.nn.connect(slot((t: java.lang.Boolean) => Toolkit.update(summon[VarContext].externalPropertyUpdated(Ops.checked(v), Some(t)))))
    v.clicked.nn.connect(slot(Toolkit.update(summon[Emitter.Context].emit(Ops.clicked(v), ()))))
  }
  
  
}
        