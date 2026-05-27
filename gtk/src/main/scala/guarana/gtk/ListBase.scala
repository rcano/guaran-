package guarana
package gtk
opaque type ListBase <: Widget = org.gnome.gtk.ListBase & Widget
object ListBase {
  val Hadjustment: ExternalVar.Aux[ListBase, org.gnome.gtk.Adjustment | Null] = ExternalVar[ListBase, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  val HscrollPolicy: ExternalVar.Aux[ListBase, org.gnome.gtk.ScrollablePolicy] = ExternalVar[ListBase, org.gnome.gtk.ScrollablePolicy]("hscroll-policy", _.getHscrollPolicy(), _.setHscrollPolicy(_), true)
  val Orientation: ExternalVar.Aux[ListBase, org.gnome.gtk.Orientation] = ExternalVar[ListBase, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val Vadjustment: ExternalVar.Aux[ListBase, org.gnome.gtk.Adjustment | Null] = ExternalVar[ListBase, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  val VscrollPolicy: ExternalVar.Aux[ListBase, org.gnome.gtk.ScrollablePolicy] = ExternalVar[ListBase, org.gnome.gtk.ScrollablePolicy]("vscroll-policy", _.getVscrollPolicy(), _.setVscrollPolicy(_), true)
  ()
  extension (v: ListBase) {
    def unwrap: org.gnome.gtk.ListBase = v
  }
  def init(v: ListBase): Unit = {
    Widget.init(v)
  }
  ()
}