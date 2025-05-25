
package guarana
package qt
        
import io.qt.widgets.*
import util.*

opaque type DateTimeSpinBox <: SpinBoxBase  = io.qt.widgets.QDateTimeEdit & SpinBoxBase
object DateTimeSpinBox {
  private val DateTimeSpinBoxInitialized: Var[Boolean] = Var[Boolean]("DateTimeSpinBoxInitialized", false, false)
  val CalendarPopup: ExternalVar.Aux[DateTimeSpinBox, Boolean] = ExternalVar[DateTimeSpinBox, Boolean]("calendarPopup", _.calendarPopup(), _.setCalendarPopup(_), true)
  val CurrentSection: ExternalVar.Aux[DateTimeSpinBox, io.qt.widgets.QDateTimeEdit.Section] = ExternalVar[DateTimeSpinBox, io.qt.widgets.QDateTimeEdit.Section]("currentSection", _.currentSection().unn, _.setCurrentSection(_), true)
  val CurrentSectionIndex: ExternalVar.Aux[DateTimeSpinBox, Int] = ExternalVar[DateTimeSpinBox, Int]("currentSectionIndex", _.currentSectionIndex(), _.setCurrentSectionIndex(_), true)
  val Date: ExternalVar.Aux[DateTimeSpinBox, io.qt.core.QDate | Null] = ExternalVar[DateTimeSpinBox, io.qt.core.QDate | Null]("date", _.date(), _.setDate(_), true)
  val DateTime: ExternalVar.Aux[DateTimeSpinBox, io.qt.core.QDateTime | Null] = ExternalVar[DateTimeSpinBox, io.qt.core.QDateTime | Null]("dateTime", _.dateTime(), _.setDateTime(_), true)
  val DisplayFormat: ExternalVar.Aux[DateTimeSpinBox, java.lang.String | Null] = ExternalVar[DateTimeSpinBox, java.lang.String | Null]("displayFormat", _.displayFormat(), _.setDisplayFormat(_), true)
  val DisplayedSections: ExternalObsVal.Aux[DateTimeSpinBox, io.qt.widgets.QDateTimeEdit.Sections | Null] = ExternalObsVal[DateTimeSpinBox, io.qt.widgets.QDateTimeEdit.Sections | Null]("displayedSections", _.displayedSections())
  val MaximumDate: ExternalVar.Aux[DateTimeSpinBox, io.qt.core.QDate | Null] = ExternalVar[DateTimeSpinBox, io.qt.core.QDate | Null]("maximumDate", _.maximumDate(), _.setMaximumDate(_), true)
  val MaximumDateTime: ExternalVar.Aux[DateTimeSpinBox, io.qt.core.QDateTime | Null] = ExternalVar[DateTimeSpinBox, io.qt.core.QDateTime | Null]("maximumDateTime", _.maximumDateTime(), _.setMaximumDateTime(_), true)
  val MaximumTime: ExternalVar.Aux[DateTimeSpinBox, io.qt.core.QTime | Null] = ExternalVar[DateTimeSpinBox, io.qt.core.QTime | Null]("maximumTime", _.maximumTime(), _.setMaximumTime(_), true)
  val MinimumDate: ExternalVar.Aux[DateTimeSpinBox, io.qt.core.QDate | Null] = ExternalVar[DateTimeSpinBox, io.qt.core.QDate | Null]("minimumDate", _.minimumDate(), _.setMinimumDate(_), true)
  val MinimumDateTime: ExternalVar.Aux[DateTimeSpinBox, io.qt.core.QDateTime | Null] = ExternalVar[DateTimeSpinBox, io.qt.core.QDateTime | Null]("minimumDateTime", _.minimumDateTime(), _.setMinimumDateTime(_), true)
  val MinimumTime: ExternalVar.Aux[DateTimeSpinBox, io.qt.core.QTime | Null] = ExternalVar[DateTimeSpinBox, io.qt.core.QTime | Null]("minimumTime", _.minimumTime(), _.setMinimumTime(_), true)
  val SectionCount: ExternalObsVal.Aux[DateTimeSpinBox, Int] = ExternalObsVal[DateTimeSpinBox, Int]("sectionCount", _.sectionCount())
  val Time: ExternalVar.Aux[DateTimeSpinBox, io.qt.core.QTime | Null] = ExternalVar[DateTimeSpinBox, io.qt.core.QTime | Null]("time", _.time(), _.setTime(_), true)
  val TimeSpec: ExternalVar.Aux[DateTimeSpinBox, io.qt.core.Qt.TimeSpec] = ExternalVar[DateTimeSpinBox, io.qt.core.Qt.TimeSpec]("timeSpec", _.timeSpec().unn, _.setTimeSpec(_), true)

  

