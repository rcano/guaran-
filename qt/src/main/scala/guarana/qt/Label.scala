
package guarana
package qt
        
import io.qt.core.Qt
import io.qt.gui.*
import io.qt.widgets.*
import util.*

opaque type Label <: FramedWidget  = io.qt.widgets.QLabel & FramedWidget
object Label {
  private val LabelInitialized: Var[Boolean] = Var[Boolean]("LabelInitialized", false, false)
  val Alignment: ExternalVar.Aux[Label, io.qt.core.Qt.Alignment | Null] = ExternalVar[Label, io.qt.core.Qt.Alignment | Null]("alignment", _.alignment(), _.setAlignment(_), true)
  val FrameRect: ExternalVar.Aux[Label, io.qt.core.QRect | Null] = ExternalVar[Label, io.qt.core.QRect | Null]("frameRect", _.frameRect(), _.setFrameRect(_), true)
  val FrameShadow: ExternalVar.Aux[Label, io.qt.widgets.QFrame.Shadow] = ExternalVar[Label, io.qt.widgets.QFrame.Shadow]("frameShadow", _.frameShadow().unn, _.setFrameShadow(_), true)
  val FrameShape: ExternalVar.Aux[Label, io.qt.widgets.QFrame.Shape] = ExternalVar[Label, io.qt.widgets.QFrame.Shape]("frameShape", _.frameShape().unn, _.setFrameShape(_), true)
  val FrameWidth: ExternalObsVal.Aux[Label, Int] = ExternalObsVal[Label, Int]("frameWidth", _.frameWidth())
  val HasSelectedText: ExternalObsVal.Aux[Label, Boolean] = ExternalObsVal[Label, Boolean]("hasSelectedText", _.hasSelectedText())
  val Indent: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("indent", _.indent(), _.setIndent(_), true)
  val LineWidth: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("lineWidth", _.lineWidth(), _.setLineWidth(_), true)
  val Margin: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("margin", _.margin(), _.setMargin(_), true)
  val MidLineWidth: ExternalVar.Aux[Label, Int] = ExternalVar[Label, Int]("midLineWidth", _.midLineWidth(), _.setMidLineWidth(_), true)
  val OpenExternalLinks: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("openExternalLinks", _.openExternalLinks(), _.setOpenExternalLinks(_), true)
  val Pixmap: ExternalVar.Aux[Label, io.qt.gui.QPixmap | Null] = ExternalVar[Label, io.qt.gui.QPixmap | Null]("pixmap", _.pixmap(), _.setPixmap(_), true)
  val ScaledContents: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("scaledContents", _.hasScaledContents(), _.setScaledContents(_), true)
  val SelectedText: ExternalObsVal.Aux[Label, java.lang.String | Null] = ExternalObsVal[Label, java.lang.String | Null]("selectedText", _.selectedText())
  val Text: ExternalVar.Aux[Label, java.lang.String | Null] = ExternalVar[Label, java.lang.String | Null]("text", _.text(), _.setText(_), true)
  val TextFormat: ExternalVar.Aux[Label, io.qt.core.Qt.TextFormat] = ExternalVar[Label, io.qt.core.Qt.TextFormat]("textFormat", _.textFormat().unn, _.setTextFormat(_), true)
  val TextInteractionFlags: ExternalVar.Aux[Label, io.qt.core.Qt.TextInteractionFlags | Null] = ExternalVar[Label, io.qt.core.Qt.TextInteractionFlags | Null]("textInteractionFlags", _.textInteractionFlags(), _.setTextInteractionFlags(_), true)
  val WordWrap: ExternalVar.Aux[Label, Boolean] = ExternalVar[Label, Boolean]("wordWrap", _.wordWrap(), _.setWordWrap(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: Label) {
      def alignment: Var.Aux[io.qt.core.Qt.Alignment | Null, v.type] = Label.Alignment.asInstanceOf[Var.Aux[io.qt.core.Qt.Alignment | Null, v.type]]
      def frameRect: Var.Aux[io.qt.core.QRect | Null, v.type] = Label.FrameRect.asInstanceOf[Var.Aux[io.qt.core.QRect | Null, v.type]]
      def frameShadow: Var.Aux[io.qt.widgets.QFrame.Shadow, v.type] = Label.FrameShadow.asInstanceOf[Var.Aux[io.qt.widgets.QFrame.Shadow, v.type]]
      def frameShape: Var.Aux[io.qt.widgets.QFrame.Shape, v.type] = Label.FrameShape.asInstanceOf[Var.Aux[io.qt.widgets.QFrame.Shape, v.type]]
      def frameWidth: ObsVal.Aux[Int, v.type] = Label.FrameWidth.asInstanceOf[ObsVal.Aux[Int, v.type]]
      def hasSelectedText: ObsVal.Aux[Boolean, v.type] = Label.HasSelectedText.asInstanceOf[ObsVal.Aux[Boolean, v.type]]
      def indent: Var.Aux[Int, v.type] = Label.Indent.asInstanceOf[Var.Aux[Int, v.type]]
      def lineWidth: Var.Aux[Int, v.type] = Label.LineWidth.asInstanceOf[Var.Aux[Int, v.type]]
      def margin: Var.Aux[Int, v.type] = Label.Margin.asInstanceOf[Var.Aux[Int, v.type]]
      def midLineWidth: Var.Aux[Int, v.type] = Label.MidLineWidth.asInstanceOf[Var.Aux[Int, v.type]]
      def openExternalLinks: Var.Aux[Boolean, v.type] = Label.OpenExternalLinks.asInstanceOf[Var.Aux[Boolean, v.type]]
      def pixmap: Var.Aux[io.qt.gui.QPixmap | Null, v.type] = Label.Pixmap.asInstanceOf[Var.Aux[io.qt.gui.QPixmap | Null, v.type]]
      def scaledContents: Var.Aux[Boolean, v.type] = Label.ScaledContents.asInstanceOf[Var.Aux[Boolean, v.type]]
      def selectedText: ObsVal.Aux[java.lang.String | Null, v.type] = Label.SelectedText.asInstanceOf[ObsVal.Aux[java.lang.String | Null, v.type]]
      def text: Var.Aux[java.lang.String | Null, v.type] = Label.Text.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def textFormat: Var.Aux[io.qt.core.Qt.TextFormat, v.type] = Label.TextFormat.asInstanceOf[Var.Aux[io.qt.core.Qt.TextFormat, v.type]]
      def textInteractionFlags: Var.Aux[io.qt.core.Qt.TextInteractionFlags | Null, v.type] = Label.TextInteractionFlags.asInstanceOf[Var.Aux[io.qt.core.Qt.TextInteractionFlags | Null, v.type]]
      def wordWrap: Var.Aux[Boolean, v.type] = Label.WordWrap.asInstanceOf[Var.Aux[Boolean, v.type]]

      

      def setDisabled(arg0: Boolean) = v.setDisabled(arg0)
      def setHidden(arg0: Boolean) = v.setHidden(arg0)
      def grab(arg0: io.qt.core.QRect | Null) = v.grab(arg0)
      def setText(arg0: java.lang.String | Null) = v.setText(arg0)
      def setPixmap(arg0: io.qt.gui.QPixmap | Null) = v.setPixmap(arg0)
      def setPicture(arg0: io.qt.gui.QPicture | Null) = v.setPicture(arg0)
      def setMovie(arg0: io.qt.gui.QMovie | Null) = v.setMovie(arg0)
      def setNum(arg0: Int) = v.setNum(arg0)
      def setNum(arg0: Double) = v.setNum(arg0)
      def clear() = v.clear()
      def unwrap: io.qt.widgets.QLabel = v
    }
  }

