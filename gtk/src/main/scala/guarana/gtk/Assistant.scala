package guarana
package gtk
opaque type Assistant <: Window = org.gnome.gtk.Assistant & Window
object Assistant {
  @deprecated("", "") val CurrentPage: ExternalVar.Aux[Assistant, Int] = ExternalVar[Assistant, Int]("current-page", _.getCurrentPage(), _.setCurrentPage(_), true)
  ()
  extension (v: Assistant) {
    def unwrap: org.gnome.gtk.Assistant = v
    export unwrap.onApply, unwrap.onCancel, unwrap.onClose, unwrap.onEscape, unwrap.onPrepare
  }
  def init(v: Assistant): Unit = {
    Window.init(v)
  }
  def uninitialized(): Assistant = {
    val res = new org.gnome.gtk.Assistant()
    res.asInstanceOf[Assistant]
  }
}