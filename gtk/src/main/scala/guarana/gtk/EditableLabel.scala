package guarana
package gtk
opaque type EditableLabel <: Widget = org.gnome.gtk.EditableLabel & Widget
object EditableLabel {
  ()
  extension (v: EditableLabel) {
    def unwrap: org.gnome.gtk.EditableLabel = v
  }
}