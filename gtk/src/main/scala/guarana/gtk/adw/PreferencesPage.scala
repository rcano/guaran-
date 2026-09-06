
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type PreferencesPage <: guarana.gtk.Widget  = org.gnome.adw.PreferencesPage & guarana.gtk.Widget
object PreferencesPage extends VarsMap {
  val Banner: ExternalVar.Aux[PreferencesPage, org.gnome.adw.Banner | Null] = ExternalVar[PreferencesPage, org.gnome.adw.Banner | Null]("banner", _.getBanner(), _.setBanner(_), true)
  val Description: ExternalVar.Aux[PreferencesPage, java.lang.String] = ExternalVar[PreferencesPage, java.lang.String]("description", _.getDescription(), _.setDescription(_), true)
  val DescriptionCentered: ExternalVar.Aux[PreferencesPage, Boolean] = ExternalVar[PreferencesPage, Boolean]("description-centered", _.getDescriptionCentered(), _.setDescriptionCentered(_), true)
  val IconName: ExternalVar.Aux[PreferencesPage, java.lang.String | Null] = ExternalVar[PreferencesPage, java.lang.String | Null]("icon-name", _.getIconName(), _.setIconName(_), true)
  val Name: ExternalVar.Aux[PreferencesPage, java.lang.String | Null] = ExternalVar[PreferencesPage, java.lang.String | Null]("name", _.getName(), _.setName(_), true)
  val Title: ExternalVar.Aux[PreferencesPage, java.lang.String] = ExternalVar[PreferencesPage, java.lang.String]("title", _.getTitle(), _.setTitle(_), true)
  val UseUnderline: ExternalVar.Aux[PreferencesPage, Boolean] = ExternalVar[PreferencesPage, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)

  

  extension (v: PreferencesPage) {
    def unwrap: org.gnome.adw.PreferencesPage = v

    def banner: Var.Aux[org.gnome.adw.Banner | Null, v.type] = guarana.gtk.adw.PreferencesPage.Banner.asInstanceOf[Var.Aux[org.gnome.adw.Banner | Null, v.type]]
    def description: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.PreferencesPage.Description.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def descriptionCentered: Var.Aux[Boolean, v.type] = guarana.gtk.adw.PreferencesPage.DescriptionCentered.asInstanceOf[Var.Aux[Boolean, v.type]]
    def iconName: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.adw.PreferencesPage.IconName.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def name: Var.Aux[java.lang.String | Null, v.type] = guarana.gtk.adw.PreferencesPage.Name.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def title: Var.Aux[java.lang.String, v.type] = guarana.gtk.adw.PreferencesPage.Title.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def useUnderline: Var.Aux[Boolean, v.type] = guarana.gtk.adw.PreferencesPage.UseUnderline.asInstanceOf[Var.Aux[Boolean, v.type]]

    

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

  def wrap(v: org.gnome.adw.PreferencesPage): PreferencesPage = 
    val res = v.asInstanceOf[PreferencesPage]
    
    res

  def init(v: PreferencesPage): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): PreferencesPage = {
    val res = {
      val res = org.gnome.adw.PreferencesPage.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[PreferencesPage]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    banner: Opt[Binding[org.gnome.adw.Banner | Null]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    description: Opt[Binding[java.lang.String]] = UnsetParam,
    descriptionCentered: Opt[Binding[Boolean]] = UnsetParam,
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
    name: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    title: Opt[Binding[java.lang.String]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    useUnderline: Opt[Binding[Boolean]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[PreferencesPage] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.PreferencesPage.init(res)
    ifSet(banner, res.banner := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(description, res.description := _)
    ifSet(descriptionCentered, res.descriptionCentered := _)
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
    ifSet(title, res.title := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(useUnderline, res.useUnderline := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        