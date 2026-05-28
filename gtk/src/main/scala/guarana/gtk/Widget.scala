package guarana
package gtk
import util.*
opaque type Widget >: org.gnome.gtk.Widget = org.gnome.gtk.Widget
object Widget extends VarsMap {
  val CanFocus: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("can-focus", _.getCanFocus(), _.setCanFocus(_), true)
  val CanTarget: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("can-target", _.getCanTarget(), _.setCanTarget(_), true)
  val ChildVisible: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("child-visible", _.getChildVisible(), _.setChildVisible(_), true)
  val Cursor: ExternalVar.Aux[Widget, org.gnome.gdk.Cursor | Null] = ExternalVar[Widget, org.gnome.gdk.Cursor | Null]("cursor", _.getCursor(), _.setCursor(_), true)
  val Direction: ExternalVar.Aux[Widget, org.gnome.gtk.TextDirection] = ExternalVar[Widget, org.gnome.gtk.TextDirection]("direction", _.getDirection(), _.setDirection(_), true)
  val FocusChild: ExternalVar.Aux[Widget, org.gnome.gtk.Widget | Null] = ExternalVar[Widget, org.gnome.gtk.Widget | Null]("focus-child", _.getFocusChild(), _.setFocusChild(_), true)
  val FocusOnClick: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("focus-on-click", _.getFocusOnClick(), _.setFocusOnClick(_), true)
  val Focusable: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("focusable", _.getFocusable(), _.setFocusable(_), true)
  val FontMap: ExternalVar.Aux[Widget, org.gnome.pango.FontMap | Null] = ExternalVar[Widget, org.gnome.pango.FontMap | Null]("font-map", _.getFontMap(), _.setFontMap(_), true)
  @deprecated("", "") val FontOptions: ExternalVar.Aux[Widget, org.freedesktop.cairo.FontOptions | Null] = ExternalVar[Widget, org.freedesktop.cairo.FontOptions | Null]("font-options", _.getFontOptions(), _.setFontOptions(_), true)
  val Halign: ExternalVar.Aux[Widget, org.gnome.gtk.Align] = ExternalVar[Widget, org.gnome.gtk.Align]("halign", _.getHalign(), _.setHalign(_), true)
  val HasTooltip: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("has-tooltip", _.getHasTooltip(), _.setHasTooltip(_), true)
  val Hexpand: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("hexpand", _.getHexpand(), _.setHexpand(_), true)
  val HexpandSet: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("hexpand-set", _.getHexpandSet(), _.setHexpandSet(_), true)
  val LayoutManager: ExternalVar.Aux[Widget, org.gnome.gtk.LayoutManager | Null] = ExternalVar[Widget, org.gnome.gtk.LayoutManager | Null]("layout-manager", _.getLayoutManager(), _.setLayoutManager(_), true)
  val LimitEvents: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("limit-events", _.getLimitEvents(), _.setLimitEvents(_), true)
  val MarginBottom: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("margin-bottom", _.getMarginBottom(), _.setMarginBottom(_), true)
  val MarginEnd: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("margin-end", _.getMarginEnd(), _.setMarginEnd(_), true)
  val MarginStart: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("margin-start", _.getMarginStart(), _.setMarginStart(_), true)
  val MarginTop: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("margin-top", _.getMarginTop(), _.setMarginTop(_), true)
  val Name: ExternalVar.Aux[Widget, java.lang.String] = ExternalVar[Widget, java.lang.String]("name", _.getName(), _.setName(_), true)
  val Opacity: ExternalVar.Aux[Widget, Double] = ExternalVar[Widget, Double]("opacity", _.getOpacity(), _.setOpacity(_), true)
  val Overflow: ExternalVar.Aux[Widget, org.gnome.gtk.Overflow] = ExternalVar[Widget, org.gnome.gtk.Overflow]("overflow", _.getOverflow(), _.setOverflow(_), true)
  val ReceivesDefault: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("receives-default", _.getReceivesDefault(), _.setReceivesDefault(_), true)
  val Sensitive: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("sensitive", _.getSensitive(), _.setSensitive(_), true)
  val TooltipMarkup: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("tooltip-markup", _.getTooltipMarkup(), _.setTooltipMarkup(_), true)
  val TooltipText: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("tooltip-text", _.getTooltipText(), _.setTooltipText(_), true)
  val Valign: ExternalVar.Aux[Widget, org.gnome.gtk.Align] = ExternalVar[Widget, org.gnome.gtk.Align]("valign", _.getValign(), _.setValign(_), true)
  val Vexpand: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("vexpand", _.getVexpand(), _.setVexpand(_), true)
  val VexpandSet: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("vexpand-set", _.getVexpandSet(), _.setVexpandSet(_), true)
  val Visible: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("visible", _.getVisible(), _.setVisible(_), true)
  ()
  extension (v: Widget) {
    def unwrap: org.gnome.gtk.Widget = v
    def canFocus: Var.Aux[Boolean, v.type] = CanFocus.asInstanceOf[Var.Aux[Boolean, v.type]]
    def canTarget: Var.Aux[Boolean, v.type] = CanTarget.asInstanceOf[Var.Aux[Boolean, v.type]]
    def childVisible: Var.Aux[Boolean, v.type] = ChildVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    def cursor: Var.Aux[org.gnome.gdk.Cursor | Null, v.type] = Cursor.asInstanceOf[Var.Aux[org.gnome.gdk.Cursor | Null, v.type]]
    def direction: Var.Aux[org.gnome.gtk.TextDirection, v.type] = Direction.asInstanceOf[Var.Aux[org.gnome.gtk.TextDirection, v.type]]
    def focusChild: Var.Aux[org.gnome.gtk.Widget | Null, v.type] = FocusChild.asInstanceOf[Var.Aux[org.gnome.gtk.Widget | Null, v.type]]
    def focusOnClick: Var.Aux[Boolean, v.type] = FocusOnClick.asInstanceOf[Var.Aux[Boolean, v.type]]
    def focusable: Var.Aux[Boolean, v.type] = Focusable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def fontMap: Var.Aux[org.gnome.pango.FontMap | Null, v.type] = FontMap.asInstanceOf[Var.Aux[org.gnome.pango.FontMap | Null, v.type]]
    @deprecated("", "") def fontOptions: Var.Aux[org.freedesktop.cairo.FontOptions | Null, v.type] = FontOptions.asInstanceOf[Var.Aux[org.freedesktop.cairo.FontOptions | Null, v.type]]
    def halign: Var.Aux[org.gnome.gtk.Align, v.type] = Halign.asInstanceOf[Var.Aux[org.gnome.gtk.Align, v.type]]
    def hasTooltip: Var.Aux[Boolean, v.type] = HasTooltip.asInstanceOf[Var.Aux[Boolean, v.type]]
    def hexpand: Var.Aux[Boolean, v.type] = Hexpand.asInstanceOf[Var.Aux[Boolean, v.type]]
    def hexpandSet: Var.Aux[Boolean, v.type] = HexpandSet.asInstanceOf[Var.Aux[Boolean, v.type]]
    def layoutManager: Var.Aux[org.gnome.gtk.LayoutManager | Null, v.type] = LayoutManager.asInstanceOf[Var.Aux[org.gnome.gtk.LayoutManager | Null, v.type]]
    def limitEvents: Var.Aux[Boolean, v.type] = LimitEvents.asInstanceOf[Var.Aux[Boolean, v.type]]
    def marginBottom: Var.Aux[Int, v.type] = MarginBottom.asInstanceOf[Var.Aux[Int, v.type]]
    def marginEnd: Var.Aux[Int, v.type] = MarginEnd.asInstanceOf[Var.Aux[Int, v.type]]
    def marginStart: Var.Aux[Int, v.type] = MarginStart.asInstanceOf[Var.Aux[Int, v.type]]
    def marginTop: Var.Aux[Int, v.type] = MarginTop.asInstanceOf[Var.Aux[Int, v.type]]
    def name: Var.Aux[java.lang.String, v.type] = Name.asInstanceOf[Var.Aux[java.lang.String, v.type]]
    def opacity: Var.Aux[Double, v.type] = Opacity.asInstanceOf[Var.Aux[Double, v.type]]
    def overflow: Var.Aux[org.gnome.gtk.Overflow, v.type] = Overflow.asInstanceOf[Var.Aux[org.gnome.gtk.Overflow, v.type]]
    def receivesDefault: Var.Aux[Boolean, v.type] = ReceivesDefault.asInstanceOf[Var.Aux[Boolean, v.type]]
    def sensitive: Var.Aux[Boolean, v.type] = Sensitive.asInstanceOf[Var.Aux[Boolean, v.type]]
    def tooltipMarkup: Var.Aux[java.lang.String | Null, v.type] = TooltipMarkup.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def tooltipText: Var.Aux[java.lang.String | Null, v.type] = TooltipText.asInstanceOf[Var.Aux[java.lang.String | Null, v.type]]
    def valign: Var.Aux[org.gnome.gtk.Align, v.type] = Valign.asInstanceOf[Var.Aux[org.gnome.gtk.Align, v.type]]
    def vexpand: Var.Aux[Boolean, v.type] = Vexpand.asInstanceOf[Var.Aux[Boolean, v.type]]
    def vexpandSet: Var.Aux[Boolean, v.type] = VexpandSet.asInstanceOf[Var.Aux[Boolean, v.type]]
    def visible: Var.Aux[Boolean, v.type] = Visible.asInstanceOf[Var.Aux[Boolean, v.type]]
    export unwrap.onDestroy, unwrap.onDirectionChanged, unwrap.onHide, unwrap.onKeynavFailed, unwrap.onMap, unwrap.onMnemonicActivate, unwrap.onMoveFocus, unwrap.onNotify, unwrap.onQueryTooltip, unwrap.onRealize, unwrap.onShow, unwrap.onStateFlagsChanged, unwrap.onUnmap, unwrap.onUnrealize
  }
  def _wrap(v: org.gnome.gtk.Widget): Widget = {
    v.asInstanceOf
  }
  def init(v: Widget): ToolkitAction[Toolkit, Unit] = {}
}