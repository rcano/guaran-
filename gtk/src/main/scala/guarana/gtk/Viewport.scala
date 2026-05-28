package guarana
package gtk
import util.*
opaque type Viewport <: Widget = org.gnome.gtk.Viewport & Widget
object Viewport {
  val Child: ExternalVar.Aux[Viewport, org.gnome.gtk.Widget | Null] = ExternalVar[Viewport, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Hadjustment: ExternalVar.Aux[Viewport, org.gnome.gtk.Adjustment | Null] = ExternalVar[Viewport, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  val HscrollPolicy: ExternalVar.Aux[Viewport, org.gnome.gtk.ScrollablePolicy] = ExternalVar[Viewport, org.gnome.gtk.ScrollablePolicy]("hscroll-policy", _.getHscrollPolicy(), _.setHscrollPolicy(_), true)
  val ScrollToFocus: ExternalVar.Aux[Viewport, Boolean] = ExternalVar[Viewport, Boolean]("scroll-to-focus", _.getScrollToFocus(), _.setScrollToFocus(_), true)
  val Vadjustment: ExternalVar.Aux[Viewport, org.gnome.gtk.Adjustment | Null] = ExternalVar[Viewport, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  val VscrollPolicy: ExternalVar.Aux[Viewport, org.gnome.gtk.ScrollablePolicy] = ExternalVar[Viewport, org.gnome.gtk.ScrollablePolicy]("vscroll-policy", _.getVscrollPolicy(), _.setVscrollPolicy(_), true)
  ()
  extension (v: Viewport) {
    def unwrap: org.gnome.gtk.Viewport = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def hadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = Hadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def hscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = HscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    def scrollToFocus: Var.Aux[Boolean, v.type] = ScrollToFocus.asInstanceOf[Var.Aux[Boolean, v.type]]
    def vadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = Vadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def vscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = VscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
  }
  def init(v: Viewport): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Viewport = {
    val res = new org.gnome.gtk.Viewport()
    res.asInstanceOf[Viewport]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, hadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, hscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam, scrollToFocus: Opt[Boolean] = UnsetParam, vadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, vscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam): VarContextAction[Viewport] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    ifSet(hadjustment, res.hadjustment := _)
    ifSet(hscrollPolicy, res.hscrollPolicy := _)
    ifSet(scrollToFocus, res.scrollToFocus := _)
    ifSet(vadjustment, res.vadjustment := _)
    ifSet(vscrollPolicy, res.vscrollPolicy := _)
    res
  }
}