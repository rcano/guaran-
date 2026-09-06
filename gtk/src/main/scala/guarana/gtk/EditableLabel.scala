
package guarana.gtk

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type EditableLabel <: guarana.gtk.Widget  = org.gnome.gtk.EditableLabel & guarana.gtk.Widget
object EditableLabel extends VarsMap {
  val Alignment: ExternalVar.Aux[EditableLabel, Float] = ExternalVar[EditableLabel, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val Editable: ExternalVar.Aux[EditableLabel, Boolean] = ExternalVar[EditableLabel, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[EditableLabel, Boolean] = ExternalVar[EditableLabel, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val MaxWidthChars: ExternalVar.Aux[EditableLabel, Int] = ExternalVar[EditableLabel, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val Position: ExternalVar.Aux[EditableLabel, Int] = ExternalVar[EditableLabel, Int]("position", _.getPosition(), _.setPosition(_), true)
  val Text: ExternalVar.Aux[EditableLabel, java.lang.String] = ExternalVar[EditableLabel, java.lang.String]("text", _.getText(), _.setText(_), true)
  val WidthChars: ExternalVar.Aux[EditableLabel, Int] = ExternalVar[EditableLabel, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)

  

  extension (v: EditableLabel) {
    def unwrap: org.gnome.gtk.EditableLabel = v

    def alignment: Var.Aux[Float, v.type] = guarana.gtk.EditableLabel.Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def editable: Var.Aux[Boolean, v.type] = guarana.gtk.EditableLabel.Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = guarana.gtk.EditableLabel.EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = guarana.gtk.EditableLabel.MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def position: Var.Aux[Int, v.type] = guarana.gtk.EditableLabel.Position.asInstanceOf[Var.Aux[Int, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = guarana.gtk.EditableLabel.Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def widthChars: Var.Aux[Int, v.type] = guarana.gtk.EditableLabel.WidthChars.asInstanceOf[Var.Aux[Int, v.type]]

    

    export unwrap.{
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.EditableLabel): EditableLabel = 
    val res = v.asInstanceOf[EditableLabel]
    
    res

  def init(v: EditableLabel): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(editing: Opt[Boolean], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], xalign: Opt[Float]): EditableLabel = {
    val res = {
      val res = org.gnome.gtk.EditableLabel.builder()
      ifSet(editing, v => res.setEditing(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      ifSet(xalign, v => res.setXalign(v))
      res.build()
    }
    
    res.asInstanceOf[EditableLabel]
  }
  
  def apply(
    editing: Opt[Boolean] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, xalign: Opt[Float] = UnsetParam,
    alignment: Opt[Binding[Float]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    editable: Opt[Binding[Boolean]] = UnsetParam,
    enableUndo: Opt[Binding[Boolean]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    maxWidthChars: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    position: Opt[Binding[Int]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    widthChars: Opt[Binding[Int]] = UnsetParam
  ): VarContextAction[EditableLabel] = {
    val res = uninitialized(editing, cssName, heightRequest, widthRequest, accessibleRole, xalign)
    guarana.gtk.EditableLabel.init(res)
    ifSet(alignment, res.alignment := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
    ifSet(parent, res.parent := _)
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
        