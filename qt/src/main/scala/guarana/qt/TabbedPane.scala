
package guarana
package qt
        
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type TabbedPane <: Widget  = io.qt.widgets.QTabWidget & Widget
object TabbedPane {
  private val TabbedPaneInitialized: Var[Boolean] = Var[Boolean]("TabbedPaneInitialized", false, false)
  val Count: ExternalObsVal.Aux[TabbedPane, Int] = ExternalObsVal[TabbedPane, Int]("count", _.count())
  val CurrentIndex: ExternalVar.Aux[TabbedPane, Int] = ExternalVar[TabbedPane, Int]("currentIndex", _.currentIndex(), _.setCurrentIndex(_), true)
  val DocumentMode: ExternalVar.Aux[TabbedPane, Boolean] = ExternalVar[TabbedPane, Boolean]("documentMode", _.documentMode(), _.setDocumentMode(_), true)
  val ElideMode: ExternalVar.Aux[TabbedPane, io.qt.core.Qt.TextElideMode] = ExternalVar[TabbedPane, io.qt.core.Qt.TextElideMode]("elideMode", _.elideMode().unn, _.setElideMode(_), true)
  val IconSize: ExternalVar.Aux[TabbedPane, io.qt.core.QSize | Null] = ExternalVar[TabbedPane, io.qt.core.QSize | Null]("iconSize", _.iconSize(), _.setIconSize(_), true)
  val Movable: ExternalVar.Aux[TabbedPane, Boolean] = ExternalVar[TabbedPane, Boolean]("movable", _.isMovable(), _.setMovable(_), true)
  val TabBarAutoHide: ExternalVar.Aux[TabbedPane, Boolean] = ExternalVar[TabbedPane, Boolean]("tabBarAutoHide", _.tabBarAutoHide(), _.setTabBarAutoHide(_), true)
  val TabPosition: ExternalVar.Aux[TabbedPane, io.qt.widgets.QTabWidget.TabPosition] = ExternalVar[TabbedPane, io.qt.widgets.QTabWidget.TabPosition]("tabPosition", _.tabPosition().unn, _.setTabPosition(_), true)
  val TabShape: ExternalVar.Aux[TabbedPane, io.qt.widgets.QTabWidget.TabShape] = ExternalVar[TabbedPane, io.qt.widgets.QTabWidget.TabShape]("tabShape", _.tabShape().unn, _.setTabShape(_), true)
  val Tabs: Var[Seq[Tab]] = Var[Seq[Tab]]("tabs", Seq.empty, true)
  val TabsClosable: ExternalVar.Aux[TabbedPane, Boolean] = ExternalVar[TabbedPane, Boolean]("tabsClosable", _.tabsClosable(), _.setTabsClosable(_), true)
  val UsesScrollButtons: ExternalVar.Aux[TabbedPane, Boolean] = ExternalVar[TabbedPane, Boolean]("usesScrollButtons", _.usesScrollButtons(), _.setUsesScrollButtons(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: TabbedPane) {
      def count: ObsVal.Aux[Int, v.type] = TabbedPane.Count.asInstanceOf[ObsVal.Aux[Int, v.type]]
      def currentIndex: Var.Aux[Int, v.type] = TabbedPane.CurrentIndex.asInstanceOf[Var.Aux[Int, v.type]]
      def documentMode: Var.Aux[Boolean, v.type] = TabbedPane.DocumentMode.asInstanceOf[Var.Aux[Boolean, v.type]]
      def elideMode: Var.Aux[io.qt.core.Qt.TextElideMode, v.type] = TabbedPane.ElideMode.asInstanceOf[Var.Aux[io.qt.core.Qt.TextElideMode, v.type]]
      def iconSize: Var.Aux[io.qt.core.QSize | Null, v.type] = TabbedPane.IconSize.asInstanceOf[Var.Aux[io.qt.core.QSize | Null, v.type]]
      def movable: Var.Aux[Boolean, v.type] = TabbedPane.Movable.asInstanceOf[Var.Aux[Boolean, v.type]]
      def tabBarAutoHide: Var.Aux[Boolean, v.type] = TabbedPane.TabBarAutoHide.asInstanceOf[Var.Aux[Boolean, v.type]]
      def tabPosition: Var.Aux[io.qt.widgets.QTabWidget.TabPosition, v.type] = TabbedPane.TabPosition.asInstanceOf[Var.Aux[io.qt.widgets.QTabWidget.TabPosition, v.type]]
      def tabShape: Var.Aux[io.qt.widgets.QTabWidget.TabShape, v.type] = TabbedPane.TabShape.asInstanceOf[Var.Aux[io.qt.widgets.QTabWidget.TabShape, v.type]]
      def tabs: Var.Aux[Seq[Tab], v.type] = TabbedPane.Tabs.asInstanceOf[Var.Aux[Seq[Tab], v.type]]
      def tabsClosable: Var.Aux[Boolean, v.type] = TabbedPane.TabsClosable.asInstanceOf[Var.Aux[Boolean, v.type]]
      def usesScrollButtons: Var.Aux[Boolean, v.type] = TabbedPane.UsesScrollButtons.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      def setDisabled(arg0: Boolean) = v.setDisabled(arg0)
      def setHidden(arg0: Boolean) = v.setHidden(arg0)
      def grab(arg0: io.qt.core.QRect | Null) = v.grab(arg0)
      def setCurrentIndex(arg0: Int) = v.setCurrentIndex(arg0)
      def setCurrentWidget(arg0: io.qt.widgets.QWidget | Null) = v.setCurrentWidget(arg0)
      def unwrap: io.qt.widgets.QTabWidget = v
    }
  }

