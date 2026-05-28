package guarana
package gtk
import util.*
opaque type GridView <: ListBase = org.gnome.gtk.GridView & ListBase
object GridView {
  val EnableRubberband: ExternalVar.Aux[GridView, Boolean] = ExternalVar[GridView, Boolean]("enable-rubberband", _.getEnableRubberband(), _.setEnableRubberband(_), true)
  val Factory: ExternalVar.Aux[GridView, org.gnome.gtk.ListItemFactory | Null] = ExternalVar[GridView, org.gnome.gtk.ListItemFactory | Null]("factory", _.getFactory(), _.setFactory(_), true)
  val MaxColumns: ExternalVar.Aux[GridView, Int] = ExternalVar[GridView, Int]("max-columns", _.getMaxColumns(), _.setMaxColumns(_), true)
  val MinColumns: ExternalVar.Aux[GridView, Int] = ExternalVar[GridView, Int]("min-columns", _.getMinColumns(), _.setMinColumns(_), true)
  val Model: ExternalVar.Aux[GridView, org.gnome.gtk.SelectionModel[?] | Null] = ExternalVar[GridView, org.gnome.gtk.SelectionModel[?] | Null]("model", _.getModel(), _.setModel(_), true)
  val SingleClickActivate: ExternalVar.Aux[GridView, Boolean] = ExternalVar[GridView, Boolean]("single-click-activate", _.getSingleClickActivate(), _.setSingleClickActivate(_), true)
  val TabBehavior: ExternalVar.Aux[GridView, org.gnome.gtk.ListTabBehavior] = ExternalVar[GridView, org.gnome.gtk.ListTabBehavior]("tab-behavior", _.getTabBehavior(), _.setTabBehavior(_), true)
  ()
  extension (v: GridView) {
    def unwrap: org.gnome.gtk.GridView = v
    def enableRubberband: Var.Aux[Boolean, v.type] = EnableRubberband.asInstanceOf[Var.Aux[Boolean, v.type]]
    def factory: Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type] = Factory.asInstanceOf[Var.Aux[org.gnome.gtk.ListItemFactory | Null, v.type]]
    def maxColumns: Var.Aux[Int, v.type] = MaxColumns.asInstanceOf[Var.Aux[Int, v.type]]
    def minColumns: Var.Aux[Int, v.type] = MinColumns.asInstanceOf[Var.Aux[Int, v.type]]
    def model: Var.Aux[org.gnome.gtk.SelectionModel[?] | Null, v.type] = Model.asInstanceOf[Var.Aux[org.gnome.gtk.SelectionModel[?] | Null, v.type]]
    def singleClickActivate: Var.Aux[Boolean, v.type] = SingleClickActivate.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tabBehavior: Var.Aux[org.gnome.gtk.ListTabBehavior, v.type] = TabBehavior.asInstanceOf[Var.Aux[org.gnome.gtk.ListTabBehavior, v.type]]
    export unwrap.onActivate
  }
  def init(v: GridView): Unit = {
    ListBase.init(v)
  }
  def uninitialized(): GridView = {
    val res = new org.gnome.gtk.GridView()
    res.asInstanceOf[GridView]
  }
  def apply(enableRubberband: Opt[Boolean] = UnsetParam, factory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, maxColumns: Opt[Int] = UnsetParam, minColumns: Opt[Int] = UnsetParam, model: Opt[org.gnome.gtk.SelectionModel[?] | Null] = UnsetParam, singleClickActivate: Opt[Boolean] = UnsetParam, tabBehavior: Opt[org.gnome.gtk.ListTabBehavior] = UnsetParam): VarContextAction[GridView] = {
    val res = uninitialized()
    init(res)
    ifSet(enableRubberband, res.enableRubberband := _)
    ifSet(factory, res.factory := _)
    ifSet(maxColumns, res.maxColumns := _)
    ifSet(minColumns, res.minColumns := _)
    ifSet(model, res.model := _)
    ifSet(singleClickActivate, res.singleClickActivate := _)
    ifSet(tabBehavior, res.tabBehavior := _)
    res
  }
}