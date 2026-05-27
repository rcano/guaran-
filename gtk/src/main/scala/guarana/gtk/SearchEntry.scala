package guarana
package gtk
opaque type SearchEntry <: Widget = org.gnome.gtk.SearchEntry & Widget
object SearchEntry {
  val Alignment: ExternalVar.Aux[SearchEntry, Float] = ExternalVar[SearchEntry, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val Editable: ExternalVar.Aux[SearchEntry, Boolean] = ExternalVar[SearchEntry, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[SearchEntry, Boolean] = ExternalVar[SearchEntry, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val InputHints: ExternalVar.Aux[SearchEntry, java.util.Set[org.gnome.gtk.InputHints]] = ExternalVar[SearchEntry, java.util.Set[org.gnome.gtk.InputHints]]("input-hints", _.getInputHints(), _.setInputHints(_), true)
  val InputPurpose: ExternalVar.Aux[SearchEntry, org.gnome.gtk.InputPurpose] = ExternalVar[SearchEntry, org.gnome.gtk.InputPurpose]("input-purpose", _.getInputPurpose(), _.setInputPurpose(_), true)
  val KeyCaptureWidget: ExternalVar.Aux[SearchEntry, org.gnome.gtk.Widget | Null] = ExternalVar[SearchEntry, org.gnome.gtk.Widget | Null]("key-capture-widget", _.getKeyCaptureWidget(), _.setKeyCaptureWidget(_), true)
  val MaxWidthChars: ExternalVar.Aux[SearchEntry, Int] = ExternalVar[SearchEntry, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val PlaceholderText: ExternalVar.Aux[SearchEntry, java.lang.String | Null] = ExternalVar[SearchEntry, java.lang.String | Null]("placeholder-text", _.getPlaceholderText(), _.setPlaceholderText(_), true)
  val Position: ExternalVar.Aux[SearchEntry, Int] = ExternalVar[SearchEntry, Int]("position", _.getPosition(), _.setPosition(_), true)
  val SearchDelay: ExternalVar.Aux[SearchEntry, Int] = ExternalVar[SearchEntry, Int]("search-delay", _.getSearchDelay(), _.setSearchDelay(_), true)
  val Text: ExternalVar.Aux[SearchEntry, java.lang.String] = ExternalVar[SearchEntry, java.lang.String]("text", _.getText(), _.setText(_), true)
  val WidthChars: ExternalVar.Aux[SearchEntry, Int] = ExternalVar[SearchEntry, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)
  ()
  extension (v: SearchEntry) {
    def unwrap: org.gnome.gtk.SearchEntry = v
    export unwrap.onActivate, unwrap.onNextMatch, unwrap.onPreviousMatch, unwrap.onSearchChanged, unwrap.onSearchStarted, unwrap.onStopSearch
  }
  def init(v: SearchEntry): Unit = {
    Widget.init(v)
  }
  def uninitialized(): SearchEntry = {
    val res = new org.gnome.gtk.SearchEntry()
    res.asInstanceOf[SearchEntry]
  }
}