  given ops: Ops.type = Ops
  object Ops {
    extension (v: DateTimeSpinBox) {
      def calendarPopup: Var.Aux[Boolean, v.type] = DateTimeSpinBox.CalendarPopup.asInstanceOf[Var.Aux[Boolean, v.type]]
      def currentSection: Var.Aux[io.qt.widgets.QDateTimeEdit.Section, v.type] = DateTimeSpinBox.CurrentSection.asInstanceOf[Var.Aux[io.qt.widgets.QDateTimeEdit.Section, v.type]]
      def currentSectionIndex: Var.Aux[Int, v.type] = DateTimeSpinBox.CurrentSectionIndex.asInstanceOf[Var.Aux[Int, v.type]]
      def date: Var.Aux[io.qt.core.QDate | Null, v.type] = DateTimeSpinBox.Date.asInstanceOf[Var.Aux[io.qt.core.QDate | Null, v.type]]
      def dateTime: Var.Aux[io.qt.core.QDateTime | Null, v.type] = DateTimeSpinBox.DateTime.asInstanceOf[Var.Aux[io.qt.core.QDateTime | Null, v.type]]
      def displayFormat: Var.Aux[java.lang.String | Null, v.type] = DateTimeSpinBox.DisplayFormat.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
      def displayedSections: ObsVal.Aux[io.qt.widgets.QDateTimeEdit.Sections | Null, v.type] = DateTimeSpinBox.DisplayedSections.asInstanceOf[ObsVal.Aux[io.qt.widgets.QDateTimeEdit.Sections | Null, v.type]]
      def maximumDate: Var.Aux[io.qt.core.QDate | Null, v.type] = DateTimeSpinBox.MaximumDate.asInstanceOf[Var.Aux[io.qt.core.QDate | Null, v.type]]
      def maximumDateTime: Var.Aux[io.qt.core.QDateTime | Null, v.type] = DateTimeSpinBox.MaximumDateTime.asInstanceOf[Var.Aux[io.qt.core.QDateTime | Null, v.type]]
      def maximumTime: Var.Aux[io.qt.core.QTime | Null, v.type] = DateTimeSpinBox.MaximumTime.asInstanceOf[Var.Aux[io.qt.core.QTime | Null, v.type]]
      def minimumDate: Var.Aux[io.qt.core.QDate | Null, v.type] = DateTimeSpinBox.MinimumDate.asInstanceOf[Var.Aux[io.qt.core.QDate | Null, v.type]]
      def minimumDateTime: Var.Aux[io.qt.core.QDateTime | Null, v.type] = DateTimeSpinBox.MinimumDateTime.asInstanceOf[Var.Aux[io.qt.core.QDateTime | Null, v.type]]
      def minimumTime: Var.Aux[io.qt.core.QTime | Null, v.type] = DateTimeSpinBox.MinimumTime.asInstanceOf[Var.Aux[io.qt.core.QTime | Null, v.type]]
      def sectionCount: ObsVal.Aux[Int, v.type] = DateTimeSpinBox.SectionCount.asInstanceOf[ObsVal.Aux[Int, v.type]]
      def time: Var.Aux[io.qt.core.QTime | Null, v.type] = DateTimeSpinBox.Time.asInstanceOf[Var.Aux[io.qt.core.QTime | Null, v.type]]
      def timeSpec: Var.Aux[io.qt.core.Qt.TimeSpec, v.type] = DateTimeSpinBox.TimeSpec.asInstanceOf[Var.Aux[io.qt.core.Qt.TimeSpec, v.type]]

      

      def setDateTime(arg0: io.qt.core.QDateTime | Null) = v.setDateTime(arg0)
      def setDate(arg0: io.qt.core.QDate | Null) = v.setDate(arg0)
      def setTime(arg0: io.qt.core.QTime | Null) = v.setTime(arg0)
      def unwrap: io.qt.widgets.QDateTimeEdit = v
    }
  }

  def wrap(v: io.qt.widgets.QDateTimeEdit): DateTimeSpinBox = 
    val res = v.asInstanceOf[DateTimeSpinBox]
    if !Toolkit.stateReader(DateTimeSpinBoxInitialized.forInstance[v.type]) then init(res)
    res

