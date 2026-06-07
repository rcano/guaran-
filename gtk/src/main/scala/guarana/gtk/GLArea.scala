
package guarana
package gtk

import guarana.util.*

opaque type GLArea <: guarana.gtk.Widget  = org.gnome.gtk.GLArea & guarana.gtk.Widget
object GLArea extends VarsMap {
  val AllowedApis: ExternalVar.Aux[GLArea, java.util.Set[org.gnome.gdk.GLAPI]] = ExternalVar[GLArea, java.util.Set[org.gnome.gdk.GLAPI]]("allowed-apis", _.getAllowedApis(), _.setAllowedApis(_), true)
  val AutoRender: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("auto-render", _.getAutoRender(), _.setAutoRender(_), true)
  val Error: ExternalVar.Aux[GLArea, org.gnome.glib.GError | Null] = ExternalVar[GLArea, org.gnome.glib.GError | Null]("error", _.getError(), _.setError(_), true)
  val HasDepthBuffer: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("has-depth-buffer", _.getHasDepthBuffer(), _.setHasDepthBuffer(_), true)
  val HasStencilBuffer: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("has-stencil-buffer", _.getHasStencilBuffer(), _.setHasStencilBuffer(_), true)
  @deprecated("", "") val UseEs: ExternalVar.Aux[GLArea, Boolean] = ExternalVar[GLArea, Boolean]("use-es", _.getUseEs(), _.setUseEs(_), true)

  

  extension (v: GLArea) {
    def unwrap: org.gnome.gtk.GLArea = v

    def allowedApis: Var.Aux[java.util.Set[org.gnome.gdk.GLAPI], v.type] = guarana.gtk.GLArea.AllowedApis.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gdk.GLAPI], v.type]]
    def autoRender: Var.Aux[Boolean, v.type] = guarana.gtk.GLArea.AutoRender.asInstanceOf[Var.Aux[Boolean, v.type]]
    def error: Var.Aux[org.gnome.glib.GError | Null, v.type] = guarana.gtk.GLArea.Error.asInstanceOf[Var.Aux[org.gnome.glib.GError | Null, v.type]]
    def hasDepthBuffer: Var.Aux[Boolean, v.type] = guarana.gtk.GLArea.HasDepthBuffer.asInstanceOf[Var.Aux[Boolean, v.type]]
    def hasStencilBuffer: Var.Aux[Boolean, v.type] = guarana.gtk.GLArea.HasStencilBuffer.asInstanceOf[Var.Aux[Boolean, v.type]]
    @deprecated("", "") def useEs: Var.Aux[Boolean, v.type] = guarana.gtk.GLArea.UseEs.asInstanceOf[Var.Aux[Boolean, v.type]]

    

    export unwrap.{
      onCreateContext,
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
      onRender,
      onResize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.GLArea): GLArea = 
    val res = v.asInstanceOf[GLArea]
    
    res

  def init(v: GLArea): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): GLArea = {
    val res = org.gnome.gtk.GLArea.builder()
    ifSet(cssName, v => res.setCssName(v))
    ifSet(heightRequest, v => res.setHeightRequest(v))
    ifSet(widthRequest, v => res.setWidthRequest(v))
    ifSet(accessibleRole, v => res.setAccessibleRole(v))
    
    res.asInstanceOf[GLArea]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    allowedApis: Opt[Binding[java.util.Set[org.gnome.gdk.GLAPI]]] = UnsetParam,
    autoRender: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    error: Opt[Binding[org.gnome.glib.GError | Null]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasDepthBuffer: Opt[Binding[Boolean]] = UnsetParam,
    hasStencilBuffer: Opt[Binding[Boolean]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    useEs: Opt[Binding[Boolean]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[GLArea] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.GLArea.init(res)
    ifSet(allowedApis, res.allowedApis := _)
    ifSet(autoRender, res.autoRender := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
        