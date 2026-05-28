package guarana
package gtk
import util.*
opaque type GLArea <: Widget = org.gnome.gtk.GLArea & Widget
object GLArea extends VarsMap {
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
  def _wrap(v: org.gnome.gtk.GLArea): GLArea = {
    v.asInstanceOf
  }
  def init(v: GLArea): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): GLArea = {
    val res = new org.gnome.gtk.GLArea()
    res.asInstanceOf[GLArea]
  }
  def apply(allowedApis: Opt[java.util.Set[org.gnome.gdk.GLAPI]] = UnsetParam, autoRender: Opt[Boolean] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, error: Opt[org.gnome.glib.GError | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasDepthBuffer: Opt[Boolean] = UnsetParam, hasStencilBuffer: Opt[Boolean] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, useEs: Opt[Boolean] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam): ToolkitAction[Toolkit, GLArea] = {
    val res = uninitialized()
    init(res)
    ifSet(allowedApis, res.allowedApis := _)
    ifSet(autoRender, res.autoRender := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(error, res.error := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasDepthBuffer, res.hasDepthBuffer := _)
    ifSet(hasStencilBuffer, res.hasStencilBuffer := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(useEs, res.useEs := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}