package guarana
package gtk
import util.*
opaque type AboutDialog <: Window = org.gnome.gtk.AboutDialog & Window
object AboutDialog extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.AboutDialog): AboutDialog = {
    v.asInstanceOf
  }
  def init(v: AboutDialog): ToolkitAction[Toolkit, Unit] = {
    Window.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): AboutDialog = {
    val res = new org.gnome.gtk.AboutDialog()
    res.asInstanceOf[AboutDialog]
  }
  def apply(application: Opt[org.gnome.gtk.Application | Null] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, comments: Opt[java.lang.String | Null] = UnsetParam, copyright: Opt[java.lang.String | Null] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, decorated: Opt[Boolean] = UnsetParam, defaultWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, deletable: Opt[Boolean] = UnsetParam, destroyWithParent: Opt[Boolean] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, display: Opt[org.gnome.gdk.Display] = UnsetParam, focus: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusVisible: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, gravity: Opt[org.gnome.gtk.WindowGravity] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, handleMenubarAccel: Opt[Boolean] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, hideOnClose: Opt[Boolean] = UnsetParam, iconName: Opt[java.lang.String | Null] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, license: Opt[java.lang.String | Null] = UnsetParam, licenseType: Opt[org.gnome.gtk.License] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, logo: Opt[org.gnome.gdk.Paintable | Null] = UnsetParam, logoIconName: Opt[java.lang.String | Null] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, mnemonicsVisible: Opt[Boolean] = UnsetParam, modal: Opt[Boolean] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, programName: Opt[java.lang.String | Null] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, resizable: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, systemInformation: Opt[java.lang.String | Null] = UnsetParam, title: Opt[java.lang.String | Null] = UnsetParam, titlebar: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, transientFor: Opt[org.gnome.gtk.Window | Null] = UnsetParam, translatorCredits: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, version: Opt[java.lang.String | Null] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, website: Opt[java.lang.String | Null] = UnsetParam, wrapLicense: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, AboutDialog] = {
    val res = uninitialized()
    init(res)
    ifSet(application, res.application := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(comments, res.comments := _)
    ifSet(copyright, res.copyright := _)
    ifSet(cursor, res.cursor := _)
    ifSet(decorated, res.decorated := _)
    ifSet(defaultWidget, res.defaultWidget := _)
    ifSet(deletable, res.deletable := _)
    ifSet(destroyWithParent, res.destroyWithParent := _)
    ifSet(direction, res.direction := _)
    ifSet(display, res.display := _)
    ifSet(focus, res.focus := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusVisible, res.focusVisible := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(gravity, res.gravity := _)
    ifSet(halign, res.halign := _)
    ifSet(handleMenubarAccel, res.handleMenubarAccel := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(hideOnClose, res.hideOnClose := _)
    ifSet(iconName, res.iconName := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(license, res.license := _)
    ifSet(licenseType, res.licenseType := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(logo, res.logo := _)
    ifSet(logoIconName, res.logoIconName := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(mnemonicsVisible, res.mnemonicsVisible := _)
    ifSet(modal, res.modal := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(programName, res.programName := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(resizable, res.resizable := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(systemInformation, res.systemInformation := _)
    ifSet(title, res.title := _)
    ifSet(titlebar, res.titlebar := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(transientFor, res.transientFor := _)
    ifSet(translatorCredits, res.translatorCredits := _)
    ifSet(valign, res.valign := _)
    ifSet(version, res.version := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(website, res.website := _)
    ifSet(wrapLicense, res.wrapLicense := _)
    res
  }
}