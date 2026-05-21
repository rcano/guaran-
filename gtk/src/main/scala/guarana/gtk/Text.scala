package guarana
package gtk
opaque type Text <: Widget = org.gnome.gtk.Text & Widget
object Text {
  val ActivatesDefault: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("activates-default", _.getActivatesDefault(), _.setActivatesDefault(_), true)
  val Attributes: ExternalVar.Aux[Text, org.gnome.pango.AttrList | Null] = ExternalVar[Text, org.gnome.pango.AttrList | Null]("attributes", _.getAttributes(), _.setAttributes(_), true)
  val Buffer: ExternalVar.Aux[Text, org.gnome.gtk.EntryBuffer | Null] = ExternalVar[Text, org.gnome.gtk.EntryBuffer | Null]("buffer", _.getBuffer(), _.setBuffer(_), true)
  val EnableEmojiCompletion: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("enable-emoji-completion", _.getEnableEmojiCompletion(), _.setEnableEmojiCompletion(_), true)
  val ExtraMenu: ExternalVar.Aux[Text, org.gnome.gio.MenuModel | Null] = ExternalVar[Text, org.gnome.gio.MenuModel | Null]("extra-menu", _.getExtraMenu(), _.setExtraMenu(_), true)
  val InputHints: ExternalVar.Aux[Text, java.util.Set[org.gnome.gtk.InputHints] | Null] = ExternalVar[Text, java.util.Set[org.gnome.gtk.InputHints] | Null]("input-hints", _.getInputHints(), _.setInputHints(_), true)
  val InputPurpose: ExternalVar.Aux[Text, org.gnome.gtk.InputPurpose | Null] = ExternalVar[Text, org.gnome.gtk.InputPurpose | Null]("input-purpose", _.getInputPurpose(), _.setInputPurpose(_), true)
  val InvisibleChar: ExternalVar.Aux[Text, Int] = ExternalVar[Text, Int]("invisible-char", _.getInvisibleChar(), _.setInvisibleChar(_), true)
  val MaxLength: ExternalVar.Aux[Text, Int] = ExternalVar[Text, Int]("max-length", _.getMaxLength(), _.setMaxLength(_), true)
  val OverwriteMode: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("overwrite-mode", _.getOverwriteMode(), _.setOverwriteMode(_), true)
  val PlaceholderText: ExternalVar.Aux[Text, java.lang.String | Null] = ExternalVar[Text, java.lang.String | Null]("placeholder-text", _.getPlaceholderText(), _.setPlaceholderText(_), true)
  val PropagateTextWidth: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("propagate-text-width", _.getPropagateTextWidth(), _.setPropagateTextWidth(_), true)
  val Tabs: ExternalVar.Aux[Text, org.gnome.pango.TabArray | Null] = ExternalVar[Text, org.gnome.pango.TabArray | Null]("tabs", _.getTabs(), _.setTabs(_), true)
  val TruncateMultiline: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("truncate-multiline", _.getTruncateMultiline(), _.setTruncateMultiline(_), true)
  val Visibility: ExternalVar.Aux[Text, Boolean] = ExternalVar[Text, Boolean]("visibility", _.getVisibility(), _.setVisibility(_), true)
  ()
  extension (v: Text) {
    def unwrap: org.gnome.gtk.Text = v
    export unwrap.onActivate, unwrap.onBackspace, unwrap.onCopyClipboard, unwrap.onCutClipboard, unwrap.onDeleteFromCursor, unwrap.onInsertAtCursor, unwrap.onInsertEmoji, unwrap.onMoveCursor, unwrap.onPasteClipboard, unwrap.onPreeditChanged, unwrap.onToggleOverwrite
  }
}