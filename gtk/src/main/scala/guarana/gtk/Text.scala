package guarana
package gtk
opaque type Text <: Widget = org.gnome.gtk.Text & Widget
object Text {
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
  ()
  extension (v: Text) {
    def unwrap: org.gnome.gtk.Text = v
    export unwrap.onActivate, unwrap.onBackspace, unwrap.onCopyClipboard, unwrap.onCutClipboard, unwrap.onDeleteFromCursor, unwrap.onInsertAtCursor, unwrap.onInsertEmoji, unwrap.onMoveCursor, unwrap.onPasteClipboard, unwrap.onPreeditChanged, unwrap.onToggleOverwrite
  }
  def init(v: Text): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Text = {
    val res = new org.gnome.gtk.Text()
    res.asInstanceOf[Text]
  }
}