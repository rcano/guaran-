package guarana
package gtk
import util.*
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
    def comments: Var.Aux[java.lang.String | Null, v.type] = Comments.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def copyright: Var.Aux[java.lang.String | Null, v.type] = Copyright.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def license: Var.Aux[java.lang.String | Null, v.type] = License.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def licenseType: Var.Aux[org.gnome.gtk.License, v.type] = LicenseType.asInstanceOf[Var.Aux[org.gnome.gtk.License, v.type]]
    def logo: Var.Aux[org.gnome.gdk.Paintable | Null, v.type] = Logo.asInstanceOf[Var.Aux[org.gnome.gdk.Paintable | Null, v.type]]
    def logoIconName: Var.Aux[java.lang.String | Null, v.type] = LogoIconName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def programName: Var.Aux[java.lang.String | Null, v.type] = ProgramName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def systemInformation: Var.Aux[java.lang.String | Null, v.type] = SystemInformation.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def translatorCredits: Var.Aux[java.lang.String | Null, v.type] = TranslatorCredits.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def version: Var.Aux[java.lang.String | Null, v.type] = Version.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def website: Var.Aux[java.lang.String | Null, v.type] = Website.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def wrapLicense: Var.Aux[Boolean, v.type] = WrapLicense.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivateLink
  }
  def init(v: AboutDialog): Unit = {
    Window.init(v)
  }
  def uninitialized(): AboutDialog = {
    val res = new org.gnome.gtk.AboutDialog()
    res.asInstanceOf[AboutDialog]
  }
  def apply(comments: Opt[java.lang.String | Null] = UnsetParam, copyright: Opt[java.lang.String | Null] = UnsetParam, license: Opt[java.lang.String | Null] = UnsetParam, licenseType: Opt[org.gnome.gtk.License] = UnsetParam, logo: Opt[org.gnome.gdk.Paintable | Null] = UnsetParam, logoIconName: Opt[java.lang.String | Null] = UnsetParam, programName: Opt[java.lang.String | Null] = UnsetParam, systemInformation: Opt[java.lang.String | Null] = UnsetParam, translatorCredits: Opt[java.lang.String | Null] = UnsetParam, version: Opt[java.lang.String | Null] = UnsetParam, website: Opt[java.lang.String | Null] = UnsetParam, wrapLicense: Opt[Boolean] = UnsetParam): VarContextAction[AboutDialog] = {
    val res = uninitialized()
    init(res)
    ifSet(comments, res.comments := _)
    ifSet(copyright, res.copyright := _)
    ifSet(license, res.license := _)
    ifSet(licenseType, res.licenseType := _)
    ifSet(logo, res.logo := _)
    ifSet(logoIconName, res.logoIconName := _)
    ifSet(programName, res.programName := _)
    ifSet(systemInformation, res.systemInformation := _)
    ifSet(translatorCredits, res.translatorCredits := _)
    ifSet(version, res.version := _)
    ifSet(website, res.website := _)
    ifSet(wrapLicense, res.wrapLicense := _)
    res
  }
}