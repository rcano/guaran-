package guarana
package gtk
opaque type CheckButton <: Widget = org.gnome.gtk.CheckButton & Widget
object CheckButton {
  val Active: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("active", _.getActive(), _.setActive(_), true)
  val Child: ExternalVar.Aux[CheckButton, org.gnome.gtk.Widget | Null] = ExternalVar[CheckButton, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Inconsistent: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("inconsistent", _.getInconsistent(), _.setInconsistent(_), true)
  val Label: ExternalVar.Aux[CheckButton, java.lang.String | Null] = ExternalVar[CheckButton, java.lang.String | Null]("label", _.getLabel(), _.setLabel(_), true)
  val UseUnderline: ExternalVar.Aux[CheckButton, Boolean] = ExternalVar[CheckButton, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)
  ()
  extension (v: CheckButton) {
    def unwrap: org.gnome.gtk.CheckButton = v
    export unwrap.onActivate, unwrap.onToggled
  }
}