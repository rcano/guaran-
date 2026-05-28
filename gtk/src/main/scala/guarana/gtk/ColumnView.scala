package guarana
package gtk
import util.*
opaque type ColumnView <: Widget = org.gnome.gtk.ColumnView & Widget
object ColumnView {
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
  def init(v: ColumnView): Unit = {
    Widget.init(v)
  }
  def uninitialized(): ColumnView = {
    val res = new org.gnome.gtk.ColumnView()
    res.asInstanceOf[ColumnView]
  }
  def apply(enableRubberband: Opt[Boolean] = UnsetParam, hadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, headerFactory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, hscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam, model: Opt[org.gnome.gtk.SelectionModel[?] | Null] = UnsetParam, reorderable: Opt[Boolean] = UnsetParam, rowFactory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, showColumnSeparators: Opt[Boolean] = UnsetParam, showRowSeparators: Opt[Boolean] = UnsetParam, singleClickActivate: Opt[Boolean] = UnsetParam, tabBehavior: Opt[org.gnome.gtk.ListTabBehavior] = UnsetParam, vadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, vscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam): VarContextAction[ColumnView] = {
    val res = uninitialized()
    init(res)
    ifSet(enableRubberband, res.enableRubberband := _)
    ifSet(hadjustment, res.hadjustment := _)
    ifSet(headerFactory, res.headerFactory := _)
    ifSet(hscrollPolicy, res.hscrollPolicy := _)
    ifSet(model, res.model := _)
    ifSet(reorderable, res.reorderable := _)
    ifSet(rowFactory, res.rowFactory := _)
    ifSet(showColumnSeparators, res.showColumnSeparators := _)
    ifSet(showRowSeparators, res.showRowSeparators := _)
    ifSet(singleClickActivate, res.singleClickActivate := _)
    ifSet(tabBehavior, res.tabBehavior := _)
    ifSet(vadjustment, res.vadjustment := _)
    ifSet(vscrollPolicy, res.vscrollPolicy := _)
    res
  }
}