  def wrap(v: io.qt.widgets.QLabel): Label = 
    val res = v.asInstanceOf[Label]
    if !Toolkit.stateReader(LabelInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: Label): Unit = {
    FramedWidget.init(v)
    Toolkit.update(LabelInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): Label = {
    val res = new io.qt.widgets.QLabel()
    
    res.asInstanceOf[Label]
  }
  
  def apply(
    
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    alignment: Opt[Binding[io.qt.core.Qt.Alignment | Null]] = UnsetParam,
    autoFillBackground: Opt[Binding[Boolean]] = UnsetParam,
    baseSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    frameRect: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    frameShadow: Opt[Binding[io.qt.widgets.QFrame.Shadow]] = UnsetParam,
    frameShape: Opt[Binding[io.qt.widgets.QFrame.Shape]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    indent: Opt[Binding[Int]] = UnsetParam,
    inputMethodHints: Opt[Binding[io.qt.core.Qt.InputMethodHints | Null]] = UnsetParam,
    layoutDirection: Opt[Binding[io.qt.core.Qt.LayoutDirection]] = UnsetParam,
    lineWidth: Opt[Binding[Int]] = UnsetParam,
    locale: Opt[Binding[io.qt.core.QLocale | Null]] = UnsetParam,
    margin: Opt[Binding[Int]] = UnsetParam,
    maximumHeight: Opt[Binding[Int]] = UnsetParam,
    maximumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    maximumWidth: Opt[Binding[Int]] = UnsetParam,
    midLineWidth: Opt[Binding[Int]] = UnsetParam,
    minimumHeight: Opt[Binding[Int]] = UnsetParam,
    minimumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    minimumWidth: Opt[Binding[Int]] = UnsetParam,
    mouseTracking: Opt[Binding[Boolean]] = UnsetParam,
    objectName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    openExternalLinks: Opt[Binding[Boolean]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    pixmap: Opt[Binding[io.qt.gui.QPixmap | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    scaledContents: Opt[Binding[Boolean]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    textFormat: Opt[Binding[io.qt.core.Qt.TextFormat]] = UnsetParam,
    textInteractionFlags: Opt[Binding[io.qt.core.Qt.TextInteractionFlags | Null]] = UnsetParam,
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
    windowTitle: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    wordWrap: Opt[Binding[Boolean]] = UnsetParam
  ): ToolkitAction[Label] = {
    val res = uninitialized()
    Label.init(res)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(alignment, Label.ops.alignment(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(frameRect, Label.ops.frameRect(res) := _)
    ifSet(frameShadow, Label.ops.frameShadow(res) := _)
    ifSet(frameShape, Label.ops.frameShape(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(indent, Label.ops.indent(res) := _)
    ifSet(inputMethodHints, Widget.ops.inputMethodHints(res) := _)
    ifSet(layoutDirection, Widget.ops.layoutDirection(res) := _)
    ifSet(lineWidth, Label.ops.lineWidth(res) := _)
    ifSet(locale, Widget.ops.locale(res) := _)
    ifSet(margin, Label.ops.margin(res) := _)
    ifSet(maximumHeight, Widget.ops.maximumHeight(res) := _)
    ifSet(maximumSize, Widget.ops.maximumSize(res) := _)
    ifSet(maximumWidth, Widget.ops.maximumWidth(res) := _)
    ifSet(midLineWidth, Label.ops.midLineWidth(res) := _)
    ifSet(minimumHeight, Widget.ops.minimumHeight(res) := _)
    ifSet(minimumSize, Widget.ops.minimumSize(res) := _)
    ifSet(minimumWidth, Widget.ops.minimumWidth(res) := _)
    ifSet(mouseTracking, Widget.ops.mouseTracking(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(openExternalLinks, Label.ops.openExternalLinks(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pixmap, Label.ops.pixmap(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(scaledContents, Label.ops.scaledContents(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(text, Label.ops.text(res) := _)
    ifSet(textFormat, Label.ops.textFormat(res) := _)
    ifSet(textInteractionFlags, Label.ops.textInteractionFlags(res) := _)
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
    ifSet(wordWrap, Label.ops.wordWrap(res) := _)
    res
  }
  
}
        