package guarana
package gtk
opaque type SearchBar <: Widget = org.gnome.gtk.SearchBar & Widget
object SearchBar {
  val ShowCloseButton: ExternalVar.Aux[SearchBar, Boolean] = ExternalVar[SearchBar, Boolean]("show-close-button", _.getShowCloseButton(), _.setShowCloseButton(_), true)
  ()
  extension (v: SearchBar) {
    def unwrap: org.gnome.gtk.SearchBar = v
  }
  def init(v: SearchBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): SearchBar = {
    val res = new org.gnome.gtk.SearchBar()
    res.asInstanceOf[SearchBar]
  }
}