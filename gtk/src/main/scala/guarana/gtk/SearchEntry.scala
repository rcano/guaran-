package guarana
package gtk
import util.*
opaque type SearchEntry <: Widget = org.gnome.gtk.SearchEntry & Widget
object SearchEntry extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.SearchEntry): SearchEntry = {
    v.asInstanceOf
  }
  def init(v: SearchEntry): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): SearchEntry = {
    val res = new org.gnome.gtk.SearchEntry()
    res.asInstanceOf[SearchEntry]
  }
  def apply(alignment: Opt[Float] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, editable: Opt[Boolean] = UnsetParam, enableUndo: Opt[Boolean] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, inputHints: Opt[java.util.Set[org.gnome.gtk.InputHints]] = UnsetParam, inputPurpose: Opt[org.gnome.gtk.InputPurpose] = UnsetParam, keyCaptureWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, maxWidthChars: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, placeholderText: Opt[java.lang.String | Null] = UnsetParam, position: Opt[Int] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, searchDelay: Opt[Int] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, text: Opt[java.lang.String] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, widthChars: Opt[Int] = UnsetParam): ToolkitAction[Toolkit, SearchEntry] = {
    val res = uninitialized()
    init(res)
    ifSet(alignment, res.alignment := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
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
    ifSet(inputHints, res.inputHints := _)
    ifSet(inputPurpose, res.inputPurpose := _)
    ifSet(keyCaptureWidget, res.keyCaptureWidget := _)
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
    ifSet(placeholderText, res.placeholderText := _)
    ifSet(position, res.position := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(searchDelay, res.searchDelay := _)
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