package guarana
package gtk
opaque type Spinner <: Widget = org.gnome.gtk.Spinner & Widget
object Spinner {
  val Spinning: ExternalVar.Aux[Spinner, Boolean] = ExternalVar[Spinner, Boolean]("spinning", _.getSpinning(), _.setSpinning(_), true)
  ()
  extension (v: Spinner) {
    def unwrap: org.gnome.gtk.Spinner = v
  }
}