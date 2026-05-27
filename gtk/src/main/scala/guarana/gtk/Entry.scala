package guarana
package gtk
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
    export unwrap.onActivate, unwrap.onIconPress, unwrap.onIconRelease
  }
  def init(v: Entry): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Entry = {
    val res = new org.gnome.gtk.Entry()
    res.asInstanceOf[Entry]
  }
}