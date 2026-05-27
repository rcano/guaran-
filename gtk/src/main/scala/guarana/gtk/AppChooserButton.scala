package guarana
package gtk
opaque type AppChooserButton <: Widget = org.gnome.gtk.AppChooserButton & Widget
object AppChooserButton {
  val Modal: ExternalVar.Aux[AppChooserButton, Boolean] = ExternalVar[AppChooserButton, Boolean]("modal", _.getModal(), _.setModal(_), true)
  val ShowDefaultItem: ExternalVar.Aux[AppChooserButton, Boolean] = ExternalVar[AppChooserButton, Boolean]("show-default-item", _.getShowDefaultItem(), _.setShowDefaultItem(_), true)
  val ShowDialogItem: ExternalVar.Aux[AppChooserButton, Boolean] = ExternalVar[AppChooserButton, Boolean]("show-dialog-item", _.getShowDialogItem(), _.setShowDialogItem(_), true)
  ()
  extension (v: AppChooserButton) {
    def unwrap: org.gnome.gtk.AppChooserButton = v
    export unwrap.onActivate, unwrap.onChanged, unwrap.onCustomItemActivated
  }
  def init(v: AppChooserButton): Unit = {
    Widget.init(v)
  }
  def uninitialized(): AppChooserButton = {
    val res = new org.gnome.gtk.AppChooserButton()
    res.asInstanceOf[AppChooserButton]
  }
}