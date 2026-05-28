package guarana
package gtk
import util.*
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
    def alignment: Var.Aux[Float, v.type] = Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def editable: Var.Aux[Boolean, v.type] = Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def inputHints: Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type] = InputHints.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type]]
    def inputPurpose: Var.Aux[org.gnome.gtk.InputPurpose, v.type] = InputPurpose.asInstanceOf[Var.Aux[org.gnome.gtk.InputPurpose, v.type]]
    def keyCaptureWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = KeyCaptureWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def placeholderText: Var.Aux[java.lang.String | Null, v.type] = PlaceholderText.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def position: Var.Aux[Int, v.type] = Position.asInstanceOf[Var.Aux[Int, v.type]]
    def searchDelay: Var.Aux[Int, v.type] = SearchDelay.asInstanceOf[Var.Aux[Int, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def widthChars: Var.Aux[Int, v.type] = WidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    export unwrap.onActivate, unwrap.onNextMatch, unwrap.onPreviousMatch, unwrap.onSearchChanged, unwrap.onSearchStarted, unwrap.onStopSearch
  }
  def init(v: SearchEntry): Unit = {
    Widget.init(v)
  }
  def uninitialized(): SearchEntry = {
    val res = new org.gnome.gtk.SearchEntry()
    res.asInstanceOf[SearchEntry]
  }
  def apply(alignment: Opt[Float] = UnsetParam, editable: Opt[Boolean] = UnsetParam, enableUndo: Opt[Boolean] = UnsetParam, inputHints: Opt[java.util.Set[org.gnome.gtk.InputHints]] = UnsetParam, inputPurpose: Opt[org.gnome.gtk.InputPurpose] = UnsetParam, keyCaptureWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, maxWidthChars: Opt[Int] = UnsetParam, placeholderText: Opt[java.lang.String | Null] = UnsetParam, position: Opt[Int] = UnsetParam, searchDelay: Opt[Int] = UnsetParam, text: Opt[java.lang.String] = UnsetParam, widthChars: Opt[Int] = UnsetParam): VarContextAction[SearchEntry] = {
    val res = uninitialized()
    init(res)
    ifSet(alignment, res.alignment := _)
    ifSet(editable, res.editable := _)
    ifSet(enableUndo, res.enableUndo := _)
    ifSet(inputHints, res.inputHints := _)
    ifSet(inputPurpose, res.inputPurpose := _)
    ifSet(keyCaptureWidget, res.keyCaptureWidget := _)
    ifSet(maxWidthChars, res.maxWidthChars := _)
    ifSet(placeholderText, res.placeholderText := _)
    ifSet(position, res.position := _)
    ifSet(searchDelay, res.searchDelay := _)
    ifSet(text, res.text := _)
    ifSet(widthChars, res.widthChars := _)
    res
  }
}