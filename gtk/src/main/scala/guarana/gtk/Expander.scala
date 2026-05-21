package guarana
package gtk
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
    export unwrap.onActivate
  }
}