package guarana
package gtk
import util.*
opaque type GLArea <: Widget = org.gnome.gtk.GLArea & Widget
object GLArea {
  val AllowedApis: ExternalVar.Aux[GLArea, java.util.Set[org.gnome.gdk.GLAPI]] = ExternalVar[GLArea, java.util.Set[org.gnome.gdk.GLAPI]]("allowed-apis", _.getAllowedApis(), _.setAllowedApis(_), true)
  val AutoRender: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("auto-render", _.getAutoRender(), _.setAutoRender(_), true)
  val Error: ExternalVar.Aux[GLArea, org.gnome.glib.GError | Null] = ExternalVar[GLArea, org.gnome.glib.GError | Null]("error", _.getError(), _.setError(_), true)
  val HasDepthBuffer: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("has-depth-buffer", _.getHasDepthBuffer(), _.setHasDepthBuffer(_), true)
  val HasStencilBuffer: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("has-stencil-buffer", _.getHasStencilBuffer(), _.setHasStencilBuffer(_), true)
  @deprecated("", "") val UseEs: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("use-es", _.getUseEs(), _.setUseEs(_), true)
  ()
  extension (v: GLArea) {
    def unwrap: org.gnome.gtk.GLArea = v
    def allowedApis: Var.Aux[java.util.Set[org.gnome.gdk.GLAPI], v.type] = AllowedApis.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gdk.GLAPI], v.type]]
    def autoRender: Var.Aux[Boolean, v.type] = AutoRender.asInstanceOf[Var.Aux[Boolean, v.type]]
    def error: Var.Aux[org.gnome.glib.GError | Null, v.type] = Error.asInstanceOf[Var.Aux[org.gnome.glib.GError | Null, v.type]]
    def hasDepthBuffer: Var.Aux[Boolean, v.type] = HasDepthBuffer.asInstanceOf[Var.Aux[Boolean, v.type]]
    def hasStencilBuffer: Var.Aux[Boolean, v.type] = HasStencilBuffer.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def useEs: Var.Aux[Boolean, v.type] = UseEs.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onCreateContext, unwrap.onRender, unwrap.onResize
  }
  def init(v: GLArea): Unit = {
    Widget.init(v)
  }
  def uninitialized(): GLArea = {
    val res = new org.gnome.gtk.GLArea()
    res.asInstanceOf[GLArea]
  }
  def apply(allowedApis: Opt[java.util.Set[org.gnome.gdk.GLAPI]] = UnsetParam, autoRender: Opt[Boolean] = UnsetParam, error: Opt[org.gnome.glib.GError | Null] = UnsetParam, hasDepthBuffer: Opt[Boolean] = UnsetParam, hasStencilBuffer: Opt[Boolean] = UnsetParam, useEs: Opt[Boolean] = UnsetParam): VarContextAction[GLArea] = {
    val res = uninitialized()
    init(res)
    ifSet(allowedApis, res.allowedApis := _)
    ifSet(autoRender, res.autoRender := _)
    ifSet(error, res.error := _)
    ifSet(hasDepthBuffer, res.hasDepthBuffer := _)
    ifSet(hasStencilBuffer, res.hasStencilBuffer := _)
    ifSet(useEs, res.useEs := _)
    res
  }
}