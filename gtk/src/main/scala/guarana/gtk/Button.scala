package guarana
package gtk
opaque type Button <: Widget = org.gnome.gtk.Button & Widget
object Button {
  val CanShrink: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("can-shrink", _.getCanShrink(), _.setCanShrink(_), true)
  val Child: ExternalVar.Aux[Button, org.gnome.gtk.Widget | Null] = ExternalVar[Button, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val HasFrame: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val IconName: ExternalVar.Aux[Button, java.lang.String | Null] = ExternalVar[Button, java.lang.String | Null]("icon-name", _.getIconName(), _.setIconName(_), true)
  val Label: ExternalVar.Aux[Button, java.lang.String | Null] = ExternalVar[Button, java.lang.String | Null]("label", _.getLabel(), _.setLabel(_), true)
  val UseUnderline: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)
  ()
  extension (v: Button) {
    def unwrap: org.gnome.gtk.Button = v
    export unwrap.onActivate, unwrap.onClicked
  }
}