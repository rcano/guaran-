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
  def apply(arg$0: Float, arg$1: Float, arg$2: Float, arg$3: Boolean, canFocus: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, child: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, obeyChild: Opt[Binding[Boolean]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, ratio: Opt[Binding[Float]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam, xalign: Opt[Binding[Float]] = UnsetParam, yalign: Opt[Binding[Float]] = UnsetParam): ToolkitAction[Toolkit, AspectFrame] = {
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