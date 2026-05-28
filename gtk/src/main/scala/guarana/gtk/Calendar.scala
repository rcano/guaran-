package guarana
package gtk
import util.*
opaque type Calendar <: Widget = org.gnome.gtk.Calendar & Widget
object Calendar extends VarsMap {
  val Date: ExternalVar.Aux[Calendar, org.gnome.glib.DateTime] = ExternalVar[Calendar, org.gnome.glib.DateTime]("date", _.getDate(), _.setDate(_), true)
  val Day: ExternalVar.Aux[Calendar, Int] = ExternalVar[Calendar, Int]("day", _.getDay(), _.setDay(_), true)
  val Month: ExternalVar.Aux[Calendar, Int] = ExternalVar[Calendar, Int]("month", _.getMonth(), _.setMonth(_), true)
  val ShowDayNames: ExternalVar.Aux[Calendar, Boolean] = ExternalVar[Calendar, Boolean]("show-day-names", _.getShowDayNames(), _.setShowDayNames(_), true)
  val ShowHeading: ExternalVar.Aux[Calendar, Boolean] = ExternalVar[Calendar, Boolean]("show-heading", _.getShowHeading(), _.setShowHeading(_), true)
  val ShowWeekNumbers: ExternalVar.Aux[Calendar, Boolean] = ExternalVar[Calendar, Boolean]("show-week-numbers", _.getShowWeekNumbers(), _.setShowWeekNumbers(_), true)
  val Year: ExternalVar.Aux[Calendar, Int] = ExternalVar[Calendar, Int]("year", _.getYear(), _.setYear(_), true)
  ()
  extension (v: Calendar) {
    def unwrap: org.gnome.gtk.Calendar = v
    def date: Var.Aux[org.gnome.glib.DateTime, v.type] = Date.asInstanceOf[Var.Aux[org.gnome.glib.DateTime, v.type]]
    def day: Var.Aux[Int, v.type] = Day.asInstanceOf[Var.Aux[Int, v.type]]
    def month: Var.Aux[Int, v.type] = Month.asInstanceOf[Var.Aux[Int, v.type]]
    def showDayNames: Var.Aux[Boolean, v.type] = ShowDayNames.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showHeading: Var.Aux[Boolean, v.type] = ShowHeading.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showWeekNumbers: Var.Aux[Boolean, v.type] = ShowWeekNumbers.asInstanceOf[Var.Aux[Boolean, v.type]]
    def year: Var.Aux[Int, v.type] = Year.asInstanceOf[Var.Aux[Int, v.type]]
    export unwrap.onDaySelected, unwrap.onNextMonth, unwrap.onNextYear, unwrap.onPrevMonth, unwrap.onPrevYear
  }
  def _wrap(v: org.gnome.gtk.Calendar): Calendar = {
    v.asInstanceOf
  }
  def init(v: Calendar): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): Calendar = {
    val res = new org.gnome.gtk.Calendar()
    res.asInstanceOf[Calendar]
  }
  def apply(canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, date: Opt[org.gnome.glib.DateTime] = UnsetParam, day: Opt[Int] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, month: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showDayNames: Opt[Boolean] = UnsetParam, showHeading: Opt[Boolean] = UnsetParam, showWeekNumbers: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, year: Opt[Int] = UnsetParam): ToolkitAction[Toolkit, Calendar] = {
    val res = uninitialized()
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(date, res.date := _)
    ifSet(day, res.day := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(month, res.month := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showDayNames, res.showDayNames := _)
    ifSet(showHeading, res.showHeading := _)
    ifSet(showWeekNumbers, res.showWeekNumbers := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(year, res.year := _)
    res
  }
}