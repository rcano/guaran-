package guarana
package gtk
opaque type Scrollbar <: Widget = org.gnome.gtk.Scrollbar & Widget
object Scrollbar {
  val Adjustment: ExternalVar.Aux[Scrollbar, org.gnome.gtk.Adjustment | Null] = ExternalVar[Scrollbar, org.gnome.gtk.Adjustment | Null]("adjustment", _.getAdjustment(), _.setAdjustment(_), true)
  ()
  extension (v: Scrollbar) {
    def unwrap: org.gnome.gtk.Scrollbar = v
  }
}