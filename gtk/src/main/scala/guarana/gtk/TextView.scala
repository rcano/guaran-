
package guarana
package gtk

import guarana.util.*

opaque type TextView <: guarana.gtk.Widget  = org.gnome.gtk.TextView & guarana.gtk.Widget
object TextView extends VarsMap, internal.TextViewSupport {
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

  

  extension (v: TextView) {
    def unwrap: org.gnome.gtk.TextView = v

    def acceptsTab: Var.Aux[Boolean, v.type] = guarana.gtk.TextView.AcceptsTab.asInstanceOf[Var.Aux[Boolean, v.type]]
    def bottomMargin: Var.Aux[Int, v.type] = guarana.gtk.TextView.BottomMargin.asInstanceOf[Var.Aux[Int, v.type]]
    def cursorVisible: Var.Aux[Boolean, v.type] = guarana.gtk.TextView.CursorVisible.asInstanceOf[Var.Aux[Boolean, v.type]]
    def editable: Var.Aux[Boolean, v.type] = guarana.gtk.TextView.Editable.asInstanceOf[Var.Aux[Boolean, v.type]]
    def extraMenu: Var.Aux[org.gnome.gio.MenuModel | Null, v.type] = guarana.gtk.TextView.ExtraMenu.asInstanceOf[Var.Aux[org.gnome.gio.MenuModel | Null, v.type]]
    def hadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = guarana.gtk.TextView.Hadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def hscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = guarana.gtk.TextView.HscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    def indent: Var.Aux[Int, v.type] = guarana.gtk.TextView.Indent.asInstanceOf[Var.Aux[Int, v.type]]
    def inputHints: Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type] = guarana.gtk.TextView.InputHints.asInstanceOf[Var.Aux[java.util.Set[org.gnome.gtk.InputHints], v.type]]
    def inputPurpose: Var.Aux[org.gnome.gtk.InputPurpose, v.type] = guarana.gtk.TextView.InputPurpose.asInstanceOf[Var.Aux[org.gnome.gtk.InputPurpose, v.type]]
    def justification: Var.Aux[org.gnome.gtk.Justification, v.type] = guarana.gtk.TextView.Justification.asInstanceOf[Var.Aux[org.gnome.gtk.Justification, v.type]]
    def leftMargin: Var.Aux[Int, v.type] = guarana.gtk.TextView.LeftMargin.asInstanceOf[Var.Aux[Int, v.type]]
    def monospace: Var.Aux[Boolean, v.type] = guarana.gtk.TextView.Monospace.asInstanceOf[Var.Aux[Boolean, v.type]]
    def overwrite: Var.Aux[Boolean, v.type] = guarana.gtk.TextView.Overwrite.asInstanceOf[Var.Aux[Boolean, v.type]]
    def pixelsAboveLines: Var.Aux[Int, v.type] = guarana.gtk.TextView.PixelsAboveLines.asInstanceOf[Var.Aux[Int, v.type]]
    def pixelsBelowLines: Var.Aux[Int, v.type] = guarana.gtk.TextView.PixelsBelowLines.asInstanceOf[Var.Aux[Int, v.type]]
    def pixelsInsideWrap: Var.Aux[Int, v.type] = guarana.gtk.TextView.PixelsInsideWrap.asInstanceOf[Var.Aux[Int, v.type]]
    def rightMargin: Var.Aux[Int, v.type] = guarana.gtk.TextView.RightMargin.asInstanceOf[Var.Aux[Int, v.type]]
    def topMargin: Var.Aux[Int, v.type] = guarana.gtk.TextView.TopMargin.asInstanceOf[Var.Aux[Int, v.type]]
    def vadjustment: Var.Aux[org.gnome.gtk.Adjustment | Null, v.type] = guarana.gtk.TextView.Vadjustment.asInstanceOf[Var.Aux[org.gnome.gtk.Adjustment | Null, v.type]]
    def vscrollPolicy: Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type] = guarana.gtk.TextView.VscrollPolicy.asInstanceOf[Var.Aux[org.gnome.gtk.ScrollablePolicy, v.type]]
    def wrapMode: Var.Aux[org.gnome.gtk.WrapMode, v.type] = guarana.gtk.TextView.WrapMode.asInstanceOf[Var.Aux[org.gnome.gtk.WrapMode, v.type]]

    

    export unwrap.{
      onBackspace,
      onCopyClipboard,
      onCutClipboard,
      onDeleteFromCursor,
      onDestroy,
      onDirectionChanged,
      onExtendSelection,
      onHide,
      onInsertAtCursor,
      onInsertEmoji,
      onKeynavFailed,
      onMap,
      onMnemonicActivate,
      onMoveCursor,
      onMoveFocus,
      onMoveViewport,
      onNotify,
      onPasteClipboard,
      onPreeditChanged,
      onQueryTooltip,
      onRealize,
      onSelectAll,
      onSetAnchor,
      onShow,
      onStateFlagsChanged,
      onToggleCursorVisible,
      onToggleOverwrite,
      onUnmap,
      onUnrealize
    }
  }

  def wrap(v: org.gnome.gtk.TextView): TextView = 
    val res = v.asInstanceOf[TextView]
    
    res

  def init(v: TextView): Unit = {
    guarana.gtk.Widget.init(v)
    connectVarsListener(v)
    
  }
  def uninitialized(): TextView = {
    val res = new org.gnome.gtk.TextView()
    
    res.asInstanceOf[TextView]
  }
  
  def apply(
    
    acceptsTab: Opt[Binding[Boolean]] = UnsetParam,
    bottomMargin: Opt[Binding[Int]] = UnsetParam,
    canFocus: Opt[Binding[Boolean]] = UnsetParam,
    canTarget: Opt[Binding[Boolean]] = UnsetParam,
    childVisible: Opt[Binding[Boolean]] = UnsetParam,
    cssClasses: Opt[Binding[Array[String]]] = UnsetParam,
    cursor: Opt[Binding[org.gnome.gdk.Cursor | Null]] = UnsetParam,
    cursorVisible: Opt[Binding[Boolean]] = UnsetParam,
    direction: Opt[Binding[org.gnome.gtk.TextDirection]] = UnsetParam,
    editable: Opt[Binding[Boolean]] = UnsetParam,
    extraMenu: Opt[Binding[org.gnome.gio.MenuModel | Null]] = UnsetParam,
    focusChild: Opt[Binding[guarana.gtk.Widget | Null]] = UnsetParam,
    focusOnClick: Opt[Binding[Boolean]] = UnsetParam,
    focusable: Opt[Binding[Boolean]] = UnsetParam,
    fontMap: Opt[Binding[org.gnome.pango.FontMap | Null]] = UnsetParam,
    fontOptions: Opt[Binding[org.freedesktop.cairo.FontOptions | Null]] = UnsetParam,
    hadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam,
    halign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    hasTooltip: Opt[Binding[Boolean]] = UnsetParam,
    hexpand: Opt[Binding[Boolean]] = UnsetParam,
    hexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    hscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam,
    indent: Opt[Binding[Int]] = UnsetParam,
    inputHints: Opt[Binding[java.util.Set[org.gnome.gtk.InputHints]]] = UnsetParam,
    inputPurpose: Opt[Binding[org.gnome.gtk.InputPurpose]] = UnsetParam,
    justification: Opt[Binding[org.gnome.gtk.Justification]] = UnsetParam,
    layoutManager: Opt[Binding[org.gnome.gtk.LayoutManager | Null]] = UnsetParam,
    leftMargin: Opt[Binding[Int]] = UnsetParam,
    limitEvents: Opt[Binding[Boolean]] = UnsetParam,
    marginBottom: Opt[Binding[Int]] = UnsetParam,
    marginEnd: Opt[Binding[Int]] = UnsetParam,
    marginStart: Opt[Binding[Int]] = UnsetParam,
    marginTop: Opt[Binding[Int]] = UnsetParam,
    monospace: Opt[Binding[Boolean]] = UnsetParam,
    name: Opt[Binding[java.lang.String]] = UnsetParam,
    opacity: Opt[Binding[Double]] = UnsetParam,
    overflow: Opt[Binding[org.gnome.gtk.Overflow]] = UnsetParam,
    overwrite: Opt[Binding[Boolean]] = UnsetParam,
    pixelsAboveLines: Opt[Binding[Int]] = UnsetParam,
    pixelsBelowLines: Opt[Binding[Int]] = UnsetParam,
    pixelsInsideWrap: Opt[Binding[Int]] = UnsetParam,
    receivesDefault: Opt[Binding[Boolean]] = UnsetParam,
    rightMargin: Opt[Binding[Int]] = UnsetParam,
    sensitive: Opt[Binding[Boolean]] = UnsetParam,
    tooltipMarkup: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    tooltipText: Opt[Binding[java.lang.String | Null]] = UnsetParam,
    topMargin: Opt[Binding[Int]] = UnsetParam,
    vadjustment: Opt[Binding[org.gnome.gtk.Adjustment | Null]] = UnsetParam,
    valign: Opt[Binding[org.gnome.gtk.Align]] = UnsetParam,
    vexpand: Opt[Binding[Boolean]] = UnsetParam,
    vexpandSet: Opt[Binding[Boolean]] = UnsetParam,
    visible: Opt[Binding[Boolean]] = UnsetParam,
    vscrollPolicy: Opt[Binding[org.gnome.gtk.ScrollablePolicy]] = UnsetParam,
    wrapMode: Opt[Binding[org.gnome.gtk.WrapMode]] = UnsetParam
  ): VarContextAction[TextView] = {
    val res = uninitialized()
    guarana.gtk.TextView.init(res)
    ifSet(acceptsTab, res.acceptsTab := _)
    ifSet(bottomMargin, res.bottomMargin := _)
    ifSet(canFocus, res.canFocus := _)
    ifSet(canTarget, res.canTarget := _)
    ifSet(childVisible, res.childVisible := _)
    ifSet(cssClasses, res.cssClasses := _)
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
        