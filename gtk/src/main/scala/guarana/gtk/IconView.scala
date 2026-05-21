package guarana
package gtk
opaque type IconView <: Widget = org.gnome.gtk.IconView & Widget
object IconView {
  val ActivateOnSingleClick: ExternalVar.Aux[IconView, Boolean] = ExternalVar[IconView, Boolean]("activate-on-single-click", _.getActivateOnSingleClick(), _.setActivateOnSingleClick(_), true)
  val ColumnSpacing: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("column-spacing", _.getColumnSpacing(), _.setColumnSpacing(_), true)
  val Columns: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("columns", _.getColumns(), _.setColumns(_), true)
  val ItemOrientation: ExternalVar.Aux[IconView, org.gnome.gtk.Orientation | Null] = ExternalVar[IconView, org.gnome.gtk.Orientation | Null]("item-orientation", _.getItemOrientation(), _.setItemOrientation(_), true)
  val ItemPadding: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("item-padding", _.getItemPadding(), _.setItemPadding(_), true)
  val ItemWidth: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("item-width", _.getItemWidth(), _.setItemWidth(_), true)
  val Margin: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("margin", _.getMargin(), _.setMargin(_), true)
  val MarkupColumn: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("markup-column", _.getMarkupColumn(), _.setMarkupColumn(_), true)
  val Model: ExternalVar.Aux[IconView, org.gnome.gtk.TreeModel | Null] = ExternalVar[IconView, org.gnome.gtk.TreeModel | Null]("model", _.getModel(), _.setModel(_), true)
  val PixbufColumn: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("pixbuf-column", _.getPixbufColumn(), _.setPixbufColumn(_), true)
  val Reorderable: ExternalVar.Aux[IconView, Boolean] = ExternalVar[IconView, Boolean]("reorderable", _.getReorderable(), _.setReorderable(_), true)
  val RowSpacing: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("row-spacing", _.getRowSpacing(), _.setRowSpacing(_), true)
  val SelectionMode: ExternalVar.Aux[IconView, org.gnome.gtk.SelectionMode | Null] = ExternalVar[IconView, org.gnome.gtk.SelectionMode | Null]("selection-mode", _.getSelectionMode(), _.setSelectionMode(_), true)
  val Spacing: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("spacing", _.getSpacing(), _.setSpacing(_), true)
  val TextColumn: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("text-column", _.getTextColumn(), _.setTextColumn(_), true)
  val TooltipColumn: ExternalVar.Aux[IconView, Int] = ExternalVar[IconView, Int]("tooltip-column", _.getTooltipColumn(), _.setTooltipColumn(_), true)
  ()
  extension (v: IconView) {
    def unwrap: org.gnome.gtk.IconView = v
    export unwrap.onActivateCursorItem, unwrap.onItemActivated, unwrap.onMoveCursor, unwrap.onSelectAll, unwrap.onSelectCursorItem, unwrap.onSelectionChanged, unwrap.onToggleCursorItem, unwrap.onUnselectAll
  }
}