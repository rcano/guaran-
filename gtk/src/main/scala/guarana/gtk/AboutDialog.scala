package guarana
package gtk
opaque type AboutDialog <: Window = org.gnome.gtk.AboutDialog & Window
object AboutDialog {
  val LicenseType: ExternalVar.Aux[AboutDialog, org.gnome.gtk.License | Null] = ExternalVar[AboutDialog, org.gnome.gtk.License | Null]("license-type", _.getLicenseType(), _.setLicenseType(_), true)
  val WrapLicense: ExternalVar.Aux[AboutDialog, Boolean] = ExternalVar[AboutDialog, Boolean]("wrap-license", _.getWrapLicense(), _.setWrapLicense(_), true)
  ()
  extension (v: AboutDialog) {
    def unwrap: org.gnome.gtk.AboutDialog = v
    export unwrap.onActivateLink
  }
  def init(v: AboutDialog): Unit = {
    Window.init(v)
  }
  def uninitialized(): AboutDialog = {
    val res = new org.gnome.gtk.AboutDialog()
    res.asInstanceOf[AboutDialog]
  }
}