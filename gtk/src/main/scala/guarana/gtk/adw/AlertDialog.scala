
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type AlertDialog <: guarana.gtk.Widget  = org.gnome.adw.AlertDialog & guarana.gtk.Widget
object AlertDialog extends VarsMap {
  val Body: ExternalVar.Aux[AlertDialog, java.lang.String] = ExternalVar[AlertDialog, java.lang.String]("body", _.getBody(), _.setBody(_), true)
  val BodyUseMarkup: ExternalVar.Aux[AlertDialog, Boolean] = ExternalVar[AlertDialog, Boolean]("body-use-markup", _.getBodyUseMarkup(), _.setBodyUseMarkup(_), true)
  val CloseResponse: ExternalVar.Aux[AlertDialog, java.lang.String] = ExternalVar[AlertDialog, java.lang.String]("close-response", _.getCloseResponse(), _.setCloseResponse(_), true)
  val DefaultResponse: ExternalVar.Aux[AlertDialog, java.lang.String | Null] = ExternalVar[AlertDialog, java.lang.String | Null]("default-response", _.getDefaultResponse(), _.setDefaultResponse(_), true)
  val ExtraChild: ExternalVar.Aux[AlertDialog, guarana.gtk.Widget | Null] = ExternalVar[AlertDialog, guarana.gtk.Widget | Null]("extra-child", _.getExtraChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setExtraChild(v.?(_.unwrap)), true)
  val Heading: ExternalVar.Aux[AlertDialog, java.lang.String | Null] = ExternalVar[AlertDialog, java.lang.String | Null]("heading", _.getHeading(), _.setHeading(_), true)
  val HeadingUseMarkup: ExternalVar.Aux[AlertDialog, Boolean] = ExternalVar[AlertDialog, Boolean]("heading-use-markup", _.getHeadingUseMarkup(), _.setHeadingUseMarkup(_), true)
  val PreferWideLayout: ExternalVar.Aux[AlertDialog, Boolean] = ExternalVar[AlertDialog, Boolean]("prefer-wide-layout", _.getPreferWideLayout(), _.setPreferWideLayout(_), true)

  

  extension (v: AlertDialog) {
    def unwrap: org.gnome.adw.AlertDialog = v

    def body: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AlertDialog.Body.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def bodyUseMarkup: Var.Aux[Boolean, v.type] = guarana.gtk.adw.AlertDialog.BodyUseMarkup.asInstanceOf[Var.Aux[Boolean, v.type]]
    def closeResponse: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.AlertDialog.CloseResponse.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def defaultResponse: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.adw.AlertDialog.DefaultResponse.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def extraChild: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.AlertDialog.ExtraChild.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def heading: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.adw.AlertDialog.Heading.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def headingUseMarkup: Var.Aux[Boolean, v.type] = guarana.gtk.adw.AlertDialog.HeadingUseMarkup.asInstanceOf[Var.Aux[Boolean, v.type]]
    def preferWideLayout: Var.Aux[Boolean, v.type] = guarana.gtk.adw.AlertDialog.PreferWideLayout.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onCloseAttempt,
      onClosed,
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
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

  def wrap(v: org.gnome.adw.AlertDialog): AlertDialog = 
    val res = v.asInstanceOf[AlertDialog]
    
    res

  def init(v: AlertDialog): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(focusWidget: Opt[guarana.gtk.Widget], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): AlertDialog = {
    val res = {
      val res = org.gnome.adw.AlertDialog.builder()
      ifSet(focusWidget, v => res.setFocusWidget(v.unwrap))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[AlertDialog]
  }
  
  def apply(
    focusWidget: Opt[guarana.gtk.Widget] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    body: Opt[Binding[java.lang.String]] = UnsetParam,
    bodyUseMarkup: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    closeResponse: Opt[Binding[java.lang.String]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    defaultResponse: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    extraChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    heading: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    headingUseMarkup: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    preferWideLayout: Opt[Binding[Boolean]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[AlertDialog] = {
    val res = uninitialized(focusWidget, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.AlertDialog.init(res)
    ifSet(body, res.body := _)
    ifSet(bodyUseMarkup, res.bodyUseMarkup := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(closeResponse, res.closeResponse := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(defaultResponse, res.defaultResponse := _)
    ifSet(direction, res.direction := _)
    ifSet(extraChild, res.extraChild := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(heading, res.heading := _)
    ifSet(headingUseMarkup, res.headingUseMarkup := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(preferWideLayout, res.preferWideLayout := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        