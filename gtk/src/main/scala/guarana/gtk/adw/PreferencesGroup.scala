
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type PreferencesGroup <: guarana.gtk.Widget  = org.gnome.adw.PreferencesGroup & guarana.gtk.Widget
object PreferencesGroup extends VarsMap {
  val Description: ExternalVar.Aux[PreferencesGroup, java.lang.String | Null] = ExternalVar[PreferencesGroup, java.lang.String | Null]("description", _.getDescription(), _.setDescription(_), true)
  val HeaderSuffix: ExternalVar.Aux[PreferencesGroup, guarana.gtk.Widget | Null] = ExternalVar[PreferencesGroup, guarana.gtk.Widget | Null]("header-suffix", _.getHeaderSuffix().?(guarana.gtk.Widget.wrap), (n, v) => n.setHeaderSuffix(v.?(_.unwrap)), true)
  val SeparateRows: ExternalVar.Aux[PreferencesGroup, Boolean] = ExternalVar[PreferencesGroup, Boolean]("separate-rows", _.getSeparateRows(), _.setSeparateRows(_), true)
  val Title: ExternalVar.Aux[PreferencesGroup, java.lang.String] = ExternalVar[PreferencesGroup, java.lang.String]("title", _.getTitle(), _.setTitle(_), true)

  

  extension (v: PreferencesGroup) {
    def unwrap: org.gnome.adw.PreferencesGroup = v

    def description: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.adw.PreferencesGroup.Description.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def headerSuffix: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.PreferencesGroup.HeaderSuffix.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def separateRows: Var.Aux[Boolean, v.type] = guarana.gtk.adw.PreferencesGroup.SeparateRows.asInstanceOf[Var.Aux[Boolean, v.type]]
    def title: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.PreferencesGroup.Title.asInstanceOf[Var.Aux[java.lang.String, v.type]]

    

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

  def wrap(v: org.gnome.adw.PreferencesGroup): PreferencesGroup = 
    val res = v.asInstanceOf[PreferencesGroup]
    
    res

  def init(v: PreferencesGroup): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): PreferencesGroup = {
    val res = {
      val res = org.gnome.adw.PreferencesGroup.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[PreferencesGroup]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    description: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    headerSuffix: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
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
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    separateRows: Opt[Binding[Boolean]] = UnsetParam,
    title: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[PreferencesGroup] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.PreferencesGroup.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(description, res.description := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(headerSuffix, res.headerSuffix := _)
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
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(separateRows, res.separateRows := _)
    ifSet(title, res.title := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        