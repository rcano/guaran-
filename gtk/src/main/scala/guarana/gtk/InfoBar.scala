package guarana
package gtk
opaque type InfoBar <: Widget = org.gnome.gtk.InfoBar & Widget
object InfoBar {
  @deprecated("", "") val MessageType: ExternalVar.Aux[InfoBar, org.gnome.gtk.MessageType] = ExternalVar[InfoBar, org.gnome.gtk.MessageType]("message-type", _.getMessageType(), _.setMessageType(_), true)
  @deprecated("", "") val Revealed: ExternalVar.Aux[InfoBar, Boolean] = ExternalVar[InfoBar, Boolean]("revealed", _.getRevealed(), _.setRevealed(_), true)
  @deprecated("", "") val ShowCloseButton: ExternalVar.Aux[InfoBar, Boolean] = ExternalVar[InfoBar, Boolean]("show-close-button", _.getShowCloseButton(), _.setShowCloseButton(_), true)
  ()
  extension (v: InfoBar) {
    def unwrap: org.gnome.gtk.InfoBar = v
    export unwrap.onClose, unwrap.onResponse
  }
  def init(v: InfoBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): InfoBar = {
    val res = new org.gnome.gtk.InfoBar()
    res.asInstanceOf[InfoBar]
  }
}