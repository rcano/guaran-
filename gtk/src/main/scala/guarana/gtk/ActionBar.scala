package guarana
package gtk
opaque type ActionBar <: Widget = org.gnome.gtk.ActionBar & Widget
object ActionBar {
  val CenterWidget: ExternalVar.Aux[ActionBar, org.gnome.gtk.Widget | Null] = ExternalVar[ActionBar, org.gnome.gtk.Widget | Null]("center-widget", _.getCenterWidget(), _.setCenterWidget(_), true)
  val Revealed: ExternalVar.Aux[ActionBar, Boolean] = ExternalVar[ActionBar, Boolean]("revealed", _.getRevealed(), _.setRevealed(_), true)
  ()
  extension (v: ActionBar) {
    def unwrap: org.gnome.gtk.ActionBar = v
  }
  def init(v: ActionBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ActionBar = {
    val res = new org.gnome.gtk.ActionBar()
    res.asInstanceOf[ActionBar]
  }
}