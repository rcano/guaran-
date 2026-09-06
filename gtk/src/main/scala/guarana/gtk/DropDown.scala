
package guarana.gtk

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type DropDown <: guarana.gtk.Widget  = org.gnome.gtk.DropDown & guarana.gtk.Widget
object DropDown extends VarsMap {
  val EnableSearch: ExternalVar.Aux[DropDown, Boolean] = ExternalVar[DropDown, Boolean]("enable-search", _.getEnableSearch(), _.setEnableSearch(_), true)
  val Expression: ExternalVar.Aux[DropDown, org.gnome.gtk.Expression | Null] = ExternalVar[DropDown, org.gnome.gtk.Expression | Null]("expression", _.getExpression(), _.setExpression(_), true)
  val Factory: ExternalVar.Aux[DropDown, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[DropDown, org.gnome.gtk.ListItemFactory | Null]("factory", _.getFactory(), _.setFactory(_), true)
  val HeaderFactory: ExternalVar.Aux[DropDown, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[DropDown, org.gnome.gtk.ListItemFactory | Null]("header-factory", _.getHeaderFactory(), _.setHeaderFactory(_), true)
  val ListFactory: ExternalVar.Aux[DropDown, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[DropDown, org.gnome.gtk.ListItemFactory | Null]("list-factory", _.getListFactory(), _.setListFactory(_), true)
  val Model: ExternalVar.Aux[DropDown, org.gnome.gio.ListModel[?] | Null] = ExternalVar[DropDown, org.gnome.gio.ListModel[?] | Null]("model", _.getModel(), _.setModel(_), true)
  val SearchMatchMode: ExternalVar.Aux[DropDown, org.gnome.gtk.StringFilterMatchMode] = ExternalVar[DropDown, org.gnome.gtk.StringFilterMatchMode]("search-match-mode", _.getSearchMatchMode(), _.setSearchMatchMode(_), true)
  val Selected: ExternalVar.Aux[DropDown, Int] = ExternalVar[DropDown, Int]("selected", _.getSelected(), _.setSelected(_), true)
  val ShowArrow: ExternalVar.Aux[DropDown, Boolean] = ExternalVar[DropDown, Boolean]("show-arrow", _.getShowArrow(), _.setShowArrow(_), true)

  

  extension (v: DropDown) {
    def unwrap: org.gnome.gtk.DropDown = v

    def enableSearch: Var.Aux[Boolean, v.type] = guarana.gtk.DropDown.EnableSearch.asInstanceOf[Var.Aux[Boolean, v.type]]
    def expression: Var.Aux[org.gnome.gtk.Expression | Null, v.type] = guarana.gtk.DropDown.Expression.asInstanceOf[Var.Aux[org.gnome.gtk.Expression | Null, v.type]]
    def factory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = guarana.gtk.DropDown.Factory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def headerFactory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = guarana.gtk.DropDown.HeaderFactory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def listFactory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = guarana.gtk.DropDown.ListFactory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def model: Var.Aux[org.gnome.gio.ListModel[?] | Null, v.type] = guarana.gtk.DropDown.Model.asInstanceOf[Var.Aux[org.gnome.gio.ListModel[?] | Null, v.type]]
    def searchMatchMode: Var.Aux[org.gnome.gtk.StringFilterMatchMode, v.type] = guarana.gtk.DropDown.SearchMatchMode.asInstanceOf[Var.Aux[org.gnome.gtk.StringFilterMatchMode, v.type]]
    def selected: Var.Aux[Int, v.type] = guarana.gtk.DropDown.Selected.asInstanceOf[Var.Aux[Int, v.type]]
    def showArrow: Var.Aux[Boolean, v.type] = guarana.gtk.DropDown.ShowArrow.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onActivate,
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

  def wrap(v: org.gnome.gtk.DropDown): DropDown = 
    val res = v.asInstanceOf[DropDown]
    
    res

  def init(v: DropDown): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): DropDown = {
    val res = {
      val res = org.gnome.gtk.DropDown.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[DropDown]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
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
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    searchMatchMode: Opt[Binding[org.gnome.gtk.StringFilterMatchMode]] = UnsetParam,
    selected: Opt[Binding[Int]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    showArrow: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[DropDown] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.DropDown.init(res)
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
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(searchMatchMode, res.searchMatchMode := _)
    ifSet(selected, res.selected := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showArrow, res.showArrow := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        