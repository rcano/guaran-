package guarana
package gtk
opaque type Switch <: Widget = org.gnome.gtk.Switch & Widget
object Switch {
  val ActionName: ExternalVar.Aux[Switch, java.lang.String | Null] = ExternalVar[Switch, java.lang.String | Null]("action-name", _.getActionName(), _.setActionName(_), true)
  val ActionTargetValue: ExternalVar.Aux[Switch, org.gnome.glib.Variant | Null] = ExternalVar[Switch, org.gnome.glib.Variant | Null]("action-target-value", _.getActionTargetValue(), _.setActionTargetValue(_), true)
  val Active: ExternalVar.Aux[Switch, Boolean] = ExternalVar[Switch, Boolean]("active", _.getActive(), _.setActive(_), true)
  val State: ExternalVar.Aux[Switch, Boolean] = ExternalVar[Switch, Boolean]("state", _.getState(), _.setState(_), true)
  ()
  extension (v: Switch) {
    def unwrap: org.gnome.gtk.Switch = v
    export unwrap.onActivate, unwrap.onStateSet
  }
  def init(v: Switch): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Switch = {
    val res = new org.gnome.gtk.Switch()
    res.asInstanceOf[Switch]
  }
}