
package guarana
package qt
        
import io.qt.core.Qt
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type FileDialog <: DialogBase  = io.qt.widgets.QFileDialog & DialogBase
object FileDialog {
  private val FileDialogInitialized: Var[Boolean] = Var[Boolean]("FileDialogInitialized", false, false)
  val AcceptMode: ExternalVar.Aux[FileDialog, io.qt.widgets.QFileDialog.AcceptMode] = ExternalVar[FileDialog, io.qt.widgets.QFileDialog.AcceptMode]("acceptMode", _.acceptMode().unn, _.setAcceptMode(_), true)
  val DefaultSuffix: ExternalVar.Aux[FileDialog, java.lang.String | Null] = ExternalVar[FileDialog, java.lang.String | Null]("defaultSuffix", _.defaultSuffix(), _.setDefaultSuffix(_), true)
  val FileMode: ExternalVar.Aux[FileDialog, io.qt.widgets.QFileDialog.FileMode] = ExternalVar[FileDialog, io.qt.widgets.QFileDialog.FileMode]("fileMode", _.fileMode().unn, _.setFileMode(_), true)
  val Options: ExternalVar.Aux[FileDialog, io.qt.widgets.QFileDialog.Options | Null] = ExternalVar[FileDialog, io.qt.widgets.QFileDialog.Options | Null]("options", _.options(), _.setOptions(_), true)
  val SupportedSchemes: ExternalVar.Aux[FileDialog, io.qt.core.QStringList | Null] = ExternalVar[FileDialog, io.qt.core.QStringList | Null]("supportedSchemes", _.supportedSchemes(), _.setSupportedSchemes(_), true)
  val ViewMode: ExternalVar.Aux[FileDialog, io.qt.widgets.QFileDialog.ViewMode] = ExternalVar[FileDialog, io.qt.widgets.QFileDialog.ViewMode]("viewMode", _.viewMode().unn, _.setViewMode(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: FileDialog) {
      def acceptMode: Var.Aux[io.qt.widgets.QFileDialog.AcceptMode, v.type] = FileDialog.AcceptMode.asInstanceOf[Var.Aux[io.qt.widgets.QFileDialog.AcceptMode, v.type]]
      def defaultSuffix: Var.Aux[java.lang.String | Null, v.type] = FileDialog.DefaultSuffix.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def fileMode: Var.Aux[io.qt.widgets.QFileDialog.FileMode, v.type] = FileDialog.FileMode.asInstanceOf[Var.Aux[io.qt.widgets.QFileDialog.FileMode, v.type]]
      def options: Var.Aux[io.qt.widgets.QFileDialog.Options | Null, v.type] = FileDialog.Options.asInstanceOf[Var.Aux[io.qt.widgets.QFileDialog.Options | Null, v.type]]
      def supportedSchemes: Var.Aux[io.qt.core.QStringList | Null, v.type] = FileDialog.SupportedSchemes.asInstanceOf[Var.Aux[io.qt.core.QStringList | Null, v.type]]
      def viewMode: Var.Aux[io.qt.widgets.QFileDialog.ViewMode, v.type] = FileDialog.ViewMode.asInstanceOf[Var.Aux[io.qt.widgets.QFileDialog.ViewMode, v.type]]

      

      
      def unwrap: io.qt.widgets.QFileDialog = v
    }
  }

  def wrap(v: io.qt.widgets.QFileDialog): FileDialog = 
    val res = v.asInstanceOf[FileDialog]
    if !Toolkit.stateReader(FileDialogInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: FileDialog): Unit = {
    DialogBase.init(v)
    Toolkit.update(FileDialogInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(parent: Widget | Null = null, windowFlags: Qt.WindowFlags | Null = null): FileDialog = {
    val res = new io.qt.widgets.QFileDialog(parent.?(_.unwrap), windowFlags)
    
    res.asInstanceOf[FileDialog]
  }
  
  def apply(
    parent: Widget | Null = null, windowFlags: Qt.WindowFlags | Null = null,
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    acceptMode: Opt[Binding[io.qt.widgets.QFileDialog.AcceptMode]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    autoFillBackground: Opt[Binding[Boolean]] = UnsetParam,
    baseSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    defaultSuffix: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    fileMode: Opt[Binding[io.qt.widgets.QFileDialog.FileMode]] = UnsetParam,
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
    options: Opt[Binding[io.qt.widgets.QFileDialog.Options | Null]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeGripEnabled: Opt[Binding[Boolean]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    supportedSchemes: Opt[Binding[io.qt.core.QStringList | Null]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
    toolTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    toolTipDuration: Opt[Binding[Int]] = UnsetParam,
    updatesEnabled: Opt[Binding[Boolean]] = UnsetParam,
    viewMode: Opt[Binding[io.qt.widgets.QFileDialog.ViewMode]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    whatsThis: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowFilePath: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowIcon: Opt[Binding[io.qt.gui.QIcon | Null]] = UnsetParam,
    windowIconText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    windowModality: Opt[Binding[io.qt.core.Qt.WindowModality]] = UnsetParam,
    windowModified: Opt[Binding[Boolean]] = UnsetParam,
    windowOpacity: Opt[Binding[Double]] = UnsetParam,
    windowTitle: Opt[Binding[java.lang.String | Null]] = UnsetParam
  ): VarContextAction[FileDialog] = {
    val res = uninitialized(parent, windowFlags)
    FileDialog.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(acceptMode, FileDialog.ops.acceptMode(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(defaultSuffix, FileDialog.ops.defaultSuffix(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(fileMode, FileDialog.ops.fileMode(res) := _)
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
    ifSet(options, FileDialog.ops.options(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeGripEnabled, DialogBase.ops.sizeGripEnabled(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(supportedSchemes, FileDialog.ops.supportedSchemes(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(toolTip, Widget.ops.toolTip(res) := _)
    ifSet(toolTipDuration, Widget.ops.toolTipDuration(res) := _)
    ifSet(updatesEnabled, Widget.ops.updatesEnabled(res) := _)
    ifSet(viewMode, FileDialog.ops.viewMode(res) := _)
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
        