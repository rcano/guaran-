package guarana
package gtk
opaque type EditableLabel <: Widget = org.gnome.gtk.EditableLabel & Widget
object EditableLabel {
  ()
  extension (v: EditableLabel) {
    def unwrap: org.gnome.gtk.EditableLabel = v
  }
  def init(v: EditableLabel): Unit = {
    Widget.init(v)
  }
  def uninitialized(): EditableLabel = {
    val res = new org.gnome.gtk.EditableLabel()
    res.asInstanceOf[EditableLabel]
  }
}