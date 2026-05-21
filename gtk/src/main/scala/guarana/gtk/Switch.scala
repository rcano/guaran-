package guarana
package gtk
opaque type Switch <: Widget = org.gnome.gtk.Switch & Widget
object Switch {
  val Active: ExternalVar.Aux[Switch, Boolean] = ExternalVar[Switch, Boolean]("active", _.getActive(), _.setActive(_), true)
  val State: ExternalVar.Aux[Switch, Boolean] = ExternalVar[Switch, Boolean]("state", _.getState(), _.setState(_), true)
  ()
  extension (v: Switch) {
    def unwrap: org.gnome.gtk.Switch = v
    export unwrap.onActivate, unwrap.onStateSet
  }
}