  def init(v: DateTimeSpinBox): Unit = {
    SpinBoxBase.init(v)
    Toolkit.connectVar(Date.forInstance[v.type], v.dateChanged.unn)
    Toolkit.connectVar(DateTime.forInstance[v.type], v.dateTimeChanged.unn)
    Toolkit.connectVar(Time.forInstance[v.type], v.timeChanged.unn)
    Toolkit.update(DateTimeSpinBoxInitialized.forInstance[v.type] := true)
    
  }
  def uninitialized(): DateTimeSpinBox = {
    val res = new io.qt.widgets.QDateTimeEdit()
    
    res.asInstanceOf[DateTimeSpinBox]
  }
  
  def apply(
    
    accelerated: Opt[Binding[Boolean]] = UnsetParam,
    acceptDrops: Opt[Binding[Boolean]] = UnsetParam,
    accessibleDescription: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    accessibleName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    alignment: Opt[Binding[io.qt.core.Qt.Alignment | Null]] = UnsetParam,
    autoFillBackground: Opt[Binding[Boolean]] = UnsetParam,
    baseSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    buttonSymbols: Opt[Binding[io.qt.widgets.QAbstractSpinBox.ButtonSymbols]] = UnsetParam,
    calendarPopup: Opt[Binding[Boolean]] = UnsetParam,
    contextMenuPolicy: Opt[Binding[io.qt.core.Qt.ContextMenuPolicy]] = UnsetParam,
    correctionMode: Opt[Binding[io.qt.widgets.QAbstractSpinBox.CorrectionMode]] = UnsetParam,
    currentSection: Opt[Binding[io.qt.widgets.QDateTimeEdit.Section]] = UnsetParam,
    currentSectionIndex: Opt[Binding[Int]] = UnsetParam,
    cursor: Opt[Binding[io.qt.gui.QCursor | Null]] = UnsetParam,
    date: Opt[Binding[io.qt.core.QDate | Null]] = UnsetParam,
    dateTime: Opt[Binding[io.qt.core.QDateTime | Null]] = UnsetParam,
    displayFormat: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    enabled: Opt[Binding[Boolean]] = UnsetParam,
    focusPolicy: Opt[Binding[io.qt.core.Qt.FocusPolicy]] = UnsetParam,
    font: Opt[Binding[io.qt.gui.QFont | Null]] = UnsetParam,
    frame: Opt[Binding[Boolean]] = UnsetParam,
    geometry: Opt[Binding[io.qt.core.QRect | Null]] = UnsetParam,
    inputMethodHints: Opt[Binding[io.qt.core.Qt.InputMethodHints | Null]] = UnsetParam,
    keyboardTracking: Opt[Binding[Boolean]] = UnsetParam,
    layoutDirection: Opt[Binding[io.qt.core.Qt.LayoutDirection]] = UnsetParam,
    locale: Opt[Binding[io.qt.core.QLocale | Null]] = UnsetParam,
    maximumDate: Opt[Binding[io.qt.core.QDate | Null]] = UnsetParam,
    maximumDateTime: Opt[Binding[io.qt.core.QDateTime | Null]] = UnsetParam,
    maximumHeight: Opt[Binding[Int]] = UnsetParam,
    maximumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    maximumTime: Opt[Binding[io.qt.core.QTime | Null]] = UnsetParam,
    maximumWidth: Opt[Binding[Int]] = UnsetParam,
    minimumDate: Opt[Binding[io.qt.core.QDate | Null]] = UnsetParam,
    minimumDateTime: Opt[Binding[io.qt.core.QDateTime | Null]] = UnsetParam,
    minimumHeight: Opt[Binding[Int]] = UnsetParam,
    minimumSize: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    minimumTime: Opt[Binding[io.qt.core.QTime | Null]] = UnsetParam,
    minimumWidth: Opt[Binding[Int]] = UnsetParam,
    mouseTracking: Opt[Binding[Boolean]] = UnsetParam,
    objectName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    palette: Opt[Binding[io.qt.gui.QPalette | Null]] = UnsetParam,
    pos: Opt[Binding[io.qt.core.QPoint | Null]] = UnsetParam,
    readOnly: Opt[Binding[Boolean]] = UnsetParam,
    showGroupSeparator: Opt[Binding[Boolean]] = UnsetParam,
    size: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizeIncrement: Opt[Binding[io.qt.core.QSize | Null]] = UnsetParam,
    sizePolicy: Opt[Binding[io.qt.widgets.QSizePolicy | Null]] = UnsetParam,
    specialValueText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    statusTip: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    styleSheet: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tabletTracking: Opt[Binding[Boolean]] = UnsetParam,
    time: Opt[Binding[io.qt.core.QTime | Null]] = UnsetParam,
    timeSpec: Opt[Binding[io.qt.core.Qt.TimeSpec]] = UnsetParam,
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
    wrapping: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[DateTimeSpinBox] = {
    val res = uninitialized()
    DateTimeSpinBox.init(res)
    ifSet(accelerated, SpinBoxBase.ops.accelerated(res) := _)
    ifSet(acceptDrops, Widget.ops.acceptDrops(res) := _)
    ifSet(accessibleDescription, Widget.ops.accessibleDescription(res) := _)
    ifSet(accessibleName, Widget.ops.accessibleName(res) := _)
    ifSet(alignment, SpinBoxBase.ops.alignment(res) := _)
    ifSet(autoFillBackground, Widget.ops.autoFillBackground(res) := _)
    ifSet(baseSize, Widget.ops.baseSize(res) := _)
    ifSet(buttonSymbols, SpinBoxBase.ops.buttonSymbols(res) := _)
    ifSet(calendarPopup, DateTimeSpinBox.ops.calendarPopup(res) := _)
    ifSet(contextMenuPolicy, Widget.ops.contextMenuPolicy(res) := _)
    ifSet(correctionMode, SpinBoxBase.ops.correctionMode(res) := _)
    ifSet(currentSection, DateTimeSpinBox.ops.currentSection(res) := _)
    ifSet(currentSectionIndex, DateTimeSpinBox.ops.currentSectionIndex(res) := _)
    ifSet(cursor, Widget.ops.cursor(res) := _)
    ifSet(date, DateTimeSpinBox.ops.date(res) := _)
    ifSet(dateTime, DateTimeSpinBox.ops.dateTime(res) := _)
    ifSet(displayFormat, DateTimeSpinBox.ops.displayFormat(res) := _)
    ifSet(enabled, Widget.ops.enabled(res) := _)
    ifSet(focusPolicy, Widget.ops.focusPolicy(res) := _)
    ifSet(font, Widget.ops.font(res) := _)
    ifSet(frame, SpinBoxBase.ops.frame(res) := _)
    ifSet(geometry, Widget.ops.geometry(res) := _)
    ifSet(inputMethodHints, Widget.ops.inputMethodHints(res) := _)
    ifSet(keyboardTracking, SpinBoxBase.ops.keyboardTracking(res) := _)
    ifSet(layoutDirection, Widget.ops.layoutDirection(res) := _)
    ifSet(locale, Widget.ops.locale(res) := _)
    ifSet(maximumDate, DateTimeSpinBox.ops.maximumDate(res) := _)
    ifSet(maximumDateTime, DateTimeSpinBox.ops.maximumDateTime(res) := _)
    ifSet(maximumHeight, Widget.ops.maximumHeight(res) := _)
    ifSet(maximumSize, Widget.ops.maximumSize(res) := _)
    ifSet(maximumTime, DateTimeSpinBox.ops.maximumTime(res) := _)
    ifSet(maximumWidth, Widget.ops.maximumWidth(res) := _)
    ifSet(minimumDate, DateTimeSpinBox.ops.minimumDate(res) := _)
    ifSet(minimumDateTime, DateTimeSpinBox.ops.minimumDateTime(res) := _)
    ifSet(minimumHeight, Widget.ops.minimumHeight(res) := _)
    ifSet(minimumSize, Widget.ops.minimumSize(res) := _)
    ifSet(minimumTime, DateTimeSpinBox.ops.minimumTime(res) := _)
    ifSet(minimumWidth, Widget.ops.minimumWidth(res) := _)
    ifSet(mouseTracking, Widget.ops.mouseTracking(res) := _)
    ifSet(objectName, Widget.ops.objectName(res) := _)
    ifSet(palette, Widget.ops.palette(res) := _)
    ifSet(pos, Widget.ops.pos(res) := _)
    ifSet(readOnly, SpinBoxBase.ops.readOnly(res) := _)
    ifSet(showGroupSeparator, SpinBoxBase.ops.showGroupSeparator(res) := _)
    ifSet(size, Widget.ops.size(res) := _)
    ifSet(sizeIncrement, Widget.ops.sizeIncrement(res) := _)
    ifSet(sizePolicy, Widget.ops.sizePolicy(res) := _)
    ifSet(specialValueText, SpinBoxBase.ops.specialValueText(res) := _)
    ifSet(statusTip, Widget.ops.statusTip(res) := _)
    ifSet(styleSheet, Widget.ops.styleSheet(res) := _)
    ifSet(tabletTracking, Widget.ops.tabletTracking(res) := _)
    ifSet(time, DateTimeSpinBox.ops.time(res) := _)
    ifSet(timeSpec, DateTimeSpinBox.ops.timeSpec(res) := _)
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
    ifSet(wrapping, SpinBoxBase.ops.wrapping(res) := _)
    res
  }
  
}
        