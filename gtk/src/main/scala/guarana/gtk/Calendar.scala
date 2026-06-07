
package guarana
package gtk

import guarana.util.*

opaque type Calendar <: guarana.gtk.Widget  = org.gnome.gtk.Calendar & guarana.gtk.Widget
object Calendar extends VarsMap {
  val Date: ExternalVar.Aux[Calendar, org.gnome.glib.DateTime] = ExternalVar[Calendar, org.gnome.glib.DateTime]("date", _.getDate(), _.setDate(_), true)
  val Day: ExternalVar.Aux[Calendar, Int] = ExternalVar[Calendar, Int]("day", _.getDay(), _.setDay(_), true)
  val Month: ExternalVar.Aux[Calendar, Int] = ExternalVar[Calendar, Int]("month", _.getMonth(), _.setMonth(_), true)
  val ShowDayNames: ExternalVar.Aux[Calendar, Boolean] = ExternalVar[Calendar, Boolean]("show-day-names", _.getShowDayNames(), _.setShowDayNames(_), true)
  val ShowHeading: ExternalVar.Aux[Calendar, Boolean] = ExternalVar[Calendar, Boolean]("show-heading", _.getShowHeading(), _.setShowHeading(_), true)
  val ShowWeekNumbers: ExternalVar.Aux[Calendar, Boolean] = ExternalVar[Calendar, Boolean]("show-week-numbers", _.getShowWeekNumbers(), _.setShowWeekNumbers(_), true)
  val Year: ExternalVar.Aux[Calendar, Int] = ExternalVar[Calendar, Int]("year", _.getYear(), _.setYear(_), true)

  

  extension (v: Calendar) {
    def unwrap: org.gnome.gtk.Calendar = v

    def date: Var.Aux[org.gnome.glib.DateTime, v.type] = guarana.gtk.Calendar.Date.asInstanceOf[Var.Aux[org.gnome.glib.DateTime, v.type]]
    def day: Var.Aux[Int, v.type] = guarana.gtk.Calendar.Day.asInstanceOf[Var.Aux[Int, v.type]]
    def month: Var.Aux[Int, v.type] = guarana.gtk.Calendar.Month.asInstanceOf[Var.Aux[Int, v.type]]
    def showDayNames: Var.Aux[Boolean, v.type] = guarana.gtk.Calendar.ShowDayNames.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showHeading: Var.Aux[Boolean, v.type] = guarana.gtk.Calendar.ShowHeading.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showWeekNumbers: Var.Aux[Boolean, v.type] = guarana.gtk.Calendar.ShowWeekNumbers.asInstanceOf[Var.Aux[Boolean, v.type]]
    def year: Var.Aux[Int, v.type] = guarana.gtk.Calendar.Year.asInstanceOf[Var.Aux[Int, v.type]]

    

    export unwrap.{
      onDaySelected,
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNextMonth,
      onNextYear,
      onNotify,
      onPrevMonth,
      onPrevYear,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.Calendar): Calendar = 
    val res = v.asInstanceOf[Calendar]
    
    res

  def init(v: Calendar): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Calendar = {
    val res = org.gnome.gtk.Calendar.builder()
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    
    res.asInstanceOf[Calendar]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    date: Opt[Binding[org.gnome.glib.DateTime]] = UnsetParam,
    day: Opt[Binding[Int]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    month: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showDayNames: Opt[Binding[Boolean]] = UnsetParam,
    showHeading: Opt[Binding[Boolean]] = UnsetParam,
    showWeekNumbers: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    year: Opt[Binding[Int]] = UnsetParam
  ): VarContextAction[Calendar] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.Calendar.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
        