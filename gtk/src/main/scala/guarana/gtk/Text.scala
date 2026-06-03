
package guarana
package gtk

import guarana.util.*

opaque type Text <: guarana.gtk.Widget  = org.gnome.gtk.Text & guarana.gtk.Widget
object Text extends VarsMap {
  val ActivatesDefault: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("activates-default", _.getActivatesDefault(), _.setActivatesDefault(_), true)
  val Alignment: ExternalVar.Aux[Text, Float] = ExternalVar[Text, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val Attributes: ExternalVar.Aux[Text, org.gnome.pango.AttrList | Null] = ExternalVar[Text, org.gnome.pango.AttrList | Null]("attributes", _.getAttributes(), _.setAttributes(_), true)
  val Buffer: ExternalVar.Aux[Text, org.gnome.gtk.EntryBuffer] = ExternalVar[Text, org.gnome.gtk.EntryBuffer]("buffer", _.getBuffer(), _.setBuffer(_), true)
  val Editable: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableEmojiCompletion: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("enable-emoji-completion", _.getEnableEmojiCompletion(), _.setEnableEmojiCompletion(_), true)
  val EnableUndo: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val ExtraMenu: ExternalVar.Aux[Text, org.gnome.gio.MenuModel | Null] = ExternalVar[Text, org.gnome.gio.MenuModel | Null]("extra-menu", _.getExtraMenu(), _.setExtraMenu(_), true)
  val InputHints: ExternalVar.Aux[Text, java.util.Set[org.gnome.gtk.InputHints]] = ExternalVar[Text, java.util.Set[org.gnome.gtk.InputHints]]("input-hints", _.getInputHints(), _.setInputHints(_), true)
  val InputPurpose: ExternalVar.Aux[Text, org.gnome.gtk.InputPurpose] = ExternalVar[Text, org.gnome.gtk.InputPurpose]("input-purpose", _.getInputPurpose(), _.setInputPurpose(_), true)
  val InvisibleChar: ExternalVar.Aux[Text, Int] = ExternalVar[Text, Int]("invisible-char", _.getInvisibleChar(), _.setInvisibleChar(_), true)
  val MaxLength: ExternalVar.Aux[Text, Int] = ExternalVar[Text, Int]("max-length", _.getMaxLength(), _.setMaxLength(_), true)
  val MaxWidthChars: ExternalVar.Aux[Text, Int] = ExternalVar[Text, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val OverwriteMode: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("overwrite-mode", _.getOverwriteMode(), _.setOverwriteMode(_), true)
  val PlaceholderText: ExternalVar.Aux[Text, java.lang.String | Null] = ExternalVar[Text, java.lang.String | Null]("placeholder-text", _.getPlaceholderText(), _.setPlaceholderText(_), true)
  val Position: ExternalVar.Aux[Text, Int] = ExternalVar[Text, Int]("position", _.getPosition(), _.setPosition(_), true)
  val PropagateTextWidth: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("propagate-text-width", _.getPropagateTextWidth(), _.setPropagateTextWidth(_), true)
  val Tabs: ExternalVar.Aux[Text, org.gnome.pango.TabArray | Null] = ExternalVar[Text, org.gnome.pango.TabArray | Null]("tabs", _.getTabs(), _.setTabs(_), true)
  val Text: ExternalVar.Aux[Text, java.lang.String] = ExternalVar[Text, java.lang.String]("text", _.getText(), _.setText(_), true)
  val TruncateMultiline: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("truncate-multiline", _.getTruncateMultiline(), _.setTruncateMultiline(_), true)
  val Visibility: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("visibility", _.getVisibility(), _.setVisibility(_), true)
  val WidthChars: ExternalVar.Aux[Text, Int] = ExternalVar[Text, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)

  

  extension (v: Text) {
    def unwrap: org.gnome.gtk.Text = v

    def activatesDefault: Var.Aux[Boolean, v.type] = guarana.gtk.Text.ActivatesDefault.asInstanceOf[Var.Aux[Boolean, v.type]]
    def alignment: Var.Aux[Float, v.type] = guarana.gtk.Text.Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def attributes: Var.Aux[org.gnome.pango.AttrList | Null, v.type] = guarana.gtk.Text.Attributes.asInstanceOf[Var.Aux[org.gnome.pango.AttrList | Null, v.type]]
    def buffer: Var.Aux[org.gnome.gtk.EntryBuffer, v.type] = guarana.gtk.Text.Buffer.asInstanceOf[Var.Aux[org.gnome.gtk.EntryBuffer, v.type]]
    def editable: Var.Aux[Boolean, v.type] = guarana.gtk.Text.Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableEmojiCompletion: Var.Aux[Boolean, v.type] = guarana.gtk.Text.EnableEmojiCompletion.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = guarana.gtk.Text.EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def extraMenu: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = guarana.gtk.Text.ExtraMenu.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def inputHints: Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type] = guarana.gtk.Text.InputHints.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type]]
    def inputPurpose: Var.Aux[org.gnome.gtk.InputPurpose, v.type] = guarana.gtk.Text.InputPurpose.asInstanceOf[Var.Aux[org.gnome.gtk.InputPurpose, v.type]]
    def invisibleChar: Var.Aux[Int, v.type] = guarana.gtk.Text.InvisibleChar.asInstanceOf[Var.Aux[Int, v.type]]
    def maxLength: Var.Aux[Int, v.type] = guarana.gtk.Text.MaxLength.asInstanceOf[Var.Aux[Int, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = guarana.gtk.Text.MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def overwriteMode: Var.Aux[Boolean, v.type] = guarana.gtk.Text.OverwriteMode.asInstanceOf[Var.Aux[Boolean, v.type]]
    def placeholderText: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.Text.PlaceholderText.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def position: Var.Aux[Int, v.type] = guarana.gtk.Text.Position.asInstanceOf[Var.Aux[Int, v.type]]
    def propagateTextWidth: Var.Aux[Boolean, v.type] = guarana.gtk.Text.PropagateTextWidth.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tabs: Var.Aux[org.gnome.pango.TabArray | Null, v.type] = guarana.gtk.Text.Tabs.asInstanceOf[Var.Aux[org.gnome.pango.TabArray | Null, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = guarana.gtk.Text.Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def truncateMultiline: Var.Aux[Boolean, v.type] = guarana.gtk.Text.TruncateMultiline.asInstanceOf[Var.Aux[Boolean, v.type]]
    def visibility: Var.Aux[Boolean, v.type] = guarana.gtk.Text.Visibility.asInstanceOf[Var.Aux[Boolean, v.type]]
    def widthChars: Var.Aux[Int, v.type] = guarana.gtk.Text.WidthChars.asInstanceOf[Var.Aux[Int, v.type]]

    

    export unwrap.{
      onActivate,
      onBackspace,
      onCopyClipboard,
      onCutClipboard,
      onDeleteFromCursor,
      onDestroy,
      onDirectionChanged,
      onHide,
      onInsertAtCursor,
      onInsertEmoji,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveCursor,
      onMoveFocus,
      onNotify,
      onPasteClipboard,
      onPreeditChanged,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onToggleOverwrite,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.Text): Text = 
    val res = v.asInstanceOf[Text]
    
    res

  def init(v: Text): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(): Text = {
    val res = new org.gnome.gtk.Text()
    
    res.asInstanceOf[Text]
  }
  
  def apply(
    
    activatesDefault: Opt[Binding[Boolean]] = UnsetParam,
    alignment: Opt[Binding[Float]] = UnsetParam,
    attributes: Opt[Binding[org.gnome.pango.AttrList | Null]] = UnsetParam,
    buffer: Opt[Binding[org.gnome.gtk.EntryBuffer]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    editable: Opt[Binding[Boolean]] = UnsetParam,
    enableEmojiCompletion: Opt[Binding[Boolean]] = UnsetParam,
    enableUndo: Opt[Binding[Boolean]] = UnsetParam,
    extraMenu: Opt[Binding[org.gnome.gio.MenuModel | Null]] = UnsetParam,
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
    invisibleChar: Opt[Binding[Int]] = UnsetParam,
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
    overwriteMode: Opt[Binding[Boolean]] = UnsetParam,
    placeholderText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    position: Opt[Binding[Int]] = UnsetParam,
    propagateTextWidth: Opt[Binding[Boolean]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tabs: Opt[Binding[org.gnome.pango.TabArray | Null]] = UnsetParam,
    text: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    truncateMultiline: Opt[Binding[Boolean]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visibility: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    widthChars: Opt[Binding[Int]] = UnsetParam
  ): VarContextAction[Text] = {
    val res = uninitialized()
    guarana.gtk.Text.init(res)
    ifSet(activatesDefault, res.activatesDefault := _)
    ifSet(alignment, res.alignment := _)
    ifSet(attributes, res.attributes := _)
    ifSet(buffer, res.buffer := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(editable, res.editable := _)
    ifSet(enableEmojiCompletion, res.enableEmojiCompletion := _)
    ifSet(enableUndo, res.enableUndo := _)
    ifSet(extraMenu, res.extraMenu := _)
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
    ifSet(invisibleChar, res.invisibleChar := _)
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
    ifSet(overwriteMode, res.overwriteMode := _)
    ifSet(placeholderText, res.placeholderText := _)
    ifSet(position, res.position := _)
    ifSet(propagateTextWidth, res.propagateTextWidth := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tabs, res.tabs := _)
    ifSet(text, res.text := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(truncateMultiline, res.truncateMultiline := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visibility, res.visibility := _)
    ifSet(visible, res.visible := _)
    ifSet(widthChars, res.widthChars := _)
    res
  }
  
}
        