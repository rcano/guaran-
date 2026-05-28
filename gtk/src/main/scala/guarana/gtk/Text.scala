package guarana
package gtk
import util.*
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
    def activatesDefault: Var.Aux[Boolean, v.type] = ActivatesDefault.asInstanceOf[Var.Aux[Boolean, v.type]]
    def alignment: Var.Aux[Float, v.type] = Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def attributes: Var.Aux[org.gnome.pango.AttrList | Null, v.type] = Attributes.asInstanceOf[Var.Aux[org.gnome.pango.AttrList | Null, v.type]]
    def buffer: Var.Aux[org.gnome.gtk.EntryBuffer, v.type] = Buffer.asInstanceOf[Var.Aux[org.gnome.gtk.EntryBuffer, v.type]]
    def editable: Var.Aux[Boolean, v.type] = Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableEmojiCompletion: Var.Aux[Boolean, v.type] = EnableEmojiCompletion.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def extraMenu: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = ExtraMenu.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def inputHints: Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type] = InputHints.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type]]
    def inputPurpose: Var.Aux[org.gnome.gtk.InputPurpose, v.type] = InputPurpose.asInstanceOf[Var.Aux[org.gnome.gtk.InputPurpose, v.type]]
    def invisibleChar: Var.Aux[Int, v.type] = InvisibleChar.asInstanceOf[Var.Aux[Int, v.type]]
    def maxLength: Var.Aux[Int, v.type] = MaxLength.asInstanceOf[Var.Aux[Int, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def overwriteMode: Var.Aux[Boolean, v.type] = OverwriteMode.asInstanceOf[Var.Aux[Boolean, v.type]]
    def placeholderText: Var.Aux[java.lang.String | Null, v.type] = PlaceholderText.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def position: Var.Aux[Int, v.type] = Position.asInstanceOf[Var.Aux[Int, v.type]]
    def propagateTextWidth: Var.Aux[Boolean, v.type] = PropagateTextWidth.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tabs: Var.Aux[org.gnome.pango.TabArray | Null, v.type] = Tabs.asInstanceOf[Var.Aux[org.gnome.pango.TabArray | Null, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def truncateMultiline: Var.Aux[Boolean, v.type] = TruncateMultiline.asInstanceOf[Var.Aux[Boolean, v.type]]
    def visibility: Var.Aux[Boolean, v.type] = Visibility.asInstanceOf[Var.Aux[Boolean, v.type]]
    def widthChars: Var.Aux[Int, v.type] = WidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    export unwrap.onActivate, unwrap.onBackspace, unwrap.onCopyClipboard, unwrap.onCutClipboard, unwrap.onDeleteFromCursor, unwrap.onInsertAtCursor, unwrap.onInsertEmoji, unwrap.onMoveCursor, unwrap.onPasteClipboard, unwrap.onPreeditChanged, unwrap.onToggleOverwrite
  }
  def init(v: Text): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Text = {
    val res = new org.gnome.gtk.Text()
    res.asInstanceOf[Text]
  }
  def apply(activatesDefault: Opt[Boolean] = UnsetParam, alignment: Opt[Float] = UnsetParam, attributes: Opt[org.gnome.pango.AttrList | Null] = UnsetParam, buffer: Opt[org.gnome.gtk.EntryBuffer] = UnsetParam, editable: Opt[Boolean] = UnsetParam, enableEmojiCompletion: Opt[Boolean] = UnsetParam, enableUndo: Opt[Boolean] = UnsetParam, extraMenu: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam, inputHints: Opt[java.util.Set[org.gnome.gtk.InputHints]] = UnsetParam, inputPurpose: Opt[org.gnome.gtk.InputPurpose] = UnsetParam, invisibleChar: Opt[Int] = UnsetParam, maxLength: Opt[Int] = UnsetParam, maxWidthChars: Opt[Int] = UnsetParam, overwriteMode: Opt[Boolean] = UnsetParam, placeholderText: Opt[java.lang.String | Null] = UnsetParam, position: Opt[Int] = UnsetParam, propagateTextWidth: Opt[Boolean] = UnsetParam, tabs: Opt[org.gnome.pango.TabArray | Null] = UnsetParam, text: Opt[java.lang.String] = UnsetParam, truncateMultiline: Opt[Boolean] = UnsetParam, visibility: Opt[Boolean] = UnsetParam, widthChars: Opt[Int] = UnsetParam): VarContextAction[Text] = {
    val res = uninitialized()
    init(res)
    ifSet(activatesDefault, res.activatesDefault := _)
    ifSet(alignment, res.alignment := _)
    ifSet(attributes, res.attributes := _)
    ifSet(buffer, res.buffer := _)
    ifSet(editable, res.editable := _)
    ifSet(enableEmojiCompletion, res.enableEmojiCompletion := _)
    ifSet(enableUndo, res.enableUndo := _)
    ifSet(extraMenu, res.extraMenu := _)
    ifSet(inputHints, res.inputHints := _)
    ifSet(inputPurpose, res.inputPurpose := _)
    ifSet(invisibleChar, res.invisibleChar := _)
    ifSet(maxLength, res.maxLength := _)
    ifSet(maxWidthChars, res.maxWidthChars := _)
    ifSet(overwriteMode, res.overwriteMode := _)
    ifSet(placeholderText, res.placeholderText := _)
    ifSet(position, res.position := _)
    ifSet(propagateTextWidth, res.propagateTextWidth := _)
    ifSet(tabs, res.tabs := _)
    ifSet(text, res.text := _)
    ifSet(truncateMultiline, res.truncateMultiline := _)
    ifSet(visibility, res.visibility := _)
    ifSet(widthChars, res.widthChars := _)
    res
  }
}