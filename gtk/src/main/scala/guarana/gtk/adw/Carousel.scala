
package guarana.gtk.adw

import guarana.*
import guarana.gtk.*
import guarana.util.*

opaque type Carousel <: guarana.gtk.Widget  = org.gnome.adw.Carousel & guarana.gtk.Widget
object Carousel extends VarsMap {
  val AllowLongSwipes: ExternalVar.Aux[Carousel, Boolean] = ExternalVar[Carousel, Boolean]("allow-long-swipes", _.getAllowLongSwipes(), _.setAllowLongSwipes(_), true)
  val AllowMouseDrag: ExternalVar.Aux[Carousel, Boolean] = ExternalVar[Carousel, Boolean]("allow-mouse-drag", _.getAllowMouseDrag(), _.setAllowMouseDrag(_), true)
  val AllowScrollWheel: ExternalVar.Aux[Carousel, Boolean] = ExternalVar[Carousel, Boolean]("allow-scroll-wheel", _.getAllowScrollWheel(), _.setAllowScrollWheel(_), true)
  val Interactive: ExternalVar.Aux[Carousel, Boolean] = ExternalVar[Carousel, Boolean]("interactive", _.getInteractive(), _.setInteractive(_), true)
  val Orientation: ExternalVar.Aux[Carousel, org.gnome.gtk.Orientation] = ExternalVar[Carousel, org.gnome.gtk.Orientation]("orientation", _.getOrientation(), _.setOrientation(_), true)
  val RevealDuration: ExternalVar.Aux[Carousel, Int] = ExternalVar[Carousel, Int]("reveal-duration", _.getRevealDuration(), _.setRevealDuration(_), true)
  val ScrollParams: ExternalVar.Aux[Carousel, org.gnome.adw.SpringParams] = ExternalVar[Carousel, org.gnome.adw.SpringParams]("scroll-params", _.getScrollParams(), _.setScrollParams(_), true)
  val Spacing: ExternalVar.Aux[Carousel, Int] = ExternalVar[Carousel, Int]("spacing", _.getSpacing(), _.setSpacing(_), true)

  

  extension (v: Carousel) {
    def unwrap: org.gnome.adw.Carousel = v

    def allowLongSwipes: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Carousel.AllowLongSwipes.asInstanceOf[Var.Aux[Boolean, v.type]]
    def allowMouseDrag: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Carousel.AllowMouseDrag.asInstanceOf[Var.Aux[Boolean, v.type]]
    def allowScrollWheel: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Carousel.AllowScrollWheel.asInstanceOf[Var.Aux[Boolean, v.type]]
    def interactive: Var.Aux[Boolean, v.type] = guarana.gtk.adw.Carousel.Interactive.asInstanceOf[Var.Aux[Boolean, v.type]]
    def orientation: Var.Aux[org.gnome.gtk.Orientation, v.type] = guarana.gtk.adw.Carousel.Orientation.asInstanceOf[Var.Aux[org.gnome.gtk.Orientation, v.type]]
    def revealDuration: Var.Aux[Int, v.type] = guarana.gtk.adw.Carousel.RevealDuration.asInstanceOf[Var.Aux[Int, v.type]]
    def scrollParams: Var.Aux[org.gnome.adw.SpringParams, v.type] = guarana.gtk.adw.Carousel.ScrollParams.asInstanceOf[Var.Aux[org.gnome.adw.SpringParams, v.type]]
    def spacing: Var.Aux[Int, v.type] = guarana.gtk.adw.Carousel.Spacing.asInstanceOf[Var.Aux[Int, v.type]]

    

    export unwrap.{
      onDestroy,
      onDirectionChanged,
      onHide,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveFocus,
      onNotify,
      onPageChanged,
      onQueryTooltip,
      onRealize,
      onShow,
      onStateFlagsChanged,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.adw.Carousel): Carousel = 
    val res = v.asInstanceOf[Carousel]
    
    res

  def init(v: Carousel): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(cssName: Opt[java.lang.String], heightRequest: Opt[Int], widthRequest: Opt[Int], accessibleRole: Opt[org.gnome.gtk.AccessibleRole]): Carousel = {
    val res = {
      val res = org.gnome.adw.Carousel.builder()
      ifSet(cssName, v => res.setCssName(v))
      ifSet(heightRequest, v => res.setHeightRequest(v))
      ifSet(widthRequest, v => res.setWidthRequest(v))
      ifSet(accessibleRole, v => res.setAccessibleRole(v))
      res.build()
    }
    
    res.asInstanceOf[Carousel]
  }
  
  def apply(
    cssName: Opt[java.lang.String] = UnsetParam, heightRequest: Opt[Int] = UnsetParam, widthRequest: Opt[Int] = UnsetParam, accessibleRole: Opt[org.gnome.gtk.AccessibleRole] = UnsetParam,
    allowLongSwipes: Opt[Binding[Boolean]] = UnsetParam,
    allowMouseDrag: Opt[Binding[Boolean]] = UnsetParam,
    allowScrollWheel: Opt[Binding[Boolean]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    interactive: Opt[Binding[Boolean]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    orientation: Opt[Binding[org.gnome.gtk.Orientation]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    revealDuration: Opt[Binding[Int]] = UnsetParam,
    scrollParams: Opt[Binding[org.gnome.adw.SpringParams]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    spacing: Opt[Binding[Int]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam
  ): VarContextAction[Carousel] = {
    val res = uninitialized(cssName, heightRequest, widthRequest, accessibleRole)
    guarana.gtk.adw.Carousel.init(res)
    ifSet(allowLongSwipes, res.allowLongSwipes := _)
    ifSet(allowMouseDrag, res.allowMouseDrag := _)
    ifSet(allowScrollWheel, res.allowScrollWheel := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
    ifSet(cursor, res.cursor := _)
    ifSet(direction, res.direction := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(interactive, res.interactive := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(orientation, res.orientation := _)
    ifSet(overflow, res.overflow := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(revealDuration, res.revealDuration := _)
    ifSet(scrollParams, res.scrollParams := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(spacing, res.spacing := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    
    res
  }
  
}
        