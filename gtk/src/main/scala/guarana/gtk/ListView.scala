package guarana
package gtk
import util.*
opaque type ListView <: ListBase = org.gnome.gtk.ListView & ListBase
object ListView extends VarsMap {
  val EnableRubberband: ExternalVar.Aux[ListView, Boolean] = ExternalVar[ListView, Boolean]("enable-rubberband", _.getEnableRubberband(), _.setEnableRubberband(_), true)
  val Factory: ExternalVar.Aux[ListView, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[ListView, org.gnome.gtk.ListItemFactory | Null]("factory", _.getFactory(), _.setFactory(_), true)
  val HeaderFactory: ExternalVar.Aux[ListView, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[ListView, org.gnome.gtk.ListItemFactory | Null]("header-factory", _.getHeaderFactory(), _.setHeaderFactory(_), true)
  val Model: ExternalVar.Aux[ListView, org.gnome.gtk.SelectionModel[?] | Null] = ExternalVar[ListView, org.gnome.gtk.SelectionModel[?] | Null]("model", _.getModel(), _.setModel(_), true)
  val ShowSeparators: ExternalVar.Aux[ListView, Boolean] = ExternalVar[ListView, Boolean]("show-separators", _.getShowSeparators(), _.setShowSeparators(_), true)
  val SingleClickActivate: ExternalVar.Aux[ListView, Boolean] = ExternalVar[ListView, Boolean]("single-click-activate", _.getSingleClickActivate(), _.setSingleClickActivate(_), true)
  val TabBehavior: ExternalVar.Aux[ListView, org.gnome.gtk.ListTabBehavior] = ExternalVar[ListView, org.gnome.gtk.ListTabBehavior]("tab-behavior", _.getTabBehavior(), _.setTabBehavior(_), true)
  ()
  extension (v: ListView) {
    def unwrap: org.gnome.gtk.ListView = v
    def enableRubberband: Var.Aux[Boolean, v.type] = EnableRubberband.asInstanceOf[Var.Aux[Boolean, v.type]]
    def factory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = Factory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def headerFactory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = HeaderFactory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def model: Var.Aux[org.gnome.gtk.SelectionModel[?] | Null, v.type] = Model.asInstanceOf[Var.Aux[org.gnome.gtk.SelectionModel[?] | Null, v.type]]
    def showSeparators: Var.Aux[Boolean, v.type] = ShowSeparators.asInstanceOf[Var.Aux[Boolean, v.type]]
    def singleClickActivate: Var.Aux[Boolean, v.type] = SingleClickActivate.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tabBehavior: Var.Aux[org.gnome.gtk.ListTabBehavior, v.type] = TabBehavior.asInstanceOf[Var.Aux[org.gnome.gtk.ListTabBehavior, v.type]]
    export unwrap.onActivate
  }
  def _wrap(v: org.gnome.gtk.ListView): ListView = {
    v.asInstanceOf
  }
  def init(v: ListView): ToolkitAction[Toolkit, Unit] = {
    ListBase.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gtk.SelectionModel[?] | Null, arg$1: org.gnome.gtk.ListItemFactory | Null): ListView = {
    val res = new org.gnome.gtk.ListView(arg$0, arg$1)
    res.asInstanceOf[ListView]
  }
  def apply(arg$0: org.gnome.gtk.SelectionModel[?] | Null, arg$1: org.gnome.gtk.ListItemFactory | Null, canFocus: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, enableRubberband: Opt[Binding[Boolean]] = UnsetParam, factory: Opt[Binding[org.gnome.gtk.ListItemFactory | Null]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, hadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, headerFactory: Opt[Binding[org.gnome.gtk.ListItemFactory | Null]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, hscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, model: Opt[Binding[org.gnome.gtk.SelectionModel[?] | Null]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, showSeparators: Opt[Binding[Boolean]] = UnsetParam, singleClickActivate: Opt[Binding[Boolean]] = UnsetParam, tabBehavior: Opt[Binding[org.gnome.gtk.ListTabBehavior]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, vadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam, vscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam): ToolkitAction[Toolkit, ListView] = {
    val res = uninitialized(arg$0, arg$1)
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(enableRubberband, res.enableRubberband := _)
    ifSet(factory, res.factory := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(hadjustment, res.hadjustment := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(headerFactory, res.headerFactory := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(hscrollPolicy, res.hscrollPolicy := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(model, res.model := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showSeparators, res.showSeparators := _)
    ifSet(singleClickActivate, res.singleClickActivate := _)
    ifSet(tabBehavior, res.tabBehavior := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(vadjustment, res.vadjustment := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(vscrollPolicy, res.vscrollPolicy := _)
    res
  }
}