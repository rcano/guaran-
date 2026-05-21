package guarana
package gtk
opaque type SearchEntry <: Widget = org.gnome.gtk.SearchEntry & Widget
object SearchEntry {
  val InputHints: ExternalVar.Aux[SearchEntry, java.util.Set[org.gnome.gtk.InputHints] | Null] = ExternalVar[SearchEntry, java.util.Set[org.gnome.gtk.InputHints] | Null]("input-hints", _.getInputHints(), _.setInputHints(_), true)
  val InputPurpose: ExternalVar.Aux[SearchEntry, org.gnome.gtk.InputPurpose | Null] = ExternalVar[SearchEntry, org.gnome.gtk.InputPurpose | Null]("input-purpose", _.getInputPurpose(), _.setInputPurpose(_), true)
  val KeyCaptureWidget: ExternalVar.Aux[SearchEntry, org.gnome.gtk.Widget | Null] = ExternalVar[SearchEntry, org.gnome.gtk.Widget | Null]("key-capture-widget", _.getKeyCaptureWidget(), _.setKeyCaptureWidget(_), true)
  val PlaceholderText: ExternalVar.Aux[SearchEntry, java.lang.String | Null] = ExternalVar[SearchEntry, java.lang.String | Null]("placeholder-text", _.getPlaceholderText(), _.setPlaceholderText(_), true)
  val SearchDelay: ExternalVar.Aux[SearchEntry, Int] = ExternalVar[SearchEntry, Int]("search-delay", _.getSearchDelay(), _.setSearchDelay(_), true)
  ()
  extension (v: SearchEntry) {
    def unwrap: org.gnome.gtk.SearchEntry = v
    export unwrap.onActivate, unwrap.onNextMatch, unwrap.onPreviousMatch, unwrap.onSearchChanged, unwrap.onSearchStarted, unwrap.onStopSearch
  }
}