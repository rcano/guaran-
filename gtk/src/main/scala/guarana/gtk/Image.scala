
package guarana
package gtk

import guarana.util.*

opaque type Image <: guarana.gtk.Widget  = org.gnome.gtk.Image & guarana.gtk.Widget
object Image extends VarsMap {
  val IconSize: ExternalVar.Aux[Image, org.gnome.gtk.IconSize] = ExternalVar[Image, org.gnome.gtk.IconSize]("icon-size", _.getIconSize(), _.setIconSize(_), true)
  val PixelSize: ExternalVar.Aux[Image, Int] = ExternalVar[Image, Int]("pixel-size", _.getPixelSize(), _.setPixelSize(_), true)

  

  extension (v: Image) {
    def unwrap: org.gnome.gtk.Image = v

    def iconSize: Var.Aux[org.gnome.gtk.IconSize, v.type] = guarana.gtk.Image.IconSize.asInstanceOf[Var.Aux[org.gnome.gtk.IconSize, v.type]]
    def pixelSize: Var.Aux[Int, v.type] = guarana.gtk.Image.PixelSize.asInstanceOf[Var.Aux[Int, v.type]]

    

    export unwrap.{
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

  def wrap(v: org.gnome.gtk.Image): Image = 
    val res = v.asInstanceOf[Image]
    
    res

  def init(v: Image): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(file: Opt[java.lang.String], gicon: Opt[org.gnome.gio.Icon], iconName: Opt[java.lang.String], paintable: Opt[org.gnome.gdk.Paintable], resource: Opt[java.lang.String], useFallback: Opt[Boolean], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Image = {
    val res = org.gnome.gtk.Image.builder()
    ifSet(file, v => res.setFile(v))
    ifSet(gicon, v => res.setGicon(v))
    ifSet(iconName, v => res.setIconName(v))
    ifSet(paintable, v => res.setPaintable(v))
    ifSet(resource, v => res.setResource(v))
    ifSet(useFallback, v => res.setUseFallback(v))
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    
    res.asInstanceOf[Image]
  }
  
  def apply(
    file: Opt[java.lang.String] = UnsetParam, gicon: Opt[org.gnome.gio.Icon] = UnsetParam, iconName: Opt[java.lang.String] = UnsetParam, paintable: Opt[org.gnome.gdk.Paintable] = UnsetParam, resource: Opt[java.lang.String] = UnsetParam, useFallback: Opt[Boolean] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
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
    iconSize: Opt[Binding[org.gnome.gtk.IconSize]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    pixelSize: Opt[Binding[Int]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Image] = {
    val res = uninitialized(file, gicon, iconName, paintable, resource, useFallback, cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.Image.init(res)
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
    ifSet(iconSize, res.iconSize := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(pixelSize, res.pixelSize := _)
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
        