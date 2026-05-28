package guarana
package gtk
import util.*
opaque type AspectFrame <: Widget = org.gnome.gtk.AspectFrame & Widget
object AspectFrame extends VarsMap {
  val Child: ExternalVar.Aux[AspectFrame, org.gnome.gtk.Widget | Null] = ExternalVar[AspectFrame, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val ObeyChild: ExternalVar.Aux[AspectFrame, Boolean] = ExternalVar[AspectFrame, Boolean]("obey-child", _.getObeyChild(), _.setObeyChild(_), true)
  val Ratio: ExternalVar.Aux[AspectFrame, Float] = ExternalVar[AspectFrame, Float]("ratio", _.getRatio(), _.setRatio(_), true)
  val Xalign: ExternalVar.Aux[AspectFrame, Float] = ExternalVar[AspectFrame, Float]("xalign", _.getXalign(), _.setXalign(_), true)
  val Yalign: ExternalVar.Aux[AspectFrame, Float] = ExternalVar[AspectFrame, Float]("yalign", _.getYalign(), _.setYalign(_), true)
  ()
  extension (v: AspectFrame) {
    def unwrap: org.gnome.gtk.AspectFrame = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def obeyChild: Var.Aux[Boolean, v.type] = ObeyChild.asInstanceOf[Var.Aux[Boolean, v.type]]
    def ratio: Var.Aux[Float, v.type] = Ratio.asInstanceOf[Var.Aux[Float, v.type]]
    def xalign: Var.Aux[Float, v.type] = Xalign.asInstanceOf[Var.Aux[Float, v.type]]
    def yalign: Var.Aux[Float, v.type] = Yalign.asInstanceOf[Var.Aux[Float, v.type]]
  }
  def _wrap(v: org.gnome.gtk.AspectFrame): AspectFrame = {
    v.asInstanceOf
  }
  def init(v: AspectFrame): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: Float, arg$1: Float, arg$2: Float, arg$3: Boolean): AspectFrame = {
    val res = new org.gnome.gtk.AspectFrame(arg$0, arg$1, arg$2, arg$3)
    res.asInstanceOf[AspectFrame]
  }
  def apply(arg$0: Float, arg$1: Float, arg$2: Float, arg$3: Boolean, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, obeyChild: Opt[Boolean] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, ratio: Opt[Float] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, xalign: Opt[Float] = UnsetParam, yalign: Opt[Float] = UnsetParam): ToolkitAction[Toolkit, AspectFrame] = {
    val res = uninitialized(arg$0, arg$1, arg$2, arg$3)
    init(res)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(child, res.child := _)
    ifSet(childVisible, res.childVisible := _)
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
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(name, res.name := _)
    ifSet(obeyChild, res.obeyChild := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(ratio, res.ratio := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(xalign, res.xalign := _)
    ifSet(yalign, res.yalign := _)
    res
  }
}