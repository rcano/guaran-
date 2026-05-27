package guarana
package gtk
opaque type PopoverBin <: Widget = org.gnome.gtk.PopoverBin & Widget
object PopoverBin {
  val HandleInput: ExternalVar.Aux[PopoverBin, Boolean] = ExternalVar[PopoverBin, Boolean]("handle-input", _.getHandleInput(), _.setHandleInput(_), true)
  ()
  extension (v: PopoverBin) {
    def unwrap: org.gnome.gtk.PopoverBin = v
  }
  def init(v: PopoverBin): Unit = {
    Widget.init(v)
  }
  def uninitialized(): PopoverBin = {
    val res = new org.gnome.gtk.PopoverBin()
    res.asInstanceOf[PopoverBin]
  }
}