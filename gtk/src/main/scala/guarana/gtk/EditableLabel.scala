package guarana
package gtk
import util.*
opaque type EditableLabel <: Widget = org.gnome.gtk.EditableLabel & Widget
object EditableLabel extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.EditableLabel): EditableLabel = {
    v.asInstanceOf
  }
  def init(v: EditableLabel): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: java.lang.String): EditableLabel = {
    val res = new org.gnome.gtk.EditableLabel(arg$0)
    res.asInstanceOf[EditableLabel]
  }
  def apply(arg$0: java.lang.String, alignment: Opt[Float] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, editable: Opt[Boolean] = UnsetParam, enableUndo: Opt[Boolean] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, maxWidthChars: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, position: Opt[Int] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, text: Opt[java.lang.String] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, widthChars: Opt[Int] = UnsetParam): ToolkitAction[Toolkit, EditableLabel] = {
    val res = uninitialized(arg$0)
    init(res)
    ifSet(alignment, res.alignment := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(editable, res.editable := _)
    ifSet(enableUndo, res.enableUndo := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(maxWidthChars, res.maxWidthChars := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(position, res.position := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(text, res.text := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(widthChars, res.widthChars := _)
    res
  }
}