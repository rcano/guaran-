
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type AboutWindow <: guarana.gtk.Window  = org.gnome.adw.AboutWindow & guarana.gtk.Window
object AboutWindow extends VarsMap {
  @deprecated("", "") val ApplicationIcon: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("application-icon", _.getApplicationIcon(), _.setApplicationIcon(_), true)
  @deprecated("", "") val ApplicationName: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("application-name", _.getApplicationName(), _.setApplicationName(_), true)
  @deprecated("", "") val Artists: ExternalVar.Aux[AboutWindow, Array[java.lang.String | Null]] = ExternalVar[AboutWindow, Array[java.lang.String | Null]]("artists", _.getArtists(), _.setArtists(_), true)
  @deprecated("", "") val Comments: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("comments", _.getComments(), _.setComments(_), true)
  @deprecated("", "") val Copyright: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("copyright", _.getCopyright(), _.setCopyright(_), true)
  @deprecated("", "") val DebugInfo: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("debug-info", _.getDebugInfo(), _.setDebugInfo(_), true)
  @deprecated("", "") val DebugInfoFilename: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("debug-info-filename", _.getDebugInfoFilename(), _.setDebugInfoFilename(_), true)
  @deprecated("", "") val Designers: ExternalVar.Aux[AboutWindow, Array[java.lang.String | Null]] = ExternalVar[AboutWindow, Array[java.lang.String | Null]]("designers", _.getDesigners(), _.setDesigners(_), true)
  @deprecated("", "") val DeveloperName: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("developer-name", _.getDeveloperName(), _.setDeveloperName(_), true)
  @deprecated("", "") val Developers: ExternalVar.Aux[AboutWindow, Array[java.lang.String | Null]] = ExternalVar[AboutWindow, Array[java.lang.String | Null]]("developers", _.getDevelopers(), _.setDevelopers(_), true)
  @deprecated("", "") val Documenters: ExternalVar.Aux[AboutWindow, Array[java.lang.String | Null]] = ExternalVar[AboutWindow, Array[java.lang.String | Null]]("documenters", _.getDocumenters(), _.setDocumenters(_), true)
  @deprecated("", "") val IssueUrl: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("issue-url", _.getIssueUrl(), _.setIssueUrl(_), true)
  @deprecated("", "") val License: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("license", _.getLicense(), _.setLicense(_), true)
  @deprecated("", "") val LicenseType: ExternalVar.Aux[AboutWindow, org.gnome.gtk.License] = ExternalVar[AboutWindow, org.gnome.gtk.License]("license-type", _.getLicenseType(), _.setLicenseType(_), true)
  @deprecated("", "") val ReleaseNotes: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("release-notes", _.getReleaseNotes(), _.setReleaseNotes(_), true)
  @deprecated("", "") val ReleaseNotesVersion: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("release-notes-version", _.getReleaseNotesVersion(), _.setReleaseNotesVersion(_), true)
  @deprecated("", "") val SupportUrl: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("support-url", _.getSupportUrl(), _.setSupportUrl(_), true)
  @deprecated("", "") val TranslatorCredits: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("translator-credits", _.getTranslatorCredits(), _.setTranslatorCredits(_), true)
  @deprecated("", "") val Version: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("version", _.getVersion(), _.setVersion(_), true)
  @deprecated("", "") val Website: ExternalVar.Aux[AboutWindow, java.lang.String] = ExternalVar[AboutWindow, java.lang.String]("website", _.getWebsite(), _.setWebsite(_), true)

  

  extension (v: AboutWindow) {
    def unwrap: org.gnome.adw.AboutWindow = v

    @deprecated("", "") def applicationIcon: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.ApplicationIcon.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def applicationName: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.ApplicationName.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def artists: Var.Aux[Array[java.lang.String | Null], v.type] = guarana.gtk.adw.AboutWindow.Artists.asInstanceOf[Var.Aux[Array[java.lang.String | Null], v.type]]
    @deprecated("", "") def comments: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.Comments.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def copyright: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.Copyright.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def debugInfo: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.DebugInfo.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def debugInfoFilename: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.DebugInfoFilename.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def designers: Var.Aux[Array[java.lang.String | Null], v.type] = guarana.gtk.adw.AboutWindow.Designers.asInstanceOf[Var.Aux[Array[java.lang.String | Null], v.type]]
    @deprecated("", "") def developerName: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.DeveloperName.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def developers: Var.Aux[Array[java.lang.String | Null], v.type] = guarana.gtk.adw.AboutWindow.Developers.asInstanceOf[Var.Aux[Array[java.lang.String | Null], v.type]]
    @deprecated("", "") def documenters: Var.Aux[Array[java.lang.String | Null], v.type] = guarana.gtk.adw.AboutWindow.Documenters.asInstanceOf[Var.Aux[Array[java.lang.String | Null], v.type]]
    @deprecated("", "") def issueUrl: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.IssueUrl.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def license: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.License.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def licenseType: Var.Aux[org.gnome.gtk.License, v.type] = guarana.gtk.adw.AboutWindow.LicenseType.asInstanceOf[Var.Aux[org.gnome.gtk.License, v.type]]
    @deprecated("", "") def releaseNotes: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.ReleaseNotes.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def releaseNotesVersion: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.ReleaseNotesVersion.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def supportUrl: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.SupportUrl.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def translatorCredits: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.TranslatorCredits.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def version: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.Version.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def website: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AboutWindow.Website.asInstanceOf[Var.Aux[java.lang.String, v.type]]

    

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

