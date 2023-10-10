
package guarana
package qt
        
import io.qt.core.Qt
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type Widget  >: io.qt.widgets.QWidget <: AnyRef = io.qt.widgets.QWidget
object Widget {
  private val WidgetInitialized: Var[Boolean] = Var[Boolean]("WidgetInitialized", false, false)
  val AcceptDrops: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("acceptDrops", _.acceptDrops(), _.setAcceptDrops(_), true)
  val AccessibleDescription: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("accessibleDescription", _.accessibleDescription(), _.setAccessibleDescription(_), true)
  val AccessibleName: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("accessibleName", _.accessibleName(), _.setAccessibleName(_), true)
  val AutoFillBackground: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("autoFillBackground", _.autoFillBackground(), _.setAutoFillBackground(_), true)
  val BaseSize: ExternalVar.Aux[Widget, io.qt.core.QSize | Null] = ExternalVar[Widget, io.qt.core.QSize | Null]("baseSize", _.baseSize(), _.setBaseSize(_), true)
  val ChildrenRect: ExternalObsVal.Aux[Widget, io.qt.core.QRect | Null] = ExternalObsVal[Widget, io.qt.core.QRect | Null]("childrenRect", _.childrenRect())
  val ChildrenRegion: ExternalObsVal.Aux[Widget, io.qt.gui.QRegion | Null] = ExternalObsVal[Widget, io.qt.gui.QRegion | Null]("childrenRegion", _.childrenRegion())
  val ContextMenuPolicy: ExternalVar.Aux[Widget, io.qt.core.Qt.ContextMenuPolicy] = ExternalVar[Widget, io.qt.core.Qt.ContextMenuPolicy]("contextMenuPolicy", _.contextMenuPolicy().unn, _.setContextMenuPolicy(_), true)
  val Cursor: ExternalVar.Aux[Widget, io.qt.gui.QCursor | Null] = ExternalVar[Widget, io.qt.gui.QCursor | Null]("cursor", _.cursor(), _.setCursor(_), true)
  val Enabled: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("enabled", _.isEnabled(), _.setEnabled(_), true)
  val Focus: ExternalObsVal.Aux[Widget, Boolean] = ExternalObsVal[Widget, Boolean]("focus", _.hasFocus())
  val FocusPolicy: ExternalVar.Aux[Widget, io.qt.core.Qt.FocusPolicy] = ExternalVar[Widget, io.qt.core.Qt.FocusPolicy]("focusPolicy", _.focusPolicy().unn, _.setFocusPolicy(_), true)
  val Font: ExternalVar.Aux[Widget, io.qt.gui.QFont | Null] = ExternalVar[Widget, io.qt.gui.QFont | Null]("font", _.font(), _.setFont(_), true)
  val FrameGeometry: ExternalObsVal.Aux[Widget, io.qt.core.QRect | Null] = ExternalObsVal[Widget, io.qt.core.QRect | Null]("frameGeometry", _.frameGeometry())
  val FrameSize: ExternalObsVal.Aux[Widget, io.qt.core.QSize | Null] = ExternalObsVal[Widget, io.qt.core.QSize | Null]("frameSize", _.frameSize())
  val FullScreen: ExternalObsVal.Aux[Widget, Boolean] = ExternalObsVal[Widget, Boolean]("fullScreen", _.isFullScreen())
  val Geometry: ExternalVar.Aux[Widget, io.qt.core.QRect | Null] = ExternalVar[Widget, io.qt.core.QRect | Null]("geometry", _.geometry(), _.setGeometry(_), true)
  val Height: ExternalObsVal.Aux[Widget, Int] = ExternalObsVal[Widget, Int]("height", _.height())
  val InputMethodHints: ExternalVar.Aux[Widget, io.qt.core.Qt.InputMethodHints | Null] = ExternalVar[Widget, io.qt.core.Qt.InputMethodHints | Null]("inputMethodHints", _.inputMethodHints(), _.setInputMethodHints(_), true)
  val IsActiveWindow: ExternalObsVal.Aux[Widget, Boolean] = ExternalObsVal[Widget, Boolean]("isActiveWindow", _.isActiveWindow())
  val LayoutDirection: ExternalVar.Aux[Widget, io.qt.core.Qt.LayoutDirection] = ExternalVar[Widget, io.qt.core.Qt.LayoutDirection]("layoutDirection", _.layoutDirection().unn, _.setLayoutDirection(_), true)
  val Locale: ExternalVar.Aux[Widget, io.qt.core.QLocale | Null] = ExternalVar[Widget, io.qt.core.QLocale | Null]("locale", _.locale(), _.setLocale(_), true)
  val Maximized: ExternalObsVal.Aux[Widget, Boolean] = ExternalObsVal[Widget, Boolean]("maximized", _.isMaximized())
  val MaximumHeight: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("maximumHeight", _.maximumHeight(), _.setMaximumHeight(_), true)
  val MaximumSize: ExternalVar.Aux[Widget, io.qt.core.QSize | Null] = ExternalVar[Widget, io.qt.core.QSize | Null]("maximumSize", _.maximumSize(), _.setMaximumSize(_), true)
  val MaximumWidth: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("maximumWidth", _.maximumWidth(), _.setMaximumWidth(_), true)
  val Minimized: ExternalObsVal.Aux[Widget, Boolean] = ExternalObsVal[Widget, Boolean]("minimized", _.isMinimized())
  val MinimumHeight: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("minimumHeight", _.minimumHeight(), _.setMinimumHeight(_), true)
  val MinimumSize: ExternalVar.Aux[Widget, io.qt.core.QSize | Null] = ExternalVar[Widget, io.qt.core.QSize | Null]("minimumSize", _.minimumSize(), _.setMinimumSize(_), true)
  val MinimumSizeHint: ExternalObsVal.Aux[Widget, io.qt.core.QSize | Null] = ExternalObsVal[Widget, io.qt.core.QSize | Null]("minimumSizeHint", _.minimumSizeHint())
  val MinimumWidth: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("minimumWidth", _.minimumWidth(), _.setMinimumWidth(_), true)
  val Modal: ExternalObsVal.Aux[Widget, Boolean] = ExternalObsVal[Widget, Boolean]("modal", _.isModal())
  val MouseTracking: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("mouseTracking", _.hasMouseTracking(), _.setMouseTracking(_), true)
  val NormalGeometry: ExternalObsVal.Aux[Widget, io.qt.core.QRect | Null] = ExternalObsVal[Widget, io.qt.core.QRect | Null]("normalGeometry", _.normalGeometry())
  val ObjectName: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("objectName", _.objectName(), _.setObjectName(_), true)
  val Palette: ExternalVar.Aux[Widget, io.qt.gui.QPalette | Null] = ExternalVar[Widget, io.qt.gui.QPalette | Null]("palette", _.palette(), _.setPalette(_), true)
  val Pos: ExternalVar.Aux[Widget, io.qt.core.QPoint | Null] = ExternalVar[Widget, io.qt.core.QPoint | Null]("pos", _.pos(), _.move(_), true)
  val Rect: ExternalObsVal.Aux[Widget, io.qt.core.QRect | Null] = ExternalObsVal[Widget, io.qt.core.QRect | Null]("rect", _.rect())
  val Size: ExternalVar.Aux[Widget, io.qt.core.QSize | Null] = ExternalVar[Widget, io.qt.core.QSize | Null]("size", _.size(), _.resize(_), true)
  val SizeHint: ExternalObsVal.Aux[Widget, io.qt.core.QSize | Null] = ExternalObsVal[Widget, io.qt.core.QSize | Null]("sizeHint", _.sizeHint())
  val SizeIncrement: ExternalVar.Aux[Widget, io.qt.core.QSize | Null] = ExternalVar[Widget, io.qt.core.QSize | Null]("sizeIncrement", _.sizeIncrement(), _.setSizeIncrement(_), true)
  val SizePolicy: ExternalVar.Aux[Widget, io.qt.widgets.QSizePolicy | Null] = ExternalVar[Widget, io.qt.widgets.QSizePolicy | Null]("sizePolicy", _.sizePolicy(), _.setSizePolicy(_), true)
  val StatusTip: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("statusTip", _.statusTip(), _.setStatusTip(_), true)
  val StyleSheet: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("styleSheet", _.styleSheet(), _.setStyleSheet(_), true)
  val TabletTracking: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("tabletTracking", _.hasTabletTracking(), _.setTabletTracking(_), true)
  val ToolTip: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("toolTip", _.toolTip(), _.setToolTip(_), true)
  val ToolTipDuration: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("toolTipDuration", _.toolTipDuration(), _.setToolTipDuration(_), true)
  val UpdatesEnabled: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("updatesEnabled", _.updatesEnabled(), _.setUpdatesEnabled(_), true)
  val Visible: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("visible", _.isVisible(), _.setVisible(_), true)
  val WhatsThis: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("whatsThis", _.whatsThis(), _.setWhatsThis(_), true)
  val Width: ExternalObsVal.Aux[Widget, Int] = ExternalObsVal[Widget, Int]("width", _.width())
  val WindowFilePath: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("windowFilePath", _.windowFilePath(), _.setWindowFilePath(_), true)
  val WindowIcon: ExternalVar.Aux[Widget, io.qt.gui.QIcon | Null] = ExternalVar[Widget, io.qt.gui.QIcon | Null]("windowIcon", _.windowIcon(), _.setWindowIcon(_), true)
  val WindowIconText: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("windowIconText", _.windowIconText(), _.setWindowIconText(_), true)
  val WindowModality: ExternalVar.Aux[Widget, io.qt.core.Qt.WindowModality] = ExternalVar[Widget, io.qt.core.Qt.WindowModality]("windowModality", _.windowModality().unn, _.setWindowModality(_), true)
  val WindowModified: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("windowModified", _.isWindowModified(), _.setWindowModified(_), true)
  val WindowOpacity: ExternalVar.Aux[Widget, Double] = ExternalVar[Widget, Double]("windowOpacity", _.windowOpacity(), _.setWindowOpacity(_), true)
  val WindowTitle: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("windowTitle", _.windowTitle(), _.setWindowTitle(_), true)
  val X: ExternalObsVal.Aux[Widget, Int] = ExternalObsVal[Widget, Int]("x", _.x())
  val Y: ExternalObsVal.Aux[Widget, Int] = ExternalObsVal[Widget, Int]("y", _.y())

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: Widget) {
      def acceptDrops: Var.Aux[Boolean, v.type] = Widget.AcceptDrops.asInstanceOf[Var.Aux[Boolean, v.type]]
      def accessibleDescription: Var.Aux[java.lang.String | Null, v.type] = Widget.AccessibleDescription.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def accessibleName: Var.Aux[java.lang.String | Null, v.type] = Widget.AccessibleName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def autoFillBackground: Var.Aux[Boolean, v.type] = Widget.AutoFillBackground.asInstanceOf[Var.Aux[Boolean, v.type]]
      def baseSize: Var.Aux[io.qt.core.QSize | Null, v.type] = Widget.BaseSize.asInstanceOf[Var.Aux[io.qt.core.QSize | Null, v.type]]
      def childrenRect: ObsVal.Aux[io.qt.core.QRect | Null, v.type] = Widget.ChildrenRect.asInstanceOf[ObsVal.Aux[io.qt.core.QRect | Null, v.type]]
      def childrenRegion: ObsVal.Aux[io.qt.gui.QRegion | Null, v.type] = Widget.ChildrenRegion.asInstanceOf[ObsVal.Aux[io.qt.gui.QRegion | Null, v.type]]
      def contextMenuPolicy: Var.Aux[io.qt.core.Qt.ContextMenuPolicy, v.type] = Widget.ContextMenuPolicy.asInstanceOf[Var.Aux[io.qt.core.Qt.ContextMenuPolicy, v.type]]
      def cursor: Var.Aux[io.qt.gui.QCursor | Null, v.type] = Widget.Cursor.asInstanceOf[Var.Aux[io.qt.gui.QCursor | Null, v.type]]
      def enabled: Var.Aux[Boolean, v.type] = Widget.Enabled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def focus: ObsVal.Aux[Boolean, v.type] = Widget.Focus.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def focusPolicy: Var.Aux[io.qt.core.Qt.FocusPolicy, v.type] = Widget.FocusPolicy.asInstanceOf[Var.Aux[io.qt.core.Qt.FocusPolicy, v.type]]
      def font: Var.Aux[io.qt.gui.QFont | Null, v.type] = Widget.Font.asInstanceOf[Var.Aux[io.qt.gui.QFont | Null, v.type]]
      def frameGeometry: ObsVal.Aux[io.qt.core.QRect | Null, v.type] = Widget.FrameGeometry.asInstanceOf[ObsVal.Aux[io.qt.core.QRect | Null, v.type]]
      def frameSize: ObsVal.Aux[io.qt.core.QSize | Null, v.type] = Widget.FrameSize.asInstanceOf[ObsVal.Aux[io.qt.core.QSize | Null, v.type]]
      def fullScreen: ObsVal.Aux[Boolean, v.type] = Widget.FullScreen.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def geometry: Var.Aux[io.qt.core.QRect | Null, v.type] = Widget.Geometry.asInstanceOf[Var.Aux[io.qt.core.QRect | Null, v.type]]
      def height: ObsVal.Aux[Int, v.type] = Widget.Height.asInstanceOf[ObsVal.Aux[Int, v.type]]
      def inputMethodHints: Var.Aux[io.qt.core.Qt.InputMethodHints | Null, v.type] = Widget.InputMethodHints.asInstanceOf[Var.Aux[io.qt.core.Qt.InputMethodHints | Null, v.type]]
      def isActiveWindow: ObsVal.Aux[Boolean, v.type] = Widget.IsActiveWindow.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def layoutDirection: Var.Aux[io.qt.core.Qt.LayoutDirection, v.type] = Widget.LayoutDirection.asInstanceOf[Var.Aux[io.qt.core.Qt.LayoutDirection, v.type]]
      def locale: Var.Aux[io.qt.core.QLocale | Null, v.type] = Widget.Locale.asInstanceOf[Var.Aux[io.qt.core.QLocale | Null, v.type]]
      def maximized: ObsVal.Aux[Boolean, v.type] = Widget.Maximized.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def maximumHeight: Var.Aux[Int, v.type] = Widget.MaximumHeight.asInstanceOf[Var.Aux[Int, v.type]]
      def maximumSize: Var.Aux[io.qt.core.QSize | Null, v.type] = Widget.MaximumSize.asInstanceOf[Var.Aux[io.qt.core.QSize | Null, v.type]]
      def maximumWidth: Var.Aux[Int, v.type] = Widget.MaximumWidth.asInstanceOf[Var.Aux[Int, v.type]]
      def minimized: ObsVal.Aux[Boolean, v.type] = Widget.Minimized.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def minimumHeight: Var.Aux[Int, v.type] = Widget.MinimumHeight.asInstanceOf[Var.Aux[Int, v.type]]
      def minimumSize: Var.Aux[io.qt.core.QSize | Null, v.type] = Widget.MinimumSize.asInstanceOf[Var.Aux[io.qt.core.QSize | Null, v.type]]
      def minimumSizeHint: ObsVal.Aux[io.qt.core.QSize | Null, v.type] = Widget.MinimumSizeHint.asInstanceOf[ObsVal.Aux[io.qt.core.QSize | Null, v.type]]
      def minimumWidth: Var.Aux[Int, v.type] = Widget.MinimumWidth.asInstanceOf[Var.Aux[Int, v.type]]
      def modal: ObsVal.Aux[Boolean, v.type] = Widget.Modal.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def mouseTracking: Var.Aux[Boolean, v.type] = Widget.MouseTracking.asInstanceOf[Var.Aux[Boolean, v.type]]
      def normalGeometry: ObsVal.Aux[io.qt.core.QRect | Null, v.type] = Widget.NormalGeometry.asInstanceOf[ObsVal.Aux[io.qt.core.QRect | Null, v.type]]
      def objectName: Var.Aux[java.lang.String | Null, v.type] = Widget.ObjectName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def palette: Var.Aux[io.qt.gui.QPalette | Null, v.type] = Widget.Palette.asInstanceOf[Var.Aux[io.qt.gui.QPalette | Null, v.type]]
      def pos: Var.Aux[io.qt.core.QPoint | Null, v.type] = Widget.Pos.asInstanceOf[Var.Aux[io.qt.core.QPoint | Null, v.type]]
      def rect: ObsVal.Aux[io.qt.core.QRect | Null, v.type] = Widget.Rect.asInstanceOf[ObsVal.Aux[io.qt.core.QRect | Null, v.type]]
      def size: Var.Aux[io.qt.core.QSize | Null, v.type] = Widget.Size.asInstanceOf[Var.Aux[io.qt.core.QSize | Null, v.type]]
      def sizeHint: ObsVal.Aux[io.qt.core.QSize | Null, v.type] = Widget.SizeHint.asInstanceOf[ObsVal.Aux[io.qt.core.QSize | Null, v.type]]
      def sizeIncrement: Var.Aux[io.qt.core.QSize | Null, v.type] = Widget.SizeIncrement.asInstanceOf[Var.Aux[io.qt.core.QSize | Null, v.type]]
      def sizePolicy: Var.Aux[io.qt.widgets.QSizePolicy | Null, v.type] = Widget.SizePolicy.asInstanceOf[Var.Aux[io.qt.widgets.QSizePolicy | Null, v.type]]
      def statusTip: Var.Aux[java.lang.String | Null, v.type] = Widget.StatusTip.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def styleSheet: Var.Aux[java.lang.String | Null, v.type] = Widget.StyleSheet.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def tabletTracking: Var.Aux[Boolean, v.type] = Widget.TabletTracking.asInstanceOf[Var.Aux[Boolean, v.type]]
      def toolTip: Var.Aux[java.lang.String | Null, v.type] = Widget.ToolTip.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def toolTipDuration: Var.Aux[Int, v.type] = Widget.ToolTipDuration.asInstanceOf[Var.Aux[Int, v.type]]
      def updatesEnabled: Var.Aux[Boolean, v.type] = Widget.UpdatesEnabled.asInstanceOf[Var.Aux[Boolean, v.type]]
      def visible: Var.Aux[Boolean, v.type] = Widget.Visible.asInstanceOf[Var.Aux[Boolean, v.type]]
      def whatsThis: Var.Aux[java.lang.String | Null, v.type] = Widget.WhatsThis.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def width: ObsVal.Aux[Int, v.type] = Widget.Width.asInstanceOf[ObsVal.Aux[Int, v.type]]
      def windowFilePath: Var.Aux[java.lang.String | Null, v.type] = Widget.WindowFilePath.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def windowIcon: Var.Aux[io.qt.gui.QIcon | Null, v.type] = Widget.WindowIcon.asInstanceOf[Var.Aux[io.qt.gui.QIcon | Null, v.type]]
      def windowIconText: Var.Aux[java.lang.String | Null, v.type] = Widget.WindowIconText.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def windowModality: Var.Aux[io.qt.core.Qt.WindowModality, v.type] = Widget.WindowModality.asInstanceOf[Var.Aux[io.qt.core.Qt.WindowModality, v.type]]
      def windowModified: Var.Aux[Boolean, v.type] = Widget.WindowModified.asInstanceOf[Var.Aux[Boolean, v.type]]
      def windowOpacity: Var.Aux[Double, v.type] = Widget.WindowOpacity.asInstanceOf[Var.Aux[Double, v.type]]
      def windowTitle: Var.Aux[java.lang.String | Null, v.type] = Widget.WindowTitle.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def x: ObsVal.Aux[Int, v.type] = Widget.X.asInstanceOf[ObsVal.Aux[Int, v.type]]
      def y: ObsVal.Aux[Int, v.type] = Widget.Y.asInstanceOf[ObsVal.Aux[Int, v.type]]

      

      def disposeLater() = v.disposeLater()
      def setEnabled(arg0: Boolean) = v.setEnabled(arg0)
      def setDisabled(disabled: Boolean) = v.setDisabled(disabled)
      def setWindowModified(arg0: Boolean) = v.setWindowModified(arg0)
      def setWindowTitle(arg0: java.lang.String | Null) = v.setWindowTitle(arg0)
      def setStyleSheet(arg0: java.lang.String | Null) = v.setStyleSheet(arg0)
      def setFocus() = v.setFocus()
      def update() = v.update()
      def repaint() = v.repaint()
      def setVisible(arg0: Boolean) = v.setVisible(arg0)
      def setHidden(hidden: Boolean) = v.setHidden(hidden)
      def show() = v.show()
      def hide() = v.hide()
      def showMinimized() = v.showMinimized()
      def showMaximized() = v.showMaximized()
      def showFullScreen() = v.showFullScreen()
      def showNormal() = v.showNormal()
      def close() = v.close()
      def raise() = v.raise()
      def lower() = v.lower()
      def grab(rectangle: io.qt.core.QRect | Null) = v.grab(rectangle)
      def grab() = v.grab()
      def windowHandle: Option[Window] = v.windowHandle.?(Window.wrap(_)).toOption
      def unwrap: io.qt.widgets.QWidget = v
    }
  }

  def wrap(v: io.qt.widgets.QWidget): Widget = 
    val res = v.asInstanceOf[Widget]
    if !Toolkit.stateReader(WidgetInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: Widget): Unit = {
    
    Toolkit.connectVar(WindowIcon.forInstance[v.type], v.windowIconChanged.unn)
    Toolkit.connectVar(WindowIconText.forInstance[v.type], v.windowIconTextChanged.unn)
    Toolkit.connectVar(WindowTitle.forInstance[v.type], v.windowTitleChanged.unn)
    Toolkit.update(WidgetInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(parent: Widget | Null = null, windowFlags: Qt.WindowFlags | Null = null): Widget = {
    val res = new io.qt.widgets.QWidget(parent.?(_.unwrap), windowFlags)
    
    res.asInstanceOf[Widget]
  }
  
  def apply(
    parent: Widget | Null = null, windowFlags: Qt.WindowFlags | Null = null,
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    autoFillBackground: Opt[Binding[Boolean]] = UnsetParam,
    baseSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
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
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
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
  ): ToolkitAction[Widget] = {
    val res = uninitialized(parent, windowFlags)
    Widget.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
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
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
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
        