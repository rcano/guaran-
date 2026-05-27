package guarana
package gtk
opaque type TextView <: Widget = org.gnome.gtk.TextView & Widget
object TextView {
  val AcceptsTab: ExternalVar.Aux[TextView, Boolean] = ExternalVar[TextView, Boolean]("accepts-tab", _.getAcceptsTab(), _.setAcceptsTab(_), true)
  val BottomMargin: ExternalVar.Aux[TextView, Int] = ExternalVar[TextView, Int]("bottom-margin", _.getBottomMargin(), _.setBottomMargin(_), true)
  val CursorVisible: ExternalVar.Aux[TextView, Boolean] = ExternalVar[TextView, Boolean]("cursor-visible", _.getCursorVisible(), _.setCursorVisible(_), true)
  val Editable: ExternalVar.Aux[TextView, Boolean] = ExternalVar[TextView, Boolean]("editable", _.getEditable(), _.setEditable(_), true)
  val ExtraMenu: ExternalVar.Aux[TextView, org.gnome.gio.MenuModel | Null] = ExternalVar[TextView, org.gnome.gio.MenuModel | Null]("extra-menu", _.getExtraMenu(), _.setExtraMenu(_), true)
  val Hadjustment: ExternalVar.Aux[TextView, org.gnome.gtk.Adjustment | Null] = ExternalVar[TextView, org.gnome.gtk.Adjustment | Null]("hadjustment", _.getHadjustment(), _.setHadjustment(_), true)
  val HscrollPolicy: ExternalVar.Aux[TextView, org.gnome.gtk.ScrollablePolicy] = ExternalVar[TextView, org.gnome.gtk.ScrollablePolicy]("hscroll-policy", _.getHscrollPolicy(), _.setHscrollPolicy(_), true)
  val Indent: ExternalVar.Aux[TextView, Int] = ExternalVar[TextView, Int]("indent", _.getIndent(), _.setIndent(_), true)
  val InputHints: ExternalVar.Aux[TextView, java.util.Set[org.gnome.gtk.InputHints]] = ExternalVar[TextView, java.util.Set[org.gnome.gtk.InputHints]]("input-hints", _.getInputHints(), _.setInputHints(_), true)
  val InputPurpose: ExternalVar.Aux[TextView, org.gnome.gtk.InputPurpose] = ExternalVar[TextView, org.gnome.gtk.InputPurpose]("input-purpose", _.getInputPurpose(), _.setInputPurpose(_), true)
  val Justification: ExternalVar.Aux[TextView, org.gnome.gtk.Justification] = ExternalVar[TextView, org.gnome.gtk.Justification]("justification", _.getJustification(), _.setJustification(_), true)
  val LeftMargin: ExternalVar.Aux[TextView, Int] = ExternalVar[TextView, Int]("left-margin", _.getLeftMargin(), _.setLeftMargin(_), true)
  val Monospace: ExternalVar.Aux[TextView, Boolean] = ExternalVar[TextView, Boolean]("monospace", _.getMonospace(), _.setMonospace(_), true)
  val Overwrite: ExternalVar.Aux[TextView, Boolean] = ExternalVar[TextView, Boolean]("overwrite", _.getOverwrite(), _.setOverwrite(_), true)
  val PixelsAboveLines: ExternalVar.Aux[TextView, Int] = ExternalVar[TextView, Int]("pixels-above-lines", _.getPixelsAboveLines(), _.setPixelsAboveLines(_), true)
  val PixelsBelowLines: ExternalVar.Aux[TextView, Int] = ExternalVar[TextView, Int]("pixels-below-lines", _.getPixelsBelowLines(), _.setPixelsBelowLines(_), true)
  val PixelsInsideWrap: ExternalVar.Aux[TextView, Int] = ExternalVar[TextView, Int]("pixels-inside-wrap", _.getPixelsInsideWrap(), _.setPixelsInsideWrap(_), true)
  val RightMargin: ExternalVar.Aux[TextView, Int] = ExternalVar[TextView, Int]("right-margin", _.getRightMargin(), _.setRightMargin(_), true)
  val TopMargin: ExternalVar.Aux[TextView, Int] = ExternalVar[TextView, Int]("top-margin", _.getTopMargin(), _.setTopMargin(_), true)
  val Vadjustment: ExternalVar.Aux[TextView, org.gnome.gtk.Adjustment | Null] = ExternalVar[TextView, org.gnome.gtk.Adjustment | Null]("vadjustment", _.getVadjustment(), _.setVadjustment(_), true)
  val VscrollPolicy: ExternalVar.Aux[TextView, org.gnome.gtk.ScrollablePolicy] = ExternalVar[TextView, org.gnome.gtk.ScrollablePolicy]("vscroll-policy", _.getVscrollPolicy(), _.setVscrollPolicy(_), true)
  val WrapMode: ExternalVar.Aux[TextView, org.gnome.gtk.WrapMode] = ExternalVar[TextView, org.gnome.gtk.WrapMode]("wrap-mode", _.getWrapMode(), _.setWrapMode(_), true)
  ()
  extension (v: TextView) {
    def unwrap: org.gnome.gtk.TextView = v
    export unwrap.onBackspace, unwrap.onCopyClipboard, unwrap.onCutClipboard, unwrap.onDeleteFromCursor, unwrap.onExtendSelection, unwrap.onInsertAtCursor, unwrap.onInsertEmoji, unwrap.onMoveCursor, unwrap.onMoveViewport, unwrap.onPasteClipboard, unwrap.onPreeditChanged, unwrap.onSelectAll, unwrap.onSetAnchor, unwrap.onToggleCursorVisible, unwrap.onToggleOverwrite
  }
  def init(v: TextView): Unit = {
    Widget.init(v)
  }
  def uninitialized(): TextView = {
    val res = new org.gnome.gtk.TextView()
    res.asInstanceOf[TextView]
  }
}