  def wrap(v: org.gnome.adw.AboutWindow): AboutWindow = 
    val res = v.asInstanceOf[AboutWindow]
    
    res

  def init(v: AboutWindow): Unit = {
    guarana.gtk.Window.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(defaultHeight: Opt[Int], defaultWidth: Opt[Int], focusWidget: Opt[guarana.gtk.Widget], fullscreened: Opt[Boolean], maximized: Opt[Boolean], startupId: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): AboutWindow = {
    val res = {
      val res = org.gnome.adw.AboutWindow.builder()
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
    
    res.asInstanceOf[AboutWindow]
  }
  
  def apply(
    defaultHeight: Opt[Int] = UnsetParam, defaultWidth: Opt[Int] = UnsetParam, focusWidget: Opt[guarana.gtk.Widget] = UnsetParam, fullscreened: Opt[Boolean] = UnsetParam, maximized: Opt[Boolean] = UnsetParam, startupId: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    application: Opt[Binding[org.gnome.gtk.Application | Null]] = UnsetParam,
    applicationIcon: Opt[Binding[java.lang.String]] = UnsetParam,
    applicationName: Opt[Binding[java.lang.String]] = UnsetParam,
    artists: Opt[Binding[Array[java.lang.String | Null]]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    comments: Opt[Binding[java.lang.String]] = UnsetParam,
    copyright: Opt[Binding[java.lang.String]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    debugInfo: Opt[Binding[java.lang.String]] = UnsetParam,
    debugInfoFilename: Opt[Binding[java.lang.String]] = UnsetParam,
    decorated: Opt[Binding[Boolean]] = UnsetParam,
    defaultWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    deletable: Opt[Binding[Boolean]] = UnsetParam,
    designers: Opt[Binding[Array[java.lang.String | Null]]] = UnsetParam,
    destroyWithParent: Opt[Binding[Boolean]] = UnsetParam,
    developerName: Opt[Binding[java.lang.String]] = UnsetParam,
    developers: Opt[Binding[Array[java.lang.String | Null]]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    display: Opt[Binding[org.gnome.gdk.Display]] = UnsetParam,
    documenters: Opt[Binding[Array[java.lang.String | Null]]] = UnsetParam,
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
    issueUrl: Opt[Binding[java.lang.String]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    license: Opt[Binding[java.lang.String]] = UnsetParam,
    licenseType: Opt[Binding[org.gnome.gtk.License]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    mnemonicsVisible: Opt[Binding[Boolean]] = UnsetParam,
    modal: Opt[Binding[Boolean]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    releaseNotes: Opt[Binding[java.lang.String]] = UnsetParam,
    releaseNotesVersion: Opt[Binding[java.lang.String]] = UnsetParam,
    resizable: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    supportUrl: Opt[Binding[java.lang.String]] = UnsetParam,
    title: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    titlebar: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transientFor: Opt[Binding[org.gnome.gtk.Window | Null]] = UnsetParam,
    translatorCredits: Opt[Binding[java.lang.String]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    version: Opt[Binding[java.lang.String]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    website: Opt[Binding[java.lang.String]] = UnsetParam
  ): VarContextAction[AboutWindow] = {
    val res = uninitialized(defaultHeight, defaultWidth, focusWidget, fullscreened, maximized, startupId, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.AboutWindow.init(res)
    ifSet(application, res.application := _)
    ifSet(applicationIcon, res.applicationIcon := _)
    ifSet(applicationName, res.applicationName := _)
    ifSet(artists, res.artists := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(comments, res.comments := _)
    ifSet(copyright, res.copyright := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(debugInfo, res.debugInfo := _)
    ifSet(debugInfoFilename, res.debugInfoFilename := _)
    ifSet(decorated, res.decorated := _)
    ifSet(defaultWidget, res.defaultWidget := _)
    ifSet(deletable, res.deletable := _)
    ifSet(designers, res.designers := _)
    ifSet(destroyWithParent, res.destroyWithParent := _)
    ifSet(developerName, res.developerName := _)
    ifSet(developers, res.developers := _)
    ifSet(direction, res.direction := _)
    ifSet(display, res.display := _)
    ifSet(documenters, res.documenters := _)
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
    ifSet(issueUrl, res.issueUrl := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(license, res.license := _)
    ifSet(licenseType, res.licenseType := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(mnemonicsVisible, res.mnemonicsVisible := _)
    ifSet(modal, res.modal := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(releaseNotes, res.releaseNotes := _)
    ifSet(releaseNotesVersion, res.releaseNotesVersion := _)
    ifSet(resizable, res.resizable := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(supportUrl, res.supportUrl := _)
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
    
    res
  }
  
}
        