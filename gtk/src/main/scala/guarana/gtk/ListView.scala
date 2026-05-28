package guarana
package gtk
import util.*
opaque type ListView <: ListBase = org.gnome.gtk.ListView & ListBase
object ListView {
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
  def init(v: ListView): Unit = {
    ListBase.init(v)
  }
  def uninitialized(): ListView = {
    val res = new org.gnome.gtk.ListView()
    res.asInstanceOf[ListView]
  }
  def apply(enableRubberband: Opt[Boolean] = UnsetParam, factory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, headerFactory: Opt[org.gnome.gtk.ListItemFactory | Null] = UnsetParam, model: Opt[org.gnome.gtk.SelectionModel[?] | Null] = UnsetParam, showSeparators: Opt[Boolean] = UnsetParam, singleClickActivate: Opt[Boolean] = UnsetParam, tabBehavior: Opt[org.gnome.gtk.ListTabBehavior] = UnsetParam): VarContextAction[ListView] = {
    val res = uninitialized()
    init(res)
    ifSet(enableRubberband, res.enableRubberband := _)
    ifSet(factory, res.factory := _)
    ifSet(headerFactory, res.headerFactory := _)
    ifSet(model, res.model := _)
    ifSet(showSeparators, res.showSeparators := _)
    ifSet(singleClickActivate, res.singleClickActivate := _)
    ifSet(tabBehavior, res.tabBehavior := _)
    res
  }
}