package guarana
package gtk
opaque type ActionBar <: Widget = org.gnome.gtk.ActionBar & Widget
object ActionBar {
  val Revealed: ExternalVar.Aux[ActionBar, Boolean] = ExternalVar[ActionBar, Boolean]("revealed", _.getRevealed(), _.setRevealed(_), true)
  ()
  extension (v: ActionBar) {
    def unwrap: org.gnome.gtk.ActionBar = v
  }
}