
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type MessageDialog <: guarana.gtk.Window  = org.gnome.adw.MessageDialog & guarana.gtk.Window
object MessageDialog extends VarsMap {
  @deprecated("", "") val Body: ExternalVar.Aux[MessageDialog, java.lang.String] = ExternalVar[MessageDialog, java.lang.String]("body", _.getBody(), _.setBody(_), true)
  @deprecated("", "") val BodyUseMarkup: ExternalVar.Aux[MessageDialog, Boolean] = ExternalVar[MessageDialog, Boolean]("body-use-markup", _.getBodyUseMarkup(), _.setBodyUseMarkup(_), true)
  @deprecated("", "") val CloseResponse: ExternalVar.Aux[MessageDialog, java.lang.String] = ExternalVar[MessageDialog, java.lang.String]("close-response", _.getCloseResponse(), _.setCloseResponse(_), true)
  @deprecated("", "") val DefaultResponse: ExternalVar.Aux[MessageDialog, java.lang.String | Null] = ExternalVar[MessageDialog, java.lang.String | Null]("default-response", _.getDefaultResponse(), _.setDefaultResponse(_), true)
  @deprecated("", "") val ExtraChild: ExternalVar.Aux[MessageDialog, guarana.gtk.Widget | Null] = ExternalVar[MessageDialog, guarana.gtk.Widget | Null]("extra-child", _.getExtraChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setExtraChild(v.?(_.unwrap)), true)
  @deprecated("", "") val Heading: ExternalVar.Aux[MessageDialog, java.lang.String | Null] = ExternalVar[MessageDialog, java.lang.String | Null]("heading", _.getHeading(), _.setHeading(_), true)
  @deprecated("", "") val HeadingUseMarkup: ExternalVar.Aux[MessageDialog, Boolean] = ExternalVar[MessageDialog, Boolean]("heading-use-markup", _.getHeadingUseMarkup(), _.setHeadingUseMarkup(_), true)

  

  extension (v: MessageDialog) {
    def unwrap: org.gnome.adw.MessageDialog = v

    @deprecated("", "") def body: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.MessageDialog.Body.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def bodyUseMarkup: Var.Aux[Boolean, v.type] = guarana.gtk.adw.MessageDialog.BodyUseMarkup.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def closeResponse: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.MessageDialog.CloseResponse.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    @deprecated("", "") def defaultResponse: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.adw.MessageDialog.DefaultResponse.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    @deprecated("", "") def extraChild: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.MessageDialog.ExtraChild.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    @deprecated("", "") def heading: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.adw.MessageDialog.Heading.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    @deprecated("", "") def headingUseMarkup: Var.Aux[Boolean, v.type] = guarana.gtk.adw.MessageDialog.HeadingUseMarkup.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivateDefault,
      onActivateFocus,
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
      onResponse,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.adw.MessageDialog): MessageDialog = 
    val res = v.asInstanceOf[MessageDialog]
    
    res

  def init(v: MessageDialog): Unit = {
    guarana.gtk.Window.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(defaultHeight: Opt[Int], defaultWidth: Opt[Int], focusWidget: Opt[guarana.gtk.Widget], fullscreened: Opt[Boolean], maximized: Opt[Boolean], startupId: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): MessageDialog = {
    val res = {
      val res = org.gnome.adw.MessageDialog.builder()
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
    
    res.asInstanceOf[MessageDialog]
  }
  
  def apply(
    defaultHeight: Opt[Int] = UnsetParam, defaultWidth: Opt[Int] = UnsetParam, focusWidget: Opt[guarana.gtk.Widget] = UnsetParam, fullscreened: Opt[Boolean] = UnsetParam, maximized: Opt[Boolean] = UnsetParam, startupId: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    application: Opt[Binding[org.gnome.gtk.Application | Null]] = UnsetParam,
    body: Opt[Binding[java.lang.String]] = UnsetParam,
    bodyUseMarkup: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    closeResponse: Opt[Binding[java.lang.String]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    decorated: Opt[Binding[Boolean]] = UnsetParam,
    defaultResponse: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    defaultWidget: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    deletable: Opt[Binding[Boolean]] = UnsetParam,
    destroyWithParent: Opt[Binding[Boolean]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    display: Opt[Binding[org.gnome.gdk.Display]] = UnsetParam,
    extraChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
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
    heading: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    headingUseMarkup: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    hideOnClose: Opt[Binding[Boolean]] = UnsetParam,
    iconName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
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
    resizable: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    title: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    titlebar: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    transientFor: Opt[Binding[org.gnome.gtk.Window | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[MessageDialog] = {
    val res = uninitialized(defaultHeight, defaultWidth, focusWidget, fullscreened, maximized, startupId, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.MessageDialog.init(res)
    ifSet(application, res.application := _)
    ifSet(body, res.body := _)
    ifSet(bodyUseMarkup, res.bodyUseMarkup := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(closeResponse, res.closeResponse := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(decorated, res.decorated := _)
    ifSet(defaultResponse, res.defaultResponse := _)
    ifSet(defaultWidget, res.defaultWidget := _)
    ifSet(deletable, res.deletable := _)
    ifSet(destroyWithParent, res.destroyWithParent := _)
    ifSet(direction, res.direction := _)
    ifSet(display, res.display := _)
    ifSet(extraChild, res.extraChild := _)
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
    ifSet(heading, res.heading := _)
    ifSet(headingUseMarkup, res.headingUseMarkup := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(hideOnClose, res.hideOnClose := _)
    ifSet(iconName, res.iconName := _)
    ifSet(layoutManager, res.layoutManager := _)
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
    ifSet(resizable, res.resizable := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(title, res.title := _)
    ifSet(titlebar, res.titlebar := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(transientFor, res.transientFor := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        