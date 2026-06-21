
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type ComboRow <: guarana.gtk.Widget  = org.gnome.adw.ComboRow & guarana.gtk.Widget
object ComboRow extends VarsMap {
  val EnableSearch: ExternalVar.Aux[ComboRow, Boolean] = ExternalVar[ComboRow, Boolean]("enable-search", _.getEnableSearch(), _.setEnableSearch(_), true)
  val Expression: ExternalVar.Aux[ComboRow, org.gnome.gtk.Expression | Null] = ExternalVar[ComboRow, org.gnome.gtk.Expression | Null]("expression", _.getExpression(), _.setExpression(_), true)
  val Factory: ExternalVar.Aux[ComboRow, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[ComboRow, org.gnome.gtk.ListItemFactory | Null]("factory", _.getFactory(), _.setFactory(_), true)
  val HeaderFactory: ExternalVar.Aux[ComboRow, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[ComboRow, org.gnome.gtk.ListItemFactory | Null]("header-factory", _.getHeaderFactory(), _.setHeaderFactory(_), true)
  val ListFactory: ExternalVar.Aux[ComboRow, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[ComboRow, org.gnome.gtk.ListItemFactory | Null]("list-factory", _.getListFactory(), _.setListFactory(_), true)
  val Model: ExternalVar.Aux[ComboRow, org.gnome.gio.ListModel[?] | Null] = ExternalVar[ComboRow, org.gnome.gio.ListModel[?] | Null]("model", _.getModel(), _.setModel(_), true)
  val SearchMatchMode: ExternalVar.Aux[ComboRow, org.gnome.gtk.StringFilterMatchMode] = ExternalVar[ComboRow, org.gnome.gtk.StringFilterMatchMode]("search-match-mode", _.getSearchMatchMode(), _.setSearchMatchMode(_), true)
  val Selected: ExternalVar.Aux[ComboRow, Int] = ExternalVar[ComboRow, Int]("selected", _.getSelected(), _.setSelected(_), true)
  val UseSubtitle: ExternalVar.Aux[ComboRow, Boolean] = ExternalVar[ComboRow, Boolean]("use-subtitle", _.getUseSubtitle(), _.setUseSubtitle(_), true)

  

  extension (v: ComboRow) {
    def unwrap: org.gnome.adw.ComboRow = v

    def enableSearch: Var.Aux[Boolean, v.type] = guarana.gtk.adw.ComboRow.EnableSearch.asInstanceOf[Var.Aux[Boolean, v.type]]
    def expression: Var.Aux[org.gnome.gtk.Expression | Null, v.type] = guarana.gtk.adw.ComboRow.Expression.asInstanceOf[Var.Aux[org.gnome.gtk.Expression | Null, v.type]]
    def factory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = guarana.gtk.adw.ComboRow.Factory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def headerFactory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = guarana.gtk.adw.ComboRow.HeaderFactory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def listFactory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = guarana.gtk.adw.ComboRow.ListFactory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def model: Var.Aux[org.gnome.gio.ListModel[?] | Null, v.type] = guarana.gtk.adw.ComboRow.Model.asInstanceOf[Var.Aux[org.gnome.gio.ListModel[?] | Null, v.type]]
    def searchMatchMode: Var.Aux[org.gnome.gtk.StringFilterMatchMode, v.type] = guarana.gtk.adw.ComboRow.SearchMatchMode.asInstanceOf[Var.Aux[org.gnome.gtk.StringFilterMatchMode, v.type]]
    def selected: Var.Aux[Int, v.type] = guarana.gtk.adw.ComboRow.Selected.asInstanceOf[Var.Aux[Int, v.type]]
    def useSubtitle: Var.Aux[Boolean, v.type] = guarana.gtk.adw.ComboRow.UseSubtitle.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivate,
      onActivated,
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

  def wrap(v: org.gnome.adw.ComboRow): ComboRow = 
    val res = v.asInstanceOf[ComboRow]
    
    res

  def init(v: ComboRow): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(subtitle: Opt[java.lang.String], cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole], actionTarget: Opt[org.gnome.glib.Variant]): ComboRow = {
    val res = {
      val res = org.gnome.adw.ComboRow.builder()
      ifSet(subtitle, v => res.setSubtitle(v))
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      ifSet(actionTarget, v => res.setActionTarget(v))
      res.build()
    }
    
    res.asInstanceOf[ComboRow]
  }
  
  def apply(
    subtitle: Opt[java.lang.String] = UnsetParam, cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam, actionTarget: Opt[org.gnome.glib.Variant] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    enableSearch: Opt[Binding[Boolean]] = UnsetParam,
    expression: Opt[Binding[org.gnome.gtk.Expression | Null]] = UnsetParam,
    factory: Opt[Binding[org.gnome.gtk.ListItemFactory | Null]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    headerFactory: Opt[Binding[org.gnome.gtk.ListItemFactory | Null]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    listFactory: Opt[Binding[org.gnome.gtk.ListItemFactory | Null]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    model: Opt[Binding[org.gnome.gio.ListModel[?] | Null]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    searchMatchMode: Opt[Binding[org.gnome.gtk.StringFilterMatchMode]] = UnsetParam,
    selected: Opt[Binding[Int]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    useSubtitle: Opt[Binding[Boolean]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[ComboRow] = {
    val res = uninitialized(subtitle, cssName, heightRequest, widthRequest, accessibleRole, actionTarget)
    guarana.gtk.adw.ComboRow.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(enableSearch, res.enableSearch := _)
    ifSet(expression, res.expression := _)
    ifSet(factory, res.factory := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(headerFactory, res.headerFactory := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(listFactory, res.listFactory := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(model, res.model := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(searchMatchMode, res.searchMatchMode := _)
    ifSet(selected, res.selected := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(useSubtitle, res.useSubtitle := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        