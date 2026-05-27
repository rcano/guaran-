package guarana
package gtk
opaque type Button <: Widget = org.gnome.gtk.Button & Widget
object Button {
  val CanShrink: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("can-shrink", _.getCanShrink(), _.setCanShrink(_), true)
  val HasFrame: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("has-frame", _.getHasFrame(), _.setHasFrame(_), true)
  val UseUnderline: ExternalVar.Aux[Button, Boolean] = ExternalVar[Button, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)
  ()
  extension (v: Button) {
    def unwrap: org.gnome.gtk.Button = v
    export unwrap.onActivate, unwrap.onClicked
  }
  def init(v: Button): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Button = {
    val res = new org.gnome.gtk.Button()
    res.asInstanceOf[Button]
  }
}