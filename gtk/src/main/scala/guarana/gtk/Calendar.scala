package guarana
package gtk
import util.*
opaque type Calendar <: Widget = org.gnome.gtk.Calendar & Widget
object Calendar {
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
  def init(v: Calendar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Calendar = {
    val res = new org.gnome.gtk.Calendar()
    res.asInstanceOf[Calendar]
  }
  def apply(date: Opt[org.gnome.glib.DateTime] = UnsetParam, day: Opt[Int] = UnsetParam, month: Opt[Int] = UnsetParam, showDayNames: Opt[Boolean] = UnsetParam, showHeading: Opt[Boolean] = UnsetParam, showWeekNumbers: Opt[Boolean] = UnsetParam, year: Opt[Int] = UnsetParam): VarContextAction[Calendar] = {
    val res = uninitialized()
    init(res)
    ifSet(date, res.date := _)
    ifSet(day, res.day := _)
    ifSet(month, res.month := _)
    ifSet(showDayNames, res.showDayNames := _)
    ifSet(showHeading, res.showHeading := _)
    ifSet(showWeekNumbers, res.showWeekNumbers := _)
    ifSet(year, res.year := _)
    res
  }
}