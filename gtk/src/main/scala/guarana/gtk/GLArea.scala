package guarana
package gtk
opaque type GLArea <: Widget = org.gnome.gtk.GLArea & Widget
object GLArea {
  val AllowedApis: ExternalVar.Aux[GLArea, java.util.Set[org.gnome.gdk.GLAPI] | Null] = ExternalVar[GLArea, java.util.Set[org.gnome.gdk.GLAPI] | Null]("allowed-apis", _.getAllowedApis(), _.setAllowedApis(_), true)
  val AutoRender: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("auto-render", _.getAutoRender(), _.setAutoRender(_), true)
  val HasDepthBuffer: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("has-depth-buffer", _.getHasDepthBuffer(), _.setHasDepthBuffer(_), true)
  val HasStencilBuffer: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("has-stencil-buffer", _.getHasStencilBuffer(), _.setHasStencilBuffer(_), true)
  @deprecated("", "") val UseEs: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("use-es", _.getUseEs(), _.setUseEs(_), true)
  ()
  extension (v: GLArea) {
    def unwrap: org.gnome.gtk.GLArea = v
    export unwrap.onCreateContext, unwrap.onRender, unwrap.onResize
  }
  def init(v: GLArea): Unit = {
    Widget.init(v)
  }
  def uninitialized(): GLArea = {
    val res = new org.gnome.gtk.GLArea()
    res.asInstanceOf[GLArea]
  }
}