  def wrap(v: io.qt.widgets.QTabWidget): TabbedPane = 
    val res = v.asInstanceOf[TabbedPane]
    if !Toolkit.stateReader(TabbedPaneInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: TabbedPane): Unit = {
    Widget.init(v)
    Toolkit.update(TabbedPaneInitialized.forInstance[v.type] := true)
    Toolkit.update {
      v.varUpdates := EventIterator.foreach {
        case v.tabs(oldv, newv) =>
          v.clear()
          newv.foreach(tab => v.addTab(tab.widget.unwrap, tab.icon.orNull, tab.label))
        case _ =>
      }
    }
    
  }
  def uninitialized(): TabbedPane = {
    val res = new io.qt.widgets.QTabWidget()
    
    res.asInstanceOf[TabbedPane]
  }
  
  def apply(
    
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    autoFillBackground: Opt[Binding[Boolean]] = UnsetParam,
    baseSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    currentIndex: Opt[Binding[Int]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    documentMode: Opt[Binding[Boolean]] = UnsetParam,
    elideMode: Opt[Binding[io.qt.core.Qt.TextElideMode]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
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
    movable: Opt[Binding[Boolean]] = UnsetParam,
    objectName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabBarAutoHide: Opt[Binding[Boolean]] = UnsetParam,
    tabPosition: Opt[Binding[io.qt.widgets.QTabWidget.TabPosition]] = UnsetParam,
    tabShape: Opt[Binding[io.qt.widgets.QTabWidget.TabShape]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
    tabs: Opt[Binding[Seq[Tab]]] = UnsetParam,
    tabsClosable: Opt[Binding[Boolean]] = UnsetParam,
    toolTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipDuration: Opt[Binding[Int]] = UnsetParam,
    updatesEnabled: Opt[Binding[Boolean]] = UnsetParam,
    usesScrollButtons: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    whatsThis: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowFilePath: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowIcon: Opt[Binding[io.qt.gui.QIcon | Null]] = UnsetParam,
    windowIconText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowModality: Opt[Binding[io.qt.core.Qt.WindowModality]] = UnsetParam,
    windowModified: Opt[Binding[Boolean]] = UnsetParam,
    windowOpacity: Opt[Binding[Double]] = UnsetParam,
    windowTitle: Opt[Binding[java.lang.String | Null]] = UnsetParam
  ): ToolkitAction[TabbedPane] = {
    val res = uninitialized()
    TabbedPane.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(currentIndex, TabbedPane.ops.currentIndex(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(documentMode, TabbedPane.ops.documentMode(res) := _)
    ifSet(elideMode, TabbedPane.ops.elideMode(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(iconSize, TabbedPane.ops.iconSize(res) := _)
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
    ifSet(movable, TabbedPane.ops.movable(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabBarAutoHide, TabbedPane.ops.tabBarAutoHide(res) := _)
    ifSet(tabPosition, TabbedPane.ops.tabPosition(res) := _)
    ifSet(tabShape, TabbedPane.ops.tabShape(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(tabs, TabbedPane.ops.tabs(res) := _)
    ifSet(tabsClosable, TabbedPane.ops.tabsClosable(res) := _)
    ifSet(toolTip, Widget.ops.toolTip(res) := _)
    ifSet(toolTipDuration, Widget.ops.toolTipDuration(res) := _)
    ifSet(updatesEnabled, Widget.ops.updatesEnabled(res) := _)
    ifSet(usesScrollButtons, TabbedPane.ops.usesScrollButtons(res) := _)
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
        