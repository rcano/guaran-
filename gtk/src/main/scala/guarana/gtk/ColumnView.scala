package guarana
package gtk
import util.*
opaque type ColumnView <: Widget = org.gnome.gtk.ColumnView & Widget
object ColumnView extends VarsMap {
  val EnableRubberband: ExternalVar.Aux[ColumnView, Boolean] = ExternalVar[ColumnView, Boolean]("enable-rubberband", _.getEnableRubberband(), _.setEnableRubberband(_), true)
  val Hadjustment: ExternalVar.Aux[ColumnView, org.gnome.gtk.Adjustment | Null] = ExternalVar[ColumnView, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  val HeaderFactory: ExternalVar.Aux[ColumnView, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[ColumnView, org.gnome.gtk.ListItemFactory | Null]("header-factory", _.getHeaderFactory(), _.setHeaderFactory(_), true)
  val HscrollPolicy: ExternalVar.Aux[ColumnView, org.gnome.gtk.ScrollablePolicy] = ExternalVar[ColumnView, org.gnome.gtk.ScrollablePolicy]("hscroll-policy", _.getHscrollPolicy(), _.setHscrollPolicy(_), true)
  val Model: ExternalVar.Aux[ColumnView, org.gnome.gtk.SelectionModel[?] | Null] = ExternalVar[ColumnView, org.gnome.gtk.SelectionModel[?] | Null]("model", _.getModel(), _.setModel(_), true)
  val Reorderable: ExternalVar.Aux[ColumnView, Boolean] = ExternalVar[ColumnView, Boolean]("reorderable", _.getReorderable(), _.setReorderable(_), true)
  val RowFactory: ExternalVar.Aux[ColumnView, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[ColumnView, org.gnome.gtk.ListItemFactory | Null]("row-factory", _.getRowFactory(), _.setRowFactory(_), true)
  val ShowColumnSeparators: ExternalVar.Aux[ColumnView, Boolean] = ExternalVar[ColumnView, Boolean]("show-column-separators", _.getShowColumnSeparators(), _.setShowColumnSeparators(_), true)
  val ShowRowSeparators: ExternalVar.Aux[ColumnView, Boolean] = ExternalVar[ColumnView, Boolean]("show-row-separators", _.getShowRowSeparators(), _.setShowRowSeparators(_), true)
  val SingleClickActivate: ExternalVar.Aux[ColumnView, Boolean] = ExternalVar[ColumnView, Boolean]("single-click-activate", _.getSingleClickActivate(), _.setSingleClickActivate(_), true)
  val TabBehavior: ExternalVar.Aux[ColumnView, org.gnome.gtk.ListTabBehavior] = ExternalVar[ColumnView, org.gnome.gtk.ListTabBehavior]("tab-behavior", _.getTabBehavior(), _.setTabBehavior(_), true)
  val Vadjustment: ExternalVar.Aux[ColumnView, org.gnome.gtk.Adjustment | Null] = ExternalVar[ColumnView, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  val VscrollPolicy: ExternalVar.Aux[ColumnView, org.gnome.gtk.ScrollablePolicy] = ExternalVar[ColumnView, org.gnome.gtk.ScrollablePolicy]("vscroll-policy", _.getVscrollPolicy(), _.setVscrollPolicy(_), true)
  ()
  extension (v: ColumnView) {
    def unwrap: org.gnome.gtk.ColumnView = v
    def enableRubberband: Var.Aux[Boolean, v.type] = EnableRubberband.asInstanceOf[Var.Aux[Boolean, v.type]]
    def hadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = Hadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def headerFactory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = HeaderFactory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def hscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = HscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    def model: Var.Aux[org.gnome.gtk.SelectionModel[?] | Null, v.type] = Model.asInstanceOf[Var.Aux[org.gnome.gtk.SelectionModel[?] | Null, v.type]]
    def reorderable: Var.Aux[Boolean, v.type] = Reorderable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def rowFactory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = RowFactory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def showColumnSeparators: Var.Aux[Boolean, v.type] = ShowColumnSeparators.asInstanceOf[Var.Aux[Boolean, v.type]]
    def showRowSeparators: Var.Aux[Boolean, v.type] = ShowRowSeparators.asInstanceOf[Var.Aux[Boolean, v.type]]
    def singleClickActivate: Var.Aux[Boolean, v.type] = SingleClickActivate.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tabBehavior: Var.Aux[org.gnome.gtk.ListTabBehavior, v.type] = TabBehavior.asInstanceOf[Var.Aux[org.gnome.gtk.ListTabBehavior, v.type]]
    def vadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = Vadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def vscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = VscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    export unwrap.onActivate
  }
  def _wrap(v: org.gnome.gtk.ColumnView): ColumnView = {
    v.asInstanceOf
  }
  def init(v: ColumnView): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: org.gnome.gtk.SelectionModel[?] | Null): ColumnView = {
    val res = new org.gnome.gtk.ColumnView(arg$0)
    res.asInstanceOf[ColumnView]
  }
  def apply(arg$0: org.gnome.gtk.SelectionModel[?] | Null, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, enableRubberband: Opt[Boolean] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, hadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, headerFactory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, hscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, model: Opt[org.gnome.gtk.SelectionModel[?] | Null] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, reorderable: Opt[Boolean] = UnsetParam, rowFactory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, showColumnSeparators: Opt[Boolean] = UnsetParam, showRowSeparators: Opt[Boolean] = UnsetParam, singleClickActivate: Opt[Boolean] = UnsetParam, tabBehavior: Opt[org.gnome.gtk.ListTabBehavior] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, vadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, vscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam): ToolkitAction[Toolkit, ColumnView] = {
    val res = uninitialized(arg$0)
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(enableRubberband, res.enableRubberband := _)
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
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(reorderable, res.reorderable := _)
    ifSet(rowFactory, res.rowFactory := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(showColumnSeparators, res.showColumnSeparators := _)
    ifSet(showRowSeparators, res.showRowSeparators := _)
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