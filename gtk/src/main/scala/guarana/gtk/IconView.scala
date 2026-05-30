package guarana
package gtk
import util.*
opaque type IconView <: Widget = org.gnome.gtk.IconView & Widget
object IconView extends VarsMap {
  @deprecated("", "") val ActivateOnSingleClick: ExternalVar.Aux[IconView, Boolean] = ExternalVar[IconView, Boolean]("activate-on-single-click", _.getActivateOnSingleClick(), _.setActivateOnSingleClick(_), true)
  @deprecated("", "") val ColumnSpacing: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("column-spacing", _.getColumnSpacing(), _.setColumnSpacing(_), true)
  @deprecated("", "") val Columns: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("columns", _.getColumns(), _.setColumns(_), true)
  val Hadjustment: ExternalVar.Aux[IconView, org.gnome.gtk.Adjustment | Null] = ExternalVar[IconView, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  val HscrollPolicy: ExternalVar.Aux[IconView, org.gnome.gtk.ScrollablePolicy] = ExternalVar[IconView, org.gnome.gtk.ScrollablePolicy]("hscroll-policy", _.getHscrollPolicy(), _.setHscrollPolicy(_), true)
  @deprecated("", "") val ItemOrientation: ExternalVar.Aux[IconView, org.gnome.gtk.Orientation] = ExternalVar[IconView, org.gnome.gtk.Orientation]("item-orientation", _.getItemOrientation(), _.setItemOrientation(_), true)
  @deprecated("", "") val ItemPadding: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("item-padding", _.getItemPadding(), _.setItemPadding(_), true)
  @deprecated("", "") val ItemWidth: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("item-width", _.getItemWidth(), _.setItemWidth(_), true)
  @deprecated("", "") val Margin: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("margin", _.getMargin(), _.setMargin(_), true)
  @deprecated("", "") val MarkupColumn: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("markup-column", _.getMarkupColumn(), _.setMarkupColumn(_), true)
  @deprecated("", "") val Model: ExternalVar.Aux[IconView, org.gnome.gtk.TreeModel | Null] = ExternalVar[IconView, org.gnome.gtk.TreeModel | Null]("model", _.getModel(), _.setModel(_), true)
  @deprecated("", "") val PixbufColumn: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("pixbuf-column", _.getPixbufColumn(), _.setPixbufColumn(_), true)
  @deprecated("", "") val Reorderable: ExternalVar.Aux[IconView, Boolean] = ExternalVar[IconView, Boolean]("reorderable", _.getReorderable(), _.setReorderable(_), true)
  @deprecated("", "") val RowSpacing: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("row-spacing", _.getRowSpacing(), _.setRowSpacing(_), true)
  @deprecated("", "") val SelectionMode: ExternalVar.Aux[IconView, org.gnome.gtk.SelectionMode] = ExternalVar[IconView, org.gnome.gtk.SelectionMode]("selection-mode", _.getSelectionMode(), _.setSelectionMode(_), true)
  @deprecated("", "") val Spacing: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("spacing", _.getSpacing(), _.setSpacing(_), true)
  @deprecated("", "") val TextColumn: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("text-column", _.getTextColumn(), _.setTextColumn(_), true)
  @deprecated("", "") val TooltipColumn: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("tooltip-column", _.getTooltipColumn(), _.setTooltipColumn(_), true)
  val Vadjustment: ExternalVar.Aux[IconView, org.gnome.gtk.Adjustment | Null] = ExternalVar[IconView, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  val VscrollPolicy: ExternalVar.Aux[IconView, org.gnome.gtk.ScrollablePolicy] = ExternalVar[IconView, org.gnome.gtk.ScrollablePolicy]("vscroll-policy", _.getVscrollPolicy(), _.setVscrollPolicy(_), true)
  ()
  extension (v: IconView) {
    def unwrap: org.gnome.gtk.IconView = v
    @deprecated("", "") def activateOnSingleClick: Var.Aux[Boolean, v.type] = ActivateOnSingleClick.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def columnSpacing: Var.Aux[Int, v.type] = ColumnSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def columns: Var.Aux[Int, v.type] = Columns.asInstanceOf[Var.Aux[Int, v.type]]
    def hadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = Hadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def hscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = HscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    @deprecated("", "") def itemOrientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = ItemOrientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    @deprecated("", "") def itemPadding: Var.Aux[Int, v.type] = ItemPadding.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def itemWidth: Var.Aux[Int, v.type] = ItemWidth.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def margin: Var.Aux[Int, v.type] = Margin.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def markupColumn: Var.Aux[Int, v.type] = MarkupColumn.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def model: Var.Aux[org.gnome.gtk.TreeModel | Null, v.type] = Model.asInstanceOf[Var.Aux[org.gnome.gtk.TreeModel | Null, v.type]]
    @deprecated("", "") def pixbufColumn: Var.Aux[Int, v.type] = PixbufColumn.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def reorderable: Var.Aux[Boolean, v.type] = Reorderable.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def rowSpacing: Var.Aux[Int, v.type] = RowSpacing.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def selectionMode: Var.Aux[org.gnome.gtk.SelectionMode, v.type] = SelectionMode.asInstanceOf[Var.Aux[org.gnome.gtk.SelectionMode, v.type]]
    @deprecated("", "") def spacing: Var.Aux[Int, v.type] = Spacing.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def textColumn: Var.Aux[Int, v.type] = TextColumn.asInstanceOf[Var.Aux[Int, v.type]]
    @deprecated("", "") def tooltipColumn: Var.Aux[Int, v.type] = TooltipColumn.asInstanceOf[Var.Aux[Int, v.type]]
    def vadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = Vadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def vscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = VscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    export unwrap.onActivateCursorItem, unwrap.onItemActivated, unwrap.onMoveCursor, unwrap.onSelectAll, unwrap.onSelectCursorItem, unwrap.onSelectionChanged, unwrap.onToggleCursorItem, unwrap.onUnselectAll
  }
  def _wrap(v: org.gnome.gtk.IconView): IconView = {
    v.asInstanceOf
  }
  def init(v: IconView): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): IconView = {
    val res = new org.gnome.gtk.IconView()
    res.asInstanceOf[IconView]
  }
  def apply(activateOnSingleClick: Opt[Binding[Boolean]] = UnsetParam, canFocus: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, columnSpacing: Opt[Binding[Int]] = UnsetParam, columns: Opt[Binding[Int]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, hadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, hscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam, itemOrientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam, itemPadding: Opt[Binding[Int]] = UnsetParam, itemWidth: Opt[Binding[Int]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, margin: Opt[Binding[Int]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, markupColumn: Opt[Binding[Int]] = UnsetParam, model: Opt[Binding[org.gnome.gtk.TreeModel | Null]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, pixbufColumn: Opt[Binding[Int]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, reorderable: Opt[Binding[Boolean]] = UnsetParam, rowSpacing: Opt[Binding[Int]] = UnsetParam, selectionMode: Opt[Binding[org.gnome.gtk.SelectionMode]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, spacing: Opt[Binding[Int]] = UnsetParam, textColumn: Opt[Binding[Int]] = UnsetParam, tooltipColumn: Opt[Binding[Int]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, vadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam, vscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam): ToolkitAction[Toolkit, IconView] = {
    val res = uninitialized()
    init(res)
    ifSet(activateOnSingleClick, res.activateOnSingleClick := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(columnSpacing, res.columnSpacing := _)
    ifSet(columns, res.columns := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(hadjustment, res.hadjustment := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(hscrollPolicy, res.hscrollPolicy := _)
    ifSet(itemOrientation, res.itemOrientation := _)
    ifSet(itemPadding, res.itemPadding := _)
    ifSet(itemWidth, res.itemWidth := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(margin, res.margin := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(markupColumn, res.markupColumn := _)
    ifSet(model, res.model := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(pixbufColumn, res.pixbufColumn := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(reorderable, res.reorderable := _)
    ifSet(rowSpacing, res.rowSpacing := _)
    ifSet(selectionMode, res.selectionMode := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(spacing, res.spacing := _)
    ifSet(textColumn, res.textColumn := _)
    ifSet(tooltipColumn, res.tooltipColumn := _)
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