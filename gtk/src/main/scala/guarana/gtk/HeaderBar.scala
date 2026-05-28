package guarana
package gtk
import util.*
opaque type HeaderBar <: Widget = org.gnome.gtk.HeaderBar & Widget
object HeaderBar {
  val DecorationLayout: ExternalVar.Aux[HeaderBar, java.lang.String | Null] = ExternalVar[HeaderBar, java.lang.String | Null]("decoration-layout", _.getDecorationLayout(), _.setDecorationLayout(_), true)
  val ShowTitleButtons: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("show-title-buttons", _.getShowTitleButtons(), _.setShowTitleButtons(_), true)
  val TitleWidget: ExternalVar.Aux[HeaderBar, org.gnome.gtk.Widget | Null] = ExternalVar[HeaderBar, org.gnome.gtk.Widget | Null]("title-widget", _.getTitleWidget(), _.setTitleWidget(_), true)
  val UseNativeControls: ExternalVar.Aux[HeaderBar, Boolean] = ExternalVar[HeaderBar, Boolean]("use-native-controls", _.getUseNativeControls(), _.setUseNativeControls(_), true)
  ()
  extension (v: HeaderBar) {
    def unwrap: org.gnome.gtk.HeaderBar = v
    def decorationLayout: Var.Aux[java.lang.String | Null, v.type] = DecorationLayout.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def showTitleButtons: Var.Aux[Boolean, v.type] = ShowTitleButtons.asInstanceOf[Var.Aux[Boolean, v.type]]
    def titleWidget: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = TitleWidget.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def useNativeControls: Var.Aux[Boolean, v.type] = UseNativeControls.asInstanceOf[Var.Aux[Boolean, v.type]]
  }
  def init(v: HeaderBar): Unit = {
    Widget.init(v)
  }
  def uninitialized(): HeaderBar = {
    val res = new org.gnome.gtk.HeaderBar()
    res.asInstanceOf[HeaderBar]
  }
  def apply(decorationLayout: Opt[java.lang.String | Null] = UnsetParam, showTitleButtons: Opt[Boolean] = UnsetParam, titleWidget: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, useNativeControls: Opt[Boolean] = UnsetParam): VarContextAction[HeaderBar] = {
    val res = uninitialized()
    init(res)
    ifSet(decorationLayout, res.decorationLayout := _)
    ifSet(showTitleButtons, res.showTitleButtons := _)
    ifSet(titleWidget, res.titleWidget := _)
    ifSet(useNativeControls, res.useNativeControls := _)
    res
  }
}