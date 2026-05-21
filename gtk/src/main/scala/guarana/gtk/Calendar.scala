package guarana
package gtk
opaque type Calendar <: Widget = org.gnome.gtk.Calendar & Widget
object Calendar {
  val Date: ExternalVar.Aux[Calendar, org.gnome.glib.DateTime | Null] = ExternalVar[Calendar, org.gnome.glib.DateTime | Null]("date", _.getDate(), _.setDate(_), true)
  val Day: ExternalVar.Aux[Calendar, Int] = ExternalVar[Calendar, Int]("day", _.getDay(), _.setDay(_), true)
  val Month: ExternalVar.Aux[Calendar, Int] = ExternalVar[Calendar, Int]("month", _.getMonth(), _.setMonth(_), true)
  val ShowDayNames: ExternalVar.Aux[Calendar, Boolean] = ExternalVar[Calendar, Boolean]("show-day-names", _.getShowDayNames(), _.setShowDayNames(_), true)
  val ShowHeading: ExternalVar.Aux[Calendar, Boolean] = ExternalVar[Calendar, Boolean]("show-heading", _.getShowHeading(), _.setShowHeading(_), true)
  val ShowWeekNumbers: ExternalVar.Aux[Calendar, Boolean] = ExternalVar[Calendar, Boolean]("show-week-numbers", _.getShowWeekNumbers(), _.setShowWeekNumbers(_), true)
  val Year: ExternalVar.Aux[Calendar, Int] = ExternalVar[Calendar, Int]("year", _.getYear(), _.setYear(_), true)
  ()
  extension (v: Calendar) {
    def unwrap: org.gnome.gtk.Calendar = v
    export unwrap.onDaySelected, unwrap.onNextMonth, unwrap.onNextYear, unwrap.onPrevMonth, unwrap.onPrevYear
  }
}