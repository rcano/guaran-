package guarana
package gtk
import util.*
opaque type Expander <: Widget = org.gnome.gtk.Expander & Widget
object Expander {
  val Child: ExternalVar.Aux[Expander, org.gnome.gtk.Widget | Null] = ExternalVar[Expander, org.gnome.gtk.Widget | Null]("child", _.getChild(), _.setChild(_), true)
  val Expanded: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("expanded", _.getExpanded(), _.setExpanded(_), true)
  val Label: ExternalVar.Aux[Expander, java.lang.String | Null] = ExternalVar[Expander, java.lang.String | Null]("label", _.getLabel(), _.setLabel(_), true)
  val LabelWidget: ExternalVar.Aux[Expander, org.gnome.gtk.Widget | Null] = ExternalVar[Expander, org.gnome.gtk.Widget | Null]("label-widget", _.getLabelWidget(), _.setLabelWidget(_), true)
  val ResizeToplevel: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("resize-toplevel", _.getResizeToplevel(), _.setResizeToplevel(_), true)
  val UseMarkup: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("use-markup", _.getUseMarkup(), _.setUseMarkup(_), true)
  val UseUnderline: ExternalVar.Aux[Expander, Boolean] = ExternalVar[Expander, Boolean]("use-underline", _.getUseUnderline(), _.setUseUnderline(_), true)
  ()
  extension (v: Expander) {
    def unwrap: org.gnome.gtk.Expander = v
    def child: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = Child.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def expanded: Var.Aux[Boolean, v.type] = Expanded.asInstanceOf[Var.Aux[Boolean, v.type]]
    def label: Var.Aux[java.lang.String | Null, v.type] = Label.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def labelWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = LabelWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def resizeToplevel: Var.Aux[Boolean, v.type] = ResizeToplevel.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useMarkup: Var.Aux[Boolean, v.type] = UseMarkup.asInstanceOf[Var.Aux[Boolean, v.type]]
    def useUnderline: Var.Aux[Boolean, v.type] = UseUnderline.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onActivate
  }
  def init(v: Expander): Unit = {
    Widget.init(v)
  }
  def uninitialized(): Expander = {
    val res = new org.gnome.gtk.Expander()
    res.asInstanceOf[Expander]
  }
  def apply(child: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, expanded: Opt[Boolean] = UnsetParam, label: Opt[java.lang.String | Null] = UnsetParam, labelWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, resizeToplevel: Opt[Boolean] = UnsetParam, useMarkup: Opt[Boolean] = UnsetParam, useUnderline: Opt[Boolean] = UnsetParam): VarContextAction[Expander] = {
    val res = uninitialized()
    init(res)
    ifSet(child, res.child := _)
    ifSet(expanded, res.expanded := _)
    ifSet(label, res.label := _)
    ifSet(labelWidget, res.labelWidget := _)
    ifSet(resizeToplevel, res.resizeToplevel := _)
    ifSet(useMarkup, res.useMarkup := _)
    ifSet(useUnderline, res.useUnderline := _)
    res
  }
}