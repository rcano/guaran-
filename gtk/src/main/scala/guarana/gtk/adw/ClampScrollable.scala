
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type ClampScrollable <: guarana.gtk.Widget  = org.gnome.adw.ClampScrollable & guarana.gtk.Widget
object ClampScrollable extends VarsMap {
  val Child: ExternalVar.Aux[ClampScrollable, guarana.gtk.Widget | Null] = ExternalVar[ClampScrollable, guarana.gtk.Widget | Null]("child", _.getChild().?(guarana.gtk.Widget.wrap), (n, v) => n.setChild(v.?(_.unwrap)), true)
  val Hadjustment: ExternalVar.Aux[ClampScrollable, org.gnome.gtk.Adjustment | Null] = ExternalVar[ClampScrollable, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  val HscrollPolicy: ExternalVar.Aux[ClampScrollable, org.gnome.gtk.ScrollablePolicy] = ExternalVar[ClampScrollable, org.gnome.gtk.ScrollablePolicy]("hscroll-policy", _.getHscrollPolicy(), _.setHscrollPolicy(_), true)
  val MaximumSize: ExternalVar.Aux[ClampScrollable, Int] = ExternalVar[ClampScrollable, Int]("maximum-size", _.getMaximumSize(), _.setMaximumSize(_), true)
  val Orientation: ExternalVar.Aux[ClampScrollable, org.gnome.gtk.Orientation] = ExternalVar[ClampScrollable, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val TighteningThreshold: ExternalVar.Aux[ClampScrollable, Int] = ExternalVar[ClampScrollable, Int]("tightening-threshold", _.getTighteningThreshold(), _.setTighteningThreshold(_), true)
  val Unit: ExternalVar.Aux[ClampScrollable, org.gnome.adw.LengthUnit] = ExternalVar[ClampScrollable, org.gnome.adw.LengthUnit]("unit", _.getUnit(), _.setUnit(_), true)
  val Vadjustment: ExternalVar.Aux[ClampScrollable, org.gnome.gtk.Adjustment | Null] = ExternalVar[ClampScrollable, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  val VscrollPolicy: ExternalVar.Aux[ClampScrollable, org.gnome.gtk.ScrollablePolicy] = ExternalVar[ClampScrollable, org.gnome.gtk.ScrollablePolicy]("vscroll-policy", _.getVscrollPolicy(), _.setVscrollPolicy(_), true)

  

  extension (v: ClampScrollable) {
    def unwrap: org.gnome.adw.ClampScrollable = v

    def child: Var.Aux[guarana.gtk.Widget | Null, v.type] = guarana.gtk.adw.ClampScrollable.Child.asInstanceOf[Var.Aux[guarana.gtk.Widget | Null, v.type]]
    def hadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = guarana.gtk.adw.ClampScrollable.Hadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def hscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = guarana.gtk.adw.ClampScrollable.HscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    def maximumSize: Var.Aux[Int, v.type] = guarana.gtk.adw.ClampScrollable.MaximumSize.asInstanceOf[Var.Aux[Int, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.adw.ClampScrollable.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def tighteningThreshold: Var.Aux[Int, v.type] = guarana.gtk.adw.ClampScrollable.TighteningThreshold.asInstanceOf[Var.Aux[Int, v.type]]
    def unit: Var.Aux[org.gnome.adw.LengthUnit, v.type] = guarana.gtk.adw.ClampScrollable.Unit.asInstanceOf[Var.Aux[org.gnome.adw.LengthUnit, v.type]]
    def vadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = guarana.gtk.adw.ClampScrollable.Vadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def vscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = guarana.gtk.adw.ClampScrollable.VscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]

    

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

  def wrap(v: org.gnome.adw.ClampScrollable): ClampScrollable = 
    val res = v.asInstanceOf[ClampScrollable]
    
    res

  def init(v: ClampScrollable): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): ClampScrollable = {
    val res = {
      val res = org.gnome.adw.ClampScrollable.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[ClampScrollable]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    child: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    hadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    hscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    maximumSize: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    parent: Opt[Binding[Widget | Null]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tighteningThreshold: Opt[Binding[Int]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    unit: Opt[Binding[org.gnome.adw.LengthUnit]] = UnsetParam,
    vadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    vscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam
  ): VarContextAction[ClampScrollable] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.ClampScrollable.init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(hadjustment, res.hadjustment := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(hscrollPolicy, res.hscrollPolicy := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(maximumSize, res.maximumSize := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(parent, res.parent := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tighteningThreshold, res.tighteningThreshold := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(unit, res.unit := _)
    ifSet(vadjustment, res.vadjustment := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(vscrollPolicy, res.vscrollPolicy := _)
    
    res
  }
  
}
        