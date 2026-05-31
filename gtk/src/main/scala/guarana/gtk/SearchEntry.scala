
package guarana
package gtk

import guarana.util.*

opaque type SearchEntry <: guarana.gtk.Widget  = org.gnome.gtk.SearchEntry & guarana.gtk.Widget
object SearchEntry extends VarsMap {
  val Alignment: ExternalVar.Aux[SearchEntry, Float] = ExternalVar[SearchEntry, Float]("alignment", _.getAlignment(), _.setAlignment(_), true)
  val Editable: ExternalVar.Aux[SearchEntry, Boolean] = ExternalVar[SearchEntry, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val EnableUndo: ExternalVar.Aux[SearchEntry, Boolean] = ExternalVar[SearchEntry, Boolean]("enable-undo", _.getEnableUndo(), _.setEnableUndo(_), true)
  val InputHints: ExternalVar.Aux[SearchEntry, java.util.Set[org.gnome.gtk.InputHints]] = ExternalVar[SearchEntry, java.util.Set[org.gnome.gtk.InputHints]]("input-hints", _.getInputHints(), _.setInputHints(_), true)
  val InputPurpose: ExternalVar.Aux[SearchEntry, org.gnome.gtk.InputPurpose] = ExternalVar[SearchEntry, org.gnome.gtk.InputPurpose]("input-purpose", _.getInputPurpose(), _.setInputPurpose(_), true)
  val KeyCaptureWidget: ExternalVar.Aux[SearchEntry, guarana.gtk.Widget | Null] = ExternalVar[SearchEntry, guarana.gtk.Widget | Null]("key-capture-widget", _.getKeyCaptureWidget().?(guarana.gtk.Widget.wrap), (n, v) => n.setKeyCaptureWidget(v.?(_.unwrap)), true)
  val MaxWidthChars: ExternalVar.Aux[SearchEntry, Int] = ExternalVar[SearchEntry, Int]("max-width-chars", _.getMaxWidthChars(), _.setMaxWidthChars(_), true)
  val PlaceholderText: ExternalVar.Aux[SearchEntry, java.lang.String | Null] = ExternalVar[SearchEntry, java.lang.String | Null]("placeholder-text", _.getPlaceholderText(), _.setPlaceholderText(_), true)
  val Position: ExternalVar.Aux[SearchEntry, Int] = ExternalVar[SearchEntry, Int]("position", _.getPosition(), _.setPosition(_), true)
  val SearchDelay: ExternalVar.Aux[SearchEntry, Int] = ExternalVar[SearchEntry, Int]("search-delay", _.getSearchDelay(), _.setSearchDelay(_), true)
  val Text: ExternalVar.Aux[SearchEntry, java.lang.String] = ExternalVar[SearchEntry, java.lang.String]("text", _.getText(), _.setText(_), true)
  val WidthChars: ExternalVar.Aux[SearchEntry, Int] = ExternalVar[SearchEntry, Int]("width-chars", _.getWidthChars(), _.setWidthChars(_), true)

  

  extension (v: SearchEntry) {
    def unwrap: org.gnome.gtk.SearchEntry = v

    def alignment: Var.Aux[Float, v.type] = guarana.gtk.SearchEntry.Alignment.asInstanceOf[Var.Aux[Float, v.type]]
    def editable: Var.Aux[Boolean, v.type] = guarana.gtk.SearchEntry.Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def enableUndo: Var.Aux[Boolean, v.type] = guarana.gtk.SearchEntry.EnableUndo.asInstanceOf[Var.Aux[Boolean, v.type]]
    def inputHints: Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type] = guarana.gtk.SearchEntry.InputHints.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type]]
    def inputPurpose: Var.Aux[org.gnome.gtk.InputPurpose, v.type] = guarana.gtk.SearchEntry.InputPurpose.asInstanceOf[Var.Aux[org.gnome.gtk.InputPurpose, v.type]]
    def keyCaptureWidget: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.SearchEntry.KeyCaptureWidget.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def maxWidthChars: Var.Aux[Int, v.type] = guarana.gtk.SearchEntry.MaxWidthChars.asInstanceOf[Var.Aux[Int, v.type]]
    def placeholderText: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.SearchEntry.PlaceholderText.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def position: Var.Aux[Int, v.type] = guarana.gtk.SearchEntry.Position.asInstanceOf[Var.Aux[Int, v.type]]
    def searchDelay: Var.Aux[Int, v.type] = guarana.gtk.SearchEntry.SearchDelay.asInstanceOf[Var.Aux[Int, v.type]]
    def text: Var.Aux[java.lang.String, v.type] = guarana.gtk.SearchEntry.Text.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def widthChars: Var.Aux[Int, v.type] = guarana.gtk.SearchEntry.WidthChars.asInstanceOf[Var.Aux[Int, v.type]]

    

    export unwrap.{
      onActivate,
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNextMatch,
      onNotify,
      onPreviousMatch,
      onQueryTooltip,
      onRealize,
      onSearchChanged,
      onSearchStarted,
      onShow,
      onStateFlagsChanged,
      onStopSearch,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.SearchEntry): SearchEntry = 
    val res = v.asInstanceOf[SearchEntry]
    
    res

  def init(v: SearchEntry): Toolkit ?=> Unit = (tk: Toolkit) ?=> {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(): SearchEntry = {
    val res = new org.gnome.gtk.SearchEntry()
    
    res.asInstanceOf[SearchEntry]
  }
  
  def apply(
    
    alignment: Opt[Binding[Float]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    editable: Opt[Binding[Boolean]] = UnsetParam,
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
    keyCaptureWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    maxWidthChars: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    placeholderText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    position: Opt[Binding[Int]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    searchDelay: Opt[Binding[Int]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    text: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    widthChars: Opt[Binding[Int]] = UnsetParam
  ): Toolkit ?=> VarContextAction[SearchEntry] = {
    val res = uninitialized()
    guarana.gtk.SearchEntry.init(res)
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
        