package guarana
package gtk
opaque type ShortcutLabel <: Widget = org.gnome.gtk.ShortcutLabel & Widget
object ShortcutLabel {
  val Accelerator: ExternalVar.Aux[ShortcutLabel, java.lang.String | Null] = ExternalVar[ShortcutLabel, java.lang.String | Null]("accelerator", _.getAccelerator(), _.setAccelerator(_), true)
  val DisabledText: ExternalVar.Aux[ShortcutLabel, java.lang.String | Null] = ExternalVar[ShortcutLabel, java.lang.String | Null]("disabled-text", _.getDisabledText(), _.setDisabledText(_), true)
  ()
  extension (v: ShortcutLabel) {
    def unwrap: org.gnome.gtk.ShortcutLabel = v
  }
}