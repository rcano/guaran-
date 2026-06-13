
package guarana
package gtk

import guarana.util.*

opaque type AboutDialog <: guarana.gtk.Window  = org.gnome.gtk.AboutDialog & guarana.gtk.Window
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

  

  extension (v: AboutDialog) {
    def unwrap: org.gnome.gtk.AboutDialog = v

    def comments: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.AboutDialog.Comments.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def copyright: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.AboutDialog.Copyright.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def license: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.AboutDialog.License.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def licenseType: Var.Aux[org.gnome.gtk.License, v.type] = guarana.gtk.AboutDialog.LicenseType.asInstanceOf[Var.Aux[org.gnome.gtk.License, v.type]]
    def logo: Var.Aux[org.gnome.gdk.Paintable | Null, v.type] = guarana.gtk.AboutDialog.Logo.asInstanceOf[Var.Aux[org.gnome.gdk.Paintable | Null, v.type]]
    def logoIconName: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.AboutDialog.LogoIconName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def programName: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.AboutDialog.ProgramName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def systemInformation: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.AboutDialog.SystemInformation.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def translatorCredits: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.AboutDialog.TranslatorCredits.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def version: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.AboutDialog.Version.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def website: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.AboutDialog.Website.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def wrapLicense: Var.Aux[Boolean, v.type] = guarana.gtk.AboutDialog.WrapLicense.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivateDefault,
      onActivateFocus,
      onActivateLink,
      onCloseRequest,
      onDestroy,
      onDirectionChanged,
      onEnableDebugging,
      onHide,
      onKeynavFailed,
      onKeysChanged,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.AboutDialog): AboutDialog = 
    val res = v.asInstanceOf[AboutDialog]
    
    res

  def init(v: AboutDialog): Unit = {
    guarana.gtk.Window.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(artists: Opt[Array[java.lang.String]], authors: Opt[Array[java.lang.String]], documenters: Opt[Array[java.lang.String]], websiteLabel: Opt[java.lang.String], defaultHeight: Opt[Int], defaultWidth: Opt[Int], focusWidget: Opt[guarana.gtk.Widget], fullscreened: Opt[Boolean], maximized: Opt[Boolean], startupId: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): AboutDialog = {
    val res = {
      val res = org.gnome.gtk.AboutDialog.builder()
      ifSet(artists, v => res.setArtists(v))
      ifSet(authors, v => res.setAuthors(v))
      ifSet(documenters, v => res.setDocumenters(v))
      ifSet(websiteLabel, v => res.setWebsiteLabel(v))
      ifSet(defaultHeight, v => res.setDefaultHeight(v))
      ifSet(defaultWidth, v => res.setDefaultWidth(v))
      ifSet(focusWidget, v => res.setFocusWidget(v.unwrap))
      ifSet(fullscreened, v => res.setFullscreened(v))
      ifSet(maximized, v => res.setMaximized(v))
      ifSet(startupId, v => res.setStartupId(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[AboutDialog]
  }
  
  def apply(
    artists: Opt[Array[java.lang.String]] = UnsetParam, authors: Opt[Array[java.lang.String]] = UnsetParam, documenters: Opt[Array[java.lang.String]] = UnsetParam, websiteLabel: Opt[java.lang.String] = UnsetParam, defaultHeight: Opt[Int] = UnsetParam, defaultWidth: Opt[Int] = UnsetParam, focusWidget: Opt[guarana.gtk.Widget] = UnsetParam, fullscreened: Opt[Boolean] = UnsetParam, maximized: Opt[Boolean] = UnsetParam, startupId: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    application: Opt[Binding[org.gnome.gtk.Application | Null]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    comments: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    copyright: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    decorated: Opt[Binding[Boolean]] = UnsetParam,
    defaultWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    deletable: Opt[Binding[Boolean]] = UnsetParam,
    destroyWithParent: Opt[Binding[Boolean]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    display: Opt[Binding[org.gnome.gdk.Display]] = UnsetParam,
    focus: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusVisible: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    gravity: Opt[Binding[org.gnome.gtk.WindowGravity]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    handleMenubarAccel: Opt[Binding[Boolean]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    hideOnClose: Opt[Binding[Boolean]] = UnsetParam,
    iconName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    license: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    licenseType: Opt[Binding[org.gnome.gtk.License]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    logo: Opt[Binding[org.gnome.gdk.Paintable | Null]] = UnsetParam,
    logoIconName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    mnemonicsVisible: Opt[Binding[Boolean]] = UnsetParam,
    modal: Opt[Binding[Boolean]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    programName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    resizable: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    systemInformation: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    title: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    titlebar: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transientFor: Opt[Binding[org.gnome.gtk.Window | Null]] = UnsetParam,
    translatorCredits: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    version: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    website: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    wrapLicense: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[AboutDialog] = {
    val res = uninitialized(artists, authors, documenters, websiteLabel, defaultHeight, defaultWidth, focusWidget, fullscreened, maximized, startupId, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.AboutDialog.init(res)
    ifSet(application, res.application := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(comments, res.comments := _)
    ifSet(copyright, res.copyright := _)
    ifSet(cssClasses, res.cssClasses := _)
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
        