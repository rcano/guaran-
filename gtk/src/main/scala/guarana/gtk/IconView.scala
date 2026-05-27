package guarana
package gtk
opaque type IconView <: Widget = org.gnome.gtk.IconView & Widget
object IconView {
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
    export unwrap.onActivateCursorItem, unwrap.onItemActivated, unwrap.onMoveCursor, unwrap.onSelectAll, unwrap.onSelectCursorItem, unwrap.onSelectionChanged, unwrap.onToggleCursorItem, unwrap.onUnselectAll
  }
  def init(v: IconView): Unit = {
    Widget.init(v)
  }
  def uninitialized(): IconView = {
    val res = new org.gnome.gtk.IconView()
    res.asInstanceOf[IconView]
  }
}