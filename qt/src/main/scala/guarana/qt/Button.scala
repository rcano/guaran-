
package guarana
package qt
        
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type Button <: ButtonBase  = io.qt.widgets.QPushButton & ButtonBase
object Button {
  private val ButtonInitialized: Var[Boolean] = Var[Boolean]("ButtonInitialized", false, false)
  val AutoDefault: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("autoDefault", _.autoDefault(), _.setAutoDefault(_), true)
  val AutoExclusive: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("autoExclusive", _.autoExclusive(), _.setAutoExclusive(_), true)
  val AutoRepeat: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("autoRepeat", _.autoRepeat(), _.setAutoRepeat(_), true)
  val AutoRepeatDelay: ExternalVar.Aux[Button, Int] = ExternalVar[Button, Int]("autoRepeatDelay", _.autoRepeatDelay(), _.setAutoRepeatDelay(_), true)
  val AutoRepeatInterval: ExternalVar.Aux[Button, Int] = ExternalVar[Button, Int]("autoRepeatInterval", _.autoRepeatInterval(), _.setAutoRepeatInterval(_), true)
  val Checkable: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("checkable", _.isCheckable(), _.setCheckable(_), true)
  val Checked: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("checked", _.isChecked(), _.setChecked(_), true)
  val Default: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("default", _.isDefault(), _.setDefault(_), true)
  val Down: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("down", _.isDown(), _.setDown(_), true)
  val Flat: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("flat", _.isFlat(), _.setFlat(_), true)
  val Icon: ExternalVar.Aux[Button, io.qt.gui.QIcon | Null] = ExternalVar[Button, io.qt.gui.QIcon | Null]("icon", _.icon(), _.setIcon(_), true)
  val IconSize: ExternalVar.Aux[Button, io.qt.core.QSize | Null] = ExternalVar[Button, io.qt.core.QSize | Null]("iconSize", _.iconSize(), _.setIconSize(_), true)
  val Shortcut: ExternalVar.Aux[Button, io.qt.gui.QKeySequence | Null] = ExternalVar[Button, io.qt.gui.QKeySequence | Null]("shortcut", _.shortcut(), _.setShortcut(_), true)
  val Text: ExternalVar.Aux[Button, java.lang.String | Null] = ExternalVar[Button, java.lang.String | Null]("text", _.text(), _.setText(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: Button) {
      def autoDefault: Var.Aux[Boolean, v.type] = Button.AutoDefault.asInstanceOf[Var.Aux[Boolean, v.type]]
      def autoExclusive: Var.Aux[Boolean, v.type] = Button.AutoExclusive.asInstanceOf[Var.Aux[Boolean, v.type]]
      def autoRepeat: Var.Aux[Boolean, v.type] = Button.AutoRepeat.asInstanceOf[Var.Aux[Boolean, v.type]]
      def autoRepeatDelay: Var.Aux[Int, v.type] = Button.AutoRepeatDelay.asInstanceOf[Var.Aux[Int, v.type]]
      def autoRepeatInterval: Var.Aux[Int, v.type] = Button.AutoRepeatInterval.asInstanceOf[Var.Aux[Int, v.type]]
      def checkable: Var.Aux[Boolean, v.type] = Button.Checkable.asInstanceOf[Var.Aux[Boolean, v.type]]
      def checked: Var.Aux[Boolean, v.type] = Button.Checked.asInstanceOf[Var.Aux[Boolean, v.type]]
      def default: Var.Aux[Boolean, v.type] = Button.Default.asInstanceOf[Var.Aux[Boolean, v.type]]
      def down: Var.Aux[Boolean, v.type] = Button.Down.asInstanceOf[Var.Aux[Boolean, v.type]]
      def flat: Var.Aux[Boolean, v.type] = Button.Flat.asInstanceOf[Var.Aux[Boolean, v.type]]
      def icon: Var.Aux[io.qt.gui.QIcon | Null, v.type] = Button.Icon.asInstanceOf[Var.Aux[io.qt.gui.QIcon | Null, v.type]]
      def iconSize: Var.Aux[io.qt.core.QSize | Null, v.type] = Button.IconSize.asInstanceOf[Var.Aux[io.qt.core.QSize | Null, v.type]]
      def shortcut: Var.Aux[io.qt.gui.QKeySequence | Null, v.type] = Button.Shortcut.asInstanceOf[Var.Aux[io.qt.gui.QKeySequence | Null, v.type]]
      def text: Var.Aux[java.lang.String | Null, v.type] = Button.Text.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]

      

      def setDisabled(arg0: Boolean) = v.setDisabled(arg0)
      def setHidden(arg0: Boolean) = v.setHidden(arg0)
      def grab(arg0: io.qt.core.QRect | Null) = v.grab(arg0)
      def setIconSize(arg0: io.qt.core.QSize | Null) = v.setIconSize(arg0)
      def animateClick(arg0: Int) = v.animateClick(arg0)
      def animateClick() = v.animateClick()
      def click() = v.click()
      def toggle() = v.toggle()
      def setChecked(arg0: Boolean) = v.setChecked(arg0)
      def showMenu() = v.showMenu()
      def unwrap: io.qt.widgets.QPushButton = v
    }
  }

  def wrap(v: io.qt.widgets.QPushButton): Button = 
    val res = v.asInstanceOf[Button]
    if !Toolkit.stateReader(ButtonInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: Button): Unit = {
    ButtonBase.init(v)
    Toolkit.update(ButtonInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): Button = {
    val res = new io.qt.widgets.QPushButton()
    
    res.asInstanceOf[Button]
  }
  
  def apply(
    
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    autoDefault: Opt[Binding[Boolean]] = UnsetParam,
    autoExclusive: Opt[Binding[Boolean]] = UnsetParam,
    autoFillBackground: Opt[Binding[Boolean]] = UnsetParam,
    autoRepeat: Opt[Binding[Boolean]] = UnsetParam,
    autoRepeatDelay: Opt[Binding[Int]] = UnsetParam,
    autoRepeatInterval: Opt[Binding[Int]] = UnsetParam,
    baseSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    checkable: Opt[Binding[Boolean]] = UnsetParam,
    checked: Opt[Binding[Boolean]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    default: Opt[Binding[Boolean]] = UnsetParam,
    down: Opt[Binding[Boolean]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    flat: Opt[Binding[Boolean]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    icon: Opt[Binding[io.qt.gui.QIcon | Null]] = UnsetParam,
    iconSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    inputMethodHints: Opt[Binding[io.qt.core.Qt.InputMethodHints | Null]] = UnsetParam,
    layoutDirection: Opt[Binding[io.qt.core.Qt.LayoutDirection]] = UnsetParam,
    locale: Opt[Binding[io.qt.core.QLocale | Null]] = UnsetParam,
    maximumHeight: Opt[Binding[Int]] = UnsetParam,
    maximumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    maximumWidth: Opt[Binding[Int]] = UnsetParam,
    minimumHeight: Opt[Binding[Int]] = UnsetParam,
    minimumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    minimumWidth: Opt[Binding[Int]] = UnsetParam,
    mouseTracking: Opt[Binding[Boolean]] = UnsetParam,
    objectName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    shortcut: Opt[Binding[io.qt.gui.QKeySequence | Null]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipDuration: Opt[Binding[Int]] = UnsetParam,
    updatesEnabled: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    whatsThis: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowFilePath: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowIcon: Opt[Binding[io.qt.gui.QIcon | Null]] = UnsetParam,
    windowIconText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowModality: Opt[Binding[io.qt.core.Qt.WindowModality]] = UnsetParam,
    windowModified: Opt[Binding[Boolean]] = UnsetParam,
    windowOpacity: Opt[Binding[Double]] = UnsetParam,
    windowTitle: Opt[Binding[java.lang.String | Null]] = UnsetParam
  ): ToolkitAction[Button] = {
    val res = uninitialized()
    Button.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(autoDefault, Button.ops.autoDefault(res) := _)
    ifSet(autoExclusive, Button.ops.autoExclusive(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(autoRepeat, Button.ops.autoRepeat(res) := _)
    ifSet(autoRepeatDelay, Button.ops.autoRepeatDelay(res) := _)
    ifSet(autoRepeatInterval, Button.ops.autoRepeatInterval(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(checkable, Button.ops.checkable(res) := _)
    ifSet(checked, Button.ops.checked(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(default, Button.ops.default(res) := _)
    ifSet(down, Button.ops.down(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(flat, Button.ops.flat(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(icon, Button.ops.icon(res) := _)
    ifSet(iconSize, Button.ops.iconSize(res) := _)
    ifSet(inputMethodHints, Widget.ops.inputMethodHints(res) := _)
    ifSet(layoutDirection, Widget.ops.layoutDirection(res) := _)
    ifSet(locale, Widget.ops.locale(res) := _)
    ifSet(maximumHeight, Widget.ops.maximumHeight(res) := _)
    ifSet(maximumSize, Widget.ops.maximumSize(res) := _)
    ifSet(maximumWidth, Widget.ops.maximumWidth(res) := _)
    ifSet(minimumHeight, Widget.ops.minimumHeight(res) := _)
    ifSet(minimumSize, Widget.ops.minimumSize(res) := _)
    ifSet(minimumWidth, Widget.ops.minimumWidth(res) := _)
    ifSet(mouseTracking, Widget.ops.mouseTracking(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(shortcut, Button.ops.shortcut(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(text, Button.ops.text(res) := _)
    ifSet(toolTip, Widget.ops.toolTip(res) := _)
    ifSet(toolTipDuration, Widget.ops.toolTipDuration(res) := _)
    ifSet(updatesEnabled, Widget.ops.updatesEnabled(res) := _)
    ifSet(visible, Widget.ops.visible(res) := _)
    ifSet(whatsThis, Widget.ops.whatsThis(res) := _)
    ifSet(windowFilePath, Widget.ops.windowFilePath(res) := _)
    ifSet(windowIcon, Widget.ops.windowIcon(res) := _)
    ifSet(windowIconText, Widget.ops.windowIconText(res) := _)
    ifSet(windowModality, Widget.ops.windowModality(res) := _)
    ifSet(windowModified, Widget.ops.windowModified(res) := _)
    ifSet(windowOpacity, Widget.ops.windowOpacity(res) := _)
    ifSet(windowTitle, Widget.ops.windowTitle(res) := _)
    res
  }
  
}
        