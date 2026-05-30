package guarana
package gtk
import util.*
opaque type Frame <: Widget = org.gnome.gtk.Frame & Widget
object Frame extends VarsMap {
  val Child: ExternalVar.Aux[Frame, org.gnome.gtk.Widget | Null] = ExternalVar[Frame, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Label: ExternalVar.Aux[Frame, java.lang.String | Null] = ExternalVar[Frame, java.lang.String | Null]("label", _.getLabel(), _.setLabel(_), true)
  val LabelAlign: ExternalVar.Aux[Frame, Float] = ExternalVar[Frame, Float]("label-align", _.getLabelAlign(), _.setLabelAlign(_), true)
  val LabelWidget: ExternalVar.Aux[Frame, org.gnome.gtk.Widget | Null] = ExternalVar[Frame, org.gnome.gtk.Widget | Null]("label-widget", _.getLabelWidget(), _.setLabelWidget(_), true)
  ()
  extension (v: Frame) {
    def unwrap: org.gnome.gtk.Frame = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def label: Var.Aux[java.lang.String | Null, v.type] = Label.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def labelAlign: Var.Aux[Float, v.type] = LabelAlign.asInstanceOf[Var.Aux[Float, v.type]]
    def labelWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = LabelWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
  }
  def _wrap(v: org.gnome.gtk.Frame): Frame = {
    v.asInstanceOf
  }
  def init(v: Frame): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(arg$0: java.lang.String | Null): Frame = {
    val res = new org.gnome.gtk.Frame(arg$0)
    res.asInstanceOf[Frame]
  }
  def apply(arg$0: java.lang.String | Null, canFocus: Opt[Binding[Boolean]] = UnsetParam, canTarget: Opt[Binding[Boolean]] = UnsetParam, child: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, childVisible: Opt[Binding[Boolean]] = UnsetParam, cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam, direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam, focusChild: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, focusOnClick: Opt[Binding[Boolean]] = UnsetParam, focusable: Opt[Binding[Boolean]] = UnsetParam, fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam, fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam, halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, hasTooltip: Opt[Binding[Boolean]] = UnsetParam, hexpand: Opt[Binding[Boolean]] = UnsetParam, hexpandSet: Opt[Binding[Boolean]] = UnsetParam, label: Opt[Binding[java.lang.String | Null]] = UnsetParam, labelAlign: Opt[Binding[Float]] = UnsetParam, labelWidget: Opt[Binding[org.gnome.gtk.Widget | Null]] = UnsetParam, layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam, limitEvents: Opt[Binding[Boolean]] = UnsetParam, marginBottom: Opt[Binding[Int]] = UnsetParam, marginEnd: Opt[Binding[Int]] = UnsetParam, marginStart: Opt[Binding[Int]] = UnsetParam, marginTop: Opt[Binding[Int]] = UnsetParam, name: Opt[Binding[java.lang.String]] = UnsetParam, opacity: Opt[Binding[Double]] = UnsetParam, overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam, receivesDefault: Opt[Binding[Boolean]] = UnsetParam, sensitive: Opt[Binding[Boolean]] = UnsetParam, tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam, tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam, valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam, vexpand: Opt[Binding[Boolean]] = UnsetParam, vexpandSet: Opt[Binding[Boolean]] = UnsetParam, visible: Opt[Binding[Boolean]] = UnsetParam): ToolkitAction[Toolkit, Frame] = {
    val res = uninitialized(arg$0)
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
    ifSet(label, res.label := _)
    ifSet(labelAlign, res.labelAlign := _)
    ifSet(labelWidget, res.labelWidget := _)
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
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    res
  }
}