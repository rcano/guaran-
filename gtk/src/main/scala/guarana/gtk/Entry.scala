package guarana
package gtk
import util.*
opaque type Entry <: Widget = org.gnome.gtk.Entry & Widget
object Entry {
  val ActivatesDefault: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("activates-default", _.getActivatesDefault(), _.setActivatesDefault(_), true)
  val Alignment: ExternalVar.Aux[Entry, Float] = ExternalVar[Entry, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val Buffer: ExternalVar.Aux[Entry, org.gnome.gtk.EntryBuffer] = ExternalVar[Entry, org.gnome.gtk.EntryBuffer]("buffer", _.getBuffer(), _.setBuffer(_), true)
  @deprecated("", "") val Completion: ExternalVar.Aux[Entry, org.gnome.gtk.EntryCompletion | Null] = ExternalVar[Entry, org.gnome.gtk.EntryCompletion | Null]("completion", _.getCompletion(), _.setCompletion(_), true)
  val Editable: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val ExtraMenu: ExternalVar.Aux[Entry, org.gnome.gio.MenuModel | Null] = ExternalVar[Entry, org.gnome.gio.MenuModel | Null]("extra-menu", _.getExtraMenu(), _.setExtraMenu(_), true)
  val HasFrame: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val InputHints: ExternalVar.Aux[Entry, java.util.Set[org.gnome.gtk.InputHints]] = ExternalVar[Entry, java.util.Set[org.gnome.gtk.InputHints]]("input-hints", _.getInputHints(), _.setInputHints(_), true)
  val InputPurpose: ExternalVar.Aux[Entry, org.gnome.gtk.InputPurpose] = ExternalVar[Entry, org.gnome.gtk.InputPurpose]("input-purpose", _.getInputPurpose(), _.setInputPurpose(_), true)
  val InvisibleChar: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("invisible-char", _.getInvisibleChar(), _.setInvisibleChar(_), true)
  val MaxLength: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("max-length", _.getMaxLength(), _.setMaxLength(_), true)
  val MaxWidthChars: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val OverwriteMode: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("overwrite-mode", _.getOverwriteMode(), _.setOverwriteMode(_), true)
  val PlaceholderText: ExternalVar.Aux[Entry, java.lang.String | Null] = ExternalVar[Entry, java.lang.String | Null]("placeholder-text", _.getPlaceholderText(), _.setPlaceholderText(_), true)
  val Position: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("position", _.getPosition(), _.setPosition(_), true)
  val ProgressFraction: ExternalVar.Aux[Entry, Double] = ExternalVar[Entry, Double]("progress-fraction", _.getProgressFraction(), _.setProgressFraction(_), true)
  val ProgressPulseStep: ExternalVar.Aux[Entry, Double] = ExternalVar[Entry, Double]("progress-pulse-step", _.getProgressPulseStep(), _.setProgressPulseStep(_), true)
  val Tabs: ExternalVar.Aux[Entry, org.gnome.pango.TabArray | Null] = ExternalVar[Entry, org.gnome.pango.TabArray | Null]("tabs", _.getTabs(), _.setTabs(_), true)
  val Text: ExternalVar.Aux[Entry, java.lang.String] = ExternalVar[Entry, java.lang.String]("text", _.getText(), _.setText(_), true)
  val Visibility: ExternalVar.Aux[Entry, Boolean] = ExternalVar[Entry, Boolean]("visibility", _.getVisibility(), _.setVisibility(_), true)
  val WidthChars: ExternalVar.Aux[Entry, Int] = ExternalVar[Entry, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)
  ()
  extension (v: Entry) {
    def unwrap: org.gnome.gtk.Entry = v
    def activatesDefault: Var.Aux[Boolean, v.type] = ActivatesDefault.asInstanceOf[Var.Aux[Boolean, v.type]]
    def alignment: Var.Aux[Float, v.type] = Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def buffer: Var.Aux[org.gnome.gtk.EntryBuffer, v.type] = Buffer.asInstanceOf[Var.Aux[org.gnome.gtk.EntryBuffer, v.type]]
    @deprecated("", "") def completion: Var.Aux[org.gnome.gtk.EntryCompletion | Null, v.type] = Completion.asInstanceOf[Var.Aux[org.gnome.gtk.EntryCompletion | Null, v.type]]
    def editable: Var.Aux[Boolean, v.type] = Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def extraMenu: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = ExtraMenu.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def hasFrame: Var.Aux[Boolean, v.type] = HasFrame.asInstanceOf[Var.Aux[Boolean, v.type]]
    def inputHints: Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type] = InputHints.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type]]
    def inputPurpose: Var.Aux[org.gnome.gtk.InputPurpose, v.type] = InputPurpose.asInstanceOf[Var.Aux[org.gnome.gtk.InputPurpose, v.type]]
    def invisibleChar: Var.Aux[Int, v.type] = InvisibleChar.asInstanceOf[Var.Aux[Int, v.type]]
    def maxLength: Var.Aux[Int, v.type] = MaxLength.asInstanceOf[Var.Aux[Int, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def overwriteMode: Var.Aux[Boolean, v.type] = OverwriteMode.asInstanceOf[Var.Aux[Boolean, v.type]]
    def placeholderText: Var.Aux[java.lang.String | Null, v.type] = PlaceholderText.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def position: Var.Aux[Int, v.type] = Position.asInstanceOf[Var.Aux[Int, v.type]]
    def progressFraction: Var.Aux[Double, v.type] = ProgressFraction.asInstanceOf[Var.Aux[Double, v.type]]
    def progressPulseStep: Var.Aux[Double, v.type] = ProgressPulseStep.asInstanceOf[Var.Aux[Double, v.type]]
    def tabs: Var.Aux[org.gnome.pango.TabArray | Null, v.type] = Tabs.asInstanceOf[Var.Aux[org.gnome.pango.TabArray | Null, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def visibility: Var.Aux[Boolean, v.type] = Visibility.asInstanceOf[Var.Aux[Boolean, v.type]]
    def widthChars: Var.Aux[Int, v.type] = WidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    export unwrap.onActivate, unwrap.onIconPress, unwrap.onIconRelease
  }
  def init(v: Entry): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Entry = {
    val res = new org.gnome.gtk.Entry()
    res.asInstanceOf[Entry]
  }
  def apply(activatesDefault: Opt[Boolean] = UnsetParam, alignment: Opt[Float] = UnsetParam, buffer: Opt[org.gnome.gtk.EntryBuffer] = UnsetParam, completion: Opt[org.gnome.gtk.EntryCompletion | Null] = UnsetParam, editable: Opt[Boolean] = UnsetParam, enableUndo: Opt[Boolean] = UnsetParam, extraMenu: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam, hasFrame: Opt[Boolean] = UnsetParam, inputHints: Opt[java.util.Set[org.gnome.gtk.InputHints]] = UnsetParam, inputPurpose: Opt[org.gnome.gtk.InputPurpose] = UnsetParam, invisibleChar: Opt[Int] = UnsetParam, maxLength: Opt[Int] = UnsetParam, maxWidthChars: Opt[Int] = UnsetParam, overwriteMode: Opt[Boolean] = UnsetParam, placeholderText: Opt[java.lang.String | Null] = UnsetParam, position: Opt[Int] = UnsetParam, progressFraction: Opt[Double] = UnsetParam, progressPulseStep: Opt[Double] = UnsetParam, tabs: Opt[org.gnome.pango.TabArray | Null] = UnsetParam, text: Opt[java.lang.String] = UnsetParam, visibility: Opt[Boolean] = UnsetParam, widthChars: Opt[Int] = UnsetParam): VarContextAction[Entry] = {
    val res = uninitialized()
    init(res)
    ifSet(activatesDefault, res.activatesDefault := _)
    ifSet(alignment, res.alignment := _)
    ifSet(buffer, res.buffer := _)
    ifSet(completion, res.completion := _)
    ifSet(editable, res.editable := _)
    ifSet(enableUndo, res.enableUndo := _)
    ifSet(extraMenu, res.extraMenu := _)
    ifSet(hasFrame, res.hasFrame := _)
    ifSet(inputHints, res.inputHints := _)
    ifSet(inputPurpose, res.inputPurpose := _)
    ifSet(invisibleChar, res.invisibleChar := _)
    ifSet(maxLength, res.maxLength := _)
    ifSet(maxWidthChars, res.maxWidthChars := _)
    ifSet(overwriteMode, res.overwriteMode := _)
    ifSet(placeholderText, res.placeholderText := _)
    ifSet(position, res.position := _)
    ifSet(progressFraction, res.progressFraction := _)
    ifSet(progressPulseStep, res.progressPulseStep := _)
    ifSet(tabs, res.tabs := _)
    ifSet(text, res.text := _)
    ifSet(visibility, res.visibility := _)
    ifSet(widthChars, res.widthChars := _)
    res
  }
}