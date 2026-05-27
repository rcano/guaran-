package guarana
package gtk
opaque type Widget >: org.gnome.gtk.Widget = org.gnome.gtk.Widget
object Widget {
  val CanFocus: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("can-focus", _.getCanFocus(), _.setCanFocus(_), true)
  val CanTarget: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("can-target", _.getCanTarget(), _.setCanTarget(_), true)
  val FocusOnClick: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("focus-on-click", _.getFocusOnClick(), _.setFocusOnClick(_), true)
  val Focusable: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("focusable", _.getFocusable(), _.setFocusable(_), true)
  val Halign: ExternalVar.Aux[Widget, org.gnome.gtk.Align | Null] = ExternalVar[Widget, org.gnome.gtk.Align | Null]("halign", _.getHalign(), _.setHalign(_), true)
  val HasTooltip: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("has-tooltip", _.getHasTooltip(), _.setHasTooltip(_), true)
  val Hexpand: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("hexpand", _.getHexpand(), _.setHexpand(_), true)
  val HexpandSet: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("hexpand-set", _.getHexpandSet(), _.setHexpandSet(_), true)
  val LimitEvents: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("limit-events", _.getLimitEvents(), _.setLimitEvents(_), true)
  val MarginBottom: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("margin-bottom", _.getMarginBottom(), _.setMarginBottom(_), true)
  val MarginEnd: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("margin-end", _.getMarginEnd(), _.setMarginEnd(_), true)
  val MarginStart: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("margin-start", _.getMarginStart(), _.setMarginStart(_), true)
  val MarginTop: ExternalVar.Aux[Widget, Int] = ExternalVar[Widget, Int]("margin-top", _.getMarginTop(), _.setMarginTop(_), true)
  val Name: ExternalVar.Aux[Widget, java.lang.String | Null] = ExternalVar[Widget, java.lang.String | Null]("name", _.getName(), _.setName(_), true)
  val Opacity: ExternalVar.Aux[Widget, Double] = ExternalVar[Widget, Double]("opacity", _.getOpacity(), _.setOpacity(_), true)
  val Overflow: ExternalVar.Aux[Widget, org.gnome.gtk.Overflow | Null] = ExternalVar[Widget, org.gnome.gtk.Overflow | Null]("overflow", _.getOverflow(), _.setOverflow(_), true)
  val ReceivesDefault: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("receives-default", _.getReceivesDefault(), _.setReceivesDefault(_), true)
  val Sensitive: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("sensitive", _.getSensitive(), _.setSensitive(_), true)
  val Valign: ExternalVar.Aux[Widget, org.gnome.gtk.Align | Null] = ExternalVar[Widget, org.gnome.gtk.Align | Null]("valign", _.getValign(), _.setValign(_), true)
  val Vexpand: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("vexpand", _.getVexpand(), _.setVexpand(_), true)
  val VexpandSet: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("vexpand-set", _.getVexpandSet(), _.setVexpandSet(_), true)
  val Visible: ExternalVar.Aux[Widget, Boolean] = ExternalVar[Widget, Boolean]("visible", _.getVisible(), _.setVisible(_), true)
  ()
  extension (v: Widget) {
    def unwrap: org.gnome.gtk.Widget = v
    export unwrap.onDestroy, unwrap.onDirectionChanged, unwrap.onHide, unwrap.onKeynavFailed, unwrap.onMap, unwrap.onMnemonicActivate, unwrap.onMoveFocus, unwrap.onQueryTooltip, unwrap.onRealize, unwrap.onShow, unwrap.onStateFlagsChanged, unwrap.onUnmap, unwrap.onUnrealize
  }
  def init(v: Widget): Unit = {
    ()
  }
  ()
}