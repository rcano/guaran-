package guarana
package gtk
import util.*
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
  def init(v: TextView): Unit = {
    Widget.init(v)
  }
  def uninitialized(): TextView = {
    val res = new org.gnome.gtk.TextView()
    res.asInstanceOf[TextView]
  }
  def apply(acceptsTab: Opt[Boolean] = UnsetParam, bottomMargin: Opt[Int] = UnsetParam, cursorVisible: Opt[Boolean] = UnsetParam, editable: Opt[Boolean] = UnsetParam, extraMenu: Opt[org.gnome.gio.MenuModel | Null] = UnsetParam, hadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, hscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam, indent: Opt[Int] = UnsetParam, inputHints: Opt[java.util.Set[org.gnome.gtk.InputHints]] = UnsetParam, inputPurpose: Opt[org.gnome.gtk.InputPurpose] = UnsetParam, justification: Opt[org.gnome.gtk.Justification] = UnsetParam, leftMargin: Opt[Int] = UnsetParam, monospace: Opt[Boolean] = UnsetParam, overwrite: Opt[Boolean] = UnsetParam, pixelsAboveLines: Opt[Int] = UnsetParam, pixelsBelowLines: Opt[Int] = UnsetParam, pixelsInsideWrap: Opt[Int] = UnsetParam, rightMargin: Opt[Int] = UnsetParam, topMargin: Opt[Int] = UnsetParam, vadjustment: Opt[org.gnome.gtk.Adjustment | Null] = UnsetParam, vscrollPolicy: Opt[org.gnome.gtk.ScrollablePolicy] = UnsetParam, wrapMode: Opt[org.gnome.gtk.WrapMode] = UnsetParam): VarContextAction[TextView] = {
    val res = uninitialized()
    init(res)
    ifSet(acceptsTab, res.acceptsTab := _)
    ifSet(bottomMargin, res.bottomMargin := _)
    ifSet(cursorVisible, res.cursorVisible := _)
    ifSet(editable, res.editable := _)
    ifSet(extraMenu, res.extraMenu := _)
    ifSet(hadjustment, res.hadjustment := _)
    ifSet(hscrollPolicy, res.hscrollPolicy := _)
    ifSet(indent, res.indent := _)
    ifSet(inputHints, res.inputHints := _)
    ifSet(inputPurpose, res.inputPurpose := _)
    ifSet(justification, res.justification := _)
    ifSet(leftMargin, res.leftMargin := _)
    ifSet(monospace, res.monospace := _)
    ifSet(overwrite, res.overwrite := _)
    ifSet(pixelsAboveLines, res.pixelsAboveLines := _)
    ifSet(pixelsBelowLines, res.pixelsBelowLines := _)
    ifSet(pixelsInsideWrap, res.pixelsInsideWrap := _)
    ifSet(rightMargin, res.rightMargin := _)
    ifSet(topMargin, res.topMargin := _)
    ifSet(vadjustment, res.vadjustment := _)
    ifSet(vscrollPolicy, res.vscrollPolicy := _)
    ifSet(wrapMode, res.wrapMode := _)
    res
  }
}