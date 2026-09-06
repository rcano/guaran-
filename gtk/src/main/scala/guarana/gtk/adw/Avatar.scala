
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type Avatar <: guarana.gtk.Widget  = org.gnome.adw.Avatar & guarana.gtk.Widget
object Avatar extends VarsMap {
  val CustomImage: ExternalVar.Aux[Avatar, org.gnome.gdk.Paintable | Null] = ExternalVar[Avatar, org.gnome.gdk.Paintable | Null]("custom-image", _.getCustomImage(), _.setCustomImage(_), true)
  val IconName: ExternalVar.Aux[Avatar, java.lang.String | Null] = ExternalVar[Avatar, java.lang.String | Null]("icon-name", _.getIconName(), _.setIconName(_), true)
  val ShowInitials: ExternalVar.Aux[Avatar, Boolean] = ExternalVar[Avatar, Boolean]("show-initials", _.getShowInitials(), _.setShowInitials(_), true)
  val Size: ExternalVar.Aux[Avatar, Int] = ExternalVar[Avatar, Int]("size", _.getSize(), _.setSize(_), true)
  val Text: ExternalVar.Aux[Avatar, java.lang.String | Null] = ExternalVar[Avatar, java.lang.String | Null]("text", _.getText(), _.setText(_), true)

  

  extension (v: Avatar) {
    def unwrap: org.gnome.adw.Avatar = v

    def customImage: Var.Aux[org.gnome.gdk.Paintable | Null, v.type] = guarana.gtk.adw.Avatar.CustomImage.asInstanceOf[Var.Aux[org.gnome.gdk.Paintable | Null, v.type]]
    def iconName: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.adw.Avatar.IconName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def showInitials: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Avatar.ShowInitials.asInstanceOf[Var.Aux[Boolean, v.type]]
    def size: Var.Aux[Int, v.type] = guarana.gtk.adw.Avatar.Size.asInstanceOf[Var.Aux[Int, v.type]]
    def text: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.adw.Avatar.Text.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]

    

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

  def wrap(v: org.gnome.adw.Avatar): Avatar = 
    val res = v.asInstanceOf[Avatar]
    
    res

  def init(v: Avatar): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Avatar = {
    val res = {
      val res = org.gnome.adw.Avatar.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[Avatar]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    customImage: Opt[Binding[org.gnome.gdk.Paintable | Null]] = UnsetParam,
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
    iconName: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showInitials: Opt[Binding[Boolean]] = UnsetParam,
    size: Opt[Binding[Int]] = UnsetParam,
    text: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Avatar] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.Avatar.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(customImage, res.customImage := _)
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
    ifSet(iconName, res.iconName := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showInitials, res.showInitials := _)
    ifSet(size, res.size := _)
    ifSet(text, res.text := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        