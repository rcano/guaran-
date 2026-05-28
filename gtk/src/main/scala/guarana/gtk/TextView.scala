package guarana
package gtk
import util.*
opaque type TextView <: Widget = org.gnome.gtk.TextView & Widget
object TextView extends VarsMap {
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
    def acceptsTab: Var.Aux[Boolean, v.type] = AcceptsTab.asInstanceOf[Var.Aux[Boolean, v.type]]
    def bottomMargin: Var.Aux[Int, v.type] = BottomMargin.asInstanceOf[Var.Aux[Int, v.type]]
    def cursorVisible: Var.Aux[Boolean, v.type] = CursorVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    def editable: Var.Aux[Boolean, v.type] = Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def extraMenu: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = ExtraMenu.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def hadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = Hadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def hscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = HscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    def indent: Var.Aux[Int, v.type] = Indent.asInstanceOf[Var.Aux[Int, v.type]]
    def inputHints: Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type] = InputHints.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type]]
    def inputPurpose: Var.Aux[org.gnome.gtk.InputPurpose, v.type] = InputPurpose.asInstanceOf[Var.Aux[org.gnome.gtk.InputPurpose, v.type]]
    def justification: Var.Aux[org.gnome.gtk.Justification, v.type] = Justification.asInstanceOf[Var.Aux[org.gnome.gtk.Justification, v.type]]
    def leftMargin: Var.Aux[Int, v.type] = LeftMargin.asInstanceOf[Var.Aux[Int, v.type]]
    def monospace: Var.Aux[Boolean, v.type] = Monospace.asInstanceOf[Var.Aux[Boolean, v.type]]
    def overwrite: Var.Aux[Boolean, v.type] = Overwrite.asInstanceOf[Var.Aux[Boolean, v.type]]
    def pixelsAboveLines: Var.Aux[Int, v.type] = PixelsAboveLines.asInstanceOf[Var.Aux[Int, v.type]]
    def pixelsBelowLines: Var.Aux[Int, v.type] = PixelsBelowLines.asInstanceOf[Var.Aux[Int, v.type]]
    def pixelsInsideWrap: Var.Aux[Int, v.type] = PixelsInsideWrap.asInstanceOf[Var.Aux[Int, v.type]]
    def rightMargin: Var.Aux[Int, v.type] = RightMargin.asInstanceOf[Var.Aux[Int, v.type]]
    def topMargin: Var.Aux[Int, v.type] = TopMargin.asInstanceOf[Var.Aux[Int, v.type]]
    def vadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = Vadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def vscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = VscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    def wrapMode: Var.Aux[org.gnome.gtk.WrapMode, v.type] = WrapMode.asInstanceOf[Var.Aux[org.gnome.gtk.WrapMode, v.type]]
    export unwrap.onBackspace, unwrap.onCopyClipboard, unwrap.onCutClipboard, unwrap.onDeleteFromCursor, unwrap.onExtendSelection, unwrap.onInsertAtCursor, unwrap.onInsertEmoji, unwrap.onMoveCursor, unwrap.onMoveViewport, unwrap.onPasteClipboard, unwrap.onPreeditChanged, unwrap.onSelectAll, unwrap.onSetAnchor, unwrap.onToggleCursorVisible, unwrap.onToggleOverwrite
  }
  def _wrap(v: org.gnome.gtk.TextView): TextView = {
    v.asInstanceOf
  }
  def init(v: TextView): ToolkitAction[Toolkit, Unit] = {
    Widget.init(v)
    connectVarsListener(v)
  }
  def uninitialized(): TextView = {
    val res = new org.gnome.gtk.TextView()
    res.asInstanceOf[TextView]
  }
  def apply(acceptsTab: Opt[Boolean] = UnsetParam, bottomMargin: Opt[Int] = UnsetParam, canFocus: Opt[Boolean] = UnsetParam, canTarget: Opt[Boolean] = UnsetParam, childVisible: Opt[Boolean] = UnsetParam, cursor: Opt[org.gnome.gdk.Cursor | Null] = UnsetParam, cursorVisible: Opt[Boolean] = UnsetParam, direction: Opt[org.gnome.gtk.TextDirection] = UnsetParam, editable: Opt[Boolean] = UnsetParam, extraMenu: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam, focusChild: Opt[org.gnome.gtk.Widget | Null] = UnsetParam, focusOnClick: Opt[Boolean] = UnsetParam, focusable: Opt[Boolean] = UnsetParam, fontMap: Opt[org.gnome.pango.FontMap | Null] = UnsetParam, fontOptions: Opt[org.freedesktop.cairo.FontOptions | Null] = UnsetParam, hadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, halign: Opt[org.gnome.gtk.Align] = UnsetParam, hasTooltip: Opt[Boolean] = UnsetParam, hexpand: Opt[Boolean] = UnsetParam, hexpandSet: Opt[Boolean] = UnsetParam, hscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam, indent: Opt[Int] = UnsetParam, inputHints: Opt[java.util.Set[org.gnome.gtk.InputHints]] = UnsetParam, inputPurpose: Opt[org.gnome.gtk.InputPurpose] = UnsetParam, justification: Opt[org.gnome.gtk.Justification] = UnsetParam, layoutManager: Opt[org.gnome.gtk.LayoutManager | Null] = UnsetParam, leftMargin: Opt[Int] = UnsetParam, limitEvents: Opt[Boolean] = UnsetParam, marginBottom: Opt[Int] = UnsetParam, marginEnd: Opt[Int] = UnsetParam, marginStart: Opt[Int] = UnsetParam, marginTop: Opt[Int] = UnsetParam, monospace: Opt[Boolean] = UnsetParam, name: Opt[java.lang.String] = UnsetParam, opacity: Opt[Double] = UnsetParam, overflow: Opt[org.gnome.gtk.Overflow] = UnsetParam, overwrite: Opt[Boolean] = UnsetParam, pixelsAboveLines: Opt[Int] = UnsetParam, pixelsBelowLines: Opt[Int] = UnsetParam, pixelsInsideWrap: Opt[Int] = UnsetParam, receivesDefault: Opt[Boolean] = UnsetParam, rightMargin: Opt[Int] = UnsetParam, sensitive: Opt[Boolean] = UnsetParam, tooltipMarkup: Opt[java.lang.String | Null] = UnsetParam, tooltipText: Opt[java.lang.String | Null] = UnsetParam, topMargin: Opt[Int] = UnsetParam, vadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, valign: Opt[org.gnome.gtk.Align] = UnsetParam, vexpand: Opt[Boolean] = UnsetParam, vexpandSet: Opt[Boolean] = UnsetParam, visible: Opt[Boolean] = UnsetParam, vscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam, wrapMode: Opt[org.gnome.gtk.WrapMode] = UnsetParam): ToolkitAction[Toolkit, TextView] = {
    val res = uninitialized()
    init(res)
    ifSet(acceptsTab, res.acceptsTab := _)
    ifSet(bottomMargin, res.bottomMargin := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cursor, res.cursor := _)
    ifSet(cursorVisible, res.cursorVisible := _)
    ifSet(direction, res.direction := _)
    ifSet(editable, res.editable := _)
    ifSet(extraMenu, res.extraMenu := _)
    ifSet(focusChild, res.focusChild := _)
    ifSet(focusOnClick, res.focusOnClick := _)
    ifSet(focusable, res.focusable := _)
    ifSet(fontMap, res.fontMap := _)
    ifSet(fontOptions, res.fontOptions := _)
    ifSet(hadjustment, res.hadjustment := _)
    ifSet(halign, res.halign := _)
    ifSet(hasTooltip, res.hasTooltip := _)
    ifSet(hexpand, res.hexpand := _)
    ifSet(hexpandSet, res.hexpandSet := _)
    ifSet(hscrollPolicy, res.hscrollPolicy := _)
    ifSet(indent, res.indent := _)
    ifSet(inputHints, res.inputHints := _)
    ifSet(inputPurpose, res.inputPurpose := _)
    ifSet(justification, res.justification := _)
    ifSet(layoutManager, res.layoutManager := _)
    ifSet(leftMargin, res.leftMargin := _)
    ifSet(limitEvents, res.limitEvents := _)
    ifSet(marginBottom, res.marginBottom := _)
    ifSet(marginEnd, res.marginEnd := _)
    ifSet(marginStart, res.marginStart := _)
    ifSet(marginTop, res.marginTop := _)
    ifSet(monospace, res.monospace := _)
    ifSet(name, res.name := _)
    ifSet(opacity, res.opacity := _)
    ifSet(overflow, res.overflow := _)
    ifSet(overwrite, res.overwrite := _)
    ifSet(pixelsAboveLines, res.pixelsAboveLines := _)
    ifSet(pixelsBelowLines, res.pixelsBelowLines := _)
    ifSet(pixelsInsideWrap, res.pixelsInsideWrap := _)
    ifSet(receivesDefault, res.receivesDefault := _)
    ifSet(rightMargin, res.rightMargin := _)
    ifSet(sensitive, res.sensitive := _)
    ifSet(tooltipMarkup, res.tooltipMarkup := _)
    ifSet(tooltipText, res.tooltipText := _)
    ifSet(topMargin, res.topMargin := _)
    ifSet(vadjustment, res.vadjustment := _)
    ifSet(valign, res.valign := _)
    ifSet(vexpand, res.vexpand := _)
    ifSet(vexpandSet, res.vexpandSet := _)
    ifSet(visible, res.visible := _)
    ifSet(vscrollPolicy, res.vscrollPolicy := _)
    ifSet(wrapMode, res.wrapMode := _)
    res
  }
}