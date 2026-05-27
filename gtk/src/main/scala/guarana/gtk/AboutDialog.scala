package guarana
package gtk
opaque type AboutDialog <: Window = org.gnome.gtk.AboutDialog & Window
object AboutDialog {
  val Comments: ExternalVar.Aux[AboutDialog, java.lang.String | Null] = ExternalVar[AboutDialog, java.lang.String | Null]("comments", _.getComments(), _.setComments(_), true)
  val Copyright: ExternalVar.Aux[AboutDialog, java.lang.String | Null] = ExternalVar[AboutDialog, java.lang.String | Null]("copyright", _.getCopyright(), _.setCopyright(_), true)
  val License: ExternalVar.Aux[AboutDialog, java.lang.String | Null] = ExternalVar[AboutDialog, java.lang.String | Null]("license", _.getLicense(), _.setLicense(_), true)
  val LicenseType: ExternalVar.Aux[AboutDialog, org.gnome.gtk.License] = ExternalVar[AboutDialog, org.gnome.gtk.License]("license-type", _.getLicenseType(), _.setLicenseType(_), true)
  val Logo: ExternalVar.Aux[AboutDialog, org.gnome.gdk.Paintable | Null] = ExternalVar[AboutDialog, org.gnome.gdk.Paintable | Null]("logo", _.getLogo(), _.setLogo(_), true)
  val LogoIconName: ExternalVar.Aux[AboutDialog, java.lang.String | Null] = ExternalVar[AboutDialog, java.lang.String | Null]("logo-icon-name", _.getLogoIconName(), _.setLogoIconName(_), true)
  val ProgramName: ExternalVar.Aux[AboutDialog, java.lang.String | Null] = ExternalVar[AboutDialog, java.lang.String | Null]("program-name", _.getProgramName(), _.setProgramName(_), true)
  val SystemInformation: ExternalVar.Aux[AboutDialog, java.lang.String | Null] = ExternalVar[AboutDialog, java.lang.String | Null]("system-information", _.getSystemInformation(), _.setSystemInformation(_), true)
  val TranslatorCredits: ExternalVar.Aux[AboutDialog, java.lang.String | Null] = ExternalVar[AboutDialog, java.lang.String | Null]("translator-credits", _.getTranslatorCredits(), _.setTranslatorCredits(_), true)
  val Version: ExternalVar.Aux[AboutDialog, java.lang.String | Null] = ExternalVar[AboutDialog, java.lang.String | Null]("version", _.getVersion(), _.setVersion(_), true)
  val Website: ExternalVar.Aux[AboutDialog, java.lang.String | Null] = ExternalVar[AboutDialog, java.lang.String | Null]("website", _.getWebsite(), _.setWebsite(_), true)
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