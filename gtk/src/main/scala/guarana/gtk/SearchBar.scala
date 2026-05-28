package guarana
package gtk
import util.*
opaque type SearchBar <: Widget = org.gnome.gtk.SearchBar & Widget
object SearchBar {
  val Child: ExternalVar.Aux[SearchBar, org.gnome.gtk.Widget | Null] = ExternalVar[SearchBar, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val KeyCaptureWidget: ExternalVar.Aux[SearchBar, org.gnome.gtk.Widget | Null] = ExternalVar[SearchBar, org.gnome.gtk.Widget | Null]("key-capture-widget", _.getKeyCaptureWidget(), _.setKeyCaptureWidget(_), true)
  val SearchMode: ExternalVar.Aux[SearchBar, Boolean] = ExternalVar[SearchBar, Boolean]("search-mode", _.getSearchMode(), _.setSearchMode(_), true)
  val ShowCloseButton: ExternalVar.Aux[SearchBar, Boolean] = ExternalVar[SearchBar, Boolean]("show-close-button", _.getShowCloseButton(), _.setShowCloseButton(_), true)
  ()
  extension (v: SearchBar) {
    def unwrap: org.gnome.gtk.SearchBar = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def keyCaptureWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = KeyCaptureWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def searchMode: Var.Aux[Boolean, v.type] = SearchMode.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showCloseButton: Var.Aux[Boolean, v.type] = ShowCloseButton.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def init(v: SearchBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): SearchBar = {
    val res = new org.gnome.gtk.SearchBar()
    res.asInstanceOf[SearchBar]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, keyCaptureWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, searchMode: Opt[Boolean] = UnsetParam, showCloseButton: Opt[Boolean] = UnsetParam): VarContextAction[SearchBar] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    ifSet(keyCaptureWidget, res.keyCaptureWidget := _)
    ifSet(searchMode, res.searchMode := _)
    ifSet(showCloseButton, res.showCloseButton := _)
    res
  }
}