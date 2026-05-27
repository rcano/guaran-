package guarana
package gtk
opaque type EditableLabel <: Widget = org.gnome.gtk.EditableLabel & Widget
object EditableLabel {
  val Alignment: ExternalVar.Aux[EditableLabel, Float] = ExternalVar[EditableLabel, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val Editable: ExternalVar.Aux[EditableLabel, Boolean] = ExternalVar[EditableLabel, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[EditableLabel, Boolean] = ExternalVar[EditableLabel, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val MaxWidthChars: ExternalVar.Aux[EditableLabel, Int] = ExternalVar[EditableLabel, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val Position: ExternalVar.Aux[EditableLabel, Int] = ExternalVar[EditableLabel, Int]("position", _.getPosition(), _.setPosition(_), true)
  val Text: ExternalVar.Aux[EditableLabel, java.lang.String] = ExternalVar[EditableLabel, java.lang.String]("text", _.getText(), _.setText(_), true)
  val WidthChars: ExternalVar.Aux[EditableLabel, Int] = ExternalVar[EditableLabel, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)
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