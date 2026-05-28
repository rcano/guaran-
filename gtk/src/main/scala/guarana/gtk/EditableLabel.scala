package guarana
package gtk
import util.*
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
    def alignment: Var.Aux[Float, v.type] = Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def editable: Var.Aux[Boolean, v.type] = Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def position: Var.Aux[Int, v.type] = Position.asInstanceOf[Var.Aux[Int, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def widthChars: Var.Aux[Int, v.type] = WidthChars.asInstanceOf[Var.Aux[Int, v.type]]
  }
  def init(v: EditableLabel): Unit = {
    Widget.init(v)
  }
  def uninitialized(): EditableLabel = {
    val res = new org.gnome.gtk.EditableLabel()
    res.asInstanceOf[EditableLabel]
  }
  def apply(alignment: Opt[Float] = UnsetParam, editable: Opt[Boolean] = UnsetParam, enableUndo: Opt[Boolean] = UnsetParam, maxWidthChars: Opt[Int] = UnsetParam, position: Opt[Int] = UnsetParam, text: Opt[java.lang.String] = UnsetParam, widthChars: Opt[Int] = UnsetParam): VarContextAction[EditableLabel] = {
    val res = uninitialized()
    init(res)
    ifSet(alignment, res.alignment := _)
    ifSet(editable, res.editable := _)
    ifSet(enableUndo, res.enableUndo := _)
    ifSet(maxWidthChars, res.maxWidthChars := _)
    ifSet(position, res.position := _)
    ifSet(text, res.text := _)
    ifSet(widthChars, res.widthChars := _)
    res
  }
}