
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type EntryRow <: guarana.gtk.Widget  = org.gnome.adw.EntryRow & guarana.gtk.Widget
object EntryRow extends VarsMap {
  val ActivatesDefault: ExternalVar.Aux[EntryRow, Boolean] = ExternalVar[EntryRow, Boolean]("activates-default", _.getActivatesDefault(), _.setActivatesDefault(_), true)
  val Alignment: ExternalVar.Aux[EntryRow, Float] = ExternalVar[EntryRow, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val Attributes: ExternalVar.Aux[EntryRow, org.gnome.pango.AttrList | Null] = ExternalVar[EntryRow, org.gnome.pango.AttrList | Null]("attributes", _.getAttributes(), _.setAttributes(_), true)
  val Editable: ExternalVar.Aux[EntryRow, Boolean] = ExternalVar[EntryRow, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableEmojiCompletion: ExternalVar.Aux[EntryRow, Boolean] = ExternalVar[EntryRow, Boolean]("enable-emoji-completion", _.getEnableEmojiCompletion(), _.setEnableEmojiCompletion(_), true)
  val EnableUndo: ExternalVar.Aux[EntryRow, Boolean] = ExternalVar[EntryRow, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val InputHints: ExternalVar.Aux[EntryRow, java.util.Set[org.gnome.gtk.InputHints]] = ExternalVar[EntryRow, java.util.Set[org.gnome.gtk.InputHints]]("input-hints", _.getInputHints(), _.setInputHints(_), true)
  val InputPurpose: ExternalVar.Aux[EntryRow, org.gnome.gtk.InputPurpose] = ExternalVar[EntryRow, org.gnome.gtk.InputPurpose]("input-purpose", _.getInputPurpose(), _.setInputPurpose(_), true)
  val MaxLength: ExternalVar.Aux[EntryRow, Int] = ExternalVar[EntryRow, Int]("max-length", _.getMaxLength(), _.setMaxLength(_), true)
  val MaxWidthChars: ExternalVar.Aux[EntryRow, Int] = ExternalVar[EntryRow, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val Position: ExternalVar.Aux[EntryRow, Int] = ExternalVar[EntryRow, Int]("position", _.getPosition(), _.setPosition(_), true)
  val ShowApplyButton: ExternalVar.Aux[EntryRow, Boolean] = ExternalVar[EntryRow, Boolean]("show-apply-button", _.getShowApplyButton(), _.setShowApplyButton(_), true)
  val Text: ExternalVar.Aux[EntryRow, java.lang.String] = ExternalVar[EntryRow, java.lang.String]("text", _.getText(), _.setText(_), true)
  val WidthChars: ExternalVar.Aux[EntryRow, Int] = ExternalVar[EntryRow, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)

  

  extension (v: EntryRow) {
    def unwrap: org.gnome.adw.EntryRow = v

    def activatesDefault: Var.Aux[Boolean, v.type] = guarana.gtk.adw.EntryRow.ActivatesDefault.asInstanceOf[Var.Aux[Boolean, v.type]]
    def alignment: Var.Aux[Float, v.type] = guarana.gtk.adw.EntryRow.Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def attributes: Var.Aux[org.gnome.pango.AttrList | Null, v.type] = guarana.gtk.adw.EntryRow.Attributes.asInstanceOf[Var.Aux[org.gnome.pango.AttrList | Null, v.type]]
    def editable: Var.Aux[Boolean, v.type] = guarana.gtk.adw.EntryRow.Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableEmojiCompletion: Var.Aux[Boolean, v.type] = guarana.gtk.adw.EntryRow.EnableEmojiCompletion.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = guarana.gtk.adw.EntryRow.EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def inputHints: Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type] = guarana.gtk.adw.EntryRow.InputHints.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type]]
    def inputPurpose: Var.Aux[org.gnome.gtk.InputPurpose, v.type] = guarana.gtk.adw.EntryRow.InputPurpose.asInstanceOf[Var.Aux[org.gnome.gtk.InputPurpose, v.type]]
    def maxLength: Var.Aux[Int, v.type] = guarana.gtk.adw.EntryRow.MaxLength.asInstanceOf[Var.Aux[Int, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = guarana.gtk.adw.EntryRow.MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def position: Var.Aux[Int, v.type] = guarana.gtk.adw.EntryRow.Position.asInstanceOf[Var.Aux[Int, v.type]]
    def showApplyButton: Var.Aux[Boolean, v.type] = guarana.gtk.adw.EntryRow.ShowApplyButton.asInstanceOf[Var.Aux[Boolean, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.EntryRow.Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def widthChars: Var.Aux[Int, v.type] = guarana.gtk.adw.EntryRow.WidthChars.asInstanceOf[Var.Aux[Int, v.type]]

    

    export unwrap.{
      onActivate,
      onApply,
      onDestroy,
      onDirectionChanged,
      onEntryActivated,
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

  def wrap(v: org.gnome.adw.EntryRow): EntryRow = 
    val res = v.asInstanceOf[EntryRow]
    
    res

  def init(v: EntryRow): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], actionTarget: Opt[org.gnome.glib.Variant], xalign: Opt[Float]): EntryRow = {
    val res = {
      val res = org.gnome.adw.EntryRow.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      ifSet(actionTarget, v => res.setActionTarget(v))
      ifSet(xalign, v => res.setXalign(v))
      res.build()
    }
    
    res.asInstanceOf[EntryRow]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, actionTarget: Opt[org.gnome.glib.Variant] = UnsetParam, xalign: Opt[Float] = UnsetParam,
    activatesDefault: Opt[Binding[Boolean]] = UnsetParam,
    alignment: Opt[Binding[Float]] = UnsetParam,
    attributes: Opt[Binding[org.gnome.pango.AttrList | Null]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    editable: Opt[Binding[Boolean]] = UnsetParam,
    enableEmojiCompletion: Opt[Binding[Boolean]] = UnsetParam,
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
    inputHints: Opt[Binding[java.util.Set[org.gnome.gtk.InputHints]]] = UnsetParam,
    inputPurpose: Opt[Binding[org.gnome.gtk.InputPurpose]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    maxLength: Opt[Binding[Int]] = UnsetParam,
    maxWidthChars: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    position: Opt[Binding[Int]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showApplyButton: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    widthChars: Opt[Binding[Int]] = UnsetParam
  ): VarContextAction[EntryRow] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole, actionTarget, xalign)
    guarana.gtk.adw.EntryRow.init(res)
    ifSet(activatesDefault, res.activatesDefault := _)
    ifSet(alignment, res.alignment := _)
    ifSet(attributes, res.attributes := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(editable, res.editable := _)
    ifSet(enableEmojiCompletion, res.enableEmojiCompletion := _)
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
    ifSet(inputHints, res.inputHints := _)
    ifSet(inputPurpose, res.inputPurpose := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(maxLength, res.maxLength := _)
    ifSet(maxWidthChars, res.maxWidthChars := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(position, res.position := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showApplyButton, res.showApplyButton := _)
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
        