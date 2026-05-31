
package guarana
package gtk

import guarana.util.*

opaque type ListBase <: guarana.gtk.Widget  = org.gnome.gtk.ListBase & guarana.gtk.Widget
object ListBase extends VarsMap {
  val Hadjustment: ExternalVar.Aux[ListBase, org.gnome.gtk.Adjustment | Null] = ExternalVar[ListBase, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  val HscrollPolicy: ExternalVar.Aux[ListBase, org.gnome.gtk.ScrollablePolicy] = ExternalVar[ListBase, org.gnome.gtk.ScrollablePolicy]("hscroll-policy", _.getHscrollPolicy(), _.setHscrollPolicy(_), true)
  val Orientation: ExternalVar.Aux[ListBase, org.gnome.gtk.Orientation] = ExternalVar[ListBase, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Vadjustment: ExternalVar.Aux[ListBase, org.gnome.gtk.Adjustment | Null] = ExternalVar[ListBase, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  val VscrollPolicy: ExternalVar.Aux[ListBase, org.gnome.gtk.ScrollablePolicy] = ExternalVar[ListBase, org.gnome.gtk.ScrollablePolicy]("vscroll-policy", _.getVscrollPolicy(), _.setVscrollPolicy(_), true)

  

  extension (v: ListBase) {
    def unwrap: org.gnome.gtk.ListBase = v

    def hadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = guarana.gtk.ListBase.Hadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def hscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = guarana.gtk.ListBase.HscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.ListBase.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def vadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = guarana.gtk.ListBase.Vadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def vscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = guarana.gtk.ListBase.VscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]

    

    export unwrap.{
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

  def wrap(v: org.gnome.gtk.ListBase): ListBase = 
    val res = v.asInstanceOf[ListBase]
    
    res

  def init(v: ListBase): Toolkit ?=> Unit = (tk: Toolkit) ?=> {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  
  
}
        