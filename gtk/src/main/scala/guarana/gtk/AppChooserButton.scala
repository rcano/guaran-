
package guarana
package gtk

import guarana.util.*

opaque type AppChooserButton <: guarana.gtk.Widget  = org.gnome.gtk.AppChooserButton & guarana.gtk.Widget
object AppChooserButton extends VarsMap {
  @deprecated("", "") val Modal: ExternalVar.Aux[AppChooserButton, Boolean] = ExternalVar[AppChooserButton, Boolean]("modal", _.getModal(), _.setModal(_), true)
  @deprecated("", "") val ShowDefaultItem: ExternalVar.Aux[AppChooserButton, Boolean] = ExternalVar[AppChooserButton, Boolean]("show-default-item", _.getShowDefaultItem(), _.setShowDefaultItem(_), true)
  @deprecated("", "") val ShowDialogItem: ExternalVar.Aux[AppChooserButton, Boolean] = ExternalVar[AppChooserButton, Boolean]("show-dialog-item", _.getShowDialogItem(), _.setShowDialogItem(_), true)

  

  extension (v: AppChooserButton) {
    def unwrap: org.gnome.gtk.AppChooserButton = v

    @deprecated("", "") def modal: Var.Aux[Boolean, v.type] = guarana.gtk.AppChooserButton.Modal.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def showDefaultItem: Var.Aux[Boolean, v.type] = guarana.gtk.AppChooserButton.ShowDefaultItem.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def showDialogItem: Var.Aux[Boolean, v.type] = guarana.gtk.AppChooserButton.ShowDialogItem.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivate,
      onChanged,
      onCustomItemActivated,
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
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.AppChooserButton): AppChooserButton = 
    val res = v.asInstanceOf[AppChooserButton]
    
    res

  def init(v: AppChooserButton): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(heading: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], contentType: Opt[java.lang.String]): AppChooserButton = {
    val res = org.gnome.gtk.AppChooserButton.builder()
    ifSet(heading, v => res.setHeading(v))
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    ifSet(contentType, v => res.setContentType(v))
    
    res.asInstanceOf[AppChooserButton]
  }
  
  def apply(
    heading: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, contentType: Opt[java.lang.String] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    modal: Opt[Binding[Boolean]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showDefaultItem: Opt[Binding[Boolean]] = UnsetParam,
    showDialogItem: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[AppChooserButton] = {
    val res = uninitialized(heading, cssName, heightRequest, widthRequest, accessibleRole, contentType)
    guarana.gtk.AppChooserButton.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(modal, res.modal := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showDefaultItem, res.showDefaultItem := _)
    ifSet(showDialogItem, res.showDialogItem := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
  
}
        